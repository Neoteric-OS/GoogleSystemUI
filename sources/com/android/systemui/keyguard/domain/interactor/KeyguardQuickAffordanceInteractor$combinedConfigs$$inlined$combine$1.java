package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.domain.model.KeyguardQuickAffordanceModel;
import com.android.systemui.keyguard.shared.quickaffordance.KeyguardQuickAffordancePosition;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1 implements Flow {
    public final /* synthetic */ Object $configs$inlined;
    public final /* synthetic */ Object $flowArray$inlined;
    public final /* synthetic */ KeyguardQuickAffordancePosition $position$inlined;
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ KeyguardQuickAffordanceInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1$3, reason: invalid class name */
    public final class AnonymousClass3 extends SuspendLambda implements Function3 {
        final /* synthetic */ List $configs$inlined;
        final /* synthetic */ KeyguardQuickAffordancePosition $position$inlined;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;
        final /* synthetic */ KeyguardQuickAffordanceInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Continuation continuation, List list, KeyguardQuickAffordanceInteractor keyguardQuickAffordanceInteractor, KeyguardQuickAffordancePosition keyguardQuickAffordancePosition) {
            super(3, continuation);
            this.$configs$inlined = list;
            this.this$0 = keyguardQuickAffordanceInteractor;
            this.$position$inlined = keyguardQuickAffordancePosition;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3((Continuation) obj3, this.$configs$inlined, this.this$0, this.$position$inlined);
            anonymousClass3.L$0 = (FlowCollector) obj;
            anonymousClass3.L$1 = (Object[]) obj2;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object obj2;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                KeyguardQuickAffordanceConfig.LockScreenState[] lockScreenStateArr = (KeyguardQuickAffordanceConfig.LockScreenState[]) ((Object[]) this.L$1);
                int length = lockScreenStateArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        i2 = -1;
                        break;
                    }
                    if (lockScreenStateArr[i2] instanceof KeyguardQuickAffordanceConfig.LockScreenState.Visible) {
                        break;
                    }
                    i2++;
                }
                if (i2 != -1) {
                    KeyguardQuickAffordanceConfig.LockScreenState.Visible visible = (KeyguardQuickAffordanceConfig.LockScreenState.Visible) lockScreenStateArr[i2];
                    String key = ((KeyguardQuickAffordanceConfig) this.$configs$inlined.get(i2)).getKey();
                    KeyguardQuickAffordanceInteractor keyguardQuickAffordanceInteractor = this.this$0;
                    String slotId = this.$position$inlined.toSlotId();
                    keyguardQuickAffordanceInteractor.getClass();
                    obj2 = new KeyguardQuickAffordanceModel.Visible(slotId + "::" + key, visible.icon, visible.activationState);
                } else {
                    obj2 = KeyguardQuickAffordanceModel.Hidden.INSTANCE;
                }
                this.label = 1;
                if (flowCollector.emit(obj2, this) == coroutineSingletons) {
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

    public KeyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1(StateFlow stateFlow, String str, KeyguardQuickAffordancePosition keyguardQuickAffordancePosition, KeyguardQuickAffordanceInteractor keyguardQuickAffordanceInteractor) {
        this.$flowArray$inlined = stateFlow;
        this.$configs$inlined = str;
        this.$position$inlined = keyguardQuickAffordancePosition;
        this.this$0 = keyguardQuickAffordanceInteractor;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                final Flow[] flowArr = (Flow[]) this.$flowArray$inlined;
                Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return new KeyguardQuickAffordanceConfig.LockScreenState[flowArr.length];
                    }
                }, new AnonymousClass3(null, (List) this.$configs$inlined, this.this$0, this.$position$inlined), flowCollector, flowArr);
                if (combineInternal != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            default:
                Object collect = ((StateFlow) this.$flowArray$inlined).collect(new KeyguardQuickAffordanceInteractor$quickAffordanceInternal$$inlined$map$1$2(flowCollector, (String) this.$configs$inlined, this.$position$inlined, this.this$0), continuation);
                if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
        }
        return Unit.INSTANCE;
    }

    public KeyguardQuickAffordanceInteractor$combinedConfigs$$inlined$combine$1(Flow[] flowArr, List list, KeyguardQuickAffordanceInteractor keyguardQuickAffordanceInteractor, KeyguardQuickAffordancePosition keyguardQuickAffordancePosition) {
        this.$flowArray$inlined = flowArr;
        this.$configs$inlined = list;
        this.this$0 = keyguardQuickAffordanceInteractor;
        this.$position$inlined = keyguardQuickAffordancePosition;
    }
}
