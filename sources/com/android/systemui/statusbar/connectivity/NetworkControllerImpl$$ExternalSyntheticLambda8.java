package com.android.systemui.statusbar.connectivity;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogMessage;
import java.util.Locale;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NetworkControllerImpl$$ExternalSyntheticLambda8 implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        boolean z = NetworkControllerImpl.DEBUG;
        Locale locale = Locale.US;
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Received broadcast with action \"", ((LogMessage) obj).getStr1(), "\"");
    }
}
