package com.android.systemui.statusbar.notification.collection.coordinator;

import android.os.Build;
import android.util.IndentingPrintWriter;
import android.util.Log;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner;
import com.android.systemui.statusbar.notification.data.repository.ActiveNotificationListRepository;
import com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.SeenNotificationsInteractor;
import com.android.systemui.util.DumpUtilsKt;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockScreenMinimalismCoordinator implements Coordinator, Dumpable {
    public final HeadsUpNotificationInteractor headsUpInteractor;
    public final LockScreenMinimalismCoordinatorLogger logger;
    public final SeenNotificationsInteractor seenNotificationsInteractor;
    public final ShadeInteractor shadeInteractor;
    public final StatusBarStateController statusBarStateController;
    public final LockScreenMinimalismCoordinator$topUnseenSectioner$1 topOngoingSectioner;
    public final LockScreenMinimalismCoordinator$topUnseenSectioner$1 topUnseenSectioner;
    public final LockScreenMinimalismCoordinator$unseenNotifPromoter$1 unseenNotifPromoter;
    public final Set unseenNotifications = new LinkedHashSet();

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.SECONDS;
        DurationKt.toDuration(0.25d, durationUnit);
        DurationKt.toDuration(0.75d, durationUnit);
    }

    public LockScreenMinimalismCoordinator(DumpManager dumpManager, HeadsUpNotificationInteractor headsUpNotificationInteractor, LockScreenMinimalismCoordinatorLogger lockScreenMinimalismCoordinatorLogger, CoroutineScope coroutineScope, SeenNotificationsInteractor seenNotificationsInteractor, StatusBarStateController statusBarStateController, ShadeInteractor shadeInteractor) {
        this.seenNotificationsInteractor = seenNotificationsInteractor;
        this.statusBarStateController = statusBarStateController;
        new LockScreenMinimalismCoordinator$unseenNotifPromoter$1("LockScreenMinimalismCoordinator");
        final String str = "TopOngoing";
        final int i = 8;
        final int i2 = 1;
        new NotifSectioner(str, i) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.LockScreenMinimalismCoordinator$topUnseenSectioner$1
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final boolean isInSection(ListEntry listEntry) {
                switch (i2) {
                    case 0:
                        if (!Log.isLoggable("RefactorFlagAssert", 7)) {
                            if (Log.isLoggable("RefactorFlag", 5)) {
                                Log.w("RefactorFlag", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.");
                                break;
                            }
                        } else {
                            Log.wtf("RefactorFlagAssert", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.") : null);
                            break;
                        }
                        break;
                    default:
                        if (!Log.isLoggable("RefactorFlagAssert", 7)) {
                            if (Log.isLoggable("RefactorFlag", 5)) {
                                Log.w("RefactorFlag", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.");
                                break;
                            }
                        } else {
                            Log.wtf("RefactorFlagAssert", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.") : null);
                            break;
                        }
                        break;
                }
                return false;
            }
        };
        final String str2 = "TopUnseen";
        final int i3 = 9;
        final int i4 = 0;
        new NotifSectioner(str2, i3) { // from class: com.android.systemui.statusbar.notification.collection.coordinator.LockScreenMinimalismCoordinator$topUnseenSectioner$1
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifSectioner
            public final boolean isInSection(ListEntry listEntry) {
                switch (i4) {
                    case 0:
                        if (!Log.isLoggable("RefactorFlagAssert", 7)) {
                            if (Log.isLoggable("RefactorFlag", 5)) {
                                Log.w("RefactorFlag", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.");
                                break;
                            }
                        } else {
                            Log.wtf("RefactorFlagAssert", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.") : null);
                            break;
                        }
                        break;
                    default:
                        if (!Log.isLoggable("RefactorFlagAssert", 7)) {
                            if (Log.isLoggable("RefactorFlag", 5)) {
                                Log.w("RefactorFlag", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.");
                                break;
                            }
                        } else {
                            Log.wtf("RefactorFlagAssert", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.") : null);
                            break;
                        }
                        break;
                }
                return false;
            }
        };
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.");
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        ActiveNotificationListRepository activeNotificationListRepository = this.seenNotificationsInteractor.notificationListRepository;
        asIndenting.append("SeenNotificationsInteractor").println(":");
        asIndenting.increaseIndent();
        try {
            asIndenting.print("hasFilteredOutSeenNotifications", activeNotificationListRepository.hasFilteredOutSeenNotifications.getValue());
            asIndenting.print("topOngoingNotificationKey", activeNotificationListRepository.topOngoingNotificationKey.getValue());
            asIndenting.print("topUnseenNotificationKey", activeNotificationListRepository.topUnseenNotificationKey.getValue());
            asIndenting.decreaseIndent();
            Set set = this.unseenNotifications;
            asIndenting.append("unseen notifications").append((CharSequence) ": ").println(set.size());
            asIndenting.increaseIndent();
            try {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    asIndenting.println(((NotificationEntry) it.next()).mKey);
                }
            } finally {
            }
        } finally {
        }
    }

    public static /* synthetic */ void getUnseenNotifPromoter$annotations() {
    }
}
