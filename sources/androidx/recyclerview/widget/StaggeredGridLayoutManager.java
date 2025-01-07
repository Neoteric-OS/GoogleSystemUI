package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GapWorker;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView$SmoothScroller$ScrollVectorProvider {
    public final AnchorInfo mAnchorInfo;
    public final AnonymousClass1 mCheckForGapsRunnable;
    public final int mGapStrategy;
    public boolean mLastLayoutFromEnd;
    public boolean mLastLayoutRTL;
    public final LayoutState mLayoutState;
    public final LazySpanLookup mLazySpanLookup;
    public final int mOrientation;
    public SavedState mPendingSavedState;
    public int[] mPrefetchDistances;
    public final OrientationHelper$1 mPrimaryOrientation;
    public final BitSet mRemainingSpans;
    public boolean mReverseLayout;
    public final OrientationHelper$1 mSecondaryOrientation;
    public int mSizePerSpan;
    public final boolean mSmoothScrollbarEnabled;
    public final int mSpanCount;
    public final Span[] mSpans;
    public final Rect mTmpRect;
    public boolean mShouldReverseLayout = false;
    public int mPendingScrollPosition = -1;
    public int mPendingScrollPositionOffset = Integer.MIN_VALUE;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnchorInfo {
        public boolean mInvalidateOffsets;
        public boolean mLayoutFromEnd;
        public int mOffset;
        public int mPosition;
        public int[] mSpanReferenceLines;
        public boolean mValid;

        public AnchorInfo() {
            reset();
        }

        public final void reset() {
            this.mPosition = -1;
            this.mOffset = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mInvalidateOffsets = false;
            this.mValid = false;
            int[] iArr = this.mSpanReferenceLines;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LayoutParams extends RecyclerView.LayoutParams {
        public Span mSpan;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LazySpanLookup {
        public int[] mData;
        public List mFullSpanItems;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator CREATOR = new SavedState.AnonymousClass1(1);
            public int mGapDir;
            public int[] mGapPerSpan;
            public boolean mHasUnwantedGapAfter;
            public int mPosition;

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public final String toString() {
                return "FullSpanItem{mPosition=" + this.mPosition + ", mGapDir=" + this.mGapDir + ", mHasUnwantedGapAfter=" + this.mHasUnwantedGapAfter + ", mGapPerSpan=" + Arrays.toString(this.mGapPerSpan) + '}';
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.mPosition);
                parcel.writeInt(this.mGapDir);
                parcel.writeInt(this.mHasUnwantedGapAfter ? 1 : 0);
                int[] iArr = this.mGapPerSpan;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.mGapPerSpan);
                }
            }
        }

        public final void clear() {
            int[] iArr = this.mData;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.mFullSpanItems = null;
        }

        public final void ensureSize(int i) {
            int[] iArr = this.mData;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i, 10) + 1];
                this.mData = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i >= iArr.length) {
                int length = iArr.length;
                while (length <= i) {
                    length *= 2;
                }
                int[] iArr3 = new int[length];
                this.mData = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.mData;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public final void offsetForAddition(int i, int i2) {
            int[] iArr = this.mData;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            ensureSize(i3);
            int[] iArr2 = this.mData;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.mData, i, i3, -1);
            List list = this.mFullSpanItems;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.mFullSpanItems.get(size);
                int i4 = fullSpanItem.mPosition;
                if (i4 >= i) {
                    fullSpanItem.mPosition = i4 + i2;
                }
            }
        }

        public final void offsetForRemoval(int i, int i2) {
            int[] iArr = this.mData;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            ensureSize(i3);
            int[] iArr2 = this.mData;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.mData;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            List list = this.mFullSpanItems;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = (FullSpanItem) this.mFullSpanItems.get(size);
                int i4 = fullSpanItem.mPosition;
                if (i4 >= i) {
                    if (i4 < i3) {
                        this.mFullSpanItems.remove(size);
                    } else {
                        fullSpanItem.mPosition = i4 - i2;
                    }
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR = new AnonymousClass1(0);
        public boolean mAnchorLayoutFromEnd;
        public int mAnchorPosition;
        public List mFullSpanItems;
        public boolean mLastLayoutRTL;
        public boolean mReverseLayout;
        public int[] mSpanLookup;
        public int mSpanLookupSize;
        public int[] mSpanOffsets;
        public int mSpanOffsetsSize;
        public int mVisibleAnchorPosition;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: androidx.recyclerview.widget.StaggeredGridLayoutManager$SavedState$1, reason: invalid class name */
        public final class AnonymousClass1 implements Parcelable.Creator {
            public final /* synthetic */ int $r8$classId;

            public /* synthetic */ AnonymousClass1(int i) {
                this.$r8$classId = i;
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                switch (this.$r8$classId) {
                    case 0:
                        SavedState savedState = new SavedState();
                        savedState.mAnchorPosition = parcel.readInt();
                        savedState.mVisibleAnchorPosition = parcel.readInt();
                        int readInt = parcel.readInt();
                        savedState.mSpanOffsetsSize = readInt;
                        if (readInt > 0) {
                            int[] iArr = new int[readInt];
                            savedState.mSpanOffsets = iArr;
                            parcel.readIntArray(iArr);
                        }
                        int readInt2 = parcel.readInt();
                        savedState.mSpanLookupSize = readInt2;
                        if (readInt2 > 0) {
                            int[] iArr2 = new int[readInt2];
                            savedState.mSpanLookup = iArr2;
                            parcel.readIntArray(iArr2);
                        }
                        savedState.mReverseLayout = parcel.readInt() == 1;
                        savedState.mAnchorLayoutFromEnd = parcel.readInt() == 1;
                        savedState.mLastLayoutRTL = parcel.readInt() == 1;
                        savedState.mFullSpanItems = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
                        return savedState;
                    default:
                        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
                        fullSpanItem.mPosition = parcel.readInt();
                        fullSpanItem.mGapDir = parcel.readInt();
                        fullSpanItem.mHasUnwantedGapAfter = parcel.readInt() == 1;
                        int readInt3 = parcel.readInt();
                        if (readInt3 > 0) {
                            int[] iArr3 = new int[readInt3];
                            fullSpanItem.mGapPerSpan = iArr3;
                            parcel.readIntArray(iArr3);
                        }
                        return fullSpanItem;
                }
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                switch (this.$r8$classId) {
                    case 0:
                        return new SavedState[i];
                    default:
                        return new LazySpanLookup.FullSpanItem[i];
                }
            }
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mVisibleAnchorPosition);
            parcel.writeInt(this.mSpanOffsetsSize);
            if (this.mSpanOffsetsSize > 0) {
                parcel.writeIntArray(this.mSpanOffsets);
            }
            parcel.writeInt(this.mSpanLookupSize);
            if (this.mSpanLookupSize > 0) {
                parcel.writeIntArray(this.mSpanLookup);
            }
            parcel.writeInt(this.mReverseLayout ? 1 : 0);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
            parcel.writeInt(this.mLastLayoutRTL ? 1 : 0);
            parcel.writeList(this.mFullSpanItems);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Span {
        public final int mIndex;
        public final ArrayList mViews = new ArrayList();
        public int mCachedStart = Integer.MIN_VALUE;
        public int mCachedEnd = Integer.MIN_VALUE;
        public int mDeletedSize = 0;

        public Span(int i) {
            this.mIndex = i;
        }

        public final void calculateCachedEnd() {
            View view = (View) CascadingMenuPopup$$ExternalSyntheticOutline0.m(this.mViews, 1);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            this.mCachedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
            layoutParams.getClass();
        }

        public final void clear() {
            this.mViews.clear();
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
            this.mDeletedSize = 0;
        }

        public final int findFirstPartiallyVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? findOnePartiallyVisibleChild(this.mViews.size() - 1, -1) : findOnePartiallyVisibleChild(0, this.mViews.size());
        }

        public final int findLastPartiallyVisibleItemPosition() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? findOnePartiallyVisibleChild(0, this.mViews.size()) : findOnePartiallyVisibleChild(this.mViews.size() - 1, -1);
        }

        public final int findOnePartiallyVisibleChild(int i, int i2) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
            int startAfterPadding = staggeredGridLayoutManager.mPrimaryOrientation.getStartAfterPadding();
            int endAfterPadding = staggeredGridLayoutManager.mPrimaryOrientation.getEndAfterPadding();
            int i3 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = (View) this.mViews.get(i);
                int decoratedStart = staggeredGridLayoutManager.mPrimaryOrientation.getDecoratedStart(view);
                int decoratedEnd = staggeredGridLayoutManager.mPrimaryOrientation.getDecoratedEnd(view);
                boolean z = decoratedStart <= endAfterPadding;
                boolean z2 = decoratedEnd >= startAfterPadding;
                if (z && z2 && (decoratedStart < startAfterPadding || decoratedEnd > endAfterPadding)) {
                    return RecyclerView.LayoutManager.getPosition(view);
                }
                i += i3;
            }
            return -1;
        }

        public final int getEndLine(int i) {
            int i2 = this.mCachedEnd;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.mViews.size() == 0) {
                return i;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        public final View getFocusableViewAfter(int i, int i2) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
            View view = null;
            if (i2 != -1) {
                int size = this.mViews.size() - 1;
                while (size >= 0) {
                    View view2 = (View) this.mViews.get(size);
                    if ((staggeredGridLayoutManager.mReverseLayout && RecyclerView.LayoutManager.getPosition(view2) >= i) || ((!staggeredGridLayoutManager.mReverseLayout && RecyclerView.LayoutManager.getPosition(view2) <= i) || !view2.hasFocusable())) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.mViews.size();
                int i3 = 0;
                while (i3 < size2) {
                    View view3 = (View) this.mViews.get(i3);
                    if ((staggeredGridLayoutManager.mReverseLayout && RecyclerView.LayoutManager.getPosition(view3) <= i) || ((!staggeredGridLayoutManager.mReverseLayout && RecyclerView.LayoutManager.getPosition(view3) >= i) || !view3.hasFocusable())) {
                        break;
                    }
                    i3++;
                    view = view3;
                }
            }
            return view;
        }

        public final int getStartLine(int i) {
            int i2 = this.mCachedStart;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.mViews.size() == 0) {
                return i;
            }
            View view = (View) this.mViews.get(0);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            this.mCachedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
            layoutParams.getClass();
            return this.mCachedStart;
        }
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [androidx.recyclerview.widget.StaggeredGridLayoutManager$1] */
    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mSpanCount = -1;
        this.mReverseLayout = false;
        LazySpanLookup lazySpanLookup = new LazySpanLookup();
        this.mLazySpanLookup = lazySpanLookup;
        this.mGapStrategy = 2;
        this.mTmpRect = new Rect();
        this.mAnchorInfo = new AnchorInfo();
        this.mSmoothScrollbarEnabled = true;
        this.mCheckForGapsRunnable = new Runnable() { // from class: androidx.recyclerview.widget.StaggeredGridLayoutManager.1
            @Override // java.lang.Runnable
            public final void run() {
                StaggeredGridLayoutManager.this.checkForGaps();
            }
        };
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2);
        int i3 = properties.orientation;
        if (i3 != 0 && i3 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i3 != this.mOrientation) {
            this.mOrientation = i3;
            OrientationHelper$1 orientationHelper$1 = this.mPrimaryOrientation;
            this.mPrimaryOrientation = this.mSecondaryOrientation;
            this.mSecondaryOrientation = orientationHelper$1;
            requestLayout();
        }
        int i4 = properties.spanCount;
        assertNotInLayoutOrScroll(null);
        if (i4 != this.mSpanCount) {
            lazySpanLookup.clear();
            requestLayout();
            this.mSpanCount = i4;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new Span[this.mSpanCount];
            for (int i5 = 0; i5 < this.mSpanCount; i5++) {
                this.mSpans[i5] = new Span(i5);
            }
            requestLayout();
        }
        boolean z = properties.reverseLayout;
        assertNotInLayoutOrScroll(null);
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.mReverseLayout != z) {
            savedState.mReverseLayout = z;
        }
        this.mReverseLayout = z;
        requestLayout();
        LayoutState layoutState = new LayoutState();
        layoutState.mRecycle = true;
        layoutState.mStartLine = 0;
        layoutState.mEndLine = 0;
        this.mLayoutState = layoutState;
        this.mPrimaryOrientation = OrientationHelper$1.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper$1.createOrientationHelper(this, 1 - this.mOrientation);
    }

    public static int updateSpecWithExtra(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public final int calculateScrollDirectionForPosition(int i) {
        if (getChildCount() == 0) {
            return this.mShouldReverseLayout ? 1 : -1;
        }
        return (i < getFirstChildPosition()) != this.mShouldReverseLayout ? -1 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    public final boolean checkForGaps() {
        int firstChildPosition;
        if (getChildCount() != 0 && this.mGapStrategy != 0 && this.mIsAttachedToWindow) {
            if (this.mShouldReverseLayout) {
                firstChildPosition = getLastChildPosition();
                getFirstChildPosition();
            } else {
                firstChildPosition = getFirstChildPosition();
                getLastChildPosition();
            }
            LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
            if (firstChildPosition == 0 && hasGapsToFix() != null) {
                lazySpanLookup.clear();
                this.mRequestedSimpleAnimations = true;
                requestLayout();
                return true;
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.State state, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        LayoutState layoutState;
        int endLine;
        int i3;
        if (this.mOrientation != 0) {
            i = i2;
        }
        if (getChildCount() == 0 || i == 0) {
            return;
        }
        prepareLayoutStateForDelta(i, state);
        int[] iArr = this.mPrefetchDistances;
        if (iArr == null || iArr.length < this.mSpanCount) {
            this.mPrefetchDistances = new int[this.mSpanCount];
        }
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = this.mSpanCount;
            layoutState = this.mLayoutState;
            if (i4 >= i6) {
                break;
            }
            if (layoutState.mItemDirection == -1) {
                endLine = layoutState.mStartLine;
                i3 = this.mSpans[i4].getStartLine(endLine);
            } else {
                endLine = this.mSpans[i4].getEndLine(layoutState.mEndLine);
                i3 = layoutState.mEndLine;
            }
            int i7 = endLine - i3;
            if (i7 >= 0) {
                this.mPrefetchDistances[i5] = i7;
                i5++;
            }
            i4++;
        }
        Arrays.sort(this.mPrefetchDistances, 0, i5);
        for (int i8 = 0; i8 < i5; i8++) {
            int i9 = layoutState.mCurrentPosition;
            if (i9 < 0 || i9 >= state.getItemCount()) {
                return;
            }
            layoutPrefetchRegistryImpl.addPosition(layoutState.mCurrentPosition, this.mPrefetchDistances[i8]);
            layoutState.mCurrentPosition += layoutState.mItemDirection;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent$1(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset$1(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange$1(state);
    }

    public final int computeScrollExtent$1(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        OrientationHelper$1 orientationHelper$1 = this.mPrimaryOrientation;
        boolean z = !this.mSmoothScrollbarEnabled;
        return ScrollbarHelper.computeScrollExtent(state, orientationHelper$1, findFirstVisibleItemClosestToStart(z), findFirstVisibleItemClosestToEnd(z), this, this.mSmoothScrollbarEnabled);
    }

    public final int computeScrollOffset$1(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        OrientationHelper$1 orientationHelper$1 = this.mPrimaryOrientation;
        boolean z = !this.mSmoothScrollbarEnabled;
        return ScrollbarHelper.computeScrollOffset(state, orientationHelper$1, findFirstVisibleItemClosestToStart(z), findFirstVisibleItemClosestToEnd(z), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    public final int computeScrollRange$1(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        OrientationHelper$1 orientationHelper$1 = this.mPrimaryOrientation;
        boolean z = !this.mSmoothScrollbarEnabled;
        return ScrollbarHelper.computeScrollRange(state, orientationHelper$1, findFirstVisibleItemClosestToStart(z), findFirstVisibleItemClosestToEnd(z), this, this.mSmoothScrollbarEnabled);
    }

    @Override // androidx.recyclerview.widget.RecyclerView$SmoothScroller$ScrollVectorProvider
    public final PointF computeScrollVectorForPosition(int i) {
        int calculateScrollDirectionForPosition = calculateScrollDirectionForPosition(i);
        PointF pointF = new PointF();
        if (calculateScrollDirectionForPosition == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            pointF.x = calculateScrollDirectionForPosition;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = calculateScrollDirectionForPosition;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent$1(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset$1(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange$1(state);
    }

    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean, int] */
    public final int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state) {
        Span span;
        ?? r6;
        int i;
        int startLine;
        int decoratedMeasurement;
        int startAfterPadding;
        int decoratedMeasurement2;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        int i7 = 1;
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        LayoutState layoutState2 = this.mLayoutState;
        int i8 = layoutState2.mInfinite ? layoutState.mLayoutDirection == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : layoutState.mLayoutDirection == 1 ? layoutState.mEndLine + layoutState.mAvailable : layoutState.mStartLine - layoutState.mAvailable;
        int i9 = layoutState.mLayoutDirection;
        for (int i10 = 0; i10 < this.mSpanCount; i10++) {
            if (!this.mSpans[i10].mViews.isEmpty()) {
                updateRemainingSpans(this.mSpans[i10], i9, i8);
            }
        }
        int endAfterPadding = this.mShouldReverseLayout ? this.mPrimaryOrientation.getEndAfterPadding() : this.mPrimaryOrientation.getStartAfterPadding();
        boolean z = false;
        while (true) {
            int i11 = layoutState.mCurrentPosition;
            if (((i11 < 0 || i11 >= state.getItemCount()) ? i6 : i7) == 0 || (!layoutState2.mInfinite && this.mRemainingSpans.isEmpty())) {
                break;
            }
            View view = recycler.tryGetViewHolderForPositionByDeadline(Long.MAX_VALUE, layoutState.mCurrentPosition).itemView;
            layoutState.mCurrentPosition += layoutState.mItemDirection;
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            int layoutPosition = layoutParams.mViewHolder.getLayoutPosition();
            LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
            int[] iArr = lazySpanLookup.mData;
            int i12 = (iArr == null || layoutPosition >= iArr.length) ? -1 : iArr[layoutPosition];
            if (i12 == -1) {
                if (preferLastSpan(layoutState.mLayoutDirection)) {
                    i5 = this.mSpanCount - i7;
                    i4 = -1;
                    i3 = -1;
                } else {
                    i3 = i7;
                    i4 = this.mSpanCount;
                    i5 = i6;
                }
                Span span2 = null;
                if (layoutState.mLayoutDirection == i7) {
                    int startAfterPadding2 = this.mPrimaryOrientation.getStartAfterPadding();
                    int i13 = Integer.MAX_VALUE;
                    while (i5 != i4) {
                        Span span3 = this.mSpans[i5];
                        int endLine = span3.getEndLine(startAfterPadding2);
                        if (endLine < i13) {
                            i13 = endLine;
                            span2 = span3;
                        }
                        i5 += i3;
                    }
                } else {
                    int endAfterPadding2 = this.mPrimaryOrientation.getEndAfterPadding();
                    int i14 = Integer.MIN_VALUE;
                    while (i5 != i4) {
                        Span span4 = this.mSpans[i5];
                        int startLine2 = span4.getStartLine(endAfterPadding2);
                        if (startLine2 > i14) {
                            span2 = span4;
                            i14 = startLine2;
                        }
                        i5 += i3;
                    }
                }
                span = span2;
                lazySpanLookup.ensureSize(layoutPosition);
                lazySpanLookup.mData[layoutPosition] = span.mIndex;
            } else {
                span = this.mSpans[i12];
            }
            layoutParams.mSpan = span;
            if (layoutState.mLayoutDirection == 1) {
                r6 = 0;
                addViewInt(-1, view, false);
            } else {
                r6 = 0;
                addViewInt(0, view, false);
            }
            if (this.mOrientation == 1) {
                i = 1;
                measureChildWithDecorationsAndMargin$1(view, RecyclerView.LayoutManager.getChildMeasureSpec(r6, this.mSizePerSpan, this.mWidthMode, r6, ((ViewGroup.MarginLayoutParams) layoutParams).width), RecyclerView.LayoutManager.getChildMeasureSpec(true, this.mHeight, this.mHeightMode, getPaddingBottom() + getPaddingTop(), ((ViewGroup.MarginLayoutParams) layoutParams).height));
            } else {
                i = 1;
                measureChildWithDecorationsAndMargin$1(view, RecyclerView.LayoutManager.getChildMeasureSpec(true, this.mWidth, this.mWidthMode, getPaddingRight() + getPaddingLeft(), ((ViewGroup.MarginLayoutParams) layoutParams).width), RecyclerView.LayoutManager.getChildMeasureSpec(false, this.mSizePerSpan, this.mHeightMode, 0, ((ViewGroup.MarginLayoutParams) layoutParams).height));
            }
            if (layoutState.mLayoutDirection == i) {
                decoratedMeasurement = span.getEndLine(endAfterPadding);
                startLine = this.mPrimaryOrientation.getDecoratedMeasurement(view) + decoratedMeasurement;
            } else {
                startLine = span.getStartLine(endAfterPadding);
                decoratedMeasurement = startLine - this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
            if (layoutState.mLayoutDirection == 1) {
                Span span5 = layoutParams.mSpan;
                span5.getClass();
                LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                layoutParams2.mSpan = span5;
                span5.mViews.add(view);
                span5.mCachedEnd = Integer.MIN_VALUE;
                if (span5.mViews.size() == 1) {
                    span5.mCachedStart = Integer.MIN_VALUE;
                }
                if (layoutParams2.mViewHolder.isRemoved() || layoutParams2.mViewHolder.isUpdated()) {
                    span5.mDeletedSize = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view) + span5.mDeletedSize;
                }
            } else {
                Span span6 = layoutParams.mSpan;
                span6.getClass();
                LayoutParams layoutParams3 = (LayoutParams) view.getLayoutParams();
                layoutParams3.mSpan = span6;
                span6.mViews.add(0, view);
                span6.mCachedStart = Integer.MIN_VALUE;
                if (span6.mViews.size() == 1) {
                    span6.mCachedEnd = Integer.MIN_VALUE;
                }
                if (layoutParams3.mViewHolder.isRemoved() || layoutParams3.mViewHolder.isUpdated()) {
                    span6.mDeletedSize = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view) + span6.mDeletedSize;
                }
            }
            if (isLayoutRTL() && this.mOrientation == 1) {
                decoratedMeasurement2 = this.mSecondaryOrientation.getEndAfterPadding() - (((this.mSpanCount - 1) - span.mIndex) * this.mSizePerSpan);
                startAfterPadding = decoratedMeasurement2 - this.mSecondaryOrientation.getDecoratedMeasurement(view);
            } else {
                startAfterPadding = this.mSecondaryOrientation.getStartAfterPadding() + (span.mIndex * this.mSizePerSpan);
                decoratedMeasurement2 = this.mSecondaryOrientation.getDecoratedMeasurement(view) + startAfterPadding;
            }
            if (this.mOrientation == 1) {
                RecyclerView.LayoutManager.layoutDecoratedWithMargins(view, startAfterPadding, decoratedMeasurement, decoratedMeasurement2, startLine);
            } else {
                RecyclerView.LayoutManager.layoutDecoratedWithMargins(view, decoratedMeasurement, startAfterPadding, startLine, decoratedMeasurement2);
            }
            updateRemainingSpans(span, layoutState2.mLayoutDirection, i8);
            recycle(recycler, layoutState2);
            if (layoutState2.mStopInFocusable && view.hasFocusable()) {
                i2 = 0;
                this.mRemainingSpans.set(span.mIndex, false);
            } else {
                i2 = 0;
            }
            i6 = i2;
            i7 = 1;
            z = true;
        }
        int i15 = i6;
        if (!z) {
            recycle(recycler, layoutState2);
        }
        int startAfterPadding3 = layoutState2.mLayoutDirection == -1 ? this.mPrimaryOrientation.getStartAfterPadding() - getMinStart(this.mPrimaryOrientation.getStartAfterPadding()) : getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding()) - this.mPrimaryOrientation.getEndAfterPadding();
        return startAfterPadding3 > 0 ? Math.min(layoutState.mAvailable, startAfterPadding3) : i15;
    }

    public final View findFirstVisibleItemClosestToEnd(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(childAt);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd <= endAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public final View findFirstVisibleItemClosestToStart(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        int childCount = getChildCount();
        View view = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart >= startAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public final void fixEndGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int maxEnd = getMaxEnd(Integer.MIN_VALUE);
        if (maxEnd != Integer.MIN_VALUE && (endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - maxEnd) > 0) {
            int i = endAfterPadding - (-scrollBy$1(-endAfterPadding, recycler, state));
            if (!z || i <= 0) {
                return;
            }
            this.mPrimaryOrientation.offsetChildren(i);
        }
    }

    public final void fixStartGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int minStart = getMinStart(Integer.MAX_VALUE);
        if (minStart != Integer.MAX_VALUE && (startAfterPadding = minStart - this.mPrimaryOrientation.getStartAfterPadding()) > 0) {
            int scrollBy$1 = startAfterPadding - scrollBy$1(startAfterPadding, recycler, state);
            if (!z || scrollBy$1 <= 0) {
                return;
            }
            this.mPrimaryOrientation.offsetChildren(-scrollBy$1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return this.mOrientation == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return Math.min(this.mSpanCount, state.getItemCount());
        }
        return -1;
    }

    public final int getFirstChildPosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        return RecyclerView.LayoutManager.getPosition(getChildAt(0));
    }

    public final int getLastChildPosition() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return RecyclerView.LayoutManager.getPosition(getChildAt(childCount - 1));
    }

    public final int getMaxEnd(int i) {
        int endLine = this.mSpans[0].getEndLine(i);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int endLine2 = this.mSpans[i2].getEndLine(i);
            if (endLine2 > endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }

    public final int getMinStart(int i) {
        int startLine = this.mSpans[0].getStartLine(i);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int startLine2 = this.mSpans[i2].getStartLine(i);
            if (startLine2 < startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return Math.min(this.mSpanCount, state.getItemCount());
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void handleUpdate(int r10, int r11, int r12) {
        /*
            r9 = this;
            boolean r0 = r9.mShouldReverseLayout
            if (r0 == 0) goto L9
            int r0 = r9.getLastChildPosition()
            goto Ld
        L9:
            int r0 = r9.getFirstChildPosition()
        Ld:
            r1 = 8
            if (r12 != r1) goto L1b
            if (r10 >= r11) goto L17
            int r2 = r11 + 1
        L15:
            r3 = r10
            goto L1e
        L17:
            int r2 = r10 + 1
            r3 = r11
            goto L1e
        L1b:
            int r2 = r10 + r11
            goto L15
        L1e:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r9.mLazySpanLookup
            int[] r5 = r4.mData
            r6 = -1
            if (r5 != 0) goto L27
            goto L97
        L27:
            int r5 = r5.length
            if (r3 < r5) goto L2c
            goto L97
        L2c:
            java.util.List r5 = r4.mFullSpanItems
            if (r5 != 0) goto L32
        L30:
            r5 = r6
            goto L7d
        L32:
            int r5 = r5.size()
            int r5 = r5 + (-1)
        L38:
            if (r5 < 0) goto L4a
            java.util.List r7 = r4.mFullSpanItems
            java.lang.Object r7 = r7.get(r5)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r7 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem) r7
            int r8 = r7.mPosition
            if (r8 != r3) goto L47
            goto L4b
        L47:
            int r5 = r5 + (-1)
            goto L38
        L4a:
            r7 = 0
        L4b:
            if (r7 == 0) goto L52
            java.util.List r5 = r4.mFullSpanItems
            r5.remove(r7)
        L52:
            java.util.List r5 = r4.mFullSpanItems
            int r5 = r5.size()
            r7 = 0
        L59:
            if (r7 >= r5) goto L6b
            java.util.List r8 = r4.mFullSpanItems
            java.lang.Object r8 = r8.get(r7)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem) r8
            int r8 = r8.mPosition
            if (r8 < r3) goto L68
            goto L6c
        L68:
            int r7 = r7 + 1
            goto L59
        L6b:
            r7 = r6
        L6c:
            if (r7 == r6) goto L30
            java.util.List r5 = r4.mFullSpanItems
            java.lang.Object r5 = r5.get(r7)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r5 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem) r5
            java.util.List r8 = r4.mFullSpanItems
            r8.remove(r7)
            int r5 = r5.mPosition
        L7d:
            if (r5 != r6) goto L89
            int[] r5 = r4.mData
            int r7 = r5.length
            java.util.Arrays.fill(r5, r3, r7, r6)
            int[] r5 = r4.mData
            int r5 = r5.length
            goto L97
        L89:
            int r5 = r5 + 1
            int[] r7 = r4.mData
            int r7 = r7.length
            int r5 = java.lang.Math.min(r5, r7)
            int[] r7 = r4.mData
            java.util.Arrays.fill(r7, r3, r5, r6)
        L97:
            r5 = 1
            if (r12 == r5) goto Lab
            r6 = 2
            if (r12 == r6) goto La7
            if (r12 == r1) goto La0
            goto Lae
        La0:
            r4.offsetForRemoval(r10, r5)
            r4.offsetForAddition(r11, r5)
            goto Lae
        La7:
            r4.offsetForRemoval(r10, r11)
            goto Lae
        Lab:
            r4.offsetForAddition(r10, r11)
        Lae:
            if (r2 > r0) goto Lb1
            return
        Lb1:
            boolean r10 = r9.mShouldReverseLayout
            if (r10 == 0) goto Lba
            int r10 = r9.getFirstChildPosition()
            goto Lbe
        Lba:
            int r10 = r9.getLastChildPosition()
        Lbe:
            if (r3 > r10) goto Lc3
            r9.requestLayout()
        Lc3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.handleUpdate(int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00fa A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x002c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View hasGapsToFix() {
        /*
            Method dump skipped, instructions count: 253
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.hasGapsToFix():android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean isAutoMeasureEnabled() {
        return this.mGapStrategy != 0;
    }

    public final boolean isLayoutRTL() {
        return this.mRecyclerView.getLayoutDirection() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean isLayoutReversed() {
        return this.mReverseLayout;
    }

    public final void measureChildWithDecorationsAndMargin$1(View view, int i, int i2) {
        calculateItemDecorationsForChild(this.mTmpRect, view);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        Rect rect = this.mTmpRect;
        int updateSpecWithExtra = updateSpecWithExtra(i, i3 + rect.left, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + rect.right);
        int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        Rect rect2 = this.mTmpRect;
        int updateSpecWithExtra2 = updateSpecWithExtra(i2, i4 + rect2.top, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + rect2.bottom);
        if (shouldMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams)) {
            view.measure(updateSpecWithExtra, updateSpecWithExtra2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void offsetChildrenHorizontal(int i) {
        super.offsetChildrenHorizontal(i);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            Span span = this.mSpans[i2];
            int i3 = span.mCachedStart;
            if (i3 != Integer.MIN_VALUE) {
                span.mCachedStart = i3 + i;
            }
            int i4 = span.mCachedEnd;
            if (i4 != Integer.MIN_VALUE) {
                span.mCachedEnd = i4 + i;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void offsetChildrenVertical(int i) {
        super.offsetChildrenVertical(i);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            Span span = this.mSpans[i2];
            int i3 = span.mCachedStart;
            if (i3 != Integer.MIN_VALUE) {
                span.mCachedStart = i3 + i;
            }
            int i4 = span.mCachedEnd;
            if (i4 != Integer.MIN_VALUE) {
                span.mCachedEnd = i4 + i;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onAdapterChanged(RecyclerView.Adapter adapter) {
        this.mLazySpanLookup.clear();
        for (int i = 0; i < this.mSpanCount; i++) {
            this.mSpans[i].clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onDetachedFromWindow(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.removeCallbacks(this.mCheckForGapsRunnable);
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            this.mSpans[i].clear();
        }
        recyclerView.requestLayout();
    }

    /* JADX WARN: Code restructure failed: missing block: B:112:0x004d, code lost:
    
        if (r8.mOrientation == 1) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0052, code lost:
    
        if (r8.mOrientation == 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x005f, code lost:
    
        if (isLayoutRTL() == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x006c, code lost:
    
        if (isLayoutRTL() == false) goto L46;
     */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View onFocusSearchFailed(android.view.View r9, int r10, androidx.recyclerview.widget.RecyclerView.Recycler r11, androidx.recyclerview.widget.RecyclerView.State r12) {
        /*
            Method dump skipped, instructions count: 348
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.onFocusSearchFailed(android.view.View, int, androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State):android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToStart(false);
            View findFirstVisibleItemClosestToEnd = findFirstVisibleItemClosestToEnd(false);
            if (findFirstVisibleItemClosestToStart == null || findFirstVisibleItemClosestToEnd == null) {
                return;
            }
            int position = RecyclerView.LayoutManager.getPosition(findFirstVisibleItemClosestToStart);
            int position2 = RecyclerView.LayoutManager.getPosition(findFirstVisibleItemClosestToEnd);
            if (position < position2) {
                accessibilityEvent.setFromIndex(position);
                accessibilityEvent.setToIndex(position2);
            } else {
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName("androidx.recyclerview.widget.StaggeredGridLayoutManager");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (this.mOrientation == 0) {
            Span span = layoutParams2.mSpan;
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(false, span == null ? -1 : span.mIndex, 1, -1, -1));
        } else {
            Span span2 = layoutParams2.mSpan;
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(false, -1, -1, span2 == null ? -1 : span2.mIndex, 1));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsAdded(int i, int i2) {
        handleUpdate(i, i2, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsChanged() {
        this.mLazySpanLookup.clear();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsMoved(int i, int i2) {
        handleUpdate(i, i2, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsRemoved(int i, int i2) {
        handleUpdate(i, i2, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        handleUpdate(i, i2, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        onLayoutChildren(recycler, state, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onLayoutCompleted(RecyclerView.State state) {
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.reset();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mPendingSavedState = savedState;
            if (this.mPendingScrollPosition != -1) {
                savedState.mSpanOffsets = null;
                savedState.mSpanOffsetsSize = 0;
                savedState.mAnchorPosition = -1;
                savedState.mVisibleAnchorPosition = -1;
                savedState.mSpanOffsets = null;
                savedState.mSpanOffsetsSize = 0;
                savedState.mSpanLookupSize = 0;
                savedState.mSpanLookup = null;
                savedState.mFullSpanItems = null;
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final Parcelable onSaveInstanceState() {
        int startLine;
        int startAfterPadding;
        int[] iArr;
        if (this.mPendingSavedState != null) {
            SavedState savedState = this.mPendingSavedState;
            SavedState savedState2 = new SavedState();
            savedState2.mSpanOffsetsSize = savedState.mSpanOffsetsSize;
            savedState2.mAnchorPosition = savedState.mAnchorPosition;
            savedState2.mVisibleAnchorPosition = savedState.mVisibleAnchorPosition;
            savedState2.mSpanOffsets = savedState.mSpanOffsets;
            savedState2.mSpanLookupSize = savedState.mSpanLookupSize;
            savedState2.mSpanLookup = savedState.mSpanLookup;
            savedState2.mReverseLayout = savedState.mReverseLayout;
            savedState2.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
            savedState2.mLastLayoutRTL = savedState.mLastLayoutRTL;
            savedState2.mFullSpanItems = savedState.mFullSpanItems;
            return savedState2;
        }
        SavedState savedState3 = new SavedState();
        savedState3.mReverseLayout = this.mReverseLayout;
        savedState3.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
        savedState3.mLastLayoutRTL = this.mLastLayoutRTL;
        LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.mData) == null) {
            savedState3.mSpanLookupSize = 0;
        } else {
            savedState3.mSpanLookup = iArr;
            savedState3.mSpanLookupSize = iArr.length;
            savedState3.mFullSpanItems = lazySpanLookup.mFullSpanItems;
        }
        if (getChildCount() > 0) {
            savedState3.mAnchorPosition = this.mLastLayoutFromEnd ? getLastChildPosition() : getFirstChildPosition();
            View findFirstVisibleItemClosestToEnd = this.mShouldReverseLayout ? findFirstVisibleItemClosestToEnd(true) : findFirstVisibleItemClosestToStart(true);
            savedState3.mVisibleAnchorPosition = findFirstVisibleItemClosestToEnd != null ? RecyclerView.LayoutManager.getPosition(findFirstVisibleItemClosestToEnd) : -1;
            int i = this.mSpanCount;
            savedState3.mSpanOffsetsSize = i;
            savedState3.mSpanOffsets = new int[i];
            for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                if (this.mLastLayoutFromEnd) {
                    startLine = this.mSpans[i2].getEndLine(Integer.MIN_VALUE);
                    if (startLine != Integer.MIN_VALUE) {
                        startAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
                        startLine -= startAfterPadding;
                        savedState3.mSpanOffsets[i2] = startLine;
                    } else {
                        savedState3.mSpanOffsets[i2] = startLine;
                    }
                } else {
                    startLine = this.mSpans[i2].getStartLine(Integer.MIN_VALUE);
                    if (startLine != Integer.MIN_VALUE) {
                        startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
                        startLine -= startAfterPadding;
                        savedState3.mSpanOffsets[i2] = startLine;
                    } else {
                        savedState3.mSpanOffsets[i2] = startLine;
                    }
                }
            }
        } else {
            savedState3.mAnchorPosition = -1;
            savedState3.mVisibleAnchorPosition = -1;
            savedState3.mSpanOffsetsSize = 0;
        }
        return savedState3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onScrollStateChanged(int i) {
        if (i == 0) {
            checkForGaps();
        }
    }

    public final boolean preferLastSpan(int i) {
        if (this.mOrientation == 0) {
            return (i == -1) != this.mShouldReverseLayout;
        }
        return ((i == -1) == this.mShouldReverseLayout) == isLayoutRTL();
    }

    public final void prepareLayoutStateForDelta(int i, RecyclerView.State state) {
        int firstChildPosition;
        int i2;
        if (i > 0) {
            firstChildPosition = getLastChildPosition();
            i2 = 1;
        } else {
            firstChildPosition = getFirstChildPosition();
            i2 = -1;
        }
        LayoutState layoutState = this.mLayoutState;
        layoutState.mRecycle = true;
        updateLayoutState(firstChildPosition, state);
        setLayoutStateDirection(i2);
        layoutState.mCurrentPosition = firstChildPosition + layoutState.mItemDirection;
        layoutState.mAvailable = Math.abs(i);
    }

    public final void recycle(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (!layoutState.mRecycle || layoutState.mInfinite) {
            return;
        }
        if (layoutState.mAvailable == 0) {
            if (layoutState.mLayoutDirection == -1) {
                recycleFromEnd(layoutState.mEndLine, recycler);
                return;
            } else {
                recycleFromStart(layoutState.mStartLine, recycler);
                return;
            }
        }
        int i = 1;
        if (layoutState.mLayoutDirection == -1) {
            int i2 = layoutState.mStartLine;
            int startLine = this.mSpans[0].getStartLine(i2);
            while (i < this.mSpanCount) {
                int startLine2 = this.mSpans[i].getStartLine(i2);
                if (startLine2 > startLine) {
                    startLine = startLine2;
                }
                i++;
            }
            int i3 = i2 - startLine;
            recycleFromEnd(i3 < 0 ? layoutState.mEndLine : layoutState.mEndLine - Math.min(i3, layoutState.mAvailable), recycler);
            return;
        }
        int i4 = layoutState.mEndLine;
        int endLine = this.mSpans[0].getEndLine(i4);
        while (i < this.mSpanCount) {
            int endLine2 = this.mSpans[i].getEndLine(i4);
            if (endLine2 < endLine) {
                endLine = endLine2;
            }
            i++;
        }
        int i5 = endLine - layoutState.mEndLine;
        recycleFromStart(i5 < 0 ? layoutState.mStartLine : Math.min(i5, layoutState.mAvailable) + layoutState.mStartLine, recycler);
    }

    public final void recycleFromEnd(int i, RecyclerView.Recycler recycler) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (this.mPrimaryOrientation.getDecoratedStart(childAt) < i || this.mPrimaryOrientation.getTransformedStartWithDecoration(childAt) < i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            layoutParams.getClass();
            if (layoutParams.mSpan.mViews.size() == 1) {
                return;
            }
            Span span = layoutParams.mSpan;
            int size = span.mViews.size();
            View view = (View) span.mViews.remove(size - 1);
            LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
            layoutParams2.mSpan = null;
            if (layoutParams2.mViewHolder.isRemoved() || layoutParams2.mViewHolder.isUpdated()) {
                span.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
            if (size == 1) {
                span.mCachedStart = Integer.MIN_VALUE;
            }
            span.mCachedEnd = Integer.MIN_VALUE;
            removeAndRecycleView(childAt, recycler);
        }
    }

    public final void recycleFromStart(int i, RecyclerView.Recycler recycler) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) > i || this.mPrimaryOrientation.getTransformedEndWithDecoration(childAt) > i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            layoutParams.getClass();
            if (layoutParams.mSpan.mViews.size() == 1) {
                return;
            }
            Span span = layoutParams.mSpan;
            View view = (View) span.mViews.remove(0);
            LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
            layoutParams2.mSpan = null;
            if (span.mViews.size() == 0) {
                span.mCachedEnd = Integer.MIN_VALUE;
            }
            if (layoutParams2.mViewHolder.isRemoved() || layoutParams2.mViewHolder.isUpdated()) {
                span.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
            span.mCachedStart = Integer.MIN_VALUE;
            removeAndRecycleView(childAt, recycler);
        }
    }

    public final void resolveShouldLayoutReverse$1() {
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
        prepareLayoutStateForDelta(i, state);
        LayoutState layoutState = this.mLayoutState;
        int fill = fill(recycler, layoutState, state);
        if (layoutState.mAvailable >= fill) {
            i = i < 0 ? -fill : fill;
        }
        this.mPrimaryOrientation.offsetChildren(-i);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        layoutState.mAvailable = 0;
        recycle(recycler, layoutState);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy$1(i, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void scrollToPosition(int i) {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.mAnchorPosition != i) {
            savedState.mSpanOffsets = null;
            savedState.mSpanOffsetsSize = 0;
            savedState.mAnchorPosition = -1;
            savedState.mVisibleAnchorPosition = -1;
        }
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy$1(i, recycler, state);
    }

    public final void setLayoutStateDirection(int i) {
        LayoutState layoutState = this.mLayoutState;
        layoutState.mLayoutDirection = i;
        layoutState.mItemDirection = this.mShouldReverseLayout != (i == -1) ? -1 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void setMeasuredDimension(Rect rect, int i, int i2) {
        int chooseSize;
        int chooseSize2;
        int i3 = this.mSpanCount;
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.mOrientation == 1) {
            int height = rect.height() + paddingBottom;
            RecyclerView recyclerView = this.mRecyclerView;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            chooseSize2 = RecyclerView.LayoutManager.chooseSize(i2, height, recyclerView.getMinimumHeight());
            chooseSize = RecyclerView.LayoutManager.chooseSize(i, (this.mSizePerSpan * i3) + paddingRight, this.mRecyclerView.getMinimumWidth());
        } else {
            int width = rect.width() + paddingRight;
            RecyclerView recyclerView2 = this.mRecyclerView;
            WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
            chooseSize = RecyclerView.LayoutManager.chooseSize(i, width, recyclerView2.getMinimumWidth());
            chooseSize2 = RecyclerView.LayoutManager.chooseSize(i2, (this.mSizePerSpan * i3) + paddingBottom, this.mRecyclerView.getMinimumHeight());
        }
        this.mRecyclerView.setMeasuredDimension(chooseSize, chooseSize2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void smoothScrollToPosition(RecyclerView recyclerView, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.mTargetPosition = i;
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }

    public final void updateLayoutState(int i, RecyclerView.State state) {
        int i2;
        int i3;
        int i4;
        LayoutState layoutState = this.mLayoutState;
        boolean z = false;
        layoutState.mAvailable = 0;
        layoutState.mCurrentPosition = i;
        if (!isSmoothScrolling() || (i4 = state.mTargetPosition) == -1) {
            i2 = 0;
            i3 = 0;
        } else {
            if (this.mShouldReverseLayout == (i4 < i)) {
                i2 = this.mPrimaryOrientation.getTotalSpace();
                i3 = 0;
            } else {
                i3 = this.mPrimaryOrientation.getTotalSpace();
                i2 = 0;
            }
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || !recyclerView.mClipToPadding) {
            layoutState.mEndLine = this.mPrimaryOrientation.getEnd() + i2;
            layoutState.mStartLine = -i3;
        } else {
            layoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - i3;
            layoutState.mEndLine = this.mPrimaryOrientation.getEndAfterPadding() + i2;
        }
        layoutState.mStopInFocusable = false;
        layoutState.mRecycle = true;
        if (this.mPrimaryOrientation.getMode() == 0 && this.mPrimaryOrientation.getEnd() == 0) {
            z = true;
        }
        layoutState.mInfinite = z;
    }

    public final void updateRemainingSpans(Span span, int i, int i2) {
        int i3 = span.mDeletedSize;
        int i4 = span.mIndex;
        if (i != -1) {
            int i5 = span.mCachedEnd;
            if (i5 == Integer.MIN_VALUE) {
                span.calculateCachedEnd();
                i5 = span.mCachedEnd;
            }
            if (i5 - i3 >= i2) {
                this.mRemainingSpans.set(i4, false);
                return;
            }
            return;
        }
        int i6 = span.mCachedStart;
        if (i6 == Integer.MIN_VALUE) {
            View view = (View) span.mViews.get(0);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            span.mCachedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
            layoutParams.getClass();
            i6 = span.mCachedStart;
        }
        if (i6 + i3 <= i2) {
            this.mRemainingSpans.set(i4, false);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:258:0x0410, code lost:
    
        if (checkForGaps() != false) goto L250;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onLayoutChildren(androidx.recyclerview.widget.RecyclerView.Recycler r17, androidx.recyclerview.widget.RecyclerView.State r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 1070
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.onLayoutChildren(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, boolean):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }
}
