package com.android.systemui.unfold.progress;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.util.FloatProperty;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.updates.DeviceFoldStateProvider;
import com.android.systemui.unfold.updates.FoldStateProvider$FoldUpdatesListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FixedTimingTransitionProgressProvider implements UnfoldTransitionProgressProvider, FoldStateProvider$FoldUpdatesListener {
    public final ObjectAnimator animator;
    public final List listeners;
    public float transitionProgress;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationProgressProperty extends FloatProperty {
        public static final AnimationProgressProperty INSTANCE = new AnimationProgressProperty("animation_progress");

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((FixedTimingTransitionProgressProvider) obj).transitionProgress);
        }

        @Override // android.util.FloatProperty
        public final void setValue(Object obj, float f) {
            FixedTimingTransitionProgressProvider fixedTimingTransitionProgressProvider = (FixedTimingTransitionProgressProvider) obj;
            Iterator it = fixedTimingTransitionProgressProvider.listeners.iterator();
            while (it.hasNext()) {
                ((UnfoldTransitionProgressProvider.TransitionProgressListener) it.next()).onTransitionProgress(f);
            }
            fixedTimingTransitionProgressProvider.transitionProgress = f;
        }
    }

    public FixedTimingTransitionProgressProvider(DeviceFoldStateProvider deviceFoldStateProvider) {
        AnimatorListener animatorListener = new AnimatorListener();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, AnimationProgressProperty.INSTANCE, 0.0f, 1.0f);
        ofFloat.setDuration(400L);
        ofFloat.addListener(animatorListener);
        this.animator = ofFloat;
        this.listeners = new ArrayList();
        deviceFoldStateProvider.addCallback(this);
        deviceFoldStateProvider.start();
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void addCallback(Object obj) {
        this.listeners.add((UnfoldTransitionProgressProvider.TransitionProgressListener) obj);
    }

    @Override // com.android.systemui.unfold.updates.FoldStateProvider$FoldUpdatesListener
    public final void onFoldUpdate(int i) {
        if (i == 4) {
            this.animator.cancel();
        }
    }

    @Override // com.android.systemui.unfold.updates.FoldStateProvider$FoldUpdatesListener
    public final void onUnfoldedScreenAvailable() {
        this.animator.start();
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void removeCallback(Object obj) {
        this.listeners.remove((UnfoldTransitionProgressProvider.TransitionProgressListener) obj);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimatorListener implements Animator.AnimatorListener {
        public AnimatorListener() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            Iterator it = FixedTimingTransitionProgressProvider.this.listeners.iterator();
            while (it.hasNext()) {
                ((UnfoldTransitionProgressProvider.TransitionProgressListener) it.next()).onTransitionFinished();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            Iterator it = FixedTimingTransitionProgressProvider.this.listeners.iterator();
            while (it.hasNext()) {
                ((UnfoldTransitionProgressProvider.TransitionProgressListener) it.next()).onTransitionStarted();
            }
            Iterator it2 = FixedTimingTransitionProgressProvider.this.listeners.iterator();
            while (it2.hasNext()) {
                ((UnfoldTransitionProgressProvider.TransitionProgressListener) it2.next()).onTransitionFinishing();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }
    }
}
