package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.hardware.biometrics.BiometricSourceType;
import android.os.Build;
import android.os.SystemProperties;
import android.os.Trace;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardStateControllerImpl implements KeyguardStateController {
    public final ConcurrentHashMap.KeySetView mCallbacks = ConcurrentHashMap.newKeySet();
    public boolean mCanDismissLockScreen;
    public final Context mContext;
    public float mDismissAmount;
    public boolean mDismissingFromTouch;
    public boolean mFaceEnrolledAndEnabled;
    public final FeatureFlags mFeatureFlags;
    public boolean mFlingingToDismissKeyguard;
    public boolean mFlingingToDismissKeyguardDuringSwipeGesture;
    public boolean mKeyguardFadingAway;
    public long mKeyguardFadingAwayDelay;
    public long mKeyguardFadingAwayDuration;
    public boolean mKeyguardGoingAway;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback;
    public boolean mLaunchTransitionFadingAway;
    public final LockPatternUtils mLockPatternUtils;
    public final KeyguardUpdateMonitorLogger mLogger;
    public boolean mOccluded;
    public boolean mPrimaryBouncerShowing;
    public boolean mSecure;
    public boolean mShowing;
    public boolean mSnappingKeyguardBackAfterSwipe;
    public boolean mTrustManaged;
    public boolean mTrusted;
    public final Lazy mUnlockAnimationControllerLazy;
    public final SelectedUserInteractor mUserInteractor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class UpdateMonitorCallback extends KeyguardUpdateMonitorCallback {
        public UpdateMonitorCallback() {
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType, boolean z) {
            Trace.beginSection("KeyguardUpdateMonitorCallback#onBiometricAuthenticated");
            KeyguardStateControllerImpl keyguardStateControllerImpl = KeyguardStateControllerImpl.this;
            if (keyguardStateControllerImpl.mKeyguardUpdateMonitor.isUnlockingWithBiometricAllowed(z)) {
                keyguardStateControllerImpl.update(false);
            }
            Trace.endSection();
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricEnrollmentStateChanged(BiometricSourceType biometricSourceType) {
            if (biometricSourceType == BiometricSourceType.FACE) {
                KeyguardStateControllerImpl keyguardStateControllerImpl = KeyguardStateControllerImpl.this;
                keyguardStateControllerImpl.update(false);
                keyguardStateControllerImpl.invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(7));
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onEnabledTrustAgentsChanged(int i) {
            KeyguardStateControllerImpl.this.update(false);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onFacesCleared() {
            KeyguardStateControllerImpl.this.update(false);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onFingerprintsCleared() {
            KeyguardStateControllerImpl.this.update(false);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onForceIsDismissibleChanged(boolean z) {
            KeyguardStateControllerImpl.this.update(false);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onKeyguardVisibilityChanged(boolean z) {
            KeyguardStateControllerImpl.this.update(false);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onLockedOutStateChanged(BiometricSourceType biometricSourceType) {
            KeyguardStateControllerImpl.this.update(false);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onStartedWakingUp() {
            KeyguardStateControllerImpl.this.update(false);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onStrongAuthStateChanged(int i) {
            KeyguardStateControllerImpl.this.update(false);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onTrustChanged(int i) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = KeyguardStateControllerImpl.this;
            keyguardStateControllerImpl.update(false);
            Trace.beginSection("KeyguardStateController#notifyKeyguardChanged");
            keyguardStateControllerImpl.invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(2));
            Trace.endSection();
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onTrustManagedChanged() {
            KeyguardStateControllerImpl.this.update(false);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onUserSwitchComplete(int i) {
            KeyguardStateControllerImpl.this.update(false);
        }
    }

    public KeyguardStateControllerImpl(Context context, KeyguardUpdateMonitor keyguardUpdateMonitor, LockPatternUtils lockPatternUtils, Lazy lazy, KeyguardUpdateMonitorLogger keyguardUpdateMonitorLogger, DumpManager dumpManager, FeatureFlags featureFlags, SelectedUserInteractor selectedUserInteractor) {
        UpdateMonitorCallback updateMonitorCallback = new UpdateMonitorCallback();
        this.mKeyguardUpdateMonitorCallback = updateMonitorCallback;
        this.mDismissAmount = 0.0f;
        this.mDismissingFromTouch = false;
        this.mFlingingToDismissKeyguard = false;
        this.mFlingingToDismissKeyguardDuringSwipeGesture = false;
        this.mSnappingKeyguardBackAfterSwipe = false;
        this.mContext = context;
        this.mLogger = keyguardUpdateMonitorLogger;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mLockPatternUtils = lockPatternUtils;
        this.mUserInteractor = selectedUserInteractor;
        keyguardUpdateMonitor.registerCallback(updateMonitorCallback);
        this.mUnlockAnimationControllerLazy = lazy;
        this.mFeatureFlags = featureFlags;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "KeyguardStateControllerImpl", this);
        update(true);
        boolean z = Build.IS_DEBUGGABLE;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        KeyguardStateController.Callback callback = (KeyguardStateController.Callback) obj;
        Objects.requireNonNull(callback, "Callback must not be null. b/128895449");
        this.mCallbacks.add(callback);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "KeyguardStateController:", "  mShowing: "), this.mShowing, printWriter, "  mOccluded: "), this.mOccluded, printWriter, "  mSecure: "), this.mSecure, printWriter, "  mCanDismissLockScreen: "), this.mCanDismissLockScreen, printWriter, "  mTrustManaged: "), this.mTrustManaged, printWriter, "  mTrusted: ");
        m.append(this.mTrusted);
        printWriter.println(m.toString());
        printWriter.println("  mDebugUnlocked: false");
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  mFaceEnrolled: "), this.mFaceEnrolledAndEnabled, printWriter, "  isKeyguardFadingAway: "), this.mKeyguardFadingAway, printWriter, "  isKeyguardGoingAway: "), this.mKeyguardGoingAway, printWriter, "  isLaunchTransitionFadingAway: "), this.mLaunchTransitionFadingAway, printWriter);
    }

    public final void invokeForEachCallback(Consumer consumer) {
        this.mCallbacks.forEach(consumer);
    }

    public final boolean isKeyguardScreenRotationAllowed() {
        if (SystemProperties.getBoolean("lockscreen.rot_override", false) || this.mContext.getResources().getBoolean(R.bool.config_enableLockScreenRotation)) {
            return true;
        }
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.mFeatureFlags.getClass();
        return false;
    }

    public final void notifyKeyguardGoingAway(boolean z) {
        if (this.mKeyguardGoingAway != z) {
            Trace.traceCounter(4096L, "keyguardGoingAway", z ? 1 : 0);
            this.mKeyguardGoingAway = z;
            invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(5));
        }
    }

    public final void notifyKeyguardState(boolean z, boolean z2) {
        if (this.mShowing == z && this.mOccluded == z2) {
            return;
        }
        this.mShowing = z;
        this.mOccluded = z2;
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
        boolean z3 = keyguardUpdateMonitor.mKeyguardOccluded != z2;
        boolean z4 = keyguardUpdateMonitor.mKeyguardShowing != z;
        if (z3 || z4) {
            boolean isKeyguardVisible = keyguardUpdateMonitor.isKeyguardVisible();
            keyguardUpdateMonitor.mKeyguardShowing = z;
            keyguardUpdateMonitor.mKeyguardOccluded = z2;
            boolean isKeyguardVisible2 = keyguardUpdateMonitor.isKeyguardVisible();
            keyguardUpdateMonitor.mLogger.logKeyguardShowingChanged(z, z2, isKeyguardVisible2);
            if (isKeyguardVisible2 != isKeyguardVisible) {
                if (isKeyguardVisible2) {
                    keyguardUpdateMonitor.mSecureCameraLaunched = false;
                }
                for (int i = 0; i < keyguardUpdateMonitor.mCallbacks.size(); i++) {
                    KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i)).get();
                    if (keyguardUpdateMonitorCallback != null) {
                        keyguardUpdateMonitorCallback.onKeyguardVisibilityChanged(isKeyguardVisible2);
                    }
                }
            }
            if (z3 || z4) {
                keyguardUpdateMonitor.updateFingerprintListeningState(2);
            }
        }
        Trace.instantForTrack(4096L, "UI Events", "Keyguard showing: " + z + " occluded: " + z2);
        Trace.beginSection("KeyguardStateController#notifyKeyguardChanged");
        invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(2));
        Trace.endSection();
        this.mDismissAmount = z ? 0.0f : 1.0f;
        this.mDismissingFromTouch = false;
        invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(4));
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        KeyguardStateController.Callback callback = (KeyguardStateController.Callback) obj;
        Objects.requireNonNull(callback, "Callback must not be null. b/128895449");
        this.mCallbacks.remove(callback);
    }

    public void update(boolean z) {
        boolean z2;
        Trace.beginSection("KeyguardStateController#update");
        int selectedUserId = this.mUserInteractor.getSelectedUserId();
        boolean isSecure = this.mLockPatternUtils.isSecure(selectedUserId);
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
        if (!isSecure || keyguardUpdateMonitor.getUserCanSkipBouncer(selectedUserId)) {
            z2 = true;
        } else {
            boolean z3 = Build.IS_DEBUGGABLE;
            z2 = false;
        }
        boolean userTrustIsManaged = keyguardUpdateMonitor.getUserTrustIsManaged(selectedUserId);
        boolean userHasTrust = keyguardUpdateMonitor.getUserHasTrust(selectedUserId);
        boolean isFaceEnabledAndEnrolled = keyguardUpdateMonitor.isFaceEnabledAndEnrolled();
        if (isSecure != this.mSecure || z2 != this.mCanDismissLockScreen || userTrustIsManaged != this.mTrustManaged || this.mTrusted != userHasTrust || this.mFaceEnrolledAndEnabled != isFaceEnabledAndEnrolled || z) {
            this.mSecure = isSecure;
            this.mCanDismissLockScreen = z2;
            this.mTrusted = userHasTrust;
            this.mTrustManaged = userTrustIsManaged;
            this.mFaceEnrolledAndEnabled = isFaceEnabledAndEnrolled;
            this.mLogger.logKeyguardStateUpdate(isSecure, z2, userHasTrust, userTrustIsManaged);
            Trace.beginSection("KeyguardStateController#notifyUnlockedChanged");
            invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(3));
            Trace.endSection();
        }
        Trace.endSection();
    }
}
