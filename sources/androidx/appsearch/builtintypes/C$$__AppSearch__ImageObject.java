package androidx.appsearch.builtintypes;

import androidx.appsearch.app.AppSearchSchema;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.builtintypes.properties.C$$__AppSearch__Keyword;
import androidx.appsearch.builtintypes.properties.Keyword;
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
/* renamed from: androidx.appsearch.builtintypes.$$__AppSearch__ImageObject, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__ImageObject implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "builtin:ImageObject";

    public List getDependencyDocumentClasses() throws AppSearchException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(PotentialAction.class);
        arrayList.add(Keyword.class);
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
        ArraySet arraySet3 = new ArraySet(0);
        Preconditions.checkArgumentInRange("cardinality", 1, 1, 3);
        if (!arraySet3.isEmpty()) {
            throw new IllegalArgumentException("DocumentIndexingConfig#shouldIndexNestedProperties is required to be false when one or more indexableNestedProperties are provided.");
        }
        PropertyConfigParcel propertyConfigParcel2 = new PropertyConfigParcel("keywords", 6, 1, C$$__AppSearch__Keyword.SCHEMA_NAME, null, new PropertyConfigParcel.DocumentIndexingConfigParcel(new ArrayList(arraySet3), true), null, null);
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
        AppSearchSchema.StringPropertyConfig.Builder builder3 = new AppSearchSchema.StringPropertyConfig.Builder("sha256");
        builder3.setCardinality(2);
        builder3.setTokenizerType(0);
        builder.addProperty(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder3, 0, 0, builder, "thumbnailSha256"), 2, 0, 0, 0));
        return builder.build();
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public ImageObject fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
        String str;
        String str2;
        ArrayList arrayList;
        ArrayList arrayList2;
        char c;
        String str3;
        GenericDocumentParcel genericDocumentParcel = genericDocument.mDocumentParcel;
        String str4 = genericDocumentParcel.mNamespace;
        String str5 = genericDocumentParcel.mId;
        int i = genericDocumentParcel.mScore;
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
            ArrayList arrayList3 = new ArrayList(propertyDocumentArray.length);
            str = str8;
            int i2 = 0;
            while (i2 < propertyDocumentArray.length) {
                arrayList3.add((PotentialAction) propertyDocumentArray[i2].toDocumentClass(PotentialAction.class, map));
                i2++;
                propertyDocumentArray = propertyDocumentArray;
            }
            arrayList = arrayList3;
        } else {
            str = str8;
            str2 = str9;
            arrayList = null;
        }
        GenericDocument[] propertyDocumentArray2 = genericDocument.getPropertyDocumentArray("keywords");
        if (propertyDocumentArray2 != null) {
            ArrayList arrayList4 = new ArrayList(propertyDocumentArray2.length);
            int i3 = 0;
            while (i3 < propertyDocumentArray2.length) {
                arrayList4.add((Keyword) propertyDocumentArray2[i3].toDocumentClass(Keyword.class, map));
                i3++;
                propertyDocumentArray2 = propertyDocumentArray2;
            }
            arrayList2 = arrayList4;
        } else {
            arrayList2 = null;
        }
        String[] propertyStringArray6 = genericDocument.getPropertyStringArray("sha256");
        if (propertyStringArray6 == null || propertyStringArray6.length == 0) {
            c = 0;
            str3 = null;
        } else {
            c = 0;
            str3 = propertyStringArray6[0];
        }
        String[] propertyStringArray7 = genericDocument.getPropertyStringArray("thumbnailSha256");
        return new ImageObject(str4, str5, i, j, j2, str6, asList, str7, str, str2, arrayList, arrayList2, str3, (propertyStringArray7 == null || propertyStringArray7.length == 0) ? null : propertyStringArray7[c]);
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(ImageObject imageObject) throws AppSearchException {
        GenericDocument.Builder builder = new GenericDocument.Builder(imageObject.mNamespace, imageObject.mId, SCHEMA_NAME);
        builder.setScore(imageObject.mDocumentScore);
        builder.mDocumentParcelBuilder.mCreationTimestampMillis = imageObject.mCreationTimestampMillis;
        builder.setTtlMillis(imageObject.mDocumentTtlMillis);
        String str = imageObject.mName;
        if (str != null) {
            builder.setPropertyString("name", str);
        }
        List list = imageObject.mAlternateNames;
        int i = 0;
        if (list != null) {
            builder.setPropertyString("alternateNames", (String[]) list.toArray(new String[0]));
        }
        String str2 = imageObject.mDescription;
        if (str2 != null) {
            builder.setPropertyString(WeatherData.DESCRIPTION_KEY, str2);
        }
        String str3 = imageObject.mImage;
        if (str3 != null) {
            builder.setPropertyString("image", str3);
        }
        String str4 = imageObject.mUrl;
        if (str4 != null) {
            builder.setPropertyString("url", str4);
        }
        List list2 = imageObject.mPotentialActions;
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
        List list3 = imageObject.mKeywords;
        if (list3 != null) {
            GenericDocument[] genericDocumentArr2 = new GenericDocument[list3.size()];
            Iterator it2 = list3.iterator();
            while (it2.hasNext()) {
                genericDocumentArr2[i] = GenericDocument.fromDocumentClass((Keyword) it2.next());
                i++;
            }
            builder.setPropertyDocument("keywords", genericDocumentArr2);
        }
        String str5 = imageObject.mSha256;
        if (str5 != null) {
            builder.setPropertyString("sha256", str5);
        }
        String str6 = imageObject.mThumbnailSha256;
        if (str6 != null) {
            builder.setPropertyString("thumbnailSha256", str6);
        }
        return builder.build();
    }
}
