package com.android.systemui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewOverlay;
import android.view.animation.Interpolator;
import android.window.WindowAnimationState;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import com.android.systemui.animation.TransitionAnimator;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$FloatRef;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TransitionAnimator {
    public static final PorterDuffXfermode SRC_MODE = new PorterDuffXfermode(PorterDuff.Mode.SRC);
    public final Interpolators interpolators;
    public final Executor mainExecutor;
    public final Timings timings;
    public final int[] transitionContainerLocation = new int[2];
    public final float[] cornerRadii = new float[8];

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static float getProgress(Timings timings, float f, long j, long j2) {
            return MathUtils.constrain(((f * timings.totalDuration) - j) / j2, 0.0f, 1.0f);
        }

        public static State toTransitionState$frameworks__base__packages__SystemUI__animation__android_common__PlatformAnimationLib(WindowAnimationState windowAnimationState) {
            State state = new State(0, 0, 0, 0, 0.0f, 0.0f, 63);
            RectF rectF = windowAnimationState.bounds;
            if (rectF != null) {
                state.top = MathKt.roundToInt(rectF.top);
                state.left = MathKt.roundToInt(rectF.left);
                state.bottom = MathKt.roundToInt(rectF.bottom);
                state.right = MathKt.roundToInt(rectF.right);
            }
            float f = 2;
            state.bottomCornerRadius = (windowAnimationState.bottomLeftRadius + windowAnimationState.bottomRightRadius) / f;
            state.topCornerRadius = (windowAnimationState.topLeftRadius + windowAnimationState.topRightRadius) / f;
            return state;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Interpolators {
        public final Interpolator contentAfterFadeInInterpolator;
        public final Interpolator contentBeforeFadeOutInterpolator;
        public final Interpolator positionInterpolator;
        public final Interpolator positionXInterpolator;

        public Interpolators(Interpolator interpolator, Interpolator interpolator2, Interpolator interpolator3, Interpolator interpolator4) {
            this.positionInterpolator = interpolator;
            this.positionXInterpolator = interpolator2;
            this.contentBeforeFadeOutInterpolator = interpolator3;
            this.contentAfterFadeInInterpolator = interpolator4;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Interpolators)) {
                return false;
            }
            Interpolators interpolators = (Interpolators) obj;
            return Intrinsics.areEqual(this.positionInterpolator, interpolators.positionInterpolator) && Intrinsics.areEqual(this.positionXInterpolator, interpolators.positionXInterpolator) && Intrinsics.areEqual(this.contentBeforeFadeOutInterpolator, interpolators.contentBeforeFadeOutInterpolator) && Intrinsics.areEqual(this.contentAfterFadeInInterpolator, interpolators.contentAfterFadeInInterpolator);
        }

        public final int hashCode() {
            return this.contentAfterFadeInInterpolator.hashCode() + ((this.contentBeforeFadeOutInterpolator.hashCode() + ((this.positionXInterpolator.hashCode() + (this.positionInterpolator.hashCode() * 31)) * 31)) * 31);
        }

        public final String toString() {
            return "Interpolators(positionInterpolator=" + this.positionInterpolator + ", positionXInterpolator=" + this.positionXInterpolator + ", contentBeforeFadeOutInterpolator=" + this.contentBeforeFadeOutInterpolator + ", contentAfterFadeInInterpolator=" + this.contentAfterFadeInInterpolator + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Timings {
        public final long contentAfterFadeInDelay;
        public final long contentAfterFadeInDuration;
        public final long contentBeforeFadeOutDelay;
        public final long contentBeforeFadeOutDuration;
        public final long totalDuration;

        public Timings(long j, long j2, long j3, long j4, long j5) {
            this.totalDuration = j;
            this.contentBeforeFadeOutDelay = j2;
            this.contentBeforeFadeOutDuration = j3;
            this.contentAfterFadeInDelay = j4;
            this.contentAfterFadeInDuration = j5;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Timings)) {
                return false;
            }
            Timings timings = (Timings) obj;
            return this.totalDuration == timings.totalDuration && this.contentBeforeFadeOutDelay == timings.contentBeforeFadeOutDelay && this.contentBeforeFadeOutDuration == timings.contentBeforeFadeOutDuration && this.contentAfterFadeInDelay == timings.contentAfterFadeInDelay && this.contentAfterFadeInDuration == timings.contentAfterFadeInDuration;
        }

        public final int hashCode() {
            return Long.hashCode(this.contentAfterFadeInDuration) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Long.hashCode(this.totalDuration) * 31, 31, this.contentBeforeFadeOutDelay), 31, this.contentBeforeFadeOutDuration), 31, this.contentAfterFadeInDelay);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Timings(totalDuration=");
            sb.append(this.totalDuration);
            sb.append(", contentBeforeFadeOutDelay=");
            sb.append(this.contentBeforeFadeOutDelay);
            sb.append(", contentBeforeFadeOutDuration=");
            sb.append(this.contentBeforeFadeOutDuration);
            sb.append(", contentAfterFadeInDelay=");
            sb.append(this.contentAfterFadeInDelay);
            sb.append(", contentAfterFadeInDuration=");
            return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.contentAfterFadeInDuration, ")", sb);
        }
    }

    public TransitionAnimator(Executor executor, Timings timings, Interpolators interpolators) {
        this.mainExecutor = executor;
        this.timings = timings;
        this.interpolators = interpolators;
    }

    public final ValueAnimator createAnimator(final Controller controller, final State state, final GradientDrawable gradientDrawable, final boolean z, final boolean z2) {
        final State createAnimatorState = controller.createAnimatorState();
        final int i = createAnimatorState.top;
        final int i2 = createAnimatorState.bottom;
        int i3 = createAnimatorState.left;
        final float f = (i3 + r1) / 2.0f;
        final int i4 = createAnimatorState.right - i3;
        final float f2 = createAnimatorState.topCornerRadius;
        final float f3 = createAnimatorState.bottomCornerRadius;
        final Ref$IntRef ref$IntRef = new Ref$IntRef();
        ref$IntRef.element = state.top;
        final Ref$IntRef ref$IntRef2 = new Ref$IntRef();
        ref$IntRef2.element = state.bottom;
        final Ref$IntRef ref$IntRef3 = new Ref$IntRef();
        ref$IntRef3.element = state.left;
        final Ref$IntRef ref$IntRef4 = new Ref$IntRef();
        ref$IntRef4.element = state.right;
        final Ref$FloatRef ref$FloatRef = new Ref$FloatRef();
        ref$FloatRef.element = (ref$IntRef3.element + ref$IntRef4.element) / 2.0f;
        final Ref$IntRef ref$IntRef5 = new Ref$IntRef();
        ref$IntRef5.element = ref$IntRef4.element - ref$IntRef3.element;
        final float f4 = state.topCornerRadius;
        final float f5 = state.bottomCornerRadius;
        final ViewGroup transitionContainer = controller.getTransitionContainer();
        final boolean isExpandingFullyAbove$frameworks__base__packages__SystemUI__animation__android_common__PlatformAnimationLib = isExpandingFullyAbove$frameworks__base__packages__SystemUI__animation__android_common__PlatformAnimationLib(transitionContainer, state);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(this.timings.totalDuration);
        ofFloat.setInterpolator(com.android.app.animation.Interpolators.LINEAR);
        final View openingWindowSyncView = controller.getOpeningWindowSyncView();
        final ViewOverlay overlay = openingWindowSyncView != null ? openingWindowSyncView.getOverlay() : null;
        final boolean z3 = (openingWindowSyncView == null || Intrinsics.areEqual(openingWindowSyncView.getViewRootImpl(), controller.getTransitionContainer().getViewRootImpl())) ? false : true;
        final ViewGroupOverlay overlay2 = transitionContainer.getOverlay();
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.animation.TransitionAnimator$createAnimator$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                Executor executor = this.mainExecutor;
                final TransitionAnimator.Controller controller2 = TransitionAnimator.Controller.this;
                final boolean z4 = isExpandingFullyAbove$frameworks__base__packages__SystemUI__animation__android_common__PlatformAnimationLib;
                final ViewGroupOverlay viewGroupOverlay = overlay2;
                final GradientDrawable gradientDrawable2 = gradientDrawable;
                final boolean z5 = z3;
                final ViewOverlay viewOverlay = overlay;
                executor.execute(new Runnable() { // from class: com.android.systemui.animation.TransitionAnimator$createAnimator$1$onAnimationEnd$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ViewOverlay viewOverlay2;
                        TransitionAnimator.Controller.this.onTransitionAnimationEnd(z4);
                        viewGroupOverlay.remove(gradientDrawable2);
                        if (z5 && TransitionAnimator.Controller.this.isLaunching() && (viewOverlay2 = viewOverlay) != null) {
                            viewOverlay2.remove(gradientDrawable2);
                        }
                    }
                });
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator, boolean z4) {
                ViewOverlay viewOverlay;
                TransitionAnimator.Controller.this.onTransitionAnimationStart(isExpandingFullyAbove$frameworks__base__packages__SystemUI__animation__android_common__PlatformAnimationLib);
                if (TransitionAnimator.Controller.this.isLaunching() || (viewOverlay = overlay) == null) {
                    overlay2.add(gradientDrawable);
                } else {
                    viewOverlay.add(gradientDrawable);
                }
            }
        });
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.animation.TransitionAnimator$createAnimator$2
            /* JADX WARN: Code restructure failed: missing block: B:12:0x00df, code lost:
            
                if (com.android.systemui.animation.TransitionAnimator.Companion.getProgress(r11, r1, r11.contentBeforeFadeOutDelay, r11.contentBeforeFadeOutDuration) < 1.0f) goto L15;
             */
            /* JADX WARN: Code restructure failed: missing block: B:13:0x00e1, code lost:
            
                r4 = true;
             */
            /* JADX WARN: Code restructure failed: missing block: B:56:0x00e3, code lost:
            
                r4 = false;
             */
            /* JADX WARN: Code restructure failed: missing block: B:58:0x00f8, code lost:
            
                if (com.android.systemui.animation.TransitionAnimator.Companion.getProgress(r11, r1, r11.contentAfterFadeInDelay, r11.contentAfterFadeInDuration) > 0.0f) goto L15;
             */
            /* JADX WARN: Removed duplicated region for block: B:25:0x016c  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x01d6  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x0217  */
            /* JADX WARN: Removed duplicated region for block: B:46:0x0172  */
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void onAnimationUpdate(android.animation.ValueAnimator r21) {
                /*
                    Method dump skipped, instructions count: 605
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.animation.TransitionAnimator$createAnimator$2.onAnimationUpdate(android.animation.ValueAnimator):void");
            }
        });
        return ofFloat;
    }

    public final boolean isExpandingFullyAbove$frameworks__base__packages__SystemUI__animation__android_common__PlatformAnimationLib(View view, State state) {
        int[] iArr = this.transitionContainerLocation;
        view.getLocationOnScreen(iArr);
        int i = state.top;
        int i2 = iArr[1];
        if (i <= i2 && state.bottom >= view.getHeight() + i2) {
            int i3 = state.left;
            int i4 = iArr[0];
            if (i3 <= i4 && state.right >= view.getWidth() + i4) {
                return true;
            }
        }
        return false;
    }

    public final TransitionAnimator$startAnimation$1 startAnimation(Controller controller, State state, int i, boolean z, boolean z2) {
        if (!controller.isLaunching()) {
            throw new IllegalStateException("isLaunching cannot be false when the returnAnimationFrameworkLibrary flag is disabled");
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setAlpha(0);
        ValueAnimator createAnimator = createAnimator(controller, state, gradientDrawable, z, z2);
        createAnimator.start();
        return new TransitionAnimator$startAnimation$1(createAnimator);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class State {
        public int bottom;
        public float bottomCornerRadius;
        public int left;
        public int right;
        public int top;
        public float topCornerRadius;
        public boolean visible;

        public State(int i, int i2, int i3, int i4, float f, float f2) {
            this.top = i;
            this.bottom = i2;
            this.left = i3;
            this.right = i4;
            this.topCornerRadius = f;
            this.bottomCornerRadius = f2;
            this.visible = true;
        }

        public final int getHeight() {
            return this.bottom - this.top;
        }

        public final int getWidth() {
            return this.right - this.left;
        }

        public /* synthetic */ State(int i, int i2, int i3, int i4, float f, float f2, int i5) {
            this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4, (i5 & 16) != 0 ? 0.0f : f, (i5 & 32) != 0 ? 0.0f : f2);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Controller {
        State createAnimatorState();

        default View getOpeningWindowSyncView() {
            return null;
        }

        ViewGroup getTransitionContainer();

        default WindowAnimationState getWindowAnimatorState() {
            return null;
        }

        boolean isLaunching();

        void setTransitionContainer(ViewGroup viewGroup);

        default void onTransitionAnimationEnd(boolean z) {
        }

        default void onTransitionAnimationStart(boolean z) {
        }

        default void onTransitionAnimationProgress(State state, float f, float f2) {
        }
    }
}
