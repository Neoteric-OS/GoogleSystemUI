package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableVectorWithMutationTracking {
    public final Function0 onVectorMutated;
    public final MutableVector vector;

    public MutableVectorWithMutationTracking(MutableVector mutableVector, Function0 function0) {
        this.vector = mutableVector;
        this.onVectorMutated = function0;
    }
}
