package androidx.appsearch.builtintypes;

import androidx.appsearch.app.AppSearchSchema;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.builtintypes.AlarmInstance;
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
/* renamed from: androidx.appsearch.builtintypes.$$__AppSearch__AlarmInstance, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__AlarmInstance implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "builtin:AlarmInstance";

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
        AppSearchSchema.StringPropertyConfig.Builder builder3 = new AppSearchSchema.StringPropertyConfig.Builder("scheduledTime");
        builder3.setCardinality(2);
        builder3.setTokenizerType(0);
        builder3.setIndexingType(0);
        builder3.setJoinableValueType(0);
        builder.addProperty(builder3.build());
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel2 = new PropertyConfigParcel("status", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        PropertyConfigParcel propertyConfigParcel3 = new PropertyConfigParcel("snoozeDurationMillis", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        return builder.build();
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public AlarmInstance fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
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
        String[] propertyStringArray6 = genericDocument.getPropertyStringArray("scheduledTime");
        String str9 = (propertyStringArray6 == null || propertyStringArray6.length == 0) ? null : propertyStringArray6[0];
        int propertyLong = (int) genericDocument.getPropertyLong("status");
        long propertyLong2 = genericDocument.getPropertyLong("snoozeDurationMillis");
        AlarmInstance.Builder builder = new AlarmInstance.Builder(str3, str4, str9);
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
        return new AlarmInstance(builder.mNamespace, builder.mId, builder.mDocumentScore, builder.mCreationTimestampMillis, builder.mDocumentTtlMillis, builder.mName, builder.mAlternateNames, builder.mDescription, builder.mImage, builder.mUrl, builder.mPotentialActions, builder.mScheduledTime, propertyLong, propertyLong2);
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(AlarmInstance alarmInstance) throws AppSearchException {
        GenericDocument.Builder builder = new GenericDocument.Builder(alarmInstance.mNamespace, alarmInstance.mId, SCHEMA_NAME);
        builder.setScore(alarmInstance.mDocumentScore);
        builder.mDocumentParcelBuilder.mCreationTimestampMillis = alarmInstance.mCreationTimestampMillis;
        builder.setTtlMillis(alarmInstance.mDocumentTtlMillis);
        String str = alarmInstance.mName;
        if (str != null) {
            builder.setPropertyString("name", str);
        }
        List list = alarmInstance.mAlternateNames;
        if (list != null) {
            builder.setPropertyString("alternateNames", (String[]) list.toArray(new String[0]));
        }
        String str2 = alarmInstance.mDescription;
        if (str2 != null) {
            builder.setPropertyString(WeatherData.DESCRIPTION_KEY, str2);
        }
        String str3 = alarmInstance.mImage;
        if (str3 != null) {
            builder.setPropertyString("image", str3);
        }
        String str4 = alarmInstance.mUrl;
        if (str4 != null) {
            builder.setPropertyString("url", str4);
        }
        List list2 = alarmInstance.mPotentialActions;
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
        String str5 = alarmInstance.mScheduledTime;
        if (str5 != null) {
            builder.setPropertyString("scheduledTime", str5);
        }
        builder.setPropertyLong("status", alarmInstance.mStatus);
        builder.setPropertyLong("snoozeDurationMillis", alarmInstance.mSnoozeDurationMillis);
        return builder.build();
    }
}
