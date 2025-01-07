package com.android.systemui.media.taptotransfer.sender;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MediaTttSenderLogger$logStateMap$2 extends Lambda implements Function1 {
    public static final MediaTttSenderLogger$logStateMap$2 INSTANCE = new MediaTttSenderLogger$logStateMap$2();

    public MediaTttSenderLogger$logStateMap$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Current sender states: ", ((LogMessage) obj).getStr1());
    }
}
