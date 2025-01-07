package androidx.appsearch.app;

import androidx.appsearch.app.AppSearchSchema;
import java.util.Comparator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class AppSearchSchema$$ExternalSyntheticLambda0 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((AppSearchSchema.PropertyConfig) obj).mPropertyConfigParcel.mName.compareTo(((AppSearchSchema.PropertyConfig) obj2).mPropertyConfigParcel.mName);
    }
}
