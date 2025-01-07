package androidx.compose.ui.platform;

import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DelegatingSoftwareKeyboardController implements SoftwareKeyboardController {
    public final TextInputService textInputService;

    public DelegatingSoftwareKeyboardController(TextInputService textInputService) {
        this.textInputService = textInputService;
    }

    public final void hide() {
        this.textInputService.platformTextInputService.hideSoftwareKeyboard();
    }

    public final void show() {
        TextInputService textInputService = this.textInputService;
        if (((TextInputSession) textInputService._currentInputSession.get()) != null) {
            textInputService.platformTextInputService.showSoftwareKeyboard();
        }
    }
}
