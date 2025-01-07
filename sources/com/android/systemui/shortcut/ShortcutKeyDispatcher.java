package com.android.systemui.shortcut;

import android.content.Context;
import android.os.RemoteException;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import com.android.systemui.CoreStartable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShortcutKeyDispatcher implements CoreStartable {
    public final Context mContext;
    public final ShortcutKeyServiceProxy mShortcutKeyServiceProxy = new ShortcutKeyServiceProxy(this);
    public final IWindowManager mWindowManagerService = WindowManagerGlobal.getWindowManagerService();

    public ShortcutKeyDispatcher(Context context) {
        this.mContext = context;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        try {
            this.mWindowManagerService.registerShortcutKey(281474976710727L, this.mShortcutKeyServiceProxy);
        } catch (RemoteException unused) {
        }
        try {
            this.mWindowManagerService.registerShortcutKey(281474976710728L, this.mShortcutKeyServiceProxy);
        } catch (RemoteException unused2) {
        }
    }
}
