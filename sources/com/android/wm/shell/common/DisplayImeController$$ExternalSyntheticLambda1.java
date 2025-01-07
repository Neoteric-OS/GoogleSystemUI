package com.android.wm.shell.common;

import android.os.RemoteException;
import android.util.Slog;
import android.view.SurfaceControl;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DisplayImeController$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                Slog.e("DisplayImeController", "Failed to remove IME surface.", (RemoteException) obj);
                break;
            default:
                ((SurfaceControl) obj).release();
                break;
        }
    }
}
