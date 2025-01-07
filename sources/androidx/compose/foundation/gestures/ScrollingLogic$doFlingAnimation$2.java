package androidx.compose.foundation.gestures;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.ui.unit.Velocity;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$LongRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ScrollingLogic$doFlingAnimation$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ long $available;
    final /* synthetic */ Ref$LongRef $result;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ ScrollingLogic this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollingLogic$doFlingAnimation$2(ScrollingLogic scrollingLogic, Ref$LongRef ref$LongRef, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = scrollingLogic;
        this.$result = ref$LongRef;
        this.$available = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ScrollingLogic$doFlingAnimation$2 scrollingLogic$doFlingAnimation$2 = new ScrollingLogic$doFlingAnimation$2(this.this$0, this.$result, this.$available, continuation);
        scrollingLogic$doFlingAnimation$2.L$0 = obj;
        return scrollingLogic$doFlingAnimation$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ScrollingLogic$doFlingAnimation$2) create((NestedScrollScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2$reverseScope$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final ScrollingLogic scrollingLogic;
        Ref$LongRef ref$LongRef;
        ScrollingLogic scrollingLogic2;
        long j;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Orientation orientation = Orientation.Horizontal;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final NestedScrollScope nestedScrollScope = (NestedScrollScope) this.L$0;
            scrollingLogic = this.this$0;
            ?? r1 = new ScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2$reverseScope$1
                @Override // androidx.compose.foundation.gestures.ScrollScope
                public final float scrollBy(float f) {
                    ScrollingLogic scrollingLogic3 = scrollingLogic;
                    if ((f > 0.0f && !scrollingLogic3.scrollableState.getCanScrollForward()) || (f < 0.0f && !scrollingLogic3.scrollableState.getCanScrollBackward())) {
                        throw new CancellationException("The fling was cancelled");
                    }
                    long m73reverseIfNeededMKHz9U = scrollingLogic3.m73reverseIfNeededMKHz9U(scrollingLogic3.m75toOffsettuRUvjQ(f));
                    ScrollingLogic scrollingLogic4 = ((ScrollingLogic$nestedScrollScope$1) nestedScrollScope).this$0;
                    scrollingLogic4.latestScrollSource = 2;
                    OverscrollEffect overscrollEffect = scrollingLogic4.overscrollEffect;
                    return scrollingLogic3.reverseIfNeeded(scrollingLogic3.m74toFloatk4lQ0M((overscrollEffect == null || !(scrollingLogic4.scrollableState.getCanScrollForward() || scrollingLogic4.scrollableState.getCanScrollBackward())) ? ScrollingLogic.m71access$performScroll3eAAhYA(scrollingLogic4, scrollingLogic4.outerStateScope, m73reverseIfNeededMKHz9U, 2) : overscrollEffect.mo19applyToScrollRhakbz0(m73reverseIfNeededMKHz9U, scrollingLogic4.latestScrollSource, scrollingLogic4.performScrollForOverscroll)));
                }
            };
            ref$LongRef = this.$result;
            long j2 = this.$available;
            FlingBehavior flingBehavior = scrollingLogic.flingBehavior;
            long j3 = ref$LongRef.element;
            float reverseIfNeeded = scrollingLogic.reverseIfNeeded(scrollingLogic.orientation == orientation ? Velocity.m694getXimpl(j2) : Velocity.m695getYimpl(j2));
            this.L$0 = scrollingLogic;
            this.L$1 = scrollingLogic;
            this.L$2 = ref$LongRef;
            this.J$0 = j3;
            this.label = 1;
            obj = flingBehavior.performFling(r1, reverseIfNeeded, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
            scrollingLogic2 = scrollingLogic;
            j = j3;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j = this.J$0;
            ref$LongRef = (Ref$LongRef) this.L$2;
            scrollingLogic = (ScrollingLogic) this.L$1;
            scrollingLogic2 = (ScrollingLogic) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        float reverseIfNeeded2 = scrollingLogic2.reverseIfNeeded(((Number) obj).floatValue());
        ref$LongRef.element = scrollingLogic.orientation == orientation ? Velocity.m693copyOhffZ5M$default(reverseIfNeeded2, 0.0f, 2, j) : Velocity.m693copyOhffZ5M$default(0.0f, reverseIfNeeded2, 1, j);
        return Unit.INSTANCE;
    }
}
