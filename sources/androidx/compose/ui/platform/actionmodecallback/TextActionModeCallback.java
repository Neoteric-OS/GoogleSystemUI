package androidx.compose.ui.platform.actionmodecallback;

import android.R;
import android.view.Menu;
import androidx.compose.ui.geometry.Rect;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextActionModeCallback {
    public final Function0 onActionModeDestroy;
    public Function0 onCopyRequested;
    public Function0 onCutRequested;
    public Function0 onPasteRequested;
    public Function0 onSelectAllRequested;
    public Rect rect;

    public TextActionModeCallback(Function0 function0) {
        Rect rect = Rect.Zero;
        this.onActionModeDestroy = function0;
        this.rect = rect;
        this.onCopyRequested = null;
        this.onPasteRequested = null;
        this.onCutRequested = null;
        this.onSelectAllRequested = null;
    }

    public static void addMenuItem$ui_release(Menu menu, MenuItemOption menuItemOption) {
        int i;
        int id = menuItemOption.getId();
        int order = menuItemOption.getOrder();
        int ordinal = menuItemOption.ordinal();
        if (ordinal == 0) {
            i = R.string.copy;
        } else if (ordinal == 1) {
            i = R.string.paste;
        } else if (ordinal == 2) {
            i = R.string.cut;
        } else {
            if (ordinal != 3) {
                throw new NoWhenBranchMatchedException();
            }
            i = R.string.selectAll;
        }
        menu.add(0, id, order, i).setShowAsAction(1);
    }

    public static void addOrRemoveMenuItem(Menu menu, MenuItemOption menuItemOption, Function0 function0) {
        if (function0 != null && menu.findItem(menuItemOption.getId()) == null) {
            addMenuItem$ui_release(menu, menuItemOption);
        } else {
            if (function0 != null || menu.findItem(menuItemOption.getId()) == null) {
                return;
            }
            menu.removeItem(menuItemOption.getId());
        }
    }
}
