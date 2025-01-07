package com.android.systemui.util.concurrency;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UiThreadContextKt$runWithScissors$1 implements Runnable {
    public final /* synthetic */ Function0 $block;
    public final /* synthetic */ AtomicReference $returnedValue;

    public UiThreadContextKt$runWithScissors$1(AtomicReference atomicReference, Function0 function0) {
        this.$returnedValue = atomicReference;
        this.$block = function0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.$returnedValue.set(this.$block.invoke());
    }
}
