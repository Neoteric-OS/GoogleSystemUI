package androidx.recyclerview.widget;

import android.content.Context;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GapWorker;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.LayoutManager implements RecyclerView$SmoothScroller$ScrollVectorProvider {
    public final AnchorInfo mAnchorInfo;
    public final int mInitialPrefetchItemCount;
    public boolean mLastStackFromEnd;
    public final LayoutChunkResult mLayoutChunkResult;
    public LayoutState mLayoutState;
    public int mOrientation;
    public OrientationHelper$1 mOrientationHelper;
    public SavedState mPendingSavedState;
    public int mPendingScrollPosition;
    public int mPendingScrollPositionOffset;
    public final int[] mReusableIntPair;
    public final boolean mReverseLayout;
    public boolean mShouldReverseLayout;
    public final boolean mSmoothScrollbarEnabled;
    public boolean mStackFromEnd;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnchorInfo {
        public int mCoordinate;
        public boolean mLayoutFromEnd;
        public OrientationHelper$1 mOrientationHelper;
        public int mPosition;
        public boolean mValid;

        public AnchorInfo() {
            reset();
        }

        public final void assignCoordinateFromPadding() {
            this.mCoordinate = this.mLayoutFromEnd ? this.mOrientationHelper.getEndAfterPadding() : this.mOrientationHelper.getStartAfterPadding();
        }

        public final void assignFromView(View view, int i) {
            if (this.mLayoutFromEnd) {
                this.mCoordinate = this.mOrientationHelper.getTotalSpaceChange() + this.mOrientationHelper.getDecoratedEnd(view);
            } else {
                this.mCoordinate = this.mOrientationHelper.getDecoratedStart(view);
            }
            this.mPosition = i;
        }

        public final void assignFromViewAndKeepVisibleRect(View view, int i) {
            int totalSpaceChange = this.mOrientationHelper.getTotalSpaceChange();
            if (totalSpaceChange >= 0) {
                assignFromView(view, i);
                return;
            }
            this.mPosition = i;
            if (!this.mLayoutFromEnd) {
                int decoratedStart = this.mOrientationHelper.getDecoratedStart(view);
                int startAfterPadding = decoratedStart - this.mOrientationHelper.getStartAfterPadding();
                this.mCoordinate = decoratedStart;
                if (startAfterPadding > 0) {
                    int endAfterPadding = (this.mOrientationHelper.getEndAfterPadding() - Math.min(0, (this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange) - this.mOrientationHelper.getDecoratedEnd(view))) - (this.mOrientationHelper.getDecoratedMeasurement(view) + decoratedStart);
                    if (endAfterPadding < 0) {
                        this.mCoordinate -= Math.min(startAfterPadding, -endAfterPadding);
                        return;
                    }
                    return;
                }
                return;
            }
            int endAfterPadding2 = (this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange) - this.mOrientationHelper.getDecoratedEnd(view);
            this.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - endAfterPadding2;
            if (endAfterPadding2 > 0) {
                int decoratedMeasurement = this.mCoordinate - this.mOrientationHelper.getDecoratedMeasurement(view);
                int startAfterPadding2 = this.mOrientationHelper.getStartAfterPadding();
                int min = decoratedMeasurement - (Math.min(this.mOrientationHelper.getDecoratedStart(view) - startAfterPadding2, 0) + startAfterPadding2);
                if (min < 0) {
                    this.mCoordinate = Math.min(endAfterPadding2, -min) + this.mCoordinate;
                }
            }
        }

        public final void reset() {
            this.mPosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mValid = false;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("AnchorInfo{mPosition=");
            sb.append(this.mPosition);
            sb.append(", mCoordinate=");
            sb.append(this.mCoordinate);
            sb.append(", mLayoutFromEnd=");
            sb.append(this.mLayoutFromEnd);
            sb.append(", mValid=");
            return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.mValid, '}');
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LayoutChunkResult {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LayoutState {
        public int mAvailable;
        public int mCurrentPosition;
        public int mExtraFillSpace;
        public boolean mInfinite;
        public int mItemDirection;
        public int mLastScrollDelta;
        public int mLayoutDirection;
        public int mNoRecycleSpace;
        public int mOffset;
        public boolean mRecycle;
        public List mScrapList;
        public int mScrollingOffset;

        public final void assignPositionFromScrapList(View view) {
            int layoutPosition;
            int size = this.mScrapList.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = ((RecyclerView.ViewHolder) this.mScrapList.get(i2)).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view3.getLayoutParams();
                if (view3 != view && !layoutParams.mViewHolder.isRemoved() && (layoutPosition = (layoutParams.mViewHolder.getLayoutPosition() - this.mCurrentPosition) * this.mItemDirection) >= 0 && layoutPosition < i) {
                    view2 = view3;
                    if (layoutPosition == 0) {
                        break;
                    } else {
                        i = layoutPosition;
                    }
                }
            }
            if (view2 == null) {
                this.mCurrentPosition = -1;
            } else {
                this.mCurrentPosition = ((RecyclerView.LayoutParams) view2.getLayoutParams()).mViewHolder.getLayoutPosition();
            }
        }

        public final View next(RecyclerView.Recycler recycler) {
            List list = this.mScrapList;
            if (list == null) {
                View view = recycler.tryGetViewHolderForPositionByDeadline(Long.MAX_VALUE, this.mCurrentPosition).itemView;
                this.mCurrentPosition += this.mItemDirection;
                return view;
            }
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view2 = ((RecyclerView.ViewHolder) this.mScrapList.get(i)).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view2.getLayoutParams();
                if (!layoutParams.mViewHolder.isRemoved() && this.mCurrentPosition == layoutParams.mViewHolder.getLayoutPosition()) {
                    assignPositionFromScrapList(view2);
                    return view2;
                }
            }
            return null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR = new AnonymousClass1();
        public boolean mAnchorLayoutFromEnd;
        public int mAnchorOffset;
        public int mAnchorPosition;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: androidx.recyclerview.widget.LinearLayoutManager$SavedState$1, reason: invalid class name */
        public final class AnonymousClass1 implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                SavedState savedState = new SavedState();
                savedState.mAnchorPosition = parcel.readInt();
                savedState.mAnchorOffset = parcel.readInt();
                savedState.mAnchorLayoutFromEnd = parcel.readInt() == 1;
                return savedState;
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
        }
    }

    public LinearLayoutManager(int i) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        setOrientation(i);
        assertNotInLayoutOrScroll(null);
        if (this.mReverseLayout) {
            this.mReverseLayout = false;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
        int i;
        int totalSpace = state.mTargetPosition != -1 ? this.mOrientationHelper.getTotalSpace() : 0;
        if (this.mLayoutState.mLayoutDirection == -1) {
            i = 0;
        } else {
            i = totalSpace;
            totalSpace = 0;
        }
        iArr[0] = totalSpace;
        iArr[1] = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.State state, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        if (this.mOrientation != 0) {
            i = i2;
        }
        if (getChildCount() == 0 || i == 0) {
            return;
        }
        ensureLayoutState();
        updateLayoutState(i > 0 ? 1 : -1, Math.abs(i), true, state);
        collectPrefetchPositionsForLayoutState(state, this.mLayoutState, layoutPrefetchRegistryImpl);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void collectInitialPrefetchPositions(int i, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        boolean z;
        int i2;
        SavedState savedState = this.mPendingSavedState;
        if (savedState == null || (i2 = savedState.mAnchorPosition) < 0) {
            resolveShouldLayoutReverse();
            z = this.mShouldReverseLayout;
            i2 = this.mPendingScrollPosition;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            z = savedState.mAnchorLayoutFromEnd;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.mInitialPrefetchItemCount && i2 >= 0 && i2 < i; i4++) {
            layoutPrefetchRegistryImpl.addPosition(i2, 0);
            i2 += i3;
        }
    }

    public void collectPrefetchPositionsForLayoutState(RecyclerView.State state, LayoutState layoutState, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        int i = layoutState.mCurrentPosition;
        if (i < 0 || i >= state.getItemCount()) {
            return;
        }
        layoutPrefetchRegistryImpl.addPosition(i, Math.max(0, layoutState.mScrollingOffset));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public final int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        OrientationHelper$1 orientationHelper$1 = this.mOrientationHelper;
        boolean z = !this.mSmoothScrollbarEnabled;
        return ScrollbarHelper.computeScrollExtent(state, orientationHelper$1, findFirstVisibleChildClosestToStart(z), findFirstVisibleChildClosestToEnd(z), this, this.mSmoothScrollbarEnabled);
    }

    public final int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        OrientationHelper$1 orientationHelper$1 = this.mOrientationHelper;
        boolean z = !this.mSmoothScrollbarEnabled;
        return ScrollbarHelper.computeScrollOffset(state, orientationHelper$1, findFirstVisibleChildClosestToStart(z), findFirstVisibleChildClosestToEnd(z), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    public final int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        OrientationHelper$1 orientationHelper$1 = this.mOrientationHelper;
        boolean z = !this.mSmoothScrollbarEnabled;
        return ScrollbarHelper.computeScrollRange(state, orientationHelper$1, findFirstVisibleChildClosestToStart(z), findFirstVisibleChildClosestToEnd(z), this, this.mSmoothScrollbarEnabled);
    }

    @Override // androidx.recyclerview.widget.RecyclerView$SmoothScroller$ScrollVectorProvider
    public final PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = (i < RecyclerView.LayoutManager.getPosition(getChildAt(0))) != this.mShouldReverseLayout ? -1 : 1;
        return this.mOrientation == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public final int convertFocusDirectionToLayoutDirection$1(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.mOrientation == 1) ? 1 : Integer.MIN_VALUE : this.mOrientation == 0 ? 1 : Integer.MIN_VALUE : this.mOrientation == 1 ? -1 : Integer.MIN_VALUE : this.mOrientation == 0 ? -1 : Integer.MIN_VALUE : (this.mOrientation != 1 && isLayoutRTL()) ? -1 : 1 : (this.mOrientation != 1 && isLayoutRTL()) ? 1 : -1;
    }

    public final void ensureLayoutState() {
        if (this.mLayoutState == null) {
            LayoutState layoutState = new LayoutState();
            layoutState.mRecycle = true;
            layoutState.mExtraFillSpace = 0;
            layoutState.mNoRecycleSpace = 0;
            layoutState.mScrapList = null;
            this.mLayoutState = layoutState;
        }
    }

    public final int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state, boolean z) {
        int i;
        int i2 = layoutState.mAvailable;
        int i3 = layoutState.mScrollingOffset;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 < 0) {
                layoutState.mScrollingOffset = i3 + i2;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        int i4 = layoutState.mAvailable + layoutState.mExtraFillSpace;
        while (true) {
            if ((!layoutState.mInfinite && i4 <= 0) || (i = layoutState.mCurrentPosition) < 0 || i >= state.getItemCount()) {
                break;
            }
            LayoutChunkResult layoutChunkResult = this.mLayoutChunkResult;
            layoutChunkResult.mConsumed = 0;
            layoutChunkResult.mFinished = false;
            layoutChunkResult.mIgnoreConsumed = false;
            layoutChunkResult.mFocusable = false;
            layoutChunk(recycler, state, layoutState, layoutChunkResult);
            if (!layoutChunkResult.mFinished) {
                int i5 = layoutState.mOffset;
                int i6 = layoutChunkResult.mConsumed;
                layoutState.mOffset = (layoutState.mLayoutDirection * i6) + i5;
                if (!layoutChunkResult.mIgnoreConsumed || layoutState.mScrapList != null || !state.mInPreLayout) {
                    layoutState.mAvailable -= i6;
                    i4 -= i6;
                }
                int i7 = layoutState.mScrollingOffset;
                if (i7 != Integer.MIN_VALUE) {
                    int i8 = i7 + i6;
                    layoutState.mScrollingOffset = i8;
                    int i9 = layoutState.mAvailable;
                    if (i9 < 0) {
                        layoutState.mScrollingOffset = i8 + i9;
                    }
                    recycleByLayoutState(recycler, layoutState);
                }
                if (z && layoutChunkResult.mFocusable) {
                    break;
                }
            } else {
                break;
            }
        }
        return i2 - layoutState.mAvailable;
    }

    public final View findFirstVisibleChildClosestToEnd(boolean z) {
        return this.mShouldReverseLayout ? findOneVisibleChild(0, getChildCount(), z) : findOneVisibleChild(getChildCount() - 1, -1, z);
    }

    public final View findFirstVisibleChildClosestToStart(boolean z) {
        return this.mShouldReverseLayout ? findOneVisibleChild(getChildCount() - 1, -1, z) : findOneVisibleChild(0, getChildCount(), z);
    }

    public final View findOnePartiallyOrCompletelyInvisibleChild(int i, int i2) {
        int i3;
        int i4;
        ensureLayoutState();
        if (i2 <= i && i2 >= i) {
            return getChildAt(i);
        }
        if (this.mOrientationHelper.getDecoratedStart(getChildAt(i)) < this.mOrientationHelper.getStartAfterPadding()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        return this.mOrientation == 0 ? this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(i, i2, i3, i4) : this.mVerticalBoundCheck.findOneViewWithinBoundFlags(i, i2, i3, i4);
    }

    public final View findOneVisibleChild(int i, int i2, boolean z) {
        ensureLayoutState();
        int i3 = z ? 24579 : 320;
        return this.mOrientation == 0 ? this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(i, i2, i3, 320) : this.mVerticalBoundCheck.findOneViewWithinBoundFlags(i, i2, i3, 320);
    }

    public View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z, boolean z2) {
        int i;
        int i2;
        int i3;
        ensureLayoutState();
        int childCount = getChildCount();
        if (z2) {
            i2 = getChildCount() - 1;
            i = -1;
            i3 = -1;
        } else {
            i = childCount;
            i2 = 0;
            i3 = 1;
        }
        int itemCount = state.getItemCount();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        View view = null;
        View view2 = null;
        View view3 = null;
        while (i2 != i) {
            View childAt = getChildAt(i2);
            int position = RecyclerView.LayoutManager.getPosition(childAt);
            int decoratedStart = this.mOrientationHelper.getDecoratedStart(childAt);
            int decoratedEnd = this.mOrientationHelper.getDecoratedEnd(childAt);
            if (position >= 0 && position < itemCount) {
                if (!((RecyclerView.LayoutParams) childAt.getLayoutParams()).mViewHolder.isRemoved()) {
                    boolean z3 = decoratedEnd <= startAfterPadding && decoratedStart < startAfterPadding;
                    boolean z4 = decoratedStart >= endAfterPadding && decoratedEnd > endAfterPadding;
                    if (!z3 && !z4) {
                        return childAt;
                    }
                    if (z) {
                        if (!z4) {
                            if (view != null) {
                            }
                            view = childAt;
                        }
                        view2 = childAt;
                    } else {
                        if (!z3) {
                            if (view != null) {
                            }
                            view = childAt;
                        }
                        view2 = childAt;
                    }
                } else if (view3 == null) {
                    view3 = childAt;
                }
            }
            i2 += i3;
        }
        return view != null ? view : view2 != null ? view2 : view3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final View findViewByPosition(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i - RecyclerView.LayoutManager.getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (RecyclerView.LayoutManager.getPosition(childAt) == i) {
                return childAt;
            }
        }
        return super.findViewByPosition(i);
    }

    public final int fixLayoutEndGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i;
        if (endAfterPadding2 <= 0) {
            return 0;
        }
        int i2 = -scrollBy$1(-endAfterPadding2, recycler, state);
        int i3 = i + i2;
        if (!z || (endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i3) <= 0) {
            return i2;
        }
        this.mOrientationHelper.offsetChildren(endAfterPadding);
        return endAfterPadding + i2;
    }

    public final int fixLayoutStartGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int startAfterPadding2 = i - this.mOrientationHelper.getStartAfterPadding();
        if (startAfterPadding2 <= 0) {
            return 0;
        }
        int i2 = -scrollBy$1(startAfterPadding2, recycler, state);
        int i3 = i + i2;
        if (!z || (startAfterPadding = i3 - this.mOrientationHelper.getStartAfterPadding()) <= 0) {
            return i2;
        }
        this.mOrientationHelper.offsetChildren(-startAfterPadding);
        return i2 - startAfterPadding;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public final View getChildClosestToEnd$1() {
        return getChildAt(this.mShouldReverseLayout ? 0 : getChildCount() - 1);
    }

    public final View getChildClosestToStart$1() {
        return getChildAt(this.mShouldReverseLayout ? getChildCount() - 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean isAutoMeasureEnabled() {
        return true;
    }

    public final boolean isLayoutRTL() {
        return this.mRecyclerView.getLayoutDirection() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean isLayoutReversed() {
        return this.mReverseLayout;
    }

    public void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState, LayoutChunkResult layoutChunkResult) {
        int paddingTop;
        int i;
        int i2;
        int i3;
        int i4;
        View next = layoutState.next(recycler);
        if (next == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) next.getLayoutParams();
        if (layoutState.mScrapList == null) {
            if (this.mShouldReverseLayout == (layoutState.mLayoutDirection == -1)) {
                addViewInt(-1, next, false);
            } else {
                addViewInt(0, next, false);
            }
        } else {
            if (this.mShouldReverseLayout == (layoutState.mLayoutDirection == -1)) {
                addViewInt(-1, next, true);
            } else {
                addViewInt(0, next, true);
            }
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) next.getLayoutParams();
        Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(next);
        int i5 = itemDecorInsetsForChild.left + itemDecorInsetsForChild.right;
        int i6 = itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom;
        int childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(canScrollHorizontally(), this.mWidth, this.mWidthMode, getPaddingRight() + getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin + i5, ((ViewGroup.MarginLayoutParams) layoutParams2).width);
        int childMeasureSpec2 = RecyclerView.LayoutManager.getChildMeasureSpec(canScrollVertically(), this.mHeight, this.mHeightMode, getPaddingBottom() + getPaddingTop() + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin + i6, ((ViewGroup.MarginLayoutParams) layoutParams2).height);
        if (shouldMeasureChild(next, childMeasureSpec, childMeasureSpec2, layoutParams2)) {
            next.measure(childMeasureSpec, childMeasureSpec2);
        }
        layoutChunkResult.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(next);
        if (this.mOrientation == 1) {
            if (isLayoutRTL()) {
                i2 = this.mWidth - getPaddingRight();
                i4 = i2 - this.mOrientationHelper.getDecoratedMeasurementInOther(next);
            } else {
                int paddingLeft = getPaddingLeft();
                i2 = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + paddingLeft;
                i4 = paddingLeft;
            }
            if (layoutState.mLayoutDirection == -1) {
                i3 = layoutState.mOffset;
                paddingTop = i3 - layoutChunkResult.mConsumed;
            } else {
                paddingTop = layoutState.mOffset;
                i3 = layoutChunkResult.mConsumed + paddingTop;
            }
        } else {
            paddingTop = getPaddingTop();
            int decoratedMeasurementInOther = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + paddingTop;
            if (layoutState.mLayoutDirection == -1) {
                i2 = layoutState.mOffset;
                i = i2 - layoutChunkResult.mConsumed;
            } else {
                i = layoutState.mOffset;
                i2 = layoutChunkResult.mConsumed + i;
            }
            int i7 = i;
            i3 = decoratedMeasurementInOther;
            i4 = i7;
        }
        RecyclerView.LayoutManager.layoutDecoratedWithMargins(next, i4, paddingTop, i2, i3);
        if (layoutParams.mViewHolder.isRemoved() || layoutParams.mViewHolder.isUpdated()) {
            layoutChunkResult.mIgnoreConsumed = true;
        }
        layoutChunkResult.mFocusable = next.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int convertFocusDirectionToLayoutDirection$1;
        resolveShouldLayoutReverse();
        if (getChildCount() == 0 || (convertFocusDirectionToLayoutDirection$1 = convertFocusDirectionToLayoutDirection$1(i)) == Integer.MIN_VALUE) {
            return null;
        }
        ensureLayoutState();
        updateLayoutState(convertFocusDirectionToLayoutDirection$1, (int) (this.mOrientationHelper.getTotalSpace() * 0.33333334f), false, state);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mScrollingOffset = Integer.MIN_VALUE;
        layoutState.mRecycle = false;
        fill(recycler, layoutState, state, true);
        View findOnePartiallyOrCompletelyInvisibleChild = convertFocusDirectionToLayoutDirection$1 == -1 ? this.mShouldReverseLayout ? findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1) : findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount()) : this.mShouldReverseLayout ? findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount()) : findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
        View childClosestToStart$1 = convertFocusDirectionToLayoutDirection$1 == -1 ? getChildClosestToStart$1() : getChildClosestToEnd$1();
        if (!childClosestToStart$1.hasFocusable()) {
            return findOnePartiallyOrCompletelyInvisibleChild;
        }
        if (findOnePartiallyOrCompletelyInvisibleChild == null) {
            return null;
        }
        return childClosestToStart$1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View findOneVisibleChild = findOneVisibleChild(0, getChildCount(), false);
            accessibilityEvent.setFromIndex(findOneVisibleChild == null ? -1 : RecyclerView.LayoutManager.getPosition(findOneVisibleChild));
            View findOneVisibleChild2 = findOneVisibleChild(getChildCount() - 1, -1, false);
            accessibilityEvent.setToIndex(findOneVisibleChild2 != null ? RecyclerView.LayoutManager.getPosition(findOneVisibleChild2) : -1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
        RecyclerView.Adapter adapter = this.mRecyclerView.mAdapter;
        if (adapter == null || adapter.getItemCount() <= 0) {
            return;
        }
        accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_TO_POSITION);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        View focusedChild;
        View focusedChild2;
        View findReferenceChild;
        int i;
        int i2;
        int i3;
        List list;
        int i4;
        int i5;
        int fixLayoutEndGap;
        int i6;
        View findViewByPosition;
        int decoratedStart;
        int i7;
        int i8;
        int i9 = -1;
        if (!(this.mPendingSavedState == null && this.mPendingScrollPosition == -1) && state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && (i8 = savedState.mAnchorPosition) >= 0) {
            this.mPendingScrollPosition = i8;
        }
        ensureLayoutState();
        this.mLayoutState.mRecycle = false;
        resolveShouldLayoutReverse();
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.mChildHelper.mHiddenViews.contains(focusedChild)) {
            focusedChild = null;
        }
        AnchorInfo anchorInfo = this.mAnchorInfo;
        if (!anchorInfo.mValid || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
            anchorInfo.reset();
            anchorInfo.mLayoutFromEnd = this.mShouldReverseLayout ^ this.mStackFromEnd;
            if (!state.mInPreLayout && (i = this.mPendingScrollPosition) != -1) {
                if (i < 0 || i >= state.getItemCount()) {
                    this.mPendingScrollPosition = -1;
                    this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
                } else {
                    int i10 = this.mPendingScrollPosition;
                    anchorInfo.mPosition = i10;
                    SavedState savedState2 = this.mPendingSavedState;
                    if (savedState2 != null && savedState2.mAnchorPosition >= 0) {
                        boolean z = savedState2.mAnchorLayoutFromEnd;
                        anchorInfo.mLayoutFromEnd = z;
                        if (z) {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset;
                        } else {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset;
                        }
                    } else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                        View findViewByPosition2 = findViewByPosition(i10);
                        if (findViewByPosition2 == null) {
                            if (getChildCount() > 0) {
                                anchorInfo.mLayoutFromEnd = (this.mPendingScrollPosition < RecyclerView.LayoutManager.getPosition(getChildAt(0))) == this.mShouldReverseLayout;
                            }
                            anchorInfo.assignCoordinateFromPadding();
                        } else if (this.mOrientationHelper.getDecoratedMeasurement(findViewByPosition2) > this.mOrientationHelper.getTotalSpace()) {
                            anchorInfo.assignCoordinateFromPadding();
                        } else if (this.mOrientationHelper.getDecoratedStart(findViewByPosition2) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                            anchorInfo.mLayoutFromEnd = false;
                        } else if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(findViewByPosition2) < 0) {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                            anchorInfo.mLayoutFromEnd = true;
                        } else {
                            anchorInfo.mCoordinate = anchorInfo.mLayoutFromEnd ? this.mOrientationHelper.getTotalSpaceChange() + this.mOrientationHelper.getDecoratedEnd(findViewByPosition2) : this.mOrientationHelper.getDecoratedStart(findViewByPosition2);
                        }
                    } else {
                        boolean z2 = this.mShouldReverseLayout;
                        anchorInfo.mLayoutFromEnd = z2;
                        if (z2) {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset;
                        } else {
                            anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
                        }
                    }
                    anchorInfo.mValid = true;
                }
            }
            if (getChildCount() != 0) {
                RecyclerView recyclerView2 = this.mRecyclerView;
                if (recyclerView2 == null || (focusedChild2 = recyclerView2.getFocusedChild()) == null || this.mChildHelper.mHiddenViews.contains(focusedChild2)) {
                    focusedChild2 = null;
                }
                if (focusedChild2 != null) {
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) focusedChild2.getLayoutParams();
                    if (!layoutParams.mViewHolder.isRemoved() && layoutParams.mViewHolder.getLayoutPosition() >= 0 && layoutParams.mViewHolder.getLayoutPosition() < state.getItemCount()) {
                        anchorInfo.assignFromViewAndKeepVisibleRect(focusedChild2, RecyclerView.LayoutManager.getPosition(focusedChild2));
                        anchorInfo.mValid = true;
                    }
                }
                boolean z3 = this.mLastStackFromEnd;
                boolean z4 = this.mStackFromEnd;
                if (z3 == z4 && (findReferenceChild = findReferenceChild(recycler, state, anchorInfo.mLayoutFromEnd, z4)) != null) {
                    anchorInfo.assignFromView(findReferenceChild, RecyclerView.LayoutManager.getPosition(findReferenceChild));
                    if (!state.mInPreLayout && supportsPredictiveItemAnimations()) {
                        int decoratedStart2 = this.mOrientationHelper.getDecoratedStart(findReferenceChild);
                        int decoratedEnd = this.mOrientationHelper.getDecoratedEnd(findReferenceChild);
                        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
                        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
                        boolean z5 = decoratedEnd <= startAfterPadding && decoratedStart2 < startAfterPadding;
                        boolean z6 = decoratedStart2 >= endAfterPadding && decoratedEnd > endAfterPadding;
                        if (z5 || z6) {
                            if (anchorInfo.mLayoutFromEnd) {
                                startAfterPadding = endAfterPadding;
                            }
                            anchorInfo.mCoordinate = startAfterPadding;
                        }
                    }
                    anchorInfo.mValid = true;
                }
            }
            anchorInfo.assignCoordinateFromPadding();
            anchorInfo.mPosition = this.mStackFromEnd ? state.getItemCount() - 1 : 0;
            anchorInfo.mValid = true;
        } else if (focusedChild != null && (this.mOrientationHelper.getDecoratedStart(focusedChild) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(focusedChild) <= this.mOrientationHelper.getStartAfterPadding())) {
            anchorInfo.assignFromViewAndKeepVisibleRect(focusedChild, RecyclerView.LayoutManager.getPosition(focusedChild));
        }
        LayoutState layoutState = this.mLayoutState;
        layoutState.mLayoutDirection = layoutState.mLastScrollDelta >= 0 ? 1 : -1;
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        iArr[1] = 0;
        calculateExtraLayoutSpace(state, iArr);
        int startAfterPadding2 = this.mOrientationHelper.getStartAfterPadding() + Math.max(0, iArr[0]);
        int endPadding = this.mOrientationHelper.getEndPadding() + Math.max(0, iArr[1]);
        if (state.mInPreLayout && (i6 = this.mPendingScrollPosition) != -1 && this.mPendingScrollPositionOffset != Integer.MIN_VALUE && (findViewByPosition = findViewByPosition(i6)) != null) {
            if (this.mShouldReverseLayout) {
                i7 = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(findViewByPosition);
                decoratedStart = this.mPendingScrollPositionOffset;
            } else {
                decoratedStart = this.mOrientationHelper.getDecoratedStart(findViewByPosition) - this.mOrientationHelper.getStartAfterPadding();
                i7 = this.mPendingScrollPositionOffset;
            }
            int i11 = i7 - decoratedStart;
            if (i11 > 0) {
                startAfterPadding2 += i11;
            } else {
                endPadding -= i11;
            }
        }
        if (!anchorInfo.mLayoutFromEnd ? !this.mShouldReverseLayout : this.mShouldReverseLayout) {
            i9 = 1;
        }
        onAnchorReady(recycler, state, anchorInfo, i9);
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            scrapOrRecycleView(recycler, childCount, getChildAt(childCount));
        }
        this.mLayoutState.mInfinite = this.mOrientationHelper.getMode() == 0 && this.mOrientationHelper.getEnd() == 0;
        this.mLayoutState.getClass();
        this.mLayoutState.mNoRecycleSpace = 0;
        if (anchorInfo.mLayoutFromEnd) {
            updateLayoutStateToFillStart(anchorInfo.mPosition, anchorInfo.mCoordinate);
            LayoutState layoutState2 = this.mLayoutState;
            layoutState2.mExtraFillSpace = startAfterPadding2;
            fill(recycler, layoutState2, state, false);
            LayoutState layoutState3 = this.mLayoutState;
            i3 = layoutState3.mOffset;
            int i12 = layoutState3.mCurrentPosition;
            int i13 = layoutState3.mAvailable;
            if (i13 > 0) {
                endPadding += i13;
            }
            updateLayoutStateToFillEnd(anchorInfo.mPosition, anchorInfo.mCoordinate);
            LayoutState layoutState4 = this.mLayoutState;
            layoutState4.mExtraFillSpace = endPadding;
            layoutState4.mCurrentPosition += layoutState4.mItemDirection;
            fill(recycler, layoutState4, state, false);
            LayoutState layoutState5 = this.mLayoutState;
            i2 = layoutState5.mOffset;
            int i14 = layoutState5.mAvailable;
            if (i14 > 0) {
                updateLayoutStateToFillStart(i12, i3);
                LayoutState layoutState6 = this.mLayoutState;
                layoutState6.mExtraFillSpace = i14;
                fill(recycler, layoutState6, state, false);
                i3 = this.mLayoutState.mOffset;
            }
        } else {
            updateLayoutStateToFillEnd(anchorInfo.mPosition, anchorInfo.mCoordinate);
            LayoutState layoutState7 = this.mLayoutState;
            layoutState7.mExtraFillSpace = endPadding;
            fill(recycler, layoutState7, state, false);
            LayoutState layoutState8 = this.mLayoutState;
            i2 = layoutState8.mOffset;
            int i15 = layoutState8.mCurrentPosition;
            int i16 = layoutState8.mAvailable;
            if (i16 > 0) {
                startAfterPadding2 += i16;
            }
            updateLayoutStateToFillStart(anchorInfo.mPosition, anchorInfo.mCoordinate);
            LayoutState layoutState9 = this.mLayoutState;
            layoutState9.mExtraFillSpace = startAfterPadding2;
            layoutState9.mCurrentPosition += layoutState9.mItemDirection;
            fill(recycler, layoutState9, state, false);
            LayoutState layoutState10 = this.mLayoutState;
            int i17 = layoutState10.mOffset;
            int i18 = layoutState10.mAvailable;
            if (i18 > 0) {
                updateLayoutStateToFillEnd(i15, i2);
                LayoutState layoutState11 = this.mLayoutState;
                layoutState11.mExtraFillSpace = i18;
                fill(recycler, layoutState11, state, false);
                i2 = this.mLayoutState.mOffset;
            }
            i3 = i17;
        }
        if (getChildCount() > 0) {
            if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
                int fixLayoutEndGap2 = fixLayoutEndGap(i2, recycler, state, true);
                i4 = i3 + fixLayoutEndGap2;
                i5 = i2 + fixLayoutEndGap2;
                fixLayoutEndGap = fixLayoutStartGap(i4, recycler, state, false);
            } else {
                int fixLayoutStartGap = fixLayoutStartGap(i3, recycler, state, true);
                i4 = i3 + fixLayoutStartGap;
                i5 = i2 + fixLayoutStartGap;
                fixLayoutEndGap = fixLayoutEndGap(i5, recycler, state, false);
            }
            i3 = i4 + fixLayoutEndGap;
            i2 = i5 + fixLayoutEndGap;
        }
        if (state.mRunPredictiveAnimations && getChildCount() != 0 && !state.mInPreLayout && supportsPredictiveItemAnimations()) {
            List list2 = recycler.mUnmodifiableAttachedScrap;
            int size = list2.size();
            int position = RecyclerView.LayoutManager.getPosition(getChildAt(0));
            int i19 = 0;
            int i20 = 0;
            for (int i21 = 0; i21 < size; i21++) {
                RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) list2.get(i21);
                if (!viewHolder.isRemoved()) {
                    if ((viewHolder.getLayoutPosition() < position) != this.mShouldReverseLayout) {
                        i19 += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
                    } else {
                        i20 += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
                    }
                }
            }
            this.mLayoutState.mScrapList = list2;
            if (i19 > 0) {
                updateLayoutStateToFillStart(RecyclerView.LayoutManager.getPosition(getChildClosestToStart$1()), i3);
                LayoutState layoutState12 = this.mLayoutState;
                layoutState12.mExtraFillSpace = i19;
                layoutState12.mAvailable = 0;
                layoutState12.assignPositionFromScrapList(null);
                fill(recycler, this.mLayoutState, state, false);
            }
            if (i20 > 0) {
                updateLayoutStateToFillEnd(RecyclerView.LayoutManager.getPosition(getChildClosestToEnd$1()), i2);
                LayoutState layoutState13 = this.mLayoutState;
                layoutState13.mExtraFillSpace = i20;
                layoutState13.mAvailable = 0;
                list = null;
                layoutState13.assignPositionFromScrapList(null);
                fill(recycler, this.mLayoutState, state, false);
            } else {
                list = null;
            }
            this.mLayoutState.mScrapList = list;
        }
        if (state.mInPreLayout) {
            anchorInfo.reset();
        } else {
            OrientationHelper$1 orientationHelper$1 = this.mOrientationHelper;
            orientationHelper$1.mLastTotalSpace = orientationHelper$1.getTotalSpace();
        }
        this.mLastStackFromEnd = this.mStackFromEnd;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mAnchorInfo.reset();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mPendingSavedState = savedState;
            if (this.mPendingScrollPosition != -1) {
                savedState.mAnchorPosition = -1;
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            SavedState savedState = this.mPendingSavedState;
            SavedState savedState2 = new SavedState();
            savedState2.mAnchorPosition = savedState.mAnchorPosition;
            savedState2.mAnchorOffset = savedState.mAnchorOffset;
            savedState2.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
            return savedState2;
        }
        SavedState savedState3 = new SavedState();
        if (getChildCount() > 0) {
            ensureLayoutState();
            boolean z = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            savedState3.mAnchorLayoutFromEnd = z;
            if (z) {
                View childClosestToEnd$1 = getChildClosestToEnd$1();
                savedState3.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(childClosestToEnd$1);
                savedState3.mAnchorPosition = RecyclerView.LayoutManager.getPosition(childClosestToEnd$1);
            } else {
                View childClosestToStart$1 = getChildClosestToStart$1();
                savedState3.mAnchorPosition = RecyclerView.LayoutManager.getPosition(childClosestToStart$1);
                savedState3.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart$1) - this.mOrientationHelper.getStartAfterPadding();
            }
        } else {
            savedState3.mAnchorPosition = -1;
        }
        return savedState3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        int min;
        if (super.performAccessibilityAction(i, bundle)) {
            return true;
        }
        if (i == 16908343 && bundle != null) {
            if (this.mOrientation == 1) {
                int i2 = bundle.getInt("android.view.accessibility.action.ARGUMENT_ROW_INT", -1);
                if (i2 < 0) {
                    return false;
                }
                RecyclerView recyclerView = this.mRecyclerView;
                min = Math.min(i2, getRowCountForAccessibility(recyclerView.mRecycler, recyclerView.mState) - 1);
            } else {
                int i3 = bundle.getInt("android.view.accessibility.action.ARGUMENT_COLUMN_INT", -1);
                if (i3 < 0) {
                    return false;
                }
                RecyclerView recyclerView2 = this.mRecyclerView;
                min = Math.min(i3, getColumnCountForAccessibility(recyclerView2.mRecycler, recyclerView2.mState) - 1);
            }
            if (min >= 0) {
                scrollToPositionWithOffset(min, 0);
                return true;
            }
        }
        return false;
    }

    public final void recycleByLayoutState(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (!layoutState.mRecycle || layoutState.mInfinite) {
            return;
        }
        int i = layoutState.mScrollingOffset;
        int i2 = layoutState.mNoRecycleSpace;
        if (layoutState.mLayoutDirection == -1) {
            int childCount = getChildCount();
            if (i < 0) {
                return;
            }
            int end = (this.mOrientationHelper.getEnd() - i) + i2;
            if (this.mShouldReverseLayout) {
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = getChildAt(i3);
                    if (this.mOrientationHelper.getDecoratedStart(childAt) < end || this.mOrientationHelper.getTransformedStartWithDecoration(childAt) < end) {
                        recycleChildren(recycler, 0, i3);
                        return;
                    }
                }
                return;
            }
            int i4 = childCount - 1;
            for (int i5 = i4; i5 >= 0; i5--) {
                View childAt2 = getChildAt(i5);
                if (this.mOrientationHelper.getDecoratedStart(childAt2) < end || this.mOrientationHelper.getTransformedStartWithDecoration(childAt2) < end) {
                    recycleChildren(recycler, i4, i5);
                    return;
                }
            }
            return;
        }
        if (i < 0) {
            return;
        }
        int i6 = i - i2;
        int childCount2 = getChildCount();
        if (!this.mShouldReverseLayout) {
            for (int i7 = 0; i7 < childCount2; i7++) {
                View childAt3 = getChildAt(i7);
                if (this.mOrientationHelper.getDecoratedEnd(childAt3) > i6 || this.mOrientationHelper.getTransformedEndWithDecoration(childAt3) > i6) {
                    recycleChildren(recycler, 0, i7);
                    return;
                }
            }
            return;
        }
        int i8 = childCount2 - 1;
        for (int i9 = i8; i9 >= 0; i9--) {
            View childAt4 = getChildAt(i9);
            if (this.mOrientationHelper.getDecoratedEnd(childAt4) > i6 || this.mOrientationHelper.getTransformedEndWithDecoration(childAt4) > i6) {
                recycleChildren(recycler, i8, i9);
                return;
            }
        }
    }

    public final void recycleChildren(RecyclerView.Recycler recycler, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                removeAndRecycleViewAt(i, recycler);
                i--;
            }
        } else {
            for (int i3 = i2 - 1; i3 >= i; i3--) {
                removeAndRecycleViewAt(i3, recycler);
            }
        }
    }

    public final void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = !this.mReverseLayout;
        }
    }

    public final int scrollBy$1(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        ensureLayoutState();
        this.mLayoutState.mRecycle = true;
        int i2 = i > 0 ? 1 : -1;
        int abs = Math.abs(i);
        updateLayoutState(i2, abs, true, state);
        LayoutState layoutState = this.mLayoutState;
        int fill = fill(recycler, layoutState, state, false) + layoutState.mScrollingOffset;
        if (fill < 0) {
            return 0;
        }
        if (abs > fill) {
            i = i2 * fill;
        }
        this.mOrientationHelper.offsetChildren(-i);
        this.mLayoutState.mLastScrollDelta = i;
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy$1(i, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void scrollToPosition(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.mAnchorPosition = -1;
        }
        requestLayout();
    }

    public final void scrollToPositionWithOffset(int i, int i2) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = i2;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.mAnchorPosition = -1;
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy$1(i, recycler, state);
    }

    public final void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "invalid orientation:"));
        }
        assertNotInLayoutOrScroll(null);
        if (i != this.mOrientation || this.mOrientationHelper == null) {
            OrientationHelper$1 createOrientationHelper = OrientationHelper$1.createOrientationHelper(this, i);
            this.mOrientationHelper = createOrientationHelper;
            this.mAnchorInfo.mOrientationHelper = createOrientationHelper;
            this.mOrientation = i;
            requestLayout();
        }
    }

    public void setStackFromEnd(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd == z) {
            return;
        }
        this.mStackFromEnd = z;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean shouldMeasureTwice() {
        if (this.mHeightMode == 1073741824 || this.mWidthMode == 1073741824) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup.LayoutParams layoutParams = getChildAt(i).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void smoothScrollToPosition(RecyclerView recyclerView, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.mTargetPosition = i;
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd;
    }

    public final void updateLayoutState(int i, int i2, boolean z, RecyclerView.State state) {
        int startAfterPadding;
        this.mLayoutState.mInfinite = this.mOrientationHelper.getMode() == 0 && this.mOrientationHelper.getEnd() == 0;
        this.mLayoutState.mLayoutDirection = i;
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        iArr[1] = 0;
        calculateExtraLayoutSpace(state, iArr);
        int max = Math.max(0, iArr[0]);
        int max2 = Math.max(0, iArr[1]);
        boolean z2 = i == 1;
        LayoutState layoutState = this.mLayoutState;
        int i3 = z2 ? max2 : max;
        layoutState.mExtraFillSpace = i3;
        if (!z2) {
            max = max2;
        }
        layoutState.mNoRecycleSpace = max;
        if (z2) {
            layoutState.mExtraFillSpace = this.mOrientationHelper.getEndPadding() + i3;
            View childClosestToEnd$1 = getChildClosestToEnd$1();
            LayoutState layoutState2 = this.mLayoutState;
            layoutState2.mItemDirection = this.mShouldReverseLayout ? -1 : 1;
            int position = RecyclerView.LayoutManager.getPosition(childClosestToEnd$1);
            LayoutState layoutState3 = this.mLayoutState;
            layoutState2.mCurrentPosition = position + layoutState3.mItemDirection;
            layoutState3.mOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd$1);
            startAfterPadding = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd$1) - this.mOrientationHelper.getEndAfterPadding();
        } else {
            View childClosestToStart$1 = getChildClosestToStart$1();
            LayoutState layoutState4 = this.mLayoutState;
            layoutState4.mExtraFillSpace = this.mOrientationHelper.getStartAfterPadding() + layoutState4.mExtraFillSpace;
            LayoutState layoutState5 = this.mLayoutState;
            layoutState5.mItemDirection = this.mShouldReverseLayout ? 1 : -1;
            int position2 = RecyclerView.LayoutManager.getPosition(childClosestToStart$1);
            LayoutState layoutState6 = this.mLayoutState;
            layoutState5.mCurrentPosition = position2 + layoutState6.mItemDirection;
            layoutState6.mOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart$1);
            startAfterPadding = (-this.mOrientationHelper.getDecoratedStart(childClosestToStart$1)) + this.mOrientationHelper.getStartAfterPadding();
        }
        LayoutState layoutState7 = this.mLayoutState;
        layoutState7.mAvailable = i2;
        if (z) {
            layoutState7.mAvailable = i2 - startAfterPadding;
        }
        layoutState7.mScrollingOffset = startAfterPadding;
    }

    public final void updateLayoutStateToFillEnd(int i, int i2) {
        this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - i2;
        LayoutState layoutState = this.mLayoutState;
        layoutState.mItemDirection = this.mShouldReverseLayout ? -1 : 1;
        layoutState.mCurrentPosition = i;
        layoutState.mLayoutDirection = 1;
        layoutState.mOffset = i2;
        layoutState.mScrollingOffset = Integer.MIN_VALUE;
    }

    public final void updateLayoutStateToFillStart(int i, int i2) {
        this.mLayoutState.mAvailable = i2 - this.mOrientationHelper.getStartAfterPadding();
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = i;
        layoutState.mItemDirection = this.mShouldReverseLayout ? 1 : -1;
        layoutState.mLayoutDirection = -1;
        layoutState.mOffset = i2;
        layoutState.mScrollingOffset = Integer.MIN_VALUE;
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.orientation);
        boolean z = properties.reverseLayout;
        assertNotInLayoutOrScroll(null);
        if (z != this.mReverseLayout) {
            this.mReverseLayout = z;
            requestLayout();
        }
        setStackFromEnd(properties.stackFromEnd);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onDetachedFromWindow(RecyclerView recyclerView) {
    }

    public void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo, int i) {
    }
}
