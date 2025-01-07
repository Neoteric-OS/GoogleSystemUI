package androidx.compose.ui.graphics.colorspace;

import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ConnectorKt {
    public static final MutableIntObjectMap Connectors;

    static {
        Rgb rgb = ColorSpaces.Srgb;
        int i = rgb.id;
        Connector$Companion$identity$1 connector$Companion$identity$1 = new Connector$Companion$identity$1(rgb, rgb, 1);
        Oklab oklab = ColorSpaces.Oklab;
        int i2 = oklab.id << 6;
        int i3 = rgb.id;
        int i4 = i2 | i3;
        Connector connector = new Connector(rgb, oklab, 0);
        int i5 = (i3 << 6) | oklab.id;
        Connector connector2 = new Connector(oklab, rgb, 0);
        MutableIntObjectMap mutableIntObjectMap = IntObjectMapKt.EmptyIntObjectMap;
        MutableIntObjectMap mutableIntObjectMap2 = new MutableIntObjectMap();
        mutableIntObjectMap2.set(i | (i << 6), connector$Companion$identity$1);
        mutableIntObjectMap2.set(i4, connector);
        mutableIntObjectMap2.set(i5, connector2);
        Connectors = mutableIntObjectMap2;
    }
}
