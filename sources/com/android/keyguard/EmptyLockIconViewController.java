package com.android.keyguard;

import android.view.MotionEvent;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmptyLockIconViewController implements LockIconViewController {
    public final Lazy keyguardRootView;

    public EmptyLockIconViewController(Lazy lazy) {
    }

    @Override // com.android.keyguard.LockIconViewController
    public final boolean willHandleTouchWhileDozing(MotionEvent motionEvent) {
        return false;
    }

    @Override // com.android.keyguard.LockIconViewController
    public final void dozeTimeTick() {
    }

    @Override // com.android.keyguard.LockIconViewController
    public final void setAlpha(float f) {
    }

    @Override // com.android.keyguard.LockIconViewController
    public final void setLockIconView(LockIconView lockIconView) {
    }
}
