package com.android.systemui.keyguard.ui.view.layout.blueprints;

import com.android.systemui.communal.ui.view.layout.sections.CommunalTutorialIndicatorSection;
import com.android.systemui.keyguard.shared.model.KeyguardBlueprint;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.view.layout.sections.AccessibilityActionsSection;
import com.android.systemui.keyguard.ui.view.layout.sections.AodBurnInSection;
import com.android.systemui.keyguard.ui.view.layout.sections.AodNotificationIconsSection;
import com.android.systemui.keyguard.ui.view.layout.sections.ClockSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultDeviceEntrySection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultIndicationAreaSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultNotificationStackScrollLayoutSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultSettingsPopupMenuSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultShortcutsSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultStatusBarSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultStatusViewSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultUdfpsAccessibilityOverlaySection;
import com.android.systemui.keyguard.ui.view.layout.sections.KeyguardSliceViewSection;
import com.android.systemui.keyguard.ui.view.layout.sections.SmartspaceSection;
import java.util.List;
import java.util.Optional;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultKeyguardBlueprint implements KeyguardBlueprint {
    public final List sections;

    /* JADX WARN: Multi-variable type inference failed */
    public DefaultKeyguardBlueprint(AccessibilityActionsSection accessibilityActionsSection, DefaultIndicationAreaSection defaultIndicationAreaSection, DefaultDeviceEntrySection defaultDeviceEntrySection, DefaultShortcutsSection defaultShortcutsSection, Optional optional, DefaultSettingsPopupMenuSection defaultSettingsPopupMenuSection, DefaultStatusViewSection defaultStatusViewSection, DefaultStatusBarSection defaultStatusBarSection, DefaultNotificationStackScrollLayoutSection defaultNotificationStackScrollLayoutSection, AodNotificationIconsSection aodNotificationIconsSection, AodBurnInSection aodBurnInSection, CommunalTutorialIndicatorSection communalTutorialIndicatorSection, ClockSection clockSection, SmartspaceSection smartspaceSection, KeyguardSliceViewSection keyguardSliceViewSection, DefaultUdfpsAccessibilityOverlaySection defaultUdfpsAccessibilityOverlaySection) {
        this.sections = ArraysKt.filterNotNull(new KeyguardSection[]{accessibilityActionsSection, defaultIndicationAreaSection, defaultShortcutsSection, optional.orElse(null), defaultSettingsPopupMenuSection, defaultStatusViewSection, defaultStatusBarSection, defaultNotificationStackScrollLayoutSection, aodNotificationIconsSection, smartspaceSection, aodBurnInSection, communalTutorialIndicatorSection, clockSection, keyguardSliceViewSection, defaultDeviceEntrySection, defaultUdfpsAccessibilityOverlaySection});
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardBlueprint
    public final String getId() {
        return "default";
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardBlueprint
    public final List getSections() {
        return this.sections;
    }
}
