package androidx.activity.result;

import androidx.activity.ComponentActivity$activityResultRegistry$1;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ActivityResultRegistry$register$2 extends ActivityResultLauncher {
    public final /* synthetic */ ActivityResultContract $contract;
    public final /* synthetic */ String $key;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ActivityResultRegistry this$0;

    public /* synthetic */ ActivityResultRegistry$register$2(ActivityResultRegistry activityResultRegistry, String str, ActivityResultContract activityResultContract, int i) {
        this.$r8$classId = i;
        this.this$0 = activityResultRegistry;
        this.$key = str;
        this.$contract = activityResultContract;
    }

    @Override // androidx.activity.result.ActivityResultLauncher
    public final void launch(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                ComponentActivity$activityResultRegistry$1 componentActivity$activityResultRegistry$1 = (ComponentActivity$activityResultRegistry$1) this.this$0;
                Map map = componentActivity$activityResultRegistry$1.keyToRc;
                String str = this.$key;
                Object obj2 = map.get(str);
                ActivityResultContracts$StartActivityForResult activityResultContracts$StartActivityForResult = (ActivityResultContracts$StartActivityForResult) this.$contract;
                if (obj2 == null) {
                    throw new IllegalStateException(("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContracts$StartActivityForResult + " and input " + obj + ". You must ensure the ActivityResultLauncher is registered before calling launch().").toString());
                }
                int intValue = ((Number) obj2).intValue();
                componentActivity$activityResultRegistry$1.launchedKeys.add(str);
                try {
                    componentActivity$activityResultRegistry$1.onLaunch(intValue, activityResultContracts$StartActivityForResult, obj);
                    return;
                } catch (Exception e) {
                    componentActivity$activityResultRegistry$1.launchedKeys.remove(str);
                    throw e;
                }
            default:
                ActivityResultRegistry activityResultRegistry = this.this$0;
                Map map2 = activityResultRegistry.keyToRc;
                String str2 = this.$key;
                Object obj3 = map2.get(str2);
                ActivityResultContract activityResultContract = this.$contract;
                if (obj3 == null) {
                    throw new IllegalStateException(("Attempting to launch an unregistered ActivityResultLauncher with contract " + activityResultContract + " and input " + obj + ". You must ensure the ActivityResultLauncher is registered before calling launch().").toString());
                }
                int intValue2 = ((Number) obj3).intValue();
                activityResultRegistry.launchedKeys.add(str2);
                try {
                    activityResultRegistry.onLaunch(intValue2, activityResultContract, obj);
                    return;
                } catch (Exception e2) {
                    activityResultRegistry.launchedKeys.remove(str2);
                    throw e2;
                }
        }
    }
}
