package com.android.systemui.navigationbar.views;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class NavigationBar$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ NavigationBar$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((NavigationBarView) obj).updateStates();
                break;
            case 1:
                ((NavigationBar) obj).mNavigationBarTransitions.setAutoDim(true);
                break;
            case 2:
                NavigationBarView navigationBarView = (NavigationBarView) ((NavigationBar) obj).mView;
                navigationBarView.mLayoutTransitionsEnabled = true;
                navigationBarView.updateLayoutTransitionsEnabled();
                break;
            default:
                NavigationBar navigationBar = (NavigationBar) obj;
                if (navigationBar.onHomeLongClick(((NavigationBarView) navigationBar.mView).getHomeButton().mCurrentView) && navigationBar.mHomeButtonLongPressHapticEnabled) {
                    ((NavigationBarView) navigationBar.mView).getHomeButton().mCurrentView.performHapticFeedback(0, 1);
                    break;
                }
                break;
        }
    }
}
