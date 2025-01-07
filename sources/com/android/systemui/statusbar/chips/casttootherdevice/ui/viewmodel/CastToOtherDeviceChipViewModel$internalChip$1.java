package com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel;

import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.chips.ui.model.OngoingActivityChipModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CastToOtherDeviceChipViewModel$internalChip$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ CastToOtherDeviceChipViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CastToOtherDeviceChipViewModel$internalChip$1(CastToOtherDeviceChipViewModel castToOtherDeviceChipViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = castToOtherDeviceChipViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        CastToOtherDeviceChipViewModel$internalChip$1 castToOtherDeviceChipViewModel$internalChip$1 = new CastToOtherDeviceChipViewModel$internalChip$1(this.this$0, (Continuation) obj3);
        castToOtherDeviceChipViewModel$internalChip$1.L$0 = (OngoingActivityChipModel) obj;
        castToOtherDeviceChipViewModel$internalChip$1.L$1 = (OngoingActivityChipModel) obj2;
        return castToOtherDeviceChipViewModel$internalChip$1.invokeSuspend(Unit.INSTANCE);
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
        LogBuffer logBuffer = this.this$0.logger;
        LogMessage obtain = logBuffer.obtain("CastToOtherVM", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.chips.casttootherdevice.ui.viewmodel.CastToOtherDeviceChipViewModel$internalChip$1.2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LogMessage logMessage = (LogMessage) obj2;
                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("projectionChip=", logMessage.getStr1(), " > routerChip=", logMessage.getStr2());
            }
        }, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = ongoingActivityChipModel.getLogName();
        logMessageImpl.str2 = ongoingActivityChipModel2.getLogName();
        logBuffer.commit(obtain);
        return ongoingActivityChipModel instanceof OngoingActivityChipModel.Shown ? ongoingActivityChipModel : ongoingActivityChipModel2;
    }
}
