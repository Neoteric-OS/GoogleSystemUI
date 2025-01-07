package com.android.wm.shell.windowdecor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class WindowDecorLinearLayout extends LinearLayout implements TaskFocusStateConsumer {
    public static final int[] TASK_FOCUSED_STATE = {R.attr.state_task_focused};
    public boolean mIsTaskFocused;

    public WindowDecorLinearLayout(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        if (!this.mIsTaskFocused) {
            return super.onCreateDrawableState(i);
        }
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        LinearLayout.mergeDrawableStates(onCreateDrawableState, TASK_FOCUSED_STATE);
        return onCreateDrawableState;
    }

    public WindowDecorLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WindowDecorLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public WindowDecorLinearLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
