package com.android.settingslib.volume.shared;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ AudioManagerEventsReceiverImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2.this.emit(null, this);
        }
    }

    public AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2(FlowCollector flowCollector, AudioManagerEventsReceiverImpl audioManagerEventsReceiverImpl) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = audioManagerEventsReceiverImpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2$1 r0 = (com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2$1 r0 = new com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L30
            if (r2 != r3) goto L28
            kotlin.ResultKt.throwOnFailure(r8)
            goto Lcc
        L28:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L30:
            kotlin.ResultKt.throwOnFailure(r8)
            android.content.Intent r7 = (android.content.Intent) r7
            com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl r8 = r6.this$0
            r8.getClass()
            java.lang.String r8 = r7.getAction()
            if (r8 == 0) goto L78
            int r2 = r8.hashCode()
            r4 = -1315844839(0xffffffffb191cd19, float:-4.2433723E-9)
            if (r2 == r4) goto L6c
            r4 = 100931828(0x60418f4, float:2.4844773E-35)
            if (r2 == r4) goto L60
            r4 = 1170999219(0x45cc07b3, float:6528.9624)
            if (r2 == r4) goto L54
            goto L78
        L54:
            java.lang.String r2 = "android.media.MASTER_MUTE_CHANGED_ACTION"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L5d
            goto L78
        L5d:
            com.android.settingslib.volume.shared.model.AudioManagerEvent$StreamMasterMuteChanged r7 = com.android.settingslib.volume.shared.model.AudioManagerEvent.StreamMasterMuteChanged.INSTANCE
            goto Lbf
        L60:
            java.lang.String r2 = "android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L69
            goto L78
        L69:
            com.android.settingslib.volume.shared.model.AudioManagerEvent$InternalRingerModeChanged r7 = com.android.settingslib.volume.shared.model.AudioManagerEvent.InternalRingerModeChanged.INSTANCE
            goto Lbf
        L6c:
            java.lang.String r2 = "android.media.STREAM_DEVICES_CHANGED_ACTION"
            boolean r8 = r8.equals(r2)
            if (r8 != 0) goto L75
            goto L78
        L75:
            com.android.settingslib.volume.shared.model.AudioManagerEvent$StreamDevicesChanged r7 = com.android.settingslib.volume.shared.model.AudioManagerEvent.StreamDevicesChanged.INSTANCE
            goto Lbf
        L78:
            java.lang.String r8 = "android.media.EXTRA_VOLUME_STREAM_TYPE"
            r2 = -1
            int r8 = r7.getIntExtra(r8, r2)
            r4 = 0
            if (r8 != r2) goto L8b
            java.lang.String r7 = "AudioManagerIntentsReceiver"
            java.lang.String r8 = "Intent doesn't have AudioManager.EXTRA_VOLUME_STREAM_TYPE extra"
            android.util.Log.e(r7, r8)
        L89:
            r7 = r4
            goto Lbf
        L8b:
            com.android.settingslib.volume.shared.model.AudioStream.m772constructorimpl(r8)
            java.lang.String r7 = r7.getAction()
            if (r7 == 0) goto L89
            int r2 = r7.hashCode()
            r5 = -1940635523(0xffffffff8c54407d, float:-1.6351292E-31)
            if (r2 == r5) goto Lb1
            r5 = 1920758225(0x727c71d1, float:5.0001804E30)
            if (r2 == r5) goto La3
            goto L89
        La3:
            java.lang.String r2 = "android.media.STREAM_MUTE_CHANGED_ACTION"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L89
            com.android.settingslib.volume.shared.model.AudioManagerEvent$StreamMuteChanged r7 = new com.android.settingslib.volume.shared.model.AudioManagerEvent$StreamMuteChanged
            r7.<init>(r8)
            goto Lbf
        Lb1:
            java.lang.String r2 = "android.media.VOLUME_CHANGED_ACTION"
            boolean r7 = r7.equals(r2)
            if (r7 != 0) goto Lba
            goto L89
        Lba:
            com.android.settingslib.volume.shared.model.AudioManagerEvent$StreamVolumeChanged r7 = new com.android.settingslib.volume.shared.model.AudioManagerEvent$StreamVolumeChanged
            r7.<init>(r8)
        Lbf:
            if (r7 == 0) goto Lcc
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r6 = r6.$this_unsafeFlow
            java.lang.Object r6 = r6.emit(r7, r0)
            if (r6 != r1) goto Lcc
            return r1
        Lcc:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
