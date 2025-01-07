package com.android.systemui.deviceentry.data.repository;

import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class FaceWakeUpTriggersConfigImpl$processStringArray$1$1 implements Function {
    public static final FaceWakeUpTriggersConfigImpl$processStringArray$1$1 INSTANCE = new FaceWakeUpTriggersConfigImpl$processStringArray$1$1();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Integer.valueOf(Integer.parseInt((String) obj));
    }
}
