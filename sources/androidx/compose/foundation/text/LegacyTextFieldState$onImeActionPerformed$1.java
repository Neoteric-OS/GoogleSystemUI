package androidx.compose.foundation.text;

import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.platform.DelegatingSoftwareKeyboardController;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.text.input.ImeAction;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LegacyTextFieldState$onImeActionPerformed$1 extends Lambda implements Function1 {
    final /* synthetic */ LegacyTextFieldState this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacyTextFieldState$onImeActionPerformed$1(LegacyTextFieldState legacyTextFieldState) {
        super(1);
        this.this$0 = legacyTextFieldState;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Function1 function1;
        Unit unit;
        SoftwareKeyboardController softwareKeyboardController;
        int i = ((ImeAction) obj).value;
        KeyboardActionRunner keyboardActionRunner = this.this$0.keyboardActionRunner;
        keyboardActionRunner.getClass();
        if (ImeAction.m617equalsimpl0(i, 7)) {
            KeyboardActions keyboardActions = keyboardActionRunner.keyboardActions;
            if (keyboardActions == null) {
                keyboardActions = null;
            }
            function1 = keyboardActions.onDone;
        } else {
            if (ImeAction.m617equalsimpl0(i, 2)) {
                KeyboardActions keyboardActions2 = keyboardActionRunner.keyboardActions;
                if (keyboardActions2 == null) {
                    keyboardActions2 = null;
                }
                keyboardActions2.getClass();
            } else if (ImeAction.m617equalsimpl0(i, 6)) {
                KeyboardActions keyboardActions3 = keyboardActionRunner.keyboardActions;
                if (keyboardActions3 == null) {
                    keyboardActions3 = null;
                }
                keyboardActions3.getClass();
            } else if (ImeAction.m617equalsimpl0(i, 5)) {
                KeyboardActions keyboardActions4 = keyboardActionRunner.keyboardActions;
                if (keyboardActions4 == null) {
                    keyboardActions4 = null;
                }
                keyboardActions4.getClass();
            } else if (ImeAction.m617equalsimpl0(i, 3)) {
                KeyboardActions keyboardActions5 = keyboardActionRunner.keyboardActions;
                if (keyboardActions5 == null) {
                    keyboardActions5 = null;
                }
                function1 = keyboardActions5.onSearch;
            } else if (ImeAction.m617equalsimpl0(i, 4)) {
                KeyboardActions keyboardActions6 = keyboardActionRunner.keyboardActions;
                if (keyboardActions6 == null) {
                    keyboardActions6 = null;
                }
                keyboardActions6.getClass();
            } else {
                if (!(ImeAction.m617equalsimpl0(i, 1) ? true : ImeAction.m617equalsimpl0(i, 0))) {
                    throw new IllegalStateException("invalid ImeAction");
                }
            }
            function1 = null;
        }
        Unit unit2 = Unit.INSTANCE;
        if (function1 != null) {
            function1.invoke(keyboardActionRunner);
            unit = unit2;
        } else {
            unit = null;
        }
        if (unit == null) {
            if (ImeAction.m617equalsimpl0(i, 6)) {
                FocusManager focusManager = keyboardActionRunner.focusManager;
                ((FocusOwnerImpl) (focusManager != null ? focusManager : null)).m291moveFocus3ESFkO8(1);
            } else if (ImeAction.m617equalsimpl0(i, 5)) {
                FocusManager focusManager2 = keyboardActionRunner.focusManager;
                ((FocusOwnerImpl) (focusManager2 != null ? focusManager2 : null)).m291moveFocus3ESFkO8(2);
            } else if (ImeAction.m617equalsimpl0(i, 7) && (softwareKeyboardController = keyboardActionRunner.keyboardController) != null) {
                ((DelegatingSoftwareKeyboardController) softwareKeyboardController).hide();
            }
        }
        return unit2;
    }
}
