package com.android.wm.shell.bubbles;

import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleData;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$BubblesImpl$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleController.BubblesImpl f$0;
    public final /* synthetic */ BubbleEntry f$1;

    public /* synthetic */ BubbleController$BubblesImpl$$ExternalSyntheticLambda5(BubbleController.BubblesImpl bubblesImpl, BubbleEntry bubbleEntry, int i) {
        this.$r8$classId = i;
        this.f$0 = bubblesImpl;
        this.f$1 = bubbleEntry;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                BubbleController.BubblesImpl bubblesImpl = this.f$0;
                BubbleController.this.expandStackAndSelectBubble(this.f$1);
                break;
            case 1:
                BubbleController.BubblesImpl bubblesImpl2 = this.f$0;
                BubbleEntry bubbleEntry = this.f$1;
                BubbleController bubbleController = BubbleController.this;
                if (BubbleController.canLaunchInTaskView(bubbleController.mContext, bubbleEntry)) {
                    bubbleController.updateBubble(bubbleEntry);
                    break;
                }
                break;
            default:
                BubbleController.BubblesImpl bubblesImpl3 = this.f$0;
                BubbleEntry bubbleEntry2 = this.f$1;
                BubbleController bubbleController2 = BubbleController.this;
                if (bubbleController2.isSummaryOfBubbles(bubbleEntry2)) {
                    String groupKey = bubbleEntry2.mSbn.getGroupKey();
                    BubbleData bubbleData = bubbleController2.mBubbleData;
                    bubbleData.mSuppressedGroupKeys.remove(groupKey);
                    BubbleData.Update update = bubbleData.mStateChange;
                    update.suppressedSummaryChanged = true;
                    update.suppressedSummaryGroup = groupKey;
                    bubbleData.dispatchPendingChanges();
                    ArrayList bubblesInGroup = bubbleController2.getBubblesInGroup(groupKey);
                    for (int i = 0; i < bubblesInGroup.size(); i++) {
                        bubbleController2.removeBubble(9, ((Bubble) bubblesInGroup.get(i)).mKey);
                    }
                    break;
                } else {
                    bubbleController2.removeBubble(5, bubbleEntry2.mSbn.getKey());
                    break;
                }
        }
    }
}
