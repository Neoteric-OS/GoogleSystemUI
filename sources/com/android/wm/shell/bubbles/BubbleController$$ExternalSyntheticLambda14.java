package com.android.wm.shell.bubbles;

import android.os.SystemProperties;
import com.android.systemui.wmshell.BubblesManager;
import com.android.systemui.wmshell.BubblesManager$5$$ExternalSyntheticLambda2;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleData;
import com.android.wm.shell.bubbles.properties.ProdBubbleProperties;
import com.android.wm.shell.shared.bubbles.BubbleBarUpdate;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$$ExternalSyntheticLambda14 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ BubbleController$$ExternalSyntheticLambda14(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                BubblesManager.AnonymousClass5 anonymousClass5 = (BubblesManager.AnonymousClass5) obj2;
                anonymousClass5.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda2(anonymousClass5, (String) obj, 4));
                break;
            default:
                BubbleController bubbleController = (BubbleController) obj;
                BubbleController.AnonymousClass8 anonymousClass8 = ((BubbleController.IBubblesImpl) obj2).mBubbleListener;
                bubbleController.mBubbleProperties.getClass();
                ProdBubbleProperties._isBubbleBarEnabled = SystemProperties.getBoolean("persist.wm.debug.bubble_bar", false);
                if (!bubbleController.canShowAsBubbleBar() || anonymousClass8 == null) {
                    bubbleController.mBubbleStateListener = null;
                    break;
                } else {
                    bubbleController.mBubbleStateListener = anonymousClass8;
                    bubbleController.setUpBubbleViewsForMode();
                    if (bubbleController.mBubbleStateListener != null) {
                        BubbleData bubbleData = bubbleController.mBubbleData;
                        BubbleData.Update update = bubbleData.mStateChange;
                        update.getClass();
                        BubbleBarUpdate bubbleBarUpdate = new BubbleBarUpdate(true);
                        bubbleBarUpdate.shouldShowEducation = update.shouldShowEducation;
                        bubbleBarUpdate.showOverflow = true ^ update.overflowBubbles.isEmpty();
                        for (int i2 = 0; i2 < update.bubbles.size(); i2++) {
                            bubbleBarUpdate.currentBubbleList.add(((Bubble) update.bubbles.get(i2)).asBubbleBarBubble());
                        }
                        bubbleBarUpdate.bubbleBarLocation = bubbleData.mPositioner.mBubbleBarLocation;
                        boolean z = bubbleData.mExpanded;
                        bubbleBarUpdate.expanded = z;
                        bubbleBarUpdate.expandedChanged = z;
                        BubbleViewProvider bubbleViewProvider = bubbleData.mSelectedBubble;
                        bubbleBarUpdate.selectedBubbleKey = bubbleViewProvider != null ? bubbleViewProvider.getKey() : null;
                        bubbleController.mBubbleStateListener.onBubbleStateChange(bubbleBarUpdate);
                        break;
                    }
                }
                break;
        }
    }
}
