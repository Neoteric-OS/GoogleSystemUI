package com.android.systemui.bluetooth.qsdialog;

import android.util.Log;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.BluetoothEventManager;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DeviceItemInteractor$deviceItemUpdateRequest$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceItemInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceItemInteractor$deviceItemUpdateRequest$1(DeviceItemInteractor deviceItemInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceItemInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceItemInteractor$deviceItemUpdateRequest$1 deviceItemInteractor$deviceItemUpdateRequest$1 = new DeviceItemInteractor$deviceItemUpdateRequest$1(this.this$0, continuation);
        deviceItemInteractor$deviceItemUpdateRequest$1.L$0 = obj;
        return deviceItemInteractor$deviceItemUpdateRequest$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceItemInteractor$deviceItemUpdateRequest$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.settingslib.bluetooth.BluetoothCallback, com.android.systemui.bluetooth.qsdialog.DeviceItemInteractor$deviceItemUpdateRequest$1$listener$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        BluetoothEventManager bluetoothEventManager;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final DeviceItemInteractor deviceItemInteractor = this.this$0;
            final ?? r1 = new BluetoothCallback() { // from class: com.android.systemui.bluetooth.qsdialog.DeviceItemInteractor$deviceItemUpdateRequest$1$listener$1
                @Override // com.android.settingslib.bluetooth.BluetoothCallback
                public final void onAclConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i2) {
                    if (i2 == 0) {
                        Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(Unit.INSTANCE);
                        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                            Log.e("DeviceItemInteractor", "Failed to send onAclConnectionStateChanged - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                        }
                    }
                }

                @Override // com.android.settingslib.bluetooth.BluetoothCallback
                public final void onActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice, int i2) {
                    BluetoothTileDialogLogger bluetoothTileDialogLogger = DeviceItemInteractor.this.logger;
                    String address = cachedBluetoothDevice != null ? cachedBluetoothDevice.mDevice.getAddress() : null;
                    LogLevel logLevel = LogLevel.DEBUG;
                    BluetoothTileDialogLogger$logActiveDeviceChanged$2 bluetoothTileDialogLogger$logActiveDeviceChanged$2 = new Function1() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogLogger$logActiveDeviceChanged$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            LogMessage logMessage = (LogMessage) obj2;
                            return CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("ActiveDeviceChanged. address=", logMessage.getStr1(), " profileId=", logMessage.getInt1());
                        }
                    };
                    LogBuffer logBuffer = bluetoothTileDialogLogger.logBuffer;
                    LogMessage obtain = logBuffer.obtain("BluetoothTileDialogLog", logLevel, bluetoothTileDialogLogger$logActiveDeviceChanged$2, null);
                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                    logMessageImpl.str1 = address;
                    logMessageImpl.int1 = i2;
                    logBuffer.commit(obtain);
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(Unit.INSTANCE);
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("DeviceItemInteractor", "Failed to send onActiveDeviceChanged - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }

                @Override // com.android.settingslib.bluetooth.BluetoothCallback
                public final void onProfileConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i2, int i3) {
                    BluetoothTileDialogLogger bluetoothTileDialogLogger = DeviceItemInteractor.this.logger;
                    String address = cachedBluetoothDevice.mDevice.getAddress();
                    String valueOf = String.valueOf(i2);
                    LogLevel logLevel = LogLevel.DEBUG;
                    BluetoothTileDialogLogger$logProfileConnectionStateChanged$2 bluetoothTileDialogLogger$logProfileConnectionStateChanged$2 = new Function1() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogLogger$logProfileConnectionStateChanged$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            LogMessage logMessage = (LogMessage) obj2;
                            String str1 = logMessage.getStr1();
                            String str2 = logMessage.getStr2();
                            int int1 = logMessage.getInt1();
                            StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("ProfileConnectionStateChanged. address=", str1, " state=", str2, " profileId=");
                            m.append(int1);
                            return m.toString();
                        }
                    };
                    LogBuffer logBuffer = bluetoothTileDialogLogger.logBuffer;
                    LogMessage obtain = logBuffer.obtain("BluetoothTileDialogLog", logLevel, bluetoothTileDialogLogger$logProfileConnectionStateChanged$2, null);
                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                    logMessageImpl.str1 = address;
                    logMessageImpl.str2 = valueOf;
                    logMessageImpl.int1 = i3;
                    logBuffer.commit(obtain);
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(Unit.INSTANCE);
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("DeviceItemInteractor", "Failed to send onProfileConnectionStateChanged - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
            };
            LocalBluetoothManager localBluetoothManager = deviceItemInteractor.localBluetoothManager;
            if (localBluetoothManager != null && (bluetoothEventManager = localBluetoothManager.mEventManager) != 0) {
                bluetoothEventManager.registerCallback(r1);
            }
            final DeviceItemInteractor deviceItemInteractor2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.bluetooth.qsdialog.DeviceItemInteractor$deviceItemUpdateRequest$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    BluetoothEventManager bluetoothEventManager2;
                    LocalBluetoothManager localBluetoothManager2 = DeviceItemInteractor.this.localBluetoothManager;
                    if (localBluetoothManager2 != null && (bluetoothEventManager2 = localBluetoothManager2.mEventManager) != null) {
                        bluetoothEventManager2.unregisterCallback(r1);
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
