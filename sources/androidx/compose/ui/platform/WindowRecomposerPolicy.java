package androidx.compose.ui.platform;

import androidx.compose.ui.platform.WindowRecomposerFactory;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class WindowRecomposerPolicy {
    public static final AtomicReference factory = new AtomicReference(WindowRecomposerFactory.Companion.LifecycleAware);
}
