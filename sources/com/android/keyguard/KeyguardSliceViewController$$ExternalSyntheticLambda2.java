package com.android.keyguard;

import android.net.Uri;
import androidx.slice.Slice;
import com.android.systemui.keyguard.KeyguardSliceProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardSliceViewController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardSliceViewController f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ KeyguardSliceViewController$$ExternalSyntheticLambda2(KeyguardSliceViewController keyguardSliceViewController, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardSliceViewController;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                KeyguardSliceViewController keyguardSliceViewController = this.f$0;
                KeyguardSliceProvider keyguardSliceProvider = (KeyguardSliceProvider) this.f$1;
                Uri uri = keyguardSliceViewController.mKeyguardSliceUri;
                keyguardSliceViewController.mHandler.post(new KeyguardSliceViewController$$ExternalSyntheticLambda2(keyguardSliceViewController, keyguardSliceProvider.onBindSlice(), 1));
                break;
            default:
                KeyguardSliceViewController keyguardSliceViewController2 = this.f$0;
                keyguardSliceViewController2.mObserver.onChanged((Slice) this.f$1);
                break;
        }
    }
}
