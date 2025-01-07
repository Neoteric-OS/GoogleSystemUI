package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PagerState$scrollToPage$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $page;
    final /* synthetic */ float $pageOffsetFraction;
    int label;
    final /* synthetic */ PagerState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerState$scrollToPage$2(PagerState pagerState, float f, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = pagerState;
        this.$pageOffsetFraction = f;
        this.$page = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new PagerState$scrollToPage$2(this.this$0, this.$pageOffsetFraction, this.$page, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((PagerState$scrollToPage$2) create((ScrollScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PagerState pagerState = this.this$0;
            this.label = 1;
            Object waitForFirstLayout = pagerState.awaitLayoutModifier.waitForFirstLayout(this);
            if (waitForFirstLayout != coroutineSingletons) {
                waitForFirstLayout = unit;
            }
            if (waitForFirstLayout == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        float f = this.$pageOffsetFraction;
        double d = f;
        if (-0.5d > d || d > 0.5d) {
            InlineClassHelperKt.throwIllegalArgumentException("pageOffsetFraction " + f + " is not within the range -0.5 to 0.5");
        }
        this.this$0.snapToItem$foundation_release(this.$pageOffsetFraction, true, this.this$0.coerceInPageRange(this.$page));
        return unit;
    }
}
