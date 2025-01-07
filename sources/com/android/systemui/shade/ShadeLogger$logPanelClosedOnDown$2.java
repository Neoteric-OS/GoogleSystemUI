package com.android.systemui.shade;

import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ShadeLogger$logPanelClosedOnDown$2 extends Lambda implements Function1 {
    public static final ShadeLogger$logPanelClosedOnDown$2 INSTANCE = new ShadeLogger$logPanelClosedOnDown$2();

    public ShadeLogger$logPanelClosedOnDown$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        return logMessage.getStr1() + "; mPanelClosedOnDown=" + logMessage.getBool1() + "; mExpandedFraction=" + logMessage.getDouble1();
    }
}
