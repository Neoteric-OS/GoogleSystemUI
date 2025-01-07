package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.DelegatingSoftwareKeyboardController;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.input.PlatformTextInputService;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LegacyPlatformTextInputServiceAdapter implements PlatformTextInputService {
    public LegacyAdaptingPlatformTextInputModifierNode textInputModifierNode;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface LegacyPlatformTextInputNode {
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void hideSoftwareKeyboard() {
        SoftwareKeyboardController softwareKeyboardController;
        LegacyAdaptingPlatformTextInputModifierNode legacyAdaptingPlatformTextInputModifierNode = this.textInputModifierNode;
        if (legacyAdaptingPlatformTextInputModifierNode == null || (softwareKeyboardController = (SoftwareKeyboardController) CompositionLocalConsumerModifierNodeKt.currentValueOf(legacyAdaptingPlatformTextInputModifierNode, CompositionLocalsKt.LocalSoftwareKeyboardController)) == null) {
            return;
        }
        ((DelegatingSoftwareKeyboardController) softwareKeyboardController).hide();
    }

    @Override // androidx.compose.ui.text.input.PlatformTextInputService
    public final void showSoftwareKeyboard() {
        SoftwareKeyboardController softwareKeyboardController;
        LegacyAdaptingPlatformTextInputModifierNode legacyAdaptingPlatformTextInputModifierNode = this.textInputModifierNode;
        if (legacyAdaptingPlatformTextInputModifierNode == null || (softwareKeyboardController = (SoftwareKeyboardController) CompositionLocalConsumerModifierNodeKt.currentValueOf(legacyAdaptingPlatformTextInputModifierNode, CompositionLocalsKt.LocalSoftwareKeyboardController)) == null) {
            return;
        }
        ((DelegatingSoftwareKeyboardController) softwareKeyboardController).show();
    }

    public final void unregisterModifier(LegacyAdaptingPlatformTextInputModifierNode legacyAdaptingPlatformTextInputModifierNode) {
        if (this.textInputModifierNode != legacyAdaptingPlatformTextInputModifierNode) {
            InlineClassHelperKt.throwIllegalStateException("Expected textInputModifierNode to be " + legacyAdaptingPlatformTextInputModifierNode + " but was " + this.textInputModifierNode);
        }
        this.textInputModifierNode = null;
    }
}
