package com.android.systemui.volume.panel.component.mediaoutput.domain.interactor;

import android.media.session.MediaController;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaOutputInteractor$special$$inlined$map$1 implements Flow {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Flow $this_unsafeTransform$inlined;
    public final /* synthetic */ Object this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$1$2, reason: invalid class name */
    public final class AnonymousClass2 implements FlowCollector {
        public final /* synthetic */ FlowCollector $this_unsafeFlow;
        public final /* synthetic */ MediaOutputInteractor this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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

        public AnonymousClass2(FlowCollector flowCollector, MediaOutputInteractor mediaOutputInteractor) {
            this.$this_unsafeFlow = flowCollector;
            this.this$0 = mediaOutputInteractor;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r11, kotlin.coroutines.Continuation r12) {
            /*
                r10 = this;
                boolean r0 = r12 instanceof com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r12
                com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$1$2$1
                r0.<init>(r12)
            L18:
                java.lang.Object r12 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L30
                if (r2 != r3) goto L28
                kotlin.ResultKt.throwOnFailure(r12)
                goto Lac
            L28:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r11)
                throw r10
            L30:
                kotlin.ResultKt.throwOnFailure(r12)
                java.util.List r11 = (java.util.List) r11
                com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor r12 = r10.this$0
                r12.getClass()
                java.util.LinkedHashSet r12 = new java.util.LinkedHashSet
                r12.<init>()
                java.util.Iterator r11 = r11.iterator()
                r2 = 0
                r4 = r2
                r5 = r4
            L46:
                boolean r6 = r11.hasNext()
                if (r6 == 0) goto L9c
                java.lang.Object r6 = r11.next()
                android.media.session.MediaController r6 = (android.media.session.MediaController) r6
                android.media.session.MediaController$PlaybackInfo r7 = r6.getPlaybackInfo()
                if (r7 != 0) goto L59
                goto L46
            L59:
                int r7 = r7.getPlaybackType()
                if (r7 == r3) goto L8d
                r8 = 2
                if (r7 == r8) goto L63
                goto L46
            L63:
                if (r4 == 0) goto L6a
                java.lang.String r7 = r4.getPackageName()
                goto L6b
            L6a:
                r7 = r2
            L6b:
                java.lang.String r8 = r6.getPackageName()
                r9 = 0
                boolean r7 = kotlin.text.StringsKt__StringsJVMKt.equals(r7, r8, r9)
                if (r7 == 0) goto L77
                r4 = r2
            L77:
                java.lang.String r7 = r6.getPackageName()
                boolean r7 = r12.contains(r7)
                if (r7 != 0) goto L46
                java.lang.String r7 = r6.getPackageName()
                r12.add(r7)
                android.media.session.MediaController r5 = com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor.chooseController(r5, r6)
                goto L46
            L8d:
                java.lang.String r7 = r6.getPackageName()
                boolean r7 = r12.contains(r7)
                if (r7 != 0) goto L46
                android.media.session.MediaController r4 = com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor.chooseController(r4, r6)
                goto L46
            L9c:
                com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$MediaControllers r11 = new com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$MediaControllers
                r11.<init>(r4, r5)
                r0.label = r3
                kotlinx.coroutines.flow.FlowCollector r10 = r10.$this_unsafeFlow
                java.lang.Object r10 = r10.emit(r11, r0)
                if (r10 != r1) goto Lac
                return r1
            Lac:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public /* synthetic */ MediaOutputInteractor$special$$inlined$map$1(Flow flow, Object obj, int i) {
        this.$r8$classId = i;
        this.$this_unsafeTransform$inlined = flow;
        this.this$0 = obj;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        switch (this.$r8$classId) {
            case 0:
                Object collect = ((ChannelFlowTransformLatest) this.$this_unsafeTransform$inlined).collect(new AnonymousClass2(flowCollector, (MediaOutputInteractor) this.this$0), continuation);
                if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            case 1:
                Object collect2 = ((ChannelLimitedFlowMerge) this.$this_unsafeTransform$inlined).collect(new MediaOutputInteractor$activeMediaControllers$lambda$2$$inlined$map$1$2(flowCollector, (List) this.this$0), continuation);
                if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
            default:
                Object collect3 = this.$this_unsafeTransform$inlined.collect(new MediaOutputInteractor$stateChanges$$inlined$map$1$2(flowCollector, (MediaController) this.this$0), continuation);
                if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    break;
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
