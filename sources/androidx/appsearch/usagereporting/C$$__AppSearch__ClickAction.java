package androidx.appsearch.usagereporting;

import androidx.appsearch.app.AppSearchSchema;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.builtintypes.C$$__AppSearch__Alarm$$ExternalSyntheticOutline0;
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
/* renamed from: androidx.appsearch.usagereporting.$$__AppSearch__ClickAction, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__ClickAction implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "builtin:ClickAction";

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
        builder.addProperty(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder2, 2, 0, builder, "referencedQualifiedId"), 2, 0, 0, 1));
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel2 = new PropertyConfigParcel("resultRankInBlock", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel3 = new PropertyConfigParcel("resultRankGlobal", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str3 = propertyConfigParcel3.mName;
        if (!arraySet.add(str3)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str3));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel3);
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel4 = new PropertyConfigParcel("timeStayOnResultMillis", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str4 = propertyConfigParcel4.mName;
        if (!arraySet.add(str4)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str4));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel4);
        return builder.build();
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public ClickAction fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
        GenericDocumentParcel genericDocumentParcel = genericDocument.mDocumentParcel;
        String str = genericDocumentParcel.mNamespace;
        String str2 = genericDocumentParcel.mId;
        long j = genericDocumentParcel.mTtlMillis;
        long j2 = genericDocumentParcel.mCreationTimestampMillis;
        int propertyLong = (int) genericDocument.getPropertyLong("actionType");
        String[] propertyStringArray = genericDocument.getPropertyStringArray("query");
        String str3 = (propertyStringArray == null || propertyStringArray.length == 0) ? null : propertyStringArray[0];
        String[] propertyStringArray2 = genericDocument.getPropertyStringArray("referencedQualifiedId");
        String str4 = (propertyStringArray2 == null || propertyStringArray2.length == 0) ? null : propertyStringArray2[0];
        int propertyLong2 = (int) genericDocument.getPropertyLong("resultRankInBlock");
        int propertyLong3 = (int) genericDocument.getPropertyLong("resultRankGlobal");
        long propertyLong4 = genericDocument.getPropertyLong("timeStayOnResultMillis");
        str.getClass();
        str2.getClass();
        return new ClickAction(str, str2, j, j2, propertyLong, str3, str4, propertyLong2, propertyLong3, propertyLong4);
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(ClickAction clickAction) throws AppSearchException {
        GenericDocument.Builder builder = new GenericDocument.Builder(clickAction.mNamespace, clickAction.mId, SCHEMA_NAME);
        builder.setTtlMillis(clickAction.mDocumentTtlMillis);
        builder.mDocumentParcelBuilder.mCreationTimestampMillis = clickAction.mActionTimestampMillis;
        builder.setPropertyLong("actionType", clickAction.mActionType);
        String str = clickAction.mQuery;
        if (str != null) {
            builder.setPropertyString("query", str);
        }
        String str2 = clickAction.mReferencedQualifiedId;
        if (str2 != null) {
            builder.setPropertyString("referencedQualifiedId", str2);
        }
        builder.setPropertyLong("resultRankInBlock", clickAction.mResultRankInBlock);
        builder.setPropertyLong("resultRankGlobal", clickAction.mResultRankGlobal);
        builder.setPropertyLong("timeStayOnResultMillis", clickAction.mTimeStayOnResultMillis);
        return builder.build();
    }
}
