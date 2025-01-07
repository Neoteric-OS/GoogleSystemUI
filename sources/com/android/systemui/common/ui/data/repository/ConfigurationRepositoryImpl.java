package com.android.systemui.common.ui.data.repository;

import android.content.Context;
import android.util.DisplayUtils;
import android.view.Display;
import android.view.DisplayInfo;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.wrapper.DisplayUtilsWrapper;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConfigurationRepositoryImpl implements ConfigurationRepository {
    public final ConfigurationController configurationController;
    public final Flow configurationValues;
    public final Context context;
    public final DisplayUtilsWrapper displayUtils;
    public final Flow onConfigurationChange;
    public final ReadonlyStateFlow scaleForResolution;
    public final StateFlowImpl displayInfo = StateFlowKt.MutableStateFlow(new DisplayInfo());
    public final Flow onAnyConfigurationChange = FlowConflatedKt.conflatedCallbackFlow(new ConfigurationRepositoryImpl$onAnyConfigurationChange$1(this, null));

    public ConfigurationRepositoryImpl(ConfigurationController configurationController, Context context, CoroutineScope coroutineScope, DisplayUtilsWrapper displayUtilsWrapper) {
        this.configurationController = configurationController;
        this.context = context;
        Flow conflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new ConfigurationRepositoryImpl$onConfigurationChange$1(this, null));
        this.onConfigurationChange = conflatedCallbackFlow;
        this.configurationValues = FlowConflatedKt.conflatedCallbackFlow(new ConfigurationRepositoryImpl$configurationValues$1(this, null));
        this.scaleForResolution = FlowKt.stateIn(FlowKt.distinctUntilChanged(FlowKt.mapLatest(new ConfigurationRepositoryImpl$scaleForResolution$1(this, null), conflatedCallbackFlow)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Float.valueOf(getResolutionScale()));
    }

    public final float getResolutionScale() {
        Display display = this.context.getDisplay();
        StateFlowImpl stateFlowImpl = this.displayInfo;
        if (display != null) {
            display.getDisplayInfo((DisplayInfo) stateFlowImpl.getValue());
        }
        Display.Mode maximumResolutionDisplayMode = DisplayUtils.getMaximumResolutionDisplayMode(((DisplayInfo) stateFlowImpl.getValue()).supportedModes);
        if (maximumResolutionDisplayMode == null) {
            return 1.0f;
        }
        float physicalPixelDisplaySizeRatio = DisplayUtils.getPhysicalPixelDisplaySizeRatio(maximumResolutionDisplayMode.getPhysicalWidth(), maximumResolutionDisplayMode.getPhysicalHeight(), ((DisplayInfo) stateFlowImpl.getValue()).getNaturalWidth(), ((DisplayInfo) stateFlowImpl.getValue()).getNaturalHeight());
        if (physicalPixelDisplaySizeRatio == Float.POSITIVE_INFINITY) {
            return 1.0f;
        }
        return physicalPixelDisplaySizeRatio;
    }
}
