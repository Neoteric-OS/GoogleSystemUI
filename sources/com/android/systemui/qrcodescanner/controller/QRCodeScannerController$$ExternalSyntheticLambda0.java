package com.android.systemui.qrcodescanner.controller;

import com.android.systemui.qrcodescanner.controller.QRCodeScannerController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class QRCodeScannerController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ QRCodeScannerController$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((QRCodeScannerController) obj).updateQRCodeScannerPreferenceDetails(true);
                break;
            case 1:
                ((QRCodeScannerController) obj).updateQRCodeScannerActivityDetails();
                break;
            case 2:
                ((QRCodeScannerController) obj).updateQRCodeScannerActivityDetails();
                break;
            default:
                ((QRCodeScannerController.AnonymousClass1) obj).this$0.updateQRCodeScannerPreferenceDetails(false);
                break;
        }
    }
}
