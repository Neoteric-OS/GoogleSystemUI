package com.android.wm.shell.windowdecor;

import android.util.Size;
import com.android.wm.shell.common.DisplayLayout;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DragResizeInputListener$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ DragResizeInputListener f$0;

    public /* synthetic */ DragResizeInputListener$$ExternalSyntheticLambda0(DragResizeInputListener dragResizeInputListener) {
        this.f$0 = dragResizeInputListener;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        DragResizeInputListener dragResizeInputListener = this.f$0;
        DisplayLayout displayLayout = dragResizeInputListener.mDisplayController.getDisplayLayout(dragResizeInputListener.mDisplayId);
        return new Size(displayLayout.mWidth, displayLayout.mHeight);
    }
}
