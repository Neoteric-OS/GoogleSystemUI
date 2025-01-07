package com.android.wm.shell.bubbles;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleExpandedView$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ BubbleExpandedView f$0;

    public /* synthetic */ BubbleExpandedView$$ExternalSyntheticLambda1(BubbleExpandedView bubbleExpandedView) {
        this.f$0 = bubbleExpandedView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.f$0.mStackView.showManageMenu(true);
    }
}
