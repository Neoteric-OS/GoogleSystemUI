package androidx.window.embedding;

import android.util.Log;
import androidx.window.WindowSdkExtensions$Companion$getInstance$1;
import androidx.window.core.ConsumerAdapter;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmbeddingCompat {
    public final EmbeddingAdapter adapter;
    public final ConsumerAdapter consumerAdapter;
    public final ActivityEmbeddingComponent embeddingExtension;
    public final WindowSdkExtensions$Companion$getInstance$1 windowSdkExtensions = new WindowSdkExtensions$Companion$getInstance$1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static boolean isEmbeddingAvailable() {
            try {
                ClassLoader classLoader = EmbeddingCompat.class.getClassLoader();
                if (classLoader != null) {
                    return new SafeActivityEmbeddingComponentProvider(classLoader, new ConsumerAdapter(classLoader), WindowExtensionsProvider.getWindowExtensions()).getActivityEmbeddingComponent() != null;
                }
                return false;
            } catch (NoClassDefFoundError unused) {
                Log.d("EmbeddingCompat", "Embedding extension version not found");
                return false;
            } catch (UnsupportedOperationException unused2) {
                Log.d("EmbeddingCompat", "Stub Extension");
                return false;
            }
        }
    }

    public EmbeddingCompat(ActivityEmbeddingComponent activityEmbeddingComponent, EmbeddingAdapter embeddingAdapter, ConsumerAdapter consumerAdapter) {
        this.embeddingExtension = activityEmbeddingComponent;
        this.adapter = embeddingAdapter;
        this.consumerAdapter = consumerAdapter;
    }
}
