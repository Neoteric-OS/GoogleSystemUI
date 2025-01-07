package com.android.systemui.qs.panels.data.repository;

import android.content.res.Resources;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StockTilesRepository {
    public final List stockTiles;

    public StockTilesRepository(Resources resources) {
        List split$default = StringsKt.split$default(resources.getString(R.string.quick_settings_tiles_stock), new String[]{","}, 0, 6);
        ArrayList arrayList = new ArrayList();
        for (Object obj : split$default) {
            arrayList.add(obj);
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(TileSpec.Companion.create((String) it.next()));
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList2) {
            if (!(((TileSpec) obj2) instanceof TileSpec.Invalid)) {
                arrayList3.add(obj2);
            }
        }
        this.stockTiles = arrayList3;
    }
}
