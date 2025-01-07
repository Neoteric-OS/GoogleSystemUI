package com.android.systemui.keyguard.ui.composable.blueprint;

import com.android.systemui.keyguard.ui.composable.section.BottomAreaSection;
import com.android.systemui.keyguard.ui.composable.section.LockSection;
import com.android.systemui.keyguard.ui.composable.section.NotificationSection;
import com.android.systemui.keyguard.ui.composable.section.SettingsMenuSection;
import com.android.systemui.keyguard.ui.composable.section.StatusBarSection;
import com.android.systemui.keyguard.ui.composable.section.TopAreaSection;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultBlueprint implements ComposableLockscreenSceneBlueprint {
    public final Optional ambientIndicationSectionOptional;
    public final BottomAreaSection bottomAreaSection;
    public final LockSection lockSection;
    public final NotificationSection notificationSection;
    public final SettingsMenuSection settingsMenuSection;
    public final StatusBarSection statusBarSection;
    public final TopAreaSection topAreaSection;

    public DefaultBlueprint(StatusBarSection statusBarSection, LockSection lockSection, Optional optional, BottomAreaSection bottomAreaSection, SettingsMenuSection settingsMenuSection, TopAreaSection topAreaSection, NotificationSection notificationSection) {
    }

    @Override // com.android.systemui.keyguard.ui.composable.blueprint.ComposableLockscreenSceneBlueprint
    public final String getId() {
        return "default";
    }
}
