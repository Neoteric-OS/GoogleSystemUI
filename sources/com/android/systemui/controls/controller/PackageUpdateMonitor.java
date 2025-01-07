package com.android.systemui.controls.controller;

import android.content.Context;
import android.os.Handler;
import android.os.UserHandle;
import com.android.internal.content.PackageMonitor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PackageUpdateMonitor extends PackageMonitor {
    public final Handler bgHandler;
    public final ControlsProviderLifecycleManager$ServiceMethod$run$1 callback;
    public final Context context;
    public final AtomicBoolean monitoring = new AtomicBoolean(false);
    public final String packageName;
    public final UserHandle user;

    public PackageUpdateMonitor(UserHandle userHandle, String str, ControlsProviderLifecycleManager$ServiceMethod$run$1 controlsProviderLifecycleManager$ServiceMethod$run$1, Handler handler, Context context) {
        this.user = userHandle;
        this.packageName = str;
        this.callback = controlsProviderLifecycleManager$ServiceMethod$run$1;
        this.bgHandler = handler;
        this.context = context;
    }

    public final void onPackageUpdateFinished(String str, int i) {
        super.onPackageUpdateFinished(str, i);
        if (Intrinsics.areEqual(str, this.packageName) && Intrinsics.areEqual(UserHandle.getUserHandleForUid(i), this.user)) {
            this.callback.run();
        }
    }
}
