package com.android.systemui.ambient.statusbar.ui;

import android.app.AlarmManager;
import android.content.res.Resources;
import android.text.format.DateFormat;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.dreams.DreamLogger;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.dreams.DreamOverlayStatusBarItemsProvider;
import com.android.systemui.dreams.DreamOverlayStatusBarItemsProvider$$ExternalSyntheticLambda0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyControllerImpl;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.statusbar.policy.NextAlarmControllerImpl;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import com.android.systemui.statusbar.window.StatusBarWindowStateController;
import com.android.systemui.statusbar.window.StatusBarWindowStateListener;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.systemui.util.time.DateFormatUtil;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AmbientStatusBarViewController extends ViewController {
    public final AlarmManager mAlarmManager;
    public final CommunalSceneInteractor mCommunalSceneInteractor;
    public boolean mCommunalVisible;
    public final DateFormatUtil mDateFormatUtil;
    public final Optional mDreamOverlayNotificationCountProvider;
    public final DreamOverlayStateController.Callback mDreamOverlayStateCallback;
    public final DreamOverlayStateController mDreamOverlayStateController;
    public boolean mEntryAnimationsFinished;
    public final List mExtraStatusBarItems;
    public boolean mIsAttached;
    public final DreamLogger mLogger;
    public final Executor mMainExecutor;
    public final AmbientStatusBarViewController$$ExternalSyntheticLambda2 mNextAlarmCallback;
    public final NextAlarmController mNextAlarmController;
    public final PrivacyItemController mPrivacyItemController;
    public final AmbientStatusBarViewController$$ExternalSyntheticLambda5 mPrivacyItemControllerCallback;
    public final Resources mResources;
    public final AmbientStatusBarViewController$$ExternalSyntheticLambda1 mSensorCallback;
    public final IndividualSensorPrivacyController mSensorPrivacyController;
    public final DreamOverlayStatusBarItemsProvider mStatusBarItemsProvider;
    public final AmbientStatusBarViewController$$ExternalSyntheticLambda3 mStatusBarItemsProviderCallback;
    public final StatusBarWindowStateController mStatusBarWindowStateController;
    public final AmbientStatusBarViewController$$ExternalSyntheticLambda4 mStatusBarWindowStateListener;
    public final UserTracker mUserTracker;
    public final WifiInteractorImpl mWifiInteractor;
    public final AnonymousClass2 mZenModeCallback;
    public final ZenModeController mZenModeController;

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$$ExternalSyntheticLambda2] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$2] */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$$ExternalSyntheticLambda4] */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$$ExternalSyntheticLambda5] */
    public AmbientStatusBarViewController(AmbientStatusBarView ambientStatusBarView, Resources resources, Executor executor, AlarmManager alarmManager, NextAlarmController nextAlarmController, DateFormatUtil dateFormatUtil, IndividualSensorPrivacyController individualSensorPrivacyController, Optional optional, ZenModeController zenModeController, StatusBarWindowStateController statusBarWindowStateController, DreamOverlayStatusBarItemsProvider dreamOverlayStatusBarItemsProvider, DreamOverlayStateController dreamOverlayStateController, UserTracker userTracker, WifiInteractorImpl wifiInteractorImpl, PrivacyItemController privacyItemController, CommunalSceneInteractor communalSceneInteractor, LogBuffer logBuffer) {
        super(ambientStatusBarView);
        this.mExtraStatusBarItems = new ArrayList();
        this.mEntryAnimationsFinished = false;
        this.mDreamOverlayStateCallback = new DreamOverlayStateController.Callback() { // from class: com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController.1
            @Override // com.android.systemui.dreams.DreamOverlayStateController.Callback
            public final void onStateChanged() {
                AmbientStatusBarViewController ambientStatusBarViewController = AmbientStatusBarViewController.this;
                ambientStatusBarViewController.mEntryAnimationsFinished = ambientStatusBarViewController.mDreamOverlayStateController.containsState(4);
                ambientStatusBarViewController.updateVisibility$1();
                ambientStatusBarViewController.showIcon(7, R.string.assistant_attention_content_description, ambientStatusBarViewController.mDreamOverlayStateController.containsState(16));
            }
        };
        this.mSensorCallback = new IndividualSensorPrivacyController.Callback() { // from class: com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$$ExternalSyntheticLambda1
            @Override // com.android.systemui.statusbar.policy.IndividualSensorPrivacyController.Callback
            public final void onSensorBlockedChanged(int i, boolean z) {
                AmbientStatusBarViewController.this.updateMicCameraBlockedStatusIcon();
            }
        };
        this.mNextAlarmCallback = new NextAlarmController.NextAlarmChangeCallback() { // from class: com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$$ExternalSyntheticLambda2
            @Override // com.android.systemui.statusbar.policy.NextAlarmController.NextAlarmChangeCallback
            public final void onNextAlarmChanged(AlarmManager.AlarmClockInfo alarmClockInfo) {
                AmbientStatusBarViewController.this.updateAlarmStatusIcon();
            }
        };
        this.mZenModeCallback = new ZenModeController.Callback() { // from class: com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController.2
            @Override // com.android.systemui.statusbar.policy.ZenModeController.Callback
            public final void onZenChanged(int i) {
                AmbientStatusBarViewController ambientStatusBarViewController = AmbientStatusBarViewController.this;
                ambientStatusBarViewController.showIcon(6, R.string.priority_mode_dream_overlay_content_description, ((ZenModeControllerImpl) ambientStatusBarViewController.mZenModeController).mZenMode != 0);
            }
        };
        this.mStatusBarItemsProviderCallback = new AmbientStatusBarViewController$$ExternalSyntheticLambda3(this);
        this.mStatusBarWindowStateListener = new StatusBarWindowStateListener() { // from class: com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$$ExternalSyntheticLambda4
            @Override // com.android.systemui.statusbar.window.StatusBarWindowStateListener
            public final void onStatusBarWindowStateChanged(int i) {
                final AmbientStatusBarViewController ambientStatusBarViewController = AmbientStatusBarViewController.this;
                if (ambientStatusBarViewController.mIsAttached && ambientStatusBarViewController.mEntryAnimationsFinished) {
                    ambientStatusBarViewController.mMainExecutor.execute(new Runnable() { // from class: com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$$ExternalSyntheticLambda13
                        @Override // java.lang.Runnable
                        public final void run() {
                            AmbientStatusBarViewController.this.updateVisibility$1();
                        }
                    });
                }
            }
        };
        this.mPrivacyItemControllerCallback = new PrivacyItemController.Callback() { // from class: com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController$$ExternalSyntheticLambda5
            @Override // com.android.systemui.privacy.PrivacyItemController.Callback
            public final void onPrivacyItemsChanged(List list) {
                AmbientStatusBarViewController ambientStatusBarViewController = AmbientStatusBarViewController.this;
                ambientStatusBarViewController.getClass();
                ambientStatusBarViewController.showIcon(8, R.string.location_active_dream_overlay_content_description, list.stream().anyMatch(new AmbientStatusBarViewController$$ExternalSyntheticLambda11()));
            }
        };
        this.mResources = resources;
        this.mMainExecutor = executor;
        this.mAlarmManager = alarmManager;
        this.mNextAlarmController = nextAlarmController;
        this.mDateFormatUtil = dateFormatUtil;
        this.mSensorPrivacyController = individualSensorPrivacyController;
        this.mDreamOverlayNotificationCountProvider = optional;
        this.mStatusBarWindowStateController = statusBarWindowStateController;
        this.mStatusBarItemsProvider = dreamOverlayStatusBarItemsProvider;
        this.mZenModeController = zenModeController;
        this.mDreamOverlayStateController = dreamOverlayStateController;
        this.mUserTracker = userTracker;
        this.mWifiInteractor = wifiInteractorImpl;
        this.mPrivacyItemController = privacyItemController;
        this.mCommunalSceneInteractor = communalSceneInteractor;
        this.mLogger = new DreamLogger(logBuffer, "DreamStatusBarCtrl");
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        this.mStatusBarWindowStateController.listeners.add(this.mStatusBarWindowStateListener);
        this.mPrivacyItemController.addCallback(this.mPrivacyItemControllerCallback);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        this.mIsAttached = true;
        JavaAdapterKt.collectFlow(this.mView, this.mWifiInteractor.wifiNetwork, new AmbientStatusBarViewController$$ExternalSyntheticLambda6(this, 1));
        JavaAdapterKt.collectFlow(this.mView, this.mCommunalSceneInteractor.isCommunalVisible, new AmbientStatusBarViewController$$ExternalSyntheticLambda6(this, 2));
        ((NextAlarmControllerImpl) this.mNextAlarmController).addCallback(this.mNextAlarmCallback);
        updateAlarmStatusIcon();
        ((IndividualSensorPrivacyControllerImpl) this.mSensorPrivacyController).addCallback(this.mSensorCallback);
        updateMicCameraBlockedStatusIcon();
        ((ZenModeControllerImpl) this.mZenModeController).addCallback(this.mZenModeCallback);
        showIcon(6, R.string.priority_mode_dream_overlay_content_description, ((ZenModeControllerImpl) this.mZenModeController).mZenMode != 0);
        this.mDreamOverlayNotificationCountProvider.ifPresent(new AmbientStatusBarViewController$$ExternalSyntheticLambda6(this, 3));
        DreamOverlayStatusBarItemsProvider dreamOverlayStatusBarItemsProvider = this.mStatusBarItemsProvider;
        dreamOverlayStatusBarItemsProvider.mExecutor.execute(new DreamOverlayStatusBarItemsProvider$$ExternalSyntheticLambda0(dreamOverlayStatusBarItemsProvider, this.mStatusBarItemsProviderCallback, 0));
        this.mDreamOverlayStateController.addCallback(this.mDreamOverlayStateCallback);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        ((ZenModeControllerImpl) this.mZenModeController).removeCallback(this.mZenModeCallback);
        ((IndividualSensorPrivacyControllerImpl) this.mSensorPrivacyController).removeCallback(this.mSensorCallback);
        ((NextAlarmControllerImpl) this.mNextAlarmController).removeCallback(this.mNextAlarmCallback);
        this.mDreamOverlayNotificationCountProvider.ifPresent(new AmbientStatusBarViewController$$ExternalSyntheticLambda6(this, 0));
        DreamOverlayStatusBarItemsProvider dreamOverlayStatusBarItemsProvider = this.mStatusBarItemsProvider;
        dreamOverlayStatusBarItemsProvider.mExecutor.execute(new DreamOverlayStatusBarItemsProvider$$ExternalSyntheticLambda0(dreamOverlayStatusBarItemsProvider, this.mStatusBarItemsProviderCallback, 1));
        ((AmbientStatusBarView) this.mView).mExtraSystemStatusViewGroup.removeAllViews();
        DreamOverlayStateController dreamOverlayStateController = this.mDreamOverlayStateController;
        dreamOverlayStateController.setDreamOverlayStatusBarVisible(false);
        dreamOverlayStateController.removeCallback(this.mDreamOverlayStateCallback);
        this.mIsAttached = false;
    }

    public final void showIcon(int i, int i2, boolean z) {
        this.mMainExecutor.execute(new AmbientStatusBarViewController$$ExternalSyntheticLambda10(this, z, i, this.mResources.getString(i2)));
    }

    public final void updateAlarmStatusIcon() {
        String str;
        AlarmManager.AlarmClockInfo nextAlarmClock = this.mAlarmManager.getNextAlarmClock(((UserTrackerImpl) this.mUserTracker).getUserId());
        boolean z = nextAlarmClock != null && nextAlarmClock.getTriggerTime() > 0;
        if (z) {
            str = this.mResources.getString(R.string.accessibility_quick_settings_alarm, DateFormat.format(DateFormat.getBestDateTimePattern(Locale.getDefault(), this.mDateFormatUtil.is24HourFormat() ? "EHm" : "Ehma"), nextAlarmClock.getTriggerTime()).toString());
        } else {
            str = null;
        }
        this.mMainExecutor.execute(new AmbientStatusBarViewController$$ExternalSyntheticLambda10(this, z, 2, str));
    }

    public final void updateMicCameraBlockedStatusIcon() {
        IndividualSensorPrivacyController individualSensorPrivacyController = this.mSensorPrivacyController;
        boolean isSensorBlocked = ((IndividualSensorPrivacyControllerImpl) individualSensorPrivacyController).isSensorBlocked(1);
        boolean isSensorBlocked2 = ((IndividualSensorPrivacyControllerImpl) individualSensorPrivacyController).isSensorBlocked(2);
        showIcon(3, R.string.camera_blocked_dream_overlay_content_description, !isSensorBlocked && isSensorBlocked2);
        showIcon(4, R.string.microphone_blocked_dream_overlay_content_description, isSensorBlocked && !isSensorBlocked2);
        showIcon(5, R.string.camera_and_microphone_blocked_dream_overlay_content_description, isSensorBlocked && isSensorBlocked2);
    }

    public final void updateVisibility$1() {
        int visibility = ((AmbientStatusBarView) this.mView).getVisibility();
        DreamOverlayStateController dreamOverlayStateController = this.mDreamOverlayStateController;
        int i = ((dreamOverlayStateController.containsState(2) || this.mStatusBarWindowStateController.windowState == 0) && !this.mCommunalVisible) ? 4 : 0;
        if (visibility == i) {
            return;
        }
        ((AmbientStatusBarView) this.mView).setVisibility(i);
        dreamOverlayStateController.setDreamOverlayStatusBarVisible(i == 0);
    }

    public void updateWifiUnavailableStatusIcon(boolean z) {
        showIcon(1, R.string.wifi_unavailable_dream_overlay_content_description, !z);
    }
}
