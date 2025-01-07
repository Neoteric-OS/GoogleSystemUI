package com.android.systemui.statusbar;

import android.util.SparseArray;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface NotificationLockscreenUserManager {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface UserChangedListener {
        default void onCurrentProfilesChanged(SparseArray sparseArray) {
        }

        default void onUserChanged(int i) {
        }

        default void onUserRemoved(int i) {
        }
    }
}
