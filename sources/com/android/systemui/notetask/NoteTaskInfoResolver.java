package com.android.systemui.notetask;

import android.app.role.RoleManager;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.util.Log;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoteTaskInfoResolver {
    public static final PackageManager.ApplicationInfoFlags EMPTY_APPLICATION_INFO_FLAGS;
    public static final String TAG;
    public final PackageManager packageManager;
    public final RoleManager roleManager;

    static {
        String simpleName = Reflection.getOrCreateKotlinClass(NoteTaskInfoResolver.class).getSimpleName();
        if (simpleName == null) {
            simpleName = "";
        }
        TAG = simpleName;
        PackageManager.ApplicationInfoFlags of = PackageManager.ApplicationInfoFlags.of(0L);
        Intrinsics.checkNotNull(of);
        EMPTY_APPLICATION_INFO_FLAGS = of;
    }

    public NoteTaskInfoResolver(RoleManager roleManager, PackageManager packageManager) {
        this.roleManager = roleManager;
        this.packageManager = packageManager;
    }

    public final NoteTaskInfo resolveInfo(NoteTaskEntryPoint noteTaskEntryPoint, boolean z, UserHandle userHandle) {
        int i;
        String str = (String) CollectionsKt.firstOrNull(this.roleManager.getRoleHoldersAsUser("android.app.role.NOTES", userHandle));
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            i = this.packageManager.getApplicationInfoAsUser(str, EMPTY_APPLICATION_INFO_FLAGS, userHandle).uid;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Couldn't find notes app UID", e);
            i = 0;
        }
        return new NoteTaskInfo(str, i, userHandle, noteTaskEntryPoint, z);
    }
}
