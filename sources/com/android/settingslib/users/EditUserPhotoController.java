package com.android.settingslib.users;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.android.settingslib.utils.ThreadUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.user.CreateUserActivity;
import com.android.systemui.user.CreateUserActivity$$ExternalSyntheticLambda0;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EditUserPhotoController {
    public final CreateUserActivity mActivity;
    public final CreateUserActivity$$ExternalSyntheticLambda0 mActivityStarter;
    public String mCachedDrawablePath;
    public final ListeningExecutorService mExecutorService;
    public final ImageView mImageView;
    public final File mImagesDir;
    public Bitmap mNewUserPhotoBitmap;
    public CircleFramedDrawable mNewUserPhotoDrawable;

    /* renamed from: -$$Nest$monPhotoProcessed, reason: not valid java name */
    public static void m760$$Nest$monPhotoProcessed(final EditUserPhotoController editUserPhotoController, Bitmap bitmap) {
        if (bitmap == null) {
            editUserPhotoController.getClass();
            return;
        }
        editUserPhotoController.mNewUserPhotoBitmap = bitmap;
        ((MoreExecutors.ListeningDecorator) editUserPhotoController.mExecutorService).submit(new Runnable() { // from class: com.android.settingslib.users.EditUserPhotoController$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                EditUserPhotoController editUserPhotoController2 = EditUserPhotoController.this;
                File file = null;
                if (editUserPhotoController2.mNewUserPhotoBitmap != null) {
                    try {
                        File file2 = new File(editUserPhotoController2.mImagesDir, "NewUserPhoto.png");
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        editUserPhotoController2.mNewUserPhotoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        file = file2;
                    } catch (IOException e) {
                        Log.e("EditUserPhotoController", "Cannot create temp file", e);
                    }
                }
                editUserPhotoController2.mCachedDrawablePath = file.getPath();
            }
        });
        Context context = editUserPhotoController.mImageView.getContext();
        Bitmap bitmap2 = editUserPhotoController.mNewUserPhotoBitmap;
        int i = CircleFramedDrawable.$r8$clinit;
        CircleFramedDrawable circleFramedDrawable = new CircleFramedDrawable(bitmap2, context.getResources().getDimensionPixelSize(17105747));
        editUserPhotoController.mNewUserPhotoDrawable = circleFramedDrawable;
        editUserPhotoController.mImageView.setImageDrawable(circleFramedDrawable);
    }

    public EditUserPhotoController(CreateUserActivity createUserActivity, CreateUserActivity$$ExternalSyntheticLambda0 createUserActivity$$ExternalSyntheticLambda0, ImageView imageView, Bitmap bitmap, CircleFramedDrawable circleFramedDrawable) {
        this.mActivity = createUserActivity;
        this.mActivityStarter = createUserActivity$$ExternalSyntheticLambda0;
        File file = new File(createUserActivity.getCacheDir(), "multi_user");
        this.mImagesDir = file;
        file.mkdir();
        this.mImageView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.android.settingslib.users.EditUserPhotoController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditUserPhotoController editUserPhotoController = EditUserPhotoController.this;
                editUserPhotoController.getClass();
                final Intent intent = new Intent("com.android.avatarpicker.FULL_SCREEN_ACTIVITY");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.putExtra("is_user_new", true);
                intent.putExtra("file_authority", "com.android.systemui.fileprovider");
                CreateUserActivity$$ExternalSyntheticLambda0 createUserActivity$$ExternalSyntheticLambda02 = editUserPhotoController.mActivityStarter;
                createUserActivity$$ExternalSyntheticLambda02.getClass();
                int i = CreateUserActivity.$r8$clinit;
                final CreateUserActivity createUserActivity2 = createUserActivity$$ExternalSyntheticLambda02.f$0;
                createUserActivity2.mActivityStarter.dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.user.CreateUserActivity$$ExternalSyntheticLambda6
                    @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
                    public final boolean onDismiss() {
                        Intent intent2 = intent;
                        CreateUserActivity createUserActivity3 = CreateUserActivity.this;
                        createUserActivity3.mCreateUserDialogController.mWaitingForActivityResult = true;
                        createUserActivity3.startActivityForResult(intent2, 1004);
                        return true;
                    }
                }, null, true);
            }
        });
        this.mNewUserPhotoBitmap = bitmap;
        this.mNewUserPhotoDrawable = circleFramedDrawable;
        this.mExecutorService = ThreadUtils.getBackgroundExecutor();
    }
}
