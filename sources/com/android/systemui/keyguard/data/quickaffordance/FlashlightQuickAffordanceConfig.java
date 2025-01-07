package com.android.systemui.keyguard.data.quickaffordance;

import android.content.Context;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.android.wm.shell.R;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlashlightQuickAffordanceConfig implements KeyguardQuickAffordanceConfig {
    public final Context context;
    public final FlashlightControllerImpl flashlightController;
    public final Flow lockScreenState = FlowConflatedKt.conflatedCallbackFlow(new FlashlightQuickAffordanceConfig$lockScreenState$1(this, null));

    public FlashlightQuickAffordanceConfig(Context context, FlashlightControllerImpl flashlightControllerImpl) {
        this.context = context;
        this.flashlightController = flashlightControllerImpl;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String getKey() {
        return "flashlight";
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Flow getLockScreenState() {
        return this.lockScreenState;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final int getPickerIconResourceId() {
        return R.drawable.ic_flashlight_off;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Object getPickerScreenState(Continuation continuation) {
        return this.flashlightController.isAvailable() ? new KeyguardQuickAffordanceConfig.PickerScreenState.Default(null) : KeyguardQuickAffordanceConfig.PickerScreenState.UnavailableOnDevice.INSTANCE;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final KeyguardQuickAffordanceConfig.OnTriggeredResult onTriggered(Expandable$Companion$fromView$1 expandable$Companion$fromView$1) {
        FlashlightControllerImpl flashlightControllerImpl = this.flashlightController;
        flashlightControllerImpl.setFlashlight(flashlightControllerImpl.isAvailable() && !flashlightControllerImpl.isEnabled());
        return KeyguardQuickAffordanceConfig.OnTriggeredResult.Handled.INSTANCE;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String pickerName() {
        return this.context.getString(R.string.quick_settings_flashlight_label);
    }
}
