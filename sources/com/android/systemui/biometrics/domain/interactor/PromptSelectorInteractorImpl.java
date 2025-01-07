package com.android.systemui.biometrics.domain.interactor;

import android.hardware.biometrics.PromptInfo;
import android.util.Log;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.biometrics.Utils;
import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepository;
import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl;
import com.android.systemui.biometrics.data.repository.PromptRepositoryImpl;
import com.android.systemui.biometrics.shared.model.BiometricModalities;
import com.android.systemui.biometrics.shared.model.PromptKind;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptSelectorInteractorImpl implements PromptSelectorInteractor {
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 credentialKind;
    public final DisplayStateInteractor displayStateInteractor;
    public final StateFlow fingerprintSensorType;
    public final Flow isConfirmationRequired;
    public final Flow isCredentialAllowed;
    public final LockPatternUtils lockPatternUtils;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 prompt;
    public final ReadonlyStateFlow promptKind;
    public final PromptRepositoryImpl promptRepository;

    public PromptSelectorInteractorImpl(FingerprintPropertyRepository fingerprintPropertyRepository, DisplayStateInteractor displayStateInteractor, PromptRepositoryImpl promptRepositoryImpl, LockPatternUtils lockPatternUtils) {
        this.displayStateInteractor = displayStateInteractor;
        this.promptRepository = promptRepositoryImpl;
        this.lockPatternUtils = lockPatternUtils;
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine = FlowKt.combine(promptRepositoryImpl.promptInfo, promptRepositoryImpl.challenge, promptRepositoryImpl.userId, promptRepositoryImpl.promptKind, promptRepositoryImpl.opPackageName, new PromptSelectorInteractorImpl$prompt$1(null));
        this.prompt = combine;
        this.promptKind = promptRepositoryImpl.promptKind;
        this.isConfirmationRequired = FlowKt.distinctUntilChanged(promptRepositoryImpl.isConfirmationRequired);
        final ReadonlyStateFlow readonlyStateFlow = promptRepositoryImpl.promptInfo;
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl$special$$inlined$map$1$2$1
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
                        boolean r5 = com.android.systemui.biometrics.Utils.isDeviceCredentialAllowed(r5)
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = StateFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        });
        this.isCredentialAllowed = distinctUntilChanged;
        this.credentialKind = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(combine, distinctUntilChanged, new PromptSelectorInteractorImpl$credentialKind$1(this, null));
        this.fingerprintSensorType = ((FingerprintPropertyRepositoryImpl) fingerprintPropertyRepository).sensorType;
    }

    public final void resetPrompt(long j) {
        PromptRepositoryImpl promptRepositoryImpl = this.promptRepository;
        StateFlowImpl stateFlowImpl = promptRepositoryImpl._requestId;
        Long l = (Long) stateFlowImpl.getValue();
        if (l == null || j != l.longValue()) {
            Log.w("PromptRepositoryImpl", "Ignoring unsetPrompt - requestId mismatch");
            return;
        }
        promptRepositoryImpl._promptInfo.setValue(null);
        promptRepositoryImpl._userId.setValue(null);
        stateFlowImpl.setValue(null);
        promptRepositoryImpl._challenge.setValue(null);
        PromptKind.None none = PromptKind.None.INSTANCE;
        StateFlowImpl stateFlowImpl2 = promptRepositoryImpl._promptKind;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, none);
        promptRepositoryImpl._opPackageName.setValue(null);
    }

    public final void setPrompt(PromptInfo promptInfo, int i, long j, BiometricModalities biometricModalities, long j2, String str, boolean z, boolean z2) {
        boolean z3 = ((promptInfo.getAuthenticators() & 255) != 0 || !Utils.isDeviceCredentialAllowed(promptInfo) || promptInfo.getContentView() == null || promptInfo.isContentViewMoreOptionsButtonUsed() || ((PromptKind) ((StateFlowImpl) this.promptKind.$$delegate_0).getValue()).isCredential()) ? false : true;
        Object obj = PromptKind.None.INSTANCE;
        if (z) {
            obj = Utils.getCredentialType(this.lockPatternUtils, i);
        } else if ((promptInfo.getAuthenticators() & 255) == 0 && !z3) {
            if (Utils.isDeviceCredentialAllowed(promptInfo)) {
                obj = Utils.getCredentialType(this.lockPatternUtils, i);
            }
        } else if (z2) {
            obj = new PromptKind.Biometric(biometricModalities, ((Boolean) ((StateFlowImpl) ((DisplayStateInteractorImpl) this.displayStateInteractor).isLargeScreen.$$delegate_0).getValue()).booleanValue() ? PromptKind.Biometric.PaneType.ONE_PANE_LARGE_SCREEN_LANDSCAPE : z3 ? PromptKind.Biometric.PaneType.ONE_PANE_NO_SENSOR_LANDSCAPE : PromptKind.Biometric.PaneType.TWO_PANE_LANDSCAPE);
        } else {
            obj = new PromptKind.Biometric(biometricModalities, PromptKind.Biometric.PaneType.ONE_PANE_PORTRAIT);
        }
        Long valueOf = Long.valueOf(j2);
        PromptRepositoryImpl promptRepositoryImpl = this.promptRepository;
        StateFlowImpl stateFlowImpl = promptRepositoryImpl._promptKind;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, obj);
        Integer valueOf2 = Integer.valueOf(i);
        StateFlowImpl stateFlowImpl2 = promptRepositoryImpl._userId;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, valueOf2);
        Long valueOf3 = Long.valueOf(j);
        StateFlowImpl stateFlowImpl3 = promptRepositoryImpl._requestId;
        stateFlowImpl3.getClass();
        stateFlowImpl3.updateState(null, valueOf3);
        StateFlowImpl stateFlowImpl4 = promptRepositoryImpl._challenge;
        stateFlowImpl4.getClass();
        stateFlowImpl4.updateState(null, valueOf);
        StateFlowImpl stateFlowImpl5 = promptRepositoryImpl._promptInfo;
        stateFlowImpl5.getClass();
        stateFlowImpl5.updateState(null, promptInfo);
        StateFlowImpl stateFlowImpl6 = promptRepositoryImpl._opPackageName;
        stateFlowImpl6.getClass();
        stateFlowImpl6.updateState(null, str);
    }
}
