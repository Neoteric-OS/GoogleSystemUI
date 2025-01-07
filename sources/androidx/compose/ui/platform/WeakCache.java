package androidx.compose.ui.platform;

import androidx.compose.runtime.collection.MutableVector;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WeakCache {
    public final MutableVector values = new MutableVector(new Reference[16]);
    public final ReferenceQueue referenceQueue = new ReferenceQueue();
}
