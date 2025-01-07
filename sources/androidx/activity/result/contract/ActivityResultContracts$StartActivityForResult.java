package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.util.ObjectsCompat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.MapsKt__MapsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ActivityResultContracts$StartActivityForResult extends ActivityResultContract {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ ActivityResultContracts$StartActivityForResult(int i) {
        this.$r8$classId = i;
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public final Intent createIntent(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return (Intent) obj;
            default:
                return new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", (String[]) obj);
        }
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public ActivityResultContract.SynchronousResult getSynchronousResult(Context context, Object obj) {
        switch (this.$r8$classId) {
            case 1:
                String[] strArr = (String[]) obj;
                if (strArr.length == 0) {
                    return new ActivityResultContract.SynchronousResult(MapsKt.emptyMap());
                }
                for (String str : strArr) {
                    ObjectsCompat.requireNonNull(str, "permission must be non-null");
                    if (context.checkPermission(str, Process.myPid(), Process.myUid()) != 0) {
                        return null;
                    }
                }
                int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(strArr.length);
                if (mapCapacity < 16) {
                    mapCapacity = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
                for (String str2 : strArr) {
                    Pair pair = new Pair(str2, Boolean.TRUE);
                    linkedHashMap.put(pair.getFirst(), pair.getSecond());
                }
                return new ActivityResultContract.SynchronousResult(linkedHashMap);
            default:
                return super.getSynchronousResult(context, obj);
        }
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public final Object parseResult(Intent intent, int i) {
        switch (this.$r8$classId) {
            case 0:
                return new ActivityResult(intent, i);
            default:
                if (i != -1) {
                    return MapsKt.emptyMap();
                }
                if (intent == null) {
                    return MapsKt.emptyMap();
                }
                String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
                if (intArrayExtra == null || stringArrayExtra == null) {
                    return MapsKt.emptyMap();
                }
                ArrayList arrayList = new ArrayList(intArrayExtra.length);
                for (int i2 : intArrayExtra) {
                    arrayList.add(Boolean.valueOf(i2 == 0));
                }
                return MapsKt.toMap(CollectionsKt.zip(ArraysKt.filterNotNull(stringArrayExtra), arrayList));
        }
    }
}
