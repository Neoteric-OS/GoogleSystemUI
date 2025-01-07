package androidx.compose.ui.layout;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutNodeSubcompositionsState;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNode;
import java.util.Iterator;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SubcomposeLayoutKt {
    public static final SubcomposeLayoutKt$ReusedSlotId$1 ReusedSlotId = new SubcomposeLayoutKt$ReusedSlotId$1();

    public static final void SubcomposeLayout(final SubcomposeLayoutState subcomposeLayoutState, Modifier modifier, final Function2 function2, Composer composer, final int i, final int i2) {
        int i3;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-511989831);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerImpl.changedInstance(subcomposeLayoutState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = 2 & i2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerImpl.changed(modifier) ? 32 : 16;
        }
        if ((4 & i2) != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerImpl.changedInstance(function2) ? 256 : 128;
        }
        if ((i3 & 147) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier = Modifier.Companion.$$INSTANCE;
            }
            OpaqueKey opaqueKey = ComposerKt.invocation;
            int i5 = composerImpl.compoundKeyHash;
            CompositionContext rememberCompositionContext = ComposablesKt.rememberCompositionContext(composerImpl);
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, modifier);
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Function0 function0 = LayoutNode.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, subcomposeLayoutState, subcomposeLayoutState.setRoot);
            Updater.m259setimpl(composerImpl, rememberCompositionContext, subcomposeLayoutState.setCompositionContext);
            Updater.m259setimpl(composerImpl, function2, subcomposeLayoutState.setMeasurePolicy);
            ComposeUiNode.Companion.getClass();
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
            Function2 function22 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function22);
            }
            composerImpl.end(true);
            if (!composerImpl.getSkipping()) {
                boolean changedInstance = composerImpl.changedInstance(subcomposeLayoutState);
                Object rememberedValue = composerImpl.rememberedValue();
                if (changedInstance || rememberedValue == Composer.Companion.Empty) {
                    rememberedValue = new Function0() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt$SubcomposeLayout$4$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            LayoutNodeSubcompositionsState state = SubcomposeLayoutState.this.getState();
                            LayoutNode layoutNode = state.root;
                            if (state.reusableCount != layoutNode.getFoldedChildren$ui_release().size()) {
                                Iterator it = state.nodeToNodeState.entrySet().iterator();
                                while (it.hasNext()) {
                                    ((LayoutNodeSubcompositionsState.NodeState) ((Map.Entry) it.next()).getValue()).forceRecompose = true;
                                }
                                if (!layoutNode.layoutDelegate.measurePending) {
                                    LayoutNode.requestRemeasure$ui_release$default(layoutNode, false, 7);
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    };
                    composerImpl.updateRememberedValue(rememberedValue);
                }
                EffectsKt.SideEffect((Function0) rememberedValue, composerImpl);
            }
        }
        final Modifier modifier2 = modifier;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.ui.layout.SubcomposeLayoutKt$SubcomposeLayout$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SubcomposeLayoutKt.SubcomposeLayout(SubcomposeLayoutState.this, modifier2, function2, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
