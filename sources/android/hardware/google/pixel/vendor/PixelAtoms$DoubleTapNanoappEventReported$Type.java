package android.hardware.google.pixel.vendor;

import com.google.protobuf.Internal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public enum PixelAtoms$DoubleTapNanoappEventReported$Type implements Internal.EnumLite {
    UNKNOWN(0),
    GATE_START(1),
    GATE_STOP(2),
    HIGH_IMU_ODR_START(3),
    HIGH_IMU_ODR_STOP(4),
    ML_PREDICTION_START(5),
    ML_PREDICTION_STOP(6),
    SINGLE_TAP(7),
    DOUBLE_TAP(8);

    private final int value;

    PixelAtoms$DoubleTapNanoappEventReported$Type(int i) {
        this.value = i;
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }
}
