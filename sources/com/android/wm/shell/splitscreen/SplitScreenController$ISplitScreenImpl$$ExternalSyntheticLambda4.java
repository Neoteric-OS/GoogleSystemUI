package com.android.wm.shell.splitscreen;

import com.android.wm.shell.splitscreen.SplitScreenController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ SplitScreenController.ISplitScreenImpl f$0;

    public /* synthetic */ SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda4(SplitScreenController.ISplitScreenImpl iSplitScreenImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = iSplitScreenImpl;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        SplitScreenController.ISplitScreenImpl iSplitScreenImpl = this.f$0;
        SplitScreenController splitScreenController = (SplitScreenController) obj;
        switch (i) {
            case 0:
                splitScreenController.mStageCoordinator.mListeners.remove(iSplitScreenImpl.mSplitScreenListener);
                break;
            case 1:
                splitScreenController.mStageCoordinator.mSelectListeners.add(iSplitScreenImpl.mSplitSelectListener);
                break;
            case 2:
                splitScreenController.mStageCoordinator.mSelectListeners.remove(iSplitScreenImpl.mSplitSelectListener);
                break;
            case 3:
                iSplitScreenImpl.mListener.unregister();
                break;
            case 4:
                iSplitScreenImpl.mSelectListener.unregister();
                break;
            default:
                splitScreenController.registerSplitScreenListener(iSplitScreenImpl.mSplitScreenListener);
                break;
        }
    }
}
