package com.android.systemui.media.controls.ui.controller;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.media.controls.ui.controller.MediaCarouselController$listenForAnyStateToLockscreenTransition$1;
import com.android.systemui.scene.shared.model.Scenes;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaCarouselController$listenForAnyStateToGoneKeyguardTransition$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ MediaCarouselController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaCarouselController$listenForAnyStateToGoneKeyguardTransition$1(MediaCarouselController mediaCarouselController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mediaCarouselController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MediaCarouselController$listenForAnyStateToGoneKeyguardTransition$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MediaCarouselController$listenForAnyStateToGoneKeyguardTransition$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i = 1;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i2 = this.label;
        Unit unit = Unit.INSTANCE;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            KeyguardTransitionInteractor keyguardTransitionInteractor = this.this$0.keyguardTransitionInteractor;
            SceneKey sceneKey = Scenes.Bouncer;
            Flow isFinishedIn = keyguardTransitionInteractor.isFinishedIn(KeyguardState.GONE);
            MediaCarouselController$listenForAnyStateToLockscreenTransition$1.AnonymousClass2 anonymousClass2 = new MediaCarouselController$listenForAnyStateToLockscreenTransition$1.AnonymousClass2(this.this$0, i);
            this.label = 1;
            Object collect = isFinishedIn.collect(new MediaCarouselController$listenForAnyStateToGoneKeyguardTransition$1$invokeSuspend$$inlined$filter$1$2(anonymousClass2), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
