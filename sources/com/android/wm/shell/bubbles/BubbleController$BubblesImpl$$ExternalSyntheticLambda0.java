package com.android.wm.shell.bubbles;

import com.android.systemui.wmshell.BubblesManager$$ExternalSyntheticLambda2;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$BubblesImpl$$ExternalSyntheticLambda0 implements IntConsumer {
    public final /* synthetic */ Executor f$0;
    public final /* synthetic */ BubblesManager$$ExternalSyntheticLambda2 f$1;

    public /* synthetic */ BubbleController$BubblesImpl$$ExternalSyntheticLambda0(Executor executor, BubblesManager$$ExternalSyntheticLambda2 bubblesManager$$ExternalSyntheticLambda2) {
        this.f$0 = executor;
        this.f$1 = bubblesManager$$ExternalSyntheticLambda2;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        this.f$0.execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda2(i, 0, this.f$1));
    }
}
