package com.android.systemui.keyguard.data.repository;

import com.android.systemui.keyguard.shared.model.KeyguardQuickAffordancePickerRepresentation;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceRepository$getAffordancePickerRepresentations$$inlined$sortedBy$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt___ComparisonsJvmKt.compareValues(((KeyguardQuickAffordancePickerRepresentation) obj).name, ((KeyguardQuickAffordancePickerRepresentation) obj2).name);
    }
}
