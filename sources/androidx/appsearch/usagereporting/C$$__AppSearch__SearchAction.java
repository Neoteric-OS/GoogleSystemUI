package androidx.appsearch.usagereporting;

import androidx.appsearch.app.AppSearchSchema;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.exceptions.AppSearchException;
import androidx.appsearch.exceptions.IllegalSchemaException;
import androidx.appsearch.safeparcel.GenericDocumentParcel;
import androidx.appsearch.safeparcel.PropertyConfigParcel;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* renamed from: androidx.appsearch.usagereporting.$$__AppSearch__SearchAction, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__SearchAction implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "builtin:SearchAction";

    public List getDependencyDocumentClasses() throws AppSearchException {
        return Collections.emptyList();
    }

    public AppSearchSchema getSchema() throws AppSearchException {
        AppSearchSchema.Builder builder = new AppSearchSchema.Builder(SCHEMA_NAME);
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel = new PropertyConfigParcel("actionType", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        ArraySet arraySet = builder.mPropertyNames;
        String str = propertyConfigParcel.mName;
        if (!arraySet.add(str)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel);
        AppSearchSchema.StringPropertyConfig.Builder builder2 = new AppSearchSchema.StringPropertyConfig.Builder("query");
        builder2.setCardinality(2);
        builder2.setTokenizerType(1);
        builder2.setIndexingType(2);
        builder2.setJoinableValueType(0);
        builder.addProperty(builder2.build());
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel2 = new PropertyConfigParcel("fetchedResultCount", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str2 = propertyConfigParcel2.mName;
        if (!arraySet.add(str2)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str2));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel2);
        return builder.build();
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public SearchAction fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
        GenericDocumentParcel genericDocumentParcel = genericDocument.mDocumentParcel;
        String str = genericDocumentParcel.mNamespace;
        String str2 = genericDocumentParcel.mId;
        long j = genericDocumentParcel.mTtlMillis;
        long j2 = genericDocumentParcel.mCreationTimestampMillis;
        int propertyLong = (int) genericDocument.getPropertyLong("actionType");
        String[] propertyStringArray = genericDocument.getPropertyStringArray("query");
        String str3 = (propertyStringArray == null || propertyStringArray.length == 0) ? null : propertyStringArray[0];
        int propertyLong2 = (int) genericDocument.getPropertyLong("fetchedResultCount");
        str.getClass();
        str2.getClass();
        return new SearchAction(str, str2, j, j2, propertyLong, str3, propertyLong2);
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(SearchAction searchAction) throws AppSearchException {
        GenericDocument.Builder builder = new GenericDocument.Builder(searchAction.mNamespace, searchAction.mId, SCHEMA_NAME);
        builder.setTtlMillis(searchAction.mDocumentTtlMillis);
        builder.mDocumentParcelBuilder.mCreationTimestampMillis = searchAction.mActionTimestampMillis;
        builder.setPropertyLong("actionType", searchAction.mActionType);
        String str = searchAction.mQuery;
        if (str != null) {
            builder.setPropertyString("query", str);
        }
        builder.setPropertyLong("fetchedResultCount", searchAction.mFetchedResultCount);
        return builder.build();
    }
}
