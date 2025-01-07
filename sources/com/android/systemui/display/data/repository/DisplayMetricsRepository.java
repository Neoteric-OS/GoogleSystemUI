package com.android.systemui.display.data.repository;

import android.content.Context;
import android.util.DisplayMetrics;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DisplayMetricsRepository {
    public DisplayMetricsRepository(CoroutineScope coroutineScope, ConfigurationController configurationController, DisplayMetrics displayMetrics, Context context, LogBuffer logBuffer) {
        FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowConflatedKt.conflatedCallbackFlow(new DisplayMetricsRepository$displayMetrics$1(configurationController, context, displayMetrics, null)), new DisplayMetricsRepository$displayMetrics$2(logBuffer, null), 0), coroutineScope, SharingStarted.Companion.Eagerly, displayMetrics);
    }
}
