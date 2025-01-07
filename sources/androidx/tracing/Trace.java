package androidx.tracing;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Trace {
    public static final void beginSection(String str) {
        String str2 = str.length() <= 127 ? str : null;
        if (str2 == null) {
            str2 = str.substring(0, 127);
        }
        android.os.Trace.beginSection(str2);
    }
}
