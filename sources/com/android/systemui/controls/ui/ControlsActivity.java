package com.android.systemui.controls.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.service.dreams.IDreamManager;
import android.util.Log;
import android.view.ViewGroup;
import androidx.activity.ComponentActivity;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.controls.controller.ControlsBindingControllerImpl;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.management.ControlsAnimations;
import com.android.systemui.controls.management.ControlsAnimations$observerForAnimations$1;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.controls.management.ControlsListingControllerImpl;
import com.android.systemui.controls.settings.ControlsSettingsDialogManager;
import com.android.systemui.controls.settings.ControlsSettingsDialogManagerImpl;
import com.android.systemui.controls.settings.ControlsSettingsDialogManagerImpl$maybeShowDialog$1;
import com.android.systemui.controls.settings.ControlsSettingsDialogManagerImpl.DialogListener;
import com.android.systemui.controls.settings.ControlsSettingsRepository;
import com.android.systemui.controls.settings.ControlsSettingsRepositoryImpl;
import com.android.systemui.controls.ui.SelectedItem;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ControlsActivity extends ComponentActivity {
    public final BroadcastDispatcher broadcastDispatcher;
    public ControlsActivity$initBroadcastReceiver$1 broadcastReceiver;
    public final ControlsSettingsDialogManager controlsSettingsDialogManager;
    public final IDreamManager dreamManager;
    public final KeyguardStateController keyguardStateController;
    public final Configuration lastConfiguration = new Configuration();
    public boolean mExitToDream;
    public ViewGroup parent;
    public final ControlsUiController uiController;

    public ControlsActivity(ControlsUiController controlsUiController, BroadcastDispatcher broadcastDispatcher, IDreamManager iDreamManager, FeatureFlags featureFlags, ControlsSettingsDialogManager controlsSettingsDialogManager, KeyguardStateController keyguardStateController) {
        this.uiController = controlsUiController;
        this.broadcastDispatcher = broadcastDispatcher;
        this.dreamManager = iDreamManager;
        this.controlsSettingsDialogManager = controlsSettingsDialogManager;
        this.keyguardStateController = keyguardStateController;
    }

    public final void finishOrReturnToDream() {
        if (this.mExitToDream) {
            try {
                this.mExitToDream = false;
                this.dreamManager.dream();
                return;
            } catch (RemoteException unused) {
            }
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        finishOrReturnToDream();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        Unit unit;
        Unit unit2;
        super.onConfigurationChanged(configuration);
        if ((this.lastConfiguration.diff(configuration) & 3200) != 0) {
            ControlsUiControllerImpl controlsUiControllerImpl = (ControlsUiControllerImpl) this.uiController;
            SelectionItem selectionItem = controlsUiControllerImpl.selectionItem;
            if (selectionItem != null) {
                SelectedItem selectedItem = controlsUiControllerImpl.selectedItem;
                boolean z = selectedItem instanceof SelectedItem.StructureItem;
                unit = Unit.INSTANCE;
                if (z) {
                    controlsUiControllerImpl.createListView(selectionItem);
                } else if (selectedItem instanceof SelectedItem.PanelItem) {
                    PanelTaskViewController panelTaskViewController = controlsUiControllerImpl.taskViewController;
                    if (panelTaskViewController != null) {
                        panelTaskViewController.taskView.onLocationChanged();
                        unit2 = unit;
                    } else {
                        unit2 = null;
                    }
                    if (unit2 == null) {
                        ViewGroup viewGroup = controlsUiControllerImpl.parent;
                        if (viewGroup == null) {
                            viewGroup = null;
                        }
                        ControlsUiControllerImpl.reload$default(controlsUiControllerImpl, viewGroup);
                    }
                }
            } else {
                unit = null;
            }
            if (unit == null) {
                ViewGroup viewGroup2 = controlsUiControllerImpl.parent;
                ControlsUiControllerImpl.reload$default(controlsUiControllerImpl, viewGroup2 != null ? viewGroup2 : null);
            }
        }
        this.lastConfiguration.setTo(configuration);
    }

    /* JADX WARN: Type inference failed for: r8v7, types: [com.android.systemui.controls.ui.ControlsActivity$initBroadcastReceiver$1] */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.lastConfiguration.setTo(getResources().getConfiguration());
        getWindow().addPrivateFlags(536870912);
        setContentView(R.layout.controls_fullscreen);
        this.lifecycleRegistry.addObserver(new ControlsAnimations$observerForAnimations$1(getIntent(), (ViewGroup) requireViewById(R.id.control_detail_root), false, getWindow()));
        ((ViewGroup) requireViewById(R.id.control_detail_root)).setOnApplyWindowInsetsListener(ControlsActivity$onCreate$1$1.INSTANCE);
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.controls.ui.ControlsActivity$initBroadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (Intrinsics.areEqual(action, "android.intent.action.SCREEN_OFF") || Intrinsics.areEqual(action, "android.intent.action.DREAMING_STARTED")) {
                    ControlsActivity.this.finish();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.DREAMING_STARTED");
        ControlsActivity$initBroadcastReceiver$1 controlsActivity$initBroadcastReceiver$1 = this.broadcastReceiver;
        if (controlsActivity$initBroadcastReceiver$1 == null) {
            controlsActivity$initBroadcastReceiver$1 = null;
        }
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, controlsActivity$initBroadcastReceiver$1, intentFilter, null, null, 0, 60);
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        ControlsActivity$initBroadcastReceiver$1 controlsActivity$initBroadcastReceiver$1 = this.broadcastReceiver;
        if (controlsActivity$initBroadcastReceiver$1 == null) {
            controlsActivity$initBroadcastReceiver$1 = null;
        }
        this.broadcastDispatcher.unregisterReceiver(controlsActivity$initBroadcastReceiver$1);
    }

    @Override // android.app.Activity
    public final void onResume() {
        super.onResume();
        this.mExitToDream = getIntent().getBooleanExtra("extra_exit_to_dream", false);
    }

    @Override // android.app.Activity
    public final void onStart() {
        super.onStart();
        ViewGroup viewGroup = (ViewGroup) requireViewById(R.id.control_detail_root);
        this.parent = viewGroup;
        viewGroup.setAlpha(0.0f);
        if (this.keyguardStateController.isUnlocked()) {
            ViewGroup viewGroup2 = this.parent;
            if (viewGroup2 == null) {
                viewGroup2 = null;
            }
            ((ControlsUiControllerImpl) this.uiController).show(viewGroup2, new ControlsActivity$onStart$2(this, 0), this);
        } else {
            Function0 function0 = new Function0() { // from class: com.android.systemui.controls.ui.ControlsActivity$onStart$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ControlsActivity controlsActivity = ControlsActivity.this;
                    ControlsUiController controlsUiController = controlsActivity.uiController;
                    ViewGroup viewGroup3 = controlsActivity.parent;
                    if (viewGroup3 == null) {
                        viewGroup3 = null;
                    }
                    ((ControlsUiControllerImpl) controlsUiController).show(viewGroup3, new ControlsActivity$onStart$2(controlsActivity, 1), controlsActivity);
                    return Unit.INSTANCE;
                }
            };
            ControlsSettingsDialogManagerImpl controlsSettingsDialogManagerImpl = (ControlsSettingsDialogManagerImpl) this.controlsSettingsDialogManager;
            AlertDialog alertDialog = controlsSettingsDialogManagerImpl.dialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            SharedPreferences sharedPreferences$1 = ((UserFileManagerImpl) controlsSettingsDialogManagerImpl.userFileManager).getSharedPreferences$1(((UserTrackerImpl) controlsSettingsDialogManagerImpl.userTracker).getUserId(), "controls_prefs");
            int i = sharedPreferences$1.getInt("show_settings_attempts", 0);
            if (i < 2) {
                ControlsSettingsRepository controlsSettingsRepository = controlsSettingsDialogManagerImpl.controlsSettingsRepository;
                if (!((Boolean) ((ControlsSettingsRepositoryImpl) controlsSettingsRepository).canShowControlsInLockscreen.getValue()).booleanValue() || !((Boolean) ((StateFlowImpl) ((ControlsSettingsRepositoryImpl) controlsSettingsRepository).allowActionOnTrivialControlsInLockscreen.$$delegate_0).getValue()).booleanValue()) {
                    ControlsSettingsDialogManagerImpl.DialogListener dialogListener = controlsSettingsDialogManagerImpl.new DialogListener(sharedPreferences$1, i, function0);
                    AlertDialog alertDialog2 = (AlertDialog) controlsSettingsDialogManagerImpl.dialogProvider.invoke(this, Integer.valueOf(R.style.Theme_SystemUI_Dialog));
                    alertDialog2.setIcon(R.drawable.ic_lock_locked);
                    alertDialog2.setOnCancelListener(dialogListener);
                    alertDialog2.setButton(-3, alertDialog2.getContext().getText(R.string.controls_settings_dialog_neutral_button), dialogListener);
                    alertDialog2.setButton(-1, alertDialog2.getContext().getText(R.string.controls_settings_dialog_positive_button), dialogListener);
                    if (((Boolean) ((ControlsSettingsRepositoryImpl) controlsSettingsRepository).canShowControlsInLockscreen.getValue()).booleanValue()) {
                        alertDialog2.setTitle(R.string.controls_settings_trivial_controls_dialog_title);
                        alertDialog2.setMessage(alertDialog2.getContext().getText(R.string.controls_settings_trivial_controls_dialog_message));
                    } else {
                        alertDialog2.setTitle(R.string.controls_settings_show_controls_dialog_title);
                        alertDialog2.setMessage(alertDialog2.getContext().getText(R.string.controls_settings_show_controls_dialog_message));
                    }
                    SystemUIDialog.registerDismissListener(alertDialog2, new ControlsSettingsDialogManagerImpl$maybeShowDialog$1(0, controlsSettingsDialogManagerImpl));
                    SystemUIDialog.setDialogSize(alertDialog2);
                    SystemUIDialog.setShowForAllUsers(alertDialog2);
                    controlsSettingsDialogManagerImpl.dialog = alertDialog2;
                    alertDialog2.show();
                }
            }
            function0.invoke();
        }
        ViewGroup viewGroup3 = this.parent;
        ControlsAnimations.enterAnimation(viewGroup3 != null ? viewGroup3 : null).start();
    }

    @Override // android.app.Activity
    public final void onStop() {
        Boolean bool;
        super.onStop();
        this.mExitToDream = false;
        ViewGroup viewGroup = this.parent;
        if (viewGroup == null) {
            viewGroup = null;
        }
        ControlsUiControllerImpl controlsUiControllerImpl = (ControlsUiControllerImpl) this.uiController;
        ViewGroup viewGroup2 = controlsUiControllerImpl.parent;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        if (viewGroup.equals(viewGroup2)) {
            Log.d("ControlsUiController", "hide()");
            controlsUiControllerImpl.hidden = true;
            ControlsPopupMenu controlsPopupMenu = controlsUiControllerImpl.popup;
            if (controlsPopupMenu != null) {
                controlsPopupMenu.dismissImmediate();
            }
            controlsUiControllerImpl.popup = null;
            Iterator it = controlsUiControllerImpl.controlViewsById.entrySet().iterator();
            while (it.hasNext()) {
                ControlViewHolder controlViewHolder = (ControlViewHolder) ((Map.Entry) it.next()).getValue();
                Dialog dialog = controlViewHolder.lastChallengeDialog;
                if (dialog != null) {
                    dialog.dismiss();
                }
                controlViewHolder.lastChallengeDialog = null;
                Dialog dialog2 = controlViewHolder.visibleDialog;
                if (dialog2 != null) {
                    dialog2.dismiss();
                }
                controlViewHolder.visibleDialog = null;
            }
            ControlActionCoordinatorImpl controlActionCoordinatorImpl = controlsUiControllerImpl.controlActionCoordinator;
            Context context = controlActionCoordinatorImpl.activityContext;
            if (context == null) {
                context = null;
            }
            Activity activity = context instanceof Activity ? (Activity) context : null;
            if (activity != null) {
                bool = Boolean.valueOf(activity.isFinishing() || activity.isDestroyed());
            } else {
                bool = null;
            }
            if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
                controlActionCoordinatorImpl.dialog = null;
            } else {
                DetailDialog detailDialog = controlActionCoordinatorImpl.dialog;
                if (detailDialog != null && detailDialog.isShowing()) {
                    DetailDialog detailDialog2 = controlActionCoordinatorImpl.dialog;
                    if (detailDialog2 != null) {
                        detailDialog2.dismiss();
                    }
                    controlActionCoordinatorImpl.dialog = null;
                }
            }
            SystemUIDialog systemUIDialog = controlsUiControllerImpl.removeAppDialog;
            if (systemUIDialog != null) {
                systemUIDialog.cancel();
            }
            ControlsControllerImpl controlsControllerImpl = (ControlsControllerImpl) ((ControlsController) controlsUiControllerImpl.controlsController.get());
            if (controlsControllerImpl.confirmAvailability()) {
                ((ControlsBindingControllerImpl) controlsControllerImpl.bindingController).unsubscribe();
            }
            PanelTaskViewController panelTaskViewController = controlsUiControllerImpl.taskViewController;
            if (panelTaskViewController != null) {
                panelTaskViewController.taskView.removeTask();
            }
            controlsUiControllerImpl.taskViewController = null;
            controlsUiControllerImpl.controlsById.clear();
            controlsUiControllerImpl.controlViewsById.clear();
            ControlsListingController controlsListingController = (ControlsListingController) controlsUiControllerImpl.controlsListingController.get();
            ControlsUiControllerImpl$createCallback$1 controlsUiControllerImpl$createCallback$1 = controlsUiControllerImpl.listingCallback;
            ((ControlsListingControllerImpl) controlsListingController).removeCallback(controlsUiControllerImpl$createCallback$1 != null ? controlsUiControllerImpl$createCallback$1 : null);
            if (!controlsUiControllerImpl.retainCache) {
                RenderInfo.iconMap.clear();
                RenderInfo.appIconMap.clear();
            }
        }
        viewGroup.removeAllViews();
        AlertDialog alertDialog = ((ControlsSettingsDialogManagerImpl) this.controlsSettingsDialogManager).dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }
}
