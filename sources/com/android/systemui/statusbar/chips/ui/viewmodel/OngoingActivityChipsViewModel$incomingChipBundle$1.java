package com.android.systemui.statusbar.chips.ui.viewmodel;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function6;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class OngoingActivityChipsViewModel$incomingChipBundle$1 extends SuspendLambda implements Function6 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    /* synthetic */ Object L$3;
    /* synthetic */ Object L$4;
    int label;
    final /* synthetic */ OngoingActivityChipsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OngoingActivityChipsViewModel$incomingChipBundle$1(OngoingActivityChipsViewModel ongoingActivityChipsViewModel, Continuation continuation) {
        super(6, continuation);
        this.this$0 = ongoingActivityChipsViewModel;
    }

    @Override // kotlin.jvm.functions.Function6
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        OngoingActivityChipsViewModel$incomingChipBundle$1 ongoingActivityChipsViewModel$incomingChipBundle$1 = new OngoingActivityChipsViewModel$incomingChipBundle$1(this.this$0, (Continuation) obj6);
        ongoingActivityChipsViewModel$incomingChipBundle$1.L$0 = (OngoingActivityChipModel) obj;
        ongoingActivityChipsViewModel$incomingChipBundle$1.L$1 = (OngoingActivityChipModel) obj2;
        ongoingActivityChipsViewModel$incomingChipBundle$1.L$2 = (OngoingActivityChipModel) obj3;
        ongoingActivityChipsViewModel$incomingChipBundle$1.L$3 = (OngoingActivityChipModel) obj4;
        ongoingActivityChipsViewModel$incomingChipBundle$1.L$4 = (OngoingActivityChipModel) obj5;
        return ongoingActivityChipsViewModel$incomingChipBundle$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        OngoingActivityChipModel ongoingActivityChipModel = (OngoingActivityChipModel) this.L$0;
        OngoingActivityChipModel ongoingActivityChipModel2 = (OngoingActivityChipModel) this.L$1;
        OngoingActivityChipModel ongoingActivityChipModel3 = (OngoingActivityChipModel) this.L$2;
        OngoingActivityChipModel ongoingActivityChipModel4 = (OngoingActivityChipModel) this.L$3;
        OngoingActivityChipModel ongoingActivityChipModel5 = (OngoingActivityChipModel) this.L$4;
        LogBuffer logBuffer = this.this$0.logger;
        LogLevel logLevel = LogLevel.INFO;
        LogMessage obtain = logBuffer.obtain("ChipsViewModel", logLevel, new Function1() { // from class: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$incomingChipBundle$1.2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LogMessage logMessage = (LogMessage) obj2;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Chips: ScreenRecord=", str1, " > ShareToApp=", str2, " > CastToOther="), logMessage.getStr3(), "...");
            }
        }, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = ongoingActivityChipModel.getLogName();
        logMessageImpl.str2 = ongoingActivityChipModel2.getLogName();
        logMessageImpl.str3 = ongoingActivityChipModel3.getLogName();
        logBuffer.commit(obtain);
        LogBuffer logBuffer2 = this.this$0.logger;
        LogMessage obtain2 = logBuffer2.obtain("ChipsViewModel", logLevel, new Function1() { // from class: com.android.systemui.statusbar.chips.ui.viewmodel.OngoingActivityChipsViewModel$incomingChipBundle$1.4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LogMessage logMessage = (LogMessage) obj2;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("... > Call=", logMessage.getStr1(), " > DemoRon=", logMessage.getStr2());
            }
        }, null);
        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
        logMessageImpl2.str1 = ongoingActivityChipModel4.getLogName();
        logMessageImpl2.str2 = ongoingActivityChipModel5.getLogName();
        logBuffer2.commit(obtain2);
        return new OngoingActivityChipsViewModel.ChipBundle(ongoingActivityChipModel, ongoingActivityChipModel2, ongoingActivityChipModel3, ongoingActivityChipModel4, ongoingActivityChipModel5);
    }
}
