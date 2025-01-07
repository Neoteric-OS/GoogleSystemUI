package com.android.systemui.statusbar.notification.icon.ui.viewmodel;

import android.graphics.drawable.Icon;
import androidx.collection.ArrayMap;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.systemui.util.kotlin.MapUtilsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.FilteringSequence;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.TransformingSequence;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationIconsViewData {
    public final int iconLimit;
    public final LimitType limitType;
    public final List visibleIcons;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static Diff computeDifference(NotificationIconsViewData notificationIconsViewData, NotificationIconsViewData notificationIconsViewData2) {
            Set set = SequencesKt.toSet(new TransformingSequence(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(notificationIconsViewData2.visibleIcons), new Function1() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData$Companion$computeDifference$prevKeys$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ((NotificationIconInfo) obj).notifKey;
                }
            }));
            Set<String> set2 = SequencesKt.toSet(new TransformingSequence(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(notificationIconsViewData.visibleIcons), new Function1() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData$Companion$computeDifference$newKeys$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ((NotificationIconInfo) obj).notifKey;
                }
            }));
            ArrayList arrayList = new ArrayList();
            for (String str : set2) {
                if (set.contains(str)) {
                    str = null;
                }
                if (str != null) {
                    arrayList.add(str);
                }
            }
            List list = notificationIconsViewData2.visibleIcons;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : list) {
                if (!set2.contains(((NotificationIconInfo) obj).notifKey)) {
                    arrayList2.add(obj);
                }
            }
            final Set set3 = SequencesKt.toSet(new TransformingSequence(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(notificationIconsViewData.visibleIcons), new Function1() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData$Companion$computeDifference$groupsToShow$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    NotificationIconInfo notificationIconInfo = (NotificationIconInfo) obj2;
                    return new IconGroupInfo(notificationIconInfo.sourceIcon, notificationIconInfo.groupKey);
                }
            }));
            FilteringSequence filter = SequencesKt.filter(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(arrayList2), new Function1() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData$Companion$computeDifference$replacements$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    NotificationIconInfo notificationIconInfo = (NotificationIconInfo) obj2;
                    return Boolean.valueOf(set3.contains(new IconGroupInfo(notificationIconInfo.sourceIcon, notificationIconInfo.groupKey)));
                }
            });
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(filter);
            while (filteringSequence$iterator$1.hasNext()) {
                Object next = filteringSequence$iterator$1.next();
                NotificationIconInfo notificationIconInfo = (NotificationIconInfo) next;
                Icon icon = notificationIconInfo.sourceIcon;
                String str2 = notificationIconInfo.groupKey;
                Object obj2 = linkedHashMap.get(str2);
                if (obj2 == null) {
                    obj2 = new ArrayList();
                    linkedHashMap.put(str2, obj2);
                }
                ((List) obj2).add(next);
            }
            ArrayMap arrayMap = new ArrayMap(0);
            MapUtilsKt.mapValuesNotNullTo(linkedHashMap, arrayMap, new Function1() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData$Companion$computeDifference$replacements$3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    NotificationIconInfo notificationIconInfo2;
                    List list2 = (List) ((Map.Entry) obj3).getValue();
                    if (list2.size() != 1) {
                        list2 = null;
                    }
                    if (list2 == null || (notificationIconInfo2 = (NotificationIconInfo) list2.get(0)) == null) {
                        return null;
                    }
                    return notificationIconInfo2.notifKey;
                }
            });
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add(((NotificationIconInfo) it.next()).notifKey);
            }
            return new Diff(arrayList, arrayList3, arrayMap);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Diff {
        public final List added;
        public final ArrayMap groupReplacements;
        public final List removed;

        public Diff(List list, List list2, ArrayMap arrayMap) {
            this.added = list;
            this.removed = list2;
            this.groupReplacements = arrayMap;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Diff)) {
                return false;
            }
            Diff diff = (Diff) obj;
            return this.added.equals(diff.added) && this.removed.equals(diff.removed) && this.groupReplacements.equals(diff.groupReplacements);
        }

        public final int hashCode() {
            return this.groupReplacements.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.added.hashCode() * 31, 31, this.removed);
        }

        public final String toString() {
            return "Diff(added=" + this.added + ", removed=" + this.removed + ", groupReplacements=" + this.groupReplacements + ")";
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LimitType {
        public static final /* synthetic */ LimitType[] $VALUES;
        public static final LimitType MaximumAmount;
        public static final LimitType MaximumIndex;

        static {
            LimitType limitType = new LimitType("MaximumAmount", 0);
            MaximumAmount = limitType;
            LimitType limitType2 = new LimitType("MaximumIndex", 1);
            MaximumIndex = limitType2;
            LimitType[] limitTypeArr = {limitType, limitType2};
            $VALUES = limitTypeArr;
            EnumEntriesKt.enumEntries(limitTypeArr);
        }

        public static LimitType valueOf(String str) {
            return (LimitType) Enum.valueOf(LimitType.class, str);
        }

        public static LimitType[] values() {
            return (LimitType[]) $VALUES.clone();
        }
    }

    public NotificationIconsViewData(List list, int i, LimitType limitType) {
        this.visibleIcons = list;
        this.iconLimit = i;
        this.limitType = limitType;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationIconsViewData)) {
            return false;
        }
        NotificationIconsViewData notificationIconsViewData = (NotificationIconsViewData) obj;
        return Intrinsics.areEqual(this.visibleIcons, notificationIconsViewData.visibleIcons) && this.iconLimit == notificationIconsViewData.iconLimit && this.limitType == notificationIconsViewData.limitType;
    }

    public final int hashCode() {
        return this.limitType.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.iconLimit, this.visibleIcons.hashCode() * 31, 31);
    }

    public final String toString() {
        return "NotificationIconsViewData(visibleIcons=" + this.visibleIcons + ", iconLimit=" + this.iconLimit + ", limitType=" + this.limitType + ")";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NotificationIconsViewData(int r2, int r3, java.util.List r4) {
        /*
            r1 = this;
            r0 = r3 & 1
            if (r0 == 0) goto L6
            kotlin.collections.EmptyList r4 = kotlin.collections.EmptyList.INSTANCE
        L6:
            r3 = r3 & 2
            if (r3 == 0) goto Le
            int r2 = r4.size()
        Le:
            com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData$LimitType r3 = com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData.LimitType.MaximumAmount
            r1.<init>(r4, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData.<init>(int, int, java.util.List):void");
    }
}
