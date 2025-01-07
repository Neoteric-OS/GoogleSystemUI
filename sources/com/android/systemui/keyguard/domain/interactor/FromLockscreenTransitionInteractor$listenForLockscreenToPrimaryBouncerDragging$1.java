package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.shade.data.repository.ShadeRepositoryImpl;
import com.android.systemui.util.kotlin.Utils;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromLockscreenTransitionInteractor$listenForLockscreenToPrimaryBouncerDragging$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Ref$ObjectRef $transitionId;
    int label;
    final /* synthetic */ FromLockscreenTransitionInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.FromLockscreenTransitionInteractor$listenForLockscreenToPrimaryBouncerDragging$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ Ref$ObjectRef $transitionId;
        public final /* synthetic */ FromLockscreenTransitionInteractor this$0;

        public AnonymousClass1(Ref$ObjectRef ref$ObjectRef, FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor) {
            this.$transitionId = ref$ObjectRef;
            this.this$0 = fromLockscreenTransitionInteractor;
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x00d3  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x005d  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(com.android.systemui.util.kotlin.Sextuple r24, kotlin.coroutines.Continuation r25) {
            /*
                Method dump skipped, instructions count: 346
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.FromLockscreenTransitionInteractor$listenForLockscreenToPrimaryBouncerDragging$1.AnonymousClass1.emit(com.android.systemui.util.kotlin.Sextuple, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromLockscreenTransitionInteractor$listenForLockscreenToPrimaryBouncerDragging$1(FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor, Ref$ObjectRef ref$ObjectRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromLockscreenTransitionInteractor;
        this.$transitionId = ref$ObjectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromLockscreenTransitionInteractor$listenForLockscreenToPrimaryBouncerDragging$1(this.this$0, this.$transitionId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromLockscreenTransitionInteractor$listenForLockscreenToPrimaryBouncerDragging$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Utils.Companion companion = Utils.Companion;
            FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor = this.this$0;
            ReadonlyStateFlow readonlyStateFlow = ((ShadeRepositoryImpl) fromLockscreenTransitionInteractor.shadeRepository).legacyShadeExpansion;
            ReadonlyStateFlow readonlyStateFlow2 = fromLockscreenTransitionInteractor.transitionInteractor.startedKeyguardTransitionStep;
            ReadonlyStateFlow readonlyStateFlow3 = fromLockscreenTransitionInteractor.internalTransitionInteractor.currentTransitionInfoInternal;
            KeyguardInteractor keyguardInteractor = fromLockscreenTransitionInteractor.keyguardInteractor;
            SafeFlow sample = companion.sample(readonlyStateFlow, readonlyStateFlow2, readonlyStateFlow3, keyguardInteractor.statusBarState, keyguardInteractor.isKeyguardDismissible, keyguardInteractor.isKeyguardOccluded);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$transitionId, this.this$0);
            this.label = 1;
            if (sample.collect(anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
