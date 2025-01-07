package com.android.systemui.notetask.shortcut;

import android.app.role.RoleManager;
import android.content.pm.ShortcutManager;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import com.android.systemui.notetask.NoteTaskRoleManagerExt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CreateNoteTaskShortcutActivity extends ComponentActivity {
    public final RoleManager roleManager;
    public final ShortcutManager shortcutManager;

    public CreateNoteTaskShortcutActivity(RoleManager roleManager, ShortcutManager shortcutManager) {
        this.roleManager = roleManager;
        this.shortcutManager = shortcutManager;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setResult(-1, this.shortcutManager.createShortcutResultIntent(NoteTaskRoleManagerExt.createNoteShortcutInfoAsUser(this.roleManager, this, getUser())));
        finish();
    }
}
