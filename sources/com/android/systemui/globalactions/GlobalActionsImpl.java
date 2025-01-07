package com.android.systemui.globalactions;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.settingslib.Utils;
import com.android.systemui.plugins.GlobalActions;
import com.android.systemui.scrim.ScrimDrawable;
import com.android.systemui.statusbar.BlurUtils;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GlobalActionsImpl implements GlobalActions, CommandQueue.Callbacks {
    public final CommandQueue mCommandQueue;
    public final Context mContext;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public boolean mDisabled;
    public final GlobalActionsDialogLite mGlobalActionsDialog;
    public final KeyguardStateController mKeyguardStateController;
    public final ShutdownUi mShutdownUi;

    public GlobalActionsImpl(Context context, CommandQueue commandQueue, GlobalActionsDialogLite globalActionsDialogLite, KeyguardStateController keyguardStateController, DeviceProvisionedController deviceProvisionedController, ShutdownUi shutdownUi) {
        this.mContext = context;
        this.mGlobalActionsDialog = globalActionsDialogLite;
        this.mKeyguardStateController = keyguardStateController;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mCommandQueue = commandQueue;
        commandQueue.addCallback((CommandQueue.Callbacks) this);
        this.mShutdownUi = shutdownUi;
    }

    @Override // com.android.systemui.plugins.GlobalActions
    public final void destroy() {
        this.mCommandQueue.removeCallback((CommandQueue.Callbacks) this);
        this.mGlobalActionsDialog.destroy();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void disable(int i, int i2, int i3, boolean z) {
        boolean z2 = (i3 & 8) != 0;
        if (i != this.mContext.getDisplayId() || z2 == this.mDisabled) {
            return;
        }
        this.mDisabled = z2;
        if (z2) {
            GlobalActionsDialogLite globalActionsDialogLite = this.mGlobalActionsDialog;
            globalActionsDialogLite.mHandler.removeMessages(0);
            globalActionsDialogLite.mHandler.sendEmptyMessage(0);
        }
    }

    @Override // com.android.systemui.plugins.GlobalActions
    public final void showGlobalActions(GlobalActions.GlobalActionsManager globalActionsManager) {
        if (this.mDisabled) {
            return;
        }
        this.mGlobalActionsDialog.showOrHideDialog(((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing, ((DeviceProvisionedControllerImpl) this.mDeviceProvisionedController).deviceProvisioned.get(), null);
    }

    @Override // com.android.systemui.plugins.GlobalActions
    public final void showShutdownUi(boolean z, String str) {
        final ShutdownUi shutdownUi = this.mShutdownUi;
        shutdownUi.getClass();
        final ScrimDrawable scrimDrawable = new ScrimDrawable();
        final Dialog dialog = new Dialog(shutdownUi.mContext, R.style.Theme_SystemUI_Dialog_GlobalActions);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.systemui.globalactions.ShutdownUi$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                ShutdownUi shutdownUi2 = ShutdownUi.this;
                ScrimDrawable scrimDrawable2 = scrimDrawable;
                Dialog dialog2 = dialog;
                BlurUtils blurUtils = shutdownUi2.mBlurUtils;
                if (!blurUtils.supportsBlursOnWindows()) {
                    scrimDrawable2.setAlpha((int) (shutdownUi2.mContext.getResources().getFloat(R.dimen.shutdown_scrim_behind_alpha) * 255.0f));
                } else {
                    scrimDrawable2.setAlpha(255);
                    blurUtils.applyBlur(dialog2.getWindow().getDecorView().getViewRootImpl(), (int) blurUtils.blurRadiusOfRatio(1.0f), true);
                }
            }
        });
        Window window = dialog.getWindow();
        window.requestFeature(1);
        window.getAttributes().systemUiVisibility |= 1792;
        window.getDecorView();
        window.getAttributes().width = -1;
        window.getAttributes().height = -1;
        window.getAttributes().layoutInDisplayCutoutMode = 3;
        window.setType(2020);
        window.getAttributes().setFitInsetsTypes(0);
        window.clearFlags(2);
        window.addFlags(17629472);
        window.setBackgroundDrawable(scrimDrawable);
        window.setWindowAnimations(R.style.Animation_ShutdownUi);
        dialog.setContentView(shutdownUi.getShutdownDialogContent(z));
        dialog.setCancelable(false);
        int colorAttrDefaultColor = shutdownUi.mBlurUtils.supportsBlursOnWindows() ? Utils.getColorAttrDefaultColor(R.attr.wallpaperTextColor, 0, shutdownUi.mContext) : shutdownUi.mContext.getResources().getColor(R.color.global_actions_shutdown_ui_text);
        ((ProgressBar) dialog.findViewById(android.R.id.progress)).getIndeterminateDrawable().setTint(colorAttrDefaultColor);
        TextView textView = (TextView) dialog.findViewById(android.R.id.text1);
        TextView textView2 = (TextView) dialog.findViewById(android.R.id.text2);
        textView.setTextColor(colorAttrDefaultColor);
        textView2.setTextColor(colorAttrDefaultColor);
        textView2.setText(shutdownUi.getRebootMessage(z, str));
        String reasonMessage = shutdownUi.getReasonMessage(str);
        if (reasonMessage != null) {
            textView.setVisibility(0);
            textView.setText(reasonMessage);
        }
        dialog.show();
    }
}
