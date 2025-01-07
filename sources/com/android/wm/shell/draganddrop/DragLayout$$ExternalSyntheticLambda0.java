package com.android.wm.shell.draganddrop;

import android.view.DragEvent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DragLayout$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DragLayout f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ DragEvent f$2;

    public /* synthetic */ DragLayout$$ExternalSyntheticLambda0(DragLayout dragLayout, Runnable runnable, DragEvent dragEvent) {
        this.f$0 = dragLayout;
        this.f$1 = runnable;
        this.f$2 = dragEvent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DragLayout dragLayout = this.f$0;
        Runnable runnable = this.f$1;
        DragEvent dragEvent = this.f$2;
        dragLayout.getClass();
        if (runnable != null) {
            runnable.run();
        }
        int action = dragEvent.getAction();
        if (action == 3 || action == 4) {
            dragLayout.mSession = null;
        }
    }
}
