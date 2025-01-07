package androidx.appsearch.builtintypes.properties;

import androidx.appsearch.app.AppSearchSchema;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.exceptions.AppSearchException;
import androidx.appsearch.exceptions.IllegalSchemaException;
import androidx.appsearch.safeparcel.GenericDocumentParcel;
import androidx.appsearch.safeparcel.PropertyConfigParcel;
import androidx.collection.ArraySet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* renamed from: androidx.appsearch.builtintypes.properties.$$__AppSearch__Keyword, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__Keyword implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "Keyword";

    public List getDependencyDocumentClasses() throws AppSearchException {
        return Collections.emptyList();
    }

    public AppSearchSchema getSchema() throws AppSearchException {
        ArrayList arrayList = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArraySet arraySet = new ArraySet(0);
        AppSearchSchema.StringPropertyConfig.Builder builder = new AppSearchSchema.StringPropertyConfig.Builder("asText");
        builder.setCardinality(2);
        builder.setTokenizerType(1);
        builder.setIndexingType(2);
        builder.setJoinableValueType(0);
        PropertyConfigParcel propertyConfigParcel = builder.build().mPropertyConfigParcel;
        String str = propertyConfigParcel.mName;
        if (!arraySet.add(str)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str));
        }
        arrayList.add(propertyConfigParcel);
        return new AppSearchSchema(SCHEMA_NAME, arrayList, new ArrayList(linkedHashSet));
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public Keyword fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
        GenericDocumentParcel genericDocumentParcel = genericDocument.mDocumentParcel;
        String str = genericDocumentParcel.mNamespace;
        String str2 = genericDocumentParcel.mId;
        String[] propertyStringArray = genericDocument.getPropertyStringArray("asText");
        Keyword keyword = new Keyword((propertyStringArray == null || propertyStringArray.length == 0) ? null : propertyStringArray[0]);
        keyword.mNamespace = str;
        keyword.mId = str2;
        return keyword;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(Keyword keyword) throws AppSearchException {
        GenericDocument.Builder builder = new GenericDocument.Builder(keyword.mNamespace, keyword.mId, SCHEMA_NAME);
        String str = keyword.mAsText;
        if (str != null) {
            builder.setPropertyString("asText", str);
        }
        return builder.build();
    }
}
