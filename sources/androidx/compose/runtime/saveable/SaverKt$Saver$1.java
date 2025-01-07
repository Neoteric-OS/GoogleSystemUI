package androidx.compose.runtime.saveable;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SaverKt$Saver$1 implements Saver {
    public final /* synthetic */ Function1 $restore;
    public final /* synthetic */ Function2 $save;

    public SaverKt$Saver$1(Function2 function2, Function1 function1) {
        this.$save = function2;
        this.$restore = function1;
    }

    @Override // androidx.compose.runtime.saveable.Saver
    public final Object save(SaverScope saverScope, Object obj) {
        return this.$save.invoke(saverScope, obj);
    }
}
