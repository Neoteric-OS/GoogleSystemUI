package com.android.systemui.sensorprivacy;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.SensorPrivacyManager;
import android.os.Bundle;
import android.os.Handler;
import com.android.internal.util.FrameworkStatsLog;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KFunction;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class SensorUseStartedActivity extends Activity implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Handler bgHandler;
    public final KeyguardDismissUtil keyguardDismissUtil;
    public final KeyguardStateController keyguardStateController;
    public SensorUseDialog mDialog;
    public final IndividualSensorPrivacyController sensorPrivacyController;
    public IndividualSensorPrivacyController.Callback sensorPrivacyListener;
    public String sensorUsePackageName;
    public boolean unsuppressImmediately;
    public int sensor = -1;
    public final KFunction mBackCallback = new SensorUseStartedActivity$mBackCallback$1(0, this, SensorUseStartedActivity.class, "onBackInvoked", "onBackInvoked()V", 0);

    public SensorUseStartedActivity(IndividualSensorPrivacyController individualSensorPrivacyController, KeyguardStateController keyguardStateController, KeyguardDismissUtil keyguardDismissUtil, Handler handler) {
        this.sensorPrivacyController = individualSensorPrivacyController;
        this.keyguardStateController = keyguardStateController;
        this.keyguardDismissUtil = keyguardDismissUtil;
        this.bgHandler = handler;
    }

    public final void disableSensorPrivacy() {
        int i = this.sensor;
        if (i == Integer.MAX_VALUE) {
            ((IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController).setSensorBlocked(3, 1, false);
            ((IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController).setSensorBlocked(3, 2, false);
        } else {
            ((IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController).setSensorBlocked(3, i, false);
        }
        this.unsuppressImmediately = true;
        setResult(-1);
    }

    public final boolean isCameraBlocked(String str) {
        return getPackageManager().hasSystemFeature("android.hardware.type.automotive") ? ((IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController).mSensorPrivacyManager.isCameraPrivacyEnabled(str) : ((IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController).isSensorBlocked(2);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -2) {
            this.unsuppressImmediately = false;
            String str = this.sensorUsePackageName;
            FrameworkStatsLog.write(382, 2, str != null ? str : null);
        } else if (i == -1) {
            if (((IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController).mSensorPrivacyManager.requiresAuthentication()) {
                KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
                if (keyguardStateControllerImpl.mSecure && keyguardStateControllerImpl.mShowing) {
                    this.keyguardDismissUtil.executeWhenUnlocked(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.sensorprivacy.SensorUseStartedActivity$onClick$1
                        @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
                        public final boolean onDismiss() {
                            SensorUseStartedActivity sensorUseStartedActivity = SensorUseStartedActivity.this;
                            sensorUseStartedActivity.bgHandler.postDelayed(new SensorUseStartedActivity$onStop$1(sensorUseStartedActivity, 1), 200L);
                            return false;
                        }
                    }, false, true);
                }
            }
            disableSensorPrivacy();
            String str2 = this.sensorUsePackageName;
            FrameworkStatsLog.write(382, 1, str2 != null ? str2 : null);
        }
        finish();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setShowWhenLocked(true);
        setFinishOnTouchOutside(false);
        setResult(0);
        String stringExtra = getIntent().getStringExtra("android.intent.extra.PACKAGE_NAME");
        if (stringExtra == null) {
            return;
        }
        this.sensorUsePackageName = stringExtra;
        if (getIntent().getBooleanExtra(SensorPrivacyManager.EXTRA_ALL_SENSORS, false)) {
            this.sensor = Integer.MAX_VALUE;
            final int i = 0;
            IndividualSensorPrivacyController.Callback callback = new IndividualSensorPrivacyController.Callback(this) { // from class: com.android.systemui.sensorprivacy.SensorUseStartedActivity$onCreate$callback$1
                public final /* synthetic */ SensorUseStartedActivity this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.android.systemui.statusbar.policy.IndividualSensorPrivacyController.Callback
                public final void onSensorBlockedChanged(int i2, boolean z) {
                    switch (i) {
                        case 0:
                            SensorUseStartedActivity sensorUseStartedActivity = this.this$0;
                            if (!((IndividualSensorPrivacyControllerImpl) sensorUseStartedActivity.sensorPrivacyController).isSensorBlocked(1)) {
                                String str = sensorUseStartedActivity.sensorUsePackageName;
                                if (str == null) {
                                    str = null;
                                }
                                if (!sensorUseStartedActivity.isCameraBlocked(str)) {
                                    sensorUseStartedActivity.finish();
                                    break;
                                }
                            }
                            break;
                        default:
                            SensorUseStartedActivity sensorUseStartedActivity2 = this.this$0;
                            if (i2 == sensorUseStartedActivity2.sensor) {
                                if (i2 == 2) {
                                    String str2 = sensorUseStartedActivity2.sensorUsePackageName;
                                    if (str2 == null) {
                                        str2 = null;
                                    }
                                    if (!sensorUseStartedActivity2.isCameraBlocked(str2)) {
                                        sensorUseStartedActivity2.finish();
                                        break;
                                    }
                                }
                                if (i2 == 1 && !z) {
                                    sensorUseStartedActivity2.finish();
                                    break;
                                }
                            }
                            break;
                    }
                }
            };
            this.sensorPrivacyListener = callback;
            ((IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController).addCallback(callback);
            if (!((IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController).isSensorBlocked(1)) {
                String str = this.sensorUsePackageName;
                if (!isCameraBlocked(str != null ? str : null)) {
                    finish();
                    return;
                }
            }
        } else {
            int intExtra = getIntent().getIntExtra(SensorPrivacyManager.EXTRA_SENSOR, -1);
            if (intExtra == -1) {
                finish();
                return;
            }
            this.sensor = intExtra;
            final int i2 = 1;
            IndividualSensorPrivacyController.Callback callback2 = new IndividualSensorPrivacyController.Callback(this) { // from class: com.android.systemui.sensorprivacy.SensorUseStartedActivity$onCreate$callback$1
                public final /* synthetic */ SensorUseStartedActivity this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.android.systemui.statusbar.policy.IndividualSensorPrivacyController.Callback
                public final void onSensorBlockedChanged(int i22, boolean z) {
                    switch (i2) {
                        case 0:
                            SensorUseStartedActivity sensorUseStartedActivity = this.this$0;
                            if (!((IndividualSensorPrivacyControllerImpl) sensorUseStartedActivity.sensorPrivacyController).isSensorBlocked(1)) {
                                String str2 = sensorUseStartedActivity.sensorUsePackageName;
                                if (str2 == null) {
                                    str2 = null;
                                }
                                if (!sensorUseStartedActivity.isCameraBlocked(str2)) {
                                    sensorUseStartedActivity.finish();
                                    break;
                                }
                            }
                            break;
                        default:
                            SensorUseStartedActivity sensorUseStartedActivity2 = this.this$0;
                            if (i22 == sensorUseStartedActivity2.sensor) {
                                if (i22 == 2) {
                                    String str22 = sensorUseStartedActivity2.sensorUsePackageName;
                                    if (str22 == null) {
                                        str22 = null;
                                    }
                                    if (!sensorUseStartedActivity2.isCameraBlocked(str22)) {
                                        sensorUseStartedActivity2.finish();
                                        break;
                                    }
                                }
                                if (i22 == 1 && !z) {
                                    sensorUseStartedActivity2.finish();
                                    break;
                                }
                            }
                            break;
                    }
                }
            };
            this.sensorPrivacyListener = callback2;
            ((IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController).addCallback(callback2);
            if (this.sensor == 2) {
                String str2 = this.sensorUsePackageName;
                if (!isCameraBlocked(str2 != null ? str2 : null)) {
                    finish();
                    return;
                }
            }
            if (this.sensor == 1 && !((IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController).isSensorBlocked(1)) {
                finish();
                return;
            }
        }
        SensorUseDialog sensorUseDialog = new SensorUseDialog(this, this.sensor, this, this);
        this.mDialog = sensorUseDialog;
        sensorUseDialog.show();
        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, new SensorUseStartedActivity$sam$android_window_OnBackInvokedCallback$0((Function0) this.mBackCallback));
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        SensorUseDialog sensorUseDialog = this.mDialog;
        if (sensorUseDialog != null) {
            sensorUseDialog.dismiss();
        }
        IndividualSensorPrivacyController.Callback callback = this.sensorPrivacyListener;
        if (callback != null) {
            ((IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController).removeCallback(callback);
        }
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(new SensorUseStartedActivity$sam$android_window_OnBackInvokedCallback$0((Function0) this.mBackCallback));
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        if (isChangingConfigurations()) {
            return;
        }
        finish();
    }

    @Override // android.app.Activity
    public final void onNewIntent(Intent intent) {
        setIntent(intent);
        recreate();
    }

    @Override // android.app.Activity
    public final void onStart() {
        super.onStart();
        setSuppressed(true);
        this.unsuppressImmediately = false;
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
        if (this.unsuppressImmediately) {
            setSuppressed(false);
        } else {
            this.bgHandler.postDelayed(new SensorUseStartedActivity$onStop$1(this, 0), 2000L);
        }
    }

    public final void setSuppressed(boolean z) {
        int i = this.sensor;
        if (i != Integer.MAX_VALUE) {
            IndividualSensorPrivacyControllerImpl individualSensorPrivacyControllerImpl = (IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController;
            individualSensorPrivacyControllerImpl.mSensorPrivacyManager.suppressSensorPrivacyReminders(i, z, ((UserTrackerImpl) individualSensorPrivacyControllerImpl.mUserTracker).getUserId());
        } else {
            IndividualSensorPrivacyControllerImpl individualSensorPrivacyControllerImpl2 = (IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController;
            individualSensorPrivacyControllerImpl2.mSensorPrivacyManager.suppressSensorPrivacyReminders(1, z, ((UserTrackerImpl) individualSensorPrivacyControllerImpl2.mUserTracker).getUserId());
            IndividualSensorPrivacyControllerImpl individualSensorPrivacyControllerImpl3 = (IndividualSensorPrivacyControllerImpl) this.sensorPrivacyController;
            individualSensorPrivacyControllerImpl3.mSensorPrivacyManager.suppressSensorPrivacyReminders(2, z, ((UserTrackerImpl) individualSensorPrivacyControllerImpl3.mUserTracker).getUserId());
        }
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
    }
}
