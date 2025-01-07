package com.android.systemui.statusbar.pipeline.mobile.data.model;

import android.os.PersistableBundle;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemUiCarrierConfig {
    public final ReadonlyStateFlow allowNetworkSliceIndicator;
    public boolean isUsingDefault = true;
    public final ReadonlyStateFlow shouldInflateSignalStrength;
    public final List trackedConfigs;

    public SystemUiCarrierConfig(PersistableBundle persistableBundle) {
        BooleanCarrierConfig booleanCarrierConfig = new BooleanCarrierConfig("inflate_signal_strength_bool", persistableBundle);
        this.shouldInflateSignalStrength = booleanCarrierConfig.config;
        BooleanCarrierConfig booleanCarrierConfig2 = new BooleanCarrierConfig("show_operator_name_in_statusbar_bool", persistableBundle);
        BooleanCarrierConfig booleanCarrierConfig3 = new BooleanCarrierConfig("show_5g_slice_icon_bool", persistableBundle);
        this.allowNetworkSliceIndicator = booleanCarrierConfig3.config;
        this.trackedConfigs = CollectionsKt__CollectionsKt.listOf(booleanCarrierConfig, booleanCarrierConfig2, booleanCarrierConfig3);
    }

    public final void processNewCarrierConfig(PersistableBundle persistableBundle) {
        this.isUsingDefault = false;
        for (BooleanCarrierConfig booleanCarrierConfig : this.trackedConfigs) {
            StateFlowImpl stateFlowImpl = booleanCarrierConfig._configValue;
            Boolean valueOf = Boolean.valueOf(persistableBundle.getBoolean(booleanCarrierConfig.key));
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
        }
    }

    public final String toString() {
        return CollectionsKt.joinToString$default(this.trackedConfigs, null, null, null, new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.model.SystemUiCarrierConfig$toString$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ((BooleanCarrierConfig) obj).toString();
            }
        }, 31);
    }

    public final String toStringConsideringDefaults() {
        return this.isUsingDefault ? "using defaults" : CollectionsKt.joinToString$default(this.trackedConfigs, null, null, null, new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.model.SystemUiCarrierConfig$toStringConsideringDefaults$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ((BooleanCarrierConfig) obj).toString();
            }
        }, 31);
    }

    public static /* synthetic */ void isUsingDefault$annotations() {
    }
}
