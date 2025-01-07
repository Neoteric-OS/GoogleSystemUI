package com.android.systemui.qrcodescanner.controller;

import com.android.systemui.qrcodescanner.controller.QRCodeScannerController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class QRCodeScannerController$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        QRCodeScannerController.Callback callback = (QRCodeScannerController.Callback) obj;
        switch (this.$r8$classId) {
            case 0:
                callback.onQRCodeScannerActivityChanged();
                break;
            default:
                callback.onQRCodeScannerPreferenceChanged();
                break;
        }
    }
}
