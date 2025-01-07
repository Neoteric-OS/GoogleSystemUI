package androidx.compose.ui.autofill;

import android.view.autofill.AutofillManager;
import androidx.compose.ui.platform.AndroidComposeView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AutofillManagerWrapperImpl implements AutofillManagerWrapper {
    public final AutofillManager autofillManager;
    public final AndroidComposeView view;

    public AutofillManagerWrapperImpl(AndroidComposeView androidComposeView) {
        this.view = androidComposeView;
        AutofillManager autofillManager = (AutofillManager) androidComposeView.getContext().getSystemService(AutofillManager.class);
        if (autofillManager == null) {
            throw new IllegalStateException("Autofill service could not be located.");
        }
        this.autofillManager = autofillManager;
    }
}
