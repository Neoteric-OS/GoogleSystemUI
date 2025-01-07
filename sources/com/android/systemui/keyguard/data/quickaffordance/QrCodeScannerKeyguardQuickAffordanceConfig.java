package com.android.systemui.keyguard.data.quickaffordance;

import android.content.Context;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.qrcodescanner.controller.QRCodeScannerController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.android.wm.shell.R;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class QrCodeScannerKeyguardQuickAffordanceConfig implements KeyguardQuickAffordanceConfig {
    public final Context context;
    public final QRCodeScannerController controller;
    public final Flow lockScreenState = FlowConflatedKt.conflatedCallbackFlow(new QrCodeScannerKeyguardQuickAffordanceConfig$lockScreenState$1(this, null));

    public QrCodeScannerKeyguardQuickAffordanceConfig(Context context, QRCodeScannerController qRCodeScannerController) {
        this.context = context;
        this.controller = qRCodeScannerController;
    }

    public static final KeyguardQuickAffordanceConfig.LockScreenState access$state(QrCodeScannerKeyguardQuickAffordanceConfig qrCodeScannerKeyguardQuickAffordanceConfig) {
        QRCodeScannerController qRCodeScannerController = qrCodeScannerKeyguardQuickAffordanceConfig.controller;
        return (qRCodeScannerController.mQRCodeScannerEnabled && qRCodeScannerController.isAbleToLaunchScannerActivity() && qRCodeScannerController.mConfigEnableLockScreenButton) ? new KeyguardQuickAffordanceConfig.LockScreenState.Visible(new Icon.Resource(R.drawable.ic_qr_code_scanner, new ContentDescription.Resource(R.string.accessibility_qr_code_scanner_button))) : KeyguardQuickAffordanceConfig.LockScreenState.Hidden.INSTANCE;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String getKey() {
        return "qr_code_scanner";
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Flow getLockScreenState() {
        return this.lockScreenState;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final int getPickerIconResourceId() {
        return R.drawable.ic_qr_code_scanner;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Object getPickerScreenState(Continuation continuation) {
        QRCodeScannerController qRCodeScannerController = this.controller;
        return (qRCodeScannerController.isAbleToLaunchScannerActivity() && qRCodeScannerController.mConfigEnableLockScreenButton) ? new KeyguardQuickAffordanceConfig.PickerScreenState.Default(null) : KeyguardQuickAffordanceConfig.PickerScreenState.UnavailableOnDevice.INSTANCE;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final KeyguardQuickAffordanceConfig.OnTriggeredResult onTriggered(Expandable$Companion$fromView$1 expandable$Companion$fromView$1) {
        return new KeyguardQuickAffordanceConfig.OnTriggeredResult.StartActivity(this.controller.mIntent, true);
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String pickerName() {
        return this.context.getString(R.string.qr_code_scanner_title);
    }
}
