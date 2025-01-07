package androidx.recyclerview.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.collection.SimpleArrayMap;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$$ExternalSyntheticLambda0;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.DifferentialMotionFlingController;
import androidx.core.view.DifferentialMotionFlingTarget;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.EdgeEffectCompat$Api31Impl;
import androidx.customview.poolingcontainer.PoolingContainer;
import androidx.customview.poolingcontainer.PoolingContainerListener;
import androidx.customview.poolingcontainer.PoolingContainerListenerHolder;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.R$styleable;
import androidx.recyclerview.widget.AdapterHelper;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DefaultItemAnimator.AnonymousClass4;
import androidx.recyclerview.widget.GapWorker;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import androidx.recyclerview.widget.ViewInfoStore;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class RecyclerView extends ViewGroup {
    public static final Class[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    public static final StretchEdgeEffectFactory sDefaultEdgeEffectFactory;
    public static final AnonymousClass3 sQuinticInterpolator;
    public RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
    public final AccessibilityManager mAccessibilityManager;
    public Adapter mAdapter;
    public final AdapterHelper mAdapterHelper;
    public boolean mAdapterUpdateDuringMeasure;
    public EdgeEffect mBottomGlow;
    public final ChildHelper mChildHelper;
    public boolean mClipToPadding;
    public boolean mDataSetHasChangedAfterLayout;
    DifferentialMotionFlingController mDifferentialMotionFlingController;
    public final AnonymousClass5 mDifferentialMotionFlingTarget;
    public boolean mDispatchItemsChangedEvent;
    public int mDispatchScrollCounter;
    public int mEatenAccessibilityChangeFlags;
    public final StretchEdgeEffectFactory mEdgeEffectFactory;
    boolean mFirstLayoutComplete;
    public GapWorker mGapWorker;
    public boolean mIgnoreMotionEventTillDown;
    public int mInitialTouchX;
    public int mInitialTouchY;
    public int mInterceptRequestLayoutDepth;
    public OnItemTouchListener mInterceptingOnItemTouchListener;
    public boolean mIsAttached;
    public DefaultItemAnimator mItemAnimator;
    public final AnonymousClass5 mItemAnimatorListener;
    public final AnonymousClass1 mItemAnimatorRunner;
    public final ArrayList mItemDecorations;
    public boolean mItemsAddedOrRemoved;
    public boolean mItemsChanged;
    public int mLastAutoMeasureNonExactMeasuredHeight;
    public int mLastAutoMeasureNonExactMeasuredWidth;
    public boolean mLastAutoMeasureSkippedDueToExact;
    public int mLastTouchX;
    public int mLastTouchY;
    LayoutManager mLayout;
    public int mLayoutOrScrollCounter;
    public boolean mLayoutSuppressed;
    public boolean mLayoutWasDefered;
    public EdgeEffect mLeftGlow;
    boolean mLowResRotaryEncoderFeature;
    public final int mMaxFlingVelocity;
    public final int mMinFlingVelocity;
    public final int[] mMinMaxLayoutPositions;
    public final int[] mNestedOffsets;
    public final RecyclerViewDataObserver mObserver;
    public List mOnChildAttachStateListeners;
    public PagerSnapHelper mOnFlingListener;
    public final ArrayList mOnItemTouchListeners;
    final List mPendingAccessibilityImportanceChange;
    public SavedState mPendingSavedState;
    public final float mPhysicalCoef;
    public boolean mPostedAnimatorRunner;
    public final GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
    public final boolean mPreserveFocusAfterLayout;
    public final Recycler mRecycler;
    public final List mRecyclerListeners;
    public final int[] mReusableIntPair;
    public EdgeEffect mRightGlow;
    public final float mScaledHorizontalScrollFactor;
    public final float mScaledVerticalScrollFactor;
    public List mScrollListeners;
    public final int[] mScrollOffset;
    public int mScrollPointerId;
    public int mScrollState;
    public NestedScrollingChildHelper mScrollingChildHelper;
    public final State mState;
    public final Rect mTempRect;
    public final Rect mTempRect2;
    public final RectF mTempRectF;
    public EdgeEffect mTopGlow;
    public int mTouchSlop;
    public final AnonymousClass1 mUpdateChildViewsRunnable;
    public VelocityTracker mVelocityTracker;
    public final ViewFlinger mViewFlinger;
    public final AnonymousClass5 mViewInfoProcessCallback;
    public final ViewInfoStore mViewInfoStore;
    public static final int[] NESTED_SCROLLING_ATTRS = {R.attr.nestedScrollingEnabled};
    public static final float DECELERATION_RATE = (float) (Math.log(0.78d) / Math.log(0.9d));
    public static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = true;
    public static final boolean ALLOW_THREAD_GAP_WORK = true;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.recyclerview.widget.RecyclerView$3, reason: invalid class name */
    public final class AnonymousClass3 implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.recyclerview.widget.RecyclerView$5, reason: invalid class name */
    public final class AnonymousClass5 implements DifferentialMotionFlingTarget {
        public /* synthetic */ AnonymousClass5() {
        }

        public void dispatchUpdate(AdapterHelper.UpdateOp updateOp) {
            int i = updateOp.cmd;
            RecyclerView recyclerView = RecyclerView.this;
            if (i == 1) {
                recyclerView.mLayout.onItemsAdded(updateOp.positionStart, updateOp.itemCount);
                return;
            }
            if (i == 2) {
                recyclerView.mLayout.onItemsRemoved(updateOp.positionStart, updateOp.itemCount);
            } else if (i == 4) {
                recyclerView.mLayout.onItemsUpdated(recyclerView, updateOp.positionStart, updateOp.itemCount);
            } else {
                if (i != 8) {
                    return;
                }
                recyclerView.mLayout.onItemsMoved(updateOp.positionStart, updateOp.itemCount);
            }
        }

        public ViewHolder findViewHolder(int i) {
            RecyclerView recyclerView = RecyclerView.this;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            int i2 = 0;
            ViewHolder viewHolder = null;
            while (true) {
                if (i2 >= unfilteredChildCount) {
                    break;
                }
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i2));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.mPosition == i) {
                    if (!recyclerView.mChildHelper.mHiddenViews.contains(childViewHolderInt.itemView)) {
                        viewHolder = childViewHolderInt;
                        break;
                    }
                    viewHolder = childViewHolderInt;
                }
                i2++;
            }
            if (viewHolder == null) {
                return null;
            }
            if (!recyclerView.mChildHelper.mHiddenViews.contains(viewHolder.itemView)) {
                return viewHolder;
            }
            int[] iArr = RecyclerView.NESTED_SCROLLING_ATTRS;
            return null;
        }

        @Override // androidx.core.view.DifferentialMotionFlingTarget
        public float getScaledScrollFactor() {
            float f;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mLayout.canScrollVertically()) {
                f = recyclerView.mScaledVerticalScrollFactor;
            } else {
                if (!recyclerView.mLayout.canScrollHorizontally()) {
                    return 0.0f;
                }
                f = recyclerView.mScaledHorizontalScrollFactor;
            }
            return -f;
        }

        public void markViewHoldersUpdated(int i, int i2, Object obj) {
            int i3;
            int i4;
            RecyclerView recyclerView = RecyclerView.this;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            int i5 = i2 + i;
            for (int i6 = 0; i6 < unfilteredChildCount; i6++) {
                View unfilteredChildAt = recyclerView.mChildHelper.getUnfilteredChildAt(i6);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(unfilteredChildAt);
                if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && (i4 = childViewHolderInt.mPosition) >= i && i4 < i5) {
                    childViewHolderInt.addFlags(2);
                    if (obj == null) {
                        childViewHolderInt.addFlags(1024);
                    } else if ((1024 & childViewHolderInt.mFlags) == 0) {
                        if (childViewHolderInt.mPayloads == null) {
                            ArrayList arrayList = new ArrayList();
                            childViewHolderInt.mPayloads = arrayList;
                            childViewHolderInt.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
                        }
                        childViewHolderInt.mPayloads.add(obj);
                    }
                    ((LayoutParams) unfilteredChildAt.getLayoutParams()).mInsetsDirty = true;
                }
            }
            Recycler recycler = recyclerView.mRecycler;
            for (int size = recycler.mCachedViews.size() - 1; size >= 0; size--) {
                ViewHolder viewHolder = (ViewHolder) recycler.mCachedViews.get(size);
                if (viewHolder != null && (i3 = viewHolder.mPosition) >= i && i3 < i5) {
                    viewHolder.addFlags(2);
                    recycler.recycleCachedViewAt(size);
                }
            }
            recyclerView.mItemsChanged = true;
        }

        public void offsetPositionsForAdd(int i, int i2) {
            RecyclerView recyclerView = RecyclerView.this;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            for (int i3 = 0; i3 < unfilteredChildCount; i3++) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i3));
                if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i) {
                    childViewHolderInt.offsetPosition(i2, false);
                    recyclerView.mState.mStructureChanged = true;
                }
            }
            Recycler recycler = recyclerView.mRecycler;
            int size = recycler.mCachedViews.size();
            for (int i4 = 0; i4 < size; i4++) {
                ViewHolder viewHolder = (ViewHolder) recycler.mCachedViews.get(i4);
                if (viewHolder != null && viewHolder.mPosition >= i) {
                    viewHolder.offsetPosition(i2, false);
                }
            }
            recyclerView.requestLayout();
            recyclerView.mItemsAddedOrRemoved = true;
        }

        public void offsetPositionsForMove(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            RecyclerView recyclerView = RecyclerView.this;
            int unfilteredChildCount = recyclerView.mChildHelper.getUnfilteredChildCount();
            int i10 = -1;
            if (i < i2) {
                i4 = i;
                i3 = i2;
                i5 = -1;
            } else {
                i3 = i;
                i4 = i2;
                i5 = 1;
            }
            for (int i11 = 0; i11 < unfilteredChildCount; i11++) {
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.getUnfilteredChildAt(i11));
                if (childViewHolderInt != null && (i9 = childViewHolderInt.mPosition) >= i4 && i9 <= i3) {
                    if (i9 == i) {
                        childViewHolderInt.offsetPosition(i2 - i, false);
                    } else {
                        childViewHolderInt.offsetPosition(i5, false);
                    }
                    recyclerView.mState.mStructureChanged = true;
                }
            }
            Recycler recycler = recyclerView.mRecycler;
            recycler.getClass();
            if (i < i2) {
                i7 = i;
                i6 = i2;
            } else {
                i6 = i;
                i7 = i2;
                i10 = 1;
            }
            int size = recycler.mCachedViews.size();
            for (int i12 = 0; i12 < size; i12++) {
                ViewHolder viewHolder = (ViewHolder) recycler.mCachedViews.get(i12);
                if (viewHolder != null && (i8 = viewHolder.mPosition) >= i7 && i8 <= i6) {
                    if (i8 == i) {
                        viewHolder.offsetPosition(i2 - i, false);
                    } else {
                        viewHolder.offsetPosition(i10, false);
                    }
                }
            }
            recyclerView.requestLayout();
            recyclerView.mItemsAddedOrRemoved = true;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0034  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void processAppeared(androidx.recyclerview.widget.RecyclerView.ViewHolder r8, androidx.recyclerview.widget.RecyclerView$ItemAnimator$ItemHolderInfo r9, androidx.recyclerview.widget.RecyclerView$ItemAnimator$ItemHolderInfo r10) {
            /*
                r7 = this;
                androidx.recyclerview.widget.RecyclerView r7 = androidx.recyclerview.widget.RecyclerView.this
                r0 = 0
                r8.setIsRecyclable(r0)
                androidx.recyclerview.widget.DefaultItemAnimator r1 = r7.mItemAnimator
                if (r9 == 0) goto L23
                r1.getClass()
                int r3 = r9.left
                int r5 = r10.left
                if (r3 != r5) goto L19
                int r0 = r9.top
                int r2 = r10.top
                if (r0 == r2) goto L23
            L19:
                int r4 = r9.top
                int r6 = r10.top
                r2 = r8
                boolean r8 = r1.animateMove(r2, r3, r4, r5, r6)
                goto L32
            L23:
                r1.resetAnimation(r8)
                android.view.View r9 = r8.itemView
                r10 = 0
                r9.setAlpha(r10)
                java.util.ArrayList r9 = r1.mPendingAdditions
                r9.add(r8)
                r8 = 1
            L32:
                if (r8 == 0) goto L37
                r7.postAnimationRunner()
            L37:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.AnonymousClass5.processAppeared(androidx.recyclerview.widget.RecyclerView$ViewHolder, androidx.recyclerview.widget.RecyclerView$ItemAnimator$ItemHolderInfo, androidx.recyclerview.widget.RecyclerView$ItemAnimator$ItemHolderInfo):void");
        }

        public void processDisappeared(ViewHolder viewHolder, RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
            boolean z;
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mRecycler.unscrapView(viewHolder);
            recyclerView.addAnimatingView(viewHolder);
            viewHolder.setIsRecyclable(false);
            DefaultItemAnimator defaultItemAnimator = recyclerView.mItemAnimator;
            defaultItemAnimator.getClass();
            int i = recyclerView$ItemAnimator$ItemHolderInfo.left;
            int i2 = recyclerView$ItemAnimator$ItemHolderInfo.top;
            View view = viewHolder.itemView;
            int left = recyclerView$ItemAnimator$ItemHolderInfo2 == null ? view.getLeft() : recyclerView$ItemAnimator$ItemHolderInfo2.left;
            int top = recyclerView$ItemAnimator$ItemHolderInfo2 == null ? view.getTop() : recyclerView$ItemAnimator$ItemHolderInfo2.top;
            if (viewHolder.isRemoved() || (i == left && i2 == top)) {
                defaultItemAnimator.resetAnimation(viewHolder);
                defaultItemAnimator.mPendingRemovals.add(viewHolder);
                z = true;
            } else {
                view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                z = defaultItemAnimator.animateMove(viewHolder, i, i2, left, top);
            }
            if (z) {
                recyclerView.postAnimationRunner();
            }
        }

        public void removeViewAt(int i) {
            RecyclerView recyclerView = RecyclerView.this;
            View childAt = recyclerView.getChildAt(i);
            if (childAt != null) {
                recyclerView.dispatchChildDetached(childAt);
                childAt.clearAnimation();
            }
            recyclerView.removeViewAt(i);
        }

        @Override // androidx.core.view.DifferentialMotionFlingTarget
        public boolean startDifferentialMotionFling(float f) {
            int i;
            int i2;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mLayout.canScrollVertically()) {
                i2 = (int) f;
                i = 0;
            } else if (recyclerView.mLayout.canScrollHorizontally()) {
                i = (int) f;
                i2 = 0;
            } else {
                i = 0;
                i2 = 0;
            }
            if (i == 0 && i2 == 0) {
                return false;
            }
            recyclerView.stopScroll();
            return recyclerView.fling(i, i2, 0, Integer.MAX_VALUE);
        }

        @Override // androidx.core.view.DifferentialMotionFlingTarget
        public void stopDifferentialMotionFling() {
            RecyclerView.this.stopScroll();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AdapterDataObservable extends Observable {
        public final boolean hasObservers() {
            return !((Observable) this).mObservers.isEmpty();
        }

        public final void notifyChanged() {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onChanged();
            }
        }

        public final void notifyItemMoved(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeMoved(i, i2);
            }
        }

        public final void notifyItemRangeChanged(int i, int i2, Object obj) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeChanged(i, i2, obj);
            }
        }

        public final void notifyItemRangeInserted(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeInserted(i, i2);
            }
        }

        public final void notifyItemRangeRemoved(int i, int i2) {
            for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
                ((AdapterDataObserver) ((Observable) this).mObservers.get(size)).onItemRangeRemoved(i, i2);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class AdapterDataObserver {
        public abstract void onChanged();

        public void onItemRangeChanged() {
        }

        public void onItemRangeChanged(int i, int i2, Object obj) {
            onItemRangeChanged();
        }

        public void onItemRangeInserted(int i, int i2) {
        }

        public void onItemRangeMoved(int i, int i2) {
        }

        public void onItemRangeRemoved(int i, int i2) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class LayoutManager {
        public boolean mAutoMeasure;
        public ChildHelper mChildHelper;
        public int mHeight;
        public int mHeightMode;
        public final ViewBoundsCheck mHorizontalBoundCheck;
        public final AnonymousClass1 mHorizontalBoundCheckCallback;
        public boolean mIsAttachedToWindow;
        public boolean mItemPrefetchEnabled;
        public final boolean mMeasurementCacheEnabled;
        public int mPrefetchMaxCountObserved;
        public boolean mPrefetchMaxObservedInInitialPrefetch;
        public RecyclerView mRecyclerView;
        public boolean mRequestedSimpleAnimations;
        public LinearSmoothScroller mSmoothScroller;
        public final ViewBoundsCheck mVerticalBoundCheck;
        public final AnonymousClass1 mVerticalBoundCheckCallback;
        public int mWidth;
        public int mWidthMode;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: androidx.recyclerview.widget.RecyclerView$LayoutManager$1, reason: invalid class name */
        public final class AnonymousClass1 {
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ LayoutManager this$0;

            public /* synthetic */ AnonymousClass1(LayoutManager layoutManager, int i) {
                this.$r8$classId = i;
                this.this$0 = layoutManager;
            }

            public final int getChildEnd(View view) {
                switch (this.$r8$classId) {
                    case 0:
                        return this.this$0.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).rightMargin;
                    default:
                        return this.this$0.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).bottomMargin;
                }
            }

            public final int getChildStart(View view) {
                switch (this.$r8$classId) {
                    case 0:
                        return this.this$0.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).leftMargin;
                    default:
                        return this.this$0.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((LayoutParams) view.getLayoutParams())).topMargin;
                }
            }

            public final int getParentEnd() {
                switch (this.$r8$classId) {
                    case 0:
                        LayoutManager layoutManager = this.this$0;
                        return layoutManager.mWidth - layoutManager.getPaddingRight();
                    default:
                        LayoutManager layoutManager2 = this.this$0;
                        return layoutManager2.mHeight - layoutManager2.getPaddingBottom();
                }
            }

            public final int getParentStart() {
                switch (this.$r8$classId) {
                    case 0:
                        return this.this$0.getPaddingLeft();
                    default:
                        return this.this$0.getPaddingTop();
                }
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Properties {
            public int orientation;
            public boolean reverseLayout;
            public int spanCount;
            public boolean stackFromEnd;
        }

        public LayoutManager() {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this, 0);
            AnonymousClass1 anonymousClass12 = new AnonymousClass1(this, 1);
            this.mHorizontalBoundCheck = new ViewBoundsCheck(anonymousClass1);
            this.mVerticalBoundCheck = new ViewBoundsCheck(anonymousClass12);
            this.mRequestedSimpleAnimations = false;
            this.mIsAttachedToWindow = false;
            this.mAutoMeasure = false;
            this.mMeasurementCacheEnabled = true;
            this.mItemPrefetchEnabled = true;
        }

        public static int chooseSize(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i2, i3) : size : Math.min(size, Math.max(i2, i3));
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0018, code lost:
        
            if (r6 == 1073741824) goto L14;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static int getChildMeasureSpec(boolean r4, int r5, int r6, int r7, int r8) {
            /*
                int r5 = r5 - r7
                r7 = 0
                int r5 = java.lang.Math.max(r7, r5)
                r0 = -2
                r1 = -1
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = 1073741824(0x40000000, float:2.0)
                if (r4 == 0) goto L1d
                if (r8 < 0) goto L12
            L10:
                r6 = r3
                goto L30
            L12:
                if (r8 != r1) goto L1a
                if (r6 == r2) goto L22
                if (r6 == 0) goto L1a
                if (r6 == r3) goto L22
            L1a:
                r6 = r7
                r8 = r6
                goto L30
            L1d:
                if (r8 < 0) goto L20
                goto L10
            L20:
                if (r8 != r1) goto L24
            L22:
                r8 = r5
                goto L30
            L24:
                if (r8 != r0) goto L1a
                if (r6 == r2) goto L2e
                if (r6 != r3) goto L2b
                goto L2e
            L2b:
                r8 = r5
                r6 = r7
                goto L30
            L2e:
                r8 = r5
                r6 = r2
            L30:
                int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r6)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.LayoutManager.getChildMeasureSpec(boolean, int, int, int, int):int");
        }

        public static int getDecoratedMeasuredHeight(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredHeight() + rect.top + rect.bottom;
        }

        public static int getDecoratedMeasuredWidth(View view) {
            Rect rect = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            return view.getMeasuredWidth() + rect.left + rect.right;
        }

        public static int getPosition(View view) {
            return ((LayoutParams) view.getLayoutParams()).mViewHolder.getLayoutPosition();
        }

        public static Properties getProperties(Context context, AttributeSet attributeSet, int i, int i2) {
            Properties properties = new Properties();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RecyclerView, i, i2);
            properties.orientation = obtainStyledAttributes.getInt(0, 1);
            properties.spanCount = obtainStyledAttributes.getInt(10, 1);
            properties.reverseLayout = obtainStyledAttributes.getBoolean(9, false);
            properties.stackFromEnd = obtainStyledAttributes.getBoolean(11, false);
            obtainStyledAttributes.recycle();
            return properties;
        }

        public static boolean isMeasurementUpToDate(int i, int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i2);
            int size = View.MeasureSpec.getSize(i2);
            if (i3 > 0 && i != i3) {
                return false;
            }
            if (mode == Integer.MIN_VALUE) {
                return size >= i;
            }
            if (mode != 0) {
                return mode == 1073741824 && size == i;
            }
            return true;
        }

        public static void layoutDecoratedWithMargins(View view, int i, int i2, int i3, int i4) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect = layoutParams.mDecorInsets;
            view.layout(i + rect.left + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, i2 + rect.top + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (i3 - rect.right) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, (i4 - rect.bottom) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        }

        public final void addViewInt(int i, View view, boolean z) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (z || childViewHolderInt.isRemoved()) {
                SimpleArrayMap simpleArrayMap = this.mRecyclerView.mViewInfoStore.mLayoutHolderMap;
                ViewInfoStore.InfoRecord infoRecord = (ViewInfoStore.InfoRecord) simpleArrayMap.get(childViewHolderInt);
                if (infoRecord == null) {
                    infoRecord = ViewInfoStore.InfoRecord.obtain();
                    simpleArrayMap.put(childViewHolderInt, infoRecord);
                }
                infoRecord.flags |= 1;
            } else {
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
                if (childViewHolderInt.isScrap()) {
                    childViewHolderInt.mScrapContainer.unscrapView(childViewHolderInt);
                } else {
                    childViewHolderInt.mFlags &= -33;
                }
                this.mChildHelper.attachViewToParent(view, i, view.getLayoutParams(), false);
            } else {
                if (view.getParent() == this.mRecyclerView) {
                    int indexOfChild = this.mChildHelper.indexOfChild(view);
                    if (i == -1) {
                        i = this.mChildHelper.getChildCount();
                    }
                    if (indexOfChild == -1) {
                        throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.mRecyclerView.indexOfChild(view) + this.mRecyclerView.exceptionLabel());
                    }
                    if (indexOfChild != i) {
                        LayoutManager layoutManager = this.mRecyclerView.mLayout;
                        View childAt = layoutManager.getChildAt(indexOfChild);
                        if (childAt == null) {
                            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + indexOfChild + layoutManager.mRecyclerView.toString());
                        }
                        layoutManager.getChildAt(indexOfChild);
                        layoutManager.mChildHelper.detachViewFromParent(indexOfChild);
                        LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                        ViewHolder childViewHolderInt2 = RecyclerView.getChildViewHolderInt(childAt);
                        if (childViewHolderInt2.isRemoved()) {
                            SimpleArrayMap simpleArrayMap2 = layoutManager.mRecyclerView.mViewInfoStore.mLayoutHolderMap;
                            ViewInfoStore.InfoRecord infoRecord2 = (ViewInfoStore.InfoRecord) simpleArrayMap2.get(childViewHolderInt2);
                            if (infoRecord2 == null) {
                                infoRecord2 = ViewInfoStore.InfoRecord.obtain();
                                simpleArrayMap2.put(childViewHolderInt2, infoRecord2);
                            }
                            infoRecord2.flags = 1 | infoRecord2.flags;
                        } else {
                            layoutManager.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt2);
                        }
                        layoutManager.mChildHelper.attachViewToParent(childAt, i, layoutParams2, childViewHolderInt2.isRemoved());
                    }
                } else {
                    this.mChildHelper.addView(i, view, false);
                    layoutParams.mInsetsDirty = true;
                    LinearSmoothScroller linearSmoothScroller = this.mSmoothScroller;
                    if (linearSmoothScroller != null && linearSmoothScroller.mRunning) {
                        linearSmoothScroller.mRecyclerView.getClass();
                        ViewHolder childViewHolderInt3 = RecyclerView.getChildViewHolderInt(view);
                        if ((childViewHolderInt3 != null ? childViewHolderInt3.getLayoutPosition() : -1) == linearSmoothScroller.mTargetPosition) {
                            linearSmoothScroller.mTargetView = view;
                        }
                    }
                }
            }
            if (layoutParams.mPendingInvalidate) {
                childViewHolderInt.itemView.invalidate();
                layoutParams.mPendingInvalidate = false;
            }
        }

        public void assertNotInLayoutOrScroll(String str) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.assertNotInLayoutOrScroll(str);
            }
        }

        public void calculateItemDecorationsForChild(Rect rect, View view) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(recyclerView.getItemDecorInsetsForChild(view));
            }
        }

        public abstract boolean canScrollHorizontally();

        public abstract boolean canScrollVertically();

        public boolean checkLayoutParams(LayoutParams layoutParams) {
            return layoutParams != null;
        }

        public int computeHorizontalScrollExtent(State state) {
            return 0;
        }

        public int computeHorizontalScrollOffset(State state) {
            return 0;
        }

        public int computeHorizontalScrollRange(State state) {
            return 0;
        }

        public int computeVerticalScrollExtent(State state) {
            return 0;
        }

        public int computeVerticalScrollOffset(State state) {
            return 0;
        }

        public int computeVerticalScrollRange(State state) {
            return 0;
        }

        public View findViewByPosition(int i) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
                if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.mInPreLayout || !childViewHolderInt.isRemoved())) {
                    return childAt;
                }
            }
            return null;
        }

        public abstract LayoutParams generateDefaultLayoutParams();

        public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
            return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
        }

        public final View getChildAt(int i) {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper != null) {
                return childHelper.getChildAt(i);
            }
            return null;
        }

        public final int getChildCount() {
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper != null) {
                return childHelper.getChildCount();
            }
            return 0;
        }

        public int getColumnCountForAccessibility(Recycler recycler, State state) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || recyclerView.mAdapter == null || !canScrollHorizontally()) {
                return 1;
            }
            return this.mRecyclerView.mAdapter.getItemCount();
        }

        public int getDecoratedBottom(View view) {
            return view.getBottom() + ((LayoutParams) view.getLayoutParams()).mDecorInsets.bottom;
        }

        public void getDecoratedBoundsWithMargins(Rect rect, View view) {
            int[] iArr = RecyclerView.NESTED_SCROLLING_ATTRS;
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Rect rect2 = layoutParams.mDecorInsets;
            rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        }

        public int getDecoratedLeft(View view) {
            return view.getLeft() - ((LayoutParams) view.getLayoutParams()).mDecorInsets.left;
        }

        public int getDecoratedRight(View view) {
            return view.getRight() + ((LayoutParams) view.getLayoutParams()).mDecorInsets.right;
        }

        public int getDecoratedTop(View view) {
            return view.getTop() - ((LayoutParams) view.getLayoutParams()).mDecorInsets.top;
        }

        public final int getItemCount() {
            RecyclerView recyclerView = this.mRecyclerView;
            Adapter adapter = recyclerView != null ? recyclerView.mAdapter : null;
            if (adapter != null) {
                return adapter.getItemCount();
            }
            return 0;
        }

        public final int getPaddingBottom() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingBottom();
            }
            return 0;
        }

        public final int getPaddingLeft() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingLeft();
            }
            return 0;
        }

        public final int getPaddingRight() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingRight();
            }
            return 0;
        }

        public final int getPaddingTop() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                return recyclerView.getPaddingTop();
            }
            return 0;
        }

        public int getRowCountForAccessibility(Recycler recycler, State state) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView == null || recyclerView.mAdapter == null || !canScrollVertically()) {
                return 1;
            }
            return this.mRecyclerView.mAdapter.getItemCount();
        }

        public final void getTransformedBoundingBox(Rect rect, View view) {
            Matrix matrix;
            Rect rect2 = ((LayoutParams) view.getLayoutParams()).mDecorInsets;
            rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
            if (this.mRecyclerView != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
                RectF rectF = this.mRecyclerView.mTempRectF;
                rectF.set(rect);
                matrix.mapRect(rectF);
                rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
            }
            rect.offset(view.getLeft(), view.getTop());
        }

        public boolean isAutoMeasureEnabled() {
            return this.mAutoMeasure;
        }

        public boolean isLayoutReversed() {
            return false;
        }

        public final boolean isSmoothScrolling() {
            LinearSmoothScroller linearSmoothScroller = this.mSmoothScroller;
            return linearSmoothScroller != null && linearSmoothScroller.mRunning;
        }

        public void offsetChildrenHorizontal(int i) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                int childCount = recyclerView.mChildHelper.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    recyclerView.mChildHelper.getChildAt(i2).offsetLeftAndRight(i);
                }
            }
        }

        public void offsetChildrenVertical(int i) {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                int childCount = recyclerView.mChildHelper.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    recyclerView.mChildHelper.getChildAt(i2).offsetTopAndBottom(i);
                }
            }
        }

        public boolean onAddFocusables(RecyclerView recyclerView, ArrayList arrayList, int i, int i2) {
            return false;
        }

        public View onFocusSearchFailed(View view, int i, Recycler recycler, State state) {
            return null;
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            RecyclerView recyclerView = this.mRecyclerView;
            Recycler recycler = recyclerView.mRecycler;
            State state = recyclerView.mState;
            if (recyclerView == null || accessibilityEvent == null) {
                return;
            }
            boolean z = true;
            if (!recyclerView.canScrollVertically(1) && !this.mRecyclerView.canScrollVertically(-1) && !this.mRecyclerView.canScrollHorizontally(-1) && !this.mRecyclerView.canScrollHorizontally(1)) {
                z = false;
            }
            accessibilityEvent.setScrollable(z);
            Adapter adapter = this.mRecyclerView.mAdapter;
            if (adapter != null) {
                accessibilityEvent.setItemCount(adapter.getItemCount());
            }
        }

        public void onInitializeAccessibilityNodeInfo(Recycler recycler, State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
                accessibilityNodeInfoCompat.setScrollable(true);
                Bundle extras = accessibilityNodeInfoCompat.mInfo.getExtras();
                if (extras != null) {
                    extras.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", (extras.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (-67108865)) | 67108864);
                }
            }
            if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
                accessibilityNodeInfoCompat.setScrollable(true);
                Bundle extras2 = accessibilityNodeInfoCompat.mInfo.getExtras();
                if (extras2 != null) {
                    extras2.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", (extras2.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (-67108865)) | 67108864);
                }
            }
            accessibilityNodeInfoCompat.mInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), false, 0));
        }

        public final void onInitializeAccessibilityNodeInfoForItem(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt == null || childViewHolderInt.isRemoved()) {
                return;
            }
            ChildHelper childHelper = this.mChildHelper;
            if (childHelper.mHiddenViews.contains(childViewHolderInt.itemView)) {
                return;
            }
            RecyclerView recyclerView = this.mRecyclerView;
            onInitializeAccessibilityNodeInfoForItem(recyclerView.mRecycler, recyclerView.mState, view, accessibilityNodeInfoCompat);
        }

        public View onInterceptFocusSearch(View view, int i) {
            return null;
        }

        public void onItemsUpdated(int i, int i2) {
        }

        public abstract void onLayoutChildren(Recycler recycler, State state);

        public abstract void onLayoutCompleted(State state);

        public void onMeasure(Recycler recycler, State state, int i, int i2) {
            this.mRecyclerView.defaultOnMeasure(i, i2);
        }

        public boolean onRequestChildFocus(RecyclerView recyclerView, View view, View view2) {
            return isSmoothScrolling() || recyclerView.isComputingLayout();
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public boolean performAccessibilityAction(int i, Bundle bundle) {
            RecyclerView recyclerView = this.mRecyclerView;
            return performAccessibilityAction(recyclerView.mRecycler, recyclerView.mState, i, bundle);
        }

        public void removeAndRecycleAllViews(Recycler recycler) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                if (!RecyclerView.getChildViewHolderInt(getChildAt(childCount)).shouldIgnore()) {
                    removeAndRecycleViewAt(childCount, recycler);
                }
            }
        }

        public final void removeAndRecycleScrapInt(Recycler recycler) {
            int size = recycler.mAttachedScrap.size();
            for (int i = size - 1; i >= 0; i--) {
                View view = ((ViewHolder) recycler.mAttachedScrap.get(i)).itemView;
                ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
                if (!childViewHolderInt.shouldIgnore()) {
                    childViewHolderInt.setIsRecyclable(false);
                    if (childViewHolderInt.isTmpDetached()) {
                        this.mRecyclerView.removeDetachedView(view, false);
                    }
                    DefaultItemAnimator defaultItemAnimator = this.mRecyclerView.mItemAnimator;
                    if (defaultItemAnimator != null) {
                        defaultItemAnimator.endAnimation(childViewHolderInt);
                    }
                    childViewHolderInt.setIsRecyclable(true);
                    ViewHolder childViewHolderInt2 = RecyclerView.getChildViewHolderInt(view);
                    childViewHolderInt2.mScrapContainer = null;
                    childViewHolderInt2.mInChangeScrap = false;
                    childViewHolderInt2.mFlags &= -33;
                    recycler.recycleViewHolderInternal(childViewHolderInt2);
                }
            }
            recycler.mAttachedScrap.clear();
            ArrayList arrayList = recycler.mChangedScrap;
            if (arrayList != null) {
                arrayList.clear();
            }
            if (size > 0) {
                this.mRecyclerView.invalidate();
            }
        }

        public final void removeAndRecycleView(View view, Recycler recycler) {
            ChildHelper childHelper = this.mChildHelper;
            AnonymousClass5 anonymousClass5 = childHelper.mCallback;
            int i = childHelper.mRemoveStatus;
            if (i == 1) {
                throw new IllegalStateException("Cannot call removeView(At) within removeView(At)");
            }
            if (i == 2) {
                throw new IllegalStateException("Cannot call removeView(At) within removeViewIfHidden");
            }
            try {
                childHelper.mRemoveStatus = 1;
                childHelper.mViewInRemoveView = view;
                int indexOfChild = RecyclerView.this.indexOfChild(view);
                if (indexOfChild >= 0) {
                    if (childHelper.mBucket.remove(indexOfChild)) {
                        childHelper.unhideViewInternal(view);
                    }
                    anonymousClass5.removeViewAt(indexOfChild);
                }
                childHelper.mRemoveStatus = 0;
                childHelper.mViewInRemoveView = null;
                recycler.recycleView(view);
            } catch (Throwable th) {
                childHelper.mRemoveStatus = 0;
                childHelper.mViewInRemoveView = null;
                throw th;
            }
        }

        public final void removeAndRecycleViewAt(int i, Recycler recycler) {
            View childAt = getChildAt(i);
            removeViewAt(i);
            recycler.recycleView(childAt);
        }

        public final void removeViewAt(int i) {
            if (getChildAt(i) != null) {
                ChildHelper childHelper = this.mChildHelper;
                AnonymousClass5 anonymousClass5 = childHelper.mCallback;
                int i2 = childHelper.mRemoveStatus;
                if (i2 == 1) {
                    throw new IllegalStateException("Cannot call removeView(At) within removeView(At)");
                }
                if (i2 == 2) {
                    throw new IllegalStateException("Cannot call removeView(At) within removeViewIfHidden");
                }
                try {
                    int offset = childHelper.getOffset(i);
                    View childAt = RecyclerView.this.getChildAt(offset);
                    if (childAt != null) {
                        childHelper.mRemoveStatus = 1;
                        childHelper.mViewInRemoveView = childAt;
                        if (childHelper.mBucket.remove(offset)) {
                            childHelper.unhideViewInternal(childAt);
                        }
                        anonymousClass5.removeViewAt(offset);
                    }
                } finally {
                    childHelper.mRemoveStatus = 0;
                    childHelper.mViewInRemoveView = null;
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x00ad, code lost:
        
            if ((r5.bottom - r10) > r2) goto L28;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean requestChildRectangleOnScreen(androidx.recyclerview.widget.RecyclerView r9, android.view.View r10, android.graphics.Rect r11, boolean r12, boolean r13) {
            /*
                r8 = this;
                int r0 = r8.getPaddingLeft()
                int r1 = r8.getPaddingTop()
                int r2 = r8.mWidth
                int r3 = r8.getPaddingRight()
                int r2 = r2 - r3
                int r3 = r8.mHeight
                int r4 = r8.getPaddingBottom()
                int r3 = r3 - r4
                int r4 = r10.getLeft()
                int r5 = r11.left
                int r4 = r4 + r5
                int r5 = r10.getScrollX()
                int r4 = r4 - r5
                int r5 = r10.getTop()
                int r6 = r11.top
                int r5 = r5 + r6
                int r10 = r10.getScrollY()
                int r5 = r5 - r10
                int r10 = r11.width()
                int r10 = r10 + r4
                int r11 = r11.height()
                int r11 = r11 + r5
                int r4 = r4 - r0
                r0 = 0
                int r6 = java.lang.Math.min(r0, r4)
                int r5 = r5 - r1
                int r1 = java.lang.Math.min(r0, r5)
                int r10 = r10 - r2
                int r2 = java.lang.Math.max(r0, r10)
                int r11 = r11 - r3
                int r11 = java.lang.Math.max(r0, r11)
                androidx.recyclerview.widget.RecyclerView r3 = r8.mRecyclerView
                int r3 = r3.getLayoutDirection()
                r7 = 1
                if (r3 != r7) goto L5e
                if (r2 == 0) goto L59
                goto L66
            L59:
                int r2 = java.lang.Math.max(r6, r10)
                goto L66
            L5e:
                if (r6 == 0) goto L61
                goto L65
            L61:
                int r6 = java.lang.Math.min(r4, r2)
            L65:
                r2 = r6
            L66:
                if (r1 == 0) goto L69
                goto L6d
            L69:
                int r1 = java.lang.Math.min(r5, r11)
            L6d:
                int[] r10 = new int[]{r2, r1}
                r11 = r10[r0]
                r10 = r10[r7]
                if (r13 == 0) goto Lb0
                android.view.View r13 = r9.getFocusedChild()
                if (r13 != 0) goto L7e
                goto Lb5
            L7e:
                int r1 = r8.getPaddingLeft()
                int r2 = r8.getPaddingTop()
                int r3 = r8.mWidth
                int r4 = r8.getPaddingRight()
                int r3 = r3 - r4
                int r4 = r8.mHeight
                int r5 = r8.getPaddingBottom()
                int r4 = r4 - r5
                androidx.recyclerview.widget.RecyclerView r5 = r8.mRecyclerView
                android.graphics.Rect r5 = r5.mTempRect
                r8.getDecoratedBoundsWithMargins(r5, r13)
                int r8 = r5.left
                int r8 = r8 - r11
                if (r8 >= r3) goto Lb5
                int r8 = r5.right
                int r8 = r8 - r11
                if (r8 <= r1) goto Lb5
                int r8 = r5.top
                int r8 = r8 - r10
                if (r8 >= r4) goto Lb5
                int r8 = r5.bottom
                int r8 = r8 - r10
                if (r8 > r2) goto Lb0
                goto Lb5
            Lb0:
                if (r11 != 0) goto Lb6
                if (r10 == 0) goto Lb5
                goto Lb6
            Lb5:
                return r0
            Lb6:
                if (r12 == 0) goto Lbc
                r9.scrollBy(r11, r10)
                goto Lbf
            Lbc:
                r9.smoothScrollBy$1(r11, r10, r0)
            Lbf:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.LayoutManager.requestChildRectangleOnScreen(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
        }

        public final void requestLayout() {
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.requestLayout();
            }
        }

        public final void scrapOrRecycleView(Recycler recycler, int i, View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt.shouldIgnore()) {
                return;
            }
            if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !this.mRecyclerView.mAdapter.mHasStableIds) {
                removeViewAt(i);
                recycler.recycleViewHolderInternal(childViewHolderInt);
            } else {
                getChildAt(i);
                this.mChildHelper.detachViewFromParent(i);
                recycler.scrapView(view);
                this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(childViewHolderInt);
            }
        }

        public abstract int scrollHorizontallyBy(int i, Recycler recycler, State state);

        public abstract void scrollToPosition(int i);

        public abstract int scrollVerticallyBy(int i, Recycler recycler, State state);

        public final void setExactMeasureSpecsFrom(RecyclerView recyclerView) {
            setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
        }

        public final void setMeasureSpecs(int i, int i2) {
            this.mWidth = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            this.mWidthMode = mode;
            if (mode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                this.mWidth = 0;
            }
            this.mHeight = View.MeasureSpec.getSize(i2);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.mHeightMode = mode2;
            if (mode2 != 0 || RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
                return;
            }
            this.mHeight = 0;
        }

        public void setMeasuredDimension(Rect rect, int i, int i2) {
            int paddingRight = getPaddingRight() + getPaddingLeft() + rect.width();
            int paddingBottom = getPaddingBottom() + getPaddingTop() + rect.height();
            RecyclerView recyclerView = this.mRecyclerView;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            this.mRecyclerView.setMeasuredDimension(chooseSize(i, paddingRight, recyclerView.getMinimumWidth()), chooseSize(i2, paddingBottom, this.mRecyclerView.getMinimumHeight()));
        }

        public final void setMeasuredDimensionFromChildren(int i, int i2) {
            int childCount = getChildCount();
            if (childCount == 0) {
                this.mRecyclerView.defaultOnMeasure(i, i2);
                return;
            }
            int i3 = Integer.MIN_VALUE;
            int i4 = Integer.MAX_VALUE;
            int i5 = Integer.MIN_VALUE;
            int i6 = Integer.MAX_VALUE;
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                Rect rect = this.mRecyclerView.mTempRect;
                getDecoratedBoundsWithMargins(rect, childAt);
                int i8 = rect.left;
                if (i8 < i6) {
                    i6 = i8;
                }
                int i9 = rect.right;
                if (i9 > i3) {
                    i3 = i9;
                }
                int i10 = rect.top;
                if (i10 < i4) {
                    i4 = i10;
                }
                int i11 = rect.bottom;
                if (i11 > i5) {
                    i5 = i11;
                }
            }
            this.mRecyclerView.mTempRect.set(i6, i4, i3, i5);
            setMeasuredDimension(this.mRecyclerView.mTempRect, i, i2);
        }

        public final void setRecyclerView(RecyclerView recyclerView) {
            if (recyclerView == null) {
                this.mRecyclerView = null;
                this.mChildHelper = null;
                this.mWidth = 0;
                this.mHeight = 0;
            } else {
                this.mRecyclerView = recyclerView;
                this.mChildHelper = recyclerView.mChildHelper;
                this.mWidth = recyclerView.getWidth();
                this.mHeight = recyclerView.getHeight();
            }
            this.mWidthMode = 1073741824;
            this.mHeightMode = 1073741824;
        }

        public final boolean shouldMeasureChild(View view, int i, int i2, LayoutParams layoutParams) {
            return (!view.isLayoutRequested() && this.mMeasurementCacheEnabled && isMeasurementUpToDate(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) && isMeasurementUpToDate(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        public boolean shouldMeasureTwice() {
            return false;
        }

        public final boolean shouldReMeasureChild(View view, int i, int i2, LayoutParams layoutParams) {
            return (this.mMeasurementCacheEnabled && isMeasurementUpToDate(view.getMeasuredWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) && isMeasurementUpToDate(view.getMeasuredHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
        }

        public abstract void smoothScrollToPosition(RecyclerView recyclerView, int i);

        public void startSmoothScroll(LinearSmoothScroller linearSmoothScroller) {
            LinearSmoothScroller linearSmoothScroller2 = this.mSmoothScroller;
            if (linearSmoothScroller2 != null && linearSmoothScroller != linearSmoothScroller2 && linearSmoothScroller2.mRunning) {
                linearSmoothScroller2.stop();
            }
            this.mSmoothScroller = linearSmoothScroller;
            RecyclerView recyclerView = this.mRecyclerView;
            ViewFlinger viewFlinger = recyclerView.mViewFlinger;
            RecyclerView.this.removeCallbacks(viewFlinger);
            viewFlinger.mOverScroller.abortAnimation();
            if (linearSmoothScroller.mStarted) {
                Log.w("RecyclerView", "An instance of " + linearSmoothScroller.getClass().getSimpleName() + " was started more than once. Each instance of" + linearSmoothScroller.getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
            }
            linearSmoothScroller.mRecyclerView = recyclerView;
            linearSmoothScroller.mLayoutManager = this;
            int i = linearSmoothScroller.mTargetPosition;
            if (i == -1) {
                throw new IllegalArgumentException("Invalid target position");
            }
            recyclerView.mState.mTargetPosition = i;
            linearSmoothScroller.mRunning = true;
            linearSmoothScroller.mPendingInitialRun = true;
            linearSmoothScroller.mTargetView = linearSmoothScroller.mRecyclerView.mLayout.findViewByPosition(i);
            linearSmoothScroller.mRecyclerView.mViewFlinger.postOnAnimation();
            linearSmoothScroller.mStarted = true;
        }

        public boolean supportsPredictiveItemAnimations() {
            return this instanceof androidx.leanback.widget.GridLayoutManager;
        }

        public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
            onItemsUpdated(i, i2);
        }

        public boolean performAccessibilityAction(Recycler recycler, State state, int i, Bundle bundle) {
            int paddingTop;
            int paddingLeft;
            float f;
            if (this.mRecyclerView == null) {
                return false;
            }
            int i2 = this.mHeight;
            int i3 = this.mWidth;
            Rect rect = new Rect();
            if (this.mRecyclerView.getMatrix().isIdentity() && this.mRecyclerView.getGlobalVisibleRect(rect)) {
                i2 = rect.height();
                i3 = rect.width();
            }
            if (i == 4096) {
                paddingTop = this.mRecyclerView.canScrollVertically(1) ? (i2 - getPaddingTop()) - getPaddingBottom() : 0;
                if (this.mRecyclerView.canScrollHorizontally(1)) {
                    paddingLeft = (i3 - getPaddingLeft()) - getPaddingRight();
                }
                paddingLeft = 0;
            } else if (i != 8192) {
                paddingTop = 0;
                paddingLeft = 0;
            } else {
                paddingTop = this.mRecyclerView.canScrollVertically(-1) ? -((i2 - getPaddingTop()) - getPaddingBottom()) : 0;
                if (this.mRecyclerView.canScrollHorizontally(-1)) {
                    paddingLeft = -((i3 - getPaddingLeft()) - getPaddingRight());
                }
                paddingLeft = 0;
            }
            if (paddingTop == 0 && paddingLeft == 0) {
                return false;
            }
            if (bundle != null) {
                f = bundle.getFloat("androidx.core.view.accessibility.action.ARGUMENT_SCROLL_AMOUNT_FLOAT", 1.0f);
                if (f < 0.0f) {
                    int[] iArr = RecyclerView.NESTED_SCROLLING_ATTRS;
                    return false;
                }
            } else {
                f = 1.0f;
            }
            if (Float.compare(f, Float.POSITIVE_INFINITY) != 0) {
                if (Float.compare(1.0f, f) != 0 && Float.compare(0.0f, f) != 0) {
                    paddingLeft = (int) (paddingLeft * f);
                    paddingTop = (int) (paddingTop * f);
                }
                this.mRecyclerView.smoothScrollBy$1(paddingLeft, paddingTop, true);
                return true;
            }
            RecyclerView recyclerView = this.mRecyclerView;
            Adapter adapter = recyclerView.mAdapter;
            if (adapter == null) {
                return false;
            }
            if (i == 4096) {
                recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
            } else if (i == 8192) {
                recyclerView.smoothScrollToPosition(0);
            }
            return true;
        }

        public LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
            return new LayoutParams(context, attributeSet);
        }

        public void onInitializeAccessibilityNodeInfoForItem(Recycler recycler, State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(false, canScrollVertically() ? getPosition(view) : 0, 1, canScrollHorizontally() ? getPosition(view) : 0, 1));
        }

        public void onItemsChanged() {
        }

        public void onAdapterChanged(Adapter adapter) {
        }

        public void onAttachedToWindow(RecyclerView recyclerView) {
        }

        public void onDetachedFromWindow(RecyclerView recyclerView) {
        }

        public void onRestoreInstanceState(Parcelable parcelable) {
        }

        public void onScrollStateChanged(int i) {
        }

        public void collectInitialPrefetchPositions(int i, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        }

        public void onItemsAdded(int i, int i2) {
        }

        public void onItemsMoved(int i, int i2) {
        }

        public void onItemsRemoved(int i, int i2) {
        }

        public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z) {
            return requestChildRectangleOnScreen(recyclerView, view, rect, z, false);
        }

        public void collectAdjacentPrefetchPositions(int i, int i2, State state, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnChildAttachStateChangeListener {
        void onChildViewAttachedToWindow(View view);

        void onChildViewDetachedFromWindow(View view);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnItemTouchListener {
        boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent);

        void onRequestDisallowInterceptTouchEvent(boolean z);

        void onTouchEvent(MotionEvent motionEvent);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RecycledViewPool {
        public int mAttachCountForClearing;
        public Set mAttachedAdaptersForPoolingContainer;
        public SparseArray mScrap;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class ScrapData {
            public final ArrayList mScrapHeap = new ArrayList();
            public final int mMaxScrap = 5;
            public long mCreateRunningAverageNs = 0;
            public long mBindRunningAverageNs = 0;
        }

        public final ScrapData getScrapDataForType(int i) {
            ScrapData scrapData = (ScrapData) this.mScrap.get(i);
            if (scrapData != null) {
                return scrapData;
            }
            ScrapData scrapData2 = new ScrapData();
            this.mScrap.put(i, scrapData2);
            return scrapData2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Recycler {
        public final ArrayList mAttachedScrap;
        public final ArrayList mCachedViews;
        public ArrayList mChangedScrap;
        public RecycledViewPool mRecyclerPool;
        public final int mRequestedCacheMax;
        public final List mUnmodifiableAttachedScrap;
        public int mViewCacheMax;

        public Recycler() {
            ArrayList arrayList = new ArrayList();
            this.mAttachedScrap = arrayList;
            this.mChangedScrap = null;
            this.mCachedViews = new ArrayList();
            this.mUnmodifiableAttachedScrap = Collections.unmodifiableList(arrayList);
            this.mRequestedCacheMax = 2;
            this.mViewCacheMax = 2;
        }

        public final void addViewHolderToRecycledViewPool(ViewHolder viewHolder, boolean z) {
            RecyclerView.clearNestedRecyclerViewIfNotNested(viewHolder);
            View view = viewHolder.itemView;
            RecyclerView recyclerView = RecyclerView.this;
            RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = recyclerView.mAccessibilityDelegate;
            if (recyclerViewAccessibilityDelegate != null) {
                AccessibilityDelegateCompat itemDelegate = recyclerViewAccessibilityDelegate.getItemDelegate();
                ViewCompat.setAccessibilityDelegate(view, itemDelegate instanceof RecyclerViewAccessibilityDelegate.ItemDelegate ? (AccessibilityDelegateCompat) ((RecyclerViewAccessibilityDelegate.ItemDelegate) itemDelegate).mOriginalItemDelegates.remove(view) : null);
            }
            if (z) {
                if (((ArrayList) recyclerView.mRecyclerListeners).size() > 0) {
                    ((ArrayList) recyclerView.mRecyclerListeners).get(0).getClass();
                    throw new ClassCastException();
                }
                Adapter adapter = recyclerView.mAdapter;
                if (adapter != null) {
                    adapter.onViewRecycled(viewHolder);
                }
                if (recyclerView.mState != null) {
                    recyclerView.mViewInfoStore.removeViewHolder(viewHolder);
                }
            }
            viewHolder.mBindingAdapter = null;
            viewHolder.mOwnerRecyclerView = null;
            RecycledViewPool recycledViewPool = getRecycledViewPool();
            recycledViewPool.getClass();
            int i = viewHolder.mItemViewType;
            ArrayList arrayList = recycledViewPool.getScrapDataForType(i).mScrapHeap;
            if (((RecycledViewPool.ScrapData) recycledViewPool.mScrap.get(i)).mMaxScrap <= arrayList.size()) {
                PoolingContainer.callPoolingContainerOnRelease(viewHolder.itemView);
            } else {
                viewHolder.resetInternal();
                arrayList.add(viewHolder);
            }
        }

        public final int convertPreLayoutPositionToPostLayout(int i) {
            RecyclerView recyclerView = RecyclerView.this;
            if (i >= 0 && i < recyclerView.mState.getItemCount()) {
                return !recyclerView.mState.mInPreLayout ? i : recyclerView.mAdapterHelper.findPositionOffset(i, 0);
            }
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("invalid position ", ". State item count is ", i);
            m.append(recyclerView.mState.getItemCount());
            m.append(recyclerView.exceptionLabel());
            throw new IndexOutOfBoundsException(m.toString());
        }

        public final RecycledViewPool getRecycledViewPool() {
            if (this.mRecyclerPool == null) {
                RecycledViewPool recycledViewPool = new RecycledViewPool();
                recycledViewPool.mScrap = new SparseArray();
                recycledViewPool.mAttachCountForClearing = 0;
                recycledViewPool.mAttachedAdaptersForPoolingContainer = Collections.newSetFromMap(new IdentityHashMap());
                this.mRecyclerPool = recycledViewPool;
                maybeSendPoolingContainerAttach();
            }
            return this.mRecyclerPool;
        }

        public final void maybeSendPoolingContainerAttach() {
            RecyclerView recyclerView;
            Adapter adapter;
            RecycledViewPool recycledViewPool = this.mRecyclerPool;
            if (recycledViewPool == null || (adapter = (recyclerView = RecyclerView.this).mAdapter) == null || !recyclerView.mIsAttached) {
                return;
            }
            recycledViewPool.mAttachedAdaptersForPoolingContainer.add(adapter);
        }

        public final void poolingContainerDetach(Adapter adapter, boolean z) {
            RecycledViewPool recycledViewPool = this.mRecyclerPool;
            if (recycledViewPool != null) {
                recycledViewPool.mAttachedAdaptersForPoolingContainer.remove(adapter);
                if (recycledViewPool.mAttachedAdaptersForPoolingContainer.size() != 0 || z) {
                    return;
                }
                for (int i = 0; i < recycledViewPool.mScrap.size(); i++) {
                    SparseArray sparseArray = recycledViewPool.mScrap;
                    ArrayList arrayList = ((RecycledViewPool.ScrapData) sparseArray.get(sparseArray.keyAt(i))).mScrapHeap;
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        PoolingContainer.callPoolingContainerOnRelease(((ViewHolder) arrayList.get(i2)).itemView);
                    }
                }
            }
        }

        public final void recycleAndClearCachedViews() {
            for (int size = this.mCachedViews.size() - 1; size >= 0; size--) {
                recycleCachedViewAt(size);
            }
            this.mCachedViews.clear();
            if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = RecyclerView.this.mPrefetchRegistry;
                int[] iArr = layoutPrefetchRegistryImpl.mPrefetchArray;
                if (iArr != null) {
                    Arrays.fill(iArr, -1);
                }
                layoutPrefetchRegistryImpl.mCount = 0;
            }
        }

        public final void recycleCachedViewAt(int i) {
            int[] iArr = RecyclerView.NESTED_SCROLLING_ATTRS;
            addViewHolderToRecycledViewPool((ViewHolder) this.mCachedViews.get(i), true);
            this.mCachedViews.remove(i);
        }

        public final void recycleView(View view) {
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            boolean isTmpDetached = childViewHolderInt.isTmpDetached();
            RecyclerView recyclerView = RecyclerView.this;
            if (isTmpDetached) {
                recyclerView.removeDetachedView(view, false);
            }
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.mScrapContainer.unscrapView(childViewHolderInt);
            } else if (childViewHolderInt.wasReturnedFromScrap()) {
                childViewHolderInt.mFlags &= -33;
            }
            recycleViewHolderInternal(childViewHolderInt);
            if (recyclerView.mItemAnimator == null || childViewHolderInt.isRecyclable()) {
                return;
            }
            recyclerView.mItemAnimator.endAnimation(childViewHolderInt);
        }

        /* JADX WARN: Code restructure failed: missing block: B:65:0x00ac, code lost:
        
            r4 = r4 - 1;
         */
        /* JADX WARN: Removed duplicated region for block: B:72:0x00bd  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x00c2  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void recycleViewHolderInternal(androidx.recyclerview.widget.RecyclerView.ViewHolder r11) {
            /*
                Method dump skipped, instructions count: 317
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Recycler.recycleViewHolderInternal(androidx.recyclerview.widget.RecyclerView$ViewHolder):void");
        }

        public final void scrapView(View view) {
            DefaultItemAnimator defaultItemAnimator;
            ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            boolean hasAnyOfTheFlags = childViewHolderInt.hasAnyOfTheFlags(12);
            RecyclerView recyclerView = RecyclerView.this;
            if (!hasAnyOfTheFlags && childViewHolderInt.isUpdated() && (defaultItemAnimator = recyclerView.mItemAnimator) != null && childViewHolderInt.getUnmodifiedPayloads().isEmpty() && defaultItemAnimator.mSupportsChangeAnimations && !childViewHolderInt.isInvalid()) {
                if (this.mChangedScrap == null) {
                    this.mChangedScrap = new ArrayList();
                }
                childViewHolderInt.mScrapContainer = this;
                childViewHolderInt.mInChangeScrap = true;
                this.mChangedScrap.add(childViewHolderInt);
                return;
            }
            if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !recyclerView.mAdapter.mHasStableIds) {
                throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + recyclerView.exceptionLabel());
            }
            childViewHolderInt.mScrapContainer = this;
            childViewHolderInt.mInChangeScrap = false;
            this.mAttachedScrap.add(childViewHolderInt);
        }

        /* JADX WARN: Code restructure failed: missing block: B:253:0x0429, code lost:
        
            if (r10.isInvalid() == false) goto L234;
         */
        /* JADX WARN: Removed duplicated region for block: B:121:0x0229  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0081  */
        /* JADX WARN: Removed duplicated region for block: B:226:0x03ef  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0087  */
        /* JADX WARN: Removed duplicated region for block: B:235:0x0566  */
        /* JADX WARN: Removed duplicated region for block: B:238:0x058a A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:242:0x0572  */
        /* JADX WARN: Removed duplicated region for block: B:248:0x041a  */
        /* JADX WARN: Removed duplicated region for block: B:257:0x0448  */
        /* JADX WARN: Removed duplicated region for block: B:264:0x0464  */
        /* JADX WARN: Removed duplicated region for block: B:267:0x047f  */
        /* JADX WARN: Removed duplicated region for block: B:269:0x0484  */
        /* JADX WARN: Removed duplicated region for block: B:277:0x04bc  */
        /* JADX WARN: Removed duplicated region for block: B:285:0x04dd  */
        /* JADX WARN: Removed duplicated region for block: B:288:0x04f6  */
        /* JADX WARN: Removed duplicated region for block: B:295:0x050f  */
        /* JADX WARN: Removed duplicated region for block: B:314:0x055b  */
        /* JADX WARN: Removed duplicated region for block: B:317:0x0554  */
        /* JADX WARN: Removed duplicated region for block: B:319:0x0481  */
        /* JADX WARN: Removed duplicated region for block: B:320:0x0475  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final androidx.recyclerview.widget.RecyclerView.ViewHolder tryGetViewHolderForPositionByDeadline(long r24, int r26) {
            /*
                Method dump skipped, instructions count: 1460
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.Recycler.tryGetViewHolderForPositionByDeadline(long, int):androidx.recyclerview.widget.RecyclerView$ViewHolder");
        }

        public final void unscrapView(ViewHolder viewHolder) {
            if (viewHolder.mInChangeScrap) {
                this.mChangedScrap.remove(viewHolder);
            } else {
                this.mAttachedScrap.remove(viewHolder);
            }
            viewHolder.mScrapContainer = null;
            viewHolder.mInChangeScrap = false;
            viewHolder.mFlags &= -33;
        }

        public final void updateViewCacheSize() {
            LayoutManager layoutManager = RecyclerView.this.mLayout;
            this.mViewCacheMax = this.mRequestedCacheMax + (layoutManager != null ? layoutManager.mPrefetchMaxCountObserved : 0);
            for (int size = this.mCachedViews.size() - 1; size >= 0 && this.mCachedViews.size() > this.mViewCacheMax; size--) {
                recycleCachedViewAt(size);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RecyclerViewDataObserver extends AdapterDataObserver {
        public RecyclerViewDataObserver() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onChanged() {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.assertNotInLayoutOrScroll(null);
            recyclerView.mState.mStructureChanged = true;
            recyclerView.processDataSetCompletelyChanged(true);
            if (recyclerView.mAdapterHelper.hasPendingUpdates()) {
                return;
            }
            recyclerView.requestLayout();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2, Object obj) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.assertNotInLayoutOrScroll(null);
            AdapterHelper adapterHelper = recyclerView.mAdapterHelper;
            if (i2 < 1) {
                adapterHelper.getClass();
                return;
            }
            adapterHelper.mPendingUpdates.add(adapterHelper.obtainUpdateOp(obj, 4, i, i2));
            adapterHelper.mExistingUpdateTypes |= 4;
            if (adapterHelper.mPendingUpdates.size() == 1) {
                triggerUpdateProcessor();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeInserted(int i, int i2) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.assertNotInLayoutOrScroll(null);
            AdapterHelper adapterHelper = recyclerView.mAdapterHelper;
            if (i2 < 1) {
                adapterHelper.getClass();
                return;
            }
            adapterHelper.mPendingUpdates.add(adapterHelper.obtainUpdateOp(null, 1, i, i2));
            adapterHelper.mExistingUpdateTypes |= 1;
            if (adapterHelper.mPendingUpdates.size() == 1) {
                triggerUpdateProcessor();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeMoved(int i, int i2) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.assertNotInLayoutOrScroll(null);
            AdapterHelper adapterHelper = recyclerView.mAdapterHelper;
            adapterHelper.getClass();
            if (i == i2) {
                return;
            }
            adapterHelper.mPendingUpdates.add(adapterHelper.obtainUpdateOp(null, 8, i, i2));
            adapterHelper.mExistingUpdateTypes |= 8;
            if (adapterHelper.mPendingUpdates.size() == 1) {
                triggerUpdateProcessor();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeRemoved(int i, int i2) {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.assertNotInLayoutOrScroll(null);
            AdapterHelper adapterHelper = recyclerView.mAdapterHelper;
            if (i2 < 1) {
                adapterHelper.getClass();
                return;
            }
            adapterHelper.mPendingUpdates.add(adapterHelper.obtainUpdateOp(null, 2, i, i2));
            adapterHelper.mExistingUpdateTypes |= 2;
            if (adapterHelper.mPendingUpdates.size() == 1) {
                triggerUpdateProcessor();
            }
        }

        public final void triggerUpdateProcessor() {
            RecyclerView recyclerView = RecyclerView.this;
            recyclerView.mAdapterUpdateDuringMeasure = true;
            recyclerView.requestLayout();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new AnonymousClass1();
        public Parcelable mLayoutState;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: androidx.recyclerview.widget.RecyclerView$SavedState$1, reason: invalid class name */
        public final class AnonymousClass1 implements Parcelable.ClassLoaderCreator {
            @Override // android.os.Parcelable.ClassLoaderCreator
            public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.mLayoutState = parcel.readParcelable(classLoader == null ? LayoutManager.class.getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.mLayoutState, 0);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class State {
        public long mFocusedItemId;
        public int mFocusedItemPosition;
        public int mFocusedSubChildId;
        public int mTargetPosition = -1;
        public int mPreviousLayoutItemCount = 0;
        public int mDeletedInvisibleItemCountSincePreviousLayout = 0;
        public int mLayoutStep = 1;
        public int mItemCount = 0;
        public boolean mStructureChanged = false;
        public boolean mInPreLayout = false;
        public boolean mTrackOldChangeHolders = false;
        public boolean mIsMeasuring = false;
        public boolean mRunSimpleAnimations = false;
        public boolean mRunPredictiveAnimations = false;

        public final void assertLayoutStep(int i) {
            if ((this.mLayoutStep & i) != 0) {
                return;
            }
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i) + " but it is " + Integer.toBinaryString(this.mLayoutStep));
        }

        public final int getItemCount() {
            return this.mInPreLayout ? this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout : this.mItemCount;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("State{mTargetPosition=");
            sb.append(this.mTargetPosition);
            sb.append(", mData=null, mItemCount=");
            sb.append(this.mItemCount);
            sb.append(", mIsMeasuring=");
            sb.append(this.mIsMeasuring);
            sb.append(", mPreviousLayoutItemCount=");
            sb.append(this.mPreviousLayoutItemCount);
            sb.append(", mDeletedInvisibleItemCountSincePreviousLayout=");
            sb.append(this.mDeletedInvisibleItemCountSincePreviousLayout);
            sb.append(", mStructureChanged=");
            sb.append(this.mStructureChanged);
            sb.append(", mInPreLayout=");
            sb.append(this.mInPreLayout);
            sb.append(", mRunSimpleAnimations=");
            sb.append(this.mRunSimpleAnimations);
            sb.append(", mRunPredictiveAnimations=");
            return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.mRunPredictiveAnimations, '}');
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StretchEdgeEffectFactory {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ViewFlinger implements Runnable {
        public boolean mEatRunOnAnimationRequest;
        public Interpolator mInterpolator;
        public int mLastFlingX;
        public int mLastFlingY;
        public OverScroller mOverScroller;
        public boolean mReSchedulePostAnimationCallback;

        public ViewFlinger() {
            AnonymousClass3 anonymousClass3 = RecyclerView.sQuinticInterpolator;
            this.mInterpolator = anonymousClass3;
            this.mEatRunOnAnimationRequest = false;
            this.mReSchedulePostAnimationCallback = false;
            this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), anonymousClass3);
        }

        public final void fling(int i, int i2) {
            RecyclerView.this.setScrollState(2);
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            Interpolator interpolator = this.mInterpolator;
            AnonymousClass3 anonymousClass3 = RecyclerView.sQuinticInterpolator;
            if (interpolator != anonymousClass3) {
                this.mInterpolator = anonymousClass3;
                this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), anonymousClass3);
            }
            this.mOverScroller.fling(0, 0, i, i2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            postOnAnimation();
        }

        public final void postOnAnimation() {
            if (this.mEatRunOnAnimationRequest) {
                this.mReSchedulePostAnimationCallback = true;
                return;
            }
            RecyclerView.this.removeCallbacks(this);
            RecyclerView recyclerView = RecyclerView.this;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            recyclerView.postOnAnimation(this);
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i;
            int i2;
            RecyclerView recyclerView = RecyclerView.this;
            if (recyclerView.mLayout == null) {
                recyclerView.removeCallbacks(this);
                this.mOverScroller.abortAnimation();
                return;
            }
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
            recyclerView.consumePendingUpdateOperations();
            OverScroller overScroller = this.mOverScroller;
            if (overScroller.computeScrollOffset()) {
                int currX = overScroller.getCurrX();
                int currY = overScroller.getCurrY();
                int i3 = currX - this.mLastFlingX;
                int i4 = currY - this.mLastFlingY;
                this.mLastFlingX = currX;
                this.mLastFlingY = currY;
                RecyclerView recyclerView2 = RecyclerView.this;
                int consumeFlingInStretch = RecyclerView.consumeFlingInStretch(i3, recyclerView2.mLeftGlow, recyclerView2.mRightGlow, recyclerView2.getWidth());
                RecyclerView recyclerView3 = RecyclerView.this;
                int consumeFlingInStretch2 = RecyclerView.consumeFlingInStretch(i4, recyclerView3.mTopGlow, recyclerView3.mBottomGlow, recyclerView3.getHeight());
                RecyclerView recyclerView4 = RecyclerView.this;
                int[] iArr = recyclerView4.mReusableIntPair;
                iArr[0] = 0;
                iArr[1] = 0;
                if (recyclerView4.getScrollingChildHelper().dispatchNestedPreScroll(consumeFlingInStretch, consumeFlingInStretch2, 1, iArr, null)) {
                    int[] iArr2 = RecyclerView.this.mReusableIntPair;
                    consumeFlingInStretch -= iArr2[0];
                    consumeFlingInStretch2 -= iArr2[1];
                }
                if (RecyclerView.this.getOverScrollMode() != 2) {
                    RecyclerView.this.considerReleasingGlowsOnScroll(consumeFlingInStretch, consumeFlingInStretch2);
                }
                RecyclerView recyclerView5 = RecyclerView.this;
                if (recyclerView5.mAdapter != null) {
                    int[] iArr3 = recyclerView5.mReusableIntPair;
                    iArr3[0] = 0;
                    iArr3[1] = 0;
                    recyclerView5.scrollStep(consumeFlingInStretch, consumeFlingInStretch2, iArr3);
                    RecyclerView recyclerView6 = RecyclerView.this;
                    int[] iArr4 = recyclerView6.mReusableIntPair;
                    i2 = iArr4[0];
                    i = iArr4[1];
                    consumeFlingInStretch -= i2;
                    consumeFlingInStretch2 -= i;
                    LinearSmoothScroller linearSmoothScroller = recyclerView6.mLayout.mSmoothScroller;
                    if (linearSmoothScroller != null && !linearSmoothScroller.mPendingInitialRun && linearSmoothScroller.mRunning) {
                        int itemCount = recyclerView6.mState.getItemCount();
                        if (itemCount == 0) {
                            linearSmoothScroller.stop();
                        } else if (linearSmoothScroller.mTargetPosition >= itemCount) {
                            linearSmoothScroller.mTargetPosition = itemCount - 1;
                            linearSmoothScroller.onAnimation(i2, i);
                        } else {
                            linearSmoothScroller.onAnimation(i2, i);
                        }
                    }
                } else {
                    i = 0;
                    i2 = 0;
                }
                if (!RecyclerView.this.mItemDecorations.isEmpty()) {
                    RecyclerView.this.invalidate();
                }
                RecyclerView recyclerView7 = RecyclerView.this;
                int[] iArr5 = recyclerView7.mReusableIntPair;
                iArr5[0] = 0;
                iArr5[1] = 0;
                recyclerView7.getScrollingChildHelper().dispatchNestedScrollInternal(i2, i, consumeFlingInStretch, consumeFlingInStretch2, null, 1, iArr5);
                RecyclerView recyclerView8 = RecyclerView.this;
                int[] iArr6 = recyclerView8.mReusableIntPair;
                int i5 = consumeFlingInStretch - iArr6[0];
                int i6 = consumeFlingInStretch2 - iArr6[1];
                if (i2 != 0 || i != 0) {
                    recyclerView8.dispatchOnScrolled(i2, i);
                }
                if (!RecyclerView.this.awakenScrollBars()) {
                    RecyclerView.this.invalidate();
                }
                boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i5 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i6 != 0));
                RecyclerView recyclerView9 = RecyclerView.this;
                LinearSmoothScroller linearSmoothScroller2 = recyclerView9.mLayout.mSmoothScroller;
                if ((linearSmoothScroller2 == null || !linearSmoothScroller2.mPendingInitialRun) && z) {
                    if (recyclerView9.getOverScrollMode() != 2) {
                        int currVelocity = (int) overScroller.getCurrVelocity();
                        int i7 = i5 < 0 ? -currVelocity : i5 > 0 ? currVelocity : 0;
                        if (i6 < 0) {
                            currVelocity = -currVelocity;
                        } else if (i6 <= 0) {
                            currVelocity = 0;
                        }
                        RecyclerView recyclerView10 = RecyclerView.this;
                        if (i7 < 0) {
                            recyclerView10.ensureLeftGlow();
                            if (recyclerView10.mLeftGlow.isFinished()) {
                                recyclerView10.mLeftGlow.onAbsorb(-i7);
                            }
                        } else if (i7 > 0) {
                            recyclerView10.ensureRightGlow();
                            if (recyclerView10.mRightGlow.isFinished()) {
                                recyclerView10.mRightGlow.onAbsorb(i7);
                            }
                        }
                        if (currVelocity < 0) {
                            recyclerView10.ensureTopGlow();
                            if (recyclerView10.mTopGlow.isFinished()) {
                                recyclerView10.mTopGlow.onAbsorb(-currVelocity);
                            }
                        } else if (currVelocity > 0) {
                            recyclerView10.ensureBottomGlow();
                            if (recyclerView10.mBottomGlow.isFinished()) {
                                recyclerView10.mBottomGlow.onAbsorb(currVelocity);
                            }
                        }
                        if (i7 != 0 || currVelocity != 0) {
                            recyclerView10.postInvalidateOnAnimation();
                        }
                    }
                    if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                        GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = RecyclerView.this.mPrefetchRegistry;
                        int[] iArr7 = layoutPrefetchRegistryImpl.mPrefetchArray;
                        if (iArr7 != null) {
                            Arrays.fill(iArr7, -1);
                        }
                        layoutPrefetchRegistryImpl.mCount = 0;
                    }
                } else {
                    postOnAnimation();
                    RecyclerView recyclerView11 = RecyclerView.this;
                    GapWorker gapWorker = recyclerView11.mGapWorker;
                    if (gapWorker != null) {
                        gapWorker.postFromTraversal(recyclerView11, i2, i);
                    }
                }
                try {
                    RecyclerView.this.setFrameContentVelocity(Math.abs(overScroller.getCurrVelocity()));
                } catch (LinkageError unused) {
                }
            }
            LinearSmoothScroller linearSmoothScroller3 = RecyclerView.this.mLayout.mSmoothScroller;
            if (linearSmoothScroller3 != null && linearSmoothScroller3.mPendingInitialRun) {
                linearSmoothScroller3.onAnimation(0, 0);
            }
            this.mEatRunOnAnimationRequest = false;
            if (!this.mReSchedulePostAnimationCallback) {
                RecyclerView.this.setScrollState(0);
                RecyclerView.this.getScrollingChildHelper().stopNestedScroll(1);
            } else {
                RecyclerView.this.removeCallbacks(this);
                RecyclerView recyclerView12 = RecyclerView.this;
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                recyclerView12.postOnAnimation(this);
            }
        }

        public final void smoothScrollBy(int i, int i2, int i3, Interpolator interpolator) {
            if (i3 == Integer.MIN_VALUE) {
                int abs = Math.abs(i);
                int abs2 = Math.abs(i2);
                boolean z = abs > abs2;
                RecyclerView recyclerView = RecyclerView.this;
                int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
                if (!z) {
                    abs = abs2;
                }
                i3 = Math.min((int) (((abs / width) + 1.0f) * 300.0f), 2000);
            }
            int i4 = i3;
            if (interpolator == null) {
                interpolator = RecyclerView.sQuinticInterpolator;
            }
            if (this.mInterpolator != interpolator) {
                this.mInterpolator = interpolator;
                this.mOverScroller = new OverScroller(RecyclerView.this.getContext(), interpolator);
            }
            this.mLastFlingY = 0;
            this.mLastFlingX = 0;
            RecyclerView.this.setScrollState(2);
            this.mOverScroller.startScroll(0, 0, i, i2, i4);
            postOnAnimation();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class ViewHolder {
        public static final List FULLUPDATE_PAYLOADS = Collections.emptyList();
        public final View itemView;
        public Adapter mBindingAdapter;
        public int mFlags;
        public WeakReference mNestedRecyclerView;
        public RecyclerView mOwnerRecyclerView;
        public int mPosition = -1;
        public int mOldPosition = -1;
        public long mItemId = -1;
        public int mItemViewType = -1;
        public int mPreLayoutPosition = -1;
        public ViewHolder mShadowedHolder = null;
        public ViewHolder mShadowingHolder = null;
        public List mPayloads = null;
        public List mUnmodifiedPayloads = null;
        public int mIsRecyclableCount = 0;
        public Recycler mScrapContainer = null;
        public boolean mInChangeScrap = false;
        public int mWasImportantForAccessibilityBeforeHidden = 0;
        int mPendingAccessibilityState = -1;

        public ViewHolder(View view) {
            if (view == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = view;
        }

        public final void addFlags(int i) {
            this.mFlags = i | this.mFlags;
        }

        public final int getAbsoluteAdapterPosition() {
            RecyclerView recyclerView = this.mOwnerRecyclerView;
            if (recyclerView == null) {
                return -1;
            }
            return recyclerView.getAdapterPositionInRecyclerView(this);
        }

        public final int getBindingAdapterPosition() {
            RecyclerView recyclerView;
            Adapter adapter;
            int adapterPositionInRecyclerView;
            if (this.mBindingAdapter == null || (recyclerView = this.mOwnerRecyclerView) == null || (adapter = recyclerView.mAdapter) == null || (adapterPositionInRecyclerView = recyclerView.getAdapterPositionInRecyclerView(this)) == -1 || this.mBindingAdapter != adapter) {
                return -1;
            }
            return adapterPositionInRecyclerView;
        }

        public final int getLayoutPosition() {
            int i = this.mPreLayoutPosition;
            return i == -1 ? this.mPosition : i;
        }

        public final List getUnmodifiedPayloads() {
            if ((this.mFlags & 1024) != 0) {
                return FULLUPDATE_PAYLOADS;
            }
            List list = this.mPayloads;
            return (list == null || ((ArrayList) list).size() == 0) ? FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads;
        }

        public final boolean hasAnyOfTheFlags(int i) {
            return (this.mFlags & i) != 0;
        }

        public final boolean isAttachedToTransitionOverlay() {
            return (this.itemView.getParent() == null || this.itemView.getParent() == this.mOwnerRecyclerView) ? false : true;
        }

        public final boolean isBound() {
            return (this.mFlags & 1) != 0;
        }

        public final boolean isInvalid() {
            return (this.mFlags & 4) != 0;
        }

        public final boolean isRecyclable() {
            if ((this.mFlags & 16) == 0) {
                View view = this.itemView;
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                if (!view.hasTransientState()) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isRemoved() {
            return (this.mFlags & 8) != 0;
        }

        public final boolean isScrap() {
            return this.mScrapContainer != null;
        }

        public final boolean isTmpDetached() {
            return (this.mFlags & 256) != 0;
        }

        public final boolean isUpdated() {
            return (this.mFlags & 2) != 0;
        }

        public final void offsetPosition(int i, boolean z) {
            if (this.mOldPosition == -1) {
                this.mOldPosition = this.mPosition;
            }
            if (this.mPreLayoutPosition == -1) {
                this.mPreLayoutPosition = this.mPosition;
            }
            if (z) {
                this.mPreLayoutPosition += i;
            }
            this.mPosition += i;
            if (this.itemView.getLayoutParams() != null) {
                ((LayoutParams) this.itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }

        public final void resetInternal() {
            int[] iArr = RecyclerView.NESTED_SCROLLING_ATTRS;
            this.mFlags = 0;
            this.mPosition = -1;
            this.mOldPosition = -1;
            this.mItemId = -1L;
            this.mPreLayoutPosition = -1;
            this.mIsRecyclableCount = 0;
            this.mShadowedHolder = null;
            this.mShadowingHolder = null;
            List list = this.mPayloads;
            if (list != null) {
                list.clear();
            }
            this.mFlags &= -1025;
            this.mWasImportantForAccessibilityBeforeHidden = 0;
            this.mPendingAccessibilityState = -1;
            RecyclerView.clearNestedRecyclerViewIfNotNested(this);
        }

        public final void setIsRecyclable(boolean z) {
            int i = this.mIsRecyclableCount;
            int i2 = z ? i - 1 : i + 1;
            this.mIsRecyclableCount = i2;
            if (i2 < 0) {
                this.mIsRecyclableCount = 0;
                int[] iArr = RecyclerView.NESTED_SCROLLING_ATTRS;
                Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            } else if (!z && i2 == 1) {
                this.mFlags |= 16;
            } else if (z && i2 == 0) {
                this.mFlags &= -17;
            }
            int[] iArr2 = RecyclerView.NESTED_SCROLLING_ATTRS;
        }

        public final boolean shouldIgnore() {
            return (this.mFlags & 128) != 0;
        }

        public final String toString() {
            StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName(), "{");
            m.append(Integer.toHexString(hashCode()));
            m.append(" position=");
            m.append(this.mPosition);
            m.append(" id=");
            m.append(this.mItemId);
            m.append(", oldPos=");
            m.append(this.mOldPosition);
            m.append(", pLpos:");
            m.append(this.mPreLayoutPosition);
            StringBuilder sb = new StringBuilder(m.toString());
            if (isScrap()) {
                sb.append(" scrap ");
                sb.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) {
                sb.append(" invalid");
            }
            if (!isBound()) {
                sb.append(" unbound");
            }
            if ((this.mFlags & 2) != 0) {
                sb.append(" update");
            }
            if (isRemoved()) {
                sb.append(" removed");
            }
            if (shouldIgnore()) {
                sb.append(" ignored");
            }
            if (isTmpDetached()) {
                sb.append(" tmpDetached");
            }
            if (!isRecyclable()) {
                sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
            }
            if ((this.mFlags & 512) != 0 || isInvalid()) {
                sb.append(" undefined adapter position");
            }
            if (this.itemView.getParent() == null) {
                sb.append(" no parent");
            }
            sb.append("}");
            return sb.toString();
        }

        public final boolean wasReturnedFromScrap() {
            return (this.mFlags & 32) != 0;
        }
    }

    static {
        Class cls = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, cls, cls};
        sQuinticInterpolator = new AnonymousClass3();
        sDefaultEdgeEffectFactory = new StretchEdgeEffectFactory();
    }

    public RecyclerView(Context context) {
        this(context, null);
    }

    public static void clearNestedRecyclerViewIfNotNested(ViewHolder viewHolder) {
        WeakReference weakReference = viewHolder.mNestedRecyclerView;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            while (view != null) {
                if (view == viewHolder.itemView) {
                    return;
                }
                Object parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
            viewHolder.mNestedRecyclerView = null;
        }
    }

    public static int consumeFlingInStretch(int i, EdgeEffect edgeEffect, EdgeEffect edgeEffect2, int i2) {
        if (i > 0 && edgeEffect != null && EdgeEffectCompat$Api31Impl.getDistance(edgeEffect) != 0.0f) {
            int round = Math.round(EdgeEffectCompat$Api31Impl.onPullDistance(edgeEffect, ((-i) * 4.0f) / i2, 0.5f) * ((-i2) / 4.0f));
            if (round != i) {
                edgeEffect.finish();
            }
            return i - round;
        }
        if (i >= 0 || edgeEffect2 == null || EdgeEffectCompat$Api31Impl.getDistance(edgeEffect2) == 0.0f) {
            return i;
        }
        float f = i2;
        int round2 = Math.round(EdgeEffectCompat$Api31Impl.onPullDistance(edgeEffect2, (i * 4.0f) / f, 0.5f) * (f / 4.0f));
        if (round2 != i) {
            edgeEffect2.finish();
        }
        return i - round2;
    }

    public static RecyclerView findNestedRecyclerView(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RecyclerView findNestedRecyclerView = findNestedRecyclerView(viewGroup.getChildAt(i));
            if (findNestedRecyclerView != null) {
                return findNestedRecyclerView;
            }
        }
        return null;
    }

    public static int getChildAdapterPosition(View view) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getAbsoluteAdapterPosition();
        }
        return -1;
    }

    public static ViewHolder getChildViewHolderInt(View view) {
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).mViewHolder;
    }

    public final void addAnimatingView(ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        boolean z = view.getParent() == this;
        this.mRecycler.unscrapView(getChildViewHolder(view));
        if (viewHolder.isTmpDetached()) {
            this.mChildHelper.attachViewToParent(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!z) {
            this.mChildHelper.addView(-1, view, true);
            return;
        }
        ChildHelper childHelper = this.mChildHelper;
        int indexOfChild = RecyclerView.this.indexOfChild(view);
        if (indexOfChild >= 0) {
            childHelper.mBucket.set(indexOfChild);
            childHelper.hideViewInternal(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList arrayList, int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || !layoutManager.onAddFocusables(this, arrayList, i, i2)) {
            super.addFocusables(arrayList, i, i2);
        }
    }

    public final void addItemDecoration(ItemDecoration itemDecoration) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        this.mItemDecorations.add(itemDecoration);
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public final void addOnScrollListener(OnScrollListener onScrollListener) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(onScrollListener);
    }

    public final void assertNotInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + exceptionLabel());
        }
        if (this.mDispatchScrollCounter > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + exceptionLabel()));
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && this.mLayout.checkLayoutParams((LayoutParams) layoutParams);
    }

    public final void clearOldPositions() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.mOldPosition = -1;
                childViewHolderInt.mPreLayoutPosition = -1;
            }
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            ViewHolder viewHolder = (ViewHolder) recycler.mCachedViews.get(i2);
            viewHolder.mOldPosition = -1;
            viewHolder.mPreLayoutPosition = -1;
        }
        int size2 = recycler.mAttachedScrap.size();
        for (int i3 = 0; i3 < size2; i3++) {
            ViewHolder viewHolder2 = (ViewHolder) recycler.mAttachedScrap.get(i3);
            viewHolder2.mOldPosition = -1;
            viewHolder2.mPreLayoutPosition = -1;
        }
        ArrayList arrayList = recycler.mChangedScrap;
        if (arrayList != null) {
            int size3 = arrayList.size();
            for (int i4 = 0; i4 < size3; i4++) {
                ViewHolder viewHolder3 = (ViewHolder) recycler.mChangedScrap.get(i4);
                viewHolder3.mOldPosition = -1;
                viewHolder3.mPreLayoutPosition = -1;
            }
        }
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollExtent(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollOffset(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollRange(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollExtent(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollOffset(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null && layoutManager.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollRange(this.mState);
        }
        return 0;
    }

    public final void considerReleasingGlowsOnScroll(int i, int i2) {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished() || i <= 0) {
            z = false;
        } else {
            this.mLeftGlow.onRelease();
            z = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i < 0) {
            this.mRightGlow.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i2 > 0) {
            this.mTopGlow.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i2 < 0) {
            this.mBottomGlow.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            postInvalidateOnAnimation();
        }
    }

    public final void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            Trace.beginSection("RV FullInvalidate");
            dispatchLayout();
            Trace.endSection();
            return;
        }
        if (this.mAdapterHelper.hasPendingUpdates()) {
            AdapterHelper adapterHelper = this.mAdapterHelper;
            int i = adapterHelper.mExistingUpdateTypes;
            if ((i & 4) == 0 || (i & 11) != 0) {
                if (adapterHelper.hasPendingUpdates()) {
                    Trace.beginSection("RV FullInvalidate");
                    dispatchLayout();
                    Trace.endSection();
                    return;
                }
                return;
            }
            Trace.beginSection("RV PartialInvalidate");
            startInterceptRequestLayout();
            onEnterLayoutOrScroll();
            this.mAdapterHelper.preProcess();
            if (!this.mLayoutWasDefered) {
                int childCount = this.mChildHelper.getChildCount();
                int i2 = 0;
                while (true) {
                    if (i2 < childCount) {
                        ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i2));
                        if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                            dispatchLayout();
                            break;
                        }
                        i2++;
                    } else {
                        this.mAdapterHelper.consumePostponedUpdates();
                        break;
                    }
                }
            }
            stopInterceptRequestLayout(true);
            onExitLayoutOrScroll(true);
            Trace.endSection();
        }
    }

    public final void defaultOnMeasure(int i, int i2) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        setMeasuredDimension(LayoutManager.chooseSize(i, paddingRight, getMinimumWidth()), LayoutManager.chooseSize(i2, getPaddingBottom() + getPaddingTop(), getMinimumHeight()));
    }

    public final void dispatchChildDetached(View view) {
        getChildViewHolderInt(view);
        List list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = ((ArrayList) list).size() - 1; size >= 0; size--) {
                ((OnChildAttachStateChangeListener) ((ArrayList) this.mOnChildAttachStateListeners).get(size)).onChildViewDetachedFromWindow(view);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        LayoutManager layoutManager = this.mLayout;
        int i = 0;
        if (layoutManager == null) {
            return false;
        }
        if (layoutManager.canScrollVertically()) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 92 || keyCode == 93) {
                int measuredHeight = getMeasuredHeight();
                if (keyCode == 93) {
                    smoothScrollBy$1(0, measuredHeight, false);
                } else {
                    smoothScrollBy$1(0, -measuredHeight, false);
                }
                return true;
            }
            if (keyCode == 122 || keyCode == 123) {
                boolean isLayoutReversed = layoutManager.isLayoutReversed();
                if (keyCode == 122) {
                    if (isLayoutReversed) {
                        i = this.mAdapter.getItemCount();
                    }
                } else if (!isLayoutReversed) {
                    i = this.mAdapter.getItemCount();
                }
                smoothScrollToPosition(i);
                return true;
            }
        } else if (layoutManager.canScrollHorizontally()) {
            int keyCode2 = keyEvent.getKeyCode();
            if (keyCode2 == 92 || keyCode2 == 93) {
                int measuredWidth = getMeasuredWidth();
                if (keyCode2 == 93) {
                    smoothScrollBy$1(measuredWidth, 0, false);
                } else {
                    smoothScrollBy$1(-measuredWidth, 0, false);
                }
                return true;
            }
            if (keyCode2 == 122 || keyCode2 == 123) {
                boolean isLayoutReversed2 = layoutManager.isLayoutReversed();
                if (keyCode2 == 122) {
                    if (isLayoutReversed2) {
                        i = this.mAdapter.getItemCount();
                    }
                } else if (!isLayoutReversed2) {
                    i = this.mAdapter.getItemCount();
                }
                smoothScrollToPosition(i);
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:162:0x034c, code lost:
    
        if (r16.mChildHelper.mHiddenViews.contains(getFocusedChild()) == false) goto L220;
     */
    /* JADX WARN: Removed duplicated region for block: B:190:0x03fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void dispatchLayout() {
        /*
            Method dump skipped, instructions count: 1053
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.dispatchLayout():void");
    }

    public final void dispatchLayoutStep1() {
        ViewInfoStore.InfoRecord infoRecord;
        View findContainingItemView;
        this.mState.assertLayoutStep(1);
        fillRemainingScrollValues(this.mState);
        this.mState.mIsMeasuring = false;
        startInterceptRequestLayout();
        ViewInfoStore viewInfoStore = this.mViewInfoStore;
        viewInfoStore.mLayoutHolderMap.clear();
        viewInfoStore.mOldChangedHolders.clear();
        onEnterLayoutOrScroll();
        processAdapterUpdatesAndSetAnimationFlags();
        ViewHolder viewHolder = null;
        View focusedChild = (this.mPreserveFocusAfterLayout && hasFocus() && this.mAdapter != null) ? getFocusedChild() : null;
        if (focusedChild != null && (findContainingItemView = findContainingItemView(focusedChild)) != null) {
            viewHolder = getChildViewHolder(findContainingItemView);
        }
        if (viewHolder == null) {
            State state = this.mState;
            state.mFocusedItemId = -1L;
            state.mFocusedItemPosition = -1;
            state.mFocusedSubChildId = -1;
        } else {
            State state2 = this.mState;
            state2.mFocusedItemId = this.mAdapter.mHasStableIds ? viewHolder.mItemId : -1L;
            state2.mFocusedItemPosition = this.mDataSetHasChangedAfterLayout ? -1 : viewHolder.isRemoved() ? viewHolder.mOldPosition : viewHolder.getAbsoluteAdapterPosition();
            State state3 = this.mState;
            View view = viewHolder.itemView;
            int id = view.getId();
            while (!view.isFocused() && (view instanceof ViewGroup) && view.hasFocus()) {
                view = ((ViewGroup) view).getFocusedChild();
                if (view.getId() != -1) {
                    id = view.getId();
                }
            }
            state3.mFocusedSubChildId = id;
        }
        State state4 = this.mState;
        state4.mTrackOldChangeHolders = state4.mRunSimpleAnimations && this.mItemsChanged;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        state4.mInPreLayout = state4.mRunPredictiveAnimations;
        state4.mItemCount = this.mAdapter.getItemCount();
        findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
        if (this.mState.mRunSimpleAnimations) {
            int childCount = this.mChildHelper.getChildCount();
            for (int i = 0; i < childCount; i++) {
                ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.mHasStableIds)) {
                    DefaultItemAnimator defaultItemAnimator = this.mItemAnimator;
                    DefaultItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt);
                    childViewHolderInt.getUnmodifiedPayloads();
                    defaultItemAnimator.getClass();
                    RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo = new RecyclerView$ItemAnimator$ItemHolderInfo();
                    recyclerView$ItemAnimator$ItemHolderInfo.setFrom(childViewHolderInt);
                    SimpleArrayMap simpleArrayMap = this.mViewInfoStore.mLayoutHolderMap;
                    ViewInfoStore.InfoRecord infoRecord2 = (ViewInfoStore.InfoRecord) simpleArrayMap.get(childViewHolderInt);
                    if (infoRecord2 == null) {
                        infoRecord2 = ViewInfoStore.InfoRecord.obtain();
                        simpleArrayMap.put(childViewHolderInt, infoRecord2);
                    }
                    infoRecord2.preInfo = recyclerView$ItemAnimator$ItemHolderInfo;
                    infoRecord2.flags |= 4;
                    if (this.mState.mTrackOldChangeHolders && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore.mOldChangedHolders.put(getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.mRunPredictiveAnimations) {
            int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
            for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
                ViewHolder childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
                if (!childViewHolderInt2.shouldIgnore() && childViewHolderInt2.mOldPosition == -1) {
                    childViewHolderInt2.mOldPosition = childViewHolderInt2.mPosition;
                }
            }
            State state5 = this.mState;
            boolean z = state5.mStructureChanged;
            state5.mStructureChanged = false;
            this.mLayout.onLayoutChildren(this.mRecycler, state5);
            this.mState.mStructureChanged = z;
            for (int i3 = 0; i3 < this.mChildHelper.getChildCount(); i3++) {
                ViewHolder childViewHolderInt3 = getChildViewHolderInt(this.mChildHelper.getChildAt(i3));
                if (!childViewHolderInt3.shouldIgnore() && ((infoRecord = (ViewInfoStore.InfoRecord) this.mViewInfoStore.mLayoutHolderMap.get(childViewHolderInt3)) == null || (infoRecord.flags & 4) == 0)) {
                    DefaultItemAnimator.buildAdapterChangeFlagsForAnimations(childViewHolderInt3);
                    boolean hasAnyOfTheFlags = childViewHolderInt3.hasAnyOfTheFlags(8192);
                    DefaultItemAnimator defaultItemAnimator2 = this.mItemAnimator;
                    childViewHolderInt3.getUnmodifiedPayloads();
                    defaultItemAnimator2.getClass();
                    RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2 = new RecyclerView$ItemAnimator$ItemHolderInfo();
                    recyclerView$ItemAnimator$ItemHolderInfo2.setFrom(childViewHolderInt3);
                    if (hasAnyOfTheFlags) {
                        recordAnimationInfoIfBouncedHiddenView(childViewHolderInt3, recyclerView$ItemAnimator$ItemHolderInfo2);
                    } else {
                        SimpleArrayMap simpleArrayMap2 = this.mViewInfoStore.mLayoutHolderMap;
                        ViewInfoStore.InfoRecord infoRecord3 = (ViewInfoStore.InfoRecord) simpleArrayMap2.get(childViewHolderInt3);
                        if (infoRecord3 == null) {
                            infoRecord3 = ViewInfoStore.InfoRecord.obtain();
                            simpleArrayMap2.put(childViewHolderInt3, infoRecord3);
                        }
                        infoRecord3.flags |= 2;
                        infoRecord3.preInfo = recyclerView$ItemAnimator$ItemHolderInfo2;
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
        this.mState.mLayoutStep = 2;
    }

    public final void dispatchLayoutStep2() {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.assertLayoutStep(6);
        this.mAdapterHelper.consumeUpdatesInOnePass();
        this.mState.mItemCount = this.mAdapter.getItemCount();
        this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        if (this.mPendingSavedState != null) {
            Adapter adapter = this.mAdapter;
            int ordinal = adapter.mStateRestorationPolicy.ordinal();
            if (ordinal == 1 ? adapter.getItemCount() > 0 : ordinal != 2) {
                Parcelable parcelable = this.mPendingSavedState.mLayoutState;
                if (parcelable != null) {
                    this.mLayout.onRestoreInstanceState(parcelable);
                }
                this.mPendingSavedState = null;
            }
        }
        State state = this.mState;
        state.mInPreLayout = false;
        this.mLayout.onLayoutChildren(this.mRecycler, state);
        State state2 = this.mState;
        state2.mStructureChanged = false;
        state2.mRunSimpleAnimations = state2.mRunSimpleAnimations && this.mItemAnimator != null;
        state2.mLayoutStep = 4;
        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f, float f2, boolean z) {
        return getScrollingChildHelper().dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f, float f2) {
        return getScrollingChildHelper().dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().dispatchNestedPreScroll(i, i2, 0, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return getScrollingChildHelper().dispatchNestedScrollInternal(i, i2, i3, i4, iArr, 0, null);
    }

    public final void dispatchOnScrolled(int i, int i2) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i, scrollY - i2);
        List list = this.mScrollListeners;
        if (list != null) {
            for (int size = ((ArrayList) list).size() - 1; size >= 0; size--) {
                ((OnScrollListener) ((ArrayList) this.mScrollListeners).get(size)).onScrolled(this, i, i2);
            }
        }
        this.mDispatchScrollCounter--;
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchSaveInstanceState(SparseArray sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        boolean z;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            ((ItemDecoration) this.mItemDecorations.get(i)).onDrawOver(canvas, this);
        }
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z = false;
        } else {
            int save = canvas.save();
            int paddingBottom = this.mClipToPadding ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.mLeftGlow;
            z = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(save);
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int save2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.mTopGlow;
            z |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(save2);
        }
        EdgeEffect edgeEffect5 = this.mRightGlow;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int save3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.mClipToPadding ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(paddingTop, -width);
            EdgeEffect edgeEffect6 = this.mRightGlow;
            z |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(save3);
        }
        EdgeEffect edgeEffect7 = this.mBottomGlow;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int save4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            if (edgeEffect8 != null && edgeEffect8.draw(canvas)) {
                z2 = true;
            }
            z |= z2;
            canvas.restoreToCount(save4);
        }
        if ((z || this.mItemAnimator == null || this.mItemDecorations.size() <= 0 || !this.mItemAnimator.isRunning()) ? z : true) {
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j) {
        return super.drawChild(canvas, view, j);
    }

    public final void ensureBottomGlow() {
        if (this.mBottomGlow != null) {
            return;
        }
        this.mEdgeEffectFactory.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.mBottomGlow = edgeEffect;
        if (this.mClipToPadding) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final void ensureLeftGlow() {
        if (this.mLeftGlow != null) {
            return;
        }
        this.mEdgeEffectFactory.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.mLeftGlow = edgeEffect;
        if (this.mClipToPadding) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void ensureRightGlow() {
        if (this.mRightGlow != null) {
            return;
        }
        this.mEdgeEffectFactory.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.mRightGlow = edgeEffect;
        if (this.mClipToPadding) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public final void ensureTopGlow() {
        if (this.mTopGlow != null) {
            return;
        }
        this.mEdgeEffectFactory.getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.mTopGlow = edgeEffect;
        if (this.mClipToPadding) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public final String exceptionLabel() {
        return " " + super.toString() + ", adapter:" + this.mAdapter + ", layout:" + this.mLayout + ", context:" + getContext();
    }

    public final void fillRemainingScrollValues(State state) {
        if (this.mScrollState != 2) {
            state.getClass();
            return;
        }
        OverScroller overScroller = this.mViewFlinger.mOverScroller;
        overScroller.getFinalX();
        overScroller.getCurrX();
        state.getClass();
        overScroller.getFinalY();
        overScroller.getCurrY();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:?, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View findContainingItemView(android.view.View r3) {
        /*
            r2 = this;
            android.view.ViewParent r0 = r3.getParent()
        L4:
            if (r0 == 0) goto L14
            if (r0 == r2) goto L14
            boolean r1 = r0 instanceof android.view.View
            if (r1 == 0) goto L14
            r3 = r0
            android.view.View r3 = (android.view.View) r3
            android.view.ViewParent r0 = r3.getParent()
            goto L4
        L14:
            if (r0 != r2) goto L17
            goto L18
        L17:
            r3 = 0
        L18:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.findContainingItemView(android.view.View):android.view.View");
    }

    public final boolean findInterceptingOnItemTouchListener(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.mOnItemTouchListeners.size();
        for (int i = 0; i < size; i++) {
            OnItemTouchListener onItemTouchListener = (OnItemTouchListener) this.mOnItemTouchListeners.get(i);
            if (onItemTouchListener.onInterceptTouchEvent(this, motionEvent) && action != 3) {
                this.mInterceptingOnItemTouchListener = onItemTouchListener;
                return true;
            }
        }
        return false;
    }

    public final void findMinMaxChildLayoutPositions(int[] iArr) {
        int childCount = this.mChildHelper.getChildCount();
        if (childCount == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < childCount; i3++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getChildAt(i3));
            if (!childViewHolderInt.shouldIgnore()) {
                int layoutPosition = childViewHolderInt.getLayoutPosition();
                if (layoutPosition < i) {
                    i = layoutPosition;
                }
                if (layoutPosition > i2) {
                    i2 = layoutPosition;
                }
            }
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    public final ViewHolder findViewHolderForAdapterPosition(int i) {
        ViewHolder viewHolder = null;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i2 = 0; i2 < unfilteredChildCount; i2++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i2));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && getAdapterPositionInRecyclerView(childViewHolderInt) == i) {
                ChildHelper childHelper = this.mChildHelper;
                if (!childHelper.mHiddenViews.contains(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                viewHolder = childViewHolderInt;
            }
        }
        return viewHolder;
    }

    /* JADX WARN: Code restructure failed: missing block: B:126:0x01e0, code lost:
    
        if (r1 < r12) goto L100;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:142:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00cb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01e8  */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean fling(int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 528
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.fling(int, int, int, int):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0192, code lost:
    
        if (r5 > 0) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0195, code lost:
    
        if (r11 < 0) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0198, code lost:
    
        if (r5 < 0) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01a0, code lost:
    
        if ((r5 * r6) <= 0) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01a8, code lost:
    
        if ((r5 * r6) >= 0) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0174, code lost:
    
        if (r11 > 0) goto L140;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View focusSearch(android.view.View r17, int r18) {
        /*
            Method dump skipped, instructions count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateDefaultLayoutParams();
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(getContext(), attributeSet);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public final int getAdapterPositionInRecyclerView(ViewHolder viewHolder) {
        if (viewHolder.hasAnyOfTheFlags(524) || !viewHolder.isBound()) {
            return -1;
        }
        AdapterHelper adapterHelper = this.mAdapterHelper;
        int i = viewHolder.mPosition;
        int size = adapterHelper.mPendingUpdates.size();
        for (int i2 = 0; i2 < size; i2++) {
            AdapterHelper.UpdateOp updateOp = (AdapterHelper.UpdateOp) adapterHelper.mPendingUpdates.get(i2);
            int i3 = updateOp.cmd;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = updateOp.positionStart;
                    if (i4 <= i) {
                        int i5 = updateOp.itemCount;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = updateOp.positionStart;
                    if (i6 == i) {
                        i = updateOp.itemCount;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (updateOp.itemCount <= i) {
                            i++;
                        }
                    }
                }
            } else if (updateOp.positionStart <= i) {
                i += updateOp.itemCount;
            }
        }
        return i;
    }

    @Override // android.view.View
    public final int getBaseline() {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            return super.getBaseline();
        }
        layoutManager.getClass();
        return -1;
    }

    public final long getChangedHolderKey(ViewHolder viewHolder) {
        return this.mAdapter.mHasStableIds ? viewHolder.mItemId : viewHolder.mPosition;
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        return super.getChildDrawingOrder(i, i2);
    }

    public final ViewHolder getChildViewHolder(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    @Override // android.view.ViewGroup
    public final boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public final Rect getItemDecorInsetsForChild(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.mInsetsDirty) {
            return layoutParams.mDecorInsets;
        }
        if (this.mState.mInPreLayout && (layoutParams.mViewHolder.isUpdated() || layoutParams.mViewHolder.isInvalid())) {
            return layoutParams.mDecorInsets;
        }
        Rect rect = layoutParams.mDecorInsets;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            this.mTempRect.set(0, 0, 0, 0);
            ((ItemDecoration) this.mItemDecorations.get(i)).getItemOffsets(this.mTempRect, view, this, this.mState);
            int i2 = rect.left;
            Rect rect2 = this.mTempRect;
            rect.left = i2 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        layoutParams.mInsetsDirty = false;
        return rect;
    }

    public final LayoutManager getLayoutManager() {
        return this.mLayout;
    }

    public final long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0L;
    }

    public final NestedScrollingChildHelper getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
        }
        return this.mScrollingChildHelper;
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().hasNestedScrollingParent(0);
    }

    public final boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.hasPendingUpdates();
    }

    public void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
            throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + exceptionLabel());
        }
        Resources resources = getContext().getResources();
        new FastScroller(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(com.android.wm.shell.R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(com.android.wm.shell.R.dimen.fastscroll_margin));
    }

    public final void invalidateItemDecorations() {
        if (this.mItemDecorations.size() == 0) {
            return;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    @Override // android.view.View
    public final boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public final boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.mLayoutSuppressed;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().mIsNestedScrollingEnabled;
    }

    public final void jumpToPositionForSmoothScroller(int i) {
        if (this.mLayout == null) {
            return;
        }
        setScrollState(2);
        this.mLayout.scrollToPosition(i);
        awakenScrollBars();
    }

    public final void markItemDecorInsetsDirty() {
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ((LayoutParams) this.mChildHelper.getUnfilteredChildAt(i).getLayoutParams()).mInsetsDirty = true;
        }
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            LayoutParams layoutParams = (LayoutParams) ((ViewHolder) recycler.mCachedViews.get(i2)).itemView.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.mInsetsDirty = true;
            }
        }
    }

    public final void offsetPositionRecordsForRemove(int i, int i2, boolean z) {
        int i3 = i + i2;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i4 = 0; i4 < unfilteredChildCount; i4++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i4));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                int i5 = childViewHolderInt.mPosition;
                if (i5 >= i3) {
                    childViewHolderInt.offsetPosition(-i2, z);
                    this.mState.mStructureChanged = true;
                } else if (i5 >= i) {
                    childViewHolderInt.addFlags(8);
                    childViewHolderInt.offsetPosition(-i2, z);
                    childViewHolderInt.mPosition = i - 1;
                    this.mState.mStructureChanged = true;
                }
            }
        }
        Recycler recycler = this.mRecycler;
        for (int size = recycler.mCachedViews.size() - 1; size >= 0; size--) {
            ViewHolder viewHolder = (ViewHolder) recycler.mCachedViews.get(size);
            if (viewHolder != null) {
                int i6 = viewHolder.mPosition;
                if (i6 >= i3) {
                    viewHolder.offsetPosition(-i2, z);
                } else if (i6 >= i) {
                    viewHolder.addFlags(8);
                    recycler.recycleCachedViewAt(size);
                }
            }
        }
        requestLayout();
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0055, code lost:
    
        if (r1 >= 30.0f) goto L22;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onAttachedToWindow() {
        /*
            r5 = this;
            super.onAttachedToWindow()
            r0 = 0
            r5.mLayoutOrScrollCounter = r0
            r1 = 1
            r5.mIsAttached = r1
            boolean r2 = r5.mFirstLayoutComplete
            if (r2 == 0) goto L15
            boolean r2 = r5.isLayoutRequested()
            if (r2 != 0) goto L15
            r2 = r1
            goto L16
        L15:
            r2 = r0
        L16:
            r5.mFirstLayoutComplete = r2
            androidx.recyclerview.widget.RecyclerView$Recycler r2 = r5.mRecycler
            r2.maybeSendPoolingContainerAttach()
            androidx.recyclerview.widget.RecyclerView$LayoutManager r2 = r5.mLayout
            if (r2 == 0) goto L26
            r2.mIsAttachedToWindow = r1
            r2.onAttachedToWindow(r5)
        L26:
            r5.mPostedAnimatorRunner = r0
            boolean r0 = androidx.recyclerview.widget.RecyclerView.ALLOW_THREAD_GAP_WORK
            if (r0 == 0) goto L70
            java.lang.ThreadLocal r0 = androidx.recyclerview.widget.GapWorker.sGapWorker
            java.lang.Object r1 = r0.get()
            androidx.recyclerview.widget.GapWorker r1 = (androidx.recyclerview.widget.GapWorker) r1
            r5.mGapWorker = r1
            if (r1 != 0) goto L66
            androidx.recyclerview.widget.GapWorker r1 = new androidx.recyclerview.widget.GapWorker
            r1.<init>()
            r5.mGapWorker = r1
            java.util.WeakHashMap r1 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            android.view.Display r1 = r5.getDisplay()
            boolean r2 = r5.isInEditMode()
            if (r2 != 0) goto L58
            if (r1 == 0) goto L58
            float r1 = r1.getRefreshRate()
            r2 = 1106247680(0x41f00000, float:30.0)
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L58
            goto L5a
        L58:
            r1 = 1114636288(0x42700000, float:60.0)
        L5a:
            androidx.recyclerview.widget.GapWorker r2 = r5.mGapWorker
            r3 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r3 = r3 / r1
            long r3 = (long) r3
            r2.mFrameIntervalNs = r3
            r0.set(r2)
        L66:
            androidx.recyclerview.widget.GapWorker r0 = r5.mGapWorker
            r0.getClass()
            java.util.ArrayList r0 = r0.mRecyclerViews
            r0.add(r5)
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onAttachedToWindow():void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        GapWorker gapWorker;
        super.onDetachedFromWindow();
        DefaultItemAnimator defaultItemAnimator = this.mItemAnimator;
        if (defaultItemAnimator != null) {
            defaultItemAnimator.endAnimations();
        }
        stopScroll();
        int i = 0;
        this.mIsAttached = false;
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.mIsAttachedToWindow = false;
            layoutManager.onDetachedFromWindow(this);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.getClass();
        while (ViewInfoStore.InfoRecord.sPool.acquire() != null) {
        }
        Recycler recycler = this.mRecycler;
        for (int i2 = 0; i2 < recycler.mCachedViews.size(); i2++) {
            PoolingContainer.callPoolingContainerOnRelease(((ViewHolder) recycler.mCachedViews.get(i2)).itemView);
        }
        recycler.poolingContainerDetach(RecyclerView.this.mAdapter, false);
        while (i < getChildCount()) {
            int i3 = i + 1;
            View childAt = getChildAt(i);
            if (childAt == null) {
                throw new IndexOutOfBoundsException();
            }
            PoolingContainerListenerHolder poolingContainerListenerHolder = PoolingContainer.getPoolingContainerListenerHolder(childAt);
            for (int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(poolingContainerListenerHolder.listeners); -1 < lastIndex; lastIndex--) {
                ((ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$$ExternalSyntheticLambda0) ((PoolingContainerListener) poolingContainerListenerHolder.listeners.get(lastIndex))).f$0.disposeComposition();
            }
            i = i3;
        }
        if (!ALLOW_THREAD_GAP_WORK || (gapWorker = this.mGapWorker) == null) {
            return;
        }
        gapWorker.mRecyclerViews.remove(this);
        this.mGapWorker = null;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i = 0; i < size; i++) {
            ((ItemDecoration) this.mItemDecorations.get(i)).onDraw(canvas, this);
        }
    }

    public final void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    public final void onExitLayoutOrScroll(boolean z) {
        int i;
        AccessibilityManager accessibilityManager;
        int i2 = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = i2;
        if (i2 < 1) {
            this.mLayoutOrScrollCounter = 0;
            if (z) {
                int i3 = this.mEatenAccessibilityChangeFlags;
                this.mEatenAccessibilityChangeFlags = 0;
                if (i3 != 0 && (accessibilityManager = this.mAccessibilityManager) != null && accessibilityManager.isEnabled()) {
                    AccessibilityEvent obtain = AccessibilityEvent.obtain();
                    obtain.setEventType(2048);
                    obtain.setContentChangeTypes(i3);
                    sendAccessibilityEventUnchecked(obtain);
                }
                for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
                    ViewHolder viewHolder = (ViewHolder) this.mPendingAccessibilityImportanceChange.get(size);
                    if (viewHolder.itemView.getParent() == this && !viewHolder.shouldIgnore() && (i = viewHolder.mPendingAccessibilityState) != -1) {
                        viewHolder.itemView.setImportantForAccessibility(i);
                        viewHolder.mPendingAccessibilityState = -1;
                    }
                }
                this.mPendingAccessibilityImportanceChange.clear();
            }
        }
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        int i;
        boolean z;
        float f;
        if (this.mLayout != null && !this.mLayoutSuppressed && motionEvent.getAction() == 8) {
            if ((motionEvent.getSource() & 2) != 0) {
                float f2 = this.mLayout.canScrollVertically() ? -motionEvent.getAxisValue(9) : 0.0f;
                z = false;
                f = this.mLayout.canScrollHorizontally() ? motionEvent.getAxisValue(10) : 0.0f;
                r4 = f2;
                i = 0;
            } else if ((motionEvent.getSource() & 4194304) != 0) {
                i = 26;
                f = motionEvent.getAxisValue(26);
                if (this.mLayout.canScrollVertically()) {
                    float f3 = -f;
                    f = 0.0f;
                    r4 = f3;
                } else if (!this.mLayout.canScrollHorizontally()) {
                    f = 0.0f;
                }
                z = this.mLowResRotaryEncoderFeature;
            } else {
                i = 0;
                z = false;
                f = 0.0f;
            }
            int i2 = (int) (r4 * this.mScaledVerticalScrollFactor);
            int i3 = (int) (f * this.mScaledHorizontalScrollFactor);
            if (z) {
                OverScroller overScroller = this.mViewFlinger.mOverScroller;
                smoothScrollBy$1((overScroller.getFinalX() - overScroller.getCurrX()) + i3, (overScroller.getFinalY() - overScroller.getCurrY()) + i2, true);
            } else {
                LayoutManager layoutManager = this.mLayout;
                if (layoutManager == null) {
                    Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
                } else if (!this.mLayoutSuppressed) {
                    int[] iArr = this.mReusableIntPair;
                    iArr[0] = 0;
                    iArr[1] = 0;
                    boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
                    boolean canScrollVertically = this.mLayout.canScrollVertically();
                    int i4 = canScrollVertically ? (canScrollHorizontally ? 1 : 0) | 2 : canScrollHorizontally ? 1 : 0;
                    float y = motionEvent.getY();
                    float x = motionEvent.getX();
                    int releaseHorizontalGlow = i3 - releaseHorizontalGlow(i3, y);
                    int releaseVerticalGlow$1 = i2 - releaseVerticalGlow$1(i2, x);
                    getScrollingChildHelper().startNestedScroll(i4, 1);
                    if (getScrollingChildHelper().dispatchNestedPreScroll(canScrollHorizontally ? releaseHorizontalGlow : 0, canScrollVertically ? releaseVerticalGlow$1 : 0, 1, this.mReusableIntPair, this.mScrollOffset)) {
                        int[] iArr2 = this.mReusableIntPair;
                        releaseHorizontalGlow -= iArr2[0];
                        releaseVerticalGlow$1 -= iArr2[1];
                    }
                    scrollByInternal(canScrollHorizontally ? releaseHorizontalGlow : 0, canScrollVertically ? releaseVerticalGlow$1 : 0, motionEvent, 1);
                    GapWorker gapWorker = this.mGapWorker;
                    if (gapWorker != null && (releaseHorizontalGlow != 0 || releaseVerticalGlow$1 != 0)) {
                        gapWorker.postFromTraversal(this, releaseHorizontalGlow, releaseVerticalGlow$1);
                    }
                    getScrollingChildHelper().stopNestedScroll(1);
                }
            }
            if (i != 0 && !z) {
                this.mDifferentialMotionFlingController.onMotionEvent(motionEvent, i);
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        if (this.mLayoutSuppressed) {
            return false;
        }
        this.mInterceptingOnItemTouchListener = null;
        if (findInterceptingOnItemTouchListener(motionEvent)) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
            getScrollingChildHelper().stopNestedScroll(0);
            releaseGlows();
            setScrollState(0);
            return true;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            return false;
        }
        boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
        boolean canScrollVertically = this.mLayout.canScrollVertically();
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            if (this.mIgnoreMotionEventTillDown) {
                this.mIgnoreMotionEventTillDown = false;
            }
            this.mScrollPointerId = motionEvent.getPointerId(0);
            int x = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY() + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
            EdgeEffect edgeEffect = this.mLeftGlow;
            if (edgeEffect == null || EdgeEffectCompat$Api31Impl.getDistance(edgeEffect) == 0.0f || canScrollHorizontally(-1)) {
                z = false;
            } else {
                EdgeEffectCompat$Api31Impl.onPullDistance(this.mLeftGlow, 0.0f, 1.0f - (motionEvent.getY() / getHeight()));
                z = true;
            }
            EdgeEffect edgeEffect2 = this.mRightGlow;
            if (edgeEffect2 != null && EdgeEffectCompat$Api31Impl.getDistance(edgeEffect2) != 0.0f && !canScrollHorizontally(1)) {
                EdgeEffectCompat$Api31Impl.onPullDistance(this.mRightGlow, 0.0f, motionEvent.getY() / getHeight());
                z = true;
            }
            EdgeEffect edgeEffect3 = this.mTopGlow;
            if (edgeEffect3 != null && EdgeEffectCompat$Api31Impl.getDistance(edgeEffect3) != 0.0f && !canScrollVertically(-1)) {
                EdgeEffectCompat$Api31Impl.onPullDistance(this.mTopGlow, 0.0f, motionEvent.getX() / getWidth());
                z = true;
            }
            EdgeEffect edgeEffect4 = this.mBottomGlow;
            if (edgeEffect4 != null && EdgeEffectCompat$Api31Impl.getDistance(edgeEffect4) != 0.0f && !canScrollVertically(1)) {
                EdgeEffectCompat$Api31Impl.onPullDistance(this.mBottomGlow, 0.0f, 1.0f - (motionEvent.getX() / getWidth()));
                z = true;
            }
            if (z || this.mScrollState == 2) {
                getParent().requestDisallowInterceptTouchEvent(true);
                setScrollState(1);
                getScrollingChildHelper().stopNestedScroll(1);
            }
            int[] iArr = this.mNestedOffsets;
            iArr[1] = 0;
            iArr[0] = 0;
            startNestedScrollForType(0);
        } else if (actionMasked == 1) {
            this.mVelocityTracker.clear();
            getScrollingChildHelper().stopNestedScroll(0);
        } else if (actionMasked == 2) {
            int findPointerIndex = motionEvent.findPointerIndex(this.mScrollPointerId);
            if (findPointerIndex < 0) {
                Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                return false;
            }
            int x2 = (int) (motionEvent.getX(findPointerIndex) + 0.5f);
            int y2 = (int) (motionEvent.getY(findPointerIndex) + 0.5f);
            if (this.mScrollState != 1) {
                int i = x2 - this.mInitialTouchX;
                int i2 = y2 - this.mInitialTouchY;
                if (!canScrollHorizontally || Math.abs(i) <= this.mTouchSlop) {
                    z2 = false;
                } else {
                    this.mLastTouchX = x2;
                    z2 = true;
                }
                if (canScrollVertically && Math.abs(i2) > this.mTouchSlop) {
                    this.mLastTouchY = y2;
                    z2 = true;
                }
                if (z2) {
                    setScrollState(1);
                }
            }
        } else if (actionMasked == 3) {
            VelocityTracker velocityTracker2 = this.mVelocityTracker;
            if (velocityTracker2 != null) {
                velocityTracker2.clear();
            }
            getScrollingChildHelper().stopNestedScroll(0);
            releaseGlows();
            setScrollState(0);
        } else if (actionMasked == 5) {
            this.mScrollPointerId = motionEvent.getPointerId(actionIndex);
            int x3 = (int) (motionEvent.getX(actionIndex) + 0.5f);
            this.mLastTouchX = x3;
            this.mInitialTouchX = x3;
            int y3 = (int) (motionEvent.getY(actionIndex) + 0.5f);
            this.mLastTouchY = y3;
            this.mInitialTouchY = y3;
        } else if (actionMasked == 6) {
            onPointerUp(motionEvent);
        }
        return this.mScrollState == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Trace.beginSection("RV OnLayout");
        dispatchLayout();
        Trace.endSection();
        this.mFirstLayoutComplete = true;
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            defaultOnMeasure(i, i2);
            return;
        }
        boolean z = false;
        if (layoutManager.isAutoMeasureEnabled()) {
            int mode = View.MeasureSpec.getMode(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            this.mLayout.onMeasure(this.mRecycler, this.mState, i, i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z = true;
            }
            this.mLastAutoMeasureSkippedDueToExact = z;
            if (z || this.mAdapter == null) {
                return;
            }
            if (this.mState.mLayoutStep == 1) {
                dispatchLayoutStep1();
            }
            this.mLayout.setMeasureSpecs(i, i2);
            this.mState.mIsMeasuring = true;
            dispatchLayoutStep2();
            this.mLayout.setMeasuredDimensionFromChildren(i, i2);
            if (this.mLayout.shouldMeasureTwice()) {
                this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.mState.mIsMeasuring = true;
                dispatchLayoutStep2();
                this.mLayout.setMeasuredDimensionFromChildren(i, i2);
            }
            this.mLastAutoMeasureNonExactMeasuredWidth = getMeasuredWidth();
            this.mLastAutoMeasureNonExactMeasuredHeight = getMeasuredHeight();
            return;
        }
        if (this.mAdapterUpdateDuringMeasure) {
            startInterceptRequestLayout();
            onEnterLayoutOrScroll();
            processAdapterUpdatesAndSetAnimationFlags();
            onExitLayoutOrScroll(true);
            State state = this.mState;
            if (state.mRunPredictiveAnimations) {
                state.mInPreLayout = true;
            } else {
                this.mAdapterHelper.consumeUpdatesInOnePass();
                this.mState.mInPreLayout = false;
            }
            this.mAdapterUpdateDuringMeasure = false;
            stopInterceptRequestLayout(false);
        } else if (this.mState.mRunPredictiveAnimations) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        Adapter adapter = this.mAdapter;
        if (adapter != null) {
            this.mState.mItemCount = adapter.getItemCount();
        } else {
            this.mState.mItemCount = 0;
        }
        startInterceptRequestLayout();
        this.mLayout.onMeasure(this.mRecycler, this.mState, i, i2);
        stopInterceptRequestLayout(false);
        this.mState.mInPreLayout = false;
    }

    public final void onPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int i = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = motionEvent.getPointerId(i);
            int x = (int) (motionEvent.getX(i) + 0.5f);
            this.mLastTouchX = x;
            this.mInitialTouchX = x;
            int y = (int) (motionEvent.getY(i) + 0.5f);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        this.mPendingSavedState = savedState;
        super.onRestoreInstanceState(savedState.mSuperState);
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = this.mPendingSavedState;
        if (savedState2 != null) {
            savedState.mLayoutState = savedState2.mLayoutState;
        } else {
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager != null) {
                savedState.mLayoutState = layoutManager.onSaveInstanceState();
            } else {
                savedState.mLayoutState = null;
            }
        }
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i == i3 && i2 == i4) {
            return;
        }
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0133  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r19) {
        /*
            Method dump skipped, instructions count: 573
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void postAnimationRunner() {
        if (this.mPostedAnimatorRunner || !this.mIsAttached) {
            return;
        }
        AnonymousClass1 anonymousClass1 = this.mItemAnimatorRunner;
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        postOnAnimation(anonymousClass1);
        this.mPostedAnimatorRunner = true;
    }

    public final void processAdapterUpdatesAndSetAnimationFlags() {
        boolean z;
        boolean z2 = false;
        if (this.mDataSetHasChangedAfterLayout) {
            AdapterHelper adapterHelper = this.mAdapterHelper;
            adapterHelper.recycleUpdateOpsAndClearList(adapterHelper.mPendingUpdates);
            adapterHelper.recycleUpdateOpsAndClearList(adapterHelper.mPostponedList);
            adapterHelper.mExistingUpdateTypes = 0;
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.onItemsChanged();
            }
        }
        if (this.mItemAnimator == null || !this.mLayout.supportsPredictiveItemAnimations()) {
            this.mAdapterHelper.consumeUpdatesInOnePass();
        } else {
            this.mAdapterHelper.preProcess();
        }
        boolean z3 = this.mItemsAddedOrRemoved || this.mItemsChanged;
        State state = this.mState;
        boolean z4 = this.mFirstLayoutComplete && this.mItemAnimator != null && ((z = this.mDataSetHasChangedAfterLayout) || z3 || this.mLayout.mRequestedSimpleAnimations) && (!z || this.mAdapter.mHasStableIds);
        state.mRunSimpleAnimations = z4;
        if (z4 && z3 && !this.mDataSetHasChangedAfterLayout && this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations()) {
            z2 = true;
        }
        state.mRunPredictiveAnimations = z2;
    }

    public final void processDataSetCompletelyChanged(boolean z) {
        this.mDispatchItemsChangedEvent = z | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        int unfilteredChildCount = this.mChildHelper.getUnfilteredChildCount();
        for (int i = 0; i < unfilteredChildCount; i++) {
            ViewHolder childViewHolderInt = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(i));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        Recycler recycler = this.mRecycler;
        int size = recycler.mCachedViews.size();
        for (int i2 = 0; i2 < size; i2++) {
            ViewHolder viewHolder = (ViewHolder) recycler.mCachedViews.get(i2);
            if (viewHolder != null) {
                viewHolder.addFlags(6);
                viewHolder.addFlags(1024);
            }
        }
        Adapter adapter = RecyclerView.this.mAdapter;
        if (adapter == null || !adapter.mHasStableIds) {
            recycler.recycleAndClearCachedViews();
        }
    }

    public final void recordAnimationInfoIfBouncedHiddenView(ViewHolder viewHolder, RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo) {
        viewHolder.mFlags &= -8193;
        if (this.mState.mTrackOldChangeHolders && viewHolder.isUpdated() && !viewHolder.isRemoved() && !viewHolder.shouldIgnore()) {
            this.mViewInfoStore.mOldChangedHolders.put(getChangedHolderKey(viewHolder), viewHolder);
        }
        SimpleArrayMap simpleArrayMap = this.mViewInfoStore.mLayoutHolderMap;
        ViewInfoStore.InfoRecord infoRecord = (ViewInfoStore.InfoRecord) simpleArrayMap.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = ViewInfoStore.InfoRecord.obtain();
            simpleArrayMap.put(viewHolder, infoRecord);
        }
        infoRecord.preInfo = recyclerView$ItemAnimator$ItemHolderInfo;
        infoRecord.flags |= 4;
    }

    public final void releaseGlows() {
        boolean z;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            z = this.mLeftGlow.isFinished();
        } else {
            z = false;
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            z |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            z |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            z |= this.mBottomGlow.isFinished();
        }
        if (z) {
            postInvalidateOnAnimation();
        }
    }

    public final int releaseHorizontalGlow(int i, float f) {
        float height = f / getHeight();
        float width = i / getWidth();
        EdgeEffect edgeEffect = this.mLeftGlow;
        float f2 = 0.0f;
        if (edgeEffect == null || EdgeEffectCompat$Api31Impl.getDistance(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.mRightGlow;
            if (edgeEffect2 != null && EdgeEffectCompat$Api31Impl.getDistance(edgeEffect2) != 0.0f) {
                if (canScrollHorizontally(1)) {
                    this.mRightGlow.onRelease();
                } else {
                    float onPullDistance = EdgeEffectCompat$Api31Impl.onPullDistance(this.mRightGlow, width, height);
                    if (EdgeEffectCompat$Api31Impl.getDistance(this.mRightGlow) == 0.0f) {
                        this.mRightGlow.onRelease();
                    }
                    f2 = onPullDistance;
                }
                invalidate();
            }
        } else {
            if (canScrollHorizontally(-1)) {
                this.mLeftGlow.onRelease();
            } else {
                float f3 = -EdgeEffectCompat$Api31Impl.onPullDistance(this.mLeftGlow, -width, 1.0f - height);
                if (EdgeEffectCompat$Api31Impl.getDistance(this.mLeftGlow) == 0.0f) {
                    this.mLeftGlow.onRelease();
                }
                f2 = f3;
            }
            invalidate();
        }
        return Math.round(f2 * getWidth());
    }

    public final int releaseVerticalGlow$1(int i, float f) {
        float width = f / getWidth();
        float height = i / getHeight();
        EdgeEffect edgeEffect = this.mTopGlow;
        float f2 = 0.0f;
        if (edgeEffect == null || EdgeEffectCompat$Api31Impl.getDistance(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.mBottomGlow;
            if (edgeEffect2 != null && EdgeEffectCompat$Api31Impl.getDistance(edgeEffect2) != 0.0f) {
                if (canScrollVertically(1)) {
                    this.mBottomGlow.onRelease();
                } else {
                    float onPullDistance = EdgeEffectCompat$Api31Impl.onPullDistance(this.mBottomGlow, height, 1.0f - width);
                    if (EdgeEffectCompat$Api31Impl.getDistance(this.mBottomGlow) == 0.0f) {
                        this.mBottomGlow.onRelease();
                    }
                    f2 = onPullDistance;
                }
                invalidate();
            }
        } else {
            if (canScrollVertically(-1)) {
                this.mTopGlow.onRelease();
            } else {
                float f3 = -EdgeEffectCompat$Api31Impl.onPullDistance(this.mTopGlow, -height, width);
                if (EdgeEffectCompat$Api31Impl.getDistance(this.mTopGlow) == 0.0f) {
                    this.mTopGlow.onRelease();
                }
                f2 = f3;
            }
            invalidate();
        }
        return Math.round(f2 * getHeight());
    }

    public final void removeAndRecycleViews() {
        DefaultItemAnimator defaultItemAnimator = this.mItemAnimator;
        if (defaultItemAnimator != null) {
            defaultItemAnimator.endAnimations();
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
        }
        Recycler recycler = this.mRecycler;
        recycler.mAttachedScrap.clear();
        recycler.recycleAndClearCachedViews();
    }

    @Override // android.view.ViewGroup
    public final void removeDetachedView(View view, boolean z) {
        ViewHolder childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.mFlags &= -257;
            } else if (!childViewHolderInt.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + childViewHolderInt + exceptionLabel());
            }
        }
        view.clearAnimation();
        dispatchChildDetached(view);
        super.removeDetachedView(view, z);
    }

    public final void removeItemDecoration(ItemDecoration itemDecoration) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(itemDecoration);
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        if (!this.mLayout.onRequestChildFocus(this, view, view2) && view2 != null) {
            requestChildOnScreen(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    public final void requestChildOnScreen(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof LayoutParams) {
            LayoutParams layoutParams2 = (LayoutParams) layoutParams;
            if (!layoutParams2.mInsetsDirty) {
                Rect rect = layoutParams2.mDecorInsets;
                Rect rect2 = this.mTempRect;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        }
        this.mLayout.requestChildRectangleOnScreen(this, view, this.mTempRect, !this.mFirstLayoutComplete, view2 == null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z) {
        int size = this.mOnItemTouchListeners.size();
        for (int i = 0; i < size; i++) {
            ((OnItemTouchListener) this.mOnItemTouchListeners.get(i)).onRequestDisallowInterceptTouchEvent(z);
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.mInterceptRequestLayoutDepth != 0 || this.mLayoutSuppressed) {
            this.mLayoutWasDefered = true;
        } else {
            super.requestLayout();
        }
    }

    @Override // android.view.View
    public final void scrollBy(int i, int i2) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.mLayoutSuppressed) {
            return;
        }
        boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
        boolean canScrollVertically = this.mLayout.canScrollVertically();
        if (canScrollHorizontally || canScrollVertically) {
            if (!canScrollHorizontally) {
                i = 0;
            }
            if (!canScrollVertically) {
                i2 = 0;
            }
            scrollByInternal(i, i2, null, 0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean scrollByInternal(int r19, int r20, android.view.MotionEvent r21, int r22) {
        /*
            Method dump skipped, instructions count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.scrollByInternal(int, int, android.view.MotionEvent, int):boolean");
    }

    public final void scrollStep(int i, int i2, int[] iArr) {
        ViewHolder viewHolder;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        Trace.beginSection("RV Scroll");
        fillRemainingScrollValues(this.mState);
        int scrollHorizontallyBy = i != 0 ? this.mLayout.scrollHorizontallyBy(i, this.mRecycler, this.mState) : 0;
        int scrollVerticallyBy = i2 != 0 ? this.mLayout.scrollVerticallyBy(i2, this.mRecycler, this.mState) : 0;
        Trace.endSection();
        int childCount = this.mChildHelper.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = this.mChildHelper.getChildAt(i3);
            ViewHolder childViewHolder = getChildViewHolder(childAt);
            if (childViewHolder != null && (viewHolder = childViewHolder.mShadowingHolder) != null) {
                View view = viewHolder.itemView;
                int left = childAt.getLeft();
                int top = childAt.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
        onExitLayoutOrScroll(true);
        stopInterceptRequestLayout(false);
        if (iArr != null) {
            iArr[0] = scrollHorizontallyBy;
            iArr[1] = scrollVerticallyBy;
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i, int i2) {
        Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i) {
        if (this.mLayoutSuppressed) {
            return;
        }
        stopScroll();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            layoutManager.scrollToPosition(i);
            awakenScrollBars();
        }
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public final void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (!isComputingLayout()) {
            super.sendAccessibilityEventUnchecked(accessibilityEvent);
        } else {
            int contentChangeTypes = accessibilityEvent != null ? accessibilityEvent.getContentChangeTypes() : 0;
            this.mEatenAccessibilityChangeFlags |= contentChangeTypes != 0 ? contentChangeTypes : 0;
        }
    }

    public final void setAdapter(Adapter adapter) {
        suppressLayout(false);
        Adapter adapter2 = this.mAdapter;
        if (adapter2 != null) {
            adapter2.mObservable.unregisterObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView();
        }
        removeAndRecycleViews();
        AdapterHelper adapterHelper = this.mAdapterHelper;
        adapterHelper.recycleUpdateOpsAndClearList(adapterHelper.mPendingUpdates);
        adapterHelper.recycleUpdateOpsAndClearList(adapterHelper.mPostponedList);
        adapterHelper.mExistingUpdateTypes = 0;
        Adapter adapter3 = this.mAdapter;
        this.mAdapter = adapter;
        if (adapter != null) {
            adapter.mObservable.registerObserver(this.mObserver);
            adapter.onAttachedToRecyclerView(this);
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            layoutManager.onAdapterChanged(adapter3);
        }
        Recycler recycler = this.mRecycler;
        Adapter adapter4 = this.mAdapter;
        recycler.mAttachedScrap.clear();
        recycler.recycleAndClearCachedViews();
        recycler.poolingContainerDetach(adapter3, true);
        RecycledViewPool recycledViewPool = recycler.getRecycledViewPool();
        if (adapter3 != null) {
            recycledViewPool.mAttachCountForClearing--;
        }
        if (recycledViewPool.mAttachCountForClearing == 0) {
            for (int i = 0; i < recycledViewPool.mScrap.size(); i++) {
                RecycledViewPool.ScrapData scrapData = (RecycledViewPool.ScrapData) recycledViewPool.mScrap.valueAt(i);
                Iterator it = scrapData.mScrapHeap.iterator();
                while (it.hasNext()) {
                    PoolingContainer.callPoolingContainerOnRelease(((ViewHolder) it.next()).itemView);
                }
                scrapData.mScrapHeap.clear();
            }
        }
        if (adapter4 != null) {
            recycledViewPool.mAttachCountForClearing++;
        }
        recycler.maybeSendPoolingContainerAttach();
        this.mState.mStructureChanged = true;
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public boolean setChildImportantForAccessibilityInternal(ViewHolder viewHolder, int i) {
        if (!isComputingLayout()) {
            viewHolder.itemView.setImportantForAccessibility(i);
            return true;
        }
        viewHolder.mPendingAccessibilityState = i;
        this.mPendingAccessibilityImportanceChange.add(viewHolder);
        return false;
    }

    @Override // android.view.ViewGroup
    public final void setClipToPadding(boolean z) {
        if (z != this.mClipToPadding) {
            this.mBottomGlow = null;
            this.mTopGlow = null;
            this.mRightGlow = null;
            this.mLeftGlow = null;
        }
        this.mClipToPadding = z;
        super.setClipToPadding(z);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public final void setLayoutManager(LayoutManager layoutManager) {
        RecyclerView recyclerView;
        if (layoutManager == this.mLayout) {
            return;
        }
        stopScroll();
        if (this.mLayout != null) {
            DefaultItemAnimator defaultItemAnimator = this.mItemAnimator;
            if (defaultItemAnimator != null) {
                defaultItemAnimator.endAnimations();
            }
            this.mLayout.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
            Recycler recycler = this.mRecycler;
            recycler.mAttachedScrap.clear();
            recycler.recycleAndClearCachedViews();
            if (this.mIsAttached) {
                LayoutManager layoutManager2 = this.mLayout;
                layoutManager2.mIsAttachedToWindow = false;
                layoutManager2.onDetachedFromWindow(this);
            }
            this.mLayout.setRecyclerView(null);
            this.mLayout = null;
        } else {
            Recycler recycler2 = this.mRecycler;
            recycler2.mAttachedScrap.clear();
            recycler2.recycleAndClearCachedViews();
        }
        ChildHelper childHelper = this.mChildHelper;
        childHelper.mBucket.reset();
        int size = ((ArrayList) childHelper.mHiddenViews).size() - 1;
        while (true) {
            recyclerView = RecyclerView.this;
            if (size < 0) {
                break;
            }
            ViewHolder childViewHolderInt = getChildViewHolderInt((View) ((ArrayList) childHelper.mHiddenViews).get(size));
            if (childViewHolderInt != null) {
                recyclerView.setChildImportantForAccessibilityInternal(childViewHolderInt, childViewHolderInt.mWasImportantForAccessibilityBeforeHidden);
                childViewHolderInt.mWasImportantForAccessibilityBeforeHidden = 0;
            }
            childHelper.mHiddenViews.remove(size);
            size--;
        }
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            recyclerView.dispatchChildDetached(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeAllViews();
        this.mLayout = layoutManager;
        if (layoutManager != null) {
            if (layoutManager.mRecyclerView != null) {
                throw new IllegalArgumentException("LayoutManager " + layoutManager + " is already attached to a RecyclerView:" + layoutManager.mRecyclerView.exceptionLabel());
            }
            layoutManager.setRecyclerView(this);
            if (this.mIsAttached) {
                LayoutManager layoutManager3 = this.mLayout;
                layoutManager3.mIsAttachedToWindow = true;
                layoutManager3.onAttachedToWindow(this);
            }
        }
        this.mRecycler.updateViewCacheSize();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    public final void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override // android.view.View
    public final void setNestedScrollingEnabled(boolean z) {
        NestedScrollingChildHelper scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.mIsNestedScrollingEnabled) {
            View view = scrollingChildHelper.mView;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api21Impl.stopNestedScroll(view);
        }
        scrollingChildHelper.mIsNestedScrollingEnabled = z;
    }

    public final void setScrollState(int i) {
        LinearSmoothScroller linearSmoothScroller;
        if (i == this.mScrollState) {
            return;
        }
        this.mScrollState = i;
        if (i != 2) {
            ViewFlinger viewFlinger = this.mViewFlinger;
            RecyclerView.this.removeCallbacks(viewFlinger);
            viewFlinger.mOverScroller.abortAnimation();
            LayoutManager layoutManager = this.mLayout;
            if (layoutManager != null && (linearSmoothScroller = layoutManager.mSmoothScroller) != null) {
                linearSmoothScroller.stop();
            }
        }
        LayoutManager layoutManager2 = this.mLayout;
        if (layoutManager2 != null) {
            layoutManager2.onScrollStateChanged(i);
        }
        List list = this.mScrollListeners;
        if (list != null) {
            for (int size = ((ArrayList) list).size() - 1; size >= 0; size--) {
                ((OnScrollListener) ((ArrayList) this.mScrollListeners).get(size)).onScrollStateChanged(i);
            }
        }
    }

    public final boolean shouldAbsorb(EdgeEffect edgeEffect, int i, int i2) {
        if (i > 0) {
            return true;
        }
        float distance = EdgeEffectCompat$Api31Impl.getDistance(edgeEffect) * i2;
        double log = Math.log((Math.abs(-i) * 0.35f) / (this.mPhysicalCoef * 0.015f));
        double d = DECELERATION_RATE;
        return ((float) (Math.exp((d / (d - 1.0d)) * log) * ((double) (this.mPhysicalCoef * 0.015f)))) < distance;
    }

    public final void smoothScrollBy$1(int i, int i2, boolean z) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.mLayoutSuppressed) {
            return;
        }
        if (!layoutManager.canScrollHorizontally()) {
            i = 0;
        }
        if (!this.mLayout.canScrollVertically()) {
            i2 = 0;
        }
        if (i == 0 && i2 == 0) {
            return;
        }
        if (z) {
            int i3 = i != 0 ? 1 : 0;
            if (i2 != 0) {
                i3 |= 2;
            }
            getScrollingChildHelper().startNestedScroll(i3, 1);
        }
        this.mViewFlinger.smoothScrollBy(i, i2, Integer.MIN_VALUE, null);
    }

    public final void smoothScrollToPosition(int i) {
        if (this.mLayoutSuppressed) {
            return;
        }
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            layoutManager.smoothScrollToPosition(this, i);
        }
    }

    public final void startInterceptRequestLayout() {
        int i = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = i;
        if (i != 1 || this.mLayoutSuppressed) {
            return;
        }
        this.mLayoutWasDefered = false;
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i) {
        return getScrollingChildHelper().startNestedScroll(i, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void startNestedScrollForType(int i) {
        boolean canScrollHorizontally = this.mLayout.canScrollHorizontally();
        int i2 = canScrollHorizontally;
        if (this.mLayout.canScrollVertically()) {
            i2 = (canScrollHorizontally ? 1 : 0) | 2;
        }
        getScrollingChildHelper().startNestedScroll(i2, i);
    }

    public final void stopInterceptRequestLayout(boolean z) {
        if (this.mInterceptRequestLayoutDepth < 1) {
            this.mInterceptRequestLayoutDepth = 1;
        }
        if (!z && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
        if (this.mInterceptRequestLayoutDepth == 1) {
            if (z && this.mLayoutWasDefered && !this.mLayoutSuppressed && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutSuppressed) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        getScrollingChildHelper().stopNestedScroll(0);
    }

    public final void stopScroll() {
        LinearSmoothScroller linearSmoothScroller;
        setScrollState(0);
        ViewFlinger viewFlinger = this.mViewFlinger;
        RecyclerView.this.removeCallbacks(viewFlinger);
        viewFlinger.mOverScroller.abortAnimation();
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager == null || (linearSmoothScroller = layoutManager.mSmoothScroller) == null) {
            return;
        }
        linearSmoothScroller.stop();
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z) {
        if (z != this.mLayoutSuppressed) {
            assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
            if (z) {
                long uptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0));
                this.mLayoutSuppressed = true;
                this.mIgnoreMotionEventTillDown = true;
                stopScroll();
                return;
            }
            this.mLayoutSuppressed = false;
            if (this.mLayoutWasDefered && this.mLayout != null && this.mAdapter != null) {
                requestLayout();
            }
            this.mLayoutWasDefered = false;
        }
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.android.wm.shell.R.attr.recyclerViewStyle);
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [androidx.recyclerview.widget.RecyclerView$1] */
    public RecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ClassLoader classLoader;
        Object[] objArr;
        Constructor constructor;
        this.mObserver = new RecyclerViewDataObserver();
        this.mRecycler = new Recycler();
        this.mViewInfoStore = new ViewInfoStore();
        final int i2 = 0;
        new Runnable(this) { // from class: androidx.recyclerview.widget.RecyclerView.1
            public final /* synthetic */ RecyclerView this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final int i3 = 1;
                final int i4 = 0;
                switch (i2) {
                    case 0:
                        RecyclerView recyclerView = this.this$0;
                        if (recyclerView.mFirstLayoutComplete && !recyclerView.isLayoutRequested()) {
                            RecyclerView recyclerView2 = this.this$0;
                            if (!recyclerView2.mIsAttached) {
                                recyclerView2.requestLayout();
                                break;
                            } else if (!recyclerView2.mLayoutSuppressed) {
                                recyclerView2.consumePendingUpdateOperations();
                                break;
                            } else {
                                recyclerView2.mLayoutWasDefered = true;
                                break;
                            }
                        }
                        break;
                    default:
                        final DefaultItemAnimator defaultItemAnimator = this.this$0.mItemAnimator;
                        if (defaultItemAnimator != null) {
                            boolean isEmpty = defaultItemAnimator.mPendingRemovals.isEmpty();
                            boolean isEmpty2 = defaultItemAnimator.mPendingMoves.isEmpty();
                            boolean isEmpty3 = defaultItemAnimator.mPendingChanges.isEmpty();
                            boolean isEmpty4 = defaultItemAnimator.mPendingAdditions.isEmpty();
                            if (!isEmpty || !isEmpty2 || !isEmpty4 || !isEmpty3) {
                                Iterator it = defaultItemAnimator.mPendingRemovals.iterator();
                                while (true) {
                                    boolean hasNext = it.hasNext();
                                    long j = defaultItemAnimator.mRemoveDuration;
                                    if (hasNext) {
                                        ViewHolder viewHolder = (ViewHolder) it.next();
                                        View view = viewHolder.itemView;
                                        ViewPropertyAnimator animate = view.animate();
                                        defaultItemAnimator.mRemoveAnimations.add(viewHolder);
                                        animate.setDuration(j).alpha(0.0f).setListener(defaultItemAnimator.new AnonymousClass4(viewHolder, animate, view)).start();
                                    } else {
                                        defaultItemAnimator.mPendingRemovals.clear();
                                        if (!isEmpty2) {
                                            final ArrayList arrayList = new ArrayList();
                                            arrayList.addAll(defaultItemAnimator.mPendingMoves);
                                            defaultItemAnimator.mMovesList.add(arrayList);
                                            defaultItemAnimator.mPendingMoves.clear();
                                            Runnable anonymousClass1 = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.1
                                                public final /* synthetic */ int $r8$classId;
                                                public final /* synthetic */ DefaultItemAnimator this$0;
                                                public final /* synthetic */ ArrayList val$moves;

                                                public /* synthetic */ AnonymousClass1(final DefaultItemAnimator defaultItemAnimator2, final ArrayList arrayList2, final int i42) {
                                                    r3 = i42;
                                                    r1 = defaultItemAnimator2;
                                                    r2 = arrayList2;
                                                }

                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    switch (r3) {
                                                        case 0:
                                                            Iterator it2 = r2.iterator();
                                                            while (it2.hasNext()) {
                                                                MoveInfo moveInfo = (MoveInfo) it2.next();
                                                                DefaultItemAnimator defaultItemAnimator2 = r1;
                                                                RecyclerView.ViewHolder viewHolder2 = moveInfo.holder;
                                                                defaultItemAnimator2.getClass();
                                                                View view2 = viewHolder2.itemView;
                                                                int i5 = moveInfo.toX - moveInfo.fromX;
                                                                int i6 = moveInfo.toY - moveInfo.fromY;
                                                                if (i5 != 0) {
                                                                    view2.animate().translationX(0.0f);
                                                                }
                                                                if (i6 != 0) {
                                                                    view2.animate().translationY(0.0f);
                                                                }
                                                                ViewPropertyAnimator animate2 = view2.animate();
                                                                defaultItemAnimator2.mMoveAnimations.add(viewHolder2);
                                                                animate2.setDuration(defaultItemAnimator2.mMoveDuration).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.6
                                                                    public final /* synthetic */ ViewPropertyAnimator val$animation;
                                                                    public final /* synthetic */ int val$deltaX;
                                                                    public final /* synthetic */ int val$deltaY;
                                                                    public final /* synthetic */ RecyclerView.ViewHolder val$holder;
                                                                    public final /* synthetic */ View val$view;

                                                                    public AnonymousClass6(RecyclerView.ViewHolder viewHolder22, int i52, View view22, int i62, ViewPropertyAnimator animate22) {
                                                                        r2 = viewHolder22;
                                                                        r3 = i52;
                                                                        r4 = view22;
                                                                        r5 = i62;
                                                                        r6 = animate22;
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationCancel(Animator animator) {
                                                                        if (r3 != 0) {
                                                                            r4.setTranslationX(0.0f);
                                                                        }
                                                                        if (r5 != 0) {
                                                                            r4.setTranslationY(0.0f);
                                                                        }
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationEnd(Animator animator) {
                                                                        r6.setListener(null);
                                                                        DefaultItemAnimator.this.dispatchAnimationFinished(r2);
                                                                        DefaultItemAnimator.this.mMoveAnimations.remove(r2);
                                                                        DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationStart(Animator animator) {
                                                                        DefaultItemAnimator.this.getClass();
                                                                    }
                                                                }).start();
                                                            }
                                                            r2.clear();
                                                            r1.mMovesList.remove(r2);
                                                            break;
                                                        case 1:
                                                            Iterator it3 = r2.iterator();
                                                            while (it3.hasNext()) {
                                                                ChangeInfo changeInfo = (ChangeInfo) it3.next();
                                                                DefaultItemAnimator defaultItemAnimator3 = r1;
                                                                defaultItemAnimator3.getClass();
                                                                RecyclerView.ViewHolder viewHolder3 = changeInfo.oldHolder;
                                                                View view3 = viewHolder3 == null ? null : viewHolder3.itemView;
                                                                RecyclerView.ViewHolder viewHolder4 = changeInfo.newHolder;
                                                                View view4 = viewHolder4 != null ? viewHolder4.itemView : null;
                                                                long j2 = defaultItemAnimator3.mChangeDuration;
                                                                if (view3 != null) {
                                                                    ViewPropertyAnimator duration = view3.animate().setDuration(j2);
                                                                    defaultItemAnimator3.mChangeAnimations.add(changeInfo.oldHolder);
                                                                    duration.translationX(changeInfo.toX - changeInfo.fromX);
                                                                    duration.translationY(changeInfo.toY - changeInfo.fromY);
                                                                    duration.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator32, ChangeInfo changeInfo2, ViewPropertyAnimator duration2, View view32, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator32;
                                                                            r2 = changeInfo2;
                                                                            r3 = duration2;
                                                                            r4 = view32;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                                if (view4 != null) {
                                                                    ViewPropertyAnimator animate3 = view4.animate();
                                                                    defaultItemAnimator32.mChangeAnimations.add(changeInfo2.newHolder);
                                                                    animate3.translationX(0.0f).translationY(0.0f).setDuration(j2).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator32, ChangeInfo changeInfo2, ViewPropertyAnimator animate32, View view42, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator32;
                                                                            r2 = changeInfo2;
                                                                            r3 = animate32;
                                                                            r4 = view42;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                            }
                                                            r2.clear();
                                                            r1.mChangesList.remove(r2);
                                                            break;
                                                        default:
                                                            Iterator it4 = r2.iterator();
                                                            while (it4.hasNext()) {
                                                                RecyclerView.ViewHolder viewHolder5 = (RecyclerView.ViewHolder) it4.next();
                                                                DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                defaultItemAnimator4.getClass();
                                                                View view5 = viewHolder5.itemView;
                                                                ViewPropertyAnimator animate4 = view5.animate();
                                                                defaultItemAnimator4.mAddAnimations.add(viewHolder5);
                                                                animate4.alpha(1.0f).setDuration(defaultItemAnimator4.mAddDuration).setListener(defaultItemAnimator4.new AnonymousClass4(viewHolder5, view5, animate4)).start();
                                                            }
                                                            r2.clear();
                                                            r1.mAdditionsList.remove(r2);
                                                            break;
                                                    }
                                                }
                                            };
                                            if (isEmpty) {
                                                anonymousClass1.run();
                                            } else {
                                                View view2 = ((DefaultItemAnimator.MoveInfo) arrayList2.get(0)).holder.itemView;
                                                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                                                view2.postOnAnimationDelayed(anonymousClass1, j);
                                            }
                                        }
                                        if (!isEmpty3) {
                                            final ArrayList arrayList2 = new ArrayList();
                                            arrayList2.addAll(defaultItemAnimator2.mPendingChanges);
                                            defaultItemAnimator2.mChangesList.add(arrayList2);
                                            defaultItemAnimator2.mPendingChanges.clear();
                                            Runnable anonymousClass12 = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.1
                                                public final /* synthetic */ int $r8$classId;
                                                public final /* synthetic */ DefaultItemAnimator this$0;
                                                public final /* synthetic */ ArrayList val$moves;

                                                public /* synthetic */ AnonymousClass1(final DefaultItemAnimator defaultItemAnimator2, final ArrayList arrayList22, final int i32) {
                                                    r3 = i32;
                                                    r1 = defaultItemAnimator2;
                                                    r2 = arrayList22;
                                                }

                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    switch (r3) {
                                                        case 0:
                                                            Iterator it2 = r2.iterator();
                                                            while (it2.hasNext()) {
                                                                MoveInfo moveInfo = (MoveInfo) it2.next();
                                                                DefaultItemAnimator defaultItemAnimator2 = r1;
                                                                RecyclerView.ViewHolder viewHolder22 = moveInfo.holder;
                                                                defaultItemAnimator2.getClass();
                                                                View view22 = viewHolder22.itemView;
                                                                int i52 = moveInfo.toX - moveInfo.fromX;
                                                                int i62 = moveInfo.toY - moveInfo.fromY;
                                                                if (i52 != 0) {
                                                                    view22.animate().translationX(0.0f);
                                                                }
                                                                if (i62 != 0) {
                                                                    view22.animate().translationY(0.0f);
                                                                }
                                                                ViewPropertyAnimator animate22 = view22.animate();
                                                                defaultItemAnimator2.mMoveAnimations.add(viewHolder22);
                                                                animate22.setDuration(defaultItemAnimator2.mMoveDuration).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.6
                                                                    public final /* synthetic */ ViewPropertyAnimator val$animation;
                                                                    public final /* synthetic */ int val$deltaX;
                                                                    public final /* synthetic */ int val$deltaY;
                                                                    public final /* synthetic */ RecyclerView.ViewHolder val$holder;
                                                                    public final /* synthetic */ View val$view;

                                                                    public AnonymousClass6(RecyclerView.ViewHolder viewHolder222, int i522, View view222, int i622, ViewPropertyAnimator animate222) {
                                                                        r2 = viewHolder222;
                                                                        r3 = i522;
                                                                        r4 = view222;
                                                                        r5 = i622;
                                                                        r6 = animate222;
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationCancel(Animator animator) {
                                                                        if (r3 != 0) {
                                                                            r4.setTranslationX(0.0f);
                                                                        }
                                                                        if (r5 != 0) {
                                                                            r4.setTranslationY(0.0f);
                                                                        }
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationEnd(Animator animator) {
                                                                        r6.setListener(null);
                                                                        DefaultItemAnimator.this.dispatchAnimationFinished(r2);
                                                                        DefaultItemAnimator.this.mMoveAnimations.remove(r2);
                                                                        DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationStart(Animator animator) {
                                                                        DefaultItemAnimator.this.getClass();
                                                                    }
                                                                }).start();
                                                            }
                                                            r2.clear();
                                                            r1.mMovesList.remove(r2);
                                                            break;
                                                        case 1:
                                                            Iterator it3 = r2.iterator();
                                                            while (it3.hasNext()) {
                                                                ChangeInfo changeInfo2 = (ChangeInfo) it3.next();
                                                                DefaultItemAnimator defaultItemAnimator32 = r1;
                                                                defaultItemAnimator32.getClass();
                                                                RecyclerView.ViewHolder viewHolder3 = changeInfo2.oldHolder;
                                                                View view32 = viewHolder3 == null ? null : viewHolder3.itemView;
                                                                RecyclerView.ViewHolder viewHolder4 = changeInfo2.newHolder;
                                                                View view42 = viewHolder4 != null ? viewHolder4.itemView : null;
                                                                long j2 = defaultItemAnimator32.mChangeDuration;
                                                                if (view32 != null) {
                                                                    ViewPropertyAnimator duration2 = view32.animate().setDuration(j2);
                                                                    defaultItemAnimator32.mChangeAnimations.add(changeInfo2.oldHolder);
                                                                    duration2.translationX(changeInfo2.toX - changeInfo2.fromX);
                                                                    duration2.translationY(changeInfo2.toY - changeInfo2.fromY);
                                                                    duration2.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator322, ChangeInfo changeInfo22, ViewPropertyAnimator duration22, View view322, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator322;
                                                                            r2 = changeInfo22;
                                                                            r3 = duration22;
                                                                            r4 = view322;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                                if (view42 != null) {
                                                                    ViewPropertyAnimator animate32 = view42.animate();
                                                                    defaultItemAnimator322.mChangeAnimations.add(changeInfo22.newHolder);
                                                                    animate32.translationX(0.0f).translationY(0.0f).setDuration(j2).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator322, ChangeInfo changeInfo22, ViewPropertyAnimator animate322, View view422, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator322;
                                                                            r2 = changeInfo22;
                                                                            r3 = animate322;
                                                                            r4 = view422;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                            }
                                                            r2.clear();
                                                            r1.mChangesList.remove(r2);
                                                            break;
                                                        default:
                                                            Iterator it4 = r2.iterator();
                                                            while (it4.hasNext()) {
                                                                RecyclerView.ViewHolder viewHolder5 = (RecyclerView.ViewHolder) it4.next();
                                                                DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                defaultItemAnimator4.getClass();
                                                                View view5 = viewHolder5.itemView;
                                                                ViewPropertyAnimator animate4 = view5.animate();
                                                                defaultItemAnimator4.mAddAnimations.add(viewHolder5);
                                                                animate4.alpha(1.0f).setDuration(defaultItemAnimator4.mAddDuration).setListener(defaultItemAnimator4.new AnonymousClass4(viewHolder5, view5, animate4)).start();
                                                            }
                                                            r2.clear();
                                                            r1.mAdditionsList.remove(r2);
                                                            break;
                                                    }
                                                }
                                            };
                                            if (isEmpty) {
                                                anonymousClass12.run();
                                            } else {
                                                View view3 = ((DefaultItemAnimator.ChangeInfo) arrayList22.get(0)).oldHolder.itemView;
                                                WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                                                view3.postOnAnimationDelayed(anonymousClass12, j);
                                            }
                                        }
                                        if (!isEmpty4) {
                                            final ArrayList arrayList3 = new ArrayList();
                                            arrayList3.addAll(defaultItemAnimator2.mPendingAdditions);
                                            defaultItemAnimator2.mAdditionsList.add(arrayList3);
                                            defaultItemAnimator2.mPendingAdditions.clear();
                                            final int i5 = 2;
                                            Runnable anonymousClass13 = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.1
                                                public final /* synthetic */ int $r8$classId;
                                                public final /* synthetic */ DefaultItemAnimator this$0;
                                                public final /* synthetic */ ArrayList val$moves;

                                                public /* synthetic */ AnonymousClass1(final DefaultItemAnimator defaultItemAnimator2, final ArrayList arrayList32, final int i52) {
                                                    r3 = i52;
                                                    r1 = defaultItemAnimator2;
                                                    r2 = arrayList32;
                                                }

                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    switch (r3) {
                                                        case 0:
                                                            Iterator it2 = r2.iterator();
                                                            while (it2.hasNext()) {
                                                                MoveInfo moveInfo = (MoveInfo) it2.next();
                                                                DefaultItemAnimator defaultItemAnimator2 = r1;
                                                                RecyclerView.ViewHolder viewHolder222 = moveInfo.holder;
                                                                defaultItemAnimator2.getClass();
                                                                View view222 = viewHolder222.itemView;
                                                                int i522 = moveInfo.toX - moveInfo.fromX;
                                                                int i622 = moveInfo.toY - moveInfo.fromY;
                                                                if (i522 != 0) {
                                                                    view222.animate().translationX(0.0f);
                                                                }
                                                                if (i622 != 0) {
                                                                    view222.animate().translationY(0.0f);
                                                                }
                                                                ViewPropertyAnimator animate222 = view222.animate();
                                                                defaultItemAnimator2.mMoveAnimations.add(viewHolder222);
                                                                animate222.setDuration(defaultItemAnimator2.mMoveDuration).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.6
                                                                    public final /* synthetic */ ViewPropertyAnimator val$animation;
                                                                    public final /* synthetic */ int val$deltaX;
                                                                    public final /* synthetic */ int val$deltaY;
                                                                    public final /* synthetic */ RecyclerView.ViewHolder val$holder;
                                                                    public final /* synthetic */ View val$view;

                                                                    public AnonymousClass6(RecyclerView.ViewHolder viewHolder2222, int i5222, View view2222, int i6222, ViewPropertyAnimator animate2222) {
                                                                        r2 = viewHolder2222;
                                                                        r3 = i5222;
                                                                        r4 = view2222;
                                                                        r5 = i6222;
                                                                        r6 = animate2222;
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationCancel(Animator animator) {
                                                                        if (r3 != 0) {
                                                                            r4.setTranslationX(0.0f);
                                                                        }
                                                                        if (r5 != 0) {
                                                                            r4.setTranslationY(0.0f);
                                                                        }
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationEnd(Animator animator) {
                                                                        r6.setListener(null);
                                                                        DefaultItemAnimator.this.dispatchAnimationFinished(r2);
                                                                        DefaultItemAnimator.this.mMoveAnimations.remove(r2);
                                                                        DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationStart(Animator animator) {
                                                                        DefaultItemAnimator.this.getClass();
                                                                    }
                                                                }).start();
                                                            }
                                                            r2.clear();
                                                            r1.mMovesList.remove(r2);
                                                            break;
                                                        case 1:
                                                            Iterator it3 = r2.iterator();
                                                            while (it3.hasNext()) {
                                                                ChangeInfo changeInfo22 = (ChangeInfo) it3.next();
                                                                DefaultItemAnimator defaultItemAnimator322 = r1;
                                                                defaultItemAnimator322.getClass();
                                                                RecyclerView.ViewHolder viewHolder3 = changeInfo22.oldHolder;
                                                                View view322 = viewHolder3 == null ? null : viewHolder3.itemView;
                                                                RecyclerView.ViewHolder viewHolder4 = changeInfo22.newHolder;
                                                                View view422 = viewHolder4 != null ? viewHolder4.itemView : null;
                                                                long j2 = defaultItemAnimator322.mChangeDuration;
                                                                if (view322 != null) {
                                                                    ViewPropertyAnimator duration22 = view322.animate().setDuration(j2);
                                                                    defaultItemAnimator322.mChangeAnimations.add(changeInfo22.oldHolder);
                                                                    duration22.translationX(changeInfo22.toX - changeInfo22.fromX);
                                                                    duration22.translationY(changeInfo22.toY - changeInfo22.fromY);
                                                                    duration22.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator3222, ChangeInfo changeInfo222, ViewPropertyAnimator duration222, View view3222, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator3222;
                                                                            r2 = changeInfo222;
                                                                            r3 = duration222;
                                                                            r4 = view3222;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                                if (view422 != null) {
                                                                    ViewPropertyAnimator animate322 = view422.animate();
                                                                    defaultItemAnimator3222.mChangeAnimations.add(changeInfo222.newHolder);
                                                                    animate322.translationX(0.0f).translationY(0.0f).setDuration(j2).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator3222, ChangeInfo changeInfo222, ViewPropertyAnimator animate3222, View view4222, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator3222;
                                                                            r2 = changeInfo222;
                                                                            r3 = animate3222;
                                                                            r4 = view4222;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                            }
                                                            r2.clear();
                                                            r1.mChangesList.remove(r2);
                                                            break;
                                                        default:
                                                            Iterator it4 = r2.iterator();
                                                            while (it4.hasNext()) {
                                                                RecyclerView.ViewHolder viewHolder5 = (RecyclerView.ViewHolder) it4.next();
                                                                DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                defaultItemAnimator4.getClass();
                                                                View view5 = viewHolder5.itemView;
                                                                ViewPropertyAnimator animate4 = view5.animate();
                                                                defaultItemAnimator4.mAddAnimations.add(viewHolder5);
                                                                animate4.alpha(1.0f).setDuration(defaultItemAnimator4.mAddDuration).setListener(defaultItemAnimator4.new AnonymousClass4(viewHolder5, view5, animate4)).start();
                                                            }
                                                            r2.clear();
                                                            r1.mAdditionsList.remove(r2);
                                                            break;
                                                    }
                                                }
                                            };
                                            if (isEmpty && isEmpty2 && isEmpty3) {
                                                anonymousClass13.run();
                                            } else {
                                                if (isEmpty) {
                                                    j = 0;
                                                }
                                                long max = Math.max(!isEmpty2 ? defaultItemAnimator2.mMoveDuration : 0L, isEmpty3 ? 0L : defaultItemAnimator2.mChangeDuration) + j;
                                                View view4 = ((ViewHolder) arrayList32.get(0)).itemView;
                                                WeakHashMap weakHashMap3 = ViewCompat.sViewPropertyAnimatorMap;
                                                view4.postOnAnimationDelayed(anonymousClass13, max);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        this.this$0.mPostedAnimatorRunner = false;
                        break;
                }
            }
        };
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mRecyclerListeners = new ArrayList();
        this.mItemDecorations = new ArrayList();
        this.mOnItemTouchListeners = new ArrayList();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = sDefaultEdgeEffectFactory;
        this.mItemAnimator = new DefaultItemAnimator();
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        final int i3 = 1;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new ViewFlinger();
        this.mPrefetchRegistry = ALLOW_THREAD_GAP_WORK ? new GapWorker.LayoutPrefetchRegistryImpl() : null;
        this.mState = new State();
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        this.mItemAnimatorListener = anonymousClass5;
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.mReusableIntPair = new int[2];
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new Runnable(this) { // from class: androidx.recyclerview.widget.RecyclerView.1
            public final /* synthetic */ RecyclerView this$0;

            {
                this.this$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final int i32 = 1;
                final int i42 = 0;
                switch (i3) {
                    case 0:
                        RecyclerView recyclerView = this.this$0;
                        if (recyclerView.mFirstLayoutComplete && !recyclerView.isLayoutRequested()) {
                            RecyclerView recyclerView2 = this.this$0;
                            if (!recyclerView2.mIsAttached) {
                                recyclerView2.requestLayout();
                                break;
                            } else if (!recyclerView2.mLayoutSuppressed) {
                                recyclerView2.consumePendingUpdateOperations();
                                break;
                            } else {
                                recyclerView2.mLayoutWasDefered = true;
                                break;
                            }
                        }
                        break;
                    default:
                        final DefaultItemAnimator defaultItemAnimator2 = this.this$0.mItemAnimator;
                        if (defaultItemAnimator2 != null) {
                            boolean isEmpty = defaultItemAnimator2.mPendingRemovals.isEmpty();
                            boolean isEmpty2 = defaultItemAnimator2.mPendingMoves.isEmpty();
                            boolean isEmpty3 = defaultItemAnimator2.mPendingChanges.isEmpty();
                            boolean isEmpty4 = defaultItemAnimator2.mPendingAdditions.isEmpty();
                            if (!isEmpty || !isEmpty2 || !isEmpty4 || !isEmpty3) {
                                Iterator it = defaultItemAnimator2.mPendingRemovals.iterator();
                                while (true) {
                                    boolean hasNext = it.hasNext();
                                    long j = defaultItemAnimator2.mRemoveDuration;
                                    if (hasNext) {
                                        ViewHolder viewHolder = (ViewHolder) it.next();
                                        View view = viewHolder.itemView;
                                        ViewPropertyAnimator animate = view.animate();
                                        defaultItemAnimator2.mRemoveAnimations.add(viewHolder);
                                        animate.setDuration(j).alpha(0.0f).setListener(defaultItemAnimator2.new AnonymousClass4(viewHolder, animate, view)).start();
                                    } else {
                                        defaultItemAnimator2.mPendingRemovals.clear();
                                        if (!isEmpty2) {
                                            final ArrayList arrayList2 = new ArrayList();
                                            arrayList2.addAll(defaultItemAnimator2.mPendingMoves);
                                            defaultItemAnimator2.mMovesList.add(arrayList2);
                                            defaultItemAnimator2.mPendingMoves.clear();
                                            Runnable anonymousClass1 = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.1
                                                public final /* synthetic */ int $r8$classId;
                                                public final /* synthetic */ DefaultItemAnimator this$0;
                                                public final /* synthetic */ ArrayList val$moves;

                                                public /* synthetic */ AnonymousClass1(final DefaultItemAnimator defaultItemAnimator22, final ArrayList arrayList22, final int i422) {
                                                    r3 = i422;
                                                    r1 = defaultItemAnimator22;
                                                    r2 = arrayList22;
                                                }

                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    switch (r3) {
                                                        case 0:
                                                            Iterator it2 = r2.iterator();
                                                            while (it2.hasNext()) {
                                                                MoveInfo moveInfo = (MoveInfo) it2.next();
                                                                DefaultItemAnimator defaultItemAnimator22 = r1;
                                                                RecyclerView.ViewHolder viewHolder2222 = moveInfo.holder;
                                                                defaultItemAnimator22.getClass();
                                                                View view2222 = viewHolder2222.itemView;
                                                                int i5222 = moveInfo.toX - moveInfo.fromX;
                                                                int i6222 = moveInfo.toY - moveInfo.fromY;
                                                                if (i5222 != 0) {
                                                                    view2222.animate().translationX(0.0f);
                                                                }
                                                                if (i6222 != 0) {
                                                                    view2222.animate().translationY(0.0f);
                                                                }
                                                                ViewPropertyAnimator animate2222 = view2222.animate();
                                                                defaultItemAnimator22.mMoveAnimations.add(viewHolder2222);
                                                                animate2222.setDuration(defaultItemAnimator22.mMoveDuration).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.6
                                                                    public final /* synthetic */ ViewPropertyAnimator val$animation;
                                                                    public final /* synthetic */ int val$deltaX;
                                                                    public final /* synthetic */ int val$deltaY;
                                                                    public final /* synthetic */ RecyclerView.ViewHolder val$holder;
                                                                    public final /* synthetic */ View val$view;

                                                                    public AnonymousClass6(RecyclerView.ViewHolder viewHolder22222, int i52222, View view22222, int i62222, ViewPropertyAnimator animate22222) {
                                                                        r2 = viewHolder22222;
                                                                        r3 = i52222;
                                                                        r4 = view22222;
                                                                        r5 = i62222;
                                                                        r6 = animate22222;
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationCancel(Animator animator) {
                                                                        if (r3 != 0) {
                                                                            r4.setTranslationX(0.0f);
                                                                        }
                                                                        if (r5 != 0) {
                                                                            r4.setTranslationY(0.0f);
                                                                        }
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationEnd(Animator animator) {
                                                                        r6.setListener(null);
                                                                        DefaultItemAnimator.this.dispatchAnimationFinished(r2);
                                                                        DefaultItemAnimator.this.mMoveAnimations.remove(r2);
                                                                        DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationStart(Animator animator) {
                                                                        DefaultItemAnimator.this.getClass();
                                                                    }
                                                                }).start();
                                                            }
                                                            r2.clear();
                                                            r1.mMovesList.remove(r2);
                                                            break;
                                                        case 1:
                                                            Iterator it3 = r2.iterator();
                                                            while (it3.hasNext()) {
                                                                ChangeInfo changeInfo222 = (ChangeInfo) it3.next();
                                                                DefaultItemAnimator defaultItemAnimator3222 = r1;
                                                                defaultItemAnimator3222.getClass();
                                                                RecyclerView.ViewHolder viewHolder3 = changeInfo222.oldHolder;
                                                                View view3222 = viewHolder3 == null ? null : viewHolder3.itemView;
                                                                RecyclerView.ViewHolder viewHolder4 = changeInfo222.newHolder;
                                                                View view4222 = viewHolder4 != null ? viewHolder4.itemView : null;
                                                                long j2 = defaultItemAnimator3222.mChangeDuration;
                                                                if (view3222 != null) {
                                                                    ViewPropertyAnimator duration222 = view3222.animate().setDuration(j2);
                                                                    defaultItemAnimator3222.mChangeAnimations.add(changeInfo222.oldHolder);
                                                                    duration222.translationX(changeInfo222.toX - changeInfo222.fromX);
                                                                    duration222.translationY(changeInfo222.toY - changeInfo222.fromY);
                                                                    duration222.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator32222, ChangeInfo changeInfo2222, ViewPropertyAnimator duration2222, View view32222, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator32222;
                                                                            r2 = changeInfo2222;
                                                                            r3 = duration2222;
                                                                            r4 = view32222;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                                if (view4222 != null) {
                                                                    ViewPropertyAnimator animate3222 = view4222.animate();
                                                                    defaultItemAnimator32222.mChangeAnimations.add(changeInfo2222.newHolder);
                                                                    animate3222.translationX(0.0f).translationY(0.0f).setDuration(j2).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator32222, ChangeInfo changeInfo2222, ViewPropertyAnimator animate32222, View view42222, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator32222;
                                                                            r2 = changeInfo2222;
                                                                            r3 = animate32222;
                                                                            r4 = view42222;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                            }
                                                            r2.clear();
                                                            r1.mChangesList.remove(r2);
                                                            break;
                                                        default:
                                                            Iterator it4 = r2.iterator();
                                                            while (it4.hasNext()) {
                                                                RecyclerView.ViewHolder viewHolder5 = (RecyclerView.ViewHolder) it4.next();
                                                                DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                defaultItemAnimator4.getClass();
                                                                View view5 = viewHolder5.itemView;
                                                                ViewPropertyAnimator animate4 = view5.animate();
                                                                defaultItemAnimator4.mAddAnimations.add(viewHolder5);
                                                                animate4.alpha(1.0f).setDuration(defaultItemAnimator4.mAddDuration).setListener(defaultItemAnimator4.new AnonymousClass4(viewHolder5, view5, animate4)).start();
                                                            }
                                                            r2.clear();
                                                            r1.mAdditionsList.remove(r2);
                                                            break;
                                                    }
                                                }
                                            };
                                            if (isEmpty) {
                                                anonymousClass1.run();
                                            } else {
                                                View view2 = ((DefaultItemAnimator.MoveInfo) arrayList22.get(0)).holder.itemView;
                                                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                                                view2.postOnAnimationDelayed(anonymousClass1, j);
                                            }
                                        }
                                        if (!isEmpty3) {
                                            final ArrayList arrayList22 = new ArrayList();
                                            arrayList22.addAll(defaultItemAnimator22.mPendingChanges);
                                            defaultItemAnimator22.mChangesList.add(arrayList22);
                                            defaultItemAnimator22.mPendingChanges.clear();
                                            Runnable anonymousClass12 = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.1
                                                public final /* synthetic */ int $r8$classId;
                                                public final /* synthetic */ DefaultItemAnimator this$0;
                                                public final /* synthetic */ ArrayList val$moves;

                                                public /* synthetic */ AnonymousClass1(final DefaultItemAnimator defaultItemAnimator22, final ArrayList arrayList222, final int i322) {
                                                    r3 = i322;
                                                    r1 = defaultItemAnimator22;
                                                    r2 = arrayList222;
                                                }

                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    switch (r3) {
                                                        case 0:
                                                            Iterator it2 = r2.iterator();
                                                            while (it2.hasNext()) {
                                                                MoveInfo moveInfo = (MoveInfo) it2.next();
                                                                DefaultItemAnimator defaultItemAnimator22 = r1;
                                                                RecyclerView.ViewHolder viewHolder22222 = moveInfo.holder;
                                                                defaultItemAnimator22.getClass();
                                                                View view22222 = viewHolder22222.itemView;
                                                                int i52222 = moveInfo.toX - moveInfo.fromX;
                                                                int i62222 = moveInfo.toY - moveInfo.fromY;
                                                                if (i52222 != 0) {
                                                                    view22222.animate().translationX(0.0f);
                                                                }
                                                                if (i62222 != 0) {
                                                                    view22222.animate().translationY(0.0f);
                                                                }
                                                                ViewPropertyAnimator animate22222 = view22222.animate();
                                                                defaultItemAnimator22.mMoveAnimations.add(viewHolder22222);
                                                                animate22222.setDuration(defaultItemAnimator22.mMoveDuration).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.6
                                                                    public final /* synthetic */ ViewPropertyAnimator val$animation;
                                                                    public final /* synthetic */ int val$deltaX;
                                                                    public final /* synthetic */ int val$deltaY;
                                                                    public final /* synthetic */ RecyclerView.ViewHolder val$holder;
                                                                    public final /* synthetic */ View val$view;

                                                                    public AnonymousClass6(RecyclerView.ViewHolder viewHolder222222, int i522222, View view222222, int i622222, ViewPropertyAnimator animate222222) {
                                                                        r2 = viewHolder222222;
                                                                        r3 = i522222;
                                                                        r4 = view222222;
                                                                        r5 = i622222;
                                                                        r6 = animate222222;
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationCancel(Animator animator) {
                                                                        if (r3 != 0) {
                                                                            r4.setTranslationX(0.0f);
                                                                        }
                                                                        if (r5 != 0) {
                                                                            r4.setTranslationY(0.0f);
                                                                        }
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationEnd(Animator animator) {
                                                                        r6.setListener(null);
                                                                        DefaultItemAnimator.this.dispatchAnimationFinished(r2);
                                                                        DefaultItemAnimator.this.mMoveAnimations.remove(r2);
                                                                        DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationStart(Animator animator) {
                                                                        DefaultItemAnimator.this.getClass();
                                                                    }
                                                                }).start();
                                                            }
                                                            r2.clear();
                                                            r1.mMovesList.remove(r2);
                                                            break;
                                                        case 1:
                                                            Iterator it3 = r2.iterator();
                                                            while (it3.hasNext()) {
                                                                ChangeInfo changeInfo2222 = (ChangeInfo) it3.next();
                                                                DefaultItemAnimator defaultItemAnimator32222 = r1;
                                                                defaultItemAnimator32222.getClass();
                                                                RecyclerView.ViewHolder viewHolder3 = changeInfo2222.oldHolder;
                                                                View view32222 = viewHolder3 == null ? null : viewHolder3.itemView;
                                                                RecyclerView.ViewHolder viewHolder4 = changeInfo2222.newHolder;
                                                                View view42222 = viewHolder4 != null ? viewHolder4.itemView : null;
                                                                long j2 = defaultItemAnimator32222.mChangeDuration;
                                                                if (view32222 != null) {
                                                                    ViewPropertyAnimator duration2222 = view32222.animate().setDuration(j2);
                                                                    defaultItemAnimator32222.mChangeAnimations.add(changeInfo2222.oldHolder);
                                                                    duration2222.translationX(changeInfo2222.toX - changeInfo2222.fromX);
                                                                    duration2222.translationY(changeInfo2222.toY - changeInfo2222.fromY);
                                                                    duration2222.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator322222, ChangeInfo changeInfo22222, ViewPropertyAnimator duration22222, View view322222, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator322222;
                                                                            r2 = changeInfo22222;
                                                                            r3 = duration22222;
                                                                            r4 = view322222;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                                if (view42222 != null) {
                                                                    ViewPropertyAnimator animate32222 = view42222.animate();
                                                                    defaultItemAnimator322222.mChangeAnimations.add(changeInfo22222.newHolder);
                                                                    animate32222.translationX(0.0f).translationY(0.0f).setDuration(j2).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator322222, ChangeInfo changeInfo22222, ViewPropertyAnimator animate322222, View view422222, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator322222;
                                                                            r2 = changeInfo22222;
                                                                            r3 = animate322222;
                                                                            r4 = view422222;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                            }
                                                            r2.clear();
                                                            r1.mChangesList.remove(r2);
                                                            break;
                                                        default:
                                                            Iterator it4 = r2.iterator();
                                                            while (it4.hasNext()) {
                                                                RecyclerView.ViewHolder viewHolder5 = (RecyclerView.ViewHolder) it4.next();
                                                                DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                defaultItemAnimator4.getClass();
                                                                View view5 = viewHolder5.itemView;
                                                                ViewPropertyAnimator animate4 = view5.animate();
                                                                defaultItemAnimator4.mAddAnimations.add(viewHolder5);
                                                                animate4.alpha(1.0f).setDuration(defaultItemAnimator4.mAddDuration).setListener(defaultItemAnimator4.new AnonymousClass4(viewHolder5, view5, animate4)).start();
                                                            }
                                                            r2.clear();
                                                            r1.mAdditionsList.remove(r2);
                                                            break;
                                                    }
                                                }
                                            };
                                            if (isEmpty) {
                                                anonymousClass12.run();
                                            } else {
                                                View view3 = ((DefaultItemAnimator.ChangeInfo) arrayList222.get(0)).oldHolder.itemView;
                                                WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                                                view3.postOnAnimationDelayed(anonymousClass12, j);
                                            }
                                        }
                                        if (!isEmpty4) {
                                            final ArrayList arrayList32 = new ArrayList();
                                            arrayList32.addAll(defaultItemAnimator22.mPendingAdditions);
                                            defaultItemAnimator22.mAdditionsList.add(arrayList32);
                                            defaultItemAnimator22.mPendingAdditions.clear();
                                            final int i52 = 2;
                                            Runnable anonymousClass13 = new Runnable() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.1
                                                public final /* synthetic */ int $r8$classId;
                                                public final /* synthetic */ DefaultItemAnimator this$0;
                                                public final /* synthetic */ ArrayList val$moves;

                                                public /* synthetic */ AnonymousClass1(final DefaultItemAnimator defaultItemAnimator22, final ArrayList arrayList322, final int i522) {
                                                    r3 = i522;
                                                    r1 = defaultItemAnimator22;
                                                    r2 = arrayList322;
                                                }

                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    switch (r3) {
                                                        case 0:
                                                            Iterator it2 = r2.iterator();
                                                            while (it2.hasNext()) {
                                                                MoveInfo moveInfo = (MoveInfo) it2.next();
                                                                DefaultItemAnimator defaultItemAnimator22 = r1;
                                                                RecyclerView.ViewHolder viewHolder222222 = moveInfo.holder;
                                                                defaultItemAnimator22.getClass();
                                                                View view222222 = viewHolder222222.itemView;
                                                                int i522222 = moveInfo.toX - moveInfo.fromX;
                                                                int i622222 = moveInfo.toY - moveInfo.fromY;
                                                                if (i522222 != 0) {
                                                                    view222222.animate().translationX(0.0f);
                                                                }
                                                                if (i622222 != 0) {
                                                                    view222222.animate().translationY(0.0f);
                                                                }
                                                                ViewPropertyAnimator animate222222 = view222222.animate();
                                                                defaultItemAnimator22.mMoveAnimations.add(viewHolder222222);
                                                                animate222222.setDuration(defaultItemAnimator22.mMoveDuration).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.6
                                                                    public final /* synthetic */ ViewPropertyAnimator val$animation;
                                                                    public final /* synthetic */ int val$deltaX;
                                                                    public final /* synthetic */ int val$deltaY;
                                                                    public final /* synthetic */ RecyclerView.ViewHolder val$holder;
                                                                    public final /* synthetic */ View val$view;

                                                                    public AnonymousClass6(RecyclerView.ViewHolder viewHolder2222222, int i5222222, View view2222222, int i6222222, ViewPropertyAnimator animate2222222) {
                                                                        r2 = viewHolder2222222;
                                                                        r3 = i5222222;
                                                                        r4 = view2222222;
                                                                        r5 = i6222222;
                                                                        r6 = animate2222222;
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationCancel(Animator animator) {
                                                                        if (r3 != 0) {
                                                                            r4.setTranslationX(0.0f);
                                                                        }
                                                                        if (r5 != 0) {
                                                                            r4.setTranslationY(0.0f);
                                                                        }
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationEnd(Animator animator) {
                                                                        r6.setListener(null);
                                                                        DefaultItemAnimator.this.dispatchAnimationFinished(r2);
                                                                        DefaultItemAnimator.this.mMoveAnimations.remove(r2);
                                                                        DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                                                    }

                                                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                    public final void onAnimationStart(Animator animator) {
                                                                        DefaultItemAnimator.this.getClass();
                                                                    }
                                                                }).start();
                                                            }
                                                            r2.clear();
                                                            r1.mMovesList.remove(r2);
                                                            break;
                                                        case 1:
                                                            Iterator it3 = r2.iterator();
                                                            while (it3.hasNext()) {
                                                                ChangeInfo changeInfo22222 = (ChangeInfo) it3.next();
                                                                DefaultItemAnimator defaultItemAnimator322222 = r1;
                                                                defaultItemAnimator322222.getClass();
                                                                RecyclerView.ViewHolder viewHolder3 = changeInfo22222.oldHolder;
                                                                View view322222 = viewHolder3 == null ? null : viewHolder3.itemView;
                                                                RecyclerView.ViewHolder viewHolder4 = changeInfo22222.newHolder;
                                                                View view422222 = viewHolder4 != null ? viewHolder4.itemView : null;
                                                                long j2 = defaultItemAnimator322222.mChangeDuration;
                                                                if (view322222 != null) {
                                                                    ViewPropertyAnimator duration22222 = view322222.animate().setDuration(j2);
                                                                    defaultItemAnimator322222.mChangeAnimations.add(changeInfo22222.oldHolder);
                                                                    duration22222.translationX(changeInfo22222.toX - changeInfo22222.fromX);
                                                                    duration22222.translationY(changeInfo22222.toY - changeInfo22222.fromY);
                                                                    duration22222.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator3222222, ChangeInfo changeInfo222222, ViewPropertyAnimator duration222222, View view3222222, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator3222222;
                                                                            r2 = changeInfo222222;
                                                                            r3 = duration222222;
                                                                            r4 = view3222222;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                                if (view422222 != null) {
                                                                    ViewPropertyAnimator animate322222 = view422222.animate();
                                                                    defaultItemAnimator3222222.mChangeAnimations.add(changeInfo222222.newHolder);
                                                                    animate322222.translationX(0.0f).translationY(0.0f).setDuration(j2).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                                                        public final /* synthetic */ int $r8$classId;
                                                                        public final /* synthetic */ DefaultItemAnimator this$0;
                                                                        public final /* synthetic */ ChangeInfo val$changeInfo;
                                                                        public final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
                                                                        public final /* synthetic */ View val$view;

                                                                        public /* synthetic */ AnonymousClass7(DefaultItemAnimator defaultItemAnimator3222222, ChangeInfo changeInfo222222, ViewPropertyAnimator animate3222222, View view4222222, int i7) {
                                                                            r5 = i7;
                                                                            r1 = defaultItemAnimator3222222;
                                                                            r2 = changeInfo222222;
                                                                            r3 = animate3222222;
                                                                            r4 = view4222222;
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationEnd(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.oldHolder);
                                                                                    r1.mChangeAnimations.remove(r2.oldHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                                default:
                                                                                    r3.setListener(null);
                                                                                    r4.setAlpha(1.0f);
                                                                                    r4.setTranslationX(0.0f);
                                                                                    r4.setTranslationY(0.0f);
                                                                                    r1.dispatchAnimationFinished(r2.newHolder);
                                                                                    r1.mChangeAnimations.remove(r2.newHolder);
                                                                                    r1.dispatchFinishedWhenDone();
                                                                                    break;
                                                                            }
                                                                        }

                                                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                                                        public final void onAnimationStart(Animator animator) {
                                                                            switch (r5) {
                                                                                case 0:
                                                                                    DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder5 = r2.oldHolder;
                                                                                    defaultItemAnimator4.getClass();
                                                                                    break;
                                                                                default:
                                                                                    DefaultItemAnimator defaultItemAnimator5 = r1;
                                                                                    RecyclerView.ViewHolder viewHolder6 = r2.newHolder;
                                                                                    defaultItemAnimator5.getClass();
                                                                                    break;
                                                                            }
                                                                        }
                                                                    }).start();
                                                                }
                                                            }
                                                            r2.clear();
                                                            r1.mChangesList.remove(r2);
                                                            break;
                                                        default:
                                                            Iterator it4 = r2.iterator();
                                                            while (it4.hasNext()) {
                                                                RecyclerView.ViewHolder viewHolder5 = (RecyclerView.ViewHolder) it4.next();
                                                                DefaultItemAnimator defaultItemAnimator4 = r1;
                                                                defaultItemAnimator4.getClass();
                                                                View view5 = viewHolder5.itemView;
                                                                ViewPropertyAnimator animate4 = view5.animate();
                                                                defaultItemAnimator4.mAddAnimations.add(viewHolder5);
                                                                animate4.alpha(1.0f).setDuration(defaultItemAnimator4.mAddDuration).setListener(defaultItemAnimator4.new AnonymousClass4(viewHolder5, view5, animate4)).start();
                                                            }
                                                            r2.clear();
                                                            r1.mAdditionsList.remove(r2);
                                                            break;
                                                    }
                                                }
                                            };
                                            if (isEmpty && isEmpty2 && isEmpty3) {
                                                anonymousClass13.run();
                                            } else {
                                                if (isEmpty) {
                                                    j = 0;
                                                }
                                                long max = Math.max(!isEmpty2 ? defaultItemAnimator22.mMoveDuration : 0L, isEmpty3 ? 0L : defaultItemAnimator22.mChangeDuration) + j;
                                                View view4 = ((ViewHolder) arrayList322.get(0)).itemView;
                                                WeakHashMap weakHashMap3 = ViewCompat.sViewPropertyAnimatorMap;
                                                view4.postOnAnimationDelayed(anonymousClass13, max);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        this.this$0.mPostedAnimatorRunner = false;
                        break;
                }
            }
        };
        this.mLastAutoMeasureNonExactMeasuredWidth = 0;
        this.mLastAutoMeasureNonExactMeasuredHeight = 0;
        this.mViewInfoProcessCallback = new AnonymousClass5();
        this.mDifferentialMotionFlingController = new DifferentialMotionFlingController(getContext(), new AnonymousClass5());
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mScaledHorizontalScrollFactor = viewConfiguration.getScaledHorizontalScrollFactor();
        this.mScaledVerticalScrollFactor = viewConfiguration.getScaledVerticalScrollFactor();
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mPhysicalCoef = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.mListener = anonymousClass5;
        this.mAdapterHelper = new AdapterHelper(new AnonymousClass5());
        this.mChildHelper = new ChildHelper(new AnonymousClass5());
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (ViewCompat.Api26Impl.getImportantForAutofill(this) == 0) {
            ViewCompat.Api26Impl.setImportantForAutofill(this, 8);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        RecyclerViewAccessibilityDelegate recyclerViewAccessibilityDelegate = new RecyclerViewAccessibilityDelegate(this);
        this.mAccessibilityDelegate = recyclerViewAccessibilityDelegate;
        ViewCompat.setAccessibilityDelegate(this, recyclerViewAccessibilityDelegate);
        int[] iArr = R$styleable.RecyclerView;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        ViewCompat.Api29Impl.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes, i, 0);
        String string = obtainStyledAttributes.getString(8);
        if (obtainStyledAttributes.getInt(2, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.mClipToPadding = obtainStyledAttributes.getBoolean(1, true);
        if (obtainStyledAttributes.getBoolean(3, false)) {
            initFastScroller((StateListDrawable) obtainStyledAttributes.getDrawable(6), obtainStyledAttributes.getDrawable(7), (StateListDrawable) obtainStyledAttributes.getDrawable(4), obtainStyledAttributes.getDrawable(5));
        }
        obtainStyledAttributes.recycle();
        this.mLowResRotaryEncoderFeature = context.getPackageManager().hasSystemFeature("android.hardware.rotaryencoder.lowres");
        if (string != null) {
            String trim = string.trim();
            if (!trim.isEmpty()) {
                if (trim.charAt(0) == '.') {
                    trim = context.getPackageName() + trim;
                } else if (!trim.contains(".")) {
                    trim = RecyclerView.class.getPackage().getName() + '.' + trim;
                }
                String str = trim;
                try {
                    if (isInEditMode()) {
                        classLoader = getClass().getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class<? extends U> asSubclass = Class.forName(str, false, classLoader).asSubclass(LayoutManager.class);
                    try {
                        constructor = asSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i), 0};
                    } catch (NoSuchMethodException e) {
                        try {
                            Class[] clsArr = new Class[0];
                            objArr = null;
                            constructor = asSubclass.getConstructor(null);
                        } catch (NoSuchMethodException e2) {
                            e2.initCause(e);
                            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + str, e2);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((LayoutManager) constructor.newInstance(objArr));
                } catch (ClassCastException e3) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + str, e3);
                } catch (ClassNotFoundException e4) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + str, e4);
                } catch (IllegalAccessException e5) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + str, e5);
                } catch (InstantiationException e6) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e6);
                } catch (InvocationTargetException e7) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + str, e7);
                }
            }
        }
        int[] iArr2 = NESTED_SCROLLING_ATTRS;
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i, 0);
        ViewCompat.Api29Impl.saveAttributeDataForStyleable(this, context, iArr2, attributeSet, obtainStyledAttributes2, i, 0);
        boolean z = obtainStyledAttributes2.getBoolean(0, true);
        obtainStyledAttributes2.recycle();
        setNestedScrollingEnabled(z);
        setTag(com.android.wm.shell.R.id.is_pooling_container_tag, Boolean.TRUE);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class LayoutParams extends ViewGroup.MarginLayoutParams {
        public final Rect mDecorInsets;
        public boolean mInsetsDirty;
        public boolean mPendingInvalidate;
        public ViewHolder mViewHolder;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.mDecorInsets = new Rect();
            this.mInsetsDirty = true;
            this.mPendingInvalidate = false;
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutManager layoutManager = this.mLayout;
        if (layoutManager != null) {
            return layoutManager.generateLayoutParams(layoutParams);
        }
        throw new IllegalStateException("RecyclerView has no LayoutManager" + exceptionLabel());
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Adapter {
        public final AdapterDataObservable mObservable = new AdapterDataObservable();
        public boolean mHasStableIds = false;
        public final StateRestorationPolicy mStateRestorationPolicy = StateRestorationPolicy.ALLOW;

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class StateRestorationPolicy {
            public static final /* synthetic */ StateRestorationPolicy[] $VALUES;
            public static final StateRestorationPolicy ALLOW;

            static {
                StateRestorationPolicy stateRestorationPolicy = new StateRestorationPolicy("ALLOW", 0);
                ALLOW = stateRestorationPolicy;
                $VALUES = new StateRestorationPolicy[]{stateRestorationPolicy, new StateRestorationPolicy("PREVENT_WHEN_EMPTY", 1), new StateRestorationPolicy("PREVENT", 2)};
            }

            public static StateRestorationPolicy valueOf(String str) {
                return (StateRestorationPolicy) Enum.valueOf(StateRestorationPolicy.class, str);
            }

            public static StateRestorationPolicy[] values() {
                return (StateRestorationPolicy[]) $VALUES.clone();
            }
        }

        public abstract int getItemCount();

        public long getItemId(int i) {
            return -1L;
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public final void notifyDataSetChanged() {
            this.mObservable.notifyChanged();
        }

        public final void notifyItemChanged(int i) {
            this.mObservable.notifyItemRangeChanged(i, 1, null);
        }

        public abstract void onBindViewHolder(ViewHolder viewHolder, int i);

        public void onBindViewHolder(ViewHolder viewHolder, int i, List list) {
            onBindViewHolder(viewHolder, i);
        }

        public abstract ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup);

        public boolean onFailedToRecycleView(ViewHolder viewHolder) {
            return false;
        }

        public final void setHasStableIds(boolean z) {
            if (this.mObservable.hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
            }
            this.mHasStableIds = z;
        }

        public void onDetachedFromRecyclerView() {
        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        }

        public void onViewRecycled(ViewHolder viewHolder) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class OnScrollListener {
        public abstract void onScrolled(RecyclerView recyclerView, int i, int i2);

        public void onScrollStateChanged(int i) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class ItemDecoration {
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
            ((LayoutParams) view.getLayoutParams()).mViewHolder.getLayoutPosition();
            rect.set(0, 0, 0, 0);
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView) {
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
        }
    }
}
