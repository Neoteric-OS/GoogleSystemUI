package androidx.compose.foundation.gestures;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.ui.unit.Velocity;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ScrollableNode$onDragStopped$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ long $velocity;
    int label;
    final /* synthetic */ ScrollableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollableNode$onDragStopped$1(ScrollableNode scrollableNode, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = scrollableNode;
        this.$velocity = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ScrollableNode$onDragStopped$1(this.this$0, this.$velocity, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ScrollableNode$onDragStopped$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object invoke;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ScrollingLogic scrollingLogic = this.this$0.scrollingLogic;
            long j = this.$velocity;
            this.label = 1;
            long m693copyOhffZ5M$default = Velocity.m693copyOhffZ5M$default(0.0f, 0.0f, scrollingLogic.orientation == Orientation.Horizontal ? 1 : 2, j);
            ScrollingLogic$onDragStopped$performFling$1 scrollingLogic$onDragStopped$performFling$1 = new ScrollingLogic$onDragStopped$performFling$1(scrollingLogic, null);
            OverscrollEffect overscrollEffect = scrollingLogic.overscrollEffect;
            Object obj2 = Unit.INSTANCE;
            if (overscrollEffect == null || (!scrollingLogic.scrollableState.getCanScrollForward() && !scrollingLogic.scrollableState.getCanScrollBackward()) ? (invoke = scrollingLogic$onDragStopped$performFling$1.invoke(new Velocity(m693copyOhffZ5M$default), this)) == CoroutineSingletons.COROUTINE_SUSPENDED : (invoke = overscrollEffect.mo18applyToFlingBMRW4eQ(m693copyOhffZ5M$default, scrollingLogic$onDragStopped$performFling$1, this)) == CoroutineSingletons.COROUTINE_SUSPENDED) {
                obj2 = invoke;
            }
            if (obj2 == coroutineSingletons) {
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
