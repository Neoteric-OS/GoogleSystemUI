package com.android.systemui.education.domain.interactor;

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
final class KeyboardTouchpadEduInteractor$start$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyboardTouchpadEduInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor$start$2$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ KeyboardTouchpadEduInteractor this$0;

        public AnonymousClass1(KeyboardTouchpadEduInteractor keyboardTouchpadEduInteractor) {
            this.this$0 = keyboardTouchpadEduInteractor;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(com.android.systemui.inputdevice.data.model.UserDeviceConnectionStatus r7, kotlin.coroutines.Continuation r8) {
            /*
                r6 = this;
                boolean r0 = r8 instanceof com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor$start$2$1$emit$1
                if (r0 == 0) goto L13
                r0 = r8
                com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor$start$2$1$emit$1 r0 = (com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor$start$2$1$emit$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor$start$2$1$emit$1 r0 = new com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor$start$2$1$emit$1
                r0.<init>(r6, r8)
            L18:
                java.lang.Object r8 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                kotlin.Unit r3 = kotlin.Unit.INSTANCE
                r4 = 2
                r5 = 1
                if (r2 == 0) goto L3c
                if (r2 == r5) goto L34
                if (r2 != r4) goto L2c
                kotlin.ResultKt.throwOnFailure(r8)
                goto L83
            L2c:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L34:
                java.lang.Object r6 = r0.L$0
                com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor$start$2$1 r6 = (com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor$start$2.AnonymousClass1) r6
                kotlin.ResultKt.throwOnFailure(r8)
                goto L5d
            L3c:
                kotlin.ResultKt.throwOnFailure(r8)
                boolean r7 = r7.isConnected
                if (r7 == 0) goto L83
                com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor r7 = r6.this$0
                com.android.systemui.education.domain.interactor.ContextualEducationInteractor r7 = r7.contextualEducationInteractor
                r0.L$0 = r6
                r0.label = r5
                com.android.systemui.education.data.repository.ContextualEducationRepository r7 = r7.repository
                com.android.systemui.education.data.repository.UserContextualEducationRepository r7 = (com.android.systemui.education.data.repository.UserContextualEducationRepository) r7
                com.android.systemui.education.data.repository.UserContextualEducationRepository$readEduDeviceConnectionTime$$inlined$map$1 r8 = new com.android.systemui.education.data.repository.UserContextualEducationRepository$readEduDeviceConnectionTime$$inlined$map$1
                kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest r2 = r7.prefData
                r8.<init>(r2, r7)
                java.lang.Object r8 = kotlinx.coroutines.flow.FlowKt.first(r8, r0)
                if (r8 != r1) goto L5d
                return r1
            L5d:
                com.android.systemui.education.data.model.EduDeviceConnectionTime r8 = (com.android.systemui.education.data.model.EduDeviceConnectionTime) r8
                java.time.Instant r7 = r8.touchpadFirstConnectionTime
                if (r7 != 0) goto L83
                com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor r6 = r6.this$0
                com.android.systemui.education.domain.interactor.ContextualEducationInteractor r6 = r6.contextualEducationInteractor
                r7 = 0
                r0.L$0 = r7
                r0.label = r4
                r6.getClass()
                com.android.systemui.education.domain.interactor.ContextualEducationInteractor$updateTouchpadFirstConnectionTime$2 r7 = new com.android.systemui.education.domain.interactor.ContextualEducationInteractor$updateTouchpadFirstConnectionTime$2
                r7.<init>()
                com.android.systemui.education.data.repository.ContextualEducationRepository r6 = r6.repository
                com.android.systemui.education.data.repository.UserContextualEducationRepository r6 = (com.android.systemui.education.data.repository.UserContextualEducationRepository) r6
                java.lang.Object r6 = r6.updateEduDeviceConnectionTime(r7, r0)
                if (r6 != r1) goto L7f
                goto L80
            L7f:
                r6 = r3
            L80:
                if (r6 != r1) goto L83
                return r1
            L83:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor$start$2.AnonymousClass1.emit(com.android.systemui.inputdevice.data.model.UserDeviceConnectionStatus, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyboardTouchpadEduInteractor$start$2(KeyboardTouchpadEduInteractor keyboardTouchpadEduInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyboardTouchpadEduInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyboardTouchpadEduInteractor$start$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyboardTouchpadEduInteractor$start$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            KeyboardTouchpadEduInteractor keyboardTouchpadEduInteractor = this.this$0;
            Flow flow = keyboardTouchpadEduInteractor.userInputDeviceRepository.isAnyTouchpadConnectedForUser;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(keyboardTouchpadEduInteractor);
            this.label = 1;
            if (flow.collect(anonymousClass1, this) == coroutineSingletons) {
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
