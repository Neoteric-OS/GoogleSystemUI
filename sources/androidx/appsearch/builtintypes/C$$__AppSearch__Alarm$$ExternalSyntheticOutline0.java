package androidx.appsearch.builtintypes;

import androidx.appsearch.app.AppSearchSchema;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* renamed from: androidx.appsearch.builtintypes.$$__AppSearch__Alarm$$ExternalSyntheticOutline0, reason: invalid class name */
/* loaded from: classes.dex */
public abstract /* synthetic */ class C$$__AppSearch__Alarm$$ExternalSyntheticOutline0 {
    public static AppSearchSchema.StringPropertyConfig.Builder m(AppSearchSchema.Builder builder, AppSearchSchema.StringPropertyConfig stringPropertyConfig, String str, int i, int i2) {
        builder.addProperty(stringPropertyConfig);
        AppSearchSchema.StringPropertyConfig.Builder builder2 = new AppSearchSchema.StringPropertyConfig.Builder(str);
        builder2.setCardinality(i);
        builder2.setTokenizerType(i2);
        return builder2;
    }

    public static AppSearchSchema.StringPropertyConfig.Builder m(AppSearchSchema.StringPropertyConfig.Builder builder, int i, int i2, AppSearchSchema.Builder builder2, String str) {
        builder.setIndexingType(i);
        builder.setJoinableValueType(i2);
        builder2.addProperty(builder.build());
        return new AppSearchSchema.StringPropertyConfig.Builder(str);
    }

    public static AppSearchSchema.StringPropertyConfig m(AppSearchSchema.StringPropertyConfig.Builder builder, int i, int i2, int i3, int i4) {
        builder.setCardinality(i);
        builder.setTokenizerType(i2);
        builder.setIndexingType(i3);
        builder.setJoinableValueType(i4);
        return builder.build();
    }
}
