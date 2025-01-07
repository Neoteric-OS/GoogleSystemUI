package com.android.systemui.biometrics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EllipseOverlapDetectorParams {
    public final float minOverlap;
    public final int stepSize;
    public final float targetSize;

    public EllipseOverlapDetectorParams(int i, float f, float f2) {
        this.minOverlap = f;
        this.targetSize = f2;
        this.stepSize = i;
    }
}
