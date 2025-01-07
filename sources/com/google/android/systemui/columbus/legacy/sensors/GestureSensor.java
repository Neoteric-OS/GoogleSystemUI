package com.google.android.systemui.columbus.legacy.sensors;

import java.io.Closeable;
import java.util.Random;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class GestureSensor implements Closeable {
    public GestureController$gestureSensorListener$1 listener;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DetectionProperties {
        public final long actionId = new Random().nextLong();
        public final boolean isHapticConsumed;

        public DetectionProperties(boolean z) {
            this.isHapticConsumed = z;
        }
    }

    public abstract boolean isListening();

    public void setGestureListener(GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1) {
        this.listener = gestureController$gestureSensorListener$1;
    }

    public abstract void startListening();

    public abstract void stopListening();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }
}
