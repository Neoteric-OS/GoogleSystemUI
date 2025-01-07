package com.android.systemui.volume.panel.component.bottombar.ui;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowMeasurePolicy;
import androidx.compose.foundation.layout.SizeKt;
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
import androidx.compose.ui.node.ComposeUiNode;
import com.android.compose.PlatformButtonsKt;
import com.android.systemui.volume.panel.component.bottombar.ui.viewmodel.BottomBarViewModel;
import com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent;
import com.android.systemui.volume.panel.ui.composable.VolumePanelComposeScope;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BottomBarComponent implements ComposeVolumePanelUiComponent {
    public final BottomBarViewModel viewModel;

    public BottomBarComponent(BottomBarViewModel bottomBarViewModel) {
        this.viewModel = bottomBarViewModel;
    }

    @Override // com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent
    public final void Content(final VolumePanelComposeScope volumePanelComposeScope, final Modifier modifier, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(695359912);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Modifier fillMaxWidth = SizeKt.fillMaxWidth(SizeKt.m110heightInVpY3zN4$default(modifier, volumePanelComposeScope.state.isLargeScreen ? 54 : 48, 0.0f, 2), 1.0f);
        RowMeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.SpaceBetween, Alignment.Companion.CenterVertically, composerImpl, 54);
        int i2 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, fillMaxWidth);
        ComposeUiNode.Companion.getClass();
        Function0 function0 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, rowMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i2))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i2, composerImpl, i2, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        PlatformButtonsKt.PlatformOutlinedButton(new BottomBarComponent$Content$1$1(0, this.viewModel, BottomBarViewModel.class, "onSettingsClicked", "onSettingsClicked()V", 0), null, false, null, null, ComposableSingletons$BottomBarComponentKt.f51lambda1, composerImpl, 196608, 30);
        PlatformButtonsKt.PlatformButton(new BottomBarComponent$Content$1$2(0, this.viewModel, BottomBarViewModel.class, "onDoneClicked", "onDoneClicked()V", 0), null, false, null, ComposableSingletons$BottomBarComponentKt.f52lambda2, composerImpl, 24576, 14);
        composerImpl.end(true);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.bottombar.ui.BottomBarComponent$Content$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    BottomBarComponent.this.Content(volumePanelComposeScope, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
