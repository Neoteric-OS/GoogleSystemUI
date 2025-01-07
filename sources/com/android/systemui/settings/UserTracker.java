package com.android.systemui.settings;

import android.content.Context;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface UserTracker extends UserContextProvider {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        default void onUserChanging(int i) {
        }

        default void onUserChanging(int i, Context context, Runnable runnable) {
            onUserChanging(i);
            runnable.run();
        }

        default void onBeforeUserSwitching(int i) {
        }

        default void onProfilesChanged(List list) {
        }

        default void onUserChanged(int i, Context context) {
        }
    }
}
