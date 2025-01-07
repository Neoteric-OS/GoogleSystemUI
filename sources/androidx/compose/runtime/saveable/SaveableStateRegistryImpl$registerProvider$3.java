package androidx.compose.runtime.saveable;

import androidx.collection.MutableScatterMap;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SaveableStateRegistryImpl$registerProvider$3 implements SaveableStateRegistry.Entry {
    public final /* synthetic */ String $key;
    public final /* synthetic */ Lambda $valueProvider;
    public final /* synthetic */ SaveableStateRegistryImpl this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public SaveableStateRegistryImpl$registerProvider$3(SaveableStateRegistryImpl saveableStateRegistryImpl, String str, Function0 function0) {
        this.this$0 = saveableStateRegistryImpl;
        this.$key = str;
        this.$valueProvider = (Lambda) function0;
    }

    public final void unregister() {
        SaveableStateRegistryImpl saveableStateRegistryImpl = this.this$0;
        MutableScatterMap mutableScatterMap = saveableStateRegistryImpl.valueProviders;
        String str = this.$key;
        List list = (List) mutableScatterMap.remove(str);
        if (list != null) {
            list.remove(this.$valueProvider);
        }
        if (list == null || list.isEmpty()) {
            return;
        }
        saveableStateRegistryImpl.valueProviders.set(str, list);
    }
}
