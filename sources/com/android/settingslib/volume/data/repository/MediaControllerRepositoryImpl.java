package com.android.settingslib.volume.data.repository;

import android.media.session.MediaSessionManager;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothManagerExtKt;
import com.android.settingslib.media.session.MediaSessionManagerExtKt;
import com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl;
import com.android.settingslib.volume.shared.model.AudioManagerEvent;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.CallbackFlowBuilder;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaControllerRepositoryImpl {
    public final ReadonlyStateFlow activeSessions;
    public final MediaSessionManager mediaSessionManager;

    public MediaControllerRepositoryImpl(AudioManagerEventsReceiverImpl audioManagerEventsReceiverImpl, MediaSessionManager mediaSessionManager, LocalBluetoothManager localBluetoothManager, CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        Flow flow;
        this.mediaSessionManager = mediaSessionManager;
        final Flow defaultRemoteSessionChanged = MediaSessionManagerExtKt.getDefaultRemoteSessionChanged(mediaSessionManager);
        final int i = 0;
        Flow flow2 = new Flow() { // from class: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MediaControllerRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MediaControllerRepositoryImpl mediaControllerRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mediaControllerRepositoryImpl;
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
                        boolean r0 = r6 instanceof com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L48
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.media.session.MediaSession$Token r5 = (android.media.session.MediaSession.Token) r5
                        com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl r5 = r4.this$0
                        android.media.session.MediaSessionManager r5 = r5.mediaSessionManager
                        r6 = 0
                        java.util.List r5 = r5.getActiveSessions(r6)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L48
                        return r1
                    L48:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = defaultRemoteSessionChanged.collect(new AnonymousClass2(flowCollector, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((CallbackFlowBuilder) defaultRemoteSessionChanged).collect(new MediaControllerRepositoryImpl$special$$inlined$map$2$2(flowCollector, this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = ((FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1) defaultRemoteSessionChanged).collect(new MediaControllerRepositoryImpl$special$$inlined$map$3$2(flowCollector, this), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(MediaSessionManagerExtKt.getActiveMediaChanges(mediaSessionManager));
        if (localBluetoothManager != null) {
            final CallbackFlowBuilder headsetAudioModeChanges = LocalBluetoothManagerExtKt.getHeadsetAudioModeChanges(localBluetoothManager);
            final int i2 = 1;
            flow = new Flow() { // from class: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;
                    public final /* synthetic */ MediaControllerRepositoryImpl this$0;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                    public AnonymousClass2(FlowCollector flowCollector, MediaControllerRepositoryImpl mediaControllerRepositoryImpl) {
                        this.$this_unsafeFlow = flowCollector;
                        this.this$0 = mediaControllerRepositoryImpl;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj, Continuation continuation) {
                        /*
                            this = this;
                            boolean r0 = r6 instanceof com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L48
                        L27:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            android.media.session.MediaSession$Token r5 = (android.media.session.MediaSession.Token) r5
                            com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl r5 = r4.this$0
                            android.media.session.MediaSessionManager r5 = r5.mediaSessionManager
                            r6 = 0
                            java.util.List r5 = r5.getActiveSessions(r6)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L48
                            return r1
                        L48:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    switch (i2) {
                        case 0:
                            Object collect = headsetAudioModeChanges.collect(new AnonymousClass2(flowCollector, this), continuation);
                            if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        case 1:
                            Object collect2 = ((CallbackFlowBuilder) headsetAudioModeChanges).collect(new MediaControllerRepositoryImpl$special$$inlined$map$2$2(flowCollector, this), continuation);
                            if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        default:
                            Object collect3 = ((FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1) headsetAudioModeChanges).collect(new MediaControllerRepositoryImpl$special$$inlined$map$3$2(flowCollector, this), continuation);
                            if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }
            };
        } else {
            flow = EmptyFlow.INSTANCE;
        }
        final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(audioManagerEventsReceiverImpl.events, Reflection.getOrCreateKotlinClass(AudioManagerEvent.StreamDevicesChanged.class), 1);
        final int i3 = 2;
        this.activeSessions = FlowKt.stateIn(FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new MediaControllerRepositoryImpl$activeSessions$4(this, null), FlowKt.merge(flow2, flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1, flow, new Flow() { // from class: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MediaControllerRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MediaControllerRepositoryImpl mediaControllerRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mediaControllerRepositoryImpl;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L48
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.media.session.MediaSession$Token r5 = (android.media.session.MediaSession.Token) r5
                        com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl r5 = r4.this$0
                        android.media.session.MediaSessionManager r5 = r5.mediaSessionManager
                        r6 = 0
                        java.util.List r5 = r5.getActiveSessions(r6)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L48
                        return r1
                    L48:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = flowKt__TransformKt$onEach$$inlined$unsafeTransform$1.collect(new AnonymousClass2(flowCollector, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = ((CallbackFlowBuilder) flowKt__TransformKt$onEach$$inlined$unsafeTransform$1).collect(new MediaControllerRepositoryImpl$special$$inlined$map$2$2(flowCollector, this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = ((FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1) flowKt__TransformKt$onEach$$inlined$unsafeTransform$1).collect(new MediaControllerRepositoryImpl$special$$inlined$map$3$2(flowCollector, this), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        })), coroutineContext), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), EmptyList.INSTANCE);
    }
}
