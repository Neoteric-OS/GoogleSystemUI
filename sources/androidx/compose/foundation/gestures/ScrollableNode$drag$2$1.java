package androidx.compose.foundation.gestures;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.gestures.DragEvent;
import androidx.compose.ui.geometry.Offset;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ScrollableNode$drag$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function2 $forEachDelta;
    final /* synthetic */ ScrollingLogic $this_with;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollableNode$drag$2$1(ScrollingLogic scrollingLogic, Continuation continuation, Function2 function2) {
        super(2, continuation);
        this.$forEachDelta = function2;
        this.$this_with = scrollingLogic;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ScrollableNode$drag$2$1 scrollableNode$drag$2$1 = new ScrollableNode$drag$2$1(this.$this_with, continuation, this.$forEachDelta);
        scrollableNode$drag$2$1.L$0 = obj;
        return scrollableNode$drag$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ScrollableNode$drag$2$1) create((NestedScrollScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final NestedScrollScope nestedScrollScope = (NestedScrollScope) this.L$0;
            Function2 function2 = this.$forEachDelta;
            final ScrollingLogic scrollingLogic = this.$this_with;
            Function1 function1 = new Function1() { // from class: androidx.compose.foundation.gestures.ScrollableNode$drag$2$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    NestedScrollScope nestedScrollScope2 = NestedScrollScope.this;
                    ScrollingLogic scrollingLogic2 = scrollingLogic;
                    long j = ((DragEvent.DragDelta) obj2).delta;
                    long m308copydBAh8RU$default = scrollingLogic2.orientation == Orientation.Horizontal ? Offset.m308copydBAh8RU$default(0.0f, 1, j) : Offset.m308copydBAh8RU$default(0.0f, 2, j);
                    ScrollingLogic scrollingLogic3 = ((ScrollingLogic$nestedScrollScope$1) nestedScrollScope2).this$0;
                    scrollingLogic3.latestScrollSource = 1;
                    OverscrollEffect overscrollEffect = scrollingLogic3.overscrollEffect;
                    if (overscrollEffect == null || !(scrollingLogic3.scrollableState.getCanScrollForward() || scrollingLogic3.scrollableState.getCanScrollBackward())) {
                        ScrollingLogic.m71access$performScroll3eAAhYA(scrollingLogic3, scrollingLogic3.outerStateScope, m308copydBAh8RU$default, 1);
                    } else {
                        overscrollEffect.mo19applyToScrollRhakbz0(m308copydBAh8RU$default, scrollingLogic3.latestScrollSource, scrollingLogic3.performScrollForOverscroll);
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (function2.invoke(function1, this) == coroutineSingletons) {
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
