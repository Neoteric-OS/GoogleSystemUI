package okio.internal;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import okio.internal.ResourceFileSystem;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ResourceFileSystem$toJarRoot$zip$1 extends Lambda implements Function1 {
    public static final ResourceFileSystem$toJarRoot$zip$1 INSTANCE = new ResourceFileSystem$toJarRoot$zip$1();

    public ResourceFileSystem$toJarRoot$zip$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ResourceFileSystem.Companion companion = ResourceFileSystem.Companion;
        return Boolean.valueOf(ResourceFileSystem.Companion.access$keepPath(((ZipEntry) obj).canonicalPath));
    }
}
