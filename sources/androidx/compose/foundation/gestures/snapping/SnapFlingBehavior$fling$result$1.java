package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.pager.MeasuredPage;
import androidx.compose.foundation.pager.PageInfo;
import androidx.compose.foundation.pager.PagerMeasureResult;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$FloatRef;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SnapFlingBehavior$fling$result$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ float $initialVelocity;
    final /* synthetic */ Function1 $onRemainingScrollOffsetUpdate;
    final /* synthetic */ ScrollScope $this_fling;
    Object L$0;
    int label;
    final /* synthetic */ SnapFlingBehavior this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnapFlingBehavior$fling$result$1(SnapFlingBehavior snapFlingBehavior, float f, Function1 function1, ScrollScope scrollScope, Continuation continuation) {
        super(2, continuation);
        this.this$0 = snapFlingBehavior;
        this.$initialVelocity = f;
        this.$onRemainingScrollOffsetUpdate = function1;
        this.$this_fling = scrollScope;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SnapFlingBehavior$fling$result$1(this.this$0, this.$initialVelocity, this.$onRemainingScrollOffsetUpdate, this.$this_fling, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SnapFlingBehavior$fling$result$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        float f;
        final Ref$FloatRef ref$FloatRef;
        Object access$tryApproach;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            float calculateTargetValue = DecayAnimationSpecKt.calculateTargetValue(this.this$0.decayAnimationSpec, this.$initialVelocity);
            PagerSnapLayoutInfoProviderKt$SnapLayoutInfoProvider$1 pagerSnapLayoutInfoProviderKt$SnapLayoutInfoProvider$1 = this.this$0.snapLayoutInfoProvider;
            float f2 = this.$initialVelocity;
            PagerState pagerState = pagerSnapLayoutInfoProviderKt$SnapLayoutInfoProvider$1.$pagerState;
            int pageSize$foundation_release = ((PagerMeasureResult) ((SnapshotMutableStateImpl) pagerState.pagerLayoutInfoState).getValue()).pageSpacing + pagerState.getPageSize$foundation_release();
            if (pageSize$foundation_release == 0) {
                f = 0.0f;
            } else {
                int i2 = f2 < 0.0f ? pagerState.firstVisiblePage + 1 : pagerState.firstVisiblePage;
                int coerceIn = RangesKt.coerceIn(((int) (calculateTargetValue / pageSize$foundation_release)) + i2, 0, pagerState.getPageCount());
                pagerState.getPageSize$foundation_release();
                int i3 = ((PagerMeasureResult) ((SnapshotMutableStateImpl) pagerState.pagerLayoutInfoState).getValue()).pageSpacing;
                long j = i2;
                long j2 = 1;
                long j3 = j - j2;
                int i4 = (int) (j3 < 0 ? 0L : j3);
                long j4 = j + j2;
                if (j4 > 2147483647L) {
                    j4 = 2147483647L;
                }
                int abs = Math.abs((RangesKt.coerceIn(RangesKt.coerceIn(coerceIn, i4, (int) j4), 0, pagerState.getPageCount()) - i2) * pageSize$foundation_release) - pageSize$foundation_release;
                if (abs < 0) {
                    abs = 0;
                }
                f = abs == 0 ? abs : Math.signum(f2) * abs;
            }
            if (Float.isNaN(f)) {
                InlineClassHelperKt.throwIllegalStateException("calculateApproachOffset returned NaN. Please use a valid value.");
            }
            ref$FloatRef = new Ref$FloatRef();
            float signum = Math.signum(this.$initialVelocity) * Math.abs(f);
            ref$FloatRef.element = signum;
            this.$onRemainingScrollOffsetUpdate.invoke(new Float(signum));
            SnapFlingBehavior snapFlingBehavior = this.this$0;
            ScrollScope scrollScope = this.$this_fling;
            float f3 = ref$FloatRef.element;
            float f4 = this.$initialVelocity;
            final Function1 function1 = this.$onRemainingScrollOffsetUpdate;
            Function1 function12 = new Function1() { // from class: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$result$1$animationState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    float floatValue = ((Number) obj2).floatValue();
                    Ref$FloatRef ref$FloatRef2 = Ref$FloatRef.this;
                    float f5 = ref$FloatRef2.element - floatValue;
                    ref$FloatRef2.element = f5;
                    function1.invoke(Float.valueOf(f5));
                    return Unit.INSTANCE;
                }
            };
            this.L$0 = ref$FloatRef;
            this.label = 1;
            access$tryApproach = SnapFlingBehavior.access$tryApproach(snapFlingBehavior, scrollScope, f3, f4, function12, this);
            if (access$tryApproach == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            Ref$FloatRef ref$FloatRef2 = (Ref$FloatRef) this.L$0;
            ResultKt.throwOnFailure(obj);
            ref$FloatRef = ref$FloatRef2;
            access$tryApproach = obj;
        }
        AnimationState animationState = (AnimationState) access$tryApproach;
        PagerSnapLayoutInfoProviderKt$SnapLayoutInfoProvider$1 pagerSnapLayoutInfoProviderKt$SnapLayoutInfoProvider$12 = this.this$0.snapLayoutInfoProvider;
        float floatValue = ((Number) animationState.getVelocity()).floatValue();
        PagerState pagerState2 = pagerSnapLayoutInfoProviderKt$SnapLayoutInfoProvider$12.$pagerState;
        SnapPosition snapPosition = ((PagerMeasureResult) pagerState2.getLayoutInfo()).snapPosition;
        List list = ((PagerMeasureResult) pagerState2.getLayoutInfo()).visiblePagesInfo;
        int size = list.size();
        float f5 = Float.NEGATIVE_INFINITY;
        float f6 = Float.POSITIVE_INFINITY;
        for (int i5 = 0; i5 < size; i5++) {
            PageInfo pageInfo = (PageInfo) list.get(i5);
            PagerMeasureResult pagerMeasureResult = (PagerMeasureResult) pagerState2.getLayoutInfo();
            Orientation orientation = pagerMeasureResult.orientation;
            Orientation orientation2 = Orientation.Vertical;
            pagerMeasureResult.m143getViewportSizeYbymL2g();
            int i6 = ((PagerMeasureResult) pagerState2.getLayoutInfo()).viewportStartOffset;
            int i7 = ((PagerMeasureResult) pagerState2.getLayoutInfo()).afterContentPadding;
            int i8 = ((PagerMeasureResult) pagerState2.getLayoutInfo()).pageSize;
            int i9 = ((MeasuredPage) pageInfo).offset;
            pagerState2.getPageCount();
            snapPosition.getClass();
            float f7 = i9 - 0;
            if (f7 <= 0.0f && f7 > f5) {
                f5 = f7;
            }
            if (f7 >= 0.0f && f7 < f6) {
                f6 = f7;
            }
        }
        if (f5 == Float.NEGATIVE_INFINITY) {
            f5 = f6;
        }
        if (f6 == Float.POSITIVE_INFINITY) {
            f6 = f5;
        }
        boolean z = PagerSnapLayoutInfoProviderKt.dragGestureDelta(pagerState2) == 0.0f;
        if (!pagerState2.getCanScrollForward()) {
            if (!z && PagerSnapLayoutInfoProviderKt.isScrollingForward(pagerState2)) {
                f5 = 0.0f;
            }
            f6 = 0.0f;
        }
        if (!pagerState2.getCanScrollBackward()) {
            if (z || PagerSnapLayoutInfoProviderKt.isScrollingForward(pagerState2)) {
                f5 = 0.0f;
            } else {
                f5 = 0.0f;
                f6 = 0.0f;
            }
        }
        Pair pair = new Pair(Float.valueOf(f5), Float.valueOf(f6));
        float floatValue2 = ((Number) pair.component1()).floatValue();
        float floatValue3 = ((Number) pair.component2()).floatValue();
        float floatValue4 = ((Number) pagerSnapLayoutInfoProviderKt$SnapLayoutInfoProvider$12.$calculateFinalSnappingBound.invoke(Float.valueOf(floatValue), Float.valueOf(floatValue2), Float.valueOf(floatValue3))).floatValue();
        if (floatValue4 != floatValue2 && floatValue4 != floatValue3 && floatValue4 != 0.0f) {
            InlineClassHelperKt.throwIllegalStateException("Final Snapping Offset Should Be one of " + floatValue2 + ", " + floatValue3 + " or 0.0");
        }
        float f8 = (floatValue4 == Float.POSITIVE_INFINITY || floatValue4 == Float.NEGATIVE_INFINITY) ? 0.0f : floatValue4;
        if (Float.isNaN(f8)) {
            InlineClassHelperKt.throwIllegalStateException("calculateSnapOffset returned NaN. Please use a valid value.");
        }
        ref$FloatRef.element = f8;
        ScrollScope scrollScope2 = this.$this_fling;
        AnimationState copy$default = AnimationStateKt.copy$default(animationState, 0.0f, 0.0f, 30);
        SpringSpec springSpec = this.this$0.snapAnimationSpec;
        final Function1 function13 = this.$onRemainingScrollOffsetUpdate;
        Function1 function14 = new Function1() { // from class: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$result$1.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                float floatValue5 = ((Number) obj2).floatValue();
                Ref$FloatRef ref$FloatRef3 = Ref$FloatRef.this;
                float f9 = ref$FloatRef3.element - floatValue5;
                ref$FloatRef3.element = f9;
                function13.invoke(Float.valueOf(f9));
                return Unit.INSTANCE;
            }
        };
        this.L$0 = null;
        this.label = 2;
        Object access$animateWithTarget = SnapFlingBehaviorKt.access$animateWithTarget(scrollScope2, f8, f8, copy$default, springSpec, function14, this);
        return access$animateWithTarget == coroutineSingletons ? coroutineSingletons : access$animateWithTarget;
    }
}
