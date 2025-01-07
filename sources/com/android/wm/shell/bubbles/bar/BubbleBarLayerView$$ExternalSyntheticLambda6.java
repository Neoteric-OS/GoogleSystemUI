package com.android.wm.shell.bubbles.bar;

import com.android.wm.shell.bubbles.BubbleData;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleBarLayerView$$ExternalSyntheticLambda6 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleBarLayerView f$0;

    public /* synthetic */ BubbleBarLayerView$$ExternalSyntheticLambda6(BubbleBarLayerView bubbleBarLayerView, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleBarLayerView;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                BubbleBarLayerView bubbleBarLayerView = this.f$0;
                BubbleEducationViewController bubbleEducationViewController = bubbleBarLayerView.mEducationViewController;
                BubbleBarLayerView$$ExternalSyntheticLambda6 bubbleBarLayerView$$ExternalSyntheticLambda6 = new BubbleBarLayerView$$ExternalSyntheticLambda6(bubbleBarLayerView, 1);
                bubbleEducationViewController.getClass();
                bubbleEducationViewController.animateTransition(new BubbleEducationViewController$hideEducation$3(bubbleEducationViewController, bubbleBarLayerView$$ExternalSyntheticLambda6), false);
                break;
            default:
                BubbleData bubbleData = this.f$0.mBubbleController.mBubbleData;
                if (bubbleData.mSelectedBubble != null) {
                    bubbleData.setExpanded(true);
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
