package androidx.datastore.preferences.core;

import androidx.datastore.core.DataStoreFactory;
import androidx.datastore.core.okio.OkioStorage;
import java.io.File;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import okio.FileSystem;
import okio.Path;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PreferenceDataStoreFactory {
    public static PreferenceDataStore create(List list, CoroutineScope coroutineScope, final Function0 function0) {
        return new PreferenceDataStore(new PreferenceDataStore(DataStoreFactory.create(new OkioStorage(FileSystem.SYSTEM, new Function0() { // from class: androidx.datastore.preferences.core.PreferenceDataStoreFactory$create$delegate$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                File file = (File) Function0.this.invoke();
                String name = file.getName();
                int lastIndexOf = name.lastIndexOf(46, StringsKt.getLastIndex(name));
                if ((lastIndexOf == -1 ? "" : name.substring(lastIndexOf + 1, name.length())).equals("preferences_pb")) {
                    Path.Companion companion = Path.Companion;
                    return Path.Companion.get$default(file.getAbsoluteFile());
                }
                throw new IllegalStateException(("File extension for file: " + file + " does not match required extension for Preferences file: preferences_pb").toString());
            }
        }), list, coroutineScope)));
    }
}
