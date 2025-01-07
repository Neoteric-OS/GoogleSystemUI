package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.domain.model.KeyguardQuickAffordanceModel;
import com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceInteractor$quickAffordanceInternal$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ KeyguardQuickAffordancePosition $position$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ KeyguardQuickAffordanceInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardQuickAffordanceInteractor$quickAffordanceInternal$$inlined$flatMapLatest$1(Continuation continuation, KeyguardQuickAffordanceInteractor keyguardQuickAffordanceInteractor, KeyguardQuickAffordancePosition keyguardQuickAffordancePosition) {
        super(3, continuation);
        this.this$0 = keyguardQuickAffordanceInteractor;
        this.$position$inlined = keyguardQuickAffordancePosition;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardQuickAffordanceInteractor$quickAffordanceInternal$$inlined$flatMapLatest$1 keyguardQuickAffordanceInteractor$quickAffordanceInternal$$inlined$flatMapLatest$1 = new KeyguardQuickAffordanceInteractor$quickAffordanceInternal$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0, this.$position$inlined);
        keyguardQuickAffordanceInteractor$quickAffordanceInternal$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        keyguardQuickAffordanceInteractor$quickAffordanceInternal$$inlined$flatMapLatest$1.L$1 = obj2;
        return keyguardQuickAffordanceInteractor$quickAffordanceInternal$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow keyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            List list = (List) this.L$1;
            KeyguardQuickAffordanceInteractor keyguardQuickAffordanceInteractor = this.this$0;
            KeyguardQuickAffordancePosition keyguardQuickAffordancePosition = this.$position$inlined;
            keyguardQuickAffordanceInteractor.getClass();
            if (list.isEmpty()) {
                keyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1 = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(KeyguardQuickAffordanceModel.Hidden.INSTANCE);
            } else {
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new KeyguardQuickAffordanceInteractor$combinedConfigs$1$1(2, null), ((KeyguardQuickAffordanceConfig) it.next()).getLockScreenState()));
                }
                keyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1 = new KeyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1((Flow[]) CollectionsKt.toList(arrayList).toArray(new Flow[0]), list, keyguardQuickAffordanceInteractor, keyguardQuickAffordancePosition);
            }
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, keyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1, this) == coroutineSingletons) {
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
