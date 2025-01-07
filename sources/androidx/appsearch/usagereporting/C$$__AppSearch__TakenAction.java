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
/* renamed from: androidx.appsearch.usagereporting.$$__AppSearch__TakenAction, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__TakenAction implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "builtin:TakenAction";

    public List getDependencyDocumentClasses() throws AppSearchException {
        return Collections.emptyList();
    }

    public AppSearchSchema getSchema() throws AppSearchException {
        ArrayList arrayList = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArraySet arraySet = new ArraySet(0);
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel = new PropertyConfigParcel("actionType", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
        if (!arraySet.add("actionType")) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", "actionType"));
        }
        arrayList.add(propertyConfigParcel);
        return new AppSearchSchema(SCHEMA_NAME, arrayList, new ArrayList(linkedHashSet));
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public TakenAction fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
        GenericDocumentParcel genericDocumentParcel = genericDocument.mDocumentParcel;
        String str = genericDocumentParcel.mNamespace;
        String str2 = genericDocumentParcel.mId;
        long j = genericDocumentParcel.mTtlMillis;
        long j2 = genericDocumentParcel.mCreationTimestampMillis;
        genericDocument.getPropertyLong("actionType");
        str.getClass();
        str2.getClass();
        throw new UnsupportedOperationException();
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(TakenAction takenAction) throws AppSearchException {
        GenericDocument.Builder builder = new GenericDocument.Builder(takenAction.mNamespace, takenAction.mId, SCHEMA_NAME);
        builder.setTtlMillis(takenAction.mDocumentTtlMillis);
        builder.mDocumentParcelBuilder.mCreationTimestampMillis = takenAction.mActionTimestampMillis;
        builder.setPropertyLong("actionType", takenAction.mActionType);
        return builder.build();
    }
}
