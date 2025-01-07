package androidx.datastore.preferences.protobuf;

import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ExtensionRegistryLite {
    public static final ExtensionRegistryLite EMPTY_REGISTRY_LITE;
    public static volatile ExtensionRegistryLite emptyRegistry;

    static {
        ExtensionRegistryLite extensionRegistryLite = new ExtensionRegistryLite();
        Collections.emptyMap();
        EMPTY_REGISTRY_LITE = extensionRegistryLite;
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        ExtensionRegistryLite extensionRegistryLite = emptyRegistry;
        if (extensionRegistryLite == null) {
            synchronized (ExtensionRegistryLite.class) {
                try {
                    extensionRegistryLite = emptyRegistry;
                    if (extensionRegistryLite == null) {
                        Class cls = ExtensionRegistryFactory.EXTENSION_REGISTRY_CLASS;
                        ExtensionRegistryLite extensionRegistryLite2 = null;
                        if (cls != null) {
                            try {
                                Class[] clsArr = new Class[0];
                                extensionRegistryLite2 = (ExtensionRegistryLite) cls.getDeclaredMethod("getEmptyRegistry", null).invoke(null, null);
                            } catch (Exception unused) {
                            }
                        }
                        if (extensionRegistryLite2 == null) {
                            extensionRegistryLite2 = EMPTY_REGISTRY_LITE;
                        }
                        emptyRegistry = extensionRegistryLite2;
                        extensionRegistryLite = extensionRegistryLite2;
                    }
                } finally {
                }
            }
        }
        return extensionRegistryLite;
    }
}
