package com.android.systemui.qs.panels.ui.compose.infinitegrid;

import android.content.Context;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.BorderModifierNodeElement;
import androidx.compose.foundation.OverscrollConfiguration_androidKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.Arrangement$Start$1;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.FillElement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValuesImpl;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.grid.GridCells;
import androidx.compose.foundation.lazy.grid.GridItemSpan;
import androidx.compose.foundation.lazy.grid.LazyGridIntervalContent;
import androidx.compose.foundation.lazy.grid.LazyGridItemScope;
import androidx.compose.foundation.lazy.grid.LazyGridScope;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.foundation.lazy.grid.LazyGridStateKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.filled.ClearKt;
import androidx.compose.material3.AppBarKt$$ExternalSyntheticOutline0;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyImpl;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.TestTagKt;
import androidx.compose.ui.res.PrimitiveResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import com.android.compose.modifiers.FadingBackgroundKt;
import com.android.systemui.qs.panels.shared.model.SizedTile;
import com.android.systemui.qs.panels.shared.model.SizedTileImpl;
import com.android.systemui.qs.panels.ui.compose.DragAndDropState;
import com.android.systemui.qs.panels.ui.compose.DragAndDropStateKt;
import com.android.systemui.qs.panels.ui.compose.EditTileListState;
import com.android.systemui.qs.panels.ui.compose.selection.MutableSelectionState;
import com.android.systemui.qs.panels.ui.compose.selection.ResizingState;
import com.android.systemui.qs.panels.ui.compose.selection.Selection;
import com.android.systemui.qs.panels.ui.compose.selection.SelectionKt;
import com.android.systemui.qs.panels.ui.compose.selection.TileWidths;
import com.android.systemui.qs.panels.ui.model.GridCell;
import com.android.systemui.qs.panels.ui.model.SpacerGridCell;
import com.android.systemui.qs.panels.ui.model.TileGridCell;
import com.android.systemui.qs.panels.ui.model.TileGridCellKt;
import com.android.systemui.qs.panels.ui.viewmodel.EditTileViewModel;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class EditTileKt {
    /* JADX WARN: Type inference failed for: r3v5, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$AvailableTileGridCell$1$3, kotlin.jvm.internal.Lambda] */
    public static final void AvailableTileGridCell(final TileGridCell tileGridCell, final int i, final DragAndDropState dragAndDropState, final MutableSelectionState mutableSelectionState, Modifier modifier, Composer composer, final int i2, final int i3) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(2016925139);
        int i4 = i3 & 16;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i4 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final String stringResource = StringResources_androidKt.stringResource(R.string.accessibility_qs_edit_tile_add_action, composerImpl);
        Object[] objArr = {Integer.valueOf(i + 1)};
        composerImpl.consume(AndroidCompositionLocals_androidKt.LocalConfiguration);
        final String string = ((Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext)).getResources().getString(R.string.accessibility_qs_edit_position, Arrays.copyOf(objArr, 1));
        final TileColors editTileColors = EditModeTileDefaults.editTileColors(composerImpl);
        BiasAlignment.Horizontal horizontal = Alignment.Companion.CenterHorizontally;
        Arrangement$Start$1 arrangement$Start$1 = Arrangement.Start;
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.m80spacedByD5KLDUw(CommonTileDefaults.TilePadding, Alignment.Companion.Top), horizontal, composerImpl, 54);
        int i5 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, modifier2);
        ComposeUiNode.Companion.getClass();
        Function0 function0 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
        Updater.m259setimpl(composerImpl, columnMeasurePolicy, function2);
        Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function22);
        Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function23);
        }
        Function2 function24 = ComposeUiNode.Companion.SetModifier;
        Updater.m259setimpl(composerImpl, materializeModifier, function24);
        Modifier m108height3ABfNKs = SizeKt.m108height3ABfNKs(SizeKt.fillMaxWidth(companion, 1.0f), CommonTileDefaults.TileHeight);
        composerImpl.startReplaceGroup(-21539827);
        Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(m108height3ABfNKs, Unit.INSTANCE, new PointerInputEventHandler() { // from class: com.android.systemui.qs.panels.ui.compose.selection.MutableSelectionStateKt$clearSelectionTile$1
            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                final MutableSelectionState mutableSelectionState2 = MutableSelectionState.this;
                Object detectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.selection.MutableSelectionStateKt$clearSelectionTile$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        long j = ((Offset) obj).packedValue;
                        MutableSelectionState mutableSelectionState3 = MutableSelectionState.this;
                        ((SnapshotMutableStateImpl) mutableSelectionState3._selection).setValue(null);
                        mutableSelectionState3.onResizingDragEnd();
                        return Unit.INSTANCE;
                    }
                }, continuation, 7);
                return detectTapGestures$default == CoroutineSingletons.COROUTINE_SUSPENDED ? detectTapGestures$default : Unit.INSTANCE;
            }
        });
        composerImpl.end(false);
        composerImpl.startReplaceGroup(-537559915);
        boolean changed = composerImpl.changed(stringResource) | composerImpl.changed(string);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$AvailableTileGridCell$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
                    SemanticsPropertiesKt.onClick(semanticsPropertyReceiver, stringResource, new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$AvailableTileGridCell$1$1$1.1
                        @Override // kotlin.jvm.functions.Function0
                        public final /* bridge */ /* synthetic */ Object invoke() {
                            return Boolean.FALSE;
                        }
                    });
                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, string);
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        final Modifier modifier3 = modifier2;
        m844EditTileContaineruDo3WH8(editTileColors, DragAndDropStateKt.dragAndDropTileSource(SemanticsModifierKt.semantics(pointerInput, true, (Function1) rememberedValue), new SizedTileImpl(tileGridCell.width, tileGridCell.tile), dragAndDropState, new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$AvailableTileGridCell$1$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                MutableSelectionState mutableSelectionState2 = MutableSelectionState.this;
                ((SnapshotMutableStateImpl) mutableSelectionState2._selection).setValue(null);
                mutableSelectionState2.onResizingDragEnd();
                return Unit.INSTANCE;
            }
        }, composerImpl, i2 & 896), null, 0L, ComposableLambdaKt.rememberComposableLambda(466278764, new Function3() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$AvailableTileGridCell$1$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                BoxScope boxScope = (BoxScope) obj;
                Composer composer2 = (Composer) obj2;
                int intValue = ((Number) obj3).intValue();
                if ((intValue & 14) == 0) {
                    intValue |= ((ComposerImpl) composer2).changed(boxScope) ? 4 : 2;
                }
                if ((intValue & 91) == 18) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                CommonTileKt.m843SmallTileContentcf5BqRc(boxScope.align(Modifier.Companion.$$INSTANCE, Alignment.Companion.Center), TileGridCell.this.tile.icon, editTileColors.icon, false, composer2, 0, 8);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 24576, 12);
        FillElement fillElement = SizeKt.FillWholeMaxSize;
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i6 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, fillElement);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function2);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function22);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i6))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i6, composerImpl, i6, function23);
        }
        Updater.m259setimpl(composerImpl, materializeModifier2, function24);
        TextKt.m241Text4IGK_g(tileGridCell.tile.label.text, BoxScopeInstance.INSTANCE.align(companion, Alignment.Companion.Center), editTileColors.label, 0L, null, null, null, 0L, null, new TextAlign(3), 0L, 2, false, 2, 0, null, null, composerImpl, 0, 3120, 120312);
        composerImpl.end(true);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$AvailableTileGridCell$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    EditTileKt.AvailableTileGridCell(TileGridCell.this, i, dragAndDropState, mutableSelectionState, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r13v0, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$DefaultEditTileGrid$1, kotlin.jvm.internal.Lambda] */
    public static final void DefaultEditTileGrid(final EditTileListState editTileListState, final List list, final int i, final Modifier modifier, final Function1 function1, final Function1 function12, final Function2 function2, Composer composer, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-875304966);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(editTileListState, composerImpl);
        composerImpl.startReplaceGroup(493235868);
        boolean changed = composerImpl.changed(rememberUpdatedState);
        Object rememberedValue = composerImpl.rememberedValue();
        Object obj = Composer.Companion.Empty;
        if (changed || rememberedValue == obj) {
            rememberedValue = new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$DefaultEditTileGrid$selectionState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    EditTileListState editTileListState2 = (EditTileListState) rememberUpdatedState.getValue();
                    int indexOf = editTileListState2.indexOf((TileSpec) obj2);
                    if (indexOf != -1) {
                        SnapshotStateList snapshotStateList = editTileListState2._tiles;
                        TileGridCell tileGridCell = (TileGridCell) ((GridCell) snapshotStateList.remove(indexOf));
                        int i3 = tileGridCell.getWidth() == 1 ? 2 : 1;
                        snapshotStateList.add(indexOf, new TileGridCell(tileGridCell.tile, tileGridCell.row, i3, tileGridCell.span, tileGridCell.s));
                        int row = ((GridCell) snapshotStateList.get(indexOf)).getRow();
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        ListIterator listIterator = snapshotStateList.listIterator();
                        while (listIterator.hasNext()) {
                            Object next = listIterator.next();
                            if (((GridCell) next).getRow() < row) {
                                arrayList.add(next);
                            } else {
                                arrayList2.add(next);
                            }
                        }
                        Pair pair = new Pair(arrayList, arrayList2);
                        List list2 = (List) pair.component1();
                        List list3 = (List) pair.component2();
                        ArrayList arrayList3 = new ArrayList();
                        for (Object obj3 : list3) {
                            if (obj3 instanceof TileGridCell) {
                                arrayList3.add(obj3);
                            }
                        }
                        List gridCells = TileGridCellKt.toGridCells(editTileListState2.columns, row, arrayList3);
                        snapshotStateList.clear();
                        snapshotStateList.addAll(list2);
                        snapshotStateList.addAll(gridCells);
                    }
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        Function1 function13 = (Function1) rememberedValue;
        composerImpl.end(false);
        composerImpl.startReplaceGroup(493235931);
        boolean changed2 = composerImpl.changed(rememberUpdatedState) | ((((i2 & 3670016) ^ 1572864) > 1048576 && composerImpl.changed(function2)) || (i2 & 1572864) == 1048576);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed2 || rememberedValue2 == obj) {
            rememberedValue2 = new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$DefaultEditTileGrid$selectionState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Boolean bool;
                    TileSpec tileSpec = (TileSpec) obj2;
                    EditTileListState editTileListState2 = (EditTileListState) rememberUpdatedState.getValue();
                    int indexOf = editTileListState2.indexOf(tileSpec);
                    if (indexOf != -1) {
                        bool = Boolean.valueOf(((TileGridCell) ((GridCell) editTileListState2._tiles.get(indexOf))).getWidth() == 1);
                    } else {
                        bool = null;
                    }
                    if (bool != null) {
                        function2.invoke(tileSpec, bool);
                    }
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        Function1 function14 = (Function1) rememberedValue2;
        composerImpl.end(false);
        composerImpl.startReplaceGroup(-1797109690);
        composerImpl.startReplaceGroup(1768368034);
        Object rememberedValue3 = composerImpl.rememberedValue();
        if (rememberedValue3 == obj) {
            rememberedValue3 = new MutableSelectionState(function13, function14);
            composerImpl.updateRememberedValue(rememberedValue3);
        }
        final MutableSelectionState mutableSelectionState = (MutableSelectionState) rememberedValue3;
        composerImpl.end(false);
        composerImpl.end(false);
        CompositionLocalKt.CompositionLocalProvider(OverscrollConfiguration_androidKt.LocalOverscrollConfiguration.defaultProvidedValue$runtime_release(null), ComposableLambdaKt.rememberComposableLambda(1714817850, new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$DefaultEditTileGrid$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r0v17, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$DefaultEditTileGrid$1$1$2, kotlin.jvm.internal.Lambda] */
            /* JADX WARN: Type inference failed for: r2v6, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$DefaultEditTileGrid$1$1$1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj2, Object obj3) {
                Modifier weight;
                Composer composer2 = (Composer) obj2;
                if ((((Number) obj3).intValue() & 11) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                Arrangement$Start$1 arrangement$Start$1 = Arrangement.Start;
                Arrangement.SpacedAligned m78spacedBy0680j_4 = Arrangement.m78spacedBy0680j_4(PrimitiveResources_androidKt.dimensionResource(R.dimen.qs_label_container_margin, composer2));
                Modifier verticalScroll$default = ScrollKt.verticalScroll$default(Modifier.this.then(SizeKt.FillWholeMaxSize), ScrollKt.rememberScrollState(composer2), false, 14);
                final EditTileListState editTileListState2 = editTileListState;
                final MutableSelectionState mutableSelectionState2 = mutableSelectionState;
                final int i3 = i;
                Function2 function22 = function2;
                Function1 function15 = function12;
                final Function1 function16 = function1;
                final Modifier modifier2 = Modifier.this;
                final List list2 = list;
                ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(m78spacedBy0680j_4, Alignment.Companion.Start, composer2, 0);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, verticalScroll$default);
                ComposeUiNode.Companion.getClass();
                Function0 function0 = ComposeUiNode.Companion.Constructor;
                composerImpl3.startReusableNode();
                if (composerImpl3.inserting) {
                    composerImpl3.createNode(function0);
                } else {
                    composerImpl3.useNode();
                }
                Updater.m259setimpl(composer2, columnMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                Updater.m259setimpl(composer2, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl3, currentCompositeKeyHash, function23);
                }
                Updater.m259setimpl(composer2, materializeModifier, ComposeUiNode.Companion.SetModifier);
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                Boolean valueOf = Boolean.valueOf(((SnapshotMutableStateImpl) editTileListState2._draggedCell).getValue() != null);
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                AnimatedContentKt.AnimatedContent(valueOf, SizeKt.wrapContentSize$default(companion, 3), null, null, "", null, ComposableLambdaKt.rememberComposableLambda(1368944225, new Function4() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$DefaultEditTileGrid$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(4);
                    }

                    /* JADX WARN: Type inference failed for: r3v4, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$DefaultEditTileGrid$1$1$1$1, kotlin.jvm.internal.Lambda] */
                    @Override // kotlin.jvm.functions.Function4
                    public final Object invoke(Object obj4, Object obj5, Object obj6, Object obj7) {
                        final boolean booleanValue = ((Boolean) obj5).booleanValue();
                        Composer composer3 = (Composer) obj6;
                        ((Number) obj7).intValue();
                        OpaqueKey opaqueKey3 = ComposerKt.invocation;
                        EditTileKt.access$EditGridHeader(DragAndDropStateKt.dragAndDropRemoveZone(Modifier.Companion.$$INSTANCE, EditTileListState.this, function16, composer3, 6), ComposableLambdaKt.rememberComposableLambda(125567971, new Function3() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$DefaultEditTileGrid$1$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(3);
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(Object obj8, Object obj9, Object obj10) {
                                Composer composer4 = (Composer) obj9;
                                if ((((Number) obj10).intValue() & 81) == 16) {
                                    ComposerImpl composerImpl4 = (ComposerImpl) composer4;
                                    if (composerImpl4.getSkipping()) {
                                        composerImpl4.skipToGroupEnd();
                                        return Unit.INSTANCE;
                                    }
                                }
                                OpaqueKey opaqueKey4 = ComposerKt.invocation;
                                if (booleanValue) {
                                    ComposerImpl composerImpl5 = (ComposerImpl) composer4;
                                    composerImpl5.startReplaceGroup(1135492003);
                                    EditTileKt.access$RemoveTileTarget(0, composerImpl5);
                                    composerImpl5.end(false);
                                } else {
                                    ComposerImpl composerImpl6 = (ComposerImpl) composer4;
                                    composerImpl6.startReplaceGroup(1135492075);
                                    TextKt.m241Text4IGK_g("Hold and drag to rearrange tiles.", null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerImpl6, 6, 0, 131070);
                                    composerImpl6.end(false);
                                }
                                return Unit.INSTANCE;
                            }
                        }, composer3), composer3, 48, 0);
                        return Unit.INSTANCE;
                    }
                }, composer2), composer2, 1597488, 44);
                EditTileKt.access$CurrentTilesGrid(editTileListState2, mutableSelectionState2, i3, function22, function15, composer2, 64);
                AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, !(((SnapshotMutableStateImpl) editTileListState2._draggedCell).getValue() != null), null, EnterExitTransitionKt.fadeIn$default(null, 3), EnterExitTransitionKt.fadeOut$default(null, 3), null, ComposableLambdaKt.rememberComposableLambda(-1177230292, new Function3() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$DefaultEditTileGrid$1$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        Composer composer3 = (Composer) obj5;
                        ((Number) obj6).intValue();
                        OpaqueKey opaqueKey3 = ComposerKt.invocation;
                        Arrangement$Start$1 arrangement$Start$12 = Arrangement.Start;
                        Arrangement.SpacedAligned m78spacedBy0680j_42 = Arrangement.m78spacedBy0680j_4(PrimitiveResources_androidKt.dimensionResource(R.dimen.qs_label_container_margin, composer3));
                        Modifier then = Modifier.this.then(SizeKt.FillWholeMaxSize);
                        List list3 = list2;
                        MutableSelectionState mutableSelectionState3 = mutableSelectionState2;
                        int i4 = i3;
                        EditTileListState editTileListState3 = editTileListState2;
                        ColumnMeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(m78spacedBy0680j_42, Alignment.Companion.Start, composer3, 0);
                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3);
                        ComposerImpl composerImpl4 = (ComposerImpl) composer3;
                        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl4.currentCompositionLocalScope();
                        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer3, then);
                        ComposeUiNode.Companion.getClass();
                        Function0 function02 = ComposeUiNode.Companion.Constructor;
                        composerImpl4.startReusableNode();
                        if (composerImpl4.inserting) {
                            composerImpl4.createNode(function02);
                        } else {
                            composerImpl4.useNode();
                        }
                        Updater.m259setimpl(composer3, columnMeasurePolicy2, ComposeUiNode.Companion.SetMeasurePolicy);
                        Updater.m259setimpl(composer3, currentCompositionLocalScope2, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                        Function2 function24 = ComposeUiNode.Companion.SetCompositeKeyHash;
                        if (composerImpl4.inserting || !Intrinsics.areEqual(composerImpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash2, composerImpl4, currentCompositeKeyHash2, function24);
                        }
                        Updater.m259setimpl(composer3, materializeModifier2, ComposeUiNode.Companion.SetModifier);
                        EditTileKt.access$EditGridHeader(null, ComposableSingletons$EditTileKt.f41lambda1, composer3, 48, 1);
                        EditTileKt.access$AvailableTileGrid(list3, mutableSelectionState3, i4, editTileListState3, composer3, 72);
                        composerImpl4.end(true);
                        return Unit.INSTANCE;
                    }
                }, composer2), composer2, 1600518, 18);
                weight = ColumnScopeInstance.INSTANCE.weight(SizeKt.fillMaxWidth(companion, 1.0f), 1.0f, true);
                SpacerKt.Spacer(composer2, DragAndDropStateKt.dragAndDropRemoveZone(weight, editTileListState2, function16, composer2, 0));
                composerImpl3.end(true);
                return Unit.INSTANCE;
            }
        }, composerImpl), composerImpl, 56);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$DefaultEditTileGrid$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    EditTileKt.DefaultEditTileGrid(EditTileListState.this, list, i, modifier, function1, function12, function2, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0075  */
    /* JADX WARN: Type inference failed for: r7v24, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$EditTile$2, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void EditTile(final com.android.systemui.qs.panels.ui.viewmodel.EditTileViewModel r16, final boolean r17, androidx.compose.ui.Modifier r18, com.android.systemui.qs.panels.ui.compose.infinitegrid.TileColors r19, kotlin.jvm.functions.Function0 r20, androidx.compose.runtime.Composer r21, final int r22, final int r23) {
        /*
            Method dump skipped, instructions count: 344
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt.EditTile(com.android.systemui.qs.panels.ui.viewmodel.EditTileViewModel, boolean, androidx.compose.ui.Modifier, com.android.systemui.qs.panels.ui.compose.infinitegrid.TileColors, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0170, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r3.rememberedValue(), java.lang.Integer.valueOf(r4)) == false) goto L105;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0118 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0191 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0082  */
    /* renamed from: EditTileContainer-uDo3WH8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m844EditTileContaineruDo3WH8(final com.android.systemui.qs.panels.ui.compose.infinitegrid.TileColors r18, androidx.compose.ui.Modifier r19, kotlin.jvm.functions.Function0 r20, long r21, kotlin.jvm.functions.Function3 r23, androidx.compose.runtime.Composer r24, final int r25, final int r26) {
        /*
            Method dump skipped, instructions count: 539
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt.m844EditTileContaineruDo3WH8(com.android.systemui.qs.panels.ui.compose.infinitegrid.TileColors, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function0, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Type inference failed for: r5v9, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$SelectedTile$2$handle$1, kotlin.jvm.internal.Lambda] */
    public static final void SelectedTile(final boolean z, final Function0 function0, final MutableSelectionState mutableSelectionState, Modifier modifier, final Function2 function2, Composer composer, final int i, final int i2) {
        TileWidths tileWidths;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1454167791);
        int i3 = i2 & 8;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(570963962);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (rememberedValue == composer$Companion$Empty$1) {
            rememberedValue = SnapshotStateKt.mutableStateOf$default(null);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final MutableState mutableState = (MutableState) rememberedValue;
        composerImpl.end(false);
        ResizingState resizingState = (ResizingState) ((SnapshotMutableStateImpl) mutableSelectionState.resizingState$delegate).getValue();
        final State animateValueAsState = AnimateAsStateKt.animateValueAsState(Integer.valueOf((resizingState == null || (tileWidths = (TileWidths) mutableState.getValue()) == null) ? 0 : tileWidths.base - ((SnapshotMutableIntStateImpl) resizingState.width$delegate).getIntValue()), VectorConvertersKt.IntToVector, AnimateAsStateKt.intDefaultSpring, null, "QSEditTileWidthOffset", null, composerImpl, 24576, 8);
        final int mo45roundToPx0680j_4 = ((Density) composerImpl.consume(CompositionLocalsKt.LocalDensity)).mo45roundToPx0680j_4(CommonTileDefaults.TileArrangementPadding);
        composerImpl.startReplaceGroup(570964488);
        boolean changed = ((((i & 14) ^ 6) > 4 && composerImpl.changed(z)) || (i & 6) == 4) | composerImpl.changed(mo45roundToPx0680j_4);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed || rememberedValue2 == composer$Companion$Empty$1) {
            rememberedValue2 = new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$SelectedTile$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    long j = ((IntSize) obj).packedValue;
                    boolean z2 = z;
                    mutableState.setValue(new TileWidths((int) (j >> 32), z2 ? (int) (j >> 32) : (((int) (j >> 32)) - mo45roundToPx0680j_4) / 2, z2 ? (((int) (j >> 32)) * 2) + mo45roundToPx0680j_4 : (int) (j >> 32)));
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        composerImpl.end(false);
        Modifier onSizeChanged = OnRemeasuredModifierKt.onSizeChanged(modifier2, (Function1) rememberedValue2);
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i4 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, onSizeChanged);
        ComposeUiNode.Companion.getClass();
        Function0 function02 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function02);
        } else {
            composerImpl.useNode();
        }
        Function2 function22 = ComposeUiNode.Companion.SetMeasurePolicy;
        Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function22);
        Function2 function23 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function23);
        Function2 function24 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function24);
        }
        Function2 function25 = ComposeUiNode.Companion.SetModifier;
        Updater.m259setimpl(composerImpl, materializeModifier, function25);
        List listOf = CollectionsKt__CollectionsKt.listOf(function2, ComposableLambdaKt.rememberComposableLambda(1390297607, new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$SelectedTile$2$handle$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Composer composer2 = (Composer) obj;
                if ((((Number) obj2).intValue() & 11) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                MutableSelectionState mutableSelectionState2 = MutableSelectionState.this;
                Function0 function03 = function0;
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                composerImpl3.startReplaceGroup(-575327838);
                final MutableState mutableState2 = mutableState;
                Object rememberedValue3 = composerImpl3.rememberedValue();
                if (rememberedValue3 == Composer.Companion.Empty) {
                    rememberedValue3 = new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$SelectedTile$2$handle$1$1$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return (TileWidths) MutableState.this.getValue();
                        }
                    };
                    composerImpl3.updateRememberedValue(rememberedValue3);
                }
                composerImpl3.end(false);
                SelectionKt.ResizingHandle(true, mutableSelectionState2, function03, (Function0) rememberedValue3, composerImpl3, 3142, 0);
                return Unit.INSTANCE;
            }
        }, composerImpl));
        MultiContentMeasurePolicy multiContentMeasurePolicy = new MultiContentMeasurePolicy() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$SelectedTile$2$1
            @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
            /* renamed from: measure-3p2s80s */
            public final MeasureResult mo91measure3p2s80s(MeasureScope measureScope, List list, long j) {
                MeasureResult layout$1;
                ArrayList arrayList = (ArrayList) list;
                List list2 = (List) arrayList.get(0);
                List list3 = (List) arrayList.get(1);
                ResizingState resizingState2 = (ResizingState) ((SnapshotMutableStateImpl) MutableSelectionState.this.resizingState$delegate).getValue();
                final Placeable mo479measureBRTryo0 = ((Measurable) CollectionsKt.first(list2)).mo479measureBRTryo0(Constraints.m648copyZbe2FdA$default(j, 0, resizingState2 != null ? ((SnapshotMutableIntStateImpl) resizingState2.width$delegate).getIntValue() : Constraints.m655getMaxWidthimpl(j) - ((Number) animateValueAsState.getValue()).intValue(), 0, 0, 13));
                final Placeable mo479measureBRTryo02 = ((Measurable) CollectionsKt.first(list3)).mo479measureBRTryo0(j);
                final int i5 = mo479measureBRTryo0.width - (mo479measureBRTryo02.width / 2);
                final int i6 = (mo479measureBRTryo0.height / 2) - (mo479measureBRTryo02.height / 2);
                layout$1 = measureScope.layout$1(Constraints.m655getMaxWidthimpl(j), Constraints.m654getMaxHeightimpl(j), MapsKt.emptyMap(), new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$SelectedTile$2$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                        placementScope.place(Placeable.this, 0, 0, 0.0f);
                        placementScope.place(mo479measureBRTryo02, i5, i6, 0.0f);
                        return Unit.INSTANCE;
                    }
                });
                return layout$1;
            }
        };
        ComposableLambdaImpl combineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listOf);
        boolean changed2 = composerImpl.changed(multiContentMeasurePolicy);
        Object rememberedValue3 = composerImpl.rememberedValue();
        if (changed2 || rememberedValue3 == composer$Companion$Empty$1) {
            rememberedValue3 = new MultiContentMeasurePolicyImpl(multiContentMeasurePolicy);
            composerImpl.updateRememberedValue(rememberedValue3);
        }
        MeasurePolicy measurePolicy = (MeasurePolicy) rememberedValue3;
        int i5 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, companion);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function02);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, measurePolicy, function22);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function23);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function24);
        }
        Updater.m259setimpl(composerImpl, materializeModifier2, function25);
        combineAsVirtualLayouts.invoke((Object) composerImpl, (Object) 0);
        composerImpl.end(true);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$SelectedTile$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    EditTileKt.SelectedTile(z, function0, mutableSelectionState, modifier3, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$UnselectedTile$handle$1, kotlin.jvm.internal.Lambda] */
    public static final void UnselectedTile(final Function0 function0, final MutableSelectionState mutableSelectionState, Modifier modifier, final Function2 function2, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(576417452);
        int i3 = i2 & 4;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        final Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposableLambdaImpl rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-533815414, new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$UnselectedTile$handle$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Composer composer2 = (Composer) obj;
                if ((((Number) obj2).intValue() & 11) == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return Unit.INSTANCE;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                SelectionKt.ResizingHandle(false, MutableSelectionState.this, function0, null, composer2, 70, 8);
                return Unit.INSTANCE;
            }
        }, composerImpl);
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i4 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, modifier2);
        ComposeUiNode.Companion.getClass();
        Function0 function02 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function02);
        } else {
            composerImpl.useNode();
        }
        Function2 function22 = ComposeUiNode.Companion.SetMeasurePolicy;
        Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function22);
        Function2 function23 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function23);
        Function2 function24 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function24);
        }
        Function2 function25 = ComposeUiNode.Companion.SetModifier;
        Updater.m259setimpl(composerImpl, materializeModifier, function25);
        List listOf = CollectionsKt__CollectionsKt.listOf(function2, rememberComposableLambda);
        EditTileKt$UnselectedTile$1$1 editTileKt$UnselectedTile$1$1 = EditTileKt$UnselectedTile$1$1.INSTANCE;
        ComposableLambdaImpl combineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listOf);
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new MultiContentMeasurePolicyImpl(editTileKt$UnselectedTile$1$1);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        MeasurePolicy measurePolicy = (MeasurePolicy) rememberedValue;
        int i5 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, companion);
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function02);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, measurePolicy, function22);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function23);
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function24);
        }
        Updater.m259setimpl(composerImpl, materializeModifier2, function25);
        combineAsVirtualLayouts.invoke((Object) composerImpl, (Object) 0);
        composerImpl.end(true);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$UnselectedTile$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    EditTileKt.UnselectedTile(Function0.this, mutableSelectionState, modifier2, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0070, code lost:
    
        if (r5 == androidx.compose.runtime.Composer.Companion.Empty) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void access$AvailableTileGrid(final java.util.List r34, final com.android.systemui.qs.panels.ui.compose.selection.MutableSelectionState r35, final int r36, final com.android.systemui.qs.panels.ui.compose.DragAndDropState r37, androidx.compose.runtime.Composer r38, final int r39) {
        /*
            Method dump skipped, instructions count: 838
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt.access$AvailableTileGrid(java.util.List, com.android.systemui.qs.panels.ui.compose.selection.MutableSelectionState, int, com.android.systemui.qs.panels.ui.compose.DragAndDropState, androidx.compose.runtime.Composer, int):void");
    }

    public static final void access$CurrentTilesGrid(final EditTileListState editTileListState, final MutableSelectionState mutableSelectionState, final int i, final Function2 function2, final Function1 function1, Composer composer, final int i2) {
        long Color;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1542377036);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(editTileListState, composerImpl);
        float f = CommonTileDefaults.TileHeight;
        GridCell gridCell = (GridCell) CollectionsKt.lastOrNull(editTileListState._tiles.getReadable$runtime_release().list);
        int row = gridCell != null ? gridCell.getRow() : 0;
        float f2 = CommonTileDefaults.TileArrangementPadding;
        float f3 = EditModeTileDefaults.CurrentTilesGridPadding;
        final State m8animateDpAsStateAjpBEmI = AnimateAsStateKt.m8animateDpAsStateAjpBEmI((((f + f2) * (row + 1)) - f2) + (2 * f3), null, "QSEditCurrentTilesGridHeight", composerImpl, 384, 10);
        LazyGridState rememberLazyGridState = LazyGridStateKt.rememberLazyGridState(0, 0, composerImpl, 3);
        composerImpl.startReplaceGroup(-1793142687);
        Object rememberedValue = composerImpl.rememberedValue();
        Object obj = Composer.Companion.Empty;
        if (rememberedValue == obj) {
            rememberedValue = SnapshotStateKt.mutableStateOf$default(new Offset((Float.floatToRawIntBits(0.0f) << 32) | (Float.floatToRawIntBits(0.0f) & 4294967295L)));
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final MutableState mutableState = (MutableState) rememberedValue;
        composerImpl.end(false);
        GridCells.Fixed fixed = new GridCells.Fixed(i);
        PaddingValuesImpl paddingValuesImpl = new PaddingValuesImpl(f3, f3, f3, f3);
        Modifier fillMaxWidth = SizeKt.fillMaxWidth(Modifier.Companion.$$INSTANCE, 1.0f);
        composerImpl.startReplaceGroup(-1793142409);
        boolean changed = composerImpl.changed(m8animateDpAsStateAjpBEmI);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed || rememberedValue2 == obj) {
            rememberedValue2 = new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$CurrentTilesGrid$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Integer.valueOf(((Density) obj2).mo45roundToPx0680j_4(((Dp) State.this.getValue()).value));
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        composerImpl.end(false);
        Modifier height = com.android.compose.modifiers.SizeKt.height(fillMaxWidth, (Function1) rememberedValue2);
        Color = ColorKt.Color(Color.m368getRedimpl(r10), Color.m367getGreenimpl(r10), Color.m365getBlueimpl(r10), 0.5f, Color.m366getColorSpaceimpl(MaterialTheme.getColorScheme(composerImpl).onBackground));
        Modifier then = height.then(new BorderModifierNodeElement(1, new SolidColor(Color), RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(48)));
        composerImpl.startReplaceGroup(-1793142115);
        Object rememberedValue3 = composerImpl.rememberedValue();
        if (rememberedValue3 == obj) {
            rememberedValue3 = new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$CurrentTilesGrid$2$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return new Offset(((Offset) MutableState.this.getValue()).packedValue);
                }
            };
            composerImpl.updateRememberedValue(rememberedValue3);
        }
        composerImpl.end(false);
        Modifier dragAndDropTileList = DragAndDropStateKt.dragAndDropTileList(then, rememberLazyGridState, (Function0) rememberedValue3, editTileListState, new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$CurrentTilesGrid$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                TileSpec tileSpec = (TileSpec) obj2;
                Function1 function12 = Function1.this;
                EditTileListState editTileListState2 = (EditTileListState) rememberUpdatedState.getValue();
                editTileListState2.getClass();
                ArrayList arrayList = new ArrayList();
                ListIterator listIterator = editTileListState2._tiles.listIterator();
                while (listIterator.hasNext()) {
                    Object next = listIterator.next();
                    if (next instanceof TileGridCell) {
                        arrayList.add(next);
                    }
                }
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((TileGridCell) it.next()).tile.tileSpec);
                }
                function12.invoke(arrayList2);
                ((SnapshotMutableStateImpl) mutableSelectionState._selection).setValue(new Selection(tileSpec, false));
                return Unit.INSTANCE;
            }
        }, composerImpl, ((i2 << 9) & 7168) | 384);
        composerImpl.startReplaceGroup(-1793141890);
        Object rememberedValue4 = composerImpl.rememberedValue();
        if (rememberedValue4 == obj) {
            rememberedValue4 = new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$CurrentTilesGrid$4$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    MutableState.this.setValue(new Offset(((LayoutCoordinates) obj2).mo484localToRootMKHz9U(0L)));
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue4);
        }
        composerImpl.end(false);
        TileKt.TileLazyGrid(fixed, TestTagKt.testTag(OnGloballyPositionedModifierKt.onGloballyPositioned(dragAndDropTileList, (Function1) rememberedValue4), "CurrentTilesGrid"), rememberLazyGridState, paddingValuesImpl, new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$CurrentTilesGrid$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                final PersistentList persistentList = EditTileListState.this._tiles.getReadable$runtime_release().list;
                final EditTileListState editTileListState2 = EditTileListState.this;
                final MutableSelectionState mutableSelectionState2 = mutableSelectionState;
                final State state = rememberUpdatedState;
                final Function2 function22 = function2;
                final Function1 function12 = new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$CurrentTilesGrid$5.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        Boolean bool;
                        TileSpec tileSpec = (TileSpec) obj3;
                        EditTileListState editTileListState3 = (EditTileListState) State.this.getValue();
                        int indexOf = editTileListState3.indexOf(tileSpec);
                        if (indexOf != -1) {
                            bool = Boolean.valueOf(((TileGridCell) ((GridCell) editTileListState3._tiles.get(indexOf))).getWidth() == 1);
                        } else {
                            bool = null;
                        }
                        if (bool != null) {
                            function22.invoke(tileSpec, Boolean.valueOf(!bool.booleanValue()));
                        }
                        return Unit.INSTANCE;
                    }
                };
                ((LazyGridIntervalContent) ((LazyGridScope) obj2)).items(persistentList.size(), new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$EditTiles$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        int intValue = ((Number) obj3).intValue();
                        GridCell gridCell2 = (GridCell) persistentList.get(intValue);
                        DragAndDropState dragAndDropState = editTileListState2;
                        if (!(gridCell2 instanceof TileGridCell)) {
                            if (gridCell2 instanceof SpacerGridCell) {
                                return Integer.valueOf(intValue);
                            }
                            throw new NoWhenBranchMatchedException();
                        }
                        TileGridCell tileGridCell = (TileGridCell) gridCell2;
                        TileSpec tileSpec = tileGridCell.tile.tileSpec;
                        SizedTile sizedTile = (SizedTile) ((SnapshotMutableStateImpl) ((EditTileListState) dragAndDropState)._draggedCell).getValue();
                        return sizedTile != null ? ((EditTileViewModel) sizedTile.getTile()).tileSpec.equals(tileSpec) : false ? Integer.valueOf(intValue) : tileGridCell.key;
                    }
                }, new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$EditTiles$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        return new GridItemSpan(((GridCell) persistentList.get(((Number) obj4).intValue())).mo848getSpanhRN5aJ8());
                    }
                }, new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$EditTiles$3
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj3) {
                        ((Number) obj3).intValue();
                        return TileType.INSTANCE;
                    }
                }, new ComposableLambdaImpl(-307161489, true, new Function4() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$EditTiles$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(4);
                    }

                    @Override // kotlin.jvm.functions.Function4
                    public final Object invoke(Object obj3, Object obj4, Object obj5, Object obj6) {
                        int i3;
                        LazyGridItemScope lazyGridItemScope = (LazyGridItemScope) obj3;
                        int intValue = ((Number) obj4).intValue();
                        Composer composer2 = (Composer) obj5;
                        int intValue2 = ((Number) obj6).intValue();
                        if ((intValue2 & 14) == 0) {
                            i3 = (((ComposerImpl) composer2).changed(lazyGridItemScope) ? 4 : 2) | intValue2;
                        } else {
                            i3 = intValue2;
                        }
                        if ((intValue2 & 112) == 0) {
                            i3 |= ((ComposerImpl) composer2).changed(intValue) ? 32 : 16;
                        }
                        if ((i3 & 731) == 146) {
                            ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                            if (composerImpl2.getSkipping()) {
                                composerImpl2.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey2 = ComposerKt.invocation;
                        GridCell gridCell2 = (GridCell) persistentList.get(intValue);
                        if (gridCell2 instanceof TileGridCell) {
                            ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                            composerImpl3.startReplaceGroup(1362697573);
                            DragAndDropState dragAndDropState = editTileListState2;
                            TileGridCell tileGridCell = (TileGridCell) gridCell2;
                            TileSpec tileSpec = tileGridCell.tile.tileSpec;
                            SizedTile sizedTile = (SizedTile) ((SnapshotMutableStateImpl) ((EditTileListState) dragAndDropState)._draggedCell).getValue();
                            if (sizedTile != null ? ((EditTileViewModel) sizedTile.getTile()).tileSpec.equals(tileSpec) : false) {
                                composerImpl3.startReplaceGroup(1362697730);
                                EditTileKt.access$SpacerGridCell(LazyGridItemScope.animateItem$default(lazyGridItemScope, FadingBackgroundKt.m741backgroundRPmYEkk(Modifier.Companion.$$INSTANCE, MaterialTheme.getColorScheme(composerImpl3).secondary, new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$EditTiles$4.1
                                    @Override // kotlin.jvm.functions.Function0
                                    public final /* bridge */ /* synthetic */ Object invoke() {
                                        return Float.valueOf(0.3f);
                                    }
                                }, RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(CommonTileDefaults.InactiveCornerRadius)), null, 7), composerImpl3, 0, 0);
                                composerImpl3.end(false);
                            } else {
                                composerImpl3.startReplaceGroup(1362698174);
                                EditTileKt.access$TileGridCell(lazyGridItemScope, tileGridCell, intValue, editTileListState2, mutableSelectionState2, function12, composerImpl3, 32768 | (i3 & 14) | ((i3 << 3) & 896));
                                composerImpl3.end(false);
                            }
                            composerImpl3.end(false);
                        } else if (gridCell2 instanceof SpacerGridCell) {
                            ComposerImpl composerImpl4 = (ComposerImpl) composer2;
                            composerImpl4.startReplaceGroup(1362698508);
                            EditTileKt.access$SpacerGridCell(null, composerImpl4, 0, 1);
                            composerImpl4.end(false);
                        } else {
                            ComposerImpl composerImpl5 = (ComposerImpl) composer2;
                            composerImpl5.startReplaceGroup(1362698534);
                            composerImpl5.end(false);
                        }
                        return Unit.INSTANCE;
                    }
                }));
                return Unit.INSTANCE;
            }
        }, composerImpl, 3072, 0);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$CurrentTilesGrid$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    EditTileKt.access$CurrentTilesGrid(EditTileListState.this, mutableSelectionState, i, function2, function1, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$EditGridHeader$1, kotlin.jvm.internal.Lambda] */
    public static final void access$EditGridHeader(final Modifier modifier, final Function3 function3, Composer composer, final int i, final int i2) {
        int i3;
        long Color;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1310330629);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 14) == 0) {
            i3 = (composerImpl.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((2 & i2) != 0) {
            i3 |= 48;
        } else if ((i & 112) == 0) {
            i3 |= composerImpl.changedInstance(function3) ? 32 : 16;
        }
        if ((i3 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier = Modifier.Companion.$$INSTANCE;
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = ContentColorKt.LocalContentColor;
            Color = ColorKt.Color(Color.m368getRedimpl(r1), Color.m367getGreenimpl(r1), Color.m365getBlueimpl(r1), 0.5f, Color.m366getColorSpaceimpl(MaterialTheme.getColorScheme(composerImpl).onBackground));
            CompositionLocalKt.CompositionLocalProvider(AppBarKt$$ExternalSyntheticOutline0.m(Color, dynamicProvidableCompositionLocal), ComposableLambdaKt.rememberComposableLambda(1336357947, new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$EditGridHeader$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Composer composer2 = (Composer) obj;
                    if ((((Number) obj2).intValue() & 11) == 2) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                        if (composerImpl2.getSkipping()) {
                            composerImpl2.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    OpaqueKey opaqueKey2 = ComposerKt.invocation;
                    BiasAlignment biasAlignment = Alignment.Companion.Center;
                    Modifier m108height3ABfNKs = SizeKt.m108height3ABfNKs(SizeKt.fillMaxWidth(Modifier.this, 1.0f), EditModeTileDefaults.EditGridHeaderHeight);
                    Function3 function32 = function3;
                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2);
                    ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                    PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl3.currentCompositionLocalScope();
                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer2, m108height3ABfNKs);
                    ComposeUiNode.Companion.getClass();
                    Function0 function0 = ComposeUiNode.Companion.Constructor;
                    composerImpl3.startReusableNode();
                    if (composerImpl3.inserting) {
                        composerImpl3.createNode(function0);
                    } else {
                        composerImpl3.useNode();
                    }
                    Updater.m259setimpl(composer2, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                    Updater.m259setimpl(composer2, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                    Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
                    if (composerImpl3.inserting || !Intrinsics.areEqual(composerImpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        AnimatedContentKt$$ExternalSyntheticOutline0.m(currentCompositeKeyHash, composerImpl3, currentCompositeKeyHash, function2);
                    }
                    Updater.m259setimpl(composer2, materializeModifier, ComposeUiNode.Companion.SetModifier);
                    function32.invoke(BoxScopeInstance.INSTANCE, composer2, 6);
                    composerImpl3.end(true);
                    return Unit.INSTANCE;
                }
            }, composerImpl), composerImpl, 56);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$EditGridHeader$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    EditTileKt.access$EditGridHeader(Modifier.this, function3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$RemoveTileTarget(final int i, Composer composer) {
        ComposerImpl composerImpl;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(-46527643);
        if (i == 0 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
            Arrangement$Start$1 arrangement$Start$1 = Arrangement.Start;
            Arrangement.SpacedAligned m79spacedByD5KLDUw = Arrangement.m79spacedByD5KLDUw(CommonTileDefaults.TileArrangementPadding, Alignment.Companion.Start);
            Modifier m98padding3ABfNKs = PaddingKt.m98padding3ABfNKs(SizeKt.FillWholeMaxHeight.then(new BorderModifierNodeElement(1, new SolidColor(((Color) composerImpl2.consume(ContentColorKt.LocalContentColor)).value), RoundedCornerShapeKt.CircleShape)), 10);
            RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m79spacedByD5KLDUw, vertical, composerImpl2, 48);
            int i2 = composerImpl2.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl2.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl2, m98padding3ABfNKs);
            ComposeUiNode.Companion.getClass();
            Function0 function0 = ComposeUiNode.Companion.Constructor;
            composerImpl2.startReusableNode();
            if (composerImpl2.inserting) {
                composerImpl2.createNode(function0);
            } else {
                composerImpl2.useNode();
            }
            Updater.m259setimpl(composerImpl2, rowMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composerImpl2, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl2.inserting || !Intrinsics.areEqual(composerImpl2.rememberedValue(), Integer.valueOf(i2))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i2, composerImpl2, i2, function2);
            }
            Updater.m259setimpl(composerImpl2, materializeModifier, ComposeUiNode.Companion.SetModifier);
            ImageVector imageVector = ClearKt._clear;
            if (imageVector == null) {
                ImageVector.Builder builder = new ImageVector.Builder("Filled.Clear", 24.0f, 24.0f, 24.0f, 24.0f, 0L, 0, false, 96);
                EmptyList emptyList = VectorKt.EmptyPath;
                SolidColor solidColor = new SolidColor(Color.Black);
                PathBuilder pathBuilder = new PathBuilder();
                pathBuilder.moveTo(19.0f, 6.41f);
                pathBuilder.lineTo(17.59f, 5.0f);
                pathBuilder.lineTo(12.0f, 10.59f);
                pathBuilder.lineTo(6.41f, 5.0f);
                pathBuilder.lineTo(5.0f, 6.41f);
                pathBuilder.lineTo(10.59f, 12.0f);
                pathBuilder.lineTo(5.0f, 17.59f);
                pathBuilder.lineTo(6.41f, 19.0f);
                pathBuilder.lineTo(12.0f, 13.41f);
                pathBuilder.lineTo(17.59f, 19.0f);
                pathBuilder.lineTo(19.0f, 17.59f);
                pathBuilder.lineTo(13.41f, 12.0f);
                pathBuilder.close();
                builder.m440addPathoIyEayM(1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0, 0, 2, solidColor, null, "", pathBuilder._nodes);
                imageVector = builder.build();
                ClearKt._clear = imageVector;
            }
            IconKt.m214Iconww6aTOc(imageVector, (String) null, (Modifier) null, 0L, composerImpl2, 48, 12);
            TextKt.m241Text4IGK_g("Remove", null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerImpl2, 6, 0, 131070);
            composerImpl = composerImpl2;
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$RemoveTileTarget$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    EditTileKt.access$RemoveTileTarget(RecomposeScopeImplKt.updateChangedFlags(i | 1), (Composer) obj);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final void access$SpacerGridCell(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(607813919);
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
            BoxKt.Box(SizeKt.fillMaxWidth(SizeKt.m108height3ABfNKs(modifier, CommonTileDefaults.TileHeight), 1.0f), composerImpl, 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$SpacerGridCell$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    EditTileKt.access$SpacerGridCell(Modifier.this, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* JADX WARN: Type inference failed for: r4v16, types: [com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$TileGridCell$content$1, kotlin.jvm.internal.Lambda] */
    public static final void access$TileGridCell(final LazyGridItemScope lazyGridItemScope, final TileGridCell tileGridCell, final int i, final DragAndDropState dragAndDropState, final MutableSelectionState mutableSelectionState, final Function1 function1, Composer composer, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1473690469);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Object[] objArr = {Integer.valueOf(i + 1)};
        composerImpl.consume(AndroidCompositionLocals_androidKt.LocalConfiguration);
        final String string = ((Context) composerImpl.consume(AndroidCompositionLocals_androidKt.LocalContext)).getResources().getString(R.string.accessibility_qs_edit_position, Arrays.copyOf(objArr, 1));
        composerImpl.startReplaceGroup(-88418322);
        Object rememberedValue = composerImpl.rememberedValue();
        Object obj = Composer.Companion.Empty;
        if (rememberedValue == obj) {
            rememberedValue = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        MutableState mutableState = (MutableState) rememberedValue;
        composerImpl.end(false);
        final State animateFloatAsState = AnimateAsStateKt.animateFloatAsState(((Boolean) mutableState.getValue()).booleanValue() ? 1.0f : 0.0f, null, "QSEditTileSelectionAlpha", null, composerImpl, 3072, 22);
        Selection selection = (Selection) ((SnapshotMutableStateImpl) mutableSelectionState.selection$delegate).getValue();
        EffectsKt.LaunchedEffect(composerImpl, selection != null ? selection.tileSpec : null, new EditTileKt$TileGridCell$1(mutableSelectionState, tileGridCell, mutableState, null));
        Modifier animateItem$default = LazyGridItemScope.animateItem$default(lazyGridItemScope, Modifier.Companion.$$INSTANCE, null, 7);
        composerImpl.startReplaceGroup(-88417497);
        boolean changed = composerImpl.changed(string) | ((((i2 & 112) ^ 48) > 32 && composerImpl.changed(tileGridCell)) || (i2 & 48) == 32) | ((((i2 & 458752) ^ 196608) > 131072 && composerImpl.changed(function1)) || (i2 & 196608) == 131072);
        Object rememberedValue2 = composerImpl.rememberedValue();
        if (changed || rememberedValue2 == obj) {
            rememberedValue2 = new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$TileGridCell$modifier$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj2;
                    SemanticsPropertiesKt.setStateDescription(semanticsPropertyReceiver, string);
                    SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, tileGridCell.tile.label.text);
                    final Function1 function12 = function1;
                    final TileGridCell tileGridCell2 = tileGridCell;
                    SemanticsPropertiesKt.setCustomActions(semanticsPropertyReceiver, Collections.singletonList(new CustomAccessibilityAction("Toggle size", new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$TileGridCell$modifier$1$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            Function1.this.invoke(tileGridCell2.tile.tileSpec);
                            return Boolean.TRUE;
                        }
                    })));
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue2);
        }
        composerImpl.end(false);
        Modifier fillMaxWidth = SizeKt.fillMaxWidth(SizeKt.m108height3ABfNKs(SemanticsModifierKt.semantics(animateItem$default, true, (Function1) rememberedValue2), CommonTileDefaults.TileHeight), 1.0f);
        ComposableLambdaImpl rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1969577828, new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$TileGridCell$content$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$TileGridCell$content$1$1, reason: invalid class name */
            final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0 {
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    MutableSelectionState mutableSelectionState = (MutableSelectionState) this.receiver;
                    ((SnapshotMutableStateImpl) mutableSelectionState._selection).setValue(null);
                    mutableSelectionState.onResizingDragEnd();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj2, Object obj3) {
                Composer composer2 = (Composer) obj2;
                int intValue = ((Number) obj3).intValue() & 11;
                Unit unit = Unit.INSTANCE;
                if (intValue == 2) {
                    ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                    if (composerImpl2.getSkipping()) {
                        composerImpl2.skipToGroupEnd();
                        return unit;
                    }
                }
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                TileGridCell tileGridCell2 = TileGridCell.this;
                EditTileViewModel editTileViewModel = tileGridCell2.tile;
                boolean z = tileGridCell2.getWidth() == 1;
                FillElement fillElement = SizeKt.FillWholeMaxSize;
                final TileSpec tileSpec = TileGridCell.this.tile.tileSpec;
                final MutableSelectionState mutableSelectionState2 = mutableSelectionState;
                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                composerImpl3.startReplaceGroup(859640867);
                Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(fillElement, unit, new PointerInputEventHandler() { // from class: com.android.systemui.qs.panels.ui.compose.selection.MutableSelectionStateKt$selectableTile$1
                    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                    public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
                        final TileSpec tileSpec2 = tileSpec;
                        final MutableSelectionState mutableSelectionState3 = MutableSelectionState.this;
                        Object detectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.selection.MutableSelectionStateKt$selectableTile$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj4) {
                                long j = ((Offset) obj4).packedValue;
                                Selection selection2 = (Selection) ((SnapshotMutableStateImpl) MutableSelectionState.this.selection$delegate).getValue();
                                if (Intrinsics.areEqual(selection2 != null ? selection2.tileSpec : null, tileSpec2)) {
                                    MutableSelectionState mutableSelectionState4 = MutableSelectionState.this;
                                    ((SnapshotMutableStateImpl) mutableSelectionState4._selection).setValue(null);
                                    mutableSelectionState4.onResizingDragEnd();
                                } else {
                                    ((SnapshotMutableStateImpl) MutableSelectionState.this._selection).setValue(new Selection(tileSpec2, true));
                                }
                                return Unit.INSTANCE;
                            }
                        }, continuation, 7);
                        return detectTapGestures$default == CoroutineSingletons.COROUTINE_SUSPENDED ? detectTapGestures$default : Unit.INSTANCE;
                    }
                });
                composerImpl3.end(false);
                TileGridCell tileGridCell3 = TileGridCell.this;
                Modifier dragAndDropTileSource = DragAndDropStateKt.dragAndDropTileSource(pointerInput, new SizedTileImpl(tileGridCell3.width, tileGridCell3.tile), dragAndDropState, new AnonymousClass1(0, mutableSelectionState, MutableSelectionState.class, "unSelect", "unSelect()V", 0), composer2, 0);
                composerImpl3.startReplaceGroup(510998824);
                boolean changed2 = composerImpl3.changed(animateFloatAsState);
                final State state = animateFloatAsState;
                Object rememberedValue3 = composerImpl3.rememberedValue();
                if (changed2 || rememberedValue3 == Composer.Companion.Empty) {
                    rememberedValue3 = new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$TileGridCell$content$1$2$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Float.valueOf(((Number) State.this.getValue()).floatValue());
                        }
                    };
                    composerImpl3.updateRememberedValue(rememberedValue3);
                }
                composerImpl3.end(false);
                EditTileKt.EditTile(editTileViewModel, z, dragAndDropTileSource, null, (Function0) rememberedValue3, composerImpl3, 0, 8);
                return unit;
            }
        }, composerImpl);
        if (((Boolean) mutableState.getValue()).booleanValue()) {
            composerImpl.startReplaceGroup(-88416301);
            boolean z = tileGridCell.getWidth() == 1;
            composerImpl.startReplaceGroup(-88416224);
            boolean changed2 = composerImpl.changed(animateFloatAsState);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (changed2 || rememberedValue3 == obj) {
                rememberedValue3 = new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$TileGridCell$2$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(((Number) State.this.getValue()).floatValue());
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            composerImpl.end(false);
            SelectedTile(z, (Function0) rememberedValue3, mutableSelectionState, ZIndexModifierKt.zIndex(fillMaxWidth, 2.0f), rememberComposableLambda, composerImpl, 25088, 0);
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceGroup(-88415998);
            composerImpl.startReplaceGroup(-88415953);
            boolean changed3 = composerImpl.changed(animateFloatAsState);
            Object rememberedValue4 = composerImpl.rememberedValue();
            if (changed3 || rememberedValue4 == obj) {
                rememberedValue4 = new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$TileGridCell$3$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(((Number) State.this.getValue()).floatValue());
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue4);
            }
            composerImpl.end(false);
            UnselectedTile((Function0) rememberedValue4, mutableSelectionState, fillMaxWidth, rememberComposableLambda, composerImpl, 3136, 0);
            composerImpl.end(false);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$TileGridCell$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    EditTileKt.access$TileGridCell(LazyGridItemScope.this, tileGridCell, i, dragAndDropState, mutableSelectionState, function1, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
