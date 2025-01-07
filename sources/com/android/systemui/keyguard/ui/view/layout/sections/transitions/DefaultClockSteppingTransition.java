package com.android.systemui.keyguard.ui.view.layout.sections.transitions;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.ViewGroup;
import com.android.app.animation.Interpolators;
import com.android.systemui.plugins.clocks.ClockController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultClockSteppingTransition extends Transition {
    public static final String[] TRANSITION_PROPERTIES = {"DefaultClockSteppingTransition:boundsLeft", "DefaultClockSteppingTransition:xInWindow"};
    public final ClockController clock;

    public DefaultClockSteppingTransition(ClockController clockController) {
        this.clock = clockController;
        setInterpolator(Interpolators.LINEAR);
        setDuration(1000L);
        addTarget(clockController.getLargeClock().getView());
    }

    public static void captureValues(TransitionValues transitionValues) {
        transitionValues.values.put("DefaultClockSteppingTransition:boundsLeft", Integer.valueOf(transitionValues.view.getLeft()));
        int[] iArr = new int[2];
        transitionValues.view.getLocationInWindow(iArr);
        transitionValues.values.put("DefaultClockSteppingTransition:xInWindow", Integer.valueOf(iArr[0]));
    }

    @Override // android.transition.Transition
    public final void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // android.transition.Transition
    public final void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // android.transition.Transition
    public final Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        final int intValue = ((Integer) transitionValues.values.get("DefaultClockSteppingTransition:boundsLeft")).intValue();
        final int i = ((Integer) transitionValues2.values.get("DefaultClockSteppingTransition:xInWindow")).intValue() - ((Integer) transitionValues.values.get("DefaultClockSteppingTransition:xInWindow")).intValue() > 0 ? 1 : -1;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.keyguard.ui.view.layout.sections.transitions.DefaultClockSteppingTransition$createAnimator$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                DefaultClockSteppingTransition.this.clock.getLargeClock().getAnimations().onPositionUpdated(intValue, i, valueAnimator.getAnimatedFraction());
            }
        });
        return ofFloat;
    }

    @Override // android.transition.Transition
    public final String[] getTransitionProperties() {
        return TRANSITION_PROPERTIES;
    }
}
