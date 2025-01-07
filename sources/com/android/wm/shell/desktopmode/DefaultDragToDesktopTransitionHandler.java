package com.android.wm.shell.desktopmode;

import android.view.SurfaceControl;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultDragToDesktopTransitionHandler extends DragToDesktopTransitionHandler {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.desktopmode.DefaultDragToDesktopTransitionHandler$1, reason: invalid class name */
    public final class AnonymousClass1 implements Supplier {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // java.util.function.Supplier
        public final Object get() {
            return new SurfaceControl.Transaction();
        }
    }
}
