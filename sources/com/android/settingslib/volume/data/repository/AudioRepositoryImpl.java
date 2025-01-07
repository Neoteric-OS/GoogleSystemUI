package com.android.settingslib.volume.data.repository;

import android.content.ContentResolver;
import android.media.AudioManager;
import android.net.Uri;
import android.provider.Settings;
import com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl;
import com.android.settingslib.volume.shared.model.AudioManagerEvent;
import com.android.settingslib.volume.shared.model.AudioStream;
import com.android.settingslib.volume.shared.model.AudioStreamModel;
import com.android.settingslib.volume.shared.model.RingerMode;
import com.android.systemui.volume.shared.VolumeLogger;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AudioRepositoryImpl implements AudioRepository {
    public final AudioManager audioManager;
    public final AudioManagerEventsReceiverImpl audioManagerEventsReceiver;
    public final CoroutineContext backgroundCoroutineContext;
    public final ContentResolver contentResolver;
    public final CoroutineScope coroutineScope;
    public final VolumeLogger logger;
    public final ReadonlyStateFlow mode;
    public final ReadonlyStateFlow ringerMode;
    public final Map streamSettingNames;
    public final ProducingVolumeController volumeController;
    public final Flow volumeControllerEvents;

    public AudioRepositoryImpl(AudioManagerEventsReceiverImpl audioManagerEventsReceiverImpl, AudioManager audioManager, ContentResolver contentResolver, CoroutineContext coroutineContext, CoroutineScope coroutineScope, VolumeLogger volumeLogger) {
        this.audioManagerEventsReceiver = audioManagerEventsReceiverImpl;
        this.audioManager = audioManager;
        this.contentResolver = contentResolver;
        this.backgroundCoroutineContext = coroutineContext;
        this.coroutineScope = coroutineScope;
        this.logger = volumeLogger;
        ProducingVolumeController producingVolumeController = new ProducingVolumeController();
        this.volumeController = producingVolumeController;
        AudioStream.m772constructorimpl(0);
        Pair pair = new Pair(new AudioStream(0), "volume_voice");
        AudioStream.m772constructorimpl(1);
        Pair pair2 = new Pair(new AudioStream(1), "volume_system");
        AudioStream.m772constructorimpl(2);
        Pair pair3 = new Pair(new AudioStream(2), "volume_ring");
        AudioStream.m772constructorimpl(3);
        Pair pair4 = new Pair(new AudioStream(3), "volume_music");
        AudioStream.m772constructorimpl(4);
        Pair pair5 = new Pair(new AudioStream(4), "volume_alarm");
        AudioStream.m772constructorimpl(5);
        Pair pair6 = new Pair(new AudioStream(5), "volume_notification");
        AudioStream.m772constructorimpl(6);
        Pair pair7 = new Pair(new AudioStream(6), "volume_bluetooth_sco");
        AudioStream.m772constructorimpl(10);
        Pair pair8 = new Pair(new AudioStream(10), "volume_a11y");
        AudioStream.m772constructorimpl(11);
        this.streamSettingNames = MapsKt.mapOf(pair, pair2, pair3, pair4, pair5, pair6, pair7, pair8, new Pair(new AudioStream(11), "volume_assistant"));
        this.volumeControllerEvents = producingVolumeController.events;
        this.mode = FlowKt.stateIn(FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AudioRepositoryImpl$mode$2(this, null), FlowKt.callbackFlow(new AudioRepositoryImpl$mode$1(this, null))), coroutineContext), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Integer.valueOf(audioManager.getMode()));
        Flow flowOn = FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AudioRepositoryImpl$ringerMode$2(this, null), new AudioRepositoryImpl$special$$inlined$map$1(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(audioManagerEventsReceiverImpl.events, Reflection.getOrCreateKotlinClass(AudioManagerEvent.InternalRingerModeChanged.class), 1), this, 0)), coroutineContext);
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        int ringerModeInternal = audioManager.getRingerModeInternal();
        RingerMode.m773constructorimpl(ringerModeInternal);
        this.ringerMode = FlowKt.stateIn(flowOn, coroutineScope, WhileSubscribed$default, new RingerMode(ringerModeInternal));
    }

    /* renamed from: access$getCurrentAudioStream-tLTdkI8, reason: not valid java name */
    public static final AudioStreamModel m761access$getCurrentAudioStreamtLTdkI8(AudioRepositoryImpl audioRepositoryImpl, int i) {
        int streamMinVolume;
        audioRepositoryImpl.getClass();
        try {
            streamMinVolume = audioRepositoryImpl.audioManager.getStreamMinVolume(i);
        } catch (IllegalArgumentException unused) {
            streamMinVolume = audioRepositoryImpl.audioManager.getStreamMinVolume(0);
        }
        return new AudioStreamModel(i, audioRepositoryImpl.audioManager.getStreamVolume(i), streamMinVolume, audioRepositoryImpl.audioManager.getStreamMaxVolume(i), audioRepositoryImpl.audioManager.isStreamMutableByUi(i), audioRepositoryImpl.audioManager.isStreamAffectedByRingerMode(i), audioRepositoryImpl.audioManager.isStreamMute(i));
    }

    /* renamed from: getAudioStream-tLTdkI8, reason: not valid java name */
    public final Flow m762getAudioStreamtLTdkI8(final int i) {
        final ReadonlySharedFlow readonlySharedFlow = this.audioManagerEventsReceiver.events;
        Flow flow = new Flow() { // from class: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ int $audioStream$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
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

                public AnonymousClass2(FlowCollector flowCollector, int i) {
                    this.$this_unsafeFlow = flowCollector;
                    this.$audioStream$inlined = i;
                }

                /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
                
                    if (r6 == r4.$audioStream$inlined) goto L18;
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
                        boolean r0 = r6 instanceof com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStreamtLTdkI8$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$1$2$1 r0 = (com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStreamtLTdkI8$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$1$2$1 r0 = new com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L50
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        r6 = r5
                        com.android.settingslib.volume.shared.model.AudioManagerEvent r6 = (com.android.settingslib.volume.shared.model.AudioManagerEvent) r6
                        boolean r2 = r6 instanceof com.android.settingslib.volume.shared.model.StreamAudioManagerEvent
                        if (r2 == 0) goto L45
                        com.android.settingslib.volume.shared.model.StreamAudioManagerEvent r6 = (com.android.settingslib.volume.shared.model.StreamAudioManagerEvent) r6
                        int r6 = r6.mo771getAudioStream2ffMKO0()
                        java.util.Set r2 = com.android.settingslib.volume.shared.model.AudioStream.supportedStreamTypes
                        int r2 = r4.$audioStream$inlined
                        if (r6 != r2) goto L50
                    L45:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L50
                        return r1
                    L50:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStreamtLTdkI8$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ReadonlySharedFlow.this.$$delegate_0.collect(new AnonymousClass2(flowCollector, i), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        String str = (String) this.streamSettingNames.get(new AudioStream(i));
        Uri uriFor = str != null ? Settings.System.getUriFor(str) : null;
        Flow callbackFlow = uriFor == null ? EmptyFlow.INSTANCE : FlowKt.callbackFlow(new AudioRepositoryImpl$volumeSettingChanges$1(this, uriFor, null));
        final Flow flow2 = this.volumeControllerEvents;
        final Flow buffer$default = FlowKt.buffer$default(FlowKt.merge(flow, callbackFlow, new Flow() { // from class: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$2$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

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
                        boolean r0 = r6 instanceof com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStreamtLTdkI8$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$2$2$1 r0 = (com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStreamtLTdkI8$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$2$2$1 r0 = new com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$filter$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L44
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        r6 = r5
                        com.android.settingslib.volume.data.model.VolumeControllerEvent r6 = (com.android.settingslib.volume.data.model.VolumeControllerEvent) r6
                        boolean r6 = r6 instanceof com.android.settingslib.volume.data.model.VolumeControllerEvent.VolumeChanged
                        if (r6 == 0) goto L44
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L44
                        return r1
                    L44:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStreamtLTdkI8$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }), -1);
        return FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AudioRepositoryImpl$getAudioStream$4(this, i, null), new Flow() { // from class: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ int $audioStream$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ AudioRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, AudioRepositoryImpl audioRepositoryImpl, int i) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = audioRepositoryImpl;
                    this.$audioStream$inlined = i;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r4, kotlin.coroutines.Continuation r5) {
                    /*
                        r3 = this;
                        boolean r4 = r5 instanceof com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStreamtLTdkI8$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r4 == 0) goto L13
                        r4 = r5
                        com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$map$1$2$1 r4 = (com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStreamtLTdkI8$$inlined$map$1.AnonymousClass2.AnonymousClass1) r4
                        int r0 = r4.label
                        r1 = -2147483648(0xffffffff80000000, float:-0.0)
                        r2 = r0 & r1
                        if (r2 == 0) goto L13
                        int r0 = r0 - r1
                        r4.label = r0
                        goto L18
                    L13:
                        com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$map$1$2$1 r4 = new com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStream-tLTdkI8$$inlined$map$1$2$1
                        r4.<init>(r5)
                    L18:
                        java.lang.Object r5 = r4.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r1 = r4.label
                        r2 = 1
                        if (r1 == 0) goto L2f
                        if (r1 != r2) goto L27
                        kotlin.ResultKt.throwOnFailure(r5)
                        goto L45
                    L27:
                        java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
                        java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
                        r3.<init>(r4)
                        throw r3
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r5)
                        com.android.settingslib.volume.data.repository.AudioRepositoryImpl r5 = r3.this$0
                        int r1 = r3.$audioStream$inlined
                        com.android.settingslib.volume.shared.model.AudioStreamModel r5 = com.android.settingslib.volume.data.repository.AudioRepositoryImpl.m761access$getCurrentAudioStreamtLTdkI8(r5, r1)
                        r4.label = r2
                        kotlinx.coroutines.flow.FlowCollector r3 = r3.$this_unsafeFlow
                        java.lang.Object r3 = r3.emit(r5, r4)
                        if (r3 != r0) goto L45
                        return r0
                    L45:
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.data.repository.AudioRepositoryImpl$getAudioStreamtLTdkI8$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this, i), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        })), new AudioRepositoryImpl$getAudioStream$5(this, i, null), 0), this.backgroundCoroutineContext);
    }

    public final Object getBluetoothAudioDeviceCategory(String str, Continuation continuation) {
        return BuildersKt.withContext(this.backgroundCoroutineContext, new AudioRepositoryImpl$getBluetoothAudioDeviceCategory$2(this, str, null), continuation);
    }

    public final ReadonlyStateFlow getCommunicationDevice() {
        return FlowKt.stateIn(FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AudioRepositoryImpl$communicationDevice$3(this, null), new AudioRepositoryImpl$special$$inlined$map$1(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(FlowKt.callbackFlow(new AudioRepositoryImpl$communicationDevice$1(this, null))), this, 1)), this.backgroundCoroutineContext), this.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), this.audioManager.getCommunicationDevice());
    }

    /* renamed from: getLastAudibleVolume-VrMivd8, reason: not valid java name */
    public final Object m763getLastAudibleVolumeVrMivd8(int i, Continuation continuation) {
        return BuildersKt.withContext(this.backgroundCoroutineContext, new AudioRepositoryImpl$getLastAudibleVolume$2(this, i, null), continuation);
    }

    public final Object notifyVolumeControllerVisible(boolean z, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.backgroundCoroutineContext, new AudioRepositoryImpl$notifyVolumeControllerVisible$2(this, z, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }

    /* renamed from: setMuted-ZdW0WiI, reason: not valid java name */
    public final Object m764setMutedZdW0WiI(int i, boolean z, Continuation continuation) {
        return BuildersKt.withContext(this.backgroundCoroutineContext, new AudioRepositoryImpl$setMuted$2(z, this, i, null), continuation);
    }

    /* renamed from: setRingerModeInternal-2JRsiQU, reason: not valid java name */
    public final Object m765setRingerModeInternal2JRsiQU(int i, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.backgroundCoroutineContext, new AudioRepositoryImpl$setRingerModeInternal$2(this, i, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }

    /* renamed from: setVolume-ZdW0WiI, reason: not valid java name */
    public final Object m766setVolumeZdW0WiI(int i, int i2, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.backgroundCoroutineContext, new AudioRepositoryImpl$setVolume$2(this, i, i2, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}
