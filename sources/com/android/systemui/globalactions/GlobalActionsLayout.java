package com.android.systemui.globalactions;

import android.R;
import android.app.Dialog;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.constraintlayout.helper.widget.Flow;
import com.android.systemui.globalactions.GlobalActionsDialogLite;
import com.android.systemui.util.leak.RotationUtils;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class GlobalActionsLayout extends LinearLayout {
    public GlobalActionsDialogLite.MyAdapter mAdapter;
    public boolean mBackgroundsSet;
    public int mRotation;
    public GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda0 mRotationListener;

    public int getCurrentLayoutDirection() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
    }

    public int getCurrentRotation() {
        return RotationUtils.getRotation(((LinearLayout) this).mContext);
    }

    public final ViewGroup getListView() {
        return (ViewGroup) findViewById(R.id.list);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int rotation = RotationUtils.getRotation(((LinearLayout) this).mContext);
        if (rotation != this.mRotation) {
            GlobalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda0 globalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda0 = this.mRotationListener;
            if (globalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda0 != null) {
                GlobalActionsDialogLite.ActionsDialogLite actionsDialogLite = globalActionsDialogLite$ActionsDialogLite$$ExternalSyntheticLambda0.f$0;
                actionsDialogLite.mOnRefreshCallback.run();
                GlobalActionsPopupMenu globalActionsPopupMenu = actionsDialogLite.mOverflowPopup;
                if (globalActionsPopupMenu != null) {
                    globalActionsPopupMenu.dismiss();
                }
                Dialog dialog = actionsDialogLite.mPowerOptionsDialog;
                if (dialog != null) {
                    dialog.dismiss();
                }
                actionsDialogLite.mGlobalActionsLayout.updateList();
            }
            this.mRotation = rotation;
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getListView() == null || this.mBackgroundsSet) {
            return;
        }
        getListView();
        getResources().getColor(com.android.wm.shell.R.color.global_actions_grid_background, null);
        if (((ViewGroup) findViewById(com.android.wm.shell.R.id.separated_button)) != null) {
            getResources().getColor(com.android.wm.shell.R.color.global_actions_separated_background, null);
        }
        this.mBackgroundsSet = true;
    }

    public final void updateList() {
        if (this.mAdapter == null) {
            throw new IllegalStateException("mAdapter must be set before calling updateList");
        }
        GlobalActionsLayoutLite globalActionsLayoutLite = (GlobalActionsLayoutLite) this;
        View findViewById = globalActionsLayoutLite.findViewById(com.android.wm.shell.R.id.list_flow);
        ViewGroup listView = globalActionsLayoutLite.getListView();
        if (listView != null) {
            listView.removeAllViews();
        }
        globalActionsLayoutLite.getListView().addView(findViewById);
        ViewGroup viewGroup = (ViewGroup) globalActionsLayoutLite.findViewById(com.android.wm.shell.R.id.separated_button);
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        boolean z = globalActionsLayoutLite.mAdapter.countItems(true) > 0;
        ViewGroup viewGroup2 = (ViewGroup) globalActionsLayoutLite.findViewById(com.android.wm.shell.R.id.separated_button);
        if (viewGroup2 != null) {
            viewGroup2.setVisibility(z ? 0 : 8);
        }
        ViewGroup listView2 = globalActionsLayoutLite.getListView();
        for (int i = 0; i < globalActionsLayoutLite.mAdapter.getCount(); i++) {
            globalActionsLayoutLite.mAdapter.getItem(i).getClass();
            View view = globalActionsLayoutLite.mAdapter.getView(i, null, listView2);
            if (globalActionsLayoutLite.shouldReverseListItems()) {
                globalActionsLayoutLite.getListView().addView(view, 0);
            } else {
                globalActionsLayoutLite.getListView().addView(view);
            }
            ((Flow) globalActionsLayoutLite.findViewById(com.android.wm.shell.R.id.list_flow)).addView(view);
        }
        int integer = globalActionsLayoutLite.getResources().getInteger(com.android.wm.shell.R.integer.power_menu_lite_max_columns);
        if (globalActionsLayoutLite.getListView().getChildCount() - 1 == 1 + integer && integer > 2) {
            integer--;
        }
        Flow flow = (Flow) globalActionsLayoutLite.findViewById(com.android.wm.shell.R.id.list_flow);
        flow.mFlow.mMaxElementsWrap = integer;
        flow.requestLayout();
    }
}
