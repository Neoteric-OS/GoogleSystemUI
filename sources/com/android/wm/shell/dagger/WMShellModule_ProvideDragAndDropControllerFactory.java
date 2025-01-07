package com.android.wm.shell.dagger;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.launcher3.icons.IconProvider;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.draganddrop.DragAndDropController;
import com.android.wm.shell.draganddrop.GlobalDragListener;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellModule_ProvideDragAndDropControllerFactory implements Provider {
    public static DragAndDropController provideDragAndDropController(Context context, ShellInit shellInit, ShellController shellController, ShellCommandHandler shellCommandHandler, ShellTaskOrganizer shellTaskOrganizer, DisplayController displayController, UiEventLogger uiEventLogger, IconProvider iconProvider, GlobalDragListener globalDragListener, Transitions transitions, ShellExecutor shellExecutor) {
        return new DragAndDropController(context, shellInit, shellController, shellCommandHandler, shellTaskOrganizer, displayController, uiEventLogger, iconProvider, globalDragListener, transitions, shellExecutor);
    }
}
