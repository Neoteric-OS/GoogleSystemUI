package androidx.compose.foundation.layout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class WindowInsetsSides {
    public static final int End = 6;
    public static final int Horizontal = 15;
    public static final int Left = 10;
    public static final int Right = 5;
    public static final int Start = 9;

    public static final void valueToString_impl$lambda$0$appendPlus(StringBuilder sb, String str) {
        if (sb.length() > 0) {
            sb.append('+');
        }
        sb.append(str);
    }
}
