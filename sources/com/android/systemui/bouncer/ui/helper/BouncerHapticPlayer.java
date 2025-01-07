package com.android.systemui.bouncer.ui.helper;

import com.android.keyguard.AuthInteractionProperties;
import dagger.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerHapticPlayer {
    public final Lazy msdlPlayer;

    public BouncerHapticPlayer(Lazy lazy) {
        LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: com.android.systemui.bouncer.ui.helper.BouncerHapticPlayer$authInteractionProperties$2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new AuthInteractionProperties();
            }
        });
    }
}
