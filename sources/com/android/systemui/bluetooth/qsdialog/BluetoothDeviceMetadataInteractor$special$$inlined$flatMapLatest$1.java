package com.android.systemui.bluetooth.qsdialog;

import android.bluetooth.BluetoothDevice;
import com.android.systemui.bluetooth.qsdialog.BluetoothDeviceMetadataInteractor;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothDeviceMetadataInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ BluetoothDeviceMetadataInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothDeviceMetadataInteractor$special$$inlined$flatMapLatest$1(Continuation continuation, BluetoothDeviceMetadataInteractor bluetoothDeviceMetadataInteractor) {
        super(3, continuation);
        this.this$0 = bluetoothDeviceMetadataInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        BluetoothDeviceMetadataInteractor$special$$inlined$flatMapLatest$1 bluetoothDeviceMetadataInteractor$special$$inlined$flatMapLatest$1 = new BluetoothDeviceMetadataInteractor$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        bluetoothDeviceMetadataInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        bluetoothDeviceMetadataInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return bluetoothDeviceMetadataInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Set<BluetoothDevice> access$getBluetoothDevices = BluetoothDeviceMetadataInteractor.Companion.access$getBluetoothDevices((List) this.L$1);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(access$getBluetoothDevices, 10));
            for (BluetoothDevice bluetoothDevice : access$getBluetoothDevices) {
                BluetoothDeviceMetadataInteractor bluetoothDeviceMetadataInteractor = this.this$0;
                bluetoothDeviceMetadataInteractor.getClass();
                arrayList.add(FlowConflatedKt.conflatedCallbackFlow(new BluetoothDeviceMetadataInteractor$metadataUpdateForDevice$1(bluetoothDeviceMetadataInteractor, bluetoothDevice, null)));
            }
            ChannelLimitedFlowMerge merge = FlowKt.merge(arrayList);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, merge, this) == coroutineSingletons) {
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
