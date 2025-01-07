package androidx.compose.ui;

import androidx.compose.ui.BiasAbsoluteAlignment;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AbsoluteAlignment {
    public static final BiasAbsoluteAlignment.Horizontal Left = null;
    public static final BiasAbsoluteAlignment.Horizontal Right = null;
    public static final BiasAbsoluteAlignment TopLeft = new BiasAbsoluteAlignment(-1.0f);
    public static final BiasAbsoluteAlignment TopRight = new BiasAbsoluteAlignment(1.0f);

    static {
        new BiasAbsoluteAlignment.Horizontal(-1.0f);
        new BiasAbsoluteAlignment.Horizontal(1.0f);
    }
}
