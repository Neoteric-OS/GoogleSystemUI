package com.android.systemui.bluetooth.qsdialog;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.bluetooth.qsdialog.DeviceItemActionInteractor;
import com.android.systemui.keyboard.KeyboardUI;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceItemActionInteractor$onClick$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ DeviceItem $deviceItem;
    final /* synthetic */ SystemUIDialog $dialog;
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;
    final /* synthetic */ DeviceItemActionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceItemActionInteractor$onClick$2(DeviceItemActionInteractor deviceItemActionInteractor, DeviceItem deviceItem, SystemUIDialog systemUIDialog, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceItemActionInteractor;
        this.$deviceItem = deviceItem;
        this.$dialog = systemUIDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DeviceItemActionInteractor$onClick$2(this.this$0, this.$deviceItem, this.$dialog, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceItemActionInteractor$onClick$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        UiEventLogger uiEventLogger;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i != 0) {
            if (i == 1) {
                boolean z = this.Z$0;
                Object obj2 = this.L$2;
                Iterator it = (Iterator) this.L$1;
                DeviceItem deviceItem = (DeviceItem) this.L$0;
                ResultKt.throwOnFailure(obj);
                do {
                    if (!((Boolean) obj).booleanValue()) {
                        if (it.hasNext()) {
                            obj2 = it.next();
                            this.L$0 = deviceItem;
                            this.L$1 = it;
                            this.L$2 = obj2;
                            this.Z$0 = z;
                            this.label = 1;
                            obj = ((DeviceItemActionInteractor.LaunchSettingsCriteria) obj2).matched(z, deviceItem, this);
                        } else {
                            obj2 = null;
                        }
                    }
                    DeviceItemActionInteractor.LaunchSettingsCriteria launchSettingsCriteria = (DeviceItemActionInteractor.LaunchSettingsCriteria) obj2;
                    if (launchSettingsCriteria != null) {
                        UiEventLogger uiEventLogger2 = this.this$0.uiEventLogger;
                        DeviceItem deviceItem2 = this.$deviceItem;
                        this.L$0 = uiEventLogger2;
                        this.L$1 = null;
                        this.L$2 = null;
                        this.label = 2;
                        BluetoothTileDialogUiEvent clickUiEvent = launchSettingsCriteria.getClickUiEvent(deviceItem2);
                        if (clickUiEvent == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                        uiEventLogger = uiEventLogger2;
                        obj = clickUiEvent;
                    }
                } while (obj != coroutineSingletons);
                return coroutineSingletons;
            }
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            uiEventLogger = (UiEventLogger) this.L$0;
            ResultKt.throwOnFailure(obj);
            uiEventLogger.log((UiEventLogger.UiEventEnum) obj);
            DeviceItemActionInteractor deviceItemActionInteractor = this.this$0;
            BluetoothDevice bluetoothDevice = this.$deviceItem.cachedBluetoothDevice.mDevice;
            SystemUIDialog systemUIDialog = this.$dialog;
            deviceItemActionInteractor.getClass();
            Intent intent = new Intent("android.settings.BLUETOOTH_SETTINGS");
            Bundle bundle = new Bundle();
            bundle.putParcelable("BLUETOOTH_DEVICE", bluetoothDevice);
            intent.putExtra(":settings:show_fragment_args", bundle);
            intent.setFlags(32768);
            deviceItemActionInteractor.activityStarter.postStartActivityDismissingKeyguard(intent, 0, DialogTransitionAnimator.createActivityTransitionController$default(deviceItemActionInteractor.dialogTransitionAnimator, systemUIDialog));
            return unit;
        }
        ResultKt.throwOnFailure(obj);
        BluetoothTileDialogLogger bluetoothTileDialogLogger = this.this$0.logger;
        String address = this.$deviceItem.cachedBluetoothDevice.mDevice.getAddress();
        DeviceItemType deviceItemType = this.$deviceItem.type;
        LogLevel logLevel = LogLevel.DEBUG;
        BluetoothTileDialogLogger$logDeviceClick$2 bluetoothTileDialogLogger$logDeviceClick$2 = new Function1() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogLogger$logDeviceClick$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                LogMessage logMessage = (LogMessage) obj3;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("DeviceClick. address=", logMessage.getStr1(), " type=", logMessage.getStr2());
            }
        };
        LogBuffer logBuffer = bluetoothTileDialogLogger.logBuffer;
        LogMessage obtain = logBuffer.obtain("BluetoothTileDialogLog", logLevel, bluetoothTileDialogLogger$logDeviceClick$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = address;
        logMessageImpl.str2 = deviceItemType.toString();
        logBuffer.commit(obtain);
        KeyboardUI.BluetoothErrorListener bluetoothErrorListener = BluetoothUtils.sErrorListener;
        BluetoothAdapter.getDefaultAdapter();
        DeviceItem deviceItem3 = this.$deviceItem;
        CachedBluetoothDevice cachedBluetoothDevice = deviceItem3.cachedBluetoothDevice;
        DeviceItemActionInteractor deviceItemActionInteractor2 = this.this$0;
        int ordinal = deviceItem3.type.ordinal();
        if (ordinal == 0) {
            cachedBluetoothDevice.disconnect();
            deviceItemActionInteractor2.uiEventLogger.log(BluetoothTileDialogUiEvent.ACTIVE_DEVICE_DISCONNECT);
        } else if (ordinal == 1) {
            deviceItemActionInteractor2.uiEventLogger.log(BluetoothTileDialogUiEvent.AUDIO_SHARING_DEVICE_CLICKED);
        } else if (ordinal == 2) {
            deviceItemActionInteractor2.uiEventLogger.log(BluetoothTileDialogUiEvent.AVAILABLE_AUDIO_SHARING_DEVICE_CLICKED);
        } else if (ordinal == 3) {
            cachedBluetoothDevice.setActive();
            deviceItemActionInteractor2.uiEventLogger.log(BluetoothTileDialogUiEvent.CONNECTED_DEVICE_SET_ACTIVE);
        } else if (ordinal == 4) {
            cachedBluetoothDevice.disconnect();
            deviceItemActionInteractor2.uiEventLogger.log(BluetoothTileDialogUiEvent.CONNECTED_OTHER_DEVICE_DISCONNECT);
        } else if (ordinal == 5) {
            cachedBluetoothDevice.connect$1();
            deviceItemActionInteractor2.uiEventLogger.log(BluetoothTileDialogUiEvent.SAVED_DEVICE_CONNECT);
        }
        return unit;
    }
}
