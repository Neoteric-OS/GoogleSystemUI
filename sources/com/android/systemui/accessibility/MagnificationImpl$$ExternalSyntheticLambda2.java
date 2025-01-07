package com.android.systemui.accessibility;

import com.android.systemui.accessibility.MagnificationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MagnificationImpl$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ MagnificationImpl$$ExternalSyntheticLambda2(int i, int i2, Object obj) {
        this.$r8$classId = i2;
        this.f$0 = obj;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((MagnificationImpl) this.f$0).toggleSettingsPanelVisibility(this.f$1);
                break;
            case 1:
                MagnificationImpl.AnonymousClass3 anonymousClass3 = (MagnificationImpl.AnonymousClass3) this.f$0;
                WindowMagnificationController windowMagnificationController = (WindowMagnificationController) MagnificationImpl.this.mWindowMagnificationControllerSupplier.get(this.f$1);
                if (windowMagnificationController != null && windowMagnificationController.isActivated()) {
                    windowMagnificationController.setEditMagnifierSizeMode(true);
                    break;
                }
                break;
            default:
                MagnificationImpl.AnonymousClass3 anonymousClass32 = (MagnificationImpl.AnonymousClass3) this.f$0;
                MagnificationImpl.this.toggleSettingsPanelVisibility(this.f$1);
                break;
        }
    }
}
