package com.android.wm.shell.bubbles;

import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$BubblesImpl$$ExternalSyntheticLambda15 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleController.BubblesImpl f$0;

    public /* synthetic */ BubbleController$BubblesImpl$$ExternalSyntheticLambda15(BubbleController.BubblesImpl bubblesImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = bubblesImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        BubbleController.BubblesImpl bubblesImpl = this.f$0;
        switch (i) {
            case 0:
                BubbleController bubbleController = BubbleController.this;
                if (bubbleController.hasBubbles() && ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 4892874825176268090L, 0, null);
                }
                for (Bubble bubble : Collections.unmodifiableList(bubbleController.mBubbleData.mBubbles)) {
                    bubble.setShowDot(bubble.showInShade());
                }
                break;
            default:
                BubbleController.this.collapseStack();
                break;
        }
    }
}
