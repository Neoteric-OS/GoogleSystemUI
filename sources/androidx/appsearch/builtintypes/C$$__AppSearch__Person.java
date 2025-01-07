package androidx.appsearch.builtintypes;

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
import com.android.systemui.plugins.clocks.WeatherData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* renamed from: androidx.appsearch.builtintypes.$$__AppSearch__Person, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__Person implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "builtin:Person";

    public List getDependencyDocumentClasses() throws AppSearchException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(PotentialAction.class);
        arrayList.add(ContactPoint.class);
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
        AppSearchSchema.StringPropertyConfig.Builder builder3 = new AppSearchSchema.StringPropertyConfig.Builder("givenName");
        builder3.setCardinality(2);
        builder3.setTokenizerType(0);
        AppSearchSchema.StringPropertyConfig.Builder m = C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder, C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder, C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder3, 0, 0, builder, "middleName"), 2, 0, 0, 0), "familyName", 2, 0), 0, 0, builder, "externalUri"), 2, 0, 0, 0), "imageUri", 2, 0);
        m.setIndexingType(0);
        m.setJoinableValueType(0);
        builder.addProperty(m.build());
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        PropertyConfigParcel propertyConfigParcel2 = new PropertyConfigParcel("isImportant", 4, 2, null, null, null, null, null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str2 = propertyConfigParcel2.mName;
        if (!arraySet2.add(str2)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str2));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel2);
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        PropertyConfigParcel propertyConfigParcel3 = new PropertyConfigParcel("isBot", 4, 2, null, null, null, null, null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str3 = propertyConfigParcel3.mName;
        if (!arraySet2.add(str3)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str3));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel3);
        AppSearchSchema.StringPropertyConfig.Builder builder4 = new AppSearchSchema.StringPropertyConfig.Builder("notes");
        builder4.setCardinality(1);
        builder4.setTokenizerType(1);
        builder4.setIndexingType(2);
        builder4.setJoinableValueType(0);
        builder.addProperty(builder4.build());
        Preconditions.checkArgumentInRange("cardinality", 1, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel4 = new PropertyConfigParcel("additionalNameTypes", 2, 1, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str4 = propertyConfigParcel4.mName;
        if (!arraySet2.add(str4)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str4));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel4);
        AppSearchSchema.StringPropertyConfig.Builder builder5 = new AppSearchSchema.StringPropertyConfig.Builder("additionalNames");
        builder5.setCardinality(1);
        builder5.setTokenizerType(1);
        AppSearchSchema.StringPropertyConfig.Builder m2 = C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder, C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder5, 2, 0, builder, "affiliations"), 1, 1, 2, 0), "relations", 1, 0);
        m2.setIndexingType(0);
        m2.setJoinableValueType(0);
        builder.addProperty(m2.build());
        ArraySet arraySet3 = new ArraySet(0);
        Preconditions.checkArgumentInRange("cardinality", 1, 1, 3);
        if (!arraySet3.isEmpty()) {
            throw new IllegalArgumentException("DocumentIndexingConfig#shouldIndexNestedProperties is required to be false when one or more indexableNestedProperties are provided.");
        }
        PropertyConfigParcel propertyConfigParcel5 = new PropertyConfigParcel("contactPoints", 6, 1, C$$__AppSearch__ContactPoint.SCHEMA_NAME, null, new PropertyConfigParcel.DocumentIndexingConfigParcel(new ArrayList(arraySet3), true), null, null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str5 = propertyConfigParcel5.mName;
        if (!arraySet2.add(str5)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str5));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel5);
        return builder.build();
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public Person fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
        String str;
        String str2;
        ArrayList arrayList;
        int i;
        String str3;
        ArrayList arrayList2;
        ArrayList arrayList3;
        GenericDocumentParcel genericDocumentParcel = genericDocument.mDocumentParcel;
        String str4 = genericDocumentParcel.mNamespace;
        String str5 = genericDocumentParcel.mId;
        int i2 = genericDocumentParcel.mScore;
        long j = genericDocumentParcel.mCreationTimestampMillis;
        long j2 = genericDocumentParcel.mTtlMillis;
        String[] propertyStringArray = genericDocument.getPropertyStringArray("name");
        String str6 = (propertyStringArray == null || propertyStringArray.length == 0) ? null : propertyStringArray[0];
        String[] propertyStringArray2 = genericDocument.getPropertyStringArray("alternateNames");
        List asList = propertyStringArray2 != null ? Arrays.asList(propertyStringArray2) : null;
        String[] propertyStringArray3 = genericDocument.getPropertyStringArray(WeatherData.DESCRIPTION_KEY);
        String str7 = (propertyStringArray3 == null || propertyStringArray3.length == 0) ? null : propertyStringArray3[0];
        String[] propertyStringArray4 = genericDocument.getPropertyStringArray("image");
        String str8 = (propertyStringArray4 == null || propertyStringArray4.length == 0) ? null : propertyStringArray4[0];
        String[] propertyStringArray5 = genericDocument.getPropertyStringArray("url");
        String str9 = (propertyStringArray5 == null || propertyStringArray5.length == 0) ? null : propertyStringArray5[0];
        GenericDocument[] propertyDocumentArray = genericDocument.getPropertyDocumentArray("potentialActions");
        if (propertyDocumentArray != null) {
            str2 = str9;
            ArrayList arrayList4 = new ArrayList(propertyDocumentArray.length);
            str = str8;
            int i3 = 0;
            while (i3 < propertyDocumentArray.length) {
                arrayList4.add((PotentialAction) propertyDocumentArray[i3].toDocumentClass(PotentialAction.class, map));
                i3++;
                propertyDocumentArray = propertyDocumentArray;
            }
            arrayList = arrayList4;
        } else {
            str = str8;
            str2 = str9;
            arrayList = null;
        }
        String[] propertyStringArray6 = genericDocument.getPropertyStringArray("givenName");
        if (propertyStringArray6 == null || propertyStringArray6.length == 0) {
            i = 0;
            str3 = null;
        } else {
            i = 0;
            str3 = propertyStringArray6[0];
        }
        String[] propertyStringArray7 = genericDocument.getPropertyStringArray("middleName");
        String str10 = (propertyStringArray7 == null || propertyStringArray7.length == 0) ? null : propertyStringArray7[i];
        String[] propertyStringArray8 = genericDocument.getPropertyStringArray("familyName");
        String str11 = (propertyStringArray8 == null || propertyStringArray8.length == 0) ? null : propertyStringArray8[i];
        String[] propertyStringArray9 = genericDocument.getPropertyStringArray("externalUri");
        String str12 = (propertyStringArray9 == null || propertyStringArray9.length == 0) ? null : propertyStringArray9[i];
        String[] propertyStringArray10 = genericDocument.getPropertyStringArray("imageUri");
        String str13 = (propertyStringArray10 == null || propertyStringArray10.length == 0) ? null : propertyStringArray10[i];
        boolean propertyBoolean = genericDocument.getPropertyBoolean("isImportant");
        boolean propertyBoolean2 = genericDocument.getPropertyBoolean("isBot");
        String[] propertyStringArray11 = genericDocument.getPropertyStringArray("notes");
        List asList2 = propertyStringArray11 != null ? Arrays.asList(propertyStringArray11) : null;
        long[] jArr = (long[]) GenericDocument.safeCastProperty("additionalNameTypes", genericDocument.getProperty("additionalNameTypes"), long[].class);
        if (jArr != null) {
            ArrayList arrayList5 = new ArrayList(jArr.length);
            for (int i4 = i; i4 < jArr.length; i4++) {
                arrayList5.add(Long.valueOf(jArr[i4]));
            }
            arrayList2 = arrayList5;
        } else {
            arrayList2 = null;
        }
        String[] propertyStringArray12 = genericDocument.getPropertyStringArray("additionalNames");
        List asList3 = propertyStringArray12 != null ? Arrays.asList(propertyStringArray12) : null;
        String[] propertyStringArray13 = genericDocument.getPropertyStringArray("affiliations");
        List asList4 = propertyStringArray13 != null ? Arrays.asList(propertyStringArray13) : null;
        String[] propertyStringArray14 = genericDocument.getPropertyStringArray("relations");
        List asList5 = propertyStringArray14 != null ? Arrays.asList(propertyStringArray14) : null;
        GenericDocument[] propertyDocumentArray2 = genericDocument.getPropertyDocumentArray("contactPoints");
        if (propertyDocumentArray2 != null) {
            ArrayList arrayList6 = new ArrayList(propertyDocumentArray2.length);
            for (GenericDocument genericDocument2 : propertyDocumentArray2) {
                arrayList6.add((ContactPoint) genericDocument2.toDocumentClass(ContactPoint.class, map));
            }
            arrayList3 = arrayList6;
        } else {
            arrayList3 = null;
        }
        return new Person(str4, str5, i2, j, j2, str6, asList, str7, str, str2, arrayList, str3, str10, str11, str12, str13, propertyBoolean, propertyBoolean2, asList2, arrayList2, asList3, asList4, asList5, arrayList3);
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(Person person) throws AppSearchException {
        int i = 0;
        GenericDocument.Builder builder = new GenericDocument.Builder(person.mNamespace, person.mId, SCHEMA_NAME);
        builder.setScore(person.mDocumentScore);
        builder.mDocumentParcelBuilder.mCreationTimestampMillis = person.mCreationTimestampMillis;
        builder.setTtlMillis(person.mDocumentTtlMillis);
        String str = person.mName;
        if (str != null) {
            builder.setPropertyString("name", str);
        }
        List list = person.mAlternateNames;
        if (list != null) {
            builder.setPropertyString("alternateNames", (String[]) list.toArray(new String[0]));
        }
        String str2 = person.mDescription;
        if (str2 != null) {
            builder.setPropertyString(WeatherData.DESCRIPTION_KEY, str2);
        }
        String str3 = person.mImage;
        if (str3 != null) {
            builder.setPropertyString("image", str3);
        }
        String str4 = person.mUrl;
        if (str4 != null) {
            builder.setPropertyString("url", str4);
        }
        List list2 = person.mPotentialActions;
        if (list2 != null) {
            GenericDocument[] genericDocumentArr = new GenericDocument[list2.size()];
            Iterator it = list2.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                genericDocumentArr[i2] = GenericDocument.fromDocumentClass((PotentialAction) it.next());
                i2++;
            }
            builder.setPropertyDocument("potentialActions", genericDocumentArr);
        }
        String str5 = person.mGivenName;
        if (str5 != null) {
            builder.setPropertyString("givenName", str5);
        }
        String str6 = person.mMiddleName;
        if (str6 != null) {
            builder.setPropertyString("middleName", str6);
        }
        String str7 = person.mFamilyName;
        if (str7 != null) {
            builder.setPropertyString("familyName", str7);
        }
        String str8 = person.mExternalUri;
        if (str8 != null) {
            builder.setPropertyString("externalUri", str8);
        }
        String str9 = person.mImageUri;
        if (str9 != null) {
            builder.setPropertyString("imageUri", str9);
        }
        builder.setPropertyBoolean("isImportant", person.mIsImportant);
        builder.setPropertyBoolean("isBot", person.mIsBot);
        List list3 = person.mNotes;
        if (list3 != null) {
            builder.setPropertyString("notes", (String[]) list3.toArray(new String[0]));
        }
        List list4 = person.mAdditionalNameTypes;
        if (list4 != null) {
            long[] jArr = new long[list4.size()];
            Iterator it2 = list4.iterator();
            int i3 = 0;
            while (it2.hasNext()) {
                jArr[i3] = ((Long) it2.next()).longValue();
                i3++;
            }
            builder.setPropertyLong("additionalNameTypes", jArr);
        }
        List list5 = person.mAdditionalNames;
        if (list5 != null) {
            builder.setPropertyString("additionalNames", (String[]) list5.toArray(new String[0]));
        }
        List list6 = person.mAffiliations;
        if (list6 != null) {
            builder.setPropertyString("affiliations", (String[]) list6.toArray(new String[0]));
        }
        List list7 = person.mRelations;
        if (list7 != null) {
            builder.setPropertyString("relations", (String[]) list7.toArray(new String[0]));
        }
        List list8 = person.mContactPoints;
        if (list8 != null) {
            GenericDocument[] genericDocumentArr2 = new GenericDocument[list8.size()];
            Iterator it3 = list8.iterator();
            while (it3.hasNext()) {
                genericDocumentArr2[i] = GenericDocument.fromDocumentClass((ContactPoint) it3.next());
                i++;
            }
            builder.setPropertyDocument("contactPoints", genericDocumentArr2);
        }
        return builder.build();
    }
}
