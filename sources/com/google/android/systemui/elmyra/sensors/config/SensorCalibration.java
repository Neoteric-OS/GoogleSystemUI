package com.google.android.systemui.elmyra.sensors.config;

import android.util.ArrayMap;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SensorCalibration {
    public final Map mProperties = new ArrayMap();

    public SensorCalibration(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return;
                }
                int indexOf = readLine.indexOf(58);
                if (indexOf != -1) {
                    String substring = readLine.substring(0, indexOf);
                    String substring2 = readLine.substring(indexOf + 1);
                    try {
                        ((ArrayMap) this.mProperties).put(substring.trim(), Float.valueOf(Float.parseFloat(substring2)));
                    } catch (NumberFormatException unused) {
                    }
                }
            } catch (IOException e) {
                Log.e("Elmyra/SensorCalibration", "Error reading calibration file", e);
                return;
            }
        }
    }
}
