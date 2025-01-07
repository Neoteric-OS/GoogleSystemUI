package com.android.wm.shell.dagger;

import android.content.Context;
import android.os.Handler;
import com.android.launcher3.icons.IconProvider;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayImeController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.LaunchAdjacentController;
import com.android.wm.shell.common.MultiInstanceHelper;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.draganddrop.DragAndDropController;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellModule_ProvideSplitScreenControllerFactory implements Provider {
    public static SplitScreenController provideSplitScreenController(Context context, ShellInit shellInit, ShellCommandHandler shellCommandHandler, ShellController shellController, ShellTaskOrganizer shellTaskOrganizer, SyncTransactionQueue syncTransactionQueue, DisplayController displayController, DisplayImeController displayImeController, DisplayInsetsController displayInsetsController, DragAndDropController dragAndDropController, Transitions transitions, TransactionPool transactionPool, IconProvider iconProvider, Optional optional, LaunchAdjacentController launchAdjacentController, Optional optional2, Optional optional3, MultiInstanceHelper multiInstanceHelper, ShellExecutor shellExecutor, Handler handler) {
        return new SplitScreenController(context, shellInit, shellCommandHandler, shellController, shellTaskOrganizer, syncTransactionQueue, displayController, displayImeController, displayInsetsController, dragAndDropController, transitions, transactionPool, iconProvider, optional, launchAdjacentController, optional2, optional3, multiInstanceHelper, shellExecutor, handler);
    }
}
