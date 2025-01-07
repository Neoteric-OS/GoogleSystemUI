package com.android.systemui.keyboard;

import com.android.systemui.CoreStartable;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.keyboard.backlight.ui.KeyboardBacklightDialogCoordinator;
import com.android.systemui.keyboard.stickykeys.ui.StickyKeysIndicatorCoordinator;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PhysicalKeyboardCoreStartable implements CoreStartable {
    public final FeatureFlags featureFlags;
    public final Lazy keyboardBacklightDialogCoordinator;
    public final Lazy stickyKeysIndicatorCoordinator;

    public PhysicalKeyboardCoreStartable(Lazy lazy, Lazy lazy2, Lazy lazy3, FeatureFlags featureFlags) {
        this.keyboardBacklightDialogCoordinator = lazy;
        this.stickyKeysIndicatorCoordinator = lazy2;
        this.featureFlags = featureFlags;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        ((StickyKeysIndicatorCoordinator) this.stickyKeysIndicatorCoordinator.get()).startListening();
        if (((FeatureFlagsClassicRelease) this.featureFlags).isEnabled(Flags.KEYBOARD_BACKLIGHT_INDICATOR)) {
            ((KeyboardBacklightDialogCoordinator) this.keyboardBacklightDialogCoordinator.get()).startListening();
        }
    }
}
