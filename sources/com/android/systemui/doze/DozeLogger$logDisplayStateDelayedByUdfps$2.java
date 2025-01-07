package com.android.systemui.doze;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DozeLogger$logDisplayStateDelayedByUdfps$2 extends Lambda implements Function1 {
    public static final DozeLogger$logDisplayStateDelayedByUdfps$2 INSTANCE = new DozeLogger$logDisplayStateDelayedByUdfps$2();

    public DozeLogger$logDisplayStateDelayedByUdfps$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Delaying display state change to: ", ((LogMessage) obj).getStr1(), " due to UDFPS activity");
    }
}
