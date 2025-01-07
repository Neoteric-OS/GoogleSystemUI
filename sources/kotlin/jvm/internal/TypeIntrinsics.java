package kotlin.jvm.internal;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.compose.runtime.internal.ComposableLambda;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TypeIntrinsics {
    public static Collection asMutableCollection(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableCollection)) {
            throwCce(obj, "kotlin.collections.MutableCollection");
            throw null;
        }
        try {
            return (Collection) obj;
        } catch (ClassCastException e) {
            Intrinsics.sanitizeStackTrace(TypeIntrinsics.class.getName(), e);
            throw e;
        }
    }

    public static List asMutableList(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableList)) {
            throwCce(obj, "kotlin.collections.MutableList");
            throw null;
        }
        try {
            return (List) obj;
        } catch (ClassCastException e) {
            Intrinsics.sanitizeStackTrace(TypeIntrinsics.class.getName(), e);
            throw e;
        }
    }

    public static Map asMutableMap(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap)) {
            throwCce(obj, "kotlin.collections.MutableMap");
            throw null;
        }
        try {
            return (Map) obj;
        } catch (ClassCastException e) {
            Intrinsics.sanitizeStackTrace(TypeIntrinsics.class.getName(), e);
            throw e;
        }
    }

    public static void beforeCheckcastToFunctionOfArity(int i, Object obj) {
        if (obj == null || isFunctionOfArity(i, obj)) {
            return;
        }
        throwCce(obj, "kotlin.jvm.functions.Function" + i);
        throw null;
    }

    public static boolean isFunctionOfArity(int i, Object obj) {
        int i2;
        if (!(obj instanceof Function)) {
            return false;
        }
        if (obj instanceof FunctionBase) {
            i2 = ((FunctionBase) obj).getArity();
        } else if (obj instanceof Function0) {
            i2 = 0;
        } else if (obj instanceof Function1) {
            i2 = 1;
        } else if (obj instanceof Function2) {
            i2 = 2;
        } else if (obj instanceof Function3) {
            i2 = 3;
        } else if (obj instanceof Function4) {
            i2 = 4;
        } else if (obj instanceof Function5) {
            i2 = 5;
        } else if (obj instanceof Function6) {
            i2 = 6;
        } else if (obj instanceof Function7) {
            i2 = 7;
        } else {
            boolean z = obj instanceof ComposableLambda;
            i2 = z ? 8 : z ? 9 : z ? 10 : z ? 11 : z ? 13 : z ? 14 : z ? 15 : z ? 16 : z ? 17 : z ? 18 : z ? 19 : z ? 20 : z ? 21 : -1;
        }
        return i2 == i;
    }

    public static void throwCce(Object obj, String str) {
        ClassCastException classCastException = new ClassCastException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(obj == null ? "null" : obj.getClass().getName(), " cannot be cast to ", str));
        Intrinsics.sanitizeStackTrace(TypeIntrinsics.class.getName(), classCastException);
        throw classCastException;
    }
}
