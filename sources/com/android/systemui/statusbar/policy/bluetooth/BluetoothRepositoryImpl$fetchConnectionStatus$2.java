package com.android.systemui.statusbar.policy.bluetooth;

import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BluetoothRepositoryImpl$fetchConnectionStatus$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Collection $currentDevices;
    int label;
    final /* synthetic */ BluetoothRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothRepositoryImpl$fetchConnectionStatus$2(BluetoothRepositoryImpl bluetoothRepositoryImpl, Collection collection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = bluetoothRepositoryImpl;
        this.$currentDevices = collection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BluetoothRepositoryImpl$fetchConnectionStatus$2(this.this$0, this.$currentDevices, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothRepositoryImpl$fetchConnectionStatus$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LocalBluetoothAdapter localBluetoothAdapter;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        LocalBluetoothManager localBluetoothManager = this.this$0.localBluetoothManager;
        int connectionState = (localBluetoothManager == null || (localBluetoothAdapter = localBluetoothManager.mLocalAdapter) == null) ? 0 : localBluetoothAdapter.mAdapter.getConnectionState();
        if (!this.$currentDevices.isEmpty()) {
            Iterator it = this.$currentDevices.iterator();
            if (!it.hasNext()) {
                throw new NoSuchElementException();
            }
            int maxConnectionState = ((CachedBluetoothDevice) it.next()).getMaxConnectionState();
            while (it.hasNext()) {
                int maxConnectionState2 = ((CachedBluetoothDevice) it.next()).getMaxConnectionState();
                if (maxConnectionState < maxConnectionState2) {
                    maxConnectionState = maxConnectionState2;
                }
            }
            if (maxConnectionState >= connectionState) {
                connectionState = maxConnectionState;
            }
        }
        Collection collection = this.$currentDevices;
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : collection) {
            if (((CachedBluetoothDevice) obj2).isConnected()) {
                arrayList.add(obj2);
            }
        }
        return new ConnectionStatusModel((arrayList.isEmpty() && connectionState == 2) ? 0 : connectionState, arrayList);
    }
}
