package androidx.slice;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.slice.compat.SliceProviderCompat$$ExternalSyntheticLambda0;
import androidx.versionedparcelable.VersionedParcelable;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class SliceItemHolder implements VersionedParcelable {
    public static final SliceProviderCompat$$ExternalSyntheticLambda0 sHandler = null;
    public static final Object sSerializeLock = null;
    public Bundle mBundle;
    public int mInt;
    public long mLong;
    public Parcelable mParcelable;
    public SliceItemPool mPool;
    public String mStr;
    public VersionedParcelable mVersionedParcelable;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SliceItemPool {
        public final ArrayList mCached = new ArrayList();
    }
}
