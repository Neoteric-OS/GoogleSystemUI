package com.android.systemui.shade;

import com.android.keyguard.KeyguardUnfoldTransition;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationPanelViewController$$ExternalSyntheticLambda21 implements Function {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ NotificationPanelViewController$$ExternalSyntheticLambda21(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return (KeyguardUnfoldTransition) ((DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) obj).keyguardUnfoldTransitionProvider.get();
            default:
                return Float.valueOf(((NotificationPanelView) obj).mCurrentPanelAlpha);
        }
    }
}
