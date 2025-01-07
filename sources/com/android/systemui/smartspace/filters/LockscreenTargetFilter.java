package com.android.systemui.smartspace.filters;

import android.app.smartspace.SmartspaceSession;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.UserInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.UserHandle;
import com.android.systemui.communal.smartspace.CommunalSmartspaceController$filterListener$1;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.concurrency.ExecutionImpl;
import com.android.systemui.util.settings.SecureSettings;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockscreenTargetFilter {
    public final ContentResolver contentResolver;
    public final ExecutionImpl execution;
    public UserHandle managedUserHandle;
    public final SecureSettings secureSettings;
    public final LockscreenTargetFilter$settingsObserver$1 settingsObserver;
    public boolean showSensitiveContentForCurrentUser;
    public boolean showSensitiveContentForManagedUser;
    public final Executor uiExecutor;
    public final UserTracker userTracker;
    public final Set listeners = new LinkedHashSet();
    public final LockscreenTargetFilter$userTrackerCallback$1 userTrackerCallback = new UserTracker.Callback() { // from class: com.android.systemui.smartspace.filters.LockscreenTargetFilter$userTrackerCallback$1
        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            LockscreenTargetFilter lockscreenTargetFilter = LockscreenTargetFilter.this;
            lockscreenTargetFilter.execution.assertIsMainThread();
            lockscreenTargetFilter.updateUserContentSettings();
        }
    };

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.smartspace.filters.LockscreenTargetFilter$settingsObserver$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.smartspace.filters.LockscreenTargetFilter$userTrackerCallback$1] */
    public LockscreenTargetFilter(SecureSettings secureSettings, UserTracker userTracker, ExecutionImpl executionImpl, final Handler handler, ContentResolver contentResolver, Executor executor) {
        this.secureSettings = secureSettings;
        this.userTracker = userTracker;
        this.execution = executionImpl;
        this.contentResolver = contentResolver;
        this.uiExecutor = executor;
        this.settingsObserver = new ContentObserver(handler) { // from class: com.android.systemui.smartspace.filters.LockscreenTargetFilter$settingsObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri) {
                LockscreenTargetFilter.this.execution.assertIsMainThread();
                LockscreenTargetFilter.this.updateUserContentSettings();
            }
        };
    }

    public final void updateUserContentSettings() {
        UserHandle userHandle;
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) this.userTracker;
        int userId = userTrackerImpl.getUserId();
        SecureSettings secureSettings = this.secureSettings;
        boolean z = secureSettings.getIntForUser("lock_screen_allow_private_notifications", 0, userId) == 1;
        boolean z2 = this.showSensitiveContentForCurrentUser;
        this.showSensitiveContentForCurrentUser = z;
        if (z2 != z) {
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                SmartspaceSession smartspaceSession = ((CommunalSmartspaceController$filterListener$1) it.next()).this$0.session;
                if (smartspaceSession != null) {
                    smartspaceSession.requestSmartspaceUpdate();
                }
            }
        }
        Iterator it2 = userTrackerImpl.getUserProfiles().iterator();
        while (true) {
            if (!it2.hasNext()) {
                userHandle = null;
                break;
            }
            UserInfo userInfo = (UserInfo) it2.next();
            if (userInfo.isManagedProfile()) {
                userHandle = userInfo.getUserHandle();
                break;
            }
        }
        this.managedUserHandle = userHandle;
        Integer valueOf = userHandle != null ? Integer.valueOf(userHandle.getIdentifier()) : null;
        if (valueOf != null) {
            boolean z3 = secureSettings.getIntForUser("lock_screen_allow_private_notifications", 0, valueOf.intValue()) == 1;
            boolean z4 = this.showSensitiveContentForManagedUser;
            this.showSensitiveContentForManagedUser = z3;
            if (z4 != z3) {
                Iterator it3 = this.listeners.iterator();
                while (it3.hasNext()) {
                    SmartspaceSession smartspaceSession2 = ((CommunalSmartspaceController$filterListener$1) it3.next()).this$0.session;
                    if (smartspaceSession2 != null) {
                        smartspaceSession2.requestSmartspaceUpdate();
                    }
                }
            }
        }
        Iterator it4 = this.listeners.iterator();
        while (it4.hasNext()) {
            SmartspaceSession smartspaceSession3 = ((CommunalSmartspaceController$filterListener$1) it4.next()).this$0.session;
            if (smartspaceSession3 != null) {
                smartspaceSession3.requestSmartspaceUpdate();
            }
        }
    }
}
