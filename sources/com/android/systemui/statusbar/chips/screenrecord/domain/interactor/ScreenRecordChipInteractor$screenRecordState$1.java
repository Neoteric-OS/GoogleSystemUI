package com.android.systemui.statusbar.chips.screenrecord.domain.interactor;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.mediaprojection.data.model.MediaProjectionState;
import com.android.systemui.screenrecord.data.model.ScreenRecordModel;
import com.android.systemui.statusbar.chips.screenrecord.domain.model.ScreenRecordChipModel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ScreenRecordChipInteractor$screenRecordState$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ ScreenRecordChipInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenRecordChipInteractor$screenRecordState$1(ScreenRecordChipInteractor screenRecordChipInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = screenRecordChipInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ScreenRecordChipInteractor$screenRecordState$1 screenRecordChipInteractor$screenRecordState$1 = new ScreenRecordChipInteractor$screenRecordState$1(this.this$0, (Continuation) obj3);
        screenRecordChipInteractor$screenRecordState$1.L$0 = (ScreenRecordModel) obj;
        screenRecordChipInteractor$screenRecordState$1.L$1 = (MediaProjectionState) obj2;
        return screenRecordChipInteractor$screenRecordState$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Intent intent;
        ComponentName component;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ScreenRecordModel screenRecordModel = (ScreenRecordModel) this.L$0;
        MediaProjectionState mediaProjectionState = (MediaProjectionState) this.L$1;
        String str = null;
        if (screenRecordModel instanceof ScreenRecordModel.DoingNothing) {
            LogBuffer logBuffer = this.this$0.logger;
            logBuffer.commit(logBuffer.obtain("ScreenRecord", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.chips.screenrecord.domain.interactor.ScreenRecordChipInteractor$screenRecordState$1.2
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return "State: DoingNothing";
                }
            }, null));
            return ScreenRecordChipModel.DoingNothing.INSTANCE;
        }
        if (screenRecordModel instanceof ScreenRecordModel.Starting) {
            LogBuffer logBuffer2 = this.this$0.logger;
            LogMessage obtain = logBuffer2.obtain("ScreenRecord", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.chips.screenrecord.domain.interactor.ScreenRecordChipInteractor$screenRecordState$1.4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return "State: Starting(" + ((LogMessage) obj2).getLong1() + ")";
                }
            }, null);
            ScreenRecordModel.Starting starting = (ScreenRecordModel.Starting) screenRecordModel;
            ((LogMessageImpl) obtain).long1 = starting.millisUntilStarted;
            logBuffer2.commit(obtain);
            return new ScreenRecordChipModel.Starting(starting.millisUntilStarted);
        }
        if (!(screenRecordModel instanceof ScreenRecordModel.Recording)) {
            throw new NoWhenBranchMatchedException();
        }
        ActivityManager.RunningTaskInfo runningTaskInfo = mediaProjectionState instanceof MediaProjectionState.Projecting.SingleTask ? ((MediaProjectionState.Projecting.SingleTask) mediaProjectionState).task : null;
        LogBuffer logBuffer3 = this.this$0.logger;
        LogMessage obtain2 = logBuffer3.obtain("ScreenRecord", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.chips.screenrecord.domain.interactor.ScreenRecordChipInteractor$screenRecordState$1.6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("State: Recording(taskPackage=", ((LogMessage) obj2).getStr1(), ")");
            }
        }, null);
        if (runningTaskInfo != null && (intent = runningTaskInfo.baseIntent) != null && (component = intent.getComponent()) != null) {
            str = component.getPackageName();
        }
        ((LogMessageImpl) obtain2).str1 = str;
        logBuffer3.commit(obtain2);
        return new ScreenRecordChipModel.Recording(runningTaskInfo);
    }
}
