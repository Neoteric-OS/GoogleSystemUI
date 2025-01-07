package com.android.wm.shell.bubbles;

import android.util.Log;
import com.android.wm.shell.bubbles.BubbleViewInfoTask;
import com.android.wm.shell.bubbles.Bubbles;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$$ExternalSyntheticLambda7 implements BubbleViewInfoTask.Callback, Bubbles.BubbleMetadataFlagListener, Bubbles.PendingIntentCanceledListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleController f$0;

    public /* synthetic */ BubbleController$$ExternalSyntheticLambda7(BubbleController bubbleController, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleController;
    }

    @Override // com.android.wm.shell.bubbles.Bubbles.BubbleMetadataFlagListener
    public void onBubbleMetadataFlagChanged(Bubble bubble) {
        this.f$0.onBubbleMetadataFlagChanged(bubble);
    }

    @Override // com.android.wm.shell.bubbles.BubbleViewInfoTask.Callback
    public void onBubbleViewsReady(Bubble bubble) {
        switch (this.$r8$classId) {
            case 1:
                BubbleController bubbleController = this.f$0;
                BubbleStackView bubbleStackView = bubbleController.mStackView;
                if (bubbleStackView == null) {
                    Log.w("Bubbles", "Tried to add a bubble to the stack but the stack is null");
                    break;
                } else {
                    bubbleStackView.addBubble(bubble);
                    bubbleController.mStackView.setSelectedBubble(bubble);
                    break;
                }
            default:
                BubbleController bubbleController2 = this.f$0;
                bubbleController2.getClass();
                String str = bubble.mKey;
                BubbleViewProvider bubbleViewProvider = bubbleController2.mBubbleData.mSelectedBubble;
                if (str.equals(bubbleViewProvider != null ? bubbleViewProvider.getKey() : null)) {
                    bubbleController2.mLayerView.showExpandedView(bubble);
                    break;
                }
                break;
        }
    }
}
