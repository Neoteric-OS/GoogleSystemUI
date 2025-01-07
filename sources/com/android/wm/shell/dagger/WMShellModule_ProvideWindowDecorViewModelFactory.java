package com.android.wm.shell.dagger;

import android.content.Context;
import android.os.Handler;
import android.view.Choreographer;
import android.view.IWindowManager;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.apptoweb.AppToWebGenericLinksParser;
import com.android.wm.shell.apptoweb.AssistContentRequester;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.MultiInstanceHelper;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.desktopmode.WindowDecorCaptionHandleRepository;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.CaptionWindowDecorViewModel;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel;
import com.android.wm.shell.windowdecor.WindowDecorViewModel;
import com.android.wm.shell.windowdecor.viewhost.WindowDecorViewHostSupplier;
import dagger.internal.Provider;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellModule_ProvideWindowDecorViewModelFactory implements Provider {
    public static WindowDecorViewModel provideWindowDecorViewModel(Context context, Handler handler, Choreographer choreographer, IWindowManager iWindowManager, InteractionJankMonitor interactionJankMonitor, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, ShellTaskOrganizer shellTaskOrganizer, AppToWebGenericLinksParser appToWebGenericLinksParser, AssistContentRequester assistContentRequester, DisplayController displayController, DisplayInsetsController displayInsetsController, MultiInstanceHelper multiInstanceHelper, ShellExecutor shellExecutor, ShellExecutor shellExecutor2, SyncTransactionQueue syncTransactionQueue, WindowDecorCaptionHandleRepository windowDecorCaptionHandleRepository, ShellCommandHandler shellCommandHandler, ShellController shellController, ShellInit shellInit, Transitions transitions, WindowDecorViewHostSupplier windowDecorViewHostSupplier, Optional optional, Optional optional2, Optional optional3) {
        return DesktopModeStatus.canEnterDesktopMode(context) ? new DesktopModeWindowDecorViewModel(context, handler, choreographer, iWindowManager, interactionJankMonitor, rootTaskDisplayAreaOrganizer, shellTaskOrganizer, appToWebGenericLinksParser, assistContentRequester, displayController, displayInsetsController, multiInstanceHelper, shellExecutor, shellExecutor2, syncTransactionQueue, windowDecorCaptionHandleRepository, shellCommandHandler, shellController, shellInit, transitions, windowDecorViewHostSupplier, optional, optional2, optional3) : new CaptionWindowDecorViewModel(context, handler, shellExecutor, shellExecutor2, choreographer, iWindowManager, shellInit, shellTaskOrganizer, displayController, rootTaskDisplayAreaOrganizer, syncTransactionQueue, transitions, windowDecorViewHostSupplier);
    }
}
