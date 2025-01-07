package com.android.wm.shell.back;

import android.window.BackAnimationAdapter;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.back.BackAnimationController;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.sysui.ShellController;
import java.io.PrintWriter;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BackAnimationController$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BackAnimationController f$0;

    public /* synthetic */ BackAnimationController$$ExternalSyntheticLambda3(BackAnimationController backAnimationController, int i) {
        this.$r8$classId = i;
        this.f$0 = backAnimationController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 1;
        int i2 = this.$r8$classId;
        final BackAnimationController backAnimationController = this.f$0;
        switch (i2) {
            case 0:
                boolean z = BackAnimationController.IS_ENABLED;
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, 4969580294387085727L, 0, null);
                }
                backAnimationController.mEnableAnimations.set(true);
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -1794321026059586220L, 0, String.valueOf(true));
                }
                backAnimationController.mBackAnimationAdapter = new BackAnimationAdapter(new BackAnimationController.AnonymousClass3(backAnimationController));
                Supplier supplier = new Supplier() { // from class: com.android.wm.shell.back.BackAnimationController$$ExternalSyntheticLambda4
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        BackAnimationController backAnimationController2 = BackAnimationController.this;
                        boolean z2 = BackAnimationController.IS_ENABLED;
                        return new BackAnimationController.IBackAnimationImpl(backAnimationController2, backAnimationController2);
                    }
                };
                ShellController shellController = backAnimationController.mShellController;
                shellController.addExternalInterface("extra_shell_back_animation", supplier, backAnimationController);
                backAnimationController.mShellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.back.BackAnimationController$$ExternalSyntheticLambda5
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        BackAnimationController backAnimationController2 = BackAnimationController.this;
                        PrintWriter printWriter = (PrintWriter) obj;
                        String str = (String) obj2;
                        boolean z2 = BackAnimationController.IS_ENABLED;
                        printWriter.println(str + "BackAnimationController state:");
                        printWriter.println(str + "  mEnableAnimations=" + backAnimationController2.mEnableAnimations.get());
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append("  mBackGestureStarted=");
                        StringBuilder m = BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(sb, backAnimationController2.mBackGestureStarted, printWriter, str, "  mPostCommitAnimationInProgress="), backAnimationController2.mPostCommitAnimationInProgress, printWriter, str, "  mShouldStartOnNextMoveEvent="), backAnimationController2.mShouldStartOnNextMoveEvent, printWriter, str, "  mPointerPilfered="), backAnimationController2.mThresholdCrossed, printWriter, str, "  mRequirePointerPilfer=");
                        m.append(backAnimationController2.mRequirePointerPilfer);
                        printWriter.println(m.toString());
                        printWriter.println(str + "  mCurrentTracker state:");
                        backAnimationController2.mCurrentTracker.dump(printWriter, str + "    ");
                        printWriter.println(str + "  mQueuedTracker state:");
                        backAnimationController2.mQueuedTracker.dump(printWriter, str + "    ");
                    }
                }, backAnimationController);
                shellController.addConfigurationChangeListener(backAnimationController);
                break;
            case 1:
                backAnimationController.onBackAnimationFinished();
                break;
            case 2:
                boolean z2 = BackAnimationController.IS_ENABLED;
                backAnimationController.getClass();
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BACK_PREVIEW_enabled[3]) {
                    ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_BACK_PREVIEW, -7068333096785398281L, 1, 2000L);
                }
                backAnimationController.finishBackAnimation();
                break;
            default:
                boolean z3 = BackAnimationController.IS_ENABLED;
                backAnimationController.getClass();
                ((HandlerExecutor) backAnimationController.mShellExecutor).execute(new BackAnimationController$$ExternalSyntheticLambda3(backAnimationController, i));
                break;
        }
    }
}
