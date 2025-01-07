package com.android.compose.nestedscroll;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PriorityNestedScrollConnection implements NestedScrollConnection {
    public final Lambda canContinueScroll;
    public final boolean canScrollOnFling;
    public final Function1 canStartPostFling;
    public final Function2 canStartPostScroll;
    public final Function2 canStartPreScroll;
    public boolean isPriorityMode;
    public long offsetScrolledBeforePriorityMode = 0;
    public final Function1 onScroll;
    public final Function1 onStart;
    public final Function1 onStop;

    /* JADX WARN: Multi-variable type inference failed */
    public PriorityNestedScrollConnection(Function2 function2, Function2 function22, Function1 function1, Function1 function12, boolean z, Function1 function13, Function1 function14, Function1 function15) {
        this.canStartPreScroll = function2;
        this.canStartPostScroll = function22;
        this.canStartPostFling = function1;
        this.canContinueScroll = (Lambda) function12;
        this.canScrollOnFling = z;
        this.onStart = function13;
        this.onScroll = function14;
        this.onStop = function15;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    public final Object mo69onPostFlingRZ2iAVY(long j, long j2, Continuation continuation) {
        if (this.isPriorityMode) {
            return new Velocity(m744onPriorityStopAH228Gc(j2));
        }
        if (!((Boolean) ((PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$3) this.canStartPostFling).invoke(new Velocity(j2))).booleanValue()) {
            return new Velocity(0L);
        }
        float signum = Math.signum(Velocity.m694getXimpl(j2));
        float signum2 = Math.signum(Velocity.m695getYimpl(j2));
        m743onPriorityStartMKHz9U((Float.floatToRawIntBits(signum2) & 4294967295L) | (Float.floatToRawIntBits(signum) << 32));
        return new Velocity(m744onPriorityStopAH228Gc(j2));
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public final long mo70onPostScrollDzOQY0M(int i, long j, long j2) {
        long m314minusMKHz9U = Offset.m314minusMKHz9U(this.offsetScrolledBeforePriorityMode, j2);
        if (this.isPriorityMode) {
            return 0L;
        }
        if (NestedScrollSource.m454equalsimpl0(i, 2) && !this.canScrollOnFling) {
            return 0L;
        }
        if (((Boolean) ((PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$2) this.canStartPostScroll).invoke(new Offset(j2), new Offset(m314minusMKHz9U))).booleanValue()) {
            return m743onPriorityStartMKHz9U(j2);
        }
        return 0L;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreFling-QWom1Mo */
    public final Object mo227onPreFlingQWom1Mo(long j, Continuation continuation) {
        return (this.isPriorityMode && this.canScrollOnFling) ? new Velocity(0L) : new Velocity(m744onPriorityStopAH228Gc(j));
    }

    /* JADX WARN: Type inference failed for: r8v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public final long mo139onPreScrollOzD1aCk(long j, int i) {
        if (this.isPriorityMode) {
            if (((Boolean) this.canContinueScroll.invoke(new NestedScrollSource(i))).booleanValue()) {
                return ((Offset) ((PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$5) this.onScroll).invoke(new Offset(j))).packedValue;
            }
            m744onPriorityStopAH228Gc(0L);
            this.offsetScrolledBeforePriorityMode = Offset.m315plusMKHz9U(this.offsetScrolledBeforePriorityMode, j);
            return 0L;
        }
        if (NestedScrollSource.m454equalsimpl0(i, 1) || this.canScrollOnFling) {
            if (((Boolean) ((PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$1) this.canStartPreScroll).invoke(new Offset(j), new Offset(this.offsetScrolledBeforePriorityMode))).booleanValue()) {
                return m743onPriorityStartMKHz9U(j);
            }
            this.offsetScrolledBeforePriorityMode = Offset.m315plusMKHz9U(this.offsetScrolledBeforePriorityMode, j);
        }
        return 0L;
    }

    /* renamed from: onPriorityStart-MK-Hz9U, reason: not valid java name */
    public final long m743onPriorityStartMKHz9U(long j) {
        if (this.isPriorityMode) {
            throw new IllegalStateException("This should never happen, onPriorityStart() was called when isPriorityMode");
        }
        this.isPriorityMode = true;
        ((PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$4) this.onStart).invoke(new Offset(j));
        return ((Offset) ((PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$5) this.onScroll).invoke(new Offset(j))).packedValue;
    }

    /* renamed from: onPriorityStop-AH228Gc, reason: not valid java name */
    public final long m744onPriorityStopAH228Gc(long j) {
        this.offsetScrolledBeforePriorityMode = 0L;
        if (!this.isPriorityMode) {
            return 0L;
        }
        this.isPriorityMode = false;
        return ((Velocity) ((PriorityNestedScrollConnectionKt$PriorityNestedScrollConnection$1$6) this.onStop).invoke(new Velocity(j))).packedValue;
    }
}
