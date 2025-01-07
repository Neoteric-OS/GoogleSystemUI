package androidx.appsearch.app;

import androidx.appsearch.exceptions.AppSearchException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DocumentClassFactoryRegistry {
    public static volatile DocumentClassFactoryRegistry sInstance;
    public final Map mFactories = new HashMap();

    public static DocumentClassFactoryRegistry getInstance() {
        if (sInstance == null) {
            synchronized (DocumentClassFactoryRegistry.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new DocumentClassFactoryRegistry();
                    }
                } finally {
                }
            }
        }
        return sInstance;
    }

    public static DocumentClassFactory loadFactoryByReflection(Class cls) {
        String str;
        Package r0 = cls.getPackage();
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            throw new AppSearchException(2, "Failed to find simple name for document class \"" + cls + "\". Perhaps it is anonymous?", null);
        }
        if (r0 != null) {
            str = r0.getName() + ".";
            canonicalName = canonicalName.substring(str.length()).replace(".", "$$__");
        } else {
            str = "";
        }
        String m$1 = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(str, "$$__AppSearch__", canonicalName);
        try {
            try {
                Class[] clsArr = new Class[0];
                return (DocumentClassFactory) Class.forName(m$1).getDeclaredConstructor(null).newInstance(null);
            } catch (Exception e) {
                throw new AppSearchException(2, DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Failed to construct document class converter \"", m$1, "\""), e);
            }
        } catch (ClassNotFoundException e2) {
            Class superclass = cls.getSuperclass();
            Class<?>[] interfaces = cls.getInterfaces();
            Class cls2 = superclass != Object.class ? superclass : null;
            int length = interfaces.length;
            if (cls2 != null) {
                length++;
            }
            if (length == 1) {
                return cls2 != null ? loadFactoryByReflection(cls2) : loadFactoryByReflection(interfaces[0]);
            }
            String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Failed to find document class converter \"", m$1, "\". Perhaps the annotation processor was not run or the class was proguarded out?");
            if (length > 1) {
                m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(m, " Or, this class may not have been annotated with @Document, and there is an ambiguity to determine a unique @Document annotated parent class/interface.");
            }
            throw new AppSearchException(2, m, e2);
        }
    }

    public final DocumentClassFactory getOrCreateFactory(Class cls) {
        DocumentClassFactory documentClassFactory;
        cls.getClass();
        synchronized (this) {
            documentClassFactory = (DocumentClassFactory) this.mFactories.get(cls);
        }
        if (documentClassFactory == null) {
            documentClassFactory = loadFactoryByReflection(cls);
            synchronized (this) {
                try {
                    DocumentClassFactory documentClassFactory2 = (DocumentClassFactory) this.mFactories.get(cls);
                    if (documentClassFactory2 == null) {
                        this.mFactories.put(cls, documentClassFactory);
                    } else {
                        documentClassFactory = documentClassFactory2;
                    }
                } finally {
                }
            }
        }
        return documentClassFactory;
    }
}
