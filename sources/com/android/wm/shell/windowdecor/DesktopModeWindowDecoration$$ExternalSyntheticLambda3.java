package com.android.wm.shell.windowdecor;

import com.android.wm.shell.shared.desktopmode.DesktopModeTransitionSource;
import com.android.wm.shell.windowdecor.viewholder.AppHeaderViewHolder;
import com.android.wm.shell.windowdecor.viewholder.AppHeaderViewHolder$onHandleMenuClosed$1;
import com.android.wm.shell.windowdecor.viewholder.WindowDecorationViewHolder;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DesktopModeWindowDecoration$$ExternalSyntheticLambda3 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DesktopModeWindowDecoration f$0;

    public /* synthetic */ DesktopModeWindowDecoration$$ExternalSyntheticLambda3(DesktopModeWindowDecoration desktopModeWindowDecoration, int i) {
        this.$r8$classId = i;
        this.f$0 = desktopModeWindowDecoration;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                WindowDecorationViewHolder windowDecorationViewHolder = this.f$0.mWindowDecorViewHolder;
                AppHeaderViewHolder appHeaderViewHolder = windowDecorationViewHolder instanceof AppHeaderViewHolder ? (AppHeaderViewHolder) windowDecorationViewHolder : null;
                if (appHeaderViewHolder != null) {
                    appHeaderViewHolder.maximizeWindowButton.post(new AppHeaderViewHolder$onHandleMenuClosed$1(appHeaderViewHolder, 1));
                }
                break;
            case 1:
                this.f$0.closeManageWindowsMenu();
                break;
            case 2:
                this.f$0.closeManageWindowsMenu();
                break;
            case 3:
                this.f$0.closeMaximizeMenu();
                break;
            case 4:
                DesktopModeWindowDecoration desktopModeWindowDecoration = this.f$0;
                DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda6 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda6 = desktopModeWindowDecoration.mOnToDesktopClickListener;
                DesktopModeTransitionSource desktopModeTransitionSource = DesktopModeTransitionSource.APP_HANDLE_MENU_BUTTON;
                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda6.accept(desktopModeTransitionSource);
                desktopModeWindowDecoration.mOnToDesktopClickListener.accept(desktopModeTransitionSource);
                break;
            case 5:
                this.f$0.closeHandleMenu();
                break;
            case 6:
                this.f$0.closeHandleMenu();
                break;
            default:
                DesktopModeWindowDecoration desktopModeWindowDecoration2 = this.f$0;
                if (!desktopModeWindowDecoration2.isMaximizeMenuActive()) {
                    desktopModeWindowDecoration2.createMaximizeMenu();
                }
                break;
        }
        return Unit.INSTANCE;
    }
}
