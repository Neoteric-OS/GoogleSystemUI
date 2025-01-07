package com.google.android.systemui.columbus.legacy.gates;

import android.telephony.TelephonyManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class TelephonyActivity$onActivate$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ TelephonyActivity this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.columbus.legacy.gates.TelephonyActivity$onActivate$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ TelephonyActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TelephonyActivity telephonyActivity, Continuation continuation) {
            super(2, continuation);
            this.this$0 = telephonyActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boolean.valueOf(new Integer(((TelephonyManager) this.this$0.telephonyManager.get()).getCallState()).intValue() == 2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TelephonyActivity$onActivate$1(TelephonyActivity telephonyActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = telephonyActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new TelephonyActivity$onActivate$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((TelephonyActivity$onActivate$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        TelephonyActivity telephonyActivity;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            TelephonyActivity telephonyActivity2 = this.this$0;
            CoroutineDispatcher coroutineDispatcher = telephonyActivity2.bgDispatcher;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(telephonyActivity2, null);
            this.L$0 = telephonyActivity2;
            this.label = 1;
            Object withContext = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
            if (withContext == coroutineSingletons) {
                return coroutineSingletons;
            }
            telephonyActivity = telephonyActivity2;
            obj = withContext;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            telephonyActivity = (TelephonyActivity) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        telephonyActivity.isCallBlocked = ((Boolean) obj).booleanValue();
        TelephonyActivity telephonyActivity3 = this.this$0;
        BuildersKt.launch$default(telephonyActivity3.coroutineScope, null, null, new TelephonyActivity$updateBlocking$1(telephonyActivity3, null), 3);
        return Unit.INSTANCE;
    }
}
