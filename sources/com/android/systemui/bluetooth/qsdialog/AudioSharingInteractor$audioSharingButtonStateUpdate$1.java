package com.android.systemui.bluetooth.qsdialog;

import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AudioSharingInteractor$audioSharingButtonStateUpdate$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ AudioSharingInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioSharingInteractor$audioSharingButtonStateUpdate$1(AudioSharingInteractor audioSharingInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = audioSharingInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        AudioSharingInteractor$audioSharingButtonStateUpdate$1 audioSharingInteractor$audioSharingButtonStateUpdate$1 = new AudioSharingInteractor$audioSharingButtonStateUpdate$1(this.this$0, (Continuation) obj3);
        audioSharingInteractor$audioSharingButtonStateUpdate$1.Z$0 = booleanValue;
        audioSharingInteractor$audioSharingButtonStateUpdate$1.L$0 = (List) obj2;
        return audioSharingInteractor$audioSharingButtonStateUpdate$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00df A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[LOOP:1: B:35:0x0048->B:44:?, LOOP_END, SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 0
            r1 = 1
            kotlin.coroutines.intrinsics.CoroutineSingletons r2 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r8.label
            if (r2 != 0) goto Le0
            kotlin.ResultKt.throwOnFailure(r9)
            boolean r9 = r8.Z$0
            java.lang.Object r2 = r8.L$0
            java.util.List r2 = (java.util.List) r2
            com.android.systemui.bluetooth.qsdialog.AudioSharingInteractor r8 = r8.this$0
            com.android.systemui.bluetooth.qsdialog.AudioSharingButtonState$Gone r3 = com.android.systemui.bluetooth.qsdialog.AudioSharingButtonState.Gone.INSTANCE
            if (r9 != 0) goto L1c
            r8.getClass()
            goto Ldf
        L1c:
            com.android.settingslib.bluetooth.LocalBluetoothManager r8 = r8.localBluetoothManager
            com.android.systemui.keyboard.KeyboardUI$BluetoothErrorListener r9 = com.android.settingslib.bluetooth.BluetoothUtils.sErrorListener
            if (r8 != 0) goto L23
            goto L3a
        L23:
            com.android.settingslib.bluetooth.LocalBluetoothProfileManager r9 = r8.mProfileManager
            com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast r9 = r9.mLeAudioBroadcast
            if (r9 == 0) goto L3a
            r4 = 0
            boolean r9 = r9.isEnabled(r4)
            if (r9 == 0) goto L3a
            com.android.systemui.bluetooth.qsdialog.AudioSharingButtonState$Visible r3 = new com.android.systemui.bluetooth.qsdialog.AudioSharingButtonState$Visible
            r8 = 2131953820(0x7f13089c, float:1.9544122E38)
            r3.<init>(r8, r1)
            goto Ldf
        L3a:
            if (r2 == 0) goto L44
            boolean r9 = r2.isEmpty()
            if (r9 == 0) goto L44
            goto Lb4
        L44:
            java.util.Iterator r9 = r2.iterator()
        L48:
            boolean r4 = r9.hasNext()
            if (r4 == 0) goto Lb4
            java.lang.Object r4 = r9.next()
            com.android.systemui.bluetooth.qsdialog.DeviceItem r4 = (com.android.systemui.bluetooth.qsdialog.DeviceItem) r4
            com.android.settingslib.bluetooth.CachedBluetoothDevice r4 = r4.cachedBluetoothDevice
            if (r4 != 0) goto L5a
        L58:
            r4 = r0
            goto Lb1
        L5a:
            android.bluetooth.BluetoothDevice r5 = r4.mDevice
            boolean r5 = com.android.settingslib.bluetooth.BluetoothUtils.hasConnectedBroadcastSourceForBtDevice(r5, r8)
            java.lang.String r6 = "BluetoothUtils"
            if (r5 == 0) goto L7d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "Lead device has connected broadcast source, device = "
            r5.<init>(r7)
            android.bluetooth.BluetoothDevice r4 = r4.mDevice
            java.lang.String r4 = r4.getAnonymizedAddress()
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            android.util.Log.d(r6, r4)
        L7b:
            r4 = r1
            goto Lb1
        L7d:
            java.util.Set r4 = r4.mMemberDevices
            java.util.HashSet r4 = (java.util.HashSet) r4
            java.util.Iterator r4 = r4.iterator()
        L85:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L58
            java.lang.Object r5 = r4.next()
            com.android.settingslib.bluetooth.CachedBluetoothDevice r5 = (com.android.settingslib.bluetooth.CachedBluetoothDevice) r5
            android.bluetooth.BluetoothDevice r7 = r5.mDevice
            boolean r7 = com.android.settingslib.bluetooth.BluetoothUtils.hasConnectedBroadcastSourceForBtDevice(r7, r8)
            if (r7 == 0) goto L85
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r7 = "Member device has connected broadcast source, device = "
            r4.<init>(r7)
            android.bluetooth.BluetoothDevice r5 = r5.mDevice
            java.lang.String r5 = r5.getAnonymizedAddress()
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r6, r4)
            goto L7b
        Lb1:
            if (r4 == 0) goto L48
            goto Ldf
        Lb4:
            if (r2 == 0) goto Lbd
            boolean r8 = r2.isEmpty()
            if (r8 == 0) goto Lbd
            goto Ldf
        Lbd:
            java.util.Iterator r8 = r2.iterator()
        Lc1:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto Ldf
            java.lang.Object r9 = r8.next()
            com.android.systemui.bluetooth.qsdialog.DeviceItem r9 = (com.android.systemui.bluetooth.qsdialog.DeviceItem) r9
            com.android.settingslib.bluetooth.CachedBluetoothDevice r9 = r9.cachedBluetoothDevice
            r1 = 22
            boolean r9 = r9.isActiveDevice(r1)
            if (r9 == 0) goto Lc1
            com.android.systemui.bluetooth.qsdialog.AudioSharingButtonState$Visible r3 = new com.android.systemui.bluetooth.qsdialog.AudioSharingButtonState$Visible
            r8 = 2131953818(0x7f13089a, float:1.9544118E38)
            r3.<init>(r8, r0)
        Ldf:
            return r3
        Le0:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bluetooth.qsdialog.AudioSharingInteractor$audioSharingButtonStateUpdate$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
