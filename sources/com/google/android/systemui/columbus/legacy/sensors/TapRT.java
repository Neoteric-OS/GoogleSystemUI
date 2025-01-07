package com.google.android.systemui.columbus.legacy.sensors;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TapRT {
    public Deque mAccXs;
    public Deque mAccYs;
    public Deque mAccZs;
    public TfClassifier mClassifier;
    public ArrayList mFeatureVector;
    public boolean mGotAcc;
    public boolean mGotGyro;
    public Deque mGyroXs;
    public Deque mGyroYs;
    public Deque mGyroZs;
    public Highpass3C mHighpassAcc;
    public Highpass3C mHighpassGyro;
    public Lowpass3C mLowpassAcc;
    public Lowpass3C mLowpassGyro;
    public int mNumberFeature;
    public PeakDetector mPeakDetector;
    public Resample3C mResampleAcc;
    public Resample3C mResampleGyro;
    public int mResult;
    public int mSizeFeatureWindow;
    public long mSizeWindowNs;
    public Slope3C mSlopeAcc;
    public Slope3C mSlopeGyro;
    public long mSyncTime;
    public Deque mTimestampsBackTap;
    public PeakDetector mValleyDetector;
    public boolean mWasPeakApproaching;

    public final void addToFeatureVector(Deque deque, int i, int i2) {
        Iterator it = deque.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            if (i3 < i) {
                it.next();
            } else {
                if (i3 >= this.mSizeFeatureWindow + i) {
                    return;
                }
                this.mFeatureVector.set(i2, (Float) it.next());
                i2++;
            }
            i3++;
        }
    }
}
