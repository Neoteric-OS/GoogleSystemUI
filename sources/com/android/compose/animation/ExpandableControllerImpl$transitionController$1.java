package com.android.compose.animation;

import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.graphics.Outline;
import com.android.systemui.animation.TransitionAnimator;
import kotlin.Pair;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ExpandableControllerImpl$transitionController$1 implements TransitionAnimator.Controller {
    public final /* synthetic */ ExpandableControllerImpl this$0;
    public ViewGroup transitionContainer;
    public final int[] rootLocationOnScreen = {0, 0};
    public final boolean isLaunching = true;

    public ExpandableControllerImpl$transitionController$1(ExpandableControllerImpl expandableControllerImpl) {
        this.this$0 = expandableControllerImpl;
        this.transitionContainer = (ViewGroup) expandableControllerImpl.composeViewRoot.getRootView();
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final TransitionAnimator.State createAnimatorState() {
        ExpandableControllerImpl expandableControllerImpl;
        Pair pair;
        ExpandableControllerImpl expandableControllerImpl2 = this.this$0;
        Rect rect = (Rect) expandableControllerImpl2.boundsInComposeViewRoot.getValue();
        float f = rect.right;
        float f2 = rect.left;
        float f3 = rect.bottom - rect.top;
        Outline mo37createOutlinePq9zytI = expandableControllerImpl2.shape.mo37createOutlinePq9zytI((Float.floatToRawIntBits(f - f2) << 32) | (Float.floatToRawIntBits(f3) & 4294967295L), expandableControllerImpl2.layoutDirection, expandableControllerImpl2.density);
        if (mo37createOutlinePq9zytI instanceof Outline.Rectangle) {
            pair = new Pair(Float.valueOf(0.0f), Float.valueOf(0.0f));
            expandableControllerImpl = expandableControllerImpl2;
        } else {
            if (!(mo37createOutlinePq9zytI instanceof Outline.Rounded)) {
                throw new IllegalStateException("ExpandableState only supports (rounded) rectangles at the moment.");
            }
            RoundRect roundRect = ((Outline.Rounded) mo37createOutlinePq9zytI).roundRect;
            long j = roundRect.topLeftCornerRadius;
            float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
            float intBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L));
            expandableControllerImpl = expandableControllerImpl2;
            long j2 = roundRect.topRightCornerRadius;
            float[] fArr = {intBitsToFloat2, Float.intBitsToFloat((int) (j2 >> 32)), Float.intBitsToFloat((int) (j2 & 4294967295L))};
            float f4 = intBitsToFloat;
            for (int i = 0; i < 3; i++) {
                f4 = Math.max(f4, fArr[i]);
            }
            long j3 = roundRect.bottomLeftCornerRadius;
            float f5 = f4;
            float intBitsToFloat3 = Float.intBitsToFloat((int) (j3 >> 32));
            float intBitsToFloat4 = Float.intBitsToFloat((int) (j3 & 4294967295L));
            float f6 = intBitsToFloat3;
            long j4 = roundRect.bottomRightCornerRadius;
            int i2 = 1;
            float[] fArr2 = {intBitsToFloat4, Float.intBitsToFloat((int) (j4 >> 32)), Float.intBitsToFloat((int) (j4 & 4294967295L))};
            int i3 = 0;
            while (i3 < 3) {
                f6 = Math.max(f6, fArr2[i3]);
                i3 += i2;
                i2 = 1;
            }
            pair = new Pair(Float.valueOf(f5), Float.valueOf(f6));
        }
        float floatValue = ((Number) pair.component1()).floatValue();
        float floatValue2 = ((Number) pair.component2()).floatValue();
        expandableControllerImpl.composeViewRoot.getLocationOnScreen(this.rootLocationOnScreen);
        Rect rect2 = (Rect) expandableControllerImpl.boundsInComposeViewRoot.getValue();
        long floatToRawIntBits = (Float.floatToRawIntBits(r0[0] + rect2.left) << 32) | (Float.floatToRawIntBits(r0[1] + rect2.top) & 4294967295L);
        int i4 = (int) (floatToRawIntBits & 4294967295L);
        int i5 = (int) (floatToRawIntBits >> 32);
        return new TransitionAnimator.State(MathKt.roundToInt(Float.intBitsToFloat(i4)), MathKt.roundToInt(Float.intBitsToFloat(i4) + f3), MathKt.roundToInt(Float.intBitsToFloat(i5)), MathKt.roundToInt((rect.right - f2) + Float.intBitsToFloat(i5)), floatValue, floatValue2);
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final ViewGroup getTransitionContainer() {
        return this.transitionContainer;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final boolean isLaunching() {
        return this.isLaunching;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationEnd(boolean z) {
        this.this$0.animatorState.setValue(null);
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        ExpandableControllerImpl expandableControllerImpl = this.this$0;
        MutableState mutableState = expandableControllerImpl.animatorState;
        TransitionAnimator.State state2 = new TransitionAnimator.State(state.top, state.bottom, state.left, state.right, state.topCornerRadius, state.bottomCornerRadius);
        state2.visible = state.visible;
        mutableState.setValue(state2);
        View view = (View) expandableControllerImpl.currentComposeViewInOverlay.getValue();
        if (view != null) {
            ExpandableKt.measureAndLayoutComposeViewInOverlay(view, state);
        }
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void setTransitionContainer(ViewGroup viewGroup) {
        this.transitionContainer = viewGroup;
    }
}
