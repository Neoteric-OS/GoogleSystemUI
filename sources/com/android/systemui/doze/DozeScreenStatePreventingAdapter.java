package com.android.systemui.doze;

import com.android.systemui.doze.DozeMachine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class DozeScreenStatePreventingAdapter extends DozeMachine.Service.Delegate {
    public DozeScreenStatePreventingAdapter(DozeMachine.Service service) {
        super(service);
    }

    @Override // com.android.systemui.doze.DozeMachine.Service.Delegate, com.android.systemui.doze.DozeMachine.Service
    public final void setDozeScreenState(int i) {
        if (i == 3) {
            i = 2;
        } else if (i == 4) {
            i = 6;
        }
        super.setDozeScreenState(i);
    }
}
