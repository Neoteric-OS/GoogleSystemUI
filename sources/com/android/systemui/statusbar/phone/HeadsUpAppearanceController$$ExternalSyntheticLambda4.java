package com.android.systemui.statusbar.phone;

import com.android.internal.widget.ViewClippingUtil;
import com.android.systemui.statusbar.HeadsUpStatusBarView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class HeadsUpAppearanceController$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ HeadsUpAppearanceController f$0;

    public /* synthetic */ HeadsUpAppearanceController$$ExternalSyntheticLambda4(HeadsUpAppearanceController headsUpAppearanceController, int i) {
        this.$r8$classId = i;
        this.f$0 = headsUpAppearanceController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        HeadsUpAppearanceController headsUpAppearanceController = this.f$0;
        switch (i) {
            case 0:
                ViewClippingUtil.setClippingDeactivated(headsUpAppearanceController.mView, false, headsUpAppearanceController.mParentClippingParams);
                break;
            default:
                headsUpAppearanceController.mHeadsUpNotificationIconInteractor.repository.isolatedIconLocation.setValue(((HeadsUpStatusBarView) headsUpAppearanceController.mView).mIconDrawingRect);
                break;
        }
    }
}
