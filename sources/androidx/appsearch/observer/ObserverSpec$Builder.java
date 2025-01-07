package androidx.appsearch.observer;

import androidx.appsearch.app.DocumentClassFactoryRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ObserverSpec$Builder {
    public boolean mBuilt;
    public ArrayList mFilterSchemas;

    public final void addFilterDocumentClasses(Class... clsArr) {
        resetIfBuilt();
        List asList = Arrays.asList(clsArr);
        asList.getClass();
        resetIfBuilt();
        ArrayList arrayList = new ArrayList(asList.size());
        DocumentClassFactoryRegistry documentClassFactoryRegistry = DocumentClassFactoryRegistry.getInstance();
        Iterator it = asList.iterator();
        while (it.hasNext()) {
            arrayList.add(documentClassFactoryRegistry.getOrCreateFactory((Class) it.next()).getSchemaName());
        }
        resetIfBuilt();
        this.mFilterSchemas.addAll(arrayList);
    }

    public final void resetIfBuilt() {
        if (this.mBuilt) {
            this.mFilterSchemas = new ArrayList(this.mFilterSchemas);
            this.mBuilt = false;
        }
    }
}
