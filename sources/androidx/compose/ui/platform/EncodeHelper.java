package androidx.compose.ui.platform;

import android.os.Parcel;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EncodeHelper {
    public Parcel parcel;

    public final void encode(byte b) {
        this.parcel.writeByte(b);
    }

    /* renamed from: encode--R2X_6o, reason: not valid java name */
    public final void m562encodeR2X_6o(long j) {
        long m687getTypeUIouoOA = TextUnit.m687getTypeUIouoOA(j);
        byte b = 0;
        if (!TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 0L)) {
            if (TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 4294967296L)) {
                b = 1;
            } else if (TextUnitType.m691equalsimpl0(m687getTypeUIouoOA, 8589934592L)) {
                b = 2;
            }
        }
        encode(b);
        if (TextUnitType.m691equalsimpl0(TextUnit.m687getTypeUIouoOA(j), 0L)) {
            return;
        }
        encode(TextUnit.m688getValueimpl(j));
    }

    public final void encode(float f) {
        this.parcel.writeFloat(f);
    }
}
