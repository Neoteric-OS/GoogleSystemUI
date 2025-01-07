package com.android.systemui.volume.panel.component.spatial.domain.interactor;

import android.media.AudioDeviceAttributes;
import com.android.settingslib.media.domain.interactor.SpatializerInteractor;
import com.android.settingslib.volume.data.repository.AudioRepository;
import com.android.systemui.volume.domain.interactor.AudioOutputInteractor;
import com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioAvailabilityModel;
import com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SpatialAudioComponentInteractor {
    public final AudioRepository audioRepository;
    public final CoroutineContext backgroundCoroutineContext;
    public final SharedFlowImpl changes;
    public final ContextScope coroutineScope;
    public final ReadonlyStateFlow currentAudioDeviceAttributes;
    public final ReadonlyStateFlow isAvailable;
    public final ReadonlyStateFlow isEnabled;
    public final SpatializerInteractor spatializerInteractor;
    public static final AudioDeviceAttributes builtinSpeaker = new AudioDeviceAttributes(2, 2, "");
    public static final Set audioProfiles = SetsKt.setOf(2, 22, 21);

    public SpatialAudioComponentInteractor(AudioOutputInteractor audioOutputInteractor, SpatializerInteractor spatializerInteractor, AudioRepository audioRepository, CoroutineContext coroutineContext, ContextScope contextScope) {
        this.spatializerInteractor = spatializerInteractor;
        this.audioRepository = audioRepository;
        this.backgroundCoroutineContext = coroutineContext;
        SharedFlowImpl MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);
        this.changes = MutableSharedFlow$default;
        final ReadonlyStateFlow readonlyStateFlow = audioOutputInteractor.currentAudioDevice;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ SpatialAudioComponentInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, SpatialAudioComponentInteractor spatialAudioComponentInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = spatialAudioComponentInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:19:0x005c A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L3a
                        if (r2 == r4) goto L32
                        if (r2 != r3) goto L2a
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L5d
                    L2a:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L32:
                        java.lang.Object r6 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L51
                    L3a:
                        kotlin.ResultKt.throwOnFailure(r8)
                        com.android.systemui.volume.domain.model.AudioOutputDevice r7 = (com.android.systemui.volume.domain.model.AudioOutputDevice) r7
                        kotlinx.coroutines.flow.FlowCollector r8 = r6.$this_unsafeFlow
                        r0.L$0 = r8
                        r0.label = r4
                        com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor r6 = r6.this$0
                        java.lang.Object r6 = com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor.access$getAudioDeviceAttributes(r6, r7, r0)
                        if (r6 != r1) goto L4e
                        return r1
                    L4e:
                        r5 = r8
                        r8 = r6
                        r6 = r5
                    L51:
                        r7 = 0
                        r0.L$0 = r7
                        r0.label = r3
                        java.lang.Object r6 = r6.emit(r8, r0)
                        if (r6 != r1) goto L5d
                        return r1
                    L5d:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, contextScope, SharingStarted.Companion.WhileSubscribed$default(3), builtinSpeaker);
        this.currentAudioDeviceAttributes = stateIn;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(stateIn, new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SpatialAudioComponentInteractor$isAvailable$1(2, null), MutableSharedFlow$default), new SpatialAudioComponentInteractor$isAvailable$2(this, null));
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, contextScope, startedEagerly, SpatialAudioAvailabilityModel.Unavailable.INSTANCE);
        this.isAvailable = stateIn2;
        this.isEnabled = FlowKt.stateIn(FlowKt.combine(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SpatialAudioComponentInteractor$isEnabled$1(2, null), MutableSharedFlow$default), stateIn, stateIn2, new SpatialAudioComponentInteractor$isEnabled$2(this, null)), contextScope, startedEagerly, SpatialAudioEnabledModel.Unknown.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$getAudioDeviceAttributes(com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor r6, com.android.systemui.volume.domain.model.AudioOutputDevice r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r6.getClass()
            boolean r0 = r8 instanceof com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$getAudioDeviceAttributes$1
            if (r0 == 0) goto L16
            r0 = r8
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$getAudioDeviceAttributes$1 r0 = (com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$getAudioDeviceAttributes$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$getAudioDeviceAttributes$1 r0 = new com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$getAudioDeviceAttributes$1
            r0.<init>(r6, r8)
        L1b:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L71
            if (r2 == r3) goto L6d
            r6 = 2
            if (r2 != r6) goto L65
            java.lang.Object r7 = r0.L$2
            java.lang.Object r2 = r0.L$1
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r3 = r0.L$0
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor r3 = (com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor) r3
            kotlin.ResultKt.throwOnFailure(r8)
        L37:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L41
            r4 = r7
            goto L61
        L41:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L61
            java.lang.Object r7 = r2.next()
            r8 = r7
            android.media.AudioDeviceAttributes r8 = (android.media.AudioDeviceAttributes) r8
            com.android.settingslib.media.domain.interactor.SpatializerInteractor r5 = r3.spatializerInteractor
            r0.L$0 = r3
            r0.L$1 = r2
            r0.L$2 = r7
            r0.label = r6
            com.android.settingslib.media.data.repository.SpatializerRepositoryImpl r5 = r5.repository
            java.lang.Object r8 = r5.isSpatialAudioAvailableForDevice(r8, r0)
            if (r8 != r1) goto L37
            goto Lae
        L61:
            android.media.AudioDeviceAttributes r4 = (android.media.AudioDeviceAttributes) r4
        L63:
            r1 = r4
            goto Lae
        L65:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L6d:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L94
        L71:
            kotlin.ResultKt.throwOnFailure(r8)
            boolean r8 = r7 instanceof com.android.systemui.volume.domain.model.AudioOutputDevice.BuiltIn
            if (r8 == 0) goto L7c
            android.media.AudioDeviceAttributes r6 = com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor.builtinSpeaker
        L7a:
            r1 = r6
            goto Lae
        L7c:
            boolean r8 = r7 instanceof com.android.systemui.volume.domain.model.AudioOutputDevice.Bluetooth
            if (r8 == 0) goto L96
            com.android.systemui.volume.domain.model.AudioOutputDevice$Bluetooth r7 = (com.android.systemui.volume.domain.model.AudioOutputDevice.Bluetooth) r7
            com.android.settingslib.bluetooth.CachedBluetoothDevice r7 = r7.cachedBluetoothDevice
            r0.label = r3
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$getAudioDeviceAttributesByBluetoothProfile$2 r8 = new com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$getAudioDeviceAttributesByBluetoothProfile$2
            r8.<init>(r7, r6, r4)
            kotlin.coroutines.CoroutineContext r6 = r6.backgroundCoroutineContext
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r6, r8, r0)
            if (r8 != r1) goto L94
            goto Lae
        L94:
            r1 = r8
            goto Lae
        L96:
            boolean r6 = r7 instanceof com.android.systemui.volume.domain.model.AudioOutputDevice.Wired
            if (r6 == 0) goto L9b
        L9a:
            goto L63
        L9b:
            boolean r6 = r7 instanceof com.android.systemui.volume.domain.model.AudioOutputDevice.Remote
            if (r6 == 0) goto La0
            goto L9a
        La0:
            boolean r6 = r7 instanceof com.android.systemui.volume.domain.model.AudioOutputDevice.Unknown
            if (r6 == 0) goto La7
            android.media.AudioDeviceAttributes r6 = com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor.builtinSpeaker
            goto L7a
        La7:
            boolean r6 = r7 instanceof com.android.systemui.volume.domain.model.AudioOutputDevice.Unavailable
            if (r6 == 0) goto Laf
            android.media.AudioDeviceAttributes r6 = com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor.builtinSpeaker
            goto L7a
        Lae:
            return r1
        Laf:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor.access$getAudioDeviceAttributes(com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor, com.android.systemui.volume.domain.model.AudioOutputDevice, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00ad A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00ac A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object setEnabled(com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel r9, kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$setEnabled$1
            if (r0 == 0) goto L13
            r0 = r10
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$setEnabled$1 r0 = (com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$setEnabled$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$setEnabled$1 r0 = new com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor$setEnabled$1
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L53
            if (r2 == r6) goto L41
            if (r2 == r5) goto L39
            if (r2 != r4) goto L31
            kotlin.ResultKt.throwOnFailure(r10)
            goto Lad
        L31:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L39:
            java.lang.Object r8 = r0.L$0
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor r8 = (com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto La0
        L41:
            java.lang.Object r8 = r0.L$2
            android.media.AudioDeviceAttributes r8 = (android.media.AudioDeviceAttributes) r8
            java.lang.Object r9 = r0.L$1
            com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel r9 = (com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel) r9
            java.lang.Object r2 = r0.L$0
            com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor r2 = (com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor) r2
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r8
            r8 = r2
            goto L87
        L53:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.flow.ReadonlyStateFlow r10 = r8.currentAudioDeviceAttributes
            kotlinx.coroutines.flow.MutableStateFlow r10 = r10.$$delegate_0
            kotlinx.coroutines.flow.StateFlowImpl r10 = (kotlinx.coroutines.flow.StateFlowImpl) r10
            java.lang.Object r10 = r10.getValue()
            android.media.AudioDeviceAttributes r10 = (android.media.AudioDeviceAttributes) r10
            if (r10 != 0) goto L65
            return r3
        L65:
            boolean r2 = r9 instanceof com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel.SpatialAudioEnabled
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r6
            com.android.settingslib.media.domain.interactor.SpatializerInteractor r6 = r8.spatializerInteractor
            com.android.settingslib.media.data.repository.SpatializerRepositoryImpl r6 = r6.repository
            if (r2 == 0) goto L7e
            java.lang.Object r2 = r6.addSpatialAudioCompatibleDevice(r10, r0)
            if (r2 != r1) goto L7c
            goto L84
        L7c:
            r2 = r3
            goto L84
        L7e:
            java.lang.Object r2 = r6.removeSpatialAudioCompatibleDevice(r10, r0)
            if (r2 != r1) goto L7c
        L84:
            if (r2 != r1) goto L87
            return r1
        L87:
            com.android.settingslib.media.domain.interactor.SpatializerInteractor r2 = r8.spatializerInteractor
            boolean r9 = r9 instanceof com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel.HeadTrackingEnabled
            r0.L$0 = r8
            r0.L$1 = r7
            r0.L$2 = r7
            r0.label = r5
            com.android.settingslib.media.data.repository.SpatializerRepositoryImpl r2 = r2.repository
            java.lang.Object r9 = r2.setHeadTrackingEnabled(r10, r9, r0)
            if (r9 != r1) goto L9c
            goto L9d
        L9c:
            r9 = r3
        L9d:
            if (r9 != r1) goto La0
            return r1
        La0:
            kotlinx.coroutines.flow.SharedFlowImpl r8 = r8.changes
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r8 = r8.emit(r3, r0)
            if (r8 != r1) goto Lad
            return r1
        Lad:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor.setEnabled(com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
