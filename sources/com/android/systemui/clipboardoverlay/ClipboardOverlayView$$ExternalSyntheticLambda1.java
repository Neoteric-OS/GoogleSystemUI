package com.android.systemui.clipboardoverlay;

import android.animation.ValueAnimator;
import android.util.MathUtils;
import android.view.View;
import android.widget.LinearLayout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ClipboardOverlayView$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ClipboardOverlayView f$0;

    public /* synthetic */ ClipboardOverlayView$$ExternalSyntheticLambda1(ClipboardOverlayView clipboardOverlayView, int i) {
        this.$r8$classId = i;
        this.f$0 = clipboardOverlayView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.$r8$classId;
        ClipboardOverlayView clipboardOverlayView = this.f$0;
        switch (i) {
            case 0:
                int i2 = ClipboardOverlayView.$r8$clinit;
                clipboardOverlayView.getClass();
                clipboardOverlayView.setAlpha(1.0f - valueAnimator.getAnimatedFraction());
                break;
            case 1:
                int i3 = ClipboardOverlayView.$r8$clinit;
                clipboardOverlayView.getClass();
                float lerp = MathUtils.lerp(1.0f, 0.9f, valueAnimator.getAnimatedFraction());
                clipboardOverlayView.mMinimizedPreview.setScaleX(lerp);
                clipboardOverlayView.mMinimizedPreview.setScaleY(lerp);
                clipboardOverlayView.mClipboardPreview.setScaleX(lerp);
                clipboardOverlayView.mClipboardPreview.setScaleY(lerp);
                clipboardOverlayView.mPreviewBorder.setScaleX(lerp);
                clipboardOverlayView.mPreviewBorder.setScaleY(lerp);
                float x = clipboardOverlayView.mClipboardPreview.getX() + (clipboardOverlayView.mClipboardPreview.getWidth() / 2.0f);
                View view = clipboardOverlayView.mActionContainerBackground;
                view.setPivotX(x - view.getX());
                LinearLayout linearLayout = clipboardOverlayView.mActionContainer;
                linearLayout.setPivotX(x - ((View) linearLayout.getParent()).getX());
                float lerp2 = MathUtils.lerp(1.0f, 0.8f, valueAnimator.getAnimatedFraction());
                float lerp3 = MathUtils.lerp(1.0f, 0.9f, valueAnimator.getAnimatedFraction());
                clipboardOverlayView.mActionContainer.setScaleX(lerp2);
                clipboardOverlayView.mActionContainer.setScaleY(lerp3);
                clipboardOverlayView.mActionContainerBackground.setScaleX(lerp2);
                clipboardOverlayView.mActionContainerBackground.setScaleY(lerp3);
                break;
            case 2:
                int i4 = ClipboardOverlayView.$r8$clinit;
                clipboardOverlayView.getClass();
                float animatedFraction = 1.0f - valueAnimator.getAnimatedFraction();
                clipboardOverlayView.mMinimizedPreview.setAlpha(animatedFraction);
                clipboardOverlayView.mClipboardPreview.setAlpha(animatedFraction);
                clipboardOverlayView.mPreviewBorder.setAlpha(animatedFraction);
                clipboardOverlayView.mDismissButton.setAlpha(animatedFraction);
                clipboardOverlayView.mActionContainer.setAlpha(animatedFraction);
                break;
            case 3:
                int i5 = ClipboardOverlayView.$r8$clinit;
                clipboardOverlayView.getClass();
                clipboardOverlayView.setAlpha(valueAnimator.getAnimatedFraction());
                break;
            case 4:
                int i6 = ClipboardOverlayView.$r8$clinit;
                clipboardOverlayView.getClass();
                float lerp4 = MathUtils.lerp(0.9f, 1.0f, valueAnimator.getAnimatedFraction());
                clipboardOverlayView.mMinimizedPreview.setScaleX(lerp4);
                clipboardOverlayView.mMinimizedPreview.setScaleY(lerp4);
                clipboardOverlayView.mClipboardPreview.setScaleX(lerp4);
                clipboardOverlayView.mClipboardPreview.setScaleY(lerp4);
                clipboardOverlayView.mPreviewBorder.setScaleX(lerp4);
                clipboardOverlayView.mPreviewBorder.setScaleY(lerp4);
                float x2 = clipboardOverlayView.mClipboardPreview.getX() + (clipboardOverlayView.mClipboardPreview.getWidth() / 2.0f);
                View view2 = clipboardOverlayView.mActionContainerBackground;
                view2.setPivotX(x2 - view2.getX());
                LinearLayout linearLayout2 = clipboardOverlayView.mActionContainer;
                linearLayout2.setPivotX(x2 - ((View) linearLayout2.getParent()).getX());
                float lerp5 = MathUtils.lerp(0.7f, 1.0f, valueAnimator.getAnimatedFraction());
                float lerp6 = MathUtils.lerp(0.9f, 1.0f, valueAnimator.getAnimatedFraction());
                clipboardOverlayView.mActionContainer.setScaleX(lerp5);
                clipboardOverlayView.mActionContainer.setScaleY(lerp6);
                clipboardOverlayView.mActionContainerBackground.setScaleX(lerp5);
                clipboardOverlayView.mActionContainerBackground.setScaleY(lerp6);
                break;
            default:
                int i7 = ClipboardOverlayView.$r8$clinit;
                clipboardOverlayView.getClass();
                float animatedFraction2 = valueAnimator.getAnimatedFraction();
                clipboardOverlayView.mMinimizedPreview.setAlpha(animatedFraction2);
                clipboardOverlayView.mClipboardPreview.setAlpha(animatedFraction2);
                clipboardOverlayView.mPreviewBorder.setAlpha(animatedFraction2);
                clipboardOverlayView.mDismissButton.setAlpha(animatedFraction2);
                clipboardOverlayView.mActionContainer.setAlpha(animatedFraction2);
                break;
        }
    }
}
