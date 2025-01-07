package com.android.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.row.ExpandableView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StackStateAnimator$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ExpandableView f$0;

    public /* synthetic */ StackStateAnimator$$ExternalSyntheticLambda1(ExpandableView expandableView, int i) {
        this.$r8$classId = i;
        this.f$0 = expandableView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        ExpandableView expandableView = this.f$0;
        switch (i) {
            case 0:
                expandableView.mInRemovalAnimation = true;
                break;
            case 1:
                expandableView.removeFromTransientContainer();
                break;
            case 2:
                expandableView.mInRemovalAnimation = true;
                break;
            case 3:
                expandableView.mInRemovalAnimation = true;
                break;
            default:
                expandableView.mInRemovalAnimation = false;
                expandableView.removeFromTransientContainer();
                break;
        }
    }
}
