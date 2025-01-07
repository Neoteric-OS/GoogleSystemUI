package com.android.systemui.statusbar.policy;

import android.os.SystemClock;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.util.time.SystemClockImpl;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BaseHeadsUpManager$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ BaseHeadsUpManager$$ExternalSyntheticLambda5(BaseHeadsUpManager.HeadsUpEntry headsUpEntry, boolean z, String str) {
        this.f$0 = headsUpEntry;
        this.f$2 = z;
        this.f$1 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((BaseHeadsUpManager) this.f$0).updateNotificationInternal(this.f$1, this.f$2);
                break;
            default:
                BaseHeadsUpManager.HeadsUpEntry headsUpEntry = (BaseHeadsUpManager.HeadsUpEntry) this.f$0;
                boolean z = this.f$2;
                String str = this.f$1;
                HeadsUpManagerLogger headsUpManagerLogger = headsUpEntry.this$0.mLogger;
                NotificationEntry notificationEntry = headsUpEntry.mEntry;
                headsUpManagerLogger.getClass();
                LogLevel logLevel = LogLevel.INFO;
                HeadsUpManagerLogger$logUpdateEntry$2 headsUpManagerLogger$logUpdateEntry$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logUpdateEntry$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        String str1 = logMessage.getStr1();
                        boolean bool1 = logMessage.getBool1();
                        String str2 = logMessage.getStr2();
                        StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("update entry ", str1, " updatePostTime: ", bool1, " reason: ");
                        m.append(str2);
                        return m.toString();
                    }
                };
                LogBuffer logBuffer = headsUpManagerLogger.buffer;
                LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logUpdateEntry$2, null);
                LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
                logMessageImpl.bool1 = z;
                logMessageImpl.str2 = str;
                logBuffer.commit(obtain);
                ((SystemClockImpl) headsUpEntry.this$0.mSystemClock).getClass();
                long elapsedRealtime = SystemClock.elapsedRealtime();
                headsUpEntry.mEarliestRemovalTime = headsUpEntry.this$0.mMinimumDisplayTime + elapsedRealtime;
                if (z) {
                    headsUpEntry.mPostTime = Math.max(headsUpEntry.mPostTime, elapsedRealtime);
                    break;
                }
                break;
        }
    }

    public /* synthetic */ BaseHeadsUpManager$$ExternalSyntheticLambda5(BaseHeadsUpManager baseHeadsUpManager, String str, boolean z) {
        this.f$0 = baseHeadsUpManager;
        this.f$1 = str;
        this.f$2 = z;
    }
}
