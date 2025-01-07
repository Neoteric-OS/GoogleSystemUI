package androidx.core.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DifferentialMotionFlingController {
    public final Context mContext;
    public float mLastFlingVelocity;
    public final DifferentialMotionFlingTarget mTarget;
    public final DifferentialMotionFlingController$$ExternalSyntheticLambda0 mVelocityProvider;
    public final DifferentialMotionFlingController$$ExternalSyntheticLambda0 mVelocityThresholdCalculator;
    public VelocityTracker mVelocityTracker;
    public int mLastProcessedAxis = -1;
    public int mLastProcessedSource = -1;
    public int mLastProcessedDeviceId = -1;
    public final int[] mFlingVelocityThresholds = {Integer.MAX_VALUE, 0};

    public DifferentialMotionFlingController(Context context, DifferentialMotionFlingTarget differentialMotionFlingTarget) {
        this.mContext = context;
        this.mTarget = differentialMotionFlingTarget;
    }

    public final void onMotionEvent(MotionEvent motionEvent, int i) {
        boolean z;
        int source = motionEvent.getSource();
        int deviceId = motionEvent.getDeviceId();
        int i2 = this.mLastProcessedSource;
        int[] iArr = this.mFlingVelocityThresholds;
        if (i2 == source && this.mLastProcessedDeviceId == deviceId && this.mLastProcessedAxis == i) {
            z = false;
        } else {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(this.mContext);
            iArr[0] = viewConfiguration.getScaledMinimumFlingVelocity(motionEvent.getDeviceId(), i, motionEvent.getSource());
            iArr[1] = viewConfiguration.getScaledMaximumFlingVelocity(motionEvent.getDeviceId(), i, motionEvent.getSource());
            this.mLastProcessedSource = source;
            this.mLastProcessedDeviceId = deviceId;
            this.mLastProcessedAxis = i;
            z = true;
        }
        if (iArr[0] == Integer.MAX_VALUE) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.mVelocityTracker = null;
                return;
            }
            return;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        VelocityTracker velocityTracker2 = this.mVelocityTracker;
        Map map = VelocityTrackerCompat.sFallbackTrackers;
        velocityTracker2.addMovement(motionEvent);
        velocityTracker2.computeCurrentVelocity(1000, Float.MAX_VALUE);
        VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(VelocityTrackerCompat.sFallbackTrackers.get(velocityTracker2));
        float axisVelocity = velocityTracker2.getAxisVelocity(i);
        DifferentialMotionFlingTarget differentialMotionFlingTarget = this.mTarget;
        float scaledScrollFactor = differentialMotionFlingTarget.getScaledScrollFactor() * axisVelocity;
        float signum = Math.signum(scaledScrollFactor);
        if (z || (signum != Math.signum(this.mLastFlingVelocity) && signum != 0.0f)) {
            differentialMotionFlingTarget.stopDifferentialMotionFling();
        }
        if (Math.abs(scaledScrollFactor) < iArr[0]) {
            return;
        }
        float max = Math.max(-r9, Math.min(scaledScrollFactor, iArr[1]));
        this.mLastFlingVelocity = differentialMotionFlingTarget.startDifferentialMotionFling(max) ? max : 0.0f;
    }
}
