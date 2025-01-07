package com.google.android.systemui.columbus.legacy.gates;

import android.provider.Settings;
import com.android.systemui.settings.UserTrackerImpl;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
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
final class SilenceAlertsDisabled$onActivate$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ SilenceAlertsDisabled this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled$onActivate$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ SilenceAlertsDisabled this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SilenceAlertsDisabled silenceAlertsDisabled, Continuation continuation) {
            super(2, continuation);
            this.this$0 = silenceAlertsDisabled;
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
            ColumbusSettings columbusSettings = this.this$0.columbusSettings;
            return Boolean.valueOf(Settings.Secure.getIntForUser(columbusSettings.contentResolver, "columbus_silence_alerts", 1, ((UserTrackerImpl) columbusSettings.userTracker).getUserId()) != 0);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SilenceAlertsDisabled$onActivate$1(SilenceAlertsDisabled silenceAlertsDisabled, Continuation continuation) {
        super(2, continuation);
        this.this$0 = silenceAlertsDisabled;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SilenceAlertsDisabled$onActivate$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SilenceAlertsDisabled$onActivate$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SilenceAlertsDisabled silenceAlertsDisabled;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SilenceAlertsDisabled silenceAlertsDisabled2 = this.this$0;
            CoroutineDispatcher coroutineDispatcher = silenceAlertsDisabled2.bgDispatcher;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(silenceAlertsDisabled2, null);
            this.L$0 = silenceAlertsDisabled2;
            this.label = 1;
            Object withContext = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
            if (withContext == coroutineSingletons) {
                return coroutineSingletons;
            }
            obj = withContext;
            silenceAlertsDisabled = silenceAlertsDisabled2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            silenceAlertsDisabled = (SilenceAlertsDisabled) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        BuildersKt.launch$default(silenceAlertsDisabled.coroutineScope, null, null, new SilenceAlertsDisabled$updateSilenceAlertsEnabled$1(silenceAlertsDisabled, ((Boolean) obj).booleanValue(), null), 3);
        return Unit.INSTANCE;
    }
}
