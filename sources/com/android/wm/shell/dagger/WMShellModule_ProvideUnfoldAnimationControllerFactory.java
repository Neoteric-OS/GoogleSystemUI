package com.android.wm.shell.dagger;

import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.unfold.ShellUnfoldProgressProvider;
import com.android.wm.shell.unfold.UnfoldAnimationController;
import com.android.wm.shell.unfold.animation.FullscreenUnfoldTaskAnimator;
import com.android.wm.shell.unfold.animation.SplitTaskUnfoldAnimator;
import dagger.Lazy;
import dagger.internal.Provider;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellModule_ProvideUnfoldAnimationControllerFactory implements Provider {
    public static UnfoldAnimationController provideUnfoldAnimationController(Optional optional, TransactionPool transactionPool, SplitTaskUnfoldAnimator splitTaskUnfoldAnimator, FullscreenUnfoldTaskAnimator fullscreenUnfoldTaskAnimator, Lazy lazy, ShellInit shellInit, ShellExecutor shellExecutor) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(splitTaskUnfoldAnimator);
        arrayList.add(fullscreenUnfoldTaskAnimator);
        return new UnfoldAnimationController(shellInit, transactionPool, (ShellUnfoldProgressProvider) optional.get(), arrayList, lazy, shellExecutor);
    }
}
