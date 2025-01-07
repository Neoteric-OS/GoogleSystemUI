package okio;

import java.io.InputStream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Okio {
    public static final InputStreamSource source(InputStream inputStream) {
        int i = Okio__JvmOkioKt.$r8$clinit;
        return new InputStreamSource(inputStream, new Timeout());
    }
}
