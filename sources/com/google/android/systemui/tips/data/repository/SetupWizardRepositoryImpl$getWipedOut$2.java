package com.google.android.systemui.tips.data.repository;

import android.os.Bundle;
import com.google.android.setupwizard.deviceorigin.DeviceOrigin;
import com.google.android.systemui.tips.data.repository.SetupWizardRepositoryImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SetupWizardRepositoryImpl$getWipedOut$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ SetupWizardRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetupWizardRepositoryImpl$getWipedOut$2(SetupWizardRepositoryImpl setupWizardRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = setupWizardRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SetupWizardRepositoryImpl$getWipedOut$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SetupWizardRepositoryImpl$getWipedOut$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = false;
        try {
            Bundle call = this.this$0.applicationContext.getContentResolver().call(DeviceOrigin.Contract.CONTENT_URI, "is_wiped_out", (String) null, (Bundle) null);
            boolean z2 = call == null ? false : call.getBoolean("is_wiped_out", false);
            SetupWizardRepositoryImpl setupWizardRepositoryImpl = this.this$0;
            if (z2) {
                setupWizardRepositoryImpl.logger.log(SetupWizardRepositoryImpl.LogEvent.CONTEXTUAL_TIPS_SOURCE_DEVICE_WIPED_OUT);
            }
            z = z2;
        } catch (Exception e) {
            e.toString();
        }
        return Boolean.valueOf(z);
    }
}
