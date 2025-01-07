package androidx.compose.runtime;

import java.util.Comparator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ComposerKt$$ExternalSyntheticLambda0 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return Intrinsics.compare(((Invalidation) obj).location, ((Invalidation) obj2).location);
    }
}
