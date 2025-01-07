package androidx.compose.ui.graphics;

import androidx.collection.MutableIntObjectMap;
import androidx.compose.runtime.OpaqueKey$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaceKt;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.Connector;
import androidx.compose.ui.graphics.colorspace.ConnectorKt;
import kotlin.UnsignedKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Color {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long Black = ColorKt.Color(4278190080L);
    public static final long Blue;
    public static final long Red;
    public static final long Transparent;
    public static final long Unspecified;
    public static final long White;
    public final long value;

    static {
        ColorKt.Color(4282664004L);
        ColorKt.Color(4287137928L);
        ColorKt.Color(4291611852L);
        White = ColorKt.Color(4294967295L);
        Red = ColorKt.Color(4294901760L);
        ColorKt.Color(4278255360L);
        Blue = ColorKt.Color(4278190335L);
        ColorKt.Color(4294967040L);
        ColorKt.Color(4278255615L);
        ColorKt.Color(4294902015L);
        Transparent = ColorKt.Color(0);
        Unspecified = ColorKt.Color(0.0f, 0.0f, 0.0f, 0.0f, ColorSpaces.Unspecified);
    }

    public /* synthetic */ Color(long j) {
        this.value = j;
    }

    /* renamed from: convert-vNxB06k, reason: not valid java name */
    public static final long m361convertvNxB06k(long j, ColorSpace colorSpace) {
        Connector connector;
        ColorSpace m366getColorSpaceimpl = m366getColorSpaceimpl(j);
        int i = m366getColorSpaceimpl.id;
        int i2 = colorSpace.id;
        if ((i | i2) < 0) {
            connector = ColorSpaceKt.m404createConnectorYBCOT_4(m366getColorSpaceimpl, colorSpace);
        } else {
            MutableIntObjectMap mutableIntObjectMap = ConnectorKt.Connectors;
            int i3 = i | (i2 << 6);
            Object obj = mutableIntObjectMap.get(i3);
            if (obj == null) {
                obj = ColorSpaceKt.m404createConnectorYBCOT_4(m366getColorSpaceimpl, colorSpace);
                mutableIntObjectMap.set(i3, obj);
            }
            connector = (Connector) obj;
        }
        return connector.mo405transformToColorl2rxGTc$ui_graphics_release(j);
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m363equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* renamed from: getAlpha-impl, reason: not valid java name */
    public static final float m364getAlphaimpl(long j) {
        float ulongToDouble;
        float f;
        if ((63 & j) == 0) {
            ulongToDouble = (float) UnsignedKt.ulongToDouble((j >>> 56) & 255);
            f = 255.0f;
        } else {
            ulongToDouble = (float) UnsignedKt.ulongToDouble((j >>> 6) & 1023);
            f = 1023.0f;
        }
        return ulongToDouble / f;
    }

    /* renamed from: getBlue-impl, reason: not valid java name */
    public static final float m365getBlueimpl(long j) {
        int i;
        int i2;
        int i3;
        if ((63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble((j >>> 32) & 255)) / 255.0f;
        }
        short s = (short) ((j >>> 16) & 65535);
        int i4 = 32768 & s;
        int i5 = ((65535 & s) >>> 10) & 31;
        int i6 = s & 1023;
        if (i5 != 0) {
            int i7 = i6 << 13;
            if (i5 == 31) {
                i = 255;
                if (i7 != 0) {
                    i7 |= 4194304;
                }
            } else {
                i = i5 + 112;
            }
            int i8 = i;
            i2 = i7;
            i3 = i8;
        } else {
            if (i6 != 0) {
                float intBitsToFloat = Float.intBitsToFloat(i6 + 1056964608) - Float16Kt.Fp32DenormalFloat;
                return i4 == 0 ? intBitsToFloat : -intBitsToFloat;
            }
            i3 = 0;
            i2 = 0;
        }
        return Float.intBitsToFloat((i3 << 23) | (i4 << 16) | i2);
    }

    /* renamed from: getColorSpace-impl, reason: not valid java name */
    public static final ColorSpace m366getColorSpaceimpl(long j) {
        ColorSpaces colorSpaces = ColorSpaces.INSTANCE;
        return ColorSpaces.ColorSpacesArray[(int) (j & 63)];
    }

    /* renamed from: getGreen-impl, reason: not valid java name */
    public static final float m367getGreenimpl(long j) {
        int i;
        int i2;
        int i3;
        if ((63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble((j >>> 40) & 255)) / 255.0f;
        }
        short s = (short) ((j >>> 32) & 65535);
        int i4 = 32768 & s;
        int i5 = ((65535 & s) >>> 10) & 31;
        int i6 = s & 1023;
        if (i5 != 0) {
            int i7 = i6 << 13;
            if (i5 == 31) {
                i = 255;
                if (i7 != 0) {
                    i7 |= 4194304;
                }
            } else {
                i = i5 + 112;
            }
            int i8 = i;
            i2 = i7;
            i3 = i8;
        } else {
            if (i6 != 0) {
                float intBitsToFloat = Float.intBitsToFloat(i6 + 1056964608) - Float16Kt.Fp32DenormalFloat;
                return i4 == 0 ? intBitsToFloat : -intBitsToFloat;
            }
            i3 = 0;
            i2 = 0;
        }
        return Float.intBitsToFloat((i3 << 23) | (i4 << 16) | i2);
    }

    /* renamed from: getRed-impl, reason: not valid java name */
    public static final float m368getRedimpl(long j) {
        int i;
        int i2;
        int i3;
        if ((63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble((j >>> 48) & 255)) / 255.0f;
        }
        short s = (short) ((j >>> 48) & 65535);
        int i4 = 32768 & s;
        int i5 = ((65535 & s) >>> 10) & 31;
        int i6 = s & 1023;
        if (i5 != 0) {
            int i7 = i6 << 13;
            if (i5 == 31) {
                i = 255;
                if (i7 != 0) {
                    i7 |= 4194304;
                }
            } else {
                i = i5 + 112;
            }
            int i8 = i;
            i2 = i7;
            i3 = i8;
        } else {
            if (i6 != 0) {
                float intBitsToFloat = Float.intBitsToFloat(i6 + 1056964608) - Float16Kt.Fp32DenormalFloat;
                return i4 == 0 ? intBitsToFloat : -intBitsToFloat;
            }
            i3 = 0;
            i2 = 0;
        }
        return Float.intBitsToFloat((i3 << 23) | (i4 << 16) | i2);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m369toStringimpl(long j) {
        StringBuilder sb = new StringBuilder("Color(");
        sb.append(m368getRedimpl(j));
        sb.append(", ");
        sb.append(m367getGreenimpl(j));
        sb.append(", ");
        sb.append(m365getBlueimpl(j));
        sb.append(", ");
        sb.append(m364getAlphaimpl(j));
        sb.append(", ");
        return OpaqueKey$$ExternalSyntheticOutline0.m(sb, m366getColorSpaceimpl(j).name, ')');
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Color) {
            return this.value == ((Color) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.value);
    }

    public final String toString() {
        return m369toStringimpl(this.value);
    }
}
