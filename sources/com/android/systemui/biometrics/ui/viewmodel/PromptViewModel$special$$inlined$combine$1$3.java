package com.android.systemui.biometrics.ui.viewmodel;

import android.graphics.Rect;
import com.android.systemui.biometrics.shared.model.BiometricModalities;
import com.android.systemui.biometrics.shared.model.PromptKind;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptViewModel$special$$inlined$combine$1$3 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ PromptViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptViewModel$special$$inlined$combine$1$3(PromptViewModel promptViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = promptViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        PromptViewModel$special$$inlined$combine$1$3 promptViewModel$special$$inlined$combine$1$3 = new PromptViewModel$special$$inlined$combine$1$3(this.this$0, (Continuation) obj3);
        promptViewModel$special$$inlined$combine$1$3.L$0 = (FlowCollector) obj;
        promptViewModel$special$$inlined$combine$1$3.L$1 = (Object[]) obj2;
        return promptViewModel$special$$inlined$combine$1$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i;
        int i2;
        int i3;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i4 = this.label;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Object[] objArr = (Object[]) this.L$1;
            Object obj2 = objArr[0];
            Object obj3 = objArr[1];
            Object obj4 = objArr[2];
            Object obj5 = objArr[3];
            Object obj6 = objArr[4];
            boolean booleanValue = ((Boolean) objArr[5]).booleanValue();
            BiometricModalities biometricModalities = (BiometricModalities) obj6;
            PromptSize promptSize = (PromptSize) obj4;
            PromptKind promptKind = (PromptKind) obj3;
            int ordinal = ((PromptPosition) obj5).ordinal();
            if (ordinal == 1) {
                i = promptKind.isOnePaneNoSensorLandscapeBiometric() ? 0 : this.this$0.mediumTopGuidelinePadding;
                i2 = 0;
                i3 = 0;
            } else if (ordinal != 2) {
                if (ordinal != 3) {
                    i2 = 0;
                    i = 0;
                } else {
                    i2 = PromptViewModel.access$getHorizontalPadding(this.this$0, promptSize, biometricModalities, booleanValue);
                    i = 0;
                }
                i3 = i;
            } else {
                i3 = PromptViewModel.access$getHorizontalPadding(this.this$0, promptSize, biometricModalities, booleanValue);
                i2 = 0;
                i = 0;
            }
            Rect rect = new Rect(i2, i, i3, 0);
            this.label = 1;
            if (flowCollector.emit(rect, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i4 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
