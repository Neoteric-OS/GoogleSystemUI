package com.android.systemui.mediaprojection.appselector.data;

import android.content.ComponentName;
import android.content.Context;
import com.android.systemui.mediaprojection.appselector.data.RecentTask;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BadgedAppIconLoader {
    public final CoroutineDispatcher backgroundDispatcher;
    public final BasicPackageManagerAppIconLoader basicAppIconLoader;
    public final Context context;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider iconFactoryProvider;

    public BadgedAppIconLoader(BasicPackageManagerAppIconLoader basicPackageManagerAppIconLoader, CoroutineDispatcher coroutineDispatcher, Context context, DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.SwitchingProvider switchingProvider) {
        this.basicAppIconLoader = basicPackageManagerAppIconLoader;
        this.backgroundDispatcher = coroutineDispatcher;
        this.context = context;
        this.iconFactoryProvider = switchingProvider;
    }

    public final Object loadIcon(int i, RecentTask.UserType userType, ComponentName componentName, Continuation continuation) {
        return BuildersKt.withContext(this.backgroundDispatcher, new BadgedAppIconLoader$loadIcon$2(this, i, componentName, userType, null), continuation);
    }
}
