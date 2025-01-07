package com.android.systemui.power;

import android.animation.Animator;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IBinder;
import android.os.IThermalEventListener;
import android.os.IThermalService;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.Temperature;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.vr.IVrManager;
import android.service.vr.IVrStateCallbacks;
import android.util.Log;
import android.util.Slog;
import android.view.WindowManager;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.settingslib.Utils;
import com.android.settingslib.fuelgauge.Estimate;
import com.android.systemui.CoreStartable;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.systemui.volume.Events;
import com.android.wm.shell.R;
import com.google.android.systemui.power.EnhancedEstimatesGoogleImpl;
import com.google.android.systemui.power.PowerNotificationWarningsGoogleImpl;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;
import kotlin.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PowerUI implements CoreStartable, ConfigurationController.ConfigurationListener, CommandQueue.Callbacks {
    public static final boolean DEBUG = Log.isLoggable("PowerUI", 3);
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final CommandQueue mCommandQueue;
    public final Context mContext;
    BatteryStateSnapshot mCurrentBatteryStateSnapshot;
    public boolean mEnableSkinTemperatureWarning;
    public boolean mEnableUsbTemperatureAlarm;
    public final EnhancedEstimates mEnhancedEstimates;
    public boolean mInVrMode;
    BatteryStateSnapshot mLastBatteryStateSnapshot;
    public ListenableFuture mLastShowWarningTask;
    public final Lazy mLazyViewCapture;
    public int mLowBatteryAlertCloseLevel;
    boolean mLowWarningShownThisChargeCycle;
    public InattentiveSleepWarningView mOverlayView;
    public final PowerManager mPowerManager;
    boolean mSevereWarningShownThisChargeCycle;
    public IThermalEventListener mSkinThermalEventListener;
    IThermalService mThermalService;
    public IThermalEventListener mUsbThermalEventListener;
    public final UserTracker mUserTracker;
    public final IVrManager mVrManager;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public final WarningsUI mWarnings;
    public final Handler mHandler = new Handler();
    final Receiver mReceiver = new Receiver();
    public final Configuration mLastConfiguration = new Configuration();
    public int mPlugType = 0;
    public int mInvalidCharger = 0;
    public final int[] mLowBatteryReminderLevels = new int[2];
    public long mScreenOffTime = -1;
    int mBatteryLevel = 100;
    int mBatteryStatus = 1;
    public final AnonymousClass1 mWakefulnessObserver = new WakefulnessLifecycle.Observer() { // from class: com.android.systemui.power.PowerUI.1
        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onFinishedGoingToSleep$1() {
            PowerUI.this.mScreenOffTime = SystemClock.elapsedRealtime();
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onStartedWakingUp() {
            PowerUI.this.mScreenOffTime = -1L;
        }
    };
    public final UserTracker.Callback mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.systemui.power.PowerUI.2
        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            ((PowerNotificationWarningsGoogleImpl) PowerUI.this.mWarnings).userSwitched();
        }
    };
    public final AnonymousClass3 mVrStateCallbacks = new IVrStateCallbacks.Stub() { // from class: com.android.systemui.power.PowerUI.3
        public final void onVrStateChanged(boolean z) {
            PowerUI.this.mInVrMode = z;
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Receiver extends BroadcastReceiver {
        public boolean mHasReceivedBattery = false;

        public Receiver() {
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x017b  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onReceive(android.content.Context r14, android.content.Intent r15) {
            /*
                Method dump skipped, instructions count: 421
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.power.PowerUI.Receiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class SkinThermalEventListener extends IThermalEventListener.Stub {
        public SkinThermalEventListener() {
        }

        public final void notifyThrottling(Temperature temperature) {
            int status = temperature.getStatus();
            if (status < 5) {
                PowerNotificationWarnings powerNotificationWarnings = (PowerNotificationWarnings) PowerUI.this.mWarnings;
                if (powerNotificationWarnings.mHighTempWarning) {
                    powerNotificationWarnings.dismissHighTemperatureWarningInternal();
                    return;
                }
                return;
            }
            PowerUI powerUI = PowerUI.this;
            if (powerUI.mInVrMode) {
                return;
            }
            PowerNotificationWarningsGoogleImpl powerNotificationWarningsGoogleImpl = (PowerNotificationWarningsGoogleImpl) powerUI.mWarnings;
            if (Settings.Secure.getInt(powerNotificationWarningsGoogleImpl.mContext.getContentResolver(), "emergency_thermal_alert_disabled", 0) == 1) {
                Log.d("PowerNotificationWarningsGoogleImpl", "[showHighTemperatureWarning] is disabled");
            } else if (!powerNotificationWarningsGoogleImpl.mHighTempWarning) {
                powerNotificationWarningsGoogleImpl.mHighTempWarning = true;
                String string = ((PowerNotificationWarnings) powerNotificationWarningsGoogleImpl).mContext.getString(R.string.high_temp_notif_message);
                Notification.Builder color = new Notification.Builder(((PowerNotificationWarnings) powerNotificationWarningsGoogleImpl).mContext, PluginManager.NOTIFICATION_CHANNEL_ID).setSmallIcon(R.drawable.ic_device_thermostat_24).setWhen(0L).setShowWhen(false).setContentTitle(((PowerNotificationWarnings) powerNotificationWarningsGoogleImpl).mContext.getString(R.string.high_temp_title)).setContentText(string).setStyle(new Notification.BigTextStyle().bigText(string)).setVisibility(1).setContentIntent(powerNotificationWarningsGoogleImpl.pendingBroadcast("PNW.clickedTempWarning")).setDeleteIntent(powerNotificationWarningsGoogleImpl.pendingBroadcast("PNW.dismissedTempWarning")).setColor(Utils.getColorAttrDefaultColor(android.R.attr.colorError, 0, ((PowerNotificationWarnings) powerNotificationWarningsGoogleImpl).mContext));
                SystemUIApplication.overrideNotificationAppName(((PowerNotificationWarnings) powerNotificationWarningsGoogleImpl).mContext, color, false);
                powerNotificationWarningsGoogleImpl.mNoMan.notifyAsUser("high_temp", 4, color.build(), UserHandle.ALL);
            }
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("SkinThermalEventListener: notifyThrottling was called , current skin status = ", ", temperature = ", status);
            m.append(temperature.getValue());
            Slog.d("PowerUI", m.toString());
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class UsbThermalEventListener extends IThermalEventListener.Stub {
        public UsbThermalEventListener() {
        }

        public final void notifyThrottling(Temperature temperature) {
            int status = temperature.getStatus();
            if (status >= 5) {
                final PowerNotificationWarnings powerNotificationWarnings = (PowerNotificationWarnings) PowerUI.this.mWarnings;
                powerNotificationWarnings.mHandler.post(new Runnable() { // from class: com.android.systemui.power.PowerNotificationWarnings$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PowerNotificationWarnings powerNotificationWarnings2 = PowerNotificationWarnings.this;
                        if (powerNotificationWarnings2.mUsbHighTempDialog != null) {
                            return;
                        }
                        SystemUIDialog systemUIDialog = new SystemUIDialog(powerNotificationWarnings2.mContext, R.style.Theme_SystemUI_Dialog_Alert, true);
                        systemUIDialog.setCancelable(false);
                        systemUIDialog.setIconAttribute(android.R.attr.alertDialogIcon);
                        systemUIDialog.setTitle(R.string.high_temp_alarm_title);
                        SystemUIDialog.setShowForAllUsers(systemUIDialog);
                        systemUIDialog.setMessage(powerNotificationWarnings2.mContext.getString(R.string.high_temp_alarm_notify_message, ""));
                        systemUIDialog.setPositiveButton(android.R.string.ok, new PowerNotificationWarnings$$ExternalSyntheticLambda1(powerNotificationWarnings2, 0));
                        systemUIDialog.setNegativeButton$1(R.string.high_temp_alarm_help_care_steps, new PowerNotificationWarnings$$ExternalSyntheticLambda1(powerNotificationWarnings2, 1));
                        systemUIDialog.setOnDismissListener(new PowerNotificationWarnings$$ExternalSyntheticLambda3(powerNotificationWarnings2, 0));
                        systemUIDialog.getWindow().addFlags(2097280);
                        systemUIDialog.show();
                        powerNotificationWarnings2.mUsbHighTempDialog = systemUIDialog;
                        Events.writeEvent(19, 3, Boolean.valueOf(powerNotificationWarnings2.mKeyguard.isKeyguardLocked()));
                    }
                });
                Slog.d("PowerUI", "UsbThermalEventListener: notifyThrottling was called , current usb port status = " + status + ", temperature = " + temperature.getValue());
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface WarningsUI {
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [com.android.systemui.power.PowerUI$3] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.android.systemui.power.PowerUI$1] */
    public PowerUI(Context context, BroadcastDispatcher broadcastDispatcher, CommandQueue commandQueue, IVrManager iVrManager, WarningsUI warningsUI, EnhancedEstimates enhancedEstimates, WakefulnessLifecycle wakefulnessLifecycle, PowerManager powerManager, UserTracker userTracker, dagger.Lazy lazy) {
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mCommandQueue = commandQueue;
        this.mVrManager = iVrManager;
        this.mWarnings = warningsUI;
        this.mEnhancedEstimates = enhancedEstimates;
        this.mPowerManager = powerManager;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mUserTracker = userTracker;
        this.mLazyViewCapture = ConvenienceExtensionsKt.toKotlinLazy(lazy);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void dismissInattentiveSleepWarning(boolean z) {
        InattentiveSleepWarningView inattentiveSleepWarningView = this.mOverlayView;
        if (inattentiveSleepWarningView == null || inattentiveSleepWarningView.getParent() == null) {
            return;
        }
        inattentiveSleepWarningView.mDismissing = true;
        if (!z) {
            inattentiveSleepWarningView.setVisibility(4);
            inattentiveSleepWarningView.mWindowManager.removeView(inattentiveSleepWarningView);
        } else {
            final Animator animator = inattentiveSleepWarningView.mFadeOutAnimator;
            Objects.requireNonNull(animator);
            inattentiveSleepWarningView.postOnAnimation(new Runnable() { // from class: com.android.systemui.power.InattentiveSleepWarningView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    animator.start();
                }
            });
        }
    }

    public synchronized void doSkinThermalEventListenerRegistration() {
        boolean z;
        boolean z2;
        boolean z3;
        try {
            z = this.mEnableSkinTemperatureWarning;
            z2 = false;
            z3 = Settings.Global.getInt(this.mContext.getContentResolver(), "show_temperature_warning", this.mContext.getResources().getInteger(R.integer.config_showTemperatureWarning)) != 0;
            this.mEnableSkinTemperatureWarning = z3;
        } catch (RemoteException e) {
            Slog.e("PowerUI", "Exception while (un)registering skin thermal event listener.", e);
        } finally {
        }
        if (z3 != z) {
            if (this.mSkinThermalEventListener == null) {
                this.mSkinThermalEventListener = new SkinThermalEventListener();
            }
            if (this.mThermalService == null) {
                this.mThermalService = IThermalService.Stub.asInterface(ServiceManager.getService("thermalservice"));
            }
            z2 = this.mEnableSkinTemperatureWarning ? this.mThermalService.registerThermalEventListenerWithType(this.mSkinThermalEventListener, 3) : this.mThermalService.unregisterThermalEventListener(this.mSkinThermalEventListener);
            if (!z2) {
                this.mEnableSkinTemperatureWarning = !this.mEnableSkinTemperatureWarning;
                Slog.e("PowerUI", "Failed to register or unregister skin thermal event listener.");
            }
        }
    }

    public synchronized void doUsbThermalEventListenerRegistration() {
        boolean z;
        boolean z2;
        boolean z3;
        try {
            z = this.mEnableUsbTemperatureAlarm;
            z2 = false;
            z3 = Settings.Global.getInt(this.mContext.getContentResolver(), "show_usb_temperature_alarm", this.mContext.getResources().getInteger(R.integer.config_showUsbPortAlarm)) != 0;
            this.mEnableUsbTemperatureAlarm = z3;
        } catch (RemoteException e) {
            Slog.e("PowerUI", "Exception while (un)registering usb thermal event listener.", e);
        } finally {
        }
        if (z3 != z) {
            if (this.mUsbThermalEventListener == null) {
                this.mUsbThermalEventListener = new UsbThermalEventListener();
            }
            if (this.mThermalService == null) {
                this.mThermalService = IThermalService.Stub.asInterface(ServiceManager.getService("thermalservice"));
            }
            z2 = this.mEnableUsbTemperatureAlarm ? this.mThermalService.registerThermalEventListenerWithType(this.mUsbThermalEventListener, 4) : this.mThermalService.unregisterThermalEventListener(this.mUsbThermalEventListener);
            if (!z2) {
                this.mEnableUsbTemperatureAlarm = !this.mEnableUsbTemperatureAlarm;
                Slog.e("PowerUI", "Failed to register or unregister usb thermal event listener.");
            }
        }
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.print("mLowBatteryAlertCloseLevel=");
        printWriter.println(this.mLowBatteryAlertCloseLevel);
        printWriter.print("mLowBatteryReminderLevels=");
        printWriter.println(Arrays.toString(this.mLowBatteryReminderLevels));
        printWriter.print("mBatteryLevel=");
        printWriter.println(Integer.toString(this.mBatteryLevel));
        printWriter.print("mBatteryStatus=");
        printWriter.println(Integer.toString(this.mBatteryStatus));
        printWriter.print("mPlugType=");
        printWriter.println(Integer.toString(this.mPlugType));
        printWriter.print("mInvalidCharger=");
        printWriter.println(Integer.toString(this.mInvalidCharger));
        printWriter.print("mScreenOffTime=");
        printWriter.print(this.mScreenOffTime);
        if (this.mScreenOffTime >= 0) {
            printWriter.print(" (");
            printWriter.print(SystemClock.elapsedRealtime() - this.mScreenOffTime);
            printWriter.print(" ago)");
        }
        printWriter.println();
        printWriter.print("soundTimeout=");
        printWriter.println(Settings.Global.getInt(this.mContext.getContentResolver(), "low_battery_sound_timeout", 0));
        printWriter.print("bucket: ");
        printWriter.println(Integer.toString(findBatteryLevelBucket(this.mBatteryLevel)));
        printWriter.print("mEnableSkinTemperatureWarning=");
        printWriter.println(this.mEnableSkinTemperatureWarning);
        printWriter.print("mEnableUsbTemperatureAlarm=");
        printWriter.println(this.mEnableUsbTemperatureAlarm);
        ((PowerNotificationWarningsGoogleImpl) this.mWarnings).dump(printWriter);
    }

    public final int findBatteryLevelBucket(int i) {
        if (i >= this.mLowBatteryAlertCloseLevel) {
            return 1;
        }
        int[] iArr = this.mLowBatteryReminderLevels;
        if (i > iArr[0]) {
            return 0;
        }
        for (int length = iArr.length - 1; length >= 0; length--) {
            if (i <= iArr[length]) {
                return (-1) - length;
            }
        }
        throw new RuntimeException("not possible!");
    }

    public void maybeShowHybridWarning(BatteryStateSnapshot batteryStateSnapshot, BatteryStateSnapshot batteryStateSnapshot2) {
        int i = batteryStateSnapshot.batteryLevel;
        boolean z = DEBUG;
        if (i >= 30) {
            this.mLowWarningShownThisChargeCycle = false;
            this.mSevereWarningShownThisChargeCycle = false;
            if (z) {
                Slog.d("PowerUI", "Charge cycle reset! Can show warnings again");
            }
        }
        if (batteryStateSnapshot.bucket == batteryStateSnapshot2.bucket) {
            boolean z2 = batteryStateSnapshot2.plugged;
        }
        boolean shouldShowHybridWarning = shouldShowHybridWarning(batteryStateSnapshot);
        WarningsUI warningsUI = this.mWarnings;
        if (!shouldShowHybridWarning) {
            if (shouldDismissHybridWarning(batteryStateSnapshot)) {
                if (z) {
                    Slog.d("PowerUI", "Dismissing warning");
                }
                ((PowerNotificationWarningsGoogleImpl) warningsUI).dismissLowBatteryWarning();
                return;
            } else {
                if (z) {
                    Slog.d("PowerUI", "Updating warning");
                }
                warningsUI.getClass();
                return;
            }
        }
        warningsUI.getClass();
        if (batteryStateSnapshot.batteryLevel > batteryStateSnapshot.severeLevelThreshold) {
            Slog.d("PowerUI", "Low warning marked as shown this cycle");
            this.mLowWarningShownThisChargeCycle = true;
            return;
        }
        this.mSevereWarningShownThisChargeCycle = true;
        this.mLowWarningShownThisChargeCycle = true;
        if (z) {
            Slog.d("PowerUI", "Severe warning marked as shown this cycle");
        }
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        if ((this.mLastConfiguration.updateFrom(configuration) & 3) != 0) {
            this.mHandler.post(new PowerUI$$ExternalSyntheticLambda0(0, this));
        }
    }

    public Estimate refreshEstimateIfNeeded() {
        BatteryStateSnapshot batteryStateSnapshot = this.mLastBatteryStateSnapshot;
        if (batteryStateSnapshot != null) {
            long j = batteryStateSnapshot.timeRemainingMillis;
            if (j != -1 && this.mBatteryLevel == batteryStateSnapshot.batteryLevel) {
                return new Estimate(j, batteryStateSnapshot.averageTimeToDischargeMillis, batteryStateSnapshot.isBasedOnUsage);
            }
        }
        Estimate estimate = ((EnhancedEstimatesGoogleImpl) this.mEnhancedEstimates).getEstimate();
        if (DEBUG) {
            Slog.d("PowerUI", "updated estimate: " + estimate.estimateMillis);
        }
        return estimate;
    }

    public boolean shouldDismissHybridWarning(BatteryStateSnapshot batteryStateSnapshot) {
        return batteryStateSnapshot.plugged || batteryStateSnapshot.batteryLevel > batteryStateSnapshot.lowLevelThreshold;
    }

    public boolean shouldDismissLowBatteryWarning(BatteryStateSnapshot batteryStateSnapshot, BatteryStateSnapshot batteryStateSnapshot2) {
        if (!batteryStateSnapshot.isPowerSaver && !batteryStateSnapshot.plugged) {
            int i = batteryStateSnapshot2.bucket;
            int i2 = batteryStateSnapshot.bucket;
            if (i2 <= i || i2 <= 0) {
                return false;
            }
        }
        return true;
    }

    public boolean shouldShowHybridWarning(BatteryStateSnapshot batteryStateSnapshot) {
        boolean z = batteryStateSnapshot.plugged;
        int i = batteryStateSnapshot.batteryStatus;
        if (z || i == 1) {
            StringBuilder sb = new StringBuilder("can't show warning due to - plugged: ");
            sb.append(batteryStateSnapshot.plugged);
            sb.append(" status unknown: ");
            sb.append(i == 1);
            Slog.d("PowerUI", sb.toString());
            return false;
        }
        boolean z2 = this.mLowWarningShownThisChargeCycle;
        int i2 = batteryStateSnapshot.batteryLevel;
        boolean z3 = (!z2 && !batteryStateSnapshot.isPowerSaver && i2 <= batteryStateSnapshot.lowLevelThreshold) || (!this.mSevereWarningShownThisChargeCycle && i2 <= batteryStateSnapshot.severeLevelThreshold);
        if (DEBUG) {
            Slog.d("PowerUI", "Enhanced trigger is: " + z3 + "\nwith battery snapshot: mLowWarningShownThisChargeCycle: " + this.mLowWarningShownThisChargeCycle + " mSevereWarningShownThisChargeCycle: " + this.mSevereWarningShownThisChargeCycle + "\n" + batteryStateSnapshot.toString());
        }
        return z3;
    }

    public boolean shouldShowLowBatteryWarning(BatteryStateSnapshot batteryStateSnapshot, BatteryStateSnapshot batteryStateSnapshot2) {
        if (!batteryStateSnapshot.plugged && !batteryStateSnapshot.isPowerSaver) {
            int i = batteryStateSnapshot2.bucket;
            int i2 = batteryStateSnapshot.bucket;
            if ((i2 < i || batteryStateSnapshot2.plugged) && i2 < 0 && batteryStateSnapshot.batteryStatus != 1) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showInattentiveSleepWarning() {
        if (this.mOverlayView == null) {
            this.mOverlayView = new InattentiveSleepWarningView(this.mContext, this.mLazyViewCapture);
        }
        InattentiveSleepWarningView inattentiveSleepWarningView = this.mOverlayView;
        if (inattentiveSleepWarningView.getParent() != null) {
            if (inattentiveSleepWarningView.mFadeOutAnimator.isStarted()) {
                inattentiveSleepWarningView.mFadeOutAnimator.cancel();
                return;
            }
            return;
        }
        inattentiveSleepWarningView.setAlpha(1.0f);
        inattentiveSleepWarningView.setVisibility(0);
        ViewCaptureAwareWindowManager viewCaptureAwareWindowManager = inattentiveSleepWarningView.mWindowManager;
        IBinder iBinder = inattentiveSleepWarningView.mWindowToken;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2006, 256, -3);
        layoutParams.privateFlags |= 16;
        layoutParams.setTitle("InattentiveSleepWarning");
        layoutParams.token = iBinder;
        viewCaptureAwareWindowManager.addView(inattentiveSleepWarningView, layoutParams);
        inattentiveSleepWarningView.announceForAccessibility(inattentiveSleepWarningView.getContext().getString(R.string.inattentive_sleep_warning_message));
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        Intent registerReceiver;
        final int i = 1;
        final int i2 = 0;
        this.mScreenOffTime = this.mPowerManager.isScreenOn() ? -1L : SystemClock.elapsedRealtime();
        this.mLastConfiguration.setTo(this.mContext.getResources().getConfiguration());
        Handler handler = this.mHandler;
        ContentObserver contentObserver = new ContentObserver(this, handler) { // from class: com.android.systemui.power.PowerUI.4
            public final /* synthetic */ PowerUI this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i2) {
                    case 0:
                        this.this$0.updateBatteryWarningLevels();
                        break;
                    case 1:
                        this.this$0.doSkinThermalEventListenerRegistration();
                        break;
                    default:
                        this.this$0.doUsbThermalEventListenerRegistration();
                        break;
                }
            }
        };
        ContentResolver contentResolver = this.mContext.getContentResolver();
        int i3 = -1;
        contentResolver.registerContentObserver(Settings.Global.getUriFor("low_power_trigger_level"), false, contentObserver, -1);
        updateBatteryWarningLevels();
        Receiver receiver = this.mReceiver;
        receiver.getClass();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        PowerUI powerUI = PowerUI.this;
        powerUI.mBroadcastDispatcher.registerReceiverWithHandler(receiver, intentFilter, powerUI.mHandler);
        if (!receiver.mHasReceivedBattery && (registerReceiver = PowerUI.this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"))) != null) {
            receiver.onReceive(PowerUI.this.mContext, registerReceiver);
        }
        ((UserTrackerImpl) this.mUserTracker).addCallback(this.mUserChangedCallback, this.mContext.getMainExecutor());
        this.mWakefulnessLifecycle.addObserver(this.mWakefulnessObserver);
        int i4 = this.mContext.getSharedPreferences("powerui_prefs", 0).getInt("boot_count", -1);
        try {
            i3 = Settings.Global.getInt(this.mContext.getContentResolver(), "boot_count");
        } catch (Settings.SettingNotFoundException unused) {
            Slog.e("PowerUI", "Failed to read system boot count from Settings.Global.BOOT_COUNT");
        }
        if (i3 > i4) {
            this.mContext.getSharedPreferences("powerui_prefs", 0).edit().putInt("boot_count", i3).apply();
            if (this.mPowerManager.getLastShutdownReason() == 4) {
                PowerNotificationWarnings powerNotificationWarnings = (PowerNotificationWarnings) this.mWarnings;
                String string = powerNotificationWarnings.mContext.getString(R.string.thermal_shutdown_message);
                Notification.Builder color = new Notification.Builder(powerNotificationWarnings.mContext, PluginManager.NOTIFICATION_CHANNEL_ID).setSmallIcon(R.drawable.ic_device_thermostat_24).setWhen(0L).setShowWhen(false).setContentTitle(powerNotificationWarnings.mContext.getString(R.string.thermal_shutdown_title)).setContentText(string).setStyle(new Notification.BigTextStyle().bigText(string)).setVisibility(1).setContentIntent(powerNotificationWarnings.pendingBroadcast("PNW.clickedThermalShutdownWarning")).setDeleteIntent(powerNotificationWarnings.pendingBroadcast("PNW.dismissedThermalShutdownWarning")).setColor(Utils.getColorAttrDefaultColor(android.R.attr.colorError, 0, powerNotificationWarnings.mContext));
                SystemUIApplication.overrideNotificationAppName(powerNotificationWarnings.mContext, color, false);
                powerNotificationWarnings.mNoMan.notifyAsUser("high_temp", 39, color.build(), UserHandle.ALL);
            }
        }
        contentResolver.registerContentObserver(Settings.Global.getUriFor("show_temperature_warning"), false, new ContentObserver(this, handler) { // from class: com.android.systemui.power.PowerUI.4
            public final /* synthetic */ PowerUI this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i) {
                    case 0:
                        this.this$0.updateBatteryWarningLevels();
                        break;
                    case 1:
                        this.this$0.doSkinThermalEventListenerRegistration();
                        break;
                    default:
                        this.this$0.doUsbThermalEventListenerRegistration();
                        break;
                }
            }
        });
        final int i5 = 2;
        contentResolver.registerContentObserver(Settings.Global.getUriFor("show_usb_temperature_alarm"), false, new ContentObserver(this, handler) { // from class: com.android.systemui.power.PowerUI.4
            public final /* synthetic */ PowerUI this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i5) {
                    case 0:
                        this.this$0.updateBatteryWarningLevels();
                        break;
                    case 1:
                        this.this$0.doSkinThermalEventListenerRegistration();
                        break;
                    default:
                        this.this$0.doUsbThermalEventListenerRegistration();
                        break;
                }
            }
        });
        doSkinThermalEventListenerRegistration();
        doUsbThermalEventListenerRegistration();
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
        IVrManager iVrManager = this.mVrManager;
        if (iVrManager != null) {
            try {
                iVrManager.registerListener(this.mVrStateCallbacks);
            } catch (RemoteException e) {
                Slog.e("PowerUI", "Failed to register VR mode state listener: " + e);
            }
        }
    }

    public final void updateBatteryWarningLevels() {
        int integer = this.mContext.getResources().getInteger(android.R.integer.config_criticalBatteryWarningLevel);
        int integer2 = this.mContext.getResources().getInteger(android.R.integer.config_lowBatteryCloseWarningBump);
        if (integer2 < integer) {
            integer2 = integer;
        }
        int[] iArr = this.mLowBatteryReminderLevels;
        iArr[0] = integer2;
        iArr[1] = integer;
        this.mLowBatteryAlertCloseLevel = this.mContext.getResources().getInteger(android.R.integer.config_lowBatteryAutoTriggerDefaultLevel) + integer2;
    }
}
