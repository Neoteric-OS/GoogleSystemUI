package com.android.systemui.keyguard.data.repository;

import android.content.Context;
import com.android.keyguard.ClockEventController;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.keyguard.shared.model.ClockSize;
import com.android.systemui.keyguard.shared.model.ClockSizeSetting;
import com.android.systemui.shared.clocks.ClockRegistry;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SettingsProxyExt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardClockRepositoryImpl {
    public final StateFlowImpl _clockSize;
    public final CoroutineDispatcher backgroundDispatcher;
    public final ClockEventController clockEventController;
    public final ClockRegistry clockRegistry;
    public final ReadonlyStateFlow clockSize;
    public final ReadonlyStateFlow currentClock;
    public final KeyguardClockRepositoryImpl$special$$inlined$mapNotNull$1 currentClockId;
    public final FeatureFlagsClassic featureFlags;
    public final KeyguardClockRepositoryImpl$special$$inlined$map$2 previewClock;
    public final SecureSettings secureSettings;
    public final ReadonlyStateFlow selectedClockSize;

    public KeyguardClockRepositoryImpl(SecureSettings secureSettings, ClockRegistry clockRegistry, ClockEventController clockEventController, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, Context context, FeatureFlagsClassic featureFlagsClassic) {
        this.secureSettings = secureSettings;
        this.clockRegistry = clockRegistry;
        this.clockEventController = clockEventController;
        this.backgroundDispatcher = coroutineDispatcher;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(ClockSize.LARGE);
        this._clockSize = MutableStateFlow;
        this.clockSize = new ReadonlyStateFlow(MutableStateFlow);
        this.selectedClockSize = FlowKt.stateIn(new KeyguardClockRepositoryImpl$special$$inlined$map$2(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new KeyguardClockRepositoryImpl$selectedClockSize$1(2, null), SettingsProxyExt.observerFlow(secureSettings, -1, "lockscreen_use_double_line_clock")), this, 2), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), getClockSize());
        KeyguardClockRepositoryImpl$special$$inlined$mapNotNull$1 keyguardClockRepositoryImpl$special$$inlined$mapNotNull$1 = new KeyguardClockRepositoryImpl$special$$inlined$mapNotNull$1(FlowKt.callbackFlow(new KeyguardClockRepositoryImpl$currentClockId$1(this, null)));
        this.currentClockId = keyguardClockRepositoryImpl$special$$inlined$mapNotNull$1;
        this.currentClock = FlowKt.stateIn(new KeyguardClockRepositoryImpl$special$$inlined$map$2(keyguardClockRepositoryImpl$special$$inlined$mapNotNull$1, this, 0), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), clockRegistry.createCurrentClock());
        this.previewClock = new KeyguardClockRepositoryImpl$special$$inlined$map$2(keyguardClockRepositoryImpl$special$$inlined$mapNotNull$1, this, 1);
    }

    public final ClockSizeSetting getClockSize() {
        ClockSizeSetting.Companion companion = ClockSizeSetting.Companion;
        int intForUser = this.secureSettings.getIntForUser("lockscreen_use_double_line_clock", 1, -2);
        companion.getClass();
        for (ClockSizeSetting clockSizeSetting : ClockSizeSetting.values()) {
            if (clockSizeSetting.getSettingValue() == intForUser) {
                return clockSizeSetting;
            }
        }
        ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Unrecognized clock setting value: ", ClockSizeSetting.TAG, intForUser);
        return ClockSizeSetting.DYNAMIC;
    }
}
