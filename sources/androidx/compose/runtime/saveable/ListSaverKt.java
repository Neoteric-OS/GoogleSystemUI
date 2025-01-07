package androidx.compose.runtime.saveable;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ListSaverKt {
    public static final SaverKt$Saver$1 listSaver(final Function2 function2, Function1 function1) {
        Function2 function22 = new Function2() { // from class: androidx.compose.runtime.saveable.ListSaverKt$listSaver$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                SaveableStateRegistry saveableStateRegistry;
                SaverScope saverScope = (SaverScope) obj;
                List list = (List) Function2.this.invoke(saverScope, obj2);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    Object obj3 = list.get(i);
                    if (obj3 != null && (saveableStateRegistry = ((SaveableHolder) saverScope).registry) != null && !saveableStateRegistry.canBeSaved(obj3)) {
                        throw new IllegalArgumentException("item can't be saved");
                    }
                }
                if (list.isEmpty()) {
                    return null;
                }
                return new ArrayList(list);
            }
        };
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(1, function1);
        SaverKt$Saver$1 saverKt$Saver$1 = SaverKt.AutoSaver;
        return new SaverKt$Saver$1(function22, function1);
    }
}
