package com.android.systemui.util.kotlin;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.util.kotlin.JavaAdapterKt$collectFlow$1;
import java.util.function.Consumer;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class JavaAdapterKt$collectFlow$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Consumer $consumer;
    final /* synthetic */ Flow $flow;
    final /* synthetic */ Lifecycle $lifecycle;
    final /* synthetic */ Lifecycle.State $state;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.util.kotlin.JavaAdapterKt$collectFlow$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Consumer $consumer;
        final /* synthetic */ Flow $flow;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Flow flow, Consumer consumer, Continuation continuation) {
            super(2, continuation);
            this.$flow = flow;
            this.$consumer = consumer;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$flow, this.$consumer, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flow = this.$flow;
                JavaAdapterKt$collectFlow$1.AnonymousClass1.C02671 c02671 = new JavaAdapterKt$collectFlow$1.AnonymousClass1.C02671(this.$consumer, 1);
                this.label = 1;
                if (flow.collect(c02671, this) == coroutineSingletons) {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavaAdapterKt$collectFlow$2(Lifecycle lifecycle, Lifecycle.State state, Flow flow, Consumer consumer, Continuation continuation) {
        super(2, continuation);
        this.$lifecycle = lifecycle;
        this.$state = state;
        this.$flow = flow;
        this.$consumer = consumer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new JavaAdapterKt$collectFlow$2(this.$lifecycle, this.$state, this.$flow, this.$consumer, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((JavaAdapterKt$collectFlow$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Lifecycle lifecycle = this.$lifecycle;
            Lifecycle.State state = this.$state;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$flow, this.$consumer, null);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycle, state, anonymousClass1, this) == coroutineSingletons) {
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
