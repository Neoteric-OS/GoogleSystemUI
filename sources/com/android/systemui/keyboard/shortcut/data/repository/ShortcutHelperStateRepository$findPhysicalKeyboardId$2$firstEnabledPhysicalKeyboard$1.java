package com.android.systemui.keyboard.shortcut.data.repository;

import android.view.InputDevice;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ShortcutHelperStateRepository$findPhysicalKeyboardId$2$firstEnabledPhysicalKeyboard$1 extends Lambda implements Function1 {
    public static final ShortcutHelperStateRepository$findPhysicalKeyboardId$2$firstEnabledPhysicalKeyboard$1 INSTANCE = new ShortcutHelperStateRepository$findPhysicalKeyboardId$2$firstEnabledPhysicalKeyboard$1();

    public ShortcutHelperStateRepository$findPhysicalKeyboardId$2$firstEnabledPhysicalKeyboard$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        InputDevice inputDevice = (InputDevice) obj;
        return Boolean.valueOf(inputDevice.isEnabled() && inputDevice.isFullKeyboard() && !inputDevice.isVirtual());
    }
}
