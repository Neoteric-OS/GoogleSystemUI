package com.android.systemui.mediaprojection.appselector.data;

import android.content.pm.UserInfo;
import android.os.UserManager;
import com.android.systemui.mediaprojection.appselector.data.RecentTask;
import com.android.systemui.settings.UserTracker;
import com.android.wm.shell.recents.RecentTasks;
import java.util.Optional;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShellRecentTaskListProvider {
    public final Executor backgroundExecutor;
    public final CoroutineDispatcher coroutineDispatcher;
    public final Optional recentTasks;
    public final Lazy recents$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.mediaprojection.appselector.data.ShellRecentTaskListProvider$recents$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return (RecentTasks) ShellRecentTaskListProvider.this.recentTasks.orElse(null);
        }
    });
    public final UserManager userManager;
    public final UserTracker userTracker;

    public ShellRecentTaskListProvider(CoroutineDispatcher coroutineDispatcher, Executor executor, Optional optional, UserTracker userTracker, UserManager userManager) {
        this.coroutineDispatcher = coroutineDispatcher;
        this.backgroundExecutor = executor;
        this.recentTasks = optional;
        this.userTracker = userTracker;
        this.userManager = userManager;
    }

    public static final RecentTask.UserType access$toUserType(ShellRecentTaskListProvider shellRecentTaskListProvider, UserInfo userInfo) {
        shellRecentTaskListProvider.getClass();
        return userInfo.isCloneProfile() ? RecentTask.UserType.CLONED : userInfo.isManagedProfile() ? RecentTask.UserType.WORK : userInfo.isPrivateProfile() ? RecentTask.UserType.PRIVATE : RecentTask.UserType.STANDARD;
    }

    public final Object loadRecentTasks(Continuation continuation) {
        return BuildersKt.withContext(this.coroutineDispatcher, new ShellRecentTaskListProvider$loadRecentTasks$2(this, null), continuation);
    }
}
