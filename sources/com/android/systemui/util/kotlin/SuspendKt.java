package com.android.systemui.util.kotlin;

import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SuspendKt {
    public static final Object race(Function1[] function1Arr, ContinuationImpl continuationImpl) {
        return CoroutineScopeKt.coroutineScope(continuationImpl, new SuspendKt$race$2(function1Arr, null));
    }
}
