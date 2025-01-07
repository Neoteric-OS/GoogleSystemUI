package com.android.wm.shell.bubbles;

import android.content.Context;
import com.android.launcher3.icons.BubbleIconFactory;
import com.android.wm.shell.bubbles.BubbleController.AnonymousClass2;
import com.android.wm.shell.bubbles.BubbleViewInfoTask;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;
import com.android.wm.shell.onehanded.OneHandedController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$$ExternalSyntheticLambda11 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleController f$0;

    public /* synthetic */ BubbleController$$ExternalSyntheticLambda11(BubbleController bubbleController, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        final BubbleController bubbleController = this.f$0;
        switch (i) {
            case 0:
                bubbleController.getClass();
                ((OneHandedController) obj).mDisplayAreaOrganizer.mTransitionCallbacks.add(bubbleController.new AnonymousClass2());
                break;
            default:
                final Bubble bubble = (Bubble) obj;
                bubbleController.getClass();
                if (!bubbleController.mBubbleData.hasAnyBubbleWithKey(bubble.mKey)) {
                    BubbleViewInfoTask.Callback callback = new BubbleViewInfoTask.Callback() { // from class: com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda26
                        @Override // com.android.wm.shell.bubbles.BubbleViewInfoTask.Callback
                        public final void onBubbleViewsReady(Bubble bubble2) {
                            BubbleController.this.mBubbleData.overflowBubble(15, bubble);
                        }
                    };
                    Context context = bubbleController.mContext;
                    BubbleStackView bubbleStackView = bubbleController.mStackView;
                    BubbleBarLayerView bubbleBarLayerView = bubbleController.mLayerView;
                    BubbleIconFactory bubbleIconFactory = bubbleController.mBubbleIconFactory;
                    bubble.inflate(callback, context, bubbleController.mExpandedViewManager, bubbleController.mBubbleTaskViewFactory, bubbleController.mBubblePositioner, bubbleStackView, bubbleBarLayerView, bubbleIconFactory, true);
                    break;
                }
                break;
        }
    }
}
