package com.android.systemui.volume.panel.data.repository;

import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.volume.panel.shared.VolumePanelLogger;
import com.android.systemui.volume.panel.shared.model.VolumePanelGlobalState;
import java.io.PrintWriter;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumePanelGlobalStateRepository implements Dumpable {
    public final ReadonlyStateFlow globalState;
    public final VolumePanelLogger logger;
    public final StateFlowImpl mutableGlobalState;

    public VolumePanelGlobalStateRepository(DumpManager dumpManager, VolumePanelLogger volumePanelLogger) {
        this.logger = volumePanelLogger;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(new VolumePanelGlobalState(false));
        this.mutableGlobalState = MutableStateFlow;
        this.globalState = new ReadonlyStateFlow(MutableStateFlow);
        dumpManager.registerNormalDumpable("VolumePanelGlobalStateRepository", this);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("isVisible: ", ((VolumePanelGlobalState) ((StateFlowImpl) this.globalState.$$delegate_0).getValue()).isVisible, printWriter);
    }
}
