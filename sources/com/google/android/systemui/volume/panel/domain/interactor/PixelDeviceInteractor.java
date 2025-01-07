package com.google.android.systemui.volume.panel.domain.interactor;

import android.bluetooth.BluetoothDevice;
import android.os.ParcelUuid;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.systemui.volume.domain.interactor.AudioOutputInteractor;
import com.android.systemui.volume.domain.model.AudioOutputDevice;
import java.util.List;
import java.util.UUID;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PixelDeviceInteractor {
    public static final List MAESTRO_UUIDS = CollectionsKt__CollectionsKt.listOf(UUID.fromString("3a046f6d-24d2-7655-6534-0d7ecb759709"), UUID.fromString("099775cb-7e0d-3465-5576-d2246d6f043a"), UUID.fromString("25e97ff7-24ce-4c4c-8951-f764a708f7b5"), UUID.fromString("b5f708a7-64f7-5189-4c4c-ce24f77fe925"));
    public final ReadonlyStateFlow activeNonPixelBluetoothMediaDevice;
    public final ReadonlyStateFlow activePixelBluetoothMediaDevice;
    public final ContextScope scope;

    public PixelDeviceInteractor(ContextScope contextScope, AudioOutputInteractor audioOutputInteractor) {
        final ReadonlyStateFlow readonlyStateFlow = audioOutputInteractor.currentAudioDevice;
        final int i = 0;
        Flow flow = new Flow() { // from class: com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ PixelDeviceInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, PixelDeviceInteractor pixelDeviceInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = pixelDeviceInteractor;
                }

                /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
                
                    if (com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor.access$isPixelDevice(r4.this$0, r5) != false) goto L20;
                 */
                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1$2$1 r0 = (com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1$2$1 r0 = new com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4f
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.volume.domain.model.AudioOutputDevice r5 = (com.android.systemui.volume.domain.model.AudioOutputDevice) r5
                        boolean r6 = r5 instanceof com.android.systemui.volume.domain.model.AudioOutputDevice.Bluetooth
                        if (r6 == 0) goto L43
                        com.android.systemui.volume.domain.model.AudioOutputDevice$Bluetooth r5 = (com.android.systemui.volume.domain.model.AudioOutputDevice.Bluetooth) r5
                        com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor r6 = r4.this$0
                        boolean r6 = com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor.access$isPixelDevice(r6, r5)
                        if (r6 == 0) goto L43
                        goto L44
                    L43:
                        r5 = 0
                    L44:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4f
                        return r1
                    L4f:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        readonlyStateFlow.collect(new AnonymousClass2(flowCollector, this), continuation);
                        break;
                    default:
                        readonlyStateFlow.collect(new PixelDeviceInteractor$special$$inlined$map$2$2(flowCollector, this), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        this.activePixelBluetoothMediaDevice = FlowKt.stateIn(flow, contextScope, startedEagerly, null);
        final int i2 = 1;
        this.activeNonPixelBluetoothMediaDevice = FlowKt.stateIn(new Flow() { // from class: com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ PixelDeviceInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, PixelDeviceInteractor pixelDeviceInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = pixelDeviceInteractor;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1$2$1 r0 = (com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1$2$1 r0 = new com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4f
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.volume.domain.model.AudioOutputDevice r5 = (com.android.systemui.volume.domain.model.AudioOutputDevice) r5
                        boolean r6 = r5 instanceof com.android.systemui.volume.domain.model.AudioOutputDevice.Bluetooth
                        if (r6 == 0) goto L43
                        com.android.systemui.volume.domain.model.AudioOutputDevice$Bluetooth r5 = (com.android.systemui.volume.domain.model.AudioOutputDevice.Bluetooth) r5
                        com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor r6 = r4.this$0
                        boolean r6 = com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor.access$isPixelDevice(r6, r5)
                        if (r6 == 0) goto L43
                        goto L44
                    L43:
                        r5 = 0
                    L44:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4f
                        return r1
                    L4f:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        readonlyStateFlow.collect(new AnonymousClass2(flowCollector, this), continuation);
                        break;
                    default:
                        readonlyStateFlow.collect(new PixelDeviceInteractor$special$$inlined$map$2$2(flowCollector, this), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, contextScope, startedEagerly, null);
    }

    public static final boolean access$isPixelDevice(PixelDeviceInteractor pixelDeviceInteractor, AudioOutputDevice.Bluetooth bluetooth) {
        BluetoothDevice bluetoothDevice;
        ParcelUuid[] uuids;
        pixelDeviceInteractor.getClass();
        CachedBluetoothDevice cachedBluetoothDevice = bluetooth.cachedBluetoothDevice;
        if (cachedBluetoothDevice == null || (bluetoothDevice = cachedBluetoothDevice.mDevice) == null || (uuids = bluetoothDevice.getUuids()) == null) {
            return false;
        }
        for (ParcelUuid parcelUuid : uuids) {
            if (MAESTRO_UUIDS.contains(parcelUuid.getUuid())) {
                return true;
            }
        }
        return false;
    }
}
