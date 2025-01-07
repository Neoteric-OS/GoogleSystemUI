package com.android.systemui.controls.panels;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.UserHandle;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SelectedComponentRepositoryImpl {
    public final CoroutineDispatcher bgDispatcher;
    public final UserFileManager userFileManager;
    public final UserTracker userTracker;

    public SelectedComponentRepositoryImpl(UserFileManager userFileManager, UserTracker userTracker, CoroutineDispatcher coroutineDispatcher) {
        this.userFileManager = userFileManager;
        this.userTracker = userTracker;
        this.bgDispatcher = coroutineDispatcher;
    }

    public final SelectedComponentRepository$SelectedComponent getSelectedComponent(UserHandle userHandle) {
        SharedPreferences sharedPreferencesForUser = getSharedPreferencesForUser(userHandle.equals(UserHandle.CURRENT) ? ((UserTrackerImpl) this.userTracker).getUserId() : userHandle.getIdentifier());
        String string = sharedPreferencesForUser.getString("controls_component", null);
        if (string == null) {
            return null;
        }
        String string2 = sharedPreferencesForUser.getString("controls_structure", "");
        Intrinsics.checkNotNull(string2);
        return new SelectedComponentRepository$SelectedComponent(string2, ComponentName.unflattenFromString(string), sharedPreferencesForUser.getBoolean("controls_is_panel", false));
    }

    public final SharedPreferences getSharedPreferencesForUser(int i) {
        return ((UserFileManagerImpl) this.userFileManager).getSharedPreferences$1(i, "controls_prefs");
    }

    public final void removeSelectedComponent() {
        getSharedPreferencesForUser(((UserTrackerImpl) this.userTracker).getUserId()).edit().remove("controls_component").remove("controls_structure").remove("controls_is_panel").apply();
    }
}
