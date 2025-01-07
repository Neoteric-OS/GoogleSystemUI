package com.android.systemui.user;

import android.content.Context;
import android.os.UserManager;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserCreator {
    public final Executor bgExecutor;
    public final Context context;
    public final Executor mainExecutor;
    public final UserManager userManager;

    public UserCreator(Context context, UserManager userManager, Executor executor, Executor executor2) {
        this.context = context;
        this.userManager = userManager;
        this.mainExecutor = executor;
        this.bgExecutor = executor2;
    }
}
