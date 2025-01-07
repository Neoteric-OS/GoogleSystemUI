package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.core.app.NotificationCompat$BigTextStyle;
import androidx.core.app.NotificationCompat$Builder;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.settingslib.fuelgauge.BatterySaverUtils;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator$createActivityTransitionController$1;
import com.android.systemui.animation.Expandable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.power.PowerNotificationWarnings;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import com.google.android.systemui.googlebattery.AdaptiveChargingManager;
import com.google.android.systemui.googlebattery.GoogleBatteryManager;
import com.google.android.systemui.power.DwellTempDefenderNotification;
import com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.aidl.SurfaceType;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.internal.ContextScope;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PowerNotificationWarningsGoogleImpl extends PowerNotificationWarnings {
    public final ActivityStarter mActivityStarter;
    public AdaptiveChargingNotification mAdaptiveChargingNotification;
    public final Lazy mBatteryControllerLazy;
    public BatteryDefenderNotificationHandler mBatteryDefenderNotificationHandler;
    public final BatteryEventClient mBatteryEventClient;
    public BatteryInfoBroadcast mBatteryInfoBroadcast;
    public BatterySaverConfirmationDialog mBatterySaverConfirmationDialog;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mBatterySaverConfirmationDialogProvider;
    public final BroadcastDispatcher mBroadcastDispatcher;
    final BroadcastReceiver mBroadcastReceiver;
    public final BroadcastSender mBroadcastSender;
    public ChargeLimitController mChargeLimitController;
    public ChargeLimitDiscoveryNotification mChargeLimitDiscoveryNotification;
    public final Context mContext;
    public final EnhancedEstimates mEnhancedEstimates;
    public final Executor mExecutor;
    public final GlobalSettings mGlobalSettings;
    public final Handler mHandler;
    public IncompatibleChargerNotification mIncompatibleChargerNotification;
    LowPowerWarningsController mLowPowerWarningsController;
    public final SecureSettings mSecureSettings;
    public SevereLowBatteryNotification mSevereLowBatteryNotification;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mSevereLowBatteryNotificationProvider;
    public final UserTracker mUserTracker;
    public WirelessChargingNotification mWirelessChargingNotification;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl$1, reason: invalid class name */
    public final class AnonymousClass1 extends BroadcastReceiver {
        public AnonymousClass1() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, final Intent intent) {
            char c;
            String action;
            UiEventLogger uiEventLogger;
            String action2;
            UiEventLogger uiEventLogger2;
            String action3;
            int hashCode;
            final int i = 1;
            final int i2 = 0;
            if (intent == null || intent.getAction() == null) {
                return;
            }
            Log.d("PowerNotificationWarningsGoogleImpl", "onReceive: " + intent.getAction());
            String action4 = intent.getAction();
            int hashCode2 = action4.hashCode();
            if (hashCode2 == -1841120158) {
                if (action4.equals("systemui.power.action.START_FLIPENDO")) {
                    c = 1;
                }
                c = 65535;
            } else if (hashCode2 != 1207904925) {
                if (hashCode2 == 1349225914 && action4.equals("PNW.dismissSevereLowBatteryWarning")) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (action4.equals("PNW.startSaverConfirmation")) {
                    c = 0;
                }
                c = 65535;
            }
            if (c == 0) {
                PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl = PowerNotificationWarningsGoogleImpl.this;
                powerNotificationWarningsGoogleImpl.mLowPowerWarningsController.cancelNotification();
                powerNotificationWarningsGoogleImpl.mBatterySaverConfirmationDialog = (BatterySaverConfirmationDialog) powerNotificationWarningsGoogleImpl.mBatterySaverConfirmationDialogProvider.get();
                WeakReference weakReference = (WeakReference) ((BatteryControllerImpl) ((BatteryController) powerNotificationWarningsGoogleImpl.mBatteryControllerLazy.get())).mPowerSaverStartExpandable.get();
                final BatterySaverConfirmationDialog batterySaverConfirmationDialog = powerNotificationWarningsGoogleImpl.mBatterySaverConfirmationDialog;
                Expandable expandable = weakReference != null ? (Expandable) weakReference.get() : null;
                SystemUIDialog systemUIDialog = batterySaverConfirmationDialog.mConfirmationDialog;
                if (systemUIDialog == null || !systemUIDialog.isShowing()) {
                    SystemUIDialog systemUIDialog2 = batterySaverConfirmationDialog.mConfirmationDialog;
                    if (systemUIDialog2 != null) {
                        systemUIDialog2.show();
                        return;
                    }
                    View inflate = LayoutInflater.from(batterySaverConfirmationDialog.mContext).inflate(R.layout.battery_saver_confirmation_content, (ViewGroup) null);
                    final RadioButton radioButton = (RadioButton) inflate.findViewById(R.id.standard_button);
                    final RadioButton radioButton2 = (RadioButton) inflate.findViewById(R.id.extreme_button);
                    batterySaverConfirmationDialog.mIsStandardMode = true;
                    inflate.findViewById(R.id.standard_option_layout).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda3
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            switch (i2) {
                                case 0:
                                    BatterySaverConfirmationDialog batterySaverConfirmationDialog2 = batterySaverConfirmationDialog;
                                    RadioButton radioButton3 = radioButton;
                                    RadioButton radioButton4 = radioButton2;
                                    batterySaverConfirmationDialog2.mIsStandardMode = true;
                                    radioButton3.setChecked(true);
                                    radioButton4.setChecked(!batterySaverConfirmationDialog2.mIsStandardMode);
                                    break;
                                default:
                                    BatterySaverConfirmationDialog batterySaverConfirmationDialog3 = batterySaverConfirmationDialog;
                                    RadioButton radioButton5 = radioButton;
                                    RadioButton radioButton6 = radioButton2;
                                    batterySaverConfirmationDialog3.mIsStandardMode = false;
                                    radioButton5.setChecked(false);
                                    radioButton6.setChecked(!batterySaverConfirmationDialog3.mIsStandardMode);
                                    break;
                            }
                        }
                    });
                    inflate.findViewById(R.id.extreme_option_layout).setOnClickListener(new View.OnClickListener() { // from class: com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda3
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            switch (i) {
                                case 0:
                                    BatterySaverConfirmationDialog batterySaverConfirmationDialog2 = batterySaverConfirmationDialog;
                                    RadioButton radioButton3 = radioButton;
                                    RadioButton radioButton4 = radioButton2;
                                    batterySaverConfirmationDialog2.mIsStandardMode = true;
                                    radioButton3.setChecked(true);
                                    radioButton4.setChecked(!batterySaverConfirmationDialog2.mIsStandardMode);
                                    break;
                                default:
                                    BatterySaverConfirmationDialog batterySaverConfirmationDialog3 = batterySaverConfirmationDialog;
                                    RadioButton radioButton5 = radioButton;
                                    RadioButton radioButton6 = radioButton2;
                                    batterySaverConfirmationDialog3.mIsStandardMode = false;
                                    radioButton5.setChecked(false);
                                    radioButton6.setChecked(!batterySaverConfirmationDialog3.mIsStandardMode);
                                    break;
                            }
                        }
                    });
                    final Button button = (Button) inflate.findViewById(R.id.setup_button);
                    button.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda5
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            BatterySaverConfirmationDialog batterySaverConfirmationDialog2 = BatterySaverConfirmationDialog.this;
                            Button button2 = button;
                            batterySaverConfirmationDialog2.getClass();
                            batterySaverConfirmationDialog2.log(BatteryMetricEvent.SAVER_CONFIRMATION_DIALOG_SETUP);
                            DialogTransitionAnimator dialogTransitionAnimator = batterySaverConfirmationDialog2.mDialogTransitionAnimator;
                            dialogTransitionAnimator.getClass();
                            DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController$default = DialogTransitionAnimator.createActivityTransitionController$default(dialogTransitionAnimator, button2);
                            if (createActivityTransitionController$default == null) {
                                batterySaverConfirmationDialog2.mConfirmationDialog.dismiss();
                            }
                            batterySaverConfirmationDialog2.mActivityStarter.startActivity(new Intent("android.settings.batterysaver.flipendo.onboarding"), true, (ActivityTransitionAnimator.Controller) createActivityTransitionController$default);
                        }
                    });
                    SystemUIDialog create = batterySaverConfirmationDialog.mSystemUIDialogFactory.create();
                    batterySaverConfirmationDialog.mConfirmationDialog = create;
                    create.setTitle(R.string.saver_confirmation_dialog_title);
                    batterySaverConfirmationDialog.mConfirmationDialog.setMessage(R.string.saver_confirmation_dialog_subtitle);
                    batterySaverConfirmationDialog.mConfirmationDialog.setView(inflate);
                    SystemUIDialog systemUIDialog3 = batterySaverConfirmationDialog.mConfirmationDialog;
                    systemUIDialog3.getClass();
                    SystemUIDialog.setShowForAllUsers(systemUIDialog3);
                    batterySaverConfirmationDialog.mConfirmationDialog.setCanceledOnTouchOutside(true);
                    batterySaverConfirmationDialog.mConfirmationDialog.setPositiveButton(R.string.battery_saver_confirmation_ok, new DialogInterface.OnClickListener() { // from class: com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda0
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i3) {
                            int i4 = i2;
                            final BatterySaverConfirmationDialog batterySaverConfirmationDialog2 = batterySaverConfirmationDialog;
                            batterySaverConfirmationDialog2.getClass();
                            switch (i4) {
                                case 0:
                                    batterySaverConfirmationDialog2.log(BatteryMetricEvent.SAVER_CONFIRMATION_DIALOG_TURN_ON);
                                    dialogInterface.dismiss();
                                    AsyncTask.execute(new Runnable() { // from class: com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda2
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            BatterySaverConfirmationDialog batterySaverConfirmationDialog3 = BatterySaverConfirmationDialog.this;
                                            if (!batterySaverConfirmationDialog3.mIsStandardMode) {
                                                Context context2 = batterySaverConfirmationDialog3.mContext;
                                                try {
                                                    Bundle bundle = new Bundle(1);
                                                    bundle.putInt("update_flipendo_mode", 1);
                                                    context2.getContentResolver().call("com.google.android.flipendo.api", "update_flipendo_mode_method", (String) null, bundle);
                                                } catch (Exception e) {
                                                    Log.e("PowerUtils", "applyExtremeSaverMode() failed", e);
                                                }
                                            }
                                            BatterySaverUtils.setPowerSaveMode(batterySaverConfirmationDialog3.mContext, true, false, 1);
                                            Context context3 = batterySaverConfirmationDialog3.mContext;
                                            Settings.Secure.putInt(context3.getContentResolver(), "low_power_warning_acknowledged", 1);
                                            Settings.Secure.putInt(context3.getContentResolver(), "extra_low_power_warning_acknowledged", 1);
                                        }
                                    });
                                    break;
                                default:
                                    batterySaverConfirmationDialog2.log(BatteryMetricEvent.SAVER_CONFIRMATION_DIALOG_CANCEL);
                                    dialogInterface.dismiss();
                                    break;
                            }
                        }
                    });
                    batterySaverConfirmationDialog.mConfirmationDialog.setNeutralButton(R.string.saver_confirmation_dialog_dismiss_text, new DialogInterface.OnClickListener() { // from class: com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda0
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i3) {
                            int i4 = i;
                            final BatterySaverConfirmationDialog batterySaverConfirmationDialog2 = batterySaverConfirmationDialog;
                            batterySaverConfirmationDialog2.getClass();
                            switch (i4) {
                                case 0:
                                    batterySaverConfirmationDialog2.log(BatteryMetricEvent.SAVER_CONFIRMATION_DIALOG_TURN_ON);
                                    dialogInterface.dismiss();
                                    AsyncTask.execute(new Runnable() { // from class: com.google.android.systemui.power.BatterySaverConfirmationDialog$$ExternalSyntheticLambda2
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            BatterySaverConfirmationDialog batterySaverConfirmationDialog3 = BatterySaverConfirmationDialog.this;
                                            if (!batterySaverConfirmationDialog3.mIsStandardMode) {
                                                Context context2 = batterySaverConfirmationDialog3.mContext;
                                                try {
                                                    Bundle bundle = new Bundle(1);
                                                    bundle.putInt("update_flipendo_mode", 1);
                                                    context2.getContentResolver().call("com.google.android.flipendo.api", "update_flipendo_mode_method", (String) null, bundle);
                                                } catch (Exception e) {
                                                    Log.e("PowerUtils", "applyExtremeSaverMode() failed", e);
                                                }
                                            }
                                            BatterySaverUtils.setPowerSaveMode(batterySaverConfirmationDialog3.mContext, true, false, 1);
                                            Context context3 = batterySaverConfirmationDialog3.mContext;
                                            Settings.Secure.putInt(context3.getContentResolver(), "low_power_warning_acknowledged", 1);
                                            Settings.Secure.putInt(context3.getContentResolver(), "extra_low_power_warning_acknowledged", 1);
                                        }
                                    });
                                    break;
                                default:
                                    batterySaverConfirmationDialog2.log(BatteryMetricEvent.SAVER_CONFIRMATION_DIALOG_CANCEL);
                                    dialogInterface.dismiss();
                                    break;
                            }
                        }
                    }, true);
                    if (expandable != null) {
                        DialogTransitionAnimator.Controller dialogTransitionController = expandable.dialogTransitionController(null);
                        if (dialogTransitionController != null) {
                            batterySaverConfirmationDialog.mDialogTransitionAnimator.show(batterySaverConfirmationDialog.mConfirmationDialog, dialogTransitionController, false);
                        } else {
                            batterySaverConfirmationDialog.mConfirmationDialog.show();
                        }
                    } else {
                        batterySaverConfirmationDialog.mConfirmationDialog.show();
                    }
                    batterySaverConfirmationDialog.log(BatteryMetricEvent.SAVER_CONFIRMATION_DIALOG);
                    return;
                }
                return;
            }
            if (c == 1) {
                PowerNotificationWarningsGoogleImpl.this.mExecutor.execute(new Runnable() { // from class: com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PowerNotificationWarningsGoogleImpl.AnonymousClass1 anonymousClass1 = PowerNotificationWarningsGoogleImpl.AnonymousClass1.this;
                        Intent intent2 = intent;
                        try {
                            PowerNotificationWarningsGoogleImpl.this.mContext.getContentResolver().call("com.google.android.flipendo.api", "force_enable_flipendo_method", (String) null, (Bundle) null);
                        } catch (Exception e) {
                            Log.e("PowerUtils", "enableFlipendo() failed", e);
                        }
                        String stringExtra = intent2.getStringExtra("extra_severe_low_battery_notification");
                        SevereLowBatteryNotification severeLowBatteryNotification = PowerNotificationWarningsGoogleImpl.this.mSevereLowBatteryNotification;
                        severeLowBatteryNotification.getClass();
                        Log.d("SevereLowBatteryNotification", "logStartFlipendo() from ".concat(stringExtra));
                        String str = stringExtra.equals("low_battery_notification_turn_on_ebs") ? BatteryMetricEvent.SEVERE_LOW_BATTERY_NOTIFICATION_TURN_ON_EBS_CLICK_TURN_ON : stringExtra.equals("low_battery_notification_switch_to_ebs") ? BatteryMetricEvent.SEVERE_LOW_BATTERY_NOTIFICATION_SWITCH_TO_EBS_CLICK_SWITCH : "";
                        if (str instanceof BatteryMetricEvent) {
                            severeLowBatteryNotification.logEvent((BatteryMetricEvent) str);
                        }
                    }
                });
                return;
            }
            if (c == 2) {
                String stringExtra = intent.getStringExtra("extra_severe_low_battery_notification");
                SevereLowBatteryNotification severeLowBatteryNotification = PowerNotificationWarningsGoogleImpl.this.mSevereLowBatteryNotification;
                severeLowBatteryNotification.getClass();
                Log.d("SevereLowBatteryNotification", "dismiss(), source: ".concat(stringExtra));
                String str = stringExtra.equals("low_battery_notification_turn_on_ebs") ? BatteryMetricEvent.SEVERE_LOW_BATTERY_NOTIFICATION_TURN_ON_EBS_DISMISS : stringExtra.equals("low_battery_notification_switch_to_ebs") ? BatteryMetricEvent.SEVERE_LOW_BATTERY_NOTIFICATION_SWITCH_TO_EBS_DISMISS : "";
                if (str instanceof BatteryMetricEvent) {
                    severeLowBatteryNotification.logEvent((BatteryMetricEvent) str);
                }
                Log.d("SevereLowBatteryNotification", "cancel()");
                ((NotificationManager) severeLowBatteryNotification.notificationManager$delegate.getValue()).cancelAsUser("low_battery", 3, UserHandle.ALL);
                PowerNotificationWarningsGoogleImpl.this.dismissLowBatteryWarning();
                return;
            }
            BatteryInfoBroadcast batteryInfoBroadcast = PowerNotificationWarningsGoogleImpl.this.mBatteryInfoBroadcast;
            batteryInfoBroadcast.mExecutor.execute(new BatteryInfoBroadcast$$ExternalSyntheticLambda0(batteryInfoBroadcast, intent));
            final LowPowerWarningsController lowPowerWarningsController = PowerNotificationWarningsGoogleImpl.this.mLowPowerWarningsController;
            lowPowerWarningsController.executor.execute(new Runnable() { // from class: com.google.android.systemui.power.LowPowerWarningsController$dispatchIntent$1
                @Override // java.lang.Runnable
                public final void run() {
                    LowPowerWarningsController lowPowerWarningsController2 = LowPowerWarningsController.this;
                    Intent intent2 = intent;
                    lowPowerWarningsController2.getClass();
                    String action5 = intent2.getAction();
                    if (action5 != null) {
                        int hashCode3 = action5.hashCode();
                        if (hashCode3 == 1019184907) {
                            if (action5.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                                lowPowerWarningsController2.cancelNotification();
                                return;
                            }
                            return;
                        }
                        if (hashCode3 != 1779291251) {
                            if (hashCode3 == 1913925732 && action5.equals("com.android.settingslib.fuelgauge.ACTION_SAVER_STATE_MANUAL_UPDATE")) {
                                lowPowerWarningsController2.uiEventLogger.logWithPosition(intent2.getBooleanExtra("extra_power_save_mode_manual_enabled", false) ? BatteryMetricEvent.BATTERY_SAVER_ENABLED_REASON : BatteryMetricEvent.BATTERY_SAVER_DISABLED_REASON, 0, (String) null, intent2.getIntExtra("extra_power_save_mode_manual_enabled_reason", 0));
                                return;
                            }
                            return;
                        }
                        if (action5.equals("android.os.action.POWER_SAVE_MODE_CHANGED")) {
                            PowerManager powerManager = lowPowerWarningsController2.powerManager;
                            Boolean valueOf = powerManager != null ? Boolean.valueOf(powerManager.isPowerSaveMode()) : null;
                            if (Intrinsics.areEqual(lowPowerWarningsController2.prevPowerSaveEnabledAsync, valueOf)) {
                                return;
                            }
                            lowPowerWarningsController2.uiEventLogger.log(Intrinsics.areEqual(valueOf, Boolean.TRUE) ? BatteryMetricEvent.BATTERY_SAVER_ENABLED : BatteryMetricEvent.BATTERY_SAVER_DISABLED);
                            lowPowerWarningsController2.prevPowerSaveEnabledAsync = valueOf;
                        }
                    }
                }
            });
            ChargeLimitController chargeLimitController = PowerNotificationWarningsGoogleImpl.this.mChargeLimitController;
            chargeLimitController.getClass();
            String action5 = intent.getAction();
            if (action5 != null && ((hashCode = action5.hashCode()) == -905063602 ? action5.equals("android.intent.action.LOCKED_BOOT_COMPLETED") : hashCode == 798292259 && action5.equals("android.intent.action.BOOT_COMPLETED"))) {
                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("dispatchIntent: ", intent.getAction(), "ChargeLimitController");
                if (PowerUtils.isChargeLimitEnabledForUser(chargeLimitController.secureSettings, ((UserTrackerImpl) chargeLimitController.userTracker).getUserId())) {
                    Log.d("ChargeLimitController", "Enable charge limit upon boot completed.");
                    BuildersKt.launch$default(chargeLimitController.backgroundCoroutineScope, null, null, new ChargeLimitController$setChargingPolicy$1(chargeLimitController, 2, null), 3);
                }
            }
            BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = PowerNotificationWarningsGoogleImpl.this.mBatteryDefenderNotificationHandler;
            if (batteryDefenderNotificationHandler != null && (action3 = intent.getAction()) != null) {
                int hashCode3 = action3.hashCode();
                ContextScope contextScope = batteryDefenderNotificationHandler.backgroundCoroutineScope;
                switch (hashCode3) {
                    case -1838162442:
                        if (action3.equals("PNW.defenderResumeCharging")) {
                            Log.d("BatteryDefenderNotification", "onActionResumeChargingIntent");
                            UiEventLogger uiEventLogger3 = batteryDefenderNotificationHandler.uiEventLogger;
                            if (uiEventLogger3 != null) {
                                uiEventLogger3.logWithPosition(BatteryMetricEvent.BATTERY_DEFENDER_BYPASS_LIMIT, 0, (String) null, batteryDefenderNotificationHandler.batteryLevel);
                            }
                            batteryDefenderNotificationHandler.getDwellTempDefenderNotification().cancelNotification();
                            BuildersKt.launch$default(contextScope, null, null, new BatteryDefenderNotificationHandler$onActionResumeChargingIntent$1(batteryDefenderNotificationHandler, null), 3);
                            break;
                        }
                        break;
                    case -1769274126:
                        if (action3.equals("systemui.power.action.dismissBatteryDefenderWarning")) {
                            DwellTempDefenderNotification dwellTempDefenderNotification = batteryDefenderNotificationHandler.getDwellTempDefenderNotification();
                            Log.d("DwellTempDefenderNotification", "swipeNotificationByUser, notificationVisible:" + dwellTempDefenderNotification.notificationVisible + "->false");
                            dwellTempDefenderNotification.notificationVisible = false;
                            UiEventLogger uiEventLogger4 = dwellTempDefenderNotification.uiEventLogger;
                            if (uiEventLogger4 != null) {
                                uiEventLogger4.log(BatteryMetricEvent.DELETE_BATTERY_DEFENDER_NOTIFICATION);
                                break;
                            }
                        }
                        break;
                    case -646306533:
                        if (action3.equals("PNW.defenderResumeCharging.settings")) {
                            Log.d("BatteryDefenderNotification", "onActionResumeChargingSettings");
                            UiEventLogger uiEventLogger5 = batteryDefenderNotificationHandler.uiEventLogger;
                            if (uiEventLogger5 != null) {
                                uiEventLogger5.logWithPosition(BatteryMetricEvent.BATTERY_DEFENDER_BYPASS_LIMIT_FOR_TIPS, 0, (String) null, batteryDefenderNotificationHandler.batteryLevel);
                            }
                            batteryDefenderNotificationHandler.getDwellTempDefenderNotification().cancelNotification();
                            BuildersKt.launch$default(contextScope, null, null, new BatteryDefenderNotificationHandler$onActionResumeChargingSettings$1(batteryDefenderNotificationHandler, intent.getBooleanExtra("is_dock_defender", false) ? 4 : 0, null), 3);
                            break;
                        }
                        break;
                    case 798292259:
                        if (action3.equals("android.intent.action.BOOT_COMPLETED") && batteryDefenderNotificationHandler.dockDefendEnabled) {
                            BuildersKt.launch$default(contextScope, null, null, new BatteryDefenderNotificationHandler$onActionBootCompleted$1(batteryDefenderNotificationHandler, null), 3);
                            break;
                        }
                        break;
                }
            }
            AdaptiveChargingNotification adaptiveChargingNotification = PowerNotificationWarningsGoogleImpl.this.mAdaptiveChargingNotification;
            if (adaptiveChargingNotification != null && (action2 = intent.getAction()) != null) {
                if ("android.intent.action.BATTERY_CHANGED".equals(action2)) {
                    adaptiveChargingNotification.resolveBatteryChangedIntent(intent);
                } else if ("com.google.android.systemui.adaptivecharging.ADAPTIVE_CHARGING_DEADLINE_SET".equals(action2)) {
                    adaptiveChargingNotification.checkAdaptiveChargingStatus(true);
                } else if ("PNW.acChargeNormally".equals(action2)) {
                    adaptiveChargingNotification.mUiEventLogger.log(BatteryMetricEvent.ADAPTIVE_CHARGING_NOTIFICATION_BYPASS);
                    adaptiveChargingNotification.mAdaptiveChargingManager.getClass();
                    IGoogleBattery initHalInterface = GoogleBatteryManager.initHalInterface(null);
                    if (initHalInterface != null) {
                        try {
                            ((IGoogleBattery.Stub.Proxy) initHalInterface).setChargingDeadline();
                        } catch (ServiceSpecificException | RemoteException | IllegalArgumentException e) {
                            Log.e("AdaptiveChargingManager", "setChargingDeadline failed: ", e);
                        }
                        GoogleBatteryManager.destroyHalInterface(initHalInterface, null);
                    }
                    adaptiveChargingNotification.cancelNotification();
                    adaptiveChargingNotification.mContext.sendBroadcastAsUser(new Intent("com.google.android.systemui.adaptivecharging.ADAPTIVE_CHARGING_DEADLINE_SET").setPackage(adaptiveChargingNotification.mContext.getPackageName()).setFlags(1342177280), UserHandle.ALL);
                } else if ("systemui.power.action.dismissAdaptiveChargingWarning".equals(action2) && (uiEventLogger2 = adaptiveChargingNotification.mUiEventLogger) != null) {
                    uiEventLogger2.log(BatteryMetricEvent.DELETE_ADAPTIVE_CHARGING_NOTIFICATION);
                }
            }
            IncompatibleChargerNotification incompatibleChargerNotification = PowerNotificationWarningsGoogleImpl.this.mIncompatibleChargerNotification;
            if (incompatibleChargerNotification != null) {
                String action6 = intent.getAction();
                Log.d("IncompatibleChargerNotification", "dispatchIntent: " + action6);
                if ("systemui.power.action.dismissIncompatibleChargerWarning".equals(action6)) {
                    BatteryMetricEvent batteryMetricEvent = BatteryMetricEvent.DELETE_INCOMPATIBLE_CHARGING_NOTIFICATION;
                    UiEventLogger uiEventLogger6 = incompatibleChargerNotification.mUiEventLogger;
                    if (uiEventLogger6 != null) {
                        uiEventLogger6.log(batteryMetricEvent);
                    }
                }
            }
            final WirelessChargingNotification wirelessChargingNotification = PowerNotificationWarningsGoogleImpl.this.mWirelessChargingNotification;
            if (wirelessChargingNotification != null) {
                String action7 = intent.getAction();
                if ("android.intent.action.BATTERY_CHANGED".equals(action7)) {
                    int intExtra = intent.getIntExtra("plugged", 0);
                    boolean z = wirelessChargingNotification.mIsWirelessCharging;
                    boolean z2 = intExtra == 4;
                    wirelessChargingNotification.mIsWirelessCharging = z2;
                    if (z2 && !z) {
                        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("mWirelessCharging:"), wirelessChargingNotification.mIsWirelessCharging, "WirelessChargingNotification");
                        wirelessChargingNotification.mExecutor.execute(new Runnable() { // from class: com.google.android.systemui.power.WirelessChargingNotification$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                WirelessChargingNotification wirelessChargingNotification2 = WirelessChargingNotification.this;
                                wirelessChargingNotification2.getClass();
                                String key = WirelessChargingNotification.getKey();
                                boolean z3 = WirelessChargingNotification.getSharedPreferences(wirelessChargingNotification2.mContext).getLong(key, -1L) == -1;
                                MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("showNotification: ", "WirelessChargingNotification", z3);
                                if (z3) {
                                    WirelessChargingNotification.putWirelessChargingNotificationTimestamp(wirelessChargingNotification2.mContext, key);
                                    CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("[sendNotification] isNotificationActive: "), wirelessChargingNotification2.mIsNotificationActive, "WirelessChargingNotification");
                                    String string = wirelessChargingNotification2.mContext.getString(R.string.wireless_charging_notification_title);
                                    String string2 = wirelessChargingNotification2.mContext.getString(R.string.wireless_charging_notification_text);
                                    NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(wirelessChargingNotification2.mContext, "BAT");
                                    notificationCompat$Builder.mNotification.icon = R.drawable.ic_battery_charging;
                                    notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(string2);
                                    notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(string);
                                    notificationCompat$Builder.mContentIntent = PowerUtils.createPendingIntent(wirelessChargingNotification2.mContext, "systemui.power.action.clickWirelessChargingNotification", null);
                                    NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
                                    notificationCompat$BigTextStyle.mBigText = NotificationCompat$Builder.limitCharSequenceLength(string2);
                                    notificationCompat$Builder.setStyle(notificationCompat$BigTextStyle);
                                    notificationCompat$Builder.mNotification.deleteIntent = PowerUtils.createPendingIntent(wirelessChargingNotification2.mContext, "systemui.power.action.dismissWirelessChargingNotification", null);
                                    notificationCompat$Builder.mVisibility = 1;
                                    notificationCompat$Builder.setFlag(2, true);
                                    notificationCompat$Builder.setFlag(16, true);
                                    notificationCompat$Builder.mSilent = true;
                                    notificationCompat$Builder.mLocalOnly = true;
                                    PowerUtils.overrideNotificationAppName(wirelessChargingNotification2.mContext, notificationCompat$Builder);
                                    wirelessChargingNotification2.mNotificationManager.notifyAsUser("wireless_charging", R.string.wireless_charging_notification_title, notificationCompat$Builder.build(), UserHandle.CURRENT);
                                    wirelessChargingNotification2.mIsNotificationActive = true;
                                    BatteryMetricEvent batteryMetricEvent2 = BatteryMetricEvent.SEND_WIRELESS_CHARGING_NOTIFICATION;
                                    UiEventLogger uiEventLogger7 = wirelessChargingNotification2.mUiEventLogger;
                                    if (uiEventLogger7 != null) {
                                        uiEventLogger7.log(batteryMetricEvent2);
                                    }
                                }
                            }
                        });
                    }
                } else if ("systemui.power.action.clickWirelessChargingNotification".equals(action7)) {
                    Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(wirelessChargingNotification.mContext.getString(R.string.wireless_charging_notification_help_url)));
                    intent2.setFlags(268468224);
                    wirelessChargingNotification.mActivityStarter.startActivity(intent2, true);
                    wirelessChargingNotification.mUiEventLogger.log(BatteryMetricEvent.CLICK_WIRELESS_CHARGING_NOTIFICATION);
                } else if ("systemui.power.action.dismissWirelessChargingNotification".equals(action7)) {
                    BatteryMetricEvent batteryMetricEvent2 = BatteryMetricEvent.DISMISS_WIRELESS_CHARGING_NOTIFICATION;
                    UiEventLogger uiEventLogger7 = wirelessChargingNotification.mUiEventLogger;
                    if (uiEventLogger7 != null) {
                        uiEventLogger7.log(batteryMetricEvent2);
                    }
                }
            }
            ChargeLimitDiscoveryNotification chargeLimitDiscoveryNotification = PowerNotificationWarningsGoogleImpl.this.mChargeLimitDiscoveryNotification;
            if (chargeLimitDiscoveryNotification == null || (action = intent.getAction()) == null) {
                return;
            }
            switch (action.hashCode()) {
                case -1538406691:
                    if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                        boolean z3 = chargeLimitDiscoveryNotification.isPluggedIn;
                        boolean isPluggedIn = BatteryStatus.isPluggedIn(intent.getIntExtra("plugged", 0));
                        chargeLimitDiscoveryNotification.isPluggedIn = isPluggedIn;
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("isPluggedIn = ", "ChargeLimitDiscoveryNotification", isPluggedIn);
                        if (chargeLimitDiscoveryNotification.isPluggedIn && !z3) {
                            BuildersKt.launch$default(chargeLimitDiscoveryNotification.backgroundCoroutineScope, null, null, new ChargeLimitDiscoveryNotification$dispatchIntent$1(chargeLimitDiscoveryNotification, null), 3);
                            break;
                        }
                    }
                    break;
                case -250205802:
                    if (action.equals("systemui.power.action.enableChargeLimitFeature")) {
                        Log.d("ChargeLimitDiscoveryNotification", "Enable charge limit manually.");
                        ChargeLimitController chargeLimitController2 = chargeLimitDiscoveryNotification.chargeLimitController;
                        chargeLimitController2.getClass();
                        BuildersKt.launch$default(chargeLimitController2.backgroundCoroutineScope, null, null, new ChargeLimitController$setChargingPolicy$1(chargeLimitController2, 2, null), 3);
                        chargeLimitDiscoveryNotification.secureSettings.putIntForUser("charge_optimization_mode", 1, ((UserTrackerImpl) chargeLimitDiscoveryNotification.userTracker).getUserId());
                        UiEventLogger uiEventLogger8 = chargeLimitDiscoveryNotification.uiEventLogger;
                        if (uiEventLogger8 != null) {
                            uiEventLogger8.log(BatteryMetricEvent.ENABLE_CHARGE_LIMIT_FEATURE);
                        }
                        NotificationManager notificationManager = chargeLimitDiscoveryNotification.notificationManager;
                        if (notificationManager != null) {
                            notificationManager.cancelAsUser("charge_limit", R.string.charge_limit_discovery_notification_title, UserHandle.CURRENT);
                            break;
                        }
                    }
                    break;
                case 452333100:
                    if (action.equals("systemui.power.action.dismissChargeLimitNotification") && (uiEventLogger = chargeLimitDiscoveryNotification.uiEventLogger) != null) {
                        uiEventLogger.log(BatteryMetricEvent.DISMISS_CHARGE_LIMIT_DISCOVERY_NOTIFICATION);
                        break;
                    }
                    break;
                case 1949120110:
                    if (action.equals("systemui.power.action.clickChargeLimitNotification")) {
                        Intent intent3 = new Intent();
                        intent3.setComponent(new ComponentName("com.google.android.settings.intelligence", "com.google.android.settings.intelligence.modules.battery.impl.chargingoptimization.ChargingOptimizationActivity"));
                        intent3.setFlags(268468224);
                        chargeLimitDiscoveryNotification.activityStarter.startActivity(intent3, true);
                        UiEventLogger uiEventLogger9 = chargeLimitDiscoveryNotification.uiEventLogger;
                        if (uiEventLogger9 != null) {
                            uiEventLogger9.log(BatteryMetricEvent.CLICK_CHARGE_LIMIT_DISCOVERY_NOTIFICATION);
                            break;
                        }
                    }
                    break;
            }
        }
    }

    public PowerNotificationWarningsGoogleImpl(final Context context, ActivityStarter activityStarter, BroadcastDispatcher broadcastDispatcher, BroadcastSender broadcastSender, final UiEventLogger uiEventLogger, GlobalSettings globalSettings, Lazy lazy, DialogTransitionAnimator dialogTransitionAnimator, EnhancedEstimates enhancedEstimates, UserTracker userTracker, BatteryEventClient batteryEventClient, SystemUIDialog.Factory factory, Executor executor, Handler handler, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider2, SecureSettings secureSettings) {
        super(context, activityStarter, broadcastSender, lazy, dialogTransitionAnimator, uiEventLogger, userTracker, factory);
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mBroadcastSender = broadcastSender;
        this.mGlobalSettings = globalSettings;
        this.mSecureSettings = secureSettings;
        this.mEnhancedEstimates = enhancedEstimates;
        this.mUserTracker = userTracker;
        this.mBatteryEventClient = batteryEventClient;
        this.mBatteryControllerLazy = lazy;
        this.mBatterySaverConfirmationDialogProvider = switchingProvider;
        this.mSevereLowBatteryNotificationProvider = switchingProvider2;
        this.mActivityStarter = activityStarter;
        this.mExecutor = executor;
        this.mHandler = handler;
        this.mBroadcastReceiver = new AnonymousClass1();
        handler.post(new Runnable() { // from class: com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                final PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl = PowerNotificationWarningsGoogleImpl.this;
                Context context2 = context;
                UiEventLogger uiEventLogger2 = uiEventLogger;
                powerNotificationWarningsGoogleImpl.mBatteryInfoBroadcast = new BatteryInfoBroadcast(context2, powerNotificationWarningsGoogleImpl.mBroadcastSender, powerNotificationWarningsGoogleImpl.mEnhancedEstimates, powerNotificationWarningsGoogleImpl.mExecutor, powerNotificationWarningsGoogleImpl.mUserTracker);
                SevereLowBatteryNotification severeLowBatteryNotification = (SevereLowBatteryNotification) powerNotificationWarningsGoogleImpl.mSevereLowBatteryNotificationProvider.get();
                powerNotificationWarningsGoogleImpl.mSevereLowBatteryNotification = severeLowBatteryNotification;
                powerNotificationWarningsGoogleImpl.mLowPowerWarningsController = new LowPowerWarningsController(context2, severeLowBatteryNotification, powerNotificationWarningsGoogleImpl.mGlobalSettings, uiEventLogger2, powerNotificationWarningsGoogleImpl.mExecutor);
                powerNotificationWarningsGoogleImpl.mChargeLimitController = new ChargeLimitController(powerNotificationWarningsGoogleImpl.mExecutor, powerNotificationWarningsGoogleImpl.mSecureSettings, powerNotificationWarningsGoogleImpl.mUserTracker);
                SurfaceType surfaceType = SurfaceType.NOTIFICATION;
                ArrayList arrayList = new ArrayList(Arrays.asList(BatteryEventType.LOW_BATTERY, BatteryEventType.SEVERE_LOW_BATTERY, BatteryEventType.EXTREME_LOW_BATTERY, BatteryEventType.WIRED_INCOMPATIBLE_CHARGING, BatteryEventType.TEMP_DEFEND_BATTERY, BatteryEventType.DWELL_DEFEND_BATTERY, BatteryEventType.DOCK_DEFEND_BATTERY));
                Function3 function3 = new Function3() { // from class: com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        final boolean contains;
                        List list = (List) obj;
                        int intValue = ((Integer) obj2).intValue();
                        int intValue2 = ((Integer) obj3).intValue();
                        PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl2 = PowerNotificationWarningsGoogleImpl.this;
                        powerNotificationWarningsGoogleImpl2.getClass();
                        Log.d("PowerNotificationWarningsGoogleImpl", "[onBatteryEventUpdate] " + list);
                        final IncompatibleChargerNotification incompatibleChargerNotification = powerNotificationWarningsGoogleImpl2.mIncompatibleChargerNotification;
                        if (incompatibleChargerNotification != null && (contains = list.contains(BatteryEventType.WIRED_INCOMPATIBLE_CHARGING)) != incompatibleChargerNotification.mContainsIncompatibleChargers) {
                            MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("[refreshUsbState] isIncompatibleCharger: ", "IncompatibleChargerNotification", contains);
                            incompatibleChargerNotification.mMainHandler.post(new Runnable() { // from class: com.google.android.systemui.power.IncompatibleChargerNotification$$ExternalSyntheticLambda0
                                @Override // java.lang.Runnable
                                public final void run() {
                                    IncompatibleChargerNotification incompatibleChargerNotification2 = IncompatibleChargerNotification.this;
                                    boolean z = contains;
                                    incompatibleChargerNotification2.getClass();
                                    Log.d("IncompatibleChargerNotification", "[updateNotification] showNotification: " + z);
                                    if (!z) {
                                        Log.d("IncompatibleChargerNotification", "[cancelNotification] isNotificationActive: " + incompatibleChargerNotification2.mNotificationActive + " -> false");
                                        if (incompatibleChargerNotification2.mNotificationActive) {
                                            incompatibleChargerNotification2.mNotificationManager.cancelAsUser("incompatible_charging", R.string.incompatible_charging_notify_title, UserHandle.ALL);
                                            incompatibleChargerNotification2.mNotificationActive = false;
                                            return;
                                        }
                                        return;
                                    }
                                    Log.d("IncompatibleChargerNotification", "[sendNotification] isNotificationActive: " + incompatibleChargerNotification2.mNotificationActive + " -> true");
                                    NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(incompatibleChargerNotification2.mContext, "BAT");
                                    notificationCompat$Builder.mNotification.icon = R.drawable.ic_battery_full;
                                    notificationCompat$Builder.mShowWhen = false;
                                    notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(incompatibleChargerNotification2.mContext.getString(R.string.incompatible_charging_notify_title));
                                    notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(incompatibleChargerNotification2.mContext.getString(R.string.incompatible_charging_notify_des));
                                    NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
                                    notificationCompat$BigTextStyle.mBigText = NotificationCompat$Builder.limitCharSequenceLength(incompatibleChargerNotification2.mContext.getString(R.string.incompatible_charging_notify_des));
                                    notificationCompat$Builder.setStyle(notificationCompat$BigTextStyle);
                                    notificationCompat$Builder.mNotification.deleteIntent = PowerUtils.createPendingIntent(incompatibleChargerNotification2.mContext, "systemui.power.action.dismissIncompatibleChargerWarning", null);
                                    notificationCompat$Builder.mContentIntent = PowerUtils.createBatterySettingsPendingIntentAsUser(incompatibleChargerNotification2.mContext);
                                    notificationCompat$Builder.addAction(incompatibleChargerNotification2.mContext.getString(R.string.incompatible_charging_learn_more), PowerUtils.createHelpArticlePendingIntentAsUser(R.string.incompatible_charging_help_url, incompatibleChargerNotification2.mContext));
                                    notificationCompat$Builder.mLocalOnly = true;
                                    PowerUtils.overrideNotificationAppName(incompatibleChargerNotification2.mContext, notificationCompat$Builder);
                                    incompatibleChargerNotification2.mNotificationManager.notifyAsUser("incompatible_charging", R.string.incompatible_charging_notify_title, notificationCompat$Builder.build(), UserHandle.ALL);
                                    incompatibleChargerNotification2.mNotificationActive = true;
                                    BatteryMetricEvent batteryMetricEvent = BatteryMetricEvent.SEND_INCOMPATIBLE_CHARGING_NOTIFICATION;
                                    UiEventLogger uiEventLogger3 = incompatibleChargerNotification2.mUiEventLogger;
                                    if (uiEventLogger3 != null) {
                                        uiEventLogger3.log(batteryMetricEvent);
                                    }
                                }
                            });
                            IncompatibleChargerNotification.getSharedPreferences(incompatibleChargerNotification.mContext).edit().putLong(contains ? IncompatibleChargerNotification.KEY_INCOMPATIBLE_CHARGER_TIME : IncompatibleChargerNotification.KEY_COMPATIBLE_CHARGER_TIME, System.currentTimeMillis()).apply();
                            incompatibleChargerNotification.mContainsIncompatibleChargers = contains;
                        }
                        LowPowerWarningsController lowPowerWarningsController = powerNotificationWarningsGoogleImpl2.mLowPowerWarningsController;
                        if (lowPowerWarningsController != null) {
                            lowPowerWarningsController.onBatteryEventUpdate(intValue, list);
                        }
                        BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = powerNotificationWarningsGoogleImpl2.mBatteryDefenderNotificationHandler;
                        if (batteryDefenderNotificationHandler != null) {
                            batteryDefenderNotificationHandler.batteryLevel = intValue;
                            boolean contains2 = list.contains(BatteryEventType.DOCK_DEFEND_BATTERY);
                            boolean z = false;
                            kotlin.Lazy lazy2 = batteryDefenderNotificationHandler.dockDefenderNotification$delegate;
                            if (contains2) {
                                if (Settings.Global.getInt(batteryDefenderNotificationHandler.context.getContentResolver(), "dock_defender_bypass", 0) != 1 && Settings.Global.getInt(batteryDefenderNotificationHandler.context.getContentResolver(), "dock_defender_first_run", -1) == -1) {
                                    z = true;
                                }
                                MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("onDockDefenderEvent: dockDefend1RunStatus: ", "BatteryDefenderNotification", z);
                                if (z) {
                                    Settings.Global.putInt(batteryDefenderNotificationHandler.context.getContentResolver(), "dock_defender_first_run", 1);
                                    DockDefenderNotification dockDefenderNotification = (DockDefenderNotification) lazy2.getValue();
                                    dockDefenderNotification.getClass();
                                    Log.d("DockDefenderNotification", "showNotification");
                                    String string = dockDefenderNotification.context.getString(R.string.dock_defender_first_run_des);
                                    NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(dockDefenderNotification.context, "BAT");
                                    notificationCompat$Builder.mNotification.icon = R.drawable.ic_battery_with_shield;
                                    notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(dockDefenderNotification.context.getString(R.string.dock_defender_first_run_title));
                                    notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(string);
                                    notificationCompat$Builder.mContentIntent = PowerUtils.createBatterySettingsPendingIntentAsUser(dockDefenderNotification.context);
                                    NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
                                    notificationCompat$BigTextStyle.mBigText = NotificationCompat$Builder.limitCharSequenceLength(string);
                                    notificationCompat$Builder.setStyle(notificationCompat$BigTextStyle);
                                    notificationCompat$Builder.mSilent = true;
                                    notificationCompat$Builder.setFlag(16, true);
                                    notificationCompat$Builder.addAction(dockDefenderNotification.context.getString(R.string.battery_health_notify_learn_more), PowerUtils.createHelpArticlePendingIntentAsUser(R.string.dock_defender_notify_helper_url, dockDefenderNotification.context));
                                    notificationCompat$Builder.addAction(dockDefenderNotification.context.getString(R.string.dock_defender_first_run_settings), PowerUtils.createBatterySettingsPendingIntentAsUser(dockDefenderNotification.context));
                                    notificationCompat$Builder.mLocalOnly = true;
                                    PowerUtils.overrideNotificationAppName(dockDefenderNotification.context, notificationCompat$Builder);
                                    NotificationManager notificationManager = dockDefenderNotification.notificationManager;
                                    if (notificationManager != null) {
                                        notificationManager.notifyAsUser("battery_defender", R.string.dock_defender_first_run_title, notificationCompat$Builder.build(), UserHandle.ALL);
                                    }
                                }
                            } else if (list.contains(BatteryEventType.TEMP_DEFEND_BATTERY)) {
                                batteryDefenderNotificationHandler.onBatteryDefenderEvent(intValue2, DwellTempDefenderNotification.BatteryDefendType.TEMP_DEFEND);
                            } else if (list.contains(BatteryEventType.DWELL_DEFEND_BATTERY)) {
                                batteryDefenderNotificationHandler.onBatteryDefenderEvent(intValue2, DwellTempDefenderNotification.BatteryDefendType.DWELL_DEFEND);
                            } else {
                                boolean isCharged = BatteryStatus.isCharged(1, batteryDefenderNotificationHandler.batteryLevel);
                                MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("onNonDefenderEvent: charged: ", "BatteryDefenderNotification", isCharged);
                                boolean z2 = batteryDefenderNotificationHandler.dockDefendEnabled;
                                ContextScope contextScope = batteryDefenderNotificationHandler.backgroundCoroutineScope;
                                if (z2 && batteryDefenderNotificationHandler.inDefenderSection) {
                                    Log.d("BatteryDefenderNotification", "dockDefendEnabled:true, no post notification");
                                    batteryDefenderNotificationHandler.inDefenderSection = false;
                                    batteryDefenderNotificationHandler.getDwellTempDefenderNotification().cancelNotification();
                                    BuildersKt.launch$default(contextScope, null, null, new BatteryDefenderNotificationHandler$exitTempOrDwellDefendOnTablet$1(batteryDefenderNotificationHandler, null), 3);
                                } else if (!z2) {
                                    if (isCharged) {
                                        batteryDefenderNotificationHandler.getDwellTempDefenderNotification().cancelPostNotification();
                                    }
                                    if (batteryDefenderNotificationHandler.inDefenderSection) {
                                        batteryDefenderNotificationHandler.inDefenderSection = false;
                                        batteryDefenderNotificationHandler.getDwellTempDefenderNotification().cancelNotification();
                                        BuildersKt.launch$default(contextScope, null, null, new BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1(batteryDefenderNotificationHandler, null), 3);
                                    }
                                } else if (intValue2 != 8) {
                                    DockDefenderNotification dockDefenderNotification2 = (DockDefenderNotification) lazy2.getValue();
                                    dockDefenderNotification2.getClass();
                                    Log.d("DockDefenderNotification", "cancelNotification");
                                    NotificationManager notificationManager2 = dockDefenderNotification2.notificationManager;
                                    if (notificationManager2 != null) {
                                        notificationManager2.cancelAsUser("battery_defender", R.string.dock_defender_first_run_title, UserHandle.ALL);
                                    }
                                    BuildersKt.launch$default(contextScope, null, null, new BatteryDefenderNotificationHandler$exitDockDefenderIfNeeded$1(batteryDefenderNotificationHandler, null), 3);
                                }
                            }
                        }
                        return null;
                    }
                };
                BatteryEventClient batteryEventClient2 = powerNotificationWarningsGoogleImpl.mBatteryEventClient;
                if (batteryEventClient2.service == null) {
                    batteryEventClient2.surfaceType = surfaceType;
                    batteryEventClient2.callerTag = "PowerNotificationWarningsGoogleImpl";
                    batteryEventClient2.subscribedBatteryEvents.addAll(arrayList);
                    batteryEventClient2.onBatteryEventUpdate = function3;
                    BuildersKt.launch$default(batteryEventClient2.coroutineScope, batteryEventClient2.backgroundDispatcher, null, new BatteryEventClient$registerBatteryEventCallback$1(batteryEventClient2, null), 2);
                } else {
                    batteryEventClient2.logWithCaller.w("already registered for NOTIFICATION, need to unregister before register again");
                }
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
                intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
                intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
                intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
                intentFilter.addAction("android.intent.action.LOCKED_BOOT_COMPLETED");
                intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                intentFilter.addAction("PNW.startSaverConfirmation");
                intentFilter.addAction("com.android.settingslib.fuelgauge.ACTION_SAVER_STATE_MANUAL_UPDATE");
                intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
                intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
                intentFilter.addAction("android.bluetooth.device.action.BATTERY_LEVEL_CHANGED");
                intentFilter.addAction("android.bluetooth.device.action.ALIAS_CHANGED");
                intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
                intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
                intentFilter.addAction("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED");
                intentFilter.addAction("systemui.power.action.START_FLIPENDO");
                intentFilter.addAction("PNW.dismissSevereLowBatteryWarning");
                Resources resources = context2.getResources();
                if (resources.getBoolean(R.bool.config_battery_defender_warning_enabled)) {
                    powerNotificationWarningsGoogleImpl.mBatteryDefenderNotificationHandler = new BatteryDefenderNotificationHandler(context2, uiEventLogger2, powerNotificationWarningsGoogleImpl.mExecutor);
                    intentFilter.addAction("PNW.defenderResumeCharging");
                    intentFilter.addAction("PNW.defenderResumeCharging.settings");
                    intentFilter.addAction("systemui.power.action.dismissBatteryDefenderWarning");
                }
                if (resources.getBoolean(R.bool.config_adaptive_charging_warning_enabled)) {
                    powerNotificationWarningsGoogleImpl.mAdaptiveChargingNotification = new AdaptiveChargingNotification(context2, new AdaptiveChargingManager(context2), uiEventLogger2);
                    intentFilter.addAction("com.google.android.systemui.adaptivecharging.ADAPTIVE_CHARGING_DEADLINE_SET");
                    intentFilter.addAction("PNW.acChargeNormally");
                    intentFilter.addAction("systemui.power.action.dismissAdaptiveChargingWarning");
                }
                if (resources.getBoolean(R.bool.config_incompatible_charging_warning_enabled)) {
                    powerNotificationWarningsGoogleImpl.mIncompatibleChargerNotification = new IncompatibleChargerNotification(context2, uiEventLogger2);
                    intentFilter.addAction("systemui.power.action.dismissIncompatibleChargerWarning");
                }
                if (resources.getBoolean(R.bool.config_wireless_charging_warning_enabled) && TextUtils.equals(SystemProperties.get("ro.boot.warranty.sku"), "EMA")) {
                    powerNotificationWarningsGoogleImpl.mWirelessChargingNotification = new WirelessChargingNotification(context2, powerNotificationWarningsGoogleImpl.mExecutor, powerNotificationWarningsGoogleImpl.mActivityStarter, uiEventLogger2);
                    intentFilter.addAction("systemui.power.action.dismissWirelessChargingNotification");
                    intentFilter.addAction("systemui.power.action.clickWirelessChargingNotification");
                }
                if (resources.getBoolean(R.bool.config_charge_limit_discovery_enabled) && TextUtils.equals(SystemProperties.get("ro.boot.warranty.sku"), "EMA")) {
                    powerNotificationWarningsGoogleImpl.mChargeLimitDiscoveryNotification = new ChargeLimitDiscoveryNotification(context2, powerNotificationWarningsGoogleImpl.mExecutor, powerNotificationWarningsGoogleImpl.mActivityStarter, uiEventLogger2, powerNotificationWarningsGoogleImpl.mChargeLimitController, powerNotificationWarningsGoogleImpl.mSecureSettings, powerNotificationWarningsGoogleImpl.mUserTracker);
                    intentFilter.addAction("systemui.power.action.enableChargeLimitFeature");
                    intentFilter.addAction("systemui.power.action.dismissChargeLimitNotification");
                    intentFilter.addAction("systemui.power.action.clickChargeLimitNotification");
                }
                BroadcastReceiver broadcastReceiver = powerNotificationWarningsGoogleImpl.mBroadcastReceiver;
                UserHandle userHandle = UserHandle.ALL;
                BroadcastDispatcher broadcastDispatcher2 = powerNotificationWarningsGoogleImpl.mBroadcastDispatcher;
                broadcastDispatcher2.getClass();
                BroadcastDispatcher.registerReceiverWithHandler$default(broadcastDispatcher2, broadcastReceiver, intentFilter, powerNotificationWarningsGoogleImpl.mHandler, userHandle, 32);
                Intent registerReceiver = context2.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                if (registerReceiver != null) {
                    powerNotificationWarningsGoogleImpl.mBroadcastReceiver.onReceive(context2, registerReceiver);
                }
            }
        });
    }

    public final void dismissLowBatteryWarning() {
        LowPowerWarningsController lowPowerWarningsController = this.mLowPowerWarningsController;
        if (lowPowerWarningsController != null) {
            lowPowerWarningsController.cancelNotification();
        }
    }

    @Override // com.android.systemui.power.PowerNotificationWarnings
    public final void dump(PrintWriter printWriter) {
        super.dump(printWriter);
        BatteryInfoBroadcast batteryInfoBroadcast = this.mBatteryInfoBroadcast;
        if (batteryInfoBroadcast != null) {
            printWriter.println("\tdump BatteryInfoBroadcast states:");
            batteryInfoBroadcast.writeString(printWriter, "LastConnectedTime: ", "last_phone_connected_time");
            batteryInfoBroadcast.writeString(printWriter, "LastDisconnectedTime: ", "last_phone_disconnected_time");
            batteryInfoBroadcast.writeString(printWriter, "LastDataResetTime: ", "last_data_reset_time");
        }
        IncompatibleChargerNotification incompatibleChargerNotification = this.mIncompatibleChargerNotification;
        if (incompatibleChargerNotification != null) {
            StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "\tdump IncompatibleCharger states:", "\t\tLastCompatibleChargerTime: ");
            m.append(DumpUtils.toReadableDateTime(IncompatibleChargerNotification.getSharedPreferences(incompatibleChargerNotification.mContext).getLong(IncompatibleChargerNotification.KEY_COMPATIBLE_CHARGER_TIME, 0L)));
            StringBuilder m2 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, m.toString(), "\t\tLastIncompatibleChargerTime: ");
            m2.append(DumpUtils.toReadableDateTime(IncompatibleChargerNotification.getSharedPreferences(incompatibleChargerNotification.mContext).getLong(IncompatibleChargerNotification.KEY_INCOMPATIBLE_CHARGER_TIME, 0L)));
            printWriter.println(m2.toString());
        }
        LowPowerWarningsController lowPowerWarningsController = this.mLowPowerWarningsController;
        if (lowPowerWarningsController != null) {
            printWriter.println("\tdump LowPowerWarningsController states");
            printWriter.println("\t\tprevBatteryLevel: " + lowPowerWarningsController.prevBatteryLevel);
            printWriter.println("\t\tprevBatteryEventType: " + lowPowerWarningsController.prevBatteryEventTypes);
            printWriter.println("\t\tisBatterySaverReminderDisabled: " + (lowPowerWarningsController.globalSettings.getInt(1, "low_power_mode_reminder_enabled") == 0));
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tisScheduledByPercentage: ", lowPowerWarningsController.isScheduledByPercentage(), printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tlowBatteryNotificationCancelled: ", lowPowerWarningsController.lowBatteryNotificationCancelled, printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tsevereLowBatteryNotificationCancelled: ", lowPowerWarningsController.severeLowBatteryNotificationCancelled, printWriter);
        }
        BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = this.mBatteryDefenderNotificationHandler;
        if (batteryDefenderNotificationHandler != null) {
            printWriter.println("\tdump BatteryDefenderNotificationHandler states:");
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tdockDefendFirstRun: ", Settings.Global.getInt(batteryDefenderNotificationHandler.context.getContentResolver(), "dock_defender_first_run", -1) == -1, printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tdockDefendBypassed: ", Settings.Global.getInt(batteryDefenderNotificationHandler.context.getContentResolver(), "dock_defender_bypass", 0) == 1, printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tdockDefendEnabled: ", batteryDefenderNotificationHandler.dockDefendEnabled, printWriter);
            ActiveUnlockConfig$$ExternalSyntheticOutline0.m("\t\tinBatteryDefenderSection: ", batteryDefenderNotificationHandler.inDefenderSection, printWriter);
            printWriter.println("\t\tbatteryLevel: " + batteryDefenderNotificationHandler.batteryLevel);
            printWriter.println("\t\tstartTimestamp: " + ((SharedPreferences) batteryDefenderNotificationHandler.sharedPreferences$delegate.getValue()).getLong("trigger_time", -1L));
            batteryDefenderNotificationHandler.systemClock.getClass();
            printWriter.println("\t\tcurrentTimestamp: " + System.currentTimeMillis());
        }
        this.mExecutor.execute(new Runnable() { // from class: com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Context context = PowerNotificationWarningsGoogleImpl.this.mContext;
                Uri uri = DumpUtils.PROVIDER_URI;
                ContentResolver contentResolver = context.getContentResolver();
                try {
                    Uri uri2 = DumpUtils.PROVIDER_URI;
                    ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri2);
                    try {
                        if (acquireUnstableContentProviderClient == null) {
                            Log.w("PowerNotificationWarningsGoogleImpl", "Failed to acquire provider: " + uri2);
                            if (acquireUnstableContentProviderClient == null) {
                                return;
                            }
                        } else {
                            acquireUnstableContentProviderClient.call("prepareProviderDump", "", null);
                        }
                        acquireUnstableContentProviderClient.close();
                    } finally {
                    }
                } catch (Exception e) {
                    Log.w("PowerNotificationWarningsGoogleImpl", "Failed to call Turbo provider", e);
                }
            }
        });
    }

    public final void userSwitched() {
        Integer num;
        LowPowerWarningsController lowPowerWarningsController = this.mLowPowerWarningsController;
        if (lowPowerWarningsController != null && (num = lowPowerWarningsController.prevBatteryLevel) != null) {
            lowPowerWarningsController.onBatteryEventUpdate(num.intValue(), lowPowerWarningsController.prevBatteryEventTypes);
        }
        ChargeLimitController chargeLimitController = this.mChargeLimitController;
        if (chargeLimitController != null) {
            int userId = ((UserTrackerImpl) chargeLimitController.userTracker).getUserId();
            ExifInterface$$ExternalSyntheticOutline0.m("onUserSwitched - current user= ", "ChargeLimitController", userId);
            boolean isChargeLimitEnabledForUser = PowerUtils.isChargeLimitEnabledForUser(chargeLimitController.secureSettings, userId);
            MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("current charge limit= ", "ChargeLimitController", isChargeLimitEnabledForUser);
            BuildersKt.launch$default(chargeLimitController.backgroundCoroutineScope, null, null, new ChargeLimitController$setChargingPolicy$1(chargeLimitController, isChargeLimitEnabledForUser ? 2 : 1, null), 3);
        }
    }
}
