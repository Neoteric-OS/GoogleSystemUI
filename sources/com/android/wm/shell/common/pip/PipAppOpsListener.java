package com.android.wm.shell.common.pip;

import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Pair;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.pip.phone.PipMotionHelper;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipAppOpsListener {
    public final PipAppOpsListener$mAppOpsChangedListener$1 mAppOpsChangedListener = new AppOpsManager.OnOpChangedListener() { // from class: com.android.wm.shell.common.pip.PipAppOpsListener$mAppOpsChangedListener$1
        @Override // android.app.AppOpsManager.OnOpChangedListener
        public final void onOpChanged(String str, String str2) {
            try {
                Pair topPipActivity = PipUtils.getTopPipActivity(PipAppOpsListener.this.mContext);
                ComponentName componentName = (ComponentName) topPipActivity.first;
                if (componentName == null) {
                    return;
                }
                Integer num = (Integer) topPipActivity.second;
                PackageManager packageManager = PipAppOpsListener.this.mContext.getPackageManager();
                Intrinsics.checkNotNull(num);
                ApplicationInfo applicationInfoAsUser = packageManager.getApplicationInfoAsUser(str2, 0, num.intValue());
                if (!Intrinsics.areEqual(applicationInfoAsUser.packageName, componentName.getPackageName()) || PipAppOpsListener.this.mAppOpsManager.checkOpNoThrow(67, applicationInfoAsUser.uid, str2) == 0) {
                    return;
                }
                final PipAppOpsListener pipAppOpsListener = PipAppOpsListener.this;
                ((HandlerExecutor) pipAppOpsListener.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.common.pip.PipAppOpsListener$mAppOpsChangedListener$1.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        PipAppOpsListener.this.mCallback.dismissPip();
                    }
                });
            } catch (PackageManager.NameNotFoundException unused) {
                PipAppOpsListener pipAppOpsListener2 = PipAppOpsListener.this;
                pipAppOpsListener2.mAppOpsManager.stopWatchingMode(pipAppOpsListener2.mAppOpsChangedListener);
            }
        }
    };
    public final AppOpsManager mAppOpsManager;
    public final PipMotionHelper mCallback;
    public final Context mContext;
    public final ShellExecutor mMainExecutor;

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.wm.shell.common.pip.PipAppOpsListener$mAppOpsChangedListener$1] */
    public PipAppOpsListener(Context context, PipMotionHelper pipMotionHelper, ShellExecutor shellExecutor) {
        this.mContext = context;
        this.mCallback = pipMotionHelper;
        this.mMainExecutor = shellExecutor;
        this.mAppOpsManager = (AppOpsManager) context.getSystemService("appops");
    }
}
