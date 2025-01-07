package com.android.systemui.accessibility.data.repository;

import android.view.accessibility.CaptioningManager;
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
/* loaded from: classes.dex */
final class CaptioningRepositoryImpl$captioningModel$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ CaptioningManager $this_captioningModel;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CaptioningRepositoryImpl$captioningModel$2(CaptioningManager captioningManager, Continuation continuation) {
        super(2, continuation);
        this.$this_captioningModel = captioningManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CaptioningRepositoryImpl$captioningModel$2 captioningRepositoryImpl$captioningModel$2 = new CaptioningRepositoryImpl$captioningModel$2(this.$this_captioningModel, continuation);
        captioningRepositoryImpl$captioningModel$2.L$0 = obj;
        return captioningRepositoryImpl$captioningModel$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CaptioningRepositoryImpl$captioningModel$2) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.view.accessibility.CaptioningManager$CaptioningChangeListener, com.android.systemui.accessibility.data.repository.CaptioningRepositoryImpl$captioningModel$2$listener$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new CaptioningManager.CaptioningChangeListener() { // from class: com.android.systemui.accessibility.data.repository.CaptioningRepositoryImpl$captioningModel$2$listener$1
                @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
                public final void onSystemAudioCaptioningChanged(boolean z) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Unit.INSTANCE);
                }

                @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
                public final void onSystemAudioCaptioningUiChanged(boolean z) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Unit.INSTANCE);
                }
            };
            this.$this_captioningModel.addCaptioningChangeListener(r1);
            final CaptioningManager captioningManager = this.$this_captioningModel;
            Function0 function0 = new Function0() { // from class: com.android.systemui.accessibility.data.repository.CaptioningRepositoryImpl$captioningModel$2.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    captioningManager.removeCaptioningChangeListener(r1);
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
