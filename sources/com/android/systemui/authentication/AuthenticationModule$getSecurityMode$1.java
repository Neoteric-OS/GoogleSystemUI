package com.android.systemui.authentication;

import com.android.keyguard.KeyguardSecurityModel;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AuthenticationModule$getSecurityMode$1 implements Function {
    public final /* synthetic */ KeyguardSecurityModel $model;

    public AuthenticationModule$getSecurityMode$1(KeyguardSecurityModel keyguardSecurityModel) {
        this.$model = keyguardSecurityModel;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return this.$model.getSecurityMode(((Integer) obj).intValue());
    }
}
