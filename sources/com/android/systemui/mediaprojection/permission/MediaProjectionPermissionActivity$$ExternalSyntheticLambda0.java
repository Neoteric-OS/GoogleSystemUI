package com.android.systemui.mediaprojection.permission;

import android.content.Intent;
import android.media.projection.IMediaProjection;
import android.media.projection.IMediaProjectionManager;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.mediaprojection.MediaProjectionServiceHelper;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorActivity;
import com.android.systemui.statusbar.phone.AlertDialogWithDelegate;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaProjectionPermissionActivity$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ MediaProjectionPermissionActivity f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ MediaProjectionPermissionActivity$$ExternalSyntheticLambda0(MediaProjectionPermissionActivity mediaProjectionPermissionActivity, boolean z) {
        this.f$0 = mediaProjectionPermissionActivity;
        this.f$1 = z;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        AlertDialogWithDelegate alertDialogWithDelegate;
        String str;
        MediaProjectionPermissionActivity mediaProjectionPermissionActivity = this.f$0;
        boolean z = this.f$1;
        int i = MediaProjectionPermissionActivity.$r8$clinit;
        int i2 = ((BaseMediaProjectionPermissionDialogDelegate) obj).selectedScreenShareOption.mode;
        try {
            try {
                int i3 = mediaProjectionPermissionActivity.mUid;
                String str2 = mediaProjectionPermissionActivity.mPackageName;
                IMediaProjection projection = mediaProjectionPermissionActivity.mReviewGrantedConsentRequired ? MediaProjectionServiceHelper.service.getProjection(i3, str2) : null;
                if (projection == null) {
                    projection = MediaProjectionServiceHelper.service.createProjection(i3, str2, 0, false);
                } else {
                    IMediaProjectionManager iMediaProjectionManager = MediaProjectionServiceHelper.service;
                }
                String str3 = "ShareToApp";
                if (i2 == 1) {
                    Intent intent = new Intent();
                    intent.putExtra("android.media.projection.extra.EXTRA_MEDIA_PROJECTION", projection.asBinder());
                    if (z) {
                        MediaProjectionAppSelectorActivity.ScreenShareType[] screenShareTypeArr = MediaProjectionAppSelectorActivity.ScreenShareType.$VALUES;
                        str = "SystemCast";
                    } else {
                        MediaProjectionAppSelectorActivity.ScreenShareType[] screenShareTypeArr2 = MediaProjectionAppSelectorActivity.ScreenShareType.$VALUES;
                        str = "ShareToApp";
                    }
                    intent.putExtra("screen_share_type", str);
                    mediaProjectionPermissionActivity.setResult(-1, intent);
                    mediaProjectionPermissionActivity.finish(1, projection);
                }
                if (i2 == 0) {
                    Intent intent2 = new Intent(mediaProjectionPermissionActivity, (Class<?>) MediaProjectionAppSelectorActivity.class);
                    intent2.putExtra("android.media.projection.extra.EXTRA_MEDIA_PROJECTION", projection.asBinder());
                    if (z) {
                        MediaProjectionAppSelectorActivity.ScreenShareType[] screenShareTypeArr3 = MediaProjectionAppSelectorActivity.ScreenShareType.$VALUES;
                        str3 = "SystemCast";
                    } else {
                        MediaProjectionAppSelectorActivity.ScreenShareType[] screenShareTypeArr4 = MediaProjectionAppSelectorActivity.ScreenShareType.$VALUES;
                    }
                    intent2.putExtra("screen_share_type", str3);
                    intent2.putExtra("launched_from_user_handle", UserHandle.getUserHandleForUid(mediaProjectionPermissionActivity.getLaunchedFromUid()));
                    intent2.putExtra("launched_from_host_uid", mediaProjectionPermissionActivity.getLaunchedFromUid());
                    intent2.putExtra("extra_media_projection_user_consent_required", mediaProjectionPermissionActivity.mReviewGrantedConsentRequired);
                    intent2.setFlags(33554432);
                    mediaProjectionPermissionActivity.mUserSelectingTask = true;
                    mediaProjectionPermissionActivity.startActivityAsUser(intent2, UserHandle.of(0));
                    mediaProjectionPermissionActivity.mStatusBarManager.collapsePanels();
                }
                alertDialogWithDelegate = mediaProjectionPermissionActivity.mDialog;
                if (alertDialogWithDelegate == null) {
                    return;
                }
            } catch (RemoteException e) {
                Log.e("MediaProjectionPermissionActivity", "Error granting projection permission", e);
                mediaProjectionPermissionActivity.setResult(0);
                mediaProjectionPermissionActivity.finish(0, null);
                alertDialogWithDelegate = mediaProjectionPermissionActivity.mDialog;
                if (alertDialogWithDelegate == null) {
                    return;
                }
            }
            alertDialogWithDelegate.dismiss();
        } catch (Throwable th) {
            AlertDialogWithDelegate alertDialogWithDelegate2 = mediaProjectionPermissionActivity.mDialog;
            if (alertDialogWithDelegate2 != null) {
                alertDialogWithDelegate2.dismiss();
            }
            throw th;
        }
    }
}
