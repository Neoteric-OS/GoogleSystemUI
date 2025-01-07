package com.android.systemui.controls.controller;

import android.app.PendingIntent;
import android.app.backup.BackupManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.UserHandle;
import android.service.controls.Control;
import android.util.ArrayMap;
import android.util.Log;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.controls.ControlStatus;
import com.android.systemui.controls.controller.ControlsBindingController;
import com.android.systemui.controls.controller.ControlsBindingControllerImpl;
import com.android.systemui.controls.controller.ControlsBindingControllerImpl.LoadSubscriber;
import com.android.systemui.controls.controller.ControlsProviderLifecycleManager;
import com.android.systemui.controls.management.ControlsFavoritingActivity$loadControls$1$1;
import com.android.systemui.controls.management.ControlsFavoritingActivity$loadControls$1$2;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.controls.management.ControlsListingControllerImpl;
import com.android.systemui.controls.panels.AuthorizedPanelsRepositoryImpl;
import com.android.systemui.controls.panels.SelectedComponentRepositoryImpl;
import com.android.systemui.controls.ui.ControlsUiController;
import com.android.systemui.controls.ui.ControlsUiControllerImpl;
import com.android.systemui.controls.ui.SelectedItem;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.DeviceControlsControllerImpl$seedFavorites$2;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsControllerImpl implements Dumpable, ControlsController {
    public final AuthorizedPanelsRepositoryImpl authorizedPanelsRepository;
    public final AuxiliaryPersistenceWrapper auxiliaryPersistenceWrapper;
    public final ControlsBindingController bindingController;
    public final Context context;
    public UserHandle currentUser;
    public final DelayableExecutor executor;
    public final ControlsListingController listingController;
    public final ControlsFavoritePersistenceWrapper persistenceWrapper;
    public final ControlsControllerImpl$restoreFinishedReceiver$1 restoreFinishedReceiver;
    public final List seedingCallbacks = new ArrayList();
    public boolean seedingInProgress;
    public final SelectedComponentRepositoryImpl selectedComponentRepository;
    public final ControlsUiController uiController;
    public boolean userChanging;
    public final UserFileManager userFileManager;
    public UserStructure userStructure;
    public final UserTracker userTracker;

    public ControlsControllerImpl(Context context, DelayableExecutor delayableExecutor, ControlsUiController controlsUiController, SelectedComponentRepositoryImpl selectedComponentRepositoryImpl, ControlsBindingController controlsBindingController, ControlsListingController controlsListingController, UserFileManager userFileManager, UserTracker userTracker, AuthorizedPanelsRepositoryImpl authorizedPanelsRepositoryImpl, Optional optional, DumpManager dumpManager) {
        this.context = context;
        this.executor = delayableExecutor;
        this.uiController = controlsUiController;
        this.selectedComponentRepository = selectedComponentRepositoryImpl;
        this.bindingController = controlsBindingController;
        this.listingController = controlsListingController;
        this.userFileManager = userFileManager;
        this.userTracker = userTracker;
        this.authorizedPanelsRepository = authorizedPanelsRepositoryImpl;
        this.userChanging = true;
        UserHandle userHandle = ((UserTrackerImpl) userTracker).getUserHandle();
        this.currentUser = userHandle;
        this.userStructure = new UserStructure(context, userHandle, userFileManager);
        this.persistenceWrapper = (ControlsFavoritePersistenceWrapper) optional.orElseGet(new Supplier() { // from class: com.android.systemui.controls.controller.ControlsControllerImpl.1
            @Override // java.util.function.Supplier
            public final Object get() {
                ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
                return new ControlsFavoritePersistenceWrapper(controlsControllerImpl.userStructure.file, controlsControllerImpl.executor, new BackupManager(ControlsControllerImpl.this.userStructure.userContext));
            }
        });
        this.auxiliaryPersistenceWrapper = new AuxiliaryPersistenceWrapper(new ControlsFavoritePersistenceWrapper(this.userStructure.auxiliaryFile, delayableExecutor, null));
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.controls.controller.ControlsControllerImpl$restoreFinishedReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                if (intent.getIntExtra("android.intent.extra.USER_ID", -10000) == ControlsControllerImpl.this.currentUser.getIdentifier()) {
                    final ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
                    ((ExecutorImpl) controlsControllerImpl.executor).execute(new Runnable() { // from class: com.android.systemui.controls.controller.ControlsControllerImpl$restoreFinishedReceiver$1$onReceive$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Log.d("ControlsControllerImpl", "Restore finished, storing auxiliary favorites");
                            ControlsControllerImpl.this.auxiliaryPersistenceWrapper.initialize();
                            ControlsControllerImpl controlsControllerImpl2 = ControlsControllerImpl.this;
                            controlsControllerImpl2.persistenceWrapper.storeFavorites(controlsControllerImpl2.auxiliaryPersistenceWrapper.favorites);
                            ControlsControllerImpl.this.resetFavorites();
                        }
                    });
                }
            }
        };
        new ContentObserver() { // from class: com.android.systemui.controls.controller.ControlsControllerImpl$settingObserver$1
            {
                super(null);
            }

            public final void onChange(boolean z, Collection collection, int i, int i2) {
                ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
                if (controlsControllerImpl.userChanging || i2 != controlsControllerImpl.currentUser.getIdentifier()) {
                    return;
                }
                ControlsControllerImpl.this.resetFavorites();
            }
        };
        ControlsListingController.ControlsListingCallback controlsListingCallback = new ControlsListingController.ControlsListingCallback() { // from class: com.android.systemui.controls.controller.ControlsControllerImpl$listingCallback$1
            @Override // com.android.systemui.controls.management.ControlsListingController.ControlsListingCallback
            public final void onServicesUpdated(List list) {
                ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
                ((ExecutorImpl) controlsControllerImpl.executor).execute(new ControlsControllerImpl$removeFavorites$1(list, controlsControllerImpl, 2));
            }
        };
        dumpManager.registerDumpable(this);
        resetFavorites();
        this.userChanging = false;
        context.registerReceiver(broadcastReceiver, new IntentFilter("com.android.systemui.backup.RESTORE_FINISHED"), "com.android.systemui.permission.SELF", null, 4);
        ((ControlsListingControllerImpl) controlsListingController).addCallback(controlsListingCallback);
    }

    public final boolean confirmAvailability() {
        if (!this.userChanging) {
            return true;
        }
        Log.w("ControlsControllerImpl", "Controls not available while user is changing");
        return false;
    }

    public final ControlStatus createRemovedStatus(ComponentName componentName, ControlInfo controlInfo, CharSequence charSequence, boolean z) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(componentName.getPackageName());
        return new ControlStatus(new Control.StatelessBuilder(controlInfo.controlId, PendingIntent.getActivity(this.context, componentName.hashCode(), intent, 67108864)).setTitle(controlInfo.controlTitle).setSubtitle(controlInfo.controlSubtitle).setStructure(charSequence).setDeviceType(controlInfo.deviceType).build(), componentName, true, z);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("ControlsController state:");
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  Changing users: ", this.userChanging, printWriter);
        printWriter.println("  Current user: " + this.currentUser.getIdentifier());
        printWriter.println("  Favorites:");
        for (StructureInfo structureInfo : Favorites.getAllStructures()) {
            printWriter.println("    " + structureInfo);
            Iterator it = structureInfo.controls.iterator();
            while (it.hasNext()) {
                printWriter.println("      " + ((ControlInfo) it.next()));
            }
        }
        printWriter.println(this.bindingController.toString());
    }

    public final SelectedItem getPreferredSelection() {
        return ((ControlsUiControllerImpl) this.uiController).getPreferredSelectedItem(Favorites.getAllStructures());
    }

    public final void loadForComponent(final ComponentName componentName, final ControlsFavoritingActivity$loadControls$1$1 controlsFavoritingActivity$loadControls$1$1, ControlsFavoritingActivity$loadControls$1$2 controlsFavoritingActivity$loadControls$1$2) {
        if (!confirmAvailability()) {
            if (this.userChanging) {
                ((ExecutorImpl) this.executor).executeDelayed(new ControlsControllerImpl$addFavorite$1(this, componentName, controlsFavoritingActivity$loadControls$1$1, controlsFavoritingActivity$loadControls$1$2), 500L, TimeUnit.MILLISECONDS);
            }
            EmptyList emptyList = EmptyList.INSTANCE;
            controlsFavoritingActivity$loadControls$1$1.accept(new ControlsControllerKt$createLoadDataObject$1(emptyList, emptyList, true));
        }
        ControlsBindingController.LoadCallback loadCallback = new ControlsBindingController.LoadCallback() { // from class: com.android.systemui.controls.controller.ControlsControllerImpl$loadForComponent$2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
                DelayableExecutor delayableExecutor = controlsControllerImpl.executor;
                ExecutorImpl executorImpl = (ExecutorImpl) delayableExecutor;
                executorImpl.execute(new ControlsControllerImpl$addFavorite$1(componentName, (List) obj, controlsControllerImpl, controlsFavoritingActivity$loadControls$1$1));
            }

            @Override // com.android.systemui.controls.controller.ControlsBindingController.LoadCallback
            public final void error(String str) {
                ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
                ((ExecutorImpl) controlsControllerImpl.executor).execute(new ControlsControllerImpl$refreshStatus$1(componentName, controlsFavoritingActivity$loadControls$1$1, controlsControllerImpl, 1));
            }
        };
        ControlsBindingControllerImpl controlsBindingControllerImpl = (ControlsBindingControllerImpl) this.bindingController;
        ControlsBindingControllerImpl.LoadSubscriber loadSubscriber = controlsBindingControllerImpl.loadSubscriber;
        final ControlsBindingControllerImpl.LoadSubscriber loadSubscriber2 = controlsBindingControllerImpl.new LoadSubscriber(loadCallback, 100000L);
        controlsBindingControllerImpl.loadSubscriber = loadSubscriber2;
        ControlsProviderLifecycleManager retrieveLifecycleManager = controlsBindingControllerImpl.retrieveLifecycleManager(componentName);
        retrieveLifecycleManager.getClass();
        retrieveLifecycleManager.onLoadCanceller = ((ExecutorImpl) retrieveLifecycleManager.executor).executeDelayed(new ControlsProviderLifecycleManager$maybeBindAndLoad$1(retrieveLifecycleManager, loadSubscriber2, 0), 20L, TimeUnit.SECONDS);
        retrieveLifecycleManager.invokeOrQueue(new ControlsProviderLifecycleManager.Load(retrieveLifecycleManager, loadSubscriber2, 0));
        controlsFavoritingActivity$loadControls$1$2.this$0.cancelLoadRunnable = new Runnable() { // from class: com.android.systemui.controls.controller.ControlsBindingControllerImpl$LoadSubscriber$loadCancel$1
            /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
            @Override // java.lang.Runnable
            public final void run() {
                ?? r0 = ControlsBindingControllerImpl.LoadSubscriber.this._loadCancelInternal;
                if (r0 != 0) {
                    Log.d("ControlsBindingControllerImpl", "Canceling loadSubscribtion");
                    r0.invoke();
                }
                ControlsBindingControllerImpl.LoadSubscriber.this.callback.error("Load cancelled");
            }
        };
    }

    public final void resetFavorites() {
        Map map = Favorites.favMap;
        Favorites.favMap = MapsKt.emptyMap();
        List readFavorites = this.persistenceWrapper.readFavorites();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : readFavorites) {
            ComponentName componentName = ((StructureInfo) obj).componentName;
            Object obj2 = linkedHashMap.get(componentName);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(componentName, obj2);
            }
            ((List) obj2).add(obj);
        }
        Favorites.favMap = linkedHashMap;
        List allStructures = Favorites.getAllStructures();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(allStructures, 10));
        Iterator it = allStructures.iterator();
        while (it.hasNext()) {
            arrayList.add(((StructureInfo) it.next()).componentName.getPackageName());
        }
        this.authorizedPanelsRepository.addAuthorizedPanels(CollectionsKt.toSet(arrayList));
    }

    public final void seedFavoritesForComponents(List list, DeviceControlsControllerImpl$seedFavorites$2 deviceControlsControllerImpl$seedFavorites$2) {
        if (this.seedingInProgress || list.isEmpty()) {
            return;
        }
        if (confirmAvailability()) {
            this.seedingInProgress = true;
            startSeeding(list, deviceControlsControllerImpl$seedFavorites$2, false);
        } else if (this.userChanging) {
            ((ExecutorImpl) this.executor).executeDelayed(new ControlsControllerImpl$refreshStatus$1(this, list, deviceControlsControllerImpl$seedFavorites$2), 500L, TimeUnit.MILLISECONDS);
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                deviceControlsControllerImpl$seedFavorites$2.accept(new SeedResponse(((ComponentName) it.next()).getPackageName(), false));
            }
        }
    }

    public final void setPreferredSelection(SelectedItem.PanelItem panelItem) {
        String obj = panelItem.name.toString();
        ComponentName componentName = panelItem.componentName;
        SelectedComponentRepositoryImpl selectedComponentRepositoryImpl = this.selectedComponentRepository;
        selectedComponentRepositoryImpl.getSharedPreferencesForUser(((UserTrackerImpl) selectedComponentRepositoryImpl.userTracker).getUserId()).edit().putString("controls_component", componentName != null ? componentName.flattenToString() : null).putString("controls_structure", obj).putBoolean("controls_is_panel", true).apply();
    }

    public final void startSeeding(List list, final DeviceControlsControllerImpl$seedFavorites$2 deviceControlsControllerImpl$seedFavorites$2, final boolean z) {
        if (list.isEmpty()) {
            boolean z2 = !z;
            this.seedingInProgress = false;
            Iterator it = this.seedingCallbacks.iterator();
            while (it.hasNext()) {
                ((Consumer) it.next()).accept(Boolean.valueOf(z2));
            }
            this.seedingCallbacks.clear();
            return;
        }
        final ComponentName componentName = (ComponentName) list.get(0);
        Log.d("ControlsControllerImpl", "Beginning request to seed favorites for: " + componentName);
        final List drop = CollectionsKt.drop(list, 1);
        ControlsBindingController.LoadCallback loadCallback = new ControlsBindingController.LoadCallback() { // from class: com.android.systemui.controls.controller.ControlsControllerImpl$startSeeding$1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                final List list2 = (List) obj;
                final ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
                DelayableExecutor delayableExecutor = controlsControllerImpl.executor;
                final DeviceControlsControllerImpl$seedFavorites$2 deviceControlsControllerImpl$seedFavorites$22 = deviceControlsControllerImpl$seedFavorites$2;
                final ComponentName componentName2 = componentName;
                final List list3 = drop;
                final boolean z3 = z;
                ((ExecutorImpl) delayableExecutor).execute(new Runnable() { // from class: com.android.systemui.controls.controller.ControlsControllerImpl$startSeeding$1$accept$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ArrayMap arrayMap = new ArrayMap();
                        for (Control control : list2) {
                            CharSequence structure = control.getStructure();
                            if (structure == null) {
                                structure = "";
                            }
                            List list4 = (List) arrayMap.get(structure);
                            if (list4 == null) {
                                list4 = new ArrayList();
                            }
                            if (list4.size() < 6) {
                                list4.add(new ControlInfo(control.getControlId(), control.getTitle(), control.getSubtitle(), control.getDeviceType()));
                                arrayMap.put(structure, list4);
                            }
                        }
                        ComponentName componentName3 = componentName2;
                        for (Map.Entry entry : arrayMap.entrySet()) {
                            CharSequence charSequence = (CharSequence) entry.getKey();
                            List list5 = (List) entry.getValue();
                            Map map = Favorites.favMap;
                            Intrinsics.checkNotNull(charSequence);
                            Intrinsics.checkNotNull(list5);
                            Favorites.replaceControls(new StructureInfo(componentName3, charSequence, list5));
                        }
                        controlsControllerImpl.persistenceWrapper.storeFavorites(Favorites.getAllStructures());
                        deviceControlsControllerImpl$seedFavorites$22.accept(new SeedResponse(componentName2.getPackageName(), true));
                        controlsControllerImpl.startSeeding(list3, deviceControlsControllerImpl$seedFavorites$22, z3);
                    }
                });
            }

            @Override // com.android.systemui.controls.controller.ControlsBindingController.LoadCallback
            public final void error(String str) {
                Log.e("ControlsControllerImpl", "Unable to seed favorites: ".concat(str));
                ControlsControllerImpl controlsControllerImpl = ControlsControllerImpl.this;
                ((ExecutorImpl) controlsControllerImpl.executor).execute(new ControlsControllerImpl$addFavorite$1(deviceControlsControllerImpl$seedFavorites$2, componentName, controlsControllerImpl, drop));
            }
        };
        ControlsBindingControllerImpl controlsBindingControllerImpl = (ControlsBindingControllerImpl) this.bindingController;
        ControlsBindingControllerImpl.LoadSubscriber loadSubscriber = controlsBindingControllerImpl.loadSubscriber;
        ControlsBindingControllerImpl.LoadSubscriber loadSubscriber2 = controlsBindingControllerImpl.new LoadSubscriber(loadCallback, 36L);
        controlsBindingControllerImpl.loadSubscriber = loadSubscriber2;
        ControlsProviderLifecycleManager retrieveLifecycleManager = controlsBindingControllerImpl.retrieveLifecycleManager(componentName);
        retrieveLifecycleManager.getClass();
        retrieveLifecycleManager.onLoadCanceller = ((ExecutorImpl) retrieveLifecycleManager.executor).executeDelayed(new ControlsProviderLifecycleManager$maybeBindAndLoad$1(retrieveLifecycleManager, loadSubscriber2, 1), 20L, TimeUnit.SECONDS);
        retrieveLifecycleManager.invokeOrQueue(new ControlsProviderLifecycleManager.Load(retrieveLifecycleManager, loadSubscriber2, 1));
    }

    public static /* synthetic */ void getAuxiliaryPersistenceWrapper$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }

    public static /* synthetic */ void getRestoreFinishedReceiver$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }

    public static /* synthetic */ void getSettingObserver$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }
}
