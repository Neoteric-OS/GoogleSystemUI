package com.android.keyguard;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.common.buffer.RingBuffer;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardFingerprintListenModel extends KeyguardListenModel {
    public static final List TABLE_HEADERS = CollectionsKt__CollectionsKt.listOf("timestamp", "time_millis", "userId", "listening", "allowOnCurrentOccludingActivity", "alternateBouncerShowing", "biometricAllowedForUser", "biometricPromptShowing", "bouncerIsOrWillShow", "canSkipBouncer", "credentialAttempted", "deviceInteractive", "dreaming", "fingerprintDisabled", "fingerprintLockedOut", "goingToSleep", "keyguardGoingAway", "keyguardIsVisible", "keyguardOccluded", "occludingAppRequestingFp", "shouldListenSidFingerprintState", "shouldListenForFingerprintAssistant", "strongAuthRequired", "switchingUser", "systemUser", "underDisplayFingerprint", "userDoesNotHaveTrust");
    public boolean allowOnCurrentOccludingActivity;
    public boolean alternateBouncerShowing;
    public final Lazy asStringList$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.keyguard.KeyguardFingerprintListenModel$asStringList$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CollectionsKt__CollectionsKt.listOf(KeyguardListenModelKt.DATE_FORMAT.format(Long.valueOf(KeyguardFingerprintListenModel.this.timeMillis)), String.valueOf(KeyguardFingerprintListenModel.this.timeMillis), String.valueOf(KeyguardFingerprintListenModel.this.userId), String.valueOf(KeyguardFingerprintListenModel.this.listening), String.valueOf(KeyguardFingerprintListenModel.this.allowOnCurrentOccludingActivity), String.valueOf(KeyguardFingerprintListenModel.this.alternateBouncerShowing), String.valueOf(KeyguardFingerprintListenModel.this.biometricEnabledForUser), String.valueOf(KeyguardFingerprintListenModel.this.biometricPromptShowing), String.valueOf(KeyguardFingerprintListenModel.this.bouncerIsOrWillShow), String.valueOf(KeyguardFingerprintListenModel.this.canSkipBouncer), String.valueOf(KeyguardFingerprintListenModel.this.credentialAttempted), String.valueOf(KeyguardFingerprintListenModel.this.deviceInteractive), String.valueOf(KeyguardFingerprintListenModel.this.dreaming), String.valueOf(KeyguardFingerprintListenModel.this.fingerprintDisabled), String.valueOf(KeyguardFingerprintListenModel.this.fingerprintLockedOut), String.valueOf(KeyguardFingerprintListenModel.this.goingToSleep), String.valueOf(KeyguardFingerprintListenModel.this.keyguardGoingAway), String.valueOf(KeyguardFingerprintListenModel.this.keyguardIsVisible), String.valueOf(KeyguardFingerprintListenModel.this.keyguardOccluded), String.valueOf(KeyguardFingerprintListenModel.this.occludingAppRequestingFp), String.valueOf(KeyguardFingerprintListenModel.this.shouldListenForFingerprintAssistant), String.valueOf(KeyguardFingerprintListenModel.this.strongerAuthRequired), String.valueOf(KeyguardFingerprintListenModel.this.switchingUser), String.valueOf(KeyguardFingerprintListenModel.this.systemUser), String.valueOf(KeyguardFingerprintListenModel.this.udfps), String.valueOf(KeyguardFingerprintListenModel.this.userDoesNotHaveTrust));
        }
    });
    public boolean biometricEnabledForUser;
    public boolean biometricPromptShowing;
    public boolean bouncerIsOrWillShow;
    public boolean canSkipBouncer;
    public boolean credentialAttempted;
    public boolean deviceInteractive;
    public boolean dreaming;
    public boolean fingerprintDisabled;
    public boolean fingerprintLockedOut;
    public boolean goingToSleep;
    public boolean keyguardGoingAway;
    public boolean keyguardIsVisible;
    public boolean keyguardOccluded;
    public boolean listening;
    public boolean occludingAppRequestingFp;
    public boolean shouldListenForFingerprintAssistant;
    public boolean strongerAuthRequired;
    public boolean switchingUser;
    public boolean systemUser;
    public long timeMillis;
    public boolean udfps;
    public boolean userDoesNotHaveTrust;
    public int userId;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Buffer {
        public final RingBuffer buffer = new RingBuffer(20, new Function0() { // from class: com.android.keyguard.KeyguardFingerprintListenModel$Buffer$buffer$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new KeyguardFingerprintListenModel(0L, 0, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false);
            }
        });
    }

    public KeyguardFingerprintListenModel(long j, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, boolean z21, boolean z22, boolean z23) {
        this.timeMillis = j;
        this.userId = i;
        this.listening = z;
        this.allowOnCurrentOccludingActivity = z2;
        this.alternateBouncerShowing = z3;
        this.biometricEnabledForUser = z4;
        this.biometricPromptShowing = z5;
        this.bouncerIsOrWillShow = z6;
        this.canSkipBouncer = z7;
        this.credentialAttempted = z8;
        this.deviceInteractive = z9;
        this.dreaming = z10;
        this.fingerprintDisabled = z11;
        this.fingerprintLockedOut = z12;
        this.goingToSleep = z13;
        this.keyguardGoingAway = z14;
        this.keyguardIsVisible = z15;
        this.keyguardOccluded = z16;
        this.occludingAppRequestingFp = z17;
        this.shouldListenForFingerprintAssistant = z18;
        this.strongerAuthRequired = z19;
        this.switchingUser = z20;
        this.systemUser = z21;
        this.udfps = z22;
        this.userDoesNotHaveTrust = z23;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyguardFingerprintListenModel)) {
            return false;
        }
        KeyguardFingerprintListenModel keyguardFingerprintListenModel = (KeyguardFingerprintListenModel) obj;
        return this.timeMillis == keyguardFingerprintListenModel.timeMillis && this.userId == keyguardFingerprintListenModel.userId && this.listening == keyguardFingerprintListenModel.listening && this.allowOnCurrentOccludingActivity == keyguardFingerprintListenModel.allowOnCurrentOccludingActivity && this.alternateBouncerShowing == keyguardFingerprintListenModel.alternateBouncerShowing && this.biometricEnabledForUser == keyguardFingerprintListenModel.biometricEnabledForUser && this.biometricPromptShowing == keyguardFingerprintListenModel.biometricPromptShowing && this.bouncerIsOrWillShow == keyguardFingerprintListenModel.bouncerIsOrWillShow && this.canSkipBouncer == keyguardFingerprintListenModel.canSkipBouncer && this.credentialAttempted == keyguardFingerprintListenModel.credentialAttempted && this.deviceInteractive == keyguardFingerprintListenModel.deviceInteractive && this.dreaming == keyguardFingerprintListenModel.dreaming && this.fingerprintDisabled == keyguardFingerprintListenModel.fingerprintDisabled && this.fingerprintLockedOut == keyguardFingerprintListenModel.fingerprintLockedOut && this.goingToSleep == keyguardFingerprintListenModel.goingToSleep && this.keyguardGoingAway == keyguardFingerprintListenModel.keyguardGoingAway && this.keyguardIsVisible == keyguardFingerprintListenModel.keyguardIsVisible && this.keyguardOccluded == keyguardFingerprintListenModel.keyguardOccluded && this.occludingAppRequestingFp == keyguardFingerprintListenModel.occludingAppRequestingFp && this.shouldListenForFingerprintAssistant == keyguardFingerprintListenModel.shouldListenForFingerprintAssistant && this.strongerAuthRequired == keyguardFingerprintListenModel.strongerAuthRequired && this.switchingUser == keyguardFingerprintListenModel.switchingUser && this.systemUser == keyguardFingerprintListenModel.systemUser && this.udfps == keyguardFingerprintListenModel.udfps && this.userDoesNotHaveTrust == keyguardFingerprintListenModel.userDoesNotHaveTrust;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.userDoesNotHaveTrust) + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.userId, Long.hashCode(this.timeMillis) * 31, 31), 31, this.listening), 31, this.allowOnCurrentOccludingActivity), 31, this.alternateBouncerShowing), 31, this.biometricEnabledForUser), 31, this.biometricPromptShowing), 31, this.bouncerIsOrWillShow), 31, this.canSkipBouncer), 31, this.credentialAttempted), 31, this.deviceInteractive), 31, this.dreaming), 31, this.fingerprintDisabled), 31, this.fingerprintLockedOut), 31, this.goingToSleep), 31, this.keyguardGoingAway), 31, this.keyguardIsVisible), 31, this.keyguardOccluded), 31, this.occludingAppRequestingFp), 31, this.shouldListenForFingerprintAssistant), 31, this.strongerAuthRequired), 31, this.switchingUser), 31, this.systemUser), 31, this.udfps);
    }

    public final String toString() {
        return "KeyguardFingerprintListenModel(timeMillis=" + this.timeMillis + ", userId=" + this.userId + ", listening=" + this.listening + ", allowOnCurrentOccludingActivity=" + this.allowOnCurrentOccludingActivity + ", alternateBouncerShowing=" + this.alternateBouncerShowing + ", biometricEnabledForUser=" + this.biometricEnabledForUser + ", biometricPromptShowing=" + this.biometricPromptShowing + ", bouncerIsOrWillShow=" + this.bouncerIsOrWillShow + ", canSkipBouncer=" + this.canSkipBouncer + ", credentialAttempted=" + this.credentialAttempted + ", deviceInteractive=" + this.deviceInteractive + ", dreaming=" + this.dreaming + ", fingerprintDisabled=" + this.fingerprintDisabled + ", fingerprintLockedOut=" + this.fingerprintLockedOut + ", goingToSleep=" + this.goingToSleep + ", keyguardGoingAway=" + this.keyguardGoingAway + ", keyguardIsVisible=" + this.keyguardIsVisible + ", keyguardOccluded=" + this.keyguardOccluded + ", occludingAppRequestingFp=" + this.occludingAppRequestingFp + ", shouldListenForFingerprintAssistant=" + this.shouldListenForFingerprintAssistant + ", strongerAuthRequired=" + this.strongerAuthRequired + ", switchingUser=" + this.switchingUser + ", systemUser=" + this.systemUser + ", udfps=" + this.udfps + ", userDoesNotHaveTrust=" + this.userDoesNotHaveTrust + ")";
    }
}
