package com.android.wm.shell.bubbles;

import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.util.CollectionUtils;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$6$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ BubbleController$6$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((BubbleController.AnonymousClass6) obj).this$0.collapseStack();
                break;
            case 1:
                BubbleController bubbleController = ((BubbleController.AnonymousClass6) obj).this$0;
                BubbleData bubbleData = bubbleController.mBubbleData;
                if (!bubbleData.mExpanded) {
                    if (bubbleData.mSelectedBubble == null) {
                        BubbleViewProvider bubbleViewProvider = (BubbleViewProvider) CollectionUtils.firstOrNull(Collections.unmodifiableList(bubbleData.mBubbles));
                        if (bubbleViewProvider == null) {
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[1]) {
                                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_BUBBLES, -4029904759595518332L, 0, null);
                            }
                            bubbleController.loadOverflowBubblesFromDisk();
                            bubbleViewProvider = bubbleData.mOverflow;
                        }
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[1]) {
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_BUBBLES, 5069015937721908995L, 0, String.valueOf(bubbleViewProvider.getKey()));
                        }
                        bubbleData.setSelectedBubbleAndExpandStack(bubbleViewProvider);
                        break;
                    } else {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[1]) {
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_BUBBLES, 4398841635101555077L, 0, null);
                        }
                        BubbleData bubbleData2 = bubbleController.mBubbleData;
                        if (bubbleData2.mSelectedBubble != null) {
                            bubbleData2.setExpanded(true);
                            break;
                        }
                    }
                } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_BUBBLES, 788200245784688848L, 0, null);
                    break;
                }
                break;
            case 2:
                BubbleController bubbleController2 = (BubbleController) ((BubbleController.AnonymousClass8) obj).this$0;
                if (bubbleController2.mBubbleData.mBubbles.isEmpty() && !bubbleController2.mBubbleData.mExpanded) {
                    bubbleController2.mLayerView.setVisibility(4);
                    bubbleController2.removeFromWindowManagerMaybe();
                    break;
                }
                break;
            default:
                ((SingleInstanceRemoteListener) obj).unregister();
                break;
        }
    }

    public /* synthetic */ BubbleController$6$$ExternalSyntheticLambda0(BubbleController.AnonymousClass6 anonymousClass6) {
        this.$r8$classId = 1;
        this.f$0 = anonymousClass6;
    }
}
