package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.SaverScope;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SaversKt$NonNullValueClassSaver$1 implements NonNullValueClassSaver {
    public final /* synthetic */ Lambda $restore;
    public final /* synthetic */ Lambda $save;

    /* JADX WARN: Multi-variable type inference failed */
    public SaversKt$NonNullValueClassSaver$1(Function2 function2, Function1 function1) {
        this.$save = (Lambda) function2;
        this.$restore = (Lambda) function1;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function2, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.runtime.saveable.Saver
    public final Object save(SaverScope saverScope, Object obj) {
        return this.$save.invoke(saverScope, obj);
    }
}
