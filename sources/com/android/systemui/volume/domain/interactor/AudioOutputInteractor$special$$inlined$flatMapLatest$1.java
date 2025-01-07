package com.android.systemui.volume.domain.interactor;

import com.android.settingslib.volume.data.repository.AudioRepository;
import com.android.settingslib.volume.data.repository.AudioRepositoryImpl;
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
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AudioOutputInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ AudioRepository $audioRepository$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ AudioOutputInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioOutputInteractor$special$$inlined$flatMapLatest$1(Continuation continuation, AudioRepository audioRepository, AudioOutputInteractor audioOutputInteractor) {
        super(3, continuation);
        this.$audioRepository$inlined = audioRepository;
        this.this$0 = audioOutputInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AudioOutputInteractor$special$$inlined$flatMapLatest$1 audioOutputInteractor$special$$inlined$flatMapLatest$1 = new AudioOutputInteractor$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$audioRepository$inlined, this.this$0);
        audioOutputInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        audioOutputInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return audioOutputInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow flow;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            if (((Boolean) this.L$1).booleanValue()) {
                final ReadonlyStateFlow communicationDevice = ((AudioRepositoryImpl) this.$audioRepository$inlined).getCommunicationDevice();
                final AudioOutputInteractor audioOutputInteractor = this.this$0;
                final int i2 = 0;
                flow = new Flow() { // from class: com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$1$2, reason: invalid class name */
                    public final class AnonymousClass2 implements FlowCollector {
                        public final /* synthetic */ FlowCollector $this_unsafeFlow;
                        public final /* synthetic */ AudioOutputInteractor this$0;

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$1$2$1, reason: invalid class name */
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

                        public AnonymousClass2(FlowCollector flowCollector, AudioOutputInteractor audioOutputInteractor) {
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
                        public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                            /*
                                Method dump skipped, instructions count: 266
                                To view this dump add '--comments-level debug' option
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector2, Continuation continuation) {
                        switch (i2) {
                            case 0:
                                ((ReadonlyStateFlow) communicationDevice).collect(new AnonymousClass2(flowCollector2, audioOutputInteractor), continuation);
                                return CoroutineSingletons.COROUTINE_SUSPENDED;
                            default:
                                Object collect = communicationDevice.collect(new AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2(flowCollector2, audioOutputInteractor), continuation);
                                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                        }
                    }
                };
            } else {
                final AudioOutputInteractor audioOutputInteractor2 = this.this$0;
                final Flow flow2 = audioOutputInteractor2.mediaOutputInteractor.currentConnectedDevice;
                final int i3 = 1;
                flow = new Flow() { // from class: com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$1$2, reason: invalid class name */
                    public final class AnonymousClass2 implements FlowCollector {
                        public final /* synthetic */ FlowCollector $this_unsafeFlow;
                        public final /* synthetic */ AudioOutputInteractor this$0;

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$1$2$1, reason: invalid class name */
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

                        public AnonymousClass2(FlowCollector flowCollector, AudioOutputInteractor audioOutputInteractor) {
                            this.$this_unsafeFlow = flowCollector;
                            this.this$0 = audioOutputInteractor;
                        }

                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj, Continuation continuation) {
                            /*
                                Method dump skipped, instructions count: 266
                                To view this dump add '--comments-level debug' option
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.domain.interactor.AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector2, Continuation continuation) {
                        switch (i3) {
                            case 0:
                                ((ReadonlyStateFlow) flow2).collect(new AnonymousClass2(flowCollector2, audioOutputInteractor2), continuation);
                                return CoroutineSingletons.COROUTINE_SUSPENDED;
                            default:
                                Object collect = flow2.collect(new AudioOutputInteractor$currentAudioDevice$lambda$2$$inlined$map$2$2(flowCollector2, audioOutputInteractor2), continuation);
                                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                        }
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
