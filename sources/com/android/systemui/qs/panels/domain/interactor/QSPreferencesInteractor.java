package com.android.systemui.qs.panels.domain.interactor;

import android.content.SharedPreferences;
import com.android.systemui.qs.panels.data.repository.QSPreferencesRepository;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.settings.UserFileManagerImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSPreferencesInteractor {
    public final Flow largeTilesSpecs;
    public final QSPreferencesRepository repo;
    public final Flow showLabels;

    public QSPreferencesInteractor(QSPreferencesRepository qSPreferencesRepository) {
        this.repo = qSPreferencesRepository;
        this.showLabels = qSPreferencesRepository.showLabels;
        this.largeTilesSpecs = qSPreferencesRepository.largeTilesSpecs;
    }

    public final void setLargeTilesSpecs(Set set) {
        QSPreferencesRepository qSPreferencesRepository = this.repo;
        SharedPreferences.Editor edit = ((UserFileManagerImpl) qSPreferencesRepository.userFileManager).getSharedPreferences$1(qSPreferencesRepository.userRepository.getSelectedUserInfo().id, "quick_settings_prefs").edit();
        Set set2 = set;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set2, 10));
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            arrayList.add(((TileSpec) it.next()).getSpec());
        }
        edit.putStringSet("large_tiles_specs", CollectionsKt.toSet(arrayList)).apply();
    }
}
