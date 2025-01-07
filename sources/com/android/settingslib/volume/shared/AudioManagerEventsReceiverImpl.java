package com.android.settingslib.volume.shared;

import android.content.Context;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AudioManagerEventsReceiverImpl {
    public final Context context;
    public final ReadonlySharedFlow events;

    public AudioManagerEventsReceiverImpl(Context context, CoroutineScope coroutineScope) {
        this.context = context;
        final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(FlowKt.callbackFlow(new AudioManagerEventsReceiverImpl$events$1(this, null)));
        final int i = 0;
        final Flow flow = new Flow() { // from class: com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ AudioManagerEventsReceiverImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, AudioManagerEventsReceiverImpl audioManagerEventsReceiverImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = audioManagerEventsReceiverImpl;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r11, kotlin.coroutines.Continuation r12) {
                    /*
                        r10 = this;
                        boolean r0 = r12 instanceof com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r12
                        com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1$2$1 r0 = (com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1$2$1 r0 = new com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1$2$1
                        r0.<init>(r12)
                    L18:
                        java.lang.Object r12 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r12)
                        goto L67
                    L27:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r12)
                        r12 = r11
                        android.content.Intent r12 = (android.content.Intent) r12
                        com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl r2 = r10.this$0
                        r2.getClass()
                        java.lang.String r8 = "android.media.STREAM_DEVICES_CHANGED_ACTION"
                        java.lang.String r9 = "android.media.VOLUME_CHANGED_ACTION"
                        java.lang.String r4 = "android.media.STREAM_MUTE_CHANGED_ACTION"
                        java.lang.String r5 = "android.media.MASTER_MUTE_CHANGED_ACTION"
                        java.lang.String r6 = "android.media.VOLUME_CHANGED_ACTION"
                        java.lang.String r7 = "android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION"
                        java.lang.String[] r2 = new java.lang.String[]{r4, r5, r6, r7, r8, r9}
                        java.util.Set r2 = kotlin.collections.SetsKt.setOf(r2)
                        java.util.Collection r2 = (java.util.Collection) r2
                        java.lang.Iterable r2 = (java.lang.Iterable) r2
                        java.lang.String r12 = r12.getAction()
                        boolean r12 = kotlin.collections.CollectionsKt.contains(r2, r12)
                        if (r12 == 0) goto L67
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r10 = r10.$this_unsafeFlow
                        java.lang.Object r10 = r10.emit(r11, r0)
                        if (r10 != r1) goto L67
                        return r1
                    L67:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new AnonymousClass2(flowCollector, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((AudioManagerEventsReceiverImpl$special$$inlined$filter$1) flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1).collect(new AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2(flowCollector, this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final int i2 = 1;
        this.events = FlowKt.shareIn(new Flow() { // from class: com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ AudioManagerEventsReceiverImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, AudioManagerEventsReceiverImpl audioManagerEventsReceiverImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = audioManagerEventsReceiverImpl;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r12 instanceof com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r12
                        com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1$2$1 r0 = (com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1$2$1 r0 = new com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1$2$1
                        r0.<init>(r12)
                    L18:
                        java.lang.Object r12 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r12)
                        goto L67
                    L27:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r12)
                        r12 = r11
                        android.content.Intent r12 = (android.content.Intent) r12
                        com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl r2 = r10.this$0
                        r2.getClass()
                        java.lang.String r8 = "android.media.STREAM_DEVICES_CHANGED_ACTION"
                        java.lang.String r9 = "android.media.VOLUME_CHANGED_ACTION"
                        java.lang.String r4 = "android.media.STREAM_MUTE_CHANGED_ACTION"
                        java.lang.String r5 = "android.media.MASTER_MUTE_CHANGED_ACTION"
                        java.lang.String r6 = "android.media.VOLUME_CHANGED_ACTION"
                        java.lang.String r7 = "android.media.INTERNAL_RINGER_MODE_CHANGED_ACTION"
                        java.lang.String[] r2 = new java.lang.String[]{r4, r5, r6, r7, r8, r9}
                        java.util.Set r2 = kotlin.collections.SetsKt.setOf(r2)
                        java.util.Collection r2 = (java.util.Collection) r2
                        java.lang.Iterable r2 = (java.lang.Iterable) r2
                        java.lang.String r12 = r12.getAction()
                        boolean r12 = kotlin.collections.CollectionsKt.contains(r2, r12)
                        if (r12 == 0) goto L67
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r10 = r10.$this_unsafeFlow
                        java.lang.Object r10 = r10.emit(r11, r0)
                        if (r10 != r1) goto L67
                        return r1
                    L67:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.shared.AudioManagerEventsReceiverImpl$special$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1) flow).collect(new AnonymousClass2(flowCollector, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((AudioManagerEventsReceiverImpl$special$$inlined$filter$1) flow).collect(new AudioManagerEventsReceiverImpl$special$$inlined$mapNotNull$1$2(flowCollector, this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
    }
}
