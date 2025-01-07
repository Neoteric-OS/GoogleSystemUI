package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.policy.CallbackController;
import java.lang.ref.WeakReference;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarLocationPublisher implements CallbackController {
    public final Set listeners = new LinkedHashSet();

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
        this.listeners.add(new WeakReference(null));
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
        WeakReference weakReference = null;
        for (WeakReference weakReference2 : this.listeners) {
            if (Intrinsics.areEqual(weakReference2.get(), (Object) null)) {
                weakReference = weakReference2;
            }
        }
        if (weakReference != null) {
            this.listeners.remove(weakReference);
        }
    }
}
