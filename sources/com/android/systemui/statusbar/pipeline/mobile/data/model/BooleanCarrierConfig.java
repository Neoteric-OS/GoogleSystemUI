package com.android.systemui.statusbar.pipeline.mobile.data.model;

import android.os.PersistableBundle;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BooleanCarrierConfig {
    public final StateFlowImpl _configValue;
    public final ReadonlyStateFlow config;
    public final String key;

    public BooleanCarrierConfig(String str, PersistableBundle persistableBundle) {
        this.key = str;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.valueOf(persistableBundle.getBoolean(str)));
        this._configValue = MutableStateFlow;
        this.config = new ReadonlyStateFlow(MutableStateFlow);
    }

    public final String toString() {
        return this.key + "=" + ((StateFlowImpl) this.config.$$delegate_0).getValue();
    }
}
