package com.android.systemui.statusbar.chips.mediaprojection.domain.interactor;

import android.content.pm.PackageManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository;
import com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaProjectionChipInteractor {
    public final LogBuffer logger;
    public final MediaProjectionManagerRepository mediaProjectionRepository;
    public final PackageManager packageManager;
    public final ReadonlyStateFlow projection;
    public final CoroutineScope scope;

    public MediaProjectionChipInteractor(CoroutineScope coroutineScope, MediaProjectionManagerRepository mediaProjectionManagerRepository, PackageManager packageManager, LogBuffer logBuffer) {
        this.scope = coroutineScope;
        this.mediaProjectionRepository = mediaProjectionManagerRepository;
        this.packageManager = packageManager;
        this.logger = logBuffer;
        final ReadonlyStateFlow readonlyStateFlow = mediaProjectionManagerRepository.mediaProjectionState;
        this.projection = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MediaProjectionChipInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MediaProjectionChipInteractor mediaProjectionChipInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mediaProjectionChipInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                    /*
                        r8 = this;
                        boolean r0 = r10 instanceof com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r10
                        com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r10)
                    L18:
                        java.lang.Object r10 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r10)
                        goto L97
                    L27:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r9)
                        throw r8
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r10)
                        com.android.systemui.mediaprojection.data.model.MediaProjectionState r9 = (com.android.systemui.mediaprojection.data.model.MediaProjectionState) r9
                        boolean r10 = r9 instanceof com.android.systemui.mediaprojection.data.model.MediaProjectionState.NotProjecting
                        r2 = 0
                        java.lang.String r4 = "MediaProjection"
                        com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor r5 = r8.this$0
                        if (r10 == 0) goto L4d
                        com.android.systemui.log.LogBuffer r9 = r5.logger
                        com.android.systemui.log.core.LogLevel r10 = com.android.systemui.log.core.LogLevel.INFO
                        com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$projection$1$2 r5 = com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$projection$1$2.INSTANCE
                        com.android.systemui.log.core.LogMessage r10 = r9.obtain(r4, r10, r5, r2)
                        r9.commit(r10)
                        com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel$NotProjecting r9 = com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel.NotProjecting.INSTANCE
                        goto L8c
                    L4d:
                        boolean r10 = r9 instanceof com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting
                        if (r10 == 0) goto L9a
                        android.content.pm.PackageManager r10 = r5.packageManager
                        com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting r9 = (com.android.systemui.mediaprojection.data.model.MediaProjectionState.Projecting) r9
                        java.lang.String r6 = r9.getHostPackage()
                        boolean r10 = com.android.systemui.util.Utils.isHeadlessRemoteDisplayProvider(r10, r6)
                        if (r10 == 0) goto L62
                        com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel$Type r10 = com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel.Type.CAST_TO_OTHER_DEVICE
                        goto L64
                    L62:
                        com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel$Type r10 = com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel.Type.SHARE_TO_APP
                    L64:
                        com.android.systemui.log.core.LogLevel r6 = com.android.systemui.log.core.LogLevel.INFO
                        com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$projection$1$4 r7 = com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$projection$1$4.INSTANCE
                        com.android.systemui.log.LogBuffer r5 = r5.logger
                        com.android.systemui.log.core.LogMessage r2 = r5.obtain(r4, r6, r7, r2)
                        java.lang.String r4 = r10.name()
                        r6 = r2
                        com.android.systemui.log.LogMessageImpl r6 = (com.android.systemui.log.LogMessageImpl) r6
                        r6.str1 = r4
                        java.lang.String r4 = r9.getHostPackage()
                        r6.str2 = r4
                        java.lang.String r4 = r9.getHostDeviceName()
                        r6.str3 = r4
                        r5.commit(r2)
                        com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel$Projecting r2 = new com.android.systemui.statusbar.chips.mediaprojection.domain.model.ProjectionChipModel$Projecting
                        r2.<init>(r10, r9)
                        r9 = r2
                    L8c:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r8 = r8.$this_unsafeFlow
                        java.lang.Object r8 = r8.emit(r9, r0)
                        if (r8 != r1) goto L97
                        return r1
                    L97:
                        kotlin.Unit r8 = kotlin.Unit.INSTANCE
                        return r8
                    L9a:
                        kotlin.NoWhenBranchMatchedException r8 = new kotlin.NoWhenBranchMatchedException
                        r8.<init>()
                        throw r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.chips.mediaprojection.domain.interactor.MediaProjectionChipInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ProjectionChipModel.NotProjecting.INSTANCE);
    }

    public final void stopProjecting() {
        BuildersKt.launch$default(this.scope, null, null, new MediaProjectionChipInteractor$stopProjecting$1(this, null), 3);
    }
}
