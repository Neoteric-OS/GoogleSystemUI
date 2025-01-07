package com.android.systemui.shared.customization.data.content;

import android.net.Uri;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CustomizationProviderContract {
    public static final Uri BASE_URI = new Uri.Builder().scheme("content").authority("com.android.systemui.customization").build();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class LockScreenQuickAffordances {
        public static final Uri LOCK_SCREEN_QUICK_AFFORDANCE_BASE_URI = CustomizationProviderContract.BASE_URI.buildUpon().path("lockscreen_quickaffordance").build();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract class SelectionTable {
            public static final Uri URI = LockScreenQuickAffordances.LOCK_SCREEN_QUICK_AFFORDANCE_BASE_URI.buildUpon().appendPath("selections").build();
        }
    }
}
