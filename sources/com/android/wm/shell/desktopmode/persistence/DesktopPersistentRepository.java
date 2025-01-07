package com.android.wm.shell.desktopmode.persistence;

import android.content.Context;
import androidx.datastore.DataStoreFile;
import androidx.datastore.core.DataStoreFactory;
import androidx.datastore.core.DataStoreImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopPersistentRepository {
    public final DataStoreImpl dataStore;
    public final FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1 dataStoreFlow;

    public DesktopPersistentRepository(final Context context, CoroutineScope coroutineScope) {
        new FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(DataStoreFactory.create$default(DesktopPersistentRepository$Companion$DesktopPersistentRepositoriesSerializer.INSTANCE, coroutineScope, new Function0() { // from class: com.android.wm.shell.desktopmode.persistence.DesktopPersistentRepository.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DataStoreFile.dataStoreFile(context, "desktop_persistent_repositories.pb");
            }
        }, 6).data, new DesktopPersistentRepository$dataStoreFlow$1(3, null));
    }
}
