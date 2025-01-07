package com.android.systemui.unfold;

import com.android.app.tracing.TraceStateLogger;
import com.android.systemui.unfold.data.repository.FoldStateRepository$FoldUpdate;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UnfoldTraceLogger$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ UnfoldTraceLogger this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.unfold.UnfoldTraceLogger$start$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ TraceStateLogger $foldUpdateLogger;
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AnonymousClass1(TraceStateLogger traceStateLogger, int i) {
            this.$r8$classId = i;
            this.$foldUpdateLogger = traceStateLogger;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    this.$foldUpdateLogger.log(((FoldStateRepository$FoldUpdate) obj).name());
                    break;
                default:
                    this.$foldUpdateLogger.log(((Boolean) obj).booleanValue() ? "folded" : "unfolded");
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnfoldTraceLogger$start$1(UnfoldTraceLogger unfoldTraceLogger, Continuation continuation) {
        super(2, continuation);
        this.this$0 = unfoldTraceLogger;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UnfoldTraceLogger$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((UnfoldTraceLogger$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TraceStateLogger traceStateLogger = new TraceStateLogger("FoldUpdate", 14);
            Flow foldUpdate = this.this$0.foldStateRepository.getFoldUpdate();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(traceStateLogger, 0);
            this.label = 1;
            if (foldUpdate.collect(anonymousClass1, this) == coroutineSingletons) {
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
