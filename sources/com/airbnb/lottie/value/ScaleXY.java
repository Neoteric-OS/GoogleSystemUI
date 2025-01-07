package com.airbnb.lottie.value;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScaleXY {
    public float scaleX;
    public float scaleY;

    public ScaleXY(float f, float f2) {
        this.scaleX = f;
        this.scaleY = f2;
    }

    public final String toString() {
        return this.scaleX + "x" + this.scaleY;
    }

    public ScaleXY() {
        this(1.0f, 1.0f);
    }
}
