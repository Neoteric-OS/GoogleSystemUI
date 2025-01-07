package com.google.android.systemui.elmyra.sensors;

import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.wm.shell.R;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class JNIGestureSensor implements GestureSensor {
    private static final String DISABLE_SETTING = "com.google.android.systemui.elmyra.disable_jni";
    private static final int SENSOR_RATE = 20000;
    private static final String TAG = "Elmyra/JNIGestureSensor";
    private static boolean sLibraryLoaded;
    private final Context mContext;
    private final AssistGestureController mController;
    private final GestureConfiguration mGestureConfiguration;
    private boolean mIsListening;
    private final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback;
    private long mNativeService;
    private int mSensorCount;
    private final String mSensorStringType;

    static {
        try {
            System.loadLibrary("elmyra");
            sLibraryLoaded = true;
        } catch (Throwable th) {
            Log.w(TAG, "Could not load JNI component: " + th);
            sLibraryLoaded = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x007a A[Catch: Exception -> 0x009c, TryCatch #0 {Exception -> 0x009c, blocks: (B:7:0x003b, B:8:0x0049, B:12:0x004f, B:14:0x007a, B:16:0x0084, B:18:0x00b2, B:19:0x009e, B:23:0x006c, B:26:0x0072, B:28:0x00b5), top: B:6:0x003b, inners: #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public JNIGestureSensor(android.content.Context r9, com.google.android.systemui.elmyra.sensors.config.GestureConfiguration r10, com.android.keyguard.KeyguardUpdateMonitor r11) {
        /*
            r8 = this;
            java.lang.String r0 = "Elmyra/JNIGestureSensor"
            java.lang.String r1 = "touch_2_sensitivity"
            r8.<init>()
            com.google.android.systemui.elmyra.sensors.JNIGestureSensor$1 r2 = new com.google.android.systemui.elmyra.sensors.JNIGestureSensor$1
            r2.<init>()
            r8.mKeyguardUpdateMonitorCallback = r2
            r8.mContext = r9
            com.google.android.systemui.elmyra.sensors.AssistGestureController r3 = new com.google.android.systemui.elmyra.sensors.AssistGestureController
            r4 = 0
            r3.<init>(r9, r8, r10, r4)
            r8.mController = r3
            android.content.res.Resources r3 = r9.getResources()
            r5 = 2131952615(0x7f1303e7, float:1.9541678E38)
            java.lang.String r3 = r3.getString(r5)
            r8.mSensorStringType = r3
            r8.mGestureConfiguration = r10
            com.google.android.systemui.elmyra.sensors.JNIGestureSensor$$ExternalSyntheticLambda0 r3 = new com.google.android.systemui.elmyra.sensors.JNIGestureSensor$$ExternalSyntheticLambda0
            r3.<init>()
            r10.mListener = r3
            r11.registerCallback(r2)
            byte[] r9 = getChassisAsset(r9)
            if (r9 == 0) goto Lc0
            int r10 = r9.length
            if (r10 == 0) goto Lc0
            r10 = 0
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis r11 = new com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis     // Catch: java.lang.Exception -> L9c
            r11.<init>()     // Catch: java.lang.Exception -> L9c
            com.google.protobuf.nano.MessageNano.mergeFrom(r11, r9)     // Catch: java.lang.Exception -> L9c
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Sensor[] r2 = r11.sensors     // Catch: java.lang.Exception -> L9c
            int r2 = r2.length     // Catch: java.lang.Exception -> L9c
            r8.mSensorCount = r2     // Catch: java.lang.Exception -> L9c
            r2 = r10
        L49:
            int r3 = r8.mSensorCount     // Catch: java.lang.Exception -> L9c
            if (r2 >= r3) goto Lb5
            java.lang.String r3 = "Elmyra/SensorCalibration"
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.SecurityException -> L68 java.io.FileNotFoundException -> L6a java.lang.Exception -> L9c
            java.lang.String r6 = "/persist/sensors/elmyra/calibration.%d"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.SecurityException -> L68 java.io.FileNotFoundException -> L6a java.lang.Exception -> L9c
            java.lang.Object[] r7 = new java.lang.Object[]{r7}     // Catch: java.lang.SecurityException -> L68 java.io.FileNotFoundException -> L6a java.lang.Exception -> L9c
            java.lang.String r6 = java.lang.String.format(r6, r7)     // Catch: java.lang.SecurityException -> L68 java.io.FileNotFoundException -> L6a java.lang.Exception -> L9c
            r5.<init>(r6)     // Catch: java.lang.SecurityException -> L68 java.io.FileNotFoundException -> L6a java.lang.Exception -> L9c
            com.google.android.systemui.elmyra.sensors.config.SensorCalibration r6 = new com.google.android.systemui.elmyra.sensors.config.SensorCalibration     // Catch: java.lang.SecurityException -> L68 java.io.FileNotFoundException -> L6a java.lang.Exception -> L9c
            r6.<init>(r5)     // Catch: java.lang.SecurityException -> L68 java.io.FileNotFoundException -> L6a java.lang.Exception -> L9c
            goto L78
        L68:
            r5 = move-exception
            goto L6c
        L6a:
            r5 = move-exception
            goto L72
        L6c:
            java.lang.String r6 = "Could not open calibration file"
            android.util.Log.e(r3, r6, r5)     // Catch: java.lang.Exception -> L9c
            goto L77
        L72:
            java.lang.String r6 = "Could not find calibration file"
            android.util.Log.e(r3, r6, r5)     // Catch: java.lang.Exception -> L9c
        L77:
            r6 = r4
        L78:
            if (r6 == 0) goto L9e
            java.util.Map r3 = r6.mProperties     // Catch: java.lang.Exception -> L9c
            android.util.ArrayMap r3 = (android.util.ArrayMap) r3     // Catch: java.lang.Exception -> L9c
            boolean r3 = r3.containsKey(r1)     // Catch: java.lang.Exception -> L9c
            if (r3 == 0) goto L9e
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Sensor[] r3 = r11.sensors     // Catch: java.lang.Exception -> L9c
            r3 = r3[r2]     // Catch: java.lang.Exception -> L9c
            java.util.Map r5 = r6.mProperties     // Catch: java.lang.Exception -> L9c
            android.util.ArrayMap r5 = (android.util.ArrayMap) r5     // Catch: java.lang.Exception -> L9c
            java.lang.Object r5 = r5.get(r1)     // Catch: java.lang.Exception -> L9c
            java.lang.Float r5 = (java.lang.Float) r5     // Catch: java.lang.Exception -> L9c
            float r5 = r5.floatValue()     // Catch: java.lang.Exception -> L9c
            r6 = 1065353216(0x3f800000, float:1.0)
            float r6 = r6 / r5
            r3.sensitivity = r6     // Catch: java.lang.Exception -> L9c
            goto Lb2
        L9c:
            r9 = move-exception
            goto Lb9
        L9e:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L9c
            r3.<init>()     // Catch: java.lang.Exception -> L9c
            java.lang.String r5 = "Error reading calibration for sensor "
            r3.append(r5)     // Catch: java.lang.Exception -> L9c
            r3.append(r2)     // Catch: java.lang.Exception -> L9c
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L9c
            android.util.Log.w(r0, r3)     // Catch: java.lang.Exception -> L9c
        Lb2:
            int r2 = r2 + 1
            goto L49
        Lb5:
            r8.createNativeService(r9)     // Catch: java.lang.Exception -> L9c
            goto Lc0
        Lb9:
            java.lang.String r11 = "Error reading chassis file"
            android.util.Log.e(r0, r11, r9)
            r8.mSensorCount = r10
        Lc0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.elmyra.sensors.JNIGestureSensor.<init>(android.content.Context, com.google.android.systemui.elmyra.sensors.config.GestureConfiguration, com.android.keyguard.KeyguardUpdateMonitor):void");
    }

    private native boolean createNativeService(byte[] bArr);

    private native void destroyNativeService();

    private static byte[] getChassisAsset(Context context) {
        try {
            return readAllBytes(context.getResources().openRawResource(R.raw.elmyra_chassis));
        } catch (Resources.NotFoundException | IOException e) {
            Log.e(TAG, "Could not load chassis resource", e);
            return null;
        }
    }

    public static boolean isAvailable(Context context) {
        byte[] chassisAsset;
        return (Settings.Secure.getInt(context.getContentResolver(), DISABLE_SETTING, 0) == 1 || !sLibraryLoaded || (chassisAsset = getChassisAsset(context)) == null || chassisAsset.length == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(GestureConfiguration gestureConfiguration) {
        updateConfiguration();
    }

    private void onGestureDetected() {
        this.mController.onGestureDetected(null);
    }

    private void onGestureProgress(float f) {
        this.mController.onGestureProgress(f);
    }

    private static byte[] readAllBytes(InputStream inputStream) throws IOException {
        int i = 1024;
        byte[] bArr = new byte[1024];
        int i2 = 0;
        while (true) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read > 0) {
                i2 += read;
            } else {
                if (read < 0) {
                    break;
                }
                i <<= 1;
                bArr = Arrays.copyOf(bArr, i);
            }
        }
        return i == i2 ? bArr : Arrays.copyOf(bArr, i2);
    }

    private native boolean setGestureDetector(byte[] bArr);

    private native boolean startListeningNative(String str, int i);

    private native void stopListeningNative();

    public void finalize() throws Throwable {
        super.finalize();
        destroyNativeService();
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor
    public boolean isListening() {
        return this.mIsListening;
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor
    public void setGestureListener(GestureSensor.Listener listener) {
        this.mController.mGestureListener = listener;
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor
    public void startListening() {
        if (this.mIsListening || !startListeningNative(this.mSensorStringType, SENSOR_RATE)) {
            return;
        }
        updateConfiguration();
        this.mIsListening = true;
    }

    @Override // com.google.android.systemui.elmyra.sensors.GestureSensor
    public void stopListening() {
        if (this.mIsListening) {
            stopListeningNative();
            this.mIsListening = false;
        }
    }

    private void updateConfiguration() {
    }
}
