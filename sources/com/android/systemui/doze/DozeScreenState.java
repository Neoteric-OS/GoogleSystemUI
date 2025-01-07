package com.android.systemui.doze;

import android.os.Handler;
import android.util.Log;
import android.view.Display;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.UdfpsController;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.DozeServiceHost;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.wakelock.SettableWakeLock;
import com.android.systemui.util.wakelock.WakeLock;
import dagger.internal.DelegateFactory;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeScreenState implements DozeMachine.Part {
    public static final boolean DEBUG = DozeService.DEBUG;
    public final AuthController mAuthController;
    public final AnonymousClass1 mAuthControllerCallback;
    public final DozeServiceHost mDozeHost;
    public final DozeLog mDozeLog;
    public final DozeScreenBrightness mDozeScreenBrightness;
    public final DozeMachine.Service mDozeService;
    public final Handler mHandler;
    public final DozeParameters mParameters;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public UdfpsController mUdfpsController;
    public final DelegateFactory mUdfpsControllerProvider;
    public final SettableWakeLock mWakeLock;
    public final DozeScreenState$$ExternalSyntheticLambda0 mApplyPendingScreenState = new Runnable() { // from class: com.android.systemui.doze.DozeScreenState$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            DozeScreenState dozeScreenState = DozeScreenState.this;
            UdfpsController udfpsController = dozeScreenState.mUdfpsController;
            if (udfpsController == null || !udfpsController.mOnFingerDown) {
                dozeScreenState.applyScreenState(dozeScreenState.mPendingScreenState);
                dozeScreenState.mPendingScreenState = 0;
                return;
            }
            int i = dozeScreenState.mPendingScreenState;
            DozeLogger dozeLogger = dozeScreenState.mDozeLog.mLogger;
            dozeLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            DozeLogger$logDisplayStateDelayedByUdfps$2 dozeLogger$logDisplayStateDelayedByUdfps$2 = DozeLogger$logDisplayStateDelayedByUdfps$2.INSTANCE;
            LogBuffer logBuffer = dozeLogger.buffer;
            LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logDisplayStateDelayedByUdfps$2, null);
            ((LogMessageImpl) obtain).str1 = Display.stateToString(i);
            logBuffer.commit(obtain);
            dozeScreenState.mHandler.postDelayed(dozeScreenState.mApplyPendingScreenState, 1200L);
        }
    };
    public int mPendingScreenState = 0;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.doze.DozeScreenState$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.biometrics.AuthController$Callback, com.android.systemui.doze.DozeScreenState$1] */
    public DozeScreenState(DozeMachine.Service service, Handler handler, DozeServiceHost dozeServiceHost, DozeParameters dozeParameters, WakeLock wakeLock, AuthController authController, DelegateFactory delegateFactory, DozeLog dozeLog, DozeScreenBrightness dozeScreenBrightness, SelectedUserInteractor selectedUserInteractor) {
        ?? r0 = new AuthController.Callback() { // from class: com.android.systemui.doze.DozeScreenState.1
            @Override // com.android.systemui.biometrics.AuthController.Callback
            public final void onAllAuthenticatorsRegistered(int i) {
                if (i == 2) {
                    DozeScreenState.this.updateUdfpsController();
                }
            }

            @Override // com.android.systemui.biometrics.AuthController.Callback
            public final void onEnrollmentsChanged(int i) {
                if (i == 2) {
                    DozeScreenState.this.updateUdfpsController();
                }
            }
        };
        this.mAuthControllerCallback = r0;
        this.mDozeService = service;
        this.mHandler = handler;
        this.mParameters = dozeParameters;
        this.mDozeHost = dozeServiceHost;
        this.mWakeLock = new SettableWakeLock(wakeLock, "DozeScreenState");
        this.mAuthController = authController;
        this.mUdfpsControllerProvider = delegateFactory;
        this.mDozeLog = dozeLog;
        this.mDozeScreenBrightness = dozeScreenBrightness;
        this.mSelectedUserInteractor = selectedUserInteractor;
        updateUdfpsController();
        if (this.mUdfpsController == null) {
            authController.addCallback(r0);
        }
    }

    public final void applyScreenState(int i) {
        if (i != 0) {
            if (DEBUG) {
                Log.d("DozeScreenState", "setDozeScreenState(" + i + ")");
            }
            this.mDozeService.setDozeScreenState(i);
            if (i == 3) {
                this.mDozeScreenBrightness.updateBrightnessAndReady(false);
            }
            this.mPendingScreenState = 0;
            this.mWakeLock.setAcquired(false);
        }
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void destroy() {
        this.mAuthController.removeCallback(this.mAuthControllerCallback);
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x001a, code lost:
    
        if (r7.getDisplayNeedsBlanking() != false) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0023, code lost:
    
        if (r7.mControlScreenOffAnimation != false) goto L5;
     */
    /* JADX WARN: Removed duplicated region for block: B:58:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0159  */
    @Override // com.android.systemui.doze.DozeMachine.Part
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void transitionTo(com.android.systemui.doze.DozeMachine.State r19, com.android.systemui.doze.DozeMachine.State r20) {
        /*
            Method dump skipped, instructions count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.doze.DozeScreenState.transitionTo(com.android.systemui.doze.DozeMachine$State, com.android.systemui.doze.DozeMachine$State):void");
    }

    public final void updateUdfpsController() {
        if (this.mAuthController.isUdfpsEnrolled(this.mSelectedUserInteractor.getSelectedUserId())) {
            this.mUdfpsController = (UdfpsController) this.mUdfpsControllerProvider.get();
        } else {
            this.mUdfpsController = null;
        }
    }
}
