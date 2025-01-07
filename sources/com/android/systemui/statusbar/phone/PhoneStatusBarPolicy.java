package com.android.systemui.statusbar.phone;

import android.app.AlarmManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.UserManager;
import android.telecom.TelecomManager;
import android.text.format.DateFormat;
import android.util.Log;
import com.android.internal.view.RotationPolicy;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl;
import com.android.systemui.plugins.clocks.WeatherData;
import com.android.systemui.privacy.PrivacyItem;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.privacy.PrivacyType;
import com.android.systemui.privacy.logging.PrivacyLogger;
import com.android.systemui.screenrecord.RecordingController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.policy.BluetoothController$Callback;
import com.android.systemui.statusbar.policy.BluetoothControllerImpl;
import com.android.systemui.statusbar.policy.CastController$Callback;
import com.android.systemui.statusbar.policy.CastControllerImpl;
import com.android.systemui.statusbar.policy.DataSaverController$Listener;
import com.android.systemui.statusbar.policy.DataSaverControllerImpl;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.LocationControllerImpl;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.statusbar.policy.RotationLockControllerImpl;
import com.android.systemui.statusbar.policy.SensorPrivacyControllerImpl;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor;
import com.android.systemui.util.RingerModeTrackerImpl;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.time.DateFormatUtil;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class PhoneStatusBarPolicy implements BluetoothController$Callback, CommandQueue.Callbacks, RotationLockController.RotationLockControllerCallback, DataSaverController$Listener, DeviceProvisionedController.DeviceProvisionedListener, KeyguardStateController.Callback, PrivacyItemController.Callback, LocationController.LocationChangeCallback, RecordingController.RecordingStateChangeCallback {
    public static final boolean DEBUG = Log.isLoggable("PhoneStatusBarPolicy", 3);
    public static final int LOCATION_STATUS_ICON_ID = PrivacyType.TYPE_LOCATION.getIconId();
    public final AlarmManager mAlarmManager;
    public final BluetoothControllerImpl mBluetooth;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final AnonymousClass4 mCastCallback;
    public final CommandQueue mCommandQueue;
    public final ConnectedDisplayInteractorImpl mConnectedDisplayInteractor;
    public boolean mCurrentUserSetup;
    public final DataSaverControllerImpl mDataSaver;
    public final DateFormatUtil mDateFormatUtil;
    public final int mDisplayId;
    public final Handler mHandler;
    public final HotspotController mHotspot;
    public final StatusBarIconController mIconController;
    public final AnonymousClass7 mIntentReceiver;
    public final JavaAdapter mJavaAdapter;
    public final KeyguardStateController mKeyguardStateController;
    public final LocationController mLocationController;
    public final Executor mMainExecutor;
    public boolean mMuteVisible;
    public AlarmManager.AlarmClockInfo mNextAlarm;
    public final AnonymousClass5 mNextAlarmCallback;
    public final NextAlarmController mNextAlarmController;
    public final PrivacyItemController mPrivacyItemController;
    public final PrivacyLogger mPrivacyLogger;
    public final DeviceProvisionedController mProvisionedController;
    public final AnonymousClass8 mRemoveCastIconRunnable;
    public final Resources mResources;
    public final RingerModeTrackerImpl mRingerModeTracker;
    public final RotationLockController mRotationLockController;
    public final SensorPrivacyControllerImpl mSensorPrivacyController;
    public final AnonymousClass6 mSensorPrivacyListener;
    public final SharedPreferences mSharedPreferences;
    public final String mSlotAlarmClock;
    public final String mSlotBluetooth;
    public final String mSlotCamera;
    public final String mSlotCast;
    public final String mSlotConnectedDisplay;
    public final String mSlotDataSaver;
    public final String mSlotHeadset;
    public final String mSlotHotspot;
    public final String mSlotLocation;
    public final String mSlotManagedProfile;
    public final String mSlotMicrophone;
    public final String mSlotMute;
    public final String mSlotRotate;
    public final String mSlotScreenRecord;
    public final String mSlotSensorsOff;
    public final String mSlotTty;
    public final String mSlotVibrate;
    public final String mSlotZen;
    public final TelecomManager mTelecomManager;
    public final Executor mUiBgExecutor;
    public final UserInfoControllerImpl mUserInfoController;
    public final UserManager mUserManager;
    public final UserTracker mUserTracker;
    public boolean mVibrateVisible;
    public final ZenModeController mZenController;
    public boolean mZenVisible;
    public boolean mProfileIconVisible = false;
    public final AnonymousClass1 mZenControllerCallback = new ZenModeController.Callback() { // from class: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.1
        @Override // com.android.systemui.statusbar.policy.ZenModeController.Callback
        public final void onConsolidatedPolicyChanged() {
            boolean z = PhoneStatusBarPolicy.DEBUG;
            PhoneStatusBarPolicy.this.updateVolumeZen();
        }

        @Override // com.android.systemui.statusbar.policy.ZenModeController.Callback
        public final void onZenChanged(int i) {
            boolean z = PhoneStatusBarPolicy.DEBUG;
            PhoneStatusBarPolicy.this.updateVolumeZen();
        }
    };
    public final UserTracker.Callback mUserSwitchListener = new AnonymousClass2();
    public final AnonymousClass3 mHotspotCallback = new HotspotController.Callback() { // from class: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.3
        @Override // com.android.systemui.statusbar.policy.HotspotController.Callback
        public final void onHotspotChanged(int i, boolean z) {
            PhoneStatusBarPolicy phoneStatusBarPolicy = PhoneStatusBarPolicy.this;
            ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotHotspot, z);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy$2, reason: invalid class name */
    class AnonymousClass2 implements UserTracker.Callback {
        public AnonymousClass2() {
        }

        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            PhoneStatusBarPolicy.this.mHandler.post(new PhoneStatusBarPolicy$2$$ExternalSyntheticLambda0(this, 0));
        }

        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanging(int i) {
            PhoneStatusBarPolicy.this.mHandler.post(new PhoneStatusBarPolicy$2$$ExternalSyntheticLambda0(this, 1));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy$6, reason: invalid class name */
    public final class AnonymousClass6 {
        public AnonymousClass6() {
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.systemui.statusbar.phone.PhoneStatusBarPolicy$1] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.android.systemui.statusbar.phone.PhoneStatusBarPolicy$3] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.android.systemui.statusbar.phone.PhoneStatusBarPolicy$5] */
    /* JADX WARN: Type inference failed for: r2v7, types: [com.android.systemui.statusbar.phone.PhoneStatusBarPolicy$7] */
    public PhoneStatusBarPolicy(StatusBarIconController statusBarIconController, CommandQueue commandQueue, BroadcastDispatcher broadcastDispatcher, Executor executor, Executor executor2, Looper looper, Resources resources, CastControllerImpl castControllerImpl, HotspotController hotspotController, BluetoothControllerImpl bluetoothControllerImpl, NextAlarmController nextAlarmController, UserInfoControllerImpl userInfoControllerImpl, RotationLockController rotationLockController, DataSaverControllerImpl dataSaverControllerImpl, ZenModeController zenModeController, DeviceProvisionedController deviceProvisionedController, KeyguardStateController keyguardStateController, LocationController locationController, SensorPrivacyControllerImpl sensorPrivacyControllerImpl, AlarmManager alarmManager, UserManager userManager, UserTracker userTracker, DevicePolicyManager devicePolicyManager, RecordingController recordingController, TelecomManager telecomManager, int i, SharedPreferences sharedPreferences, DateFormatUtil dateFormatUtil, RingerModeTrackerImpl ringerModeTrackerImpl, PrivacyItemController privacyItemController, PrivacyLogger privacyLogger, ConnectedDisplayInteractorImpl connectedDisplayInteractorImpl, ZenModeInteractor zenModeInteractor, JavaAdapter javaAdapter) {
        new CastController$Callback() { // from class: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.4
            @Override // com.android.systemui.statusbar.policy.CastController$Callback
            public final void onCastDevicesChanged() {
                boolean z = PhoneStatusBarPolicy.DEBUG;
                PhoneStatusBarPolicy.this.getClass();
            }
        };
        this.mNextAlarmCallback = new NextAlarmController.NextAlarmChangeCallback() { // from class: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.5
            @Override // com.android.systemui.statusbar.policy.NextAlarmController.NextAlarmChangeCallback
            public final void onNextAlarmChanged(AlarmManager.AlarmClockInfo alarmClockInfo) {
                PhoneStatusBarPolicy phoneStatusBarPolicy = PhoneStatusBarPolicy.this;
                phoneStatusBarPolicy.mNextAlarm = alarmClockInfo;
                phoneStatusBarPolicy.updateAlarm();
            }
        };
        this.mSensorPrivacyListener = new AnonymousClass6();
        this.mIntentReceiver = new BroadcastReceiver() { // from class: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.7
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                char c;
                String action = intent.getAction();
                switch (action.hashCode()) {
                    case -1846941263:
                        if (action.equals("android.intent.action.PROFILE_ACCESSIBLE")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1823736795:
                        if (action.equals("android.intent.action.PROFILE_REMOVED")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1676458352:
                        if (action.equals("android.intent.action.HEADSET_PLUG")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1238404651:
                        if (action.equals("android.intent.action.MANAGED_PROFILE_UNAVAILABLE")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case -864107122:
                        if (action.equals("android.intent.action.MANAGED_PROFILE_AVAILABLE")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case -229777127:
                        if (action.equals("android.intent.action.SIM_STATE_CHANGED")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 11531734:
                        if (action.equals("android.intent.action.PROFILE_INACCESSIBLE")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1051344550:
                        if (action.equals("android.telecom.action.CURRENT_TTY_MODE_CHANGED")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        intent.getBooleanExtra("rebroadcastOnUnlock", false);
                        break;
                    case 1:
                        PhoneStatusBarPolicy phoneStatusBarPolicy = PhoneStatusBarPolicy.this;
                        int intExtra = intent.getIntExtra("android.telecom.extra.CURRENT_TTY_MODE", 0);
                        boolean z = PhoneStatusBarPolicy.DEBUG;
                        phoneStatusBarPolicy.updateTTY(intExtra);
                        break;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        PhoneStatusBarPolicy phoneStatusBarPolicy2 = PhoneStatusBarPolicy.this;
                        boolean z2 = PhoneStatusBarPolicy.DEBUG;
                        phoneStatusBarPolicy2.updateProfileIcon();
                        break;
                    case 7:
                        PhoneStatusBarPolicy phoneStatusBarPolicy3 = PhoneStatusBarPolicy.this;
                        boolean z3 = PhoneStatusBarPolicy.DEBUG;
                        phoneStatusBarPolicy3.getClass();
                        boolean z4 = intent.getIntExtra(WeatherData.STATE_KEY, 0) != 0;
                        boolean z5 = intent.getIntExtra("microphone", 0) != 0;
                        String str = phoneStatusBarPolicy3.mSlotHeadset;
                        StatusBarIconController statusBarIconController2 = phoneStatusBarPolicy3.mIconController;
                        if (!z4) {
                            ((StatusBarIconControllerImpl) statusBarIconController2).setIconVisibility(str, false);
                            break;
                        } else {
                            StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) statusBarIconController2;
                            statusBarIconControllerImpl.setIcon(phoneStatusBarPolicy3.mResources.getString(z5 ? R.string.accessibility_status_bar_headset : R.string.accessibility_status_bar_headphones), str, z5 ? R.drawable.stat_sys_headset_mic : R.drawable.stat_sys_headset);
                            statusBarIconControllerImpl.setIconVisibility(str, true);
                            break;
                        }
                }
            }
        };
        this.mIconController = statusBarIconController;
        this.mCommandQueue = commandQueue;
        this.mConnectedDisplayInteractor = connectedDisplayInteractorImpl;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mHandler = new Handler(looper);
        this.mResources = resources;
        this.mHotspot = hotspotController;
        this.mBluetooth = bluetoothControllerImpl;
        this.mNextAlarmController = nextAlarmController;
        this.mAlarmManager = alarmManager;
        this.mUserInfoController = userInfoControllerImpl;
        this.mUserManager = userManager;
        this.mUserTracker = userTracker;
        this.mRotationLockController = rotationLockController;
        this.mDataSaver = dataSaverControllerImpl;
        this.mZenController = zenModeController;
        this.mProvisionedController = deviceProvisionedController;
        this.mKeyguardStateController = keyguardStateController;
        this.mLocationController = locationController;
        this.mPrivacyItemController = privacyItemController;
        this.mSensorPrivacyController = sensorPrivacyControllerImpl;
        this.mMainExecutor = executor;
        this.mUiBgExecutor = executor2;
        this.mTelecomManager = telecomManager;
        this.mRingerModeTracker = ringerModeTrackerImpl;
        this.mPrivacyLogger = privacyLogger;
        this.mJavaAdapter = javaAdapter;
        this.mSlotCast = resources.getString(android.R.string.stk_cc_ss_to_ussd);
        this.mSlotConnectedDisplay = resources.getString(android.R.string.stk_cc_ussd_to_ss);
        this.mSlotHotspot = resources.getString(android.R.string.storage_usb);
        this.mSlotBluetooth = resources.getString(android.R.string.stk_cc_ss_to_dial);
        this.mSlotTty = resources.getString(android.R.string.textSelectionCABTitle);
        this.mSlotZen = resources.getString(android.R.string.time_picker_decrement_set_am_button);
        this.mSlotMute = resources.getString(android.R.string.sync_binding_label);
        this.mSlotVibrate = resources.getString(android.R.string.time_of_day);
        this.mSlotAlarmClock = resources.getString(android.R.string.status_bar_wifi);
        this.mSlotManagedProfile = resources.getString(android.R.string.submit);
        this.mSlotRotate = resources.getString(android.R.string.system_error_manufacturer);
        this.mSlotHeadset = resources.getString(android.R.string.storage_sd_card_label);
        this.mSlotDataSaver = resources.getString(android.R.string.storage_internal);
        this.mSlotLocation = resources.getString(android.R.string.storage_usb_drive_label);
        this.mSlotMicrophone = resources.getString(android.R.string.supervised_user_creation_label);
        this.mSlotCamera = resources.getString(android.R.string.stk_cc_ss_to_ss);
        this.mSlotSensorsOff = resources.getString(android.R.string.system_ui_date_pattern);
        this.mSlotScreenRecord = resources.getString(android.R.string.system_error_wipe_data);
        this.mDisplayId = i;
        this.mSharedPreferences = sharedPreferences;
        this.mDateFormatUtil = dateFormatUtil;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void appTransitionFinished(int i) {
        if (this.mDisplayId == i) {
            updateProfileIcon();
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void appTransitionStarting(int i, long j, long j2, boolean z) {
        if (this.mDisplayId == i) {
            updateProfileIcon();
        }
    }

    @Override // com.android.systemui.statusbar.policy.BluetoothController$Callback
    public final void onBluetoothDevicesChanged() {
        updateBluetooth();
    }

    @Override // com.android.systemui.statusbar.policy.BluetoothController$Callback
    public final void onBluetoothStateChange() {
        updateBluetooth();
    }

    @Override // com.android.systemui.statusbar.policy.DataSaverController$Listener
    public final void onDataSaverChanged(boolean z) {
        ((StatusBarIconControllerImpl) this.mIconController).setIconVisibility(this.mSlotDataSaver, z);
    }

    @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
    public final void onKeyguardShowingChanged() {
        updateProfileIcon();
    }

    @Override // com.android.systemui.statusbar.policy.LocationController.LocationChangeCallback
    public final void onLocationActiveChanged() {
        if (this.mPrivacyItemController.privacyConfig.locationAvailable) {
            return;
        }
        boolean z = ((LocationControllerImpl) this.mLocationController).mAreActiveLocationRequests;
        String str = this.mSlotLocation;
        StatusBarIconController statusBarIconController = this.mIconController;
        if (z) {
            ((StatusBarIconControllerImpl) statusBarIconController).setIconVisibility(str, true);
        } else {
            ((StatusBarIconControllerImpl) statusBarIconController).setIconVisibility(str, false);
        }
    }

    @Override // com.android.systemui.privacy.PrivacyItemController.Callback
    public final void onPrivacyItemsChanged(List list) {
        Iterator it = list.iterator();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (it.hasNext()) {
            PrivacyItem privacyItem = (PrivacyItem) it.next();
            if (privacyItem == null) {
                Log.e("PhoneStatusBarPolicy", "updatePrivacyItems - null item found");
                StringWriter stringWriter = new StringWriter();
                this.mPrivacyItemController.dump(new PrintWriter(stringWriter), null);
                throw new NullPointerException(stringWriter.toString());
            }
            int ordinal = privacyItem.privacyType.ordinal();
            if (ordinal == 0) {
                z = true;
            } else if (ordinal == 1) {
                z2 = true;
            } else if (ordinal == 2) {
                z3 = true;
            }
        }
        this.mPrivacyLogger.logStatusBarIconsVisible(z, z2, z3);
    }

    @Override // com.android.systemui.statusbar.policy.RotationLockController.RotationLockControllerCallback
    public final void onRotationLockStateChanged(boolean z) {
        Resources resources = this.mResources;
        int rotationLockOrientation = RotationPolicy.getRotationLockOrientation(((RotationLockControllerImpl) this.mRotationLockController).mRotationPolicy.context);
        boolean z2 = rotationLockOrientation != 0 ? rotationLockOrientation != 2 : resources.getConfiguration().orientation != 2;
        String str = this.mSlotRotate;
        StatusBarIconController statusBarIconController = this.mIconController;
        if (!z) {
            ((StatusBarIconControllerImpl) statusBarIconController).setIconVisibility(str, false);
            return;
        }
        if (z2) {
            ((StatusBarIconControllerImpl) statusBarIconController).setIcon(this.mResources.getString(R.string.accessibility_rotation_lock_on_portrait), str, R.drawable.stat_sys_rotate_portrait);
        } else {
            ((StatusBarIconControllerImpl) statusBarIconController).setIcon(this.mResources.getString(R.string.accessibility_rotation_lock_on_landscape), str, R.drawable.stat_sys_rotate_landscape);
        }
        ((StatusBarIconControllerImpl) statusBarIconController).setIconVisibility(str, true);
    }

    @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
    public final void onUserSetupChanged() {
        boolean isCurrentUserSetup = ((DeviceProvisionedControllerImpl) this.mProvisionedController).isCurrentUserSetup();
        if (this.mCurrentUserSetup == isCurrentUserSetup) {
            return;
        }
        this.mCurrentUserSetup = isCurrentUserSetup;
        updateAlarm();
    }

    public final void updateAlarm() {
        String string;
        AlarmManager.AlarmClockInfo nextAlarmClock = this.mAlarmManager.getNextAlarmClock(((UserTrackerImpl) this.mUserTracker).getUserId());
        boolean z = false;
        boolean z2 = nextAlarmClock != null && nextAlarmClock.getTriggerTime() > 0;
        boolean z3 = ((ZenModeControllerImpl) this.mZenController).mZenMode == 2;
        StatusBarIconController statusBarIconController = this.mIconController;
        String str = this.mSlotAlarmClock;
        int i = z3 ? R.drawable.stat_sys_alarm_dim : R.drawable.stat_sys_alarm;
        if (this.mNextAlarm == null) {
            string = this.mResources.getString(R.string.status_bar_alarm);
        } else {
            string = this.mResources.getString(R.string.accessibility_quick_settings_alarm, DateFormat.format(DateFormat.getBestDateTimePattern(Locale.getDefault(), this.mDateFormatUtil.is24HourFormat() ? "EHm" : "Ehma"), this.mNextAlarm.getTriggerTime()).toString());
        }
        ((StatusBarIconControllerImpl) statusBarIconController).setIcon(string, str, i);
        StatusBarIconController statusBarIconController2 = this.mIconController;
        String str2 = this.mSlotAlarmClock;
        if (this.mCurrentUserSetup && z2) {
            z = true;
        }
        ((StatusBarIconControllerImpl) statusBarIconController2).setIconVisibility(str2, z);
    }

    public final void updateBluetooth() {
        boolean z;
        String string = this.mResources.getString(R.string.accessibility_quick_settings_bluetooth_on);
        BluetoothControllerImpl bluetoothControllerImpl = this.mBluetooth;
        if (bluetoothControllerImpl == null || bluetoothControllerImpl.mConnectionState != 2 || (!bluetoothControllerImpl.mIsActive && bluetoothControllerImpl.mAudioProfileOnly)) {
            z = false;
        } else {
            string = this.mResources.getString(R.string.accessibility_bluetooth_connected);
            z = bluetoothControllerImpl.mEnabled;
        }
        StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) this.mIconController;
        String str = this.mSlotBluetooth;
        statusBarIconControllerImpl.setIcon(string, str, R.drawable.stat_sys_data_bluetooth_connected);
        statusBarIconControllerImpl.setIconVisibility(str, z);
    }

    public final void updateProfileIcon() {
        this.mUiBgExecutor.execute(new PhoneStatusBarPolicy$$ExternalSyntheticLambda0(this, 0));
    }

    public final void updateTTY(int i) {
        boolean z = i != 0;
        String str = this.mSlotTty;
        StatusBarIconController statusBarIconController = this.mIconController;
        if (!z) {
            ((StatusBarIconControllerImpl) statusBarIconController).setIconVisibility(str, false);
        } else {
            ((StatusBarIconControllerImpl) statusBarIconController).setIcon(this.mResources.getString(R.string.accessibility_tty_enabled), str, R.drawable.stat_sys_tty_mode);
            ((StatusBarIconControllerImpl) statusBarIconController).setIconVisibility(str, true);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0089, code lost:
    
        if (r0.intValue() == 0) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateVolumeZen() {
        /*
            r10 = this;
            com.android.systemui.statusbar.policy.ZenModeController r0 = r10.mZenController
            com.android.systemui.statusbar.policy.ZenModeControllerImpl r0 = (com.android.systemui.statusbar.policy.ZenModeControllerImpl) r0
            int r0 = r0.mZenMode
            android.content.SharedPreferences r1 = r10.mSharedPreferences
            android.content.Intent r2 = com.android.systemui.qs.tiles.DndTile.ZEN_SETTINGS
            java.lang.String r2 = "DndTileVisible"
            r3 = 0
            boolean r1 = r1.getBoolean(r2, r3)
            r2 = 1
            r4 = 2131233492(0x7f080ad4, float:1.8083123E38)
            if (r1 != 0) goto L42
            android.content.SharedPreferences r1 = r10.mSharedPreferences
            java.lang.String r5 = "DndTileCombinedIcon"
            boolean r1 = r1.getBoolean(r5, r3)
            if (r1 == 0) goto L22
            goto L42
        L22:
            r1 = 2
            if (r0 != r1) goto L31
            android.content.res.Resources r1 = r10.mResources
            r5 = 2131952846(0x7f1304ce, float:1.9542146E38)
            java.lang.String r1 = r1.getString(r5)
        L2e:
            r5 = r1
            r1 = r2
            goto L50
        L31:
            if (r0 != r2) goto L3d
            android.content.res.Resources r1 = r10.mResources
            r5 = 2131952849(0x7f1304d1, float:1.9542152E38)
            java.lang.String r1 = r1.getString(r5)
            goto L2e
        L3d:
            r1 = 0
            r5 = r1
            r1 = r3
            r4 = r1
            goto L50
        L42:
            if (r0 == 0) goto L46
            r1 = r2
            goto L47
        L46:
            r1 = r3
        L47:
            android.content.res.Resources r5 = r10.mResources
            r6 = 2131953879(0x7f1308d7, float:1.9544241E38)
            java.lang.String r5 = r5.getString(r6)
        L50:
            java.lang.String r6 = r10.mSlotZen
            com.android.systemui.statusbar.phone.ui.StatusBarIconController r7 = r10.mIconController
            if (r1 == 0) goto L5c
            r8 = r7
            com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl r8 = (com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl) r8
            r8.setIcon(r5, r6, r4)
        L5c:
            boolean r4 = r10.mZenVisible
            if (r1 == r4) goto L67
            com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl r7 = (com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl) r7
            r7.setIconVisibility(r6, r1)
            r10.mZenVisible = r1
        L67:
            com.android.systemui.statusbar.policy.ZenModeController r1 = r10.mZenController
            com.android.systemui.statusbar.policy.ZenModeControllerImpl r1 = (com.android.systemui.statusbar.policy.ZenModeControllerImpl) r1
            android.app.NotificationManager$Policy r1 = r1.mConsolidatedNotificationPolicy
            boolean r0 = android.service.notification.ZenModeConfig.isZenOverridingRinger(r0, r1)
            if (r0 != 0) goto L8c
            com.android.systemui.util.RingerModeTrackerImpl r0 = r10.mRingerModeTracker
            com.android.systemui.util.RingerModeLiveData r0 = r0.ringerModeInternal
            java.lang.Integer r0 = r0.getValue()
            int r1 = r0.intValue()
            if (r1 != r2) goto L85
            r9 = r3
            r3 = r2
            r2 = r9
            goto L8d
        L85:
            int r0 = r0.intValue()
            if (r0 != 0) goto L8c
            goto L8d
        L8c:
            r2 = r3
        L8d:
            boolean r0 = r10.mVibrateVisible
            com.android.systemui.statusbar.phone.ui.StatusBarIconController r1 = r10.mIconController
            if (r3 == r0) goto L9d
            java.lang.String r0 = r10.mSlotVibrate
            r4 = r1
            com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl r4 = (com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl) r4
            r4.setIconVisibility(r0, r3)
            r10.mVibrateVisible = r3
        L9d:
            boolean r0 = r10.mMuteVisible
            if (r2 == r0) goto Laa
            java.lang.String r0 = r10.mSlotMute
            com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl r1 = (com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl) r1
            r1.setIconVisibility(r0, r2)
            r10.mMuteVisible = r2
        Laa:
            r10.updateAlarm()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy.updateVolumeZen():void");
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy$8, reason: invalid class name */
    public final class AnonymousClass8 implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
        }
    }

    @Override // com.android.systemui.screenrecord.RecordingController.RecordingStateChangeCallback
    public final void onCountdown(long j) {
    }

    @Override // com.android.systemui.screenrecord.RecordingController.RecordingStateChangeCallback
    public final void onCountdownEnd() {
    }

    @Override // com.android.systemui.screenrecord.RecordingController.RecordingStateChangeCallback
    public final void onRecordingEnd() {
    }

    @Override // com.android.systemui.screenrecord.RecordingController.RecordingStateChangeCallback
    public final void onRecordingStart() {
    }
}
