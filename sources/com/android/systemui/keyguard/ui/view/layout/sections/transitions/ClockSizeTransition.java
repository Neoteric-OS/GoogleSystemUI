package com.android.systemui.keyguard.ui.view.layout.sections.transitions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import com.android.app.animation.Interpolators;
import com.android.systemui.keyguard.ui.view.layout.blueprints.transitions.IntraBlueprintTransition;
import com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.wm.shell.R;
import com.google.android.material.math.MathUtils;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$FloatRef;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockSizeTransition extends TransitionSet {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ClockFaceInTransition extends ClockFaceTransition {
        public static final Interpolator CLOCK_IN_INTERPOLATOR = Interpolators.LINEAR_OUT_SLOW_IN;
        public final boolean isLargeClock;
        public final float smallClockMoveScale;

        public ClockFaceInTransition(IntraBlueprintTransition.Config config, KeyguardClockViewModel keyguardClockViewModel) {
            super(keyguardClockViewModel);
            this.isLargeClock = ((Boolean) ((StateFlowImpl) keyguardClockViewModel.isLargeClockVisible.$$delegate_0).getValue()).booleanValue();
            this.smallClockMoveScale = 0.3576017f;
            setDuration(167L);
            setStartDelay(133L);
            setInterpolator(CLOCK_IN_INTERPOLATOR);
            addTargets();
        }

        @Override // com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition.ClockFaceTransition
        public final float getSmallClockMoveScale() {
            return this.smallClockMoveScale;
        }

        @Override // com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition.ClockFaceTransition
        public final boolean isLargeClock() {
            return this.isLargeClock;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ClockFaceOutTransition extends ClockFaceTransition {
        public static final Interpolator CLOCK_OUT_INTERPOLATOR = Interpolators.LINEAR;
        public final boolean isLargeClock;
        public final float smallClockMoveScale;

        public ClockFaceOutTransition(IntraBlueprintTransition.Config config, KeyguardClockViewModel keyguardClockViewModel) {
            super(keyguardClockViewModel);
            this.isLargeClock = !((Boolean) ((StateFlowImpl) keyguardClockViewModel.isLargeClockVisible.$$delegate_0).getValue()).booleanValue();
            this.smallClockMoveScale = 0.13753878f;
            setDuration(133L);
            setInterpolator(CLOCK_OUT_INTERPOLATOR);
            addTargets();
        }

        @Override // com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition.ClockFaceTransition
        public final float getSmallClockMoveScale() {
            return this.smallClockMoveScale;
        }

        @Override // com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition.ClockFaceTransition
        public final boolean isLargeClock() {
            return this.isLargeClock;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class ClockFaceTransition extends VisibilityBoundsTransition {
        public final KeyguardClockViewModel viewModel;

        public ClockFaceTransition(KeyguardClockViewModel keyguardClockViewModel) {
            this.viewModel = keyguardClockViewModel;
        }

        public final void addTargets() {
            if (!isLargeClock()) {
                int i = ClockSizeTransition.$r8$clinit;
                addTarget(R.id.lockscreen_clock_view);
                return;
            }
            ClockController clockController = (ClockController) ((StateFlowImpl) this.viewModel.currentClock.$$delegate_0).getValue();
            if (clockController == null) {
                Log.e(this.TAG, "No large clock set, falling back");
                addTarget(R.id.lockscreen_clock_view_large);
            } else {
                int i2 = ClockSizeTransition.$r8$clinit;
                Iterator it = clockController.getLargeClock().getLayout().getViews().iterator();
                while (it.hasNext()) {
                    addTarget((View) it.next());
                }
            }
        }

        @Override // com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition.VisibilityBoundsTransition
        public final boolean getCaptureSmartspace() {
            return !isLargeClock();
        }

        public abstract float getSmallClockMoveScale();

        public abstract boolean isLargeClock();

        @Override // com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition.VisibilityBoundsTransition
        public final void mutateBounds(boolean z, boolean z2, Rect rect, Rect rect2, Rect rect3, Rect rect4) {
            if (z == z2) {
                return;
            }
            rect.set(rect2);
            if (isLargeClock()) {
                return;
            }
            if (rect4 == null || rect3 == null) {
                Log.e(this.TAG, "mutateBounds: smallClock received no smartspace bounds");
                return;
            }
            int abs = (int) Math.abs(getSmallClockMoveScale() * (rect4.top - rect3.top));
            rect.top = rect2.top - abs;
            rect.bottom = rect2.bottom - abs;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SmartspaceMoveTransition extends VisibilityBoundsTransition {
        @Override // com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition.VisibilityBoundsTransition
        public final boolean getCaptureSmartspace() {
            return false;
        }

        @Override // com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition.VisibilityBoundsTransition
        public final void mutateBounds(boolean z, boolean z2, Rect rect, Rect rect2, Rect rect3, Rect rect4) {
            if (z == z2) {
                return;
            }
            int i = ClockSizeTransition.$r8$clinit;
            rect2.set(rect);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class VisibilityBoundsTransition extends Transition {
        public static final String[] TRANSITION_PROPERTIES = {"ClockSizeTransition:Visibility", "ClockSizeTransition:Alpha", "ClockSizeTransition:Bounds", "ClockSizeTransition:SSBounds"};
        public final String TAG;

        public VisibilityBoundsTransition() {
            String simpleName = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
            Intrinsics.checkNotNull(simpleName);
            this.TAG = simpleName;
        }

        public static final void createAnimator$assignAnimValues(Ref$FloatRef ref$FloatRef, float f, View view, Rect rect, Rect rect2, float f2, Integer num) {
            Rect rect3 = new Rect((int) MathUtils.lerp(rect.left, rect2.left, f2), (int) MathUtils.lerp(rect.top, rect2.top, f2), (int) MathUtils.lerp(rect.right, rect2.right, f2), (int) MathUtils.lerp(rect.bottom, rect2.bottom, f2));
            float lerp = MathUtils.lerp(ref$FloatRef.element, f, f2);
            int i = ClockSizeTransition.$r8$clinit;
            view.setVisibility(num != null ? num.intValue() : 0);
            view.setAlpha(lerp);
            view.setLeftTopRightBottom(rect3.left, rect3.top, rect3.right, rect3.bottom);
        }

        @Override // android.transition.Transition
        public final void captureEndValues(TransitionValues transitionValues) {
            captureValues(transitionValues);
        }

        @Override // android.transition.Transition
        public final void captureStartValues(TransitionValues transitionValues) {
            captureValues(transitionValues);
        }

        public final void captureValues(TransitionValues transitionValues) {
            View view = transitionValues.view;
            transitionValues.values.put("ClockSizeTransition:Visibility", Integer.valueOf(view.getVisibility()));
            transitionValues.values.put("ClockSizeTransition:Alpha", Float.valueOf(view.getAlpha()));
            transitionValues.values.put("ClockSizeTransition:Bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            if (getCaptureSmartspace()) {
                View view2 = (View) view.getParent();
                View findViewById = view2.findViewById(R.id.bc_smartspace_view);
                if (findViewById == null) {
                    findViewById = view2.findViewById(R.id.keyguard_slice_view);
                }
                if (findViewById != null) {
                    transitionValues.values.put("ClockSizeTransition:SSBounds", new Rect(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom()));
                    return;
                }
                Log.e(this.TAG, "Failed to find smartspace equivalent target under " + view2);
            }
        }

        /* JADX WARN: Type inference failed for: r6v3, types: [com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition$VisibilityBoundsTransition$createAnimator$1$predrawCallback$1] */
        @Override // android.transition.Transition
        public final Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
            boolean z;
            boolean z2;
            boolean z3;
            if (transitionValues == null || transitionValues2 == null) {
                Log.w(this.TAG, "Couldn't create animator: startValues=" + transitionValues + "; endValues=" + transitionValues2);
                return null;
            }
            final Ref$IntRef ref$IntRef = new Ref$IntRef();
            int intValue = ((Integer) transitionValues.values.get("ClockSizeTransition:Visibility")).intValue();
            ref$IntRef.element = intValue;
            boolean z4 = intValue == 0;
            final Ref$FloatRef ref$FloatRef = new Ref$FloatRef();
            ref$FloatRef.element = ((Float) transitionValues.values.get("ClockSizeTransition:Alpha")).floatValue();
            final Rect rect = (Rect) transitionValues.values.get("ClockSizeTransition:Bounds");
            Rect rect2 = (Rect) transitionValues.values.get("ClockSizeTransition:SSBounds");
            final View view = transitionValues2.view;
            final int intValue2 = ((Integer) transitionValues2.values.get("ClockSizeTransition:Visibility")).intValue();
            final Rect rect3 = (Rect) transitionValues2.values.get("ClockSizeTransition:Bounds");
            Rect rect4 = (Rect) transitionValues2.values.get("ClockSizeTransition:SSBounds");
            boolean z5 = intValue2 == 0;
            float f = z5 ? 1.0f : 0.0f;
            if (!z4) {
                ref$FloatRef.element = 0.0f;
            } else if (ref$FloatRef.element <= 0.0f) {
                ref$IntRef.element = 4;
                z = false;
                z2 = z;
                z3 = z5;
                mutateBounds(z, z5, rect, rect3, rect2, rect4);
                if (z2 != z3 && rect.equals(rect3)) {
                    int i = ClockSizeTransition.$r8$clinit;
                    return null;
                }
                final boolean z6 = (z2 || z3) ? false : true;
                int i2 = ClockSizeTransition.$r8$clinit;
                final ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                final float f2 = f;
                final ?? r6 = new ViewTreeObserver.OnPreDrawListener(ofFloat, ref$FloatRef, f2, this, view, rect, rect3) { // from class: com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition$VisibilityBoundsTransition$createAnimator$1$predrawCallback$1
                    public final /* synthetic */ ValueAnimator $anim;
                    public final /* synthetic */ Ref$FloatRef $fromAlpha;
                    public final /* synthetic */ Rect $fromBounds;
                    public final /* synthetic */ float $toAlpha;
                    public final /* synthetic */ Rect $toBounds;
                    public final /* synthetic */ View $toView;
                    public final /* synthetic */ ClockSizeTransition.VisibilityBoundsTransition this$0;

                    {
                        this.$toView = view;
                        this.$fromBounds = rect;
                        this.$toBounds = rect3;
                    }

                    @Override // android.view.ViewTreeObserver.OnPreDrawListener
                    public final boolean onPreDraw() {
                        ClockSizeTransition.VisibilityBoundsTransition.createAnimator$assignAnimValues(this.$fromAlpha, this.$toAlpha, this.$toView, this.$fromBounds, this.$toBounds, this.$anim.getAnimatedFraction(), null);
                        return true;
                    }
                };
                addListener(new TransitionListenerAdapter() { // from class: com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition$VisibilityBoundsTransition$createAnimator$1$1
                    @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                    public final void onTransitionEnd(Transition transition) {
                        view.getViewTreeObserver().removeOnPreDrawListener(r6);
                    }

                    @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                    public final void onTransitionStart(Transition transition) {
                        view.getViewTreeObserver().addOnPreDrawListener(r6);
                    }
                });
                ofFloat.addListener(new AnimatorListenerAdapter(ref$FloatRef, f2, this, view, rect, rect3, intValue2, z6) { // from class: com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition$VisibilityBoundsTransition$createAnimator$1$listener$1
                    public final /* synthetic */ Ref$FloatRef $fromAlpha;
                    public final /* synthetic */ Rect $fromBounds;
                    public final /* synthetic */ boolean $sendToBack;
                    public final /* synthetic */ float $toAlpha;
                    public final /* synthetic */ Rect $toBounds;
                    public final /* synthetic */ View $toView;
                    public final /* synthetic */ int $toVis;
                    public final /* synthetic */ ClockSizeTransition.VisibilityBoundsTransition this$0;

                    {
                        this.$toView = view;
                        this.$fromBounds = rect;
                        this.$toBounds = rect3;
                        this.$toVis = intValue2;
                        this.$sendToBack = z6;
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        ClockSizeTransition.VisibilityBoundsTransition.createAnimator$assignAnimValues(this.$fromAlpha, this.$toAlpha, this.$toView, this.$fromBounds, this.$toBounds, 1.0f, Integer.valueOf(this.$toVis));
                        if (this.$sendToBack) {
                            this.$toView.setTranslationZ(0.0f);
                        }
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationStart(Animator animator) {
                        ClockSizeTransition.VisibilityBoundsTransition.createAnimator$assignAnimValues(this.$fromAlpha, this.$toAlpha, this.$toView, this.$fromBounds, this.$toBounds, 0.0f, Integer.valueOf(Ref$IntRef.this.element));
                    }
                });
                createAnimator$assignAnimValues(ref$FloatRef, f, view, rect, rect3, 0.0f, Integer.valueOf(ref$IntRef.element));
                return ofFloat;
            }
            z = z4;
            z2 = z;
            z3 = z5;
            mutateBounds(z, z5, rect, rect3, rect2, rect4);
            if (z2 != z3) {
            }
            if (z2) {
            }
            int i22 = ClockSizeTransition.$r8$clinit;
            final ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
            final float f22 = f;
            final ClockSizeTransition$VisibilityBoundsTransition$createAnimator$1$predrawCallback$1 r62 = new ViewTreeObserver.OnPreDrawListener(ofFloat2, ref$FloatRef, f22, this, view, rect, rect3) { // from class: com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition$VisibilityBoundsTransition$createAnimator$1$predrawCallback$1
                public final /* synthetic */ ValueAnimator $anim;
                public final /* synthetic */ Ref$FloatRef $fromAlpha;
                public final /* synthetic */ Rect $fromBounds;
                public final /* synthetic */ float $toAlpha;
                public final /* synthetic */ Rect $toBounds;
                public final /* synthetic */ View $toView;
                public final /* synthetic */ ClockSizeTransition.VisibilityBoundsTransition this$0;

                {
                    this.$toView = view;
                    this.$fromBounds = rect;
                    this.$toBounds = rect3;
                }

                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public final boolean onPreDraw() {
                    ClockSizeTransition.VisibilityBoundsTransition.createAnimator$assignAnimValues(this.$fromAlpha, this.$toAlpha, this.$toView, this.$fromBounds, this.$toBounds, this.$anim.getAnimatedFraction(), null);
                    return true;
                }
            };
            addListener(new TransitionListenerAdapter() { // from class: com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition$VisibilityBoundsTransition$createAnimator$1$1
                @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                public final void onTransitionEnd(Transition transition) {
                    view.getViewTreeObserver().removeOnPreDrawListener(r62);
                }

                @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
                public final void onTransitionStart(Transition transition) {
                    view.getViewTreeObserver().addOnPreDrawListener(r62);
                }
            });
            ofFloat2.addListener(new AnimatorListenerAdapter(ref$FloatRef, f22, this, view, rect, rect3, intValue2, z6) { // from class: com.android.systemui.keyguard.ui.view.layout.sections.transitions.ClockSizeTransition$VisibilityBoundsTransition$createAnimator$1$listener$1
                public final /* synthetic */ Ref$FloatRef $fromAlpha;
                public final /* synthetic */ Rect $fromBounds;
                public final /* synthetic */ boolean $sendToBack;
                public final /* synthetic */ float $toAlpha;
                public final /* synthetic */ Rect $toBounds;
                public final /* synthetic */ View $toView;
                public final /* synthetic */ int $toVis;
                public final /* synthetic */ ClockSizeTransition.VisibilityBoundsTransition this$0;

                {
                    this.$toView = view;
                    this.$fromBounds = rect;
                    this.$toBounds = rect3;
                    this.$toVis = intValue2;
                    this.$sendToBack = z6;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    ClockSizeTransition.VisibilityBoundsTransition.createAnimator$assignAnimValues(this.$fromAlpha, this.$toAlpha, this.$toView, this.$fromBounds, this.$toBounds, 1.0f, Integer.valueOf(this.$toVis));
                    if (this.$sendToBack) {
                        this.$toView.setTranslationZ(0.0f);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator) {
                    ClockSizeTransition.VisibilityBoundsTransition.createAnimator$assignAnimValues(this.$fromAlpha, this.$toAlpha, this.$toView, this.$fromBounds, this.$toBounds, 0.0f, Integer.valueOf(Ref$IntRef.this.element));
                }
            });
            createAnimator$assignAnimValues(ref$FloatRef, f, view, rect, rect3, 0.0f, Integer.valueOf(ref$IntRef.element));
            return ofFloat2;
        }

        public abstract boolean getCaptureSmartspace();

        @Override // android.transition.Transition
        public final String[] getTransitionProperties() {
            return TRANSITION_PROPERTIES;
        }

        public abstract void mutateBounds(boolean z, boolean z2, Rect rect, Rect rect2, Rect rect3, Rect rect4);
    }
}
