package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.shared.model.FingerprintSensorType;
import com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function6;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PromptIconViewModel$contentDescriptionId$1$1 extends SuspendLambda implements Function6 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    int label;
    final /* synthetic */ PromptIconViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptIconViewModel$contentDescriptionId$1$1(PromptIconViewModel promptIconViewModel, Continuation continuation) {
        super(6, continuation);
        this.this$0 = promptIconViewModel;
    }

    @Override // kotlin.jvm.functions.Function6
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        boolean booleanValue = ((Boolean) obj3).booleanValue();
        boolean booleanValue2 = ((Boolean) obj4).booleanValue();
        boolean booleanValue3 = ((Boolean) obj5).booleanValue();
        PromptIconViewModel$contentDescriptionId$1$1 promptIconViewModel$contentDescriptionId$1$1 = new PromptIconViewModel$contentDescriptionId$1$1(this.this$0, (Continuation) obj6);
        promptIconViewModel$contentDescriptionId$1$1.L$0 = (FingerprintSensorType) obj;
        promptIconViewModel$contentDescriptionId$1$1.L$1 = (PromptAuthState) obj2;
        promptIconViewModel$contentDescriptionId$1$1.Z$0 = booleanValue;
        promptIconViewModel$contentDescriptionId$1$1.Z$1 = booleanValue2;
        promptIconViewModel$contentDescriptionId$1$1.Z$2 = booleanValue3;
        return promptIconViewModel$contentDescriptionId$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        FingerprintSensorType fingerprintSensorType = (FingerprintSensorType) this.L$0;
        PromptAuthState promptAuthState = (PromptAuthState) this.L$1;
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        PromptIconViewModel promptIconViewModel = this.this$0;
        boolean z4 = promptAuthState.isAuthenticated;
        promptIconViewModel.getClass();
        int i = -1;
        if (z2) {
            if (PromptIconViewModel.WhenMappings.$EnumSwitchMapping$0[fingerprintSensorType.ordinal()] != 1) {
                i = R.string.biometric_dialog_confirm;
            }
        } else if (z || z4) {
            i = PromptIconViewModel.WhenMappings.$EnumSwitchMapping$0[fingerprintSensorType.ordinal()] == 1 ? R.string.security_settings_sfps_enroll_find_sensor_message : R.string.fingerprint_dialog_touch_sensor;
        } else if (z3) {
            i = R.string.biometric_dialog_try_again;
        }
        return new Integer(i);
    }
}
