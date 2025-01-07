package com.android.systemui.notetask;

import android.content.Intent;
import com.android.systemui.notetask.NoteTaskLaunchMode;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NoteTaskControllerKt {
    public static final Intent access$createNoteTaskIntent(NoteTaskInfo noteTaskInfo) {
        Intent intent = new Intent("android.intent.action.CREATE_NOTE");
        intent.setPackage(noteTaskInfo.packageName);
        intent.putExtra("android.intent.extra.USE_STYLUS_MODE", noteTaskInfo.entryPoint != NoteTaskEntryPoint.KEYBOARD_SHORTCUT);
        intent.addFlags(268435456);
        if (Intrinsics.areEqual(noteTaskInfo.launchMode, NoteTaskLaunchMode.Activity.INSTANCE)) {
            intent.addFlags(134217728);
            intent.addFlags(524288);
        }
        return intent;
    }
}
