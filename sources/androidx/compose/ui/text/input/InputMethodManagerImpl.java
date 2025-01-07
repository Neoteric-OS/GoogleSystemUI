package androidx.compose.ui.text.input;

import android.view.inputmethod.InputMethodManager;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.core.view.SoftwareKeyboardControllerCompat;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
@Deprecated
/* loaded from: classes.dex */
public final class InputMethodManagerImpl {
    public final Lazy imm$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.compose.ui.text.input.InputMethodManagerImpl$imm$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return (InputMethodManager) InputMethodManagerImpl.this.view.getContext().getSystemService("input_method");
        }
    });
    public final SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat;
    public final AndroidComposeView view;

    public InputMethodManagerImpl(AndroidComposeView androidComposeView) {
        this.view = androidComposeView;
        this.softwareKeyboardControllerCompat = new SoftwareKeyboardControllerCompat(androidComposeView);
    }
}
