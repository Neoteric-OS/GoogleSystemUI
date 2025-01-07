package com.android.systemui.biometrics.data.repository;

import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.shared.model.PromptKind;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptRepositoryImpl {
    public final StateFlowImpl _challenge;
    public final PromptRepositoryImpl$special$$inlined$map$2 _isConfirmationRequired;
    public final StateFlowImpl _opPackageName;
    public final StateFlowImpl _promptInfo;
    public final StateFlowImpl _promptKind;
    public final StateFlowImpl _requestId;
    public final StateFlowImpl _userId;
    public final AuthController authController;
    public final ReadonlyStateFlow challenge;
    public final FaceSettingsRepositoryImpl faceSettings;
    public final Flow isConfirmationRequired;
    public final ReadonlyStateFlow opPackageName;
    public final ReadonlyStateFlow promptInfo;
    public final ReadonlyStateFlow promptKind;
    public final ReadonlyStateFlow requestId;
    public final ReadonlyStateFlow userId;

    public PromptRepositoryImpl(FaceSettingsRepositoryImpl faceSettingsRepositoryImpl, AuthController authController) {
        this.faceSettings = faceSettingsRepositoryImpl;
        this.authController = authController;
        FlowConflatedKt.conflatedCallbackFlow(new PromptRepositoryImpl$isShowing$1(this, null));
        final StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._promptInfo = MutableStateFlow;
        this.promptInfo = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(null);
        this._challenge = MutableStateFlow2;
        this.challenge = new ReadonlyStateFlow(MutableStateFlow2);
        final StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(null);
        this._userId = MutableStateFlow3;
        this.userId = new ReadonlyStateFlow(MutableStateFlow3);
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(null);
        this._requestId = MutableStateFlow4;
        this.requestId = new ReadonlyStateFlow(MutableStateFlow4);
        StateFlowImpl MutableStateFlow5 = StateFlowKt.MutableStateFlow(PromptKind.None.INSTANCE);
        this._promptKind = MutableStateFlow5;
        this.promptKind = new ReadonlyStateFlow(MutableStateFlow5);
        StateFlowImpl MutableStateFlow6 = StateFlowKt.MutableStateFlow(null);
        this._opPackageName = MutableStateFlow6;
        this.opPackageName = new ReadonlyStateFlow(MutableStateFlow6);
        this.isConfirmationRequired = FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new Flow() { // from class: com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$2$2$1 r0 = (com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$2$2$1 r0 = new com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4b
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.hardware.biometrics.PromptInfo r5 = (android.hardware.biometrics.PromptInfo) r5
                        if (r5 == 0) goto L3b
                        boolean r5 = r5.isConfirmationRequested()
                        goto L3c
                    L3b:
                        r5 = 0
                    L3c:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                StateFlowImpl.this.collect(new AnonymousClass2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ PromptRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, PromptRepositoryImpl promptRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = promptRepositoryImpl;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L5b
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.lang.Integer r6 = (java.lang.Integer) r6
                        com.android.systemui.biometrics.data.repository.PromptRepositoryImpl r7 = r5.this$0
                        com.android.systemui.biometrics.data.repository.FaceSettingsRepositoryImpl r7 = r7.faceSettings
                        if (r6 == 0) goto L4b
                        java.util.concurrent.ConcurrentHashMap r2 = r7.userSettings
                        com.android.systemui.biometrics.data.repository.FaceSettingsRepositoryImpl$forUser$1 r4 = new com.android.systemui.biometrics.data.repository.FaceSettingsRepositoryImpl$forUser$1
                        r4.<init>(r6, r7)
                        java.lang.Object r6 = r2.computeIfAbsent(r6, r4)
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
                        com.android.systemui.biometrics.data.repository.FaceUserSettingsRepository r6 = (com.android.systemui.biometrics.data.repository.FaceUserSettingsRepository) r6
                        goto L50
                    L4b:
                        r7.getClass()
                        com.android.systemui.biometrics.data.repository.FaceUserSettingsRepositoryImpl$Empty r6 = com.android.systemui.biometrics.data.repository.FaceUserSettingsRepositoryImpl.Empty.INSTANCE
                    L50:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r6, r0)
                        if (r5 != r1) goto L5b
                        return r1
                    L5b:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.data.repository.PromptRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                StateFlowImpl.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }), new PromptRepositoryImpl$special$$inlined$flatMapLatest$1(3, null))), new PromptRepositoryImpl$isConfirmationRequired$1(3, null)));
    }
}
