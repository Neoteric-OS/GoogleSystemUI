package com.android.wm.shell.displayareahelper;

import com.android.wm.shell.RootDisplayAreaOrganizer;
import com.android.wm.shell.common.ShellExecutor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplayAreaHelperController {
    public final ShellExecutor mExecutor;
    public final RootDisplayAreaOrganizer mRootDisplayAreaOrganizer;

    public DisplayAreaHelperController(ShellExecutor shellExecutor, RootDisplayAreaOrganizer rootDisplayAreaOrganizer) {
        this.mExecutor = shellExecutor;
        this.mRootDisplayAreaOrganizer = rootDisplayAreaOrganizer;
    }
}
