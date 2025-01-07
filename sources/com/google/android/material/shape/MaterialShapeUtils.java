package com.google.android.material.shape;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MaterialShapeUtils {
    public static CornerTreatment createCornerTreatment(int i) {
        return i != 0 ? i != 1 ? new RoundedCornerTreatment() : new CutCornerTreatment() : new RoundedCornerTreatment();
    }
}
