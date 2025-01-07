package com.android.systemui.notetask;

import android.app.role.RoleManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Icon;
import android.os.PersistableBundle;
import android.os.UserHandle;
import com.android.systemui.notetask.shortcut.LaunchNoteTaskActivity;
import com.android.wm.shell.R;
import kotlin.Result;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NoteTaskRoleManagerExt {
    public static ShortcutInfo createNoteShortcutInfoAsUser(RoleManager roleManager, Context context, UserHandle userHandle) {
        String string;
        Object failure;
        String str = (String) CollectionsKt.firstOrNull(roleManager.getRoleHoldersAsUser("android.app.role.NOTES", userHandle));
        PersistableBundle persistableBundle = new PersistableBundle();
        if (str != null) {
            persistableBundle.putString("extra_shortcut_badge_override_package", str);
        }
        String string2 = context.getString(R.string.note_task_button_label);
        PackageManager packageManager = context.getPackageManager();
        String str2 = null;
        if (str != null) {
            try {
                failure = packageManager.getApplicationInfo(str, 0);
                Intrinsics.checkNotNull(failure);
            } catch (Throwable th) {
                failure = new Result.Failure(th);
            }
            if (failure instanceof Result.Failure) {
                failure = null;
            }
            ApplicationInfo applicationInfo = (ApplicationInfo) failure;
            if (applicationInfo != null) {
                str2 = packageManager.getApplicationLabel(applicationInfo).toString();
            }
        }
        if (str2 == null) {
            string = string2;
        } else {
            string = context.getString(R.string.note_task_shortcut_long_label, str2);
            Intrinsics.checkNotNull(string);
        }
        Icon createWithResource = Icon.createWithResource(context, R.drawable.ic_note_task_shortcut_widget);
        ShortcutInfo.Builder builder = new ShortcutInfo.Builder(context, "note_task_shortcut_id");
        int i = LaunchNoteTaskActivity.$r8$clinit;
        Intent intent = new Intent(context, (Class<?>) LaunchNoteTaskActivity.class);
        intent.setAction("android.intent.action.CREATE_NOTE");
        return builder.setIntent(intent).setActivity(new ComponentName(context, (Class<?>) LaunchNoteTaskActivity.class)).setShortLabel(string2).setLongLabel(string).setLongLived(true).setIcon(createWithResource).setExtras(persistableBundle).build();
    }
}
