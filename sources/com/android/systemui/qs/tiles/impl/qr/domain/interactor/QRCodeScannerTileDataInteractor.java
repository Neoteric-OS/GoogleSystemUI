package com.android.systemui.qs.tiles.impl.qr.domain.interactor;

import android.os.UserHandle;
import com.android.systemui.qrcodescanner.controller.QRCodeScannerController;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QRCodeScannerTileDataInteractor implements QSTileDataInteractor {
    public final CoroutineContext bgCoroutineContext;
    public final QRCodeScannerController qrController;

    public QRCodeScannerTileDataInteractor(CoroutineContext coroutineContext, QRCodeScannerController qRCodeScannerController) {
        this.bgCoroutineContext = coroutineContext;
        this.qrController = qRCodeScannerController;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow availability(UserHandle userHandle) {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.valueOf(this.qrController.isCameraAvailable()));
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow tileData(UserHandle userHandle, ReadonlyStateFlow readonlyStateFlow) {
        return FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new QRCodeScannerTileDataInteractor$tileData$2(this, null), FlowConflatedKt.conflatedCallbackFlow(new QRCodeScannerTileDataInteractor$tileData$1(this, null))), this.bgCoroutineContext);
    }
}
