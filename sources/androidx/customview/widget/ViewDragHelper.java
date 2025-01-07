package androidx.customview.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import java.util.Arrays;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewDragHelper {
    public static final AnonymousClass1 sInterpolator = new AnonymousClass1();
    public final Callback mCallback;
    public View mCapturedView;
    public int mDragState;
    public int[] mEdgeDragsInProgress;
    public int[] mEdgeDragsLocked;
    public final int mEdgeSize;
    public int[] mInitialEdgesTouched;
    public float[] mInitialMotionX;
    public float[] mInitialMotionY;
    public float[] mLastMotionX;
    public float[] mLastMotionY;
    public final float mMaxVelocity;
    public final float mMinVelocity;
    public final CoordinatorLayout mParentView;
    public int mPointersDown;
    public boolean mReleaseInProgress;
    public final OverScroller mScroller;
    public final int mTouchSlop;
    public VelocityTracker mVelocityTracker;
    public int mActivePointerId = -1;
    public final AnonymousClass2 mSetIdleRunnable = new Runnable() { // from class: androidx.customview.widget.ViewDragHelper.2
        @Override // java.lang.Runnable
        public final void run() {
            ViewDragHelper.this.setDragState(0);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: androidx.customview.widget.ViewDragHelper$1, reason: invalid class name */
    public final class AnonymousClass1 implements Interpolator {
        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.customview.widget.ViewDragHelper$2] */
    public ViewDragHelper(Context context, CoordinatorLayout coordinatorLayout, Callback callback) {
        if (coordinatorLayout == null) {
            throw new NullPointerException("Parent view may not be null");
        }
        if (callback == null) {
            throw new NullPointerException("Callback may not be null");
        }
        this.mParentView = coordinatorLayout;
        this.mCallback = callback;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mEdgeSize = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMaxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mMinVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mScroller = new OverScroller(context, sInterpolator);
    }

    public final void cancel() {
        this.mActivePointerId = -1;
        float[] fArr = this.mInitialMotionX;
        if (fArr != null) {
            Arrays.fill(fArr, 0.0f);
            Arrays.fill(this.mInitialMotionY, 0.0f);
            Arrays.fill(this.mLastMotionX, 0.0f);
            Arrays.fill(this.mLastMotionY, 0.0f);
            Arrays.fill(this.mInitialEdgesTouched, 0);
            Arrays.fill(this.mEdgeDragsInProgress, 0);
            Arrays.fill(this.mEdgeDragsLocked, 0);
            this.mPointersDown = 0;
        }
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public final void captureChildView(View view, int i) {
        ViewParent parent = view.getParent();
        CoordinatorLayout coordinatorLayout = this.mParentView;
        if (parent != coordinatorLayout) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + coordinatorLayout + ")");
        }
        this.mCapturedView = view;
        this.mActivePointerId = i;
        this.mCallback.onViewCaptured(view, i);
        setDragState(1);
    }

    public final boolean checkTouchSlop(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        Callback callback = this.mCallback;
        boolean z = callback.getViewHorizontalDragRange(view) > 0;
        boolean z2 = callback.getViewVerticalDragRange() > 0;
        if (!z || !z2) {
            return z ? Math.abs(f) > ((float) this.mTouchSlop) : z2 && Math.abs(f2) > ((float) this.mTouchSlop);
        }
        float f3 = (f2 * f2) + (f * f);
        int i = this.mTouchSlop;
        return f3 > ((float) (i * i));
    }

    public final void clearMotionHistory(int i) {
        float[] fArr = this.mInitialMotionX;
        if (fArr != null) {
            int i2 = this.mPointersDown;
            int i3 = 1 << i;
            if ((i2 & i3) != 0) {
                fArr[i] = 0.0f;
                this.mInitialMotionY[i] = 0.0f;
                this.mLastMotionX[i] = 0.0f;
                this.mLastMotionY[i] = 0.0f;
                this.mInitialEdgesTouched[i] = 0;
                this.mEdgeDragsInProgress[i] = 0;
                this.mEdgeDragsLocked[i] = 0;
                this.mPointersDown = (~i3) & i2;
            }
        }
    }

    public final int computeAxisDuration(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        float width = this.mParentView.getWidth() / 2;
        float sin = (((float) Math.sin((Math.min(1.0f, Math.abs(i) / r3) - 0.5f) * 0.47123894f)) * width) + width;
        int abs = Math.abs(i2);
        return Math.min(abs > 0 ? Math.round(Math.abs(sin / abs) * 1000.0f) * 4 : (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f), 600);
    }

    public final boolean continueSettling() {
        if (this.mDragState == 2) {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            int left = currX - this.mCapturedView.getLeft();
            int top = currY - this.mCapturedView.getTop();
            if (left != 0) {
                View view = this.mCapturedView;
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                view.offsetLeftAndRight(left);
            }
            if (top != 0) {
                View view2 = this.mCapturedView;
                WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                view2.offsetTopAndBottom(top);
            }
            if (left != 0 || top != 0) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, currX, currY);
            }
            if (computeScrollOffset && currX == this.mScroller.getFinalX() && currY == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                this.mParentView.post(this.mSetIdleRunnable);
            }
        }
        return this.mDragState == 2;
    }

    public final View findTopChildUnder(int i, int i2) {
        CoordinatorLayout coordinatorLayout = this.mParentView;
        for (int childCount = coordinatorLayout.getChildCount() - 1; childCount >= 0; childCount--) {
            this.mCallback.getClass();
            View childAt = coordinatorLayout.getChildAt(childCount);
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean forceSettleCapturedViewAt(int r10, int r11, int r12, int r13) {
        /*
            r9 = this;
            android.view.View r0 = r9.mCapturedView
            int r2 = r0.getLeft()
            android.view.View r0 = r9.mCapturedView
            int r3 = r0.getTop()
            int r4 = r10 - r2
            int r5 = r11 - r3
            r10 = 0
            if (r4 != 0) goto L1e
            if (r5 != 0) goto L1e
            android.widget.OverScroller r11 = r9.mScroller
            r11.abortAnimation()
            r9.setDragState(r10)
            return r10
        L1e:
            android.view.View r11 = r9.mCapturedView
            float r0 = r9.mMinVelocity
            int r0 = (int) r0
            float r1 = r9.mMaxVelocity
            int r1 = (int) r1
            int r6 = java.lang.Math.abs(r12)
            if (r6 >= r0) goto L2e
            r12 = r10
            goto L35
        L2e:
            if (r6 <= r1) goto L35
            if (r12 <= 0) goto L34
            r12 = r1
            goto L35
        L34:
            int r12 = -r1
        L35:
            int r6 = java.lang.Math.abs(r13)
            if (r6 >= r0) goto L3d
        L3b:
            r13 = r10
            goto L45
        L3d:
            if (r6 <= r1) goto L45
            if (r13 <= 0) goto L43
            r13 = r1
            goto L45
        L43:
            int r10 = -r1
            goto L3b
        L45:
            int r10 = java.lang.Math.abs(r4)
            int r0 = java.lang.Math.abs(r5)
            int r1 = java.lang.Math.abs(r12)
            int r6 = java.lang.Math.abs(r13)
            int r7 = r1 + r6
            int r8 = r10 + r0
            if (r12 == 0) goto L5f
            float r10 = (float) r1
            float r1 = (float) r7
        L5d:
            float r10 = r10 / r1
            goto L62
        L5f:
            float r10 = (float) r10
            float r1 = (float) r8
            goto L5d
        L62:
            if (r13 == 0) goto L68
            float r0 = (float) r6
            float r1 = (float) r7
        L66:
            float r0 = r0 / r1
            goto L6b
        L68:
            float r0 = (float) r0
            float r1 = (float) r8
            goto L66
        L6b:
            androidx.customview.widget.ViewDragHelper$Callback r1 = r9.mCallback
            int r11 = r1.getViewHorizontalDragRange(r11)
            int r11 = r9.computeAxisDuration(r4, r12, r11)
            int r12 = r1.getViewVerticalDragRange()
            int r12 = r9.computeAxisDuration(r5, r13, r12)
            float r11 = (float) r11
            float r11 = r11 * r10
            float r10 = (float) r12
            float r10 = r10 * r0
            float r10 = r10 + r11
            int r6 = (int) r10
            android.widget.OverScroller r1 = r9.mScroller
            r1.startScroll(r2, r3, r4, r5, r6)
            r10 = 2
            r9.setDragState(r10)
            r9 = 1
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ViewDragHelper.forceSettleCapturedViewAt(int, int, int, int):boolean");
    }

    public final void processTouchEvent(MotionEvent motionEvent) {
        int findPointerIndex;
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View findTopChildUnder = findTopChildUnder((int) x, (int) y);
            saveInitialMotion(pointerId, x, y);
            tryCaptureViewForDrag(findTopChildUnder, pointerId);
            int i2 = this.mInitialEdgesTouched[pointerId];
            return;
        }
        if (actionMasked == 1) {
            if (this.mDragState == 1) {
                releaseViewForPointerUp();
            }
            cancel();
            return;
        }
        Callback callback = this.mCallback;
        if (actionMasked != 2) {
            if (actionMasked == 3) {
                if (this.mDragState == 1) {
                    this.mReleaseInProgress = true;
                    callback.onViewReleased(this.mCapturedView, 0.0f, 0.0f);
                    this.mReleaseInProgress = false;
                    if (this.mDragState == 1) {
                        setDragState(0);
                    }
                }
                cancel();
                return;
            }
            if (actionMasked == 5) {
                int pointerId2 = motionEvent.getPointerId(actionIndex);
                float x2 = motionEvent.getX(actionIndex);
                float y2 = motionEvent.getY(actionIndex);
                saveInitialMotion(pointerId2, x2, y2);
                if (this.mDragState == 0) {
                    tryCaptureViewForDrag(findTopChildUnder((int) x2, (int) y2), pointerId2);
                    int i3 = this.mInitialEdgesTouched[pointerId2];
                    return;
                }
                int i4 = (int) x2;
                int i5 = (int) y2;
                View view = this.mCapturedView;
                if ((view != null ? (i4 < view.getLeft() || i4 >= view.getRight() || i5 < view.getTop() || i5 >= view.getBottom()) ? 0 : 1 : 0) != 0) {
                    tryCaptureViewForDrag(this.mCapturedView, pointerId2);
                    return;
                }
                return;
            }
            if (actionMasked != 6) {
                return;
            }
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            if (this.mDragState == 1 && pointerId3 == this.mActivePointerId) {
                int pointerCount = motionEvent.getPointerCount();
                while (true) {
                    if (r3 >= pointerCount) {
                        i = -1;
                        break;
                    }
                    int pointerId4 = motionEvent.getPointerId(r3);
                    if (pointerId4 != this.mActivePointerId) {
                        View findTopChildUnder2 = findTopChildUnder((int) motionEvent.getX(r3), (int) motionEvent.getY(r3));
                        View view2 = this.mCapturedView;
                        if (findTopChildUnder2 == view2 && tryCaptureViewForDrag(view2, pointerId4)) {
                            i = this.mActivePointerId;
                            break;
                        }
                    }
                    r3++;
                }
                if (i == -1) {
                    releaseViewForPointerUp();
                }
            }
            clearMotionHistory(pointerId3);
            return;
        }
        if (this.mDragState == 1) {
            int i6 = this.mActivePointerId;
            if (((this.mPointersDown & (1 << i6)) == 0 ? 0 : 1) == 0 || (findPointerIndex = motionEvent.findPointerIndex(i6)) == -1) {
                return;
            }
            float x3 = motionEvent.getX(findPointerIndex);
            float y3 = motionEvent.getY(findPointerIndex);
            float[] fArr = this.mLastMotionX;
            int i7 = this.mActivePointerId;
            int i8 = (int) (x3 - fArr[i7]);
            int i9 = (int) (y3 - this.mLastMotionY[i7]);
            int left = this.mCapturedView.getLeft() + i8;
            int top = this.mCapturedView.getTop() + i9;
            int left2 = this.mCapturedView.getLeft();
            int top2 = this.mCapturedView.getTop();
            if (i8 != 0) {
                left = callback.clampViewPositionHorizontal(this.mCapturedView, left);
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                this.mCapturedView.offsetLeftAndRight(left - left2);
            }
            if (i9 != 0) {
                top = callback.clampViewPositionVertical(this.mCapturedView, top);
                WeakHashMap weakHashMap2 = ViewCompat.sViewPropertyAnimatorMap;
                this.mCapturedView.offsetTopAndBottom(top - top2);
            }
            if (i8 != 0 || i9 != 0) {
                callback.onViewPositionChanged(this.mCapturedView, left, top);
            }
        } else {
            int pointerCount2 = motionEvent.getPointerCount();
            for (int i10 = 0; i10 < pointerCount2; i10++) {
                int pointerId5 = motionEvent.getPointerId(i10);
                if ((this.mPointersDown & (1 << pointerId5)) != 0) {
                    float x4 = motionEvent.getX(i10);
                    float y4 = motionEvent.getY(i10);
                    float f = x4 - this.mInitialMotionX[pointerId5];
                    float f2 = y4 - this.mInitialMotionY[pointerId5];
                    Math.abs(f);
                    Math.abs(f2);
                    int i11 = this.mInitialEdgesTouched[pointerId5];
                    Math.abs(f2);
                    Math.abs(f);
                    int i12 = this.mInitialEdgesTouched[pointerId5];
                    Math.abs(f);
                    Math.abs(f2);
                    int i13 = this.mInitialEdgesTouched[pointerId5];
                    Math.abs(f2);
                    Math.abs(f);
                    int i14 = this.mInitialEdgesTouched[pointerId5];
                    if (this.mDragState == 1) {
                        break;
                    }
                    View findTopChildUnder3 = findTopChildUnder((int) x4, (int) y4);
                    if (checkTouchSlop(findTopChildUnder3, f, f2) && tryCaptureViewForDrag(findTopChildUnder3, pointerId5)) {
                        break;
                    }
                }
            }
        }
        saveLastMotion(motionEvent);
    }

    public final void releaseViewForPointerUp() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        float f = this.mMaxVelocity;
        velocityTracker.computeCurrentVelocity(1000, f);
        float xVelocity = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
        float f2 = this.mMinVelocity;
        float abs = Math.abs(xVelocity);
        float f3 = 0.0f;
        if (abs < f2) {
            xVelocity = 0.0f;
        } else if (abs > f) {
            xVelocity = xVelocity > 0.0f ? f : -f;
        }
        float yVelocity = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
        float abs2 = Math.abs(yVelocity);
        if (abs2 >= f2) {
            if (abs2 > f) {
                if (yVelocity <= 0.0f) {
                    f = -f;
                }
                f3 = f;
            } else {
                f3 = yVelocity;
            }
        }
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, xVelocity, f3);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            setDragState(0);
        }
    }

    public final void saveInitialMotion(int i, float f, float f2) {
        float[] fArr = this.mInitialMotionX;
        if (fArr == null || fArr.length <= i) {
            int i2 = i + 1;
            float[] fArr2 = new float[i2];
            float[] fArr3 = new float[i2];
            float[] fArr4 = new float[i2];
            float[] fArr5 = new float[i2];
            int[] iArr = new int[i2];
            int[] iArr2 = new int[i2];
            int[] iArr3 = new int[i2];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.mInitialMotionY;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.mLastMotionX;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.mLastMotionY;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.mInitialEdgesTouched;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.mEdgeDragsInProgress;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.mEdgeDragsLocked;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.mInitialMotionX = fArr2;
            this.mInitialMotionY = fArr3;
            this.mLastMotionX = fArr4;
            this.mLastMotionY = fArr5;
            this.mInitialEdgesTouched = iArr;
            this.mEdgeDragsInProgress = iArr2;
            this.mEdgeDragsLocked = iArr3;
        }
        float[] fArr9 = this.mInitialMotionX;
        this.mLastMotionX[i] = f;
        fArr9[i] = f;
        float[] fArr10 = this.mInitialMotionY;
        this.mLastMotionY[i] = f2;
        fArr10[i] = f2;
        int[] iArr7 = this.mInitialEdgesTouched;
        int i3 = (int) f;
        int i4 = (int) f2;
        CoordinatorLayout coordinatorLayout = this.mParentView;
        int left = coordinatorLayout.getLeft();
        int i5 = this.mEdgeSize;
        int i6 = i3 < left + i5 ? 1 : 0;
        if (i4 < coordinatorLayout.getTop() + i5) {
            i6 |= 4;
        }
        if (i3 > coordinatorLayout.getRight() - i5) {
            i6 |= 2;
        }
        if (i4 > coordinatorLayout.getBottom() - i5) {
            i6 |= 8;
        }
        iArr7[i] = i6;
        this.mPointersDown = (1 << i) | this.mPointersDown;
    }

    public final void saveLastMotion(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if ((this.mPointersDown & (1 << pointerId)) != 0) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.mLastMotionX[pointerId] = x;
                this.mLastMotionY[pointerId] = y;
            }
        }
    }

    public final void setDragState(int i) {
        this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState != i) {
            this.mDragState = i;
            this.mCallback.onViewDragStateChanged(i);
            if (this.mDragState == 0) {
                this.mCapturedView = null;
            }
        }
    }

    public final boolean settleCapturedViewAt(int i, int i2) {
        if (this.mReleaseInProgress) {
            return forceSettleCapturedViewAt(i, i2, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00c5, code lost:
    
        if (r12 != r11) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean shouldInterceptTouchEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ViewDragHelper.shouldInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public final boolean tryCaptureViewForDrag(View view, int i) {
        if (view == this.mCapturedView && this.mActivePointerId == i) {
            return true;
        }
        if (view == null || !this.mCallback.tryCaptureView(view, i)) {
            return false;
        }
        this.mActivePointerId = i;
        captureChildView(view, i);
        return true;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Callback {
        public abstract int clampViewPositionHorizontal(View view, int i);

        public abstract int clampViewPositionVertical(View view, int i);

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange() {
            return 0;
        }

        public abstract void onViewDragStateChanged(int i);

        public abstract void onViewPositionChanged(View view, int i, int i2);

        public abstract void onViewReleased(View view, float f, float f2);

        public abstract boolean tryCaptureView(View view, int i);

        public void onViewCaptured(View view, int i) {
        }
    }
}
