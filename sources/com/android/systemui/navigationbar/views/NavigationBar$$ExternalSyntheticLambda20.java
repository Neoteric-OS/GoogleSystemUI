package com.android.systemui.navigationbar.views;

import com.android.wm.shell.back.BackAnimationController;
import com.android.wm.shell.pip.Pip;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class NavigationBar$$ExternalSyntheticLambda20 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NavigationBarView f$0;

    public /* synthetic */ NavigationBar$$ExternalSyntheticLambda20(NavigationBarView navigationBarView, int i) {
        this.$r8$classId = i;
        this.f$0 = navigationBarView;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        NavigationBarView navigationBarView = this.f$0;
        switch (i) {
            case 0:
                ((Pip) obj).addPipExclusionBoundsChangeListener(navigationBarView.mPipListener);
                break;
            case 1:
                navigationBarView.mEdgeBackGestureHandler.setBackAnimation((BackAnimationController.BackAnimationImpl) obj);
                break;
            default:
                ((Pip) obj).removePipExclusionBoundsChangeListener(navigationBarView.mPipListener);
                break;
        }
    }
}
