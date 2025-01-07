package com.android.keyguard;

import android.hardware.biometrics.BiometricSourceType;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.systemui.plugins.clocks.WeatherData;
import java.util.TimeZone;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardUpdateMonitorCallback {
    public void onDevicePolicyManagerStateChanged() {
    }

    public void onDeviceProvisioned() {
    }

    public void onEmergencyCallAction() {
    }

    public void onFacesCleared() {
    }

    public void onFingerprintsCleared() {
    }

    public void onKeyguardDismissAnimationFinished() {
    }

    public void onKeyguardGoingAway() {
    }

    public void onLogoutEnabledChanged() {
    }

    public void onPhoneStateChanged() {
    }

    public void onRefreshCarrierInfo() {
    }

    public void onRequireUnlockForNfc() {
    }

    public void onStartedGoingToSleep$1() {
    }

    public void onStartedWakingUp() {
    }

    public void onTimeChanged() {
    }

    public void onTimeFormatChanged() {
    }

    public void onTrustManagedChanged() {
    }

    public void onUserUnlocked() {
    }

    public void onBiometricAuthFailed(BiometricSourceType biometricSourceType) {
    }

    public void onBiometricDetected(BiometricSourceType biometricSourceType) {
    }

    public void onBiometricEnrollmentStateChanged(BiometricSourceType biometricSourceType) {
    }

    public void onDreamingStateChanged(boolean z) {
    }

    public void onEnabledTrustAgentsChanged(int i) {
    }

    public void onFinishedGoingToSleep(int i) {
    }

    public void onForceIsDismissibleChanged(boolean z) {
    }

    public void onKeyguardBouncerFullyShowingChanged(boolean z) {
    }

    public void onKeyguardBouncerStateChanged(boolean z) {
    }

    public void onKeyguardVisibilityChanged(boolean z) {
    }

    public void onLockedOutStateChanged(BiometricSourceType biometricSourceType) {
    }

    public void onRefreshBatteryInfo(BatteryStatus batteryStatus) {
    }

    public void onSecondaryLockscreenRequirementChanged(int i) {
    }

    public void onShadeExpandedChanged(boolean z) {
    }

    public void onStrongAuthStateChanged(int i) {
    }

    public void onTelephonyCapable(boolean z) {
    }

    public void onTimeZoneChanged(TimeZone timeZone) {
    }

    public void onTrustAgentErrorMessage(CharSequence charSequence) {
    }

    public void onTrustChanged(int i) {
    }

    public void onUserSwitchComplete(int i) {
    }

    public void onUserSwitching(int i) {
    }

    public void onWeatherDataChanged(WeatherData weatherData) {
    }

    public void onBiometricAcquired(BiometricSourceType biometricSourceType, int i) {
    }

    public void onBiometricRunningStateChanged(BiometricSourceType biometricSourceType, boolean z) {
    }

    public void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType, boolean z) {
    }

    public void onBiometricError(int i, String str, BiometricSourceType biometricSourceType) {
    }

    public void onBiometricHelp(int i, String str, BiometricSourceType biometricSourceType) {
    }

    public void onSimStateChanged(int i, int i2, int i3) {
    }

    public void onTrustGrantedForCurrentUser(boolean z, boolean z2, TrustGrantFlags trustGrantFlags, String str) {
    }
}
