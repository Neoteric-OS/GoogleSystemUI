package com.android.systemui.unfold;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.SystemProperties;
import android.view.animation.DecelerateInterpolator;
import com.android.systemui.statusbar.LightRevealScrim;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FoldLightRevealOverlayAnimation$playFoldLightRevealOverlayAnimation$2 extends SuspendLambda implements Function1 {
    int label;
    final /* synthetic */ FoldLightRevealOverlayAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FoldLightRevealOverlayAnimation$playFoldLightRevealOverlayAnimation$2(FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation, Continuation continuation) {
        super(1, continuation);
        this.this$0 = foldLightRevealOverlayAnimation;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new FoldLightRevealOverlayAnimation$playFoldLightRevealOverlayAnimation$2(this.this$0, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v2, types: [android.animation.Animator$AnimatorListener, com.android.systemui.unfold.FoldLightRevealOverlayAnimation$startAndAwaitCompletion$2$listener$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.revealProgressValueAnimator.setDuration(SystemProperties.getLong("persist.fold_animation_duration", 200L));
            this.this$0.revealProgressValueAnimator.setInterpolator(new DecelerateInterpolator());
            final FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation = this.this$0;
            foldLightRevealOverlayAnimation.revealProgressValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.unfold.FoldLightRevealOverlayAnimation$playFoldLightRevealOverlayAnimation$2.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    FullscreenLightRevealAnimationController fullscreenLightRevealAnimationController = FoldLightRevealOverlayAnimation.this.controller;
                    if (fullscreenLightRevealAnimationController == null) {
                        fullscreenLightRevealAnimationController = null;
                    }
                    float animatedFraction = valueAnimator.getAnimatedFraction();
                    LightRevealScrim lightRevealScrim = fullscreenLightRevealAnimationController.scrimView;
                    if (lightRevealScrim == null) {
                        return;
                    }
                    lightRevealScrim.setRevealAmount(animatedFraction);
                }
            });
            final ValueAnimator valueAnimator = this.this$0.revealProgressValueAnimator;
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt__IntrinsicsJvmKt.intercepted(this));
            cancellableContinuationImpl.initCancellability();
            final ?? r6 = new AnimatorListenerAdapter() { // from class: com.android.systemui.unfold.FoldLightRevealOverlayAnimation$startAndAwaitCompletion$2$listener$1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    CancellableContinuationImpl.this.resumeWith(Unit.INSTANCE);
                    valueAnimator.removeListener(this);
                }
            };
            valueAnimator.addListener(r6);
            cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: com.android.systemui.unfold.FoldLightRevealOverlayAnimation$startAndAwaitCompletion$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    valueAnimator.removeListener(r6);
                    return Unit.INSTANCE;
                }
            });
            valueAnimator.start();
            Object result = cancellableContinuationImpl.getResult();
            if (result != coroutineSingletons) {
                result = unit;
            }
            if (result == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
