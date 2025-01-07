package com.android.systemui.statusbar.notification.row;

import android.animation.ValueAnimator;
import android.graphics.Point;
import android.util.MathUtils;
import android.view.animation.PathInterpolator;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.systemui.statusbar.notification.NotificationUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ActivatableNotificationView$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ ActivatableNotificationView f$0;

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.$r8$classId;
        ActivatableNotificationView activatableNotificationView = this.f$0;
        switch (i) {
            case 0:
                activatableNotificationView.setBackgroundTintColor(NotificationUtils.interpolateColors(activatableNotificationView.mStartTint, valueAnimator.getAnimatedFraction(), activatableNotificationView.mTargetTint));
                break;
            default:
                activatableNotificationView.getClass();
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                activatableNotificationView.mAppearAnimationFraction = floatValue;
                activatableNotificationView.setContentAlpha$1(((PathInterpolator) Interpolators.ALPHA_IN).getInterpolation((MathUtils.constrain(floatValue, 0.7f, 1.0f) - 0.7f) / 0.3f));
                float f = activatableNotificationView.mAppearAnimationFraction;
                float f2 = (1.0f - f) * activatableNotificationView.mAnimationTranslationY;
                activatableNotificationView.mAppearAnimationTranslation = f2;
                float f3 = activatableNotificationView.mActualHeight;
                float f4 = f * f3;
                if (activatableNotificationView.mTargetPoint != null) {
                    int width = activatableNotificationView.getWidth();
                    float f5 = 1.0f - activatableNotificationView.mAppearAnimationFraction;
                    Point point = activatableNotificationView.mTargetPoint;
                    int i2 = point.x;
                    float f6 = activatableNotificationView.mAnimationTranslationY;
                    activatableNotificationView.setOutlineRect(i2 * f5, AndroidFlingSpline$$ExternalSyntheticOutline0.m(f6, point.y, f5, f6), width - ((width - i2) * f5), f3 - ((r1 - r0) * f5));
                } else {
                    activatableNotificationView.setOutlineRect(0.0f, f2, activatableNotificationView.getWidth(), f4 + activatableNotificationView.mAppearAnimationTranslation);
                }
                activatableNotificationView.invalidate();
                break;
        }
    }
}
