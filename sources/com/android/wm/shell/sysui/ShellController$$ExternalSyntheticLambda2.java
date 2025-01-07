package com.android.wm.shell.sysui;

import android.view.SurfaceControlRegistry;
import java.io.PrintWriter;
import java.util.function.BiConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ShellController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ ShellController$$ExternalSyntheticLambda2(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                final ShellController shellController = (ShellController) obj;
                shellController.getClass();
                shellController.mShellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.sysui.ShellController$$ExternalSyntheticLambda4
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj2, Object obj3) {
                        ShellController shellController2 = ShellController.this;
                        PrintWriter printWriter = (PrintWriter) obj2;
                        String str = (String) obj3;
                        shellController2.getClass();
                        String str2 = str + "  ";
                        printWriter.println(str + "ShellController");
                        printWriter.println(str2 + "mConfigChangeListeners=" + shellController2.mConfigChangeListeners.size());
                        printWriter.println(str2 + "mLastConfiguration=" + shellController2.mLastConfiguration);
                        printWriter.println(str2 + "mKeyguardChangeListeners=" + shellController2.mKeyguardChangeListeners.size());
                        printWriter.println(str2 + "mUserChangeListeners=" + shellController2.mUserChangeListeners.size());
                        if (shellController2.mExternalInterfaces.isEmpty()) {
                            return;
                        }
                        printWriter.println(str2 + "mExternalInterfaces={");
                        for (String str3 : shellController2.mExternalInterfaces.keySet()) {
                            printWriter.println(str2 + "\t" + str3 + ": " + shellController2.mExternalInterfaces.get(str3));
                        }
                        printWriter.println(str2 + "}");
                    }
                }, shellController);
                shellController.mDisplayInsetsController.addInsetsChangedListener(shellController.mContext.getDisplayId(), shellController.mInsetsChangeListener);
                break;
            case 1:
                ShellController shellController2 = (ShellController) obj;
                SurfaceControlRegistry.createProcessInstance(shellController2.mContext);
                shellController2.mShellInit.init();
                break;
            default:
                ShellController.this.onKeyguardDismissAnimationFinished();
                break;
        }
    }
}
