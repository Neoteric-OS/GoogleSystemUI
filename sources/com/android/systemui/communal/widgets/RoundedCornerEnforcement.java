package com.android.systemui.communal.widgets;

import android.view.View;
import android.view.ViewGroup;
import com.android.wm.shell.R;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RoundedCornerEnforcement {
    public static void accumulateViewsWithId(View view, List list) {
        if (view.getId() == R.id.background) {
            list.add(view);
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                accumulateViewsWithId(viewGroup.getChildAt(i), list);
            }
        }
    }

    public static View findUndefinedBackground(View view) {
        View view2 = null;
        if (view.getVisibility() != 0) {
            return null;
        }
        if (view.getVisibility() == 0 && !(view.willNotDraw() && view.getForeground() == null && view.getBackground() == null)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View findUndefinedBackground = findUndefinedBackground(viewGroup.getChildAt(i));
                if (findUndefinedBackground != null) {
                    if (view2 != null) {
                        return view;
                    }
                    view2 = findUndefinedBackground;
                }
            }
        }
        return view2;
    }
}
