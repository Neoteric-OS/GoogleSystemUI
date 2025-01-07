package com.android.systemui.keyevent.domain.interactor;

import com.android.systemui.back.domain.interactor.BackActionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardKeyEventInteractor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SysUIKeyEventHandler {
    public final BackActionInteractor backActionInteractor;
    public final KeyguardKeyEventInteractor keyguardKeyEventInteractor;

    public SysUIKeyEventHandler(BackActionInteractor backActionInteractor, KeyguardKeyEventInteractor keyguardKeyEventInteractor) {
        this.backActionInteractor = backActionInteractor;
        this.keyguardKeyEventInteractor = keyguardKeyEventInteractor;
    }
}
