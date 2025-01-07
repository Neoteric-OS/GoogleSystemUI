package androidx.recyclerview.widget;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PagerSnapHelper {
    public OrientationHelper$1 mHorizontalHelper;
    public RecyclerView mRecyclerView;
    public final SnapHelper$1 mScrollListener = new RecyclerView.OnScrollListener() { // from class: androidx.recyclerview.widget.SnapHelper$1
        public boolean mScrolled = false;

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public final void onScrollStateChanged(int i) {
            if (i == 0 && this.mScrolled) {
                this.mScrolled = false;
                PagerSnapHelper.this.snapToTargetExistingView();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public final void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (i == 0 && i2 == 0) {
                return;
            }
            this.mScrolled = true;
        }
    };
    public OrientationHelper$1 mVerticalHelper;

    public static int distanceToCenter(View view, OrientationHelper$1 orientationHelper$1) {
        return ((orientationHelper$1.getDecoratedMeasurement(view) / 2) + orientationHelper$1.getDecoratedStart(view)) - ((orientationHelper$1.getTotalSpace() / 2) + orientationHelper$1.getStartAfterPadding());
    }

    public static View findCenterView(RecyclerView.LayoutManager layoutManager, OrientationHelper$1 orientationHelper$1) {
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int totalSpace = (orientationHelper$1.getTotalSpace() / 2) + orientationHelper$1.getStartAfterPadding();
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = layoutManager.getChildAt(i2);
            int abs = Math.abs(((orientationHelper$1.getDecoratedMeasurement(childAt) / 2) + orientationHelper$1.getDecoratedStart(childAt)) - totalSpace);
            if (abs < i) {
                view = childAt;
                i = abs;
            }
        }
        return view;
    }

    public final void attachToRecyclerView(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == recyclerView) {
            return;
        }
        SnapHelper$1 snapHelper$1 = this.mScrollListener;
        if (recyclerView2 != null) {
            List list = recyclerView2.mScrollListeners;
            if (list != null) {
                list.remove(snapHelper$1);
            }
            this.mRecyclerView.mOnFlingListener = null;
        }
        this.mRecyclerView = recyclerView;
        if (recyclerView != null) {
            if (recyclerView.mOnFlingListener != null) {
                throw new IllegalStateException("An instance of OnFlingListener already set.");
            }
            recyclerView.addOnScrollListener(snapHelper$1);
            this.mRecyclerView.mOnFlingListener = this;
            new Scroller(this.mRecyclerView.getContext(), new DecelerateInterpolator());
            snapToTargetExistingView();
        }
    }

    public final int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = distanceToCenter(view, getHorizontalHelper(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = distanceToCenter(view, getVerticalHelper(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    public LinearSmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof RecyclerView$SmoothScroller$ScrollVectorProvider) {
            return new LinearSmoothScroller(this.mRecyclerView.getContext()) { // from class: androidx.recyclerview.widget.PagerSnapHelper.1
                @Override // androidx.recyclerview.widget.LinearSmoothScroller
                public final float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    return 100.0f / displayMetrics.densityDpi;
                }

                @Override // androidx.recyclerview.widget.LinearSmoothScroller
                public final int calculateTimeForScrolling(int i) {
                    return Math.min(100, super.calculateTimeForScrolling(i));
                }

                @Override // androidx.recyclerview.widget.LinearSmoothScroller
                public final void onTargetFound(View view, RecyclerView$SmoothScroller$Action recyclerView$SmoothScroller$Action) {
                    PagerSnapHelper pagerSnapHelper = PagerSnapHelper.this;
                    int[] calculateDistanceToFinalSnap = pagerSnapHelper.calculateDistanceToFinalSnap(pagerSnapHelper.mRecyclerView.mLayout, view);
                    int i = calculateDistanceToFinalSnap[0];
                    int i2 = calculateDistanceToFinalSnap[1];
                    int calculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i), Math.abs(i2)));
                    if (calculateTimeForDeceleration > 0) {
                        recyclerView$SmoothScroller$Action.update(i, i2, calculateTimeForDeceleration, this.mDecelerateInterpolator);
                    }
                }
            };
        }
        return null;
    }

    public abstract View findSnapView(RecyclerView.LayoutManager layoutManager);

    public final OrientationHelper$1 getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper$1 orientationHelper$1 = this.mHorizontalHelper;
        if (orientationHelper$1 == null || orientationHelper$1.mLayoutManager != layoutManager) {
            this.mHorizontalHelper = new OrientationHelper$1(layoutManager, 0);
        }
        return this.mHorizontalHelper;
    }

    public final OrientationHelper$1 getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper$1 orientationHelper$1 = this.mVerticalHelper;
        if (orientationHelper$1 == null || orientationHelper$1.mLayoutManager != layoutManager) {
            this.mVerticalHelper = new OrientationHelper$1(layoutManager, 1);
        }
        return this.mVerticalHelper;
    }

    public final void snapToTargetExistingView() {
        RecyclerView.LayoutManager layoutManager;
        View findSnapView;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (layoutManager = recyclerView.mLayout) == null || (findSnapView = findSnapView(layoutManager)) == null) {
            return;
        }
        int[] calculateDistanceToFinalSnap = calculateDistanceToFinalSnap(layoutManager, findSnapView);
        int i = calculateDistanceToFinalSnap[0];
        if (i == 0 && calculateDistanceToFinalSnap[1] == 0) {
            return;
        }
        this.mRecyclerView.smoothScrollBy$1(i, calculateDistanceToFinalSnap[1], false);
    }
}
