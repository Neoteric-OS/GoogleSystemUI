package com.android.systemui.deviceentry.domain.interactor;

import android.content.res.Resources;
import com.android.systemui.biometrics.domain.interactor.FingerprintPropertyInteractor;
import com.android.systemui.keyguard.domain.interactor.DevicePostureInteractor;
import com.android.systemui.util.kotlin.FlowKt;
import com.android.wm.shell.R;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricMessageInteractor {
    public final DeviceEntryBiometricSettingsInteractor biometricSettingsInteractor;
    public final BiometricMessageInteractor$special$$inlined$map$3 coExFaceAcquisitionMsgIdsToShow;
    public final Set coExFaceAcquisitionMsgIdsToShowDefault;
    public final Set coExFaceAcquisitionMsgIdsToShowUnfolded;
    public final BiometricMessageInteractor$special$$inlined$filterIsInstance$1 faceError;
    public final BiometricMessageInteractor$special$$inlined$map$3 faceErrorMessage;
    public final BiometricMessageInteractor$special$$inlined$filterIsInstance$1 faceFailure;
    public final BiometricMessageInteractor$special$$inlined$map$3 faceFailureMessage;
    public final BiometricMessageInteractor$special$$inlined$filterIsInstance$1 faceHelp;
    public final BiometricMessageInteractor$special$$inlined$map$1 faceHelpMessage;
    public final ChannelLimitedFlowMerge faceMessage;
    public final ChannelFlowTransformLatest filterConditionForFaceHelpMessages;
    public final BiometricMessageInteractor$special$$inlined$map$1 fingerprintErrorMessage;
    public final ChannelFlowTransformLatest fingerprintFailMessage;
    public final BiometricMessageInteractor$special$$inlined$map$1 fingerprintHelpMessage;
    public final ChannelLimitedFlowMerge fingerprintMessage;
    public final Resources resources;

    public BiometricMessageInteractor(Resources resources, DeviceEntryFingerprintAuthInteractor deviceEntryFingerprintAuthInteractor, FingerprintPropertyInteractor fingerprintPropertyInteractor, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, DeviceEntryBiometricSettingsInteractor deviceEntryBiometricSettingsInteractor, FaceHelpMessageDeferralInteractor faceHelpMessageDeferralInteractor, DevicePostureInteractor devicePostureInteractor) {
        int i = 4;
        int i2 = 3;
        this.resources = resources;
        this.biometricSettingsInteractor = deviceEntryBiometricSettingsInteractor;
        int i3 = 0;
        BiometricMessageInteractor$special$$inlined$filterIsInstance$1 biometricMessageInteractor$special$$inlined$filterIsInstance$1 = new BiometricMessageInteractor$special$$inlined$filterIsInstance$1(deviceEntryFaceAuthInteractor.getAuthenticationStatus(), i3);
        int i4 = 1;
        BiometricMessageInteractor$special$$inlined$filterIsInstance$1 biometricMessageInteractor$special$$inlined$filterIsInstance$12 = new BiometricMessageInteractor$special$$inlined$filterIsInstance$1(deviceEntryFaceAuthInteractor.getAuthenticationStatus(), i4);
        int i5 = 2;
        BiometricMessageInteractor$special$$inlined$filterIsInstance$1 biometricMessageInteractor$special$$inlined$filterIsInstance$13 = new BiometricMessageInteractor$special$$inlined$filterIsInstance$1(deviceEntryFaceAuthInteractor.getAuthenticationStatus(), i5);
        this.coExFaceAcquisitionMsgIdsToShowDefault = ArraysKt.toSet(resources.getIntArray(R.array.config_face_help_msgs_when_fingerprint_enrolled));
        this.coExFaceAcquisitionMsgIdsToShowUnfolded = ArraysKt.toSet(resources.getIntArray(R.array.config_face_help_msgs_when_fingerprint_enrolled_unfolded));
        BiometricMessageInteractor$special$$inlined$map$3 biometricMessageInteractor$special$$inlined$map$3 = new BiometricMessageInteractor$special$$inlined$map$3(deviceEntryFingerprintAuthInteractor.fingerprintError, this, i4);
        StateFlow stateFlow = deviceEntryBiometricSettingsInteractor.fingerprintAuthCurrentlyAllowed;
        BiometricMessageInteractor$special$$inlined$map$1 biometricMessageInteractor$special$$inlined$map$1 = new BiometricMessageInteractor$special$$inlined$map$1(new BiometricMessageInteractor$special$$inlined$filter$1(FlowKt.sample(biometricMessageInteractor$special$$inlined$map$3, stateFlow, BiometricMessageInteractor$fingerprintErrorMessage$3.INSTANCE), i3), i3);
        BiometricMessageInteractor$special$$inlined$map$1 biometricMessageInteractor$special$$inlined$map$12 = new BiometricMessageInteractor$special$$inlined$map$1(new BiometricMessageInteractor$special$$inlined$filter$1(FlowKt.sample(deviceEntryFingerprintAuthInteractor.fingerprintHelp, stateFlow, BiometricMessageInteractor$fingerprintHelpMessage$2.INSTANCE), i4), i5);
        ChannelFlowTransformLatest transformLatest = kotlinx.coroutines.flow.FlowKt.transformLatest(fingerprintPropertyInteractor.isUdfps, new BiometricMessageInteractor$special$$inlined$flatMapLatest$1(null, deviceEntryFingerprintAuthInteractor, this));
        this.coExFaceAcquisitionMsgIdsToShow = new BiometricMessageInteractor$special$$inlined$map$3(devicePostureInteractor.posture, this, i3);
        this.fingerprintMessage = kotlinx.coroutines.flow.FlowKt.merge(biometricMessageInteractor$special$$inlined$map$1, transformLatest, biometricMessageInteractor$special$$inlined$map$12);
        BiometricMessageInteractor$filterConditionForFaceHelpMessages$2 biometricMessageInteractor$filterConditionForFaceHelpMessages$2 = BiometricMessageInteractor$filterConditionForFaceHelpMessages$2.INSTANCE;
        StateFlow stateFlow2 = deviceEntryBiometricSettingsInteractor.isFingerprintAuthEnrolledAndEnabled;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = deviceEntryBiometricSettingsInteractor.faceAuthCurrentlyAllowed;
        this.faceMessage = kotlinx.coroutines.flow.FlowKt.merge(new BiometricMessageInteractor$special$$inlined$map$1(new BiometricMessageInteractor$special$$inlined$filter$1(FlowKt.sample(new BiometricMessageInteractor$special$$inlined$map$3(biometricMessageInteractor$special$$inlined$filterIsInstance$1, faceHelpMessageDeferralInteractor, i5), kotlinx.coroutines.flow.FlowKt.transformLatest(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(stateFlow2, flowKt__ZipKt$combine$$inlined$unsafeFlow$1, biometricMessageInteractor$filterConditionForFaceHelpMessages$2), new BiometricMessageInteractor$special$$inlined$flatMapLatest$2(null, this)), BiometricMessageInteractor$faceHelpMessage$3.INSTANCE), i5), i2), new BiometricMessageInteractor$special$$inlined$map$3(new BiometricMessageInteractor$special$$inlined$filter$1(FlowKt.sample(biometricMessageInteractor$special$$inlined$filterIsInstance$13, flowKt__ZipKt$combine$$inlined$unsafeFlow$1), i2), this, i), new BiometricMessageInteractor$special$$inlined$map$3(new BiometricMessageInteractor$special$$inlined$filter$1(FlowKt.sample(new BiometricMessageInteractor$special$$inlined$map$3(biometricMessageInteractor$special$$inlined$filterIsInstance$12, this, i2), flowKt__ZipKt$combine$$inlined$unsafeFlow$1, BiometricMessageInteractor$faceErrorMessage$3.INSTANCE), i), faceHelpMessageDeferralInteractor, 5));
    }
}
