package com.android.systemui.communal.ui.viewmodel;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.os.UserHandle;
import android.view.View;
import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.TransitionKey;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.widgets.WidgetConfigurator;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BaseCommunalViewModel {
    public final StateFlowImpl _isNestedScrolling;
    public final StateFlowImpl _isTouchConsumed;
    public final CommunalInteractor communalInteractor;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final ReadonlyStateFlow currentScene;
    public int currentScrollIndex;
    public int currentScrollOffset;
    public final Flow glanceableTouchAvailable;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 isEmptyState;
    public final StateFlowImpl isFocusable;
    public final ReadonlyStateFlow isNestedScrolling;
    public final ReadonlyStateFlow isTouchConsumed;
    public final MediaHost mediaHost;
    public final StateFlowImpl reorderingWidgets;
    public final ReadonlyStateFlow selectedKey;

    public BaseCommunalViewModel(CommunalSceneInteractor communalSceneInteractor, CommunalInteractor communalInteractor, MediaHost mediaHost) {
        this.communalSceneInteractor = communalSceneInteractor;
        this.communalInteractor = communalInteractor;
        this.mediaHost = mediaHost;
        this.currentScene = communalSceneInteractor.currentScene;
        Boolean bool = Boolean.FALSE;
        StateFlowKt.MutableStateFlow(bool);
        this.isFocusable = StateFlowKt.MutableStateFlow(bool);
        this.reorderingWidgets = StateFlowKt.MutableStateFlow(bool);
        this.selectedKey = communalInteractor.selectedKey;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._isTouchConsumed = MutableStateFlow;
        ReadonlyStateFlow readonlyStateFlow = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._isNestedScrolling = MutableStateFlow2;
        this.glanceableTouchAvailable = FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(2, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{new BooleanFlowOperators$not$$inlined$map$1(0, readonlyStateFlow), new ReadonlyStateFlow(MutableStateFlow2)})).toArray(new Flow[0])));
        this.isEmptyState = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool);
    }

    public static void changeScene$default(BaseCommunalViewModel baseCommunalViewModel, SceneKey sceneKey, String str, TransitionKey transitionKey, int i) {
        if ((i & 4) != 0) {
            transitionKey = null;
        }
        baseCommunalViewModel.communalSceneInteractor.changeScene(sceneKey, str, transitionKey, null);
    }

    public abstract FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 getCommunalContent();

    public StateFlowImpl getReorderingWidgets() {
        return this.reorderingWidgets;
    }

    public View.AccessibilityDelegate getWidgetAccessibilityDelegate() {
        return null;
    }

    public abstract Flow isCommunalContentVisible();

    public boolean isEditMode() {
        return false;
    }

    public Flow isEmptyState() {
        return this.isEmptyState;
    }

    public Flow isFocusable() {
        return this.isFocusable;
    }

    public final void onResetTouchState() {
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = this._isTouchConsumed;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        StateFlowImpl stateFlowImpl2 = this._isNestedScrolling;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, bool);
    }

    public final void setSelectedKey(String str) {
        this.communalInteractor._selectedKey.setValue(str);
    }

    public void onDismissCtaTile() {
    }

    public void onLongClick() {
    }

    public void onOpenEnableWorkProfileDialog() {
    }

    public void onReorderWidgetCancel() {
    }

    public void onReorderWidgetEnd() {
    }

    public void onReorderWidgetStart() {
    }

    public void onNewWidgetAdded(AppWidgetProviderInfo appWidgetProviderInfo) {
    }

    public void onOpenWidgetEditor(boolean z) {
    }

    public void onReorderWidgets(Map map) {
    }

    public void onTapWidget(int i, ComponentName componentName) {
    }

    public void onDeleteWidget(int i, ComponentName componentName, int i2) {
    }

    public void onAddWidget(ComponentName componentName, UserHandle userHandle, Integer num, WidgetConfigurator widgetConfigurator) {
    }
}
