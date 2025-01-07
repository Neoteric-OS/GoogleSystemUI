package com.android.systemui.keyboard.shortcut.data.source;

import android.view.WindowManager;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AppCategoriesShortcutsSource implements KeyboardShortcutGroupsSource {
    public final CoroutineDispatcher backgroundDispatcher;
    public final WindowManager windowManager;

    public AppCategoriesShortcutsSource(WindowManager windowManager, CoroutineDispatcher coroutineDispatcher) {
        this.windowManager = windowManager;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    @Override // com.android.systemui.keyboard.shortcut.data.source.KeyboardShortcutGroupsSource
    public final Object shortcutGroups(int i, Continuation continuation) {
        return BuildersKt.withContext(this.backgroundDispatcher, new AppCategoriesShortcutsSource$shortcutGroups$2(this, i, null), continuation);
    }
}
