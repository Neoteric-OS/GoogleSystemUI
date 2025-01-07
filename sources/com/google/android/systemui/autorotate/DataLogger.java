package com.google.android.systemui.autorotate;

import android.app.StatsManager;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.android.internal.util.FrameworkStatsLog;
import com.google.android.systemui.autorotate.proto.nano.AutorotateProto$DeviceRotatedSensorData;
import com.google.android.systemui.autorotate.proto.nano.AutorotateProto$DeviceRotatedSensorHeader;
import com.google.android.systemui.autorotate.proto.nano.AutorotateProto$DeviceRotatedSensorSample;
import com.google.protobuf.nano.MessageNano;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DataLogger {
    public Queue mDataQueue;
    public long mLastPullTimeNanos;
    public StatsManager mStatsManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StatsPullAtomCallbackImpl implements StatsManager.StatsPullAtomCallback {
        public StatsPullAtomCallbackImpl() {
        }

        public final int onPullAtom(int i, List list) {
            int i2;
            if (i != 10097) {
                throw new UnsupportedOperationException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown tagId: "));
            }
            DataLogger dataLogger = DataLogger.this;
            dataLogger.getClass();
            Log.d("Autorotate-DataLogger", "Received pull request from statsd.");
            dataLogger.mLastPullTimeNanos = SystemClock.elapsedRealtimeNanos();
            Pair pair = (Pair) ((LinkedList) dataLogger.mDataQueue).poll();
            SensorData[] sensorDataArr = (SensorData[]) pair.first;
            int intValue = ((Integer) pair.second).intValue();
            if (sensorDataArr != null && sensorDataArr.length != 0 && sensorDataArr[0] != null) {
                AutorotateProto$DeviceRotatedSensorData autorotateProto$DeviceRotatedSensorData = new AutorotateProto$DeviceRotatedSensorData();
                AutorotateProto$DeviceRotatedSensorHeader autorotateProto$DeviceRotatedSensorHeader = new AutorotateProto$DeviceRotatedSensorHeader();
                autorotateProto$DeviceRotatedSensorHeader.timestampBase = sensorDataArr[0].mTimestampMillis;
                autorotateProto$DeviceRotatedSensorData.header = autorotateProto$DeviceRotatedSensorHeader;
                AutorotateProto$DeviceRotatedSensorSample[] autorotateProto$DeviceRotatedSensorSampleArr = new AutorotateProto$DeviceRotatedSensorSample[sensorDataArr.length];
                int i3 = 0;
                while (true) {
                    i2 = 2;
                    if (i3 >= sensorDataArr.length) {
                        break;
                    }
                    AutorotateProto$DeviceRotatedSensorSample autorotateProto$DeviceRotatedSensorSample = new AutorotateProto$DeviceRotatedSensorSample();
                    SensorData sensorData = sensorDataArr[i3];
                    autorotateProto$DeviceRotatedSensorSample.timestampOffset = (int) (sensorData.mTimestampMillis - autorotateProto$DeviceRotatedSensorHeader.timestampBase);
                    int i4 = sensorData.mSensorIdentifier;
                    if (i4 != 4) {
                        i2 = i4;
                    }
                    autorotateProto$DeviceRotatedSensorSample.sensorType = i2;
                    autorotateProto$DeviceRotatedSensorSample.xValue = sensorData.mValueX;
                    autorotateProto$DeviceRotatedSensorSample.yValue = sensorData.mValueY;
                    autorotateProto$DeviceRotatedSensorSample.zValue = sensorData.mValueZ;
                    autorotateProto$DeviceRotatedSensorSampleArr[i3] = autorotateProto$DeviceRotatedSensorSample;
                    i3++;
                }
                autorotateProto$DeviceRotatedSensorData.sample = autorotateProto$DeviceRotatedSensorSampleArr;
                byte[] byteArray = MessageNano.toByteArray(autorotateProto$DeviceRotatedSensorData);
                int i5 = 1;
                if (intValue != 0) {
                    if (intValue != 1) {
                        i5 = 3;
                        if (intValue != 2) {
                            i2 = intValue != 3 ? 0 : 4;
                        }
                    }
                    list.add(FrameworkStatsLog.buildStatsEvent(10097, byteArray, i2));
                }
                i2 = i5;
                list.add(FrameworkStatsLog.buildStatsEvent(10097, byteArray, i2));
            }
            return 0;
        }
    }
}
