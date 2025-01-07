package androidx.appsearch.app;

import android.util.Log;
import androidx.appsearch.safeparcel.GenericDocumentParcel;
import androidx.appsearch.safeparcel.PropertyParcel;
import androidx.appsearch.util.IndentingStringBuilder;
import androidx.collection.ArrayMap;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GenericDocument {
    public final GenericDocumentParcel mDocumentParcel;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final Builder mBuilderTypeInstance;
        public final GenericDocumentParcel.Builder mDocumentParcelBuilder;

        public Builder(String str, String str2, String str3) {
            str.getClass();
            str2.getClass();
            str3.getClass();
            this.mBuilderTypeInstance = this;
            GenericDocumentParcel.Builder builder = new GenericDocumentParcel.Builder();
            builder.mNamespace = str;
            builder.mId = str2;
            builder.mSchemaType = str3;
            builder.mCreationTimestampMillis = -1L;
            builder.mTtlMillis = 0L;
            builder.mScore = 0;
            builder.mPropertyMap = new ArrayMap(0);
            this.mDocumentParcelBuilder = builder;
        }

        public static void validatePropertyName(String str) {
            if (str.isEmpty()) {
                throw new IllegalArgumentException("Property name cannot be blank.");
            }
        }

        public final GenericDocument build() {
            GenericDocumentParcel.Builder builder = this.mDocumentParcelBuilder;
            if (builder.mCreationTimestampMillis == -1) {
                builder.mCreationTimestampMillis = System.currentTimeMillis();
            }
            return new GenericDocument(new GenericDocumentParcel(builder.mNamespace, builder.mId, builder.mSchemaType, builder.mCreationTimestampMillis, builder.mTtlMillis, builder.mScore, new ArrayList(builder.mPropertyMap.values()), builder.mParentTypes));
        }

        public final void setPropertyBoolean(String str, boolean... zArr) {
            zArr.getClass();
            validatePropertyName(str);
            GenericDocumentParcel.Builder builder = this.mDocumentParcelBuilder;
            builder.getClass();
            builder.mPropertyMap.put(str, new PropertyParcel(str, null, null, null, zArr, null, null, null));
        }

        public final void setPropertyDocument(String str, GenericDocument... genericDocumentArr) {
            validatePropertyName(str);
            GenericDocumentParcel[] genericDocumentParcelArr = new GenericDocumentParcel[genericDocumentArr.length];
            for (int i = 0; i < genericDocumentArr.length; i++) {
                GenericDocument genericDocument = genericDocumentArr[i];
                if (genericDocument == null) {
                    throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("The document at ", " is null.", i));
                }
                genericDocumentParcelArr[i] = genericDocument.mDocumentParcel;
            }
            GenericDocumentParcel.Builder builder = this.mDocumentParcelBuilder;
            builder.getClass();
            builder.mPropertyMap.put(str, new PropertyParcel(str, null, null, null, null, null, genericDocumentParcelArr, null));
        }

        public final void setPropertyLong(String str, long... jArr) {
            jArr.getClass();
            validatePropertyName(str);
            GenericDocumentParcel.Builder builder = this.mDocumentParcelBuilder;
            builder.getClass();
            builder.mPropertyMap.put(str, new PropertyParcel(str, null, jArr, null, null, null, null, null));
        }

        public final void setPropertyString(String str, String... strArr) {
            strArr.getClass();
            validatePropertyName(str);
            for (int i = 0; i < strArr.length; i++) {
                if (strArr[i] == null) {
                    throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("The String at ", " is null.", i));
                }
            }
            GenericDocumentParcel.Builder builder = this.mDocumentParcelBuilder;
            builder.getClass();
            builder.mPropertyMap.put(str, new PropertyParcel(str, strArr, null, null, null, null, null, null));
        }

        public final Builder setScore(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Document score cannot be negative.");
            }
            this.mDocumentParcelBuilder.mScore = i;
            return this.mBuilderTypeInstance;
        }

        public final Builder setTtlMillis(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("Document ttlMillis cannot be negative.");
            }
            GenericDocumentParcel.Builder builder = this.mDocumentParcelBuilder;
            if (j >= 0) {
                builder.mTtlMillis = j;
                return this.mBuilderTypeInstance;
            }
            builder.getClass();
            throw new IllegalArgumentException("Document ttlMillis cannot be negative.");
        }
    }

    static {
        new Builder("", "", "").build();
    }

    public GenericDocument(GenericDocumentParcel genericDocumentParcel) {
        Objects.requireNonNull(genericDocumentParcel);
        this.mDocumentParcel = genericDocumentParcel;
    }

    public static GenericDocument fromDocumentClass(Object obj) {
        obj.getClass();
        DocumentClassFactoryRegistry documentClassFactoryRegistry = DocumentClassFactoryRegistry.getInstance();
        documentClassFactoryRegistry.getClass();
        return documentClassFactoryRegistry.getOrCreateFactory(obj.getClass()).toGenericDocument(obj);
    }

    /* JADX WARN: Code restructure failed: missing block: B:152:0x022a, code lost:
    
        android.util.Log.e("AppSearchGenericDocumen", "Failed to apply path to document; no nested value found: " + r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x023d, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x023e, code lost:
    
        if (r10 == null) goto L189;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0242, code lost:
    
        if ((r10 instanceof androidx.appsearch.safeparcel.PropertyParcel) == false) goto L190;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0244, code lost:
    
        r10 = (androidx.appsearch.safeparcel.PropertyParcel) r10;
        r8 = r10.mStringValues;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0248, code lost:
    
        if (r8 == null) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x024a, code lost:
    
        r1 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x026b, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x024c, code lost:
    
        r8 = r10.mLongValues;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x024e, code lost:
    
        if (r8 == null) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0251, code lost:
    
        r8 = r10.mDoubleValues;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0253, code lost:
    
        if (r8 == null) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0256, code lost:
    
        r8 = r10.mBooleanValues;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x0258, code lost:
    
        if (r8 == null) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x025b, code lost:
    
        r8 = r10.mBytesValues;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x025d, code lost:
    
        if (r8 == null) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0260, code lost:
    
        r8 = r10.mDocumentValues;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x0262, code lost:
    
        if (r8 == null) goto L158;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0265, code lost:
    
        r8 = r10.mEmbeddingValues;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0267, code lost:
    
        if (r8 == null) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:?, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:?, code lost:
    
        return r10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object getRawPropertyFromRawDocument(androidx.appsearch.app.PropertyPath r8, int r9, androidx.collection.ArrayMap r10) {
        /*
            Method dump skipped, instructions count: 621
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appsearch.app.GenericDocument.getRawPropertyFromRawDocument(androidx.appsearch.app.PropertyPath, int, androidx.collection.ArrayMap):java.lang.Object");
    }

    public static Object safeCastProperty(String str, Object obj, Class cls) {
        if (obj == null) {
            return null;
        }
        try {
            return cls.cast(obj);
        } catch (ClassCastException e) {
            Log.w("AppSearchGenericDocumen", "Error casting to requested type for path \"" + str + "\"", e);
            return null;
        }
    }

    public static void warnIfSinglePropertyTooLong(String str, String str2, int i) {
        if (i > 1) {
            StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("The value for \"", str2, "\" contains ", i, " elements. Only the first one will be returned from getProperty");
            m.append(str);
            m.append("(). Try getProperty");
            m.append(str);
            m.append("Array().");
            Log.w("AppSearchGenericDocumen", m.toString());
        }
    }

    public final void appendGenericDocumentString(IndentingStringBuilder indentingStringBuilder) {
        indentingStringBuilder.applyIndentToString("{\n");
        indentingStringBuilder.increaseIndentLevel();
        indentingStringBuilder.applyIndentToString("namespace: \"");
        GenericDocumentParcel genericDocumentParcel = this.mDocumentParcel;
        indentingStringBuilder.applyIndentToString(genericDocumentParcel.mNamespace);
        indentingStringBuilder.applyIndentToString("\",\n");
        indentingStringBuilder.applyIndentToString("id: \"");
        indentingStringBuilder.applyIndentToString(genericDocumentParcel.mId);
        indentingStringBuilder.applyIndentToString("\",\n");
        indentingStringBuilder.applyIndentToString("score: ");
        indentingStringBuilder.applyIndentToString(Integer.valueOf(genericDocumentParcel.mScore).toString());
        indentingStringBuilder.applyIndentToString(",\n");
        indentingStringBuilder.applyIndentToString("schemaType: \"");
        indentingStringBuilder.applyIndentToString(genericDocumentParcel.mSchemaType);
        indentingStringBuilder.applyIndentToString("\",\n");
        List list = genericDocumentParcel.mParentTypes;
        List unmodifiableList = list == null ? null : Collections.unmodifiableList(list);
        if (unmodifiableList != null) {
            indentingStringBuilder.applyIndentToString("parentTypes: ");
            indentingStringBuilder.applyIndentToString(unmodifiableList.toString());
            indentingStringBuilder.applyIndentToString("\n");
        }
        indentingStringBuilder.applyIndentToString("creationTimestampMillis: ");
        indentingStringBuilder.applyIndentToString(Long.valueOf(genericDocumentParcel.mCreationTimestampMillis).toString());
        indentingStringBuilder.applyIndentToString(",\n");
        indentingStringBuilder.applyIndentToString("timeToLiveMillis: ");
        indentingStringBuilder.applyIndentToString(Long.valueOf(genericDocumentParcel.mTtlMillis).toString());
        indentingStringBuilder.applyIndentToString(",\n");
        indentingStringBuilder.applyIndentToString("properties: {\n");
        String[] strArr = (String[]) Collections.unmodifiableSet(genericDocumentParcel.mPropertyMap.keySet()).toArray(new String[0]);
        Arrays.sort(strArr);
        for (int i = 0; i < strArr.length; i++) {
            Object property = getProperty(strArr[i]);
            property.getClass();
            indentingStringBuilder.increaseIndentLevel();
            String str = strArr[i];
            str.getClass();
            indentingStringBuilder.applyIndentToString("\"");
            indentingStringBuilder.applyIndentToString(str);
            indentingStringBuilder.applyIndentToString("\": [");
            if (property instanceof GenericDocument[]) {
                GenericDocument[] genericDocumentArr = (GenericDocument[]) property;
                for (int i2 = 0; i2 < genericDocumentArr.length; i2++) {
                    indentingStringBuilder.applyIndentToString("\n");
                    indentingStringBuilder.increaseIndentLevel();
                    genericDocumentArr[i2].appendGenericDocumentString(indentingStringBuilder);
                    if (i2 != genericDocumentArr.length - 1) {
                        indentingStringBuilder.applyIndentToString(",");
                    }
                    indentingStringBuilder.applyIndentToString("\n");
                    indentingStringBuilder.decreaseIndentLevel();
                }
            } else {
                int length = Array.getLength(property);
                for (int i3 = 0; i3 < length; i3++) {
                    Object obj = Array.get(property, i3);
                    if (obj instanceof String) {
                        indentingStringBuilder.applyIndentToString("\"");
                        indentingStringBuilder.applyIndentToString((String) obj);
                        indentingStringBuilder.applyIndentToString("\"");
                    } else if (obj instanceof byte[]) {
                        indentingStringBuilder.applyIndentToString(Arrays.toString((byte[]) obj));
                    } else if (obj != null) {
                        indentingStringBuilder.applyIndentToString(obj.toString());
                    }
                    if (i3 != length - 1) {
                        indentingStringBuilder.applyIndentToString(", ");
                    }
                }
            }
            indentingStringBuilder.applyIndentToString("]");
            if (i != strArr.length - 1) {
                indentingStringBuilder.applyIndentToString(",\n");
            }
            indentingStringBuilder.decreaseIndentLevel();
        }
        indentingStringBuilder.applyIndentToString("\n");
        indentingStringBuilder.applyIndentToString("}");
        indentingStringBuilder.decreaseIndentLevel();
        indentingStringBuilder.applyIndentToString("\n");
        indentingStringBuilder.applyIndentToString("}");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GenericDocument) {
            return this.mDocumentParcel.equals(((GenericDocument) obj).mDocumentParcel);
        }
        return false;
    }

    public final Object getProperty(String str) {
        Objects.requireNonNull(str);
        Object rawPropertyFromRawDocument = getRawPropertyFromRawDocument(new PropertyPath(str), 0, this.mDocumentParcel.mPropertyMap);
        if (rawPropertyFromRawDocument instanceof GenericDocumentParcel) {
            return new GenericDocument[]{new GenericDocument((GenericDocumentParcel) rawPropertyFromRawDocument)};
        }
        if (!(rawPropertyFromRawDocument instanceof GenericDocumentParcel[])) {
            return rawPropertyFromRawDocument;
        }
        GenericDocumentParcel[] genericDocumentParcelArr = (GenericDocumentParcel[]) rawPropertyFromRawDocument;
        GenericDocument[] genericDocumentArr = new GenericDocument[genericDocumentParcelArr.length];
        for (int i = 0; i < genericDocumentParcelArr.length; i++) {
            GenericDocumentParcel genericDocumentParcel = genericDocumentParcelArr[i];
            if (genericDocumentParcel == null) {
                Log.e("AppSearchGenericDocumen", "The inner parcel is null at " + i + ", for path: " + str);
            } else {
                genericDocumentArr[i] = new GenericDocument(genericDocumentParcel);
            }
        }
        return genericDocumentArr;
    }

    public final boolean getPropertyBoolean(String str) {
        boolean[] zArr = (boolean[]) safeCastProperty(str, getProperty(str), boolean[].class);
        if (zArr == null || zArr.length == 0) {
            return false;
        }
        warnIfSinglePropertyTooLong("Boolean", str, zArr.length);
        return zArr[0];
    }

    public final GenericDocument[] getPropertyDocumentArray(String str) {
        return (GenericDocument[]) safeCastProperty(str, getProperty(str), GenericDocument[].class);
    }

    public final long getPropertyLong(String str) {
        long[] jArr = (long[]) safeCastProperty(str, getProperty(str), long[].class);
        if (jArr == null || jArr.length == 0) {
            return 0L;
        }
        warnIfSinglePropertyTooLong("Long", str, jArr.length);
        return jArr[0];
    }

    public final String[] getPropertyStringArray(String str) {
        return (String[]) safeCastProperty(str, getProperty(str), String[].class);
    }

    public final int hashCode() {
        return this.mDocumentParcel.hashCode();
    }

    public final Object toDocumentClass(Class cls, Map map) {
        DocumentClassFactoryRegistry documentClassFactoryRegistry = DocumentClassFactoryRegistry.getInstance();
        if (map != null) {
            GenericDocumentParcel genericDocumentParcel = this.mDocumentParcel;
            Class assignableClassBySchemaName = AppSearchDocumentClassMap.getAssignableClassBySchemaName(map, genericDocumentParcel.mSchemaType, cls);
            if (assignableClassBySchemaName != null) {
                cls = assignableClassBySchemaName;
            } else {
                List list = genericDocumentParcel.mParentTypes;
                List unmodifiableList = list == null ? null : Collections.unmodifiableList(list);
                if (unmodifiableList != null) {
                    for (int i = 0; i < unmodifiableList.size(); i++) {
                        Class assignableClassBySchemaName2 = AppSearchDocumentClassMap.getAssignableClassBySchemaName(map, (String) unmodifiableList.get(i), cls);
                        if (assignableClassBySchemaName2 != null) {
                            cls = assignableClassBySchemaName2;
                            break;
                        }
                    }
                }
                Log.w("AppSearchGenericDocumen", "Cannot find any compatible target class to deserialize. Perhaps the annotation processor was not run or the generated document class map was proguarded out?\nTry to deserialize to " + cls.getCanonicalName() + " directly.");
            }
        }
        return documentClassFactoryRegistry.getOrCreateFactory(cls).fromGenericDocument(this, map);
    }

    public final String toString() {
        IndentingStringBuilder indentingStringBuilder = new IndentingStringBuilder();
        appendGenericDocumentString(indentingStringBuilder);
        return indentingStringBuilder.mStringBuilder.toString();
    }
}
