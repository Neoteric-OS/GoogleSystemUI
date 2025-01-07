package com.google.android.systemui.autorotate;

import android.app.StatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.provider.DeviceConfig;
import android.util.Log;
import android.util.Pair;
import com.android.internal.util.ConcurrentUtils;
import com.android.internal.util.FrameworkStatsLog;
import com.android.internal.util.LatencyTracker;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.google.android.systemui.autorotate.DataLogger.StatsPullAtomCallbackImpl;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AutorotateDataService {
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final Context mContext;
    public Sensor mDebugSensor;
    public final DeviceConfigProxy mDeviceConfig;
    public LatencyTracker mLatencyTracker;
    public final DelayableExecutor mMainExecutor;
    public Sensor mPreindicationSensor;
    public boolean mRawSensorLoggingEnabled;
    public boolean mRotationPreindicationEnabled;
    public final DataLogger mSensorDataLogger;
    public final SensorManager mSensorManager;
    public boolean mServiceRunning = false;
    public int mLastPreIndication = -1;
    public SensorData[] mSensorData = new SensorData[600];
    public int mSensorDataIndex = 0;
    public final AnonymousClass1 mScreenEventBroadcastReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.autorotate.AutorotateDataService.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                AutorotateDataService.this.registerRequiredSensors();
            } else if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                AutorotateDataService autorotateDataService = AutorotateDataService.this;
                autorotateDataService.mSensorManager.unregisterListener(autorotateDataService.mSensorListener);
                autorotateDataService.mSensorDataLogger.mDataQueue.clear();
                AutorotateDataService.this.mLastPreIndication = -1;
            }
        }
    };
    public final SensorListener mSensorListener = new SensorListener();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SensorDataReadyRunnable implements Runnable {
        public final int mRotation;
        public final long mRotationTimestampMillis;

        public SensorDataReadyRunnable(int i, long j) {
            this.mRotation = i;
            this.mRotationTimestampMillis = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AutorotateDataService autorotateDataService = AutorotateDataService.this;
            DataLogger dataLogger = autorotateDataService.mSensorDataLogger;
            SensorData[] sensorDataArr = autorotateDataService.mSensorData;
            int i = this.mRotation;
            dataLogger.getClass();
            int i2 = 0;
            if (sensorDataArr.length != 0 && sensorDataArr[0] != null) {
                if (SystemClock.elapsedRealtimeNanos() - dataLogger.mLastPullTimeNanos > 5000000000L) {
                    dataLogger.mDataQueue.clear();
                }
                dataLogger.mDataQueue.add(Pair.create(sensorDataArr, Integer.valueOf(i)));
            }
            DataLogger dataLogger2 = AutorotateDataService.this.mSensorDataLogger;
            long j = this.mRotationTimestampMillis;
            int i3 = this.mRotation;
            dataLogger2.getClass();
            if (i3 == 0) {
                i2 = 1;
            } else if (i3 == 1) {
                i2 = 2;
            } else if (i3 == 2) {
                i2 = 3;
            } else if (i3 == 3) {
                i2 = 4;
            }
            FrameworkStatsLog.write(333, j, i2, 3);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.systemui.autorotate.AutorotateDataService$1] */
    public AutorotateDataService(Context context, SensorManager sensorManager, DataLogger dataLogger, BroadcastDispatcher broadcastDispatcher, DeviceConfigProxy deviceConfigProxy, DelayableExecutor delayableExecutor) {
        this.mContext = context;
        this.mMainExecutor = delayableExecutor;
        this.mSensorDataLogger = dataLogger;
        this.mSensorManager = sensorManager;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mDeviceConfig = deviceConfigProxy;
    }

    public final void readFlagsToControlSensorLogging() {
        Sensor sensor;
        Sensor sensor2;
        this.mRawSensorLoggingEnabled = DeviceConfig.getBoolean("window_manager", "log_raw_sensor_data", false);
        boolean z = DeviceConfig.getBoolean("window_manager", "log_rotation_preindication", false);
        this.mRotationPreindicationEnabled = z;
        boolean z2 = this.mRawSensorLoggingEnabled;
        SensorListener sensorListener = this.mSensorListener;
        BroadcastDispatcher broadcastDispatcher = this.mBroadcastDispatcher;
        DataLogger dataLogger = this.mSensorDataLogger;
        if (!z2 && !z) {
            if (this.mServiceRunning) {
                StatsManager statsManager = dataLogger.mStatsManager;
                if (statsManager != null) {
                    statsManager.clearPullAtomCallback(10097);
                }
                broadcastDispatcher.unregisterReceiver(this.mScreenEventBroadcastReceiver);
                this.mSensorManager.unregisterListener(sensorListener);
                dataLogger.mDataQueue.clear();
                this.mServiceRunning = false;
            }
            this.mSensorManager.unregisterListener(sensorListener);
            dataLogger.mDataQueue.clear();
            return;
        }
        if (z2 || z) {
            if (!this.mServiceRunning) {
                StatsManager statsManager2 = dataLogger.mStatsManager;
                if (statsManager2 != null) {
                    statsManager2.setPullAtomCallback(10097, (StatsManager.PullAtomMetadata) null, ConcurrentUtils.DIRECT_EXECUTOR, dataLogger.new StatsPullAtomCallbackImpl());
                    Log.d("Autorotate-DataLogger", "Registered to statsd for pull");
                }
                IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                broadcastDispatcher.getClass();
                BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, this.mScreenEventBroadcastReceiver, intentFilter, null, null, 0, 56);
                this.mServiceRunning = true;
            }
            registerRequiredSensors();
        }
        if (!this.mRawSensorLoggingEnabled && (sensor2 = this.mDebugSensor) != null) {
            this.mSensorManager.unregisterListener(sensorListener, sensor2);
        }
        if (this.mRotationPreindicationEnabled || (sensor = this.mPreindicationSensor) == null) {
            return;
        }
        this.mSensorManager.unregisterListener(sensorListener, sensor);
    }

    public final void registerRequiredSensors() {
        Sensor defaultSensor = this.mSensorManager.getDefaultSensor(27);
        SensorManager sensorManager = this.mSensorManager;
        SensorListener sensorListener = this.mSensorListener;
        sensorManager.registerListener(sensorListener, defaultSensor, 1);
        if (this.mRawSensorLoggingEnabled) {
            Sensor defaultSensor2 = this.mSensorManager.getDefaultSensor(65548);
            this.mDebugSensor = defaultSensor2;
            this.mSensorManager.registerListener(sensorListener, defaultSensor2, 1);
        }
        if (this.mRotationPreindicationEnabled) {
            Sensor defaultSensor3 = this.mSensorManager.getDefaultSensor(65553);
            this.mPreindicationSensor = defaultSensor3;
            this.mSensorManager.registerListener(sensorListener, defaultSensor3, 1);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SensorListener implements SensorEventListener {
        public SensorListener() {
        }

        @Override // android.hardware.SensorEventListener
        public final void onSensorChanged(SensorEvent sensorEvent) {
            int i = 3;
            if (sensorEvent.sensor.getType() == 27) {
                int i2 = (int) sensorEvent.values[0];
                if (i2 < 0 || i2 > 3) {
                    return;
                }
                AutorotateDataService autorotateDataService = AutorotateDataService.this;
                autorotateDataService.mSensorData = new SensorData[600];
                autorotateDataService.mSensorDataIndex = 0;
                ((ExecutorImpl) autorotateDataService.mMainExecutor).executeDelayed(autorotateDataService.new SensorDataReadyRunnable(i2, sensorEvent.timestamp / 1000000), 2300L, TimeUnit.MILLISECONDS);
                AutorotateDataService autorotateDataService2 = AutorotateDataService.this;
                if (autorotateDataService2.mPreindicationSensor == null || i2 != autorotateDataService2.mLastPreIndication) {
                    return;
                }
                autorotateDataService2.mLatencyTracker.onActionEnd(10);
                return;
            }
            if (sensorEvent.sensor.getType() != 65548) {
                if (sensorEvent.sensor.getType() == 65553) {
                    AutorotateDataService autorotateDataService3 = AutorotateDataService.this;
                    if (autorotateDataService3.mPreindicationSensor != null) {
                        int i3 = (int) sensorEvent.values[0];
                        autorotateDataService3.mLatencyTracker.onActionStart(10);
                        AutorotateDataService autorotateDataService4 = AutorotateDataService.this;
                        autorotateDataService4.mLastPreIndication = i3;
                        long j = sensorEvent.timestamp / 1000000;
                        autorotateDataService4.mSensorDataLogger.getClass();
                        if (i3 == 0) {
                            i = 1;
                        } else if (i3 == 1) {
                            i = 2;
                        } else if (i3 != 2) {
                            i = i3 != 3 ? 0 : 4;
                        }
                        FrameworkStatsLog.write(333, j, i, 1);
                        return;
                    }
                    return;
                }
                return;
            }
            AutorotateDataService autorotateDataService5 = AutorotateDataService.this;
            int i4 = autorotateDataService5.mSensorDataIndex;
            if (i4 >= 600) {
                autorotateDataService5.mSensorDataIndex = 0;
                return;
            }
            SensorData[] sensorDataArr = autorotateDataService5.mSensorData;
            float[] fArr = sensorEvent.values;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[2];
            int i5 = (int) fArr[3];
            long j2 = sensorEvent.timestamp / 1000000;
            SensorData sensorData = new SensorData();
            sensorData.mValueX = f;
            sensorData.mValueY = f2;
            sensorData.mValueZ = f3;
            sensorData.mSensorIdentifier = i5;
            sensorData.mTimestampMillis = j2;
            sensorDataArr[i4] = sensorData;
            autorotateDataService5.mSensorDataIndex = i4 + 1;
        }

        @Override // android.hardware.SensorEventListener
        public final void onAccuracyChanged(Sensor sensor, int i) {
        }
    }
}
