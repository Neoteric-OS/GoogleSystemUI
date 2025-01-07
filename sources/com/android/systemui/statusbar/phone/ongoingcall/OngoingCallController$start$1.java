package com.android.systemui.statusbar.phone.ongoingcall;

import android.app.PendingIntent;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.shared.ActiveNotificationModel;
import com.android.systemui.statusbar.notification.shared.CallType;
import com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class OngoingCallController$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ OngoingCallController this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$start$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ OngoingCallController this$0;

        public /* synthetic */ AnonymousClass1(OngoingCallController ongoingCallController, int i) {
            this.$r8$classId = i;
            this.this$0 = ongoingCallController;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    ActiveNotificationModel activeNotificationModel = (ActiveNotificationModel) obj;
                    OngoingCallController ongoingCallController = this.this$0;
                    LogBuffer logBuffer = ongoingCallController.logger;
                    if (activeNotificationModel == null) {
                        logBuffer.commit(logBuffer.obtain("OngoingCall", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$updateInfoFromNotifModel$2
                            @Override // kotlin.jvm.functions.Function1
                            public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                                return "NotifInteractorCallModel: null";
                            }
                        }, null));
                        ongoingCallController.removeChip();
                    } else {
                        CallType callType = CallType.Ongoing;
                        CallType callType2 = activeNotificationModel.callType;
                        if (callType2 != callType) {
                            LogMessage obtain = logBuffer.obtain("OngoingCall", LogLevel.ERROR, new Function1() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$updateInfoFromNotifModel$4
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Notification Interactor sent ActiveNotificationModel with callType=", ((LogMessage) obj2).getStr1());
                                }
                            }, null);
                            ((LogMessageImpl) obtain).str1 = callType2.name();
                            logBuffer.commit(obtain);
                            ongoingCallController.removeChip();
                        } else {
                            LogMessage obtain2 = logBuffer.obtain("OngoingCall", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController$updateInfoFromNotifModel$6
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    LogMessage logMessage = (LogMessage) obj2;
                                    return "NotifInteractorCallModel: key=" + logMessage.getStr1() + " when=" + logMessage.getLong1() + " callType=" + logMessage.getStr2() + " hasIcon=" + logMessage.getBool1();
                                }
                            }, null);
                            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain2;
                            logMessageImpl.str1 = activeNotificationModel.key;
                            logMessageImpl.long1 = activeNotificationModel.whenTime;
                            logMessageImpl.str1 = callType2.name();
                            logMessageImpl.bool1 = activeNotificationModel.statusBarChipIconView != null;
                            logBuffer.commit(obtain2);
                            PendingIntent pendingIntent = activeNotificationModel.contentIntent;
                            OngoingCallController.CallNotificationInfo callNotificationInfo = ongoingCallController.callNotificationInfo;
                            boolean z = callNotificationInfo != null ? callNotificationInfo.statusBarSwipedAway : false;
                            OngoingCallController.CallNotificationInfo callNotificationInfo2 = new OngoingCallController.CallNotificationInfo(activeNotificationModel.key, activeNotificationModel.whenTime, activeNotificationModel.statusBarChipIconView, pendingIntent, activeNotificationModel.uid, true, z);
                            if (!callNotificationInfo2.equals(callNotificationInfo)) {
                                ongoingCallController.callNotificationInfo = callNotificationInfo2;
                                ongoingCallController.updateChip();
                            }
                        }
                    }
                    break;
                default:
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    OngoingCallController ongoingCallController2 = this.this$0;
                    ongoingCallController2.isFullscreen = booleanValue;
                    ongoingCallController2.updateGestureListening();
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OngoingCallController$start$1(OngoingCallController ongoingCallController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = ongoingCallController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new OngoingCallController$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((OngoingCallController$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            OngoingCallController ongoingCallController = this.this$0;
            Flow flow = ongoingCallController.activeNotificationsInteractor.ongoingCallNotification;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(ongoingCallController, 0);
            this.label = 1;
            if (flow.collect(anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
