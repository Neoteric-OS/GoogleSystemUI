package com.android.wm.shell.bubbles;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda12 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleStackView f$0;

    public /* synthetic */ BubbleStackView$$ExternalSyntheticLambda12(BubbleStackView bubbleStackView, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleStackView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.$r8$classId;
        BubbleStackView bubbleStackView = this.f$0;
        switch (i) {
            case 0:
                if (bubbleStackView.mShowingManage) {
                    bubbleStackView.showManageMenu(false);
                } else if (bubbleStackView.isManageEduVisible()) {
                    bubbleStackView.mManageEduView.hide();
                } else if (bubbleStackView.isStackEduVisible()) {
                    bubbleStackView.mStackEduView.hide(false);
                } else {
                    BubbleData bubbleData = bubbleStackView.mBubbleData;
                    if (bubbleData.mExpanded) {
                        bubbleData.setExpanded(false);
                    } else {
                        bubbleStackView.maybeShowStackEdu();
                    }
                }
                bubbleStackView.mIsDraggingStack = false;
                bubbleStackView.mMagnetizedObject = null;
                break;
            case 1:
                bubbleStackView.showManageMenu(false);
                bubbleStackView.dismissBubbleIfExists(bubbleStackView.mBubbleData.mSelectedBubble);
                break;
            case 2:
                bubbleStackView.showManageMenu(false);
                bubbleStackView.mUnbubbleConversationCallback.accept(bubbleStackView.mBubbleData.mSelectedBubble.getKey());
                break;
            case 3:
                BubbleStackView.m898$r8$lambda$Pshq06SD_DiAmjLSXEQ3_CrIvE(bubbleStackView);
                break;
            default:
                BubbleData bubbleData2 = bubbleStackView.mBubbleData;
                bubbleData2.mShowingOverflow = true;
                bubbleData2.setSelectedBubbleInternal(bubbleStackView.mBubbleOverflow);
                bubbleData2.dispatchPendingChanges();
                bubbleStackView.mBubbleData.setExpanded(true);
                break;
        }
    }
}
