package com.android.wm.shell.dagger;

import android.content.Context;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.compatui.impl.DefaultCompatUIComponentFactory;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellBaseModule_ProvideCompatUIComponentFactoryFactory implements Provider {
    public static DefaultCompatUIComponentFactory provideCompatUIComponentFactory(Context context, SyncTransactionQueue syncTransactionQueue, DisplayController displayController) {
        return new DefaultCompatUIComponentFactory();
    }
}
