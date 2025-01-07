package com.google.android.systemui.dreamliner;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.UserManager;
import android.service.dreams.IDreamManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.dock.DockManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.notification.interruption.NotificationInterruptSuppressor;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProviderImpl;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.google.android.systemui.dreamliner.WirelessCharger;
import com.google.android.systemui.dreamliner.WirelessChargerCommander$setFan$1;
import dagger.Lazy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DockObserver implements DockManager {
    static final String ACTION_ALIGN_STATE_CHANGE = "com.google.android.systemui.dreamliner.ALIGNMENT_CHANGE";
    static final String ACTION_DOCK_UI_ACTIVE = "com.google.android.systemui.dreamliner.ACTION_DOCK_UI_ACTIVE";
    static final String ACTION_DOCK_UI_IDLE = "com.google.android.systemui.dreamliner.ACTION_DOCK_UI_IDLE";
    static final String ACTION_REBIND_DOCK_SERVICE = "com.google.android.systemui.dreamliner.ACTION_REBIND_DOCK_SERVICE";
    static final String ACTION_START_DREAMLINER_CONTROL_SERVICE = "com.google.android.apps.dreamliner.START";
    static final String ACTION_START_TESTING = "com.google.android.systemui.dreamliner.ACTION_START_TESTING";
    static final String COMPONENTNAME_DREAMLINER_CONTROL_SERVICE = "com.google.android.apps.dreamliner/.DreamlinerControlService";
    static final String EXTRA_ALIGN_STATE = "align_state";
    static final String KEY_SHOWING = "showing";
    static final String PERMISSION_WIRELESS_CHARGER_STATUS = "com.google.android.systemui.permission.WIRELESS_CHARGER_STATUS";
    static final int RESULT_NOT_FOUND = -1;
    static final int RESULT_OK = 0;
    public static boolean sIsDockingUiShowing = false;
    public final List mAlignmentStateListeners;
    public final List mClients;
    public final ConfigurationController mConfigurationController;
    public final Context mContext;
    public final DockAlignmentController mDockAlignmentController;
    DockGestureController mDockGestureController;
    public final DockObserverBroadcastReceiver mDockObserverBroadcastReceiver;
    int mDockState;
    public ImageView mDreamlinerGear;
    DreamlinerServiceConn mDreamlinerServiceConn;
    public DockIndicationController mIndicationController;
    public final AnonymousClass2 mInterruptSuppressor;
    public final KeyguardStateController mKeyguardStateController;
    int mLastAlignState;
    public final DelayableExecutor mMainExecutor;
    public DockObserver$$ExternalSyntheticLambda1 mPhotoAction;
    public FrameLayout mPhotoPreview;
    ProtectedBroadcastSender mProtectedBroadcastSender;
    StartTestingReceiver mStartTestingReceiver;
    public final StatusBarStateController mStatusBarStateController;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserTracker mUserTracker;
    public final AnonymousClass3 mVisualInterruptionCondition;
    public final Lazy mVisualInterruptionDecisionProviderLazy;
    public final Optional mWirelessCharger;
    public final WirelessChargerCommander mWirelessChargerCommander;
    public boolean mIsWirelessCharging = false;
    final DreamlinerBroadcastReceiver mDreamlinerReceiver = new DreamlinerBroadcastReceiver();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.dreamliner.DockObserver$2, reason: invalid class name */
    public final class AnonymousClass2 implements NotificationInterruptSuppressor {
        @Override // com.android.systemui.statusbar.notification.interruption.NotificationInterruptSuppressor
        public final String getName() {
            return "DLObserver";
        }

        @Override // com.android.systemui.statusbar.notification.interruption.NotificationInterruptSuppressor
        public final boolean suppressInterruptions() {
            return DockObserver.sIsDockingUiShowing;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.dreamliner.DockObserver$3, reason: invalid class name */
    public final class AnonymousClass3 extends VisualInterruptionCondition {
        @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition
        public final boolean shouldSuppress() {
            return DockObserver.sIsDockingUiShowing;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DockObserverBroadcastReceiver extends BroadcastReceiver {
        public DockObserverBroadcastReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            char c;
            if (intent == null) {
                return;
            }
            Log.d("DLObserver", "onReceive(); " + intent.getAction());
            String action = intent.getAction();
            switch (action.hashCode()) {
                case -1886648615:
                    if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1538406691:
                    if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 798292259:
                    if (action.equals("android.intent.action.BOOT_COMPLETED")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1318602046:
                    if (action.equals(DockObserver.ACTION_REBIND_DOCK_SERVICE)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c != 0) {
                if (c == 1) {
                    DockObserver.m1766$$Nest$mstopDreamlinerService(DockObserver.this, context);
                    DockObserver.sIsDockingUiShowing = false;
                    return;
                } else {
                    if (c == 2 || c == 3) {
                        DockObserver.this.updateCurrentDockingStatus(context);
                        return;
                    }
                    return;
                }
            }
            DockObserver dockObserver = DockObserver.this;
            boolean z = dockObserver.mIsWirelessCharging;
            dockObserver.mIsWirelessCharging = DockObserver.isWirelessCharging(context, intent);
            if (z != DockObserver.this.mIsWirelessCharging) {
                CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("mWirelessCharging:"), DockObserver.this.mIsWirelessCharging, "DLObserver");
                DockObserver dockObserver2 = DockObserver.this;
                dockObserver2.tryToStartDreamlinerServiceIfNeeded(context, dockObserver2.mIsWirelessCharging);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class DreamlinerBroadcastReceiver extends BroadcastReceiver {
        public boolean mListening;

        public DreamlinerBroadcastReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            char c;
            if (intent == null) {
                return;
            }
            Log.d("DLObserver", "Dock Receiver.onReceive(): " + intent.getAction());
            String action = intent.getAction();
            switch (action.hashCode()) {
                case -1584152500:
                    if (action.equals("com.google.android.systemui.dreamliner.photo_error")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case -1579804275:
                    if (action.equals(DockObserver.ACTION_DOCK_UI_IDLE)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -545730616:
                    if (action.equals("com.google.android.systemui.dreamliner.paired")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -484477188:
                    if (action.equals("com.google.android.systemui.dreamliner.resume")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -390730981:
                    if (action.equals("com.google.android.systemui.dreamliner.undock")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 664552276:
                    if (action.equals("com.google.android.systemui.dreamliner.dream")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 675144007:
                    if (action.equals("com.google.android.systemui.dreamliner.pause")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 675346819:
                    if (action.equals("com.google.android.systemui.dreamliner.photo")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 717413661:
                    if (action.equals("com.google.android.systemui.dreamliner.assistant_poodle")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 1996802687:
                    if (action.equals(DockObserver.ACTION_DOCK_UI_ACTIVE)) {
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
                    DockObserver dockObserver = DockObserver.this;
                    dockObserver.getClass();
                    Log.d("DLObserver", "sendDockIdleIntent()");
                    dockObserver.mProtectedBroadcastSender.sendBroadcast(new Intent("android.intent.action.DOCK_IDLE").addFlags(1073741824));
                    DockObserver.sIsDockingUiShowing = true;
                    return;
                case 1:
                    DockObserver dockObserver2 = DockObserver.this;
                    dockObserver2.getClass();
                    Log.d("DLObserver", "sendDockActiveIntent()");
                    dockObserver2.mProtectedBroadcastSender.sendBroadcast(new Intent("android.intent.action.DOCK_ACTIVE").addFlags(1073741824));
                    DockObserver.sIsDockingUiShowing = false;
                    return;
                case 2:
                    DockObserver.this.getClass();
                    PowerManager powerManager = (PowerManager) context.getSystemService(PowerManager.class);
                    if (powerManager.isScreenOn()) {
                        powerManager.goToSleep(SystemClock.uptimeMillis());
                        return;
                    }
                    return;
                case 3:
                    if (DockObserver.assertNotNull(DockObserver.this.mDockGestureController)) {
                        DockObserver.this.mDockGestureController.mTapAction = (PendingIntent) intent.getParcelableExtra("single_tap_action");
                        break;
                    }
                    break;
                case 4:
                    break;
                case 5:
                    DockObserver.this.onDockStateChanged(2);
                    if (DockObserver.assertNotNull(DockObserver.this.mDockGestureController)) {
                        DockObserver.this.mDockGestureController.stopMonitoring();
                        return;
                    }
                    return;
                case 6:
                    DockObserver.this.onDockStateChanged(0);
                    if (DockObserver.assertNotNull(DockObserver.this.mDockGestureController)) {
                        DockObserver.this.mDockGestureController.stopMonitoring();
                        return;
                    }
                    return;
                case 7:
                    DockIndicationController dockIndicationController = DockObserver.this.mIndicationController;
                    if (dockIndicationController != null) {
                        dockIndicationController.mTopIconShowing = intent.getBooleanExtra(DockObserver.KEY_SHOWING, false);
                        dockIndicationController.updateVisibility$8();
                        return;
                    }
                    return;
                case '\b':
                    DockObserver dockObserver3 = DockObserver.this;
                    dockObserver3.getClass();
                    ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra("android.intent.extra.RESULT_RECEIVER");
                    boolean booleanExtra = intent.getBooleanExtra("enabled", false);
                    MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("configPhotoAction, enabled=", "DLObserver", booleanExtra);
                    DockGestureController dockGestureController = dockObserver3.mDockGestureController;
                    if (dockGestureController != null) {
                        dockGestureController.mPhotoEnabled = booleanExtra;
                    }
                    dockObserver3.mPhotoAction = (resultReceiver == null || dockObserver3.mIndicationController == null) ? null : new DockObserver$$ExternalSyntheticLambda1(dockObserver3, resultReceiver, 2);
                    DockObserver.this.runPhotoAction();
                    return;
                case '\t':
                    DockObserver dockObserver4 = DockObserver.this;
                    dockObserver4.getClass();
                    Log.w("DLObserver", "Fail to launch photo");
                    DockGestureController dockGestureController2 = dockObserver4.mDockGestureController;
                    if (dockGestureController2 != null) {
                        dockGestureController2.hidePhotoPreview(false);
                        return;
                    }
                    return;
                default:
                    return;
            }
            DockObserver.this.onDockStateChanged(1);
            if (DockObserver.assertNotNull(DockObserver.this.mDockGestureController)) {
                DockGestureController dockGestureController3 = DockObserver.this.mDockGestureController;
                dockGestureController3.mSettingsGear.setVisibility(4);
                dockGestureController3.onDozingChanged(dockGestureController3.mStatusBarStateController.isDozing());
                dockGestureController3.mStatusBarStateController.addCallback(dockGestureController3);
                ((KeyguardStateControllerImpl) dockGestureController3.mKeyguardStateController).addCallback(dockGestureController3.mKeyguardMonitorCallback);
            }
        }

        public final void registerReceiver(Context context) {
            if (this.mListening) {
                return;
            }
            UserHandle userHandle = UserHandle.ALL;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(DockObserver.ACTION_DOCK_UI_IDLE);
            intentFilter.addAction(DockObserver.ACTION_DOCK_UI_ACTIVE);
            intentFilter.addAction("com.google.android.systemui.dreamliner.dream");
            intentFilter.addAction("com.google.android.systemui.dreamliner.paired");
            intentFilter.addAction("com.google.android.systemui.dreamliner.pause");
            intentFilter.addAction("com.google.android.systemui.dreamliner.resume");
            intentFilter.addAction("com.google.android.systemui.dreamliner.undock");
            intentFilter.addAction("com.google.android.systemui.dreamliner.assistant_poodle");
            intentFilter.addAction("com.google.android.systemui.dreamliner.photo");
            intentFilter.addAction("com.google.android.systemui.dreamliner.photo_error");
            context.registerReceiverAsUser(this, userHandle, intentFilter, DockObserver.PERMISSION_WIRELESS_CHARGER_STATUS, null, 2);
            this.mListening = true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class IsDockPresentCallback implements WirelessCharger.IsDockPresentCallback {
        public final Context mContext;

        public IsDockPresentCallback(Context context) {
            this.mContext = context;
        }

        @Override // com.google.android.systemui.dreamliner.WirelessCharger.IsDockPresentCallback
        public final void onCallback(boolean z, byte b, byte b2, boolean z2, int i, int i2) {
            if (z) {
                DockObserver.m1765$$Nest$mstartDreamlinerService(DockObserver.this, this.mContext, b, b2, z2, i, i2);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ProtectedBroadcastSender {
        public final Context mContext;

        public ProtectedBroadcastSender(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public final void sendBroadcast(Intent intent) {
            if (this.mContext.getUser().isSystem()) {
                try {
                    this.mContext.sendBroadcast(intent);
                } catch (SecurityException e) {
                    Log.w("DLObserver", "Send protected broadcast failed. intent= " + intent, e);
                }
            }
        }

        public final void sendBroadcastAsUser(Intent intent, UserHandle userHandle) {
            if (this.mContext.getUser().isSystem()) {
                try {
                    this.mContext.sendBroadcastAsUser(intent, userHandle);
                } catch (SecurityException e) {
                    Log.w("DLObserver", "Send protected broadcast failed. intent= " + intent, e);
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StartTestingReceiver extends BroadcastReceiver {
        public StartTestingReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            Log.d("DLObserver", "onReceive(): " + action);
            if (!TextUtils.equals(action, DockObserver.ACTION_START_TESTING)) {
                Log.d("DLObserver", "unknown action, abort");
                return;
            }
            Log.d("DLObserver", "start testing");
            int intExtra = intent.getIntExtra("id", 0);
            DockObserver dockObserver = DockObserver.this;
            DockObserver.m1765$$Nest$mstartDreamlinerService(dockObserver, dockObserver.mContext, 0, 2, true, intExtra, 114);
        }
    }

    /* renamed from: -$$Nest$mstartDreamlinerService, reason: not valid java name */
    public static void m1765$$Nest$mstartDreamlinerService(DockObserver dockObserver, Context context, int i, int i2, boolean z, int i3, int i4) {
        boolean z2;
        synchronized (dockObserver) {
            if (dockObserver.mDreamlinerServiceConn != null) {
                return;
            }
            dockObserver.addInterruptionSuppressor();
            notifyForceEnabledAmbientDisplay(true);
            dockObserver.mDreamlinerReceiver.registerReceiver(context);
            Intent putExtra = new Intent(ACTION_START_DREAMLINER_CONTROL_SERVICE).setComponent(ComponentName.unflattenFromString(COMPONENTNAME_DREAMLINER_CONTROL_SERVICE)).putExtra("type", i).putExtra("orientation", i2).putExtra("is_get_info_supported", z).putExtra("id", i3).putExtra("manufacturer_code", i4).putExtra("occluded", ((KeyguardStateControllerImpl) dockObserver.mKeyguardStateController).mOccluded);
            DreamlinerServiceConn dreamlinerServiceConn = dockObserver.new DreamlinerServiceConn(context);
            dockObserver.mDreamlinerServiceConn = dreamlinerServiceConn;
            try {
                z2 = context.bindServiceAsUser(putExtra, dreamlinerServiceConn, 1, new UserHandle(((UserTrackerImpl) dockObserver.mUserTracker).getUserId()));
            } catch (SecurityException e) {
                Log.e("DLObserver", e.getMessage(), e);
                z2 = false;
            }
            if (z2) {
                ((ExecutorImpl) dockObserver.mMainExecutor).execute(new DockObserver$$ExternalSyntheticLambda1(dockObserver, context, 1));
            } else {
                dockObserver.mDreamlinerServiceConn = null;
                Log.w("DLObserver", "Unable to bind Dreamliner service: " + putExtra);
            }
        }
    }

    /* renamed from: -$$Nest$mstopDreamlinerService, reason: not valid java name */
    public static void m1766$$Nest$mstopDreamlinerService(DockObserver dockObserver, Context context) {
        dockObserver.getClass();
        notifyForceEnabledAmbientDisplay(false);
        dockObserver.onDockStateChanged(0);
        dockObserver.removeInterruptionSuppressor();
        try {
            if (dockObserver.mDreamlinerServiceConn != null) {
                if (assertNotNull(dockObserver.mDockGestureController)) {
                    ((ConfigurationControllerImpl) dockObserver.mConfigurationController).removeCallback(dockObserver.mDockGestureController);
                    dockObserver.mDockGestureController.stopMonitoring();
                    dockObserver.mDockGestureController = null;
                }
                ((UserTrackerImpl) dockObserver.mUserTracker).removeCallback(dockObserver.mUserChangedCallback);
                DreamlinerBroadcastReceiver dreamlinerBroadcastReceiver = dockObserver.mDreamlinerReceiver;
                if (dreamlinerBroadcastReceiver.mListening) {
                    context.unregisterReceiver(dreamlinerBroadcastReceiver);
                    dreamlinerBroadcastReceiver.mListening = false;
                }
                context.unbindService(dockObserver.mDreamlinerServiceConn);
                dockObserver.mDreamlinerServiceConn = null;
            }
        } catch (IllegalArgumentException e) {
            Log.e("DLObserver", e.getMessage(), e);
        }
    }

    public DockObserver(Context context, Optional optional, final WirelessChargerCommander wirelessChargerCommander, StatusBarStateController statusBarStateController, Lazy lazy, ConfigurationController configurationController, DelayableExecutor delayableExecutor, KeyguardStateController keyguardStateController, DockAlignmentController dockAlignmentController, UserTracker userTracker) {
        DockObserverBroadcastReceiver dockObserverBroadcastReceiver = new DockObserverBroadcastReceiver();
        this.mDockObserverBroadcastReceiver = dockObserverBroadcastReceiver;
        this.mDockState = 0;
        this.mLastAlignState = -1;
        this.mUserChangedCallback = new UserTracker.Callback() { // from class: com.google.android.systemui.dreamliner.DockObserver.1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                DockObserver dockObserver = DockObserver.this;
                DockObserver.m1766$$Nest$mstopDreamlinerService(dockObserver, dockObserver.mContext);
                dockObserver.updateCurrentDockingStatus(dockObserver.mContext);
            }
        };
        this.mInterruptSuppressor = new AnonymousClass2();
        this.mVisualInterruptionCondition = new AnonymousClass3("docking UI showing", Set.of(VisualInterruptionType.PEEK, VisualInterruptionType.PULSE, VisualInterruptionType.BUBBLE));
        this.mVisualInterruptionDecisionProviderLazy = lazy;
        this.mMainExecutor = delayableExecutor;
        this.mContext = context;
        this.mKeyguardStateController = keyguardStateController;
        this.mClients = new ArrayList();
        this.mAlignmentStateListeners = new ArrayList();
        this.mUserTracker = userTracker;
        this.mProtectedBroadcastSender = new ProtectedBroadcastSender(context);
        this.mWirelessCharger = optional;
        if (optional.isEmpty()) {
            Log.i("DLObserver", "wireless charger is not present, check dock component.");
        }
        this.mWirelessChargerCommander = wirelessChargerCommander;
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        if (userManager != null && userManager.isSystemUser()) {
            wirelessChargerCommander.wirelessChargerFanLevelChangedCallback.add(new DockObserver$$ExternalSyntheticLambda2(this));
            WirelessChargerCommander.asyncIfPresent(wirelessChargerCommander.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$registerFanLevelChangedCallback$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    WirelessCharger wirelessCharger = (WirelessCharger) obj;
                    if (WirelessChargerCommander.this.isFanLevelCallbackRegistered.compareAndSet(false, true)) {
                        WirelessChargerImpl wirelessChargerImpl = (WirelessChargerImpl) wirelessCharger;
                        wirelessChargerImpl.mFanLevelEventListeners.add(WirelessChargerCommander.this.fanLevelEventListener);
                        if (wirelessChargerImpl.mIsFanLevelCallbackRegistered.compareAndSet(false, true) && wirelessChargerImpl.initHALInterface()) {
                            try {
                                ((IWirelessCharger.Stub.Proxy) wirelessChargerImpl.mWirelessCharger).registerFanLevelChangedCallback(wirelessChargerImpl.mIWirelessChargerFanLevelChangedCallback);
                            } catch (Exception e) {
                                Log.i("Dreamliner-WLC_HAL", "register fan level changed callback fail: " + e.getMessage());
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
            });
            context.registerReceiverAsUser(wirelessChargerCommander.commandReceiver, UserHandle.ALL, wirelessChargerCommander.commandIntents, PERMISSION_WIRELESS_CHARGER_STATUS, null, 2);
            if (Build.IS_ENG || Build.IS_USERDEBUG) {
                Log.d("DLObserver", "setUpTestingReceiverIfNotProduction");
                if (this.mStartTestingReceiver == null) {
                    this.mStartTestingReceiver = new StartTestingReceiver();
                }
                context.registerReceiver(this.mStartTestingReceiver, new IntentFilter(ACTION_START_TESTING), PERMISSION_WIRELESS_CHARGER_STATUS, null, 2);
            }
        }
        this.mStatusBarStateController = statusBarStateController;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
        intentFilter.addAction(ACTION_REBIND_DOCK_SERVICE);
        intentFilter.setPriority(1000);
        context.registerReceiver(dockObserverBroadcastReceiver, intentFilter, PERMISSION_WIRELESS_CHARGER_STATUS, null, 2);
        this.mDockAlignmentController = dockAlignmentController;
        this.mConfigurationController = configurationController;
    }

    public static boolean assertNotNull(Object obj) {
        if (obj != null) {
            return true;
        }
        Log.w("DLObserver", "DockGestureController is null");
        return false;
    }

    public static boolean isWirelessCharging(Context context, Intent intent) {
        if (intent == null) {
            intent = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        }
        if (intent != null && "android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
            int intExtra = intent.getIntExtra("plugged", -1);
            ExifInterface$$ExternalSyntheticOutline0.m("plugged=", "DLObserver", intExtra);
            return intExtra == 4;
        }
        Log.i("DLObserver", "invalid battery intent when checking plugged status. batteryIntent=" + intent);
        return false;
    }

    public static void notifyForceEnabledAmbientDisplay(boolean z) {
        IDreamManager asInterface = IDreamManager.Stub.asInterface(ServiceManager.checkService("dreams"));
        if (asInterface == null) {
            Log.e("DLObserver", "DreamManager not found");
        } else {
            try {
                asInterface.forceAmbientDisplayEnabled(z);
            } catch (RemoteException unused) {
            }
        }
    }

    public void addInterruptionSuppressor() {
        ((VisualInterruptionDecisionProviderImpl) ((VisualInterruptionDecisionProvider) this.mVisualInterruptionDecisionProviderLazy.get())).addCondition(this.mVisualInterruptionCondition);
    }

    public final void addListener(DockManager.DockEventListener dockEventListener) {
        Log.d("DLObserver", "add listener: " + dockEventListener);
        if (!this.mClients.contains(dockEventListener)) {
            this.mClients.add(dockEventListener);
        }
        ((ExecutorImpl) this.mMainExecutor).execute(new DockObserver$$ExternalSyntheticLambda1(this, dockEventListener, 0));
    }

    public BroadcastReceiver getDockObserverBroadcastReceiver() {
        return this.mDockObserverBroadcastReceiver;
    }

    public final boolean isDocked() {
        int i = this.mDockState;
        return i == 1 || i == 2;
    }

    public final boolean isHidden() {
        return this.mDockState == 2;
    }

    public final void notifyDreamlinerAlignStateChanged(int i) {
        Log.d("DLObserver", "sendBroadcastAsUser for alignment changed, alignState: " + i);
        if (isDocked()) {
            this.mProtectedBroadcastSender.sendBroadcastAsUser(new Intent(ACTION_ALIGN_STATE_CHANGE).putExtra(EXTRA_ALIGN_STATE, i).addFlags(1073741824), UserHandle.ALL);
        }
    }

    public final void onDockStateChanged(int i) {
        if (this.mDockState == i) {
            return;
        }
        Log.d("DLObserver", "dock state changed from " + this.mDockState + " to " + i);
        int i2 = this.mDockState;
        this.mDockState = i;
        for (int i3 = 0; i3 < ((ArrayList) this.mClients).size(); i3++) {
            DockManager.DockEventListener dockEventListener = (DockManager.DockEventListener) ((ArrayList) this.mClients).get(i3);
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("onDockEvent mDockState = "), this.mDockState, "DLObserver");
            dockEventListener.onEvent(this.mDockState);
        }
        DockIndicationController dockIndicationController = this.mIndicationController;
        if (dockIndicationController != null) {
            boolean isDocked = isDocked();
            dockIndicationController.mDocking = isDocked;
            if (!isDocked) {
                dockIndicationController.mTopIconShowing = false;
                dockIndicationController.mShowPromo = false;
            }
            dockIndicationController.updateVisibility$8();
            dockIndicationController.updateLiveRegionIfNeeded();
        }
        if (i2 == 0 && i == 1) {
            notifyDreamlinerAlignStateChanged(this.mLastAlignState);
        }
    }

    public void removeInterruptionSuppressor() {
        ((VisualInterruptionDecisionProviderImpl) ((VisualInterruptionDecisionProvider) this.mVisualInterruptionDecisionProviderLazy.get())).removeLegacySuppressor(this.mInterruptSuppressor);
    }

    public final void removeListener(DockManager.DockEventListener dockEventListener) {
        Log.d("DLObserver", "remove listener: " + dockEventListener);
        this.mClients.remove(dockEventListener);
    }

    public final void runPhotoAction() {
        int i = this.mLastAlignState;
        if (i == 1 || i == 2 || this.mPhotoAction == null || this.mIndicationController.mDockPromo.getVisibility() == 0) {
            return;
        }
        this.mMainExecutor.executeDelayed(this.mPhotoAction, Duration.ofSeconds(3L).toMillis());
    }

    public final void tryToStartDreamlinerServiceIfNeeded(Context context, boolean z) {
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        if (userManager.isManagedProfile() && !userManager.isSystemUser()) {
            Log.i("DLObserver", "do not bind Dreamliner service for work profile");
        } else if (z) {
            final IsDockPresentCallback isDockPresentCallback = new IsDockPresentCallback(context);
            WirelessChargerCommander.asyncIfPresent(this.mWirelessChargerCommander.wirelessCharger, new Function1() { // from class: com.google.android.systemui.dreamliner.WirelessChargerCommander$getDockPresent$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ((WirelessCharger) obj).asyncIsDockPresent(new WirelessChargerCommander$setFan$1.AnonymousClass1(WirelessCharger.IsDockPresentCallback.this));
                    return Unit.INSTANCE;
                }
            });
        }
    }

    public final void updateCurrentDockingStatus(Context context) {
        notifyForceEnabledAmbientDisplay(false);
        tryToStartDreamlinerServiceIfNeeded(context, isWirelessCharging(context, null));
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class DreamlinerServiceConn implements ServiceConnection {
        public final Context mContext;

        public DreamlinerServiceConn(Context context) {
            this.mContext = context;
        }

        @Override // android.content.ServiceConnection
        public final void onBindingDied(ComponentName componentName) {
            DockObserver.m1766$$Nest$mstopDreamlinerService(DockObserver.this, this.mContext);
            DockObserver.sIsDockingUiShowing = false;
            boolean isWirelessCharging = DockObserver.isWirelessCharging(this.mContext, null);
            MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("onBindingDied, isWirelessCharging:", "DLObserver", isWirelessCharging);
            if (isWirelessCharging) {
                DockObserver.this.updateCurrentDockingStatus(this.mContext);
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            DockObserver dockObserver = DockObserver.this;
            dockObserver.getClass();
            Log.d("DLObserver", "sendDockActiveIntent()");
            dockObserver.mProtectedBroadcastSender.sendBroadcast(new Intent("android.intent.action.DOCK_ACTIVE").addFlags(1073741824));
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }
    }
}
