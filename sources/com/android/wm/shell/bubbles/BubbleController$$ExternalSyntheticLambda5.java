package com.android.wm.shell.bubbles;

import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.common.HandlerExecutor;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleController f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ BubbleController$$ExternalSyntheticLambda5(BubbleController bubbleController, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleController;
        this.f$1 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                BubbleController bubbleController = this.f$0;
                ((HandlerExecutor) bubbleController.mMainExecutor).execute(new BubbleController$$ExternalSyntheticLambda6(bubbleController, (List) obj, 0, (BubbleController.UserBubbleData) this.f$1));
                break;
            default:
                BubbleController bubbleController2 = this.f$0;
                bubbleController2.getClass();
                ((HandlerExecutor) bubbleController2.mMainExecutor).execute(new BubbleController$$ExternalSyntheticLambda0(0, (BubbleStackView$$ExternalSyntheticLambda40) this.f$1, (Boolean) obj));
                break;
        }
    }
}
