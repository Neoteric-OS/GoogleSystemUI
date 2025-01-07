package com.android.systemui.wallet.dagger;

import android.content.Context;
import android.service.quickaccesswallet.QuickAccessWalletClient;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WalletModule_ProvideQuickAccessWalletClientFactory implements Provider {
    public static QuickAccessWalletClient provideQuickAccessWalletClient(Context context, Executor executor) {
        QuickAccessWalletClient create = QuickAccessWalletClient.create(context, executor);
        Preconditions.checkNotNullFromProvides(create);
        return create;
    }
}
