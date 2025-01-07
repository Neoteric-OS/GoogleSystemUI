package com.android.systemui.util.sensors;

import android.content.res.Resources;
import android.hardware.Sensor;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.util.sensors.ThresholdSensorImpl;
import java.util.Arrays;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SensorModule {
    public static ThresholdSensor[] createPostureToSensorMapping(ThresholdSensorImpl.BuilderFactory builderFactory, String[] strArr, int i, int i2) {
        if (0.0f > 0.0f) {
            throw new IllegalStateException("Threshold must be less than or equal to Threshold Latch");
        }
        ThresholdSensor[] thresholdSensorArr = new ThresholdSensor[5];
        Arrays.fill(thresholdSensorArr, new ThresholdSensorImpl(builderFactory.mSensorManager, null, builderFactory.mExecution, 0.0f, 0.0f));
        if (strArr != null && strArr.length != 0) {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str)) {
                    HashMap hashMap = new HashMap();
                    for (int i3 = 0; i3 < strArr.length; i3++) {
                        try {
                            String str2 = strArr[i3];
                            if (hashMap.containsKey(str2)) {
                                thresholdSensorArr[i3] = (ThresholdSensor) hashMap.get(str2);
                            } else {
                                Resources resources = builderFactory.mResources;
                                ThresholdSensorImpl.Builder builder = new ThresholdSensorImpl.Builder(resources, builderFactory.mSensorManager, builderFactory.mExecution);
                                Sensor findSensorByType = builder.findSensorByType(strArr[i3], true);
                                if (findSensorByType != null) {
                                    builder.mSensor = findSensorByType;
                                    builder.mSensorSet = true;
                                }
                                try {
                                    builder.setThresholdValue(resources.getFloat(i));
                                } catch (Resources.NotFoundException unused) {
                                }
                                builder.setThresholdLatchResourceId(i2);
                                ThresholdSensorImpl build = builder.build();
                                thresholdSensorArr[i3] = build;
                                hashMap.put(str2, build);
                            }
                        } catch (IllegalStateException unused2) {
                        }
                    }
                    return thresholdSensorArr;
                }
            }
        }
        Log.e("SensorModule", "config doesn't support postures, but attempting to retrieve proxSensorMapping");
        return thresholdSensorArr;
    }
}
