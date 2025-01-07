package com.android.systemui.controls.management;

import android.service.controls.Control;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.android.systemui.controls.ControlStatus;
import com.android.systemui.controls.controller.ControlInfo;
import com.android.systemui.controls.management.ControlsModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.sequences.TransformingSequence;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AllModel implements ControlsModel {
    public final List controls;
    public final ControlsModel.ControlsModelCallback controlsModelCallback;
    public final List elements;
    public final CharSequence emptyZoneString;
    public final List favoriteIds;
    public boolean modified;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OrderedMap implements Map, KMutableMap {
        public final Map map;
        public final List orderedKeys = new ArrayList();

        public OrderedMap(Map map) {
            this.map = map;
        }

        @Override // java.util.Map
        public final void clear() {
            this.orderedKeys.clear();
            this.map.clear();
        }

        @Override // java.util.Map
        public final boolean containsKey(Object obj) {
            return this.map.containsKey(obj);
        }

        @Override // java.util.Map
        public final boolean containsValue(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override // java.util.Map
        public final Set entrySet() {
            return this.map.entrySet();
        }

        @Override // java.util.Map
        public final Object get(Object obj) {
            return this.map.get(obj);
        }

        @Override // java.util.Map
        public final boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override // java.util.Map
        public final Set keySet() {
            return this.map.keySet();
        }

        @Override // java.util.Map
        public final Object put(Object obj, Object obj2) {
            if (!this.map.containsKey(obj)) {
                this.orderedKeys.add(obj);
            }
            return this.map.put(obj, obj2);
        }

        @Override // java.util.Map
        public final void putAll(Map map) {
            this.map.putAll(map);
        }

        @Override // java.util.Map
        public final Object remove(Object obj) {
            Object remove = this.map.remove(obj);
            if (remove != null) {
                this.orderedKeys.remove(obj);
            }
            return remove;
        }

        @Override // java.util.Map
        public final int size() {
            return this.map.size();
        }

        @Override // java.util.Map
        public final Collection values() {
            return this.map.values();
        }
    }

    public AllModel(List list, List list2, CharSequence charSequence, ControlsModel.ControlsModelCallback controlsModelCallback) {
        this.controls = list;
        this.emptyZoneString = charSequence;
        this.controlsModelCallback = controlsModelCallback;
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(((ControlStatus) it.next()).control.getControlId());
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list2) {
            if (hashSet.contains((String) obj)) {
                arrayList.add(obj);
            }
        }
        this.favoriteIds = new ArrayList(arrayList);
        List list3 = this.controls;
        OrderedMap orderedMap = new OrderedMap(new ArrayMap());
        for (Object obj2 : list3) {
            String zone = ((ControlStatus) obj2).control.getZone();
            zone = zone == null ? "" : zone;
            Object obj3 = ((ArrayMap) orderedMap.map).get(zone);
            if (obj3 == null) {
                obj3 = new ArrayList();
                orderedMap.put(zone, obj3);
            }
            ((List) obj3).add(obj2);
        }
        ArrayList arrayList2 = new ArrayList();
        TransformingSequence transformingSequence = null;
        for (CharSequence charSequence2 : orderedMap.orderedKeys) {
            TransformingSequence transformingSequence2 = new TransformingSequence(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1((Iterable) MapsKt.getValue(charSequence2, orderedMap)), new Function1() { // from class: com.android.systemui.controls.management.AllModel$createWrappers$values$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj4) {
                    return new ControlStatusWrapper((ControlStatus) obj4);
                }
            });
            if (TextUtils.isEmpty(charSequence2)) {
                transformingSequence = transformingSequence2;
            } else {
                Intrinsics.checkNotNull(charSequence2);
                arrayList2.add(new ZoneNameWrapper(charSequence2));
                CollectionsKt__MutableCollectionsKt.addAll(arrayList2, transformingSequence2);
            }
        }
        if (transformingSequence != null) {
            if (((ArrayMap) orderedMap.map).size() != 1) {
                arrayList2.add(new ZoneNameWrapper(this.emptyZoneString));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList2, transformingSequence);
        }
        this.elements = arrayList2;
    }

    @Override // com.android.systemui.controls.management.ControlsModel
    public final void changeFavoriteStatus(String str, boolean z) {
        Object obj;
        ControlStatus controlStatus;
        Iterator it = this.elements.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            ElementWrapper elementWrapper = (ElementWrapper) obj;
            if ((elementWrapper instanceof ControlStatusWrapper) && Intrinsics.areEqual(((ControlStatusWrapper) elementWrapper).controlStatus.control.getControlId(), str)) {
                break;
            }
        }
        ControlStatusWrapper controlStatusWrapper = (ControlStatusWrapper) obj;
        if (controlStatusWrapper == null || (controlStatus = controlStatusWrapper.controlStatus) == null || z != controlStatus.favorite) {
            if (z ? this.favoriteIds.add(str) : this.favoriteIds.remove(str)) {
                boolean z2 = this.modified;
                ControlsModel.ControlsModelCallback controlsModelCallback = this.controlsModelCallback;
                if (!z2) {
                    this.modified = true;
                    controlsModelCallback.onFirstChange();
                }
                controlsModelCallback.onChange();
            }
            if (controlStatusWrapper != null) {
                controlStatusWrapper.controlStatus.favorite = z;
            }
        }
    }

    @Override // com.android.systemui.controls.management.ControlsModel
    public final List getElements() {
        return this.elements;
    }

    public final List getFavorites() {
        Object obj;
        List<String> list = this.favoriteIds;
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            Iterator it = this.controls.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual(((ControlStatus) obj).control.getControlId(), str)) {
                    break;
                }
            }
            ControlStatus controlStatus = (ControlStatus) obj;
            Control control = controlStatus != null ? controlStatus.control : null;
            ControlInfo controlInfo = control != null ? new ControlInfo(control.getControlId(), control.getTitle(), control.getSubtitle(), control.getDeviceType()) : null;
            if (controlInfo != null) {
                arrayList.add(controlInfo);
            }
        }
        return arrayList;
    }

    @Override // com.android.systemui.controls.management.ControlsModel
    public final /* bridge */ /* synthetic */ FavoritesModel$moveHelper$1 getMoveHelper() {
        return null;
    }
}
