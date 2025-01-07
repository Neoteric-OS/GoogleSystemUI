package com.android.wm.shell.bubbles.bar;

import com.android.wm.shell.bubbles.Bubble;
import com.android.wm.shell.bubbles.BubbleController$6$$ExternalSyntheticLambda0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleBarLayerView$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ Bubble f$0;
    public final /* synthetic */ BubbleController$6$$ExternalSyntheticLambda0 f$1;

    public /* synthetic */ BubbleBarLayerView$$ExternalSyntheticLambda7(Bubble bubble, BubbleController$6$$ExternalSyntheticLambda0 bubbleController$6$$ExternalSyntheticLambda0) {
        this.f$0 = bubble;
        this.f$1 = bubbleController$6$$ExternalSyntheticLambda0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bubble bubble = this.f$0;
        BubbleController$6$$ExternalSyntheticLambda0 bubbleController$6$$ExternalSyntheticLambda0 = this.f$1;
        bubble.cleanupViews();
        bubbleController$6$$ExternalSyntheticLambda0.run();
    }
}
