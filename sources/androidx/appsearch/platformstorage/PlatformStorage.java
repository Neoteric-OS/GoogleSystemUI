package androidx.appsearch.platformstorage;

import android.content.Context;
import androidx.appsearch.app.AppSearchEnvironmentFactory;
import androidx.appsearch.app.JetpackAppSearchEnvironment;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PlatformStorage {
    public static final Executor EXECUTOR;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class GlobalSearchContext {
        public final Context mContext;
        public final Executor mExecutor;

        public GlobalSearchContext(Context context, Executor executor) {
            context.getClass();
            this.mContext = context;
            executor.getClass();
            this.mExecutor = executor;
        }
    }

    static {
        if (AppSearchEnvironmentFactory.sAppSearchEnvironment == null) {
            synchronized (AppSearchEnvironmentFactory.class) {
                try {
                    if (AppSearchEnvironmentFactory.sAppSearchEnvironment == null) {
                        AppSearchEnvironmentFactory.sAppSearchEnvironment = new JetpackAppSearchEnvironment();
                    }
                } finally {
                }
            }
        }
        EXECUTOR = Executors.newCachedThreadPool();
    }
}
