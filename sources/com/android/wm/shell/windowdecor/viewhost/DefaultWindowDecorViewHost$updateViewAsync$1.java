package com.android.wm.shell.windowdecor.viewhost;

import android.content.res.Configuration;
import android.view.View;
import android.view.WindowManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DefaultWindowDecorViewHost$updateViewAsync$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ WindowManager.LayoutParams $attrs;
    final /* synthetic */ Configuration $configuration;
    final /* synthetic */ View $view;
    int label;
    final /* synthetic */ DefaultWindowDecorViewHost this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultWindowDecorViewHost$updateViewAsync$1(DefaultWindowDecorViewHost defaultWindowDecorViewHost, View view, WindowManager.LayoutParams layoutParams, Configuration configuration, Continuation continuation) {
        super(2, continuation);
        this.this$0 = defaultWindowDecorViewHost;
        this.$view = view;
        this.$attrs = layoutParams;
        this.$configuration = configuration;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DefaultWindowDecorViewHost$updateViewAsync$1(this.this$0, this.$view, this.$attrs, this.$configuration, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DefaultWindowDecorViewHost$updateViewAsync$1 defaultWindowDecorViewHost$updateViewAsync$1 = (DefaultWindowDecorViewHost$updateViewAsync$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        defaultWindowDecorViewHost$updateViewAsync$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.updateViewHost(this.$view, this.$attrs, this.$configuration, null);
        return Unit.INSTANCE;
    }
}
