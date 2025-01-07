package com.google.android.systemui.columbus.legacy.gates;

import android.telephony.TelephonyCallback;
import com.android.systemui.telephony.TelephonyListenerManager;
import dagger.Lazy;
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
public final class TelephonyActivity extends Gate {
    public final CoroutineDispatcher bgDispatcher;
    public boolean isCallBlocked;
    public final TelephonyActivity$phoneStateListener$1 phoneStateListener = new TelephonyCallback.CallStateListener() { // from class: com.google.android.systemui.columbus.legacy.gates.TelephonyActivity$phoneStateListener$1

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.google.android.systemui.columbus.legacy.gates.TelephonyActivity$phoneStateListener$1$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ int $state;
            int label;
            final /* synthetic */ TelephonyActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TelephonyActivity telephonyActivity, int i, Continuation continuation) {
                super(2, continuation);
                this.this$0 = telephonyActivity;
                this.$state = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass1(this.this$0, this.$state, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
                Unit unit = Unit.INSTANCE;
                anonymousClass1.invokeSuspend(unit);
                return unit;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                TelephonyActivity telephonyActivity = this.this$0;
                Integer num = new Integer(this.$state);
                telephonyActivity.getClass();
                telephonyActivity.isCallBlocked = num.intValue() == 2;
                TelephonyActivity telephonyActivity2 = this.this$0;
                BuildersKt.launch$default(telephonyActivity2.coroutineScope, null, null, new TelephonyActivity$updateBlocking$1(telephonyActivity2, null), 3);
                return Unit.INSTANCE;
            }
        }

        @Override // android.telephony.TelephonyCallback.CallStateListener
        public final void onCallStateChanged(int i) {
            TelephonyActivity telephonyActivity = TelephonyActivity.this;
            BuildersKt.launch$default(telephonyActivity.coroutineScope, null, null, new AnonymousClass1(telephonyActivity, i, null), 3);
        }
    };
    public final Lazy telephonyListenerManager;
    public final Lazy telephonyManager;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.gates.TelephonyActivity$phoneStateListener$1] */
    public TelephonyActivity(Lazy lazy, Lazy lazy2, CoroutineDispatcher coroutineDispatcher) {
        this.telephonyManager = lazy;
        this.telephonyListenerManager = lazy2;
        this.bgDispatcher = coroutineDispatcher;
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        ((TelephonyListenerManager) this.telephonyListenerManager.get()).addCallStateListener(this.phoneStateListener);
        TelephonyActivity$onActivate$1 telephonyActivity$onActivate$1 = new TelephonyActivity$onActivate$1(this, null);
        BuildersKt.launch$default(this.coroutineScope, this.mainDispatcher, null, telephonyActivity$onActivate$1, 2);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        ((TelephonyListenerManager) this.telephonyListenerManager.get()).removeCallStateListener(this.phoneStateListener);
    }
}
