package com.android.app.tracing;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlowTracing$traceEmissionCount$trackName$2 extends Lambda implements Function0 {
    final /* synthetic */ String $flowName;
    final /* synthetic */ boolean $uniqueSuffix;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowTracing$traceEmissionCount$trackName$2(String str, boolean z) {
        super(0);
        this.$flowName = str;
        this.$uniqueSuffix = z;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(this.$flowName, "#emissionCount", this.$uniqueSuffix ? AnnotationValue$1$$ExternalSyntheticOutline0.m(FlowTracing.counter.addAndGet(1), "$") : "");
    }
}
