package com.android.systemui.bluetooth.qsdialog;

import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothAutoOnInteractor {
    public final BluetoothAutoOnRepository bluetoothAutoOnRepository;
    public final Flow isEnabled;

    public BluetoothAutoOnInteractor(BluetoothAutoOnRepository bluetoothAutoOnRepository) {
        this.bluetoothAutoOnRepository = bluetoothAutoOnRepository;
        this.isEnabled = bluetoothAutoOnRepository.isAutoOn;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object setEnabled(boolean r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnInteractor$setEnabled$1
            if (r0 == 0) goto L13
            r0 = r9
            com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnInteractor$setEnabled$1 r0 = (com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnInteractor$setEnabled$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnInteractor$setEnabled$1 r0 = new com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnInteractor$setEnabled$1
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 0
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L3f
            if (r2 == r6) goto L35
            if (r2 != r5) goto L2d
            kotlin.ResultKt.throwOnFailure(r9)
            goto L86
        L2d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L35:
            boolean r8 = r0.Z$0
            java.lang.Object r7 = r0.L$0
            com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnInteractor r7 = (com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnInteractor) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L5b
        L3f:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r7
            r0.Z$0 = r8
            r0.label = r6
            com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnRepository r9 = r7.bluetoothAutoOnRepository
            r9.getClass()
            com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnRepository$isAutoOnSupported$2 r2 = new com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnRepository$isAutoOnSupported$2
            r2.<init>(r9, r4)
            kotlinx.coroutines.CoroutineDispatcher r9 = r9.backgroundDispatcher
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r2, r0)
            if (r9 != r1) goto L5b
            return r1
        L5b:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 != 0) goto L6b
            java.lang.String r7 = "BluetoothAutoOnInteractor"
            java.lang.String r8 = "Trying to set toggle value while feature not available."
            android.util.Log.e(r7, r8)
            return r3
        L6b:
            com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnRepository r7 = r7.bluetoothAutoOnRepository
            r0.L$0 = r4
            r0.label = r5
            r7.getClass()
            com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnRepository$setAutoOn$2 r9 = new com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnRepository$setAutoOn$2
            r9.<init>(r7, r8, r4)
            kotlinx.coroutines.CoroutineDispatcher r7 = r7.backgroundDispatcher
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r9, r0)
            if (r7 != r1) goto L82
            goto L83
        L82:
            r7 = r3
        L83:
            if (r7 != r1) goto L86
            return r1
        L86:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bluetooth.qsdialog.BluetoothAutoOnInteractor.setEnabled(boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
