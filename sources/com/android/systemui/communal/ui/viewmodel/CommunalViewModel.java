package com.android.systemui.communal.ui.viewmodel;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import com.android.systemui.communal.domain.interactor.CommunalTutorialInteractor;
import com.android.systemui.communal.shared.log.CommunalMetricsLogger;
import com.android.systemui.communal.shared.model.EditModeState;
import com.android.systemui.communal.ui.viewmodel.PopupType;
import com.android.systemui.communal.widgets.EditWidgetsActivity;
import com.android.systemui.communal.widgets.EditWidgetsActivityStarterImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.android.systemui.utils.coroutines.flow.LatestConflatedKt;
import com.android.wm.shell.R;
import java.util.List;
import java.util.Locale;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalViewModel extends BaseCommunalViewModel {
    public final StateFlowImpl _currentPopup;
    public final StateFlowImpl _isEnableWidgetDialogShowing;
    public final StateFlowImpl _isEnableWorkProfileDialogShowing;
    public final Flow communalBackground;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 communalContent;
    public final CommunalInteractor communalInteractor;
    public final ReadonlyStateFlow currentPopup;
    public StandaloneCoroutine delayedHideCurrentPopupJob;
    public List frozenCommunalContent;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 isCommunalContentFlowFrozen;
    public final StateFlowImpl isCommunalContentVisible;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 isEmptyState;
    public final ReadonlyStateFlow isEnableWidgetDialogShowing;
    public final ReadonlyStateFlow isEnableWorkProfileDialogShowing;
    public final Flow isFocusable;
    public final KeyguardIndicationController keyguardIndicationController;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 latestCommunalContent;
    public final Logger logger;
    public final CommunalMetricsLogger metricsLogger;
    public final ChannelFlowTransformLatest ongoingContent;
    public final Resources resources;
    public final CoroutineScope scope;
    public final ShadeInteractor shadeInteractor;
    public final ReadonlyStateFlow touchesAllowed;
    public final CommunalViewModel$widgetAccessibilityDelegate$1 widgetAccessibilityDelegate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.communal.ui.viewmodel.CommunalViewModel$widgetAccessibilityDelegate$1] */
    public CommunalViewModel(CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, CoroutineScope coroutineScope2, Resources resources, KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardInteractor keyguardInteractor, KeyguardIndicationController keyguardIndicationController, CommunalSceneInteractor communalSceneInteractor, CommunalInteractor communalInteractor, CommunalSettingsInteractor communalSettingsInteractor, CommunalTutorialInteractor communalTutorialInteractor, ShadeInteractor shadeInteractor, MediaHost mediaHost, LogBuffer logBuffer, CommunalMetricsLogger communalMetricsLogger) {
        super(communalSceneInteractor, communalInteractor, mediaHost);
        int i = 0;
        this.scope = coroutineScope;
        this.resources = resources;
        this.keyguardIndicationController = keyguardIndicationController;
        this.communalInteractor = communalInteractor;
        this.shadeInteractor = shadeInteractor;
        this.metricsLogger = communalMetricsLogger;
        this.logger = new Logger(logBuffer, "CommunalViewModel");
        this.ongoingContent = FlowKt.transformLatest(FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new CommunalViewModel$isMediaHostVisible$2(mediaHost, null), FlowConflatedKt.conflatedCallbackFlow(new CommunalViewModel$isMediaHostVisible$1(mediaHost, null)))), new CommunalViewModel$isMediaHostVisible$3(this, null), i), coroutineDispatcher), new CommunalViewModel$special$$inlined$flatMapLatest$1(null, this, mediaHost));
        this.latestCommunalContent = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.transformLatest(communalTutorialInteractor.isTutorialAvailable, new CommunalViewModel$special$$inlined$flatMapLatest$2(this, null)), new CommunalViewModel$latestCommunalContent$2(this, null), i);
        this.isCommunalContentVisible = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        SceneKey sceneKey = Scenes.Bouncer;
        KeyguardState keyguardState = KeyguardState.GLANCEABLE_HUB;
        this.communalContent = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(LatestConflatedKt.flatMapLatestConflated(new CommunalViewModel$communalContent$1(this, null), new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(1, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{keyguardTransitionInteractor.isFinishedIn(keyguardState), keyguardInteractor.isKeyguardOccluded, new BooleanFlowOperators$not$$inlined$map$1(i, keyguardInteractor.isAbleToDream)})).toArray(new Flow[0])))), new CommunalViewModel$isCommunalContentFlowFrozen$1(this, null), i)), new CommunalViewModel$communalContent$2(this, null), i);
        final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = communalInteractor.widgetContent;
        this.isEmptyState = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.communal.ui.viewmodel.CommunalViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.communal.ui.viewmodel.CommunalViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.communal.ui.viewmodel.CommunalViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
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
                        boolean r0 = r6 instanceof com.android.systemui.communal.ui.viewmodel.CommunalViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.communal.ui.viewmodel.CommunalViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.communal.ui.viewmodel.CommunalViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.communal.ui.viewmodel.CommunalViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.communal.ui.viewmodel.CommunalViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L47
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.util.List r5 = (java.util.List) r5
                        boolean r5 = r5.isEmpty()
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.ui.viewmodel.CommunalViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__ZipKt$combine$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }), new CommunalViewModel$isEmptyState$2(this, null), i);
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._currentPopup = MutableStateFlow;
        this.currentPopup = new ReadonlyStateFlow(MutableStateFlow);
        ShadeInteractorImpl shadeInteractorImpl = (ShadeInteractorImpl) shadeInteractor;
        this.isFocusable = FlowKt.distinctUntilChanged(FlowKt.combine(keyguardTransitionInteractor.isFinishedIn(keyguardState), communalInteractor.isIdleOnCommunal, shadeInteractorImpl.isAnyFullyExpanded, new CommunalViewModel$isFocusable$1(4, null)));
        this.widgetAccessibilityDelegate = new View.AccessibilityDelegate() { // from class: com.android.systemui.communal.ui.viewmodel.CommunalViewModel$widgetAccessibilityDelegate$1
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK.getId(), CommunalViewModel.this.resources.getString(R.string.accessibility_action_label_edit_widgets).toLowerCase(Locale.ROOT)));
            }

            @Override // android.view.View.AccessibilityDelegate
            public final boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
                if (i2 != AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK.getId()) {
                    return super.performAccessibilityAction(view, i2, bundle);
                }
                CommunalViewModel.this.onOpenWidgetEditor(false);
                return true;
            }
        };
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._isEnableWidgetDialogShowing = MutableStateFlow2;
        this.isEnableWidgetDialogShowing = new ReadonlyStateFlow(MutableStateFlow2);
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this._isEnableWorkProfileDialogShowing = MutableStateFlow3;
        this.isEnableWorkProfileDialogShowing = new ReadonlyStateFlow(MutableStateFlow3);
        mediaHost.setExpansion(1.0f);
        MediaHost.MediaHostStateHolder mediaHostStateHolder = mediaHost.state;
        mediaHostStateHolder.setExpandedMatchesParentHeight(true);
        mediaHost.setShowsOnlyActiveMedia(false);
        mediaHost.setFalsingProtectionNeeded(false);
        mediaHostStateHolder.setDisablePagination(true);
        mediaHost.init(4);
        this.touchesAllowed = FlowKt.stateIn(new BooleanFlowOperators$not$$inlined$map$1(i, shadeInteractorImpl.isAnyFullyExpanded), coroutineScope2, SharingStarted.Companion.Eagerly, bool);
        this.communalBackground = communalSettingsInteractor.communalBackground;
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 getCommunalContent() {
        return this.communalContent;
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final View.AccessibilityDelegate getWidgetAccessibilityDelegate() {
        return this.widgetAccessibilityDelegate;
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final Flow isCommunalContentVisible() {
        return this.isCommunalContentVisible;
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final Flow isEmptyState() {
        return this.isEmptyState;
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final Flow isFocusable() {
        return this.isFocusable;
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onDismissCtaTile() {
        BuildersKt.launch$default(this.scope, null, null, new CommunalViewModel$onDismissCtaTile$1(this, null), 3);
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onLongClick() {
        setCurrentPopupType(PopupType.CustomizeWidgetButton.INSTANCE);
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onOpenEnableWorkProfileDialog() {
        Boolean bool = Boolean.TRUE;
        StateFlowImpl stateFlowImpl = this._isEnableWorkProfileDialogShowing;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onOpenWidgetEditor(boolean z) {
        int i = this.currentScrollIndex;
        int i2 = this.currentScrollOffset;
        CommunalInteractor communalInteractor = super.communalInteractor;
        communalInteractor._firstVisibleItemIndex = i;
        communalInteractor._firstVisibleItemOffset = i2;
        CommunalInteractor communalInteractor2 = this.communalInteractor;
        communalInteractor2.getClass();
        communalInteractor2.communalSceneInteractor._editModeState.setValue(EditModeState.STARTING);
        EditWidgetsActivityStarterImpl editWidgetsActivityStarterImpl = communalInteractor2.editWidgetsActivityStarter;
        Intent addFlags = new Intent(editWidgetsActivityStarterImpl.applicationContext, (Class<?>) EditWidgetsActivity.class).addFlags(268468224);
        addFlags.putExtra("open_widget_picker_on_start", z);
        editWidgetsActivityStarterImpl.activityStarter.startActivityDismissingKeyguard(addFlags, true, true, editWidgetsActivityStarterImpl.applicationContext.getResources().getString(R.string.unlock_reason_to_customize_widgets));
    }

    @Override // com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel
    public final void onTapWidget(int i, ComponentName componentName) {
        String flattenToString = componentName.flattenToString();
        CommunalMetricsLogger communalMetricsLogger = this.metricsLogger;
        if (communalMetricsLogger.isLoggable(flattenToString)) {
            communalMetricsLogger.statsLogProxy.writeCommunalHubWidgetEventReported(flattenToString, 3, i);
        }
    }

    public final void setCurrentPopupType(PopupType popupType) {
        this._currentPopup.setValue(popupType);
        StandaloneCoroutine standaloneCoroutine = this.delayedHideCurrentPopupJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        if (popupType == null) {
            this.delayedHideCurrentPopupJob = null;
        } else {
            this.delayedHideCurrentPopupJob = BuildersKt.launch$default(this.scope, null, null, new CommunalViewModel$setCurrentPopupType$1(this, null), 3);
        }
    }
}
