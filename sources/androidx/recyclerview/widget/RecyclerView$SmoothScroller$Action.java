package androidx.recyclerview.widget;

import android.util.Log;
import android.view.animation.Interpolator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RecyclerView$SmoothScroller$Action {
    public boolean mChanged;
    public int mConsecutiveUpdates;
    public int mDuration;
    public int mDx;
    public int mDy;
    public Interpolator mInterpolator;
    public int mJumpToPosition;

    public final void runIfNecessary(RecyclerView recyclerView) {
        int i = this.mJumpToPosition;
        if (i >= 0) {
            this.mJumpToPosition = -1;
            recyclerView.jumpToPositionForSmoothScroller(i);
            this.mChanged = false;
            return;
        }
        if (!this.mChanged) {
            this.mConsecutiveUpdates = 0;
            return;
        }
        Interpolator interpolator = this.mInterpolator;
        if (interpolator != null && this.mDuration < 1) {
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        }
        int i2 = this.mDuration;
        if (i2 < 1) {
            throw new IllegalStateException("Scroll duration must be a positive number");
        }
        recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, i2, interpolator);
        int i3 = this.mConsecutiveUpdates + 1;
        this.mConsecutiveUpdates = i3;
        if (i3 > 10) {
            Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
        }
        this.mChanged = false;
    }

    public final void update(int i, int i2, int i3, Interpolator interpolator) {
        this.mDx = i;
        this.mDy = i2;
        this.mDuration = i3;
        this.mInterpolator = interpolator;
        this.mChanged = true;
    }
}
