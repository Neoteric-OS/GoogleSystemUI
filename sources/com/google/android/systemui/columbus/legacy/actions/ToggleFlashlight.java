package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.os.Handler;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ToggleFlashlight extends UserAction {
    public static final long FLASHLIGHT_TIMEOUT = TimeUnit.MINUTES.toMillis(5);
    public final FlashlightControllerImpl flashlightController;
    public final ToggleFlashlight$flashlightListener$1 flashlightListener;
    public final Handler handler;
    public final String tag;
    public final ToggleFlashlight$turnOffFlashlight$1 turnOffFlashlight;
    public final UiEventLogger uiEventLogger;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.google.android.systemui.columbus.legacy.actions.ToggleFlashlight$flashlightListener$1, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.android.systemui.columbus.legacy.actions.ToggleFlashlight$turnOffFlashlight$1] */
    public ToggleFlashlight(Context context, FlashlightControllerImpl flashlightControllerImpl, Handler handler, UiEventLogger uiEventLogger) {
        super(context, null);
        this.flashlightController = flashlightControllerImpl;
        this.handler = handler;
        this.uiEventLogger = uiEventLogger;
        this.tag = "ToggleFlashlight";
        ?? r2 = new FlashlightController$FlashlightListener() { // from class: com.google.android.systemui.columbus.legacy.actions.ToggleFlashlight$flashlightListener$1
            @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
            public final void onFlashlightAvailabilityChanged(boolean z) {
                ToggleFlashlight toggleFlashlight = ToggleFlashlight.this;
                if (!z) {
                    toggleFlashlight.handler.removeCallbacks(toggleFlashlight.turnOffFlashlight);
                }
                toggleFlashlight.updateAvailable$6();
            }

            @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
            public final void onFlashlightChanged(boolean z) {
                ToggleFlashlight toggleFlashlight = ToggleFlashlight.this;
                if (!z) {
                    toggleFlashlight.handler.removeCallbacks(toggleFlashlight.turnOffFlashlight);
                }
                toggleFlashlight.updateAvailable$6();
            }

            @Override // com.android.systemui.statusbar.policy.FlashlightController$FlashlightListener
            public final void onFlashlightError() {
                ToggleFlashlight toggleFlashlight = ToggleFlashlight.this;
                toggleFlashlight.handler.removeCallbacks(toggleFlashlight.turnOffFlashlight);
                toggleFlashlight.updateAvailable$6();
            }
        };
        this.flashlightListener = r2;
        this.turnOffFlashlight = new Runnable() { // from class: com.google.android.systemui.columbus.legacy.actions.ToggleFlashlight$turnOffFlashlight$1
            @Override // java.lang.Runnable
            public final void run() {
                ToggleFlashlight.this.flashlightController.setFlashlight(false);
            }
        };
        flashlightControllerImpl.addCallback(r2);
        updateAvailable$6();
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.UserAction
    public final boolean availableOnLockscreen() {
        return true;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        Handler handler = this.handler;
        ToggleFlashlight$turnOffFlashlight$1 toggleFlashlight$turnOffFlashlight$1 = this.turnOffFlashlight;
        handler.removeCallbacks(toggleFlashlight$turnOffFlashlight$1);
        FlashlightControllerImpl flashlightControllerImpl = this.flashlightController;
        boolean isEnabled = flashlightControllerImpl.isEnabled();
        flashlightControllerImpl.setFlashlight(!isEnabled);
        if (!isEnabled) {
            handler.postDelayed(toggleFlashlight$turnOffFlashlight$1, FLASHLIGHT_TIMEOUT);
        }
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_INVOKED_FLASHLIGHT_TOGGLE);
    }

    public final void updateAvailable$6() {
        FlashlightControllerImpl flashlightControllerImpl = this.flashlightController;
        setAvailable(flashlightControllerImpl.mHasFlashlight && flashlightControllerImpl.isAvailable());
    }
}
