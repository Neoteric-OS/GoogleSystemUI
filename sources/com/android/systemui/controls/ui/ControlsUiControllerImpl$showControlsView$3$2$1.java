package com.android.systemui.controls.ui;

import android.widget.ListView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsUiControllerImpl$showControlsView$3$2$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ ControlsUiControllerImpl$showControlsView$3$2$1(int i, Object obj) {
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                OverflowMenuAdapter overflowMenuAdapter = ((ControlsUiControllerImpl) this.this$0).overflowMenuAdapter;
                if (overflowMenuAdapter != null) {
                    overflowMenuAdapter.notifyDataSetChanged();
                    break;
                }
                break;
            case 1:
                ListView listView = ((ControlsPopupMenu) this.this$0).getListView();
                if (listView != null) {
                    listView.requestAccessibilityFocus();
                    break;
                }
                break;
            default:
                ListView listView2 = ((ControlsPopupMenu) this.this$0).getListView();
                if (listView2 != null) {
                    listView2.requestAccessibilityFocus();
                    break;
                }
                break;
        }
    }
}
