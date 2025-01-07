package com.android.app.viewcapture;

import android.media.permission.SafeCloseable;
import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoOpViewCapture extends ViewCapture {
    @Override // com.android.app.viewcapture.ViewCapture
    public final SafeCloseable startCapture(View view, String str) {
        return NoOpViewCapture$startCapture$1.INSTANCE;
    }
}
