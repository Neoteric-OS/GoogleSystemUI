package androidx.appsearch.platformstorage.converter;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.appsearch.safeparcel.GenericDocumentParcel;
import androidx.appsearch.safeparcel.PropertyParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class GenericDocumentToPlatformConverter {
    public static GenericDocument toJetpackGenericDocument(android.app.appsearch.GenericDocument genericDocument) {
        genericDocument.getClass();
        GenericDocument.Builder builder = new GenericDocument.Builder(genericDocument.getNamespace(), genericDocument.getId(), genericDocument.getSchemaType());
        builder.setScore(genericDocument.getScore()).setTtlMillis(genericDocument.getTtlMillis()).mDocumentParcelBuilder.mCreationTimestampMillis = genericDocument.getCreationTimestampMillis();
        for (String str : genericDocument.getPropertyNames()) {
            Object property = genericDocument.getProperty(str);
            boolean equals = str.equals("$$__AppSearch__parentTypes");
            GenericDocumentParcel.Builder builder2 = builder.mDocumentParcelBuilder;
            if (equals) {
                if (!(property instanceof String[])) {
                    throw new IllegalStateException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Parents list must be of String[] type, but got ", property.getClass().toString()));
                }
                List asList = Arrays.asList((String[]) property);
                asList.getClass();
                builder2.getClass();
                builder2.mParentTypes = new ArrayList(asList);
            } else if (property instanceof String[]) {
                builder.setPropertyString(str, (String[]) property);
            } else if (property instanceof long[]) {
                builder.setPropertyLong(str, (long[]) property);
            } else if (property instanceof double[]) {
                double[] dArr = (double[]) property;
                dArr.getClass();
                GenericDocument.Builder.validatePropertyName(str);
                builder2.getClass();
                builder2.mPropertyMap.put(str, new PropertyParcel(str, null, null, dArr, null, null, null, null));
            } else if (property instanceof boolean[]) {
                builder.setPropertyBoolean(str, (boolean[]) property);
            } else {
                int i = 0;
                if (property instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) property;
                    bArr.getClass();
                    GenericDocument.Builder.validatePropertyName(str);
                    while (i < bArr.length) {
                        if (bArr[i] == null) {
                            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("The byte[] at ", " is null.", i));
                        }
                        i++;
                    }
                    builder2.getClass();
                    builder2.mPropertyMap.put(str, new PropertyParcel(str, null, null, null, null, bArr, null, null));
                } else {
                    if (!(property instanceof android.app.appsearch.GenericDocument[])) {
                        throw new IllegalStateException(GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Property \"", str, "\" has unsupported value type ", property.getClass().toString()));
                    }
                    android.app.appsearch.GenericDocument[] genericDocumentArr = (android.app.appsearch.GenericDocument[]) property;
                    GenericDocument[] genericDocumentArr2 = new GenericDocument[genericDocumentArr.length];
                    while (i < genericDocumentArr.length) {
                        genericDocumentArr2[i] = toJetpackGenericDocument(genericDocumentArr[i]);
                        i++;
                    }
                    builder.setPropertyDocument(str, genericDocumentArr2);
                }
            }
        }
        return builder.build();
    }
}
