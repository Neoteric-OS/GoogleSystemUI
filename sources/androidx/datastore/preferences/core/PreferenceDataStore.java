package androidx.datastore.preferences.core;

import androidx.datastore.core.DataStore;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PreferenceDataStore implements DataStore {
    public final DataStore delegate;

    public PreferenceDataStore(DataStore dataStore) {
        this.delegate = dataStore;
    }

    @Override // androidx.datastore.core.DataStore
    public final Flow getData() {
        return this.delegate.getData();
    }

    @Override // androidx.datastore.core.DataStore
    public final Object updateData(Continuation continuation, Function2 function2) {
        return this.delegate.updateData(continuation, new PreferenceDataStore$updateData$2(null, function2));
    }
}
