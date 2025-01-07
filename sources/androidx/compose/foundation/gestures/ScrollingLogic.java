package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollNode;
import androidx.compose.ui.node.TraversableNodeKt;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScrollingLogic {
    public FlingBehavior flingBehavior;
    public NestedScrollDispatcher nestedScrollDispatcher;
    public Orientation orientation;
    public OverscrollEffect overscrollEffect;
    public boolean reverseDirection;
    public ScrollableState scrollableState;
    public int latestScrollSource = 1;
    public ScrollScope outerStateScope = ScrollableKt.NoOpScrollScope;
    public final ScrollingLogic$nestedScrollScope$1 nestedScrollScope = new ScrollingLogic$nestedScrollScope$1(this);
    public final Function1 performScrollForOverscroll = new Function1() { // from class: androidx.compose.foundation.gestures.ScrollingLogic$performScrollForOverscroll$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            long j = ((Offset) obj).packedValue;
            ScrollingLogic scrollingLogic = ScrollingLogic.this;
            return new Offset(ScrollingLogic.m71access$performScroll3eAAhYA(scrollingLogic, scrollingLogic.outerStateScope, j, scrollingLogic.latestScrollSource));
        }
    };

    public ScrollingLogic(ScrollableState scrollableState, OverscrollEffect overscrollEffect, FlingBehavior flingBehavior, Orientation orientation, boolean z, NestedScrollDispatcher nestedScrollDispatcher) {
        this.scrollableState = scrollableState;
        this.overscrollEffect = overscrollEffect;
        this.flingBehavior = flingBehavior;
        this.orientation = orientation;
        this.reverseDirection = z;
        this.nestedScrollDispatcher = nestedScrollDispatcher;
    }

    /* renamed from: access$performScroll-3eAAhYA, reason: not valid java name */
    public static final long m71access$performScroll3eAAhYA(ScrollingLogic scrollingLogic, ScrollScope scrollScope, long j, int i) {
        NestedScrollNode nestedScrollNode = scrollingLogic.nestedScrollDispatcher.nestedScrollNode;
        NestedScrollNode nestedScrollNode2 = null;
        if (nestedScrollNode != null && nestedScrollNode.isAttached) {
            nestedScrollNode2 = (NestedScrollNode) TraversableNodeKt.findNearestAncestor(nestedScrollNode);
        }
        long mo139onPreScrollOzD1aCk = nestedScrollNode2 != null ? nestedScrollNode2.mo139onPreScrollOzD1aCk(j, i) : 0L;
        long m314minusMKHz9U = Offset.m314minusMKHz9U(j, mo139onPreScrollOzD1aCk);
        long m73reverseIfNeededMKHz9U = scrollingLogic.m73reverseIfNeededMKHz9U(scrollingLogic.m75toOffsettuRUvjQ(scrollScope.scrollBy(scrollingLogic.m74toFloatk4lQ0M(scrollingLogic.m73reverseIfNeededMKHz9U(Offset.m308copydBAh8RU$default(0.0f, scrollingLogic.orientation == Orientation.Horizontal ? 1 : 2, m314minusMKHz9U))))));
        return Offset.m315plusMKHz9U(Offset.m315plusMKHz9U(mo139onPreScrollOzD1aCk, m73reverseIfNeededMKHz9U), scrollingLogic.nestedScrollDispatcher.m452dispatchPostScrollDzOQY0M(i, m73reverseIfNeededMKHz9U, Offset.m314minusMKHz9U(m314minusMKHz9U, m73reverseIfNeededMKHz9U)));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* renamed from: doFlingAnimation-QWom1Mo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m72doFlingAnimationQWom1Mo(long r12, kotlin.coroutines.jvm.internal.ContinuationImpl r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1
            if (r0 == 0) goto L13
            r0 = r14
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1 r0 = (androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1 r0 = new androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$1
            r0.<init>(r11, r14)
        L18:
            java.lang.Object r14 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            java.lang.Object r11 = r0.L$0
            kotlin.jvm.internal.Ref$LongRef r11 = (kotlin.jvm.internal.Ref$LongRef) r11
            kotlin.ResultKt.throwOnFailure(r14)
            goto L55
        L2b:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L33:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.jvm.internal.Ref$LongRef r14 = new kotlin.jvm.internal.Ref$LongRef
            r14.<init>()
            r14.element = r12
            androidx.compose.foundation.MutatePriority r2 = androidx.compose.foundation.MutatePriority.Default
            androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2 r10 = new androidx.compose.foundation.gestures.ScrollingLogic$doFlingAnimation$2
            r9 = 0
            r4 = r10
            r5 = r11
            r6 = r14
            r7 = r12
            r4.<init>(r5, r6, r7, r9)
            r0.L$0 = r14
            r0.label = r3
            java.lang.Object r11 = r11.scroll(r2, r10, r0)
            if (r11 != r1) goto L54
            return r1
        L54:
            r11 = r14
        L55:
            long r11 = r11.element
            androidx.compose.ui.unit.Velocity r13 = new androidx.compose.ui.unit.Velocity
            r13.<init>(r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollingLogic.m72doFlingAnimationQWom1Mo(long, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final float reverseIfNeeded(float f) {
        return this.reverseDirection ? f * (-1) : f;
    }

    /* renamed from: reverseIfNeeded-MK-Hz9U, reason: not valid java name */
    public final long m73reverseIfNeededMKHz9U(long j) {
        return this.reverseDirection ? Offset.m316timestuRUvjQ(-1.0f, j) : j;
    }

    public final Object scroll(MutatePriority mutatePriority, Function2 function2, ContinuationImpl continuationImpl) {
        Object scroll = this.scrollableState.scroll(mutatePriority, new ScrollingLogic$scroll$2(this, null, function2), continuationImpl);
        return scroll == CoroutineSingletons.COROUTINE_SUSPENDED ? scroll : Unit.INSTANCE;
    }

    /* renamed from: toFloat-k-4lQ0M, reason: not valid java name */
    public final float m74toFloatk4lQ0M(long j) {
        return Float.intBitsToFloat((int) (this.orientation == Orientation.Horizontal ? j >> 32 : j & 4294967295L));
    }

    /* renamed from: toOffset-tuRUvjQ, reason: not valid java name */
    public final long m75toOffsettuRUvjQ(float f) {
        if (f == 0.0f) {
            return 0L;
        }
        if (this.orientation == Orientation.Horizontal) {
            return (Float.floatToRawIntBits(f) << 32) | (Float.floatToRawIntBits(0.0f) & 4294967295L);
        }
        return (Float.floatToRawIntBits(f) & 4294967295L) | (Float.floatToRawIntBits(0.0f) << 32);
    }
}
