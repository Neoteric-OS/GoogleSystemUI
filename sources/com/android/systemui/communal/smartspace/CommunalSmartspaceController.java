package com.android.systemui.communal.smartspace;

import android.app.smartspace.SmartspaceConfig;
import android.app.smartspace.SmartspaceManager;
import android.app.smartspace.SmartspaceSession;
import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.SmartspaceTargetEvent;
import android.content.ContentResolver;
import android.content.Context;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.smartspace.filters.LockscreenTargetFilter;
import com.android.systemui.smartspace.preconditions.LockscreenPrecondition;
import com.android.systemui.util.concurrency.ExecutionImpl;
import com.android.systemui.util.settings.SecureSettingsImpl;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalSmartspaceController {
    public final Context context;
    public final ExecutionImpl execution;
    public final Set listeners;
    public final BcSmartspaceDataPlugin plugin;
    public final LockscreenPrecondition precondition;
    public SmartspaceSession session;
    public final CommunalSmartspaceController$sessionListener$1 sessionListener;
    public final SmartspaceManager smartspaceManager;
    public final LockscreenTargetFilter targetFilter;
    public final Executor uiExecutor;

    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.communal.smartspace.CommunalSmartspaceController$sessionListener$1] */
    public CommunalSmartspaceController(Context context, SmartspaceManager smartspaceManager, ExecutionImpl executionImpl, Executor executor, LockscreenPrecondition lockscreenPrecondition, Optional optional, Optional optional2) {
        this.context = context;
        this.smartspaceManager = smartspaceManager;
        this.execution = executionImpl;
        this.uiExecutor = executor;
        this.precondition = lockscreenPrecondition;
        this.plugin = (BcSmartspaceDataPlugin) optional2.orElse(null);
        LockscreenTargetFilter lockscreenTargetFilter = (LockscreenTargetFilter) optional.orElse(null);
        this.targetFilter = lockscreenTargetFilter;
        this.listeners = new LinkedHashSet();
        CommunalSmartspaceController$preconditionListener$1 communalSmartspaceController$preconditionListener$1 = new CommunalSmartspaceController$preconditionListener$1(this);
        synchronized (lockscreenPrecondition.listeners) {
            lockscreenPrecondition.listeners.add(communalSmartspaceController$preconditionListener$1);
        }
        if (this.session != null || this.listeners.isEmpty()) {
            SmartspaceSession smartspaceSession = this.session;
            if (smartspaceSession != null) {
                smartspaceSession.requestSmartspaceUpdate();
            }
        } else {
            Log.d("CommunalSmartspaceCtrlr", "Precondition criteria changed. Attempting to connect session.");
            connectSession();
        }
        CommunalSmartspaceController$filterListener$1 communalSmartspaceController$filterListener$1 = new CommunalSmartspaceController$filterListener$1(this);
        if (lockscreenTargetFilter != null) {
            lockscreenTargetFilter.listeners.add(communalSmartspaceController$filterListener$1);
            if (lockscreenTargetFilter.listeners.size() == 1) {
                ((UserTrackerImpl) lockscreenTargetFilter.userTracker).addCallback(lockscreenTargetFilter.userTrackerCallback, lockscreenTargetFilter.uiExecutor);
                ContentResolver contentResolver = lockscreenTargetFilter.contentResolver;
                ((SecureSettingsImpl) lockscreenTargetFilter.secureSettings).getClass();
                contentResolver.registerContentObserver(Settings.Secure.getUriFor("lock_screen_allow_private_notifications"), true, lockscreenTargetFilter.settingsObserver, -1);
                lockscreenTargetFilter.updateUserContentSettings();
            }
        }
        this.sessionListener = new SmartspaceSession.OnTargetsAvailableListener() { // from class: com.android.systemui.communal.smartspace.CommunalSmartspaceController$sessionListener$1
            public final void onTargetsAvailable(List list) {
                CommunalSmartspaceController.this.execution.assertIsMainThread();
                CommunalSmartspaceController communalSmartspaceController = CommunalSmartspaceController.this;
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    SmartspaceTarget smartspaceTarget = (SmartspaceTarget) obj;
                    LockscreenTargetFilter lockscreenTargetFilter2 = communalSmartspaceController.targetFilter;
                    if (lockscreenTargetFilter2 != null) {
                        Intrinsics.checkNotNull(smartspaceTarget);
                        UserHandle userHandle = smartspaceTarget.getUserHandle();
                        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) lockscreenTargetFilter2.userTracker;
                        if (Intrinsics.areEqual(userHandle, userTrackerImpl.getUserHandle())) {
                            if (smartspaceTarget.isSensitive() && !lockscreenTargetFilter2.showSensitiveContentForCurrentUser) {
                            }
                        } else if (Intrinsics.areEqual(userHandle, lockscreenTargetFilter2.managedUserHandle)) {
                            if (userTrackerImpl.getUserHandle().getIdentifier() == 0) {
                                if (smartspaceTarget.isSensitive() && !lockscreenTargetFilter2.showSensitiveContentForManagedUser) {
                                }
                            }
                        }
                    }
                    arrayList.add(obj);
                }
                BcSmartspaceDataPlugin bcSmartspaceDataPlugin = CommunalSmartspaceController.this.plugin;
                if (bcSmartspaceDataPlugin != null) {
                    bcSmartspaceDataPlugin.onTargetsAvailable(arrayList);
                }
            }
        };
    }

    public final void connectSession() {
        BcSmartspaceDataPlugin bcSmartspaceDataPlugin;
        if (this.smartspaceManager == null || (bcSmartspaceDataPlugin = this.plugin) == null || this.session != null || this.listeners.isEmpty()) {
            return;
        }
        LockscreenPrecondition lockscreenPrecondition = this.precondition;
        lockscreenPrecondition.execution.assertIsMainThread();
        if (lockscreenPrecondition.deviceReady) {
            SmartspaceSession createSmartspaceSession = this.smartspaceManager.createSmartspaceSession(new SmartspaceConfig.Builder(this.context, BcSmartspaceDataPlugin.UI_SURFACE_GLANCEABLE_HUB).build());
            Log.d("CommunalSmartspaceCtrlr", "Starting smartspace session for communal");
            createSmartspaceSession.addOnTargetsAvailableListener(this.uiExecutor, this.sessionListener);
            this.session = createSmartspaceSession;
            bcSmartspaceDataPlugin.registerSmartspaceEventNotifier(new BcSmartspaceDataPlugin.SmartspaceEventNotifier() { // from class: com.android.systemui.communal.smartspace.CommunalSmartspaceController$connectSession$1
                @Override // com.android.systemui.plugins.BcSmartspaceDataPlugin.SmartspaceEventNotifier
                public final void notifySmartspaceEvent(SmartspaceTargetEvent smartspaceTargetEvent) {
                    SmartspaceSession smartspaceSession = CommunalSmartspaceController.this.session;
                    if (smartspaceSession != null) {
                        smartspaceSession.notifySmartspaceEvent(smartspaceTargetEvent);
                    }
                }
            });
            SmartspaceSession smartspaceSession = this.session;
            if (smartspaceSession != null) {
                smartspaceSession.requestSmartspaceUpdate();
            }
        }
    }
}
