package com.android.systemui.user;

import android.content.pm.UserInfo;
import android.os.RemoteException;
import android.util.Log;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CreateUserActivity$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ CreateUserActivity f$0;
    public final /* synthetic */ Boolean f$1;

    public /* synthetic */ CreateUserActivity$$ExternalSyntheticLambda4(CreateUserActivity createUserActivity, Boolean bool) {
        this.f$0 = createUserActivity;
        this.f$1 = bool;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        CreateUserActivity createUserActivity = this.f$0;
        Boolean bool = this.f$1;
        UserInfo userInfo = (UserInfo) obj;
        int i = CreateUserActivity.$r8$clinit;
        if (bool.booleanValue()) {
            createUserActivity.mUserCreator.userManager.setUserAdmin(userInfo.id);
        }
        try {
            createUserActivity.mActivityManager.switchUser(userInfo.id);
        } catch (RemoteException e) {
            Log.e("CreateUserActivity", "Couldn't switch user.", e);
        }
        if (createUserActivity.isFinishing() || createUserActivity.isDestroyed()) {
            return;
        }
        createUserActivity.finish();
    }
}
