package com.android.systemui.bluetooth.qsdialog;

import java.util.Comparator;
import java.util.List;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceItemInteractor$sort$$inlined$compareBy$1 implements Comparator {
    public final /* synthetic */ List $displayPriority$inlined;

    public DeviceItemInteractor$sort$$inlined$compareBy$1(List list) {
        this.$displayPriority$inlined = list;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt___ComparisonsJvmKt.compareValues(Integer.valueOf(this.$displayPriority$inlined.indexOf(((DeviceItem) obj).type)), Integer.valueOf(this.$displayPriority$inlined.indexOf(((DeviceItem) obj2).type)));
    }
}
