package com.android.systemui.statusbar.pipeline.shared.data.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DataActivityModelKt {
    public static final DataActivityModel toMobileDataActivityModel(int i) {
        return i != 1 ? i != 2 ? i != 3 ? new DataActivityModel(false, false) : new DataActivityModel(true, true) : new DataActivityModel(false, true) : new DataActivityModel(true, false);
    }

    public static final DataActivityModel toWifiDataActivityModel(int i) {
        return i != 1 ? i != 2 ? i != 3 ? new DataActivityModel(false, false) : new DataActivityModel(true, true) : new DataActivityModel(false, true) : new DataActivityModel(true, false);
    }
}
