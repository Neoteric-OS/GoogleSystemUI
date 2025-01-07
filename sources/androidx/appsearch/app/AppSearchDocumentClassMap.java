package androidx.appsearch.app;

import android.util.Log;
import androidx.appsearch.builtintypes.properties.C$$__AppSearch__Keyword;
import androidx.collection.ArrayMap;
import androidx.collection.IndexBasedArrayIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceConfigurationError;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AppSearchDocumentClassMap {
    private static final String TAG = "AppSearchDocumentClassM";
    private static volatile Map sGlobalMap;
    private static final Object sLock = new Object();
    private static volatile Map sCachedAppSearchClasses = new ArrayMap(0);

    public static Map buildGlobalMapLocked() {
        ArrayMap arrayMap = new ArrayMap(0);
        try {
            Iterator it = Arrays.asList(new AppSearchDocumentClassMap() { // from class: androidx.appsearch.usagereporting.$$__AppSearch__DocumentClassMap_eba6b1601ac44645c9e91065be7525abbaef0b2e56cde9881fde605f05641bd0_0
                @Override // androidx.appsearch.app.AppSearchDocumentClassMap
                public Map getMap() {
                    HashMap hashMap = new HashMap();
                    hashMap.put(C$$__AppSearch__ClickAction.SCHEMA_NAME, Arrays.asList("androidx.appsearch.usagereporting.ClickAction"));
                    hashMap.put(C$$__AppSearch__SearchAction.SCHEMA_NAME, Arrays.asList("androidx.appsearch.usagereporting.SearchAction"));
                    hashMap.put(C$$__AppSearch__TakenAction.SCHEMA_NAME, Arrays.asList("androidx.appsearch.usagereporting.TakenAction"));
                    return hashMap;
                }
            }, new AppSearchDocumentClassMap() { // from class: androidx.appsearch.builtintypes.$$__AppSearch__DocumentClassMap_301e161bc79d76a748ba3f8e4a606d5c4d44d194712197fa0f68a8281260677f_0
                @Override // androidx.appsearch.app.AppSearchDocumentClassMap
                public Map getMap() {
                    HashMap hashMap = new HashMap();
                    hashMap.put(C$$__AppSearch__StopwatchLap.SCHEMA_NAME, Arrays.asList("androidx.appsearch.builtintypes.StopwatchLap"));
                    hashMap.put(C$$__AppSearch__ContactPoint.SCHEMA_NAME, Arrays.asList("androidx.appsearch.builtintypes.ContactPoint"));
                    hashMap.put(C$$__AppSearch__Thing.SCHEMA_NAME, Arrays.asList("androidx.appsearch.builtintypes.Thing"));
                    hashMap.put(C$$__AppSearch__Person.SCHEMA_NAME, Arrays.asList("androidx.appsearch.builtintypes.Person"));
                    hashMap.put(C$$__AppSearch__AlarmInstance.SCHEMA_NAME, Arrays.asList("androidx.appsearch.builtintypes.AlarmInstance"));
                    hashMap.put(C$$__AppSearch__Keyword.SCHEMA_NAME, Arrays.asList("androidx.appsearch.builtintypes.properties.Keyword"));
                    hashMap.put(C$$__AppSearch__Alarm.SCHEMA_NAME, Arrays.asList("androidx.appsearch.builtintypes.Alarm"));
                    hashMap.put(C$$__AppSearch__Timer.SCHEMA_NAME, Arrays.asList("androidx.appsearch.builtintypes.Timer"));
                    hashMap.put(C$$__AppSearch__PotentialAction.SCHEMA_NAME, Arrays.asList("androidx.appsearch.builtintypes.PotentialAction"));
                    hashMap.put(C$$__AppSearch__ImageObject.SCHEMA_NAME, Arrays.asList("androidx.appsearch.builtintypes.ImageObject"));
                    hashMap.put(C$$__AppSearch__Stopwatch.SCHEMA_NAME, Arrays.asList("androidx.appsearch.builtintypes.Stopwatch"));
                    return hashMap;
                }
            }).iterator();
            while (it.hasNext()) {
                for (Map.Entry entry : ((AppSearchDocumentClassMap) it.next()).getMap().entrySet()) {
                    String str = (String) entry.getKey();
                    List list = (List) arrayMap.get(str);
                    if (list == null) {
                        list = new ArrayList();
                        arrayMap.put(str, list);
                    }
                    list.addAll((Collection) entry.getValue());
                }
            }
            Iterator it2 = ((ArrayMap.KeySet) arrayMap.keySet()).iterator();
            while (true) {
                IndexBasedArrayIterator indexBasedArrayIterator = (IndexBasedArrayIterator) it2;
                if (!indexBasedArrayIterator.hasNext()) {
                    return Collections.unmodifiableMap(arrayMap);
                }
                String str2 = (String) indexBasedArrayIterator.next();
                List list2 = (List) arrayMap.get(str2);
                Objects.requireNonNull(list2);
                arrayMap.put(str2, Collections.unmodifiableList(list2));
            }
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }

    public static Class getAppSearchDocumentClass(String str) {
        Class<?> cls;
        Object obj = sLock;
        synchronized (obj) {
            cls = (Class) sCachedAppSearchClasses.get(str);
        }
        if (cls == null) {
            cls = Class.forName(str);
            synchronized (obj) {
                sCachedAppSearchClasses.put(str, cls);
            }
        }
        return cls;
    }

    public static Class getAssignableClassBySchemaName(Map map, String str, Class cls) {
        Class<?> appSearchDocumentClass;
        List list = (List) map.get(str);
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            String str2 = (String) list.get(i);
            try {
                appSearchDocumentClass = getAppSearchDocumentClass(str2);
            } catch (ClassNotFoundException unused) {
                Log.w(TAG, "Failed to load document class \"" + str2 + "\". Perhaps the class was proguarded out?");
            }
            if (cls.isAssignableFrom(appSearchDocumentClass)) {
                return appSearchDocumentClass.asSubclass(cls);
            }
            continue;
        }
        return null;
    }

    public static Map getGlobalMap() {
        if (sGlobalMap == null) {
            synchronized (sLock) {
                try {
                    if (sGlobalMap == null) {
                        sGlobalMap = buildGlobalMapLocked();
                    }
                } finally {
                }
            }
        }
        return sGlobalMap;
    }

    public abstract Map getMap();
}
