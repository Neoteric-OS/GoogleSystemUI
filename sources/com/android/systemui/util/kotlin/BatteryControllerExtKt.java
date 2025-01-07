package com.android.systemui.util.kotlin;

import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BatteryControllerExtKt {
    public static final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 getBatteryLevel(BatteryController batteryController) {
        return new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new BatteryControllerExtKt$getBatteryLevel$2(2, null), FlowConflatedKt.conflatedCallbackFlow(new BatteryControllerExtKt$getBatteryLevel$1(batteryController, null)));
    }

    public static final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 isBatteryPowerSaveEnabled(BatteryController batteryController) {
        return new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new BatteryControllerExtKt$isBatteryPowerSaveEnabled$2(batteryController, null), FlowConflatedKt.conflatedCallbackFlow(new BatteryControllerExtKt$isBatteryPowerSaveEnabled$1(batteryController, null)));
    }

    public static final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 isDevicePluggedIn(BatteryController batteryController) {
        return new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new BatteryControllerExtKt$isDevicePluggedIn$2(batteryController, null), FlowConflatedKt.conflatedCallbackFlow(new BatteryControllerExtKt$isDevicePluggedIn$1(batteryController, null)));
    }

    public static final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 isExtremePowerSaverEnabled(BatteryController batteryController) {
        return new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new BatteryControllerExtKt$isExtremePowerSaverEnabled$2(batteryController, null), FlowConflatedKt.conflatedCallbackFlow(new BatteryControllerExtKt$isExtremePowerSaverEnabled$1(batteryController, null)));
    }
}
