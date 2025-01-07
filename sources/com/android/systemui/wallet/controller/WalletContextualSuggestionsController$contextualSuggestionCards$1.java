package com.android.systemui.wallet.controller;

import android.service.quickaccesswallet.WalletCard;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class WalletContextualSuggestionsController$contextualSuggestionCards$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        WalletContextualSuggestionsController$contextualSuggestionCards$1 walletContextualSuggestionsController$contextualSuggestionCards$1 = new WalletContextualSuggestionsController$contextualSuggestionCards$1(3, (Continuation) obj3);
        walletContextualSuggestionsController$contextualSuggestionCards$1.L$0 = (List) obj;
        walletContextualSuggestionsController$contextualSuggestionCards$1.L$1 = (Set) obj2;
        return walletContextualSuggestionsController$contextualSuggestionCards$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        Set set = (Set) this.L$1;
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : list) {
            WalletCard walletCard = (WalletCard) obj2;
            if (walletCard.getCardType() == 2 && set.contains(walletCard.getCardId())) {
                arrayList.add(obj2);
            }
        }
        return arrayList;
    }
}
