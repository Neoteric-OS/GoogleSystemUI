package com.android.systemui.classifier;

import android.view.MotionEvent;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.FalsingManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FalsingClassifier {
    public final FalsingDataProvider mDataProvider;
    public final FalsingClassifier$$ExternalSyntheticLambda0 mMotionEventListener;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Result {
        public final double mConfidence;
        public final String mContext;
        public final boolean mFalsed;
        public final String mReason;

        public Result(boolean z, double d, String str, String str2) {
            this.mFalsed = z;
            this.mConfidence = d;
            this.mContext = str;
            this.mReason = str2;
        }

        public static Result passed(double d) {
            return new Result(false, d, null, null);
        }

        public final String getReason() {
            StringBuilder sb = new StringBuilder("{context=");
            sb.append(this.mContext);
            sb.append(" reason=");
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.mReason, "}");
        }
    }

    public FalsingClassifier(FalsingDataProvider falsingDataProvider) {
        FalsingClassifier$$ExternalSyntheticLambda0 falsingClassifier$$ExternalSyntheticLambda0 = new FalsingClassifier$$ExternalSyntheticLambda0(this);
        this.mMotionEventListener = falsingClassifier$$ExternalSyntheticLambda0;
        this.mDataProvider = falsingDataProvider;
        falsingDataProvider.mMotionEventListeners.add(falsingClassifier$$ExternalSyntheticLambda0);
    }

    public abstract Result calculateFalsingResult(int i);

    public final Result falsed(double d, String str) {
        return new Result(true, d, getClass().getSimpleName(), str);
    }

    public final boolean isRight() {
        FalsingDataProvider falsingDataProvider = this.mDataProvider;
        falsingDataProvider.recalculateData();
        return !falsingDataProvider.mRecentMotionEvents.mInputEvents.isEmpty() && falsingDataProvider.mLastMotionEvent.getX() > falsingDataProvider.mFirstRecentMotionEvent.getX();
    }

    public final boolean isUp() {
        FalsingDataProvider falsingDataProvider = this.mDataProvider;
        falsingDataProvider.recalculateData();
        return !falsingDataProvider.mRecentMotionEvents.mInputEvents.isEmpty() && falsingDataProvider.mLastMotionEvent.getY() < falsingDataProvider.mFirstRecentMotionEvent.getY();
    }

    public void onSessionEnded() {
    }

    public void onSessionStarted() {
    }

    public void onProximityEvent(FalsingManager.ProximityEvent proximityEvent) {
    }

    public void onTouchEvent(MotionEvent motionEvent) {
    }
}
