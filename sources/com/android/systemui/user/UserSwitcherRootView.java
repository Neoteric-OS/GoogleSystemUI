package com.android.systemui.user;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.user.ui.binder.UserSwitcherViewBinder$bind$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitcherRootView extends ConstraintLayout {
    public UserSwitcherViewBinder$bind$1 touchHandler;

    public UserSwitcherRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        UserSwitcherViewBinder$bind$1 userSwitcherViewBinder$bind$1 = this.touchHandler;
        if (userSwitcherViewBinder$bind$1 != null) {
            userSwitcherViewBinder$bind$1.onTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
