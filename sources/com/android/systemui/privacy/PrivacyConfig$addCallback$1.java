package com.android.systemui.privacy;

import com.android.systemui.privacy.PrivacyConfig;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyConfig$addCallback$1 implements Runnable {
    public final /* synthetic */ WeakReference $callback;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PrivacyConfig this$0;

    public /* synthetic */ PrivacyConfig$addCallback$1(PrivacyConfig privacyConfig, WeakReference weakReference, int i) {
        this.$r8$classId = i;
        this.this$0 = privacyConfig;
        this.$callback = weakReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.callbacks.add(this.$callback);
                break;
            default:
                List list = this.this$0.callbacks;
                final WeakReference weakReference = this.$callback;
                ((ArrayList) list).removeIf(new Predicate() { // from class: com.android.systemui.privacy.PrivacyConfig$removeCallback$1$1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        PrivacyConfig.Callback callback = (PrivacyConfig.Callback) ((WeakReference) obj).get();
                        if (callback != null) {
                            return callback.equals(weakReference.get());
                        }
                        return true;
                    }
                });
                break;
        }
    }
}
