package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.os.UserHandle;
import android.util.Log;
import androidx.core.app.NotificationCompat$BigTextStyle;
import androidx.core.app.NotificationCompat$Builder;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1 extends SuspendLambda implements Function2 {
    long J$0;
    int label;
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.power.BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ String $readableEndTime;
        final /* synthetic */ String $readableStartTime;
        int label;
        final /* synthetic */ BatteryDefenderNotificationHandler this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, String str, String str2, Continuation continuation) {
            super(2, continuation);
            this.this$0 = batteryDefenderNotificationHandler;
            this.$readableStartTime = str;
            this.$readableEndTime = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$readableStartTime, this.$readableEndTime, continuation);
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
            BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = this.this$0;
            int i = BatteryDefenderNotificationHandler.$r8$clinit;
            DwellTempDefenderNotification dwellTempDefenderNotification = batteryDefenderNotificationHandler.getDwellTempDefenderNotification();
            String str = this.$readableStartTime;
            String str2 = this.$readableEndTime;
            Log.d("DwellTempDefenderNotification", "showPostNotification, postNotificationVisible:" + dwellTempDefenderNotification.postNotificationVisible + "->true");
            dwellTempDefenderNotification.postNotificationVisible = true;
            Log.d("DwellTempDefenderNotification", "sendPostNotification, startTime: " + str + ", endTime: " + str2);
            String string = dwellTempDefenderNotification.context.getString(R.string.defender_post_notify_des, str, str2);
            NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(dwellTempDefenderNotification.context, "BAT");
            notificationCompat$Builder.mNotification.icon = R.drawable.ic_battery_with_shield;
            notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(dwellTempDefenderNotification.context.getString(R.string.defender_post_notify_title));
            notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(string);
            NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
            notificationCompat$BigTextStyle.mBigText = NotificationCompat$Builder.limitCharSequenceLength(string);
            notificationCompat$Builder.setStyle(notificationCompat$BigTextStyle);
            notificationCompat$Builder.mContentIntent = PowerUtils.createBatterySettingsPendingIntentAsUser(dwellTempDefenderNotification.context);
            notificationCompat$Builder.mSilent = true;
            notificationCompat$Builder.addAction(dwellTempDefenderNotification.context.getString(R.string.battery_health_notify_learn_more), PowerUtils.createHelpArticlePendingIntentAsUser(R.string.defender_notify_help_url, dwellTempDefenderNotification.context));
            notificationCompat$Builder.mLocalOnly = true;
            PowerUtils.overrideNotificationAppName(dwellTempDefenderNotification.context, notificationCompat$Builder);
            NotificationManager notificationManager = dwellTempDefenderNotification.notificationManager;
            if (notificationManager != null) {
                notificationManager.notifyAsUser("battery_defender", R.string.defender_post_notify_title, notificationCompat$Builder.build(), UserHandle.ALL);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryDefenderNotificationHandler;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0101 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0102 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.BatteryDefenderNotificationHandler$exitTempOrDwellDefenderIfNeeded$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
