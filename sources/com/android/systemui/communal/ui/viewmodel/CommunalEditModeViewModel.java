package com.android.systemui.communal.ui.viewmodel;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.UserHandle;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.activity.result.ActivityResultRegistry$register$2;
import com.android.compose.animation.scene.SceneKey;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import com.android.systemui.communal.shared.log.CommunalMetricsLogger;
import com.android.systemui.communal.shared.log.CommunalUiEvent;
import com.android.systemui.communal.widgets.WidgetConfigurator;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import com.android.wm.shell.R;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalEditModeViewModel extends BaseCommunalViewModel {
    public final StateFlowImpl _reorderingWidgets;
    public final AccessibilityManager accessibilityManager;
    public final CoroutineDispatcher backgroundDispatcher;
    public final CommunalEditModeViewModel$special$$inlined$map$1 canShowEditMode;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 communalContent;
    public final CommunalInteractor communalInteractor;
    public final CommunalSettingsInteractor communalSettingsInteractor;
    public final Context context;
    public final CommunalEditModeViewModel$special$$inlined$map$1 isCommunalContentVisible;
    public final boolean isEditMode;
    public final ReadonlyStateFlow isIdleOnCommunal;
    public final String launcherPackage;
    public final Logger logger;
    public final CommunalMetricsLogger metricsLogger;
    public final PackageManager packageManager;
    public final Flow showDisclaimer;
    public final UiEventLogger uiEventLogger;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalEditModeViewModel(CommunalSceneInteractor communalSceneInteractor, CommunalInteractor communalInteractor, CommunalSettingsInteractor communalSettingsInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, MediaHost mediaHost, UiEventLogger uiEventLogger, LogBuffer logBuffer, CoroutineDispatcher coroutineDispatcher, CommunalMetricsLogger communalMetricsLogger, Context context, AccessibilityManager accessibilityManager, PackageManager packageManager, String str) {
        super(communalSceneInteractor, communalInteractor, mediaHost);
        int i = 0;
        this.communalInteractor = communalInteractor;
        this.communalSettingsInteractor = communalSettingsInteractor;
        this.uiEventLogger = uiEventLogger;
        this.backgroundDispatcher = coroutineDispatcher;
        this.metricsLogger = communalMetricsLogger;
        this.context = context;
        this.accessibilityManager = accessibilityManager;
        this.packageManager = packageManager;
        this.launcherPackage = str;
        this.logger = new Logger(logBuffer, "CommunalEditModeViewModel");
        int i2 = 1;
        this.isEditMode = true;
        CommunalEditModeViewModel$special$$inlined$map$1 communalEditModeViewModel$special$$inlined$map$1 = new CommunalEditModeViewModel$special$$inlined$map$1(communalSceneInteractor.editModeState, i);
        this.isCommunalContentVisible = communalEditModeViewModel$special$$inlined$map$1;
        this.showDisclaimer = FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(i2, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{communalEditModeViewModel$special$$inlined$map$1, new BooleanFlowOperators$not$$inlined$map$1(i, communalInteractor.isDisclaimerDismissed)})).toArray(new Flow[0])));
        SceneKey sceneKey = Scenes.Bouncer;
        this.canShowEditMode = new CommunalEditModeViewModel$special$$inlined$map$1(FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(i2, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{keyguardTransitionInteractor.isFinishedIn(KeyguardState.GONE), communalInteractor.editModeOpen})).toArray(new Flow[0]))), i2);
        this.communalContent = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(communalInteractor.widgetContent, new CommunalEditModeViewModel$communalContent$1(this, null), i);
        this._reorderingWidgets = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.isIdleOnCommunal = communalInteractor.isIdleOnCommunal;
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 getCommunalContent() {
        return this.communalContent;
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final StateFlowImpl getReorderingWidgets() {
        return this._reorderingWidgets;
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final Flow isCommunalContentVisible() {
        return this.isCommunalContentVisible;
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final boolean isEditMode() {
        return this.isEditMode;
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onAddWidget(ComponentName componentName, UserHandle userHandle, Integer num, WidgetConfigurator widgetConfigurator) {
        this.communalInteractor.widgetRepository.addWidget(componentName, userHandle, num, widgetConfigurator);
        String flattenToString = componentName.flattenToString();
        CommunalMetricsLogger communalMetricsLogger = this.metricsLogger;
        if (communalMetricsLogger.isLoggable(flattenToString)) {
            communalMetricsLogger.statsLogProxy.writeCommunalHubWidgetEventReported(flattenToString, 1, num != null ? num.intValue() : -1);
        }
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onDeleteWidget(int i, ComponentName componentName, int i2) {
        this.communalInteractor.widgetRepository.deleteWidget(i);
        String flattenToString = componentName.flattenToString();
        CommunalMetricsLogger communalMetricsLogger = this.metricsLogger;
        if (communalMetricsLogger.isLoggable(flattenToString)) {
            communalMetricsLogger.statsLogProxy.writeCommunalHubWidgetEventReported(flattenToString, 2, i2);
        }
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onNewWidgetAdded(AppWidgetProviderInfo appWidgetProviderInfo) {
        if (this.accessibilityManager.isEnabled()) {
            String string = this.context.getString(R.string.accessibility_announcement_communal_widget_added, appWidgetProviderInfo.loadLabel(this.packageManager));
            AccessibilityManager accessibilityManager = this.accessibilityManager;
            AccessibilityEvent accessibilityEvent = new AccessibilityEvent(16384);
            accessibilityEvent.setContentDescription(string);
            accessibilityManager.sendAccessibilityEvent(accessibilityEvent);
        }
    }

    public final Object onOpenWidgetPicker(Resources resources, ActivityResultRegistry$register$2 activityResultRegistry$register$2, Continuation continuation) {
        return BuildersKt.withContext(this.backgroundDispatcher, new CommunalEditModeViewModel$onOpenWidgetPicker$2(this, resources, activityResultRegistry$register$2, null), continuation);
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onReorderWidgetCancel() {
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = this._reorderingWidgets;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        this.uiEventLogger.log(CommunalUiEvent.COMMUNAL_HUB_REORDER_WIDGET_CANCEL);
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onReorderWidgetEnd() {
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = this._reorderingWidgets;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        this.uiEventLogger.log(CommunalUiEvent.COMMUNAL_HUB_REORDER_WIDGET_FINISH);
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onReorderWidgetStart() {
        setSelectedKey(null);
        Boolean bool = Boolean.TRUE;
        StateFlowImpl stateFlowImpl = this._reorderingWidgets;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        this.uiEventLogger.log(CommunalUiEvent.COMMUNAL_HUB_REORDER_WIDGET_START);
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onReorderWidgets(Map map) {
        this.communalInteractor.widgetRepository.updateWidgetOrder(map);
    }
}
