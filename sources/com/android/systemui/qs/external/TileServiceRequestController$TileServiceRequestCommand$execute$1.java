package com.android.systemui.qs.external;

import android.util.Log;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileServiceRequestController$TileServiceRequestCommand$execute$1 implements Consumer {
    public static final TileServiceRequestController$TileServiceRequestCommand$execute$1 INSTANCE = new TileServiceRequestController$TileServiceRequestCommand$execute$1();

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Log.d("TileServiceRequestController", "Response: " + ((Integer) obj));
    }
}
