package com.android.systemui.bluetooth.qsdialog;

import com.android.systemui.statusbar.phone.SystemUIDialog;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BluetoothTileDialogDelegate$onDeviceItemUpdated$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ List $deviceItem;
    final /* synthetic */ SystemUIDialog $dialog;
    final /* synthetic */ boolean $showPairNewDevice;
    final /* synthetic */ boolean $showSeeAll;
    int I$0;
    long J$0;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BluetoothTileDialogDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BluetoothTileDialogDelegate$onDeviceItemUpdated$2(BluetoothTileDialogDelegate bluetoothTileDialogDelegate, List list, boolean z, boolean z2, SystemUIDialog systemUIDialog, Continuation continuation) {
        super(2, continuation);
        this.this$0 = bluetoothTileDialogDelegate;
        this.$deviceItem = list;
        this.$showSeeAll = z;
        this.$showPairNewDevice = z2;
        this.$dialog = systemUIDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BluetoothTileDialogDelegate$onDeviceItemUpdated$2 bluetoothTileDialogDelegate$onDeviceItemUpdated$2 = new BluetoothTileDialogDelegate$onDeviceItemUpdated$2(this.this$0, this.$deviceItem, this.$showSeeAll, this.$showPairNewDevice, this.$dialog, continuation);
        bluetoothTileDialogDelegate$onDeviceItemUpdated$2.L$0 = obj;
        return bluetoothTileDialogDelegate$onDeviceItemUpdated$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BluetoothTileDialogDelegate$onDeviceItemUpdated$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x006a  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogDelegate$onDeviceItemUpdated$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
