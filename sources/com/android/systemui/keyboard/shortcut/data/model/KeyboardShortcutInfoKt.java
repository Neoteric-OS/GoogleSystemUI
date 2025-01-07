package com.android.systemui.keyboard.shortcut.data.model;

import android.view.KeyboardShortcutInfo;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyboardShortcutInfoKt {
    public static final KeyboardShortcutInfo shortcutInfo(String str, Function1 function1) {
        KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = new KeyboardShortcutInfoBuilder();
        function1.invoke(keyboardShortcutInfoBuilder);
        return new KeyboardShortcutInfo(str, keyboardShortcutInfoBuilder.keyCode, keyboardShortcutInfoBuilder.modifiers);
    }
}
