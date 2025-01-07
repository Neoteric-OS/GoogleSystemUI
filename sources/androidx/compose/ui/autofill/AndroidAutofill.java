package androidx.compose.ui.autofill;

import android.view.autofill.AutofillManager;
import androidx.compose.ui.platform.AndroidComposeView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidAutofill {
    public final AutofillManager autofillManager;
    public final AutofillTree autofillTree;
    public final AndroidComposeView view;

    public AndroidAutofill(AndroidComposeView androidComposeView, AutofillTree autofillTree) {
        this.view = androidComposeView;
        this.autofillTree = autofillTree;
        AutofillManager autofillManager = (AutofillManager) androidComposeView.getContext().getSystemService(AutofillManager.class);
        if (autofillManager == null) {
            throw new IllegalStateException("Autofill service could not be located.");
        }
        this.autofillManager = autofillManager;
        androidComposeView.setImportantForAutofill(1);
    }
}
