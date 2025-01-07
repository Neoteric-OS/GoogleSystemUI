package com.android.systemui.keyguard.data.quickaffordance;

import android.service.quickaccesswallet.GetWalletCardsError;
import android.service.quickaccesswallet.GetWalletCardsResponse;
import android.service.quickaccesswallet.QuickAccessWalletClient;
import com.android.systemui.wallet.util.WalletCardUtilsKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class QuickAccessWalletKeyguardQuickAffordanceConfig$queryCards$2 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ QuickAccessWalletKeyguardQuickAffordanceConfig this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QuickAccessWalletKeyguardQuickAffordanceConfig$queryCards$2(QuickAccessWalletKeyguardQuickAffordanceConfig quickAccessWalletKeyguardQuickAffordanceConfig, Continuation continuation) {
        super(2, continuation);
        this.this$0 = quickAccessWalletKeyguardQuickAffordanceConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new QuickAccessWalletKeyguardQuickAffordanceConfig$queryCards$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((QuickAccessWalletKeyguardQuickAffordanceConfig$queryCards$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            QuickAccessWalletKeyguardQuickAffordanceConfig quickAccessWalletKeyguardQuickAffordanceConfig = this.this$0;
            this.L$0 = quickAccessWalletKeyguardQuickAffordanceConfig;
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt__IntrinsicsJvmKt.intercepted(this));
            cancellableContinuationImpl.initCancellability();
            quickAccessWalletKeyguardQuickAffordanceConfig.walletController.queryWalletCards(new QuickAccessWalletClient.OnWalletCardsRetrievedCallback() { // from class: com.android.systemui.keyguard.data.quickaffordance.QuickAccessWalletKeyguardQuickAffordanceConfig$queryCards$2$1$callback$1
                public final void onWalletCardRetrievalError(GetWalletCardsError getWalletCardsError) {
                    CancellableContinuationImpl.this.resumeWith(EmptyList.INSTANCE);
                }

                public final void onWalletCardsRetrieved(GetWalletCardsResponse getWalletCardsResponse) {
                    CancellableContinuationImpl.this.resumeWith(WalletCardUtilsKt.getPaymentCards(getWalletCardsResponse.getWalletCards()));
                }
            }, 1);
            obj = cancellableContinuationImpl.getResult();
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
