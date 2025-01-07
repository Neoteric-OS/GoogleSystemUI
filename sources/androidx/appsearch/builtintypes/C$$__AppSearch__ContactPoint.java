package androidx.appsearch.builtintypes;

import androidx.appsearch.app.AppSearchSchema;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.builtintypes.ContactPoint;
import androidx.appsearch.exceptions.AppSearchException;
import androidx.appsearch.exceptions.IllegalSchemaException;
import androidx.appsearch.safeparcel.GenericDocumentParcel;
import androidx.appsearch.safeparcel.PropertyConfigParcel;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import com.android.systemui.plugins.clocks.WeatherData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* renamed from: androidx.appsearch.builtintypes.$$__AppSearch__ContactPoint, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__ContactPoint implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "builtin:ContactPoint";

    public List getDependencyDocumentClasses() throws AppSearchException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(PotentialAction.class);
        return arrayList;
    }

    public AppSearchSchema getSchema() throws AppSearchException {
        AppSearchSchema.Builder builder = new AppSearchSchema.Builder(SCHEMA_NAME);
        AppSearchSchema.StringPropertyConfig.Builder builder2 = new AppSearchSchema.StringPropertyConfig.Builder("name");
        builder2.setCardinality(2);
        builder2.setTokenizerType(1);
        builder2.setIndexingType(2);
        builder2.setJoinableValueType(0);
        builder.addProperty(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder, C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder, builder2.build(), "alternateNames", 1, 0), 0, 0, builder, WeatherData.DESCRIPTION_KEY), 2, 0, 0, 0), "image", 2, 0), 0, 0, builder, "url"), 2, 0, 0, 0));
        ArraySet arraySet = new ArraySet(0);
        Preconditions.checkArgumentInRange("cardinality", 1, 1, 3);
        PropertyConfigParcel propertyConfigParcel = new PropertyConfigParcel("potentialActions", 6, 1, C$$__AppSearch__PotentialAction.SCHEMA_NAME, null, new PropertyConfigParcel.DocumentIndexingConfigParcel(new ArrayList(arraySet), false), null, null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        ArraySet arraySet2 = builder.mPropertyNames;
        String str = propertyConfigParcel.mName;
        if (!arraySet2.add(str)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel);
        AppSearchSchema.StringPropertyConfig.Builder builder3 = new AppSearchSchema.StringPropertyConfig.Builder("label");
        builder3.setCardinality(2);
        builder3.setTokenizerType(1);
        builder.addProperty(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder, C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder3, 2, 0, builder, "address"), 1, 1, 2, 0), "email", 1, 1), 2, 0, builder, "telephone"), 1, 1, 2, 0));
        return builder.build();
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public ContactPoint fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
        String str;
        String str2;
        ArrayList arrayList;
        GenericDocumentParcel genericDocumentParcel = genericDocument.mDocumentParcel;
        String str3 = genericDocumentParcel.mNamespace;
        String str4 = genericDocumentParcel.mId;
        int i = genericDocumentParcel.mScore;
        long j = genericDocumentParcel.mCreationTimestampMillis;
        long j2 = genericDocumentParcel.mTtlMillis;
        String[] propertyStringArray = genericDocument.getPropertyStringArray("name");
        String str5 = (propertyStringArray == null || propertyStringArray.length == 0) ? null : propertyStringArray[0];
        String[] propertyStringArray2 = genericDocument.getPropertyStringArray("alternateNames");
        List asList = propertyStringArray2 != null ? Arrays.asList(propertyStringArray2) : null;
        String[] propertyStringArray3 = genericDocument.getPropertyStringArray(WeatherData.DESCRIPTION_KEY);
        String str6 = (propertyStringArray3 == null || propertyStringArray3.length == 0) ? null : propertyStringArray3[0];
        String[] propertyStringArray4 = genericDocument.getPropertyStringArray("image");
        String str7 = (propertyStringArray4 == null || propertyStringArray4.length == 0) ? null : propertyStringArray4[0];
        String[] propertyStringArray5 = genericDocument.getPropertyStringArray("url");
        String str8 = (propertyStringArray5 == null || propertyStringArray5.length == 0) ? null : propertyStringArray5[0];
        GenericDocument[] propertyDocumentArray = genericDocument.getPropertyDocumentArray("potentialActions");
        if (propertyDocumentArray != null) {
            arrayList = new ArrayList(propertyDocumentArray.length);
            str2 = str8;
            int i2 = 0;
            while (i2 < propertyDocumentArray.length) {
                arrayList.add((PotentialAction) propertyDocumentArray[i2].toDocumentClass(PotentialAction.class, map));
                i2++;
                propertyDocumentArray = propertyDocumentArray;
                str7 = str7;
            }
            str = str7;
        } else {
            str = str7;
            str2 = str8;
            arrayList = null;
        }
        String[] propertyStringArray6 = genericDocument.getPropertyStringArray("label");
        String str9 = (propertyStringArray6 == null || propertyStringArray6.length == 0) ? null : propertyStringArray6[0];
        String[] propertyStringArray7 = genericDocument.getPropertyStringArray("address");
        List asList2 = propertyStringArray7 != null ? Arrays.asList(propertyStringArray7) : null;
        String[] propertyStringArray8 = genericDocument.getPropertyStringArray("email");
        List asList3 = propertyStringArray8 != null ? Arrays.asList(propertyStringArray8) : null;
        String[] propertyStringArray9 = genericDocument.getPropertyStringArray("telephone");
        List asList4 = propertyStringArray9 != null ? Arrays.asList(propertyStringArray9) : null;
        ContactPoint.Builder builder = new ContactPoint.Builder(str3, str4);
        builder.mAddresses = Collections.emptyList();
        builder.mEmails = Collections.emptyList();
        builder.mTelephones = Collections.emptyList();
        str9.getClass();
        builder.resetIfBuilt();
        builder.mDocumentScore = i;
        builder.resetIfBuilt();
        builder.mCreationTimestampMillis = j;
        builder.resetIfBuilt();
        builder.mDocumentTtlMillis = j2;
        builder.resetIfBuilt();
        builder.mName = str5;
        builder.setAlternateNames(asList);
        builder.resetIfBuilt();
        builder.mDescription = str6;
        builder.resetIfBuilt();
        builder.mImage = str;
        builder.resetIfBuilt();
        builder.mUrl = str2;
        builder.setPotentialActions(arrayList);
        asList2.getClass();
        builder.mAddresses = asList2;
        asList3.getClass();
        builder.mEmails = asList3;
        asList4.getClass();
        builder.mTelephones = asList4;
        return new ContactPoint(builder.mNamespace, builder.mId, builder.mDocumentScore, builder.mCreationTimestampMillis, builder.mDocumentTtlMillis, builder.mName, builder.mAlternateNames, builder.mDescription, builder.mImage, builder.mUrl, builder.mPotentialActions, str9, new ArrayList(builder.mAddresses), new ArrayList(builder.mEmails), new ArrayList(builder.mTelephones));
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(ContactPoint contactPoint) throws AppSearchException {
        GenericDocument.Builder builder = new GenericDocument.Builder(contactPoint.mNamespace, contactPoint.mId, SCHEMA_NAME);
        builder.setScore(contactPoint.mDocumentScore);
        builder.mDocumentParcelBuilder.mCreationTimestampMillis = contactPoint.mCreationTimestampMillis;
        builder.setTtlMillis(contactPoint.mDocumentTtlMillis);
        String str = contactPoint.mName;
        if (str != null) {
            builder.setPropertyString("name", str);
        }
        List list = contactPoint.mAlternateNames;
        if (list != null) {
            builder.setPropertyString("alternateNames", (String[]) list.toArray(new String[0]));
        }
        String str2 = contactPoint.mDescription;
        if (str2 != null) {
            builder.setPropertyString(WeatherData.DESCRIPTION_KEY, str2);
        }
        String str3 = contactPoint.mImage;
        if (str3 != null) {
            builder.setPropertyString("image", str3);
        }
        String str4 = contactPoint.mUrl;
        if (str4 != null) {
            builder.setPropertyString("url", str4);
        }
        List list2 = contactPoint.mPotentialActions;
        if (list2 != null) {
            GenericDocument[] genericDocumentArr = new GenericDocument[list2.size()];
            Iterator it = list2.iterator();
            int i = 0;
            while (it.hasNext()) {
                genericDocumentArr[i] = GenericDocument.fromDocumentClass((PotentialAction) it.next());
                i++;
            }
            builder.setPropertyDocument("potentialActions", genericDocumentArr);
        }
        String str5 = contactPoint.mLabel;
        if (str5 != null) {
            builder.setPropertyString("label", str5);
        }
        List list3 = contactPoint.mAddresses;
        if (list3 != null) {
            builder.setPropertyString("address", (String[]) list3.toArray(new String[0]));
        }
        List list4 = contactPoint.mEmails;
        if (list4 != null) {
            builder.setPropertyString("email", (String[]) list4.toArray(new String[0]));
        }
        List list5 = contactPoint.mTelephones;
        if (list5 != null) {
            builder.setPropertyString("telephone", (String[]) list5.toArray(new String[0]));
        }
        return builder.build();
    }
}
