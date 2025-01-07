package com.google.protobuf;

import java.util.Collections;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExtensionRegistryLite {
    public static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = new ExtensionRegistryLite();
    public static volatile ExtensionRegistryLite emptyRegistry;
    public final Map extensionsByNumber;

    public ExtensionRegistryLite() {
        Collections.emptyMap();
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
