package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$2;
import com.android.systemui.keyboard.shortcut.ui.composable.ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$3;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyListInterval implements LazyLayoutIntervalContent.Interval {
    public final ComposableLambdaImpl item;
    public final ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$2 key;
    public final ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$3 type;

    public LazyListInterval(ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$2 shortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$2, ShortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$3 shortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$3, ComposableLambdaImpl composableLambdaImpl) {
        this.key = shortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$2;
        this.type = shortcutHelperKt$EndSidePanel$2$invoke$$inlined$items$default$3;
        this.item = composableLambdaImpl;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public final Function1 getKey() {
        return this.key;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public final Function1 getType() {
        return this.type;
    }
}
