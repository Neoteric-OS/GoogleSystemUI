package android.hardware.google.pixel.vendor;

import com.google.protobuf.Internal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public enum PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$UnlockAnimType implements Internal.EnumLite {
    NONE(0),
    WAKE_TO_LAUNCHER(1),
    WAKE_TO_APP(2),
    UNLOCK_TO_LAUNCHER(3),
    UNLOCK_TO_APP(4);

    private final int value;

    PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$UnlockAnimType(int i) {
        this.value = i;
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }
}
