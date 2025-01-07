package androidx.datastore.preferences;

import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PreferenceDataStoreDelegateKt {
    public static PreferenceDataStoreSingletonDelegate preferencesDataStore$default(CoroutineScope coroutineScope) {
        return new PreferenceDataStoreSingletonDelegate(new Function1() { // from class: androidx.datastore.preferences.PreferenceDataStoreDelegateKt$preferencesDataStore$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return EmptyList.INSTANCE;
            }
        }, coroutineScope);
    }
}
