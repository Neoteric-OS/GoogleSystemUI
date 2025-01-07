package com.android.wm.shell.pip2.phone;

import android.app.ActivityTaskManager;
import android.app.PictureInPictureUiState;
import android.os.RemoteException;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipUiStateChangeController$$ExternalSyntheticLambda0 implements Consumer {
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        try {
            ActivityTaskManager.getService().onPictureInPictureUiStateChanged((PictureInPictureUiState) obj);
        } catch (RemoteException | IllegalStateException unused) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -3683790773216265302L, 0, null);
            }
        }
    }
}
