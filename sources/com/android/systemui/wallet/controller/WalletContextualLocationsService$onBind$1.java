package com.android.systemui.wallet.controller;

import android.util.Log;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class WalletContextualLocationsService$onBind$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ WalletContextualLocationsService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WalletContextualLocationsService$onBind$1(WalletContextualLocationsService walletContextualLocationsService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = walletContextualLocationsService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new WalletContextualLocationsService$onBind$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((WalletContextualLocationsService$onBind$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
        }
        ResultKt.throwOnFailure(obj);
        final WalletContextualLocationsService walletContextualLocationsService = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = walletContextualLocationsService.controller.allWalletCards;
        FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.wallet.controller.WalletContextualLocationsService$onBind$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                List list = (List) obj2;
                Log.i("WalletContextualLocationsService", "Number of cards registered " + list.size());
                IWalletCardsUpdatedListener iWalletCardsUpdatedListener = WalletContextualLocationsService.this.listener;
                if (iWalletCardsUpdatedListener != null) {
                    ((IWalletCardsUpdatedListener$Stub$Proxy) iWalletCardsUpdatedListener).registerNewWalletCards(list);
                }
                return Unit.INSTANCE;
            }
        };
        this.label = 1;
        ((StateFlowImpl) readonlyStateFlow.$$delegate_0).collect(flowCollector, this);
        return coroutineSingletons;
    }
}
