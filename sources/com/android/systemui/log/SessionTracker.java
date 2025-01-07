package com.android.systemui.log;

import android.app.StatusBarManager;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.RemoteException;
import android.os.UserManager;
import android.util.Log;
import com.android.internal.logging.InstanceId;
import com.android.internal.logging.InstanceIdSequence;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.CoreStartable;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.process.ProcessWrapper;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SessionTracker implements CoreStartable {
    public static final boolean DEBUG = Log.isLoggable("SessionTracker", 3);
    public final AuthController mAuthController;
    public boolean mKeyguardSessionStarted;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final ProcessWrapper mProcessWrapper;
    public final IStatusBarService mStatusBarManagerService;
    public final UiEventLogger mUiEventLogger;
    public final InstanceIdSequence mInstanceIdGenerator = new InstanceIdSequence(1048576);
    public final Map mSessionToInstanceId = new HashMap();
    public KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.log.SessionTracker.1
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onStartedGoingToSleep$1() {
            SessionTracker sessionTracker = SessionTracker.this;
            if (sessionTracker.mKeyguardSessionStarted) {
                sessionTracker.endSession(1, SessionUiEvent.KEYGUARD_SESSION_END_GOING_TO_SLEEP);
            }
            sessionTracker.mKeyguardSessionStarted = true;
            sessionTracker.startSession(1);
        }
    };
    public final AnonymousClass2 mKeyguardStateCallback = new KeyguardStateController.Callback() { // from class: com.android.systemui.log.SessionTracker.2
        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardShowingChanged() {
            SessionTracker sessionTracker = SessionTracker.this;
            boolean z = sessionTracker.mKeyguardSessionStarted;
            boolean z2 = ((KeyguardStateControllerImpl) sessionTracker.mKeyguardStateController).mShowing;
            if (z2 && !z) {
                sessionTracker.mKeyguardSessionStarted = true;
                sessionTracker.startSession(1);
            } else {
                if (z2 || !z) {
                    return;
                }
                sessionTracker.mKeyguardSessionStarted = false;
                sessionTracker.endSession(1, SessionUiEvent.KEYGUARD_SESSION_END_KEYGUARD_GOING_AWAY);
            }
        }
    };
    public final AnonymousClass3 mAuthControllerCallback = new AuthController.Callback() { // from class: com.android.systemui.log.SessionTracker.3
        @Override // com.android.systemui.biometrics.AuthController.Callback
        public final void onBiometricPromptDismissed() {
            SessionTracker.this.endSession(2, null);
        }

        @Override // com.android.systemui.biometrics.AuthController.Callback
        public final void onBiometricPromptShown() {
            SessionTracker.this.startSession(2);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    enum SessionUiEvent implements UiEventLogger.UiEventEnum {
        KEYGUARD_SESSION_END_KEYGUARD_GOING_AWAY(1354),
        KEYGUARD_SESSION_END_GOING_TO_SLEEP(1355);

        private final int mId;

        SessionUiEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.log.SessionTracker$2] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.systemui.log.SessionTracker$3] */
    public SessionTracker(IStatusBarService iStatusBarService, AuthController authController, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardStateController keyguardStateController, UiEventLogger uiEventLogger, ProcessWrapper processWrapper) {
        this.mStatusBarManagerService = iStatusBarService;
        this.mAuthController = authController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mKeyguardStateController = keyguardStateController;
        this.mUiEventLogger = uiEventLogger;
        this.mProcessWrapper = processWrapper;
    }

    public static String getString(int i) {
        return i == 1 ? "KEYGUARD" : i == 2 ? "BIOMETRIC_PROMPT" : AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "unknownType=");
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        for (Integer num : StatusBarManager.ALL_SESSIONS) {
            printWriter.println("  " + getString(num.intValue()) + " instanceId=" + this.mSessionToInstanceId.get(num));
        }
    }

    public final void endSession(int i, SessionUiEvent sessionUiEvent) {
        ProcessWrapper processWrapper = this.mProcessWrapper;
        if (((HashMap) this.mSessionToInstanceId).getOrDefault(Integer.valueOf(i), null) == null) {
            Log.e("SessionTracker", "session [" + getString(i) + "] was not started");
            return;
        }
        InstanceId instanceId = (InstanceId) this.mSessionToInstanceId.get(Integer.valueOf(i));
        this.mSessionToInstanceId.put(Integer.valueOf(i), null);
        try {
            boolean z = DEBUG;
            if (z) {
                Log.d("SessionTracker", "Session end for [" + getString(i) + "] id=" + instanceId);
            }
            if (sessionUiEvent != null) {
                this.mUiEventLogger.log(sessionUiEvent, instanceId);
            }
            if (UserManager.isVisibleBackgroundUsersEnabled()) {
                processWrapper.getClass();
                if (!ProcessWrapper.isSystemUser() && !ProcessWrapper.isForegroundUser()) {
                    if (z) {
                        Log.d("SessionTracker", "Status bar manager is disabled for visible background users");
                        return;
                    }
                    return;
                }
            }
            this.mStatusBarManagerService.onSessionEnded(i, instanceId);
        } catch (RemoteException e) {
            Log.e("SessionTracker", "Unable to send onSessionEnded for session=[" + getString(i) + "]", e);
        }
    }

    public final InstanceId getSessionId(int i) {
        return (InstanceId) ((HashMap) this.mSessionToInstanceId).getOrDefault(Integer.valueOf(i), null);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mAuthController.addCallback(this.mAuthControllerCallback);
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateMonitorCallback);
        AnonymousClass2 anonymousClass2 = this.mKeyguardStateCallback;
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        keyguardStateControllerImpl.addCallback(anonymousClass2);
        if (keyguardStateControllerImpl.mShowing) {
            this.mKeyguardSessionStarted = true;
            startSession(1);
        }
    }

    public final void startSession(int i) {
        if (((HashMap) this.mSessionToInstanceId).getOrDefault(Integer.valueOf(i), null) != null) {
            Log.e("SessionTracker", "session [" + getString(i) + "] was already started");
            return;
        }
        InstanceId newInstanceId = this.mInstanceIdGenerator.newInstanceId();
        this.mSessionToInstanceId.put(Integer.valueOf(i), newInstanceId);
        boolean isVisibleBackgroundUsersEnabled = UserManager.isVisibleBackgroundUsersEnabled();
        boolean z = DEBUG;
        if (isVisibleBackgroundUsersEnabled) {
            this.mProcessWrapper.getClass();
            if (!ProcessWrapper.isSystemUser() && !ProcessWrapper.isForegroundUser()) {
                if (z) {
                    Log.d("SessionTracker", "Status bar manager is disabled for visible background users");
                    return;
                }
                return;
            }
        }
        if (z) {
            try {
                Log.d("SessionTracker", "Session start for [" + getString(i) + "] id=" + newInstanceId);
            } catch (RemoteException e) {
                Log.e("SessionTracker", "Unable to send onSessionStarted for session=[" + getString(i) + "]", e);
                return;
            }
        }
        this.mStatusBarManagerService.onSessionStarted(i, newInstanceId);
    }
}
