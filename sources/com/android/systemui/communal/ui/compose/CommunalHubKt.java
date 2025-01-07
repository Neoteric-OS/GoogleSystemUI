package com.android.systemui.communal.ui.compose;

import android.appwidget.AppWidgetProviderInfo;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.UserHandle;
import android.util.SizeF;
import android.view.DragEvent;
import android.view.IWindowManager;
import android.widget.FrameLayout;
import android.widget.RemoteViews;
import androidx.activity.compose.PredictiveBackHandlerKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.EasingKt$$ExternalSyntheticLambda0;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.draganddrop.DragAndDropTargetKt;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.Arrangement$Center$1;
import androidx.compose.foundation.layout.Arrangement$Start$1;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.FillElement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.PaddingValuesImpl;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.grid.GridCells;
import androidx.compose.foundation.lazy.grid.GridItemSpan;
import androidx.compose.foundation.lazy.grid.LazyGridDslKt;
import androidx.compose.foundation.lazy.grid.LazyGridIntervalContent;
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo;
import androidx.compose.foundation.lazy.grid.LazyGridItemScope;
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope;
import androidx.compose.foundation.lazy.grid.LazyGridMeasureResult;
import androidx.compose.foundation.lazy.grid.LazyGridMeasuredItem;
import androidx.compose.foundation.lazy.grid.LazyGridScope;
import androidx.compose.foundation.lazy.grid.LazyGridScrollPosition;
import androidx.compose.foundation.lazy.grid.LazyGridSpanKt;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.foundation.lazy.grid.LazyGridStateKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.filled.AddKt;
import androidx.compose.material.icons.outlined.WidgetsKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardColors;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.IconButtonColors;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ModalBottomSheetKt;
import androidx.compose.material3.SheetState;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropTarget;
import androidx.compose.ui.draganddrop.DragAndDrop_androidKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendPointerInputElement;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TestTagKt;
import androidx.compose.ui.res.PrimitiveResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesAndroid;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsProperties_androidKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.window.layout.WindowMetricsCalculator;
import com.airbnb.lottie.compose.LottieAnimationKt$$ExternalSyntheticOutline0;
import com.android.compose.animation.Easings;
import com.android.compose.animation.Easings$fromInterpolator$1;
import com.android.compose.theme.AndroidColorScheme;
import com.android.compose.theme.AndroidColorSchemeKt;
import com.android.compose.ui.graphics.painter.DrawablePainterKt;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import com.android.systemui.communal.shared.model.CommunalContentSize;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.communal.ui.compose.Dimensions;
import com.android.systemui.communal.ui.compose.extensions.ModifierExtKt$allowGestures$1;
import com.android.systemui.communal.ui.compose.extensions.PointerInputScopeExtKt;
import com.android.systemui.communal.ui.view.layout.sections.CommunalAppWidgetSection;
import com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel;
import com.android.systemui.communal.ui.viewmodel.CommunalEditModeViewModel;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.communal.util.DensityUtils;
import com.android.systemui.communal.util.WidgetPickerIntentUtils$WidgetExtra;
import com.android.systemui.communal.widgets.SmartspaceAppWidgetHostView;
import com.android.systemui.communal.widgets.WidgetConfigurationController;
import com.android.systemui.communal.widgets.WidgetConfigurator;
import com.android.systemui.statusbar.phone.SystemUIDialogFactory;
import com.android.systemui.util.animation.UniqueObjectHostView;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.sequences.FilteringSequence;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CommunalHubKt {
    public static final void AccessibilityContainer(final BaseCommunalViewModel baseCommunalViewModel, final Function2 function2, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-325917450);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final Context context = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(baseCommunalViewModel.isFocusable(), Boolean.FALSE, composerImpl, 56);
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier wrapContentHeight$default = SizeKt.wrapContentHeight$default(SizeKt.fillMaxWidth(companion, 1.0f), 3);
        if (((Boolean) collectAsStateWithLifecycle.getValue()).booleanValue() && !baseCommunalViewModel.isEditMode()) {
            wrapContentHeight$default = wrapContentHeight$default.then(SemanticsModifierKt.semantics(FocusableKt.focusable$default(companion, ((Boolean) collectAsStateWithLifecycle.getValue()).booleanValue(), null, 2), false, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$AccessibilityContainer$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
                    SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, context.getString(R.string.accessibility_content_description_for_communal_hub));
                    String string = context.getString(R.string.accessibility_action_label_close_communal_hub);
                    final BaseCommunalViewModel baseCommunalViewModel2 = baseCommunalViewModel;
                    CustomAccessibilityAction customAccessibilityAction = new CustomAccessibilityAction(string, new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$AccessibilityContainer$1$1.1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            BaseCommunalViewModel.changeScene$default(BaseCommunalViewModel.this, CommunalScenes.Blank, "closed by accessibility", null, 12);
                            return Boolean.TRUE;
                        }
                    });
                    String string2 = context.getString(R.string.accessibility_action_label_edit_widgets);
                    final BaseCommunalViewModel baseCommunalViewModel3 = baseCommunalViewModel;
                    SemanticsPropertiesKt.setCustomActions(semanticsPropertyReceiver, CollectionsKt__CollectionsKt.listOf(customAccessibilityAction, new CustomAccessibilityAction(string2, new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$AccessibilityContainer$1$1.2
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            BaseCommunalViewModel.this.onOpenWidgetEditor(false);
                            return Boolean.TRUE;
                        }
                    })));
                    return Unit.INSTANCE;
                }
            }));
        }
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i2 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, wrapContentHeight$default);
        ComposeUiNode.Companion.getClass();
        Function0 function0 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function22 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i2))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i2, composerImpl, i2, function22);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        function2.invoke(composerImpl, Integer.valueOf((i >> 3) & 14));
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$AccessibilityContainer$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.AccessibilityContainer(BaseCommunalViewModel.this, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v14, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$2, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v28, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$8, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v8, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$1, kotlin.jvm.internal.Lambda] */
    public static final void CommunalHub(Modifier modifier, final BaseCommunalViewModel baseCommunalViewModel, final CommunalAppWidgetSection communalAppWidgetSection, RemoteViews.InteractionHandler interactionHandler, SystemUIDialogFactory systemUIDialogFactory, WidgetConfigurator widgetConfigurator, Function0 function0, Function0 function02, Composer composer, final int i, final int i2) {
        LazyGridState lazyGridState;
        Object obj;
        WidgetConfigurator widgetConfigurator2;
        Boolean bool;
        PaddingValuesImpl paddingValuesImpl;
        LayoutDirection layoutDirection;
        LazyGridState lazyGridState2;
        PaddingValuesImpl paddingValuesImpl2;
        Object obj2;
        Modifier modifier2;
        MutableState mutableState;
        CommunalHubKt$CommunalHub$nestedScrollConnection$1$1 communalHubKt$CommunalHub$nestedScrollConnection$1$1;
        long j;
        boolean z;
        Boolean bool2;
        LazyGridState lazyGridState3;
        int i3;
        BaseCommunalViewModel baseCommunalViewModel2;
        ?? r7;
        final BaseCommunalViewModel baseCommunalViewModel3;
        Boolean bool3;
        EnterTransition slideInVertically;
        ExitTransition slideOutVertically;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(980126193);
        int i4 = i2 & 1;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier3 = i4 != 0 ? companion : modifier;
        RemoteViews.InteractionHandler interactionHandler2 = (i2 & 8) != 0 ? null : interactionHandler;
        SystemUIDialogFactory systemUIDialogFactory2 = (i2 & 16) != 0 ? null : systemUIDialogFactory;
        WidgetConfigurator widgetConfigurator3 = (i2 & 32) != 0 ? null : widgetConfigurator;
        Function0 function03 = (i2 & 64) != 0 ? null : function0;
        Function0 function04 = (i2 & 128) != 0 ? null : function02;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(baseCommunalViewModel.getCommunalContent(), EmptyList.INSTANCE, composerImpl, 56);
        composerImpl.startReplaceGroup(1017044286);
        Object rememberedValue = composerImpl.rememberedValue();
        Object obj3 = Composer.Companion.Empty;
        if (rememberedValue == obj3) {
            rememberedValue = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final MutableState mutableState2 = (MutableState) rememberedValue;
        Object m = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl, false, 1017044353);
        if (m == obj3) {
            m = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateRememberedValue(m);
        }
        final MutableState mutableState3 = (MutableState) m;
        Object m2 = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl, false, 1017044434);
        if (m2 == obj3) {
            m2 = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateRememberedValue(m2);
        }
        final MutableState mutableState4 = (MutableState) m2;
        composerImpl.end(false);
        CommunalInteractor communalInteractor = baseCommunalViewModel.communalInteractor;
        LazyGridState rememberLazyGridState = LazyGridStateKt.rememberLazyGridState(communalInteractor._firstVisibleItemIndex, communalInteractor._firstVisibleItemOffset, composerImpl, 0);
        EffectsKt.LaunchedEffect(composerImpl, Unit.INSTANCE, new CommunalHubKt$CommunalHub$1(baseCommunalViewModel, null));
        List list = (List) collectAsStateWithLifecycle.getValue();
        composerImpl.startReplaceGroup(-1560096318);
        composerImpl.startReplaceGroup(1447450953);
        boolean changed = composerImpl.changed(list);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed || rememberedValue2 == obj3) {
            lazyGridState = rememberLazyGridState;
            obj = obj3;
            widgetConfigurator2 = widgetConfigurator3;
            ContentListState contentListState = new ContentListState(list, new ContentListStateKt$rememberContentListState$1$1(baseCommunalViewModel, widgetConfigurator3), new ContentListStateKt$rememberContentListState$1$2(3, baseCommunalViewModel, BaseCommunalViewModel.class, "onDeleteWidget", "onDeleteWidget(ILandroid/content/ComponentName;I)V", 0), new ContentListStateKt$rememberContentListState$1$3(1, baseCommunalViewModel, BaseCommunalViewModel.class, "onReorderWidgets", "onReorderWidgets(Ljava/util/Map;)V", 0));
            composerImpl.updateRememberedValue(contentListState);
            rememberedValue2 = contentListState;
        } else {
            lazyGridState = rememberLazyGridState;
            obj = obj3;
            widgetConfigurator2 = widgetConfigurator3;
        }
        final ContentListState contentListState2 = (ContentListState) rememberedValue2;
        composerImpl.end(false);
        composerImpl.end(false);
        final MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(baseCommunalViewModel.getReorderingWidgets(), composerImpl);
        final MutableState collectAsStateWithLifecycle3 = FlowExtKt.collectAsStateWithLifecycle(baseCommunalViewModel.selectedKey, composerImpl);
        composerImpl.startReplaceGroup(1017045013);
        Object rememberedValue3 = composerImpl.rememberedValue();
        Object obj4 = obj;
        if (rememberedValue3 == obj4) {
            rememberedValue3 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$removeButtonEnabled$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(collectAsStateWithLifecycle3.getValue() != null || ((Boolean) collectAsStateWithLifecycle2.getValue()).booleanValue());
                }
            });
            composerImpl.updateRememberedValue(rememberedValue3);
        }
        final State state = (State) rememberedValue3;
        composerImpl.end(false);
        Flow isEmptyState = baseCommunalViewModel.isEmptyState();
        Boolean bool4 = Boolean.FALSE;
        final MutableState collectAsStateWithLifecycle4 = FlowExtKt.collectAsStateWithLifecycle(isEmptyState, bool4, composerImpl, 56);
        final MutableState collectAsStateWithLifecycle5 = FlowExtKt.collectAsStateWithLifecycle(baseCommunalViewModel.isCommunalContentVisible(), Boolean.valueOf(!baseCommunalViewModel.isEditMode()), composerImpl, 8);
        boolean isEditMode = baseCommunalViewModel.isEditMode();
        IntSize intSize = (IntSize) mutableState3.getValue();
        composerImpl.startReplaceGroup(189392001);
        composerImpl.startReplaceGroup(-857934250);
        if (!isEditMode || intSize == null) {
            bool = bool4;
            PaddingValuesImpl paddingValuesImpl3 = Dimensions.ButtonPadding;
            paddingValuesImpl = new PaddingValuesImpl(Dimensions.Companion.m797getItemSpacingD9Ej5fM(), getHubDimensions(composerImpl).m796getGridTopSpacingD9Ej5fM(), Dimensions.Companion.m797getItemSpacingD9Ej5fM(), 0);
            composerImpl.end(false);
            composerImpl.end(false);
        } else {
            composerImpl.end(false);
            Context context = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
            Density density = (Density) composerImpl.consume(CompositionLocalsKt.LocalDensity);
            WindowMetricsCalculator.Companion.getClass();
            float mo48toDpu2uoSUM = density.mo48toDpu2uoSUM(WindowMetricsCalculator.Companion.getOrCreate().computeCurrentWindowMetrics(context)._bounds.toRect().height());
            PaddingValuesImpl paddingValuesImpl4 = Dimensions.ButtonPadding;
            IWindowManager iWindowManager = DensityUtils.windowManagerService;
            bool = bool4;
            float mo48toDpu2uoSUM2 = density.mo48toDpu2uoSUM((int) (intSize.packedValue & 4294967295L)) + DensityUtils.Companion.m799getAdjustedDpu2uoSUM(27);
            float m796getGridTopSpacingD9Ej5fM = getHubDimensions(composerImpl).m796getGridTopSpacingD9Ej5fM() + ((mo48toDpu2uoSUM - mo48toDpu2uoSUM2) - (getHubDimensions(composerImpl).m796getGridTopSpacingD9Ej5fM() + DensityUtils.Companion.m799getAdjustedDpu2uoSUM(530)));
            float f = 2;
            Dp dp = new Dp(m796getGridTopSpacingD9Ej5fM / f);
            Dp dp2 = new Dp(Dimensions.Companion.m797getItemSpacingD9Ej5fM() / f);
            if (dp.compareTo(dp2) < 0) {
                dp = dp2;
            }
            float f2 = dp.value;
            PaddingValuesImpl paddingValuesImpl5 = new PaddingValuesImpl(Dimensions.Companion.m797getItemSpacingD9Ej5fM(), mo48toDpu2uoSUM2 + f2, Dimensions.Companion.m797getItemSpacingD9Ej5fM(), f2);
            composerImpl.end(false);
            paddingValuesImpl = paddingValuesImpl5;
        }
        composerImpl.startReplaceGroup(1906284573);
        Density density2 = (Density) composerImpl.consume(CompositionLocalsKt.LocalDensity);
        StaticProvidableCompositionLocal staticProvidableCompositionLocal = CompositionLocalsKt.LocalLayoutDirection;
        float mo51toPx0680j_4 = density2.mo51toPx0680j_4(PaddingKt.calculateStartPadding(paddingValuesImpl, (LayoutDirection) composerImpl.consume(staticProvidableCompositionLocal)));
        float mo51toPx0680j_42 = density2.mo51toPx0680j_4(paddingValuesImpl.top);
        composerImpl.end(false);
        final long floatToRawIntBits = (Float.floatToRawIntBits(mo51toPx0680j_4) << 32) | (4294967295L & Float.floatToRawIntBits(mo51toPx0680j_42));
        final LazyGridState lazyGridState4 = lazyGridState;
        ObserveScrollEffect(lazyGridState4, baseCommunalViewModel, composerImpl, 64);
        Context context2 = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
        WindowMetricsCalculator.Companion.getClass();
        final int width = WindowMetricsCalculator.Companion.getOrCreate().computeCurrentWindowMetrics(context2)._bounds.toRect().width();
        final LayoutDirection layoutDirection2 = (LayoutDirection) composerImpl.consume(staticProvidableCompositionLocal);
        if (baseCommunalViewModel.isEditMode()) {
            composerImpl.startReplaceGroup(1017045852);
            ObserveNewWidgetAddedEffect((List) collectAsStateWithLifecycle.getValue(), lazyGridState4, baseCommunalViewModel, composerImpl, 520);
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceGroup(1017045940);
            ScrollOnUpdatedLiveContentEffect((List) collectAsStateWithLifecycle.getValue(), lazyGridState4, composerImpl, 8);
            composerImpl.end(false);
        }
        composerImpl.startReplaceGroup(1017046041);
        Object rememberedValue4 = composerImpl.rememberedValue();
        if (rememberedValue4 == obj4) {
            rememberedValue4 = new NestedScrollConnection() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$nestedScrollConnection$1$1
                @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
                /* renamed from: onPreScroll-OzD1aCk */
                public final long mo139onPreScrollOzD1aCk(long j2, int i5) {
                    StateFlowImpl stateFlowImpl = BaseCommunalViewModel.this._isNestedScrolling;
                    if (((Boolean) stateFlowImpl.getValue()).booleanValue()) {
                        return 0L;
                    }
                    stateFlowImpl.updateState(null, Boolean.TRUE);
                    return 0L;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue4);
        }
        CommunalHubKt$CommunalHub$nestedScrollConnection$1$1 communalHubKt$CommunalHub$nestedScrollConnection$1$12 = (CommunalHubKt$CommunalHub$nestedScrollConnection$1$1) rememberedValue4;
        composerImpl.end(false);
        final String stringResource = StringResources_androidKt.stringResource(R.string.accessibility_content_description_for_communal_hub, composerImpl);
        composerImpl.startReplaceGroup(1017046549);
        boolean changed2 = composerImpl.changed(stringResource);
        Object rememberedValue5 = composerImpl.rememberedValue();
        if (changed2 || rememberedValue5 == obj4) {
            rememberedValue5 = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj5) {
                    SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj5;
                    KProperty[] kPropertyArr = SemanticsProperties_androidKt.$$delegatedProperties;
                    SemanticsPropertyKey semanticsPropertyKey = SemanticsPropertiesAndroid.TestTagsAsResourceId;
                    KProperty kProperty = SemanticsProperties_androidKt.$$delegatedProperties[0];
                    semanticsPropertyKey.setValue(semanticsPropertyReceiver, Boolean.TRUE);
                    SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, stringResource);
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue5);
        }
        composerImpl.end(false);
        Modifier then = TestTagKt.testTag(SemanticsModifierKt.semantics(modifier3, false, (Function1) rememberedValue5), "communal_hub").then(SizeKt.FillWholeMaxSize);
        if (baseCommunalViewModel.isEditMode()) {
            Object[] objArr = {layoutDirection2, lazyGridState4, new Offset(floatToRawIntBits), contentListState2};
            paddingValuesImpl2 = paddingValuesImpl;
            layoutDirection = layoutDirection2;
            obj2 = obj4;
            communalHubKt$CommunalHub$nestedScrollConnection$1$1 = communalHubKt$CommunalHub$nestedScrollConnection$1$12;
            lazyGridState2 = lazyGridState4;
            modifier2 = modifier3;
            mutableState = collectAsStateWithLifecycle3;
            j = floatToRawIntBits;
            PointerInputEventHandler pointerInputEventHandler = new PointerInputEventHandler() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$3$1
                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                    final long j2 = floatToRawIntBits;
                    final LazyGridState lazyGridState5 = lazyGridState4;
                    final LayoutDirection layoutDirection3 = LayoutDirection.this;
                    final int i5 = width;
                    final BaseCommunalViewModel baseCommunalViewModel4 = baseCommunalViewModel;
                    final ContentListState contentListState3 = contentListState2;
                    Object observeTaps$default = PointerInputScopeExtKt.observeTaps$default(pointerInputScope, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$3$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj5) {
                            Object obj6;
                            long j3 = ((Offset) obj5).packedValue;
                            long m314minusMKHz9U = Offset.m314minusMKHz9U((Float.floatToRawIntBits(LayoutDirection.this == LayoutDirection.Rtl ? i5 - Float.intBitsToFloat((int) (j3 >> 32)) : Float.intBitsToFloat((int) (j3 >> 32))) << 32) | (Float.floatToRawIntBits(Float.intBitsToFloat((int) (j3 & 4294967295L))) & 4294967295L), j2);
                            Iterator it = ((LazyGridMeasureResult) lazyGridState5.getLayoutInfo()).visibleItemsInfo.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    obj6 = null;
                                    break;
                                }
                                obj6 = it.next();
                                LazyGridMeasuredItem lazyGridMeasuredItem = (LazyGridMeasuredItem) ((LazyGridItemInfo) obj6);
                                IntRect m681IntRectVbeCjmY = IntRectKt.m681IntRectVbeCjmY(lazyGridMeasuredItem.offset, lazyGridMeasuredItem.size);
                                if (new Rect(m681IntRectVbeCjmY.left, m681IntRectVbeCjmY.top, m681IntRectVbeCjmY.right, m681IntRectVbeCjmY.bottom).m319containsk4lQ0M(m314minusMKHz9U)) {
                                    break;
                                }
                            }
                            LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) obj6;
                            Integer valueOf = lazyGridItemInfo != null ? Integer.valueOf(((LazyGridMeasuredItem) lazyGridItemInfo).index) : null;
                            baseCommunalViewModel4.setSelectedKey(valueOf != null ? CommunalHubKt.access$keyAtIndexIfEditable(valueOf.intValue(), contentListState3.list) : null);
                            return Unit.INSTANCE;
                        }
                    }, continuation, 3);
                    return observeTaps$default == CoroutineSingletons.COROUTINE_SUSPENDED ? observeTaps$default : Unit.INSTANCE;
                }
            };
            PointerEvent pointerEvent = SuspendingPointerInputFilterKt.EmptyPointerEvent;
            then = then.then(new SuspendPointerInputElement(null, null, objArr, pointerInputEventHandler, 3));
        } else {
            layoutDirection = layoutDirection2;
            lazyGridState2 = lazyGridState4;
            paddingValuesImpl2 = paddingValuesImpl;
            obj2 = obj4;
            modifier2 = modifier3;
            mutableState = collectAsStateWithLifecycle3;
            communalHubKt$CommunalHub$nestedScrollConnection$1$1 = communalHubKt$CommunalHub$nestedScrollConnection$1$12;
            j = floatToRawIntBits;
        }
        if (baseCommunalViewModel.isEditMode()) {
            z = true;
        } else {
            Modifier nestedScroll = NestedScrollModifierKt.nestedScroll(companion, communalHubKt$CommunalHub$nestedScrollConnection$1$1, null);
            z = true;
            then = then.then(SuspendingPointerInputFilterKt.pointerInput(nestedScroll, baseCommunalViewModel, new CommunalHubKt$Umo$1(baseCommunalViewModel, 1)));
        }
        Modifier modifier4 = then;
        if (baseCommunalViewModel.isEditMode() || ((Boolean) collectAsStateWithLifecycle4.getValue()).booleanValue()) {
            bool2 = bool;
            lazyGridState3 = lazyGridState2;
        } else {
            final LazyGridState lazyGridState5 = lazyGridState2;
            Object[] objArr2 = {lazyGridState5, new Offset(j), (List) collectAsStateWithLifecycle.getValue(), (LayoutCoordinates) mutableState4.getValue()};
            final LayoutDirection layoutDirection3 = layoutDirection;
            final long j2 = j;
            bool2 = bool;
            lazyGridState3 = lazyGridState5;
            PointerInputEventHandler pointerInputEventHandler2 = new PointerInputEventHandler() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$5$1
                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                    final MutableState mutableState5 = collectAsStateWithLifecycle;
                    final MutableState mutableState6 = mutableState4;
                    final int i5 = width;
                    final long j3 = j2;
                    final BaseCommunalViewModel baseCommunalViewModel4 = BaseCommunalViewModel.this;
                    final LayoutDirection layoutDirection4 = layoutDirection3;
                    final LazyGridState lazyGridState6 = lazyGridState5;
                    Object detectLongPressGesture$default = PointerInputScopeExtKt.detectLongPressGesture$default(pointerInputScope, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$5$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        /* JADX WARN: Removed duplicated region for block: B:18:0x00ab  */
                        /* JADX WARN: Removed duplicated region for block: B:20:0x00bd  */
                        @Override // kotlin.jvm.functions.Function1
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final java.lang.Object invoke(java.lang.Object r12) {
                            /*
                                r11 = this;
                                androidx.compose.ui.geometry.Offset r12 = (androidx.compose.ui.geometry.Offset) r12
                                long r0 = r12.packedValue
                                androidx.compose.runtime.MutableState r12 = r2
                                java.lang.Object r12 = r12.getValue()
                                androidx.compose.ui.layout.LayoutCoordinates r12 = (androidx.compose.ui.layout.LayoutCoordinates) r12
                                r2 = 0
                                if (r12 == 0) goto L58
                                androidx.compose.ui.unit.LayoutDirection r3 = r3
                                int r4 = r4
                                long r5 = r5
                                androidx.compose.ui.unit.LayoutDirection r7 = androidx.compose.ui.unit.LayoutDirection.Rtl
                                r8 = 32
                                if (r3 != r7) goto L25
                                float r3 = (float) r4
                                long r9 = r0 >> r8
                                int r4 = (int) r9
                                float r4 = java.lang.Float.intBitsToFloat(r4)
                                float r3 = r3 - r4
                                goto L2c
                            L25:
                                long r3 = r0 >> r8
                                int r3 = (int) r3
                                float r3 = java.lang.Float.intBitsToFloat(r3)
                            L2c:
                                r9 = 4294967295(0xffffffff, double:2.1219957905E-314)
                                long r0 = r0 & r9
                                int r0 = (int) r0
                                float r0 = java.lang.Float.intBitsToFloat(r0)
                                int r1 = java.lang.Float.floatToRawIntBits(r3)
                                long r3 = (long) r1
                                int r0 = java.lang.Float.floatToRawIntBits(r0)
                                long r0 = (long) r0
                                long r3 = r3 << r8
                                long r0 = r0 & r9
                                long r0 = r0 | r3
                                r3 = 0
                                long r3 = r12.mo486localToWindowMKHz9U(r3)
                                long r0 = androidx.compose.ui.geometry.Offset.m314minusMKHz9U(r0, r3)
                                long r0 = androidx.compose.ui.geometry.Offset.m314minusMKHz9U(r0, r5)
                                androidx.compose.ui.geometry.Offset r12 = new androidx.compose.ui.geometry.Offset
                                r12.<init>(r0)
                                goto L59
                            L58:
                                r12 = r2
                            L59:
                                if (r12 == 0) goto La8
                                androidx.compose.foundation.lazy.grid.LazyGridState r0 = r7
                                androidx.compose.foundation.lazy.grid.LazyGridLayoutInfo r0 = r0.getLayoutInfo()
                                androidx.compose.foundation.lazy.grid.LazyGridMeasureResult r0 = (androidx.compose.foundation.lazy.grid.LazyGridMeasureResult) r0
                                java.util.List r0 = r0.visibleItemsInfo
                                java.util.Iterator r0 = r0.iterator()
                            L69:
                                boolean r1 = r0.hasNext()
                                if (r1 == 0) goto L9a
                                java.lang.Object r1 = r0.next()
                                r3 = r1
                                androidx.compose.foundation.lazy.grid.LazyGridItemInfo r3 = (androidx.compose.foundation.lazy.grid.LazyGridItemInfo) r3
                                androidx.compose.foundation.lazy.grid.LazyGridMeasuredItem r3 = (androidx.compose.foundation.lazy.grid.LazyGridMeasuredItem) r3
                                long r4 = r3.offset
                                long r6 = r3.size
                                androidx.compose.ui.unit.IntRect r3 = androidx.compose.ui.unit.IntRectKt.m681IntRectVbeCjmY(r4, r6)
                                androidx.compose.ui.geometry.Rect r4 = new androidx.compose.ui.geometry.Rect
                                int r5 = r3.left
                                float r5 = (float) r5
                                int r6 = r3.top
                                float r6 = (float) r6
                                int r7 = r3.right
                                float r7 = (float) r7
                                int r3 = r3.bottom
                                float r3 = (float) r3
                                r4.<init>(r5, r6, r7, r3)
                                long r5 = r12.packedValue
                                boolean r3 = r4.m319containsk4lQ0M(r5)
                                if (r3 == 0) goto L69
                                goto L9b
                            L9a:
                                r1 = r2
                            L9b:
                                androidx.compose.foundation.lazy.grid.LazyGridItemInfo r1 = (androidx.compose.foundation.lazy.grid.LazyGridItemInfo) r1
                                if (r1 == 0) goto La8
                                androidx.compose.foundation.lazy.grid.LazyGridMeasuredItem r1 = (androidx.compose.foundation.lazy.grid.LazyGridMeasuredItem) r1
                                int r12 = r1.index
                                java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
                                goto La9
                            La8:
                                r12 = r2
                            La9:
                                if (r12 == 0) goto Lbb
                                androidx.compose.runtime.State r0 = r8
                                java.lang.Object r0 = r0.getValue()
                                java.util.List r0 = (java.util.List) r0
                                int r12 = r12.intValue()
                                java.lang.String r2 = com.android.systemui.communal.ui.compose.CommunalHubKt.access$keyAtIndexIfEditable(r12, r0)
                            Lbb:
                                if (r2 == 0) goto Lc7
                                com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel r12 = com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel.this
                                r12.onLongClick()
                                com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel r11 = com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel.this
                                r11.setSelectedKey(r2)
                            Lc7:
                                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                                return r11
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$5$1.AnonymousClass1.invoke(java.lang.Object):java.lang.Object");
                        }
                    }, continuation);
                    return detectLongPressGesture$default == CoroutineSingletons.COROUTINE_SUSPENDED ? detectLongPressGesture$default : Unit.INSTANCE;
                }
            };
            PointerEvent pointerEvent2 = SuspendingPointerInputFilterKt.EmptyPointerEvent;
            modifier4 = modifier4.then(new SuspendPointerInputElement(null, null, objArr2, pointerInputEventHandler2, 3));
        }
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i5 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, modifier4);
        ComposeUiNode.Companion.getClass();
        Function0 function05 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function05);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        final PaddingValuesImpl paddingValuesImpl6 = paddingValuesImpl2;
        final MutableState mutableState5 = mutableState;
        final long j3 = j;
        Object obj5 = obj2;
        final LazyGridState lazyGridState6 = lazyGridState3;
        final Modifier modifier5 = modifier2;
        final WidgetConfigurator widgetConfigurator4 = widgetConfigurator2;
        final MutableState mutableState6 = mutableState;
        final RemoteViews.InteractionHandler interactionHandler3 = interactionHandler2;
        AccessibilityContainer(baseCommunalViewModel, ComposableLambdaKt.rememberComposableLambda(1416359000, new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r2v14, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$1$3, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj6, Object obj7) {
                Composer composer2 = (Composer) obj6;
                if ((((Number) obj7).intValue() & 11) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                if (BaseCommunalViewModel.this.isEditMode() || !((Boolean) collectAsStateWithLifecycle4.getValue()).booleanValue()) {
                    ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                    composerImpl3.startReplaceGroup(2136039145);
                    final int mo51toPx0680j_43 = (int) ((Density) composerImpl3.consume(CompositionLocalsKt.LocalDensity)).mo51toPx0680j_4(Dimensions.SlideOffsetY);
                    boolean booleanValue = ((Boolean) collectAsStateWithLifecycle5.getValue()).booleanValue();
                    EasingKt$$ExternalSyntheticLambda0 easingKt$$ExternalSyntheticLambda0 = EasingKt.LinearEasing;
                    EnterTransition fadeIn$default = EnterExitTransitionKt.fadeIn$default(new TweenSpec(83, 83, easingKt$$ExternalSyntheticLambda0), 2);
                    Easings$fromInterpolator$1 easings$fromInterpolator$1 = Easings.Emphasized;
                    TweenSpec tween$default = AnimationSpecKt.tween$default(1000, 0, easings$fromInterpolator$1, 2);
                    composerImpl3.startReplaceGroup(2136039772);
                    boolean changed3 = composerImpl3.changed(mo51toPx0680j_43);
                    Object rememberedValue6 = composerImpl3.rememberedValue();
                    Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
                    if (changed3 || rememberedValue6 == composer$Companion$Empty$1) {
                        rememberedValue6 = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj8) {
                                ((Number) obj8).intValue();
                                return Integer.valueOf(-mo51toPx0680j_43);
                            }
                        };
                        composerImpl3.updateRememberedValue(rememberedValue6);
                    }
                    composerImpl3.end(false);
                    EnterTransition plus = fadeIn$default.plus(EnterExitTransitionKt.slideInVertically(tween$default, (Function1) rememberedValue6));
                    ExitTransition fadeOut$default = EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(167, 0, easingKt$$ExternalSyntheticLambda0, 2), 2);
                    TweenSpec tween$default2 = AnimationSpecKt.tween$default(1000, 0, easings$fromInterpolator$1, 2);
                    composerImpl3.startReplaceGroup(2136040203);
                    boolean changed4 = composerImpl3.changed(mo51toPx0680j_43);
                    Object rememberedValue7 = composerImpl3.rememberedValue();
                    if (changed4 || rememberedValue7 == composer$Companion$Empty$1) {
                        rememberedValue7 = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$1$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj8) {
                                ((Number) obj8).intValue();
                                return Integer.valueOf(-mo51toPx0680j_43);
                            }
                        };
                        composerImpl3.updateRememberedValue(rememberedValue7);
                    }
                    composerImpl3.end(false);
                    ExitTransition plus2 = fadeOut$default.plus(EnterExitTransitionKt.slideOutVertically(tween$default2, (Function1) rememberedValue7));
                    FillElement fillElement = SizeKt.FillWholeMaxSize;
                    final BaseCommunalViewModel baseCommunalViewModel4 = BaseCommunalViewModel.this;
                    final PaddingValues paddingValues = paddingValuesImpl6;
                    final State state2 = mutableState5;
                    final int i6 = width;
                    final long j4 = j3;
                    final LazyGridState lazyGridState7 = lazyGridState6;
                    final ContentListState contentListState3 = contentListState2;
                    final WidgetConfigurator widgetConfigurator5 = widgetConfigurator4;
                    final RemoteViews.InteractionHandler interactionHandler4 = interactionHandler3;
                    final CommunalAppWidgetSection communalAppWidgetSection2 = communalAppWidgetSection;
                    final State state3 = collectAsStateWithLifecycle;
                    final MutableState mutableState7 = mutableState4;
                    final State state4 = state;
                    final MutableState mutableState8 = mutableState2;
                    AnimatedVisibilityKt.AnimatedVisibility(booleanValue, fillElement, plus, plus2, null, ComposableLambdaKt.rememberComposableLambda(-1337046828, new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$1.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj8, Object obj9, Object obj10) {
                            Composer composer3 = (Composer) obj9;
                            ((Number) obj10).intValue();
                            OpaqueKey opaqueKey3 = ComposerKt.invocation;
                            BaseCommunalViewModel baseCommunalViewModel5 = BaseCommunalViewModel.this;
                            PaddingValues paddingValues2 = paddingValues;
                            State state5 = state2;
                            int i7 = i6;
                            long j5 = j4;
                            LazyGridState lazyGridState8 = lazyGridState7;
                            ContentListState contentListState4 = contentListState3;
                            WidgetConfigurator widgetConfigurator6 = widgetConfigurator5;
                            RemoteViews.InteractionHandler interactionHandler5 = interactionHandler4;
                            CommunalAppWidgetSection communalAppWidgetSection3 = communalAppWidgetSection2;
                            State state6 = state3;
                            final MutableState mutableState9 = mutableState7;
                            final State state7 = state4;
                            final MutableState mutableState10 = mutableState8;
                            Modifier.Companion companion2 = Modifier.Companion.$$INSTANCE;
                            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3);
                            ComposerImpl composerImpl4 = (ComposerImpl) composer3;
                            PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl4.currentCompositionLocalScope();
                            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer3, companion2);
                            ComposeUiNode.Companion.getClass();
                            Function0 function06 = ComposeUiNode.Companion.Constructor;
                            composerImpl4.startReusableNode();
                            if (composerImpl4.inserting) {
                                composerImpl4.createNode(function06);
                            } else {
                                composerImpl4.useNode();
                            }
                            Updater.m259setimpl(composer3, maybeCachedBoxMeasurePolicy2, ComposeUiNode.Companion.SetMeasurePolicy);
                            Updater.m259setimpl(composer3, currentCompositionLocalScope2, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                            Function2 function22 = ComposeUiNode.Companion.SetCompositeKeyHash;
                            if (composerImpl4.inserting || !Intrinsics.areEqual(composerImpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl4, currentCompositeKeyHash, function22);
                            }
                            Updater.m259setimpl(composer3, materializeModifier2, ComposeUiNode.Companion.SetModifier);
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            List list2 = (List) state6.getValue();
                            composerImpl4.startReplaceGroup(1454515174);
                            Object rememberedValue8 = composerImpl4.rememberedValue();
                            Composer$Companion$Empty$1 composer$Companion$Empty$12 = Composer.Companion.Empty;
                            if (rememberedValue8 == composer$Companion$Empty$12) {
                                rememberedValue8 = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$1$3$1$1$1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj11) {
                                        MutableState.this.setValue((LayoutCoordinates) obj11);
                                        return Unit.INSTANCE;
                                    }
                                };
                                composerImpl4.updateRememberedValue(rememberedValue8);
                            }
                            Function1 function1 = (Function1) rememberedValue8;
                            Object m3 = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl4, false, 1454515258);
                            if (m3 == composer$Companion$Empty$12) {
                                m3 = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$1$3$1$2$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj11) {
                                        long j6 = ((Offset) obj11).packedValue;
                                        boolean booleanValue2 = ((Boolean) State.this.getValue()).booleanValue();
                                        LayoutCoordinates layoutCoordinates = (LayoutCoordinates) mutableState9.getValue();
                                        return Boolean.valueOf(CommunalHubKt.m795isPointerWithinEnabledRemoveButton_UaWb3I(booleanValue2, layoutCoordinates != null ? new Offset(Offset.m315plusMKHz9U(layoutCoordinates.mo486localToWindowMKHz9U(0L), j6)) : null, (LayoutCoordinates) mutableState10.getValue()));
                                    }
                                };
                                composerImpl4.updateRememberedValue(m3);
                            }
                            composerImpl4.end(false);
                            CommunalHubKt.m794access$CommunalHubLazyGrid_JG6Y(boxScopeInstance, list2, baseCommunalViewModel5, paddingValues2, state5, i7, j5, lazyGridState8, contentListState4, function1, (Function1) m3, widgetConfigurator6, interactionHandler5, communalAppWidgetSection3, composer3, 939524678, 4614);
                            composerImpl4.end(true);
                            return Unit.INSTANCE;
                        }
                    }, composerImpl3), composerImpl3, 196656, 16);
                    composerImpl3.end(false);
                } else {
                    ComposerImpl composerImpl4 = (ComposerImpl) composer2;
                    composerImpl4.startReplaceGroup(2136039034);
                    CommunalHubKt.access$EmptyStateCta(paddingValuesImpl6, BaseCommunalViewModel.this, composerImpl4, 64);
                    composerImpl4.end(false);
                }
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 56);
        composerImpl.startReplaceGroup(-162559134);
        if (function03 == null || function04 == null) {
            i3 = 56;
            baseCommunalViewModel2 = baseCommunalViewModel;
            r7 = 0;
        } else {
            boolean z2 = baseCommunalViewModel.isEditMode() && ((Boolean) collectAsStateWithLifecycle5.getValue()).booleanValue();
            EasingKt$$ExternalSyntheticLambda0 easingKt$$ExternalSyntheticLambda0 = EasingKt.LinearEasing;
            EnterTransition fadeIn$default = EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(250, 0, easingKt$$ExternalSyntheticLambda0, 2), 2);
            Easings$fromInterpolator$1 easings$fromInterpolator$1 = Easings.Emphasized;
            slideInVertically = EnterExitTransitionKt.slideInVertically(AnimationSpecKt.tween$default(1000, 0, easings$fromInterpolator$1, 2), new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideInVertically$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj6) {
                    return Integer.valueOf((-((Number) obj6).intValue()) / 2);
                }
            });
            EnterTransition plus = fadeIn$default.plus(slideInVertically);
            ExitTransition fadeOut$default = EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(167, 0, easingKt$$ExternalSyntheticLambda0, 2), 2);
            slideOutVertically = EnterExitTransitionKt.slideOutVertically(AnimationSpecKt.tween$default(1000, 0, easings$fromInterpolator$1, 2), new Function1() { // from class: androidx.compose.animation.EnterExitTransitionKt$slideOutVertically$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj6) {
                    return Integer.valueOf((-((Number) obj6).intValue()) / 2);
                }
            });
            ExitTransition plus2 = fadeOut$default.plus(slideOutVertically);
            final Function0 function06 = function03;
            final Function0 function07 = function04;
            r7 = 0;
            i3 = 56;
            baseCommunalViewModel2 = baseCommunalViewModel;
            AnimatedVisibilityKt.AnimatedVisibility(z2, null, plus, plus2, null, ComposableLambdaKt.rememberComposableLambda(-24647958, new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj6, Object obj7, Object obj8) {
                    ((Number) obj8).intValue();
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    boolean booleanValue = ((Boolean) state.getValue()).booleanValue();
                    final State state2 = mutableState6;
                    final ContentListState contentListState3 = contentListState2;
                    final BaseCommunalViewModel baseCommunalViewModel4 = baseCommunalViewModel;
                    Function0 function08 = new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Integer num;
                            String str = (String) State.this.getValue();
                            if (str != null) {
                                ListIterator listIterator = contentListState3.list.listIterator();
                                int i6 = 0;
                                while (true) {
                                    if (!listIterator.hasNext()) {
                                        i6 = -1;
                                        break;
                                    }
                                    if (Intrinsics.areEqual(((CommunalContentModel) listIterator.next()).getKey(), str)) {
                                        break;
                                    }
                                    i6++;
                                }
                                num = Integer.valueOf(i6);
                            } else {
                                num = null;
                            }
                            if (num != null) {
                                ContentListState contentListState4 = contentListState3;
                                BaseCommunalViewModel baseCommunalViewModel5 = baseCommunalViewModel4;
                                contentListState4.onRemove(num.intValue());
                                contentListState4.onSaveList(null, null, null);
                                baseCommunalViewModel5.setSelectedKey(null);
                            }
                            return Unit.INSTANCE;
                        }
                    };
                    ComposerImpl composerImpl2 = (ComposerImpl) ((Composer) obj7);
                    composerImpl2.startReplaceGroup(2136042544);
                    final MutableState mutableState7 = mutableState3;
                    Object rememberedValue6 = composerImpl2.rememberedValue();
                    Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
                    if (rememberedValue6 == composer$Companion$Empty$1) {
                        rememberedValue6 = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$2$2$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj9) {
                                MutableState.this.setValue(new IntSize(((IntSize) obj9).packedValue));
                                return Unit.INSTANCE;
                            }
                        };
                        composerImpl2.updateRememberedValue(rememberedValue6);
                    }
                    Function1 function1 = (Function1) rememberedValue6;
                    composerImpl2.end(false);
                    composerImpl2.startReplaceGroup(2136042615);
                    final MutableState mutableState8 = mutableState2;
                    Object rememberedValue7 = composerImpl2.rememberedValue();
                    if (rememberedValue7 == composer$Companion$Empty$1) {
                        rememberedValue7 = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$2$3$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj9) {
                                MutableState.this.setValue((LayoutCoordinates) obj9);
                                return Unit.INSTANCE;
                            }
                        };
                        composerImpl2.updateRememberedValue(rememberedValue7);
                    }
                    composerImpl2.end(false);
                    CommunalHubKt.access$Toolbar(booleanValue, function08, function1, (Function1) rememberedValue7, Function0.this, function07, composerImpl2, 3456);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 196608, 18);
        }
        composerImpl.end(r7);
        composerImpl.startReplaceGroup(-162557490);
        if (!(baseCommunalViewModel2 instanceof CommunalViewModel) || systemUIDialogFactory2 == null) {
            baseCommunalViewModel3 = baseCommunalViewModel2;
            bool3 = bool2;
        } else {
            CommunalViewModel communalViewModel = (CommunalViewModel) baseCommunalViewModel2;
            Boolean bool5 = bool2;
            MutableState collectAsStateWithLifecycle6 = FlowExtKt.collectAsStateWithLifecycle(communalViewModel.isEnableWidgetDialogShowing, bool5, composerImpl, i3);
            MutableState collectAsStateWithLifecycle7 = FlowExtKt.collectAsStateWithLifecycle(communalViewModel.isEnableWorkProfileDialogShowing, bool5, composerImpl, i3);
            baseCommunalViewModel3 = baseCommunalViewModel2;
            EnableWidgetDialogKt.EnableWidgetDialog(((Boolean) collectAsStateWithLifecycle6.getValue()).booleanValue(), systemUIDialogFactory2, StringResources_androidKt.stringResource(R.string.dialog_title_to_allow_any_widget, composerImpl), StringResources_androidKt.stringResource(R.string.button_text_to_open_settings, composerImpl), new CommunalHubKt$CommunalHub$6$3(0, baseCommunalViewModel, CommunalViewModel.class, "onEnableWidgetDialogConfirm", "onEnableWidgetDialogConfirm()V", 0), new CommunalHubKt$CommunalHub$6$4(0, baseCommunalViewModel, CommunalViewModel.class, "onEnableWidgetDialogCancel", "onEnableWidgetDialogCancel()V", 0), composerImpl, 64);
            bool3 = bool5;
            EnableWidgetDialogKt.EnableWidgetDialog(((Boolean) collectAsStateWithLifecycle7.getValue()).booleanValue(), systemUIDialogFactory2, StringResources_androidKt.stringResource(R.string.work_mode_off_title, composerImpl), StringResources_androidKt.stringResource(R.string.work_mode_turn_on, composerImpl), new CommunalHubKt$CommunalHub$6$5(0, baseCommunalViewModel, CommunalViewModel.class, "onEnableWorkProfileDialogConfirm", "onEnableWorkProfileDialogConfirm()V", 0), new CommunalHubKt$CommunalHub$6$6(0, baseCommunalViewModel, CommunalViewModel.class, "onEnableWorkProfileDialogCancel", "onEnableWorkProfileDialogCancel()V", 0), composerImpl, 64);
        }
        composerImpl.end(r7);
        composerImpl.startReplaceGroup(1017057248);
        if ((baseCommunalViewModel3 instanceof CommunalEditModeViewModel) && ((Boolean) FlowExtKt.collectAsStateWithLifecycle(((CommunalEditModeViewModel) baseCommunalViewModel3).showDisclaimer, bool3, composerImpl, 56).getValue()).booleanValue()) {
            Object rememberedValue6 = composerImpl.rememberedValue();
            if (rememberedValue6 == obj5) {
                rememberedValue6 = PredictiveBackHandlerKt$$ExternalSyntheticOutline0.m(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerImpl), composerImpl);
            }
            final ContextScope contextScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue6).coroutineScope;
            final SheetState rememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(r7, 3, composerImpl);
            ModalBottomSheetKt.m215ModalBottomSheetdYc4hso(new CommunalHubKt$CommunalHub$6$7(0, baseCommunalViewModel, CommunalEditModeViewModel.class, "onDisclaimerDismissed", "onDisclaimerDismissed()V", 0), null, rememberModalBottomSheetState, 0.0f, null, ((AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme)).surfaceContainer, 0L, 0.0f, 0L, null, null, null, ComposableLambdaKt.rememberComposableLambda(1510509506, new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$8
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj6, Object obj7, Object obj8) {
                    Composer composer2 = (Composer) obj7;
                    if ((((Number) obj8).intValue() & 81) == 16) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                        if (composerImpl2.getSkipping()) {
                            composerImpl2.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    final CoroutineScope coroutineScope = contextScope;
                    final SheetState sheetState = rememberModalBottomSheetState;
                    final BaseCommunalViewModel baseCommunalViewModel4 = baseCommunalViewModel3;
                    CommunalHubKt.access$DisclaimerBottomSheetContent(new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$8.1

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$6$8$1$1, reason: invalid class name and collision with other inner class name */
                        final class C00631 extends SuspendLambda implements Function2 {
                            final /* synthetic */ SheetState $sheetState;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public C00631(SheetState sheetState, Continuation continuation) {
                                super(2, continuation);
                                this.$sheetState = sheetState;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation create(Object obj, Continuation continuation) {
                                return new C00631(this.$sheetState, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return ((C00631) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    SheetState sheetState = this.$sheetState;
                                    this.label = 1;
                                    if (sheetState.hide(this) == coroutineSingletons) {
                                        return coroutineSingletons;
                                    }
                                } else {
                                    if (i != 1) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(obj);
                                }
                                return Unit.INSTANCE;
                            }
                        }

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            StandaloneCoroutine launch$default = BuildersKt.launch$default(CoroutineScope.this, null, null, new C00631(sheetState, null), 3);
                            final SheetState sheetState2 = sheetState;
                            final BaseCommunalViewModel baseCommunalViewModel5 = baseCommunalViewModel4;
                            launch$default.invokeOnCompletion(new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt.CommunalHub.6.8.1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj9) {
                                    if (!SheetState.this.isVisible()) {
                                        ((CommunalEditModeViewModel) baseCommunalViewModel5).communalInteractor.setDisclaimerDismissed();
                                    }
                                    return Unit.INSTANCE;
                                }
                            });
                            return Unit.INSTANCE;
                        }
                    }, composer2, 0);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 805306368, 384, 3546);
        }
        composerImpl.end(r7);
        composerImpl.end(true);
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final RemoteViews.InteractionHandler interactionHandler4 = interactionHandler2;
            final SystemUIDialogFactory systemUIDialogFactory3 = systemUIDialogFactory2;
            final WidgetConfigurator widgetConfigurator5 = widgetConfigurator2;
            final Function0 function08 = function03;
            final Function0 function09 = function04;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHub$7
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj6, Object obj7) {
                    ((Number) obj7).intValue();
                    CommunalHubKt.CommunalHub(Modifier.this, baseCommunalViewModel, communalAppWidgetSection, interactionHandler4, systemUIDialogFactory3, widgetConfigurator5, function08, function09, (Composer) obj6, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r0v9, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$CtaTileInViewModeContent$1, kotlin.jvm.internal.Lambda] */
    public static final void CtaTileInViewModeContent(final BaseCommunalViewModel baseCommunalViewModel, final Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1975660102);
        if ((i2 & 2) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final AndroidColorScheme androidColorScheme = (AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme);
        CardColors m200cardColorsro_MJ88 = CardDefaults.m200cardColorsro_MJ88(androidColorScheme.primary, androidColorScheme.onPrimary, composerImpl, 0, 12);
        IWindowManager iWindowManager = DensityUtils.windowManagerService;
        CardKt.Card(modifier, RoundedCornerShapeKt.m149RoundedCornerShapea9UjIt4(DensityUtils.Companion.m799getAdjustedDpu2uoSUM(68), DensityUtils.Companion.m799getAdjustedDpu2uoSUM(34), DensityUtils.Companion.m799getAdjustedDpu2uoSUM(68), DensityUtils.Companion.m799getAdjustedDpu2uoSUM(34)), m200cardColorsro_MJ88, null, null, ComposableLambdaKt.rememberComposableLambda(-2139731156, new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CtaTileInViewModeContent$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            /* JADX WARN: Type inference failed for: r3v20, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$CtaTileInViewModeContent$1$1$2$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Modifier weight;
                Composer composer2 = (Composer) obj2;
                if ((((Number) obj3).intValue() & 81) == 16) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                FillElement fillElement = SizeKt.FillWholeMaxSize;
                IWindowManager iWindowManager2 = DensityUtils.windowManagerService;
                Modifier m99paddingVpY3zN4 = PaddingKt.m99paddingVpY3zN4(fillElement, DensityUtils.Companion.m799getAdjustedDpu2uoSUM(50), DensityUtils.Companion.m799getAdjustedDpu2uoSUM(32));
                Arrangement$Center$1 arrangement$Center$1 = Arrangement.Center;
                BiasAlignment.Horizontal horizontal = Alignment.Companion.CenterHorizontally;
                final AndroidColorScheme androidColorScheme2 = AndroidColorScheme.this;
                final BaseCommunalViewModel baseCommunalViewModel2 = baseCommunalViewModel;
                ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(arrangement$Center$1, horizontal, composer2, 54);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, m99paddingVpY3zN4);
                ComposeUiNode.Companion.getClass();
                Function0 function0 = ComposeUiNode.Companion.Constructor;
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function0);
                } else {
                    composerImpl3.useNode();
                }
                Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
                Updater.m259setimpl(composer2, columnMeasurePolicy, function2);
                Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
                Updater.m259setimpl(composer2, currentCompositionLocalScope, function22);
                Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl3, currentCompositeKeyHash, function23);
                }
                Function2 function24 = ComposeUiNode.Companion.SetModifier;
                Updater.m259setimpl(composer2, materializeModifier, function24);
                IconKt.m214Iconww6aTOc(WidgetsKt.getWidgets(), StringResources_androidKt.stringResource(R.string.cta_label_to_open_widget_picker, composer2), SemanticsModifierKt.clearAndSetSemantics(SizeKt.m113size3ABfNKs(companion, Dimensions.IconSize), new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CtaTileInViewModeContent$1$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj4) {
                        return Unit.INSTANCE;
                    }
                }), 0L, composer2, 0, 8);
                SpacerKt.Spacer(composer2, SizeKt.m113size3ABfNKs(companion, DensityUtils.Companion.m799getAdjustedDpu2uoSUM(6)));
                String stringResource = StringResources_androidKt.stringResource(R.string.cta_label_to_edit_widget, composer2);
                TextStyle textStyle = MaterialTheme.getTypography(composer2).titleLarge;
                ComposerImpl composerImpl4 = (ComposerImpl) composer2;
                composerImpl4.startReplaceGroup(-647122957);
                StaticProvidableCompositionLocal staticProvidableCompositionLocal = CompositionLocalsKt.LocalDensity;
                long mo53toSp0xMU5do = ((Density) composerImpl4.consume(staticProvidableCompositionLocal)).mo53toSp0xMU5do(22);
                composerImpl4.end(false);
                composerImpl4.startReplaceGroup(-647122957);
                long mo53toSp0xMU5do2 = ((Density) composerImpl4.consume(staticProvidableCompositionLocal)).mo53toSp0xMU5do(28);
                composerImpl4.end(false);
                weight = ColumnScopeInstance.INSTANCE.weight(ScrollKt.verticalScroll$default(companion, ScrollKt.rememberScrollState(composer2), false, 14), 1.0f, true);
                TextKt.m241Text4IGK_g(stringResource, weight, 0L, mo53toSp0xMU5do, null, null, null, 0L, null, null, mo53toSp0xMU5do2, 0, false, 0, 0, null, textStyle, composer2, 0, 0, 64500);
                SpacerKt.Spacer(composer2, SizeKt.m113size3ABfNKs(companion, DensityUtils.Companion.m799getAdjustedDpu2uoSUM(16)));
                Modifier m108height3ABfNKs = SizeKt.m108height3ABfNKs(SizeKt.fillMaxWidth(companion, 1.0f), DensityUtils.Companion.m799getAdjustedDpu2uoSUM(56));
                RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.m79spacedByD5KLDUw(DensityUtils.Companion.m799getAdjustedDpu2uoSUM(16), horizontal), Alignment.Companion.Top, composer2, 0);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer2, m108height3ABfNKs);
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function0);
                } else {
                    composerImpl3.useNode();
                }
                Updater.m259setimpl(composer2, rowMeasurePolicy, function2);
                Updater.m259setimpl(composer2, currentCompositionLocalScope2, function22);
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash2, composerImpl3, currentCompositeKeyHash2, function23);
                }
                Updater.m259setimpl(composer2, materializeModifier2, function24);
                CompositionLocalKt.CompositionLocalProvider(staticProvidableCompositionLocal.defaultProvidedValue$runtime_release(DensityKt.Density(((Density) composerImpl3.consume(staticProvidableCompositionLocal)).getDensity(), RangesKt.coerceIn(((Density) composerImpl3.consume(staticProvidableCompositionLocal)).getFontScale(), 0.0f, 1.25f))), ComposableLambdaKt.rememberComposableLambda(1286552390, new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CtaTileInViewModeContent$1$1$2$1
                    final /* synthetic */ RowScope $this_Row = RowScopeInstance.INSTANCE;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.communal.ui.compose.CommunalHubKt$CtaTileInViewModeContent$1$1$2$1$1, reason: invalid class name */
                    final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0 {
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            ((BaseCommunalViewModel) this.receiver).onDismissCtaTile();
                            return Unit.INSTANCE;
                        }
                    }

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.communal.ui.compose.CommunalHubKt$CtaTileInViewModeContent$1$1$2$1$2, reason: invalid class name */
                    final /* synthetic */ class AnonymousClass2 extends AdaptedFunctionReference implements Function0 {
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            ((BaseCommunalViewModel) this.receiver).onOpenWidgetEditor(false);
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj4, Object obj5) {
                        Composer composer3 = (Composer) obj4;
                        if ((((Number) obj5).intValue() & 11) == 2) {
                            ComposerImpl composerImpl5 = (ComposerImpl) composer3;
                            if (composerImpl5.getSkipping()) {
                                composerImpl5.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey3 = ComposerKt.invocation;
                        RowScope rowScope = this.$this_Row;
                        FillElement fillElement2 = SizeKt.FillWholeMaxHeight;
                        Modifier weight2 = rowScope.weight(fillElement2, 1.0f, true);
                        PaddingValuesImpl paddingValuesImpl = ButtonDefaults.ContentPadding;
                        float f = 0;
                        ButtonKt.OutlinedButton(new AnonymousClass1(0, baseCommunalViewModel2, BaseCommunalViewModel.class, "onDismissCtaTile", "onDismissCtaTile()V", 0), weight2, false, null, ButtonDefaults.m198buttonColorsro_MJ88(0L, AndroidColorScheme.this.onPrimary, composer3, 13), null, BorderStrokeKt.m28BorderStrokecXLIe8U((float) 1.0d, AndroidColorScheme.this.primaryContainer), new PaddingValuesImpl(f, f, f, f), null, ComposableSingletons$CommunalHubKt.f20lambda5, composer3, 817889280, 300);
                        Modifier weight3 = this.$this_Row.weight(fillElement2, 1.0f, true);
                        AndroidColorScheme androidColorScheme3 = AndroidColorScheme.this;
                        ButtonKt.Button(new AnonymousClass2(0, baseCommunalViewModel2, BaseCommunalViewModel.class, "onOpenWidgetEditor", "onOpenWidgetEditor(Z)V", 0), weight3, false, null, ButtonDefaults.m198buttonColorsro_MJ88(androidColorScheme3.primaryContainer, androidColorScheme3.onPrimaryContainer, composer3, 12), null, null, new PaddingValuesImpl(f, f, f, f), null, ComposableSingletons$CommunalHubKt.f21lambda6, composer3, 817889280, 364);
                        return Unit.INSTANCE;
                    }
                }, composer2), composer2, 56);
                composerImpl3.end(true);
                composerImpl3.end(true);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, ((i >> 3) & 14) | 196608, 24);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CtaTileInViewModeContent$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.CtaTileInViewModeContent(BaseCommunalViewModel.this, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void HighlightedItem(final Modifier modifier, final float f, Composer composer, final int i, final int i2) {
        int i3;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1602272507);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 14) == 0) {
            i3 = (composerImpl.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i5 = 2 & i2;
        if (i5 != 0) {
            i3 |= 48;
        } else if ((i & 112) == 0) {
            i3 |= composerImpl.changed(f) ? 32 : 16;
        }
        if ((i3 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier = Modifier.Companion.$$INSTANCE;
            }
            if (i5 != 0) {
                f = 1.0f;
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            final SolidColor solidColor = new SolidColor(((AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme)).primary);
            composerImpl.startReplaceGroup(-201786347);
            boolean changed = ((i3 & 112) == 32) | composerImpl.changed(solidColor);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$HighlightedItem$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        DrawScope drawScope = (DrawScope) obj;
                        IWindowManager iWindowManager = DensityUtils.windowManagerService;
                        float mo51toPx0680j_4 = drawScope.mo51toPx0680j_4(DensityUtils.Companion.m799getAdjustedDpu2uoSUM(8));
                        float f2 = -mo51toPx0680j_4;
                        float f3 = mo51toPx0680j_4 * 2;
                        float intBitsToFloat = Float.intBitsToFloat((int) (drawScope.mo432getSizeNHjbRc() >> 32)) + f3;
                        float intBitsToFloat2 = Float.intBitsToFloat((int) (drawScope.mo432getSizeNHjbRc() & 4294967295L)) + f3;
                        long floatToRawIntBits = (Float.floatToRawIntBits(intBitsToFloat2) & 4294967295L) | (Float.floatToRawIntBits(intBitsToFloat) << 32);
                        long floatToRawIntBits2 = Float.floatToRawIntBits(drawScope.mo51toPx0680j_4(DensityUtils.Companion.m799getAdjustedDpu2uoSUM(37)));
                        long floatToRawIntBits3 = 4294967295L & Float.floatToRawIntBits(r15);
                        DrawScope.m428drawRoundRectZuiqVtQ$default(drawScope, solidColor, (Float.floatToRawIntBits(f2) << 32) | (Float.floatToRawIntBits(f2) & 4294967295L), floatToRawIntBits, floatToRawIntBits3 | (floatToRawIntBits2 << 32), f, new Stroke(drawScope.mo51toPx0680j_4(DensityUtils.Companion.m799getAdjustedDpu2uoSUM(3)), 0.0f, 0, 0, 30), 192);
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            composerImpl.end(false);
            BoxKt.Box(DrawModifierKt.drawBehind(modifier, (Function1) rememberedValue), composerImpl, 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$HighlightedItem$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.HighlightedItem(Modifier.this, f, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void ObserveNewWidgetAddedEffect(final List list, final LazyGridState lazyGridState, final BaseCommunalViewModel baseCommunalViewModel, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1770238181);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = PredictiveBackHandlerKt$$ExternalSyntheticOutline0.m(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerImpl), composerImpl);
        }
        ContextScope contextScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).coroutineScope;
        composerImpl.startReplaceGroup(-440894615);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new ArrayList();
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        List list2 = (List) rememberedValue2;
        Object m = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl, false, -440894544);
        if (m == composer$Companion$Empty$1) {
            m = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
            composerImpl.updateRememberedValue(m);
        }
        composerImpl.end(false);
        EffectsKt.LaunchedEffect(composerImpl, list, new CommunalHubKt$ObserveNewWidgetAddedEffect$1(list, list2, baseCommunalViewModel, lazyGridState, contextScope, (MutableState) m, null));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$ObserveNewWidgetAddedEffect$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.ObserveNewWidgetAddedEffect(list, lazyGridState, baseCommunalViewModel, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void ObserveScrollEffect(final LazyGridState lazyGridState, final BaseCommunalViewModel baseCommunalViewModel, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1825347584);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        EffectsKt.LaunchedEffect(composerImpl, lazyGridState, new CommunalHubKt$ObserveScrollEffect$1(lazyGridState, baseCommunalViewModel, null));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$ObserveScrollEffect$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.ObserveScrollEffect(LazyGridState.this, baseCommunalViewModel, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void PendingWidgetPlaceholder(final CommunalContentModel.WidgetContent.PendingWidget pendingWidget, final Modifier modifier, Composer composer, final int i, final int i2) {
        Icon createWithResource;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(127270360);
        int i3 = i2 & 2;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        if (i3 != 0) {
            modifier = companion;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Context context = (Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext);
        Bitmap bitmap = pendingWidget.icon;
        if (bitmap != null) {
            createWithResource = Icon.createWithBitmap(bitmap);
            Intrinsics.checkNotNull(createWithResource);
        } else {
            createWithResource = Icon.createWithResource(context, android.R.drawable.sym_def_app_icon);
            Intrinsics.checkNotNull(createWithResource);
        }
        Modifier m25backgroundbw27NRU = BackgroundKt.m25backgroundbw27NRU(modifier, MaterialTheme.getColorScheme(composerImpl).surfaceVariant, RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(PrimitiveResources_androidKt.dimensionResource(android.R.dimen.system_app_widget_background_radius, composerImpl)));
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Center, Alignment.Companion.CenterHorizontally, composerImpl, 54);
        int i4 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, m25backgroundbw27NRU);
        ComposeUiNode.Companion.getClass();
        Function0 function0 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, columnMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        ImageKt.Image(DrawablePainterKt.rememberDrawablePainter(createWithResource.loadDrawable(context), composerImpl), StringResources_androidKt.stringResource(R.string.icon_description_for_pending_widget, composerImpl), SizeKt.m113size3ABfNKs(companion, Dimensions.IconSize), null, null, 0.0f, null, composerImpl, 392, 120);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$PendingWidgetPlaceholder$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.PendingWidgetPlaceholder(CommunalContentModel.WidgetContent.PendingWidget.this, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void ScrollOnUpdatedLiveContentEffect(final List list, final LazyGridState lazyGridState, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(483517442);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(-1449763552);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = new ArrayList();
            composerImpl.updateRememberedValue(rememberedValue);
        }
        List list2 = (List) rememberedValue;
        Object m = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl, false, -1449763481);
        if (m == composer$Companion$Empty$1) {
            m = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
            composerImpl.updateRememberedValue(m);
        }
        composerImpl.end(false);
        EffectsKt.LaunchedEffect(composerImpl, list, new CommunalHubKt$ScrollOnUpdatedLiveContentEffect$1(list, list2, lazyGridState, (MutableState) m, null));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$ScrollOnUpdatedLiveContentEffect$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.ScrollOnUpdatedLiveContentEffect(list, lazyGridState, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void SmartspaceContent(final RemoteViews.InteractionHandler interactionHandler, final CommunalContentModel.Smartspace smartspace, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1317833478);
        if ((i2 & 4) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Modifier modifier2 = modifier;
        AndroidView_androidKt.AndroidView(new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$SmartspaceContent$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SmartspaceAppWidgetHostView smartspaceAppWidgetHostView = new SmartspaceAppWidgetHostView((Context) obj);
                RemoteViews.InteractionHandler interactionHandler2 = interactionHandler;
                if (interactionHandler2 != null) {
                    smartspaceAppWidgetHostView.setInteractionHandler(interactionHandler2);
                }
                return smartspaceAppWidgetHostView;
            }
        }, modifier2, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$SmartspaceContent$3
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        }, null, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$SmartspaceContent$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((SmartspaceAppWidgetHostView) obj).updateAppWidget(CommunalContentModel.Smartspace.this.remoteViews);
                return Unit.INSTANCE;
            }
        }, composerImpl, ((i >> 3) & 112) | 384, 8);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$SmartspaceContent$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.SmartspaceContent(interactionHandler, smartspace, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006a  */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$ToolbarButton$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r7v8, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$ToolbarButton$2, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ToolbarButton(boolean r21, final kotlin.jvm.functions.Function0 r22, androidx.compose.ui.Modifier r23, final kotlin.jvm.functions.Function3 r24, androidx.compose.runtime.Composer r25, final int r26, final int r27) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.ui.compose.CommunalHubKt.ToolbarButton(boolean, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void TutorialContent(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(412326112);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 14) == 0) {
            i3 = (composerImpl.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i3 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier = Modifier.Companion.$$INSTANCE;
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            CardKt.Card(modifier, null, null, null, null, ComposableSingletons$CommunalHubKt.f23lambda8, composerImpl, (i3 & 14) | 196608, 30);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$TutorialContent$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.TutorialContent(Modifier.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void Umo(final BaseCommunalViewModel baseCommunalViewModel, final Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1155214189);
        if ((i2 & 2) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        AndroidView_androidKt.AndroidView(new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$Umo$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                UniqueObjectHostView uniqueObjectHostView = BaseCommunalViewModel.this.mediaHost.hostView;
                if (uniqueObjectHostView == null) {
                    uniqueObjectHostView = null;
                }
                uniqueObjectHostView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                UniqueObjectHostView uniqueObjectHostView2 = BaseCommunalViewModel.this.mediaHost.hostView;
                if (uniqueObjectHostView2 != null) {
                    return uniqueObjectHostView2;
                }
                return null;
            }
        }, SuspendingPointerInputFilterKt.pointerInput(modifier, Unit.INSTANCE, new CommunalHubKt$Umo$1(baseCommunalViewModel, 0)), new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$Umo$3
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        }, null, null, composerImpl, 384, 24);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$Umo$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.Umo(BaseCommunalViewModel.this, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r5v0, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetConfigureButton$1, kotlin.jvm.internal.Lambda] */
    public static final void WidgetConfigureButton(final boolean z, final CommunalContentModel.WidgetContent.Widget widget, Modifier modifier, final WidgetConfigurator widgetConfigurator, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(2015050074);
        if ((i2 & 4) != 0) {
            modifier = Modifier.Companion.$$INSTANCE;
        }
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final AndroidColorScheme androidColorScheme = (AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme);
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == Composer.Companion.Empty) {
            rememberedValue = PredictiveBackHandlerKt$$ExternalSyntheticOutline0.m(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerImpl), composerImpl);
        }
        final ContextScope contextScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).coroutineScope;
        EnterTransition fadeIn$default = EnterExitTransitionKt.fadeIn$default(null, 3);
        ExitTransition fadeOut$default = EnterExitTransitionKt.fadeOut$default(null, 3);
        IWindowManager iWindowManager = DensityUtils.windowManagerService;
        AnimatedVisibilityKt.AnimatedVisibility(z, PaddingKt.m98padding3ABfNKs(modifier, DensityUtils.Companion.m799getAdjustedDpu2uoSUM(16)), fadeIn$default, fadeOut$default, null, ComposableLambdaKt.rememberComposableLambda(-635566206, new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetConfigureButton$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ((Number) obj3).intValue();
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                IWindowManager iWindowManager2 = DensityUtils.windowManagerService;
                RoundedCornerShape m148RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(DensityUtils.Companion.m799getAdjustedDpu2uoSUM(16));
                Modifier m113size3ABfNKs = SizeKt.m113size3ABfNKs(Modifier.Companion.$$INSTANCE, DensityUtils.Companion.m799getAdjustedDpu2uoSUM(48));
                AndroidColorScheme androidColorScheme2 = AndroidColorScheme.this;
                long j = androidColorScheme2.primary;
                long j2 = Color.Transparent;
                IconButtonColors iconButtonColors = new IconButtonColors(j, androidColorScheme2.onPrimary, j2, j2);
                final CoroutineScope coroutineScope = contextScope;
                final WidgetConfigurator widgetConfigurator2 = widgetConfigurator;
                final CommunalContentModel.WidgetContent.Widget widget2 = widget;
                IconButtonKt.FilledIconButton(1572864, 36, null, iconButtonColors, (Composer) obj2, m113size3ABfNKs, m148RoundedCornerShape0680j_4, new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetConfigureButton$1.1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetConfigureButton$1$1$1, reason: invalid class name and collision with other inner class name */
                    final class C00641 extends SuspendLambda implements Function2 {
                        final /* synthetic */ CommunalContentModel.WidgetContent.Widget $model;
                        final /* synthetic */ WidgetConfigurator $widgetConfigurator;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C00641(WidgetConfigurator widgetConfigurator, CommunalContentModel.WidgetContent.Widget widget, Continuation continuation) {
                            super(2, continuation);
                            this.$widgetConfigurator = widgetConfigurator;
                            this.$model = widget;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object obj, Continuation continuation) {
                            return new C00641(this.$widgetConfigurator, this.$model, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return ((C00641) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                WidgetConfigurator widgetConfigurator = this.$widgetConfigurator;
                                int i2 = this.$model.appWidgetId;
                                this.label = 1;
                                if (((WidgetConfigurationController) widgetConfigurator).configureWidget(i2, this) == coroutineSingletons) {
                                    return coroutineSingletons;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        BuildersKt.launch$default(CoroutineScope.this, null, null, new C00641(widgetConfigurator2, widget2, null), 3);
                        return Unit.INSTANCE;
                    }
                }, ComposableSingletons$CommunalHubKt.f22lambda7, false);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, (i & 14) | 200064, 16);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier2 = modifier;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetConfigureButton$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.WidgetConfigureButton(z, widget, modifier2, widgetConfigurator, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void WidgetContent(final BaseCommunalViewModel baseCommunalViewModel, final CommunalContentModel.WidgetContent.Widget widget, final SizeF sizeF, final boolean z, final WidgetConfigurator widgetConfigurator, Modifier modifier, final int i, final ContentListState contentListState, final CommunalAppWidgetSection communalAppWidgetSection, Composer composer, final int i2, final int i3) {
        Integer num;
        Unit unit;
        FocusRequester focusRequester;
        MutableInteractionSource mutableInteractionSource;
        Modifier modifier2;
        Unit unit2;
        Unit unit3;
        boolean z2;
        Modifier modifier3;
        ComposerImpl composerImpl;
        Modifier.Companion companion;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(-267910840);
        int i4 = i3 & 32;
        Modifier.Companion companion2 = Modifier.Companion.$$INSTANCE;
        Modifier modifier4 = i4 != 0 ? companion2 : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Context context = (Context) composerImpl2.consume(AndroidCompositionLocals_androidKt.LocalContext);
        composerImpl2.startReplaceGroup(-1013762477);
        boolean changed = composerImpl2.changed(widget) | composerImpl2.changed(context);
        Object rememberedValue = composerImpl2.rememberedValue();
        Object obj = Composer.Companion.Empty;
        if (changed || rememberedValue == obj) {
            rememberedValue = StringsKt.trim(widget.providerInfo.loadLabel(context.getPackageManager()).toString()).toString();
            composerImpl2.updateRememberedValue(rememberedValue);
        }
        final String str = (String) rememberedValue;
        composerImpl2.end(false);
        final String stringResource = StringResources_androidKt.stringResource(R.string.accessibility_action_label_select_widget, composerImpl2);
        final String stringResource2 = StringResources_androidKt.stringResource(R.string.accessibility_action_label_remove_widget, composerImpl2);
        final String stringResource3 = StringResources_androidKt.stringResource(R.string.accessibility_action_label_place_widget, composerImpl2);
        final String stringResource4 = StringResources_androidKt.stringResource(R.string.accessibility_action_label_unselect_widget, composerImpl2);
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(baseCommunalViewModel.selectedKey, composerImpl2);
        String str2 = (String) collectAsStateWithLifecycle.getValue();
        if (str2 != null) {
            ListIterator listIterator = contentListState.list.listIterator();
            int i5 = 0;
            while (true) {
                if (!listIterator.hasNext()) {
                    i5 = -1;
                    break;
                } else if (Intrinsics.areEqual(((CommunalContentModel) listIterator.next()).getKey(), str2)) {
                    break;
                } else {
                    i5++;
                }
            }
            num = Integer.valueOf(i5);
        } else {
            num = null;
        }
        composerImpl2.startReplaceGroup(-1013761737);
        Object rememberedValue2 = composerImpl2.rememberedValue();
        if (rememberedValue2 == obj) {
            rememberedValue2 = InteractionSourceKt.MutableInteractionSource();
            composerImpl2.updateRememberedValue(rememberedValue2);
        }
        MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) rememberedValue2;
        Object m = LottieAnimationKt$$ExternalSyntheticOutline0.m(composerImpl2, false, -1013761672);
        if (m == obj) {
            m = new FocusRequester();
            composerImpl2.updateRememberedValue(m);
        }
        FocusRequester focusRequester2 = (FocusRequester) m;
        composerImpl2.end(false);
        composerImpl2.startReplaceGroup(-1013761638);
        boolean isEditMode = baseCommunalViewModel.isEditMode();
        Unit unit4 = Unit.INSTANCE;
        if (isEditMode && z) {
            composerImpl2.startReplaceGroup(-1013761569);
            Object rememberedValue3 = composerImpl2.rememberedValue();
            if (rememberedValue3 == obj) {
                rememberedValue3 = new CommunalHubKt$WidgetContent$1$1(focusRequester2, null);
                composerImpl2.updateRememberedValue(rememberedValue3);
            }
            composerImpl2.end(false);
            EffectsKt.LaunchedEffect(composerImpl2, unit4, (Function2) rememberedValue3);
        }
        composerImpl2.end(false);
        boolean areEqual = Intrinsics.areEqual((String) collectAsStateWithLifecycle.getValue(), widget.key);
        if (baseCommunalViewModel.isEditMode()) {
            unit = unit4;
            focusRequester = focusRequester2;
            mutableInteractionSource = mutableInteractionSource2;
            modifier2 = SelectableKt.m145selectableO2vRcR0$default(companion2, areEqual, mutableInteractionSource2, null, false, new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetContent$selectableModifier$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    BaseCommunalViewModel.this.setSelectedKey(widget.key);
                    return Unit.INSTANCE;
                }
            }, 24);
        } else {
            unit = unit4;
            focusRequester = focusRequester2;
            mutableInteractionSource = mutableInteractionSource2;
            modifier2 = companion2;
        }
        Modifier then = FocusableKt.focusable$default(FocusRequesterModifierKt.focusRequester(modifier4, focusRequester), false, mutableInteractionSource, 1).then(modifier2);
        boolean isEditMode2 = baseCommunalViewModel.isEditMode();
        boolean z3 = widget.inQuietMode;
        if (isEditMode2 || z3) {
            unit2 = unit;
        } else {
            unit2 = unit;
            then = then.then(SuspendingPointerInputFilterKt.pointerInput(companion2, unit2, new PointerInputEventHandler() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetContent$2$1
                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                    final BaseCommunalViewModel baseCommunalViewModel2 = BaseCommunalViewModel.this;
                    final CommunalContentModel.WidgetContent.Widget widget2 = widget;
                    Object observeTaps$default = PointerInputScopeExtKt.observeTaps$default(pointerInputScope, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetContent$2$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            long j = ((Offset) obj2).packedValue;
                            BaseCommunalViewModel baseCommunalViewModel3 = BaseCommunalViewModel.this;
                            CommunalContentModel.WidgetContent.Widget widget3 = widget2;
                            baseCommunalViewModel3.onTapWidget(widget3.rank, widget3.componentName);
                            return Unit.INSTANCE;
                        }
                    }, continuation, 3);
                    return observeTaps$default == CoroutineSingletons.COROUTINE_SUSPENDED ? observeTaps$default : Unit.INSTANCE;
                }
            }));
        }
        if (!baseCommunalViewModel.isEditMode() && z3) {
            then = then.then(SuspendingPointerInputFilterKt.pointerInput(companion2, unit2, new CommunalHubKt$Umo$1(baseCommunalViewModel, 2)));
        }
        Modifier modifier5 = then;
        if (baseCommunalViewModel.isEditMode()) {
            final Integer num2 = num;
            unit3 = unit2;
            z2 = false;
            modifier3 = modifier4;
            composerImpl = composerImpl2;
            companion = companion2;
            modifier5 = modifier5.then(SemanticsModifierKt.semantics(companion, false, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetContent$4$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj2;
                    SemanticsPropertiesKt.onClick(semanticsPropertyReceiver, stringResource, null);
                    SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str);
                    String str3 = stringResource2;
                    final ContentListState contentListState2 = contentListState;
                    final int i6 = i;
                    List mutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new CustomAccessibilityAction(str3, new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetContent$4$1$deleteAction$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            ContentListState.this.onRemove(i6);
                            ContentListState.this.onSaveList(null, null, null);
                            return Boolean.TRUE;
                        }
                    }));
                    Integer num3 = num2;
                    if (num3 != null) {
                        if (num3.intValue() != i) {
                            String str4 = stringResource3;
                            final ContentListState contentListState3 = contentListState;
                            final Integer num4 = num2;
                            final int i7 = i;
                            final BaseCommunalViewModel baseCommunalViewModel2 = baseCommunalViewModel;
                            mutableListOf.add(new CustomAccessibilityAction(str4, new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetContent$4$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    ContentListState contentListState4 = ContentListState.this;
                                    Integer num5 = num4;
                                    Intrinsics.checkNotNull(num5);
                                    int intValue = num5.intValue();
                                    int i8 = i7;
                                    SnapshotStateList snapshotStateList = contentListState4.list;
                                    snapshotStateList.add(i8, snapshotStateList.remove(intValue));
                                    ContentListState.this.onSaveList(null, null, null);
                                    baseCommunalViewModel2.setSelectedKey(null);
                                    return Boolean.TRUE;
                                }
                            }));
                        }
                    }
                    if (z) {
                        String str5 = stringResource4;
                        final BaseCommunalViewModel baseCommunalViewModel3 = baseCommunalViewModel;
                        mutableListOf.add(new CustomAccessibilityAction(str5, new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetContent$4$1.3
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                BaseCommunalViewModel.this.setSelectedKey(null);
                                return Boolean.TRUE;
                            }
                        }));
                    } else {
                        String str6 = stringResource;
                        final BaseCommunalViewModel baseCommunalViewModel4 = baseCommunalViewModel;
                        final CommunalContentModel.WidgetContent.Widget widget2 = widget;
                        mutableListOf.add(new CustomAccessibilityAction(str6, new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetContent$4$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                BaseCommunalViewModel.this.setSelectedKey(widget2.key);
                                return Boolean.TRUE;
                            }
                        }));
                    }
                    SemanticsPropertiesKt.setCustomActions(semanticsPropertyReceiver, mutableListOf);
                    return Unit.INSTANCE;
                }
            }));
        } else {
            unit3 = unit2;
            z2 = false;
            modifier3 = modifier4;
            composerImpl = composerImpl2;
            companion = companion2;
        }
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, z2);
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        ComposerImpl composerImpl3 = composerImpl;
        int i6 = composerImpl3.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl3, modifier5);
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
        if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(i6))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i6, composerImpl3, i6, function2);
        }
        Updater.m259setimpl(composerImpl3, materializeModifier, ComposeUiNode.Companion.SetModifier);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        composerImpl3.startReplaceGroup(294479442);
        Modifier modifier6 = SizeKt.FillWholeMaxSize;
        if (baseCommunalViewModel.isEditMode()) {
            modifier6 = modifier6.then(SuspendingPointerInputFilterKt.pointerInput(modifier6, unit3, ModifierExtKt$allowGestures$1.INSTANCE));
        }
        communalAppWidgetSection.Widget(baseCommunalViewModel, widget, sizeF, modifier6, composerImpl3, 33352, 0);
        composerImpl3.end(z2);
        composerImpl3.startReplaceGroup(-1013757888);
        if (baseCommunalViewModel instanceof CommunalEditModeViewModel) {
            AppWidgetProviderInfo appWidgetProviderInfo = widget.providerInfo;
            if ((appWidgetProviderInfo.widgetFeatures & 1) != 0 && appWidgetProviderInfo.configure != null && widgetConfigurator != null) {
                WidgetConfigureButton(z, widget, boxScopeInstance.align(companion, Alignment.Companion.BottomEnd), widgetConfigurator, composerImpl3, ((i2 >> 9) & 14) | 64 | ((i2 >> 3) & 7168), 0);
            }
        }
        composerImpl3.end(z2);
        composerImpl3.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl3.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier7 = modifier3;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$WidgetContent$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    CommunalHubKt.WidgetContent(BaseCommunalViewModel.this, widget, sizeF, z, widgetConfigurator, modifier7, i, contentListState, communalAppWidgetSection, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$CommunalContent(final CommunalContentModel communalContentModel, final BaseCommunalViewModel baseCommunalViewModel, final SizeF sizeF, final boolean z, Modifier modifier, WidgetConfigurator widgetConfigurator, final int i, final ContentListState contentListState, final RemoteViews.InteractionHandler interactionHandler, final CommunalAppWidgetSection communalAppWidgetSection, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1302604846);
        Modifier modifier3 = (i3 & 16) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        WidgetConfigurator widgetConfigurator2 = (i3 & 32) != 0 ? null : widgetConfigurator;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        if (communalContentModel instanceof CommunalContentModel.WidgetContent.Widget) {
            composerImpl.startReplaceGroup(-1570648002);
            WidgetContent(baseCommunalViewModel, (CommunalContentModel.WidgetContent.Widget) communalContentModel, sizeF, z, widgetConfigurator2, modifier3, i, contentListState, communalAppWidgetSection, composerImpl, (i2 & 7168) | 150995528 | ((i2 >> 3) & 57344) | (458752 & (i2 << 3)) | (3670016 & i2), 0);
            composerImpl.end(false);
            modifier2 = modifier3;
        } else {
            Modifier modifier4 = modifier3;
            if (communalContentModel instanceof CommunalContentModel.WidgetPlaceholder) {
                composerImpl.startReplaceGroup(-1570647672);
                modifier2 = modifier4;
                HighlightedItem(modifier2, 0.0f, composerImpl, (i2 >> 12) & 14, 2);
                composerImpl.end(false);
            } else {
                modifier2 = modifier4;
                if (communalContentModel instanceof CommunalContentModel.WidgetContent.PendingWidget) {
                    composerImpl.startReplaceGroup(-1570647441);
                    PendingWidgetPlaceholder((CommunalContentModel.WidgetContent.PendingWidget) communalContentModel, modifier2, composerImpl, ((i2 >> 9) & 112) | 8, 0);
                    composerImpl.end(false);
                } else if (communalContentModel instanceof CommunalContentModel.CtaTileInViewMode) {
                    composerImpl.startReplaceGroup(-1570647346);
                    CtaTileInViewModeContent(baseCommunalViewModel, modifier2, composerImpl, ((i2 >> 9) & 112) | 8, 0);
                    composerImpl.end(false);
                } else if (communalContentModel instanceof CommunalContentModel.Smartspace) {
                    composerImpl.startReplaceGroup(-1570647254);
                    SmartspaceContent(interactionHandler, (CommunalContentModel.Smartspace) communalContentModel, modifier2, composerImpl, ((i2 >> 6) & 896) | 72, 0);
                    composerImpl.end(false);
                } else if (communalContentModel instanceof CommunalContentModel.Tutorial) {
                    composerImpl.startReplaceGroup(-1570647155);
                    TutorialContent(modifier2, composerImpl, (i2 >> 12) & 14, 0);
                    composerImpl.end(false);
                } else if (communalContentModel instanceof CommunalContentModel.Umo) {
                    composerImpl.startReplaceGroup(-1570647090);
                    Umo(baseCommunalViewModel, modifier2, composerImpl, ((i2 >> 9) & 112) | 8, 0);
                    composerImpl.end(false);
                } else {
                    composerImpl.startReplaceGroup(-1570647060);
                    composerImpl.end(false);
                }
            }
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier5 = modifier2;
            final WidgetConfigurator widgetConfigurator3 = widgetConfigurator2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalContent$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.access$CommunalContent(CommunalContentModel.this, baseCommunalViewModel, sizeF, z, modifier5, widgetConfigurator3, i, contentListState, interactionHandler, communalAppWidgetSection, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* renamed from: access$CommunalHubLazyGrid-_J--G6Y, reason: not valid java name */
    public static final void m794access$CommunalHubLazyGrid_JG6Y(final BoxScope boxScope, final List list, final BaseCommunalViewModel baseCommunalViewModel, final PaddingValues paddingValues, final State state, final int i, final long j, final LazyGridState lazyGridState, final ContentListState contentListState, final Function1 function1, final Function1 function12, final WidgetConfigurator widgetConfigurator, final RemoteViews.InteractionHandler interactionHandler, final CommunalAppWidgetSection communalAppWidgetSection, Composer composer, final int i2, final int i3) {
        Ref$ObjectRef ref$ObjectRef;
        Ref$ObjectRef ref$ObjectRef2;
        Modifier m108height3ABfNKs;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1277836753);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Modifier modifier = Modifier.Companion.$$INSTANCE;
        BiasAlignment biasAlignment = Alignment.Companion.TopStart;
        Modifier align = boxScope.align(modifier, biasAlignment);
        composerImpl.startReplaceGroup(-435037167);
        boolean z = (((1879048192 & i2) ^ 805306368) > 536870912 && composerImpl.changed(function1)) || (i2 & 805306368) == 536870912;
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (z || rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHubLazyGrid$gridModifier$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Function1.this.invoke((LayoutCoordinates) obj);
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        Modifier onGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(align, (Function1) rememberedValue);
        Ref$ObjectRef ref$ObjectRef3 = new Ref$ObjectRef();
        ref$ObjectRef3.element = list;
        Ref$ObjectRef ref$ObjectRef4 = new Ref$ObjectRef();
        if (baseCommunalViewModel.isEditMode() && (baseCommunalViewModel instanceof CommunalEditModeViewModel)) {
            composerImpl.startReplaceGroup(-435036978);
            ref$ObjectRef3.element = contentListState.list;
            int i4 = (i2 >> 21) & 14;
            int i5 = i4 | 64 | ((i3 << 6) & 896);
            composerImpl.startReplaceGroup(590879362);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = PredictiveBackHandlerKt$$ExternalSyntheticOutline0.m(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerImpl), composerImpl);
            }
            ContextScope contextScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue2).coroutineScope;
            composerImpl.startReplaceGroup(203331972);
            boolean changed = ((((i5 & 14) ^ 6) > 4 && composerImpl.changed(lazyGridState)) || (i5 & 6) == 4) | composerImpl.changed(contentListState);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (changed || rememberedValue3 == composer$Companion$Empty$1) {
                rememberedValue3 = new GridDragDropState(lazyGridState, contentListState, contextScope, function12);
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            GridDragDropState gridDragDropState = (GridDragDropState) rememberedValue3;
            composerImpl.end(false);
            EffectsKt.LaunchedEffect(composerImpl, gridDragDropState, new GridDragDropStateKt$rememberGridDragDropState$1(gridDragDropState, lazyGridState, null));
            composerImpl.end(false);
            ref$ObjectRef4.element = gridDragDropState;
            FillElement fillElement = SizeKt.FillWholeMaxSize;
            Modifier then = onGloballyPositioned.then(fillElement);
            final GridDragDropState gridDragDropState2 = (GridDragDropState) ref$ObjectRef4.element;
            final LayoutDirection layoutDirection = (LayoutDirection) composerImpl.consume(CompositionLocalsKt.LocalLayoutDirection);
            Offset offset = new Offset(j);
            ref$ObjectRef = ref$ObjectRef3;
            ref$ObjectRef2 = ref$ObjectRef4;
            PointerInputEventHandler pointerInputEventHandler = new PointerInputEventHandler() { // from class: com.android.systemui.communal.ui.compose.GridDragDropStateKt$dragContainer$1
                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                    final GridDragDropState gridDragDropState3 = GridDragDropState.this;
                    final int i6 = i;
                    final LayoutDirection layoutDirection2 = layoutDirection;
                    final long j2 = j;
                    final BaseCommunalViewModel baseCommunalViewModel2 = baseCommunalViewModel;
                    Object detectDragGesturesAfterLongPress = DragGestureDetectorKt.detectDragGesturesAfterLongPress(pointerInputScope, new Function1() { // from class: com.android.systemui.communal.ui.compose.GridDragDropStateKt$dragContainer$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            Object obj2;
                            long j3 = ((Offset) obj).packedValue;
                            GridDragDropState gridDragDropState4 = GridDragDropState.this;
                            int i7 = i6;
                            LayoutDirection layoutDirection3 = layoutDirection2;
                            long j4 = j2;
                            gridDragDropState4.getClass();
                            float intBitsToFloat = layoutDirection3 == LayoutDirection.Ltr ? Float.intBitsToFloat((int) (j3 >> 32)) : i7 - Float.intBitsToFloat((int) (j3 >> 32));
                            long floatToRawIntBits = (Float.floatToRawIntBits(intBitsToFloat) << 32) | (Float.floatToRawIntBits(Float.intBitsToFloat((int) (j3 & 4294967295L))) & 4294967295L);
                            List list2 = ((LazyGridMeasureResult) gridDragDropState4.state.getLayoutInfo()).visibleItemsInfo;
                            ArrayList arrayList = new ArrayList();
                            for (Object obj3 : list2) {
                                if (gridDragDropState4.contentListState.isItemEditable(((LazyGridMeasuredItem) ((LazyGridItemInfo) obj3)).index)) {
                                    arrayList.add(obj3);
                                }
                            }
                            long m314minusMKHz9U = Offset.m314minusMKHz9U(floatToRawIntBits, j4);
                            Iterator it = arrayList.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    obj2 = null;
                                    break;
                                }
                                obj2 = it.next();
                                LazyGridMeasuredItem lazyGridMeasuredItem = (LazyGridMeasuredItem) ((LazyGridItemInfo) obj2);
                                IntRect m681IntRectVbeCjmY = IntRectKt.m681IntRectVbeCjmY(lazyGridMeasuredItem.offset, lazyGridMeasuredItem.size);
                                if (new Rect(m681IntRectVbeCjmY.left, m681IntRectVbeCjmY.top, m681IntRectVbeCjmY.right, m681IntRectVbeCjmY.bottom).m319containsk4lQ0M(m314minusMKHz9U)) {
                                    break;
                                }
                            }
                            LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) obj2;
                            if (lazyGridItemInfo != null) {
                                LazyGridMeasuredItem lazyGridMeasuredItem2 = (LazyGridMeasuredItem) lazyGridItemInfo;
                                long j5 = lazyGridMeasuredItem2.offset;
                                ((SnapshotMutableStateImpl) gridDragDropState4.dragStartPointerOffset$delegate).setValue(new Offset(Offset.m314minusMKHz9U(floatToRawIntBits, (Float.floatToRawIntBits((int) (j5 >> 32)) << 32) | (Float.floatToRawIntBits((int) (j5 & 4294967295L)) & 4294967295L))));
                                ((SnapshotMutableStateImpl) gridDragDropState4.draggingItemIndex$delegate).setValue(Integer.valueOf(lazyGridMeasuredItem2.index));
                                long j6 = lazyGridMeasuredItem2.offset;
                                ((SnapshotMutableStateImpl) gridDragDropState4.draggingItemInitialOffset$delegate).setValue(new Offset((Float.floatToRawIntBits((int) (j6 >> 32)) << 32) | (Float.floatToRawIntBits((int) (j6 & 4294967295L)) & 4294967295L)));
                                baseCommunalViewModel2.onReorderWidgetStart();
                            }
                            return Unit.INSTANCE;
                        }
                    }, new Function0() { // from class: com.android.systemui.communal.ui.compose.GridDragDropStateKt$dragContainer$1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            GridDragDropState.this.onDragInterrupted$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
                            baseCommunalViewModel2.onReorderWidgetEnd();
                            return Unit.INSTANCE;
                        }
                    }, new Function0() { // from class: com.android.systemui.communal.ui.compose.GridDragDropStateKt$dragContainer$1.3
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            GridDragDropState.this.onDragInterrupted$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
                            baseCommunalViewModel2.onReorderWidgetCancel();
                            return Unit.INSTANCE;
                        }
                    }, new Function2() { // from class: com.android.systemui.communal.ui.compose.GridDragDropStateKt$dragContainer$1.4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        /* JADX WARN: Removed duplicated region for block: B:21:0x0155  */
                        /* JADX WARN: Removed duplicated region for block: B:23:0x0164  */
                        @Override // kotlin.jvm.functions.Function2
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final java.lang.Object invoke(java.lang.Object r21, java.lang.Object r22) {
                            /*
                                Method dump skipped, instructions count: 564
                                To view this dump add '--comments-level debug' option
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.ui.compose.GridDragDropStateKt$dragContainer$1.AnonymousClass4.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }, continuation);
                    return detectDragGesturesAfterLongPress == CoroutineSingletons.COROUTINE_SUSPENDED ? detectDragGesturesAfterLongPress : Unit.INSTANCE;
                }
            };
            PointerEvent pointerEvent = SuspendingPointerInputFilterKt.EmptyPointerEvent;
            Modifier then2 = then.then(then.then(new SuspendPointerInputElement(gridDragDropState2, offset, null, pointerInputEventHandler, 4)));
            int i6 = i4 | 512 | ((i2 >> 15) & 112);
            composerImpl.startReplaceGroup(-1054567112);
            Object rememberedValue4 = composerImpl.rememberedValue();
            if (rememberedValue4 == composer$Companion$Empty$1) {
                rememberedValue4 = PredictiveBackHandlerKt$$ExternalSyntheticOutline0.m(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerImpl), composerImpl);
            }
            ContextScope contextScope2 = ((CompositionScopedCoroutineScopeCanceller) rememberedValue4).coroutineScope;
            float mo51toPx0680j_4 = ((Density) composerImpl.consume(CompositionLocalsKt.LocalDensity)).mo51toPx0680j_4(60);
            composerImpl.startReplaceGroup(-871443164);
            boolean changed2 = ((((i6 & 112) ^ 48) > 32 && composerImpl.changed(j)) || (i6 & 48) == 32) | ((((i6 & 14) ^ 6) > 4 && composerImpl.changed(lazyGridState)) || (i6 & 6) == 4) | composerImpl.changed(contentListState) | composerImpl.changed(mo51toPx0680j_4) | composerImpl.changed(contextScope2);
            Object rememberedValue5 = composerImpl.rememberedValue();
            if (changed2 || rememberedValue5 == composer$Companion$Empty$1) {
                DragAndDropTargetState dragAndDropTargetState = new DragAndDropTargetState(lazyGridState, j, contentListState, mo51toPx0680j_4, contextScope2);
                composerImpl.updateRememberedValue(dragAndDropTargetState);
                rememberedValue5 = dragAndDropTargetState;
            }
            DragAndDropTargetState dragAndDropTargetState2 = (DragAndDropTargetState) rememberedValue5;
            composerImpl.end(false);
            EffectsKt.LaunchedEffect(composerImpl, dragAndDropTargetState2, new DragAndDropTargetStateKt$rememberDragAndDropTargetState$1(dragAndDropTargetState2, lazyGridState, null));
            composerImpl.end(false);
            composerImpl.startReplaceGroup(-2092996508);
            final MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(dragAndDropTargetState2, composerImpl);
            Modifier then3 = fillElement.then(DragAndDropTargetKt.dragAndDropTarget(modifier, new Function1() { // from class: com.android.systemui.communal.ui.compose.DragAndDropTargetStateKt$dragAndDropTarget$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Set mimeTypes = DragAndDrop_androidKt.mimeTypes((DragAndDropEvent) obj);
                    boolean z2 = false;
                    if (!(mimeTypes instanceof Collection) || !mimeTypes.isEmpty()) {
                        Iterator it = mimeTypes.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (Intrinsics.areEqual((String) it.next(), "text/vnd.android.intent")) {
                                z2 = true;
                                break;
                            }
                        }
                    }
                    return Boolean.valueOf(z2);
                }
            }, new DragAndDropTarget() { // from class: com.android.systemui.communal.ui.compose.DragAndDropTargetStateKt$dragAndDropTarget$2
                @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
                public final boolean onDrop(DragAndDropEvent dragAndDropEvent) {
                    ClipData.Item itemAt;
                    Intent intent;
                    DragAndDropTargetState dragAndDropTargetState3 = (DragAndDropTargetState) MutableState.this.getValue();
                    Integer num = dragAndDropTargetState3.placeHolderIndex;
                    if (num == null) {
                        return false;
                    }
                    int intValue = num.intValue();
                    ClipData clipData = dragAndDropEvent.dragEvent.getClipData();
                    WidgetPickerIntentUtils$WidgetExtra widgetPickerIntentUtils$WidgetExtra = null;
                    if (clipData.getItemCount() == 0) {
                        clipData = null;
                    }
                    if (clipData != null && (itemAt = clipData.getItemAt(0)) != null && (intent = itemAt.getIntent()) != null) {
                        widgetPickerIntentUtils$WidgetExtra = new WidgetPickerIntentUtils$WidgetExtra((ComponentName) intent.getParcelableExtra("android.intent.extra.COMPONENT_NAME", ComponentName.class), (UserHandle) intent.getParcelableExtra("android.intent.extra.USER", UserHandle.class));
                    }
                    if (widgetPickerIntentUtils$WidgetExtra == null) {
                        return false;
                    }
                    ComponentName componentName = widgetPickerIntentUtils$WidgetExtra.componentName;
                    UserHandle userHandle = widgetPickerIntentUtils$WidgetExtra.user;
                    if (componentName == null || userHandle == null) {
                        return false;
                    }
                    dragAndDropTargetState3.contentListState.onSaveList(componentName, userHandle, Integer.valueOf(intValue));
                    return true;
                }

                @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
                public final void onEnded(DragAndDropEvent dragAndDropEvent) {
                    DragAndDropTargetState dragAndDropTargetState3 = (DragAndDropTargetState) MutableState.this.getValue();
                    dragAndDropTargetState3.placeHolderIndex = null;
                    dragAndDropTargetState3.contentListState.list.remove(dragAndDropTargetState3.placeHolder);
                }

                @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
                public final void onExited(DragAndDropEvent dragAndDropEvent) {
                    DragAndDropTargetState dragAndDropTargetState3 = (DragAndDropTargetState) MutableState.this.getValue();
                    dragAndDropTargetState3.placeHolderIndex = null;
                    dragAndDropTargetState3.contentListState.list.remove(dragAndDropTargetState3.placeHolder);
                }

                @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
                public final void onMoved(DragAndDropEvent dragAndDropEvent) {
                    Object obj;
                    final DragAndDropTargetState dragAndDropTargetState3 = (DragAndDropTargetState) MutableState.this.getValue();
                    dragAndDropTargetState3.getClass();
                    DragEvent dragEvent = dragAndDropEvent.dragEvent;
                    long floatToRawIntBits = (Float.floatToRawIntBits(dragEvent.getX()) << 32) | (Float.floatToRawIntBits(dragEvent.getY()) & 4294967295L);
                    LazyGridState lazyGridState2 = dragAndDropTargetState3.state;
                    FilteringSequence filter = SequencesKt.filter(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(((LazyGridMeasureResult) lazyGridState2.getLayoutInfo()).visibleItemsInfo), new Function1() { // from class: com.android.systemui.communal.ui.compose.DragAndDropTargetState$onMoved$targetItem$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return Boolean.valueOf(DragAndDropTargetState.this.contentListState.isItemEditable(((LazyGridMeasuredItem) ((LazyGridItemInfo) obj2)).index));
                        }
                    });
                    long m314minusMKHz9U = Offset.m314minusMKHz9U(floatToRawIntBits, dragAndDropTargetState3.contentOffset);
                    FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(filter);
                    while (true) {
                        if (!filteringSequence$iterator$1.hasNext()) {
                            obj = null;
                            break;
                        }
                        obj = filteringSequence$iterator$1.next();
                        LazyGridMeasuredItem lazyGridMeasuredItem = (LazyGridMeasuredItem) ((LazyGridItemInfo) obj);
                        IntRect m681IntRectVbeCjmY = IntRectKt.m681IntRectVbeCjmY(lazyGridMeasuredItem.offset, lazyGridMeasuredItem.size);
                        if (new Rect(m681IntRectVbeCjmY.left, m681IntRectVbeCjmY.top, m681IntRectVbeCjmY.right, m681IntRectVbeCjmY.bottom).m319containsk4lQ0M(m314minusMKHz9U)) {
                            break;
                        }
                    }
                    LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) obj;
                    if (lazyGridItemInfo == null) {
                        Orientation orientation = ((LazyGridMeasureResult) lazyGridState2.getLayoutInfo()).orientation;
                        Orientation orientation2 = Orientation.Horizontal;
                        float intBitsToFloat = orientation == orientation2 ? Float.intBitsToFloat((int) (floatToRawIntBits >> 32)) : Float.intBitsToFloat((int) (floatToRawIntBits & 4294967295L));
                        float intBitsToFloat2 = orientation == orientation2 ? ((LazyGridMeasureResult) lazyGridState2.getLayoutInfo()).viewportEndOffset - Float.intBitsToFloat((int) (floatToRawIntBits >> 32)) : ((LazyGridMeasureResult) lazyGridState2.getLayoutInfo()).viewportEndOffset - Float.intBitsToFloat((int) (floatToRawIntBits & 4294967295L));
                        float f = dragAndDropTargetState3.autoScrollThreshold;
                        float f2 = intBitsToFloat2 < f ? f - intBitsToFloat2 : intBitsToFloat < f ? intBitsToFloat - f : 0.0f;
                        Float valueOf = f2 != 0.0f ? Float.valueOf(f2) : null;
                        if (valueOf != null) {
                            dragAndDropTargetState3.scrollChannel.mo1790trySendJP2dKIU(Float.valueOf(valueOf.floatValue()));
                            return;
                        }
                        return;
                    }
                    Ref$ObjectRef ref$ObjectRef5 = new Ref$ObjectRef();
                    Ref$ObjectRef ref$ObjectRef6 = new Ref$ObjectRef();
                    Integer num = dragAndDropTargetState3.placeHolderIndex;
                    LazyGridScrollPosition lazyGridScrollPosition = lazyGridState2.scrollPosition;
                    int index = lazyGridScrollPosition.getIndex();
                    if (num != null && num.intValue() == index) {
                        ref$ObjectRef5.element = dragAndDropTargetState3.placeHolderIndex;
                        ref$ObjectRef6.element = Integer.valueOf(lazyGridScrollPosition.getScrollOffset());
                    }
                    ContentListState contentListState2 = dragAndDropTargetState3.contentListState;
                    int i7 = ((LazyGridMeasuredItem) lazyGridItemInfo).index;
                    if (contentListState2.isItemEditable(i7)) {
                        int indexOf = contentListState2.list.indexOf(dragAndDropTargetState3.placeHolder);
                        if (indexOf != i7) {
                            SnapshotStateList snapshotStateList = contentListState2.list;
                            snapshotStateList.add(i7, snapshotStateList.remove(indexOf));
                        }
                        dragAndDropTargetState3.placeHolderIndex = Integer.valueOf(i7);
                    }
                    if (ref$ObjectRef5.element == null || ref$ObjectRef6.element == null) {
                        return;
                    }
                    BuildersKt.launch$default(dragAndDropTargetState3.scope, null, null, new DragAndDropTargetState$onMoved$1(dragAndDropTargetState3, ref$ObjectRef5, ref$ObjectRef6, null), 3);
                }

                @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
                public final void onStarted(DragAndDropEvent dragAndDropEvent) {
                    DragAndDropTargetState dragAndDropTargetState3 = (DragAndDropTargetState) MutableState.this.getValue();
                    dragAndDropTargetState3.contentListState.list.add(dragAndDropTargetState3.placeHolder);
                    dragAndDropTargetState3.placeHolderIndex = Integer.valueOf(r3.list.size() - 1);
                }
            }));
            composerImpl.end(false);
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
            int i7 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, then3);
            ComposeUiNode.Companion.getClass();
            Function0 function0 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i7))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i7, composerImpl, i7, function2);
            }
            Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
            composerImpl.end(true);
            composerImpl.end(false);
            m108height3ABfNKs = then2;
        } else {
            ref$ObjectRef = ref$ObjectRef3;
            ref$ObjectRef2 = ref$ObjectRef4;
            composerImpl.startReplaceGroup(-435035714);
            Dimensions hubDimensions = getHubDimensions(composerImpl);
            IWindowManager iWindowManager = DensityUtils.windowManagerService;
            m108height3ABfNKs = SizeKt.m108height3ABfNKs(onGloballyPositioned, hubDimensions.m796getGridTopSpacingD9Ej5fM() + DensityUtils.Companion.m799getAdjustedDpu2uoSUM(530));
            composerImpl.end(false);
        }
        GridCells.Fixed fixed = new GridCells.Fixed(CommunalContentSize.FULL.getSpan());
        Arrangement$Start$1 arrangement$Start$1 = Arrangement.Start;
        PaddingValuesImpl paddingValuesImpl = Dimensions.ButtonPadding;
        final Ref$ObjectRef ref$ObjectRef5 = ref$ObjectRef;
        final Ref$ObjectRef ref$ObjectRef6 = ref$ObjectRef2;
        LazyGridDslKt.LazyHorizontalGrid(((i2 >> 15) & 896) | (i2 & 7168), 400, null, Arrangement.m78spacedBy0680j_4(Dimensions.Companion.m797getItemSpacingD9Ej5fM()), Arrangement.m78spacedBy0680j_4(Dimensions.Companion.m797getItemSpacingD9Ej5fM()), paddingValues, fixed, lazyGridState, composerImpl, m108height3ABfNKs, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHubLazyGrid$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LazyGridScope lazyGridScope = (LazyGridScope) obj;
                final List list2 = (List) Ref$ObjectRef.this.element;
                final AnonymousClass1 anonymousClass1 = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHubLazyGrid$2.1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        ((Number) obj2).intValue();
                        return ((CommunalContentModel) obj3).getKey();
                    }
                };
                final AnonymousClass2 anonymousClass2 = new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHubLazyGrid$2.2
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        ((Number) obj3).intValue();
                        return new GridItemSpan(LazyGridSpanKt.GridItemSpan(((CommunalContentModel) obj4).getSize().getSpan()));
                    }
                };
                final BaseCommunalViewModel baseCommunalViewModel2 = baseCommunalViewModel;
                final Ref$ObjectRef ref$ObjectRef7 = ref$ObjectRef6;
                final State state2 = state;
                final ContentListState contentListState2 = contentListState;
                final RemoteViews.InteractionHandler interactionHandler2 = interactionHandler;
                final CommunalAppWidgetSection communalAppWidgetSection2 = communalAppWidgetSection;
                final WidgetConfigurator widgetConfigurator2 = widgetConfigurator;
                ((LazyGridIntervalContent) lazyGridScope).items(list2.size(), anonymousClass1 != null ? new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHubLazyGrid$2$invoke$$inlined$itemsIndexed$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        int intValue = ((Number) obj2).intValue();
                        return Function2.this.invoke(Integer.valueOf(intValue), list2.get(intValue));
                    }
                } : null, anonymousClass2 != null ? new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHubLazyGrid$2$invoke$$inlined$itemsIndexed$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        int intValue = ((Number) obj3).intValue();
                        return new GridItemSpan(((GridItemSpan) Function3.this.invoke((LazyGridItemSpanScope) obj2, Integer.valueOf(intValue), list2.get(intValue))).packedValue);
                    }
                } : null, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHubLazyGrid$2$invoke$$inlined$itemsIndexed$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ((CommunalContentModel) list2.get(((Number) obj2).intValue())).getKey();
                    }
                }, new ComposableLambdaImpl(1229287273, true, new Function4() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHubLazyGrid$2$invoke$$inlined$itemsIndexed$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(4);
                    }

                    /* JADX WARN: Type inference failed for: r9v4, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHubLazyGrid$2$4$1, kotlin.jvm.internal.Lambda] */
                    @Override // kotlin.jvm.functions.Function4
                    public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                        int i8;
                        float m799getAdjustedDpu2uoSUM;
                        boolean z2;
                        ComposerImpl composerImpl2;
                        float m799getAdjustedDpu2uoSUM2;
                        float f;
                        LazyGridItemScope lazyGridItemScope = (LazyGridItemScope) obj2;
                        final int intValue = ((Number) obj3).intValue();
                        Composer composer2 = (Composer) obj4;
                        int intValue2 = ((Number) obj5).intValue();
                        if ((intValue2 & 6) == 0) {
                            i8 = (((ComposerImpl) composer2).changed(lazyGridItemScope) ? 4 : 2) | intValue2;
                        } else {
                            i8 = intValue2;
                        }
                        if ((intValue2 & 48) == 0) {
                            i8 |= ((ComposerImpl) composer2).changed(intValue) ? 32 : 16;
                        }
                        int i9 = i8;
                        if ((i9 & 147) == 146) {
                            ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                            if (composerImpl3.getSkipping()) {
                                composerImpl3.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey2 = ComposerKt.invocation;
                        int i10 = i9 & 126;
                        final CommunalContentModel communalContentModel = (CommunalContentModel) list2.get(intValue);
                        ComposerImpl composerImpl4 = (ComposerImpl) composer2;
                        composerImpl4.startReplaceGroup(-857123049);
                        PaddingValuesImpl paddingValuesImpl2 = Dimensions.ButtonPadding;
                        IWindowManager iWindowManager2 = DensityUtils.windowManagerService;
                        float m799getAdjustedDpu2uoSUM3 = DensityUtils.Companion.m799getAdjustedDpu2uoSUM(360);
                        int ordinal = communalContentModel.getSize().ordinal();
                        if (ordinal != 0) {
                            if (ordinal == 1) {
                                m799getAdjustedDpu2uoSUM2 = DensityUtils.Companion.m799getAdjustedDpu2uoSUM(530) - Dimensions.Companion.m797getItemSpacingD9Ej5fM();
                                f = 2;
                            } else {
                                if (ordinal != 2) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                m799getAdjustedDpu2uoSUM2 = DensityUtils.Companion.m799getAdjustedDpu2uoSUM(530) - (2 * Dimensions.Companion.m797getItemSpacingD9Ej5fM());
                                f = 3;
                            }
                            m799getAdjustedDpu2uoSUM = m799getAdjustedDpu2uoSUM2 / f;
                        } else {
                            m799getAdjustedDpu2uoSUM = DensityUtils.Companion.m799getAdjustedDpu2uoSUM(530);
                        }
                        final SizeF sizeF = new SizeF(m799getAdjustedDpu2uoSUM3, m799getAdjustedDpu2uoSUM);
                        Modifier modifier2 = Modifier.Companion.$$INSTANCE;
                        final Modifier m111requiredSizeVpY3zN4 = SizeKt.m111requiredSizeVpY3zN4(modifier2, sizeF.getWidth(), sizeF.getHeight());
                        if (!baseCommunalViewModel2.isEditMode() || ref$ObjectRef7.element == null) {
                            z2 = false;
                            composerImpl4.startReplaceGroup(-857121505);
                            composerImpl2 = composerImpl4;
                            CommunalHubKt.access$CommunalContent(communalContentModel, baseCommunalViewModel2, sizeF, false, LazyGridItemScope.animateItem$default(lazyGridItemScope, m111requiredSizeVpY3zN4, null, 7), null, intValue, contentListState2, interactionHandler2, communalAppWidgetSection2, composerImpl4, ((i10 << 15) & 3670016) | 1224740416, 32);
                            composerImpl2.end(false);
                        } else {
                            composerImpl4.startReplaceGroup(-857122796);
                            final boolean areEqual = Intrinsics.areEqual(communalContentModel.getKey(), state2.getValue());
                            Integer num = (Integer) ((SnapshotMutableStateImpl) ((GridDragDropState) ref$ObjectRef7.element).draggingItemIndex$delegate).getValue();
                            if (num == null || num.intValue() != intValue) {
                                modifier2 = LazyGridItemScope.animateItem$default(lazyGridItemScope, modifier2, AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5), 5);
                            }
                            Modifier modifier3 = modifier2;
                            final BaseCommunalViewModel baseCommunalViewModel3 = baseCommunalViewModel2;
                            final WidgetConfigurator widgetConfigurator3 = widgetConfigurator2;
                            final ContentListState contentListState3 = contentListState2;
                            final RemoteViews.InteractionHandler interactionHandler3 = interactionHandler2;
                            final CommunalAppWidgetSection communalAppWidgetSection3 = communalAppWidgetSection2;
                            GridDragDropStateKt.DraggableItem(lazyGridItemScope, (GridDragDropState) ref$ObjectRef7.element, intValue, communalContentModel instanceof CommunalContentModel.WidgetContent, areEqual, modifier3, ComposableLambdaKt.rememberComposableLambda(-1305837418, new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHubLazyGrid$2$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public final Object invoke(Object obj6, Object obj7, Object obj8) {
                                    boolean booleanValue = ((Boolean) obj6).booleanValue();
                                    Composer composer3 = (Composer) obj7;
                                    int intValue3 = ((Number) obj8).intValue();
                                    if ((intValue3 & 14) == 0) {
                                        intValue3 |= ((ComposerImpl) composer3).changed(booleanValue) ? 4 : 2;
                                    }
                                    if ((intValue3 & 91) == 18) {
                                        ComposerImpl composerImpl5 = (ComposerImpl) composer3;
                                        if (composerImpl5.getSkipping()) {
                                            composerImpl5.skipToGroupEnd();
                                            return Unit.INSTANCE;
                                        }
                                    }
                                    OpaqueKey opaqueKey3 = ComposerKt.invocation;
                                    CommunalHubKt.access$CommunalContent(communalContentModel, baseCommunalViewModel3, sizeF, areEqual && !booleanValue, m111requiredSizeVpY3zN4, widgetConfigurator3, intValue, contentListState3, interactionHandler3, communalAppWidgetSection3, composer3, 1224737344, 0);
                                    return Unit.INSTANCE;
                                }
                            }, composerImpl4), composerImpl4, (i9 & 14) | 1572928 | ((i10 << 3) & 896), 0);
                            z2 = false;
                            composerImpl4.end(false);
                            composerImpl2 = composerImpl4;
                        }
                        composerImpl2.end(z2);
                        return Unit.INSTANCE;
                    }
                }));
                return Unit.INSTANCE;
            }
        }, false, false);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$CommunalHubLazyGrid$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.m794access$CommunalHubLazyGrid_JG6Y(BoxScope.this, list, baseCommunalViewModel, paddingValues, state, i, j, lazyGridState, contentListState, function1, function12, widgetConfigurator, interactionHandler, communalAppWidgetSection, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$DisclaimerBottomSheetContent(final Function0 function0, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(31097356);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            AndroidColorScheme androidColorScheme = (AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme);
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            float f = 32;
            Modifier m99paddingVpY3zN4 = PaddingKt.m99paddingVpY3zN4(SizeKt.fillMaxWidth(companion, 1.0f), f, 24);
            ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Center, Alignment.Companion.CenterHorizontally, composerImpl, 54);
            int i3 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, m99paddingVpY3zN4);
            ComposeUiNode.Companion.getClass();
            Function0 function02 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function02);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, columnMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function2);
            }
            Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
            IconKt.m214Iconww6aTOc(WidgetsKt.getWidgets(), (String) null, SizeKt.m113size3ABfNKs(companion, f), androidColorScheme.primary, composerImpl, 432, 0);
            float f2 = 16;
            SpacerKt.Spacer(composerImpl, SizeKt.m108height3ABfNKs(companion, f2));
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.communal_widgets_disclaimer_title, composerImpl), null, androidColorScheme.onSurface, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composerImpl).headlineMedium, composerImpl, 0, 0, 65530);
            SpacerKt.Spacer(composerImpl, SizeKt.m108height3ABfNKs(companion, f2));
            TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.communal_widgets_disclaimer_text, composerImpl), null, androidColorScheme.onSurfaceVariant, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerImpl, 0, 0, 131066);
            Modifier m110heightInVpY3zN4$default = SizeKt.m110heightInVpY3zN4$default(SizeKt.m118widthInVpY3zN4$default(PaddingKt.m99paddingVpY3zN4(companion, 26, f2), 200, 0.0f, 2), 56, 0.0f, 2);
            composerImpl = composerImpl;
            composerImpl.startReplaceGroup(1209075690);
            boolean z = (i2 & 14) == 4;
            Object rememberedValue = composerImpl.rememberedValue();
            if (z || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$DisclaimerBottomSheetContent$1$1$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Function0.this.invoke();
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            composerImpl.end(false);
            ButtonKt.Button((Function0) rememberedValue, m110heightInVpY3zN4$default, false, null, null, null, null, null, null, ComposableSingletons$CommunalHubKt.f16lambda1, composerImpl, 805306416, 508);
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$DisclaimerBottomSheetContent$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.access$DisclaimerBottomSheetContent(function0, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$EmptyStateCta$1, kotlin.jvm.internal.Lambda] */
    public static final void access$EmptyStateCta(final PaddingValues paddingValues, final BaseCommunalViewModel baseCommunalViewModel, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1754062866);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final AndroidColorScheme androidColorScheme = (AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme);
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Dimensions hubDimensions = getHubDimensions(composerImpl);
        IWindowManager iWindowManager = DensityUtils.windowManagerService;
        CardKt.Card(PaddingKt.padding(SizeKt.m108height3ABfNKs(companion, hubDimensions.m796getGridTopSpacingD9Ej5fM() + DensityUtils.Companion.m799getAdjustedDpu2uoSUM(530)), paddingValues), RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(DensityUtils.Companion.m799getAdjustedDpu2uoSUM(80)), CardDefaults.m200cardColorsro_MJ88(Color.Transparent, 0L, composerImpl, 6, 14), null, BorderStrokeKt.m28BorderStrokecXLIe8U(DensityUtils.Companion.m799getAdjustedDpu2uoSUM(3), androidColorScheme.secondary), ComposableLambdaKt.rememberComposableLambda(-635955488, new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$EmptyStateCta$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Composer composer2 = (Composer) obj2;
                if ((((Number) obj3).intValue() & 81) == 16) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                Modifier.Companion companion2 = Modifier.Companion.$$INSTANCE;
                FillElement fillElement = SizeKt.FillWholeMaxSize;
                IWindowManager iWindowManager2 = DensityUtils.windowManagerService;
                Modifier m100paddingVpY3zN4$default = PaddingKt.m100paddingVpY3zN4$default(fillElement, DensityUtils.Companion.m799getAdjustedDpu2uoSUM(110), 0.0f, 2);
                Arrangement$Start$1 arrangement$Start$1 = Arrangement.Start;
                PaddingValuesImpl paddingValuesImpl = Dimensions.ButtonPadding;
                Arrangement.SpacedAligned m80spacedByD5KLDUw = Arrangement.m80spacedByD5KLDUw(Dimensions.Companion.m797getItemSpacingD9Ej5fM() / 2, Alignment.Companion.CenterVertically);
                BiasAlignment.Horizontal horizontal = Alignment.Companion.CenterHorizontally;
                AndroidColorScheme androidColorScheme2 = AndroidColorScheme.this;
                final BaseCommunalViewModel baseCommunalViewModel2 = baseCommunalViewModel;
                ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(m80spacedByD5KLDUw, horizontal, composer2, 48);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, m100paddingVpY3zN4$default);
                ComposeUiNode.Companion.getClass();
                Function0 function0 = ComposeUiNode.Companion.Constructor;
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function0);
                } else {
                    composerImpl3.useNode();
                }
                Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
                Updater.m259setimpl(composer2, columnMeasurePolicy, function2);
                Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
                Updater.m259setimpl(composer2, currentCompositionLocalScope, function22);
                Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl3, currentCompositeKeyHash, function23);
                }
                Function2 function24 = ComposeUiNode.Companion.SetModifier;
                Updater.m259setimpl(composer2, materializeModifier, function24);
                TextKt.m241Text4IGK_g(StringResources_androidKt.stringResource(R.string.title_for_empty_state_cta, composer2), null, androidColorScheme2.secondary, 0L, null, null, null, 0L, null, new TextAlign(3), 0L, 0, false, 0, 0, null, MaterialTheme.getTypography(composer2).displaySmall, composer2, 0, 0, 65018);
                Modifier fillMaxWidth = SizeKt.fillMaxWidth(companion2, 1.0f);
                RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.Center, Alignment.Companion.Top, composer2, 6);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer2, fillMaxWidth);
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function0);
                } else {
                    composerImpl3.useNode();
                }
                Updater.m259setimpl(composer2, rowMeasurePolicy, function2);
                Updater.m259setimpl(composer2, currentCompositionLocalScope2, function22);
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash2, composerImpl3, currentCompositeKeyHash2, function23);
                }
                Updater.m259setimpl(composer2, materializeModifier2, function24);
                Modifier m108height3ABfNKs = SizeKt.m108height3ABfNKs(companion2, 56);
                PaddingValuesImpl paddingValuesImpl2 = ButtonDefaults.ContentPadding;
                ButtonKt.Button(new Function0() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$EmptyStateCta$1$1$1$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        BaseCommunalViewModel.this.onOpenWidgetEditor(true);
                        return Unit.INSTANCE;
                    }
                }, m108height3ABfNKs, false, null, ButtonDefaults.m198buttonColorsro_MJ88(androidColorScheme2.primary, androidColorScheme2.onPrimary, composer2, 12), null, null, null, null, ComposableSingletons$CommunalHubKt.f17lambda2, composer2, 805306416, 492);
                composerImpl3.end(true);
                composerImpl3.end(true);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 196608, 8);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$EmptyStateCta$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.access$EmptyStateCta(PaddingValues.this, baseCommunalViewModel, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r0v28, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$Toolbar$2$2, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r1v20, types: [com.android.systemui.communal.ui.compose.CommunalHubKt$Toolbar$2$1, kotlin.jvm.internal.Lambda] */
    public static final void access$Toolbar(final boolean z, final Function0 function0, final Function1 function1, final Function1 function12, final Function0 function02, final Function0 function03, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1489933479);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changedInstance(function0) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerImpl.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= composerImpl.changedInstance(function12) ? 2048 : 1024;
        }
        if ((57344 & i) == 0) {
            i2 |= composerImpl.changedInstance(function02) ? 16384 : 8192;
        }
        if ((458752 & i) == 0) {
            i2 |= composerImpl.changedInstance(function03) ? 131072 : 65536;
        }
        int i3 = i2;
        if ((374491 & i3) == 74898 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            if (!z) {
                function12.invoke(null);
            }
            final State animateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.0f : 0.5f, null, "RemoveButtonAlphaAnimation", null, composerImpl, 3072, 22);
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            Modifier fillMaxWidth = SizeKt.fillMaxWidth(companion, 1.0f);
            PaddingValuesImpl paddingValuesImpl = Dimensions.ButtonPadding;
            IWindowManager iWindowManager = DensityUtils.windowManagerService;
            Modifier m102paddingqDBjuR0$default = PaddingKt.m102paddingqDBjuR0$default(fillMaxWidth, Dimensions.Companion.m797getItemSpacingD9Ej5fM(), DensityUtils.Companion.m799getAdjustedDpu2uoSUM(27), Dimensions.Companion.m797getItemSpacingD9Ej5fM(), 0.0f, 8);
            composerImpl.startReplaceGroup(-1894115038);
            boolean z2 = (i3 & 896) == 256;
            Object rememberedValue = composerImpl.rememberedValue();
            if (z2 || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$Toolbar$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Function1.this.invoke(new IntSize(((IntSize) obj).packedValue));
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            composerImpl.end(false);
            Modifier onSizeChanged = OnRemeasuredModifierKt.onSizeChanged(m102paddingqDBjuR0$default, (Function1) rememberedValue);
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
            int i4 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, onSizeChanged);
            ComposeUiNode.Companion.getClass();
            Function0 function04 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function04);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function2);
            }
            Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            final String stringResource = StringResources_androidKt.stringResource(R.string.hub_mode_add_widget_button_text, composerImpl);
            boolean z3 = !z;
            ToolbarButton(z3, function02, boxScopeInstance.align(companion, Alignment.Companion.CenterStart), ComposableLambdaKt.rememberComposableLambda(-763302154, new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$Toolbar$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Composer composer2 = (Composer) obj2;
                    if ((((Number) obj3).intValue() & 81) == 16) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                        if (composerImpl2.getSkipping()) {
                            composerImpl2.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    IconKt.m214Iconww6aTOc(AddKt.getAdd(), (String) null, (Modifier) null, 0L, composer2, 48, 12);
                    TextKt.m241Text4IGK_g(stringResource, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 131070);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, ((i3 >> 9) & 112) | 3072, 0);
            AnimatedVisibilityKt.AnimatedVisibility(z, boxScopeInstance.align(companion, Alignment.Companion.Center), EnterExitTransitionKt.fadeIn$default(null, 3), EnterExitTransitionKt.fadeOut$default(null, 3), null, ComposableLambdaKt.rememberComposableLambda(545396663, new Function3() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$Toolbar$2$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Composer composer2 = (Composer) obj2;
                    ((Number) obj3).intValue();
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    ButtonColors access$filledButtonColors = CommunalHubKt.access$filledButtonColors(composer2);
                    PaddingValuesImpl paddingValuesImpl2 = Dimensions.ButtonPadding;
                    Modifier.Companion companion2 = Modifier.Companion.$$INSTANCE;
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    composerImpl2.startReplaceGroup(-331381992);
                    boolean changed = composerImpl2.changed(State.this);
                    final State state = State.this;
                    Object rememberedValue2 = composerImpl2.rememberedValue();
                    Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
                    if (changed || rememberedValue2 == composer$Companion$Empty$1) {
                        rememberedValue2 = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$Toolbar$2$2$1$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                ((ReusableGraphicsLayerScope) ((GraphicsLayerScope) obj4)).setAlpha(((Number) State.this.getValue()).floatValue());
                                return Unit.INSTANCE;
                            }
                        };
                        composerImpl2.updateRememberedValue(rememberedValue2);
                    }
                    composerImpl2.end(false);
                    Modifier graphicsLayer = GraphicsLayerModifierKt.graphicsLayer(companion2, (Function1) rememberedValue2);
                    composerImpl2.startReplaceGroup(-331381916);
                    boolean changed2 = composerImpl2.changed(z) | composerImpl2.changed(function12);
                    final boolean z4 = z;
                    final Function1 function13 = function12;
                    Object rememberedValue3 = composerImpl2.rememberedValue();
                    if (changed2 || rememberedValue3 == composer$Companion$Empty$1) {
                        rememberedValue3 = new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$Toolbar$2$2$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                LayoutCoordinates layoutCoordinates = (LayoutCoordinates) obj4;
                                if (z4) {
                                    function13.invoke(layoutCoordinates);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        composerImpl2.updateRememberedValue(rememberedValue3);
                    }
                    composerImpl2.end(false);
                    ButtonKt.Button(function0, OnGloballyPositionedModifierKt.onGloballyPositioned(graphicsLayer, (Function1) rememberedValue3), false, null, access$filledButtonColors, null, null, paddingValuesImpl2, null, ComposableSingletons$CommunalHubKt.f18lambda3, composerImpl2, 817889280, 364);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, (i3 & 14) | 200064, 16);
            ToolbarButton(z3, function03, boxScopeInstance.align(companion, Alignment.Companion.CenterEnd), ComposableSingletons$CommunalHubKt.f19lambda4, composerImpl, ((i3 >> 12) & 112) | 3072, 0);
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalHubKt$Toolbar$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalHubKt.access$Toolbar(z, function0, function1, function12, function02, function03, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final ButtonColors access$filledButtonColors(Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-878954106);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        AndroidColorScheme androidColorScheme = (AndroidColorScheme) composerImpl.consume(AndroidColorSchemeKt.LocalAndroidColorScheme);
        PaddingValuesImpl paddingValuesImpl = ButtonDefaults.ContentPadding;
        ButtonColors m198buttonColorsro_MJ88 = ButtonDefaults.m198buttonColorsro_MJ88(androidColorScheme.primary, androidColorScheme.onPrimary, composerImpl, 12);
        composerImpl.end(false);
        return m198buttonColorsro_MJ88;
    }

    public static final String access$keyAtIndexIfEditable(int i, List list) {
        if (i >= 0 && i < list.size()) {
            CommunalContentModel communalContentModel = (CommunalContentModel) list.get(i);
            communalContentModel.getClass();
            if (communalContentModel instanceof CommunalContentModel.WidgetContent) {
                return ((CommunalContentModel) list.get(i)).getKey();
            }
        }
        return null;
    }

    public static final Dimensions getHubDimensions(Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-1423256976);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Dimensions dimensions = new Dimensions((Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext), (Configuration) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalConfiguration));
        composerImpl.end(false);
        return dimensions;
    }

    /* renamed from: isPointerWithinEnabledRemoveButton-_UaWb3I, reason: not valid java name */
    public static final boolean m795isPointerWithinEnabledRemoveButton_UaWb3I(boolean z, Offset offset, LayoutCoordinates layoutCoordinates) {
        if (!z || offset == null || layoutCoordinates == null) {
            return false;
        }
        return LayoutCoordinatesKt.boundsInWindow(layoutCoordinates).m319containsk4lQ0M(offset.packedValue);
    }
}
