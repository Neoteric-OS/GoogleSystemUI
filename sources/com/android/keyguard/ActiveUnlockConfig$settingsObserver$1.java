package com.android.keyguard;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import com.android.keyguard.ActiveUnlockConfig;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import kotlin.collections.EmptySet;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ActiveUnlockConfig$settingsObserver$1 extends ContentObserver {
    public final Uri bioFailUri;
    public final Uri faceAcquireInfoUri;
    public final Uri faceErrorsUri;
    public final /* synthetic */ ActiveUnlockConfig this$0;
    public final Uri unlockIntentLegacyUri;
    public final Uri unlockIntentUri;
    public final Uri unlockIntentWhenBiometricEnrolledUri;
    public final Uri wakeUri;
    public final Uri wakeupsConsideredUnlockIntentsUri;
    public final Uri wakeupsToForceDismissKeyguardUri;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActiveUnlockConfig$settingsObserver$1(ActiveUnlockConfig activeUnlockConfig, Handler handler) {
        super(handler);
        this.this$0 = activeUnlockConfig;
        ((SecureSettingsImpl) activeUnlockConfig.secureSettings).getClass();
        this.wakeUri = Settings.Secure.getUriFor("active_unlock_on_wake");
        SecureSettings secureSettings = activeUnlockConfig.secureSettings;
        ((SecureSettingsImpl) secureSettings).getClass();
        this.unlockIntentLegacyUri = Settings.Secure.getUriFor("active_unlock_on_unlock_intent_legacy");
        ((SecureSettingsImpl) secureSettings).getClass();
        this.unlockIntentUri = Settings.Secure.getUriFor("active_unlock_on_unlock_intent");
        ((SecureSettingsImpl) secureSettings).getClass();
        this.bioFailUri = Settings.Secure.getUriFor("active_unlock_on_biometric_fail");
        ((SecureSettingsImpl) secureSettings).getClass();
        this.faceErrorsUri = Settings.Secure.getUriFor("active_unlock_on_face_errors");
        ((SecureSettingsImpl) secureSettings).getClass();
        this.faceAcquireInfoUri = Settings.Secure.getUriFor("active_unlock_on_face_acquire_info");
        ((SecureSettingsImpl) secureSettings).getClass();
        this.unlockIntentWhenBiometricEnrolledUri = Settings.Secure.getUriFor("active_unlock_on_unlock_intent_when_biometric_enrolled");
        ((SecureSettingsImpl) secureSettings).getClass();
        this.wakeupsConsideredUnlockIntentsUri = Settings.Secure.getUriFor("active_unlock_wakeups_considered_unlock_intents");
        ((SecureSettingsImpl) secureSettings).getClass();
        this.wakeupsToForceDismissKeyguardUri = Settings.Secure.getUriFor("active_unlock_wakeups_to_force_dismiss_keyguard");
    }

    public static void processStringArray(String str, Set set, Set set2) {
        set.clear();
        if (str == null) {
            set.addAll(set2);
            return;
        }
        for (String str2 : StringsKt.split$default(str, new String[]{"|"}, 0, 6)) {
            if (str2.length() > 0) {
                try {
                    set.add(Integer.valueOf(Integer.parseInt(str2)));
                } catch (NumberFormatException unused) {
                    Log.e("ActiveUnlockConfig", "Passed an invalid setting=".concat(str2));
                }
            }
        }
    }

    public final void onChange(boolean z, Collection collection, int i, int i2) {
        if (this.this$0.selectedUserInteractor.getSelectedUserId() != i2) {
            return;
        }
        if (z || collection.contains(this.wakeUri)) {
            ActiveUnlockConfig activeUnlockConfig = this.this$0;
            activeUnlockConfig.requestActiveUnlockOnWakeup = activeUnlockConfig.secureSettings.getIntForUser("active_unlock_on_wake", 0, activeUnlockConfig.selectedUserInteractor.getSelectedUserId()) == 1;
        }
        if (z || collection.contains(this.unlockIntentLegacyUri)) {
            ActiveUnlockConfig activeUnlockConfig2 = this.this$0;
            activeUnlockConfig2.requestActiveUnlockOnUnlockIntentLegacy = activeUnlockConfig2.secureSettings.getIntForUser("active_unlock_on_unlock_intent_legacy", 0, activeUnlockConfig2.selectedUserInteractor.getSelectedUserId()) == 1;
        }
        if (z || collection.contains(this.unlockIntentUri)) {
            ActiveUnlockConfig activeUnlockConfig3 = this.this$0;
            activeUnlockConfig3.requestActiveUnlockOnUnlockIntent = activeUnlockConfig3.secureSettings.getIntForUser("active_unlock_on_unlock_intent", 0, activeUnlockConfig3.selectedUserInteractor.getSelectedUserId()) == 1;
        }
        if (z || collection.contains(this.bioFailUri)) {
            ActiveUnlockConfig activeUnlockConfig4 = this.this$0;
            activeUnlockConfig4.requestActiveUnlockOnBioFail = activeUnlockConfig4.secureSettings.getIntForUser("active_unlock_on_biometric_fail", 0, activeUnlockConfig4.selectedUserInteractor.getSelectedUserId()) == 1;
        }
        if (z || collection.contains(this.faceErrorsUri)) {
            ActiveUnlockConfig activeUnlockConfig5 = this.this$0;
            processStringArray(((SecureSettingsImpl) activeUnlockConfig5.secureSettings).getStringForUser(activeUnlockConfig5.selectedUserInteractor.getSelectedUserId(), "active_unlock_on_face_errors"), this.this$0.faceErrorsToTriggerBiometricFailOn, Collections.singleton(3));
        }
        if (z || collection.contains(this.faceAcquireInfoUri)) {
            ActiveUnlockConfig activeUnlockConfig6 = this.this$0;
            processStringArray(((SecureSettingsImpl) activeUnlockConfig6.secureSettings).getStringForUser(activeUnlockConfig6.selectedUserInteractor.getSelectedUserId(), "active_unlock_on_face_acquire_info"), this.this$0.faceAcquireInfoToTriggerBiometricFailOn, EmptySet.INSTANCE);
        }
        if (z || collection.contains(this.unlockIntentWhenBiometricEnrolledUri)) {
            ActiveUnlockConfig activeUnlockConfig7 = this.this$0;
            processStringArray(((SecureSettingsImpl) activeUnlockConfig7.secureSettings).getStringForUser(activeUnlockConfig7.selectedUserInteractor.getSelectedUserId(), "active_unlock_on_unlock_intent_when_biometric_enrolled"), this.this$0.onUnlockIntentWhenBiometricEnrolled, Collections.singleton(Integer.valueOf(ActiveUnlockConfig.BiometricType.NONE.getIntValue())));
        }
        if (z || collection.contains(this.wakeupsConsideredUnlockIntentsUri)) {
            ActiveUnlockConfig activeUnlockConfig8 = this.this$0;
            processStringArray(((SecureSettingsImpl) activeUnlockConfig8.secureSettings).getStringForUser(activeUnlockConfig8.selectedUserInteractor.getSelectedUserId(), "active_unlock_wakeups_considered_unlock_intents"), this.this$0.wakeupsConsideredUnlockIntents, Collections.singleton(12));
        }
        if (z || collection.contains(this.wakeupsToForceDismissKeyguardUri)) {
            ActiveUnlockConfig activeUnlockConfig9 = this.this$0;
            processStringArray(((SecureSettingsImpl) activeUnlockConfig9.secureSettings).getStringForUser(activeUnlockConfig9.selectedUserInteractor.getSelectedUserId(), "active_unlock_wakeups_to_force_dismiss_keyguard"), this.this$0.wakeupsToForceDismissKeyguard, Collections.singleton(12));
        }
    }
}
