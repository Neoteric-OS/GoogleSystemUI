package com.android.systemui.bouncer.ui;

import com.android.systemui.bouncer.ui.binder.KeyguardBouncerViewBinder$bind$delegate$1;
import java.lang.ref.WeakReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerViewImpl {
    public WeakReference _delegate = new WeakReference(null);

    public final KeyguardBouncerViewBinder$bind$delegate$1 getDelegate() {
        return (KeyguardBouncerViewBinder$bind$delegate$1) this._delegate.get();
    }
}
