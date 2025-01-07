package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import java.util.function.BooleanSupplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StatusBarNotificationPresenter$$ExternalSyntheticLambda0 implements BooleanSupplier {
    public final /* synthetic */ KeyguardStateController f$0;

    @Override // java.util.function.BooleanSupplier
    public final boolean getAsBoolean() {
        return ((KeyguardStateControllerImpl) this.f$0).mCanDismissLockScreen;
    }
}
