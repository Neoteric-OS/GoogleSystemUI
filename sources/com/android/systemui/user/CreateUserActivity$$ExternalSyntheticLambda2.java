package com.android.systemui.user;

import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CreateUserActivity$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CreateUserActivity f$0;

    public /* synthetic */ CreateUserActivity$$ExternalSyntheticLambda2(CreateUserActivity createUserActivity, int i) {
        this.$r8$classId = i;
        this.f$0 = createUserActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        CreateUserActivity createUserActivity = this.f$0;
        switch (i) {
            case 0:
                createUserActivity.finish();
                break;
            default:
                int i2 = CreateUserActivity.$r8$clinit;
                Log.e("CreateUserActivity", "Unable to create user");
                if (!createUserActivity.isFinishing() && !createUserActivity.isDestroyed()) {
                    createUserActivity.finish();
                    break;
                }
                break;
        }
    }
}
