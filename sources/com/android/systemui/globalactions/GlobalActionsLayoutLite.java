package com.android.systemui.globalactions;

import android.content.Context;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.systemui.util.leak.RotationUtils;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class GlobalActionsLayoutLite extends GlobalActionsLayout {
    public static final /* synthetic */ int $r8$clinit = 0;

    public GlobalActionsLayoutLite(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRotation = RotationUtils.getRotation(context);
        setOnClickListener(new GlobalActionsLayoutLite$$ExternalSyntheticLambda0());
    }

    public float getAnimationDistance() {
        return getGridItemSize() / 2.0f;
    }

    public float getGridItemSize() {
        return getContext().getResources().getDimension(R.dimen.global_actions_grid_item_height);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TextView textView;
        Layout layout;
        super.onLayout(z, i, i2, i3, i4);
        ViewGroup listView = getListView();
        boolean z2 = false;
        for (int i5 = 0; i5 < listView.getChildCount(); i5++) {
            View childAt = listView.getChildAt(i5);
            if (childAt instanceof GlobalActionsItem) {
                z2 = z2 || ((textView = (TextView) ((GlobalActionsItem) childAt).findViewById(android.R.id.message)) != null && (layout = textView.getLayout()) != null && layout.getLineCount() > 0 && layout.getEllipsisCount(layout.getLineCount() - 1) > 0);
            }
        }
        if (z2) {
            for (int i6 = 0; i6 < listView.getChildCount(); i6++) {
                View childAt2 = listView.getChildAt(i6);
                if (childAt2 instanceof GlobalActionsItem) {
                    TextView textView2 = (TextView) ((GlobalActionsItem) childAt2).findViewById(android.R.id.message);
                    textView2.setSingleLine(true);
                    textView2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                }
            }
        }
    }

    public boolean shouldReverseListItems() {
        return false;
    }
}
