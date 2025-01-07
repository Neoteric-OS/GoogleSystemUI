package com.android.systemui.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.IActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.UserIcons;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.android.settingslib.users.CreateUserDialogController;
import com.android.settingslib.users.EditUserPhotoController;
import com.android.settingslib.utils.CustomDialogHelper;
import com.android.settingslib.utils.ThreadUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.user.CreateUserActivity;
import com.android.systemui.user.CreateUserActivity$$ExternalSyntheticLambda0;
import com.android.systemui.user.CreateUserActivity$$ExternalSyntheticLambda2;
import com.android.systemui.user.CreateUserActivity$$ExternalSyntheticLambda4;
import com.android.systemui.user.UserCreator;
import com.android.systemui.user.UserCreator$createUser$1;
import com.android.wm.shell.R;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.File;
import java.util.concurrent.Callable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class CreateUserActivity extends Activity {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final IActivityManager mActivityManager;
    public final ActivityStarter mActivityStarter;
    public final CreateUserActivity$$ExternalSyntheticLambda3 mBackCallback = new OnBackInvokedCallback() { // from class: com.android.systemui.user.CreateUserActivity$$ExternalSyntheticLambda3
        @Override // android.window.OnBackInvokedCallback
        public final void onBackInvoked() {
            CreateUserActivity createUserActivity = CreateUserActivity.this;
            int i = CreateUserActivity.$r8$clinit;
            Dialog dialog = createUserActivity.mSetupUserDialog;
            if (dialog != null) {
                dialog.dismiss();
            }
            createUserActivity.finish();
        }
    };
    public final CreateUserDialogController mCreateUserDialogController;
    public Dialog mSetupUserDialog;
    public final UserCreator mUserCreator;

    /* JADX WARN: Type inference failed for: r5v1, types: [com.android.systemui.user.CreateUserActivity$$ExternalSyntheticLambda3] */
    public CreateUserActivity(UserCreator userCreator, CreateUserDialogController createUserDialogController, IActivityManager iActivityManager, ActivityStarter activityStarter, UiEventLogger uiEventLogger) {
        this.mUserCreator = userCreator;
        this.mCreateUserDialogController = createUserDialogController;
        this.mActivityManager = iActivityManager;
        this.mActivityStarter = activityStarter;
    }

    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        CreateUserDialogController createUserDialogController = this.mCreateUserDialogController;
        createUserDialogController.mWaitingForActivityResult = false;
        final EditUserPhotoController editUserPhotoController = createUserDialogController.mEditUserPhotoController;
        if (editUserPhotoController != null && i2 == -1 && i == 1004) {
            boolean hasExtra = intent.hasExtra("default_icon_tint_color");
            ListeningExecutorService listeningExecutorService = editUserPhotoController.mExecutorService;
            if (hasExtra) {
                final int intExtra = intent.getIntExtra("default_icon_tint_color", -1);
                ListenableFuture submit = ((MoreExecutors.ListeningDecorator) listeningExecutorService).submit(new Callable() { // from class: com.android.settingslib.users.EditUserPhotoController$$ExternalSyntheticLambda2
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        Resources resources = EditUserPhotoController.this.mActivity.getResources();
                        return UserIcons.convertToBitmapAtUserIconSize(resources, UserIcons.getDefaultUserIconInColor(resources, intExtra));
                    }
                });
                final int i3 = 0;
                FutureCallback anonymousClass1 = new FutureCallback() { // from class: com.android.settingslib.users.EditUserPhotoController.1
                    public final /* synthetic */ int $r8$classId;
                    public final /* synthetic */ EditUserPhotoController this$0;

                    public /* synthetic */ AnonymousClass1(final EditUserPhotoController editUserPhotoController2, final int i32) {
                        r2 = i32;
                        r1 = editUserPhotoController2;
                    }

                    @Override // com.google.common.util.concurrent.FutureCallback
                    public final void onFailure(Throwable th) {
                        switch (r2) {
                            case 0:
                                Log.e("EditUserPhotoController", "Error processing default icon", th);
                                break;
                        }
                    }

                    @Override // com.google.common.util.concurrent.FutureCallback
                    public final void onSuccess(Object obj) {
                        switch (r2) {
                            case 0:
                                EditUserPhotoController.m760$$Nest$monPhotoProcessed(r1, (Bitmap) obj);
                                break;
                            default:
                                EditUserPhotoController.m760$$Nest$monPhotoProcessed(r1, (Bitmap) obj);
                                break;
                        }
                    }

                    private final void onFailure$com$android$settingslib$users$EditUserPhotoController$2(Throwable th) {
                    }
                };
                submit.addListener(new Futures.CallbackListener(submit, anonymousClass1), editUserPhotoController2.mImageView.getContext().getMainExecutor());
                return;
            }
            if (intent.getData() != null) {
                final Uri data = intent.getData();
                ListenableFuture submit2 = ((MoreExecutors.ListeningDecorator) listeningExecutorService).submit(new Callable() { // from class: com.android.settingslib.users.EditUserPhotoController$$ExternalSyntheticLambda1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Removed duplicated region for block: B:25:0x003b A[EXC_TOP_SPLITTER, SYNTHETIC] */
                    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.InputStream] */
                    /* JADX WARN: Type inference failed for: r3v3 */
                    /* JADX WARN: Type inference failed for: r3v8 */
                    /* JADX WARN: Type inference failed for: r5v10, types: [java.io.InputStream] */
                    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.IOException, java.lang.Throwable] */
                    /* JADX WARN: Type inference failed for: r5v6 */
                    /* JADX WARN: Type inference failed for: r5v7, types: [java.io.InputStream] */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0021 -> B:9:0x0038). Please report as a decompilation issue!!! */
                    @Override // java.util.concurrent.Callable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object call() {
                        /*
                            r5 = this;
                            android.net.Uri r0 = r2
                            com.android.settingslib.users.EditUserPhotoController r5 = com.android.settingslib.users.EditUserPhotoController.this
                            r5.getClass()
                            java.lang.String r1 = "Cannot close image stream"
                            java.lang.String r2 = "EditUserPhotoController"
                            r3 = 0
                            com.android.systemui.user.CreateUserActivity r5 = r5.mActivity     // Catch: java.lang.Throwable -> L2a java.io.FileNotFoundException -> L2c
                            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch: java.lang.Throwable -> L2a java.io.FileNotFoundException -> L2c
                            java.io.InputStream r5 = r5.openInputStream(r0)     // Catch: java.lang.Throwable -> L2a java.io.FileNotFoundException -> L2c
                            android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeStream(r5)     // Catch: java.lang.Throwable -> L25 java.io.FileNotFoundException -> L28
                            if (r5 == 0) goto L38
                            r5.close()     // Catch: java.io.IOException -> L20
                            goto L38
                        L20:
                            r5 = move-exception
                            android.util.Log.w(r2, r1, r5)
                            goto L38
                        L25:
                            r0 = move-exception
                            r3 = r5
                            goto L39
                        L28:
                            r0 = move-exception
                            goto L2e
                        L2a:
                            r0 = move-exception
                            goto L39
                        L2c:
                            r0 = move-exception
                            r5 = r3
                        L2e:
                            java.lang.String r4 = "Cannot find image file"
                            android.util.Log.w(r2, r4, r0)     // Catch: java.lang.Throwable -> L25
                            if (r5 == 0) goto L38
                            r5.close()     // Catch: java.io.IOException -> L20
                        L38:
                            return r3
                        L39:
                            if (r3 == 0) goto L43
                            r3.close()     // Catch: java.io.IOException -> L3f
                            goto L43
                        L3f:
                            r5 = move-exception
                            android.util.Log.w(r2, r1, r5)
                        L43:
                            throw r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.users.EditUserPhotoController$$ExternalSyntheticLambda1.call():java.lang.Object");
                    }
                });
                final int i4 = 1;
                FutureCallback anonymousClass12 = new FutureCallback() { // from class: com.android.settingslib.users.EditUserPhotoController.1
                    public final /* synthetic */ int $r8$classId;
                    public final /* synthetic */ EditUserPhotoController this$0;

                    public /* synthetic */ AnonymousClass1(final EditUserPhotoController editUserPhotoController2, final int i42) {
                        r2 = i42;
                        r1 = editUserPhotoController2;
                    }

                    @Override // com.google.common.util.concurrent.FutureCallback
                    public final void onFailure(Throwable th) {
                        switch (r2) {
                            case 0:
                                Log.e("EditUserPhotoController", "Error processing default icon", th);
                                break;
                        }
                    }

                    @Override // com.google.common.util.concurrent.FutureCallback
                    public final void onSuccess(Object obj) {
                        switch (r2) {
                            case 0:
                                EditUserPhotoController.m760$$Nest$monPhotoProcessed(r1, (Bitmap) obj);
                                break;
                            default:
                                EditUserPhotoController.m760$$Nest$monPhotoProcessed(r1, (Bitmap) obj);
                                break;
                        }
                    }

                    private final void onFailure$com$android$settingslib$users$EditUserPhotoController$2(Throwable th) {
                    }
                };
                submit2.addListener(new Futures.CallbackListener(submit2, anonymousClass12), editUserPhotoController2.mImageView.getContext().getMainExecutor());
            }
        }
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        Dialog dialog = this.mSetupUserDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        finish();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        final int i = 1;
        setShowWhenLocked(true);
        setContentView(R.layout.activity_create_new_user);
        final CreateUserDialogController createUserDialogController = this.mCreateUserDialogController;
        final int i2 = 0;
        if (bundle != null) {
            createUserDialogController.getClass();
            createUserDialogController.mCachedDrawablePath = bundle.getString("pending_photo");
            createUserDialogController.mCurrentState = bundle.getInt("current_state");
            if (bundle.containsKey("admin_status")) {
                createUserDialogController.mIsAdmin = Boolean.valueOf(bundle.getBoolean("admin_status"));
            }
            createUserDialogController.mSavedName = bundle.getString("saved_name");
            createUserDialogController.mWaitingForActivityResult = bundle.getBoolean("awaiting_result", false);
        }
        getString(R.string.user_new_user_name);
        boolean booleanExtra = getIntent().getBooleanExtra("extra_is_keyguard_showing", true);
        CreateUserActivity$$ExternalSyntheticLambda0 createUserActivity$$ExternalSyntheticLambda0 = new CreateUserActivity$$ExternalSyntheticLambda0(this);
        UserCreator userCreator = this.mUserCreator;
        userCreator.getClass();
        final boolean z = UserManager.isMultipleAdminEnabled() && !userCreator.userManager.hasUserRestriction("no_grant_admin") && userCreator.userManager.isAdminUser() && !booleanExtra;
        CreateUserActivity$$ExternalSyntheticLambda0 createUserActivity$$ExternalSyntheticLambda02 = new CreateUserActivity$$ExternalSyntheticLambda0(this);
        CreateUserActivity$$ExternalSyntheticLambda2 createUserActivity$$ExternalSyntheticLambda2 = new CreateUserActivity$$ExternalSyntheticLambda2(this, 0);
        createUserDialogController.mActivity = this;
        CustomDialogHelper customDialogHelper = new CustomDialogHelper();
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_with_icon, (ViewGroup) null);
        customDialogHelper.mDialogIcon = (ImageView) inflate.findViewById(R.id.dialog_with_icon_icon);
        customDialogHelper.mDialogTitle = (TextView) inflate.findViewById(R.id.dialog_with_icon_title);
        customDialogHelper.mDialogMessage = (TextView) inflate.findViewById(R.id.dialog_with_icon_message);
        customDialogHelper.mCustomLayout = (LinearLayout) inflate.findViewById(R.id.custom_layout);
        customDialogHelper.mPositiveButton = (Button) inflate.findViewById(R.id.button_ok);
        customDialogHelper.mNegativeButton = (Button) inflate.findViewById(R.id.button_cancel);
        customDialogHelper.mBackButton = (Button) inflate.findViewById(R.id.button_back);
        AlertDialog create = new AlertDialog.Builder(this).setView(inflate).setCancelable(true).create();
        customDialogHelper.mDialog = create;
        create.getWindow().setSoftInputMode(4);
        createUserDialogController.mCustomDialogHelper = customDialogHelper;
        createUserDialogController.mSuccessCallback = createUserActivity$$ExternalSyntheticLambda02;
        createUserDialogController.mCancelCallback = createUserActivity$$ExternalSyntheticLambda2;
        createUserDialogController.mActivityStarter = createUserActivity$$ExternalSyntheticLambda0;
        View inflate2 = View.inflate(createUserDialogController.mActivity, R.layout.grant_admin_dialog_content, null);
        createUserDialogController.mGrantAdminView = inflate2;
        createUserDialogController.mCustomDialogHelper.mCustomLayout.addView(inflate2);
        RadioGroup radioGroup = (RadioGroup) createUserDialogController.mGrantAdminView.findViewById(R.id.choose_admin);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.android.settingslib.users.CreateUserDialogController$$ExternalSyntheticLambda4
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i3) {
                CreateUserDialogController createUserDialogController2 = CreateUserDialogController.this;
                createUserDialogController2.mCustomDialogHelper.mPositiveButton.setEnabled(true);
                createUserDialogController2.mIsAdmin = Boolean.valueOf(i3 == R.id.grant_admin_yes);
            }
        });
        if (Boolean.TRUE.equals(createUserDialogController.mIsAdmin)) {
            ((RadioButton) radioGroup.findViewById(R.id.grant_admin_yes)).setChecked(true);
        } else if (Boolean.FALSE.equals(createUserDialogController.mIsAdmin)) {
            ((RadioButton) radioGroup.findViewById(R.id.grant_admin_no)).setChecked(true);
        }
        View inflate3 = View.inflate(createUserDialogController.mActivity, R.layout.edit_user_info_dialog_content, null);
        createUserDialogController.mEditUserInfoView = inflate3;
        createUserDialogController.mCustomDialogHelper.mCustomLayout.addView(inflate3);
        EditText editText = (EditText) createUserDialogController.mEditUserInfoView.findViewById(R.id.user_name);
        createUserDialogController.mUserNameView = editText;
        String str = createUserDialogController.mSavedName;
        if (str == null) {
            editText.setText(R.string.user_new_user_name);
        } else {
            editText.setText(str);
        }
        final ImageView imageView = (ImageView) createUserDialogController.mEditUserInfoView.findViewById(R.id.user_photo);
        Drawable defaultUserIcon = UserIcons.getDefaultUserIcon(createUserDialogController.mActivity.getResources(), -10000, false);
        if (createUserDialogController.mCachedDrawablePath != null) {
            ListenableFuture submit = ((MoreExecutors.ListeningDecorator) ThreadUtils.getBackgroundExecutor()).submit(new Callable() { // from class: com.android.settingslib.users.CreateUserDialogController$$ExternalSyntheticLambda5
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    CreateUserDialogController createUserDialogController2 = CreateUserDialogController.this;
                    createUserDialogController2.getClass();
                    Bitmap decodeFile = BitmapFactory.decodeFile(new File(createUserDialogController2.mCachedDrawablePath).getAbsolutePath());
                    createUserDialogController2.mSavedPhoto = decodeFile;
                    CreateUserActivity createUserActivity = createUserDialogController2.mActivity;
                    int i3 = CircleFramedDrawable.$r8$clinit;
                    CircleFramedDrawable circleFramedDrawable = new CircleFramedDrawable(decodeFile, createUserActivity.getResources().getDimensionPixelSize(17105747));
                    createUserDialogController2.mSavedDrawable = circleFramedDrawable;
                    return circleFramedDrawable;
                }
            });
            submit.addListener(new Futures.CallbackListener(submit, new FutureCallback() { // from class: com.android.settingslib.users.CreateUserDialogController.1
                @Override // com.google.common.util.concurrent.FutureCallback
                public final void onSuccess(Object obj) {
                    imageView.setImageDrawable((Drawable) obj);
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public final void onFailure(Throwable th) {
                }
            }), createUserDialogController.mActivity.getMainExecutor());
        } else {
            imageView.setImageDrawable(defaultUserIcon);
        }
        if (createUserDialogController.isChangePhotoRestrictedByBase(createUserDialogController.mActivity)) {
            createUserDialogController.mEditUserInfoView.findViewById(R.id.add_a_photo_icon).setVisibility(8);
        } else {
            final RestrictedLockUtils.EnforcedAdmin changePhotoAdminRestriction = createUserDialogController.getChangePhotoAdminRestriction(createUserDialogController.mActivity);
            if (changePhotoAdminRestriction != null) {
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.android.settingslib.users.CreateUserDialogController$$ExternalSyntheticLambda3
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        CreateUserDialogController createUserDialogController2 = CreateUserDialogController.this;
                        RestrictedLockUtils.sendShowAdminSupportDetailsIntent(createUserDialogController2.mActivity, changePhotoAdminRestriction);
                    }
                });
            } else {
                createUserDialogController.mEditUserPhotoController = createUserDialogController.createEditUserPhotoController(imageView);
            }
        }
        createUserDialogController.mCustomDialogHelper.setButton(6, R.string.next, new View.OnClickListener() { // from class: com.android.settingslib.users.CreateUserDialogController$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i2) {
                    case 0:
                        CreateUserDialogController createUserDialogController2 = createUserDialogController;
                        boolean z2 = z;
                        int i3 = createUserDialogController2.mCurrentState;
                        int i4 = i3 + 1;
                        createUserDialogController2.mCurrentState = i4;
                        if (i4 == 1 && !z2) {
                            createUserDialogController2.mCurrentState = i3 + 2;
                        }
                        createUserDialogController2.updateLayout();
                        break;
                    default:
                        CreateUserDialogController createUserDialogController3 = createUserDialogController;
                        boolean z3 = z;
                        int i5 = createUserDialogController3.mCurrentState;
                        int i6 = i5 - 1;
                        createUserDialogController3.mCurrentState = i6;
                        if (i6 == 1 && !z3) {
                            createUserDialogController3.mCurrentState = i5 - 2;
                        }
                        createUserDialogController3.updateLayout();
                        break;
                }
            }
        });
        createUserDialogController.mCustomDialogHelper.setButton(5, R.string.back, new View.OnClickListener() { // from class: com.android.settingslib.users.CreateUserDialogController$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i) {
                    case 0:
                        CreateUserDialogController createUserDialogController2 = createUserDialogController;
                        boolean z2 = z;
                        int i3 = createUserDialogController2.mCurrentState;
                        int i4 = i3 + 1;
                        createUserDialogController2.mCurrentState = i4;
                        if (i4 == 1 && !z2) {
                            createUserDialogController2.mCurrentState = i3 + 2;
                        }
                        createUserDialogController2.updateLayout();
                        break;
                    default:
                        CreateUserDialogController createUserDialogController3 = createUserDialogController;
                        boolean z3 = z;
                        int i5 = createUserDialogController3.mCurrentState;
                        int i6 = i5 - 1;
                        createUserDialogController3.mCurrentState = i6;
                        if (i6 == 1 && !z3) {
                            createUserDialogController3.mCurrentState = i5 - 2;
                        }
                        createUserDialogController3.updateLayout();
                        break;
                }
            }
        });
        createUserDialogController.mUserCreationDialog = createUserDialogController.mCustomDialogHelper.mDialog;
        createUserDialogController.updateLayout();
        createUserDialogController.mUserCreationDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.settingslib.users.CreateUserDialogController$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                CreateUserDialogController createUserDialogController2 = CreateUserDialogController.this;
                if (createUserDialogController2.mCurrentState == 3) {
                    CreateUserActivity$$ExternalSyntheticLambda0 createUserActivity$$ExternalSyntheticLambda03 = createUserDialogController2.mSuccessCallback;
                    if (createUserActivity$$ExternalSyntheticLambda03 != null) {
                        String str2 = createUserDialogController2.mUserName;
                        Drawable drawable = createUserDialogController2.mNewUserIcon;
                        Boolean valueOf = Boolean.valueOf(Boolean.TRUE.equals(createUserDialogController2.mIsAdmin));
                        CreateUserActivity createUserActivity = createUserActivity$$ExternalSyntheticLambda03.f$0;
                        createUserActivity.mSetupUserDialog.dismiss();
                        if (str2 == null || str2.trim().isEmpty()) {
                            str2 = createUserActivity.getString(R.string.user_new_user_name);
                        }
                        String str3 = str2;
                        CreateUserActivity$$ExternalSyntheticLambda4 createUserActivity$$ExternalSyntheticLambda4 = new CreateUserActivity$$ExternalSyntheticLambda4(createUserActivity, valueOf);
                        CreateUserActivity$$ExternalSyntheticLambda2 createUserActivity$$ExternalSyntheticLambda22 = new CreateUserActivity$$ExternalSyntheticLambda2(createUserActivity, 1);
                        UserCreator userCreator2 = createUserActivity.mUserCreator;
                        userCreator2.getClass();
                        UserCreatingDialog userCreatingDialog = new UserCreatingDialog(userCreator2.context, false);
                        userCreatingDialog.show();
                        userCreator2.bgExecutor.execute(new UserCreator$createUser$1(userCreator2, str3, userCreatingDialog, createUserActivity$$ExternalSyntheticLambda22, createUserActivity$$ExternalSyntheticLambda4, drawable));
                    }
                } else {
                    CreateUserActivity$$ExternalSyntheticLambda2 createUserActivity$$ExternalSyntheticLambda23 = createUserDialogController2.mCancelCallback;
                    if (createUserActivity$$ExternalSyntheticLambda23 != null) {
                        createUserActivity$$ExternalSyntheticLambda23.run();
                    }
                }
                createUserDialogController2.mUserCreationDialog = null;
                createUserDialogController2.mCustomDialogHelper = null;
                createUserDialogController2.mEditUserPhotoController = null;
                createUserDialogController2.mSavedPhoto = null;
                createUserDialogController2.mSavedName = null;
                createUserDialogController2.mSavedDrawable = null;
                createUserDialogController2.mIsAdmin = null;
                createUserDialogController2.mActivity = null;
                createUserDialogController2.mActivityStarter = null;
                createUserDialogController2.mGrantAdminView = null;
                createUserDialogController2.mEditUserInfoView = null;
                createUserDialogController2.mUserNameView = null;
                createUserDialogController2.mSuccessCallback = null;
                createUserDialogController2.mCancelCallback = null;
                createUserDialogController2.mCachedDrawablePath = null;
                createUserDialogController2.mCurrentState = 0;
            }
        });
        createUserDialogController.mCustomDialogHelper.mDialogMessage.setPadding(10, 10, 10, 10);
        createUserDialogController.mUserCreationDialog.setCanceledOnTouchOutside(true);
        Dialog dialog = createUserDialogController.mUserCreationDialog;
        this.mSetupUserDialog = dialog;
        dialog.show();
        getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, this.mBackCallback);
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.mBackCallback);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final void onRestoreInstanceState(Bundle bundle) {
        Dialog dialog;
        super.onRestoreInstanceState(bundle);
        Bundle bundle2 = bundle.getBundle("create_user_dialog_state");
        if (bundle2 == null || (dialog = this.mSetupUserDialog) == null) {
            return;
        }
        dialog.onRestoreInstanceState(bundle2);
    }

    @Override // android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        EditUserPhotoController editUserPhotoController;
        Dialog dialog = this.mSetupUserDialog;
        if (dialog != null && dialog.isShowing()) {
            bundle.putBundle("create_user_dialog_state", this.mSetupUserDialog.onSaveInstanceState());
        }
        CreateUserDialogController createUserDialogController = this.mCreateUserDialogController;
        if (createUserDialogController.mUserCreationDialog != null && (editUserPhotoController = createUserDialogController.mEditUserPhotoController) != null && createUserDialogController.mCachedDrawablePath == null) {
            createUserDialogController.mCachedDrawablePath = editUserPhotoController.mCachedDrawablePath;
        }
        String str = createUserDialogController.mCachedDrawablePath;
        if (str != null) {
            bundle.putString("pending_photo", str);
        }
        Boolean bool = createUserDialogController.mIsAdmin;
        if (bool != null) {
            bundle.putBoolean("admin_status", Boolean.TRUE.equals(bool));
        }
        bundle.putString("saved_name", createUserDialogController.mUserNameView.getText().toString().trim());
        bundle.putInt("current_state", createUserDialogController.mCurrentState);
        bundle.putBoolean("awaiting_result", createUserDialogController.mWaitingForActivityResult);
        super.onSaveInstanceState(bundle);
    }
}
