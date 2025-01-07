package com.android.wm.shell.bubbles;

import com.android.wm.shell.bubbles.BubbleStackView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ BubbleStackView$$ExternalSyntheticLambda7(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((BubbleStackView$$ExternalSyntheticLambda37) obj).accept(Boolean.TRUE);
                break;
            default:
                BubbleStackView bubbleStackView = ((BubbleStackView.AnonymousClass4) obj).this$0;
                bubbleStackView.mBubbleData.dismissAll(1);
                BubbleStackView.m900$$Nest$mresetDismissAnimator(bubbleStackView);
                break;
        }
    }
}
