package com.android.systemui.classifier;

import android.provider.DeviceConfig;
import android.view.MotionEvent;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.util.DeviceConfigProxy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ProximityClassifier extends FalsingClassifier {
    public final DistanceClassifier mDistanceClassifier;
    public long mGestureStartTimeNs;
    public boolean mNear;
    public long mNearDurationNs;
    public final float mPercentCoveredThreshold;
    public float mPercentNear;
    public long mPrevNearTimeNs;

    public ProximityClassifier(DistanceClassifier distanceClassifier, FalsingDataProvider falsingDataProvider, DeviceConfigProxy deviceConfigProxy) {
        super(falsingDataProvider);
        this.mDistanceClassifier = distanceClassifier;
        deviceConfigProxy.getClass();
        this.mPercentCoveredThreshold = DeviceConfig.getFloat("systemui", "brightline_falsing_proximity_percent_covered_threshold", 0.1f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0060, code lost:
    
        if (java.lang.Math.abs(r3.mDx) >= r7) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0062, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0097, code lost:
    
        com.android.systemui.classifier.BrightLineFalsingManager.logDebug("Is longSwipe? " + r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00a8, code lost:
    
        if (r5 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00aa, code lost:
    
        r10 = com.android.systemui.classifier.FalsingClassifier.Result.passed(0.5d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00b9, code lost:
    
        if (r10.mFalsed == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:?, code lost:
    
        return falsed(0.5d, java.lang.String.format(null, "{percentInProximity=%f, threshold=%f, distanceClassifier=%s}", java.lang.Float.valueOf(r9.mPercentNear), java.lang.Float.valueOf(r0), r10.getReason()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00dd, code lost:
    
        return com.android.systemui.classifier.FalsingClassifier.Result.passed(0.5d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00af, code lost:
    
        r10 = r10.falsed(0.5d, r10.getReason$1());
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0094, code lost:
    
        if (java.lang.Math.abs(r3.mDy) >= r7) goto L18;
     */
    @Override // com.android.systemui.classifier.FalsingClassifier
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.android.systemui.classifier.FalsingClassifier.Result calculateFalsingResult(int r10) {
        /*
            r9 = this;
            if (r10 == 0) goto Le3
            r0 = 10
            if (r10 == r0) goto Le3
            r0 = 12
            if (r10 == r0) goto Le3
            r0 = 15
            if (r10 == r0) goto Le3
            r0 = 18
            if (r10 != r0) goto L14
            goto Le3
        L14:
            float r10 = r9.mPercentNear
            float r0 = r9.mPercentCoveredThreshold
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            r1 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            if (r10 <= 0) goto Lde
            com.android.systemui.classifier.DistanceClassifier r10 = r9.mDistanceClassifier
            com.android.systemui.classifier.DistanceClassifier$DistanceVectors r3 = r10.getDistances()
            com.android.systemui.classifier.FalsingDataProvider r4 = r10.mDataProvider
            boolean r4 = r4.isHorizontal()
            r5 = 0
            r6 = 1
            java.lang.String r7 = "Threshold: "
            if (r4 == 0) goto L64
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r8 = "Horizontal swipe distance: "
            r4.<init>(r8)
            float r8 = r3.mDx
            float r8 = java.lang.Math.abs(r8)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            com.android.systemui.classifier.BrightLineFalsingManager.logDebug(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r7)
            float r7 = r10.mHorizontalSwipeThresholdPx
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            com.android.systemui.classifier.BrightLineFalsingManager.logDebug(r4)
            float r3 = r3.mDx
            float r3 = java.lang.Math.abs(r3)
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 < 0) goto L97
        L62:
            r5 = r6
            goto L97
        L64:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r8 = "Vertical swipe distance: "
            r4.<init>(r8)
            float r8 = r3.mDy
            float r8 = java.lang.Math.abs(r8)
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            com.android.systemui.classifier.BrightLineFalsingManager.logDebug(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r7)
            float r7 = r10.mVerticalSwipeThresholdPx
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            com.android.systemui.classifier.BrightLineFalsingManager.logDebug(r4)
            float r3 = r3.mDy
            float r3 = java.lang.Math.abs(r3)
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 < 0) goto L97
            goto L62
        L97:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Is longSwipe? "
            r3.<init>(r4)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            com.android.systemui.classifier.BrightLineFalsingManager.logDebug(r3)
            if (r5 == 0) goto Laf
            com.android.systemui.classifier.FalsingClassifier$Result r10 = com.android.systemui.classifier.FalsingClassifier.Result.passed(r1)
            goto Lb7
        Laf:
            java.lang.String r3 = r10.getReason$1()
            com.android.systemui.classifier.FalsingClassifier$Result r10 = r10.falsed(r1, r3)
        Lb7:
            boolean r3 = r10.mFalsed
            if (r3 == 0) goto Ld9
            float r3 = r9.mPercentNear
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            java.lang.String r10 = r10.getReason()
            java.lang.Object[] r10 = new java.lang.Object[]{r3, r0, r10}
            r0 = 0
            java.lang.String r3 = "{percentInProximity=%f, threshold=%f, distanceClassifier=%s}"
            java.lang.String r10 = java.lang.String.format(r0, r3, r10)
            com.android.systemui.classifier.FalsingClassifier$Result r9 = r9.falsed(r1, r10)
            goto Ldd
        Ld9:
            com.android.systemui.classifier.FalsingClassifier$Result r9 = com.android.systemui.classifier.FalsingClassifier.Result.passed(r1)
        Ldd:
            return r9
        Lde:
            com.android.systemui.classifier.FalsingClassifier$Result r9 = com.android.systemui.classifier.FalsingClassifier.Result.passed(r1)
            return r9
        Le3:
            r9 = 0
            com.android.systemui.classifier.FalsingClassifier$Result r9 = com.android.systemui.classifier.FalsingClassifier.Result.passed(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.classifier.ProximityClassifier.calculateFalsingResult(int):com.android.systemui.classifier.FalsingClassifier$Result");
    }

    @Override // com.android.systemui.classifier.FalsingClassifier
    public final void onProximityEvent(FalsingManager.ProximityEvent proximityEvent) {
        boolean covered = proximityEvent.getCovered();
        long timestampNs = proximityEvent.getTimestampNs();
        BrightLineFalsingManager.logDebug("Sensor is: " + covered + " at time " + timestampNs);
        update(timestampNs, covered);
    }

    @Override // com.android.systemui.classifier.FalsingClassifier
    public final void onSessionEnded() {
        this.mPrevNearTimeNs = 0L;
        this.mPercentNear = 0.0f;
    }

    @Override // com.android.systemui.classifier.FalsingClassifier
    public final void onSessionStarted() {
        this.mPrevNearTimeNs = 0L;
        this.mPercentNear = 0.0f;
    }

    @Override // com.android.systemui.classifier.FalsingClassifier
    public final void onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mGestureStartTimeNs = motionEvent.getEventTimeNanos();
            if (this.mPrevNearTimeNs > 0) {
                this.mPrevNearTimeNs = motionEvent.getEventTimeNanos();
            }
            BrightLineFalsingManager.logDebug("Gesture start time: " + this.mGestureStartTimeNs);
            this.mNearDurationNs = 0L;
        }
        if (actionMasked == 1 || actionMasked == 3) {
            update(motionEvent.getEventTimeNanos(), this.mNear);
            long eventTimeNanos = motionEvent.getEventTimeNanos() - this.mGestureStartTimeNs;
            BrightLineFalsingManager.logDebug("Gesture duration, Proximity duration: " + eventTimeNanos + ", " + this.mNearDurationNs);
            if (eventTimeNanos == 0) {
                this.mPercentNear = this.mNear ? 1.0f : 0.0f;
            } else {
                this.mPercentNear = this.mNearDurationNs / eventTimeNanos;
            }
        }
    }

    public final void update(long j, boolean z) {
        long j2 = this.mPrevNearTimeNs;
        if (j2 != 0 && j > j2 && this.mNear) {
            this.mNearDurationNs = (j - j2) + this.mNearDurationNs;
            BrightLineFalsingManager.logDebug("Updating duration: " + this.mNearDurationNs);
        }
        if (z) {
            BrightLineFalsingManager.logDebug("Set prevNearTimeNs: " + j);
            this.mPrevNearTimeNs = j;
        }
        this.mNear = z;
    }
}
