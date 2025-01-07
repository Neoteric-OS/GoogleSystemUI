package com.android.wm.shell.desktopmode.education.data;

import android.content.Context;
import androidx.datastore.DataStoreFile;
import androidx.datastore.core.DataStore;
import androidx.datastore.core.DataStoreFactory;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AppHandleEducationDatastoreRepository {
    public final DataStore dataStore;
    public final FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1 dataStoreFlow;

    public AppHandleEducationDatastoreRepository(DataStore dataStore) {
        new FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(dataStore.getData(), new AppHandleEducationDatastoreRepository$dataStoreFlow$1(3, null));
    }

    public AppHandleEducationDatastoreRepository(final Context context) {
        this(DataStoreFactory.create$default(AppHandleEducationDatastoreRepository$Companion$WindowingEducationProtoSerializer.INSTANCE, null, new Function0() { // from class: com.android.wm.shell.desktopmode.education.data.AppHandleEducationDatastoreRepository.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DataStoreFile.dataStoreFile(context, "app_handle_education.pb");
            }
        }, 14));
    }
}
