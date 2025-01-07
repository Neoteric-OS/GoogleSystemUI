package com.android.systemui.controls.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Trace;
import android.os.UserHandle;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TextView;
import com.android.systemui.Dumpable;
import com.android.systemui.controls.ControlsMetricsLoggerImpl;
import com.android.systemui.controls.ControlsServiceInfo;
import com.android.systemui.controls.CustomIconCache;
import com.android.systemui.controls.controller.ControlInfo;
import com.android.systemui.controls.controller.ControlsBindingControllerImpl;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.controller.ControlsControllerImpl$removeFavorites$1;
import com.android.systemui.controls.controller.ControlsProviderLifecycleManager;
import com.android.systemui.controls.controller.ControlsProviderLifecycleManager$bindService$1;
import com.android.systemui.controls.controller.Favorites;
import com.android.systemui.controls.controller.StatefulControlSubscriber;
import com.android.systemui.controls.controller.StructureInfo;
import com.android.systemui.controls.management.ControlsEditingActivity;
import com.android.systemui.controls.management.ControlsFavoritingActivity;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.controls.management.ControlsListingControllerImpl;
import com.android.systemui.controls.management.ControlsProviderSelectorActivity;
import com.android.systemui.controls.panels.AuthorizedPanelsRepositoryImpl;
import com.android.systemui.controls.panels.SelectedComponentRepository$SelectedComponent;
import com.android.systemui.controls.panels.SelectedComponentRepositoryImpl;
import com.android.systemui.controls.settings.ControlsSettingsRepository;
import com.android.systemui.controls.settings.ControlsSettingsRepositoryImpl;
import com.android.systemui.controls.ui.OverflowMenuAdapter;
import com.android.systemui.controls.ui.RenderInfo;
import com.android.systemui.controls.ui.SelectedItem;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.io.PrintWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt;
import kotlin.collections.builders.ListBuilder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsUiControllerImpl implements ControlsUiController, Dumpable {
    public Context activityContext;
    public final ActivityStarter activityStarter;
    public List allStructures;
    public final AuthorizedPanelsRepositoryImpl authorizedPanelsRepository;
    public final DelayableExecutor bgExecutor;
    public final Context context;
    public final ControlActionCoordinatorImpl controlActionCoordinator;
    public final Lazy controlsController;
    public final Lazy controlsListingController;
    public final ControlsMetricsLoggerImpl controlsMetricsLogger;
    public final ControlsSettingsRepository controlsSettingsRepository;
    public final ControlsDialogsFactory dialogsFactory;
    public final CustomIconCache iconCache;
    public final KeyguardStateController keyguardStateController;
    public ControlsUiControllerImpl$createCallback$1 listingCallback;
    public final ControlsUiControllerImpl$special$$inlined$compareBy$1 localeComparator;
    public Runnable onDismiss;
    public final ControlsUiControllerImpl$onSeedingComplete$1 onSeedingComplete;
    public Intent openAppIntent;
    public OverflowMenuAdapter overflowMenuAdapter;
    public final PackageManager packageManager;
    public ViewGroup parent;
    public ControlsPopupMenu popup;
    public final ContextThemeWrapper popupThemedContext;
    public SystemUIDialog removeAppDialog;
    public boolean retainCache;
    public final SelectedComponentRepositoryImpl selectedComponentRepository;
    public SelectionItem selectionItem;
    public PanelTaskViewController taskViewController;
    public final Optional taskViewFactory;
    public final DelayableExecutor uiExecutor;
    public final UserTracker userTracker;
    public SelectedItem selectedItem = SelectedItem.EMPTY_SELECTION;
    public final Map controlsById = new LinkedHashMap();
    public final Map controlViewsById = new LinkedHashMap();
    public boolean hidden = true;
    public List lastSelections = EmptyList.INSTANCE;

    /* JADX WARN: Type inference failed for: r2v23, types: [com.android.systemui.controls.ui.ControlsUiControllerImpl$special$$inlined$compareBy$1] */
    public ControlsUiControllerImpl(Lazy lazy, Context context, PackageManager packageManager, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, Lazy lazy2, ControlActionCoordinatorImpl controlActionCoordinatorImpl, ActivityStarter activityStarter, CustomIconCache customIconCache, ControlsMetricsLoggerImpl controlsMetricsLoggerImpl, KeyguardStateController keyguardStateController, UserTracker userTracker, Optional optional, ControlsSettingsRepository controlsSettingsRepository, AuthorizedPanelsRepositoryImpl authorizedPanelsRepositoryImpl, SelectedComponentRepositoryImpl selectedComponentRepositoryImpl, ControlsDialogsFactory controlsDialogsFactory, DumpManager dumpManager) {
        this.controlsController = lazy;
        this.context = context;
        this.packageManager = packageManager;
        this.uiExecutor = delayableExecutor;
        this.bgExecutor = delayableExecutor2;
        this.controlsListingController = lazy2;
        this.controlActionCoordinator = controlActionCoordinatorImpl;
        this.activityStarter = activityStarter;
        this.iconCache = customIconCache;
        this.controlsMetricsLogger = controlsMetricsLoggerImpl;
        this.keyguardStateController = keyguardStateController;
        this.userTracker = userTracker;
        this.taskViewFactory = optional;
        this.controlsSettingsRepository = controlsSettingsRepository;
        this.authorizedPanelsRepository = authorizedPanelsRepositoryImpl;
        this.selectedComponentRepository = selectedComponentRepositoryImpl;
        this.dialogsFactory = controlsDialogsFactory;
        this.popupThemedContext = new ContextThemeWrapper(context, R.style.Control_ListPopupWindow);
        final Collator collator = Collator.getInstance(context.getResources().getConfiguration().getLocales().get(0));
        this.localeComparator = new Comparator() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$special$$inlined$compareBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return collator.compare(((SelectionItem) obj).getTitle(), ((SelectionItem) obj2).getTitle());
            }
        };
        this.onSeedingComplete = new ControlsUiControllerImpl$onSeedingComplete$1(this);
        dumpManager.registerDumpable(this);
    }

    public static void reload$default(final ControlsUiControllerImpl controlsUiControllerImpl, final ViewGroup viewGroup) {
        if (controlsUiControllerImpl.hidden) {
            return;
        }
        ControlsListingController controlsListingController = (ControlsListingController) controlsUiControllerImpl.controlsListingController.get();
        ControlsUiControllerImpl$createCallback$1 controlsUiControllerImpl$createCallback$1 = controlsUiControllerImpl.listingCallback;
        if (controlsUiControllerImpl$createCallback$1 == null) {
            controlsUiControllerImpl$createCallback$1 = null;
        }
        ((ControlsListingControllerImpl) controlsListingController).removeCallback(controlsUiControllerImpl$createCallback$1);
        ControlsControllerImpl controlsControllerImpl = (ControlsControllerImpl) ((ControlsController) controlsUiControllerImpl.controlsController.get());
        if (controlsControllerImpl.confirmAvailability()) {
            ((ControlsBindingControllerImpl) controlsControllerImpl.bindingController).unsubscribe();
        }
        PanelTaskViewController panelTaskViewController = controlsUiControllerImpl.taskViewController;
        if (panelTaskViewController != null) {
            panelTaskViewController.taskView.removeTask();
        }
        controlsUiControllerImpl.taskViewController = null;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(viewGroup, "alpha", 1.0f, 0.0f);
        ofFloat.setInterpolator(new AccelerateInterpolator(1.0f));
        ofFloat.setDuration(200L);
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$reload$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                ControlsUiControllerImpl.this.controlViewsById.clear();
                ControlsUiControllerImpl.this.controlsById.clear();
                ControlsUiControllerImpl controlsUiControllerImpl2 = ControlsUiControllerImpl.this;
                ViewGroup viewGroup2 = viewGroup;
                Runnable runnable = controlsUiControllerImpl2.onDismiss;
                if (runnable == null) {
                    runnable = null;
                }
                Context context = controlsUiControllerImpl2.activityContext;
                controlsUiControllerImpl2.show(viewGroup2, runnable, context != null ? context : null);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(viewGroup, "alpha", 0.0f, 1.0f);
                ofFloat2.setInterpolator(new DecelerateInterpolator(1.0f));
                ofFloat2.setDuration(200L);
                ofFloat2.start();
            }
        });
        ofFloat.start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v7, types: [android.view.ViewGroup$MarginLayoutParams] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v4, types: [android.view.LayoutInflater] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v5 */
    public final void createListView(SelectionItem selectionItem) {
        Object obj;
        ViewGroup viewGroup;
        int i;
        SelectedItem selectedItem = this.selectedItem;
        if (selectedItem instanceof SelectedItem.StructureItem) {
            SelectedItem.StructureItem structureItem = (SelectedItem.StructureItem) selectedItem;
            Context context = this.activityContext;
            if (context == null) {
                context = null;
            }
            LayoutInflater from = LayoutInflater.from(context);
            Context context2 = this.activityContext;
            if (context2 == null) {
                context2 = null;
            }
            Resources resources = context2.getResources();
            int integer = resources.getInteger(R.integer.controls_max_columns);
            int integer2 = resources.getInteger(R.integer.controls_max_columns_adjust_below_width_dp);
            TypedValue typedValue = new TypedValue();
            int i2 = 1;
            resources.getValue(R.dimen.controls_max_columns_adjust_above_font_scale, typedValue, true);
            float f = typedValue.getFloat();
            Configuration configuration = resources.getConfiguration();
            if (configuration.orientation == 1 && (i = configuration.screenWidthDp) != 0 && i <= integer2 && configuration.fontScale >= f) {
                integer--;
            }
            ViewGroup viewGroup2 = this.parent;
            ViewGroup viewGroup3 = (ViewGroup) (viewGroup2 != null ? viewGroup2 : null).requireViewById(R.id.controls_list);
            viewGroup3.removeAllViews();
            Intrinsics.checkNotNull(from);
            int i3 = R.layout.controls_row;
            ?? r6 = 0;
            ViewGroup viewGroup4 = (ViewGroup) from.inflate(R.layout.controls_row, viewGroup3, false);
            viewGroup3.addView(viewGroup4);
            StructureInfo structureInfo = structureItem.structure;
            Iterator it = structureInfo.controls.iterator();
            ?? r2 = from;
            while (it.hasNext()) {
                ControlKey controlKey = new ControlKey(structureInfo.componentName, ((ControlInfo) it.next()).controlId);
                ControlWithState controlWithState = (ControlWithState) this.controlsById.get(controlKey);
                if (controlWithState != null) {
                    if (viewGroup4.getChildCount() == integer) {
                        viewGroup4 = (ViewGroup) r2.inflate(i3, viewGroup3, r6);
                        viewGroup3.addView(viewGroup4);
                    }
                    ViewGroup viewGroup5 = (ViewGroup) r2.inflate(R.layout.controls_base_item, viewGroup4, r6);
                    viewGroup4.addView(viewGroup5);
                    if (viewGroup4.getChildCount() == i2) {
                        ((ViewGroup.MarginLayoutParams) viewGroup5.getLayoutParams()).setMarginStart(r6);
                    }
                    Lazy lazy = this.controlsController;
                    obj = r2;
                    viewGroup = viewGroup3;
                    ControlViewHolder controlViewHolder = new ControlViewHolder(viewGroup5, (ControlsController) lazy.get(), this.uiExecutor, this.bgExecutor, this.controlActionCoordinator, this.controlsMetricsLogger, selectionItem.uid, ((ControlsControllerImpl) ((ControlsController) lazy.get())).currentUser.getIdentifier());
                    controlViewHolder.bindData(controlWithState, false);
                    this.controlViewsById.put(controlKey, controlViewHolder);
                } else {
                    obj = r2;
                    viewGroup = viewGroup3;
                }
                r2 = obj;
                viewGroup3 = viewGroup;
                i3 = R.layout.controls_row;
                r6 = 0;
                i2 = 1;
            }
            int size = structureInfo.controls.size() % integer;
            int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.control_spacing);
            for (int i4 = size == 0 ? 0 : integer - size; i4 > 0; i4--) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 0, 1.0f);
                layoutParams.setMarginStart(dimensionPixelSize);
                viewGroup4.addView(new Space(this.context), layoutParams);
            }
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.println("ControlsUiControllerImpl:");
        asIndenting.increaseIndent();
        try {
            asIndenting.println("hidden: " + this.hidden);
            asIndenting.println("selectedItem: " + this.selectedItem);
            asIndenting.println("lastSelections: " + this.lastSelections);
            asIndenting.println("setting: " + ((StateFlowImpl) ((ControlsSettingsRepositoryImpl) this.controlsSettingsRepository).allowActionOnTrivialControlsInLockscreen.$$delegate_0).getValue());
        } finally {
            asIndenting.decreaseIndent();
        }
    }

    public final SelectedItem getPreferredSelectedItem(List list) {
        SelectedComponentRepository$SelectedComponent selectedComponent;
        ComponentName componentName;
        Object obj;
        selectedComponent = this.selectedComponentRepository.getSelectedComponent(UserHandle.CURRENT);
        if (selectedComponent == null || (componentName = selectedComponent.componentName) == null) {
            componentName = StructureInfo.EMPTY_COMPONENT;
        }
        if (selectedComponent != null && selectedComponent.isPanel) {
            return new SelectedItem.PanelItem(componentName, selectedComponent.name);
        }
        if (list.isEmpty()) {
            return SelectedItem.EMPTY_SELECTION;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            StructureInfo structureInfo = (StructureInfo) next;
            if (Intrinsics.areEqual(componentName, structureInfo.componentName)) {
                if (Intrinsics.areEqual(selectedComponent != null ? selectedComponent.name : null, structureInfo.structure)) {
                    obj = next;
                    break;
                }
            }
        }
        StructureInfo structureInfo2 = (StructureInfo) obj;
        if (structureInfo2 == null) {
            structureInfo2 = (StructureInfo) ((ArrayList) list).get(0);
        }
        return new SelectedItem.StructureItem(structureInfo2);
    }

    public final boolean maybeUpdateSelectedItem(SelectionItem selectionItem) {
        SelectedItem structureItem;
        if (selectionItem.isPanel) {
            structureItem = new SelectedItem.PanelItem(selectionItem.componentName, selectionItem.appName);
        } else {
            List list = this.allStructures;
            Object obj = null;
            if (list == null) {
                list = null;
            }
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                StructureInfo structureInfo = (StructureInfo) next;
                if (Intrinsics.areEqual(structureInfo.structure, selectionItem.structure) && Intrinsics.areEqual(structureInfo.componentName, selectionItem.componentName)) {
                    obj = next;
                    break;
                }
            }
            StructureInfo structureInfo2 = (StructureInfo) obj;
            if (structureInfo2 == null) {
                ComponentName componentName = StructureInfo.EMPTY_COMPONENT;
                structureInfo2 = StructureInfo.EMPTY_STRUCTURE;
            }
            structureItem = new SelectedItem.StructureItem(structureInfo2);
        }
        if (structureItem.equals(this.selectedItem)) {
            return false;
        }
        this.selectedItem = structureItem;
        updatePreferences(structureItem);
        return true;
    }

    public final Class resolveActivity() {
        boolean z;
        Lazy lazy = this.controlsController;
        ((ControlsControllerImpl) ((ControlsController) lazy.get())).getClass();
        List allStructures = Favorites.getAllStructures();
        SelectedItem preferredSelectedItem = getPreferredSelectedItem(allStructures);
        List currentServices = ((ControlsListingControllerImpl) ((ControlsListingController) this.controlsListingController.get())).getCurrentServices();
        boolean z2 = false;
        if (!currentServices.isEmpty()) {
            Iterator it = currentServices.iterator();
            while (it.hasNext()) {
                if (((ControlsServiceInfo) it.next()).panelActivity != null) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        ControlsControllerImpl controlsControllerImpl = (ControlsControllerImpl) ((ControlsController) lazy.get());
        if (controlsControllerImpl.seedingInProgress) {
            ((ExecutorImpl) controlsControllerImpl.executor).execute(new ControlsControllerImpl$removeFavorites$1(controlsControllerImpl, this.onSeedingComplete));
            z2 = true;
        }
        return (z2 || preferredSelectedItem.getHasControls() || ((ArrayList) allStructures).size() > 1 || z) ? ControlsActivity.class : ControlsProviderSelectorActivity.class;
    }

    /* JADX WARN: Type inference failed for: r12v11, types: [com.android.systemui.controls.ui.ControlsUiControllerImpl$createCallback$1] */
    /* JADX WARN: Type inference failed for: r12v16, types: [com.android.systemui.controls.ui.ControlsUiControllerImpl$createCallback$1] */
    /* JADX WARN: Type inference failed for: r12v26, types: [com.android.systemui.controls.ui.ControlsUiControllerImpl$createCallback$1] */
    public final void show(ViewGroup viewGroup, Runnable runnable, Context context) {
        StructureInfo structureInfo;
        Log.d("ControlsUiController", "show()");
        Trace.instant(4096L, "ControlsUiControllerImpl#show");
        this.parent = viewGroup;
        this.onDismiss = runnable;
        this.activityContext = context;
        this.openAppIntent = null;
        this.overflowMenuAdapter = null;
        this.hidden = false;
        this.retainCache = false;
        this.selectionItem = null;
        this.controlActionCoordinator.activityContext = context;
        Lazy lazy = this.controlsController;
        ((ControlsControllerImpl) ((ControlsController) lazy.get())).getClass();
        List allStructures = Favorites.getAllStructures();
        this.allStructures = allStructures;
        this.selectedItem = getPreferredSelectedItem(allStructures);
        ControlsControllerImpl controlsControllerImpl = (ControlsControllerImpl) ((ControlsController) lazy.get());
        if (controlsControllerImpl.seedingInProgress) {
            ((ExecutorImpl) controlsControllerImpl.executor).execute(new ControlsControllerImpl$removeFavorites$1(controlsControllerImpl, this.onSeedingComplete));
            final ControlsUiControllerImpl$show$1 controlsUiControllerImpl$show$1 = new ControlsUiControllerImpl$show$1(1, this, ControlsUiControllerImpl.class, "showSeedingView", "showSeedingView(Ljava/util/List;)V", 0);
            this.listingCallback = new ControlsListingController.ControlsListingCallback(controlsUiControllerImpl$show$1) { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$createCallback$1
                public final /* synthetic */ FunctionReferenceImpl $onResult;

                /* JADX WARN: Multi-variable type inference failed */
                {
                    this.$onResult = (FunctionReferenceImpl) controlsUiControllerImpl$show$1;
                }

                /* JADX WARN: Type inference failed for: r12v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.FunctionReferenceImpl] */
                @Override // com.android.systemui.controls.management.ControlsListingController.ControlsListingCallback
                public final void onServicesUpdated(List list) {
                    ControlsUiControllerImpl controlsUiControllerImpl = ControlsUiControllerImpl.this;
                    AuthorizedPanelsRepositoryImpl authorizedPanelsRepositoryImpl = controlsUiControllerImpl.authorizedPanelsRepository;
                    Set<String> stringSet = authorizedPanelsRepositoryImpl.instantiateSharedPrefs(((UserTrackerImpl) authorizedPanelsRepositoryImpl.userTracker).getUserHandle()).getStringSet("authorized_panels", EmptySet.INSTANCE);
                    Intrinsics.checkNotNull(stringSet);
                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        ControlsServiceInfo controlsServiceInfo = (ControlsServiceInfo) it.next();
                        int i = controlsServiceInfo.serviceInfo.applicationInfo.uid;
                        CharSequence loadLabel = controlsServiceInfo.loadLabel();
                        Drawable loadIcon = controlsServiceInfo.loadIcon();
                        ComponentName componentName = controlsServiceInfo.componentName;
                        arrayList.add(new SelectionItem(loadLabel, "", loadIcon, componentName, i, stringSet.contains(componentName.getPackageName()) ? controlsServiceInfo.panelActivity : null));
                    }
                    ((ExecutorImpl) controlsUiControllerImpl.uiExecutor).execute(new ControlsUiControllerImpl$createPanelView$1(controlsUiControllerImpl, arrayList, (Function1) this.$onResult));
                }
            };
        } else {
            SelectedItem selectedItem = this.selectedItem;
            if (!(selectedItem instanceof SelectedItem.PanelItem) && !selectedItem.getHasControls()) {
                List list = this.allStructures;
                if (list == null) {
                    list = null;
                }
                if (list.size() <= 1) {
                    final ControlsUiControllerImpl$show$2 controlsUiControllerImpl$show$2 = new ControlsUiControllerImpl$show$2(1, this, ControlsUiControllerImpl.class, "initialView", "initialView(Ljava/util/List;)V", 0);
                    this.listingCallback = new ControlsListingController.ControlsListingCallback(controlsUiControllerImpl$show$2) { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$createCallback$1
                        public final /* synthetic */ FunctionReferenceImpl $onResult;

                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            this.$onResult = (FunctionReferenceImpl) controlsUiControllerImpl$show$2;
                        }

                        /* JADX WARN: Type inference failed for: r12v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.FunctionReferenceImpl] */
                        @Override // com.android.systemui.controls.management.ControlsListingController.ControlsListingCallback
                        public final void onServicesUpdated(List list2) {
                            ControlsUiControllerImpl controlsUiControllerImpl = ControlsUiControllerImpl.this;
                            AuthorizedPanelsRepositoryImpl authorizedPanelsRepositoryImpl = controlsUiControllerImpl.authorizedPanelsRepository;
                            Set<String> stringSet = authorizedPanelsRepositoryImpl.instantiateSharedPrefs(((UserTrackerImpl) authorizedPanelsRepositoryImpl.userTracker).getUserHandle()).getStringSet("authorized_panels", EmptySet.INSTANCE);
                            Intrinsics.checkNotNull(stringSet);
                            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                            Iterator it = list2.iterator();
                            while (it.hasNext()) {
                                ControlsServiceInfo controlsServiceInfo = (ControlsServiceInfo) it.next();
                                int i = controlsServiceInfo.serviceInfo.applicationInfo.uid;
                                CharSequence loadLabel = controlsServiceInfo.loadLabel();
                                Drawable loadIcon = controlsServiceInfo.loadIcon();
                                ComponentName componentName = controlsServiceInfo.componentName;
                                arrayList.add(new SelectionItem(loadLabel, "", loadIcon, componentName, i, stringSet.contains(componentName.getPackageName()) ? controlsServiceInfo.panelActivity : null));
                            }
                            ((ExecutorImpl) controlsUiControllerImpl.uiExecutor).execute(new ControlsUiControllerImpl$createPanelView$1(controlsUiControllerImpl, arrayList, (Function1) this.$onResult));
                        }
                    };
                }
            }
            SelectedItem selectedItem2 = this.selectedItem;
            if (selectedItem2 instanceof SelectedItem.StructureItem) {
                SelectedItem.StructureItem structureItem = (SelectedItem.StructureItem) selectedItem2;
                List list2 = structureItem.structure.controls;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                Iterator it = list2.iterator();
                while (true) {
                    boolean hasNext = it.hasNext();
                    structureInfo = structureItem.structure;
                    if (!hasNext) {
                        break;
                    } else {
                        arrayList.add(new ControlWithState(structureInfo.componentName, (ControlInfo) it.next(), null));
                    }
                }
                Map map = this.controlsById;
                for (Object obj : arrayList) {
                    map.put(new ControlKey(structureInfo.componentName, ((ControlWithState) obj).ci.controlId), obj);
                }
                ControlsControllerImpl controlsControllerImpl2 = (ControlsControllerImpl) ((ControlsController) lazy.get());
                if (controlsControllerImpl2.confirmAvailability()) {
                    ControlsBindingControllerImpl controlsBindingControllerImpl = (ControlsBindingControllerImpl) controlsControllerImpl2.bindingController;
                    controlsBindingControllerImpl.unsubscribe();
                    ControlsProviderLifecycleManager retrieveLifecycleManager = controlsBindingControllerImpl.retrieveLifecycleManager(structureInfo.componentName);
                    StatefulControlSubscriber statefulControlSubscriber = new StatefulControlSubscriber((ControlsController) controlsBindingControllerImpl.lazyController.get(), retrieveLifecycleManager, controlsBindingControllerImpl.backgroundExecutor);
                    controlsBindingControllerImpl.statefulControlSubscriber = statefulControlSubscriber;
                    List list3 = structureInfo.controls;
                    ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
                    Iterator it2 = list3.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(((ControlInfo) it2.next()).controlId);
                    }
                    retrieveLifecycleManager.invokeOrQueue(new ControlsProviderLifecycleManager.Action(retrieveLifecycleManager, arrayList2, statefulControlSubscriber, 1));
                }
            } else {
                ControlsProviderLifecycleManager retrieveLifecycleManager2 = ((ControlsBindingControllerImpl) ((ControlsControllerImpl) ((ControlsController) lazy.get())).bindingController).retrieveLifecycleManager(selectedItem2.getComponentName());
                retrieveLifecycleManager2.getClass();
                ((ExecutorImpl) retrieveLifecycleManager2.executor).execute(new ControlsProviderLifecycleManager$bindService$1(retrieveLifecycleManager2, true, true));
            }
            final ControlsUiControllerImpl$show$5 controlsUiControllerImpl$show$5 = new ControlsUiControllerImpl$show$5(1, this, ControlsUiControllerImpl.class, "showControlsView", "showControlsView(Ljava/util/List;)V", 0);
            this.listingCallback = new ControlsListingController.ControlsListingCallback(controlsUiControllerImpl$show$5) { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$createCallback$1
                public final /* synthetic */ FunctionReferenceImpl $onResult;

                /* JADX WARN: Multi-variable type inference failed */
                {
                    this.$onResult = (FunctionReferenceImpl) controlsUiControllerImpl$show$5;
                }

                /* JADX WARN: Type inference failed for: r12v1, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.FunctionReferenceImpl] */
                @Override // com.android.systemui.controls.management.ControlsListingController.ControlsListingCallback
                public final void onServicesUpdated(List list22) {
                    ControlsUiControllerImpl controlsUiControllerImpl = ControlsUiControllerImpl.this;
                    AuthorizedPanelsRepositoryImpl authorizedPanelsRepositoryImpl = controlsUiControllerImpl.authorizedPanelsRepository;
                    Set<String> stringSet = authorizedPanelsRepositoryImpl.instantiateSharedPrefs(((UserTrackerImpl) authorizedPanelsRepositoryImpl.userTracker).getUserHandle()).getStringSet("authorized_panels", EmptySet.INSTANCE);
                    Intrinsics.checkNotNull(stringSet);
                    ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list22, 10));
                    Iterator it3 = list22.iterator();
                    while (it3.hasNext()) {
                        ControlsServiceInfo controlsServiceInfo = (ControlsServiceInfo) it3.next();
                        int i = controlsServiceInfo.serviceInfo.applicationInfo.uid;
                        CharSequence loadLabel = controlsServiceInfo.loadLabel();
                        Drawable loadIcon = controlsServiceInfo.loadIcon();
                        ComponentName componentName = controlsServiceInfo.componentName;
                        arrayList3.add(new SelectionItem(loadLabel, "", loadIcon, componentName, i, stringSet.contains(componentName.getPackageName()) ? controlsServiceInfo.panelActivity : null));
                    }
                    ((ExecutorImpl) controlsUiControllerImpl.uiExecutor).execute(new ControlsUiControllerImpl$createPanelView$1(controlsUiControllerImpl, arrayList3, (Function1) this.$onResult));
                }
            };
        }
        ControlsListingController controlsListingController = (ControlsListingController) this.controlsListingController.get();
        ControlsUiControllerImpl$createCallback$1 controlsUiControllerImpl$createCallback$1 = this.listingCallback;
        ControlsUiControllerImpl$createCallback$1 controlsUiControllerImpl$createCallback$12 = controlsUiControllerImpl$createCallback$1 != null ? controlsUiControllerImpl$createCallback$1 : null;
        ControlsListingControllerImpl controlsListingControllerImpl = (ControlsListingControllerImpl) controlsListingController;
        controlsListingControllerImpl.getClass();
        controlsListingControllerImpl.addCallback((ControlsListingController.ControlsListingCallback) controlsUiControllerImpl$createCallback$12);
    }

    public final void showControlsView(List list) {
        Object obj;
        StructureInfo structureInfo;
        this.controlViewsById.clear();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : list) {
            if (((SelectionItem) obj2).isPanel) {
                arrayList.add(obj2);
            } else {
                arrayList2.add(obj2);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list2 = (List) pair.component1();
        List list3 = (List) pair.component2();
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList3.add(((SelectionItem) it.next()).componentName);
        }
        Set set = CollectionsKt.toSet(arrayList3);
        int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10));
        if (mapCapacity < 16) {
            mapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        for (Object obj3 : list3) {
            linkedHashMap.put(((SelectionItem) obj3).componentName, obj3);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (!set.contains(entry.getKey())) {
                linkedHashMap2.put(entry.getKey(), entry.getValue());
            }
        }
        ArrayList<SelectionItem> arrayList4 = new ArrayList();
        List<StructureInfo> list4 = this.allStructures;
        if (list4 == null) {
            list4 = null;
        }
        for (StructureInfo structureInfo2 : list4) {
            SelectionItem selectionItem = (SelectionItem) linkedHashMap2.get(structureInfo2.componentName);
            SelectionItem selectionItem2 = selectionItem != null ? new SelectionItem(selectionItem.appName, structureInfo2.structure, selectionItem.icon, selectionItem.componentName, selectionItem.uid, selectionItem.panelComponentName) : null;
            if (selectionItem2 != null) {
                arrayList4.add(selectionItem2);
            }
        }
        arrayList4.addAll(list2);
        CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList4, this.localeComparator);
        this.lastSelections = arrayList4;
        SelectedItem selectedItem = this.selectedItem;
        Iterator it2 = arrayList4.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            SelectionItem selectionItem3 = (SelectionItem) obj;
            if (!Intrinsics.areEqual(selectionItem3.componentName, selectedItem.getComponentName()) ? false : (selectionItem3.isPanel || (selectedItem instanceof SelectedItem.PanelItem)) ? true : Intrinsics.areEqual(selectionItem3.structure, ((SelectedItem.StructureItem) selectedItem).structure.structure)) {
                break;
            }
        }
        final SelectionItem selectionItem4 = (SelectionItem) obj;
        if (selectionItem4 == null) {
            selectionItem4 = !list2.isEmpty() ? (SelectionItem) list2.get(0) : (SelectionItem) list.get(0);
        }
        maybeUpdateSelectedItem(selectionItem4);
        Context context = this.activityContext;
        if (context == null) {
            context = null;
        }
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup viewGroup = this.parent;
        if (viewGroup == null) {
            viewGroup = null;
        }
        from.inflate(R.layout.controls_with_favorites, viewGroup, true);
        ViewGroup viewGroup2 = this.parent;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        ImageView imageView = (ImageView) viewGroup2.requireViewById(R.id.controls_close);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$createControlsSpaceFrame$1$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Runnable runnable = ControlsUiControllerImpl.this.onDismiss;
                if (runnable == null) {
                    runnable = null;
                }
                runnable.run();
            }
        });
        imageView.setVisibility(0);
        boolean isPresent = this.taskViewFactory.isPresent();
        boolean z = selectionItem4.isPanel;
        if (isPresent && z) {
            ComponentName componentName = selectionItem4.panelComponentName;
            Intrinsics.checkNotNull(componentName);
            boolean booleanValue = ((Boolean) ((StateFlowImpl) ((ControlsSettingsRepositoryImpl) this.controlsSettingsRepository).allowActionOnTrivialControlsInLockscreen.$$delegate_0).getValue()).booleanValue();
            Context context2 = this.context;
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.putExtra("android.service.controls.extra.LOCKSCREEN_ALLOW_TRIVIAL_CONTROLS", booleanValue);
            intent.putExtra("android.service.controls.extra.CONTROLS_SURFACE", 0);
            PendingIntent activityAsUser = PendingIntent.getActivityAsUser(context2, 0, intent, 201326592, ActivityOptions.makeBasic().setPendingIntentCreatorBackgroundActivityStartMode(1).toBundle(), ((UserTrackerImpl) this.userTracker).getUserHandle());
            ViewGroup viewGroup3 = this.parent;
            if (viewGroup3 == null) {
                viewGroup3 = null;
            }
            viewGroup3.requireViewById(R.id.controls_scroll_view).setVisibility(8);
            ViewGroup viewGroup4 = this.parent;
            if (viewGroup4 == null) {
                viewGroup4 = null;
            }
            FrameLayout frameLayout = (FrameLayout) viewGroup4.requireViewById(R.id.controls_panel);
            frameLayout.setVisibility(0);
            frameLayout.post(new ControlsUiControllerImpl$createPanelView$1(this, activityAsUser, frameLayout));
        } else if (z) {
            Log.w("ControlsUiController", "Not TaskViewFactory to display panel " + selectionItem4);
        } else {
            this.controlsMetricsLogger.refreshBegin(selectionItem4.uid, !this.keyguardStateController.isUnlocked());
            createListView(selectionItem4);
        }
        this.selectionItem = selectionItem4;
        ((ExecutorImpl) this.bgExecutor).execute(new Runnable() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$showControlsView$3
            @Override // java.lang.Runnable
            public final void run() {
                Object obj4;
                Intent addFlags = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").setPackage(SelectionItem.this.componentName.getPackageName()).addFlags(270532608);
                Iterator<T> it3 = this.packageManager.queryIntentActivities(addFlags, PackageManager.ResolveInfoFlags.of(0L)).iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        obj4 = null;
                        break;
                    } else {
                        obj4 = it3.next();
                        if (((ResolveInfo) obj4).activityInfo.exported) {
                            break;
                        }
                    }
                }
                ResolveInfo resolveInfo = (ResolveInfo) obj4;
                if (resolveInfo != null) {
                    ControlsUiControllerImpl controlsUiControllerImpl = this;
                    addFlags.setPackage(null);
                    addFlags.setComponent(resolveInfo.activityInfo.getComponentName());
                    controlsUiControllerImpl.openAppIntent = addFlags;
                    ViewGroup viewGroup5 = controlsUiControllerImpl.parent;
                    (viewGroup5 != null ? viewGroup5 : null).post(new ControlsUiControllerImpl$showControlsView$3$2$1(0, controlsUiControllerImpl));
                }
            }
        });
        for (SelectionItem selectionItem5 : arrayList4) {
            RenderInfo.Companion companion = RenderInfo.Companion;
            RenderInfo.appIconMap.put(selectionItem5.componentName, selectionItem5.icon);
        }
        final ItemAdapter itemAdapter = new ItemAdapter(this.context);
        itemAdapter.add(selectionItem4);
        ArrayList arrayList5 = new ArrayList();
        for (Object obj4 : arrayList4) {
            if (((SelectionItem) obj4) != selectionItem4) {
                arrayList5.add(obj4);
            }
        }
        itemAdapter.addAll(CollectionsKt.sortedWith(arrayList5, new ControlsUiControllerImpl$createDropDown$lambda$20$$inlined$sortedBy$1()));
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.controls_header_app_icon_size);
        ViewGroup viewGroup5 = this.parent;
        if (viewGroup5 == null) {
            viewGroup5 = null;
        }
        TextView textView = (TextView) viewGroup5.requireViewById(R.id.app_or_structure_spinner);
        textView.setText(selectionItem4.getTitle());
        ((LayerDrawable) textView.getBackground()).getDrawable(0).setTint(textView.getContext().getResources().getColor(R.color.control_spinner_dropdown, null));
        selectionItem4.icon.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
        textView.setCompoundDrawablePadding((int) (dimensionPixelSize / 2.4f));
        textView.setCompoundDrawablesRelative(selectionItem4.icon, null, null, null);
        ViewGroup viewGroup6 = this.parent;
        if (viewGroup6 == null) {
            viewGroup6 = null;
        }
        final View requireViewById = viewGroup6.requireViewById(R.id.app_or_structure_spinner);
        if (arrayList4.size() == 1) {
            textView.setBackground(null);
            requireViewById.setOnClickListener(null);
            requireViewById.setClickable(false);
        } else {
            ViewGroup viewGroup7 = this.parent;
            if (viewGroup7 == null) {
                viewGroup7 = null;
            }
            textView.setBackground(viewGroup7.getContext().getResources().getDrawable(R.drawable.control_spinner_background));
            requireViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$createDropDown$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ControlsUiControllerImpl controlsUiControllerImpl = ControlsUiControllerImpl.this;
                    final ControlsPopupMenu controlsPopupMenu = new ControlsPopupMenu(ControlsUiControllerImpl.this.popupThemedContext);
                    View view2 = requireViewById;
                    ItemAdapter itemAdapter2 = itemAdapter;
                    final ControlsUiControllerImpl controlsUiControllerImpl2 = ControlsUiControllerImpl.this;
                    controlsPopupMenu.setAnchorView(view2);
                    controlsPopupMenu.setWidth(-1);
                    controlsPopupMenu.setAdapter(itemAdapter2);
                    controlsPopupMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$createDropDown$2$onClick$1$1
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public final void onItemClick(AdapterView adapterView, View view3, int i, long j) {
                            SelectionItem selectionItem6 = (SelectionItem) adapterView.getItemAtPosition(i);
                            ControlsUiControllerImpl controlsUiControllerImpl3 = ControlsUiControllerImpl.this;
                            if (controlsUiControllerImpl3.maybeUpdateSelectedItem(selectionItem6)) {
                                ViewGroup viewGroup8 = controlsUiControllerImpl3.parent;
                                if (viewGroup8 == null) {
                                    viewGroup8 = null;
                                }
                                ControlsUiControllerImpl.reload$default(controlsUiControllerImpl3, viewGroup8);
                            }
                            controlsPopupMenu.dismiss();
                        }
                    });
                    controlsPopupMenu.show();
                    ListView listView = controlsPopupMenu.getListView();
                    if (listView != null) {
                        listView.post(new ControlsUiControllerImpl$showControlsView$3$2$1(1, controlsPopupMenu));
                    }
                    controlsUiControllerImpl.popup = controlsPopupMenu;
                }
            });
        }
        ArrayList arrayList6 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList4, 10));
        Iterator it3 = arrayList4.iterator();
        while (it3.hasNext()) {
            arrayList6.add(((SelectionItem) it3.next()).componentName);
        }
        Set set2 = CollectionsKt.toSet(arrayList6);
        List currentServices = ((ControlsListingControllerImpl) ((ControlsListingController) this.controlsListingController.get())).getCurrentServices();
        ArrayList arrayList7 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(currentServices, 10));
        Iterator it4 = currentServices.iterator();
        while (it4.hasNext()) {
            arrayList7.add(((ControlsServiceInfo) it4.next()).componentName);
        }
        boolean isEmpty = SetsKt.minus(CollectionsKt.toSet(arrayList7), (Iterable) set2).isEmpty();
        SelectedItem selectedItem2 = this.selectedItem;
        boolean z2 = selectedItem2 instanceof SelectedItem.PanelItem;
        SelectedItem.StructureItem structureItem = selectedItem2 instanceof SelectedItem.StructureItem ? (SelectedItem.StructureItem) selectedItem2 : null;
        if (structureItem == null || (structureInfo = structureItem.structure) == null) {
            structureInfo = StructureInfo.EMPTY_STRUCTURE;
        }
        final StructureInfo structureInfo3 = structureInfo;
        ListBuilder listBuilder = new ListBuilder();
        listBuilder.add(new OverflowMenuAdapter.MenuItem(this.context.getText(R.string.controls_open_app), 0L));
        if (!isEmpty) {
            listBuilder.add(new OverflowMenuAdapter.MenuItem(this.context.getText(R.string.controls_menu_add_another_app), 2L));
        }
        listBuilder.add(new OverflowMenuAdapter.MenuItem(this.context.getText(R.string.controls_menu_remove), 4L));
        if (!z2) {
            listBuilder.add(new OverflowMenuAdapter.MenuItem(this.context.getText(R.string.controls_menu_edit), 3L));
        }
        final OverflowMenuAdapter overflowMenuAdapter = new OverflowMenuAdapter(this.context, listBuilder.build(), new Function2() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$createMenu$adapter$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj5, Object obj6) {
                return Boolean.valueOf((((OverflowMenuAdapter) obj5).getItemId(((Number) obj6).intValue()) == 0 && ControlsUiControllerImpl.this.openAppIntent == null) ? false : true);
            }
        });
        ViewGroup viewGroup8 = this.parent;
        final ImageView imageView2 = (ImageView) (viewGroup8 != null ? viewGroup8 : null).requireViewById(R.id.controls_more);
        final SelectionItem selectionItem6 = selectionItem4;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$createMenu$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ControlsUiControllerImpl controlsUiControllerImpl = ControlsUiControllerImpl.this;
                final ControlsPopupMenu controlsPopupMenu = new ControlsPopupMenu(ControlsUiControllerImpl.this.popupThemedContext);
                ImageView imageView3 = imageView2;
                OverflowMenuAdapter overflowMenuAdapter2 = overflowMenuAdapter;
                final ControlsUiControllerImpl controlsUiControllerImpl2 = ControlsUiControllerImpl.this;
                final StructureInfo structureInfo4 = structureInfo3;
                final SelectionItem selectionItem7 = selectionItem6;
                controlsPopupMenu.setWidth(-2);
                controlsPopupMenu.setAnchorView(imageView3);
                controlsPopupMenu.setDropDownGravity(8388613);
                controlsPopupMenu.setAdapter(overflowMenuAdapter2);
                controlsPopupMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$createMenu$1$onClick$1$1
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public final void onItemClick(AdapterView adapterView, View view2, int i, long j) {
                        if (j == 0) {
                            ControlsUiControllerImpl controlsUiControllerImpl3 = ControlsUiControllerImpl.this;
                            Intent intent2 = controlsUiControllerImpl3.openAppIntent;
                            if (intent2 != null) {
                                controlsUiControllerImpl3.startActivity(intent2, false);
                            }
                        } else if (j == 2) {
                            ControlsUiControllerImpl controlsUiControllerImpl4 = ControlsUiControllerImpl.this;
                            controlsUiControllerImpl4.getClass();
                            Context context3 = controlsUiControllerImpl4.activityContext;
                            if (context3 == null) {
                                context3 = null;
                            }
                            Intent intent3 = new Intent(context3, (Class<?>) ControlsProviderSelectorActivity.class);
                            intent3.putExtra("back_should_exit", true);
                            controlsUiControllerImpl4.startActivity(intent3, true);
                        } else if (j == 1) {
                            ControlsUiControllerImpl.this.startTargetedActivity(structureInfo4, ControlsFavoritingActivity.class);
                        } else if (j == 3) {
                            ControlsUiControllerImpl.this.startTargetedActivity(structureInfo4, ControlsEditingActivity.class);
                        } else if (j == 4) {
                            ControlsUiControllerImpl controlsUiControllerImpl5 = ControlsUiControllerImpl.this;
                            SelectionItem selectionItem8 = selectionItem7;
                            controlsUiControllerImpl5.startRemovingApp$frameworks__base__packages__SystemUI__android_common__SystemUI_core(selectionItem8.componentName, selectionItem8.appName);
                        }
                        controlsPopupMenu.dismiss();
                    }
                });
                controlsPopupMenu.show();
                ListView listView = controlsPopupMenu.getListView();
                if (listView != null) {
                    listView.post(new ControlsUiControllerImpl$showControlsView$3$2$1(2, controlsPopupMenu));
                }
                controlsUiControllerImpl.popup = controlsPopupMenu;
            }
        });
        this.overflowMenuAdapter = overflowMenuAdapter;
    }

    public final void startActivity(Intent intent, boolean z) {
        if (z) {
            intent.putExtra("extra_animate", true);
        }
        if (((KeyguardStateControllerImpl) this.keyguardStateController).mShowing) {
            this.activityStarter.postStartActivityDismissingKeyguard(intent, 0);
            return;
        }
        Context context = this.activityContext;
        Context context2 = context == null ? null : context;
        if (context == null) {
            context = null;
        }
        context2.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context, new android.util.Pair[0]).toBundle());
    }

    public final void startRemovingApp$frameworks__base__packages__SystemUI__android_common__SystemUI_core(final ComponentName componentName, final CharSequence charSequence) {
        this.activityStarter.dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$startRemovingApp$1
            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
            public final boolean onDismiss() {
                ComponentName componentName2 = componentName;
                CharSequence charSequence2 = charSequence;
                ControlsUiControllerImpl controlsUiControllerImpl = ControlsUiControllerImpl.this;
                SystemUIDialog systemUIDialog = controlsUiControllerImpl.removeAppDialog;
                if (systemUIDialog != null) {
                    systemUIDialog.cancel();
                }
                Context context = controlsUiControllerImpl.context;
                final ControlsUiControllerImpl$showAppRemovalDialog$1 controlsUiControllerImpl$showAppRemovalDialog$1 = new ControlsUiControllerImpl$showAppRemovalDialog$1(controlsUiControllerImpl, componentName2);
                ControlsDialogsFactory controlsDialogsFactory = controlsUiControllerImpl.dialogsFactory;
                DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.android.systemui.controls.ui.ControlsDialogsFactory$createRemoveAppDialog$listener$1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        ControlsUiControllerImpl$showAppRemovalDialog$1.this.accept(Boolean.valueOf(i == -1));
                    }
                };
                SystemUIDialog.Factory factory = controlsDialogsFactory.dialogFactory;
                factory.getClass();
                SystemUIDialog.AnonymousClass1 anonymousClass1 = new SystemUIDialog.AnonymousClass1();
                int i = SystemUIDialog.$r8$clinit;
                SystemUIDialog create = factory.create(anonymousClass1, context, R.style.Theme_SystemUI_Dialog);
                create.setTitle(context.getString(R.string.controls_panel_remove_app_authorization, charSequence2));
                create.setCanceledOnTouchOutside(true);
                create.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.systemui.controls.ui.ControlsDialogsFactory$createRemoveAppDialog$1$1
                    @Override // android.content.DialogInterface.OnCancelListener
                    public final void onCancel(DialogInterface dialogInterface) {
                        ControlsUiControllerImpl$showAppRemovalDialog$1.this.accept(Boolean.FALSE);
                    }
                });
                create.setPositiveButton(R.string.controls_dialog_remove, onClickListener);
                create.setNeutralButton(R.string.cancel, onClickListener, true);
                create.show();
                controlsUiControllerImpl.removeAppDialog = create;
                return true;
            }
        }, null, true);
    }

    public final void startTargetedActivity(StructureInfo structureInfo, Class cls) {
        Context context = this.activityContext;
        if (context == null) {
            context = null;
        }
        Intent intent = new Intent(context, (Class<?>) cls);
        intent.putExtra("extra_app_label", ((ControlsListingControllerImpl) ((ControlsListingController) this.controlsListingController.get())).getAppLabel(structureInfo.componentName));
        intent.putExtra("extra_structure", structureInfo.structure);
        intent.putExtra("android.intent.extra.COMPONENT_NAME", structureInfo.componentName);
        startActivity(intent, true);
        this.retainCache = true;
    }

    public final void updatePreferences(SelectedItem selectedItem) {
        String obj = selectedItem.getName().toString();
        ComponentName componentName = selectedItem.getComponentName();
        boolean z = selectedItem instanceof SelectedItem.PanelItem;
        SelectedComponentRepositoryImpl selectedComponentRepositoryImpl = this.selectedComponentRepository;
        selectedComponentRepositoryImpl.getSharedPreferencesForUser(((UserTrackerImpl) selectedComponentRepositoryImpl.userTracker).getUserId()).edit().putString("controls_component", componentName != null ? componentName.flattenToString() : null).putString("controls_structure", obj).putBoolean("controls_is_panel", z).apply();
    }
}
