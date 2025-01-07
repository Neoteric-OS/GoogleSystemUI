package com.android.systemui.classifier;

import android.provider.DeviceConfig;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import com.android.systemui.util.DeviceConfigProxy;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DistanceClassifier extends FalsingClassifier {
    public DistanceVectors mCachedDistance;
    public boolean mDistanceDirty;
    public final float mHorizontalFlingThresholdPx;
    public final float mHorizontalSwipeThresholdPx;
    public final float mVelocityToDistanceMultiplier;
    public final float mVerticalFlingThresholdPx;
    public final float mVerticalSwipeThresholdPx;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DistanceVectors {
        public final float mDx;
        public final float mDy;
        public final float mVx;
        public final float mVy;

        public DistanceVectors(float f, float f2, float f3, float f4) {
            this.mDx = f;
            this.mDy = f2;
            this.mVx = f3;
            this.mVy = f4;
        }

        public final String toString() {
            return String.format(null, "{dx=%f, vx=%f, dy=%f, vy=%f}", Float.valueOf(this.mDx), Float.valueOf(this.mVx), Float.valueOf(this.mDy), Float.valueOf(this.mVy));
        }
    }

    public DistanceClassifier(FalsingDataProvider falsingDataProvider, DeviceConfigProxy deviceConfigProxy) {
        super(falsingDataProvider);
        deviceConfigProxy.getClass();
        this.mVelocityToDistanceMultiplier = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_velcoity_to_distance", 30.0f);
        float f = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_horizontal_fling_threshold_in", 1.0f);
        float f2 = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_vertical_fling_threshold_in", 1.5f);
        float f3 = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_horizontal_swipe_threshold_in", 3.0f);
        float f4 = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_horizontal_swipe_threshold_in", 3.0f);
        float f5 = DeviceConfig.getFloat("systemui", "brightline_falsing_distance_screen_fraction_max_distance", 0.8f);
        this.mHorizontalFlingThresholdPx = Math.min(falsingDataProvider.mWidthPixels * f5, f * falsingDataProvider.mXdpi);
        this.mVerticalFlingThresholdPx = Math.min(falsingDataProvider.mHeightPixels * f5, f2 * falsingDataProvider.mYdpi);
        this.mHorizontalSwipeThresholdPx = Math.min(falsingDataProvider.mWidthPixels * f5, f3 * falsingDataProvider.mXdpi);
        this.mVerticalSwipeThresholdPx = Math.min(falsingDataProvider.mHeightPixels * f5, f4 * falsingDataProvider.mYdpi);
        this.mDistanceDirty = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0073, code lost:
    
        if (java.lang.Math.abs(r0) >= r1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00b3, code lost:
    
        return falsed(0.5d, getReason$1());
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:?, code lost:
    
        return com.android.systemui.classifier.FalsingClassifier.Result.passed(0.5d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a4, code lost:
    
        if (java.lang.Math.abs(r4) >= r0) goto L26;
     */
    @Override // com.android.systemui.classifier.FalsingClassifier
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.android.systemui.classifier.FalsingClassifier.Result calculateFalsingResult(int r11) {
        /*
            r10 = this;
            r0 = 10
            if (r11 == r0) goto Lb4
            r0 = 18
            if (r11 == r0) goto Lb4
            r0 = 11
            if (r11 == r0) goto Lb4
            r0 = 12
            if (r11 == r0) goto Lb4
            r0 = 13
            if (r11 == r0) goto Lb4
            r0 = 15
            if (r11 == r0) goto Lb4
            r0 = 17
            if (r11 == r0) goto Lb4
            r0 = 19
            if (r11 != r0) goto L22
            goto Lb4
        L22:
            com.android.systemui.classifier.DistanceClassifier$DistanceVectors r11 = r10.getDistances()
            float r0 = r11.mDx
            float r1 = r11.mVx
            float r2 = r10.mVelocityToDistanceMultiplier
            float r1 = r1 * r2
            float r0 = r0 + r1
            float r3 = r11.mVy
            float r3 = r3 * r2
            float r2 = r11.mDy
            float r4 = r3 + r2
            com.android.systemui.classifier.FalsingDataProvider r5 = r10.mDataProvider
            boolean r5 = r5.isHorizontal()
            r6 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            java.lang.String r8 = "Threshold: "
            java.lang.String r9 = ", "
            if (r5 == 0) goto L76
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Horizontal swipe and fling distance: "
            r2.<init>(r3)
            float r11 = r11.mDx
            r2.append(r11)
            r2.append(r9)
            r2.append(r1)
            java.lang.String r11 = r2.toString()
            com.android.systemui.classifier.BrightLineFalsingManager.logDebug(r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r8)
            float r1 = r10.mHorizontalFlingThresholdPx
            r11.append(r1)
            java.lang.String r11 = r11.toString()
            com.android.systemui.classifier.BrightLineFalsingManager.logDebug(r11)
            float r11 = java.lang.Math.abs(r0)
            int r11 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r11 < 0) goto Lab
            goto La6
        L76:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "Vertical swipe and fling distance: "
            r11.<init>(r0)
            r11.append(r2)
            r11.append(r9)
            r11.append(r3)
            java.lang.String r11 = r11.toString()
            com.android.systemui.classifier.BrightLineFalsingManager.logDebug(r11)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r8)
            float r0 = r10.mVerticalFlingThresholdPx
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            com.android.systemui.classifier.BrightLineFalsingManager.logDebug(r11)
            float r11 = java.lang.Math.abs(r4)
            int r11 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r11 < 0) goto Lab
        La6:
            com.android.systemui.classifier.FalsingClassifier$Result r10 = com.android.systemui.classifier.FalsingClassifier.Result.passed(r6)
            goto Lb3
        Lab:
            java.lang.String r11 = r10.getReason$1()
            com.android.systemui.classifier.FalsingClassifier$Result r10 = r10.falsed(r6, r11)
        Lb3:
            return r10
        Lb4:
            r10 = 0
            com.android.systemui.classifier.FalsingClassifier$Result r10 = com.android.systemui.classifier.FalsingClassifier.Result.passed(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.classifier.DistanceClassifier.calculateFalsingResult(int):com.android.systemui.classifier.FalsingClassifier$Result");
    }

    public final DistanceVectors getDistances() {
        DistanceVectors distanceVectors;
        if (this.mDistanceDirty) {
            FalsingDataProvider falsingDataProvider = this.mDataProvider;
            List recentMotionEvents = falsingDataProvider.getRecentMotionEvents();
            if (recentMotionEvents.size() < 3) {
                BrightLineFalsingManager.logDebug("Only " + recentMotionEvents.size() + " motion events recorded.");
                distanceVectors = new DistanceVectors(0.0f, 0.0f, 0.0f, 0.0f);
            } else {
                VelocityTracker obtain = VelocityTracker.obtain();
                Iterator it = recentMotionEvents.iterator();
                while (it.hasNext()) {
                    obtain.addMovement((MotionEvent) it.next());
                }
                obtain.computeCurrentVelocity(1);
                float xVelocity = obtain.getXVelocity();
                float yVelocity = obtain.getYVelocity();
                obtain.recycle();
                falsingDataProvider.recalculateData();
                float x = falsingDataProvider.mLastMotionEvent.getX();
                falsingDataProvider.recalculateData();
                float x2 = x - falsingDataProvider.mFirstRecentMotionEvent.getX();
                falsingDataProvider.recalculateData();
                float y = falsingDataProvider.mLastMotionEvent.getY();
                falsingDataProvider.recalculateData();
                distanceVectors = new DistanceVectors(x2, y - falsingDataProvider.mFirstRecentMotionEvent.getY(), xVelocity, yVelocity);
            }
            this.mCachedDistance = distanceVectors;
            this.mDistanceDirty = false;
        }
        return this.mCachedDistance;
    }

    public final String getReason$1() {
        return String.format(null, "{distanceVectors=%s, isHorizontal=%s, velocityToDistanceMultiplier=%f, horizontalFlingThreshold=%f, verticalFlingThreshold=%f, horizontalSwipeThreshold=%f, verticalSwipeThreshold=%s}", getDistances(), Boolean.valueOf(this.mDataProvider.isHorizontal()), Float.valueOf(this.mVelocityToDistanceMultiplier), Float.valueOf(this.mHorizontalFlingThresholdPx), Float.valueOf(this.mVerticalFlingThresholdPx), Float.valueOf(this.mHorizontalSwipeThresholdPx), Float.valueOf(this.mVerticalSwipeThresholdPx));
    }

    @Override // com.android.systemui.classifier.FalsingClassifier
    public final void onTouchEvent(MotionEvent motionEvent) {
        this.mDistanceDirty = true;
    }
}
