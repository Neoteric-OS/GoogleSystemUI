package com.android.systemui;

import android.animation.ValueAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class FaceScanningOverlay$enableShowProtection$1$1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ FaceScanningOverlay $tmp0;

    public /* synthetic */ FaceScanningOverlay$enableShowProtection$1$1(FaceScanningOverlay faceScanningOverlay, int i) {
        this.$r8$classId = i;
        this.$tmp0 = faceScanningOverlay;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.$r8$classId) {
            case 0:
                FaceScanningOverlay faceScanningOverlay = this.$tmp0;
                faceScanningOverlay.getClass();
                faceScanningOverlay.cameraProtectionProgress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                faceScanningOverlay.invalidate();
                break;
            case 1:
                FaceScanningOverlay faceScanningOverlay2 = this.$tmp0;
                faceScanningOverlay2.getClass();
                faceScanningOverlay2.rimProgress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                faceScanningOverlay2.invalidate();
                break;
            case 2:
                FaceScanningOverlay faceScanningOverlay3 = this.$tmp0;
                faceScanningOverlay3.getClass();
                faceScanningOverlay3.rimProgress = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                faceScanningOverlay3.invalidate();
                break;
            default:
                FaceScanningOverlay faceScanningOverlay4 = this.$tmp0;
                faceScanningOverlay4.rimPaint.setAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
                faceScanningOverlay4.invalidate();
                break;
        }
    }
}
