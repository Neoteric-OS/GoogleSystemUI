package com.android.systemui.qs.pipeline.domain.interactor;

import com.android.systemui.Dumpable;
import com.android.systemui.qs.pipeline.domain.model.TileModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface CurrentTilesInteractor extends Dumpable {
    default List getCurrentTilesSpecs() {
        Iterable iterable = (Iterable) ((CurrentTilesInteractorImpl) this).currentTiles.getValue();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((TileModel) it.next()).spec);
        }
        return arrayList;
    }
}
