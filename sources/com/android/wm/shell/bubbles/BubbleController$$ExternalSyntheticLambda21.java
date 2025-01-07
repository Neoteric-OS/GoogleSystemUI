package com.android.wm.shell.bubbles;

import android.os.SystemProperties;
import com.android.wm.shell.bubbles.properties.ProdBubbleProperties;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$$ExternalSyntheticLambda21 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ BubbleController$$ExternalSyntheticLambda21(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                Bubble bubble = (Bubble) obj;
                bubble.cleanupExpandedView(false);
                bubble.mIconView = null;
                break;
            default:
                BubbleController bubbleController = (BubbleController) obj;
                bubbleController.mBubbleProperties.getClass();
                ProdBubbleProperties._isBubbleBarEnabled = SystemProperties.getBoolean("persist.wm.debug.bubble_bar", false);
                if (bubbleController.mBubbleStateListener != null) {
                    bubbleController.mBubbleStateListener = null;
                    bubbleController.setUpBubbleViewsForMode();
                    break;
                }
                break;
        }
    }
}
