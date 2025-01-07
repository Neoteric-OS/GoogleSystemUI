package com.android.systemui.statusbar.phone.ongoingcall;

import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$start$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class OngoingCallController$start$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ OngoingCallController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OngoingCallController$start$2(OngoingCallController ongoingCallController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = ongoingCallController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new OngoingCallController$start$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((OngoingCallController$start$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
        }
        ResultKt.throwOnFailure(obj);
        OngoingCallController ongoingCallController = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = ongoingCallController.statusBarModeRepository.defaultDisplay.isInFullscreenMode;
        OngoingCallController$start$1.AnonymousClass1 anonymousClass1 = new OngoingCallController$start$1.AnonymousClass1(ongoingCallController, 1);
        this.label = 1;
        readonlyStateFlow.collect(anonymousClass1, this);
        return coroutineSingletons;
    }
}
