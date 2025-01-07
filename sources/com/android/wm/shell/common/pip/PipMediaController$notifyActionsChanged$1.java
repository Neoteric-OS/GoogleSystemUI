package com.android.wm.shell.common.pip;

import com.android.wm.shell.common.pip.PipMediaController;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipMediaController$notifyActionsChanged$1 implements Consumer {
    public final /* synthetic */ List $actions;

    public PipMediaController$notifyActionsChanged$1(List list) {
        this.$actions = list;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((PipMediaController.ActionListener) obj).onMediaActionsChanged(this.$actions);
    }
}
