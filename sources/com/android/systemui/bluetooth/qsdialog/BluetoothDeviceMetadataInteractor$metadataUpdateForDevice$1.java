package com.android.systemui.bluetooth.qsdialog;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
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
final class BluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ BluetoothDevice $bluetoothDevice;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BluetoothDeviceMetadataInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1(BluetoothDeviceMetadataInteractor bluetoothDeviceMetadataInteractor, BluetoothDevice bluetoothDevice, Continuation continuation) {
        super(2, continuation);
        this.this$0 = bluetoothDeviceMetadataInteractor;
        this.$bluetoothDevice = bluetoothDevice;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1 bluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1 = new BluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1(this.this$0, this.$bluetoothDevice, continuation);
        bluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1.L$0 = obj;
        return bluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.bluetooth.BluetoothAdapter$OnMetadataChangedListener, com.android.systemui.bluetooth.qsdialog.BluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1$metadataChangedListener$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final BluetoothDeviceMetadataInteractor bluetoothDeviceMetadataInteractor = this.this$0;
            final ?? r1 = new BluetoothAdapter.OnMetadataChangedListener() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1$metadataChangedListener$1
                public final void onMetadataChanged(BluetoothDevice bluetoothDevice, int i2, byte[] bArr) {
                    String str;
                    if (i2 != 18) {
                        switch (i2) {
                        }
                    }
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(Unit.INSTANCE);
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("BluetoothDeviceMetadataInteractor", "Failed to send onMetadataChanged - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                    BluetoothTileDialogLogger bluetoothTileDialogLogger = bluetoothDeviceMetadataInteractor.logger;
                    String address = bluetoothDevice.getAddress();
                    LogLevel logLevel = LogLevel.DEBUG;
                    BluetoothTileDialogLogger$logBatteryChanged$2 bluetoothTileDialogLogger$logBatteryChanged$2 = new Function1() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogLogger$logBatteryChanged$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            LogMessage logMessage = (LogMessage) obj2;
                            String str1 = logMessage.getStr1();
                            int int1 = logMessage.getInt1();
                            String str2 = logMessage.getStr2();
                            StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("BatteryChanged. address=", str1, " key=", int1, " value=");
                            m.append(str2);
                            return m.toString();
                        }
                    };
                    LogBuffer logBuffer = bluetoothTileDialogLogger.logBuffer;
                    LogMessage obtain = logBuffer.obtain("BluetoothTileDialogLog", logLevel, bluetoothTileDialogLogger$logBatteryChanged$2, null);
                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                    logMessageImpl.str1 = address;
                    logMessageImpl.int1 = i2;
                    if (bArr == null || (str = bArr.toString()) == null) {
                        str = "";
                    }
                    logMessageImpl.str2 = str;
                    logBuffer.commit(obtain);
                }
            };
            BluetoothAdapter bluetoothAdapter = bluetoothDeviceMetadataInteractor.bluetoothAdapter;
            if (bluetoothAdapter != 0) {
                bluetoothAdapter.addOnMetadataChangedListener(this.$bluetoothDevice, bluetoothDeviceMetadataInteractor.executor, r1);
            }
            final BluetoothDeviceMetadataInteractor bluetoothDeviceMetadataInteractor2 = this.this$0;
            final BluetoothDevice bluetoothDevice = this.$bluetoothDevice;
            Function0 function0 = new Function0() { // from class: com.android.systemui.bluetooth.qsdialog.BluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    BluetoothAdapter bluetoothAdapter2 = BluetoothDeviceMetadataInteractor.this.bluetoothAdapter;
                    if (bluetoothAdapter2 != null) {
                        bluetoothAdapter2.removeOnMetadataChangedListener(bluetoothDevice, r1);
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
