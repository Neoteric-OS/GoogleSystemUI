package com.android.systemui.screenshot;

import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DeferredCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotSoundControllerImpl {
    public final CoroutineDispatcher bgDispatcher;
    public final CoroutineScope coroutineScope;
    public final DeferredCoroutine player;
    public final ScreenshotSoundProviderImpl soundProvider;

    public ScreenshotSoundControllerImpl(ScreenshotSoundProviderImpl screenshotSoundProviderImpl, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.soundProvider = screenshotSoundProviderImpl;
        this.coroutineScope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        Function2 screenshotSoundControllerImpl$player$1 = new ScreenshotSoundControllerImpl$player$1(this, null);
        CoroutineStart coroutineStart = CoroutineStart.DEFAULT;
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        EmptyCoroutineContext.INSTANCE.getClass();
        CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(coroutineScope, coroutineDispatcher);
        CoroutineStart coroutineStart2 = CoroutineStart.DEFAULT;
        DeferredCoroutine deferredCoroutine = new DeferredCoroutine(newCoroutineContext, true);
        deferredCoroutine.start(coroutineStart, deferredCoroutine, screenshotSoundControllerImpl$player$1);
        this.player = deferredCoroutine;
    }
}
