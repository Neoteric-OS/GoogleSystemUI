package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.shared.model.TransitionModeOnCanceled;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TransitionInteractor$listenForSleepTransition$2 extends Lambda implements Function1 {
    public static final TransitionInteractor$listenForSleepTransition$2 INSTANCE = new TransitionInteractor$listenForSleepTransition$2();

    public TransitionInteractor$listenForSleepTransition$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return TransitionModeOnCanceled.LAST_VALUE;
    }
}
