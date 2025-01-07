package com.android.systemui.statusbar.phone;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.policy.domain.interactor.DeviceProvisioningInteractor;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.concurrency.DelayableExecutor;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActivityStarterInternalImpl implements ActivityStarterInternal {
    public final Lazy centralSurfacesOptLazy;
    public final Context context;
    public final DeviceEntryInteractor deviceEntryInteractor;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;

    public ActivityStarterInternalImpl(Lazy lazy, KeyguardInteractor keyguardInteractor, Lazy lazy2, Context context, Resources resources, SelectedUserInteractor selectedUserInteractor, DeviceEntryInteractor deviceEntryInteractor, ActivityTransitionAnimator activityTransitionAnimator, int i, DeviceProvisioningInteractor deviceProvisioningInteractor, ActivityIntentHelper activityIntentHelper, KeyguardTransitionInteractor keyguardTransitionInteractor, Lazy lazy3, DelayableExecutor delayableExecutor, Lazy lazy4, CommunalSceneInteractor communalSceneInteractor, StatusBarWindowControllerImpl statusBarWindowControllerImpl, ShadeAnimationInteractor shadeAnimationInteractor, Lazy lazy5, CommandQueue commandQueue, NotificationLockscreenUserManager notificationLockscreenUserManager) {
        this.deviceEntryInteractor = deviceEntryInteractor;
        this.keyguardTransitionInteractor = keyguardTransitionInteractor;
    }

    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final void dismissKeyguardThenExecute(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable, boolean z, String str) {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
        }
    }

    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final void executeRunnableDismissingKeyguard(Runnable runnable, Runnable runnable2, boolean z, boolean z2, boolean z3, boolean z4, String str) {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
        }
    }

    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final boolean shouldAnimateLaunch(boolean z) {
        if (this.keyguardTransitionInteractor.getCurrentState() == KeyguardState.OCCLUDED) {
            return false;
        }
        if (((Boolean) this.deviceEntryInteractor.isDeviceEntered.getValue()).booleanValue()) {
            return true;
        }
        return z;
    }

    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final void startActivity(Intent intent, boolean z, ActivityTransitionAnimator.Controller controller, boolean z2, UserHandle userHandle) {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
        }
    }

    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final void startActivityDismissingKeyguard(Intent intent, boolean z, boolean z2, ActivityStarter.Callback callback, int i, ActivityTransitionAnimator.Controller controller, String str, boolean z3, UserHandle userHandle) {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
        }
    }

    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final void startPendingIntentDismissingKeyguard(PendingIntent pendingIntent, boolean z, Runnable runnable, View view, ActivityTransitionAnimator.Controller controller, boolean z2, boolean z3, Intent intent, Bundle bundle, String str) {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects SceneContainerFlag to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects SceneContainerFlag to be enabled.") : null);
        } else if (Log.isLoggable("RefactorFlag", 5)) {
            Log.w("RefactorFlag", "New code path expects SceneContainerFlag to be enabled.");
        }
    }
}
