package com.android.systemui.classifier;

import android.hardware.devicestate.DeviceStateManager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.android.systemui.dock.DockManager;
import com.android.systemui.statusbar.policy.BatteryController;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FalsingDataProvider {
    public boolean mA11YAction;
    public final BatteryController mBatteryController;
    public final DockManager mDockManager;
    public boolean mDropLastEvent;
    public MotionEvent mFirstRecentMotionEvent;
    public final DeviceStateManager.FoldStateListener mFoldStateListener;
    public final int mHeightPixels;
    public final boolean mIsFoldableDevice;
    public boolean mJustUnlockedWithFace;
    public MotionEvent mLastMotionEvent;
    public final int mWidthPixels;
    public final float mXdpi;
    public final float mYdpi;
    public final List mSessionListeners = new ArrayList();
    public final List mMotionEventListeners = new ArrayList();
    public final List mGestureFinalizedListeners = new ArrayList();
    public TimeLimitedInputEventBuffer mRecentMotionEvents = new TimeLimitedInputEventBuffer(1000);
    public final TimeLimitedInputEventBuffer mRecentKeyEvents = new TimeLimitedInputEventBuffer(500);
    public List mPriorMotionEvents = new ArrayList();
    public boolean mDirty = true;
    public float mAngle = 0.0f;

    public FalsingDataProvider(DisplayMetrics displayMetrics, BatteryController batteryController, DeviceStateManager.FoldStateListener foldStateListener, DockManager dockManager, boolean z) {
        float f = displayMetrics.xdpi;
        this.mXdpi = f;
        float f2 = displayMetrics.ydpi;
        this.mYdpi = f2;
        int i = displayMetrics.widthPixels;
        this.mWidthPixels = i;
        int i2 = displayMetrics.heightPixels;
        this.mHeightPixels = i2;
        this.mBatteryController = batteryController;
        this.mFoldStateListener = foldStateListener;
        this.mDockManager = dockManager;
        this.mIsFoldableDevice = z;
        BrightLineFalsingManager.logInfo("xdpi, ydpi: " + f + ", " + f2);
        BrightLineFalsingManager.logInfo("width, height: " + i + ", " + i2);
    }

    public final void completePriorGesture() {
        if (!this.mRecentMotionEvents.mInputEvents.isEmpty()) {
            ((ArrayList) this.mGestureFinalizedListeners).forEach(new FalsingDataProvider$$ExternalSyntheticLambda2(1, this));
            this.mPriorMotionEvents = this.mRecentMotionEvents;
            this.mRecentMotionEvents = new TimeLimitedInputEventBuffer(1000L);
        }
        this.mDropLastEvent = false;
        this.mA11YAction = false;
    }

    public final List getRecentMotionEvents() {
        if (!this.mDropLastEvent || this.mRecentMotionEvents.mInputEvents.isEmpty()) {
            return this.mRecentMotionEvents;
        }
        return this.mRecentMotionEvents.mInputEvents.subList(0, r2.size() - 1);
    }

    public final boolean isHorizontal() {
        recalculateData();
        return !this.mRecentMotionEvents.mInputEvents.isEmpty() && Math.abs(this.mFirstRecentMotionEvent.getX() - this.mLastMotionEvent.getX()) > Math.abs(this.mFirstRecentMotionEvent.getY() - this.mLastMotionEvent.getY());
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x011e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMotionEvent(android.view.MotionEvent r28) {
        /*
            Method dump skipped, instructions count: 348
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.classifier.FalsingDataProvider.onMotionEvent(android.view.MotionEvent):void");
    }

    public final void recalculateData() {
        if (this.mDirty) {
            List recentMotionEvents = getRecentMotionEvents();
            if (recentMotionEvents.isEmpty()) {
                this.mFirstRecentMotionEvent = null;
                this.mLastMotionEvent = null;
            } else {
                this.mFirstRecentMotionEvent = (MotionEvent) recentMotionEvents.get(0);
                this.mLastMotionEvent = (MotionEvent) recentMotionEvents.get(recentMotionEvents.size() - 1);
            }
            if (this.mRecentMotionEvents.size() >= 2) {
                this.mAngle = (float) Math.atan2(this.mLastMotionEvent.getY() - this.mFirstRecentMotionEvent.getY(), this.mLastMotionEvent.getX() - this.mFirstRecentMotionEvent.getX());
                while (true) {
                    float f = this.mAngle;
                    if (f >= 0.0f) {
                        break;
                    } else {
                        this.mAngle = f + 6.2831855f;
                    }
                }
                while (true) {
                    float f2 = this.mAngle;
                    if (f2 <= 6.2831855f) {
                        break;
                    } else {
                        this.mAngle = f2 - 6.2831855f;
                    }
                }
            } else {
                this.mAngle = Float.MAX_VALUE;
            }
            this.mDirty = false;
        }
    }
}
