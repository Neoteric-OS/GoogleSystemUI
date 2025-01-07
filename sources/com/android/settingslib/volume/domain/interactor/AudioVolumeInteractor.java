package com.android.settingslib.volume.domain.interactor;

import com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor;
import com.android.settingslib.volume.data.repository.AudioRepository;
import com.android.settingslib.volume.data.repository.AudioRepositoryImpl;
import com.android.settingslib.volume.shared.model.AudioStream;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AudioVolumeInteractor {
    public final AudioRepository audioRepository;
    public final NotificationsSoundPolicyInteractor notificationsSoundPolicyInteractor;

    public AudioVolumeInteractor(AudioRepository audioRepository, NotificationsSoundPolicyInteractor notificationsSoundPolicyInteractor) {
        this.audioRepository = audioRepository;
        this.notificationsSoundPolicyInteractor = notificationsSoundPolicyInteractor;
    }

    /* renamed from: canChangeVolume-tLTdkI8, reason: not valid java name */
    public final Flow m767canChangeVolumetLTdkI8(int i) {
        NotificationsSoundPolicyInteractor notificationsSoundPolicyInteractor = this.notificationsSoundPolicyInteractor;
        if (i != 5) {
            final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 m758isZenMutedtLTdkI8 = notificationsSoundPolicyInteractor.m758isZenMutedtLTdkI8(i);
            final int i2 = 1;
            return new Flow() { // from class: com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1$2$1, reason: invalid class name */
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
                            boolean r0 = r6 instanceof com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolumetLTdkI8$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1$2$1 r0 = (com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolumetLTdkI8$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1$2$1 r0 = new com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L45
                        L27:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            com.android.settingslib.volume.shared.model.AudioStreamModel r5 = (com.android.settingslib.volume.shared.model.AudioStreamModel) r5
                            boolean r5 = r5.isMuted
                            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L45
                            return r1
                        L45:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolumetLTdkI8$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    switch (i2) {
                        case 0:
                            Object collect = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) m758isZenMutedtLTdkI8).collect(new AnonymousClass2(flowCollector), continuation);
                            if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        default:
                            Object collect2 = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) m758isZenMutedtLTdkI8).collect(new AudioVolumeInteractor$canChangeVolumetLTdkI8$$inlined$map$2$2(flowCollector), continuation);
                            if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }
            };
        }
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 m758isZenMutedtLTdkI82 = notificationsSoundPolicyInteractor.m758isZenMutedtLTdkI8(i);
        AudioStream.m772constructorimpl(2);
        final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 m768getAudioStreamtLTdkI8 = m768getAudioStreamtLTdkI8(2);
        final int i3 = 0;
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(m758isZenMutedtLTdkI82, new Flow() { // from class: com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolumetLTdkI8$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1$2$1 r0 = (com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolumetLTdkI8$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1$2$1 r0 = new com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolume-tLTdkI8$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L45
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.settingslib.volume.shared.model.AudioStreamModel r5 = (com.android.settingslib.volume.shared.model.AudioStreamModel) r5
                        boolean r5 = r5.isMuted
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L45
                        return r1
                    L45:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$canChangeVolumetLTdkI8$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) m768getAudioStreamtLTdkI8).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) m768getAudioStreamtLTdkI8).collect(new AudioVolumeInteractor$canChangeVolumetLTdkI8$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, new AudioVolumeInteractor$canChangeVolume$2(3, null));
    }

    /* renamed from: getAudioStream-tLTdkI8, reason: not valid java name */
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 m768getAudioStreamtLTdkI8(int i) {
        AudioRepositoryImpl audioRepositoryImpl = (AudioRepositoryImpl) this.audioRepository;
        return FlowKt.combine(audioRepositoryImpl.m762getAudioStreamtLTdkI8(i), audioRepositoryImpl.ringerMode, this.notificationsSoundPolicyInteractor.m758isZenMutedtLTdkI8(i), new AudioVolumeInteractor$getAudioStream$1(this, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00de A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* renamed from: setMuted-ZdW0WiI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m769setMutedZdW0WiI(int r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor.m769setMutedZdW0WiI(int, kotlin.coroutines.jvm.internal.ContinuationImpl, boolean):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /* renamed from: setVolume-ZdW0WiI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m770setVolumeZdW0WiI(int r11, int r12, kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$setVolume$1
            if (r0 == 0) goto L13
            r0 = r13
            com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$setVolume$1 r0 = (com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$setVolume$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$setVolume$1 r0 = new com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor$setVolume$1
            r0.<init>(r10, r13)
        L18:
            java.lang.Object r13 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L5d
            if (r2 == r7) goto L51
            if (r2 == r6) goto L3f
            if (r2 == r5) goto L3b
            if (r2 != r4) goto L33
            kotlin.ResultKt.throwOnFailure(r13)
            goto Lbb
        L33:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3b:
            kotlin.ResultKt.throwOnFailure(r13)
            goto La8
        L3f:
            int r10 = r0.I$2
            int r11 = r0.I$1
            int r12 = r0.I$0
            java.lang.Object r2 = r0.L$1
            com.android.settingslib.volume.shared.model.AudioStreamModel r2 = (com.android.settingslib.volume.shared.model.AudioStreamModel) r2
            java.lang.Object r6 = r0.L$0
            com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor r6 = (com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor) r6
            kotlin.ResultKt.throwOnFailure(r13)
            goto L96
        L51:
            int r12 = r0.I$1
            int r11 = r0.I$0
            java.lang.Object r10 = r0.L$0
            com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor r10 = (com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor) r10
            kotlin.ResultKt.throwOnFailure(r13)
            goto L73
        L5d:
            kotlin.ResultKt.throwOnFailure(r13)
            kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 r13 = r10.m768getAudioStreamtLTdkI8(r11)
            r0.L$0 = r10
            r0.I$0 = r11
            r0.I$1 = r12
            r0.label = r7
            java.lang.Object r13 = kotlinx.coroutines.flow.FlowKt.first(r13, r0)
            if (r13 != r1) goto L73
            return r1
        L73:
            r2 = r13
            com.android.settingslib.volume.shared.model.AudioStreamModel r2 = (com.android.settingslib.volume.shared.model.AudioStreamModel) r2
            int r13 = r2.volume
            if (r12 == r13) goto Lbb
            com.android.settingslib.volume.data.repository.AudioRepository r8 = r10.audioRepository
            r0.L$0 = r10
            r0.L$1 = r2
            r0.I$0 = r11
            r0.I$1 = r12
            r0.I$2 = r13
            r0.label = r6
            com.android.settingslib.volume.data.repository.AudioRepositoryImpl r8 = (com.android.settingslib.volume.data.repository.AudioRepositoryImpl) r8
            java.lang.Object r6 = r8.m766setVolumeZdW0WiI(r11, r12, r0)
            if (r6 != r1) goto L91
            return r1
        L91:
            r6 = r10
            r10 = r13
            r9 = r12
            r12 = r11
            r11 = r9
        L96:
            int r13 = r2.minVolume
            r2 = 0
            if (r11 != r13) goto La9
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r5
            java.lang.Object r10 = r6.m769setMutedZdW0WiI(r12, r0, r7)
            if (r10 != r1) goto La8
            return r1
        La8:
            return r3
        La9:
            if (r10 != r13) goto Lbb
            if (r11 <= r13) goto Lbb
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r4
            r10 = 0
            java.lang.Object r10 = r6.m769setMutedZdW0WiI(r12, r0, r10)
            if (r10 != r1) goto Lbb
            return r1
        Lbb:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.volume.domain.interactor.AudioVolumeInteractor.m770setVolumeZdW0WiI(int, int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
