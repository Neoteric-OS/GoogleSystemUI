package com.android.systemui.accessibility.floatingmenu;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MenuViewLayer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MenuViewLayer f$0;

    public /* synthetic */ MenuViewLayer$$ExternalSyntheticLambda0(MenuViewLayer menuViewLayer, int i) {
        this.$r8$classId = i;
        this.f$0 = menuViewLayer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        MenuViewLayer menuViewLayer = this.f$0;
        switch (i) {
            case 0:
                menuViewLayer.mMenuView.setVisibility(8);
                break;
            case 1:
                menuViewLayer.mEduTooltipView.ifPresent(new MenuViewLayer$$ExternalSyntheticLambda9(menuViewLayer, 0));
                break;
            case 2:
                menuViewLayer.mMenuView.setVisibility(8);
                break;
            default:
                MenuViewLayer.$r8$lambda$hJVOPKG3uKij0Zkewl6JRXHaXWw(menuViewLayer);
                break;
        }
    }
}
