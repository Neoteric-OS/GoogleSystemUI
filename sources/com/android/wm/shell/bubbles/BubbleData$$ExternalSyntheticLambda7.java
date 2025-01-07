package com.android.wm.shell.bubbles;

import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleData$$ExternalSyntheticLambda7 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ BubbleData$$ExternalSyntheticLambda7(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                BubbleData bubbleData = (BubbleData) obj2;
                bubbleData.getClass();
                bubbleData.doRemove(2, ((Bubble) obj).mKey);
                break;
            case 1:
                BubbleData bubbleData2 = (BubbleData) obj2;
                bubbleData2.getClass();
                bubbleData2.dismissBubbleWithKey(12, ((Bubble) obj).mKey);
                break;
            case 2:
                BubbleData bubbleData3 = (BubbleData) obj2;
                bubbleData3.getClass();
                bubbleData3.dismissBubbleWithKey(13, ((Bubble) obj).mKey);
                break;
            default:
                ((List) obj2).add((Bubble) obj);
                break;
        }
    }
}
