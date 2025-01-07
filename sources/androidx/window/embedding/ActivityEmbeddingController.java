package androidx.window.embedding;

import android.content.Context;
import androidx.window.embedding.EmbeddingBackend;
import androidx.window.embedding.ExtensionEmbeddingBackend;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ActivityEmbeddingController {
    public final ExtensionEmbeddingBackend backend;

    public ActivityEmbeddingController(ExtensionEmbeddingBackend extensionEmbeddingBackend) {
        this.backend = extensionEmbeddingBackend;
    }

    public static final ActivityEmbeddingController getInstance(Context context) {
        EmbeddingBackend.Companion.getClass();
        Function1 function1 = EmbeddingBackend.Companion.decorator;
        ExtensionEmbeddingBackend extensionEmbeddingBackend = ExtensionEmbeddingBackend.globalInstance;
        if (ExtensionEmbeddingBackend.globalInstance == null) {
            ReentrantLock reentrantLock = ExtensionEmbeddingBackend.globalLock;
            reentrantLock.lock();
            try {
                if (ExtensionEmbeddingBackend.globalInstance == null) {
                    ExtensionEmbeddingBackend.globalInstance = new ExtensionEmbeddingBackend(context.getApplicationContext(), ExtensionEmbeddingBackend.Companion.initAndVerifyEmbeddingExtension());
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        ExtensionEmbeddingBackend extensionEmbeddingBackend2 = ExtensionEmbeddingBackend.globalInstance;
        Intrinsics.checkNotNull(extensionEmbeddingBackend2);
        ((EmbeddingBackend$Companion$decorator$1) function1).getClass();
        return new ActivityEmbeddingController(extensionEmbeddingBackend2);
    }
}
