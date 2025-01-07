package com.android.systemui.controls.controller;

import android.content.ComponentName;
import android.service.controls.Control;
import com.android.systemui.controls.ControlStatus;
import com.android.systemui.controls.management.ControlsFavoritingActivity$loadControls$1$1;
import com.android.systemui.statusbar.policy.DeviceControlsControllerImpl$seedFavorites$2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsControllerImpl$refreshStatus$1 implements Runnable {
    public final /* synthetic */ Object $componentName;
    public final /* synthetic */ Object $control;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ControlsControllerImpl this$0;

    public /* synthetic */ ControlsControllerImpl$refreshStatus$1(ComponentName componentName, Object obj, ControlsControllerImpl controlsControllerImpl, int i) {
        this.$r8$classId = i;
        this.$componentName = componentName;
        this.$control = obj;
        this.this$0 = controlsControllerImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                Map map = Favorites.favMap;
                if (Favorites.updateControls((ComponentName) this.$componentName, Collections.singletonList((Control) this.$control))) {
                    this.this$0.persistenceWrapper.storeFavorites(Favorites.getAllStructures());
                    break;
                }
                break;
            case 1:
                Map map2 = Favorites.favMap;
                List<StructureInfo> structuresForComponent = Favorites.getStructuresForComponent((ComponentName) this.$componentName);
                ControlsControllerImpl controlsControllerImpl = this.this$0;
                ComponentName componentName = (ComponentName) this.$componentName;
                ArrayList arrayList = new ArrayList();
                for (StructureInfo structureInfo : structuresForComponent) {
                    List list = structureInfo.controls;
                    ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(controlsControllerImpl.createRemovedStatus(componentName, (ControlInfo) it.next(), structureInfo.structure, false));
                    }
                    CollectionsKt__MutableCollectionsKt.addAll(arrayList2, arrayList);
                }
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    arrayList3.add(((ControlStatus) it2.next()).control.getControlId());
                }
                ((ControlsFavoritingActivity$loadControls$1$1) this.$control).accept(new ControlsControllerKt$createLoadDataObject$1(arrayList, arrayList3, true));
                break;
            default:
                this.this$0.seedFavoritesForComponents((List) this.$componentName, (DeviceControlsControllerImpl$seedFavorites$2) this.$control);
                break;
        }
    }

    public ControlsControllerImpl$refreshStatus$1(ControlsControllerImpl controlsControllerImpl, List list, DeviceControlsControllerImpl$seedFavorites$2 deviceControlsControllerImpl$seedFavorites$2) {
        this.$r8$classId = 2;
        this.this$0 = controlsControllerImpl;
        this.$componentName = list;
        this.$control = deviceControlsControllerImpl$seedFavorites$2;
    }
}
