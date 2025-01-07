package com.google.android.systemui.fingerprint;

import android.R;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ FingerprintInteractiveToAuthProviderGoogle this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1(Continuation continuation, FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle) {
        super(3, continuation);
        this.this$0 = fingerprintInteractiveToAuthProviderGoogle;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        FingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1 fingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1 = new FingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        fingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        fingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return fingerprintInteractiveToAuthProviderGoogle$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            final int intValue = ((Number) this.L$1).intValue();
            final FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle = this.this$0;
            Flow conflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$1(fingerprintInteractiveToAuthProviderGoogle, new Function0() { // from class: com.google.android.systemui.fingerprint.FingerprintInteractiveToAuthProviderGoogle$enabledForCurrentUser$1$getCurrentSettingValue$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle2 = FingerprintInteractiveToAuthProviderGoogle.this;
                    return Boolean.valueOf(fingerprintInteractiveToAuthProviderGoogle2.secureSettings.getIntForUser("sfps_performant_auth_enabled_v2", fingerprintInteractiveToAuthProviderGoogle2.context.getResources().getBoolean(R.bool.config_profcollectReportUploaderEnabled) ? 1 : 0, intValue) == 0);
                }
            }, null));
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, conflatedCallbackFlow, this) == coroutineSingletons) {
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
