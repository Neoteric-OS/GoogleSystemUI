package com.android.systemui.volume.panel.component.mediaoutput.domain.interactor;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaOutputInteractor$special$$inlined$map$3$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ MediaOutputInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$3$2$1, reason: invalid class name */
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
            return MediaOutputInteractor$special$$inlined$map$3$2.this.emit(null, this);
        }
    }

    public MediaOutputInteractor$special$$inlined$map$3$2(FlowCollector flowCollector, MediaOutputInteractor mediaOutputInteractor) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = mediaOutputInteractor;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$3$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$3$2$1 r0 = (com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$3$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$3$2$1 r0 = new com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$3$2$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L42
            if (r2 == r7) goto L3a
            if (r2 == r6) goto L3a
            if (r2 == r5) goto L3a
            if (r2 != r4) goto L32
            kotlin.ResultKt.throwOnFailure(r10)
            goto La0
        L32:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3a:
            java.lang.Object r8 = r0.L$0
            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L95
        L42:
            kotlin.ResultKt.throwOnFailure(r10)
            com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$MediaControllers r9 = (com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor.MediaControllers) r9
            android.media.session.MediaController r10 = r9.local
            com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor r2 = r8.this$0
            kotlinx.coroutines.flow.FlowCollector r8 = r8.$this_unsafeFlow
            if (r10 == 0) goto L68
            android.media.session.PlaybackState r10 = r10.getPlaybackState()
            if (r10 == 0) goto L68
            boolean r10 = r10.isActive()
            if (r10 != r7) goto L68
            android.media.session.MediaController r9 = r9.local
            r0.L$0 = r8
            r0.label = r7
            java.lang.Object r10 = com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor.access$mediaDeviceSession(r2, r9, r0)
            if (r10 != r1) goto L95
            return r1
        L68:
            android.media.session.MediaController r10 = r9.remote
            if (r10 == 0) goto L85
            android.media.session.PlaybackState r10 = r10.getPlaybackState()
            if (r10 == 0) goto L85
            boolean r10 = r10.isActive()
            if (r10 != r7) goto L85
            android.media.session.MediaController r9 = r9.remote
            r0.L$0 = r8
            r0.label = r6
            java.lang.Object r10 = com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor.access$mediaDeviceSession(r2, r9, r0)
            if (r10 != r1) goto L95
            return r1
        L85:
            android.media.session.MediaController r9 = r9.local
            if (r9 == 0) goto L94
            r0.L$0 = r8
            r0.label = r5
            java.lang.Object r10 = com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor.access$mediaDeviceSession(r2, r9, r0)
            if (r10 != r1) goto L95
            return r1
        L94:
            r10 = r3
        L95:
            r0.L$0 = r3
            r0.label = r4
            java.lang.Object r8 = r8.emit(r10, r0)
            if (r8 != r1) goto La0
            return r1
        La0:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$3$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
