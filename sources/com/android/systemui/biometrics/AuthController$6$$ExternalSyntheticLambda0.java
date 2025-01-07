package com.android.systemui.biometrics;

import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import android.util.Log;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.UdfpsController;
import com.android.systemui.util.concurrency.ExecutionImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AuthController$6$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ AuthController$6$$ExternalSyntheticLambda0(AuthController.AnonymousClass6 anonymousClass6, List list) {
        this.f$0 = anonymousClass6;
        this.f$1 = list;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                AuthController.AnonymousClass6 anonymousClass6 = (AuthController.AnonymousClass6) this.f$0;
                List list = this.f$1;
                final AuthController authController = AuthController.this;
                ExecutionImpl executionImpl = authController.mExecution;
                executionImpl.assertIsMainThread();
                Log.d("AuthController", "handleAllFingerprintAuthenticatorsRegistered | sensors: " + Arrays.toString(list.toArray()));
                authController.mAllFingerprintAuthenticatorsRegistered = true;
                authController.mFpProps = list;
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (FingerprintSensorPropertiesInternal fingerprintSensorPropertiesInternal : authController.mFpProps) {
                    if (fingerprintSensorPropertiesInternal.isAnyUdfpsType()) {
                        arrayList.add(fingerprintSensorPropertiesInternal);
                    }
                    if (fingerprintSensorPropertiesInternal.isAnySidefpsType()) {
                        arrayList2.add(fingerprintSensorPropertiesInternal);
                    }
                }
                if (arrayList.isEmpty()) {
                    arrayList = null;
                }
                authController.mUdfpsProps = arrayList;
                if (arrayList != null) {
                    UdfpsController udfpsController = (UdfpsController) authController.mUdfpsControllerFactory.get();
                    authController.mUdfpsController = udfpsController;
                    udfpsController.mCallbacks.add(new UdfpsController.Callback() { // from class: com.android.systemui.biometrics.AuthController.3
                        public AnonymousClass3() {
                        }

                        @Override // com.android.systemui.biometrics.UdfpsController.Callback
                        public final void onFingerDown() {
                            AuthDialog authDialog = AuthController.this.mCurrentDialog;
                            if (authDialog != null) {
                                AuthContainerView authContainerView = (AuthContainerView) authDialog;
                                if (authContainerView.mBiometricView == null) {
                                    Log.e("AuthContainerView", "onPointerDown(): mBiometricView is null");
                                } else if (authContainerView.mFailedModalities.contains(8)) {
                                    Log.d("AuthContainerView", "retrying failed modalities (pointer down)");
                                    authContainerView.mFailedModalities.remove(8);
                                    authContainerView.mBiometricCallback.onButtonTryAgain();
                                }
                            }
                        }

                        @Override // com.android.systemui.biometrics.UdfpsController.Callback
                        public final void onFingerUp() {
                        }
                    });
                    UdfpsController udfpsController2 = authController.mUdfpsController;
                    udfpsController2.mAuthControllerUpdateUdfpsLocation = new AuthController$$ExternalSyntheticLambda2(authController, 0);
                    udfpsController2.mUdfpsDisplayMode = new UdfpsDisplayMode(authController.mContext, executionImpl, authController, (UdfpsLogger) authController.mUdfpsLogger.get());
                    authController.mUdfpsBounds = ((FingerprintSensorPropertiesInternal) authController.mUdfpsProps.get(0)).getLocation().getRect();
                }
                if (arrayList2.isEmpty()) {
                    arrayList2 = null;
                }
                authController.mSidefpsProps = arrayList2;
                authController.mFingerprintManager.registerBiometricStateListener(new AuthController.AnonymousClass4(authController, 0));
                authController.updateSensorLocations();
                Iterator it = ((HashSet) authController.mCallbacks).iterator();
                while (it.hasNext()) {
                    ((AuthController.Callback) it.next()).onAllAuthenticatorsRegistered(2);
                }
                break;
            default:
                AuthController.AnonymousClass7 anonymousClass7 = (AuthController.AnonymousClass7) this.f$0;
                List list2 = this.f$1;
                AuthController authController2 = AuthController.this;
                authController2.mExecution.assertIsMainThread();
                Log.d("AuthController", "handleAllFaceAuthenticatorsRegistered | sensors: " + Arrays.toString(list2.toArray()));
                authController2.mFaceManager.registerBiometricStateListener(new AuthController.AnonymousClass4(authController2, 1));
                Iterator it2 = ((HashSet) authController2.mCallbacks).iterator();
                while (it2.hasNext()) {
                    ((AuthController.Callback) it2.next()).onAllAuthenticatorsRegistered(8);
                }
                break;
        }
    }

    public /* synthetic */ AuthController$6$$ExternalSyntheticLambda0(AuthController.AnonymousClass7 anonymousClass7, List list) {
        this.f$0 = anonymousClass7;
        this.f$1 = list;
    }
}
