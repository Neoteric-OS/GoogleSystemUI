package com.android.systemui.keyguard.data.repository;

import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceSelectionManager;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceRepository$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Set $configs$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardQuickAffordanceRepository$special$$inlined$flatMapLatest$1(Continuation continuation, Set set) {
        super(3, continuation);
        this.$configs$inlined = set;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardQuickAffordanceRepository$special$$inlined$flatMapLatest$1 keyguardQuickAffordanceRepository$special$$inlined$flatMapLatest$1 = new KeyguardQuickAffordanceRepository$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$configs$inlined);
        keyguardQuickAffordanceRepository$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        keyguardQuickAffordanceRepository$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return keyguardQuickAffordanceRepository$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow mo824getSelections = ((KeyguardQuickAffordanceSelectionManager) this.L$1).mo824getSelections();
            Set set = this.$configs$inlined;
            this.label = 1;
            FlowKt.ensureActive(flowCollector);
            Object collect = mo824getSelections.collect(new KeyguardQuickAffordanceRepository$selections$lambda$5$$inlined$map$1$2(flowCollector, set), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
