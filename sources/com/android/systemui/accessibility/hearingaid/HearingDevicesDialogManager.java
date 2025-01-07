package com.android.systemui.accessibility.hearingaid;

import android.util.Log;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.android.systemui.animation.DialogCuj;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$54;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HearingDevicesDialogManager {
    public final Executor mBackgroundExecutor;
    public final HearingDevicesChecker mDevicesChecker;
    public SystemUIDialog mDialog;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$54 mDialogFactory;
    public final DialogTransitionAnimator mDialogTransitionAnimator;
    public final Executor mMainExecutor;

    public HearingDevicesDialogManager(DialogTransitionAnimator dialogTransitionAnimator, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$54 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$54, HearingDevicesChecker hearingDevicesChecker, Executor executor, Executor executor2) {
        this.mDialogTransitionAnimator = dialogTransitionAnimator;
        this.mDialogFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$54;
        this.mDevicesChecker = hearingDevicesChecker;
        this.mBackgroundExecutor = executor;
        this.mMainExecutor = executor2;
    }

    public final void showDialog(final Expandable expandable, final int i) {
        if (this.mDialog != null) {
            Log.d("HearingDevicesDialogManager", "HearingDevicesDialog already showing. Destroy it first.");
            this.mDialog.dismiss();
            this.mDialog = null;
        }
        final CallbackToFutureAdapter.SafeFuture future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: com.android.systemui.accessibility.hearingaid.HearingDevicesDialogManager$$ExternalSyntheticLambda0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(final CallbackToFutureAdapter.Completer completer) {
                final HearingDevicesDialogManager hearingDevicesDialogManager = HearingDevicesDialogManager.this;
                hearingDevicesDialogManager.mBackgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.accessibility.hearingaid.HearingDevicesDialogManager$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        completer.set(Boolean.valueOf(HearingDevicesDialogManager.this.mDevicesChecker.isAnyPairedHearingDevice()));
                    }
                });
                return "isAnyPairedHearingDevice check";
            }
        });
        future.delegate.addListener(new Runnable() { // from class: com.android.systemui.accessibility.hearingaid.HearingDevicesDialogManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DialogTransitionAnimator.Controller dialogTransitionController;
                HearingDevicesDialogManager hearingDevicesDialogManager = HearingDevicesDialogManager.this;
                CallbackToFutureAdapter.SafeFuture safeFuture = future;
                int i2 = i;
                Expandable expandable2 = expandable;
                hearingDevicesDialogManager.getClass();
                try {
                    hearingDevicesDialogManager.mDialog = hearingDevicesDialogManager.mDialogFactory.create(i2, !((Boolean) safeFuture.delegate.get()).booleanValue()).createDialog();
                    if (expandable2 == null || (dialogTransitionController = expandable2.dialogTransitionController(new DialogCuj(58, "hearing_devices_tile"))) == null) {
                        hearingDevicesDialogManager.mDialog.show();
                    } else {
                        hearingDevicesDialogManager.mDialogTransitionAnimator.show(hearingDevicesDialogManager.mDialog, dialogTransitionController, true);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    Log.e("HearingDevicesDialogManager", "Exception occurs while running pairedHearingDeviceCheckTask", e);
                }
            }
        }, this.mMainExecutor);
    }
}
