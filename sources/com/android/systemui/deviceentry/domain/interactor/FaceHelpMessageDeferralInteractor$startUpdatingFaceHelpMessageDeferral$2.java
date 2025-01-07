package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.deviceentry.domain.interactor.FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FaceHelpMessageDeferralInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$2(FaceHelpMessageDeferralInteractor faceHelpMessageDeferralInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = faceHelpMessageDeferralInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FaceHelpMessageDeferralInteractor faceHelpMessageDeferralInteractor = this.this$0;
            ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(faceHelpMessageDeferralInteractor.biometricSettingsInteractor.isFaceAuthEnrolledAndEnabled, new FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$2$invokeSuspend$$inlined$flatMapLatest$1(faceHelpMessageDeferralInteractor, null));
            FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$1.AnonymousClass2 anonymousClass2 = new FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$1.AnonymousClass2(this.this$0, 1);
            this.label = 1;
            if (transformLatest.collect(anonymousClass2, this) == coroutineSingletons) {
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
