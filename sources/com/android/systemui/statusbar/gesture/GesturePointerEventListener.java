package com.android.systemui.statusbar.gesture;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.display.DisplayManagerGlobal;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewRootImpl;
import android.widget.OverScroller;
import com.android.systemui.CoreStartable;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GesturePointerEventListener implements CoreStartable {
    public final Context mContext;
    public boolean mDebugFireable;
    public final int mDisplayCutoutTouchableRegionSize;
    public final int[] mDownPointerId;
    public int mDownPointers;
    public final long[] mDownTime;
    public final float[] mDownX;
    public final float[] mDownY;
    public GesturePointerEventListener$start$2 mFlingGestureDetector;
    public final GesturePointerEventDetector mGestureDetector;
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public long mLastFlingTime;
    public boolean mMouseHoveringAtBottom;
    public boolean mMouseHoveringAtLeft;
    public boolean mMouseHoveringAtRight;
    public boolean mMouseHoveringAtTop;
    public final int mSwipeDistanceThreshold;
    public boolean mSwipeFireable;
    public final Rect mSwipeStartThreshold;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FlingGestureDetector extends GestureDetector.SimpleOnGestureListener {
        public final OverScroller mOverscroller;

        public FlingGestureDetector() {
            this.mOverscroller = new OverScroller(GesturePointerEventListener.this.mContext);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            this.mOverscroller.computeScrollOffset();
            long uptimeMillis = SystemClock.uptimeMillis();
            long j = GesturePointerEventListener.this.mLastFlingTime;
            if (j != 0 && uptimeMillis > j + 5000) {
                this.mOverscroller.forceFinished(true);
            }
            this.mOverscroller.fling(0, 0, (int) f, (int) f2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
            this.mOverscroller.getDuration();
            GesturePointerEventListener gesturePointerEventListener = GesturePointerEventListener.this;
            gesturePointerEventListener.mLastFlingTime = uptimeMillis;
            gesturePointerEventListener.getClass();
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onSingleTapUp(MotionEvent motionEvent) {
            if (!this.mOverscroller.isFinished()) {
                this.mOverscroller.forceFinished(true);
            }
            return true;
        }
    }

    public GesturePointerEventListener(Context context, GesturePointerEventDetector gesturePointerEventDetector) {
        Rect rect = new Rect();
        this.mSwipeStartThreshold = rect;
        this.mDownPointerId = new int[32];
        this.mDownX = new float[32];
        this.mDownY = new float[32];
        this.mDownTime = new long[32];
        this.mContext = context;
        this.mGestureDetector = gesturePointerEventDetector;
        if (ViewRootImpl.CLIENT_TRANSIENT) {
            Resources resources = context.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.text_size_display_1_material);
            rect.set(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            this.mSwipeDistanceThreshold = resources.getDimensionPixelSize(R.dimen.text_size_caption_material);
            Display realDisplay = DisplayManagerGlobal.getInstance().getRealDisplay(context.getDisplayId());
            DisplayCutout cutout = realDisplay != null ? realDisplay.getCutout() : null;
            if (cutout != null) {
                this.mDisplayCutoutTouchableRegionSize = resources.getDimensionPixelSize(R.dimen.edit_text_inset_horizontal_material);
                Rect[] boundingRectsAll = cutout.getBoundingRectsAll();
                Rect rect2 = boundingRectsAll[0];
                if (rect2 != null) {
                    rect.left = Math.max(rect.left, rect2.width() + this.mDisplayCutoutTouchableRegionSize);
                }
                Rect rect3 = boundingRectsAll[1];
                if (rect3 != null) {
                    rect.top = Math.max(rect.top, rect3.height() + this.mDisplayCutoutTouchableRegionSize);
                }
                Rect rect4 = boundingRectsAll[2];
                if (rect4 != null) {
                    rect.right = Math.max(rect.right, rect4.width() + this.mDisplayCutoutTouchableRegionSize);
                }
                Rect rect5 = boundingRectsAll[3];
                if (rect5 != null) {
                    rect.bottom = Math.max(rect.bottom, rect5.height() + this.mDisplayCutoutTouchableRegionSize);
                }
            }
        }
    }

    public final void captureDown(MotionEvent motionEvent, int i) {
        int findIndex = findIndex(motionEvent.getPointerId(i));
        if (findIndex != -1) {
            this.mDownX[findIndex] = motionEvent.getX(i);
            this.mDownY[findIndex] = motionEvent.getY(i);
            this.mDownTime[findIndex] = motionEvent.getEventTime();
        }
    }

    public final int detectSwipe(float f, float f2, int i, long j) {
        float f3 = this.mDownX[i];
        float f4 = this.mDownY[i];
        long j2 = j - this.mDownTime[i];
        Rect rect = this.mSwipeStartThreshold;
        if (f4 <= rect.top && f2 > this.mSwipeDistanceThreshold + f4 && j2 < 500) {
            return 1;
        }
        if (f4 >= 0 - rect.bottom && f2 < f4 - this.mSwipeDistanceThreshold && j2 < 500) {
            return 2;
        }
        if (f3 < 0 - rect.right || f >= f3 - this.mSwipeDistanceThreshold || j2 >= 500) {
            return (f3 > ((float) rect.left) || f <= f3 + ((float) this.mSwipeDistanceThreshold) || j2 >= 500) ? 0 : 4;
        }
        return 3;
    }

    public final int findIndex(int i) {
        int i2 = this.mDownPointers;
        int i3 = 0;
        while (true) {
            int[] iArr = this.mDownPointerId;
            if (i3 >= i2) {
                int i4 = this.mDownPointers;
                if (i4 == 32 || i == -1) {
                    return -1;
                }
                this.mDownPointers = i4 + 1;
                iArr[i4] = i;
                return i4;
            }
            if (iArr[i3] == i) {
                return i3;
            }
            i3++;
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (ViewRootImpl.CLIENT_TRANSIENT) {
            Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.gesture.GesturePointerEventListener$start$1
                {
                    super(1);
                }

                /* JADX WARN: Removed duplicated region for block: B:77:0x00fd  */
                @Override // kotlin.jvm.functions.Function1
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invoke(java.lang.Object r15) {
                    /*
                        Method dump skipped, instructions count: 374
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.gesture.GesturePointerEventListener$start$1.invoke(java.lang.Object):java.lang.Object");
                }
            };
            GesturePointerEventDetector gesturePointerEventDetector = this.mGestureDetector;
            gesturePointerEventDetector.addOnGestureDetectedCallback("GesturePointerEventHandler", function1);
            gesturePointerEventDetector.startGestureListening$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
            this.mFlingGestureDetector = new GesturePointerEventListener$start$2(this.mContext, new FlingGestureDetector(), this.mHandler);
        }
    }
}
