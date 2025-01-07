package com.android.wm.shell.bubbles;

import android.service.notification.NotificationListenerService;
import com.android.wm.shell.bubbles.BubbleController;
import java.util.HashMap;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ Object f$2;

    public /* synthetic */ BubbleController$$ExternalSyntheticLambda6(Object obj, Object obj2, int i, Object obj3) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
        this.f$2 = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                BubbleController bubbleController = (BubbleController) this.f$0;
                List<BubbleEntry> list = (List) this.f$1;
                BubbleController.UserBubbleData userBubbleData = (BubbleController.UserBubbleData) this.f$2;
                bubbleController.getClass();
                for (BubbleEntry bubbleEntry : list) {
                    if (BubbleController.canLaunchInTaskView(bubbleController.mContext, bubbleEntry)) {
                        bubbleController.updateBubble(bubbleEntry, true, ((Boolean) userBubbleData.mKeyToShownInShadeMap.get(bubbleEntry.mSbn.getKey())).booleanValue());
                    }
                }
                break;
            default:
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) this.f$0;
                BubbleController.this.onRankingUpdated((NotificationListenerService.RankingMap) this.f$1, (HashMap) this.f$2);
                break;
        }
    }
}
