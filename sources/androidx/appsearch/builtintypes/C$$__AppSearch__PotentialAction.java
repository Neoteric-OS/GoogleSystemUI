package androidx.appsearch.builtintypes;

import androidx.appsearch.app.AppSearchSchema;
import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.exceptions.AppSearchException;
import androidx.appsearch.safeparcel.GenericDocumentParcel;
import com.android.systemui.plugins.clocks.WeatherData;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* renamed from: androidx.appsearch.builtintypes.$$__AppSearch__PotentialAction, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__PotentialAction implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "builtin:PotentialAction";

    public List getDependencyDocumentClasses() throws AppSearchException {
        return Collections.emptyList();
    }

    public AppSearchSchema getSchema() throws AppSearchException {
        AppSearchSchema.Builder builder = new AppSearchSchema.Builder(SCHEMA_NAME);
        AppSearchSchema.StringPropertyConfig.Builder builder2 = new AppSearchSchema.StringPropertyConfig.Builder("name");
        builder2.setCardinality(2);
        builder2.setTokenizerType(0);
        builder2.setIndexingType(0);
        builder2.setJoinableValueType(0);
        builder.addProperty(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder, builder2.build(), WeatherData.DESCRIPTION_KEY, 2, 0), 0, 0, builder, "uri"), 2, 0, 0, 0));
        return builder.build();
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public PotentialAction fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
        GenericDocumentParcel genericDocumentParcel = genericDocument.mDocumentParcel;
        String str = genericDocumentParcel.mNamespace;
        String str2 = genericDocumentParcel.mId;
        String[] propertyStringArray = genericDocument.getPropertyStringArray("name");
        String str3 = (propertyStringArray == null || propertyStringArray.length == 0) ? null : propertyStringArray[0];
        String[] propertyStringArray2 = genericDocument.getPropertyStringArray(WeatherData.DESCRIPTION_KEY);
        String str4 = (propertyStringArray2 == null || propertyStringArray2.length == 0) ? null : propertyStringArray2[0];
        String[] propertyStringArray3 = genericDocument.getPropertyStringArray("uri");
        return new PotentialAction(str, str2, str3, str4, (propertyStringArray3 == null || propertyStringArray3.length == 0) ? null : propertyStringArray3[0]);
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(PotentialAction potentialAction) throws AppSearchException {
        GenericDocument.Builder builder = new GenericDocument.Builder(potentialAction.mNamespace, potentialAction.mId, SCHEMA_NAME);
        String str = potentialAction.mName;
        if (str != null) {
            builder.setPropertyString("name", str);
        }
        String str2 = potentialAction.mDescription;
        if (str2 != null) {
            builder.setPropertyString(WeatherData.DESCRIPTION_KEY, str2);
        }
        String str3 = potentialAction.mUri;
        if (str3 != null) {
            builder.setPropertyString("uri", str3);
        }
        return builder.build();
    }
}
