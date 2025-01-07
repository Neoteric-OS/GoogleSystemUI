package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function0;
import kotlin.random.Random;
import kotlin.sequences.ConstrainedOnceSequence;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ActivityResultRegistry {
    public final Map rcToKey = new LinkedHashMap();
    public final Map keyToRc = new LinkedHashMap();
    public final Map keyToLifecycleContainers = new LinkedHashMap();
    public final List launchedKeys = new ArrayList();
    public final transient Map keyToCallback = new LinkedHashMap();
    public final Map parsedPendingResults = new LinkedHashMap();
    public final Bundle pendingResults = new Bundle();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CallbackAndContract {
        public final ActivityResultCallback callback;
        public final ActivityResultContract contract;

        public CallbackAndContract(ActivityResultCallback activityResultCallback, ActivityResultContract activityResultContract) {
            this.callback = activityResultCallback;
            this.contract = activityResultContract;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LifecycleContainer {
        public final Lifecycle lifecycle;
        public final List observers = new ArrayList();

        public LifecycleContainer(Lifecycle lifecycle) {
            this.lifecycle = lifecycle;
        }
    }

    public final boolean dispatchResult(int i, int i2, Intent intent) {
        String str = (String) this.rcToKey.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        CallbackAndContract callbackAndContract = (CallbackAndContract) this.keyToCallback.get(str);
        if ((callbackAndContract != null ? callbackAndContract.callback : null) == null || !this.launchedKeys.contains(str)) {
            this.parsedPendingResults.remove(str);
            this.pendingResults.putParcelable(str, new ActivityResult(intent, i2));
            return true;
        }
        callbackAndContract.callback.onActivityResult(callbackAndContract.contract.parseResult(intent, i2));
        this.launchedKeys.remove(str);
        return true;
    }

    public abstract void onLaunch(int i, ActivityResultContract activityResultContract, Object obj);

    public final ActivityResultRegistry$register$2 register(String str, ActivityResultContract activityResultContract, ActivityResultCallback activityResultCallback) {
        registerKey(str);
        this.keyToCallback.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
        if (this.parsedPendingResults.containsKey(str)) {
            Object obj = this.parsedPendingResults.get(str);
            this.parsedPendingResults.remove(str);
            activityResultCallback.onActivityResult(obj);
        }
        ActivityResult activityResult = (ActivityResult) this.pendingResults.getParcelable(str, ActivityResult.class);
        if (activityResult != null) {
            this.pendingResults.remove(str);
            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.data, activityResult.resultCode));
        }
        return new ActivityResultRegistry$register$2(this, str, activityResultContract, 1);
    }

    public final void registerKey(String str) {
        if (((Integer) this.keyToRc.get(str)) != null) {
            return;
        }
        Iterator it = ((ConstrainedOnceSequence) SequencesKt.generateSequence(new Function0() { // from class: androidx.activity.result.ActivityResultRegistry$generateRandomNumber$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Random.Default.getClass();
                return Integer.valueOf(Random.defaultRandom.getImpl().nextInt(2147418112) + 65536);
            }
        })).iterator();
        while (it.hasNext()) {
            Number number = (Number) it.next();
            if (!this.rcToKey.containsKey(Integer.valueOf(number.intValue()))) {
                int intValue = number.intValue();
                this.rcToKey.put(Integer.valueOf(intValue), str);
                this.keyToRc.put(str, Integer.valueOf(intValue));
                return;
            }
        }
        throw new NoSuchElementException("Sequence contains no element matching the predicate.");
    }

    public final void unregister$activity_release(String str) {
        Integer num;
        if (!this.launchedKeys.contains(str) && (num = (Integer) this.keyToRc.remove(str)) != null) {
            this.rcToKey.remove(num);
        }
        this.keyToCallback.remove(str);
        if (this.parsedPendingResults.containsKey(str)) {
            StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("Dropping pending result for request ", str, ": ");
            m.append(this.parsedPendingResults.get(str));
            Log.w("ActivityResultRegistry", m.toString());
            this.parsedPendingResults.remove(str);
        }
        if (this.pendingResults.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + ((ActivityResult) this.pendingResults.getParcelable(str, ActivityResult.class)));
            this.pendingResults.remove(str);
        }
        LifecycleContainer lifecycleContainer = (LifecycleContainer) this.keyToLifecycleContainers.get(str);
        if (lifecycleContainer != null) {
            Iterator it = lifecycleContainer.observers.iterator();
            while (it.hasNext()) {
                lifecycleContainer.lifecycle.removeObserver((LifecycleEventObserver) it.next());
            }
            lifecycleContainer.observers.clear();
            this.keyToLifecycleContainers.remove(str);
        }
    }
}
