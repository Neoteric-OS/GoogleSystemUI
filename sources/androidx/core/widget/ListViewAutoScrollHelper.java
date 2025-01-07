package androidx.core.widget;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.appcompat.widget.DropDownListView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ListViewAutoScrollHelper implements View.OnTouchListener {
    public static final int DEFAULT_ACTIVATION_DELAY = ViewConfiguration.getTapTimeout();
    public final int mActivationDelay;
    public boolean mAlreadyDelayed;
    public boolean mAnimating;
    public final Interpolator mEdgeInterpolator;
    public final int mEdgeType;
    public boolean mEnabled;
    public final float[] mMaximumEdges;
    public final float[] mMaximumVelocity;
    public final float[] mMinimumVelocity;
    public boolean mNeedsCancel;
    public boolean mNeedsReset;
    public final float[] mRelativeEdges;
    public final float[] mRelativeVelocity;
    public AutoScrollHelper$ScrollAnimationRunnable mRunnable;
    public final AutoScrollHelper$ClampedScroller mScroller;
    public final DropDownListView mTarget;
    public final View mTarget$1;

    public ListViewAutoScrollHelper(DropDownListView dropDownListView) {
        AutoScrollHelper$ClampedScroller autoScrollHelper$ClampedScroller = new AutoScrollHelper$ClampedScroller();
        autoScrollHelper$ClampedScroller.mStartTime = Long.MIN_VALUE;
        autoScrollHelper$ClampedScroller.mStopTime = -1L;
        autoScrollHelper$ClampedScroller.mDeltaTime = 0L;
        this.mScroller = autoScrollHelper$ClampedScroller;
        this.mEdgeInterpolator = new AccelerateInterpolator();
        float[] fArr = {0.0f, 0.0f};
        this.mRelativeEdges = fArr;
        float[] fArr2 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.mMaximumEdges = fArr2;
        float[] fArr3 = {0.0f, 0.0f};
        this.mRelativeVelocity = fArr3;
        float[] fArr4 = {0.0f, 0.0f};
        this.mMinimumVelocity = fArr4;
        float[] fArr5 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.mMaximumVelocity = fArr5;
        this.mTarget$1 = dropDownListView;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float f2 = ((int) ((1575.0f * f) + 0.5f)) / 1000.0f;
        fArr5[0] = f2;
        fArr5[1] = f2;
        float f3 = ((int) ((f * 315.0f) + 0.5f)) / 1000.0f;
        fArr4[0] = f3;
        fArr4[1] = f3;
        this.mEdgeType = 1;
        fArr2[0] = Float.MAX_VALUE;
        fArr2[1] = Float.MAX_VALUE;
        fArr[0] = 0.2f;
        fArr[1] = 0.2f;
        fArr3[0] = 0.001f;
        fArr3[1] = 0.001f;
        this.mActivationDelay = DEFAULT_ACTIVATION_DELAY;
        autoScrollHelper$ClampedScroller.mRampUpDuration = 500;
        autoScrollHelper$ClampedScroller.mRampDownDuration = 500;
        this.mTarget = dropDownListView;
    }

    public static float constrain(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0041 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float computeTargetVelocity(int r4, float r5, float r6, float r7) {
        /*
            r3 = this;
            float[] r0 = r3.mRelativeEdges
            r0 = r0[r4]
            float[] r1 = r3.mMaximumEdges
            r1 = r1[r4]
            float r0 = r0 * r6
            r2 = 0
            float r0 = constrain(r0, r2, r1)
            float r1 = r3.constrainEdgeValue(r5, r0)
            float r6 = r6 - r5
            float r5 = r3.constrainEdgeValue(r6, r0)
            float r5 = r5 - r1
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 >= 0) goto L27
            android.view.animation.Interpolator r6 = r3.mEdgeInterpolator
            float r5 = -r5
            android.view.animation.AccelerateInterpolator r6 = (android.view.animation.AccelerateInterpolator) r6
            float r5 = r6.getInterpolation(r5)
            float r5 = -r5
            goto L33
        L27:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 <= 0) goto L3c
            android.view.animation.Interpolator r6 = r3.mEdgeInterpolator
            android.view.animation.AccelerateInterpolator r6 = (android.view.animation.AccelerateInterpolator) r6
            float r5 = r6.getInterpolation(r5)
        L33:
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            float r5 = constrain(r5, r6, r0)
            goto L3d
        L3c:
            r5 = r2
        L3d:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 != 0) goto L42
            return r2
        L42:
            float[] r0 = r3.mRelativeVelocity
            r0 = r0[r4]
            float[] r1 = r3.mMinimumVelocity
            r1 = r1[r4]
            float[] r3 = r3.mMaximumVelocity
            r3 = r3[r4]
            float r0 = r0 * r7
            if (r6 <= 0) goto L57
            float r5 = r5 * r0
            float r3 = constrain(r5, r1, r3)
            return r3
        L57:
            float r4 = -r5
            float r4 = r4 * r0
            float r3 = constrain(r4, r1, r3)
            float r3 = -r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.ListViewAutoScrollHelper.computeTargetVelocity(int, float, float, float):float");
    }

    public final float constrainEdgeValue(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        int i = this.mEdgeType;
        if (i == 0 || i == 1) {
            if (f < f2) {
                if (f >= 0.0f) {
                    return 1.0f - (f / f2);
                }
                if (this.mAnimating && i == 1) {
                    return 1.0f;
                }
            }
        } else if (i == 2 && f < 0.0f) {
            return f / (-f2);
        }
        return 0.0f;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
    
        if (r0 != 3) goto L29;
     */
    /* JADX WARN: Type inference failed for: r6v11, types: [androidx.core.widget.AutoScrollHelper$ScrollAnimationRunnable] */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.mEnabled
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L1e
            r6 = 3
            if (r0 == r6) goto L16
            goto L7f
        L16:
            r5.requestStop()
            goto L7f
        L1a:
            r5.mNeedsCancel = r2
            r5.mAlreadyDelayed = r1
        L1e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.mTarget$1
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.computeTargetVelocity(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.mTarget$1
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.computeTargetVelocity(r2, r7, r6, r3)
            androidx.core.widget.AutoScrollHelper$ClampedScroller r7 = r5.mScroller
            r7.mTargetVelocityX = r0
            r7.mTargetVelocityY = r6
            boolean r6 = r5.mAnimating
            if (r6 != 0) goto L7f
            boolean r6 = r5.shouldAnimate()
            if (r6 == 0) goto L7f
            androidx.core.widget.AutoScrollHelper$ScrollAnimationRunnable r6 = r5.mRunnable
            if (r6 != 0) goto L61
            androidx.core.widget.AutoScrollHelper$ScrollAnimationRunnable r6 = new androidx.core.widget.AutoScrollHelper$ScrollAnimationRunnable
            r6.<init>()
            r5.mRunnable = r6
        L61:
            r5.mAnimating = r2
            r5.mNeedsReset = r2
            boolean r6 = r5.mAlreadyDelayed
            if (r6 != 0) goto L78
            int r6 = r5.mActivationDelay
            if (r6 <= 0) goto L78
            android.view.View r7 = r5.mTarget$1
            androidx.core.widget.AutoScrollHelper$ScrollAnimationRunnable r0 = r5.mRunnable
            long r3 = (long) r6
            java.util.WeakHashMap r6 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            r7.postOnAnimationDelayed(r0, r3)
            goto L7d
        L78:
            androidx.core.widget.AutoScrollHelper$ScrollAnimationRunnable r6 = r5.mRunnable
            r6.run()
        L7d:
            r5.mAlreadyDelayed = r2
        L7f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.ListViewAutoScrollHelper.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public final void requestStop() {
        int i = 0;
        if (this.mNeedsReset) {
            this.mAnimating = false;
            return;
        }
        AutoScrollHelper$ClampedScroller autoScrollHelper$ClampedScroller = this.mScroller;
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i2 = (int) (currentAnimationTimeMillis - autoScrollHelper$ClampedScroller.mStartTime);
        int i3 = autoScrollHelper$ClampedScroller.mRampDownDuration;
        if (i2 > i3) {
            i = i3;
        } else if (i2 >= 0) {
            i = i2;
        }
        autoScrollHelper$ClampedScroller.mEffectiveRampDown = i;
        autoScrollHelper$ClampedScroller.mStopValue = autoScrollHelper$ClampedScroller.getValueAt(currentAnimationTimeMillis);
        autoScrollHelper$ClampedScroller.mStopTime = currentAnimationTimeMillis;
    }

    public final boolean shouldAnimate() {
        DropDownListView dropDownListView;
        int count;
        AutoScrollHelper$ClampedScroller autoScrollHelper$ClampedScroller = this.mScroller;
        float f = autoScrollHelper$ClampedScroller.mTargetVelocityY;
        int abs = (int) (f / Math.abs(f));
        Math.abs(autoScrollHelper$ClampedScroller.mTargetVelocityX);
        if (abs == 0 || (count = (dropDownListView = this.mTarget).getCount()) == 0) {
            return false;
        }
        int childCount = dropDownListView.getChildCount();
        int firstVisiblePosition = dropDownListView.getFirstVisiblePosition();
        int i = firstVisiblePosition + childCount;
        if (abs > 0) {
            if (i >= count && dropDownListView.getChildAt(childCount - 1).getBottom() <= dropDownListView.getHeight()) {
                return false;
            }
        } else {
            if (abs >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && dropDownListView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }
}
