package com.google.android.systemui.power.batteryevent.repository;

import android.content.Context;
import android.content.Intent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SystemEventDataSource$onReceive$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    final /* synthetic */ Intent $intent;
    final /* synthetic */ String $intentAction;
    int label;
    final /* synthetic */ SystemEventDataSource this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemEventDataSource$onReceive$1(Context context, Intent intent, SystemEventDataSource systemEventDataSource, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemEventDataSource;
        this.$context = context;
        this.$intent = intent;
        this.$intentAction = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SystemEventDataSource$onReceive$1(this.$context, this.$intent, this.this$0, this.$intentAction, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SystemEventDataSource$onReceive$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SystemEventDataSource systemEventDataSource = this.this$0;
            Context context = this.$context;
            Intent intent = this.$intent;
            String str = this.$intentAction;
            this.label = 1;
            Object withContext = BuildersKt.withContext(systemEventDataSource.backgroundDispatcher, new SystemEventDataSource$processIntent$2(context, intent, systemEventDataSource, str, null), this);
            if (withContext != coroutineSingletons) {
                withContext = unit;
            }
            if (withContext == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
