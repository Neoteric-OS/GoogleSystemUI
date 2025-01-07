package com.android.systemui.controls.panels;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.UserHandle;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.wm.shell.R;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.collections.EmptySet;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthorizedPanelsRepositoryImpl implements AuthorizedPanelsRepository {
    public final Context context;
    public final UserFileManager userFileManager;
    public final UserTracker userTracker;

    public AuthorizedPanelsRepositoryImpl(Context context, UserFileManager userFileManager, UserTracker userTracker) {
        this.context = context;
        this.userFileManager = userFileManager;
        this.userTracker = userTracker;
    }

    public final void addAuthorizedPanels(Set set) {
        SharedPreferences instantiateSharedPrefs = instantiateSharedPrefs(((UserTrackerImpl) this.userTracker).getUserHandle());
        Set<String> stringSet = instantiateSharedPrefs.getStringSet("authorized_panels", EmptySet.INSTANCE);
        Intrinsics.checkNotNull(stringSet);
        instantiateSharedPrefs.edit().putStringSet("authorized_panels", SetsKt.plus((Set) stringSet, (Iterable) set)).apply();
    }

    public final SharedPreferences instantiateSharedPrefs(UserHandle userHandle) {
        SharedPreferences sharedPreferences$1 = ((UserFileManagerImpl) this.userFileManager).getSharedPreferences$1(userHandle.getIdentifier(), "controls_prefs");
        if (sharedPreferences$1.getStringSet("authorized_panels", null) == null) {
            sharedPreferences$1.edit().putStringSet("authorized_panels", ArraysKt.toSet(this.context.getResources().getStringArray(R.array.config_controlsPreferredPackages))).apply();
        }
        return sharedPreferences$1;
    }
}
