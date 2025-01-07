package com.android.systemui.statusbar.notification.collection.coordinator;

import android.app.Notification;
import android.app.NotificationChannel;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.notification.DynamicPrivacyController;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.Invalidator;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.Assert;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SensitiveContentCoordinatorImpl extends Invalidator implements DynamicPrivacyController.Listener, OnBeforeRenderListListener, Coordinator {
    public final DeviceEntryInteractor deviceEntryInteractor;
    public final DynamicPrivacyController dynamicPrivacyController;
    public final KeyguardStateController keyguardStateController;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final NotificationLockscreenUserManager lockscreenUserManager;
    public final SensitiveContentCoordinatorImpl$onSensitiveStateChanged$1 onSensitiveStateChanged;
    public final SceneInteractor sceneInteractor;
    public final SensitiveContentCoordinatorImpl$screenshareSecretFilter$1 screenshareSecretFilter;
    public final SelectedUserInteractor selectedUserInteractor;
    public final SensitiveNotificationProtectionController sensitiveNotificationProtectionController;
    public final StatusBarStateController statusBarStateController;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinatorImpl$onSensitiveStateChanged$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinatorImpl$screenshareSecretFilter$1] */
    public SensitiveContentCoordinatorImpl(DynamicPrivacyController dynamicPrivacyController, NotificationLockscreenUserManager notificationLockscreenUserManager, KeyguardUpdateMonitor keyguardUpdateMonitor, StatusBarStateController statusBarStateController, KeyguardStateController keyguardStateController, SelectedUserInteractor selectedUserInteractor, SensitiveNotificationProtectionController sensitiveNotificationProtectionController, DeviceEntryInteractor deviceEntryInteractor, SceneInteractor sceneInteractor, CoroutineScope coroutineScope) {
        super("SensitiveContentInvalidator");
        this.dynamicPrivacyController = dynamicPrivacyController;
        this.lockscreenUserManager = notificationLockscreenUserManager;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.statusBarStateController = statusBarStateController;
        this.keyguardStateController = keyguardStateController;
        this.selectedUserInteractor = selectedUserInteractor;
        this.sensitiveNotificationProtectionController = sensitiveNotificationProtectionController;
        this.onSensitiveStateChanged = new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinatorImpl$onSensitiveStateChanged$1
            @Override // java.lang.Runnable
            public final void run() {
                SensitiveContentCoordinatorImpl.this.invalidateList("onSensitiveStateChanged");
            }
        };
        this.screenshareSecretFilter = new NotifFilter() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinatorImpl$screenshareSecretFilter$1
            {
                super("ScreenshareSecretFilter");
            }

            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
            public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
                NotificationChannel channel;
                Notification notification;
                return ((SensitiveNotificationProtectionControllerImpl) SensitiveContentCoordinatorImpl.this.sensitiveNotificationProtectionController).isSensitiveStateActive() && (((channel = notificationEntry.mRanking.getChannel()) != null && channel.getLockscreenVisibility() == -1) || ((notification = notificationEntry.mSbn.getNotification()) != null && notification.visibility == -1));
            }
        };
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        this.dynamicPrivacyController.mListeners.add(this);
        ((SensitiveNotificationProtectionControllerImpl) this.sensitiveNotificationProtectionController).mListeners.addIfAbsent(this.onSensitiveStateChanged);
        notifPipeline.addOnBeforeRenderListListener(this);
        ShadeListBuilder shadeListBuilder = notifPipeline.mShadeListBuilder;
        shadeListBuilder.getClass();
        Assert.isMainThread();
        shadeListBuilder.mPipelineState.requireState();
        this.mListener = new ShadeListBuilder$$ExternalSyntheticLambda0(shadeListBuilder, 4);
        notifPipeline.addFinalizeFilter(this.screenshareSecretFilter);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00c2  */
    @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onBeforeRenderList$1(java.util.List r14) {
        /*
            Method dump skipped, instructions count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinatorImpl.onBeforeRenderList$1(java.util.List):void");
    }

    @Override // com.android.systemui.statusbar.notification.DynamicPrivacyController.Listener
    public final void onDynamicPrivacyChanged() {
        invalidateList("onDynamicPrivacyChanged");
    }
}
