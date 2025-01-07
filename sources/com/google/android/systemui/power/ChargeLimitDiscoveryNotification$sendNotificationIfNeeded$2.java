package com.google.android.systemui.power;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.UserHandle;
import android.util.Log;
import androidx.core.app.NotificationCompat$BigTextStyle;
import androidx.core.app.NotificationCompat$Builder;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
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
final class ChargeLimitDiscoveryNotification$sendNotificationIfNeeded$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ChargeLimitDiscoveryNotification this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.power.ChargeLimitDiscoveryNotification$sendNotificationIfNeeded$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ ChargeLimitDiscoveryNotification this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ChargeLimitDiscoveryNotification chargeLimitDiscoveryNotification, Continuation continuation) {
            super(2, continuation);
            this.this$0 = chargeLimitDiscoveryNotification;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation);
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
            ChargeLimitDiscoveryNotification chargeLimitDiscoveryNotification = this.this$0;
            chargeLimitDiscoveryNotification.getClass();
            Log.d("ChargeLimitDiscoveryNotification", "sendNotification");
            String string = chargeLimitDiscoveryNotification.context.getString(R.string.charge_limit_discovery_notification_text);
            NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(chargeLimitDiscoveryNotification.context, "BAT");
            notificationCompat$Builder.mNotification.icon = R.drawable.ic_battery_charging;
            notificationCompat$Builder.mContentTitle = NotificationCompat$Builder.limitCharSequenceLength(chargeLimitDiscoveryNotification.context.getString(R.string.charge_limit_discovery_notification_title));
            notificationCompat$Builder.mContentText = NotificationCompat$Builder.limitCharSequenceLength(string);
            notificationCompat$Builder.mContentIntent = PowerUtils.createPendingIntent(chargeLimitDiscoveryNotification.context, "systemui.power.action.clickChargeLimitNotification", null);
            notificationCompat$Builder.mNotification.deleteIntent = PowerUtils.createPendingIntent(chargeLimitDiscoveryNotification.context, "systemui.power.action.dismissChargeLimitNotification", null);
            NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
            notificationCompat$BigTextStyle.mBigText = NotificationCompat$Builder.limitCharSequenceLength(string);
            notificationCompat$Builder.setStyle(notificationCompat$BigTextStyle);
            notificationCompat$Builder.setFlag(16, true);
            notificationCompat$Builder.setFlag(2, true);
            notificationCompat$Builder.mSilent = true;
            notificationCompat$Builder.addAction(chargeLimitDiscoveryNotification.context.getString(R.string.battery_health_notify_learn_more), PowerUtils.createHelpArticlePendingIntentAsUser(R.string.charge_limit_discovery_notification_help_url, chargeLimitDiscoveryNotification.context));
            notificationCompat$Builder.addAction(chargeLimitDiscoveryNotification.context.getString(R.string.charge_limit_discovery_notification_enable_button), PowerUtils.createPendingIntent(chargeLimitDiscoveryNotification.context, "systemui.power.action.enableChargeLimitFeature", null));
            notificationCompat$Builder.mLocalOnly = true;
            PowerUtils.overrideNotificationAppName(chargeLimitDiscoveryNotification.context, notificationCompat$Builder);
            NotificationManager notificationManager = chargeLimitDiscoveryNotification.notificationManager;
            if (notificationManager != null) {
                notificationManager.notifyAsUser("charge_limit", R.string.charge_limit_discovery_notification_title, notificationCompat$Builder.build(), UserHandle.CURRENT);
            }
            UiEventLogger uiEventLogger = chargeLimitDiscoveryNotification.uiEventLogger;
            if (uiEventLogger != null) {
                uiEventLogger.log(BatteryMetricEvent.SEND_CHARGE_LIMIT_DISCOVERY_NOTIFICATION);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeLimitDiscoveryNotification$sendNotificationIfNeeded$2(ChargeLimitDiscoveryNotification chargeLimitDiscoveryNotification, Continuation continuation) {
        super(2, continuation);
        this.this$0 = chargeLimitDiscoveryNotification;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ChargeLimitDiscoveryNotification$sendNotificationIfNeeded$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ChargeLimitDiscoveryNotification$sendNotificationIfNeeded$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SharedPreferences sharedPreferences = (SharedPreferences) this.this$0.sharedPreferences$delegate.getValue();
            int currentUser = ActivityManager.getCurrentUser();
            StringBuilder sb = new StringBuilder();
            sb.append(currentUser);
            sb.append("|last_charge_limit_notification_time");
            boolean z = sharedPreferences.getLong(sb.toString(), -1L) == -1;
            MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("showNotification: ", "ChargeLimitDiscoveryNotification", z);
            if (z) {
                ChargeLimitDiscoveryNotification chargeLimitDiscoveryNotification = this.this$0;
                chargeLimitDiscoveryNotification.getClass();
                long currentTimeMillis = System.currentTimeMillis();
                String str = ActivityManager.getCurrentUser() + "|last_charge_limit_notification_time";
                Log.d("ChargeLimitDiscoveryNotification", "putTimestamp: " + currentTimeMillis + ", key: " + str);
                ((SharedPreferences) chargeLimitDiscoveryNotification.sharedPreferences$delegate.getValue()).edit().putLong(str, currentTimeMillis).apply();
                ChargeLimitDiscoveryNotification chargeLimitDiscoveryNotification2 = this.this$0;
                CoroutineDispatcher coroutineDispatcher = chargeLimitDiscoveryNotification2.mainDispatcher;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(chargeLimitDiscoveryNotification2, null);
                this.label = 1;
                if (BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
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
