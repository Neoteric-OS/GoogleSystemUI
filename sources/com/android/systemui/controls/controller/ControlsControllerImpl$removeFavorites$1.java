package com.android.systemui.controls.controller;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.util.Log;
import com.android.systemui.controls.ControlsServiceInfo;
import com.android.systemui.controls.panels.AuthorizedPanelsRepositoryImpl;
import com.android.systemui.controls.ui.ControlsUiControllerImpl$onSeedingComplete$1;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptySet;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsControllerImpl$removeFavorites$1 implements Runnable {
    public final /* synthetic */ Object $componentName;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ControlsControllerImpl this$0;

    public ControlsControllerImpl$removeFavorites$1(ControlsControllerImpl controlsControllerImpl, ControlsUiControllerImpl$onSeedingComplete$1 controlsUiControllerImpl$onSeedingComplete$1) {
        this.$r8$classId = 1;
        this.this$0 = controlsControllerImpl;
        this.$componentName = controlsUiControllerImpl$onSeedingComplete$1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                Map map = Favorites.favMap;
                ComponentName componentName = (ComponentName) this.$componentName;
                LinkedHashMap linkedHashMap = new LinkedHashMap(Favorites.favMap);
                boolean z = linkedHashMap.remove(componentName) != null;
                Favorites.favMap = linkedHashMap;
                if (z) {
                    this.this$0.persistenceWrapper.storeFavorites(Favorites.getAllStructures());
                }
                AuthorizedPanelsRepositoryImpl authorizedPanelsRepositoryImpl = this.this$0.authorizedPanelsRepository;
                Set singleton = Collections.singleton(((ComponentName) this.$componentName).getPackageName());
                SharedPreferences instantiateSharedPrefs = authorizedPanelsRepositoryImpl.instantiateSharedPrefs(((UserTrackerImpl) authorizedPanelsRepositoryImpl.userTracker).getUserHandle());
                Set<String> stringSet = instantiateSharedPrefs.getStringSet("authorized_panels", EmptySet.INSTANCE);
                Intrinsics.checkNotNull(stringSet);
                instantiateSharedPrefs.edit().putStringSet("authorized_panels", SetsKt.minus((Set) stringSet, (Iterable) singleton)).apply();
                break;
            case 1:
                ControlsControllerImpl controlsControllerImpl = this.this$0;
                if (controlsControllerImpl.seedingInProgress) {
                    controlsControllerImpl.seedingCallbacks.add((ControlsUiControllerImpl$onSeedingComplete$1) this.$componentName);
                    break;
                } else {
                    ((ControlsUiControllerImpl$onSeedingComplete$1) this.$componentName).accept(Boolean.FALSE);
                    break;
                }
            case 2:
                List list = (List) this.$componentName;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((ControlsServiceInfo) it.next()).componentName);
                }
                Set set = CollectionsKt.toSet(arrayList);
                List allStructures = Favorites.getAllStructures();
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(allStructures, 10));
                Iterator it2 = allStructures.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((StructureInfo) it2.next()).componentName);
                }
                Set set2 = CollectionsKt.toSet(arrayList2);
                ControlsControllerImpl controlsControllerImpl2 = this.this$0;
                SharedPreferences sharedPreferences$1 = ((UserFileManagerImpl) controlsControllerImpl2.userFileManager).getSharedPreferences$1(((UserTrackerImpl) controlsControllerImpl2.userTracker).getUserId(), "controls_prefs");
                Set<String> stringSet2 = sharedPreferences$1.getStringSet("SeedingCompleted", new LinkedHashSet());
                Set set3 = set;
                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set3, 10));
                Iterator it3 = set3.iterator();
                while (it3.hasNext()) {
                    arrayList3.add(((ComponentName) it3.next()).getPackageName());
                }
                sharedPreferences$1.edit().putStringSet("SeedingCompleted", stringSet2 != null ? CollectionsKt.intersect(stringSet2, arrayList3) : EmptySet.INSTANCE).apply();
                Set set4 = set2;
                Set<ComponentName> subtract = CollectionsKt.subtract(set4, set3);
                ControlsControllerImpl controlsControllerImpl3 = this.this$0;
                boolean z2 = false;
                for (ComponentName componentName2 : subtract) {
                    Map map2 = Favorites.favMap;
                    Intrinsics.checkNotNull(componentName2);
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap(Favorites.favMap);
                    linkedHashMap2.remove(componentName2);
                    Favorites.favMap = linkedHashMap2;
                    ControlsBindingControllerImpl controlsBindingControllerImpl = (ControlsBindingControllerImpl) controlsControllerImpl3.bindingController;
                    controlsBindingControllerImpl.getClass();
                    ((ExecutorImpl) controlsBindingControllerImpl.backgroundExecutor).execute(new ControlsBindingControllerImpl$onComponentRemoved$1(0, controlsBindingControllerImpl, componentName2));
                    z2 = true;
                }
                if (!this.this$0.auxiliaryPersistenceWrapper.favorites.isEmpty()) {
                    Set<ComponentName> subtract2 = CollectionsKt.subtract(set3, set4);
                    ControlsControllerImpl controlsControllerImpl4 = this.this$0;
                    for (ComponentName componentName3 : subtract2) {
                        AuxiliaryPersistenceWrapper auxiliaryPersistenceWrapper = controlsControllerImpl4.auxiliaryPersistenceWrapper;
                        Intrinsics.checkNotNull(componentName3);
                        List cachedFavoritesAndRemoveFor = auxiliaryPersistenceWrapper.getCachedFavoritesAndRemoveFor(componentName3);
                        if (!cachedFavoritesAndRemoveFor.isEmpty()) {
                            Iterator it4 = cachedFavoritesAndRemoveFor.iterator();
                            while (it4.hasNext()) {
                                Favorites.replaceControls((StructureInfo) it4.next());
                            }
                            z2 = true;
                        }
                    }
                    Set<ComponentName> intersect = CollectionsKt.intersect(set3, set4);
                    ControlsControllerImpl controlsControllerImpl5 = this.this$0;
                    for (ComponentName componentName4 : intersect) {
                        AuxiliaryPersistenceWrapper auxiliaryPersistenceWrapper2 = controlsControllerImpl5.auxiliaryPersistenceWrapper;
                        Intrinsics.checkNotNull(componentName4);
                        auxiliaryPersistenceWrapper2.getCachedFavoritesAndRemoveFor(componentName4);
                    }
                }
                if (z2) {
                    Log.d("ControlsControllerImpl", "Detected change in available services, storing updated favorites");
                    this.this$0.persistenceWrapper.storeFavorites(Favorites.getAllStructures());
                    break;
                }
                break;
            default:
                Map map3 = Favorites.favMap;
                Favorites.replaceControls((StructureInfo) this.$componentName);
                this.this$0.persistenceWrapper.storeFavorites(Favorites.getAllStructures());
                break;
        }
    }

    public /* synthetic */ ControlsControllerImpl$removeFavorites$1(Object obj, ControlsControllerImpl controlsControllerImpl, int i) {
        this.$r8$classId = i;
        this.$componentName = obj;
        this.this$0 = controlsControllerImpl;
    }
}
