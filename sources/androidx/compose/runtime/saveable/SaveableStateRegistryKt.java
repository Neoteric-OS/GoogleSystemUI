package androidx.compose.runtime.saveable;

import androidx.compose.runtime.StaticProvidableCompositionLocal;
import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SaveableStateRegistryKt {
    public static final StaticProvidableCompositionLocal LocalSaveableStateRegistry = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.runtime.saveable.SaveableStateRegistryKt$LocalSaveableStateRegistry$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return null;
        }
    });

    public static final SaveableStateRegistry SaveableStateRegistry(Map map, Function1 function1) {
        return new SaveableStateRegistryImpl(map, function1);
    }
}
