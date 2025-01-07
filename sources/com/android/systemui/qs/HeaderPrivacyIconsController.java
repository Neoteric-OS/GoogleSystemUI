package com.android.systemui.qs;

import android.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.permission.PermissionManager;
import android.safetycenter.SafetyCenterManager;
import android.view.View;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.appops.AppOpsController;
import com.android.systemui.appops.AppOpsControllerImpl;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.privacy.OngoingPrivacyChip;
import com.android.systemui.privacy.PrivacyChipEvent;
import com.android.systemui.privacy.PrivacyDialogController;
import com.android.systemui.privacy.PrivacyDialogControllerV2;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.privacy.logging.PrivacyLogger;
import com.android.systemui.shade.ShadeHeaderController$updateListeners$1;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HeaderPrivacyIconsController {
    public final ActivityStarter activityStarter;
    public final AppOpsController appOpsController;
    public final HeaderPrivacyIconsController$attachStateChangeListener$1 attachStateChangeListener;
    public final Executor backgroundExecutor;
    public final BroadcastDispatcher broadcastDispatcher;
    public final String cameraSlot;
    public ShadeHeaderController$updateListeners$1 chipVisibilityListener;
    public final DeviceProvisionedController deviceProvisionedController;
    public final FeatureFlags featureFlags;
    public final StatusIconContainer iconContainer;
    public boolean listening;
    public boolean locationIndicatorsEnabled;
    public final String locationSlot;
    public boolean micCameraIndicatorsEnabled;
    public final String micSlot;
    public final PermissionManager permissionManager;
    public final HeaderPrivacyIconsController$picCallback$1 picCallback;
    public final OngoingPrivacyChip privacyChip;
    public boolean privacyChipLogged;
    public final PrivacyDialogController privacyDialogController;
    public final PrivacyDialogControllerV2 privacyDialogControllerV2;
    public final PrivacyItemController privacyItemController;
    public final PrivacyLogger privacyLogger;
    public boolean safetyCenterEnabled;
    public final SafetyCenterManager safetyCenterManager;
    public final HeaderPrivacyIconsController$safetyCenterReceiver$1 safetyCenterReceiver;
    public final UiEventLogger uiEventLogger;
    public final Executor uiExecutor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.HeaderPrivacyIconsController$1, reason: invalid class name */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ HeaderPrivacyIconsController this$0;

        public /* synthetic */ AnonymousClass1(HeaderPrivacyIconsController headerPrivacyIconsController, int i) {
            this.$r8$classId = i;
            this.this$0 = headerPrivacyIconsController;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    HeaderPrivacyIconsController headerPrivacyIconsController = this.this$0;
                    headerPrivacyIconsController.safetyCenterEnabled = headerPrivacyIconsController.safetyCenterManager.isSafetyCenterEnabled();
                    break;
                default:
                    HeaderPrivacyIconsController headerPrivacyIconsController2 = this.this$0;
                    ArrayList<? extends Parcelable> arrayList = new ArrayList<>(headerPrivacyIconsController2.permissionManager.getIndicatorAppOpUsageData(((AppOpsControllerImpl) headerPrivacyIconsController2.appOpsController).mMicMuted));
                    this.this$0.privacyLogger.logUnfilteredPermGroupUsage(arrayList);
                    final Intent intent = new Intent("android.intent.action.VIEW_SAFETY_CENTER_QS");
                    intent.putParcelableArrayListExtra("android.permission.extra.PERMISSION_USAGES", arrayList);
                    intent.setFlags(268435456);
                    final HeaderPrivacyIconsController headerPrivacyIconsController3 = this.this$0;
                    headerPrivacyIconsController3.uiExecutor.execute(new Runnable() { // from class: com.android.systemui.qs.HeaderPrivacyIconsController$showSafetyCenter$1$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            HeaderPrivacyIconsController headerPrivacyIconsController4 = HeaderPrivacyIconsController.this;
                            headerPrivacyIconsController4.activityStarter.startActivity(intent, true, (ActivityTransitionAnimator.Controller) ActivityTransitionAnimator.Controller.Companion.fromView$default(headerPrivacyIconsController4.privacyChip, null, 30));
                        }
                    });
                    break;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.qs.HeaderPrivacyIconsController$picCallback$1] */
    /* JADX WARN: Type inference failed for: r4v9, types: [android.content.BroadcastReceiver, com.android.systemui.qs.HeaderPrivacyIconsController$safetyCenterReceiver$1] */
    public HeaderPrivacyIconsController(PrivacyItemController privacyItemController, UiEventLogger uiEventLogger, OngoingPrivacyChip ongoingPrivacyChip, PrivacyDialogController privacyDialogController, PrivacyDialogControllerV2 privacyDialogControllerV2, PrivacyLogger privacyLogger, StatusIconContainer statusIconContainer, PermissionManager permissionManager, Executor executor, Executor executor2, ActivityStarter activityStarter, AppOpsController appOpsController, BroadcastDispatcher broadcastDispatcher, SafetyCenterManager safetyCenterManager, DeviceProvisionedController deviceProvisionedController, FeatureFlags featureFlags) {
        this.privacyItemController = privacyItemController;
        this.uiEventLogger = uiEventLogger;
        this.privacyChip = ongoingPrivacyChip;
        this.privacyDialogController = privacyDialogController;
        this.privacyDialogControllerV2 = privacyDialogControllerV2;
        this.privacyLogger = privacyLogger;
        this.iconContainer = statusIconContainer;
        this.permissionManager = permissionManager;
        this.backgroundExecutor = executor;
        this.uiExecutor = executor2;
        this.activityStarter = activityStarter;
        this.appOpsController = appOpsController;
        this.broadcastDispatcher = broadcastDispatcher;
        this.safetyCenterManager = safetyCenterManager;
        this.deviceProvisionedController = deviceProvisionedController;
        this.featureFlags = featureFlags;
        this.cameraSlot = ongoingPrivacyChip.getResources().getString(R.string.stk_cc_ss_to_ss);
        this.micSlot = ongoingPrivacyChip.getResources().getString(R.string.supervised_user_creation_label);
        this.locationSlot = ongoingPrivacyChip.getResources().getString(R.string.storage_usb_drive_label);
        ?? r4 = new BroadcastReceiver() { // from class: com.android.systemui.qs.HeaderPrivacyIconsController$safetyCenterReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                HeaderPrivacyIconsController headerPrivacyIconsController = HeaderPrivacyIconsController.this;
                headerPrivacyIconsController.safetyCenterEnabled = headerPrivacyIconsController.safetyCenterManager.isSafetyCenterEnabled();
            }
        };
        this.safetyCenterReceiver = r4;
        View.OnAttachStateChangeListener onAttachStateChangeListener = new View.OnAttachStateChangeListener() { // from class: com.android.systemui.qs.HeaderPrivacyIconsController$attachStateChangeListener$1
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                HeaderPrivacyIconsController headerPrivacyIconsController = HeaderPrivacyIconsController.this;
                BroadcastDispatcher.registerReceiver$default(headerPrivacyIconsController.broadcastDispatcher, headerPrivacyIconsController.safetyCenterReceiver, new IntentFilter("android.safetycenter.action.SAFETY_CENTER_ENABLED_CHANGED"), HeaderPrivacyIconsController.this.backgroundExecutor, null, 0, 56);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
                HeaderPrivacyIconsController headerPrivacyIconsController = HeaderPrivacyIconsController.this;
                headerPrivacyIconsController.broadcastDispatcher.unregisterReceiver(headerPrivacyIconsController.safetyCenterReceiver);
            }
        };
        executor.execute(new AnonymousClass1(this, 0));
        if (ongoingPrivacyChip.isAttachedToWindow()) {
            BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, r4, new IntentFilter("android.safetycenter.action.SAFETY_CENTER_ENABLED_CHANGED"), executor, null, 0, 56);
        }
        ongoingPrivacyChip.addOnAttachStateChangeListener(onAttachStateChangeListener);
        this.picCallback = new PrivacyItemController.Callback() { // from class: com.android.systemui.qs.HeaderPrivacyIconsController$picCallback$1
            @Override // com.android.systemui.privacy.PrivacyConfig.Callback
            public final void onFlagLocationChanged(boolean z) {
                HeaderPrivacyIconsController headerPrivacyIconsController = HeaderPrivacyIconsController.this;
                if (headerPrivacyIconsController.locationIndicatorsEnabled != z) {
                    headerPrivacyIconsController.locationIndicatorsEnabled = z;
                    update$12();
                }
            }

            @Override // com.android.systemui.privacy.PrivacyConfig.Callback
            public final void onFlagMicCameraChanged(boolean z) {
                HeaderPrivacyIconsController headerPrivacyIconsController = HeaderPrivacyIconsController.this;
                if (headerPrivacyIconsController.micCameraIndicatorsEnabled != z) {
                    headerPrivacyIconsController.micCameraIndicatorsEnabled = z;
                    update$12();
                }
            }

            @Override // com.android.systemui.privacy.PrivacyItemController.Callback
            public final void onPrivacyItemsChanged(List list) {
                HeaderPrivacyIconsController headerPrivacyIconsController = HeaderPrivacyIconsController.this;
                headerPrivacyIconsController.privacyChip.setPrivacyList(list);
                headerPrivacyIconsController.setChipVisibility(!list.isEmpty());
            }

            public final void update$12() {
                HeaderPrivacyIconsController headerPrivacyIconsController = HeaderPrivacyIconsController.this;
                headerPrivacyIconsController.updatePrivacyIconSlots();
                headerPrivacyIconsController.setChipVisibility(!headerPrivacyIconsController.privacyChip.privacyList.isEmpty());
            }
        };
    }

    public final void setChipVisibility(boolean z) {
        PrivacyLogger privacyLogger = this.privacyLogger;
        if (z && (this.micCameraIndicatorsEnabled || this.locationIndicatorsEnabled)) {
            privacyLogger.logChipVisible(true);
            if (!this.privacyChipLogged && this.listening) {
                this.privacyChipLogged = true;
                this.uiEventLogger.log(PrivacyChipEvent.ONGOING_INDICATORS_CHIP_VIEW);
            }
        } else {
            privacyLogger.logChipVisible(false);
        }
        this.privacyChip.setVisibility(z ? 0 : 8);
        ShadeHeaderController$updateListeners$1 shadeHeaderController$updateListeners$1 = this.chipVisibilityListener;
        if (shadeHeaderController$updateListeners$1 != null) {
            shadeHeaderController$updateListeners$1.onChipVisibilityRefreshed(z);
        }
    }

    public final void updatePrivacyIconSlots() {
        boolean z = this.micCameraIndicatorsEnabled;
        boolean z2 = z || this.locationIndicatorsEnabled;
        String str = this.locationSlot;
        String str2 = this.micSlot;
        String str3 = this.cameraSlot;
        StatusIconContainer statusIconContainer = this.iconContainer;
        if (!z2) {
            statusIconContainer.removeIgnoredSlot(str3);
            statusIconContainer.removeIgnoredSlot(str2);
            statusIconContainer.removeIgnoredSlot(str);
            return;
        }
        if (z) {
            statusIconContainer.addIgnoredSlot(str3);
            statusIconContainer.addIgnoredSlot(str2);
        } else {
            statusIconContainer.removeIgnoredSlot(str3);
            statusIconContainer.removeIgnoredSlot(str2);
        }
        if (this.locationIndicatorsEnabled) {
            statusIconContainer.addIgnoredSlot(str);
        } else {
            statusIconContainer.removeIgnoredSlot(str);
        }
    }
}
