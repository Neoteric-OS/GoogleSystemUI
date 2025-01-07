package com.android.systemui.statusbar.policy;

import com.android.systemui.Dumpable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SecurityController extends CallbackController, Dumpable {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface SecurityControllerCallback {
        void onStateChanged();
    }
}
