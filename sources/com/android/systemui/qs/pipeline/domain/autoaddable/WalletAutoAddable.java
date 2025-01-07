package com.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.statusbar.policy.WalletControllerImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WalletAutoAddable implements AutoAddable {
    public final WalletControllerImpl walletController;
    public final TileSpec spec = TileSpec.Companion.create("wallet");
    public final String description = "WalletAutoAddable (" + getAutoAddTracking() + ")";

    public WalletAutoAddable(WalletControllerImpl walletControllerImpl) {
        this.walletController = walletControllerImpl;
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final Flow autoAddSignal(int i) {
        return new SafeFlow(new WalletAutoAddable$autoAddSignal$1(this, null));
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final AutoAddTracking getAutoAddTracking() {
        return new AutoAddTracking.IfNotAdded(this.spec);
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final String getDescription() {
        return this.description;
    }
}
