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
/* renamed from: androidx.appsearch.builtintypes.$$__AppSearch__Timer, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__Timer implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "builtin:Timer";

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
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel2 = new PropertyConfigParcel("durationMillis", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel3 = new PropertyConfigParcel("originalDurationMillis", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel4 = new PropertyConfigParcel("startTimeMillis", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel5 = new PropertyConfigParcel("baseTimeMillis", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel6 = new PropertyConfigParcel("baseTimeMillisInElapsedRealtime", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str6 = propertyConfigParcel6.mName;
        if (!arraySet2.add(str6)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str6));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel6);
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel7 = new PropertyConfigParcel("bootCount", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str7 = propertyConfigParcel7.mName;
        if (!arraySet2.add(str7)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str7));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel7);
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel8 = new PropertyConfigParcel("remainingDurationMillis", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str8 = propertyConfigParcel8.mName;
        if (!arraySet2.add(str8)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str8));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel8);
        AppSearchSchema.StringPropertyConfig.Builder builder3 = new AppSearchSchema.StringPropertyConfig.Builder("ringtone");
        builder3.setCardinality(2);
        builder3.setTokenizerType(0);
        builder3.setIndexingType(0);
        builder3.setJoinableValueType(0);
        builder.addProperty(builder3.build());
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel9 = new PropertyConfigParcel("status", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str9 = propertyConfigParcel9.mName;
        if (!arraySet2.add(str9)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str9));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel9);
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        PropertyConfigParcel propertyConfigParcel10 = new PropertyConfigParcel("shouldVibrate", 4, 2, null, null, null, null, null);
        if (builder.mBuilt) {
            builder.mPropertyConfigParcels = new ArrayList(builder.mPropertyConfigParcels);
            builder.mParentTypes = new LinkedHashSet(builder.mParentTypes);
            builder.mBuilt = false;
        }
        String str10 = propertyConfigParcel10.mName;
        if (!arraySet2.add(str10)) {
            throw new IllegalSchemaException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Property defined more than once: ", str10));
        }
        builder.mPropertyConfigParcels.add(propertyConfigParcel10);
        return builder.build();
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public Timer fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
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
            ArrayList arrayList2 = new ArrayList(propertyDocumentArray.length);
            str2 = str8;
            int i2 = 0;
            while (i2 < propertyDocumentArray.length) {
                arrayList2.add((PotentialAction) propertyDocumentArray[i2].toDocumentClass(PotentialAction.class, map));
                i2++;
                propertyDocumentArray = propertyDocumentArray;
                str7 = str7;
            }
            str = str7;
            arrayList = arrayList2;
        } else {
            str = str7;
            str2 = str8;
            arrayList = null;
        }
        long propertyLong = genericDocument.getPropertyLong("durationMillis");
        long propertyLong2 = genericDocument.getPropertyLong("originalDurationMillis");
        long propertyLong3 = genericDocument.getPropertyLong("startTimeMillis");
        long propertyLong4 = genericDocument.getPropertyLong("baseTimeMillis");
        long propertyLong5 = genericDocument.getPropertyLong("baseTimeMillisInElapsedRealtime");
        int propertyLong6 = (int) genericDocument.getPropertyLong("bootCount");
        long propertyLong7 = genericDocument.getPropertyLong("remainingDurationMillis");
        String[] propertyStringArray6 = genericDocument.getPropertyStringArray("ringtone");
        return new Timer(str3, str4, i, j, j2, str5, asList, str6, str, str2, arrayList, propertyLong, propertyLong2, propertyLong3, propertyLong4, propertyLong5, propertyLong6, propertyLong7, (propertyStringArray6 == null || propertyStringArray6.length == 0) ? null : propertyStringArray6[0], (int) genericDocument.getPropertyLong("status"), genericDocument.getPropertyBoolean("shouldVibrate"));
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(Timer timer) throws AppSearchException {
        GenericDocument.Builder builder = new GenericDocument.Builder(timer.mNamespace, timer.mId, SCHEMA_NAME);
        builder.setScore(timer.mDocumentScore);
        builder.mDocumentParcelBuilder.mCreationTimestampMillis = timer.mCreationTimestampMillis;
        builder.setTtlMillis(timer.mDocumentTtlMillis);
        String str = timer.mName;
        if (str != null) {
            builder.setPropertyString("name", str);
        }
        List list = timer.mAlternateNames;
        if (list != null) {
            builder.setPropertyString("alternateNames", (String[]) list.toArray(new String[0]));
        }
        String str2 = timer.mDescription;
        if (str2 != null) {
            builder.setPropertyString(WeatherData.DESCRIPTION_KEY, str2);
        }
        String str3 = timer.mImage;
        if (str3 != null) {
            builder.setPropertyString("image", str3);
        }
        String str4 = timer.mUrl;
        if (str4 != null) {
            builder.setPropertyString("url", str4);
        }
        List list2 = timer.mPotentialActions;
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
        builder.setPropertyLong("durationMillis", timer.mDurationMillis);
        builder.setPropertyLong("originalDurationMillis", timer.mOriginalDurationMillis);
        builder.setPropertyLong("startTimeMillis", timer.mStartTimeMillis);
        builder.setPropertyLong("baseTimeMillis", timer.mBaseTimeMillis);
        builder.setPropertyLong("baseTimeMillisInElapsedRealtime", timer.mBaseTimeMillisInElapsedRealtime);
        builder.setPropertyLong("bootCount", timer.mBootCount);
        builder.setPropertyLong("remainingDurationMillis", timer.mRemainingDurationMillis);
        String str5 = timer.mRingtone;
        if (str5 != null) {
            builder.setPropertyString("ringtone", str5);
        }
        builder.setPropertyLong("status", timer.mStatus);
        builder.setPropertyBoolean("shouldVibrate", timer.mShouldVibrate);
        return builder.build();
    }
}
