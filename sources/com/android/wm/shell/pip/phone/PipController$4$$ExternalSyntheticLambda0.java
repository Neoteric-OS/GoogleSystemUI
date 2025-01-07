package com.android.wm.shell.pip.phone;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipController$4$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ PipController$4$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                PipController pipController = PipController.this;
                pipController.onDisplayChangedUncheck(pipController.mDisplayController.getDisplayLayout(pipController.mPipDisplayLayoutState.mDisplayId), false);
                break;
            default:
                PipTouchHandler pipTouchHandler = PipController.this.mTouchHandler;
                if (!pipTouchHandler.mTouchState.mIsUserInteracting) {
                    pipTouchHandler.mMenuController.showMenuInternal(1, pipTouchHandler.mPipBoundsState.getBounds(), false, pipTouchHandler.willResizeMenu(), false);
                    break;
                }
                break;
        }
    }
}
