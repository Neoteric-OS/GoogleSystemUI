package com.android.wm.shell.dagger;

import android.content.Context;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.unfold.UnfoldBackgroundController;
import com.android.wm.shell.unfold.animation.SplitTaskUnfoldAnimator;
import dagger.Lazy;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellModule_ProvideSplitTaskUnfoldAnimatorBaseFactory implements Provider {
    public static SplitTaskUnfoldAnimator provideSplitTaskUnfoldAnimatorBase(Context context, DisplayInsetsController displayInsetsController, ShellExecutor shellExecutor, ShellController shellController, UnfoldBackgroundController unfoldBackgroundController, Lazy lazy) {
        return new SplitTaskUnfoldAnimator(context, displayInsetsController, shellExecutor, shellController, unfoldBackgroundController, lazy);
    }
}
