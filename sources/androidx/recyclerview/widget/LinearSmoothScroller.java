package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class LinearSmoothScroller {
    public final DecelerateInterpolator mDecelerateInterpolator;
    public final DisplayMetrics mDisplayMetrics;
    public boolean mHasCalculatedMillisPerPixel;
    public int mInterimTargetDx;
    public int mInterimTargetDy;
    public RecyclerView.LayoutManager mLayoutManager;
    public final LinearInterpolator mLinearInterpolator;
    public float mMillisPerPixel;
    public boolean mPendingInitialRun;
    public RecyclerView mRecyclerView;
    public final RecyclerView$SmoothScroller$Action mRecyclingAction;
    public boolean mRunning;
    public boolean mStarted;
    public int mTargetPosition = -1;
    public PointF mTargetVector;
    public View mTargetView;

    public LinearSmoothScroller(Context context) {
        RecyclerView$SmoothScroller$Action recyclerView$SmoothScroller$Action = new RecyclerView$SmoothScroller$Action();
        recyclerView$SmoothScroller$Action.mJumpToPosition = -1;
        recyclerView$SmoothScroller$Action.mChanged = false;
        recyclerView$SmoothScroller$Action.mConsecutiveUpdates = 0;
        recyclerView$SmoothScroller$Action.mDx = 0;
        recyclerView$SmoothScroller$Action.mDy = 0;
        recyclerView$SmoothScroller$Action.mDuration = Integer.MIN_VALUE;
        recyclerView$SmoothScroller$Action.mInterpolator = null;
        this.mRecyclingAction = recyclerView$SmoothScroller$Action;
        this.mLinearInterpolator = new LinearInterpolator();
        this.mDecelerateInterpolator = new DecelerateInterpolator();
        this.mHasCalculatedMillisPerPixel = false;
        this.mInterimTargetDx = 0;
        this.mInterimTargetDy = 0;
        this.mDisplayMetrics = context.getResources().getDisplayMetrics();
    }

    public static int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
        if (i5 == -1) {
            return i3 - i;
        }
        if (i5 != 0) {
            if (i5 == 1) {
                return i4 - i2;
            }
            throw new IllegalArgumentException("snap preference should be one of the constants defined in SmoothScroller, starting with SNAP_");
        }
        int i6 = i3 - i;
        if (i6 > 0) {
            return i6;
        }
        int i7 = i4 - i2;
        if (i7 < 0) {
            return i7;
        }
        return 0;
    }

    public int calculateDxToMakeVisible(View view, int i) {
        RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
        if (layoutManager == null || !layoutManager.canScrollHorizontally()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return calculateDtToFit(layoutManager.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, layoutManager.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, layoutManager.getPaddingLeft(), layoutManager.mWidth - layoutManager.getPaddingRight(), i);
    }

    public int calculateDyToMakeVisible(View view, int i) {
        RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
        if (layoutManager == null || !layoutManager.canScrollVertically()) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return calculateDtToFit(layoutManager.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, layoutManager.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin, layoutManager.getPaddingTop(), layoutManager.mHeight - layoutManager.getPaddingBottom(), i);
    }

    public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 25.0f / displayMetrics.densityDpi;
    }

    public final int calculateTimeForDeceleration(int i) {
        return (int) Math.ceil(calculateTimeForScrolling(i) / 0.3356d);
    }

    public int calculateTimeForScrolling(int i) {
        float abs = Math.abs(i);
        if (!this.mHasCalculatedMillisPerPixel) {
            this.mMillisPerPixel = calculateSpeedPerPixel(this.mDisplayMetrics);
            this.mHasCalculatedMillisPerPixel = true;
        }
        return (int) Math.ceil(abs * this.mMillisPerPixel);
    }

    public PointF computeScrollVectorForPosition(int i) {
        Object obj = this.mLayoutManager;
        if (obj instanceof RecyclerView$SmoothScroller$ScrollVectorProvider) {
            return ((RecyclerView$SmoothScroller$ScrollVectorProvider) obj).computeScrollVectorForPosition(i);
        }
        Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + RecyclerView$SmoothScroller$ScrollVectorProvider.class.getCanonicalName());
        return null;
    }

    public final void onAnimation(int i, int i2) {
        PointF computeScrollVectorForPosition;
        RecyclerView recyclerView = this.mRecyclerView;
        if (this.mTargetPosition == -1 || recyclerView == null) {
            stop();
        }
        if (this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null && (computeScrollVectorForPosition = computeScrollVectorForPosition(this.mTargetPosition)) != null) {
            float f = computeScrollVectorForPosition.x;
            if (f != 0.0f || computeScrollVectorForPosition.y != 0.0f) {
                recyclerView.scrollStep((int) Math.signum(f), (int) Math.signum(computeScrollVectorForPosition.y), null);
            }
        }
        this.mPendingInitialRun = false;
        View view = this.mTargetView;
        RecyclerView$SmoothScroller$Action recyclerView$SmoothScroller$Action = this.mRecyclingAction;
        if (view != null) {
            this.mRecyclerView.getClass();
            RecyclerView.ViewHolder childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if ((childViewHolderInt != null ? childViewHolderInt.getLayoutPosition() : -1) == this.mTargetPosition) {
                View view2 = this.mTargetView;
                RecyclerView.State state = recyclerView.mState;
                onTargetFound(view2, recyclerView$SmoothScroller$Action);
                recyclerView$SmoothScroller$Action.runIfNecessary(recyclerView);
                stop();
            } else {
                Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
                this.mTargetView = null;
            }
        }
        if (this.mRunning) {
            RecyclerView.State state2 = recyclerView.mState;
            if (this.mRecyclerView.mLayout.getChildCount() == 0) {
                stop();
            } else {
                int i3 = this.mInterimTargetDx;
                int i4 = i3 - i;
                if (i3 * i4 <= 0) {
                    i4 = 0;
                }
                this.mInterimTargetDx = i4;
                int i5 = this.mInterimTargetDy;
                int i6 = i5 - i2;
                if (i5 * i6 <= 0) {
                    i6 = 0;
                }
                this.mInterimTargetDy = i6;
                if (i4 == 0 && i6 == 0) {
                    PointF computeScrollVectorForPosition2 = computeScrollVectorForPosition(this.mTargetPosition);
                    if (computeScrollVectorForPosition2 != null) {
                        if (computeScrollVectorForPosition2.x != 0.0f || computeScrollVectorForPosition2.y != 0.0f) {
                            float f2 = computeScrollVectorForPosition2.y;
                            float sqrt = (float) Math.sqrt((f2 * f2) + (r10 * r10));
                            float f3 = computeScrollVectorForPosition2.x / sqrt;
                            computeScrollVectorForPosition2.x = f3;
                            float f4 = computeScrollVectorForPosition2.y / sqrt;
                            computeScrollVectorForPosition2.y = f4;
                            this.mTargetVector = computeScrollVectorForPosition2;
                            this.mInterimTargetDx = (int) (f3 * 10000.0f);
                            this.mInterimTargetDy = (int) (f4 * 10000.0f);
                            recyclerView$SmoothScroller$Action.update((int) (this.mInterimTargetDx * 1.2f), (int) (this.mInterimTargetDy * 1.2f), (int) (calculateTimeForScrolling(10000) * 1.2f), this.mLinearInterpolator);
                        }
                    }
                    recyclerView$SmoothScroller$Action.mJumpToPosition = this.mTargetPosition;
                    stop();
                }
            }
            boolean z = recyclerView$SmoothScroller$Action.mJumpToPosition >= 0;
            recyclerView$SmoothScroller$Action.runIfNecessary(recyclerView);
            if (z && this.mRunning) {
                this.mPendingInitialRun = true;
                recyclerView.mViewFlinger.postOnAnimation();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onTargetFound(android.view.View r7, androidx.recyclerview.widget.RecyclerView$SmoothScroller$Action r8) {
        /*
            r6 = this;
            android.graphics.PointF r0 = r6.mTargetVector
            r1 = 0
            r2 = -1
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L15
            float r0 = r0.x
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 != 0) goto Lf
            goto L15
        Lf:
            if (r0 <= 0) goto L13
            r0 = r3
            goto L16
        L13:
            r0 = r2
            goto L16
        L15:
            r0 = r1
        L16:
            int r0 = r6.calculateDxToMakeVisible(r7, r0)
            android.graphics.PointF r5 = r6.mTargetVector
            if (r5 == 0) goto L2a
            float r5 = r5.y
            int r4 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r4 != 0) goto L25
            goto L2a
        L25:
            if (r4 <= 0) goto L29
            r1 = r3
            goto L2a
        L29:
            r1 = r2
        L2a:
            int r7 = r6.calculateDyToMakeVisible(r7, r1)
            int r1 = r0 * r0
            int r2 = r7 * r7
            int r2 = r2 + r1
            double r1 = (double) r2
            double r1 = java.lang.Math.sqrt(r1)
            int r1 = (int) r1
            int r1 = r6.calculateTimeForDeceleration(r1)
            if (r1 <= 0) goto L46
            int r0 = -r0
            int r7 = -r7
            android.view.animation.DecelerateInterpolator r6 = r6.mDecelerateInterpolator
            r8.update(r0, r7, r1, r6)
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearSmoothScroller.onTargetFound(android.view.View, androidx.recyclerview.widget.RecyclerView$SmoothScroller$Action):void");
    }

    public final void stop() {
        if (this.mRunning) {
            this.mRunning = false;
            this.mInterimTargetDy = 0;
            this.mInterimTargetDx = 0;
            this.mTargetVector = null;
            this.mRecyclerView.mState.mTargetPosition = -1;
            this.mTargetView = null;
            this.mTargetPosition = -1;
            this.mPendingInitialRun = false;
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            if (layoutManager.mSmoothScroller == this) {
                layoutManager.mSmoothScroller = null;
            }
            this.mLayoutManager = null;
            this.mRecyclerView = null;
        }
    }
}
