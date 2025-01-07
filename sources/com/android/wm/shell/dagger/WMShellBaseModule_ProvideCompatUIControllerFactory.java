package com.android.wm.shell.dagger;

import android.content.Context;
import android.view.accessibility.AccessibilityManager;
import com.android.wm.shell.R;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayImeController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.DockStateReader;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.compatui.CompatUIConfiguration;
import com.android.wm.shell.compatui.CompatUIController;
import com.android.wm.shell.compatui.CompatUIShellCommandHandler;
import com.android.wm.shell.compatui.CompatUIStatusManager;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import dagger.Lazy;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;
import java.util.function.IntPredicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideCompatUIControllerFactory implements Provider {
    public static Optional provideCompatUIController(Context context, ShellInit shellInit, ShellController shellController, DisplayController displayController, DisplayInsetsController displayInsetsController, DisplayImeController displayImeController, SyncTransactionQueue syncTransactionQueue, ShellExecutor shellExecutor, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Optional optional, CompatUIStatusManager compatUIStatusManager) {
        Optional of;
        if (context.getResources().getBoolean(R.bool.config_enableCompatUIController)) {
            of = Optional.of(new CompatUIController(context, shellInit, shellController, displayController, displayInsetsController, displayImeController, syncTransactionQueue, shellExecutor, lazy, (DockStateReader) lazy2.get(), (CompatUIConfiguration) lazy3.get(), (CompatUIShellCommandHandler) lazy4.get(), (AccessibilityManager) lazy5.get(), compatUIStatusManager, (IntPredicate) optional.map(new WMShellBaseModule$$ExternalSyntheticLambda1(6)).orElseGet(new WMShellBaseModule$$ExternalSyntheticLambda9())));
        } else {
            of = Optional.empty();
        }
        Preconditions.checkNotNullFromProvides(of);
        return of;
    }
}
