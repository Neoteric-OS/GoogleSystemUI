package com.android.systemui.biometrics;

import android.graphics.RuntimeShader;
import android.util.MathUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DwellRippleShader extends RuntimeShader {
    public int color;
    public float maxRadius;
    public float progress;
    public float time;

    public final void setColor(int i) {
        this.color = i;
        setColorUniform("in_color", i);
    }

    public final void setProgress(float f) {
        this.progress = f;
        float f2 = 1;
        float f3 = f2 - f;
        setFloatUniform("in_radius", (f2 - ((f3 * f3) * f3)) * this.maxRadius);
        setFloatUniform("in_blur", MathUtils.lerp(1.0f, 0.7f, f));
    }

    public final void setTime(float f) {
        float f2 = f * 0.001f;
        this.time = f2;
        setFloatUniform("in_time", f2);
        setFloatUniform("in_phase1", (this.time * 3.0f) + 0.367f);
        setFloatUniform("in_phase2", this.time * 7.2f * 1.531f);
    }
}
