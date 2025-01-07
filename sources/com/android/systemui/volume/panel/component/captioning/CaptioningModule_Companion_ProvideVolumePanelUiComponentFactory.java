package com.android.systemui.volume.panel.component.captioning;

import com.android.systemui.volume.panel.component.button.ui.composable.ToggleButtonComponent;
import com.android.systemui.volume.panel.component.captioning.ui.viewmodel.CaptioningViewModel;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CaptioningModule_Companion_ProvideVolumePanelUiComponentFactory implements Provider {
    public static ToggleButtonComponent provideVolumePanelUiComponent(CaptioningViewModel captioningViewModel) {
        return new ToggleButtonComponent(captioningViewModel.buttonViewModel, new CaptioningModule$Companion$provideVolumePanelUiComponent$1(1, captioningViewModel, CaptioningViewModel.class, "setIsSystemAudioCaptioningEnabled", "setIsSystemAudioCaptioningEnabled(Z)V", 0));
    }
}
