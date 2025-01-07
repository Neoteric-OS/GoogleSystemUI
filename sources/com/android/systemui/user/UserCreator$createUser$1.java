package com.android.systemui.user;

import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.android.internal.util.UserIcons;
import com.android.settingslib.users.UserCreatingDialog;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserCreator$createUser$1 implements Runnable {
    public final /* synthetic */ CreateUserActivity$$ExternalSyntheticLambda2 $errorCallback;
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ CreateUserActivity$$ExternalSyntheticLambda4 $successCallback;
    public final /* synthetic */ UserCreatingDialog $userCreationProgressDialog;
    public final /* synthetic */ Drawable $userIcon;
    public final /* synthetic */ Object $userName;
    public final /* synthetic */ UserCreator this$0;

    public UserCreator$createUser$1(UserInfo userInfo, UserCreatingDialog userCreatingDialog, CreateUserActivity$$ExternalSyntheticLambda2 createUserActivity$$ExternalSyntheticLambda2, UserCreator userCreator, CreateUserActivity$$ExternalSyntheticLambda4 createUserActivity$$ExternalSyntheticLambda4, Drawable drawable) {
        this.$userName = userInfo;
        this.$userCreationProgressDialog = userCreatingDialog;
        this.$errorCallback = createUserActivity$$ExternalSyntheticLambda2;
        this.this$0 = userCreator;
        this.$successCallback = createUserActivity$$ExternalSyntheticLambda4;
        this.$userIcon = drawable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                UserInfo createUser = this.this$0.userManager.createUser((String) this.$userName, "android.os.usertype.full.SECONDARY", 0);
                UserCreator userCreator = this.this$0;
                userCreator.mainExecutor.execute(new UserCreator$createUser$1(createUser, this.$userCreationProgressDialog, this.$errorCallback, userCreator, this.$successCallback, this.$userIcon));
                break;
            default:
                final UserInfo userInfo = (UserInfo) this.$userName;
                if (userInfo != null) {
                    final UserCreator userCreator2 = this.this$0;
                    Executor executor = userCreator2.bgExecutor;
                    final Drawable drawable = this.$userIcon;
                    executor.execute(new Runnable() { // from class: com.android.systemui.user.UserCreator$createUser$1$1$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            Drawable drawable2 = drawable;
                            Resources resources = userCreator2.context.getResources();
                            if (drawable2 == null) {
                                drawable2 = UserIcons.getDefaultUserIcon(resources, userInfo.id, false);
                            }
                            userCreator2.userManager.setUserIcon(userInfo.id, UserIcons.convertToBitmapAtUserIconSize(resources, drawable2));
                        }
                    });
                    this.$userCreationProgressDialog.dismiss();
                    this.$successCallback.accept((UserInfo) this.$userName);
                    break;
                } else {
                    this.$userCreationProgressDialog.dismiss();
                    this.$errorCallback.run();
                    break;
                }
        }
    }

    public UserCreator$createUser$1(UserCreator userCreator, String str, UserCreatingDialog userCreatingDialog, CreateUserActivity$$ExternalSyntheticLambda2 createUserActivity$$ExternalSyntheticLambda2, CreateUserActivity$$ExternalSyntheticLambda4 createUserActivity$$ExternalSyntheticLambda4, Drawable drawable) {
        this.this$0 = userCreator;
        this.$userName = str;
        this.$userCreationProgressDialog = userCreatingDialog;
        this.$errorCallback = createUserActivity$$ExternalSyntheticLambda2;
        this.$successCallback = createUserActivity$$ExternalSyntheticLambda4;
        this.$userIcon = drawable;
    }
}
