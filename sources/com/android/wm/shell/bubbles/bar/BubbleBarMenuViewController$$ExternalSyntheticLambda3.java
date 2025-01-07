package com.android.wm.shell.bubbles.bar;

import com.android.wm.shell.bubbles.bar.BubbleBarExpandedView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleBarMenuViewController$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleBarMenuViewController f$0;

    public /* synthetic */ BubbleBarMenuViewController$$ExternalSyntheticLambda3(BubbleBarMenuViewController bubbleBarMenuViewController, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleBarMenuViewController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        BubbleBarMenuViewController bubbleBarMenuViewController = this.f$0;
        switch (i) {
            case 0:
                bubbleBarMenuViewController.mMenuView.getChildAt(0).requestAccessibilityFocus();
                BubbleBarExpandedView.AnonymousClass3 anonymousClass3 = bubbleBarMenuViewController.mListener;
                if (anonymousClass3 != null) {
                    anonymousClass3.onMenuVisibilityChanged(true);
                    break;
                }
                break;
            case 1:
                bubbleBarMenuViewController.mMenuView.setVisibility(8);
                bubbleBarMenuViewController.mScrimView.setVisibility(8);
                BubbleBarExpandedView.AnonymousClass3 anonymousClass32 = bubbleBarMenuViewController.mListener;
                if (anonymousClass32 != null) {
                    anonymousClass32.onMenuVisibilityChanged(false);
                    break;
                }
                break;
            default:
                bubbleBarMenuViewController.hideMenu(true);
                break;
        }
    }
}
