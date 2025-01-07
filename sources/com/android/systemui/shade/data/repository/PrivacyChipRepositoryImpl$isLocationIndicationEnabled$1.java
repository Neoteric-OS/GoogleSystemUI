package com.android.systemui.shade.data.repository;

import com.android.systemui.privacy.PrivacyConfig;
import com.android.systemui.privacy.PrivacyConfig$addCallback$1;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.lang.ref.WeakReference;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class PrivacyChipRepositoryImpl$isLocationIndicationEnabled$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PrivacyChipRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrivacyChipRepositoryImpl$isLocationIndicationEnabled$1(PrivacyChipRepositoryImpl privacyChipRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = privacyChipRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        PrivacyChipRepositoryImpl$isLocationIndicationEnabled$1 privacyChipRepositoryImpl$isLocationIndicationEnabled$1 = new PrivacyChipRepositoryImpl$isLocationIndicationEnabled$1(this.this$0, continuation);
        privacyChipRepositoryImpl$isLocationIndicationEnabled$1.L$0 = obj;
        return privacyChipRepositoryImpl$isLocationIndicationEnabled$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((PrivacyChipRepositoryImpl$isLocationIndicationEnabled$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.privacy.PrivacyConfig$Callback, com.android.systemui.shade.data.repository.PrivacyChipRepositoryImpl$isLocationIndicationEnabled$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new PrivacyConfig.Callback() { // from class: com.android.systemui.shade.data.repository.PrivacyChipRepositoryImpl$isLocationIndicationEnabled$1$callback$1
                @Override // com.android.systemui.privacy.PrivacyConfig.Callback
                public final void onFlagLocationChanged(boolean z) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Boolean.valueOf(z));
                }
            };
            this.this$0.privacyConfig.addCallback(r1);
            final PrivacyChipRepositoryImpl privacyChipRepositoryImpl = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.shade.data.repository.PrivacyChipRepositoryImpl$isLocationIndicationEnabled$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    PrivacyConfig privacyConfig = PrivacyChipRepositoryImpl.this.privacyConfig;
                    PrivacyChipRepositoryImpl$isLocationIndicationEnabled$1$callback$1 privacyChipRepositoryImpl$isLocationIndicationEnabled$1$callback$1 = r1;
                    privacyConfig.getClass();
                    ((ExecutorImpl) privacyConfig.uiExecutor).execute(new PrivacyConfig$addCallback$1(privacyConfig, new WeakReference(privacyChipRepositoryImpl$isLocationIndicationEnabled$1$callback$1), 1));
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
