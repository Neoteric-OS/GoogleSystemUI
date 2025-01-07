package com.android.systemui.keyboard.shortcut.ui.composable;

import androidx.compose.foundation.IndicationNodeFactory;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.ui.node.DelegatableNode;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutHelperIndication implements IndicationNodeFactory {
    public final InteractionSource interactionSource;
    public final InteractionsConfig interactionsConfig;

    public ShortcutHelperIndication(InteractionSource interactionSource, InteractionsConfig interactionsConfig) {
        this.interactionSource = interactionSource;
        this.interactionsConfig = interactionsConfig;
    }

    @Override // androidx.compose.foundation.IndicationNodeFactory
    public final DelegatableNode create(InteractionSource interactionSource) {
        return new ShortcutHelperInteractionsNode(interactionSource, this.interactionsConfig);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShortcutHelperIndication)) {
            return false;
        }
        ShortcutHelperIndication shortcutHelperIndication = (ShortcutHelperIndication) obj;
        return Intrinsics.areEqual(this.interactionSource, shortcutHelperIndication.interactionSource) && Intrinsics.areEqual(this.interactionsConfig, shortcutHelperIndication.interactionsConfig);
    }

    @Override // androidx.compose.foundation.IndicationNodeFactory
    public final int hashCode() {
        return this.interactionsConfig.hashCode() + (this.interactionSource.hashCode() * 31);
    }

    public final String toString() {
        return "ShortcutHelperIndication(interactionSource=" + this.interactionSource + ", interactionsConfig=" + this.interactionsConfig + ")";
    }
}
