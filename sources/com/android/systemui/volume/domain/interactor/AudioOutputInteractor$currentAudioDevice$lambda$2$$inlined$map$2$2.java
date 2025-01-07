package com.android.systemui.volume.domain.interactor;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ AudioOutputInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2$1, reason: invalid class name */
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
            return AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2.this.emit(null, this);
        }
    }

    public AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2(FlowCollector flowCollector, AudioOutputInteractor audioOutputInteractor) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = audioOutputInteractor;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2$1 r0 = (com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2$1 r0 = new com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L30
            if (r2 != r3) goto L28
            kotlin.ResultKt.throwOnFailure(r7)
            goto La0
        L28:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L30:
            kotlin.ResultKt.throwOnFailure(r7)
            com.android.settingslib.media.MediaDevice r6 = (com.android.settingslib.media.MediaDevice) r6
            if (r6 == 0) goto L94
            com.android.systemui.volume.domain.interactor.AudioOutputInteractor r7 = r5.this$0
            r7.getClass()
            boolean r7 = r6 instanceof com.android.settingslib.media.BluetoothMediaDevice
            if (r7 == 0) goto L54
            com.android.systemui.volume.domain.model.AudioOutputDevice$Bluetooth r7 = new com.android.systemui.volume.domain.model.AudioOutputDevice$Bluetooth
            com.android.settingslib.media.BluetoothMediaDevice r6 = (com.android.settingslib.media.BluetoothMediaDevice) r6
            com.android.settingslib.bluetooth.CachedBluetoothDevice r2 = r6.mCachedDevice
            java.lang.String r2 = r2.getName()
            android.graphics.drawable.Drawable r4 = r6.getIcon()
            com.android.settingslib.bluetooth.CachedBluetoothDevice r6 = r6.mCachedDevice
            r7.<init>(r2, r4, r6)
            goto L95
        L54:
            int r7 = r6.getDeviceType()
            r2 = 3
            if (r7 == r2) goto L86
            int r7 = r6.getDeviceType()
            r2 = 2
            if (r7 != r2) goto L63
            goto L86
        L63:
            int r7 = r6.getDeviceType()
            r2 = 6
            if (r7 != r2) goto L78
            com.android.systemui.volume.domain.model.AudioOutputDevice$Remote r7 = new com.android.systemui.volume.domain.model.AudioOutputDevice$Remote
            java.lang.String r2 = r6.getName()
            android.graphics.drawable.Drawable r6 = r6.getIcon()
            r7.<init>(r6, r2)
            goto L95
        L78:
            com.android.systemui.volume.domain.model.AudioOutputDevice$BuiltIn r7 = new com.android.systemui.volume.domain.model.AudioOutputDevice$BuiltIn
            java.lang.String r2 = r6.getName()
            android.graphics.drawable.Drawable r6 = r6.getIcon()
            r7.<init>(r6, r2)
            goto L95
        L86:
            com.android.systemui.volume.domain.model.AudioOutputDevice$Wired r7 = new com.android.systemui.volume.domain.model.AudioOutputDevice$Wired
            java.lang.String r2 = r6.getName()
            android.graphics.drawable.Drawable r6 = r6.getIcon()
            r7.<init>(r6, r2)
            goto L95
        L94:
            r7 = 0
        L95:
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
            java.lang.Object r5 = r5.emit(r7, r0)
            if (r5 != r1) goto La0
            return r1
        La0:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
