package androidx.appsearch.builtintypes;

import androidx.appsearch.app.AppSearchSchema;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactory;
import androidx.appsearch.app.GenericDocument;
import androidx.appsearch.builtintypes.Alarm;
import androidx.appsearch.exceptions.AppSearchException;
import androidx.appsearch.exceptions.IllegalSchemaException;
import androidx.appsearch.safeparcel.GenericDocumentParcel;
import androidx.appsearch.safeparcel.PropertyConfigParcel;
import androidx.appsearch.utils.DateTimeFormatValidator;
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
/* renamed from: androidx.appsearch.builtintypes.$$__AppSearch__Alarm, reason: invalid class name */
/* loaded from: classes.dex */
public final class C$$__AppSearch__Alarm implements DocumentClassFactory {
    public static final String SCHEMA_NAME = "builtin:Alarm";

    public List getDependencyDocumentClasses() throws AppSearchException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(PotentialAction.class);
        arrayList.add(AlarmInstance.class);
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
        PropertyConfigParcel propertyConfigParcel2 = new PropertyConfigParcel("enabled", 4, 2, null, null, null, null, null);
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
        Preconditions.checkArgumentInRange("cardinality", 1, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel3 = new PropertyConfigParcel("daysOfWeek", 2, 1, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        PropertyConfigParcel propertyConfigParcel4 = new PropertyConfigParcel("hour", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        PropertyConfigParcel propertyConfigParcel5 = new PropertyConfigParcel("minute", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        AppSearchSchema.StringPropertyConfig.Builder builder3 = new AppSearchSchema.StringPropertyConfig.Builder("blackoutPeriodStartDate");
        builder3.setCardinality(2);
        builder3.setTokenizerType(0);
        AppSearchSchema.StringPropertyConfig.Builder m = C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder, C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(C$$__AppSearch__Alarm$$ExternalSyntheticOutline0.m(builder3, 0, 0, builder, "blackoutPeriodEndDate"), 2, 0, 0, 0), "ringtone", 2, 0);
        m.setIndexingType(0);
        m.setJoinableValueType(0);
        builder.addProperty(m.build());
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        PropertyConfigParcel propertyConfigParcel6 = new PropertyConfigParcel("shouldVibrate", 4, 2, null, null, null, null, null);
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
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        PropertyConfigParcel propertyConfigParcel7 = new PropertyConfigParcel("previousInstance", 6, 2, C$$__AppSearch__AlarmInstance.SCHEMA_NAME, null, new PropertyConfigParcel.DocumentIndexingConfigParcel(new ArrayList(arraySet3), false), null, null);
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
        ArraySet arraySet4 = new ArraySet(0);
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        PropertyConfigParcel propertyConfigParcel8 = new PropertyConfigParcel("nextInstance", 6, 2, C$$__AppSearch__AlarmInstance.SCHEMA_NAME, null, new PropertyConfigParcel.DocumentIndexingConfigParcel(new ArrayList(arraySet4), false), null, null);
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
        Preconditions.checkArgumentInRange("cardinality", 2, 1, 3);
        Preconditions.checkArgumentInRange("indexingType", 0, 0, 1);
        PropertyConfigParcel propertyConfigParcel9 = new PropertyConfigParcel("computingDevice", 2, 2, null, null, null, new PropertyConfigParcel.IntegerIndexingConfigParcel(), null);
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
        return builder.build();
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public Alarm fromGenericDocument(GenericDocument genericDocument, Map map) throws AppSearchException {
        String str;
        String str2;
        ArrayList arrayList;
        ArrayList arrayList2;
        List list;
        String str3;
        int[] iArr;
        GenericDocument genericDocument2;
        GenericDocument genericDocument3;
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
            str = str9;
            arrayList = new ArrayList(propertyDocumentArray.length);
            str2 = str8;
            int i2 = 0;
            while (i2 < propertyDocumentArray.length) {
                arrayList.add((PotentialAction) propertyDocumentArray[i2].toDocumentClass(PotentialAction.class, map));
                i2++;
                propertyDocumentArray = propertyDocumentArray;
            }
        } else {
            str = str9;
            str2 = str8;
            arrayList = null;
        }
        boolean propertyBoolean = genericDocument.getPropertyBoolean("enabled");
        long[] jArr = (long[]) GenericDocument.safeCastProperty("daysOfWeek", genericDocument.getProperty("daysOfWeek"), long[].class);
        if (jArr != null) {
            iArr = new int[jArr.length];
            arrayList2 = arrayList;
            str3 = "daysOfWeek";
            int i3 = 0;
            while (true) {
                list = asList;
                if (i3 >= jArr.length) {
                    break;
                }
                iArr[i3] = (int) jArr[i3];
                i3++;
                asList = list;
            }
        } else {
            arrayList2 = arrayList;
            list = asList;
            str3 = "daysOfWeek";
            iArr = null;
        }
        int propertyLong = (int) genericDocument.getPropertyLong("hour");
        int propertyLong2 = (int) genericDocument.getPropertyLong("minute");
        String[] propertyStringArray6 = genericDocument.getPropertyStringArray("blackoutPeriodStartDate");
        String str10 = (propertyStringArray6 == null || propertyStringArray6.length == 0) ? null : propertyStringArray6[0];
        String[] propertyStringArray7 = genericDocument.getPropertyStringArray("blackoutPeriodEndDate");
        String str11 = str10;
        String str12 = (propertyStringArray7 == null || propertyStringArray7.length == 0) ? null : propertyStringArray7[0];
        String[] propertyStringArray8 = genericDocument.getPropertyStringArray("ringtone");
        String str13 = str12;
        String str14 = (propertyStringArray8 == null || propertyStringArray8.length == 0) ? null : propertyStringArray8[0];
        boolean propertyBoolean2 = genericDocument.getPropertyBoolean("shouldVibrate");
        GenericDocument[] propertyDocumentArray2 = genericDocument.getPropertyDocumentArray("previousInstance");
        if (propertyDocumentArray2 == null || propertyDocumentArray2.length == 0) {
            genericDocument2 = null;
        } else {
            GenericDocument.warnIfSinglePropertyTooLong("Document", "previousInstance", propertyDocumentArray2.length);
            genericDocument2 = propertyDocumentArray2[0];
        }
        AlarmInstance alarmInstance = genericDocument2 != null ? (AlarmInstance) genericDocument2.toDocumentClass(AlarmInstance.class, map) : null;
        GenericDocument[] propertyDocumentArray3 = genericDocument.getPropertyDocumentArray("nextInstance");
        int[] iArr2 = iArr;
        if (propertyDocumentArray3 == null || propertyDocumentArray3.length == 0) {
            genericDocument3 = null;
        } else {
            GenericDocument.warnIfSinglePropertyTooLong("Document", "nextInstance", propertyDocumentArray3.length);
            genericDocument3 = propertyDocumentArray3[0];
        }
        AlarmInstance alarmInstance2 = genericDocument3 != null ? (AlarmInstance) genericDocument3.toDocumentClass(AlarmInstance.class, map) : null;
        int propertyLong3 = (int) genericDocument.getPropertyLong("computingDevice");
        Alarm.Builder builder = new Alarm.Builder(str4, str5);
        builder.resetIfBuilt();
        builder.mDocumentScore = i;
        builder.resetIfBuilt();
        builder.mCreationTimestampMillis = j;
        builder.resetIfBuilt();
        builder.mDocumentTtlMillis = j2;
        builder.resetIfBuilt();
        builder.mName = str6;
        builder.setAlternateNames(list);
        builder.resetIfBuilt();
        builder.mDescription = str7;
        builder.resetIfBuilt();
        builder.mImage = str2;
        builder.resetIfBuilt();
        builder.mUrl = str;
        builder.setPotentialActions(arrayList2);
        builder.mEnabled = propertyBoolean;
        if (iArr2 != null) {
            for (int i4 : iArr2) {
                Preconditions.checkArgumentInRange(str3, i4, 1, 7);
            }
        }
        builder.mDaysOfWeek = iArr2;
        Preconditions.checkArgumentInRange("hour", propertyLong, 0, 23);
        builder.mHour = propertyLong;
        Preconditions.checkArgumentInRange("minute", propertyLong2, 0, 59);
        builder.mMinute = propertyLong2;
        if (str11 != null) {
            Preconditions.checkArgument("blackoutPeriodStartDate must be in the format: yyyy-MM-dd", DateTimeFormatValidator.validateDateFormat("yyyy-MM-dd", str11));
        }
        builder.mBlackoutPeriodStartDate = str11;
        if (str13 != null) {
            Preconditions.checkArgument("blackoutPeriodEndDate must be in the format: yyyy-MM-dd", DateTimeFormatValidator.validateDateFormat("yyyy-MM-dd", str13));
        }
        return new Alarm(builder.mNamespace, builder.mId, builder.mDocumentScore, builder.mCreationTimestampMillis, builder.mDocumentTtlMillis, builder.mName, builder.mAlternateNames, builder.mDescription, builder.mImage, builder.mUrl, builder.mPotentialActions, builder.mEnabled, builder.mDaysOfWeek, builder.mHour, builder.mMinute, builder.mBlackoutPeriodStartDate, str13, str14, propertyBoolean2, alarmInstance, alarmInstance2, propertyLong3);
    }

    @Override // androidx.appsearch.app.DocumentClassFactory
    public GenericDocument toGenericDocument(Alarm alarm) throws AppSearchException {
        GenericDocument.Builder builder = new GenericDocument.Builder(alarm.mNamespace, alarm.mId, SCHEMA_NAME);
        builder.setScore(alarm.mDocumentScore);
        builder.mDocumentParcelBuilder.mCreationTimestampMillis = alarm.mCreationTimestampMillis;
        builder.setTtlMillis(alarm.mDocumentTtlMillis);
        String str = alarm.mName;
        if (str != null) {
            builder.setPropertyString("name", str);
        }
        List list = alarm.mAlternateNames;
        if (list != null) {
            builder.setPropertyString("alternateNames", (String[]) list.toArray(new String[0]));
        }
        String str2 = alarm.mDescription;
        if (str2 != null) {
            builder.setPropertyString(WeatherData.DESCRIPTION_KEY, str2);
        }
        String str3 = alarm.mImage;
        if (str3 != null) {
            builder.setPropertyString("image", str3);
        }
        String str4 = alarm.mUrl;
        if (str4 != null) {
            builder.setPropertyString("url", str4);
        }
        List list2 = alarm.mPotentialActions;
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
        builder.setPropertyBoolean("enabled", alarm.mEnabled);
        int[] iArr = alarm.mDaysOfWeek;
        if (iArr != null) {
            long[] jArr = new long[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                jArr[i2] = iArr[i2];
            }
            builder.setPropertyLong("daysOfWeek", jArr);
        }
        builder.setPropertyLong("hour", alarm.mHour);
        builder.setPropertyLong("minute", alarm.mMinute);
        String str5 = alarm.mBlackoutPeriodStartDate;
        if (str5 != null) {
            builder.setPropertyString("blackoutPeriodStartDate", str5);
        }
        String str6 = alarm.mBlackoutPeriodEndDate;
        if (str6 != null) {
            builder.setPropertyString("blackoutPeriodEndDate", str6);
        }
        String str7 = alarm.mRingtone;
        if (str7 != null) {
            builder.setPropertyString("ringtone", str7);
        }
        builder.setPropertyBoolean("shouldVibrate", alarm.mShouldVibrate);
        AlarmInstance alarmInstance = alarm.mPreviousInstance;
        if (alarmInstance != null) {
            builder.setPropertyDocument("previousInstance", GenericDocument.fromDocumentClass(alarmInstance));
        }
        AlarmInstance alarmInstance2 = alarm.mNextInstance;
        if (alarmInstance2 != null) {
            builder.setPropertyDocument("nextInstance", GenericDocument.fromDocumentClass(alarmInstance2));
        }
        builder.setPropertyLong("computingDevice", alarm.mComputingDevice);
        return builder.build();
    }
}
