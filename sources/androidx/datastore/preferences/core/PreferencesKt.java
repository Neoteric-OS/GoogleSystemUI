package androidx.datastore.preferences.core;

import androidx.datastore.core.DataStore;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PreferencesKt {
    public static final Object edit(DataStore dataStore, Function2 function2, Continuation continuation) {
        return dataStore.updateData(continuation, new PreferencesKt$edit$2(null, function2));
    }
}
