package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$$ExternalSyntheticLambda3;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeEventCoordinator implements Coordinator {
    public boolean mEntryRemoved;
    public boolean mEntryRemovedByUser;
    public final ShadeEventCoordinatorLogger mLogger;
    public final Executor mMainExecutor;
    public final ShadeEventCoordinator$mNotifCollectionListener$1 mNotifCollectionListener = new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.ShadeEventCoordinator$mNotifCollectionListener$1
        @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
        public final void onEntryRemoved(NotificationEntry notificationEntry, int i) {
            ShadeEventCoordinator shadeEventCoordinator = ShadeEventCoordinator.this;
            boolean z = true;
            shadeEventCoordinator.mEntryRemoved = true;
            if (i != 1 && i != 3 && i != 2) {
                z = false;
            }
            shadeEventCoordinator.mEntryRemovedByUser = z;
        }
    };
    public StatusBarNotificationPresenter$$ExternalSyntheticLambda3 mNotifRemovedByUserCallback;
    public StatusBarNotificationPresenter$$ExternalSyntheticLambda3 mShadeEmptiedCallback;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.notification.collection.coordinator.ShadeEventCoordinator$mNotifCollectionListener$1] */
    public ShadeEventCoordinator(Executor executor, ShadeEventCoordinatorLogger shadeEventCoordinatorLogger) {
        this.mMainExecutor = executor;
        this.mLogger = shadeEventCoordinatorLogger;
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.addCollectionListener(this.mNotifCollectionListener);
        notifPipeline.addOnBeforeRenderListListener(new OnBeforeRenderListListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.ShadeEventCoordinator$attach$1
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener
            public final void onBeforeRenderList$1(List list) {
                ShadeEventCoordinator shadeEventCoordinator = ShadeEventCoordinator.this;
                boolean z = shadeEventCoordinator.mEntryRemoved;
                LogBuffer logBuffer = shadeEventCoordinator.mLogger.buffer;
                if (z && list.isEmpty()) {
                    logBuffer.commit(logBuffer.obtain("ShadeEventCoordinator", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.ShadeEventCoordinatorLogger$logShadeEmptied$2
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            return "Shade emptied";
                        }
                    }, null));
                    StatusBarNotificationPresenter$$ExternalSyntheticLambda3 statusBarNotificationPresenter$$ExternalSyntheticLambda3 = shadeEventCoordinator.mShadeEmptiedCallback;
                    if (statusBarNotificationPresenter$$ExternalSyntheticLambda3 != null) {
                        shadeEventCoordinator.mMainExecutor.execute(statusBarNotificationPresenter$$ExternalSyntheticLambda3);
                    }
                }
                if (shadeEventCoordinator.mEntryRemoved && shadeEventCoordinator.mEntryRemovedByUser) {
                    logBuffer.commit(logBuffer.obtain("ShadeEventCoordinator", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.ShadeEventCoordinatorLogger$logNotifRemovedByUser$2
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            return "Notification removed by user";
                        }
                    }, null));
                    StatusBarNotificationPresenter$$ExternalSyntheticLambda3 statusBarNotificationPresenter$$ExternalSyntheticLambda32 = shadeEventCoordinator.mNotifRemovedByUserCallback;
                    if (statusBarNotificationPresenter$$ExternalSyntheticLambda32 != null) {
                        shadeEventCoordinator.mMainExecutor.execute(statusBarNotificationPresenter$$ExternalSyntheticLambda32);
                    }
                }
                shadeEventCoordinator.mEntryRemoved = false;
                shadeEventCoordinator.mEntryRemovedByUser = false;
            }
        });
    }
}
