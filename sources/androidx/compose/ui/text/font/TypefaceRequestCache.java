package androidx.compose.ui.text.font;

import androidx.collection.SieveCache;
import androidx.compose.ui.text.platform.SynchronizedObject;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TypefaceRequestCache {
    public final SynchronizedObject lock = new SynchronizedObject();
    public final SieveCache resultCache = new SieveCache();
}
