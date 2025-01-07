package com.android.systemui.media.controls.domain.pipeline.interactor;

import android.content.Context;
import android.content.Intent;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.media.controls.data.repository.MediaFilterRepository;
import com.android.systemui.media.controls.domain.pipeline.MediaDataProcessor;
import com.android.systemui.plugins.ActivityStarter;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaRecommendationsInteractor {
    public static final Intent SETTINGS_INTENT = new Intent("android.settings.ACTION_MEDIA_CONTROLS_SETTINGS");
    public final ActivityStarter activityStarter;
    public final Context applicationContext;
    public final BroadcastSender broadcastSender;
    public final MediaDataProcessor mediaDataProcessor;
    public final Flow onAnyMediaConfigurationChange;
    public final Flow recommendations;
    public final MediaFilterRepository repository;

    public MediaRecommendationsInteractor(CoroutineScope coroutineScope, Context context, MediaFilterRepository mediaFilterRepository, MediaDataProcessor mediaDataProcessor, BroadcastSender broadcastSender, ActivityStarter activityStarter) {
        this.applicationContext = context;
        this.repository = mediaFilterRepository;
        this.mediaDataProcessor = mediaDataProcessor;
        this.broadcastSender = broadcastSender;
        this.activityStarter = activityStarter;
        final ReadonlyStateFlow readonlyStateFlow = mediaFilterRepository.smartspaceMediaData;
        this.recommendations = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MediaRecommendationsInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MediaRecommendationsInteractor mediaRecommendationsInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mediaRecommendationsInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r14, kotlin.coroutines.Continuation r15) {
                    /*
                        r13 = this;
                        boolean r0 = r15 instanceof com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r15
                        com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r15)
                    L18:
                        java.lang.Object r15 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r15)
                        goto L99
                    L27:
                        java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                        java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
                        r13.<init>(r14)
                        throw r13
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r15)
                        com.android.systemui.media.controls.shared.model.SmartspaceMediaData r14 = (com.android.systemui.media.controls.shared.model.SmartspaceMediaData) r14
                        android.content.Intent r15 = com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor.SETTINGS_INTENT
                        com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor r15 = r13.this$0
                        r15.getClass()
                        java.util.ArrayList r12 = new java.util.ArrayList
                        r12.<init>()
                        java.util.List r2 = r14.recommendations
                        java.util.Iterator r2 = r2.iterator()
                    L46:
                        boolean r4 = r2.hasNext()
                        if (r4 == 0) goto L70
                        java.lang.Object r4 = r2.next()
                        android.app.smartspace.SmartspaceAction r4 = (android.app.smartspace.SmartspaceAction) r4
                        com.android.systemui.media.controls.shared.model.MediaRecModel r11 = new com.android.systemui.media.controls.shared.model.MediaRecModel
                        android.content.Intent r6 = r4.getIntent()
                        java.lang.CharSequence r7 = r4.getTitle()
                        java.lang.CharSequence r8 = r4.getSubtitle()
                        android.graphics.drawable.Icon r9 = r4.getIcon()
                        android.os.Bundle r10 = r4.getExtras()
                        r5 = r11
                        r5.<init>(r6, r7, r8, r9, r10)
                        r12.add(r11)
                        goto L46
                    L70:
                        com.android.systemui.media.controls.shared.model.MediaRecommendationsModel r2 = new com.android.systemui.media.controls.shared.model.MediaRecommendationsModel
                        android.content.Context r4 = r15.applicationContext
                        int r6 = r14.getUid(r4)
                        com.android.internal.logging.InstanceId r8 = r14.instanceId
                        android.content.Context r15 = r15.applicationContext
                        java.lang.CharSequence r9 = r14.getAppName(r15)
                        android.content.Intent r10 = r14.dismissIntent
                        boolean r11 = r14.isValid()
                        java.lang.String r5 = r14.targetId
                        java.lang.String r7 = r14.packageName
                        r4 = r2
                        r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r13 = r13.$this_unsafeFlow
                        java.lang.Object r13 = r13.emit(r2, r0)
                        if (r13 != r1) goto L99
                        return r1
                    L99:
                        kotlin.Unit r13 = kotlin.Unit.INSTANCE
                        return r13
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        });
        FlowKt.stateIn(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$2$2$1 r0 = (com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$2$2$1 r0 = new com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$2$2$1
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
                        com.android.systemui.media.controls.shared.model.SmartspaceMediaData r5 = (com.android.systemui.media.controls.shared.model.SmartspaceMediaData) r5
                        boolean r5 = r5.isActive
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.domain.pipeline.interactor.MediaRecommendationsInteractor$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
        this.onAnyMediaConfigurationChange = mediaFilterRepository.onAnyMediaConfigurationChange;
    }
}
