package com.android.systemui.user.utils;

import android.content.Context;
import android.os.UserHandle;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserScopedServiceImpl {
    public final Context context;
    public final Class serviceType;

    public UserScopedServiceImpl(Context context, Class cls) {
        this.context = context;
        this.serviceType = cls;
    }

    public final Object forUser(UserHandle userHandle) {
        Context createContextAsUser;
        if (Intrinsics.areEqual(this.context.getUser(), userHandle)) {
            createContextAsUser = this.context;
        } else {
            createContextAsUser = this.context.createContextAsUser(userHandle, 0);
            Intrinsics.checkNotNull(createContextAsUser);
        }
        Object systemService = createContextAsUser.getSystemService((Class<Object>) this.serviceType);
        if (systemService != null) {
            return systemService;
        }
        throw new IllegalArgumentException("Required value was null.");
    }
}
