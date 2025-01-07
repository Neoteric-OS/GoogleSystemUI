package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.State;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LazyLayoutKt$LazyLayout$1$itemContentFactory$1$1 extends Lambda implements Function0 {
    final /* synthetic */ State $currentItemProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyLayoutKt$LazyLayout$1$itemContentFactory$1$1(State state) {
        super(0);
        this.$currentItemProvider = state;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return (LazyLayoutItemProvider) ((Function0) this.$currentItemProvider.getValue()).invoke();
    }
}
