package com.android.systemui.volume.panel.component.spatialaudio.ui.composable;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.systemui.volume.panel.component.button.ui.composable.ButtonComponent;
import com.android.systemui.volume.panel.component.button.ui.composable.ToggleButtonComponent;
import com.android.systemui.volume.panel.component.spatial.domain.model.SpatialAudioEnabledModel;
import com.android.systemui.volume.panel.component.spatial.ui.viewmodel.SpatialAudioViewModel;
import com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent;
import com.android.systemui.volume.panel.ui.composable.VolumePanelComposeScope;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SpatialAudioComponent implements ComposeVolumePanelUiComponent {
    public final SpatialAudioPopup popup;
    public final SpatialAudioViewModel viewModel;

    public SpatialAudioComponent(SpatialAudioViewModel spatialAudioViewModel, SpatialAudioPopup spatialAudioPopup) {
        this.viewModel = spatialAudioViewModel;
        this.popup = spatialAudioPopup;
    }

    @Override // com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent
    public final void Content(final VolumePanelComposeScope volumePanelComposeScope, final Modifier modifier, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-986021957);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        SpatialAudioViewModel spatialAudioViewModel = this.viewModel;
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(spatialAudioViewModel.shouldUsePopup, composerImpl);
        boolean booleanValue = ((Boolean) collectAsStateWithLifecycle.getValue()).booleanValue();
        composerImpl.startReplaceGroup(933975511);
        boolean changed = composerImpl.changed(booleanValue);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            boolean booleanValue2 = ((Boolean) collectAsStateWithLifecycle.getValue()).booleanValue();
            ReadonlyStateFlow readonlyStateFlow = spatialAudioViewModel.spatialAudioButton;
            rememberedValue = booleanValue2 ? new ButtonComponent(readonlyStateFlow, new SpatialAudioComponent$Content$buttonComponent$1$1(2, this.popup, SpatialAudioPopup.class, "show", "show(Lcom/android/systemui/animation/Expandable;I)V", 0)) : new ToggleButtonComponent(readonlyStateFlow, new Function1() { // from class: com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioComponent$Content$buttonComponent$1$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    if (((Boolean) obj).booleanValue()) {
                        SpatialAudioComponent.this.viewModel.setEnabled(SpatialAudioEnabledModel.SpatialAudioEnabled.Companion.$$INSTANCE);
                    } else {
                        SpatialAudioComponent.this.viewModel.setEnabled(SpatialAudioEnabledModel.Disabled.INSTANCE);
                    }
                    return Unit.INSTANCE;
                }
            });
            composerImpl.updateRememberedValue(rememberedValue);
        }
        composerImpl.end(false);
        ((ComposeVolumePanelUiComponent) rememberedValue).Content(volumePanelComposeScope, modifier, composerImpl, i & 126);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioComponent$Content$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SpatialAudioComponent.this.Content(volumePanelComposeScope, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
