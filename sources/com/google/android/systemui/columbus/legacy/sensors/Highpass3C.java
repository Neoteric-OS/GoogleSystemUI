package com.google.android.systemui.columbus.legacy.sensors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Highpass3C {
    public final Highpass1C mHighpassX = new Highpass1C();
    public final Highpass1C mHighpassY = new Highpass1C();
    public final Highpass1C mHighpassZ = new Highpass1C();

    public final void setPara() {
        this.mHighpassX.mPara = 0.05f;
        this.mHighpassY.mPara = 0.05f;
        this.mHighpassZ.mPara = 0.05f;
    }
}
