package com.android.systemui.screenshot;

import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.plugins.ActivityStarter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ScreenshotProxyService$executeAfterDismissing$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ IOnDoneCallback $callback;
    int label;
    final /* synthetic */ ScreenshotProxyService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenshotProxyService$executeAfterDismissing$2(ScreenshotProxyService screenshotProxyService, IOnDoneCallback iOnDoneCallback, Continuation continuation) {
        super(2, continuation);
        this.this$0 = screenshotProxyService;
        this.$callback = iOnDoneCallback;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ScreenshotProxyService$executeAfterDismissing$2(this.this$0, this.$callback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ScreenshotProxyService$executeAfterDismissing$2 screenshotProxyService$executeAfterDismissing$2 = (ScreenshotProxyService$executeAfterDismissing$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        screenshotProxyService$executeAfterDismissing$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ActivityStarter activityStarter = this.this$0.activityStarter;
        final IOnDoneCallback iOnDoneCallback = this.$callback;
        activityStarter.executeRunnableDismissingKeyguard(new Runnable() { // from class: com.android.systemui.screenshot.ScreenshotProxyService$executeAfterDismissing$2.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    IOnDoneCallback.this.onDone();
                } catch (RemoteException e) {
                    Log.w("ScreenshotProxyService", "Failed to complete callback transaction", e);
                }
            }
        }, null, true, true, true);
        return Unit.INSTANCE;
    }
}
