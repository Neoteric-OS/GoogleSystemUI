package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.GridView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.leanback.widget.WindowAlignment;
import androidx.recyclerview.widget.GapWorker;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper$1;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GridLayoutManager extends RecyclerView.LayoutManager {
    public static final Rect sTempRect = null;
    public static final int[] sTwoInts;
    public final ViewsStateBundle mChildrenStates;
    public GridLinearSmoothScroller mCurrentSmoothScroller;
    public int mFixedRowSizeSecondary;
    public int mFlag;
    public int mFocusPosition;
    public int mFocusPositionOffset;
    public Grid mGrid;
    public final ItemAlignment mItemAlignment;
    public int mNumRows;
    public final int mNumRowsRequested;
    public final OrientationHelper$1 mOrientationHelper;
    public final SparseIntArray mPositionToRowInPostLayout;
    public RecyclerView.Recycler mRecycler;
    public final AnonymousClass1 mRequestLayoutRunnable;
    public int mSaveContextLevel;
    public int mScrollOffsetSecondary;
    public RecyclerView.State mState;
    public final WindowAlignment mWindowAlignment;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class GridLinearSmoothScroller extends LinearSmoothScroller {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LayoutParams extends RecyclerView.LayoutParams {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR = new AnonymousClass1();
        public Bundle mChildStates;
        public int mIndex;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: androidx.leanback.widget.GridLayoutManager$SavedState$1, reason: invalid class name */
        public final class AnonymousClass1 implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                SavedState savedState = new SavedState();
                savedState.mChildStates = Bundle.EMPTY;
                savedState.mIndex = parcel.readInt();
                savedState.mChildStates = parcel.readBundle(GridLayoutManager.class.getClassLoader());
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
            parcel.writeInt(this.mIndex);
            parcel.writeBundle(this.mChildStates);
        }
    }

    static {
        new Rect();
        sTwoInts = new int[2];
    }

    public GridLayoutManager() {
        new OrientationHelper$1(this, 0);
        this.mPositionToRowInPostLayout = new SparseIntArray();
        this.mFlag = 221696;
        this.mFocusPosition = -1;
        this.mFocusPositionOffset = 0;
        this.mNumRowsRequested = 1;
        this.mWindowAlignment = new WindowAlignment();
        new ItemAlignment();
        this.mChildrenStates = new ViewsStateBundle();
        new Runnable() { // from class: androidx.leanback.widget.GridLayoutManager.1
            @Override // java.lang.Runnable
            public final void run() {
                GridLayoutManager.this.requestLayout();
            }
        };
        if (this.mItemPrefetchEnabled) {
            this.mItemPrefetchEnabled = false;
            this.mPrefetchMaxCountObserved = 0;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.mRecycler.updateViewCacheSize();
            }
        }
    }

    public static int getAdapterPositionByView(View view) {
        LayoutParams layoutParams;
        if (view == null || (layoutParams = (LayoutParams) view.getLayoutParams()) == null || layoutParams.mViewHolder.isRemoved()) {
            return -1;
        }
        return layoutParams.mViewHolder.getAbsoluteAdapterPosition();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollHorizontally() {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean canScrollVertically() {
        return this.mNumRows > 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.State state, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        try {
            saveContext(null, state);
            if (getChildCount() != 0 && i != 0) {
                this.mGrid.collectAdjacentPrefetchPositions(0, i, layoutPrefetchRegistryImpl);
            }
        } finally {
            leaveContext();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void collectInitialPrefetchPositions(int i, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        throw null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int getDecoratedBottom(View view) {
        int decoratedBottom = super.getDecoratedBottom(view);
        ((LayoutParams) view.getLayoutParams()).getClass();
        return decoratedBottom + 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void getDecoratedBoundsWithMargins(Rect rect, View view) {
        super.getDecoratedBoundsWithMargins(rect, view);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i = rect.left;
        layoutParams.getClass();
        rect.left = i + 0;
        rect.top += 0;
        rect.right += 0;
        rect.bottom += 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int getDecoratedLeft(View view) {
        int decoratedLeft = super.getDecoratedLeft(view);
        ((LayoutParams) view.getLayoutParams()).getClass();
        return decoratedLeft + 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int getDecoratedRight(View view) {
        int decoratedRight = super.getDecoratedRight(view);
        ((LayoutParams) view.getLayoutParams()).getClass();
        return decoratedRight + 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int getDecoratedTop(View view) {
        int decoratedTop = super.getDecoratedTop(view);
        ((LayoutParams) view.getLayoutParams()).getClass();
        return decoratedTop + 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.getRowCountForAccessibility(recycler, state);
    }

    public final void leaveContext() {
        int i = this.mSaveContextLevel - 1;
        this.mSaveContextLevel = i;
        if (i == 0) {
            this.mState = null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onAdapterChanged(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            this.mFlag &= -1025;
            this.mFocusPosition = -1;
            this.mFocusPositionOffset = 0;
            this.mChildrenStates.getClass();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean onAddFocusables(RecyclerView recyclerView, ArrayList arrayList, int i, int i2) {
        if ((this.mFlag & 32768) != 0) {
            return true;
        }
        if (recyclerView.hasFocus()) {
            if (i != 17) {
            }
            recyclerView.findFocus();
            int adapterPositionByView = getAdapterPositionByView(getChildAt(-1));
            View findViewByPosition = adapterPositionByView == -1 ? null : findViewByPosition(adapterPositionByView);
            if (findViewByPosition != null) {
                findViewByPosition.addFocusables(arrayList, i, i2);
            }
            return true;
        }
        int size = arrayList.size();
        View findViewByPosition2 = findViewByPosition(this.mFocusPosition);
        if (findViewByPosition2 != null) {
            findViewByPosition2.addFocusables(arrayList, i, i2);
        }
        if (arrayList.size() == size && recyclerView.isFocusable()) {
            arrayList.add(recyclerView);
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        saveContext(recycler, state);
        int itemCount = state.getItemCount();
        int i = this.mFlag;
        boolean z = (262144 & i) != 0;
        if ((i & 2048) == 0) {
            accessibilityNodeInfoCompat.addAction(z ? AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT : AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT);
            accessibilityNodeInfoCompat.setScrollable(true);
        } else if (itemCount > 1) {
            throw null;
        }
        if ((this.mFlag & 4096) == 0) {
            accessibilityNodeInfoCompat.addAction(z ? AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT : AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT);
            accessibilityNodeInfoCompat.setScrollable(true);
        } else if (itemCount > 1) {
            throw null;
        }
        accessibilityNodeInfoCompat.mInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(getRowCountForAccessibility(recycler, state), super.getColumnCountForAccessibility(recycler, state), false, 0));
        accessibilityNodeInfoCompat.setClassName(GridView.class.getName());
        leaveContext();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        view.getLayoutParams();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final View onInterceptFocusSearch(View view, int i) {
        View findNextFocus;
        if ((this.mFlag & 32768) != 0) {
            return view;
        }
        FocusFinder focusFinder = FocusFinder.getInstance();
        if (i == 2 || i == 1) {
            if (canScrollVertically()) {
                focusFinder.findNextFocus(null, view, i == 2 ? 130 : 33);
            }
            findNextFocus = focusFinder.findNextFocus(null, view, (this.mRecyclerView.getLayoutDirection() == 1) ^ (i == 2) ? 66 : 17);
        } else {
            findNextFocus = focusFinder.findNextFocus(null, view, i);
        }
        findNextFocus.getClass();
        return findNextFocus;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsAdded(int i, int i2) {
        int i3 = this.mFocusPosition;
        this.mChildrenStates.getClass();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsChanged() {
        this.mFocusPositionOffset = 0;
        this.mChildrenStates.getClass();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsMoved(int i, int i2) {
        int i3;
        int i4 = this.mFocusPosition;
        if (i4 != -1 && (i3 = this.mFocusPositionOffset) != Integer.MIN_VALUE) {
            int i5 = i4 + i3;
            if (i <= i5 && i5 < i + 1) {
                this.mFocusPositionOffset = (i2 - i) + i3;
            } else if (i < i5 && i2 > i5 - 1) {
                this.mFocusPositionOffset = i3 - 1;
            } else if (i > i5 && i2 < i5) {
                this.mFocusPositionOffset = i3 + 1;
            }
        }
        this.mChildrenStates.getClass();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsRemoved(int i, int i2) {
        int i3 = this.mFocusPosition;
        this.mChildrenStates.getClass();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onItemsUpdated(int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            this.mChildrenStates.getClass();
            i++;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        if (this.mNumRows != 0 && state.getItemCount() >= 0) {
            if ((this.mFlag & 64) != 0 && getChildCount() > 0) {
                this.mFlag |= 128;
                return;
            }
            int i2 = this.mFlag;
            if ((i2 & 512) == 0) {
                this.mFlag = i2 & (-1025);
                removeAndRecycleAllViews(recycler);
                return;
            }
            this.mFlag = (i2 & (-4)) | 1;
            saveContext(recycler, state);
            if (state.mInPreLayout) {
                if (getChildCount() > 0) {
                    LayoutParams layoutParams = (LayoutParams) getChildAt(0).getLayoutParams();
                    int i3 = this.mGrid.mFirstVisibleIndex;
                    layoutParams.mViewHolder.getLayoutPosition();
                }
                getChildCount();
                this.mFlag &= -4;
                leaveContext();
                return;
            }
            if (state.mRunPredictiveAnimations) {
                this.mPositionToRowInPostLayout.clear();
                if (getChildCount() > 0) {
                    getChildAt(0);
                    throw null;
                }
            }
            int i4 = this.mFocusPosition;
            if (i4 != -1 && (i = this.mFocusPositionOffset) != Integer.MIN_VALUE) {
                this.mFocusPosition = i4 + i;
            }
            this.mFocusPositionOffset = 0;
            findViewByPosition(this.mFocusPosition);
            throw null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        int i3;
        saveContext(recycler, state);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int i4 = this.mNumRowsRequested;
        if (mode != Integer.MIN_VALUE) {
            if (mode == 0) {
                int i5 = size2 - paddingBottom;
                this.mFixedRowSizeSecondary = i5;
                if (i4 == 0) {
                    i4 = 1;
                }
                this.mNumRows = i4;
                size2 = (i5 * i4) + paddingBottom;
                this.mRecyclerView.setMeasuredDimension(size, size2);
                leaveContext();
            }
            if (mode != 1073741824) {
                throw new IllegalStateException("wrong spec");
            }
        }
        if (i4 == 0) {
            this.mNumRows = 1;
            this.mFixedRowSizeSecondary = size2 - paddingBottom;
        } else if (i4 == 0) {
            this.mFixedRowSizeSecondary = 0;
            this.mNumRows = size2 / 0;
        } else {
            this.mNumRows = i4;
            this.mFixedRowSizeSecondary = (size2 - paddingBottom) / i4;
        }
        if (mode == Integer.MIN_VALUE && (i3 = (this.mFixedRowSizeSecondary * this.mNumRows) + paddingBottom) < size2) {
            size2 = i3;
        }
        this.mRecyclerView.setMeasuredDimension(size, size2);
        leaveContext();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean onRequestChildFocus(RecyclerView recyclerView, View view, View view2) {
        if ((this.mFlag & 32768) != 0 || getAdapterPositionByView(view) == -1) {
            return true;
        }
        int i = this.mFlag;
        if ((i & 35) == 0 && (i & 64) == 0) {
            int adapterPositionByView = getAdapterPositionByView(view);
            if (view != null && view2 != null) {
                ((LayoutParams) view.getLayoutParams()).getClass();
            }
            if (adapterPositionByView != this.mFocusPosition) {
                this.mFocusPosition = adapterPositionByView;
                this.mFocusPositionOffset = 0;
                throw null;
            }
            if (view != null) {
                if (!view.hasFocus()) {
                    throw null;
                }
                if ((this.mFlag & 131072) != 0) {
                    int[] iArr = sTwoInts;
                    WindowAlignment windowAlignment = this.mWindowAlignment;
                    WindowAlignment.Axis axis = windowAlignment.mMainAxis;
                    ((LayoutParams) view.getLayoutParams()).getClass();
                    int scroll = axis.getScroll(view.getLeft() + 0 + 0);
                    if (view2 != null) {
                        ((LayoutParams) view.getLayoutParams()).getClass();
                    }
                    ((LayoutParams) view.getLayoutParams()).getClass();
                    int scroll2 = windowAlignment.mSecondAxis.getScroll(view.getTop() + 0 + 0);
                    if (scroll == 0 && scroll2 == 0) {
                        iArr[0] = 0;
                        iArr[1] = 0;
                    } else {
                        iArr[0] = scroll;
                        iArr[1] = scroll2;
                        if ((this.mFlag & 3) != 1) {
                            throw null;
                        }
                        scrollDirectionPrimary(scroll);
                        scrollDirectionSecondary(scroll2);
                    }
                }
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mFocusPosition = ((SavedState) parcelable).mIndex;
            this.mFocusPositionOffset = 0;
            this.mChildrenStates.getClass();
            this.mFlag |= 256;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.mChildStates = Bundle.EMPTY;
        savedState.mIndex = this.mFocusPosition;
        this.mChildrenStates.getClass();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getAdapterPositionByView(getChildAt(i));
        }
        savedState.mChildStates = null;
        return savedState;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0026, code lost:
    
        r7 = 8192;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0030, code lost:
    
        if (r5 != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0022, code lost:
    
        if (r5 != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0024, code lost:
    
        r7 = 4096;
     */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean performAccessibilityAction(androidx.recyclerview.widget.RecyclerView.Recycler r5, androidx.recyclerview.widget.RecyclerView.State r6, int r7, android.os.Bundle r8) {
        /*
            r4 = this;
            int r8 = r4.mFlag
            r0 = 131072(0x20000, float:1.83671E-40)
            r8 = r8 & r0
            r0 = 1
            if (r8 == 0) goto L6e
            r4.saveContext(r5, r6)
            int r5 = r4.mFlag
            r8 = 262144(0x40000, float:3.67342E-40)
            r5 = r5 & r8
            r8 = 0
            if (r5 == 0) goto L15
            r5 = r0
            goto L16
        L15:
            r5 = r8
        L16:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r1 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT
            int r1 = r1.getId()
            r2 = 8192(0x2000, float:1.148E-41)
            r3 = 4096(0x1000, float:5.74E-42)
            if (r7 != r1) goto L28
            if (r5 == 0) goto L26
        L24:
            r7 = r3
            goto L33
        L26:
            r7 = r2
            goto L33
        L28:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$AccessibilityActionCompat r1 = androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT
            int r1 = r1.getId()
            if (r7 != r1) goto L33
            if (r5 == 0) goto L24
            goto L26
        L33:
            int r5 = r4.mFocusPosition
            if (r5 != 0) goto L3b
            if (r7 != r2) goto L3b
            r1 = r0
            goto L3c
        L3b:
            r1 = r8
        L3c:
            int r6 = r6.getItemCount()
            int r6 = r6 - r0
            if (r5 != r6) goto L46
            if (r7 != r3) goto L46
            r8 = r0
        L46:
            r5 = 0
            if (r1 != 0) goto L6a
            if (r8 != 0) goto L6a
            if (r7 == r3) goto L5c
            if (r7 == r2) goto L50
            goto L65
        L50:
            int r6 = r4.getItemCount()
            if (r6 != 0) goto L5b
            r5 = -1
            r4.processSelectionMoves(r5)
            goto L65
        L5b:
            throw r5
        L5c:
            int r6 = r4.getItemCount()
            if (r6 != 0) goto L69
            r4.processSelectionMoves(r0)
        L65:
            r4.leaveContext()
            return r0
        L69:
            throw r5
        L6a:
            android.view.accessibility.AccessibilityEvent.obtain(r3)
            throw r5
        L6e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.performAccessibilityAction(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, int, android.os.Bundle):boolean");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void removeAndRecycleAllViews(RecyclerView.Recycler recycler) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            removeAndRecycleViewAt(childCount, recycler);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z) {
        return false;
    }

    public final void saveContext(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i = this.mSaveContextLevel;
        if (i == 0) {
            this.mState = state;
        }
        this.mSaveContextLevel = i + 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001b, code lost:
    
        if (r12 <= r0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002d, code lost:
    
        r12 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002b, code lost:
    
        if (r12 >= r0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a1, code lost:
    
        if (r7.mMinEdge == Integer.MIN_VALUE) goto L59;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int scrollDirectionPrimary(int r12) {
        /*
            Method dump skipped, instructions count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.scrollDirectionPrimary(int):int");
    }

    public final void scrollDirectionSecondary(int i) {
        int i2;
        if (i == 0) {
            return;
        }
        int i3 = -i;
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            getChildAt(i4).offsetTopAndBottom(i3);
        }
        this.mScrollOffsetSecondary += i;
        WindowAlignment.Axis axis = this.mWindowAlignment.mSecondAxis;
        axis.getClass();
        int i5 = 0 - this.mScrollOffsetSecondary;
        int i6 = (this.mFlag & 524288) != 0 ? 0 : this.mNumRows - 1;
        if ((this.mFlag & 524288) != 0) {
            i2 = 0;
            for (int i7 = this.mNumRows - 1; i7 > i6; i7--) {
                int i8 = this.mFixedRowSizeSecondary;
                if (i8 == 0) {
                    i8 = 0;
                }
                i2 += i8;
            }
        } else {
            i2 = 0;
            for (int i9 = 0; i9 < i6; i9++) {
                int i10 = this.mFixedRowSizeSecondary;
                if (i10 == 0) {
                    i10 = 0;
                }
                i2 += i10;
            }
        }
        int i11 = this.mFixedRowSizeSecondary;
        axis.updateMinMax(i5, (i11 != 0 ? i11 : 0) + i2 + i5);
        throw null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void scrollToPosition(int i) {
        if (this.mFocusPosition == i || i == -1) {
            return;
        }
        findViewByPosition(i);
        boolean isSmoothScrolling = isSmoothScrolling();
        if (!isSmoothScrolling) {
            throw null;
        }
        int i2 = this.mFlag;
        if ((i2 & 512) == 0 || (i2 & 64) != 0) {
            this.mFocusPosition = i;
            this.mFocusPositionOffset = Integer.MIN_VALUE;
        } else {
            if (!isSmoothScrolling) {
                throw null;
            }
            throw null;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void smoothScrollToPosition(RecyclerView recyclerView, int i) {
        if (this.mFocusPosition == i || i == -1) {
            return;
        }
        findViewByPosition(i);
        if (!isSmoothScrolling()) {
            throw null;
        }
        int i2 = this.mFlag;
        if ((i2 & 512) != 0 && (i2 & 64) == 0) {
            throw null;
        }
        this.mFocusPosition = i;
        this.mFocusPositionOffset = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void startSmoothScroll(LinearSmoothScroller linearSmoothScroller) {
        super.startSmoothScroll(linearSmoothScroller);
        if (linearSmoothScroller.mRunning && (linearSmoothScroller instanceof GridLinearSmoothScroller)) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            return new LayoutParams((RecyclerView.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public final void onLayoutCompleted(RecyclerView.State state) {
    }

    public final void processSelectionMoves(int i) {
    }
}
