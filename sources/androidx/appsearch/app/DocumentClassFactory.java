package androidx.appsearch.app;

import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface DocumentClassFactory {
    Object fromGenericDocument(GenericDocument genericDocument, Map map);

    String getSchemaName();

    GenericDocument toGenericDocument(Object obj);
}
