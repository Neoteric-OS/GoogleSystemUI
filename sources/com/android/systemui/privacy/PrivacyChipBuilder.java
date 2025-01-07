package com.android.systemui.privacy;

import android.content.Context;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyChipBuilder {
    public final Context context;
    public final String lastSeparator;
    public final String separator;
    public final List types;

    public PrivacyChipBuilder(Context context, List list) {
        this.context = context;
        this.separator = context.getString(R.string.ongoing_privacy_dialog_separator);
        this.lastSeparator = context.getString(R.string.ongoing_privacy_dialog_last_separator);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            PrivacyItem privacyItem = (PrivacyItem) it.next();
            PrivacyApplication privacyApplication = privacyItem.application;
            Object obj = linkedHashMap.get(privacyApplication);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(privacyApplication, obj);
            }
            ((List) obj).add(privacyItem.privacyType);
        }
        CollectionsKt.sortedWith(MapsKt.toList(linkedHashMap), ComparisonsKt___ComparisonsJvmKt.compareBy(new Function1() { // from class: com.android.systemui.privacy.PrivacyChipBuilder.3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Integer.valueOf(-((List) ((Pair) obj2).getSecond()).size());
            }
        }, new Function1() { // from class: com.android.systemui.privacy.PrivacyChipBuilder.4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return CollectionsKt.minOrNull((Iterable) ((Pair) obj2).getSecond());
            }
        }));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(((PrivacyItem) it2.next()).privacyType);
        }
        this.types = CollectionsKt.sorted(CollectionsKt.distinct(arrayList));
    }

    public final String joinTypes() {
        int size = this.types.size();
        if (size == 0) {
            return "";
        }
        if (size == 1) {
            return ((PrivacyType) this.types.get(0)).getName(this.context);
        }
        List list = this.types;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((PrivacyType) it.next()).getName(this.context));
        }
        List subList = arrayList.subList(0, arrayList.size() - 1);
        StringBuilder sb = new StringBuilder();
        CollectionsKt.joinTo$default(subList, sb, this.separator, null, 124);
        sb.append(this.lastSeparator);
        sb.append(CollectionsKt.last(arrayList));
        return sb.toString();
    }
}
