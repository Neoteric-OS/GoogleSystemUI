package com.android.wm.shell.bubbles;

import com.android.systemui.wmshell.BubblesManager;
import com.android.systemui.wmshell.BubblesManager$5$$ExternalSyntheticLambda2;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleData;
import java.util.List;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$BubblesImpl$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ BubbleController.BubblesImpl f$0;
    public final /* synthetic */ BubbleEntry f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ BubbleController$BubblesImpl$$ExternalSyntheticLambda0 f$3;

    public /* synthetic */ BubbleController$BubblesImpl$$ExternalSyntheticLambda1(BubbleController.BubblesImpl bubblesImpl, BubbleEntry bubbleEntry, List list, BubbleController$BubblesImpl$$ExternalSyntheticLambda0 bubbleController$BubblesImpl$$ExternalSyntheticLambda0) {
        this.f$0 = bubblesImpl;
        this.f$1 = bubbleEntry;
        this.f$2 = list;
        this.f$3 = bubbleController$BubblesImpl$$ExternalSyntheticLambda0;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        BubbleController.BubblesImpl bubblesImpl = this.f$0;
        BubbleEntry bubbleEntry = this.f$1;
        List list = this.f$2;
        BubbleController$BubblesImpl$$ExternalSyntheticLambda0 bubbleController$BubblesImpl$$ExternalSyntheticLambda0 = this.f$3;
        BubbleController bubbleController = BubbleController.this;
        boolean isSummaryOfBubbles = bubbleController.isSummaryOfBubbles(bubbleEntry);
        boolean z = true;
        BubbleData bubbleData = bubbleController.mBubbleData;
        if (isSummaryOfBubbles) {
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    BubbleEntry bubbleEntry2 = (BubbleEntry) list.get(i);
                    if (bubbleData.hasAnyBubbleWithKey(bubbleEntry2.mSbn.getKey())) {
                        Bubble anyBubbleWithkey = bubbleData.getAnyBubbleWithkey(bubbleEntry2.mSbn.getKey());
                        if (anyBubbleWithkey != null) {
                            anyBubbleWithkey.setSuppressNotification(true);
                            anyBubbleWithkey.setShowDot(false);
                        }
                    } else {
                        bubbleController$BubblesImpl$$ExternalSyntheticLambda0.accept(i);
                    }
                }
            }
            bubbleController$BubblesImpl$$ExternalSyntheticLambda0.accept(-1);
            String groupKey = bubbleEntry.mSbn.getGroupKey();
            bubbleData.mSuppressedGroupKeys.put(groupKey, bubbleEntry.mSbn.getKey());
            BubbleData.Update update = bubbleData.mStateChange;
            update.suppressedSummaryChanged = true;
            update.suppressedSummaryGroup = groupKey;
            bubbleData.dispatchPendingChanges();
        } else {
            Bubble bubbleInStackWithKey = bubbleData.getBubbleInStackWithKey(bubbleEntry.mSbn.getKey());
            if (bubbleInStackWithKey == null || !bubbleEntry.isBubble()) {
                bubbleInStackWithKey = bubbleData.getOverflowBubbleWithKey(bubbleEntry.mSbn.getKey());
            }
            if (bubbleInStackWithKey == null) {
                z = false;
                return Boolean.valueOf(z);
            }
            bubbleInStackWithKey.setSuppressNotification(true);
            bubbleInStackWithKey.setShowDot(false);
        }
        BubblesManager.AnonymousClass5 anonymousClass5 = bubbleController.mSysuiProxy;
        anonymousClass5.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda2(anonymousClass5, "BubbleController.handleDismissalInterception", 2));
        return Boolean.valueOf(z);
    }
}
