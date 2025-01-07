package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.keyguard.domain.interactor.KeyguardTouchHandlingInteractor;
import com.android.wm.shell.R;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSettingsMenuViewModel {
    public final KeyguardTouchHandlingInteractor interactor;
    public final ReadonlyStateFlow isVisible;
    public final ReadonlyStateFlow shouldOpenSettings;
    public final Icon.Resource icon = new Icon.Resource(R.drawable.ic_palette, null);
    public final Text.Resource text = new Text.Resource(R.string.lock_screen_settings);

    public KeyguardSettingsMenuViewModel(KeyguardTouchHandlingInteractor keyguardTouchHandlingInteractor) {
        this.interactor = keyguardTouchHandlingInteractor;
        this.isVisible = keyguardTouchHandlingInteractor.isMenuVisible;
        this.shouldOpenSettings = keyguardTouchHandlingInteractor.shouldOpenSettings;
    }
}
