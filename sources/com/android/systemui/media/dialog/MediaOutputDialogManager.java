package com.android.systemui.media.dialog;

import android.app.KeyguardManager;
import android.content.Context;
import android.media.AudioManager;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.os.PowerExemptionManager;
import android.os.UserHandle;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.media.nearby.NearbyMediaDevicesManager;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.volume.panel.domain.interactor.VolumePanelGlobalStateInteractor;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$12;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.internal.Preconditions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaOutputDialogManager {
    public final BroadcastSender broadcastSender;
    public final Context context;
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$12 mediaSwitchingControllerFactory;
    public final UiEventLogger uiEventLogger;

    public MediaOutputDialogManager(Context context, BroadcastSender broadcastSender, UiEventLogger uiEventLogger, DialogTransitionAnimator dialogTransitionAnimator, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$12 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$12) {
        this.context = context;
        this.broadcastSender = broadcastSender;
        this.uiEventLogger = uiEventLogger;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.mediaSwitchingControllerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$12;
    }

    public static void createAndShowWithController$default(MediaOutputDialogManager mediaOutputDialogManager, String str, boolean z, DialogTransitionAnimator.Controller controller, MediaSession.Token token, int i) {
        if ((i & 16) != 0) {
            token = null;
        }
        mediaOutputDialogManager.createAndShow(str, z, controller, true, null, token);
    }

    public final void createAndShow(String str, boolean z, DialogTransitionAnimator.Controller controller, boolean z2, UserHandle userHandle, MediaSession.Token token) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$12 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$12 = this.mediaSwitchingControllerFactory;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$12.getClass();
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$12.this$0;
        Context context = switchingProvider.sysUIGoogleGlobalRootComponentImpl.context;
        MediaSessionManager mediaSessionManager = (MediaSessionManager) context.getSystemService(MediaSessionManager.class);
        Preconditions.checkNotNullFromProvides(mediaSessionManager);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        LocalBluetoothManager localBluetoothManager = (LocalBluetoothManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocalBluetoothControllerProvider.get();
        ActivityStarter activityStarter = (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get();
        CommonNotifCollection commonNotifCollection = (CommonNotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifPipelineProvider.get();
        DialogTransitionAnimator dialogTransitionAnimator = (DialogTransitionAnimator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDialogTransitionAnimatorProvider.get();
        NearbyMediaDevicesManager nearbyMediaDevicesManager = (NearbyMediaDevicesManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nearbyMediaDevicesManagerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        MediaSwitchingController mediaSwitchingController = new MediaSwitchingController(context, str, userHandle, token, mediaSessionManager, localBluetoothManager, activityStarter, commonNotifCollection, dialogTransitionAnimator, nearbyMediaDevicesManager, (AudioManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAudioManagerProvider.get(), (PowerExemptionManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePowerExemptionManagerProvider.get(), (KeyguardManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideKeyguardManagerProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (VolumePanelGlobalStateInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumePanelGlobalStateInteractorProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
        MediaOutputDialog mediaOutputDialog = new MediaOutputDialog(this.context, z, this.broadcastSender, mediaSwitchingController, this.dialogTransitionAnimator, this.uiEventLogger, z2);
        if (controller == null) {
            mediaOutputDialog.show();
        } else {
            TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
            this.dialogTransitionAnimator.show(mediaOutputDialog, controller, false);
        }
    }
}
