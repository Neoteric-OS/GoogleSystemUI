package androidx.compose.ui.viewinterop;

import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryImpl$registerProvider$3;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ViewFactoryHolder$releaseBlock$1 extends Lambda implements Function0 {
    final /* synthetic */ ViewFactoryHolder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewFactoryHolder$releaseBlock$1(ViewFactoryHolder viewFactoryHolder) {
        super(0);
        this.this$0 = viewFactoryHolder;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ViewFactoryHolder viewFactoryHolder = this.this$0;
        viewFactoryHolder.releaseBlock.invoke(viewFactoryHolder.typedView);
        ViewFactoryHolder viewFactoryHolder2 = this.this$0;
        SaveableStateRegistry.Entry entry = viewFactoryHolder2.savableRegistryEntry;
        if (entry != null) {
            ((SaveableStateRegistryImpl$registerProvider$3) entry).unregister();
        }
        viewFactoryHolder2.savableRegistryEntry = null;
        return Unit.INSTANCE;
    }
}
