package com.android.systemui.statusbar.phone;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.biometrics.BiometricSourceType;
import android.os.Trace;
import android.provider.Settings;
import com.android.app.tracing.TraceUtilsKt;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DevicePostureControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.tuner.TunerServiceImpl;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardBypassController implements Dumpable {
    public final CoroutineScope applicationScope;
    public boolean bouncerShowing;
    public boolean bypassEnabled;
    public final int bypassOverride;
    public final int configFaceAuthSupportedPosture;
    public final boolean hasFaceFeature;
    public boolean isPulseExpanding;
    public final KeyguardStateController keyguardStateController;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;
    public boolean launchingAffordance;
    public PendingUnlock pendingUnlock;
    public int postureState;
    public boolean qsExpanded;
    public final Lazy shadeInteractorLazy;
    public final StatusBarStateController statusBarStateController;
    public BiometricUnlockController unlockController;
    public final List listeners = new ArrayList();
    public final KeyguardBypassController$faceAuthEnabledChangedCallback$1 faceAuthEnabledChangedCallback = new KeyguardStateController.Callback() { // from class: com.android.systemui.statusbar.phone.KeyguardBypassController$faceAuthEnabledChangedCallback$1
        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onFaceEnrolledChanged() {
            KeyguardBypassController.this.notifyListeners$2();
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnBypassStateChangedListener {
        void onBypassStateChanged(boolean z);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PendingUnlock {
        public final boolean isStrongBiometric;
        public final BiometricSourceType pendingUnlockType;

        public PendingUnlock(BiometricSourceType biometricSourceType, boolean z) {
            this.pendingUnlockType = biometricSourceType;
            this.isStrongBiometric = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PendingUnlock)) {
                return false;
            }
            PendingUnlock pendingUnlock = (PendingUnlock) obj;
            return this.pendingUnlockType == pendingUnlock.pendingUnlockType && this.isStrongBiometric == pendingUnlock.isStrongBiometric;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isStrongBiometric) + (this.pendingUnlockType.hashCode() * 31);
        }

        public final String toString() {
            return "PendingUnlock(pendingUnlockType=" + this.pendingUnlockType + ", isStrongBiometric=" + this.isStrongBiometric + ")";
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.android.systemui.statusbar.phone.KeyguardBypassController$faceAuthEnabledChangedCallback$1] */
    public KeyguardBypassController(Resources resources, PackageManager packageManager, CoroutineScope coroutineScope, final TunerService tunerService, StatusBarStateController statusBarStateController, NotificationLockscreenUserManager notificationLockscreenUserManager, KeyguardStateController keyguardStateController, Lazy lazy, DevicePostureController devicePostureController, KeyguardTransitionInteractor keyguardTransitionInteractor, DumpManager dumpManager) {
        this.applicationScope = coroutineScope;
        this.statusBarStateController = statusBarStateController;
        this.keyguardStateController = keyguardStateController;
        this.shadeInteractorLazy = lazy;
        this.keyguardTransitionInteractor = keyguardTransitionInteractor;
        this.bypassOverride = resources.getInteger(R.integer.config_face_unlock_bypass_override);
        int integer = resources.getInteger(R.integer.config_face_auth_supported_posture);
        this.configFaceAuthSupportedPosture = integer;
        boolean hasSystemFeature = packageManager.hasSystemFeature("android.hardware.biometrics.face");
        this.hasFaceFeature = hasSystemFeature;
        if (hasSystemFeature) {
            if (integer != 0) {
                ((DevicePostureControllerImpl) devicePostureController).addCallback(new DevicePostureController.Callback() { // from class: com.android.systemui.statusbar.phone.KeyguardBypassController.1
                    @Override // com.android.systemui.statusbar.policy.DevicePostureController.Callback
                    public final void onPostureChanged(int i) {
                        KeyguardBypassController keyguardBypassController = KeyguardBypassController.this;
                        if (keyguardBypassController.postureState != i) {
                            keyguardBypassController.postureState = i;
                            keyguardBypassController.notifyListeners$2();
                        }
                    }
                });
            }
            dumpManager.registerNormalDumpable("KeyguardBypassController", this);
            statusBarStateController.addCallback(new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.phone.KeyguardBypassController.2
                @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
                public final void onStateChanged(int i) {
                    if (i != 1) {
                        KeyguardBypassController.this.pendingUnlock = null;
                    }
                }
            });
            final int i = resources.getBoolean(android.R.bool.config_forceOrientationListenerEnabledWhileDreaming) ? 1 : 0;
            tunerService.addTunable(new TunerService.Tunable() { // from class: com.android.systemui.statusbar.phone.KeyguardBypassController.3
                @Override // com.android.systemui.tuner.TunerService.Tunable
                public final void onTuningChanged(String str, String str2) {
                    TunerServiceImpl tunerServiceImpl = (TunerServiceImpl) tunerService;
                    boolean z = Settings.Secure.getIntForUser(tunerServiceImpl.mContentResolver, str, i, tunerServiceImpl.mCurrentUser) != 0;
                    KeyguardBypassController keyguardBypassController = KeyguardBypassController.this;
                    keyguardBypassController.bypassEnabled = z;
                    keyguardBypassController.notifyListeners$2();
                }
            }, "face_unlock_dismisses_keyguard");
            ((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager).mListeners.add(new NotificationLockscreenUserManager.UserChangedListener() { // from class: com.android.systemui.statusbar.phone.KeyguardBypassController.4
                @Override // com.android.systemui.statusbar.NotificationLockscreenUserManager.UserChangedListener
                public final void onUserChanged(int i2) {
                    KeyguardBypassController.this.pendingUnlock = null;
                }
            });
        }
    }

    public final boolean canBypass() {
        if (getBypassEnabled()) {
            return this.bouncerShowing || this.keyguardTransitionInteractor.getCurrentState() == KeyguardState.ALTERNATE_BOUNCER || !(this.statusBarStateController.getState() != 1 || this.launchingAffordance || this.isPulseExpanding || this.qsExpanded);
        }
        return false;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("KeyguardBypassController:");
        PendingUnlock pendingUnlock = this.pendingUnlock;
        if (pendingUnlock != null) {
            printWriter.println("  mPendingUnlock.pendingUnlockType: " + pendingUnlock.pendingUnlockType);
            PendingUnlock pendingUnlock2 = this.pendingUnlock;
            Intrinsics.checkNotNull(pendingUnlock2);
            KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  mPendingUnlock.isStrongBiometric: "), pendingUnlock2.isStrongBiometric, printWriter);
        } else {
            printWriter.println("  mPendingUnlock: " + pendingUnlock);
        }
        printWriter.println("  bypassEnabled: " + getBypassEnabled());
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  canBypass: ", canBypass(), printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  bouncerShowing: ", this.bouncerShowing, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  altBouncerShowing: ", this.keyguardTransitionInteractor.getCurrentState() == KeyguardState.ALTERNATE_BOUNCER, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  isPulseExpanding: ", this.isPulseExpanding, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  launchingAffordance: ", this.launchingAffordance, printWriter);
        printWriter.println("  qSExpanded: " + this.qsExpanded);
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  hasFaceFeature: "), this.hasFaceFeature, printWriter);
        printWriter.println("  postureState: " + this.postureState);
    }

    public final boolean getBypassEnabled() {
        int i = this.bypassOverride;
        if (!(i != 1 ? i != 2 ? this.bypassEnabled : false : true) || !((KeyguardStateControllerImpl) this.keyguardStateController).mFaceEnrolledAndEnabled) {
            return false;
        }
        int i2 = this.configFaceAuthSupportedPosture;
        return i2 == 0 || this.postureState == i2;
    }

    public final void maybePerformPendingUnlock() {
        PendingUnlock pendingUnlock = this.pendingUnlock;
        if (pendingUnlock != null) {
            Intrinsics.checkNotNull(pendingUnlock);
            BiometricSourceType biometricSourceType = pendingUnlock.pendingUnlockType;
            PendingUnlock pendingUnlock2 = this.pendingUnlock;
            Intrinsics.checkNotNull(pendingUnlock2);
            if (onBiometricAuthenticated(biometricSourceType, pendingUnlock2.isStrongBiometric)) {
                BiometricUnlockController biometricUnlockController = this.unlockController;
                if (biometricUnlockController == null) {
                    biometricUnlockController = null;
                }
                PendingUnlock pendingUnlock3 = this.pendingUnlock;
                Intrinsics.checkNotNull(pendingUnlock3);
                BiometricSourceType biometricSourceType2 = pendingUnlock3.pendingUnlockType;
                PendingUnlock pendingUnlock4 = this.pendingUnlock;
                Intrinsics.checkNotNull(pendingUnlock4);
                biometricUnlockController.startWakeAndUnlock(biometricSourceType2, pendingUnlock4.isStrongBiometric);
                this.pendingUnlock = null;
            }
        }
    }

    public final void notifyListeners$2() {
        for (Object obj : this.listeners) {
            boolean isEnabled = Trace.isEnabled();
            if (isEnabled) {
                TraceUtilsKt.beginSlice("KeyguardBypassController#".concat(((Class) new KeyguardBypassController$notifyListeners$$inlined$forEachTraced$1(obj, JvmClassMappingKt.class, "javaClass", "getJavaClass(Ljava/lang/Object;)Ljava/lang/Class;", 1).get()).getName()));
            }
            try {
                ((OnBypassStateChangedListener) obj).onBypassStateChanged(getBypassEnabled());
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
            } catch (Throwable th) {
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
                throw th;
            }
        }
    }

    public final boolean onBiometricAuthenticated(BiometricSourceType biometricSourceType, boolean z) {
        if (biometricSourceType != BiometricSourceType.FACE || !getBypassEnabled()) {
            return true;
        }
        boolean canBypass = canBypass();
        if (!canBypass && (this.isPulseExpanding || this.qsExpanded)) {
            this.pendingUnlock = new PendingUnlock(biometricSourceType, z);
        }
        return canBypass;
    }

    public final void registerOnBypassStateChangedListener(OnBypassStateChangedListener onBypassStateChangedListener) {
        boolean isEmpty = this.listeners.isEmpty();
        this.listeners.add(onBypassStateChangedListener);
        if (isEmpty) {
            ((KeyguardStateControllerImpl) this.keyguardStateController).addCallback(this.faceAuthEnabledChangedCallback);
        }
    }

    public final void unregisterOnBypassStateChangedListener(OnBypassStateChangedListener onBypassStateChangedListener) {
        this.listeners.remove(onBypassStateChangedListener);
        if (this.listeners.isEmpty()) {
            ((KeyguardStateControllerImpl) this.keyguardStateController).removeCallback(this.faceAuthEnabledChangedCallback);
        }
    }
}
