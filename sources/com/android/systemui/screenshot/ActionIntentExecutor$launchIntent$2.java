package com.android.systemui.screenshot;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ActionIntentExecutor$launchIntent$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Intent $intent;
    final /* synthetic */ Ref$ObjectRef $transitionOptions;
    int label;
    final /* synthetic */ ActionIntentExecutor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActionIntentExecutor$launchIntent$2(ActionIntentExecutor actionIntentExecutor, Intent intent, Ref$ObjectRef ref$ObjectRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = actionIntentExecutor;
        this.$intent = intent;
        this.$transitionOptions = ref$ObjectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ActionIntentExecutor$launchIntent$2(this.this$0, this.$intent, this.$transitionOptions, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ActionIntentExecutor$launchIntent$2 actionIntentExecutor$launchIntent$2 = (ActionIntentExecutor$launchIntent$2) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        actionIntentExecutor$launchIntent$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Context context = this.this$0.context;
        Intent intent = this.$intent;
        ActivityOptions activityOptions = (ActivityOptions) this.$transitionOptions.element;
        context.startActivity(intent, activityOptions != null ? activityOptions.toBundle() : null);
        return Unit.INSTANCE;
    }
}
