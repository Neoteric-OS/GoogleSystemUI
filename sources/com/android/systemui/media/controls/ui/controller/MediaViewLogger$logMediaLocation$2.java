package com.android.systemui.media.controls.ui.controller;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaViewLogger$logMediaLocation$2 extends Lambda implements Function1 {
    public static final MediaViewLogger$logMediaLocation$2 INSTANCE = new MediaViewLogger$logMediaLocation$2();

    public MediaViewLogger$logMediaLocation$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LogMessage logMessage = (LogMessage) obj;
        String str1 = logMessage.getStr1();
        int int1 = logMessage.getInt1();
        int int2 = logMessage.getInt2();
        StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("location (", str1, "): ", int1, " -> ");
        m.append(int2);
        return m.toString();
    }
}
