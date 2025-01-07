package com.android.systemui.globalactions;

import android.util.Log;
import android.view.View;
import com.android.systemui.globalactions.GlobalActionsDialogLite;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class GlobalActionsDialogLite$MyAdapter$$ExternalSyntheticLambda1 implements View.OnLongClickListener {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ GlobalActionsDialogLite$MyAdapter$$ExternalSyntheticLambda1(GlobalActionsDialogLite.MyAdapter myAdapter, int i) {
        this.f$0 = myAdapter;
        this.f$1 = i;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        switch (this.$r8$classId) {
            case 0:
                GlobalActionsDialogLite.MyAdapter myAdapter = (GlobalActionsDialogLite.MyAdapter) this.f$0;
                GlobalActionsDialogLite.Action item = myAdapter.this$0.mAdapter.getItem(this.f$1);
                if (!(item instanceof GlobalActionsDialogLite.LongPressAction)) {
                    return false;
                }
                GlobalActionsDialogLite globalActionsDialogLite = myAdapter.this$0;
                if (globalActionsDialogLite.mDialog != null) {
                    globalActionsDialogLite.mDialogTransitionAnimator.disableAllCurrentDialogsExitAnimations();
                    myAdapter.this$0.mDialog.dismiss();
                } else {
                    Log.w("GlobalActionsDialogLite", "Action long-clicked while mDialog is null.");
                }
                return ((GlobalActionsDialogLite.LongPressAction) item).onLongPress();
            default:
                GlobalActionsDialogLite.MyAdapter myAdapter2 = (GlobalActionsDialogLite.MyAdapter) this.f$0;
                GlobalActionsDialogLite.Action action = (GlobalActionsDialogLite.Action) myAdapter2.this$0.mPowerItems.get(this.f$1);
                if (!(action instanceof GlobalActionsDialogLite.LongPressAction)) {
                    return false;
                }
                GlobalActionsDialogLite globalActionsDialogLite2 = myAdapter2.this$0;
                if (globalActionsDialogLite2.mDialog != null) {
                    globalActionsDialogLite2.mDialogTransitionAnimator.disableAllCurrentDialogsExitAnimations();
                    myAdapter2.this$0.mDialog.dismiss();
                } else {
                    Log.w("GlobalActionsDialogLite", "Action long-clicked while mDialog is null.");
                }
                return ((GlobalActionsDialogLite.LongPressAction) action).onLongPress();
        }
    }

    public /* synthetic */ GlobalActionsDialogLite$MyAdapter$$ExternalSyntheticLambda1(GlobalActionsDialogLite.MyAdapter myAdapter, int i, byte b) {
        this.f$0 = myAdapter;
        this.f$1 = i;
    }
}
