package com.android.systemui.statusbar.notification.stack.ui.viewmodel;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SharedNotificationContainerViewModel$getMaxNotifications$$inlined$combineTransform$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function2 $calculateSpace$inlined;
    final /* synthetic */ Flow[] $flows;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$getMaxNotifications$$inlined$combineTransform$1$2, reason: invalid class name */
    public final class AnonymousClass2 extends SuspendLambda implements Function3 {
        final /* synthetic */ Function2 $calculateSpace$inlined;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Continuation continuation, Function2 function2) {
            super(3, continuation);
            this.$calculateSpace$inlined = function2;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2((Continuation) obj3, this.$calculateSpace$inlined);
            anonymousClass2.L$0 = (FlowCollector) obj;
            anonymousClass2.L$1 = (Object[]) obj2;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                Object[] objArr = (Object[]) this.L$1;
                boolean booleanValue = ((Boolean) objArr[0]).booleanValue();
                boolean booleanValue2 = ((Boolean) objArr[1]).booleanValue();
                boolean booleanValue3 = ((Boolean) objArr[2]).booleanValue();
                float floatValue = ((Float) objArr[3]).floatValue();
                boolean booleanValue4 = ((Boolean) objArr[5]).booleanValue();
                if (!booleanValue3) {
                    if (booleanValue) {
                        Object invoke = this.$calculateSpace$inlined.invoke(new Float(floatValue), Boolean.valueOf(booleanValue4));
                        this.label = 1;
                        if (flowCollector.emit(invoke, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else if (booleanValue2) {
                        Integer num = new Integer(-1);
                        this.label = 2;
                        if (flowCollector.emit(num, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    }
                }
            } else {
                if (i != 1 && i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharedNotificationContainerViewModel$getMaxNotifications$$inlined$combineTransform$1(Flow[] flowArr, Continuation continuation, Function2 function2) {
        super(2, continuation);
        this.$flows = flowArr;
        this.$calculateSpace$inlined = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SharedNotificationContainerViewModel$getMaxNotifications$$inlined$combineTransform$1 sharedNotificationContainerViewModel$getMaxNotifications$$inlined$combineTransform$1 = new SharedNotificationContainerViewModel$getMaxNotifications$$inlined$combineTransform$1(this.$flows, continuation, this.$calculateSpace$inlined);
        sharedNotificationContainerViewModel$getMaxNotifications$$inlined$combineTransform$1.L$0 = obj;
        return sharedNotificationContainerViewModel$getMaxNotifications$$inlined$combineTransform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SharedNotificationContainerViewModel$getMaxNotifications$$inlined$combineTransform$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            final Flow[] flowArr = this.$flows;
            Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$getMaxNotifications$$inlined$combineTransform$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new Object[flowArr.length];
                }
            };
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(null, this.$calculateSpace$inlined);
            this.label = 1;
            if (CombineKt.combineInternal(this, function0, anonymousClass2, flowCollector, flowArr) == coroutineSingletons) {
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
