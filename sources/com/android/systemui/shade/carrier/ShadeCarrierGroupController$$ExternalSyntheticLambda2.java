package com.android.systemui.shade.carrier;

import com.android.systemui.shade.carrier.ShadeCarrierGroupController;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ShadeCarrierGroupController$$ExternalSyntheticLambda2 implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        int i = ((ShadeCarrierGroupController.IconData) obj).slotIndex;
        return i < 3 && i != -1;
    }
}
