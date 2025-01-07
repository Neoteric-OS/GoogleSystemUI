package com.android.systemui.statusbar.policy;

import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ConfigurationControllerExtKt {
    public static final Flow getOnConfigChanged(ConfigurationController configurationController) {
        return FlowConflatedKt.conflatedCallbackFlow(new ConfigurationControllerExtKt$onConfigChanged$1(configurationController, null));
    }

    public static final Flow getOnDensityOrFontScaleChanged(ConfigurationController configurationController) {
        return FlowConflatedKt.conflatedCallbackFlow(new ConfigurationControllerExtKt$onDensityOrFontScaleChanged$1(configurationController, null));
    }

    public static final Flow getOnThemeChanged(ConfigurationController configurationController) {
        return FlowConflatedKt.conflatedCallbackFlow(new ConfigurationControllerExtKt$onThemeChanged$1(configurationController, null));
    }
}
