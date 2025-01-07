package android.hardware.google.pixel.vendor;

import com.google.protobuf.Internal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public enum PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType implements Internal.EnumLite {
    UNKNOWN(0),
    REAR(1),
    UDFPS_ULTRASONIC(2),
    UDFPS_OPTICAL(3),
    POWER_BUTTON(4),
    HOME_BUTTON(5);

    private final int value;

    PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType(int i) {
        this.value = i;
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }
}
