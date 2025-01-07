package androidx.datastore.preferences;

import android.content.Context;
import androidx.datastore.DataStoreFile;
import androidx.datastore.preferences.core.PreferenceDataStore;
import androidx.datastore.preferences.core.PreferenceDataStoreFactory;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PreferenceDataStoreSingletonDelegate {
    public volatile PreferenceDataStore INSTANCE;
    public final Object lock = new Object();
    public final Function1 produceMigrations;
    public final CoroutineScope scope;

    public PreferenceDataStoreSingletonDelegate(Function1 function1, CoroutineScope coroutineScope) {
        this.produceMigrations = function1;
        this.scope = coroutineScope;
    }

    public final Object getValue(Object obj, KProperty kProperty) {
        PreferenceDataStore preferenceDataStore;
        Context context = (Context) obj;
        PreferenceDataStore preferenceDataStore2 = this.INSTANCE;
        if (preferenceDataStore2 != null) {
            return preferenceDataStore2;
        }
        synchronized (this.lock) {
            try {
                if (this.INSTANCE == null) {
                    final Context applicationContext = context.getApplicationContext();
                    Function1 function1 = this.produceMigrations;
                    Intrinsics.checkNotNull(applicationContext);
                    this.INSTANCE = PreferenceDataStoreFactory.create((List) function1.invoke(applicationContext), this.scope, new Function0() { // from class: androidx.datastore.preferences.PreferenceDataStoreSingletonDelegate$getValue$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Context context2 = applicationContext;
                            Intrinsics.checkNotNull(context2);
                            this.getClass();
                            return DataStoreFile.dataStoreFile(context2, "TutorialScheduler".concat(".preferences_pb"));
                        }
                    });
                }
                preferenceDataStore = this.INSTANCE;
                Intrinsics.checkNotNull(preferenceDataStore);
            } catch (Throwable th) {
                throw th;
            }
        }
        return preferenceDataStore;
    }
}
