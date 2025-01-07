package com.android.systemui.keyguard.ui.binder;

import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.CoreStartable;
import dagger.Lazy;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardDismissActionBinder implements CoreStartable {
    public final KeyguardLogger keyguardLogger;

    public KeyguardDismissActionBinder(Lazy lazy, CoroutineScope coroutineScope, KeyguardLogger keyguardLogger) {
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
