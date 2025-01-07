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
/* renamed from: androidx.appsearch.builtintypes.$$__AppSearch__Stopwatch, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__Stopwatch implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "builtin:Stopwatch";

    public List getDependencyDocumentClasses() throws AppSearchException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(PotentialAction.class);
        arrayList.add(StopwatchLap.class);
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
        PropertyConfigParcel propertyConfigParcel2 = new PropertyConfigParcel("baseTimeMillis", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        PropertyConfigParcel propertyConfigParcel3 = new PropertyConfigParcel("baseTimeMillisInElapsedRealtime", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        PropertyConfigParcel propertyConfigParcel4 = new PropertyConfigParcel("bootCount", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        PropertyConfigParcel propertyConfigParcel5 = new PropertyConfigParcel("status", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        PropertyConfigParcel propertyConfigParcel6 = new PropertyConfigParcel("accumulatedDurationMillis", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        ArraySet arraySet3 = new ArraySet(0);
        Preconditions.checkArgumentInRange("cardinality", 1, 1, 3);
        PropertyConfigParcel propertyConfigParcel7 = new PropertyConfigParcel("laps", 6, 1, C$$__AppSearch__StopwatchLap.SCHEMA_NAME, null, new PropertyConfigParcel.DocumentIndexingConfigParcel(new ArrayList(arraySet3), false), null, null);
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
        return builder.build();
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public Stopwatch fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
        String str;
        String str2;
        ArrayList arrayList;
        ArrayList arrayList2;
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
            str2 = str8;
            ArrayList arrayList3 = new ArrayList(propertyDocumentArray.length);
            str = str7;
            int i2 = 0;
            while (i2 < propertyDocumentArray.length) {
                arrayList3.add((PotentialAction) propertyDocumentArray[i2].toDocumentClass(PotentialAction.class, map));
                i2++;
                propertyDocumentArray = propertyDocumentArray;
            }
            arrayList = arrayList3;
        } else {
            str = str7;
            str2 = str8;
            arrayList = null;
        }
        long propertyLong = genericDocument.getPropertyLong("baseTimeMillis");
        long propertyLong2 = genericDocument.getPropertyLong("baseTimeMillisInElapsedRealtime");
        int propertyLong3 = (int) genericDocument.getPropertyLong("bootCount");
        int propertyLong4 = (int) genericDocument.getPropertyLong("status");
        long propertyLong5 = genericDocument.getPropertyLong("accumulatedDurationMillis");
        GenericDocument[] propertyDocumentArray2 = genericDocument.getPropertyDocumentArray("laps");
        if (propertyDocumentArray2 != null) {
            ArrayList arrayList4 = new ArrayList(propertyDocumentArray2.length);
            int i3 = 0;
            while (i3 < propertyDocumentArray2.length) {
                arrayList4.add((StopwatchLap) propertyDocumentArray2[i3].toDocumentClass(StopwatchLap.class, map));
                i3++;
                propertyDocumentArray2 = propertyDocumentArray2;
            }
            arrayList2 = arrayList4;
        } else {
            arrayList2 = null;
        }
        return new Stopwatch(str3, str4, i, j, j2, str5, asList, str6, str, str2, arrayList, propertyLong, propertyLong2, propertyLong3, propertyLong4, propertyLong5, arrayList2);
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(Stopwatch stopwatch) throws AppSearchException {
        int i = 0;
        GenericDocument.Builder builder = new GenericDocument.Builder(stopwatch.mNamespace, stopwatch.mId, SCHEMA_NAME);
        builder.setScore(stopwatch.mDocumentScore);
        builder.mDocumentParcelBuilder.mCreationTimestampMillis = stopwatch.mCreationTimestampMillis;
        builder.setTtlMillis(stopwatch.mDocumentTtlMillis);
        String str = stopwatch.mName;
        if (str != null) {
            builder.setPropertyString("name", str);
        }
        List list = stopwatch.mAlternateNames;
        if (list != null) {
            builder.setPropertyString("alternateNames", (String[]) list.toArray(new String[0]));
        }
        String str2 = stopwatch.mDescription;
        if (str2 != null) {
            builder.setPropertyString(WeatherData.DESCRIPTION_KEY, str2);
        }
        String str3 = stopwatch.mImage;
        if (str3 != null) {
            builder.setPropertyString("image", str3);
        }
        String str4 = stopwatch.mUrl;
        if (str4 != null) {
            builder.setPropertyString("url", str4);
        }
        List list2 = stopwatch.mPotentialActions;
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
        builder.setPropertyLong("baseTimeMillis", stopwatch.mBaseTimeMillis);
        builder.setPropertyLong("baseTimeMillisInElapsedRealtime", stopwatch.mBaseTimeMillisInElapsedRealtime);
        builder.setPropertyLong("bootCount", stopwatch.mBootCount);
        builder.setPropertyLong("status", stopwatch.mStatus);
        builder.setPropertyLong("accumulatedDurationMillis", stopwatch.mAccumulatedDurationMillis);
        List list3 = stopwatch.mLaps;
        if (list3 != null) {
            GenericDocument[] genericDocumentArr2 = new GenericDocument[list3.size()];
            Iterator it2 = list3.iterator();
            while (it2.hasNext()) {
                genericDocumentArr2[i] = GenericDocument.fromDocumentClass((StopwatchLap) it2.next());
                i++;
            }
            builder.setPropertyDocument("laps", genericDocumentArr2);
        }
        return builder.build();
    }
}
