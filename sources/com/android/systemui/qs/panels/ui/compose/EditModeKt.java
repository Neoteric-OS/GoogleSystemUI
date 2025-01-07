package com.android.systemui.qs.panels.ui.compose;

import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnMeasurePolicy;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.systemui.qs.panels.ui.viewmodel.EditModeViewModel;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class EditModeKt {
    public static final void EditMode(final EditModeViewModel editModeViewModel, Modifier modifier, Composer composer, final int i, final int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1011548058);
        int i3 = i2 & 2;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i3 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(editModeViewModel.gridLayout, composerImpl);
        MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(editModeViewModel.tiles, EmptyList.INSTANCE, composerImpl, 56);
        BackHandlerKt.BackHandler(false, new Function0() { // from class: com.android.systemui.qs.panels.ui.compose.EditModeKt$EditMode$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                EditModeViewModel editModeViewModel2 = EditModeViewModel.this;
                Boolean bool = Boolean.FALSE;
                StateFlowImpl stateFlowImpl = editModeViewModel2._isEditing;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool);
                return Unit.INSTANCE;
            }
        }, composerImpl, 0, 1);
        EffectsKt.DisposableEffect(Unit.INSTANCE, new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.EditModeKt$EditMode$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                final EditModeViewModel editModeViewModel2 = EditModeViewModel.this;
                return new DisposableEffectResult() { // from class: com.android.systemui.qs.panels.ui.compose.EditModeKt$EditMode$2$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public final void dispose() {
                        Boolean bool = Boolean.FALSE;
                        StateFlowImpl stateFlowImpl = EditModeViewModel.this._isEditing;
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, bool);
                    }
                };
            }
        }, composerImpl);
        ColumnMeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.Top, Alignment.Companion.Start, composerImpl, 0);
        int i4 = composerImpl.compoundKeyHash;
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
        Updater.m259setimpl(composerImpl, columnMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        final Modifier modifier3 = modifier2;
        ((GridLayout) collectAsStateWithLifecycle.getValue()).EditTileGrid((List) collectAsStateWithLifecycle2.getValue(), companion, new EditModeKt$EditMode$3$1(2, editModeViewModel, EditModeViewModel.class, "addTile", "addTile(Lcom/android/systemui/qs/pipeline/shared/TileSpec;I)V", 0), new EditModeKt$EditMode$3$2(1, editModeViewModel, EditModeViewModel.class, "removeTile", "removeTile(Lcom/android/systemui/qs/pipeline/shared/TileSpec;)V", 0), new EditModeKt$EditMode$3$3(1, editModeViewModel, EditModeViewModel.class, "setTiles", "setTiles(Ljava/util/List;)V", 0), composerImpl, 56);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.qs.panels.ui.compose.EditModeKt$EditMode$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    EditModeKt.EditMode(EditModeViewModel.this, modifier3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
