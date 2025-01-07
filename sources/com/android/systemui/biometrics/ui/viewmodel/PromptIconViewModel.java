package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractor;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractorImpl;
import com.android.systemui.biometrics.domain.interactor.PromptSelectorInteractor;
import com.android.systemui.biometrics.shared.model.DisplayRotation;
import com.android.systemui.biometrics.shared.model.FingerprintSensorType;
import com.android.wm.shell.R;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.enums.EnumEntriesKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptIconViewModel {
    public final StateFlowImpl _previousIconWasError;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 activeAuthType;
    public final List assetsReusedAcrossRotations;
    public final ChannelFlowTransformLatest contentDescriptionId;
    public final DisplayStateInteractor displayStateInteractor;
    public final ChannelFlowTransformLatest iconAsset;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 iconViewRotation;
    public final ChannelFlowTransformLatest shouldAnimateIconView;
    public final ChannelFlowTransformLatest shouldLoopIconView;
    public final Flow showingError;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AuthType {
        public static final /* synthetic */ AuthType[] $VALUES;
        public static final AuthType Coex;
        public static final AuthType Face;
        public static final AuthType Fingerprint;

        static {
            AuthType authType = new AuthType("Fingerprint", 0);
            Fingerprint = authType;
            AuthType authType2 = new AuthType("Face", 1);
            Face = authType2;
            AuthType authType3 = new AuthType("Coex", 2);
            Coex = authType3;
            AuthType[] authTypeArr = {authType, authType2, authType3};
            $VALUES = authTypeArr;
            EnumEntriesKt.enumEntries(authTypeArr);
        }

        public static AuthType valueOf(String str) {
            return (AuthType) Enum.valueOf(AuthType.class, str);
        }

        public static AuthType[] values() {
            return (AuthType[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FingerprintSensorType.values().length];
            try {
                iArr[4] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[AuthType.values().length];
            try {
                iArr2[0] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                AuthType authType = AuthType.Fingerprint;
                iArr2[1] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                AuthType authType2 = AuthType.Fingerprint;
                iArr2[2] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr3 = new int[DisplayRotation.values().length];
            try {
                iArr3[0] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                DisplayRotation displayRotation = DisplayRotation.ROTATION_0;
                iArr3[1] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                DisplayRotation displayRotation2 = DisplayRotation.ROTATION_0;
                iArr3[2] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                DisplayRotation displayRotation3 = DisplayRotation.ROTATION_0;
                iArr3[3] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public PromptIconViewModel(PromptViewModel promptViewModel, DisplayStateInteractor displayStateInteractor, PromptSelectorInteractor promptSelectorInteractor) {
        this.displayStateInteractor = displayStateInteractor;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(FlowKt.distinctUntilChanged(promptViewModel.modalities), FlowKt.distinctUntilChanged(promptViewModel.faceMode), new PromptIconViewModel$activeAuthType$1(3, null));
        this.activeAuthType = flowKt__ZipKt$combine$$inlined$unsafeFlow$1;
        this.showingError = promptViewModel.showingError;
        this._previousIconWasError = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        new PromptIconViewModel$iconSize$1(promptViewModel, null);
        ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, new PromptIconViewModel$special$$inlined$flatMapLatest$1(promptSelectorInteractor, this, promptViewModel, null));
        this.iconAsset = transformLatest;
        this.contentDescriptionId = FlowKt.transformLatest(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, new PromptIconViewModel$special$$inlined$flatMapLatest$2(promptSelectorInteractor, this, promptViewModel, null));
        this.shouldAnimateIconView = FlowKt.transformLatest(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, new PromptIconViewModel$special$$inlined$flatMapLatest$3(promptSelectorInteractor, this, promptViewModel, null));
        this.shouldLoopIconView = FlowKt.transformLatest(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, new PromptIconViewModel$special$$inlined$flatMapLatest$4(promptViewModel, null));
        this.iconViewRotation = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(transformLatest, ((DisplayStateInteractorImpl) displayStateInteractor).currentRotation, new PromptIconViewModel$iconViewRotation$1(this, null));
        this.assetsReusedAcrossRotations = CollectionsKt__CollectionsKt.listOf(Integer.valueOf(R.raw.biometricprompt_sfps_fingerprint_authenticating), Integer.valueOf(R.raw.biometricprompt_sfps_rear_display_fingerprint_authenticating), Integer.valueOf(R.raw.biometricprompt_sfps_rear_display_fingerprint_authenticating));
    }

    public static int getSfpsAsset_errorToFingerprint(DisplayRotation displayRotation, boolean z) {
        if (z) {
            int ordinal = displayRotation.ordinal();
            if (ordinal == 0) {
                return R.raw.biometricprompt_sfps_rear_display_error_to_fingerprint;
            }
            if (ordinal == 1) {
                return R.raw.biometricprompt_sfps_rear_display_error_to_fingerprint_90;
            }
            if (ordinal == 2) {
                return R.raw.biometricprompt_sfps_rear_display_error_to_fingerprint_180;
            }
            if (ordinal == 3) {
                return R.raw.biometricprompt_sfps_rear_display_error_to_fingerprint_270;
            }
            throw new NoWhenBranchMatchedException();
        }
        int ordinal2 = displayRotation.ordinal();
        if (ordinal2 == 0) {
            return R.raw.biometricprompt_sfps_error_to_fingerprint;
        }
        if (ordinal2 == 1) {
            return R.raw.biometricprompt_sfps_error_to_fingerprint_90;
        }
        if (ordinal2 == 2) {
            return R.raw.biometricprompt_sfps_error_to_fingerprint_180;
        }
        if (ordinal2 == 3) {
            return R.raw.biometricprompt_sfps_error_to_fingerprint_270;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static int getSfpsAsset_fingerprintToError(DisplayRotation displayRotation, boolean z) {
        if (z) {
            int ordinal = displayRotation.ordinal();
            if (ordinal == 0) {
                return R.raw.biometricprompt_sfps_rear_display_fingerprint_to_error;
            }
            if (ordinal == 1) {
                return R.raw.biometricprompt_sfps_rear_display_fingerprint_to_error_90;
            }
            if (ordinal == 2) {
                return R.raw.biometricprompt_sfps_rear_display_fingerprint_to_error_180;
            }
            if (ordinal == 3) {
                return R.raw.biometricprompt_sfps_rear_display_fingerprint_to_error_270;
            }
            throw new NoWhenBranchMatchedException();
        }
        int ordinal2 = displayRotation.ordinal();
        if (ordinal2 == 0) {
            return R.raw.biometricprompt_sfps_fingerprint_to_error;
        }
        if (ordinal2 == 1) {
            return R.raw.biometricprompt_sfps_fingerprint_to_error_90;
        }
        if (ordinal2 == 2) {
            return R.raw.biometricprompt_sfps_fingerprint_to_error_180;
        }
        if (ordinal2 == 3) {
            return R.raw.biometricprompt_sfps_fingerprint_to_error_270;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static int getSfpsAsset_fingerprintToSuccess(DisplayRotation displayRotation, boolean z) {
        if (z) {
            int ordinal = displayRotation.ordinal();
            if (ordinal == 0) {
                return R.raw.biometricprompt_sfps_rear_display_fingerprint_to_success;
            }
            if (ordinal == 1) {
                return R.raw.biometricprompt_sfps_rear_display_fingerprint_to_success_90;
            }
            if (ordinal == 2) {
                return R.raw.biometricprompt_sfps_rear_display_fingerprint_to_success_180;
            }
            if (ordinal == 3) {
                return R.raw.biometricprompt_sfps_rear_display_fingerprint_to_success_270;
            }
            throw new NoWhenBranchMatchedException();
        }
        int ordinal2 = displayRotation.ordinal();
        if (ordinal2 == 0) {
            return R.raw.biometricprompt_sfps_fingerprint_to_success;
        }
        if (ordinal2 == 1) {
            return R.raw.biometricprompt_sfps_fingerprint_to_success_90;
        }
        if (ordinal2 == 2) {
            return R.raw.biometricprompt_sfps_fingerprint_to_success_180;
        }
        if (ordinal2 == 3) {
            return R.raw.biometricprompt_sfps_fingerprint_to_success_270;
        }
        throw new NoWhenBranchMatchedException();
    }
}
