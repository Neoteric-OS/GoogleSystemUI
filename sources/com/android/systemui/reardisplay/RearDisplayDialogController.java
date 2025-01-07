package com.android.systemui.reardisplay;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.devicestate.DeviceState;
import android.hardware.devicestate.DeviceStateManager;
import android.hardware.devicestate.DeviceStateManagerGlobal;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.wm.shell.R;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RearDisplayDialogController implements CoreStartable, ConfigurationController.ConfigurationListener, CommandQueue.Callbacks {
    public final CommandQueue mCommandQueue;
    public DeviceStateManagerGlobal mDeviceStateManagerGlobal;
    public LinearLayout mDialogViewContainer;
    public final Executor mExecutor;
    public int[] mFoldedStates;
    public final LayoutInflater mLayoutInflater;
    public SystemUIDialog mRearDisplayEducationDialog;
    public final Resources mResources;
    public boolean mStartedFolded;
    public final SystemUIDialog.Factory mSystemUIDialogFactory;
    public boolean mServiceNotified = false;
    public final DeviceStateManagerCallback mDeviceStateManagerCallback = new DeviceStateManagerCallback();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DeviceStateManagerCallback implements DeviceStateManager.DeviceStateCallback {
        public DeviceStateManagerCallback() {
        }

        public final void onDeviceStateChanged(DeviceState deviceState) {
            if (RearDisplayDialogController.this.mStartedFolded && !deviceState.hasProperty(1)) {
                RearDisplayDialogController.this.mRearDisplayEducationDialog.dismiss();
                RearDisplayDialogController.this.closeOverlayAndNotifyService(false);
            } else {
                if (RearDisplayDialogController.this.mStartedFolded || !deviceState.hasProperty(1)) {
                    return;
                }
                RearDisplayDialogController.this.mRearDisplayEducationDialog.dismiss();
                RearDisplayDialogController.this.closeOverlayAndNotifyService(true);
            }
        }
    }

    public RearDisplayDialogController(CommandQueue commandQueue, Executor executor, Resources resources, LayoutInflater layoutInflater, SystemUIDialog.Factory factory) {
        this.mCommandQueue = commandQueue;
        this.mExecutor = executor;
        this.mResources = resources;
        this.mLayoutInflater = layoutInflater;
        this.mSystemUIDialogFactory = factory;
    }

    public final void closeOverlayAndNotifyService(boolean z) {
        this.mServiceNotified = true;
        this.mDeviceStateManagerGlobal.unregisterDeviceStateCallback(this.mDeviceStateManagerCallback);
        this.mDeviceStateManagerGlobal.onStateRequestOverlayDismissed(z);
        this.mDialogViewContainer = null;
    }

    public final View createDialogView(Context context) {
        LayoutInflater cloneInContext = this.mLayoutInflater.cloneInContext(context);
        View inflate = this.mStartedFolded ? cloneInContext.inflate(R.layout.activity_rear_display_education, (ViewGroup) null) : cloneInContext.inflate(R.layout.activity_rear_display_education_opened, (ViewGroup) null);
        ((LottieAnimationView) inflate.findViewById(R.id.rear_display_folded_animation)).setRepeatCount(-1);
        return inflate;
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        SystemUIDialog systemUIDialog = this.mRearDisplayEducationDialog;
        if (systemUIDialog == null || !systemUIDialog.isShowing() || this.mDialogViewContainer == null) {
            return;
        }
        View createDialogView = createDialogView(this.mRearDisplayEducationDialog.getContext());
        this.mDialogViewContainer.removeAllViews();
        this.mDialogViewContainer.addView(createDialogView);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showRearDisplayDialog(int i) {
        boolean z;
        final int i2 = 0;
        final int i3 = 1;
        this.mRearDisplayEducationDialog = this.mSystemUIDialogFactory.create();
        if (this.mFoldedStates == null) {
            this.mFoldedStates = this.mResources.getIntArray(android.R.array.config_fontManagerServiceCerts);
        }
        int i4 = 0;
        while (true) {
            int[] iArr = this.mFoldedStates;
            if (i4 >= iArr.length) {
                z = false;
                break;
            } else {
                if (iArr[i4] == i) {
                    z = true;
                    break;
                }
                i4++;
            }
        }
        this.mStartedFolded = z;
        DeviceStateManagerGlobal deviceStateManagerGlobal = DeviceStateManagerGlobal.getInstance();
        this.mDeviceStateManagerGlobal = deviceStateManagerGlobal;
        deviceStateManagerGlobal.registerDeviceStateCallback(this.mDeviceStateManagerCallback, this.mExecutor);
        this.mServiceNotified = false;
        Context context = this.mRearDisplayEducationDialog.getContext();
        View createDialogView = createDialogView(context);
        LinearLayout linearLayout = new LinearLayout(context);
        this.mDialogViewContainer = linearLayout;
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.mDialogViewContainer.setOrientation(1);
        this.mDialogViewContainer.addView(createDialogView);
        this.mRearDisplayEducationDialog.setView(this.mDialogViewContainer);
        if (!this.mStartedFolded) {
            this.mRearDisplayEducationDialog.setButton(-1, R.string.rear_display_bottom_sheet_confirm, new DialogInterface.OnClickListener(this) { // from class: com.android.systemui.reardisplay.RearDisplayDialogController$$ExternalSyntheticLambda0
                public final /* synthetic */ RearDisplayDialogController f$0;

                {
                    this.f$0 = this;
                }

                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i5) {
                    int i6 = i2;
                    RearDisplayDialogController rearDisplayDialogController = this.f$0;
                    switch (i6) {
                        case 0:
                            rearDisplayDialogController.closeOverlayAndNotifyService(false);
                            break;
                        default:
                            rearDisplayDialogController.closeOverlayAndNotifyService(true);
                            break;
                    }
                }
            }, true);
        }
        this.mRearDisplayEducationDialog.setNegativeButton$1(R.string.rear_display_bottom_sheet_cancel, new DialogInterface.OnClickListener(this) { // from class: com.android.systemui.reardisplay.RearDisplayDialogController$$ExternalSyntheticLambda0
            public final /* synthetic */ RearDisplayDialogController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i5) {
                int i6 = i3;
                RearDisplayDialogController rearDisplayDialogController = this.f$0;
                switch (i6) {
                    case 0:
                        rearDisplayDialogController.closeOverlayAndNotifyService(false);
                        break;
                    default:
                        rearDisplayDialogController.closeOverlayAndNotifyService(true);
                        break;
                }
            }
        });
        this.mRearDisplayEducationDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.reardisplay.RearDisplayDialogController$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                RearDisplayDialogController rearDisplayDialogController = RearDisplayDialogController.this;
                if (rearDisplayDialogController.mServiceNotified) {
                    return;
                }
                rearDisplayDialogController.closeOverlayAndNotifyService(true);
            }
        });
        this.mRearDisplayEducationDialog.show();
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
    }
}
