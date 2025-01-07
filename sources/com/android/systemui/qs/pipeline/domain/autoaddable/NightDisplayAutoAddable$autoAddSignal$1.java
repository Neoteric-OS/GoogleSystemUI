package com.android.systemui.qs.pipeline.domain.autoaddable;

import android.hardware.display.NightDisplayListener;
import com.android.systemui.dagger.NightDisplayListenerModule$Builder;
import com.android.systemui.qs.pipeline.domain.model.AutoAddSignal;
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
final class NightDisplayAutoAddable$autoAddSignal$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $userId;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NightDisplayAutoAddable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NightDisplayAutoAddable$autoAddSignal$1(NightDisplayAutoAddable nightDisplayAutoAddable, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = nightDisplayAutoAddable;
        this.$userId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NightDisplayAutoAddable$autoAddSignal$1 nightDisplayAutoAddable$autoAddSignal$1 = new NightDisplayAutoAddable$autoAddSignal$1(this.this$0, this.$userId, continuation);
        nightDisplayAutoAddable$autoAddSignal$1.L$0 = obj;
        return nightDisplayAutoAddable$autoAddSignal$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NightDisplayAutoAddable$autoAddSignal$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            NightDisplayListenerModule$Builder nightDisplayListenerModule$Builder = this.this$0.nightDisplayListenerBuilder;
            nightDisplayListenerModule$Builder.mUserId = this.$userId;
            final NightDisplayListener nightDisplayListener = new NightDisplayListener(nightDisplayListenerModule$Builder.mContext, nightDisplayListenerModule$Builder.mUserId, nightDisplayListenerModule$Builder.mBgHandler);
            final NightDisplayAutoAddable nightDisplayAutoAddable = this.this$0;
            nightDisplayListener.setCallback(new NightDisplayListener.Callback() { // from class: com.android.systemui.qs.pipeline.domain.autoaddable.NightDisplayAutoAddable$autoAddSignal$1$callback$1
                public final void onActivated(boolean z) {
                    if (z) {
                        ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(new AutoAddSignal.Add(-1, nightDisplayAutoAddable.spec));
                    }
                }

                public final void onAutoModeChanged(int i2) {
                    if (i2 == 1 || i2 == 2) {
                        ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(new AutoAddSignal.Add(-1, nightDisplayAutoAddable.spec));
                    }
                }
            });
            Function0 function0 = new Function0() { // from class: com.android.systemui.qs.pipeline.domain.autoaddable.NightDisplayAutoAddable$autoAddSignal$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    nightDisplayListener.setCallback((NightDisplayListener.Callback) null);
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
