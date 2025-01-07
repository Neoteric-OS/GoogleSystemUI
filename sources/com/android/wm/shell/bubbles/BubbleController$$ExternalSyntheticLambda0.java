package com.android.wm.shell.bubbles;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda0;
import com.android.systemui.wmshell.BubblesManager;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.shared.bubbles.BubbleBarLocation;
import com.android.wm.shell.shared.bubbles.BubbleBarUpdate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ BubbleController$$ExternalSyntheticLambda0(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((BubbleStackView$$ExternalSyntheticLambda40) this.f$0).accept((Boolean) this.f$1);
                break;
            case 1:
                BubbleController bubbleController = (BubbleController) this.f$0;
                Bubble bubble = (Bubble) this.f$1;
                bubbleController.getClass();
                bubbleController.removeBubble(10, bubble.mKey);
                break;
            case 2:
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) this.f$0;
                BubbleController.this.setExpandListener((CentralSurfacesImpl$$ExternalSyntheticLambda0) this.f$1);
                break;
            case 3:
                BubbleController.this.mCurrentProfiles = (SparseArray) this.f$1;
                break;
            case 4:
                BubbleController.BubblesImpl bubblesImpl2 = (BubbleController.BubblesImpl) this.f$0;
                Bubble bubble2 = (Bubble) this.f$1;
                BubbleController bubbleController2 = BubbleController.this;
                if (bubble2 != null) {
                    BubbleData bubbleData = bubbleController2.mBubbleData;
                    String str = bubble2.mKey;
                    if (!bubbleData.hasBubbleInStackWithKey(str)) {
                        if (bubbleData.hasOverflowBubbleWithKey(str)) {
                            bubbleController2.promoteBubbleFromOverflow(bubble2);
                            break;
                        }
                    } else {
                        bubbleData.setSelectedBubbleAndExpandStack(bubble2);
                        break;
                    }
                } else {
                    bubbleController2.getClass();
                    break;
                }
                break;
            case 5:
                BubbleController.this.mSysuiProxy = (BubblesManager.AnonymousClass5) this.f$1;
                break;
            case 6:
                BubbleController.IBubblesImpl iBubblesImpl = (BubbleController.IBubblesImpl) this.f$0;
                iBubblesImpl.mController.setBubbleBarLocation((BubbleBarLocation) this.f$1);
                break;
            case 7:
                BubbleController.IBubblesImpl iBubblesImpl2 = (BubbleController.IBubblesImpl) this.f$0;
                String str2 = (String) this.f$1;
                BubbleController bubbleController3 = iBubblesImpl2.mController;
                if (bubbleController3.mBubbleData.mSelectedBubble != null) {
                    bubbleController3.mBubbleBarViewCallback.expansionChanged(false);
                }
                if (bubbleController3.mBubbleStateListener != null) {
                    boolean equals = "Overflow".equals(str2);
                    Rect rect = new Rect();
                    BubblePositioner bubblePositioner = bubbleController3.mBubblePositioner;
                    bubblePositioner.getBubbleBarExpandedViewBounds(bubblePositioner.isBubbleBarOnLeft(), equals, rect);
                    BubbleBarUpdate bubbleBarUpdate = new BubbleBarUpdate(false);
                    bubbleBarUpdate.expandedViewDropTargetSize = new Point(rect.width(), rect.height());
                    bubbleController3.mBubbleStateListener.onBubbleStateChange(bubbleBarUpdate);
                    break;
                }
                break;
            default:
                BubbleController.IBubblesImpl iBubblesImpl3 = (BubbleController.IBubblesImpl) this.f$0;
                iBubblesImpl3.mListener.register((IBubblesListener$Stub$Proxy) this.f$1);
                break;
        }
    }
}
