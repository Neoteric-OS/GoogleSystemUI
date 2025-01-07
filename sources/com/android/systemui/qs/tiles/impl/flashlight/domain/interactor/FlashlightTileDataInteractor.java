package com.android.systemui.qs.tiles.impl.flashlight.domain.interactor;

import android.os.UserHandle;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FlashlightTileDataInteractor implements QSTileDataInteractor {
    public final FlashlightControllerImpl flashlightController;

    public FlashlightTileDataInteractor(FlashlightControllerImpl flashlightControllerImpl) {
        this.flashlightController = flashlightControllerImpl;
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow availability(UserHandle userHandle) {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.valueOf(this.flashlightController.mHasFlashlight));
    }

    @Override // com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor
    public final Flow tileData(UserHandle userHandle, ReadonlyStateFlow readonlyStateFlow) {
        return FlowConflatedKt.conflatedCallbackFlow(new FlashlightTileDataInteractor$tileData$1(this, null));
    }
}
