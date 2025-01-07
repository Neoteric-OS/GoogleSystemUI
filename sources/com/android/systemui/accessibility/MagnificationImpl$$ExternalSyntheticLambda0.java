package com.android.systemui.accessibility;

import com.android.systemui.accessibility.MagnificationModeSwitch;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MagnificationImpl$$ExternalSyntheticLambda0 implements MagnificationModeSwitch.ClickListener {
    public final /* synthetic */ MagnificationImpl f$0;

    public /* synthetic */ MagnificationImpl$$ExternalSyntheticLambda0(MagnificationImpl magnificationImpl) {
        this.f$0 = magnificationImpl;
    }

    @Override // com.android.systemui.accessibility.MagnificationModeSwitch.ClickListener
    public final void onClick(int i) {
        MagnificationImpl magnificationImpl = this.f$0;
        magnificationImpl.mHandler.post(new MagnificationImpl$$ExternalSyntheticLambda2(i, 0, magnificationImpl));
    }
}
