package com.android.systemui.communal.ui.compose;

import android.view.KeyEvent;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CommunalTouchableSurfaceKt {
    public static final void CommunalTouchableSurface(final CommunalViewModel communalViewModel, Modifier modifier, final Function3 function3, Composer composer, final int i, final int i2) {
        Modifier m33combinedClickableauXiCPI;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1850970277);
        Modifier modifier2 = (i2 & 2) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        composerImpl.startReplaceGroup(-253190745);
        Object rememberedValue = composerImpl.rememberedValue();
        if (rememberedValue == Composer.Companion.Empty) {
            rememberedValue = InteractionSourceKt.MutableInteractionSource();
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        m33combinedClickableauXiCPI = ClickableKt.m33combinedClickableauXiCPI(modifier2, (MutableInteractionSource) rememberedValue, null, true, null, null, null, (r17 & 64) != 0 ? null : new CommunalTouchableSurfaceKt$CommunalTouchableSurface$1(0, communalViewModel, CommunalViewModel.class, "onLongClick", "onLongClick()V", 0), null, true, new CommunalTouchableSurfaceKt$CommunalTouchableSurface$2(0, communalViewModel, CommunalViewModel.class, "onClick", "onClick()V", 0));
        Modifier motionEventSpy = PointerInteropFilter_androidKt.motionEventSpy(KeyInputModifierKt.onPreviewKeyEvent(m33combinedClickableauXiCPI, new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalTouchableSurfaceKt$CommunalTouchableSurface$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyEvent keyEvent = ((androidx.compose.ui.input.key.KeyEvent) obj).nativeKeyEvent;
                ((BaseCommunalViewModel) CommunalViewModel.this).communalInteractor._userActivity.tryEmit(Unit.INSTANCE);
                return Boolean.FALSE;
            }
        }), new Function1() { // from class: com.android.systemui.communal.ui.compose.CommunalTouchableSurfaceKt$CommunalTouchableSurface$4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SharedFlowImpl sharedFlowImpl = ((BaseCommunalViewModel) CommunalViewModel.this).communalInteractor._userActivity;
                Unit unit = Unit.INSTANCE;
                sharedFlowImpl.tryEmit(unit);
                return unit;
            }
        });
        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
        int i3 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, motionEventSpy);
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
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i3))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i3, composerImpl, i3, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        function3.invoke(BoxScopeInstance.INSTANCE, composerImpl, Integer.valueOf(((i >> 3) & 112) | 6));
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.communal.ui.compose.CommunalTouchableSurfaceKt$CommunalTouchableSurface$6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    CommunalTouchableSurfaceKt.CommunalTouchableSurface(CommunalViewModel.this, modifier3, function3, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
