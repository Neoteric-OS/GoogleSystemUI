package com.android.systemui.controls.start;

import android.app.backup.BackupManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import com.android.systemui.CoreStartable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.common.data.repository.PackageChangeRepositoryImpl;
import com.android.systemui.common.domain.interactor.PackageChangeInteractor;
import com.android.systemui.common.domain.interactor.PackageChangeInteractor$packageChanged$$inlined$flatMapLatest$1;
import com.android.systemui.common.domain.interactor.PackageChangeInteractor$packageChangedInternal$$inlined$filter$1;
import com.android.systemui.controls.ControlsServiceInfo;
import com.android.systemui.controls.controller.AuxiliaryPersistenceWrapper;
import com.android.systemui.controls.controller.ControlsBindingControllerImpl;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.controller.ControlsFavoritePersistenceWrapper;
import com.android.systemui.controls.controller.ControlsProviderLifecycleManager;
import com.android.systemui.controls.controller.ControlsProviderLifecycleManager$bindService$1;
import com.android.systemui.controls.controller.UserStructure;
import com.android.systemui.controls.dagger.ControlsComponent;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.controls.management.ControlsListingControllerImpl;
import com.android.systemui.controls.management.ControlsListingControllerImpl$changeUser$1;
import com.android.systemui.controls.panels.AuthorizedPanelsRepositoryImpl;
import com.android.systemui.controls.panels.SelectedComponentRepositoryImpl;
import com.android.systemui.controls.ui.SelectedItem;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsStartable implements CoreStartable {
    public final AuthorizedPanelsRepositoryImpl authorizedPanelsRepository;
    public final CoroutineDispatcher bgDispatcher;
    public final BroadcastDispatcher broadcastDispatcher;
    public final ControlsComponent controlsComponent;
    public final Executor executor;
    public final PackageChangeInteractor packageChangeInteractor;
    public StandaloneCoroutine packageJob;
    public final CoroutineScope scope;
    public final SelectedComponentRepositoryImpl selectedComponentRepository;
    public final UserManager userManager;
    public final UserTracker userTracker;
    public final ControlsStartable$userTrackerCallback$1 userTrackerCallback = new UserTracker.Callback() { // from class: com.android.systemui.controls.start.ControlsStartable$userTrackerCallback$1
        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            ControlsStartable controlsStartable = ControlsStartable.this;
            ControlsController controlsController = (ControlsController) controlsStartable.controlsComponent.controlsController.get();
            UserHandle of = UserHandle.of(i);
            ControlsControllerImpl controlsControllerImpl = (ControlsControllerImpl) controlsController;
            controlsControllerImpl.userChanging = true;
            if (Intrinsics.areEqual(controlsControllerImpl.currentUser, of)) {
                controlsControllerImpl.userChanging = false;
            } else {
                Log.d("ControlsControllerImpl", "Changing to user: " + of);
                controlsControllerImpl.currentUser = of;
                UserStructure userStructure = new UserStructure(controlsControllerImpl.context, of, controlsControllerImpl.userFileManager);
                controlsControllerImpl.userStructure = userStructure;
                File file = userStructure.file;
                BackupManager backupManager = new BackupManager(controlsControllerImpl.userStructure.userContext);
                ControlsFavoritePersistenceWrapper controlsFavoritePersistenceWrapper = controlsControllerImpl.persistenceWrapper;
                controlsFavoritePersistenceWrapper.file = file;
                controlsFavoritePersistenceWrapper.backupManager = backupManager;
                File file2 = controlsControllerImpl.userStructure.auxiliaryFile;
                AuxiliaryPersistenceWrapper auxiliaryPersistenceWrapper = controlsControllerImpl.auxiliaryPersistenceWrapper;
                ControlsFavoritePersistenceWrapper controlsFavoritePersistenceWrapper2 = auxiliaryPersistenceWrapper.persistenceWrapper;
                controlsFavoritePersistenceWrapper2.file = file2;
                controlsFavoritePersistenceWrapper2.backupManager = null;
                auxiliaryPersistenceWrapper.initialize();
                controlsControllerImpl.resetFavorites();
                ControlsBindingControllerImpl controlsBindingControllerImpl = (ControlsBindingControllerImpl) controlsControllerImpl.bindingController;
                if (!of.equals(controlsBindingControllerImpl.currentUser)) {
                    controlsBindingControllerImpl.unbind();
                    controlsBindingControllerImpl.currentUser = of;
                }
                ControlsListingControllerImpl controlsListingControllerImpl = (ControlsListingControllerImpl) controlsControllerImpl.listingController;
                controlsListingControllerImpl.userChangeInProgress.incrementAndGet();
                controlsListingControllerImpl.serviceListing.setListening(false);
                controlsListingControllerImpl.backgroundExecutor.execute(new ControlsListingControllerImpl$changeUser$1(controlsListingControllerImpl, of, 0));
                controlsControllerImpl.userChanging = false;
            }
            ControlsStartable.access$startForUser(controlsStartable);
        }
    };

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.controls.start.ControlsStartable$userTrackerCallback$1] */
    public ControlsStartable(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Executor executor, ControlsComponent controlsComponent, UserTracker userTracker, AuthorizedPanelsRepositoryImpl authorizedPanelsRepositoryImpl, SelectedComponentRepositoryImpl selectedComponentRepositoryImpl, PackageChangeInteractor packageChangeInteractor, UserManager userManager, BroadcastDispatcher broadcastDispatcher) {
        this.scope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        this.executor = executor;
        this.controlsComponent = controlsComponent;
        this.userTracker = userTracker;
        this.authorizedPanelsRepository = authorizedPanelsRepositoryImpl;
        this.selectedComponentRepository = selectedComponentRepositoryImpl;
        this.packageChangeInteractor = packageChangeInteractor;
        this.userManager = userManager;
        this.broadcastDispatcher = broadcastDispatcher;
    }

    public static final void access$startForUser(final ControlsStartable controlsStartable) {
        ControlsServiceInfo controlsServiceInfo;
        Object obj;
        ControlsListingControllerImpl controlsListingControllerImpl = (ControlsListingControllerImpl) ((ControlsListingController) controlsStartable.controlsComponent.controlsListingController.get());
        PackageManager packageManager = controlsListingControllerImpl.context.getPackageManager();
        Intent intent = new Intent("android.service.controls.ControlsProviderService");
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) controlsListingControllerImpl.userTracker;
        List queryIntentServicesAsUser = packageManager.queryIntentServicesAsUser(intent, PackageManager.ResolveInfoFlags.of(786564), userTrackerImpl.getUserHandle());
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(queryIntentServicesAsUser, 10));
        Iterator it = queryIntentServicesAsUser.iterator();
        while (it.hasNext()) {
            arrayList.add(new ControlsServiceInfo(userTrackerImpl.getUserContext(), ((ResolveInfo) it.next()).serviceInfo));
        }
        controlsListingControllerImpl.updateServices(arrayList);
        SelectedComponentRepositoryImpl selectedComponentRepositoryImpl = controlsStartable.selectedComponentRepository;
        if (selectedComponentRepositoryImpl.getSharedPreferencesForUser(((UserTrackerImpl) selectedComponentRepositoryImpl.userTracker).getUserId()).getBoolean("should_add_default_panel", true)) {
            ControlsComponent controlsComponent = controlsStartable.controlsComponent;
            if (Intrinsics.areEqual(((ControlsControllerImpl) ((ControlsController) controlsComponent.controlsController.get())).getPreferredSelection(), SelectedItem.EMPTY_SELECTION)) {
                List currentServices = ((ControlsListingControllerImpl) ((ControlsListingController) controlsComponent.controlsListingController.get())).getCurrentServices();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj2 : currentServices) {
                    if (((ControlsServiceInfo) obj2).panelActivity != null) {
                        arrayList2.add(obj2);
                    }
                }
                Iterator it2 = ArraysKt.toSet(controlsStartable.authorizedPanelsRepository.context.getResources().getStringArray(R.array.config_controlsPreferredPackages)).iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        controlsServiceInfo = null;
                        break;
                    }
                    String str = (String) it2.next();
                    Iterator it3 = arrayList2.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            obj = it3.next();
                            if (Intrinsics.areEqual(((ControlsServiceInfo) obj).componentName.getPackageName(), str)) {
                                break;
                            }
                        } else {
                            obj = null;
                            break;
                        }
                    }
                    controlsServiceInfo = (ControlsServiceInfo) obj;
                    if (controlsServiceInfo != null) {
                        break;
                    }
                }
                if (controlsServiceInfo != null) {
                    ((ControlsControllerImpl) ((ControlsController) controlsComponent.controlsController.get())).setPreferredSelection(new SelectedItem.PanelItem(controlsServiceInfo.componentName, controlsServiceInfo.loadLabel()));
                }
            }
        }
        UserManager userManager = controlsStartable.userManager;
        UserTrackerImpl userTrackerImpl2 = (UserTrackerImpl) controlsStartable.userTracker;
        if (userManager.isUserUnlocked(userTrackerImpl2.getUserId())) {
            controlsStartable.bindToPanelInternal();
        } else {
            BroadcastDispatcher.registerReceiver$default(controlsStartable.broadcastDispatcher, new BroadcastReceiver() { // from class: com.android.systemui.controls.start.ControlsStartable$bindToPanel$1
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent2) {
                    ControlsStartable controlsStartable2 = ControlsStartable.this;
                    if (controlsStartable2.userManager.isUserUnlocked(((UserTrackerImpl) controlsStartable2.userTracker).getUserId())) {
                        ControlsStartable.this.bindToPanelInternal();
                        ControlsStartable.this.broadcastDispatcher.unregisterReceiver(this);
                    }
                }
            }, new IntentFilter("android.intent.action.USER_UNLOCKED"), controlsStartable.executor, userTrackerImpl2.getUserHandle(), 0, 48);
        }
        StandaloneCoroutine standaloneCoroutine = controlsStartable.packageJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        UserHandle userHandle = userTrackerImpl2.getUserHandle();
        PackageChangeInteractor packageChangeInteractor = controlsStartable.packageChangeInteractor;
        packageChangeInteractor.getClass();
        final ControlsStartable$monitorPackageUninstall$$inlined$filterIsInstance$1 controlsStartable$monitorPackageUninstall$$inlined$filterIsInstance$1 = new ControlsStartable$monitorPackageUninstall$$inlined$filterIsInstance$1(userHandle.equals(UserHandle.CURRENT) ? FlowKt.transformLatest(packageChangeInteractor.userInteractor.selectedUser, new PackageChangeInteractor$packageChanged$$inlined$flatMapLatest$1(null, packageChangeInteractor, null)) : new PackageChangeInteractor$packageChangedInternal$$inlined$filter$1(((PackageChangeRepositoryImpl) packageChangeInteractor.packageChangeRepository).packageChanged(userHandle), null));
        controlsStartable.packageJob = FlowKt.launchIn(FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.controls.start.ControlsStartable$monitorPackageUninstall$$inlined$filter$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.controls.start.ControlsStartable$monitorPackageUninstall$$inlined$filter$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ControlsStartable this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.controls.start.ControlsStartable$monitorPackageUninstall$$inlined$filter$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, ControlsStartable controlsStartable) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = controlsStartable;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.controls.start.ControlsStartable$monitorPackageUninstall$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.controls.start.ControlsStartable$monitorPackageUninstall$$inlined$filter$1$2$1 r0 = (com.android.systemui.controls.start.ControlsStartable$monitorPackageUninstall$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.controls.start.ControlsStartable$monitorPackageUninstall$$inlined$filter$1$2$1 r0 = new com.android.systemui.controls.start.ControlsStartable$monitorPackageUninstall$$inlined$filter$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L5c
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        r6 = r5
                        com.android.systemui.common.shared.model.PackageChangeModel$Uninstalled r6 = (com.android.systemui.common.shared.model.PackageChangeModel.Uninstalled) r6
                        com.android.systemui.controls.start.ControlsStartable r2 = r4.this$0
                        com.android.systemui.controls.panels.SelectedComponentRepositoryImpl r2 = r2.selectedComponentRepository
                        com.android.systemui.controls.panels.SelectedComponentRepository$SelectedComponent r2 = com.android.systemui.controls.panels.SelectedComponentRepositoryImpl.getSelectedComponent$default(r2)
                        if (r2 == 0) goto L48
                        android.content.ComponentName r2 = r2.componentName
                        if (r2 == 0) goto L48
                        java.lang.String r2 = r2.getPackageName()
                        goto L49
                    L48:
                        r2 = 0
                    L49:
                        java.lang.String r6 = r6.packageName
                        boolean r6 = r6.equals(r2)
                        if (r6 == 0) goto L5c
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L5c
                        return r1
                    L5c:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.controls.start.ControlsStartable$monitorPackageUninstall$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ControlsStartable$monitorPackageUninstall$$inlined$filterIsInstance$1.this.collect(new AnonymousClass2(flowCollector, controlsStartable), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, new ControlsStartable$monitorPackageUninstall$2(controlsStartable, null), 0), controlsStartable.bgDispatcher), controlsStartable.scope);
    }

    public final void bindToPanelInternal() {
        Object obj;
        ControlsComponent controlsComponent = this.controlsComponent;
        SelectedItem preferredSelection = ((ControlsControllerImpl) ((ControlsController) controlsComponent.controlsController.get())).getPreferredSelection();
        List currentServices = ((ControlsListingControllerImpl) ((ControlsListingController) controlsComponent.controlsListingController.get())).getCurrentServices();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : currentServices) {
            if (((ControlsServiceInfo) obj2).panelActivity != null) {
                arrayList.add(obj2);
            }
        }
        if (preferredSelection instanceof SelectedItem.PanelItem) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    obj = it.next();
                    if (Intrinsics.areEqual(((ControlsServiceInfo) obj).componentName, ((SelectedItem.PanelItem) preferredSelection).componentName)) {
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            if (obj != null) {
                ControlsController controlsController = (ControlsController) controlsComponent.controlsController.get();
                ControlsProviderLifecycleManager retrieveLifecycleManager = ((ControlsBindingControllerImpl) ((ControlsControllerImpl) controlsController).bindingController).retrieveLifecycleManager(((SelectedItem.PanelItem) preferredSelection).componentName);
                retrieveLifecycleManager.getClass();
                ((ExecutorImpl) retrieveLifecycleManager.executor).execute(new ControlsProviderLifecycleManager$bindService$1(retrieveLifecycleManager, true, true));
            }
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void onBootCompleted() {
        if (this.controlsComponent.featureEnabled) {
            this.executor.execute(new Runnable() { // from class: com.android.systemui.controls.start.ControlsStartable$onBootCompleted$1
                @Override // java.lang.Runnable
                public final void run() {
                    ControlsStartable.access$startForUser(ControlsStartable.this);
                }
            });
            ((UserTrackerImpl) this.userTracker).addCallback(this.userTrackerCallback, this.executor);
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
