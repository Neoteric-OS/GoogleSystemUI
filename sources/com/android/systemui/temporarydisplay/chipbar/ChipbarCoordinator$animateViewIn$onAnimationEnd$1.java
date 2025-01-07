package com.android.systemui.temporarydisplay.chipbar;

import android.view.ViewGroup;
import com.android.systemui.temporarydisplay.TemporaryViewDisplayController$removeViewFromWindow$1;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator;
import com.android.systemui.temporarydisplay.chipbar.ChipbarEndItem;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChipbarCoordinator$animateViewIn$onAnimationEnd$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object $view;
    public final /* synthetic */ ChipbarCoordinator this$0;

    public /* synthetic */ ChipbarCoordinator$animateViewIn$onAnimationEnd$1(ChipbarCoordinator chipbarCoordinator, Object obj, int i) {
        this.$r8$classId = i;
        this.this$0 = chipbarCoordinator;
        this.$view = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ChipbarCoordinator chipbarCoordinator = this.this$0;
                ChipbarInfo chipbarInfo = (ChipbarInfo) ((ViewGroup) this.$view).getTag(R.id.tag_chipbar_info);
                ViewGroup viewGroup = (ViewGroup) this.$view;
                chipbarCoordinator.getClass();
                if (!((chipbarInfo != null ? chipbarInfo.endItem : null) instanceof ChipbarEndItem.Button)) {
                    ((ViewGroup) viewGroup.requireViewById(R.id.chipbar_inner)).clearAccessibilityFocus();
                    break;
                } else {
                    ((ViewGroup) viewGroup.requireViewById(R.id.chipbar_inner)).requestAccessibilityFocus();
                    break;
                }
            default:
                ChipbarCoordinator chipbarCoordinator2 = this.this$0;
                ChipbarCoordinator.LoadingDetails loadingDetails = chipbarCoordinator2.loadingDetails;
                if (loadingDetails != null) {
                    loadingDetails.animator.cancel();
                }
                chipbarCoordinator2.loadingDetails = null;
                ((TemporaryViewDisplayController$removeViewFromWindow$1) this.$view).run();
                break;
        }
    }
}
