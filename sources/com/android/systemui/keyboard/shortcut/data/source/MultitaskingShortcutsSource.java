package com.android.systemui.keyboard.shortcut.data.source;

import android.content.res.Resources;
import android.view.KeyboardShortcutGroup;
import com.android.systemui.keyboard.shortcut.data.model.KeyboardShortcutInfoBuilder;
import com.android.systemui.keyboard.shortcut.data.model.KeyboardShortcutInfoKt;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MultitaskingShortcutsSource implements KeyboardShortcutGroupsSource {
    public final Resources resources;

    public MultitaskingShortcutsSource(Resources resources) {
        this.resources = resources;
    }

    @Override // com.android.systemui.keyboard.shortcut.data.source.KeyboardShortcutGroupsSource
    public final Object shortcutGroups(int i, Continuation continuation) {
        return CollectionsKt__CollectionsKt.listOf(new KeyboardShortcutGroup(this.resources.getString(R.string.shortcutHelper_category_recent_apps), CollectionsKt__CollectionsKt.listOf(KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_cycle_forward), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.MultitaskingShortcutsSource$recentsShortcuts$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 2;
                keyboardShortcutInfoBuilder.keyCode = 61;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_cycle_back), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.MultitaskingShortcutsSource$recentsShortcuts$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 3;
                keyboardShortcutInfoBuilder.keyCode = 61;
                return Unit.INSTANCE;
            }
        }))), new KeyboardShortcutGroup(this.resources.getString(R.string.shortcutHelper_category_split_screen), CollectionsKt__CollectionsKt.listOf(KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.system_multitasking_rhs), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.MultitaskingShortcutsSource$splitScreenShortcuts$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 69632;
                keyboardShortcutInfoBuilder.keyCode = 22;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.system_multitasking_lhs), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.MultitaskingShortcutsSource$splitScreenShortcuts$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 69632;
                keyboardShortcutInfoBuilder.keyCode = 21;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.system_multitasking_full_screen), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.MultitaskingShortcutsSource$splitScreenShortcuts$3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 69632;
                keyboardShortcutInfoBuilder.keyCode = 19;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.system_multitasking_splitscreen_focus_rhs), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.MultitaskingShortcutsSource$splitScreenShortcuts$4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65538;
                keyboardShortcutInfoBuilder.keyCode = 22;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.system_multitasking_splitscreen_focus_rhs), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.MultitaskingShortcutsSource$splitScreenShortcuts$5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65538;
                keyboardShortcutInfoBuilder.keyCode = 21;
                return Unit.INSTANCE;
            }
        }))));
    }
}
