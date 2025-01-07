package com.google.android.systemui.volume.panel.component.clearcalling;

import com.android.systemui.volume.panel.component.button.ui.composable.ToggleButtonComponent;
import com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ClearCallingModule_Companion_ProvideClearCallingComponentFactory implements Provider {
    public static ToggleButtonComponent provideClearCallingComponent(ClearCallingViewModel clearCallingViewModel) {
        return new ToggleButtonComponent(clearCallingViewModel.buttonViewModel, new ClearCallingModule$Companion$provideClearCallingComponent$1(1, clearCallingViewModel, ClearCallingViewModel.class, "setIsClearCallingEnabled", "setIsClearCallingEnabled(Z)V", 0));
    }
}
