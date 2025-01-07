package com.google.android.systemui.elmyra.sensors;

import java.util.Random;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface GestureSensor {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DetectionProperties {
        public final long mActionId = new Random().nextLong();
        public final boolean mHapticConsumed;
        public final boolean mHostSuspended;

        public DetectionProperties(boolean z, boolean z2) {
            this.mHapticConsumed = z;
            this.mHostSuspended = z2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void onGestureDetected(DetectionProperties detectionProperties);

        void onGestureProgress(int i, float f);
    }

    boolean isListening();

    void setGestureListener(Listener listener);

    void startListening();

    void stopListening();
}
