package com.android.wm.shell.windowdecor.viewholder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AppHeaderViewHolder$onHandleMenuClosed$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AppHeaderViewHolder this$0;

    public /* synthetic */ AppHeaderViewHolder$onHandleMenuClosed$1(AppHeaderViewHolder appHeaderViewHolder, int i) {
        this.$r8$classId = i;
        this.this$0 = appHeaderViewHolder;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.openMenuButton.sendAccessibilityEvent(8);
                break;
            default:
                this.this$0.maximizeWindowButton.sendAccessibilityEvent(8);
                break;
        }
    }
}
