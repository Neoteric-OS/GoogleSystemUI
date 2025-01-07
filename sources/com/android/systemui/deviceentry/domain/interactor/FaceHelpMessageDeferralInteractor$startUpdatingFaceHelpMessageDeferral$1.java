package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.deviceentry.shared.model.AcquiredFaceAuthenticationStatus;
import com.android.systemui.deviceentry.shared.model.HelpFaceAuthenticationStatus;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FaceHelpMessageDeferralInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.deviceentry.domain.interactor.FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ FaceHelpMessageDeferralInteractor this$0;

        public /* synthetic */ AnonymousClass2(FaceHelpMessageDeferralInteractor faceHelpMessageDeferralInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = faceHelpMessageDeferralInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    AcquiredFaceAuthenticationStatus acquiredFaceAuthenticationStatus = (AcquiredFaceAuthenticationStatus) obj;
                    int i = acquiredFaceAuthenticationStatus.acquiredInfo;
                    FaceHelpMessageDeferralInteractor faceHelpMessageDeferralInteractor = this.this$0;
                    if (i == 20) {
                        faceHelpMessageDeferralInteractor.faceHelpMessageDeferral.reset$1();
                    }
                    faceHelpMessageDeferralInteractor.faceHelpMessageDeferral.processFrame(acquiredFaceAuthenticationStatus.acquiredInfo);
                    break;
                default:
                    HelpFaceAuthenticationStatus helpFaceAuthenticationStatus = (HelpFaceAuthenticationStatus) obj;
                    String str = helpFaceAuthenticationStatus.msg;
                    if (str != null) {
                        this.this$0.faceHelpMessageDeferral.updateMessage(helpFaceAuthenticationStatus.msgId, str);
                    }
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$1(FaceHelpMessageDeferralInteractor faceHelpMessageDeferralInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = faceHelpMessageDeferralInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FaceHelpMessageDeferralInteractor faceHelpMessageDeferralInteractor = this.this$0;
            ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(faceHelpMessageDeferralInteractor.biometricSettingsInteractor.isFaceAuthEnrolledAndEnabled, new FaceHelpMessageDeferralInteractor$startUpdatingFaceHelpMessageDeferral$1$invokeSuspend$$inlined$flatMapLatest$1(faceHelpMessageDeferralInteractor, null));
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, 0);
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
