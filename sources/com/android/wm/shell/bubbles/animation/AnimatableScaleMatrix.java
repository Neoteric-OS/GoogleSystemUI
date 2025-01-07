package com.android.wm.shell.bubbles.animation;

import android.graphics.Matrix;
import androidx.dynamicanimation.animation.FloatPropertyCompat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AnimatableScaleMatrix extends Matrix {
    public static final AnonymousClass1 SCALE_X;
    public static final AnonymousClass1 SCALE_Y;
    public float mScaleX = 1.0f;
    public float mScaleY = 1.0f;
    public float mPivotX = 0.0f;
    public float mPivotY = 0.0f;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.wm.shell.bubbles.animation.AnimatableScaleMatrix$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.wm.shell.bubbles.animation.AnimatableScaleMatrix$1] */
    static {
        final int i = 0;
        SCALE_X = new FloatPropertyCompat() { // from class: com.android.wm.shell.bubbles.animation.AnimatableScaleMatrix.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i) {
                    case 0:
                        return ((AnimatableScaleMatrix) obj).mScaleX * 499.99997f;
                    default:
                        return ((AnimatableScaleMatrix) obj).mScaleY * 499.99997f;
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i) {
                    case 0:
                        ((AnimatableScaleMatrix) obj).setScaleX(f * 0.002f);
                        break;
                    default:
                        ((AnimatableScaleMatrix) obj).setScaleY(f * 0.002f);
                        break;
                }
            }
        };
        final int i2 = 1;
        SCALE_Y = new FloatPropertyCompat() { // from class: com.android.wm.shell.bubbles.animation.AnimatableScaleMatrix.1
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final float getValue(Object obj) {
                switch (i2) {
                    case 0:
                        return ((AnimatableScaleMatrix) obj).mScaleX * 499.99997f;
                    default:
                        return ((AnimatableScaleMatrix) obj).mScaleY * 499.99997f;
                }
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public final void setValue(Object obj, float f) {
                switch (i2) {
                    case 0:
                        ((AnimatableScaleMatrix) obj).setScaleX(f * 0.002f);
                        break;
                    default:
                        ((AnimatableScaleMatrix) obj).setScaleY(f * 0.002f);
                        break;
                }
            }
        };
    }

    @Override // android.graphics.Matrix
    public final boolean equals(Object obj) {
        return obj == this;
    }

    @Override // android.graphics.Matrix
    public final void setScale(float f, float f2, float f3, float f4) {
        this.mScaleX = f;
        this.mScaleY = f2;
        this.mPivotX = f3;
        this.mPivotY = f4;
        super.setScale(f, f2, f3, f4);
    }

    public final void setScaleX(float f) {
        this.mScaleX = f;
        super.setScale(f, this.mScaleY, this.mPivotX, this.mPivotY);
    }

    public final void setScaleY(float f) {
        this.mScaleY = f;
        super.setScale(this.mScaleX, f, this.mPivotX, this.mPivotY);
    }
}
