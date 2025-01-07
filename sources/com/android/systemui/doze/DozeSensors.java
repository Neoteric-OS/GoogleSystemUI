package com.android.systemui.doze;

import android.content.res.Resources;
import android.database.ContentObserver;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.hardware.display.AmbientDisplayConfiguration;
import android.os.Handler;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.logging.UiEventLoggerImpl;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.doze.DozeSensors;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.SensorManagerPlugin;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DevicePostureControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.sensors.AsyncSensorManager;
import com.android.systemui.util.sensors.ProximitySensor;
import com.android.systemui.util.sensors.ProximitySensorImpl;
import com.android.systemui.util.sensors.ThresholdSensor;
import com.android.systemui.util.sensors.ThresholdSensorEvent;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeSensors {
    public static final UiEventLogger UI_EVENT_LOGGER = new UiEventLoggerImpl();
    public final AuthController mAuthController;
    public final AnonymousClass2 mAuthControllerCallback;
    public final AmbientDisplayConfiguration mConfig;
    public long mDebounceFrom;
    public int mDevicePosture;
    public final DozeSensors$$ExternalSyntheticLambda0 mDevicePostureCallback;
    public final DevicePostureController mDevicePostureController;
    public final DozeLog mDozeLog;
    public final Handler mHandler;
    public boolean mListening;
    public boolean mListeningAodOnlySensors;
    public boolean mListeningProxSensors;
    public boolean mListeningTouchScreenSensors;
    public final DozeTriggers$$ExternalSyntheticLambda3 mProxCallback;
    public final ProximitySensor mProximitySensor;
    public final boolean mScreenOffUdfpsEnabled;
    public final SecureSettings mSecureSettings;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public final boolean mSelectivelyRegisterProxSensors;
    public final DozeTriggers$$ExternalSyntheticLambda2 mSensorCallback;
    public final AsyncSensorManager mSensorManager;
    public boolean mSettingRegistered;
    public final AnonymousClass1 mSettingsObserver;
    protected TriggerSensor[] mTriggerSensors;
    public boolean mUdfpsEnrolled;
    public final WakeLock mWakeLock;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DozeSensorsUiEvent implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ DozeSensorsUiEvent[] $VALUES;
        public static final DozeSensorsUiEvent ACTION_AMBIENT_GESTURE_PICKUP;
        private final int mId = 459;

        static {
            DozeSensorsUiEvent dozeSensorsUiEvent = new DozeSensorsUiEvent();
            ACTION_AMBIENT_GESTURE_PICKUP = dozeSensorsUiEvent;
            $VALUES = new DozeSensorsUiEvent[]{dozeSensorsUiEvent};
        }

        public static DozeSensorsUiEvent valueOf(String str) {
            return (DozeSensorsUiEvent) Enum.valueOf(DozeSensorsUiEvent.class, str);
        }

        public static DozeSensorsUiEvent[] values() {
            return (DozeSensorsUiEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class PluginSensor extends TriggerSensor implements SensorManagerPlugin.SensorEventListener {
        public static final /* synthetic */ int $r8$clinit = 0;
        public final long mDebounce;
        public final SensorManagerPlugin.Sensor mPluginSensor;

        public PluginSensor(SensorManagerPlugin.Sensor sensor, String str, boolean z, int i, long j) {
            super(DozeSensors.this, null, str, z, i, false, false);
            this.mPluginSensor = sensor;
            this.mDebounce = j;
        }

        @Override // com.android.systemui.plugins.SensorManagerPlugin.SensorEventListener
        public final void onSensorChanged(final SensorManagerPlugin.SensorEvent sensorEvent) {
            DozeLog dozeLog = DozeSensors.this.mDozeLog;
            int i = this.mPulseReason;
            DozeLogger dozeLogger = dozeLog.mLogger;
            dozeLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            DozeLogger$logSensorTriggered$2 dozeLogger$logSensorTriggered$2 = DozeLogger$logSensorTriggered$2.INSTANCE;
            LogBuffer logBuffer = dozeLogger.buffer;
            LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logSensorTriggered$2, null);
            ((LogMessageImpl) obtain).int1 = i;
            logBuffer.commit(obtain);
            DozeSensors dozeSensors = DozeSensors.this;
            dozeSensors.mHandler.post(dozeSensors.mWakeLock.wrap(new Runnable() { // from class: com.android.systemui.doze.DozeSensors$PluginSensor$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DozeSensors.PluginSensor pluginSensor = DozeSensors.PluginSensor.this;
                    SensorManagerPlugin.SensorEvent sensorEvent2 = sensorEvent;
                    int i2 = DozeSensors.PluginSensor.$r8$clinit;
                    long uptimeMillis = SystemClock.uptimeMillis();
                    DozeSensors dozeSensors2 = DozeSensors.this;
                    if (uptimeMillis < dozeSensors2.mDebounceFrom + pluginSensor.mDebounce) {
                        dozeSensors2.mDozeLog.traceSensorEventDropped(pluginSensor.mPulseReason, "debounce");
                    } else {
                        dozeSensors2.mSensorCallback.f$0.onSensor(pluginSensor.mPulseReason, -1.0f, -1.0f, sensorEvent2.getValues());
                    }
                }
            }));
        }

        @Override // com.android.systemui.doze.DozeSensors.TriggerSensor
        public final String toString() {
            return "{mRegistered=" + this.mRegistered + ", mRequested=" + this.mRequested + ", mDisabled=false, mConfigured=" + this.mConfigured + ", mIgnoresSetting=" + this.mIgnoresSetting + ", mSensor=" + this.mPluginSensor + "}";
        }

        @Override // com.android.systemui.doze.DozeSensors.TriggerSensor
        public final void updateListening() {
            if (this.mConfigured) {
                final AsyncSensorManager asyncSensorManager = DozeSensors.this.mSensorManager;
                if (!this.mRequested || (!(enabledBySetting() || this.mIgnoresSetting) || this.mRegistered)) {
                    if (this.mRegistered) {
                        final SensorManagerPlugin.Sensor sensor = this.mPluginSensor;
                        final int i = 0;
                        asyncSensorManager.mExecutor.execute(new Runnable() { // from class: com.android.systemui.util.sensors.AsyncSensorManager$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i) {
                                    case 0:
                                        AsyncSensorManager asyncSensorManager2 = asyncSensorManager;
                                        SensorManagerPlugin.Sensor sensor2 = sensor;
                                        SensorManagerPlugin.SensorEventListener sensorEventListener = this;
                                        for (int i2 = 0; i2 < ((ArrayList) asyncSensorManager2.mPlugins).size(); i2++) {
                                            ((SensorManagerPlugin) ((ArrayList) asyncSensorManager2.mPlugins).get(i2)).unregisterListener(sensor2, sensorEventListener);
                                        }
                                        break;
                                    default:
                                        AsyncSensorManager asyncSensorManager3 = asyncSensorManager;
                                        SensorManagerPlugin.Sensor sensor3 = sensor;
                                        SensorManagerPlugin.SensorEventListener sensorEventListener2 = this;
                                        for (int i3 = 0; i3 < ((ArrayList) asyncSensorManager3.mPlugins).size(); i3++) {
                                            ((SensorManagerPlugin) ((ArrayList) asyncSensorManager3.mPlugins).get(i3)).registerListener(sensor3, sensorEventListener2);
                                        }
                                        break;
                                }
                            }
                        });
                        this.mRegistered = false;
                        DozeLogger dozeLogger = DozeSensors.this.mDozeLog.mLogger;
                        dozeLogger.getClass();
                        dozeLogger.buffer.log("DozeLog", LogLevel.DEBUG, "unregister plugin sensor", null);
                        return;
                    }
                    return;
                }
                final SensorManagerPlugin.Sensor sensor2 = this.mPluginSensor;
                if (asyncSensorManager.mPlugins.isEmpty()) {
                    Log.w("AsyncSensorManager", "No plugins registered");
                } else {
                    final int i2 = 1;
                    asyncSensorManager.mExecutor.execute(new Runnable() { // from class: com.android.systemui.util.sensors.AsyncSensorManager$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i2) {
                                case 0:
                                    AsyncSensorManager asyncSensorManager2 = asyncSensorManager;
                                    SensorManagerPlugin.Sensor sensor22 = sensor2;
                                    SensorManagerPlugin.SensorEventListener sensorEventListener = this;
                                    for (int i22 = 0; i22 < ((ArrayList) asyncSensorManager2.mPlugins).size(); i22++) {
                                        ((SensorManagerPlugin) ((ArrayList) asyncSensorManager2.mPlugins).get(i22)).unregisterListener(sensor22, sensorEventListener);
                                    }
                                    break;
                                default:
                                    AsyncSensorManager asyncSensorManager3 = asyncSensorManager;
                                    SensorManagerPlugin.Sensor sensor3 = sensor2;
                                    SensorManagerPlugin.SensorEventListener sensorEventListener2 = this;
                                    for (int i3 = 0; i3 < ((ArrayList) asyncSensorManager3.mPlugins).size(); i3++) {
                                        ((SensorManagerPlugin) ((ArrayList) asyncSensorManager3.mPlugins).get(i3)).registerListener(sensor3, sensorEventListener2);
                                    }
                                    break;
                            }
                        }
                    });
                }
                this.mRegistered = true;
                DozeLogger dozeLogger2 = DozeSensors.this.mDozeLog.mLogger;
                dozeLogger2.getClass();
                dozeLogger2.buffer.log("DozeLog", LogLevel.DEBUG, "register plugin sensor", null);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class TriggerSensor extends TriggerEventListener {
        public static final /* synthetic */ int $r8$clinit = 0;
        public boolean mConfigured;
        public boolean mIgnoresSetting;
        public final boolean mImmediatelyReRegister;
        public int mPosture;
        public final int mPulseReason;
        public boolean mRegistered;
        public final boolean mReportsTouchCoordinates;
        public boolean mRequested;
        public final boolean mRequiresAod;
        public final boolean mRequiresProx;
        public final boolean mRequiresTouchscreen;
        public final Sensor[] mSensors;
        public final String mSetting;
        public final boolean mSettingDefault;

        public TriggerSensor(DozeSensors dozeSensors, Sensor sensor, String str, boolean z, int i, boolean z2, boolean z3) {
            this(dozeSensors, sensor, str, true, z, i, z2, z3, false, true, false);
        }

        public final boolean enabledBySetting() {
            DozeSensors dozeSensors = DozeSensors.this;
            if (!dozeSensors.mConfig.enabled(dozeSensors.mSelectedUserInteractor.getSelectedUserId())) {
                return false;
            }
            if (TextUtils.isEmpty(this.mSetting)) {
                return true;
            }
            DozeSensors dozeSensors2 = DozeSensors.this;
            return dozeSensors2.mSecureSettings.getIntForUser(this.mSetting, this.mSettingDefault ? 1 : 0, dozeSensors2.mSelectedUserInteractor.getSelectedUserId()) != 0;
        }

        @Override // android.hardware.TriggerEventListener
        public final void onTrigger(final TriggerEvent triggerEvent) {
            final Sensor sensor = this.mSensors[this.mPosture];
            DozeLog dozeLog = DozeSensors.this.mDozeLog;
            int i = this.mPulseReason;
            DozeLogger dozeLogger = dozeLog.mLogger;
            dozeLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            DozeLogger$logSensorTriggered$2 dozeLogger$logSensorTriggered$2 = DozeLogger$logSensorTriggered$2.INSTANCE;
            LogBuffer logBuffer = dozeLogger.buffer;
            LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logSensorTriggered$2, null);
            ((LogMessageImpl) obtain).int1 = i;
            logBuffer.commit(obtain);
            DozeSensors dozeSensors = DozeSensors.this;
            dozeSensors.mHandler.post(dozeSensors.mWakeLock.wrap(new Runnable() { // from class: com.android.systemui.doze.DozeSensors$TriggerSensor$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    float f;
                    float f2;
                    DozeSensors.TriggerSensor triggerSensor = DozeSensors.TriggerSensor.this;
                    Sensor sensor2 = sensor;
                    TriggerEvent triggerEvent2 = triggerEvent;
                    if (sensor2 != null) {
                        int i2 = DozeSensors.TriggerSensor.$r8$clinit;
                        if (sensor2.getType() == 25) {
                            DozeSensors.UI_EVENT_LOGGER.log(DozeSensors.DozeSensorsUiEvent.ACTION_AMBIENT_GESTURE_PICKUP);
                        }
                    }
                    triggerSensor.mRegistered = false;
                    if (triggerSensor.mReportsTouchCoordinates) {
                        float[] fArr = triggerEvent2.values;
                        if (fArr.length >= 2) {
                            f = fArr[0];
                            f2 = fArr[1];
                            DozeSensors.this.mSensorCallback.f$0.onSensor(triggerSensor.mPulseReason, f, f2, triggerEvent2.values);
                            if (triggerSensor.mRegistered && triggerSensor.mImmediatelyReRegister) {
                                triggerSensor.updateListening();
                                return;
                            }
                        }
                    }
                    f = -1.0f;
                    f2 = -1.0f;
                    DozeSensors.this.mSensorCallback.f$0.onSensor(triggerSensor.mPulseReason, f, f2, triggerEvent2.values);
                    if (triggerSensor.mRegistered) {
                    }
                }
            }));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{mRegistered=");
            sb.append(this.mRegistered);
            sb.append(", mRequested=");
            sb.append(this.mRequested);
            sb.append(", mDisabled=false, mConfigured=");
            sb.append(this.mConfigured);
            sb.append(", mIgnoresSetting=");
            sb.append(this.mIgnoresSetting);
            sb.append(", mSensors=");
            sb.append(Arrays.toString(this.mSensors));
            if (this.mSensors.length > 2) {
                sb.append(", mPosture=");
                sb.append(DevicePostureController.devicePostureToString(DozeSensors.this.mDevicePosture));
            }
            sb.append("}");
            return sb.toString();
        }

        public void updateListening() {
            Sensor sensor = this.mSensors[this.mPosture];
            if (!this.mConfigured || sensor == null) {
                return;
            }
            if (!this.mRequested || (!enabledBySetting() && !this.mIgnoresSetting)) {
                if (this.mRegistered) {
                    boolean cancelTriggerSensor = DozeSensors.this.mSensorManager.cancelTriggerSensor(this, sensor);
                    DozeLog dozeLog = DozeSensors.this.mDozeLog;
                    String sensor2 = sensor.toString();
                    DozeLogger dozeLogger = dozeLog.mLogger;
                    dozeLogger.getClass();
                    LogLevel logLevel = LogLevel.INFO;
                    DozeLogger$logSensorUnregisterAttempt$2 dozeLogger$logSensorUnregisterAttempt$2 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logSensorUnregisterAttempt$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            LogMessage logMessage = (LogMessage) obj;
                            return "Unregister sensor. Success=" + logMessage.getBool1() + " sensor=" + logMessage.getStr1();
                        }
                    };
                    LogBuffer logBuffer = dozeLogger.buffer;
                    LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logSensorUnregisterAttempt$2, null);
                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                    logMessageImpl.str1 = sensor2;
                    logMessageImpl.bool1 = cancelTriggerSensor;
                    logBuffer.commit(obtain);
                    this.mRegistered = false;
                    return;
                }
                return;
            }
            if (this.mRegistered) {
                DozeLog dozeLog2 = DozeSensors.this.mDozeLog;
                String sensor3 = sensor.toString();
                DozeLogger dozeLogger2 = dozeLog2.mLogger;
                dozeLogger2.getClass();
                LogLevel logLevel2 = LogLevel.DEBUG;
                DozeLogger$logSkipSensorRegistration$2 dozeLogger$logSkipSensorRegistration$2 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logSkipSensorRegistration$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Skipping sensor registration because its already registered. sensor=", ((LogMessage) obj).getStr1());
                    }
                };
                LogBuffer logBuffer2 = dozeLogger2.buffer;
                LogMessage obtain2 = logBuffer2.obtain("DozeLog", logLevel2, dozeLogger$logSkipSensorRegistration$2, null);
                ((LogMessageImpl) obtain2).str1 = sensor3;
                logBuffer2.commit(obtain2);
                return;
            }
            this.mRegistered = DozeSensors.this.mSensorManager.requestTriggerSensor(this, sensor);
            DozeLog dozeLog3 = DozeSensors.this.mDozeLog;
            String sensor4 = sensor.toString();
            boolean z = this.mRegistered;
            DozeLogger dozeLogger3 = dozeLog3.mLogger;
            dozeLogger3.getClass();
            LogLevel logLevel3 = LogLevel.INFO;
            DozeLogger$logSensorRegisterAttempt$2 dozeLogger$logSensorRegisterAttempt$2 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logSensorRegisterAttempt$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return "Register sensor. Success=" + logMessage.getBool1() + " sensor=" + logMessage.getStr1();
                }
            };
            LogBuffer logBuffer3 = dozeLogger3.buffer;
            LogMessage obtain3 = logBuffer3.obtain("DozeLog", logLevel3, dozeLogger$logSensorRegisterAttempt$2, null);
            LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain3;
            logMessageImpl2.str1 = sensor4;
            logMessageImpl2.bool1 = z;
            logBuffer3.commit(obtain3);
        }

        public TriggerSensor(DozeSensors dozeSensors, Sensor sensor, String str, boolean z, boolean z2, int i, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
            this(new Sensor[]{sensor}, str, z, z2, i, z3, z4, z5, z6, 0, z7);
        }

        public TriggerSensor(Sensor[] sensorArr, String str, boolean z, boolean z2, int i, boolean z3, boolean z4, boolean z5, boolean z6, int i2, boolean z7) {
            this.mSensors = sensorArr;
            this.mSetting = str;
            this.mSettingDefault = z;
            this.mConfigured = z2;
            this.mPulseReason = i;
            this.mReportsTouchCoordinates = z3;
            this.mRequiresTouchscreen = z4;
            this.mIgnoresSetting = false;
            this.mRequiresProx = z5;
            this.mRequiresAod = z7;
            this.mPosture = i2;
            this.mImmediatelyReRegister = z6;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.systemui.doze.DozeSensors$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.android.systemui.biometrics.AuthController$Callback, com.android.systemui.doze.DozeSensors$2] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.android.systemui.doze.DozeSensors$1] */
    public DozeSensors(Resources resources, AsyncSensorManager asyncSensorManager, DozeParameters dozeParameters, AmbientDisplayConfiguration ambientDisplayConfiguration, WakeLock wakeLock, DozeTriggers$$ExternalSyntheticLambda2 dozeTriggers$$ExternalSyntheticLambda2, DozeTriggers$$ExternalSyntheticLambda3 dozeTriggers$$ExternalSyntheticLambda3, DozeLog dozeLog, ProximitySensor proximitySensor, SecureSettings secureSettings, AuthController authController, DevicePostureController devicePostureController, SelectedUserInteractor selectedUserInteractor) {
        boolean z;
        Handler handler = new Handler();
        this.mHandler = handler;
        this.mSettingsObserver = new ContentObserver(handler) { // from class: com.android.systemui.doze.DozeSensors.1
            public final void onChange(boolean z2, Collection collection, int i, int i2) {
                if (i2 != DozeSensors.this.mSelectedUserInteractor.getSelectedUserId()) {
                    return;
                }
                for (TriggerSensor triggerSensor : DozeSensors.this.mTriggerSensors) {
                    triggerSensor.updateListening();
                }
            }
        };
        this.mDevicePostureCallback = new DevicePostureController.Callback() { // from class: com.android.systemui.doze.DozeSensors$$ExternalSyntheticLambda0
            @Override // com.android.systemui.statusbar.policy.DevicePostureController.Callback
            public final void onPostureChanged(int i) {
                DozeSensors dozeSensors = DozeSensors.this;
                if (dozeSensors.mDevicePosture == i) {
                    return;
                }
                dozeSensors.mDevicePosture = i;
                for (DozeSensors.TriggerSensor triggerSensor : dozeSensors.mTriggerSensors) {
                    int i2 = dozeSensors.mDevicePosture;
                    int i3 = triggerSensor.mPosture;
                    if (i3 != i2) {
                        Sensor[] sensorArr = triggerSensor.mSensors;
                        if (sensorArr.length >= 2 && i2 < sensorArr.length) {
                            Sensor sensor = sensorArr[i3];
                            Sensor sensor2 = sensorArr[i2];
                            if (Objects.equals(sensor, sensor2)) {
                                triggerSensor.mPosture = i2;
                            } else {
                                if (triggerSensor.mRegistered) {
                                    boolean cancelTriggerSensor = DozeSensors.this.mSensorManager.cancelTriggerSensor(triggerSensor, sensor);
                                    DozeLog dozeLog2 = DozeSensors.this.mDozeLog;
                                    String sensor3 = sensor.toString();
                                    DozeLogger dozeLogger = dozeLog2.mLogger;
                                    dozeLogger.getClass();
                                    LogLevel logLevel = LogLevel.INFO;
                                    DozeLogger$logSensorUnregisterAttempt$4 dozeLogger$logSensorUnregisterAttempt$4 = new Function1() { // from class: com.android.systemui.doze.DozeLogger$logSensorUnregisterAttempt$4
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            LogMessage logMessage = (LogMessage) obj;
                                            String str2 = logMessage.getStr2();
                                            boolean bool1 = logMessage.getBool1();
                                            String str1 = logMessage.getStr1();
                                            StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Unregister sensor. reason=", str2, ". Success=", bool1, " sensor=");
                                            m.append(str1);
                                            return m.toString();
                                        }
                                    };
                                    LogBuffer logBuffer = dozeLogger.buffer;
                                    LogMessage obtain = logBuffer.obtain("DozeLog", logLevel, dozeLogger$logSensorUnregisterAttempt$4, null);
                                    LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                                    logMessageImpl.str1 = sensor3;
                                    logMessageImpl.bool1 = cancelTriggerSensor;
                                    logMessageImpl.str2 = "posture changed";
                                    logBuffer.commit(obtain);
                                    triggerSensor.mRegistered = false;
                                }
                                triggerSensor.mPosture = i2;
                                triggerSensor.updateListening();
                                DozeLog dozeLog3 = DozeSensors.this.mDozeLog;
                                int i4 = triggerSensor.mPosture;
                                String str = "DozeSensors swap {" + sensor + "} => {" + sensor2 + "}, mRegistered=" + triggerSensor.mRegistered;
                                DozeLogger dozeLogger2 = dozeLog3.mLogger;
                                dozeLogger2.getClass();
                                LogLevel logLevel2 = LogLevel.INFO;
                                DozeLogger$logPostureChanged$2 dozeLogger$logPostureChanged$2 = DozeLogger$logPostureChanged$2.INSTANCE;
                                LogBuffer logBuffer2 = dozeLogger2.buffer;
                                LogMessage obtain2 = logBuffer2.obtain("DozeLog", logLevel2, dozeLogger$logPostureChanged$2, null);
                                LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
                                logMessageImpl2.int1 = i4;
                                logMessageImpl2.str1 = str;
                                logBuffer2.commit(obtain2);
                            }
                        }
                    }
                }
            }
        };
        ?? r3 = new AuthController.Callback() { // from class: com.android.systemui.doze.DozeSensors.2
            @Override // com.android.systemui.biometrics.AuthController.Callback
            public final void onAllAuthenticatorsRegistered(int i) {
                if (i == 2) {
                    updateUdfpsEnrolled();
                }
            }

            @Override // com.android.systemui.biometrics.AuthController.Callback
            public final void onEnrollmentsChanged(int i) {
                if (i == 2) {
                    updateUdfpsEnrolled();
                }
            }

            public final void updateUdfpsEnrolled() {
                DozeSensors dozeSensors = DozeSensors.this;
                AuthController authController2 = dozeSensors.mAuthController;
                SelectedUserInteractor selectedUserInteractor2 = dozeSensors.mSelectedUserInteractor;
                dozeSensors.mUdfpsEnrolled = authController2.isUdfpsEnrolled(selectedUserInteractor2.getSelectedUserId());
                for (TriggerSensor triggerSensor : dozeSensors.mTriggerSensors) {
                    int i = triggerSensor.mPulseReason;
                    if (11 == i) {
                        r8 = dozeSensors.mUdfpsEnrolled && dozeSensors.mConfig.quickPickupSensorEnabled(selectedUserInteractor2.getSelectedUserId());
                        if (triggerSensor.mConfigured != r8) {
                            triggerSensor.mConfigured = r8;
                            triggerSensor.updateListening();
                        }
                    } else if (10 == i) {
                        if (!dozeSensors.mUdfpsEnrolled || (!dozeSensors.mConfig.alwaysOnEnabled(selectedUserInteractor2.getSelectedUserId()) && !dozeSensors.mScreenOffUdfpsEnabled)) {
                            r8 = false;
                        }
                        if (triggerSensor.mConfigured != r8) {
                            triggerSensor.mConfigured = r8;
                            triggerSensor.updateListening();
                        }
                    }
                }
            }
        };
        this.mAuthControllerCallback = r3;
        this.mSensorManager = asyncSensorManager;
        this.mConfig = ambientDisplayConfiguration;
        this.mWakeLock = wakeLock;
        this.mProxCallback = dozeTriggers$$ExternalSyntheticLambda3;
        this.mSecureSettings = secureSettings;
        this.mSensorCallback = dozeTriggers$$ExternalSyntheticLambda2;
        this.mDozeLog = dozeLog;
        this.mProximitySensor = proximitySensor;
        ((ProximitySensorImpl) proximitySensor).setTag("DozeSensors");
        boolean z2 = SystemProperties.getBoolean("doze.prox.selectively_register", dozeParameters.mResources.getBoolean(R.bool.doze_selectively_register_prox));
        this.mSelectivelyRegisterProxSensors = z2;
        this.mListeningProxSensors = !z2;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mScreenOffUdfpsEnabled = ambientDisplayConfiguration.screenOffUdfpsEnabled(selectedUserInteractor.getSelectedUserId());
        this.mDevicePostureController = devicePostureController;
        this.mDevicePosture = ((DevicePostureControllerImpl) devicePostureController).getDevicePosture();
        this.mAuthController = authController;
        this.mUdfpsEnrolled = authController.isUdfpsEnrolled(selectedUserInteractor.getSelectedUserId());
        authController.addCallback(r3);
        TriggerSensor triggerSensor = new TriggerSensor(this, asyncSensorManager.getDefaultSensor(17), null, SystemProperties.getBoolean("doze.pulse.sigmotion", dozeParameters.mResources.getBoolean(R.bool.doze_pulse_on_significant_motion)), 2, false, false);
        TriggerSensor triggerSensor2 = new TriggerSensor(this, asyncSensorManager.getDefaultSensor(25), "doze_pulse_on_pick_up", resources.getBoolean(android.R.bool.config_dozePulsePickup), ambientDisplayConfiguration.dozePickupSensorAvailable(), 3, false, false, false, true, false);
        TriggerSensor triggerSensor3 = new TriggerSensor(this, findSensor(asyncSensorManager, ambientDisplayConfiguration.doubleTapSensorType(), null), "doze_pulse_on_double_tap", true, 4, dozeParameters.mResources.getBoolean(R.bool.doze_double_tap_reports_touch_coordinates), true);
        String[] tapSensorTypeMapping = ambientDisplayConfiguration.tapSensorTypeMapping();
        Sensor[] sensorArr = new Sensor[5];
        HashMap hashMap = new HashMap();
        for (int i = 0; i < tapSensorTypeMapping.length; i++) {
            String str = tapSensorTypeMapping[i];
            if (!hashMap.containsKey(str)) {
                hashMap.put(str, findSensor(this.mSensorManager, str, null));
            }
            sensorArr[i] = (Sensor) hashMap.get(str);
        }
        int i2 = this.mDevicePosture;
        int[] intArray = dozeParameters.mResources.getIntArray(R.array.doze_single_tap_uses_prox_posture_mapping);
        boolean z3 = dozeParameters.mResources.getBoolean(R.bool.doze_single_tap_uses_prox);
        if (i2 < intArray.length) {
            z = intArray[i2] != 0;
        } else {
            ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Unsupported doze posture ", "DozeParameters", i2);
            z = z3;
        }
        this.mTriggerSensors = new TriggerSensor[]{triggerSensor, triggerSensor2, triggerSensor3, new TriggerSensor(sensorArr, "doze_tap_gesture", true, true, 9, true, true, z, true, this.mDevicePosture, false), new TriggerSensor(this, findSensor(this.mSensorManager, ambientDisplayConfiguration.longPressSensorType(), null), "doze_pulse_on_long_press", false, true, 5, true, true, dozeParameters.mResources.getBoolean(R.bool.doze_long_press_uses_prox), true, false), new TriggerSensor(this, findSensor(this.mSensorManager, ambientDisplayConfiguration.udfpsLongPressSensorType(), null), "doze_pulse_on_auth", true, this.mUdfpsEnrolled && (this.mConfig.alwaysOnEnabled(this.mSelectedUserInteractor.getSelectedUserId()) || this.mScreenOffUdfpsEnabled), 10, true, true, dozeParameters.mResources.getBoolean(R.bool.doze_long_press_uses_prox), false, true), new PluginSensor(new SensorManagerPlugin.Sensor(2), "doze_wake_display_gesture", this.mConfig.wakeScreenGestureAvailable() && this.mConfig.alwaysOnEnabled(this.mSelectedUserInteractor.getSelectedUserId()), 7, 0L), new PluginSensor(new SensorManagerPlugin.Sensor(1), "doze_wake_screen_gesture", this.mConfig.wakeScreenGestureAvailable(), 8, this.mConfig.getWakeLockScreenDebounce()), new TriggerSensor(this, findSensor(this.mSensorManager, ambientDisplayConfiguration.quickPickupSensorType(), null), "doze_quick_pickup_gesture", true, this.mUdfpsEnrolled && this.mConfig.quickPickupSensorEnabled(this.mSelectedUserInteractor.getSelectedUserId()), 11, false, false, false, true, false)};
        setProxListening(false);
        this.mProximitySensor.register(new ThresholdSensor.Listener() { // from class: com.android.systemui.doze.DozeSensors$$ExternalSyntheticLambda1
            @Override // com.android.systemui.util.sensors.ThresholdSensor.Listener
            public final void onThresholdCrossed(ThresholdSensorEvent thresholdSensorEvent) {
                DozeSensors dozeSensors = DozeSensors.this;
                if (thresholdSensorEvent != null) {
                    dozeSensors.mProxCallback.accept(Boolean.valueOf(!thresholdSensorEvent.mBelow));
                } else {
                    dozeSensors.getClass();
                }
            }
        });
        ((DevicePostureControllerImpl) this.mDevicePostureController).addCallback(this.mDevicePostureCallback);
    }

    public static Sensor findSensor(SensorManager sensorManager, String str, String str2) {
        boolean isEmpty = TextUtils.isEmpty(str2);
        boolean isEmpty2 = TextUtils.isEmpty(str);
        if (isEmpty && isEmpty2) {
            return null;
        }
        for (Sensor sensor : sensorManager.getSensorList(-1)) {
            if (isEmpty || str2.equals(sensor.getName())) {
                if (isEmpty2 || str.equals(sensor.getStringType())) {
                    return sensor;
                }
            }
        }
        return null;
    }

    public final void ignoreTouchScreenSensorsSettingInterferingWithDocking(boolean z) {
        for (TriggerSensor triggerSensor : this.mTriggerSensors) {
            if (triggerSensor.mRequiresTouchscreen && triggerSensor.mIgnoresSetting != z) {
                triggerSensor.mIgnoresSetting = z;
                triggerSensor.updateListening();
            }
        }
    }

    public final void setProxListening(boolean z) {
        ProximitySensorImpl proximitySensorImpl = (ProximitySensorImpl) this.mProximitySensor;
        if (proximitySensorImpl.mRegistered && z) {
            proximitySensorImpl.alertListeners();
        } else if (z) {
            proximitySensorImpl.resume();
        } else {
            proximitySensorImpl.pause();
        }
    }

    public final void updateListening() {
        boolean z = false;
        for (TriggerSensor triggerSensor : this.mTriggerSensors) {
            boolean z2 = this.mListening && (!triggerSensor.mRequiresTouchscreen || this.mListeningTouchScreenSensors) && ((!triggerSensor.mRequiresProx || this.mListeningProxSensors) && (!triggerSensor.mRequiresAod || this.mListeningAodOnlySensors));
            if (triggerSensor.mRequested != z2) {
                triggerSensor.mRequested = z2;
                triggerSensor.updateListening();
            }
            if (z2) {
                z = true;
            }
        }
        if (!z) {
            this.mSecureSettings.unregisterContentObserverAsync(this.mSettingsObserver);
        } else if (!this.mSettingRegistered) {
            for (TriggerSensor triggerSensor2 : this.mTriggerSensors) {
                if (triggerSensor2.mConfigured && !TextUtils.isEmpty(triggerSensor2.mSetting)) {
                    DozeSensors dozeSensors = DozeSensors.this;
                    dozeSensors.mSecureSettings.registerContentObserverForUserAsync(triggerSensor2.mSetting, dozeSensors.mSettingsObserver);
                }
            }
        }
        this.mSettingRegistered = z;
    }
}
