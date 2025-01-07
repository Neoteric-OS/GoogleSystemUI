package com.android.wm.shell.bubbles;

import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda40 implements Consumer {
    public final /* synthetic */ BubbleStackView f$0;

    public /* synthetic */ BubbleStackView$$ExternalSyntheticLambda40(BubbleStackView bubbleStackView) {
        this.f$0 = bubbleStackView;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        BubbleStackView bubbleStackView = this.f$0;
        bubbleStackView.getClass();
        if (((Boolean) obj).booleanValue() || !bubbleStackView.mIsExpanded) {
            return;
        }
        bubbleStackView.startMonitoringSwipeUpGesture();
    }
}
