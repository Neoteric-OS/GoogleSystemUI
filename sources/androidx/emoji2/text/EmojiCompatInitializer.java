package androidx.emoji2.text;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.EmojiCompatInitializer;
import androidx.emoji2.text.FontRequestEmojiCompatConfig;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleInitializer;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class EmojiCompatInitializer implements Initializer {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BackgroundDefaultConfig extends EmojiCompat.Config {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BackgroundDefaultLoader implements EmojiCompat.MetadataRepoLoader {
        public final Context mContext;

        public BackgroundDefaultLoader(Context context) {
            this.mContext = context.getApplicationContext();
        }

        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoader
        public final void load(final EmojiCompat.InitCallback initCallback) {
            final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ConcurrencyHelpers$$ExternalSyntheticLambda1("EmojiCompatInitializer"));
            threadPoolExecutor.allowCoreThreadTimeOut(true);
            threadPoolExecutor.execute(new Runnable() { // from class: androidx.emoji2.text.EmojiCompatInitializer$BackgroundDefaultLoader$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EmojiCompatInitializer.BackgroundDefaultLoader backgroundDefaultLoader = EmojiCompatInitializer.BackgroundDefaultLoader.this;
                    final EmojiCompat.InitCallback initCallback2 = initCallback;
                    final ThreadPoolExecutor threadPoolExecutor2 = threadPoolExecutor;
                    backgroundDefaultLoader.getClass();
                    try {
                        FontRequestEmojiCompatConfig create = DefaultEmojiCompatConfig.create(backgroundDefaultLoader.mContext);
                        if (create == null) {
                            throw new RuntimeException("EmojiCompat font provider not available on this device.");
                        }
                        FontRequestEmojiCompatConfig.FontRequestMetadataLoader fontRequestMetadataLoader = (FontRequestEmojiCompatConfig.FontRequestMetadataLoader) create.mMetadataLoader;
                        synchronized (fontRequestMetadataLoader.mLock) {
                            fontRequestMetadataLoader.mExecutor = threadPoolExecutor2;
                        }
                        create.mMetadataLoader.load(new EmojiCompat.InitCallback() { // from class: androidx.emoji2.text.EmojiCompatInitializer.BackgroundDefaultLoader.1
                            @Override // androidx.emoji2.text.EmojiCompat.InitCallback
                            public final void onFailed(Throwable th) {
                                try {
                                    EmojiCompat.InitCallback.this.onFailed(th);
                                } finally {
                                    threadPoolExecutor2.shutdown();
                                }
                            }

                            @Override // androidx.emoji2.text.EmojiCompat.InitCallback
                            public final void onLoaded(MetadataRepo metadataRepo) {
                                try {
                                    EmojiCompat.InitCallback.this.onLoaded(metadataRepo);
                                } finally {
                                    threadPoolExecutor2.shutdown();
                                }
                            }
                        });
                    } catch (Throwable th) {
                        initCallback2.onFailed(th);
                        threadPoolExecutor2.shutdown();
                    }
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LoadEmojiCompatRunnable implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
            try {
                Trace.beginSection("EmojiCompat.EmojiCompatInitializer.run");
                if (EmojiCompat.isConfigured()) {
                    EmojiCompat.get().load();
                }
            } finally {
                Trace.endSection();
            }
        }
    }

    @Override // androidx.startup.Initializer
    public final Object create(Context context) {
        Object obj;
        BackgroundDefaultConfig backgroundDefaultConfig = new BackgroundDefaultConfig(new BackgroundDefaultLoader(context));
        backgroundDefaultConfig.mMetadataLoadStrategy = 1;
        if (EmojiCompat.sInstance == null) {
            synchronized (EmojiCompat.INSTANCE_LOCK) {
                try {
                    if (EmojiCompat.sInstance == null) {
                        EmojiCompat.sInstance = new EmojiCompat(backgroundDefaultConfig);
                    }
                } finally {
                }
            }
        }
        AppInitializer appInitializer = AppInitializer.getInstance(context);
        appInitializer.getClass();
        synchronized (AppInitializer.sLock) {
            try {
                obj = appInitializer.mInitialized.get(ProcessLifecycleInitializer.class);
                if (obj == null) {
                    obj = appInitializer.doInitialize(ProcessLifecycleInitializer.class, new HashSet());
                }
            } finally {
            }
        }
        final Lifecycle lifecycle = ((LifecycleOwner) obj).getLifecycle();
        lifecycle.addObserver(new DefaultLifecycleObserver(this) { // from class: androidx.emoji2.text.EmojiCompatInitializer.1
            public final /* synthetic */ EmojiCompatInitializer this$0;

            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public final void onResume$1() {
                Handler.createAsync(Looper.getMainLooper()).postDelayed(new LoadEmojiCompatRunnable(), 500L);
                lifecycle.removeObserver(this);
            }
        });
        return Boolean.TRUE;
    }

    @Override // androidx.startup.Initializer
    public final List dependencies() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }
}
