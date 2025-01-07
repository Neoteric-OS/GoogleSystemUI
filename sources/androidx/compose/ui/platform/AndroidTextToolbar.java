package androidx.compose.ui.platform;

import android.view.ActionMode;
import androidx.compose.ui.platform.actionmodecallback.TextActionModeCallback;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidTextToolbar implements TextToolbar {
    public ActionMode actionMode;
    public final AndroidComposeView view;
    public final TextActionModeCallback textActionModeCallback = new TextActionModeCallback(new Function0() { // from class: androidx.compose.ui.platform.AndroidTextToolbar$textActionModeCallback$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            AndroidTextToolbar.this.actionMode = null;
            return Unit.INSTANCE;
        }
    });
    public TextToolbarStatus status = TextToolbarStatus.Hidden;

    public AndroidTextToolbar(AndroidComposeView androidComposeView) {
        this.view = androidComposeView;
    }
}
