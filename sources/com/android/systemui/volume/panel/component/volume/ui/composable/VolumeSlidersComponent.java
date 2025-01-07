package com.android.systemui.volume.panel.component.volume.ui.composable;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.lifecycle.compose.FlowExtKt;
import com.android.compose.PlatformSliderDefaults;
import com.android.systemui.volume.panel.component.volume.ui.viewmodel.AudioVolumeComponentViewModel;
import com.android.systemui.volume.panel.component.volume.ui.viewmodel.SlidersExpandableViewModel;
import com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent;
import com.android.systemui.volume.panel.ui.composable.VolumePanelComposeScope;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VolumeSlidersComponent implements ComposeVolumePanelUiComponent {
    public final AudioVolumeComponentViewModel viewModel;

    public VolumeSlidersComponent(AudioVolumeComponentViewModel audioVolumeComponentViewModel) {
        this.viewModel = audioVolumeComponentViewModel;
    }

    @Override // com.android.systemui.volume.panel.ui.composable.ComposeVolumePanelUiComponent
    public final void Content(final VolumePanelComposeScope volumePanelComposeScope, final Modifier modifier, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1711119471);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        AudioVolumeComponentViewModel audioVolumeComponentViewModel = this.viewModel;
        MutableState collectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(audioVolumeComponentViewModel.sliderViewModels, composerImpl);
        if (((List) collectAsStateWithLifecycle.getValue()).isEmpty()) {
            RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
            if (endRestartGroup != null) {
                endRestartGroup.block = new Function2() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.VolumeSlidersComponent$Content$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        ((Number) obj2).intValue();
                        VolumeSlidersComponent.this.Content(volumePanelComposeScope, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                        return Unit.INSTANCE;
                    }
                };
                return;
            }
            return;
        }
        if (volumePanelComposeScope.state.isLargeScreen) {
            composerImpl.startReplaceGroup(-884934826);
            GridVolumeSlidersKt.GridVolumeSliders((List) collectAsStateWithLifecycle.getValue(), PlatformSliderDefaults.defaultPlatformSliderColors(composerImpl), SizeKt.fillMaxWidth(modifier, 1.0f), composerImpl, 8, 0);
            composerImpl.end(false);
        } else {
            composerImpl.startReplaceGroup(-884934580);
            MutableState collectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(volumePanelComposeScope.state.orientation == 1 ? audioVolumeComponentViewModel.portraitExpandable : new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(SlidersExpandableViewModel.Fixed.INSTANCE), SlidersExpandableViewModel.Unavailable.INSTANCE, composerImpl, 56);
            if (((SlidersExpandableViewModel) collectAsStateWithLifecycle2.getValue()) instanceof SlidersExpandableViewModel.Unavailable) {
                composerImpl.end(false);
                RecomposeScopeImpl endRestartGroup2 = composerImpl.endRestartGroup();
                if (endRestartGroup2 != null) {
                    endRestartGroup2.block = new Function2() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.VolumeSlidersComponent$Content$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            ((Number) obj2).intValue();
                            VolumeSlidersComponent.this.Content(volumePanelComposeScope, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                            return Unit.INSTANCE;
                        }
                    };
                    return;
                }
                return;
            }
            SlidersExpandableViewModel slidersExpandableViewModel = (SlidersExpandableViewModel) collectAsStateWithLifecycle2.getValue();
            SlidersExpandableViewModel.Expandable expandable = slidersExpandableViewModel instanceof SlidersExpandableViewModel.Expandable ? (SlidersExpandableViewModel.Expandable) slidersExpandableViewModel : null;
            ColumnVolumeSlidersKt.ColumnVolumeSliders((List) collectAsStateWithLifecycle.getValue(), expandable != null ? expandable.isExpanded : true, new VolumeSlidersComponent$Content$3(1, this.viewModel, AudioVolumeComponentViewModel.class, "onExpandedChanged", "onExpandedChanged(Z)V", 0), PlatformSliderDefaults.defaultPlatformSliderColors(composerImpl), ((SlidersExpandableViewModel) collectAsStateWithLifecycle2.getValue()) instanceof SlidersExpandableViewModel.Expandable, SizeKt.fillMaxWidth(modifier, 1.0f), composerImpl, 8, 0);
            composerImpl.end(false);
        }
        RecomposeScopeImpl endRestartGroup3 = composerImpl.endRestartGroup();
        if (endRestartGroup3 != null) {
            endRestartGroup3.block = new Function2() { // from class: com.android.systemui.volume.panel.component.volume.ui.composable.VolumeSlidersComponent$Content$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    VolumeSlidersComponent.this.Content(volumePanelComposeScope, modifier, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
