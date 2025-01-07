package com.android.systemui.statusbar.disableflags.data.repository;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.disableflags.DisableFlagsLogger;
import com.android.systemui.statusbar.disableflags.data.model.DisableFlagsModel;
import com.android.systemui.statusbar.policy.RemoteInputQuickSettingsDisabler;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisableFlagsRepositoryImpl {
    public final ReadonlyStateFlow disableFlags;
    public final DisableFlagsLogger disableFlagsLogger;
    public final LogBuffer logBuffer;
    public final int thisDisplayId;

    public DisableFlagsRepositoryImpl(CommandQueue commandQueue, int i, CoroutineScope coroutineScope, RemoteInputQuickSettingsDisabler remoteInputQuickSettingsDisabler, LogBuffer logBuffer, DisableFlagsLogger disableFlagsLogger) {
        this.thisDisplayId = i;
        this.logBuffer = logBuffer;
        this.disableFlagsLogger = disableFlagsLogger;
        this.disableFlags = FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new DisableFlagsRepositoryImpl$disableFlags$1(commandQueue, this, remoteInputQuickSettingsDisabler, null))), new DisableFlagsRepositoryImpl$disableFlags$2(this, null), 0), coroutineScope, SharingStarted.Companion.Eagerly, new DisableFlagsModel(0, 0, false));
    }
}
