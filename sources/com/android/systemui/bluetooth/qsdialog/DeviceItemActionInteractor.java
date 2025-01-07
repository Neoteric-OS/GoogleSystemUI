package com.android.systemui.bluetooth.qsdialog;

import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.plugins.ActivityStarter;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceItemActionInteractor {
    public final ActivityStarter activityStarter;
    public final CoroutineDispatcher backgroundDispatcher;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final LocalBluetoothManager localBluetoothManager;
    public final BluetoothTileDialogLogger logger;
    public final UiEventLogger uiEventLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface LaunchSettingsCriteria {
        BluetoothTileDialogUiEvent getClickUiEvent(DeviceItem deviceItem);

        Object matched(boolean z, DeviceItem deviceItem, Continuation continuation);
    }

    public DeviceItemActionInteractor(ActivityStarter activityStarter, DialogTransitionAnimator dialogTransitionAnimator, LocalBluetoothManager localBluetoothManager, CoroutineDispatcher coroutineDispatcher, BluetoothTileDialogLogger bluetoothTileDialogLogger, UiEventLogger uiEventLogger) {
        this.activityStarter = activityStarter;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.backgroundDispatcher = coroutineDispatcher;
        this.logger = bluetoothTileDialogLogger;
        this.uiEventLogger = uiEventLogger;
    }
}
