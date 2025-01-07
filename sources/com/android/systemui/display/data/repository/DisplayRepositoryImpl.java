package com.android.systemui.display.data.repository;

import android.hardware.display.DisplayManager;
import android.os.Trace;
import android.util.Log;
import android.view.Display;
import com.android.app.tracing.FlowTracing;
import com.android.app.tracing.TraceStateLogger;
import com.android.app.tracing.TraceUtilsKt;
import java.util.Set;
import kotlin.Lazy;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DisplayRepositoryImpl implements DisplayRepository {
    public static final boolean DEBUG = Log.isLoggable("DisplayRepository", 3);
    public final StateFlowImpl _ignoredDisplayIds;
    public final Flow allDisplayEvents;
    public final ReadonlyStateFlow connectedDisplayIds;
    public final Lazy defaultDisplay$delegate;
    public final Flow defaultDisplayOff;
    public final DisplayRepositoryImpl$special$$inlined$map$2 displayAdditionEvent;
    public final DisplayRepositoryImpl$special$$inlined$map$1 displayChangeEvent;
    public final DisplayManager displayManager;
    public final ReadonlyStateFlow displays;
    public final ReadonlyStateFlow enabledDisplays;
    public final Set initialDisplays;
    public final Flow pendingDisplay;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x007d A[LOOP:0: B:13:0x0077->B:15:0x007d, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DisplayRepositoryImpl(android.hardware.display.DisplayManager r9, android.os.Handler r10, kotlinx.coroutines.CoroutineScope r11, kotlinx.coroutines.CoroutineDispatcher r12) {
        /*
            Method dump skipped, instructions count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.display.data.repository.DisplayRepositoryImpl.<init>(android.hardware.display.DisplayManager, android.os.Handler, kotlinx.coroutines.CoroutineScope, kotlinx.coroutines.CoroutineDispatcher):void");
    }

    public static final Display access$getDisplay(int i, DisplayRepositoryImpl displayRepositoryImpl) {
        displayRepositoryImpl.getClass();
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("DisplayRepository#getDisplay");
        }
        try {
            return displayRepositoryImpl.displayManager.getDisplay(i);
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    public static Flow debugLog(Flow flow, String str) {
        if (!DEBUG) {
            return flow;
        }
        return new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowTracing.traceEmissionCount$default(flow, str), new DisplayRepositoryImpl$debugLog$$inlined$traceEach$default$1(new TraceStateLogger(str, 6), null), 0);
    }
}
