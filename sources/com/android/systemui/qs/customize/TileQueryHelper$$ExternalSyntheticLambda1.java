package com.android.systemui.qs.customize;

import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TileQueryHelper$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ TileQueryHelper f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ TileQueryHelper$$ExternalSyntheticLambda1(TileQueryHelper tileQueryHelper, ArrayList arrayList, boolean z) {
        this.f$0 = tileQueryHelper;
        this.f$1 = arrayList;
        this.f$2 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TileQueryHelper tileQueryHelper = this.f$0;
        ArrayList arrayList = this.f$1;
        boolean z = this.f$2;
        TileAdapter tileAdapter = tileQueryHelper.mListener;
        if (tileAdapter != null) {
            tileAdapter.mAllTiles = arrayList;
            tileAdapter.recalcSpecs();
        }
        tileQueryHelper.mFinished = z;
    }
}
