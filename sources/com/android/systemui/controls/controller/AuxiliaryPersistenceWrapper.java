package com.android.systemui.controls.controller;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.os.UserHandle;
import com.android.systemui.backup.BackupHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Pair;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuxiliaryPersistenceWrapper {
    public List favorites = EmptyList.INSTANCE;
    public final ControlsFavoritePersistenceWrapper persistenceWrapper;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DeletionJobService extends JobService {
        public static final /* synthetic */ int $r8$clinit = 0;
        public static final long WEEK_IN_MILLIS = TimeUnit.DAYS.toMillis(7);

        public final void attachContext(Context context) {
            attachBaseContext(context);
        }

        @Override // android.app.job.JobService
        public final boolean onStartJob(JobParameters jobParameters) {
            File file;
            PersistableBundle extras = jobParameters.getExtras();
            int i = extras != null ? extras.getInt("USER", 0) : 0;
            Object obj = BackupHelper.controlsDataLock;
            synchronized (BackupHelper.controlsDataLock) {
                if (new UserHandle(i).isSystem()) {
                    file = new File("aux_controls_favorites.xml");
                } else {
                    file = new File(("__USER_" + i + "_") + "aux_controls_favorites.xml");
                }
                getBaseContext().deleteFile(file.getPath());
            }
            return false;
        }

        @Override // android.app.job.JobService
        public final boolean onStopJob(JobParameters jobParameters) {
            return true;
        }
    }

    public AuxiliaryPersistenceWrapper(ControlsFavoritePersistenceWrapper controlsFavoritePersistenceWrapper) {
        this.persistenceWrapper = controlsFavoritePersistenceWrapper;
        initialize();
    }

    public final List getCachedFavoritesAndRemoveFor(ComponentName componentName) {
        ControlsFavoritePersistenceWrapper controlsFavoritePersistenceWrapper = this.persistenceWrapper;
        if (!controlsFavoritePersistenceWrapper.file.exists()) {
            return EmptyList.INSTANCE;
        }
        List list = this.favorites;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : list) {
            if (Intrinsics.areEqual(((StructureInfo) obj).componentName, componentName)) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list2 = (List) pair.component1();
        List list3 = (List) pair.component2();
        this.favorites = list3;
        if (list3.isEmpty()) {
            controlsFavoritePersistenceWrapper.file.delete();
        } else {
            controlsFavoritePersistenceWrapper.storeFavorites(list3);
        }
        return list2;
    }

    public final void initialize() {
        ControlsFavoritePersistenceWrapper controlsFavoritePersistenceWrapper = this.persistenceWrapper;
        this.favorites = controlsFavoritePersistenceWrapper.file.exists() ? controlsFavoritePersistenceWrapper.readFavorites() : EmptyList.INSTANCE;
    }
}
