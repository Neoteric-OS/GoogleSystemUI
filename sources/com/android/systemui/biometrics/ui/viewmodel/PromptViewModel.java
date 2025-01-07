package com.android.systemui.biometrics.ui.viewmodel;

import android.app.ActivityTaskManager;
import android.content.Context;
import android.hardware.biometrics.PromptInfo;
import android.os.VibrationAttributes;
import android.util.Log;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.launcher3.icons.IconProvider;
import com.android.systemui.biometrics.UdfpsUtils;
import com.android.systemui.biometrics.data.repository.PromptRepositoryImpl;
import com.android.systemui.biometrics.domain.interactor.BiometricStatusInteractorImpl;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractor;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractorImpl;
import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractor;
import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractorImpl;
import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import com.android.systemui.biometrics.shared.model.BiometricModalities;
import com.android.systemui.biometrics.shared.model.BiometricModality;
import com.android.systemui.biometrics.shared.model.PromptKind;
import com.android.systemui.biometrics.ui.viewmodel.PromptMessage;
import com.android.wm.shell.R;
import java.util.LinkedHashSet;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptViewModel {
    public final SharedFlowImpl _accessibilityHint;
    public final StateFlowImpl _canTryAgainNow;
    public final StateFlowImpl _fingerprintStartMode;
    public final StateFlowImpl _forceLargeSize;
    public final StateFlowImpl _forceMediumSize;
    public final StateFlowImpl _hapticsToPlay;
    public final StateFlowImpl _isAuthenticated;
    public final StateFlowImpl _isAuthenticating;
    public final StateFlowImpl _isIconViewLoaded;
    public final StateFlowImpl _isOverlayTouched;
    public final StateFlowImpl _message;
    public final ReadonlySharedFlow accessibilityHint;
    public final ActivityTaskManager activityTaskManager;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 canTryAgainNow;
    public final Flow contentView;
    public final Context context;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 credentialKind;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 description;
    public final int faceIconHeight;
    public final int faceIconWidth;
    public final Flow faceMode;
    public final int fingerprintIconHeight;
    public final int fingerprintIconWidth;
    public final ReadonlyStateFlow fingerprintStartMode;
    public final Flow guidelineBounds;
    public final ReadonlyStateFlow hapticsToPlay;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 hasFingerOnSensor;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 hasOnlyOneLineTitle;
    public final Flow hideSensorIcon;
    public final PromptHistoryImpl history;
    public final Flow iconPosition;
    public final IconProvider iconProvider;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 iconSize;
    public final PromptIconViewModel iconViewModel;
    public final ReadonlyStateFlow isAuthenticated;
    public final ReadonlyStateFlow isAuthenticating;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 isCancelButtonVisible;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 isConfirmButtonVisible;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isConfirmationRequired;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 isCredentialButtonVisible;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isIconConfirmButton;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 isIndicatorMessageVisible;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 isNegativeButtonVisible;
    public final PromptViewModel$special$$inlined$map$2 isPendingConfirmation;
    public final PromptViewModel$special$$inlined$map$5 isRetrySupported;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isTryAgainButtonVisible;
    public final int landscapeMediumBottomPadding;
    public final int landscapeMediumHorizontalPadding;
    public final int landscapeSmallBottomPadding;
    public final int landscapeSmallHorizontalPadding;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 legacyFingerprintSensorHeight;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 legacyFingerprintSensorWidth;
    public final Flow logoInfo;
    public final int mediumHorizontalGuidelinePadding;
    public final int mediumTopGuidelinePadding;
    public final ReadonlyStateFlow message;
    public StandaloneCoroutine messageJob;
    public final Flow modalities;
    public final PromptViewModel$special$$inlined$map$1 negativeButtonText;
    public final int portraitLargeScreenBottomPadding;
    public final int portraitMediumBottomPadding;
    public final int portraitSmallBottomPadding;
    public final Flow position;
    public final ReadonlyStateFlow promptKind;
    public final PromptSelectorInteractor promptSelectorInteractor;
    public final Flow showingError;
    public final Flow size;
    public final int smallHorizontalGuidelinePadding;
    public final Flow subtitle;
    public final Flow title;
    public final int udfpsHorizontalGuidelinePadding;
    public final int udfpsHorizontalShorterGuidelinePadding;
    public final ReadonlyStateFlow udfpsOverlayParams;
    public final PromptViewModel$special$$inlined$map$2 udfpsSensorHeight;
    public final PromptViewModel$special$$inlined$map$2 udfpsSensorWidth;
    public final UdfpsUtils udfpsUtils;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface HapticsToPlay {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class HapticConstant implements HapticsToPlay {
            public final int constant;

            public HapticConstant(int i) {
                this.constant = i;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof HapticConstant) {
                    return this.constant == ((HapticConstant) obj).constant && Intrinsics.areEqual((Object) null, (Object) null);
                }
                return false;
            }

            public final int hashCode() {
                return Integer.hashCode(this.constant) * 31;
            }

            public final String toString() {
                return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("HapticConstant(constant="), this.constant, ", flag=null)");
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class None implements HapticsToPlay {
            public static final None INSTANCE = new None();

            public final boolean equals(Object obj) {
                return this == obj || (obj instanceof None);
            }

            public final int hashCode() {
                return -1012995335;
            }

            public final String toString() {
                return "None";
            }
        }
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2, kotlinx.coroutines.flow.Flow] */
    public PromptViewModel(DisplayStateInteractor displayStateInteractor, PromptSelectorInteractor promptSelectorInteractor, Context context, UdfpsOverlayInteractor udfpsOverlayInteractor, BiometricStatusInteractorImpl biometricStatusInteractorImpl, UdfpsUtils udfpsUtils, IconProvider iconProvider, ActivityTaskManager activityTaskManager) {
        final int i = 2;
        final int i2 = 1;
        final int i3 = 0;
        this.promptSelectorInteractor = promptSelectorInteractor;
        this.context = context;
        this.udfpsUtils = udfpsUtils;
        this.iconProvider = iconProvider;
        this.activityTaskManager = activityTaskManager;
        PromptSelectorInteractorImpl promptSelectorInteractorImpl = (PromptSelectorInteractorImpl) promptSelectorInteractor;
        final Flow distinctUntilChanged = FlowKt.distinctUntilChanged(new PromptViewModel$special$$inlined$map$1(promptSelectorInteractorImpl.prompt, i3));
        this.modalities = distinctUntilChanged;
        this.fingerprintIconWidth = context.getResources().getDimensionPixelSize(R.dimen.biometric_dialog_fingerprint_icon_width);
        this.fingerprintIconHeight = context.getResources().getDimensionPixelSize(R.dimen.biometric_dialog_fingerprint_icon_height);
        this.faceIconWidth = context.getResources().getDimensionPixelSize(R.dimen.biometric_dialog_face_icon_size);
        this.faceIconHeight = context.getResources().getDimensionPixelSize(R.dimen.biometric_dialog_face_icon_size);
        this.portraitSmallBottomPadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_portrait_small_bottom_padding);
        this.portraitMediumBottomPadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_portrait_medium_bottom_padding);
        this.portraitLargeScreenBottomPadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_portrait_large_screen_bottom_padding);
        this.landscapeSmallBottomPadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_landscape_small_bottom_padding);
        this.landscapeSmallHorizontalPadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_landscape_small_horizontal_padding);
        this.landscapeMediumBottomPadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_landscape_medium_bottom_padding);
        this.landscapeMediumHorizontalPadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_landscape_medium_horizontal_padding);
        final ReadonlyStateFlow readonlyStateFlow = udfpsOverlayInteractor.udfpsOverlayParams;
        this.udfpsOverlayParams = readonlyStateFlow;
        DisplayStateInteractorImpl displayStateInteractorImpl = (DisplayStateInteractorImpl) displayStateInteractor;
        ReadonlyStateFlow readonlyStateFlow2 = displayStateInteractorImpl.currentRotation;
        Flow distinctUntilChanged2 = FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, readonlyStateFlow2, new PromptViewModel$udfpsSensorBounds$1(3, null)));
        Flow flow = new Flow() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1 r0 = (com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1 r0 = new com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.biometrics.shared.model.UdfpsOverlayParams r5 = (com.android.systemui.biometrics.shared.model.UdfpsOverlayParams) r5
                        android.graphics.Rect r5 = r5.sensorBounds
                        int r5 = r5.width()
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        readonlyStateFlow.collect(new PromptViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    case 2:
                        readonlyStateFlow.collect(new PromptViewModel$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                    default:
                        readonlyStateFlow.collect(new PromptViewModel$special$$inlined$map$7$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        Flow flow2 = new Flow() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1 r0 = (com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1 r0 = new com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.biometrics.shared.model.UdfpsOverlayParams r5 = (com.android.systemui.biometrics.shared.model.UdfpsOverlayParams) r5
                        android.graphics.Rect r5 = r5.sensorBounds
                        int r5 = r5.width()
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        readonlyStateFlow.collect(new PromptViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    case 2:
                        readonlyStateFlow.collect(new PromptViewModel$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                    default:
                        readonlyStateFlow.collect(new PromptViewModel$special$$inlined$map$7$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(distinctUntilChanged, flow, new PromptViewModel$legacyFingerprintSensorWidth$1(this, null));
        new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(distinctUntilChanged, flow2, new PromptViewModel$legacyFingerprintSensorHeight$1(this, null));
        SharedFlowImpl MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);
        this._accessibilityHint = MutableSharedFlow$default;
        this.accessibilityHint = new ReadonlySharedFlow(MutableSharedFlow$default);
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._isAuthenticating = MutableStateFlow;
        this.isAuthenticating = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(new PromptAuthState());
        this._isAuthenticated = MutableStateFlow2;
        final ReadonlyStateFlow readonlyStateFlow3 = new ReadonlyStateFlow(MutableStateFlow2);
        this.isAuthenticated = readonlyStateFlow3;
        ?? r3 = new Flow() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1 r0 = (com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1 r0 = new com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.biometrics.shared.model.UdfpsOverlayParams r5 = (com.android.systemui.biometrics.shared.model.UdfpsOverlayParams) r5
                        android.graphics.Rect r5 = r5.sensorBounds
                        int r5 = r5.width()
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        readonlyStateFlow3.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        readonlyStateFlow3.collect(new PromptViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    case 2:
                        readonlyStateFlow3.collect(new PromptViewModel$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                    default:
                        readonlyStateFlow3.collect(new PromptViewModel$special$$inlined$map$7$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        this.isPendingConfirmation = r3;
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this._isOverlayTouched = MutableStateFlow3;
        this.credentialKind = promptSelectorInteractorImpl.credentialKind;
        ReadonlyStateFlow readonlyStateFlow4 = promptSelectorInteractorImpl.promptKind;
        this.promptKind = readonlyStateFlow4;
        final int i4 = 0;
        Flow distinctUntilChanged3 = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5$2$1 r0 = (com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5$2$1 r0 = new com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L51
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.biometrics.shared.model.BiometricModalities r5 = (com.android.systemui.biometrics.shared.model.BiometricModalities) r5
                        boolean r6 = r5.getHasFingerprint()
                        if (r6 != 0) goto L41
                        android.hardware.face.FaceSensorPropertiesInternal r5 = r5.faceProperties
                        if (r5 == 0) goto L3f
                        goto L41
                    L3f:
                        r5 = r3
                        goto L42
                    L41:
                        r5 = 0
                    L42:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L51
                        return r1
                    L51:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = distinctUntilChanged.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = distinctUntilChanged.collect(new PromptViewModel$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        });
        this.hideSensorIcon = distinctUntilChanged3;
        this.negativeButtonText = new PromptViewModel$special$$inlined$map$1(promptSelectorInteractorImpl.prompt, 5);
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(PromptMessage.Empty.INSTANCE);
        this._message = MutableStateFlow4;
        final ReadonlyStateFlow readonlyStateFlow5 = new ReadonlyStateFlow(MutableStateFlow4);
        this.message = readonlyStateFlow5;
        final int i5 = 3;
        this.showingError = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1 r0 = (com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1 r0 = new com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.biometrics.shared.model.UdfpsOverlayParams r5 = (com.android.systemui.biometrics.shared.model.UdfpsOverlayParams) r5
                        android.graphics.Rect r5 = r5.sensorBounds
                        int r5 = r5.width()
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i5) {
                    case 0:
                        readonlyStateFlow5.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        readonlyStateFlow5.collect(new PromptViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    case 2:
                        readonlyStateFlow5.collect(new PromptViewModel$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                    default:
                        readonlyStateFlow5.collect(new PromptViewModel$special$$inlined$map$7$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        });
        final int i6 = 1;
        Flow flow3 = new Flow() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5$2$1 r0 = (com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5$2$1 r0 = new com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L51
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.biometrics.shared.model.BiometricModalities r5 = (com.android.systemui.biometrics.shared.model.BiometricModalities) r5
                        boolean r6 = r5.getHasFingerprint()
                        if (r6 != 0) goto L41
                        android.hardware.face.FaceSensorPropertiesInternal r5 = r5.faceProperties
                        if (r5 == 0) goto L3f
                        goto L41
                    L3f:
                        r5 = r3
                        goto L42
                    L41:
                        r5 = 0
                    L42:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L51
                        return r1
                    L51:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$5.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i6) {
                    case 0:
                        Object collect = distinctUntilChanged.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = distinctUntilChanged.collect(new PromptViewModel$special$$inlined$map$8$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        StateFlowImpl MutableStateFlow5 = StateFlowKt.MutableStateFlow(FingerprintStartMode.Pending);
        this._fingerprintStartMode = MutableStateFlow5;
        ReadonlyStateFlow readonlyStateFlow6 = new ReadonlyStateFlow(MutableStateFlow5);
        this.fingerprintStartMode = readonlyStateFlow6;
        this.hasFingerOnSensor = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(biometricStatusInteractorImpl.fingerprintAcquiredStatus, distinctUntilChanged, new PromptViewModel$hasFingerBeenAcquired$1(3, null))), MutableStateFlow3, new PromptViewModel$hasFingerOnSensor$1(3, null));
        StateFlowImpl MutableStateFlow6 = StateFlowKt.MutableStateFlow(bool);
        this._forceLargeSize = MutableStateFlow6;
        StateFlowImpl MutableStateFlow7 = StateFlowKt.MutableStateFlow(bool);
        this._forceMediumSize = MutableStateFlow7;
        VibrationAttributes.createForUsage(65);
        StateFlowImpl MutableStateFlow8 = StateFlowKt.MutableStateFlow(HapticsToPlay.None.INSTANCE);
        this._hapticsToPlay = MutableStateFlow8;
        this.hapticsToPlay = new ReadonlyStateFlow(MutableStateFlow8);
        Flow distinctUntilChanged4 = FlowKt.distinctUntilChanged(FlowKt.combine(MutableStateFlow6, readonlyStateFlow4, displayStateInteractorImpl.isLargeScreen, readonlyStateFlow2, distinctUntilChanged, new PromptViewModel$position$1(null)));
        this.position = distinctUntilChanged4;
        Flow distinctUntilChanged5 = FlowKt.distinctUntilChanged(FlowKt.combine(MutableStateFlow6, MutableStateFlow7, distinctUntilChanged, promptSelectorInteractorImpl.isConfirmationRequired, readonlyStateFlow6, new PromptViewModel$size$1(null)));
        this.size = distinctUntilChanged5;
        this.smallHorizontalGuidelinePadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_land_small_horizontal_guideline_padding);
        this.udfpsHorizontalGuidelinePadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_two_pane_udfps_horizontal_guideline_padding);
        this.udfpsHorizontalShorterGuidelinePadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_two_pane_udfps_shorter_horizontal_guideline_padding);
        this.mediumTopGuidelinePadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_one_pane_medium_top_guideline_padding);
        this.mediumHorizontalGuidelinePadding = context.getResources().getDimensionPixelSize(R.dimen.biometric_prompt_two_pane_medium_horizontal_guideline_padding);
        Flow distinctUntilChanged6 = FlowKt.distinctUntilChanged(FlowKt.combine(distinctUntilChanged2, distinctUntilChanged5, distinctUntilChanged4, distinctUntilChanged, new PromptViewModel$iconPosition$1(this, null)));
        this.iconPosition = distinctUntilChanged6;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(MutableStateFlow3, distinctUntilChanged5, new PromptViewModel$isConfirmationRequired$1(3, null));
        this.isConfirmationRequired = flowKt__ZipKt$combine$$inlined$unsafeFlow$1;
        this.faceMode = FlowKt.distinctUntilChanged(FlowKt.combine(distinctUntilChanged, flowKt__ZipKt$combine$$inlined$unsafeFlow$1, readonlyStateFlow6, new PromptViewModel$faceMode$1(4, null)));
        PromptIconViewModel promptIconViewModel = new PromptIconViewModel(this, displayStateInteractor, promptSelectorInteractor);
        this.iconViewModel = promptIconViewModel;
        FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(distinctUntilChanged3, new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(bool)), new PromptViewModel$isIconViewLoaded$1(3, null)));
        this.iconSize = FlowKt.combine(promptIconViewModel.activeAuthType, distinctUntilChanged, flow, flow2, new PromptViewModel$iconSize$1(this, null));
        new PromptViewModel$promptPadding$1(this, null);
        final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 = promptSelectorInteractorImpl.prompt;
        final int i7 = 0;
        this.logoInfo = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$9

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$9$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ PromptViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$9$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, PromptViewModel promptViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = promptViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:100:0x019e  */
                /* JADX WARN: Removed duplicated region for block: B:103:0x01ce  */
                /* JADX WARN: Removed duplicated region for block: B:105:0x01e4  */
                /* JADX WARN: Removed duplicated region for block: B:113:0x01af A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:118:0x017d  */
                /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
                /* JADX WARN: Removed duplicated region for block: B:20:0x0227 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:81:0x0143  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
                /* JADX WARN: Removed duplicated region for block: B:90:0x0164  */
                /* JADX WARN: Removed duplicated region for block: B:97:0x0178  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r17, kotlin.coroutines.Continuation r18) {
                    /*
                        Method dump skipped, instructions count: 555
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$9.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i7) {
                    case 0:
                        Object collect = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1).collect(new AnonymousClass2(flowCollector, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        final Flow[] flowArr = (Flow[]) flowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
                        Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$combine$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return new Object[flowArr.length];
                            }
                        }, new PromptViewModel$special$$inlined$combine$1$3(this, null), flowCollector, flowArr);
                        if (combineInternal != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        });
        Flow distinctUntilChanged7 = FlowKt.distinctUntilChanged(new PromptViewModel$special$$inlined$map$1(promptSelectorInteractorImpl.prompt, 1));
        this.title = distinctUntilChanged7;
        Flow distinctUntilChanged8 = FlowKt.distinctUntilChanged(new PromptViewModel$special$$inlined$map$1(promptSelectorInteractorImpl.prompt, 2));
        this.subtitle = distinctUntilChanged8;
        Flow distinctUntilChanged9 = FlowKt.distinctUntilChanged(new PromptViewModel$special$$inlined$map$1(((PromptSelectorInteractorImpl) this.promptSelectorInteractor).prompt, 3));
        this.contentView = distinctUntilChanged9;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$12 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(distinctUntilChanged9, FlowKt.distinctUntilChanged(new PromptViewModel$special$$inlined$map$1(((PromptSelectorInteractorImpl) this.promptSelectorInteractor).prompt, 4)), new PromptViewModel$description$1(3, null));
        this.description = flowKt__ZipKt$combine$$inlined$unsafeFlow$12;
        final Flow[] flowArr = {distinctUntilChanged6, readonlyStateFlow4, distinctUntilChanged5, distinctUntilChanged4, this.modalities, FlowKt.combine(distinctUntilChanged7, distinctUntilChanged8, distinctUntilChanged9, flowKt__ZipKt$combine$$inlined$unsafeFlow$12, new PromptViewModel$hasOnlyOneLineTitle$1(this, null))};
        final int i8 = 1;
        this.guidelineBounds = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$9

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$9$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ PromptViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$9$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, PromptViewModel promptViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = promptViewModel;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        Method dump skipped, instructions count: 555
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$map$9.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i8) {
                    case 0:
                        Object collect = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) flowArr).collect(new AnonymousClass2(flowCollector, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        final Flow[] flowArr2 = (Flow[]) flowArr;
                        Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$special$$inlined$combine$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return new Object[flowArr2.length];
                            }
                        }, new PromptViewModel$special$$inlined$combine$1$3(this, null), flowCollector, flowArr2);
                        if (combineInternal != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        });
        this.isIndicatorMessageVisible = FlowKt.combine(distinctUntilChanged5, distinctUntilChanged4, readonlyStateFlow5, new PromptViewModel$isIndicatorMessageVisible$1(4, null));
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine = FlowKt.combine(distinctUntilChanged5, distinctUntilChanged4, r3, new PromptViewModel$isConfirmButtonVisible$1(4, null));
        this.isConfirmButtonVisible = combine;
        this.isIconConfirmButton = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(this.modalities, distinctUntilChanged5, new PromptViewModel$isIconConfirmButton$1(3, null));
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine2 = FlowKt.combine(distinctUntilChanged5, distinctUntilChanged4, readonlyStateFlow3, ((PromptSelectorInteractorImpl) this.promptSelectorInteractor).isCredentialAllowed, new PromptViewModel$isNegativeButtonVisible$1(5, null));
        this.isNegativeButtonVisible = combine2;
        this.isCancelButtonVisible = FlowKt.combine(distinctUntilChanged5, distinctUntilChanged4, readonlyStateFlow3, combine2, combine, new PromptViewModel$isCancelButtonVisible$1(null));
        StateFlowImpl MutableStateFlow9 = StateFlowKt.MutableStateFlow(bool);
        this._canTryAgainNow = MutableStateFlow9;
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine3 = FlowKt.combine(MutableStateFlow9, distinctUntilChanged5, distinctUntilChanged4, readonlyStateFlow3, flow3, new PromptViewModel$canTryAgainNow$1(null));
        this.canTryAgainNow = combine3;
        this.isTryAgainButtonVisible = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(combine3, this.modalities, new PromptViewModel$isTryAgainButtonVisible$1(3, null));
        this.isCredentialButtonVisible = FlowKt.combine(distinctUntilChanged5, distinctUntilChanged4, readonlyStateFlow3, ((PromptSelectorInteractorImpl) this.promptSelectorInteractor).isCredentialAllowed, new PromptViewModel$isCredentialButtonVisible$1(5, null));
        PromptHistoryImpl promptHistoryImpl = new PromptHistoryImpl();
        promptHistoryImpl.failures = new LinkedHashSet();
        this.history = promptHistoryImpl;
    }

    public static final int access$getHorizontalPadding(PromptViewModel promptViewModel, PromptSize promptSize, BiometricModalities biometricModalities, boolean z) {
        int i;
        promptViewModel.getClass();
        if (PromptSizeKt.isSmall(promptSize)) {
            i = promptViewModel.smallHorizontalGuidelinePadding;
        } else if (!biometricModalities.getHasUdfps()) {
            i = promptViewModel.mediumHorizontalGuidelinePadding;
        } else {
            if (!z) {
                return promptViewModel.udfpsHorizontalGuidelinePadding;
            }
            i = promptViewModel.udfpsHorizontalShorterGuidelinePadding;
        }
        return -i;
    }

    public static void showAuthenticating$default(PromptViewModel promptViewModel, String str, int i) {
        if ((i & 1) != 0) {
            str = "";
        }
        boolean z = (i & 2) == 0;
        StateFlowImpl stateFlowImpl = promptViewModel._isAuthenticated;
        if (((PromptAuthState) stateFlowImpl.getValue()).isAuthenticated) {
            Log.w("PromptViewModel", "Cannot show authenticating after authenticated");
            return;
        }
        Boolean bool = Boolean.TRUE;
        StateFlowImpl stateFlowImpl2 = promptViewModel._isAuthenticating;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, bool);
        stateFlowImpl.updateState(null, new PromptAuthState());
        Object help = StringsKt__StringsJVMKt.isBlank(str) ? PromptMessage.Empty.INSTANCE : new PromptMessage.Help(str);
        StateFlowImpl stateFlowImpl3 = promptViewModel._message;
        stateFlowImpl3.getClass();
        stateFlowImpl3.updateState(null, help);
        if (z) {
            Boolean bool2 = Boolean.FALSE;
            StateFlowImpl stateFlowImpl4 = promptViewModel._canTryAgainNow;
            stateFlowImpl4.getClass();
            stateFlowImpl4.updateState(null, bool2);
        }
        StandaloneCoroutine standaloneCoroutine = promptViewModel.messageJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        promptViewModel.messageJob = null;
    }

    public static Object showTemporaryError$default(PromptViewModel promptViewModel, String str, String str2, boolean z, Function2 function2, BiometricModality biometricModality, SuspendLambda suspendLambda, int i) {
        Function2 function22 = (i & 8) != 0 ? new Function2() { // from class: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$showTemporaryError$2
            @Override // kotlin.jvm.functions.Function2
            public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return Boolean.FALSE;
            }
        } : function2;
        boolean z2 = (i & 16) != 0;
        BiometricModality biometricModality2 = (i & 32) != 0 ? BiometricModality.None : biometricModality;
        promptViewModel.getClass();
        Object coroutineScope = CoroutineScopeKt.coroutineScope(suspendLambda, new PromptViewModel$showTemporaryError$3(promptViewModel, z2, biometricModality2, function22, str, z, str2, null));
        return coroutineScope == CoroutineSingletons.COROUTINE_SUSPENDED ? coroutineScope : Unit.INSTANCE;
    }

    public final void confirmAuthenticated() {
        StateFlowImpl stateFlowImpl = this._isAuthenticated;
        PromptAuthState promptAuthState = (PromptAuthState) stateFlowImpl.getValue();
        boolean z = promptAuthState.isAuthenticated;
        if (!z) {
            Log.w("PromptViewModel", "Cannot confirm authenticated when not authenticated");
            return;
        }
        PromptAuthState promptAuthState2 = new PromptAuthState(z, promptAuthState.authenticatedModality, false, promptAuthState.delay);
        promptAuthState2.wasConfirmed = true;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, promptAuthState2);
        PromptMessage.Empty empty = PromptMessage.Empty.INSTANCE;
        StateFlowImpl stateFlowImpl2 = this._message;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, empty);
        HapticsToPlay.HapticConstant hapticConstant = new HapticsToPlay.HapticConstant(10004);
        StateFlowImpl stateFlowImpl3 = this._hapticsToPlay;
        stateFlowImpl3.getClass();
        stateFlowImpl3.updateState(null, hapticConstant);
        StandaloneCoroutine standaloneCoroutine = this.messageJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.messageJob = null;
    }

    public final void ensureFingerprintHasStarted(boolean z) {
        StateFlowImpl stateFlowImpl = this._fingerprintStartMode;
        if (stateFlowImpl.getValue() == FingerprintStartMode.Pending) {
            FingerprintStartMode fingerprintStartMode = z ? FingerprintStartMode.Delayed : FingerprintStartMode.Normal;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, fingerprintStartMode);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object needsExplicitConfirmation(com.android.systemui.biometrics.shared.model.BiometricModality r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$needsExplicitConfirmation$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$needsExplicitConfirmation$1 r0 = (com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$needsExplicitConfirmation$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$needsExplicitConfirmation$1 r0 = new com.android.systemui.biometrics.ui.viewmodel.PromptViewModel$needsExplicitConfirmation$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2c
            java.lang.Object r4 = r0.L$0
            r5 = r4
            com.android.systemui.biometrics.shared.model.BiometricModality r5 = (com.android.systemui.biometrics.shared.model.BiometricModality) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L44
        L2c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L34:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r5
            r0.label = r3
            kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 r4 = r4.isConfirmationRequired
            java.lang.Object r6 = kotlinx.coroutines.flow.FlowKt.first(r4, r0)
            if (r6 != r1) goto L44
            return r1
        L44:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            r6.getClass()
            com.android.systemui.biometrics.shared.model.BiometricModality r4 = com.android.systemui.biometrics.shared.model.BiometricModality.Face
            if (r5 != r4) goto L4e
            return r6
        L4e:
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel.needsExplicitConfirmation(com.android.systemui.biometrics.shared.model.BiometricModality, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0082 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object onAnnounceAccessibilityHint(android.view.MotionEvent r13, boolean r14, kotlin.coroutines.jvm.internal.ContinuationImpl r15) {
        /*
            Method dump skipped, instructions count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel.onAnnounceAccessibilityHint(android.view.MotionEvent, boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void onSwitchToCredential() {
        Boolean bool = Boolean.TRUE;
        StateFlowImpl stateFlowImpl = this._forceLargeSize;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        PromptSelectorInteractorImpl promptSelectorInteractorImpl = (PromptSelectorInteractorImpl) this.promptSelectorInteractor;
        PromptRepositoryImpl promptRepositoryImpl = promptSelectorInteractorImpl.promptRepository;
        BiometricModalities biometricModalities = ((PromptKind) ((StateFlowImpl) promptRepositoryImpl.promptKind.$$delegate_0).getValue()).isBiometric() ? ((PromptKind.Biometric) ((StateFlowImpl) promptRepositoryImpl.promptKind.$$delegate_0).getValue()).activeModalities : new BiometricModalities(null, null);
        Object value = ((StateFlowImpl) promptRepositoryImpl.promptInfo.$$delegate_0).getValue();
        Intrinsics.checkNotNull(value);
        PromptInfo promptInfo = (PromptInfo) value;
        Object value2 = ((StateFlowImpl) promptRepositoryImpl.userId.$$delegate_0).getValue();
        Intrinsics.checkNotNull(value2);
        int intValue = ((Number) value2).intValue();
        Object value3 = ((StateFlowImpl) promptRepositoryImpl.requestId.$$delegate_0).getValue();
        Intrinsics.checkNotNull(value3);
        long longValue = ((Number) value3).longValue();
        Object value4 = ((StateFlowImpl) promptRepositoryImpl.challenge.$$delegate_0).getValue();
        Intrinsics.checkNotNull(value4);
        long longValue2 = ((Number) value4).longValue();
        Object value5 = ((StateFlowImpl) promptRepositoryImpl.opPackageName.$$delegate_0).getValue();
        Intrinsics.checkNotNull(value5);
        promptSelectorInteractorImpl.setPrompt(promptInfo, intValue, longValue, biometricModalities, longValue2, (String) value5, true, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00f8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object showAuthenticated(com.android.systemui.biometrics.shared.model.BiometricModality r16, long r17, java.lang.String r19, kotlin.coroutines.jvm.internal.ContinuationImpl r20) {
        /*
            Method dump skipped, instructions count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.PromptViewModel.showAuthenticated(com.android.systemui.biometrics.shared.model.BiometricModality, long, java.lang.String, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final void showHelp(String str) {
        StateFlowImpl stateFlowImpl = this._isAuthenticated;
        if (!((PromptAuthState) stateFlowImpl.getValue()).isAuthenticated) {
            Boolean bool = Boolean.FALSE;
            StateFlowImpl stateFlowImpl2 = this._isAuthenticating;
            stateFlowImpl2.getClass();
            stateFlowImpl2.updateState(null, bool);
            PromptAuthState promptAuthState = new PromptAuthState();
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, promptAuthState);
        }
        Object help = !StringsKt__StringsJVMKt.isBlank(str) ? new PromptMessage.Help(str) : PromptMessage.Empty.INSTANCE;
        StateFlowImpl stateFlowImpl3 = this._message;
        stateFlowImpl3.getClass();
        stateFlowImpl3.updateState(null, help);
        Boolean bool2 = Boolean.TRUE;
        StateFlowImpl stateFlowImpl4 = this._forceMediumSize;
        stateFlowImpl4.getClass();
        stateFlowImpl4.updateState(null, bool2);
        StandaloneCoroutine standaloneCoroutine = this.messageJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.messageJob = null;
    }
}
