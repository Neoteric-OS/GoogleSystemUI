package com.android.systemui.statusbar.phone;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class KeyguardStatusBarViewController$$ExternalSyntheticLambda10 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardStatusBarViewController f$0;

    public /* synthetic */ KeyguardStatusBarViewController$$ExternalSyntheticLambda10(KeyguardStatusBarViewController keyguardStatusBarViewController, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardStatusBarViewController;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Float f = (Float) obj;
        switch (this.$r8$classId) {
            case 0:
                KeyguardStatusBarViewController keyguardStatusBarViewController = this.f$0;
                keyguardStatusBarViewController.getClass();
                keyguardStatusBarViewController.mSystemEventAnimatorAlpha = f.floatValue();
                keyguardStatusBarViewController.updateViewState();
                break;
            default:
                ((KeyguardStatusBarView) this.f$0.mView).setTranslationX(f.floatValue());
                break;
        }
        return Unit.INSTANCE;
    }
}
