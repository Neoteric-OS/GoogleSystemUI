package com.android.systemui.controls.controller;

import android.content.ComponentName;
import android.service.controls.Control;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Favorites {
    public static Map favMap = MapsKt.emptyMap();

    public static List getAllStructures() {
        Map map = favMap;
        ArrayList arrayList = new ArrayList();
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll((List) ((Map.Entry) it.next()).getValue(), arrayList);
        }
        return arrayList;
    }

    public static List getControlsForComponent(ComponentName componentName) {
        List structuresForComponent = getStructuresForComponent(componentName);
        ArrayList arrayList = new ArrayList();
        Iterator it = structuresForComponent.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(((StructureInfo) it.next()).controls, arrayList);
        }
        return arrayList;
    }

    public static List getStructuresForComponent(ComponentName componentName) {
        List list = (List) favMap.get(componentName);
        return list == null ? EmptyList.INSTANCE : list;
    }

    public static void replaceControls(StructureInfo structureInfo) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(favMap);
        ArrayList arrayList = new ArrayList();
        ComponentName componentName = structureInfo.componentName;
        boolean z = false;
        for (StructureInfo structureInfo2 : getStructuresForComponent(componentName)) {
            if (Intrinsics.areEqual(structureInfo2.structure, structureInfo.structure)) {
                z = true;
                structureInfo2 = structureInfo;
            }
            if (!structureInfo2.controls.isEmpty()) {
                arrayList.add(structureInfo2);
            }
        }
        if (!z && !structureInfo.controls.isEmpty()) {
            arrayList.add(structureInfo);
        }
        linkedHashMap.put(componentName, arrayList);
        favMap = linkedHashMap;
    }

    public static boolean updateControls(ComponentName componentName, List list) {
        Pair pair;
        int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        if (mapCapacity < 16) {
            mapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        for (Object obj : list) {
            linkedHashMap.put(((Control) obj).getControlId(), obj);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        boolean z = false;
        for (StructureInfo structureInfo : getStructuresForComponent(componentName)) {
            for (ControlInfo controlInfo : structureInfo.controls) {
                Control control = (Control) linkedHashMap.get(controlInfo.controlId);
                if (control != null) {
                    if (!Intrinsics.areEqual(control.getTitle(), controlInfo.controlTitle) || !Intrinsics.areEqual(control.getSubtitle(), controlInfo.controlSubtitle) || control.getDeviceType() != controlInfo.deviceType) {
                        z = true;
                        controlInfo = new ControlInfo(controlInfo.controlId, control.getTitle(), control.getSubtitle(), control.getDeviceType());
                    }
                    CharSequence structure = control.getStructure();
                    if (structure == null) {
                        structure = "";
                    }
                    if (!Intrinsics.areEqual(structureInfo.structure, structure)) {
                        z = true;
                    }
                    pair = new Pair(structure, controlInfo);
                } else {
                    pair = new Pair(structureInfo.structure, controlInfo);
                }
                CharSequence charSequence = (CharSequence) pair.component1();
                ControlInfo controlInfo2 = (ControlInfo) pair.component2();
                Object obj2 = linkedHashMap2.get(charSequence);
                if (obj2 == null) {
                    obj2 = new ArrayList();
                    linkedHashMap2.put(charSequence, obj2);
                }
                ((List) obj2).add(controlInfo2);
            }
        }
        if (!z) {
            return false;
        }
        ArrayList arrayList = new ArrayList(linkedHashMap2.size());
        for (Map.Entry entry : linkedHashMap2.entrySet()) {
            arrayList.add(new StructureInfo(componentName, (CharSequence) entry.getKey(), (List) entry.getValue()));
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(favMap);
        linkedHashMap3.put(componentName, arrayList);
        favMap = linkedHashMap3;
        return true;
    }
}
