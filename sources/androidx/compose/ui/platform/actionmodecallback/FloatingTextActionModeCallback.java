package androidx.compose.ui.platform.actionmodecallback;

import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FloatingTextActionModeCallback extends ActionMode.Callback2 {
    public final TextActionModeCallback callback;

    public FloatingTextActionModeCallback(TextActionModeCallback textActionModeCallback) {
        this.callback = textActionModeCallback;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        TextActionModeCallback textActionModeCallback = this.callback;
        textActionModeCallback.getClass();
        Intrinsics.checkNotNull(menuItem);
        int itemId = menuItem.getItemId();
        if (itemId == MenuItemOption.Copy.getId()) {
            Function0 function0 = textActionModeCallback.onCopyRequested;
            if (function0 != null) {
                function0.invoke();
            }
        } else if (itemId == MenuItemOption.Paste.getId()) {
            Function0 function02 = textActionModeCallback.onPasteRequested;
            if (function02 != null) {
                function02.invoke();
            }
        } else if (itemId == MenuItemOption.Cut.getId()) {
            Function0 function03 = textActionModeCallback.onCutRequested;
            if (function03 != null) {
                function03.invoke();
            }
        } else {
            if (itemId != MenuItemOption.SelectAll.getId()) {
                return false;
            }
            Function0 function04 = textActionModeCallback.onSelectAllRequested;
            if (function04 != null) {
                function04.invoke();
            }
        }
        if (actionMode != null) {
            actionMode.finish();
        }
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        TextActionModeCallback textActionModeCallback = this.callback;
        textActionModeCallback.getClass();
        if (menu == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null menu");
        }
        if (actionMode == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null mode");
        }
        if (textActionModeCallback.onCopyRequested != null) {
            TextActionModeCallback.addMenuItem$ui_release(menu, MenuItemOption.Copy);
        }
        if (textActionModeCallback.onPasteRequested != null) {
            TextActionModeCallback.addMenuItem$ui_release(menu, MenuItemOption.Paste);
        }
        if (textActionModeCallback.onCutRequested != null) {
            TextActionModeCallback.addMenuItem$ui_release(menu, MenuItemOption.Cut);
        }
        if (textActionModeCallback.onSelectAllRequested == null) {
            return true;
        }
        TextActionModeCallback.addMenuItem$ui_release(menu, MenuItemOption.SelectAll);
        return true;
    }

    @Override // android.view.ActionMode.Callback
    public final void onDestroyActionMode(ActionMode actionMode) {
        this.callback.onActionModeDestroy.invoke();
    }

    @Override // android.view.ActionMode.Callback2
    public final void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
        androidx.compose.ui.geometry.Rect rect2 = this.callback.rect;
        if (rect != null) {
            rect.set((int) rect2.left, (int) rect2.top, (int) rect2.right, (int) rect2.bottom);
        }
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        TextActionModeCallback textActionModeCallback = this.callback;
        textActionModeCallback.getClass();
        if (actionMode == null || menu == null) {
            return false;
        }
        TextActionModeCallback.addOrRemoveMenuItem(menu, MenuItemOption.Copy, textActionModeCallback.onCopyRequested);
        TextActionModeCallback.addOrRemoveMenuItem(menu, MenuItemOption.Paste, textActionModeCallback.onPasteRequested);
        TextActionModeCallback.addOrRemoveMenuItem(menu, MenuItemOption.Cut, textActionModeCallback.onCutRequested);
        TextActionModeCallback.addOrRemoveMenuItem(menu, MenuItemOption.SelectAll, textActionModeCallback.onSelectAllRequested);
        return true;
    }
}
