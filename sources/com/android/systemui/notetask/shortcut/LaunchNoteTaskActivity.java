package com.android.systemui.notetask.shortcut;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import com.android.systemui.notetask.NoteTaskController;
import com.android.systemui.notetask.NoteTaskEntryPoint;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LaunchNoteTaskActivity extends ComponentActivity {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final NoteTaskController controller;

    public LaunchNoteTaskActivity(NoteTaskController noteTaskController) {
        this.controller = noteTaskController;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.controller.showNoteTaskAsUser(isInMultiWindowMode() ? NoteTaskEntryPoint.WIDGET_PICKER_SHORTCUT_IN_MULTI_WINDOW_MODE : NoteTaskEntryPoint.WIDGET_PICKER_SHORTCUT, getUser());
        finish();
    }
}
