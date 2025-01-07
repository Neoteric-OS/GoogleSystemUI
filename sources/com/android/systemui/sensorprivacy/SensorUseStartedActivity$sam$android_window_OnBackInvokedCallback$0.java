package com.android.systemui.sensorprivacy;

import android.window.OnBackInvokedCallback;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class SensorUseStartedActivity$sam$android_window_OnBackInvokedCallback$0 implements OnBackInvokedCallback {
    public final /* synthetic */ Function0 function;

    public SensorUseStartedActivity$sam$android_window_OnBackInvokedCallback$0(Function0 function0) {
        this.function = function0;
    }

    @Override // android.window.OnBackInvokedCallback
    public final /* synthetic */ void onBackInvoked() {
        this.function.invoke();
    }
}
