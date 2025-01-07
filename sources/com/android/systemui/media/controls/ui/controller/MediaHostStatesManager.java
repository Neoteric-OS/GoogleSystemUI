package com.android.systemui.media.controls.ui.controller;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.media.controls.ui.view.MediaHostState;
import com.android.systemui.util.animation.MeasurementOutput;
import com.android.systemui.util.animation.TransitionViewState;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaHostStatesManager {
    public final Set callbacks = new LinkedHashSet();
    public final Set controllers = new LinkedHashSet();
    public final Map carouselSizes = new LinkedHashMap();
    public final Map mediaHostStates = new LinkedHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        void onHostStateChanged(int i, MediaHostState mediaHostState);
    }

    public final MeasurementOutput updateCarouselDimensions(int i, MediaHostState mediaHostState) {
        MeasurementOutput measurementOutput;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("MediaHostStatesManager#updateCarouselDimensions");
        }
        try {
            MeasurementOutput measurementOutput2 = new MeasurementOutput();
            for (MediaViewController mediaViewController : this.controllers) {
                mediaViewController.getClass();
                isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("MediaViewController#getMeasurementsForState");
                }
                try {
                    TransitionViewState obtainViewState = mediaViewController.obtainViewState(mediaHostState, false);
                    if (obtainViewState == null) {
                        if (isEnabled) {
                            TraceUtilsKt.endSlice();
                        }
                        measurementOutput = null;
                    } else {
                        measurementOutput = mediaViewController.measurement;
                        measurementOutput.measuredWidth = obtainViewState.measureWidth;
                        measurementOutput.measuredHeight = obtainViewState.measureHeight;
                        if (isEnabled) {
                            TraceUtilsKt.endSlice();
                        }
                    }
                    if (measurementOutput != null) {
                        int i2 = measurementOutput.measuredHeight;
                        if (i2 > measurementOutput2.measuredHeight) {
                            measurementOutput2.measuredHeight = i2;
                        }
                        int i3 = measurementOutput.measuredWidth;
                        if (i3 > measurementOutput2.measuredWidth) {
                            measurementOutput2.measuredWidth = i3;
                        }
                    }
                } finally {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                }
            }
            this.carouselSizes.put(Integer.valueOf(i), measurementOutput2);
            return measurementOutput2;
        } catch (Throwable th) {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }
}
