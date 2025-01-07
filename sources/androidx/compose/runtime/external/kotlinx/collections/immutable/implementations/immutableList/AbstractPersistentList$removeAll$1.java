package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AbstractPersistentList$removeAll$1 extends Lambda implements Function1 {
    final /* synthetic */ Collection $elements;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractPersistentList$removeAll$1(Collection collection) {
        super(1);
        this.$elements = collection;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return Boolean.valueOf(this.$elements.contains(obj));
    }
}
