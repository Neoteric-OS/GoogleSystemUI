package com.android.systemui.unfold.util;

import com.android.systemui.unfold.updates.RotationChangeProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NaturalRotationUnfoldProgressProvider$rotationListener$1 implements RotationChangeProvider.RotationListener {
    public final /* synthetic */ NaturalRotationUnfoldProgressProvider this$0;

    public NaturalRotationUnfoldProgressProvider$rotationListener$1(NaturalRotationUnfoldProgressProvider naturalRotationUnfoldProgressProvider) {
        this.this$0 = naturalRotationUnfoldProgressProvider;
    }

    @Override // com.android.systemui.unfold.updates.RotationChangeProvider.RotationListener
    public final void onRotationChanged(int i) {
        boolean z = i == 0 || i == 2;
        NaturalRotationUnfoldProgressProvider naturalRotationUnfoldProgressProvider = this.this$0;
        if (naturalRotationUnfoldProgressProvider.isNaturalRotation != z) {
            naturalRotationUnfoldProgressProvider.isNaturalRotation = z;
            naturalRotationUnfoldProgressProvider.scopedUnfoldTransitionProgressProvider.setReadyToHandleTransition(z);
        }
    }
}
