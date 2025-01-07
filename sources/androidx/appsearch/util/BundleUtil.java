package androidx.appsearch.util;

import android.os.Bundle;
import android.os.Parcel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BundleUtil {
    public static Bundle deepCopy(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeBundle(bundle);
            byte[] marshall = obtain.marshall();
            obtain.unmarshall(marshall, 0, marshall.length);
            obtain.setDataPosition(0);
            return obtain.readBundle(BundleUtil.class.getClassLoader());
        } finally {
            obtain.recycle();
        }
    }
}
