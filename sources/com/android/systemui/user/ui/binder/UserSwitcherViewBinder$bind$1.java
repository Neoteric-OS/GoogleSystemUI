package com.android.systemui.user.ui.binder;

import android.view.MotionEvent;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.classifier.FalsingCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserSwitcherViewBinder$bind$1 implements Gefingerpoken {
    public final /* synthetic */ FalsingCollector $falsingCollector;

    public UserSwitcherViewBinder$bind$1(FalsingCollector falsingCollector) {
        this.$falsingCollector = falsingCollector;
    }

    @Override // com.android.systemui.Gefingerpoken
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        this.$falsingCollector.onTouchEvent(motionEvent);
        return false;
    }
}
