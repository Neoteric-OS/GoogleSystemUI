package com.android.systemui.volume.panel.component.mediaoutput.domain.interactor;

import com.android.systemui.volume.panel.component.mediaoutput.shared.model.MediaDeviceSession;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaOutputComponentInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ MediaOutputComponentInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaOutputComponentInteractor$special$$inlined$flatMapLatest$1(MediaOutputComponentInteractor mediaOutputComponentInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = mediaOutputComponentInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        MediaOutputComponentInteractor$special$$inlined$flatMapLatest$1 mediaOutputComponentInteractor$special$$inlined$flatMapLatest$1 = new MediaOutputComponentInteractor$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        mediaOutputComponentInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        mediaOutputComponentInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return mediaOutputComponentInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow flow;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            final MediaDeviceSession mediaDeviceSession = (MediaDeviceSession) this.L$1;
            if (mediaDeviceSession == null) {
                flow = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(null);
            } else {
                final MediaDeviceSessionInteractor$playbackInfo$$inlined$map$1 playbackState = this.this$0.mediaDeviceSessionInteractor.playbackState(mediaDeviceSession);
                flow = new Flow() { // from class: com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputComponentInteractor$sessionWithPlaybackState$lambda$2$$inlined$mapNotNull$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputComponentInteractor$sessionWithPlaybackState$lambda$2$$inlined$mapNotNull$1$2, reason: invalid class name */
                    public final class AnonymousClass2 implements FlowCollector {
                        public final /* synthetic */ MediaDeviceSession $session$inlined;
                        public final /* synthetic */ FlowCollector $this_unsafeFlow;

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputComponentInteractor$sessionWithPlaybackState$lambda$2$$inlined$mapNotNull$1$2$1, reason: invalid class name */
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

                        public AnonymousClass2(FlowCollector flowCollector, MediaDeviceSession mediaDeviceSession) {
                            this.$this_unsafeFlow = flowCollector;
                            this.$session$inlined = mediaDeviceSession;
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
                                boolean r0 = r6 instanceof com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputComponentInteractor$sessionWithPlaybackState$lambda$2$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r6
                                com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputComponentInteractor$sessionWithPlaybackState$lambda$2$$inlined$mapNotNull$1$2$1 r0 = (com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputComponentInteractor$sessionWithPlaybackState$lambda$2$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputComponentInteractor$sessionWithPlaybackState$lambda$2$$inlined$mapNotNull$1$2$1 r0 = new com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputComponentInteractor$sessionWithPlaybackState$lambda$2$$inlined$mapNotNull$1$2$1
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
                                android.media.session.PlaybackState r5 = (android.media.session.PlaybackState) r5
                                if (r5 == 0) goto L42
                                com.android.systemui.volume.panel.component.mediaoutput.shared.model.SessionWithPlaybackState r6 = new com.android.systemui.volume.panel.component.mediaoutput.shared.model.SessionWithPlaybackState
                                com.android.systemui.volume.panel.component.mediaoutput.shared.model.MediaDeviceSession r2 = r4.$session$inlined
                                boolean r5 = r5.isActive()
                                r6.<init>(r2, r5)
                                goto L43
                            L42:
                                r6 = 0
                            L43:
                                if (r6 == 0) goto L50
                                r0.label = r3
                                kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                                java.lang.Object r4 = r4.emit(r6, r0)
                                if (r4 != r1) goto L50
                                return r1
                            L50:
                                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                                return r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputComponentInteractor$sessionWithPlaybackState$lambda$2$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector2, Continuation continuation) {
                        Object collect = MediaDeviceSessionInteractor$playbackInfo$$inlined$map$1.this.collect(new AnonymousClass2(flowCollector2, mediaDeviceSession), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                };
            }
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flow, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
