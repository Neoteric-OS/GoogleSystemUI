package androidx.compose.material.ripple;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RippleHostView extends View {
    public static final int[] PressedState = {R.attr.state_pressed, R.attr.state_enabled};
    public static final int[] RestingState = new int[0];
    public Boolean bounded;
    public Long lastRippleStateChangeTimeMillis;
    public Function0 onInvalidateRipple;
    public RippleHostView$$ExternalSyntheticLambda0 resetRippleRunnable;
    public UnprojectedRipple ripple;

    public final void disposeRipple() {
        this.onInvalidateRipple = null;
        Runnable runnable = this.resetRippleRunnable;
        if (runnable != null) {
            removeCallbacks(runnable);
            RippleHostView$$ExternalSyntheticLambda0 rippleHostView$$ExternalSyntheticLambda0 = this.resetRippleRunnable;
            Intrinsics.checkNotNull(rippleHostView$$ExternalSyntheticLambda0);
            rippleHostView$$ExternalSyntheticLambda0.run();
        } else {
            UnprojectedRipple unprojectedRipple = this.ripple;
            if (unprojectedRipple != null) {
                unprojectedRipple.setState(RestingState);
            }
        }
        UnprojectedRipple unprojectedRipple2 = this.ripple;
        if (unprojectedRipple2 == null) {
            return;
        }
        unprojectedRipple2.setVisible(false, false);
        unscheduleDrawable(unprojectedRipple2);
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Function0 function0 = this.onInvalidateRipple;
        if (function0 != null) {
            function0.invoke();
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    public final void setRippleState(boolean z) {
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        Runnable runnable = this.resetRippleRunnable;
        if (runnable != null) {
            removeCallbacks(runnable);
            runnable.run();
        }
        Long l = this.lastRippleStateChangeTimeMillis;
        long longValue = currentAnimationTimeMillis - (l != null ? l.longValue() : 0L);
        if (z || longValue >= 5) {
            int[] iArr = z ? PressedState : RestingState;
            UnprojectedRipple unprojectedRipple = this.ripple;
            if (unprojectedRipple != null) {
                unprojectedRipple.setState(iArr);
            }
        } else {
            RippleHostView$$ExternalSyntheticLambda0 rippleHostView$$ExternalSyntheticLambda0 = new RippleHostView$$ExternalSyntheticLambda0(this);
            this.resetRippleRunnable = rippleHostView$$ExternalSyntheticLambda0;
            postDelayed(rippleHostView$$ExternalSyntheticLambda0, 50L);
        }
        this.lastRippleStateChangeTimeMillis = Long.valueOf(currentAnimationTimeMillis);
    }

    /* renamed from: updateRippleProperties-biQXAtU, reason: not valid java name */
    public final void m192updateRipplePropertiesbiQXAtU(int i, long j, long j2) {
        long Color;
        UnprojectedRipple unprojectedRipple = this.ripple;
        if (unprojectedRipple == null) {
            return;
        }
        Integer num = unprojectedRipple.rippleRadius;
        if (num == null || num.intValue() != i) {
            unprojectedRipple.rippleRadius = Integer.valueOf(i);
            unprojectedRipple.setRadius(i);
        }
        Color = ColorKt.Color(Color.m368getRedimpl(j2), Color.m367getGreenimpl(j2), Color.m365getBlueimpl(j2), RangesKt.coerceAtMost(0.1f, 1.0f), Color.m366getColorSpaceimpl(j2));
        Color color = unprojectedRipple.rippleColor;
        if (!(color == null ? false : Color.m363equalsimpl0(color.value, Color))) {
            unprojectedRipple.rippleColor = new Color(Color);
            unprojectedRipple.setColor(ColorStateList.valueOf(ColorKt.m373toArgb8_81llA(Color)));
        }
        Rect rect = new Rect(0, 0, MathKt.roundToInt(Size.m329getWidthimpl(j)), MathKt.roundToInt(Size.m327getHeightimpl(j)));
        setLeft(rect.left);
        setTop(rect.top);
        setRight(rect.right);
        setBottom(rect.bottom);
        unprojectedRipple.setBounds(rect);
    }

    @Override // android.view.View
    public final void refreshDrawableState() {
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }
}
