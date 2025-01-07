package com.android.wm.shell.pip2.phone;

import com.android.wm.shell.pip2.phone.PipTouchHandler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipTouchHandler$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ PipTouchHandler$$ExternalSyntheticLambda4(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((PhonePipMenuController) obj).hideMenu();
                break;
            default:
                PipTouchHandler.DefaultPipTouchGesture defaultPipTouchGesture = (PipTouchHandler.DefaultPipTouchGesture) obj;
                if (defaultPipTouchGesture.mShouldHideMenuAfterFling) {
                    defaultPipTouchGesture.this$0.mMenuController.hideMenu();
                    break;
                }
                break;
        }
    }
}
