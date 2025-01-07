package androidx.compose.ui.text.platform;

import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class URLSpanCache {
    public final WeakHashMap spansByAnnotation = new WeakHashMap();
    public final WeakHashMap urlSpansByAnnotation = new WeakHashMap();
    public final WeakHashMap linkSpansWithListenerByAnnotation = new WeakHashMap();
}
