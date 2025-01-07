package com.android.systemui.screenshot;

import java.util.function.Consumer;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TakeScreenshotExecutorImpl$sam$java_util_function_Consumer$0 implements Consumer {
    public final /* synthetic */ Function1 function;

    public TakeScreenshotExecutorImpl$sam$java_util_function_Consumer$0(Function1 function1) {
        this.function = function1;
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ void accept(Object obj) {
        this.function.invoke(obj);
    }
}
