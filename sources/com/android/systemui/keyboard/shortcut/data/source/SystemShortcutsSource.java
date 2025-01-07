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
public final class SystemShortcutsSource implements KeyboardShortcutGroupsSource {
    public final Resources resources;

    public SystemShortcutsSource(Resources resources) {
        this.resources = resources;
    }

    @Override // com.android.systemui.keyboard.shortcut.data.source.KeyboardShortcutGroupsSource
    public final Object shortcutGroups(int i, Continuation continuation) {
        return CollectionsKt__CollectionsKt.listOf(new KeyboardShortcutGroup(this.resources.getString(R.string.shortcut_helper_category_system_controls), CollectionsKt__CollectionsKt.listOf(KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_access_all_apps_search), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 0;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_access_home_screen), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 0;
                keyboardShortcutInfoBuilder.keyCode = 3;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_access_home_screen), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 36;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_access_home_screen), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 66;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_overview_open_apps), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 0;
                keyboardShortcutInfoBuilder.keyCode = 312;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_overview_open_apps), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 61;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_go_back), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 0;
                keyboardShortcutInfoBuilder.keyCode = 4;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_go_back), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 111;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_go_back), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 67;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_go_back), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 21;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_full_screenshot), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 69632;
                keyboardShortcutInfoBuilder.keyCode = 47;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_access_system_app_shortcuts), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 76;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_access_notification_shade), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 42;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_lock_screen), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemControlsShortcuts$14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 40;
                return Unit.INSTANCE;
            }
        }))), new KeyboardShortcutGroup(this.resources.getString(R.string.shortcut_helper_category_system_apps), CollectionsKt__CollectionsKt.listOf(KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_quick_memo), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemAppsShortcuts$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 69632;
                keyboardShortcutInfoBuilder.keyCode = 42;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_access_system_settings), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemAppsShortcuts$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 37;
                return Unit.INSTANCE;
            }
        }), KeyboardShortcutInfoKt.shortcutInfo(this.resources.getString(R.string.group_system_access_google_assistant), new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.source.SystemShortcutsSource$systemAppsShortcuts$3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyboardShortcutInfoBuilder keyboardShortcutInfoBuilder = (KeyboardShortcutInfoBuilder) obj;
                keyboardShortcutInfoBuilder.modifiers = 65536;
                keyboardShortcutInfoBuilder.keyCode = 29;
                return Unit.INSTANCE;
            }
        }))));
    }
}
