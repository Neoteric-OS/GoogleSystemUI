package com.android.systemui.screenshot.ui;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotAnimationController$getActionsAnimator$1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ScreenshotAnimationController this$0;

    public /* synthetic */ ScreenshotAnimationController$getActionsAnimator$1(ScreenshotAnimationController screenshotAnimationController, int i) {
        this.$r8$classId = i;
        this.this$0 = screenshotAnimationController;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.actionContainer.setTranslationY(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            case 1:
                this.this$0.scrollingScrim.setImageTintList(ColorStateList.valueOf(Color.argb(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f, 0.0f, 0.0f)));
                break;
            case 2:
                Iterator it = this.this$0.fadeUI.iterator();
                while (it.hasNext()) {
                    ((View) it.next()).setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
                break;
            case 3:
                Iterator it2 = this.this$0.staticUI.iterator();
                while (it2.hasNext()) {
                    ((View) it2.next()).setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
                break;
            case 4:
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                this.this$0.screenshotPreview.setY(floatValue - (r2.getHeight() / 2.0f));
                break;
            case 5:
                this.this$0.view.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                this.this$0.view.setAlpha(1.0f - valueAnimator.getAnimatedFraction());
                break;
            case 6:
                this.this$0.view.setTranslationX(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            case 7:
                this.this$0.scrollingScrim.setAlpha(1 - valueAnimator.getAnimatedFraction());
                break;
            default:
                this.this$0.scrollTransitionPreview.setAlpha(1 - valueAnimator.getAnimatedFraction());
                break;
        }
    }
}
