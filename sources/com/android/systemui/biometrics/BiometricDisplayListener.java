package com.android.systemui.biometrics;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Trace;
import android.view.Display;
import android.view.DisplayInfo;
import com.android.app.tracing.TraceUtilsKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricDisplayListener implements DisplayManager.DisplayListener {
    public final DisplayInfo cachedDisplayInfo = new DisplayInfo();
    public final Context context;
    public final DisplayManager displayManager;
    public final Handler handler;
    public final Function0 onChanged;
    public final SensorType sensorType;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class SensorType {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Generic extends SensorType {
            public static final Generic INSTANCE = new Generic();

            public final boolean equals(Object obj) {
                return this == obj || (obj instanceof Generic);
            }

            public final int hashCode() {
                return -12627015;
            }

            public final String toString() {
                return "Generic";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class UnderDisplayFingerprint extends SensorType {
            public static final UnderDisplayFingerprint INSTANCE = new UnderDisplayFingerprint();

            public final boolean equals(Object obj) {
                return this == obj || (obj instanceof UnderDisplayFingerprint);
            }

            public final int hashCode() {
                return -949352036;
            }

            public final String toString() {
                return "UnderDisplayFingerprint";
            }
        }
    }

    public BiometricDisplayListener(Context context, DisplayManager displayManager, Handler handler, SensorType sensorType, Function0 function0) {
        this.context = context;
        this.displayManager = displayManager;
        this.handler = handler;
        this.sensorType = sensorType;
        this.onChanged = function0;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayChanged(int i) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("BiometricDisplayListener(" + this.sensorType + ")#onDisplayChanged");
        }
        try {
            int i2 = this.cachedDisplayInfo.rotation;
            Display display = this.context.getDisplay();
            if (display != null) {
                display.getDisplayInfo(this.cachedDisplayInfo);
            }
            if (i2 != this.cachedDisplayInfo.rotation) {
                this.onChanged.invoke();
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayAdded(int i) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayRemoved(int i) {
    }
}
