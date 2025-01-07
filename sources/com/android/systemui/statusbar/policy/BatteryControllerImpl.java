package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.settingslib.fuelgauge.Estimate;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.demomode.DemoMode;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.util.Assert;
import com.android.systemui.util.DumpUtilsKt;
import com.google.android.systemui.power.EnhancedEstimatesGoogleImpl;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BatteryControllerImpl extends BroadcastReceiver implements BatteryController, Dumpable {
    public boolean mAodPowerSave;
    public final Handler mBgHandler;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public boolean mCharged;
    public boolean mCharging;
    public final Context mContext;
    public final DemoModeController mDemoModeController;
    public final DumpManager mDumpManager;
    public Estimate mEstimate;
    public final EnhancedEstimates mEstimates;
    public int mLevel;
    public final BatteryControllerLogger mLogger;
    public final Handler mMainHandler;
    public int mPluggedChargingSource;
    public boolean mPluggedIn;
    public final PowerManager mPowerManager;
    public boolean mPowerSave;
    public boolean mWirelessCharging;
    public final ArrayList mChangeCallbacks = new ArrayList();
    public final ArrayList mFetchCallbacks = new ArrayList();
    public boolean mStateUnknown = false;
    public boolean mIsBatteryDefender = false;
    public boolean mIsIncompatibleCharging = false;
    public boolean mTestMode = false;
    boolean mHasReceivedBattery = false;
    public final Object mEstimateLock = new Object();
    public boolean mFetchingEstimate = false;
    public final AtomicReference mPowerSaverStartExpandable = new AtomicReference();

    public BatteryControllerImpl(Context context, EnhancedEstimates enhancedEstimates, PowerManager powerManager, BroadcastDispatcher broadcastDispatcher, DemoModeController demoModeController, DumpManager dumpManager, BatteryControllerLogger batteryControllerLogger, Handler handler, Handler handler2) {
        this.mContext = context;
        this.mMainHandler = handler;
        this.mBgHandler = handler2;
        this.mPowerManager = powerManager;
        this.mEstimates = enhancedEstimates;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mDemoModeController = demoModeController;
        this.mDumpManager = dumpManager;
        this.mLogger = batteryControllerLogger;
        batteryControllerLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        BatteryControllerLogger$logBatteryControllerInstance$2 batteryControllerLogger$logBatteryControllerInstance$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.BatteryControllerLogger$logBatteryControllerInstance$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("BatteryController CREATE (", Integer.toHexString(((LogMessage) obj).getInt1()), ")");
            }
        };
        LogBuffer logBuffer = batteryControllerLogger.logBuffer;
        LogMessage obtain = logBuffer.obtain("BatteryControllerLog", logLevel, batteryControllerLogger$logBatteryControllerInstance$2, null);
        ((LogMessageImpl) obtain).int1 = System.identityHashCode(this);
        logBuffer.commit(obtain);
    }

    @Override // com.android.systemui.demomode.DemoMode
    public final List demoCommands() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("battery");
        return arrayList;
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void dispatchDemoCommand(Bundle bundle, String str) {
        if (this.mDemoModeController.isInDemoMode) {
            String string = bundle.getString("level");
            String string2 = bundle.getString("plugged");
            String string3 = bundle.getString("powersave");
            String string4 = bundle.getString("present");
            String string5 = bundle.getString("defender");
            String string6 = bundle.getString("incompatible");
            if (string != null) {
                this.mLevel = Math.min(Math.max(Integer.parseInt(string), 0), 100);
            }
            if (string2 != null) {
                this.mPluggedIn = Boolean.parseBoolean(string2);
            }
            if (string3 != null) {
                this.mPowerSave = string3.equals("true");
                firePowerSaveChanged();
            }
            if (string4 != null) {
                this.mStateUnknown = !string4.equals("true");
                dispatchSafeChange(new BatteryControllerImpl$$ExternalSyntheticLambda1(this, 0));
            }
            if (string5 != null) {
                this.mIsBatteryDefender = string5.equals("true");
                dispatchSafeChange(new BatteryControllerImpl$$ExternalSyntheticLambda1(this, 5));
            }
            if (string6 != null) {
                this.mIsIncompatibleCharging = string6.equals("true");
                dispatchSafeChange(new BatteryControllerImpl$$ExternalSyntheticLambda1(this, 3));
            }
            fireBatteryLevelChanged();
        }
    }

    public final void dispatchSafeChange(Consumer consumer) {
        ArrayList arrayList;
        synchronized (this.mChangeCallbacks) {
            arrayList = new ArrayList(this.mChangeCallbacks);
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            consumer.accept((BatteryController.BatteryStateChangeCallback) arrayList.get(i));
        }
    }

    @Override // com.android.systemui.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        PrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.println("BatteryController state:");
        asIndenting.increaseIndent();
        asIndenting.print("mHasReceivedBattery=");
        asIndenting.println(this.mHasReceivedBattery);
        asIndenting.print("mLevel=");
        asIndenting.println(this.mLevel);
        asIndenting.print("mPluggedIn=");
        asIndenting.println(this.mPluggedIn);
        asIndenting.print("mCharging=");
        asIndenting.println(this.mCharging);
        asIndenting.print("mCharged=");
        asIndenting.println(this.mCharged);
        asIndenting.print("mIsBatteryDefender=");
        asIndenting.println(this.mIsBatteryDefender);
        asIndenting.print("mIsIncompatibleCharging=");
        asIndenting.println(this.mIsIncompatibleCharging);
        asIndenting.print("mPowerSave=");
        asIndenting.println(this.mPowerSave);
        asIndenting.print("mStateUnknown=");
        asIndenting.println(this.mStateUnknown);
        asIndenting.println("Callbacks:------------------");
        asIndenting.increaseIndent();
        synchronized (this.mChangeCallbacks) {
            try {
                int size = this.mChangeCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((BatteryController.BatteryStateChangeCallback) this.mChangeCallbacks.get(i)).dump(asIndenting, strArr);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        asIndenting.decreaseIndent();
        asIndenting.println("------------------");
    }

    public final void fireBatteryLevelChanged() {
        BatteryControllerLogger batteryControllerLogger = this.mLogger;
        int i = this.mLevel;
        boolean z = this.mPluggedIn;
        boolean z2 = this.mCharging;
        batteryControllerLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        BatteryControllerLogger$logBatteryLevelChangedCallback$2 batteryControllerLogger$logBatteryLevelChangedCallback$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.BatteryControllerLogger$logBatteryLevelChangedCallback$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "Sending onBatteryLevelChanged callbacks with level=" + logMessage.getInt1() + ", plugged=" + logMessage.getBool1() + ", charging=" + logMessage.getBool2();
            }
        };
        LogBuffer logBuffer = batteryControllerLogger.logBuffer;
        LogMessage obtain = logBuffer.obtain("BatteryControllerLog", logLevel, batteryControllerLogger$logBatteryLevelChangedCallback$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = i;
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logBuffer.commit(obtain);
        dispatchSafeChange(new BatteryControllerImpl$$ExternalSyntheticLambda1(this, 4));
    }

    public final void firePowerSaveChanged() {
        BatteryControllerLogger batteryControllerLogger = this.mLogger;
        boolean z = this.mPowerSave;
        batteryControllerLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        BatteryControllerLogger$logPowerSaveChangedCallback$2 batteryControllerLogger$logPowerSaveChangedCallback$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.BatteryControllerLogger$logPowerSaveChangedCallback$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("Sending onPowerSaveChanged callback with powerSave=", ((LogMessage) obj).getBool1());
            }
        };
        LogBuffer logBuffer = batteryControllerLogger.logBuffer;
        LogMessage obtain = logBuffer.obtain("BatteryControllerLog", logLevel, batteryControllerLogger$logPowerSaveChangedCallback$2, null);
        ((LogMessageImpl) obtain).bool1 = z;
        logBuffer.commit(obtain);
        dispatchSafeChange(new BatteryControllerImpl$$ExternalSyntheticLambda1(this, 1));
    }

    public void init$9() {
        Intent registerReceiver;
        BatteryControllerLogger batteryControllerLogger = this.mLogger;
        boolean z = this.mHasReceivedBattery;
        batteryControllerLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        BatteryControllerLogger$logBatteryControllerInit$2 batteryControllerLogger$logBatteryControllerInit$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.BatteryControllerLogger$logBatteryControllerInit$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return "BatteryController INIT (" + Integer.toHexString(logMessage.getInt1()) + ") hasReceivedBattery=" + logMessage.getBool1();
            }
        };
        LogBuffer logBuffer = batteryControllerLogger.logBuffer;
        LogMessage obtain = logBuffer.obtain("BatteryControllerLog", logLevel, batteryControllerLogger$logBatteryControllerInit$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.int1 = System.identityHashCode(this);
        logMessageImpl.bool1 = z;
        logBuffer.commit(obtain);
        registerReceiver$1();
        if (!this.mHasReceivedBattery && (registerReceiver = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"))) != null && !this.mHasReceivedBattery) {
            onReceive(this.mContext, registerReceiver);
        }
        this.mDemoModeController.addCallback((DemoMode) this);
        DumpManager dumpManager = this.mDumpManager;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "BatteryController", this);
        updatePowerSave();
        if (this.mFetchingEstimate) {
            return;
        }
        this.mFetchingEstimate = true;
        this.mBgHandler.post(new BatteryControllerImpl$$ExternalSyntheticLambda0(this, 0));
    }

    public boolean isBatteryDefender() {
        return this.mIsBatteryDefender;
    }

    public boolean isBatteryDefenderMode(int i) {
        return i == 4;
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeFinished() {
        registerReceiver$1();
        updatePowerSave();
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeStarted() {
        this.mBroadcastDispatcher.unregisterReceiver(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        BatteryControllerLogger batteryControllerLogger = this.mLogger;
        batteryControllerLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        BatteryControllerLogger$logIntentReceived$2 batteryControllerLogger$logIntentReceived$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.BatteryControllerLogger$logIntentReceived$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Received intent ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = batteryControllerLogger.logBuffer;
        LogMessage obtain = logBuffer.obtain("BatteryControllerLog", logLevel, batteryControllerLogger$logIntentReceived$2, null);
        ((LogMessageImpl) obtain).str1 = action;
        logBuffer.commit(obtain);
        if (!action.equals("android.intent.action.BATTERY_CHANGED")) {
            if (action.equals("android.os.action.POWER_SAVE_MODE_CHANGED")) {
                updatePowerSave();
                return;
            }
            if (action.equals("android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED")) {
                boolean containsIncompatibleChargers = Utils.containsIncompatibleChargers(this.mContext, "BatteryController");
                if (containsIncompatibleChargers != this.mIsIncompatibleCharging) {
                    this.mIsIncompatibleCharging = containsIncompatibleChargers;
                    dispatchSafeChange(new BatteryControllerImpl$$ExternalSyntheticLambda1(this, 3));
                    return;
                }
                return;
            }
            if (action.equals("com.android.systemui.BATTERY_LEVEL_TEST")) {
                BatteryControllerLogger batteryControllerLogger2 = this.mLogger;
                batteryControllerLogger2.getClass();
                BatteryControllerLogger$logEnterTestMode$2 batteryControllerLogger$logEnterTestMode$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.BatteryControllerLogger$logEnterTestMode$2
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return "Entering test mode for BATTERY_LEVEL_TEST intent";
                    }
                };
                LogBuffer logBuffer2 = batteryControllerLogger2.logBuffer;
                logBuffer2.commit(logBuffer2.obtain("BatteryControllerLog", logLevel, batteryControllerLogger$logEnterTestMode$2, null));
                this.mTestMode = true;
                this.mMainHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.policy.BatteryControllerImpl.1
                    public final int mSavedLevel;
                    public final boolean mSavedPluggedIn;
                    public int mCurrentLevel = 0;
                    public int mIncrement = 1;
                    public final Intent mTestIntent = new Intent("android.intent.action.BATTERY_CHANGED");

                    {
                        this.mSavedLevel = BatteryControllerImpl.this.mLevel;
                        this.mSavedPluggedIn = BatteryControllerImpl.this.mPluggedIn;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        int i = this.mCurrentLevel;
                        if (i < 0) {
                            BatteryControllerLogger batteryControllerLogger3 = BatteryControllerImpl.this.mLogger;
                            batteryControllerLogger3.getClass();
                            LogLevel logLevel2 = LogLevel.DEBUG;
                            BatteryControllerLogger$logExitTestMode$2 batteryControllerLogger$logExitTestMode$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.BatteryControllerLogger$logExitTestMode$2
                                @Override // kotlin.jvm.functions.Function1
                                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                    return "Exiting test mode";
                                }
                            };
                            LogBuffer logBuffer3 = batteryControllerLogger3.logBuffer;
                            logBuffer3.commit(logBuffer3.obtain("BatteryControllerLog", logLevel2, batteryControllerLogger$logExitTestMode$2, null));
                            BatteryControllerImpl.this.mTestMode = false;
                            this.mTestIntent.putExtra("level", this.mSavedLevel);
                            this.mTestIntent.putExtra("plugged", this.mSavedPluggedIn);
                            this.mTestIntent.putExtra("testmode", false);
                        } else {
                            this.mTestIntent.putExtra("level", i);
                            this.mTestIntent.putExtra("plugged", this.mIncrement > 0 ? 1 : 0);
                            this.mTestIntent.putExtra("testmode", true);
                        }
                        context.sendBroadcast(this.mTestIntent);
                        BatteryControllerImpl batteryControllerImpl = BatteryControllerImpl.this;
                        if (batteryControllerImpl.mTestMode) {
                            int i2 = this.mCurrentLevel;
                            int i3 = this.mIncrement;
                            int i4 = i2 + i3;
                            this.mCurrentLevel = i4;
                            if (i4 == 100) {
                                this.mIncrement = i3 * (-1);
                            }
                            batteryControllerImpl.mMainHandler.postDelayed(this, 200L);
                        }
                    }
                });
                return;
            }
            return;
        }
        final BatteryControllerLogger batteryControllerLogger3 = this.mLogger;
        batteryControllerLogger3.getClass();
        Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.policy.BatteryControllerLogger$logBatteryChangedIntent$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                BatteryControllerLogger batteryControllerLogger4 = BatteryControllerLogger.this;
                int int1 = logMessage.getInt1();
                batteryControllerLogger4.getClass();
                String valueOf = int1 == -11 ? "(missing)" : String.valueOf(int1);
                BatteryControllerLogger batteryControllerLogger5 = BatteryControllerLogger.this;
                int int2 = logMessage.getInt2();
                batteryControllerLogger5.getClass();
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Processing BATTERY_CHANGED intent. level=", valueOf, " scale=", int2 != -11 ? String.valueOf(int2) : "(missing)");
            }
        };
        LogBuffer logBuffer3 = batteryControllerLogger3.logBuffer;
        LogMessage obtain2 = logBuffer3.obtain("BatteryControllerLog", logLevel, function1, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain2;
        logMessageImpl.int1 = intent.getIntExtra("level", -11);
        logMessageImpl.int2 = intent.getIntExtra("scale", -11);
        logBuffer3.commit(obtain2);
        boolean z = false;
        if (this.mTestMode && !intent.getBooleanExtra("testmode", false)) {
            BatteryControllerLogger batteryControllerLogger4 = this.mLogger;
            batteryControllerLogger4.getClass();
            BatteryControllerLogger$logBatteryChangedSkipBecauseTest$2 batteryControllerLogger$logBatteryChangedSkipBecauseTest$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.BatteryControllerLogger$logBatteryChangedSkipBecauseTest$2
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return "Detected test intent. Will not execute battery level callbacks.";
                }
            };
            LogBuffer logBuffer4 = batteryControllerLogger4.logBuffer;
            logBuffer4.commit(logBuffer4.obtain("BatteryControllerLog", logLevel, batteryControllerLogger$logBatteryChangedSkipBecauseTest$2, null));
            return;
        }
        this.mHasReceivedBattery = true;
        this.mLevel = (int) ((intent.getIntExtra("level", 0) * 100.0f) / intent.getIntExtra("scale", 100));
        int i = this.mPluggedChargingSource;
        int intExtra = intent.getIntExtra("plugged", 0);
        this.mPluggedChargingSource = intExtra;
        this.mPluggedIn = intExtra != 0;
        int intExtra2 = intent.getIntExtra("status", 1);
        boolean z2 = intExtra2 == 5;
        this.mCharged = z2;
        boolean z3 = z2 || intExtra2 == 2;
        this.mCharging = z3;
        boolean z4 = this.mWirelessCharging;
        if (z3 && intent.getIntExtra("plugged", 0) == 4) {
            z = true;
        }
        if (z4 != z) {
            this.mWirelessCharging = !this.mWirelessCharging;
            synchronized (this.mChangeCallbacks) {
                this.mChangeCallbacks.forEach(new BatteryControllerImpl$$ExternalSyntheticLambda1(this, 2));
            }
        }
        boolean z5 = !intent.getBooleanExtra("present", true);
        if (z5 != this.mStateUnknown) {
            this.mStateUnknown = z5;
            dispatchSafeChange(new BatteryControllerImpl$$ExternalSyntheticLambda1(this, 0));
        }
        boolean isBatteryDefenderMode = isBatteryDefenderMode(intent.getIntExtra("android.os.extra.CHARGING_STATUS", 1));
        if (isBatteryDefenderMode != this.mIsBatteryDefender) {
            this.mIsBatteryDefender = isBatteryDefenderMode;
            dispatchSafeChange(new BatteryControllerImpl$$ExternalSyntheticLambda1(this, 5));
        }
        if (this.mPluggedChargingSource != i) {
            updatePowerSave();
        }
        fireBatteryLevelChanged();
    }

    public final void registerReceiver$1() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        intentFilter.addAction("com.android.systemui.BATTERY_LEVEL_TEST");
        intentFilter.addAction("android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED");
        this.mBroadcastDispatcher.registerReceiver(this, intentFilter);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        BatteryController.BatteryStateChangeCallback batteryStateChangeCallback = (BatteryController.BatteryStateChangeCallback) obj;
        synchronized (this.mChangeCallbacks) {
            this.mChangeCallbacks.remove(batteryStateChangeCallback);
        }
    }

    public final void updateEstimate() {
        Estimate estimate;
        Assert.isNotMainThread();
        Context context = this.mContext;
        ContentResolver contentResolver = context.getContentResolver();
        if (Duration.between(Instant.ofEpochMilli(Settings.Global.getLong(context.getContentResolver(), "battery_estimates_last_update_time", -1L)), Instant.now()).compareTo(Duration.ofMinutes(1L)) > 0) {
            estimate = null;
        } else {
            estimate = new Estimate(Settings.Global.getLong(contentResolver, "time_remaining_estimate_millis", -1L), Settings.Global.getLong(contentResolver, "average_time_to_discharge", -1L), Settings.Global.getInt(contentResolver, "time_remaining_estimate_based_on_usage", 0) == 1);
        }
        this.mEstimate = estimate;
        if (estimate == null) {
            Estimate estimate2 = ((EnhancedEstimatesGoogleImpl) this.mEstimates).getEstimate();
            this.mEstimate = estimate2;
            Estimate.storeCachedEstimate(this.mContext, estimate2);
        }
    }

    public final void updatePowerSave() {
        boolean isPowerSaveMode = this.mPowerManager.isPowerSaveMode();
        if (isPowerSaveMode == this.mPowerSave) {
            return;
        }
        this.mPowerSave = isPowerSaveMode;
        this.mAodPowerSave = this.mPowerManager.getPowerSaveState(14).batterySaverEnabled;
        firePowerSaveChanged();
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void addCallback(BatteryController.BatteryStateChangeCallback batteryStateChangeCallback) {
        synchronized (this.mChangeCallbacks) {
            this.mChangeCallbacks.add(batteryStateChangeCallback);
        }
        if (this.mHasReceivedBattery) {
            batteryStateChangeCallback.onBatteryLevelChanged(this.mLevel, this.mPluggedIn, this.mCharging);
            batteryStateChangeCallback.onPowerSaveChanged(this.mPowerSave);
            batteryStateChangeCallback.onBatteryUnknownStateChanged(this.mStateUnknown);
            batteryStateChangeCallback.onWirelessChargingChanged(this.mWirelessCharging);
            batteryStateChangeCallback.onIsBatteryDefenderChanged(this.mIsBatteryDefender);
            batteryStateChangeCallback.onIsIncompatibleChargingChanged(this.mIsIncompatibleCharging);
        }
    }
}
