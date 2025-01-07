package com.android.systemui.util.animation;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.util.MathUtils;
import com.android.app.animation.Interpolators;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TransitionLayoutController {
    public TransitionViewState animationStartState;
    public ValueAnimator animator;
    public int currentHeight;
    public TransitionViewState currentState;
    public int currentWidth;
    public boolean isGutsAnimation;
    public Function2 sizeChangedListener;
    public TransitionViewState state;
    public TransitionLayout transitionLayout;

    public static TransitionViewState getGoneState(TransitionViewState transitionViewState, DisappearParameters disappearParameters, float f, TransitionViewState transitionViewState2) {
        disappearParameters.getClass();
        float constrain = MathUtils.constrain(MathUtils.map(0.0f, disappearParameters.disappearEnd, 0.0f, 1.0f, f), 0.0f, 1.0f);
        TransitionViewState copy = transitionViewState.copy(transitionViewState2);
        float f2 = transitionViewState.width;
        copy.width = (int) MathUtils.lerp(f2, disappearParameters.disappearSize.x * f2, constrain);
        float f3 = transitionViewState.height;
        copy.height = (int) MathUtils.lerp(f3, disappearParameters.disappearSize.y * f3, constrain);
        PointF pointF = copy.translation;
        float f4 = transitionViewState.width - copy.width;
        PointF pointF2 = disappearParameters.gonePivot;
        float f5 = f4 * pointF2.x;
        pointF.x = f5;
        float f6 = (transitionViewState.height - r0) * pointF2.y;
        pointF.y = f6;
        PointF pointF3 = copy.contentTranslation;
        PointF pointF4 = disappearParameters.contentTranslationFraction;
        pointF3.x = (pointF4.x - 1.0f) * f5;
        pointF3.y = (pointF4.y - 1.0f) * f6;
        copy.alpha = MathUtils.constrain(MathUtils.map(disappearParameters.fadeStartPosition, 1.0f, 1.0f, 0.0f, constrain), 0.0f, 1.0f);
        return copy;
    }

    public final void applyStateToLayout(TransitionViewState transitionViewState) {
        TransitionLayout transitionLayout = this.transitionLayout;
        if (transitionLayout != null) {
            transitionLayout.currentState = transitionViewState;
            transitionLayout.applyCurrentState$1();
        }
        int i = this.currentHeight;
        int i2 = transitionViewState.height;
        if (i == i2 && this.currentWidth == transitionViewState.width) {
            return;
        }
        this.currentHeight = i2;
        int i3 = transitionViewState.width;
        this.currentWidth = i3;
        Function2 function2 = this.sizeChangedListener;
        if (function2 != null) {
            function2.invoke(Integer.valueOf(i3), Integer.valueOf(this.currentHeight));
        }
    }

    public final TransitionViewState getInterpolatedState(TransitionViewState transitionViewState, TransitionViewState transitionViewState2, float f, TransitionViewState transitionViewState3) {
        WidgetState widgetState;
        TransitionLayout transitionLayout;
        int i;
        int i2;
        int i3;
        float lerp;
        float lerp2;
        float lerp3;
        float f2;
        float f3;
        boolean z;
        TransitionLayoutController transitionLayoutController = this;
        TransitionViewState transitionViewState4 = transitionViewState3 == null ? new TransitionViewState() : transitionViewState3;
        TransitionLayout transitionLayout2 = transitionLayoutController.transitionLayout;
        if (transitionLayout2 == null) {
            return transitionViewState4;
        }
        int i4 = 0;
        for (int childCount = transitionLayout2.getChildCount(); i4 < childCount; childCount = i) {
            int id = transitionLayout2.getChildAt(i4).getId();
            WidgetState widgetState2 = (WidgetState) transitionViewState4.widgetStates.get(Integer.valueOf(id));
            if (widgetState2 == null) {
                widgetState2 = new WidgetState(511);
            }
            WidgetState widgetState3 = (WidgetState) transitionViewState.widgetStates.get(Integer.valueOf(id));
            if (widgetState3 == null || (widgetState = (WidgetState) transitionViewState2.widgetStates.get(Integer.valueOf(id))) == null) {
                transitionLayout = transitionLayout2;
                i = childCount;
            } else {
                boolean z2 = widgetState3.gone;
                if (z2 != widgetState.gone) {
                    if (z2) {
                        i2 = widgetState.measureWidth;
                        i3 = widgetState.measureHeight;
                        if (transitionLayoutController.isGutsAnimation) {
                            transitionLayout = transitionLayout2;
                            f2 = MathUtils.map(0.286f, 1.0f, 0.0f, 1.0f, f);
                            boolean z3 = f < 0.286f;
                            i = childCount;
                            lerp2 = widgetState3.x;
                            lerp3 = widgetState3.y;
                            z = z3;
                            f3 = 1.0f;
                            lerp = 1.0f;
                        } else {
                            transitionLayout = transitionLayout2;
                            float map = MathUtils.map(0.8f, 1.0f, 0.0f, 1.0f, f);
                            boolean z4 = f < 0.8f;
                            float f4 = widgetState.scale;
                            float lerp4 = MathUtils.lerp(0.8f * f4, f4, f);
                            float lerp5 = MathUtils.lerp(widgetState3.x - (i2 / 2.0f), widgetState.x, f);
                            i = childCount;
                            lerp3 = MathUtils.lerp(widgetState3.y - (i3 / 2.0f), widgetState.y, f);
                            z = z4;
                            lerp = lerp4;
                            f2 = map;
                            lerp2 = lerp5;
                            f3 = 1.0f;
                        }
                    } else {
                        transitionLayout = transitionLayout2;
                        i = childCount;
                        i2 = widgetState3.measureWidth;
                        i3 = widgetState3.measureHeight;
                        if (transitionLayoutController.isGutsAnimation) {
                            float map2 = MathUtils.map(0.0f, 0.355f, 0.0f, 1.0f, f);
                            boolean z5 = f > 0.355f;
                            lerp2 = widgetState.x;
                            lerp3 = widgetState.y;
                            f2 = map2;
                            z = z5;
                            lerp = 1.0f;
                        } else {
                            float map3 = MathUtils.map(0.0f, 0.19999999f, 0.0f, 1.0f, f);
                            boolean z6 = f > 0.19999999f;
                            float f5 = widgetState3.scale;
                            float lerp6 = MathUtils.lerp(f5, 0.8f * f5, f);
                            lerp2 = MathUtils.lerp(widgetState3.x, widgetState.x - (i2 / 2.0f), f);
                            lerp3 = MathUtils.lerp(widgetState3.y, widgetState.y - (i3 / 2.0f), f);
                            f2 = map3;
                            z = z6;
                            lerp = lerp6;
                        }
                        f3 = 0.0f;
                    }
                    widgetState2.gone = z;
                } else {
                    transitionLayout = transitionLayout2;
                    i = childCount;
                    widgetState2.gone = z2;
                    i2 = widgetState.measureWidth;
                    i3 = widgetState.measureHeight;
                    lerp = MathUtils.lerp(widgetState3.scale, widgetState.scale, f);
                    lerp2 = MathUtils.lerp(widgetState3.x, widgetState.x, f);
                    lerp3 = MathUtils.lerp(widgetState3.y, widgetState.y, f);
                    f2 = f;
                    f3 = f2;
                }
                widgetState2.x = lerp2;
                widgetState2.y = lerp3;
                widgetState2.alpha = MathUtils.lerp(widgetState3.alpha, widgetState.alpha, f2);
                widgetState2.width = (int) MathUtils.lerp(widgetState3.width, widgetState.width, f3);
                widgetState2.height = (int) MathUtils.lerp(widgetState3.height, widgetState.height, f3);
                widgetState2.scale = lerp;
                widgetState2.measureWidth = i2;
                widgetState2.measureHeight = i3;
                transitionViewState4.widgetStates.put(Integer.valueOf(id), widgetState2);
            }
            i4++;
            transitionLayoutController = this;
            transitionLayout2 = transitionLayout;
        }
        transitionViewState4.width = (int) MathUtils.lerp(transitionViewState.width, transitionViewState2.width, f);
        transitionViewState4.height = (int) MathUtils.lerp(transitionViewState.height, transitionViewState2.height, f);
        if (f == 0.0f) {
            transitionViewState4.measureWidth = transitionViewState.measureWidth;
            transitionViewState4.measureHeight = transitionViewState.measureHeight;
        } else {
            transitionViewState4.measureWidth = transitionViewState2.measureWidth;
            transitionViewState4.measureHeight = transitionViewState2.measureHeight;
        }
        transitionViewState4.translation.x = MathUtils.lerp(transitionViewState.translation.x, transitionViewState2.translation.x, f);
        transitionViewState4.translation.y = MathUtils.lerp(transitionViewState.translation.y, transitionViewState2.translation.y, f);
        transitionViewState4.alpha = MathUtils.lerp(transitionViewState.alpha, transitionViewState2.alpha, f);
        transitionViewState4.contentTranslation.x = MathUtils.lerp(transitionViewState.contentTranslation.x, transitionViewState2.contentTranslation.x, f);
        transitionViewState4.contentTranslation.y = MathUtils.lerp(transitionViewState.contentTranslation.y, transitionViewState2.contentTranslation.y, f);
        return transitionViewState4;
    }

    public final void setMeasureState(TransitionViewState transitionViewState) {
        TransitionLayout transitionLayout = this.transitionLayout;
        if (transitionLayout == null) {
            return;
        }
        int i = transitionViewState.measureWidth;
        int i2 = transitionViewState.measureHeight;
        if (i == transitionLayout.desiredMeasureWidth && i2 == transitionLayout.desiredMeasureHeight) {
            return;
        }
        transitionLayout.desiredMeasureWidth = i;
        transitionLayout.desiredMeasureHeight = i2;
        if (transitionLayout.isInLayout()) {
            transitionLayout.forceLayout();
        } else {
            transitionLayout.requestLayout();
        }
    }

    public final void setState(TransitionViewState transitionViewState, boolean z, boolean z2, long j, long j2, boolean z3) {
        this.isGutsAnimation = z3;
        boolean z4 = z2 && this.currentState.width != 0;
        this.state = transitionViewState.copy(null);
        if (z || this.transitionLayout == null) {
            this.animator.cancel();
            applyStateToLayout(this.state);
            this.currentState = transitionViewState.copy(this.currentState);
        } else {
            if (!z4) {
                if (this.animator.isRunning()) {
                    return;
                }
                applyStateToLayout(this.state);
                this.currentState = transitionViewState.copy(this.currentState);
                return;
            }
            this.animationStartState = this.currentState.copy(null);
            this.animator.setDuration(j);
            this.animator.setStartDelay(j2);
            this.animator.setInterpolator(this.isGutsAnimation ? Interpolators.LINEAR : Interpolators.FAST_OUT_SLOW_IN);
            this.animator.start();
        }
    }
}
