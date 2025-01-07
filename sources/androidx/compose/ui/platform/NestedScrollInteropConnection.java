package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.Velocity;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;
import java.util.Arrays;
import java.util.WeakHashMap;
import kotlin.coroutines.Continuation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NestedScrollInteropConnection implements NestedScrollConnection {
    public final int[] consumedScrollCache;
    public final NestedScrollingChildHelper nestedScrollChildHelper;

    public NestedScrollInteropConnection(View view) {
        NestedScrollingChildHelper nestedScrollingChildHelper = new NestedScrollingChildHelper(view);
        if (nestedScrollingChildHelper.mIsNestedScrollingEnabled) {
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api21Impl.stopNestedScroll(view);
        }
        nestedScrollingChildHelper.mIsNestedScrollingEnabled = true;
        this.nestedScrollChildHelper = nestedScrollingChildHelper;
        this.consumedScrollCache = new int[2];
        WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
        ViewCompat.Api21Impl.setNestedScrollingEnabled(view, true);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostFling-RZ2iAVY */
    public final Object mo69onPostFlingRZ2iAVY(long j, long j2, Continuation continuation) {
        float m694getXimpl = Velocity.m694getXimpl(j2) * (-1.0f);
        float m695getYimpl = Velocity.m695getYimpl(j2) * (-1.0f);
        NestedScrollingChildHelper nestedScrollingChildHelper = this.nestedScrollChildHelper;
        if (!nestedScrollingChildHelper.dispatchNestedFling(m694getXimpl, m695getYimpl, true)) {
            j2 = 0;
        }
        if (nestedScrollingChildHelper.hasNestedScrollingParent(0)) {
            nestedScrollingChildHelper.stopNestedScroll(0);
        }
        if (nestedScrollingChildHelper.hasNestedScrollingParent(1)) {
            nestedScrollingChildHelper.stopNestedScroll(1);
        }
        return new Velocity(j2);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPostScroll-DzOQY0M */
    public final long mo70onPostScrollDzOQY0M(int i, long j, long j2) {
        if (!this.nestedScrollChildHelper.startNestedScroll(NestedScrollInteropConnectionKt.m568access$getScrollAxesk4lQ0M(j2), !NestedScrollSource.m454equalsimpl0(i, 1) ? 1 : 0)) {
            return 0L;
        }
        int[] iArr = this.consumedScrollCache;
        Arrays.fill(iArr, 0, iArr.length, 0);
        this.nestedScrollChildHelper.dispatchNestedScrollInternal(NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat((int) (j >> 32))), NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat((int) (j & 4294967295L))), NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat((int) (j2 >> 32))), NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat((int) (4294967295L & j2))), null, !NestedScrollSource.m454equalsimpl0(i, 1) ? 1 : 0, this.consumedScrollCache);
        return NestedScrollInteropConnectionKt.m569access$toOffsetUv8p0NA(iArr, j2);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreFling-QWom1Mo */
    public final Object mo227onPreFlingQWom1Mo(long j, Continuation continuation) {
        float m694getXimpl = Velocity.m694getXimpl(j) * (-1.0f);
        float m695getYimpl = Velocity.m695getYimpl(j) * (-1.0f);
        NestedScrollingChildHelper nestedScrollingChildHelper = this.nestedScrollChildHelper;
        if (!nestedScrollingChildHelper.dispatchNestedPreFling(m694getXimpl, m695getYimpl)) {
            j = 0;
        }
        if (nestedScrollingChildHelper.hasNestedScrollingParent(0)) {
            nestedScrollingChildHelper.stopNestedScroll(0);
        }
        if (nestedScrollingChildHelper.hasNestedScrollingParent(1)) {
            nestedScrollingChildHelper.stopNestedScroll(1);
        }
        return new Velocity(j);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* renamed from: onPreScroll-OzD1aCk */
    public final long mo139onPreScrollOzD1aCk(long j, int i) {
        if (!this.nestedScrollChildHelper.startNestedScroll(NestedScrollInteropConnectionKt.m568access$getScrollAxesk4lQ0M(j), !NestedScrollSource.m454equalsimpl0(i, 1) ? 1 : 0)) {
            return 0L;
        }
        int[] iArr = this.consumedScrollCache;
        Arrays.fill(iArr, 0, iArr.length, 0);
        this.nestedScrollChildHelper.dispatchNestedPreScroll(NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat((int) (j >> 32))), NestedScrollInteropConnectionKt.composeToViewOffset(Float.intBitsToFloat((int) (4294967295L & j))), !NestedScrollSource.m454equalsimpl0(i, 1) ? 1 : 0, this.consumedScrollCache, null);
        return NestedScrollInteropConnectionKt.m569access$toOffsetUv8p0NA(iArr, j);
    }
}
