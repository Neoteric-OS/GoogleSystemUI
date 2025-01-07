package com.android.systemui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceScanningOverlay$enableShowProtection$1$2 extends AnimatorListenerAdapter {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ FaceScanningOverlay this$0;

    public /* synthetic */ FaceScanningOverlay$enableShowProtection$1$2(FaceScanningOverlay faceScanningOverlay, int i) {
        this.$r8$classId = i;
        this.this$0 = faceScanningOverlay;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                FaceScanningOverlay faceScanningOverlay = this.this$0;
                faceScanningOverlay.cameraProtectionAnimator = null;
                if (!faceScanningOverlay.showScanningAnim) {
                    faceScanningOverlay.setVisibility(4);
                    ScreenDecorations$$ExternalSyntheticLambda7 screenDecorations$$ExternalSyntheticLambda7 = faceScanningOverlay.hideOverlayRunnable;
                    if (screenDecorations$$ExternalSyntheticLambda7 != null) {
                        screenDecorations$$ExternalSyntheticLambda7.run();
                    }
                    faceScanningOverlay.hideOverlayRunnable = null;
                    faceScanningOverlay.requestLayout();
                    break;
                }
                break;
            case 1:
                FaceScanningOverlay faceScanningOverlay2 = this.this$0;
                faceScanningOverlay2.rimProgress = 0.5f;
                faceScanningOverlay2.invalidate();
                break;
            case 2:
                this.this$0.rimPaint.setAlpha(255);
                this.this$0.invalidate();
                break;
            default:
                FaceScanningOverlay faceScanningOverlay3 = this.this$0;
                faceScanningOverlay3.rimAnimator = null;
                if (!faceScanningOverlay3.showScanningAnim) {
                    faceScanningOverlay3.requestLayout();
                    break;
                }
                break;
        }
    }
}
