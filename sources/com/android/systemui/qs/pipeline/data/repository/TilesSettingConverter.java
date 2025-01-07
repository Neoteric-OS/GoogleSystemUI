package com.android.systemui.qs.pipeline.data.repository;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TilesSettingConverter {
    public static List toTilesList(String str) {
        List split$default = StringsKt.split$default(str, new String[]{","}, 0, 6);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10));
        Iterator it = split$default.iterator();
        while (it.hasNext()) {
            arrayList.add(TileSpec.Companion.create((String) it.next()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!Intrinsics.areEqual((TileSpec) obj, TileSpec.Invalid.INSTANCE)) {
                arrayList2.add(obj);
            }
        }
        return arrayList2;
    }

    public static Set toTilesSet(String str) {
        List split$default = StringsKt.split$default(str, new String[]{","}, 0, 6);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10));
        Iterator it = split$default.iterator();
        while (it.hasNext()) {
            arrayList.add(TileSpec.Companion.create((String) it.next()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!Intrinsics.areEqual((TileSpec) obj, TileSpec.Invalid.INSTANCE)) {
                arrayList2.add(obj);
            }
        }
        return CollectionsKt.toSet(arrayList2);
    }
}
