package com.android.systemui.media.controls.ui.controller;

import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaCarouselController$listenForAnyStateToLockscreenTransition$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ MediaCarouselController this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaCarouselController$listenForAnyStateToLockscreenTransition$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ MediaCarouselController this$0;

        public /* synthetic */ AnonymousClass2(MediaCarouselController mediaCarouselController, int i) {
            this.$r8$classId = i;
            this.this$0 = mediaCarouselController;
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
        /* JADX WARN: Type inference failed for: r0v5, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    MediaCarouselController mediaCarouselController = this.this$0;
                    if (!mediaCarouselController.allowMediaPlayerOnLockScreen) {
                        mediaCarouselController.updateHostVisibility.invoke();
                    }
                    break;
                default:
                    ((Boolean) obj).getClass();
                    MediaCarouselController mediaCarouselController2 = this.this$0;
                    mediaCarouselController2.mediaCarousel.setVisibility(0);
                    mediaCarouselController2.updateHostVisibility.invoke();
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaCarouselController$listenForAnyStateToLockscreenTransition$1(MediaCarouselController mediaCarouselController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mediaCarouselController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MediaCarouselController$listenForAnyStateToLockscreenTransition$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MediaCarouselController$listenForAnyStateToLockscreenTransition$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            KeyguardTransitionInteractor keyguardTransitionInteractor = this.this$0.keyguardTransitionInteractor;
            Edge.Companion companion = Edge.Companion;
            Flow transition = keyguardTransitionInteractor.transition(Edge.Companion.create$default(null, KeyguardState.LOCKSCREEN, 1));
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, 0);
            this.label = 1;
            Object collect = transition.collect(new MediaCarouselController$listenForAnyStateToLockscreenTransition$1$invokeSuspend$$inlined$filter$1$2(anonymousClass2), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
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
