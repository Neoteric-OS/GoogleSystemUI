package com.android.systemui.shared.recents.utilities;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ViewRippler {
    public final AnonymousClass1 mRipple = new Runnable() { // from class: com.android.systemui.shared.recents.utilities.ViewRippler.1
        @Override // java.lang.Runnable
        public final void run() {
            if (ViewRippler.this.mRoot.isAttachedToWindow()) {
                ViewRippler.this.mRoot.setPressed(true);
                ViewRippler.this.mRoot.setPressed(false);
            }
        }
    };
    public View mRoot;
}
