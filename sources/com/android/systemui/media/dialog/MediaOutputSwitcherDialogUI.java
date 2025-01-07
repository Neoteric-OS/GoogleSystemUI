package com.android.systemui.media.dialog;

import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.CommandQueue;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaOutputSwitcherDialogUI implements CoreStartable, CommandQueue.Callbacks {
    public final CommandQueue mCommandQueue;
    public final MediaOutputDialogManager mMediaOutputDialogManager;

    public MediaOutputSwitcherDialogUI(CommandQueue commandQueue, MediaOutputDialogManager mediaOutputDialogManager) {
        this.mCommandQueue = commandQueue;
        this.mMediaOutputDialogManager = mediaOutputDialogManager;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showMediaOutputSwitcher(String str, UserHandle userHandle) {
        if (TextUtils.isEmpty(str)) {
            Log.e("MediaOutputSwitcherDialogUI", "Unable to launch media output dialog. Package name is empty.");
        } else {
            this.mMediaOutputDialogManager.createAndShow(str, false, null, true, userHandle, null);
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
    }
}
