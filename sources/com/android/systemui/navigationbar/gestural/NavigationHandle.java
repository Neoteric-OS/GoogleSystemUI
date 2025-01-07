package com.android.systemui.navigationbar.gestural;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.view.ContextThemeWrapper;
import android.view.View;
import com.android.app.animation.Interpolators;
import com.android.settingslib.Utils;
import com.android.systemui.navigationbar.views.buttons.ButtonInterface;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class NavigationHandle extends View implements ButtonInterface {
    public static final AnonymousClass1 PULSE_ANIMATION_PROGRESS = new AnonymousClass1("pulseAnimationProgress");
    public final float mAdditionalHeightForAnimation;
    public final float mAdditionalWidthForAnimation;
    public final float mBottom;
    public final int mDarkColor;
    public final int mLightColor;
    public final Paint mPaint;
    public float mPulseAnimationProgress;
    public ObjectAnimator mPulseAnimator;
    public final float mRadius;
    public boolean mRequiresInvalidate;
    public boolean mShrink;
    public final float mShrinkWidthForAnimation;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.gestural.NavigationHandle$1, reason: invalid class name */
    public final class AnonymousClass1 extends FloatProperty {
        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((NavigationHandle) obj).mPulseAnimationProgress);
        }

        @Override // android.util.FloatProperty
        public final void setValue(Object obj, float f) {
            NavigationHandle navigationHandle = (NavigationHandle) obj;
            navigationHandle.mPulseAnimationProgress = f;
            navigationHandle.invalidate();
        }
    }

    public NavigationHandle(Context context) {
        this(context, null);
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public final void animateLongPress(boolean z, boolean z2, long j) {
        ObjectAnimator objectAnimator = this.mPulseAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        this.mShrink = z2;
        TimeInterpolator navigationHandle$$ExternalSyntheticLambda0 = z2 ? Interpolators.LEGACY_DECELERATE : z ? new NavigationHandle$$ExternalSyntheticLambda0() : Interpolators.LEGACY_DECELERATE;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, PULSE_ANIMATION_PROGRESS, z ? 1.0f : 0.0f);
        this.mPulseAnimator = ofFloat;
        ofFloat.setDuration(j).setInterpolator(navigationHandle$$ExternalSyntheticLambda0);
        this.mPulseAnimator.start();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        float f;
        float f2;
        super.onDraw(canvas);
        int height = getHeight();
        if (this.mShrink) {
            f2 = (-this.mShrinkWidthForAnimation) * this.mPulseAnimationProgress;
            f = 0.0f;
        } else {
            float f3 = this.mAdditionalHeightForAnimation;
            float f4 = this.mPulseAnimationProgress;
            float f5 = f3 * f4;
            float f6 = f4 * this.mAdditionalWidthForAnimation;
            f = f5;
            f2 = f6;
        }
        float f7 = (this.mRadius * 2.0f) + f;
        float f8 = (f / 2.0f) + ((height - this.mBottom) - f7);
        float f9 = f7 / 2.0f;
        canvas.drawRoundRect(-f2, f8, getWidth() + f2, f8 + f7, f9, f9, this.mPaint);
    }

    @Override // android.view.View
    public final void setAlpha(float f) {
        super.setAlpha(f);
        if (f <= 0.0f || !this.mRequiresInvalidate) {
            return;
        }
        this.mRequiresInvalidate = false;
        invalidate();
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public final void setDarkIntensity(float f) {
        int intValue = ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(this.mLightColor), Integer.valueOf(this.mDarkColor))).intValue();
        if (this.mPaint.getColor() != intValue) {
            this.mPaint.setColor(intValue);
            if (getVisibility() != 0 || getAlpha() <= 0.0f) {
                this.mRequiresInvalidate = true;
            } else {
                invalidate();
            }
        }
    }

    public NavigationHandle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mPulseAnimator = null;
        Resources resources = context.getResources();
        this.mRadius = resources.getDimension(R.dimen.navigation_handle_radius);
        this.mBottom = resources.getDimension(R.dimen.navigation_handle_bottom);
        this.mAdditionalWidthForAnimation = resources.getDimension(R.dimen.navigation_home_handle_additional_width_for_animation);
        this.mAdditionalHeightForAnimation = resources.getDimension(R.dimen.navigation_home_handle_additional_height_for_animation);
        this.mShrinkWidthForAnimation = resources.getDimension(R.dimen.navigation_home_handle_shrink_width_for_animation);
        int themeAttr = Utils.getThemeAttr(R.attr.darkIconTheme, context);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, Utils.getThemeAttr(R.attr.lightIconTheme, context));
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(context, themeAttr);
        this.mLightColor = Utils.getColorAttrDefaultColor(R.attr.homeHandleColor, 0, contextThemeWrapper);
        this.mDarkColor = Utils.getColorAttrDefaultColor(R.attr.homeHandleColor, 0, contextThemeWrapper2);
        paint.setAntiAlias(true);
        setFocusable(false);
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public final void abortCurrentGesture() {
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public final void setImageDrawable(Drawable drawable) {
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public final void setVertical(boolean z) {
    }
}
