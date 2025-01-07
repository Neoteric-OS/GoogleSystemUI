package com.android.systemui.communal.widgets;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.IWindowManager;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import androidx.activity.ComponentActivity;
import androidx.activity.ComponentActivity$activityResultRegistry$1;
import androidx.activity.compose.ComponentActivityKt;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistry$register$2;
import androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.compose.theme.PlatformThemeKt;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.shared.log.CommunalUiEvent;
import com.android.systemui.communal.ui.compose.CommunalHubKt;
import com.android.systemui.communal.ui.view.layout.sections.CommunalAppWidgetSection;
import com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel;
import com.android.systemui.communal.ui.viewmodel.CommunalEditModeViewModel;
import com.android.systemui.communal.widgets.EditWidgetsActivity$addWidgetActivityLauncher$1;
import com.android.systemui.communal.widgets.WidgetConfigurationController;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$62;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EditWidgetsActivity extends ComponentActivity {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final NopActivityController activityController;
    public final ActivityResultRegistry$register$2 addWidgetActivityLauncher;
    public final CommunalEditModeViewModel communalViewModel;
    public final Logger logger;
    public boolean shouldOpenWidgetPickerOnStart;
    public final UiEventLogger uiEventLogger;
    public final Lazy widgetConfigurator$delegate;
    public final WidgetConfigurationController.Factory widgetConfiguratorFactory;
    public final CommunalAppWidgetSection widgetSection;
    public final IWindowManager windowManagerService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NopActivityController {
    }

    public /* synthetic */ EditWidgetsActivity(CommunalEditModeViewModel communalEditModeViewModel, IWindowManager iWindowManager, UiEventLogger uiEventLogger, WidgetConfigurationController.Factory factory, CommunalAppWidgetSection communalAppWidgetSection, LogBuffer logBuffer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(communalEditModeViewModel, (i & 2) != 0 ? null : iWindowManager, uiEventLogger, factory, communalAppWidgetSection, logBuffer);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        CompletableDeferred result;
        this.activityController.getClass();
        super.onActivityResult(i, i2, intent);
        if (i != 100 || (result = ((WidgetConfigurationController) this.widgetConfigurator$delegate.getValue()).getResult()) == null) {
            return;
        }
        ((CompletableDeferredImpl) result).makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Boolean.valueOf(i2 == -1));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BuildersKt.launch$default(LifecycleKt.getCoroutineScope(getLifecycle()), null, null, new EditWidgetsActivity$listenForTransitionAndChangeScene$1(this, null), 3);
        this.activityController.getClass();
        StateFlowImpl stateFlowImpl = this.communalViewModel.communalInteractor._editModeOpen;
        Boolean bool = Boolean.TRUE;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        WindowInsetsController windowInsetsController = getWindow().getDecorView().getWindowInsetsController();
        if (windowInsetsController != null) {
            windowInsetsController.hide(WindowInsets.Type.systemBars());
        }
        getWindow().setDecorFitsSystemWindows(false);
        this.shouldOpenWidgetPickerOnStart = getIntent().getBooleanExtra("open_widget_picker_on_start", false);
        ComponentActivityKt.setContent$default(this, new ComposableLambdaImpl(-664677188, true, new Function2() { // from class: com.android.systemui.communal.widgets.EditWidgetsActivity$onCreate$1
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r4v5, types: [com.android.systemui.communal.widgets.EditWidgetsActivity$onCreate$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Composer composer = (Composer) obj;
                if ((((Number) obj2).intValue() & 11) == 2) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey = ComposerKt.invocation;
                final EditWidgetsActivity editWidgetsActivity = EditWidgetsActivity.this;
                PlatformThemeKt.PlatformTheme(false, ComposableLambdaKt.rememberComposableLambda(-700058190, new Function2() { // from class: com.android.systemui.communal.widgets.EditWidgetsActivity$onCreate$1.1
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        Composer composer2 = (Composer) obj3;
                        if ((((Number) obj4).intValue() & 11) == 2) {
                            ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                            if (composerImpl2.getSkipping()) {
                                composerImpl2.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey2 = ComposerKt.invocation;
                        ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                        Modifier m25backgroundbw27NRU = BackgroundKt.m25backgroundbw27NRU(SizeKt.FillWholeMaxSize, ((AndroidColorScheme) composerImpl3.consume(AndroidColorSchemeKt.LocalAndroidColorScheme)).surfaceDim, RectangleShapeKt.RectangleShape);
                        EditWidgetsActivity editWidgetsActivity2 = EditWidgetsActivity.this;
                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
                        int i = composerImpl3.compoundKeyHash;
                        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl3, m25backgroundbw27NRU);
                        ComposeUiNode.Companion.getClass();
                        Function0 function0 = ComposeUiNode.Companion.Constructor;
                        composerImpl3.startReusableNode();
                        if (composerImpl3.inserting) {
                            composerImpl3.createNode(function0);
                        } else {
                            composerImpl3.useNode();
                        }
                        Updater.m259setimpl(composerImpl3, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                        Updater.m259setimpl(composerImpl3, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
                        if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(i))) {
                            AnimatedContentKt$$ExternalSyntheticOutline0.m(i, composerImpl3, i, function2);
                        }
                        Updater.m259setimpl(composerImpl3, materializeModifier, ComposeUiNode.Companion.SetModifier);
                        CommunalEditModeViewModel communalEditModeViewModel = editWidgetsActivity2.communalViewModel;
                        EditWidgetsActivity$onCreate$1$1$1$1 editWidgetsActivity$onCreate$1$1$1$1 = new EditWidgetsActivity$onCreate$1$1$1$1(0, editWidgetsActivity2, EditWidgetsActivity.class, "onOpenWidgetPicker", "onOpenWidgetPicker()V", 0);
                        CommunalHubKt.CommunalHub(null, communalEditModeViewModel, editWidgetsActivity2.widgetSection, null, null, (WidgetConfigurationController) editWidgetsActivity2.widgetConfigurator$delegate.getValue(), editWidgetsActivity$onCreate$1$1$1$1, new EditWidgetsActivity$onCreate$1$1$1$2(0, editWidgetsActivity2, EditWidgetsActivity.class, "onEditDone", "onEditDone()V", 0), composerImpl3, 262720, 25);
                        composerImpl3.end(true);
                        return Unit.INSTANCE;
                    }
                }, composer), composer, 48, 1);
                return Unit.INSTANCE;
            }
        }));
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        CommunalEditModeViewModel communalEditModeViewModel = this.communalViewModel;
        communalEditModeViewModel.communalSceneInteractor._editModeState.setValue(null);
        int i = communalEditModeViewModel.currentScrollIndex;
        int i2 = communalEditModeViewModel.currentScrollOffset;
        CommunalInteractor communalInteractor = ((BaseCommunalViewModel) communalEditModeViewModel).communalInteractor;
        communalInteractor._firstVisibleItemIndex = i;
        communalInteractor._firstVisibleItemOffset = i2;
        StateFlowImpl stateFlowImpl = communalEditModeViewModel.communalInteractor._editModeOpen;
        Boolean bool = Boolean.FALSE;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
    }

    @Override // android.app.Activity
    public final void onStart() {
        super.onStart();
        StateFlowImpl stateFlowImpl = this.communalViewModel.communalInteractor._editActivityShowing;
        Boolean bool = Boolean.TRUE;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        Logger.i$default(this.logger, "Starting the communal widget editor activity", null, 2, null);
        this.uiEventLogger.log(CommunalUiEvent.COMMUNAL_HUB_EDIT_MODE_SHOWN);
    }

    @Override // android.app.Activity
    public final void onStop() {
        super.onStop();
        StateFlowImpl stateFlowImpl = this.communalViewModel.communalInteractor._editActivityShowing;
        Boolean bool = Boolean.FALSE;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        Logger.i$default(this.logger, "Stopping the communal widget editor activity", null, 2, null);
        this.uiEventLogger.log(CommunalUiEvent.COMMUNAL_HUB_EDIT_MODE_GONE);
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i, Bundle bundle) {
        this.activityController.getClass();
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        this.activityController.getClass();
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    public EditWidgetsActivity(CommunalEditModeViewModel communalEditModeViewModel, IWindowManager iWindowManager, UiEventLogger uiEventLogger, WidgetConfigurationController.Factory factory, CommunalAppWidgetSection communalAppWidgetSection, LogBuffer logBuffer) {
        this.communalViewModel = communalEditModeViewModel;
        this.windowManagerService = iWindowManager;
        this.uiEventLogger = uiEventLogger;
        this.widgetConfiguratorFactory = factory;
        this.widgetSection = communalAppWidgetSection;
        this.logger = new Logger(logBuffer, "EditWidgetsActivity");
        this.widgetConfigurator$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.communal.widgets.EditWidgetsActivity$widgetConfigurator$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                EditWidgetsActivity editWidgetsActivity = EditWidgetsActivity.this;
                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$62 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$62 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$62) editWidgetsActivity.widgetConfiguratorFactory;
                daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$62.getClass();
                DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$62.this$0;
                return new WidgetConfigurationController(editWidgetsActivity, (CommunalAppWidgetHost) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).provideCommunalAppWidgetHostProvider.get(), (CoroutineDispatcher) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).bgDispatcherProvider.get());
            }
        });
        this.activityController = new NopActivityController();
        final ActivityResultContracts$StartActivityForResult activityResultContracts$StartActivityForResult = new ActivityResultContracts$StartActivityForResult(0);
        final EditWidgetsActivity$addWidgetActivityLauncher$1 editWidgetsActivity$addWidgetActivityLauncher$1 = new EditWidgetsActivity$addWidgetActivityLauncher$1(this);
        final String str = "activity_rq#" + this.nextLocalRequestCode.getAndIncrement();
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry.state.isAtLeast(Lifecycle.State.STARTED)) {
            throw new IllegalStateException(("LifecycleOwner " + this + " is attempting to register while current state is " + lifecycleRegistry.state + ". LifecycleOwners must call register before they are STARTED.").toString());
        }
        final ComponentActivity$activityResultRegistry$1 componentActivity$activityResultRegistry$1 = this.activityResultRegistry;
        componentActivity$activityResultRegistry$1.registerKey(str);
        ActivityResultRegistry.LifecycleContainer lifecycleContainer = (ActivityResultRegistry.LifecycleContainer) componentActivity$activityResultRegistry$1.keyToLifecycleContainers.get(str);
        lifecycleContainer = lifecycleContainer == null ? new ActivityResultRegistry.LifecycleContainer(lifecycleRegistry) : lifecycleContainer;
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                Lifecycle.Event event2 = Lifecycle.Event.ON_START;
                ComponentActivity$activityResultRegistry$1 componentActivity$activityResultRegistry$12 = ComponentActivity$activityResultRegistry$1.this;
                String str2 = str;
                if (event2 != event) {
                    if (Lifecycle.Event.ON_STOP == event) {
                        componentActivity$activityResultRegistry$12.keyToCallback.remove(str2);
                        return;
                    } else {
                        if (Lifecycle.Event.ON_DESTROY == event) {
                            componentActivity$activityResultRegistry$12.unregister$activity_release(str2);
                            return;
                        }
                        return;
                    }
                }
                Map map = componentActivity$activityResultRegistry$12.keyToCallback;
                EditWidgetsActivity$addWidgetActivityLauncher$1 editWidgetsActivity$addWidgetActivityLauncher$12 = editWidgetsActivity$addWidgetActivityLauncher$1;
                map.put(str2, new ActivityResultRegistry.CallbackAndContract(editWidgetsActivity$addWidgetActivityLauncher$12, activityResultContracts$StartActivityForResult));
                if (componentActivity$activityResultRegistry$12.parsedPendingResults.containsKey(str2)) {
                    Object obj = componentActivity$activityResultRegistry$12.parsedPendingResults.get(str2);
                    componentActivity$activityResultRegistry$12.parsedPendingResults.remove(str2);
                    editWidgetsActivity$addWidgetActivityLauncher$12.onActivityResult(obj);
                }
                ActivityResult activityResult = (ActivityResult) componentActivity$activityResultRegistry$12.pendingResults.getParcelable(str2, ActivityResult.class);
                if (activityResult != null) {
                    componentActivity$activityResultRegistry$12.pendingResults.remove(str2);
                    editWidgetsActivity$addWidgetActivityLauncher$12.onActivityResult(new ActivityResult(activityResult.data, activityResult.resultCode));
                }
            }
        };
        lifecycleContainer.lifecycle.addObserver(lifecycleEventObserver);
        lifecycleContainer.observers.add(lifecycleEventObserver);
        componentActivity$activityResultRegistry$1.keyToLifecycleContainers.put(str, lifecycleContainer);
        this.addWidgetActivityLauncher = new ActivityResultRegistry$register$2(componentActivity$activityResultRegistry$1, str, activityResultContracts$StartActivityForResult, 0);
    }
}
