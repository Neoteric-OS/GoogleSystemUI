package com.android.systemui.util.concurrency;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;
import dagger.internal.Provider;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SysUIConcurrencyModule_ProvideBackPanelUiThreadContextFactory implements Provider {
    public static UiThreadContext provideBackPanelUiThreadContext(Looper looper, Handler handler, Executor executor) {
        SysUIConcurrencyModule$provideBackPanelUiThreadContext$2 sysUIConcurrencyModule$provideBackPanelUiThreadContext$2 = new Function0() { // from class: com.android.systemui.util.concurrency.SysUIConcurrencyModule$provideBackPanelUiThreadContext$2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Choreographer.getInstance();
            }
        };
        AtomicReference atomicReference = new AtomicReference();
        handler.runWithScissors(new UiThreadContextKt$runWithScissors$1(atomicReference, sysUIConcurrencyModule$provideBackPanelUiThreadContext$2), 150L);
        Object obj = atomicReference.get();
        Intrinsics.checkNotNull(obj);
        return new UiThreadContext(looper, handler, executor, (Choreographer) obj);
    }
}
