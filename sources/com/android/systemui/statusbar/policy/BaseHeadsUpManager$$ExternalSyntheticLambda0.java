package com.android.systemui.statusbar.policy;

import android.os.SystemClock;
import android.util.ArrayMap;
import android.util.EventLog;
import android.util.Log;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BaseHeadsUpManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;
    public final /* synthetic */ BaseHeadsUpManager.HeadsUpEntry f$2;

    public /* synthetic */ BaseHeadsUpManager$$ExternalSyntheticLambda0(BaseHeadsUpManager.HeadsUpEntry headsUpEntry, BaseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0 baseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0, String str) {
        this.$r8$classId = 2;
        this.f$2 = headsUpEntry;
        this.f$0 = baseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0;
        this.f$1 = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ExpandableNotificationRow expandableNotificationRow;
        ExpandableNotificationRow expandableNotificationRow2;
        long elapsedRealtime;
        int i;
        switch (this.$r8$classId) {
            case 0:
                HeadsUpManagerPhone headsUpManagerPhone = (HeadsUpManagerPhone) this.f$0;
                String str = (String) this.f$1;
                BaseHeadsUpManager.HeadsUpEntry headsUpEntry = this.f$2;
                HeadsUpManagerLogger headsUpManagerLogger = headsUpManagerPhone.mLogger;
                headsUpManagerLogger.getClass();
                LogLevel logLevel = LogLevel.INFO;
                HeadsUpManagerLogger$logUnpinEntry$2 headsUpManagerLogger$logUnpinEntry$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logUnpinEntry$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("unpin entry ", ((LogMessage) obj).getStr1());
                    }
                };
                LogBuffer logBuffer = headsUpManagerLogger.buffer;
                LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logUnpinEntry$2, null);
                ((LogMessageImpl) obtain).str1 = str.replace("\n", "");
                logBuffer.commit(obtain);
                headsUpManagerPhone.setEntryPinned(headsUpEntry, false, "unpinAll");
                headsUpEntry.updateEntry("unpinAll", false);
                NotificationEntry notificationEntry = headsUpEntry.mEntry;
                if (notificationEntry != null && (expandableNotificationRow = notificationEntry.row) != null && expandableNotificationRow.mustStayOnScreen() && (expandableNotificationRow2 = headsUpEntry.mEntry.row) != null) {
                    expandableNotificationRow2.mMustStayOnScreen = false;
                    break;
                }
                break;
            case 1:
                BaseHeadsUpManager baseHeadsUpManager = (BaseHeadsUpManager) this.f$0;
                NotificationEntry notificationEntry2 = (NotificationEntry) this.f$1;
                HeadsUpManagerPhone.HeadsUpEntryPhone headsUpEntryPhone = (HeadsUpManagerPhone.HeadsUpEntryPhone) this.f$2;
                HeadsUpManagerLogger headsUpManagerLogger2 = baseHeadsUpManager.mLogger;
                headsUpManagerLogger2.getClass();
                LogLevel logLevel2 = LogLevel.INFO;
                HeadsUpManagerLogger$logShowNotification$2 headsUpManagerLogger$logShowNotification$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logShowNotification$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("show notification ", ((LogMessage) obj).getStr1());
                    }
                };
                LogBuffer logBuffer2 = headsUpManagerLogger2.buffer;
                LogMessage obtain2 = logBuffer2.obtain("HeadsUpManager", logLevel2, headsUpManagerLogger$logShowNotification$2, null);
                ((LogMessageImpl) obtain2).str1 = NotificationUtilsKt.getLogKey(notificationEntry2);
                logBuffer2.commit(obtain2);
                ArrayMap arrayMap = baseHeadsUpManager.mHeadsUpEntryMap;
                String str2 = notificationEntry2.mKey;
                arrayMap.put(str2, headsUpEntryPhone);
                HeadsUpManagerPhone headsUpManagerPhone2 = (HeadsUpManagerPhone) baseHeadsUpManager;
                NotificationEntry notificationEntry3 = headsUpEntryPhone.mEntry;
                notificationEntry3.setHeadsUp(true);
                headsUpManagerPhone2.setEntryPinned(headsUpEntryPhone, headsUpManagerPhone2.shouldHeadsUpBecomePinned(notificationEntry3), "onEntryAdded");
                EventLog.writeEvent(36001, notificationEntry3.mKey, 1);
                Iterator it = headsUpManagerPhone2.mListeners.listeners.iterator();
                while (it.hasNext()) {
                    ((OnHeadsUpChangedListener) it.next()).onHeadsUpStateChanged(notificationEntry3, true);
                }
                headsUpManagerPhone2.updateTopHeadsUpFlow();
                HashSet hashSet = new HashSet(headsUpManagerPhone2.mHeadsUpEntryMap.values());
                StateFlowImpl stateFlowImpl = headsUpManagerPhone2.mHeadsUpNotificationRows;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, hashSet);
                ExpandableNotificationRow expandableNotificationRow3 = notificationEntry2.row;
                if (expandableNotificationRow3 != null) {
                    expandableNotificationRow3.sendAccessibilityEvent(2048);
                }
                notificationEntry2.mIsHeadsUpEntry = true;
                baseHeadsUpManager.updateNotificationInternal(str2, true);
                notificationEntry2.interruption = true;
                break;
            default:
                BaseHeadsUpManager.HeadsUpEntry headsUpEntry2 = this.f$2;
                BaseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0 baseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0 = (BaseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0) this.f$0;
                String str3 = (String) this.f$1;
                headsUpEntry2.getClass();
                switch (baseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0.$r8$classId) {
                    case 0:
                        BaseHeadsUpManager.HeadsUpEntry headsUpEntry3 = baseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0.f$0;
                        long j = headsUpEntry3.mEarliestRemovalTime;
                        ((SystemClockImpl) headsUpEntry3.this$0.mSystemClock).getClass();
                        elapsedRealtime = j - SystemClock.elapsedRealtime();
                        break;
                    default:
                        BaseHeadsUpManager.HeadsUpEntry headsUpEntry4 = baseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0.f$0;
                        headsUpEntry4.getClass();
                        HeadsUpManagerPhone.HeadsUpEntryPhone headsUpEntryPhone2 = (HeadsUpManagerPhone.HeadsUpEntryPhone) headsUpEntry4;
                        NotificationEntry notificationEntry4 = headsUpEntryPhone2.mEntry;
                        if (notificationEntry4 == null ? false : notificationEntry4.isStickyAndNotDemoted()) {
                            i = ((BaseHeadsUpManager.HeadsUpEntry) headsUpEntryPhone2).this$0.mStickyForSomeTimeAutoDismissTime;
                        } else {
                            HeadsUpManagerPhone headsUpManagerPhone3 = ((BaseHeadsUpManager.HeadsUpEntry) headsUpEntryPhone2).this$0;
                            headsUpManagerPhone3.mAvalancheController.getClass();
                            i = headsUpManagerPhone3.mAutoDismissTime;
                        }
                        long recommendedTimeoutMillis = headsUpEntryPhone2.mPostTime + ((BaseHeadsUpManager.HeadsUpEntry) headsUpEntryPhone2).this$0.mAccessibilityMgr.mAccessibilityManager.getRecommendedTimeoutMillis(i, 7);
                        int i2 = headsUpEntryPhone2.extended ? HeadsUpManagerPhone.this.mExtensionTime : 0;
                        ((SystemClockImpl) headsUpEntry4.this$0.mSystemClock).getClass();
                        elapsedRealtime = Math.max((recommendedTimeoutMillis + i2) - SystemClock.elapsedRealtime(), headsUpEntry4.this$0.mMinimumDisplayTime);
                        break;
                }
                if (headsUpEntry2.mRemoveRunnable != null) {
                    ExecutorImpl.ExecutionToken executionToken = headsUpEntry2.mCancelRemoveRunnable;
                    boolean z = executionToken != null;
                    if (z) {
                        executionToken.run();
                        headsUpEntry2.mCancelRemoveRunnable = null;
                    }
                    headsUpEntry2.mCancelRemoveRunnable = headsUpEntry2.this$0.mExecutor.executeDelayed(headsUpEntry2.mRemoveRunnable, elapsedRealtime);
                    if (!z) {
                        HeadsUpManagerLogger headsUpManagerLogger3 = headsUpEntry2.this$0.mLogger;
                        NotificationEntry notificationEntry5 = headsUpEntry2.mEntry;
                        headsUpManagerLogger3.getClass();
                        LogLevel logLevel3 = LogLevel.INFO;
                        HeadsUpManagerLogger$logAutoRemoveScheduled$2 headsUpManagerLogger$logAutoRemoveScheduled$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logAutoRemoveScheduled$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LogMessage logMessage = (LogMessage) obj;
                                String str1 = logMessage.getStr1();
                                long long1 = logMessage.getLong1();
                                String str22 = logMessage.getStr2();
                                StringBuilder sb = new StringBuilder("schedule auto remove of ");
                                sb.append(str1);
                                sb.append(" in ");
                                sb.append(long1);
                                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, " ms reason: ", str22);
                            }
                        };
                        LogBuffer logBuffer3 = headsUpManagerLogger3.buffer;
                        LogMessage obtain3 = logBuffer3.obtain("HeadsUpManager", logLevel3, headsUpManagerLogger$logAutoRemoveScheduled$2, null);
                        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain3;
                        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry5);
                        logMessageImpl.long1 = elapsedRealtime;
                        logMessageImpl.str2 = str3;
                        logBuffer3.commit(obtain3);
                        break;
                    } else {
                        HeadsUpManagerLogger headsUpManagerLogger4 = headsUpEntry2.this$0.mLogger;
                        NotificationEntry notificationEntry6 = headsUpEntry2.mEntry;
                        headsUpManagerLogger4.getClass();
                        LogLevel logLevel4 = LogLevel.INFO;
                        HeadsUpManagerLogger$logAutoRemoveRescheduled$2 headsUpManagerLogger$logAutoRemoveRescheduled$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logAutoRemoveRescheduled$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LogMessage logMessage = (LogMessage) obj;
                                String str1 = logMessage.getStr1();
                                long long1 = logMessage.getLong1();
                                String str22 = logMessage.getStr2();
                                StringBuilder sb = new StringBuilder("reschedule auto remove of ");
                                sb.append(str1);
                                sb.append(" in ");
                                sb.append(long1);
                                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, " ms reason: ", str22);
                            }
                        };
                        LogBuffer logBuffer4 = headsUpManagerLogger4.buffer;
                        LogMessage obtain4 = logBuffer4.obtain("HeadsUpManager", logLevel4, headsUpManagerLogger$logAutoRemoveRescheduled$2, null);
                        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain4;
                        logMessageImpl2.str1 = NotificationUtilsKt.getLogKey(notificationEntry6);
                        logMessageImpl2.long1 = elapsedRealtime;
                        logMessageImpl2.str2 = str3;
                        logBuffer4.commit(obtain4);
                        break;
                    }
                } else {
                    Log.wtf("BaseHeadsUpManager", "scheduleAutoRemovalCallback with no callback set");
                    break;
                }
        }
    }

    public /* synthetic */ BaseHeadsUpManager$$ExternalSyntheticLambda0(BaseHeadsUpManager baseHeadsUpManager, Object obj, BaseHeadsUpManager.HeadsUpEntry headsUpEntry, int i) {
        this.$r8$classId = i;
        this.f$0 = baseHeadsUpManager;
        this.f$1 = obj;
        this.f$2 = headsUpEntry;
    }
}
