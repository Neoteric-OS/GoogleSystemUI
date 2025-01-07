package com.android.systemui.mediaprojection.permission;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.app.StatusBarManager;
import android.app.compat.CompatChanges;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.projection.IMediaProjection;
import android.media.projection.IMediaProjectionManager;
import android.media.projection.MediaProjectionConfig;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.BidiFormatter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.mediaprojection.MediaProjectionMetricsLogger;
import com.android.systemui.mediaprojection.MediaProjectionServiceHelper;
import com.android.systemui.mediaprojection.SessionCreationSource;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDisabledDialogDelegate;
import com.android.systemui.statusbar.phone.AlertDialogWithDelegate;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.Utils;
import com.android.wm.shell.R;
import dagger.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class MediaProjectionPermissionActivity extends Activity {
    public static final /* synthetic */ int $r8$clinit = 0;
    public AlertDialogWithDelegate mDialog;
    public final FeatureFlags mFeatureFlags;
    public final MediaProjectionMetricsLogger mMediaProjectionMetricsLogger;
    public String mPackageName;
    public final Lazy mScreenCaptureDevicePolicyResolver;
    public final ScreenCaptureDisabledDialogDelegate mScreenCaptureDisabledDialogDelegate;
    public final StatusBarManager mStatusBarManager;
    public int mUid;
    public boolean mReviewGrantedConsentRequired = false;
    public boolean mUserSelectingTask = false;

    public MediaProjectionPermissionActivity(FeatureFlags featureFlags, Lazy lazy, StatusBarManager statusBarManager, KeyguardManager keyguardManager, MediaProjectionMetricsLogger mediaProjectionMetricsLogger, ScreenCaptureDisabledDialogDelegate screenCaptureDisabledDialogDelegate) {
        this.mFeatureFlags = featureFlags;
        this.mScreenCaptureDevicePolicyResolver = lazy;
        this.mStatusBarManager = statusBarManager;
        this.mMediaProjectionMetricsLogger = mediaProjectionMetricsLogger;
        this.mScreenCaptureDisabledDialogDelegate = screenCaptureDisabledDialogDelegate;
    }

    @Override // android.app.Activity
    public final void finish() {
        if (this.mUserSelectingTask) {
            super.finish();
        } else {
            finish(0, null);
        }
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.mReviewGrantedConsentRequired = intent.getBooleanExtra("extra_media_projection_user_consent_required", false);
        String callingPackage = getCallingPackage();
        this.mPackageName = callingPackage;
        if (callingPackage == null) {
            if (!intent.hasExtra("extra_media_projection_package_reusing_consent")) {
                setResult(0);
                finish(0, null);
                return;
            }
            this.mPackageName = intent.getStringExtra("extra_media_projection_package_reusing_consent");
        }
        PackageManager packageManager = getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.mPackageName, 0);
            int i = applicationInfo.uid;
            this.mUid = i;
            try {
                String str = this.mPackageName;
                IMediaProjectionManager iMediaProjectionManager = MediaProjectionServiceHelper.service;
                boolean hasProjectionPermission = iMediaProjectionManager.hasProjectionPermission(i, str);
                MediaProjectionMetricsLogger mediaProjectionMetricsLogger = this.mMediaProjectionMetricsLogger;
                if (hasProjectionPermission) {
                    if (bundle == null) {
                        mediaProjectionMetricsLogger.notifyProjectionInitiated(this.mUid, SessionCreationSource.APP);
                    }
                    int i2 = this.mUid;
                    String str2 = this.mPackageName;
                    IMediaProjection projection = this.mReviewGrantedConsentRequired ? iMediaProjectionManager.getProjection(i2, str2) : null;
                    if (projection == null) {
                        projection = iMediaProjectionManager.createProjection(i2, str2, 0, false);
                    }
                    ActivityOptions.LaunchCookie launchCookie = (ActivityOptions.LaunchCookie) intent.getParcelableExtra("android.media.projection.extra.EXTRA_LAUNCH_COOKIE", ActivityOptions.LaunchCookie.class);
                    if (launchCookie != null) {
                        projection.setLaunchCookie(launchCookie);
                    }
                    Intent intent2 = new Intent();
                    intent2.putExtra("android.media.projection.extra.EXTRA_MEDIA_PROJECTION", projection.asBinder());
                    setResult(-1, intent2);
                    finish(1, projection);
                    return;
                }
                if (((FeatureFlagsClassicRelease) this.mFeatureFlags).isEnabled(Flags.WM_ENABLE_PARTIAL_SCREEN_SHARING_ENTERPRISE_POLICIES)) {
                    if (((ScreenCaptureDevicePolicyResolver) this.mScreenCaptureDevicePolicyResolver.get()).isScreenCaptureCompletelyDisabled(UserHandle.getUserHandleForUid(getLaunchedFromUid()))) {
                        ScreenCaptureDisabledDialogDelegate screenCaptureDisabledDialogDelegate = this.mScreenCaptureDisabledDialogDelegate;
                        screenCaptureDisabledDialogDelegate.getClass();
                        AlertDialog create = new AlertDialog.Builder(screenCaptureDisabledDialogDelegate.context, R.style.Theme_SystemUI_Dialog).create();
                        Intrinsics.checkNotNull(create);
                        screenCaptureDisabledDialogDelegate.initDialog(create);
                        setUpDialog(create);
                        create.show();
                        setResult(0);
                        finish(0, null);
                        return;
                    }
                }
                String charSequence = applicationInfo.loadLabel(packageManager).toString();
                int length = charSequence.length();
                int i3 = 0;
                while (i3 < length) {
                    int codePointAt = charSequence.codePointAt(i3);
                    int type = Character.getType(codePointAt);
                    if (type == 13 || type == 15 || type == 14) {
                        charSequence = charSequence.substring(0, i3) + "…";
                        break;
                    }
                    i3 += Character.charCount(codePointAt);
                }
                if (charSequence.isEmpty()) {
                    charSequence = this.mPackageName;
                }
                TextPaint textPaint = new TextPaint();
                textPaint.setTextSize(42.0f);
                String unicodeWrap = BidiFormatter.getInstance().unicodeWrap(TextUtils.ellipsize(charSequence, textPaint, 500.0f, TextUtils.TruncateAt.END).toString());
                if (unicodeWrap == null || unicodeWrap.isEmpty()) {
                    unicodeWrap = this.mPackageName;
                }
                String str3 = unicodeWrap;
                boolean isHeadlessRemoteDisplayProvider = Utils.isHeadlessRemoteDisplayProvider(packageManager, this.mPackageName);
                Context applicationContext = getApplicationContext();
                boolean isChangeEnabled = CompatChanges.isChangeEnabled(316897322L, this.mPackageName, UserHandle.getUserHandleForUid(getLaunchedFromUid()));
                Intent intent3 = getIntent();
                MediaProjectionConfig mediaProjectionConfig = intent3 != null ? (MediaProjectionConfig) intent3.getParcelableExtra("android.media.projection.extra.EXTRA_MEDIA_PROJECTION_CONFIG") : null;
                MediaProjectionPermissionActivity$$ExternalSyntheticLambda0 mediaProjectionPermissionActivity$$ExternalSyntheticLambda0 = new MediaProjectionPermissionActivity$$ExternalSyntheticLambda0(this, isHeadlessRemoteDisplayProvider);
                MediaProjectionPermissionActivity$$ExternalSyntheticLambda1 mediaProjectionPermissionActivity$$ExternalSyntheticLambda1 = new MediaProjectionPermissionActivity$$ExternalSyntheticLambda1(this);
                this.mDialog = new AlertDialogWithDelegate(applicationContext, isHeadlessRemoteDisplayProvider ? new SystemCastPermissionDialogDelegate(applicationContext, mediaProjectionConfig, mediaProjectionPermissionActivity$$ExternalSyntheticLambda0, mediaProjectionPermissionActivity$$ExternalSyntheticLambda1, str3, isChangeEnabled, this.mUid, this.mMediaProjectionMetricsLogger) : new ShareToAppPermissionDialogDelegate(applicationContext, mediaProjectionConfig, mediaProjectionPermissionActivity$$ExternalSyntheticLambda0, mediaProjectionPermissionActivity$$ExternalSyntheticLambda1, str3, isChangeEnabled, this.mUid, this.mMediaProjectionMetricsLogger));
                if (bundle == null) {
                    mediaProjectionMetricsLogger.notifyProjectionInitiated(this.mUid, isHeadlessRemoteDisplayProvider ? SessionCreationSource.CAST : SessionCreationSource.APP);
                }
                setUpDialog(this.mDialog);
                this.mDialog.show();
                if (bundle == null) {
                    mediaProjectionMetricsLogger.notifyPermissionRequestDisplayed(this.mUid);
                }
            } catch (RemoteException e) {
                Log.e("MediaProjectionPermissionActivity", "Error checking projection permissions", e);
                setResult(0);
                finish(0, null);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("MediaProjectionPermissionActivity", "Unable to look up package name", e2);
            setResult(0);
            finish(0, null);
        }
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        AlertDialogWithDelegate alertDialogWithDelegate = this.mDialog;
        if (alertDialogWithDelegate != null) {
            alertDialogWithDelegate.setOnDismissListener(null);
            this.mDialog.setOnCancelListener(null);
            this.mDialog.dismiss();
        }
    }

    public final void setUpDialog(AlertDialog alertDialog) {
        SystemUIDialog.registerDismissListener(alertDialog, null);
        SystemUIDialog.applyFlags(alertDialog, false);
        SystemUIDialog.setDialogSize(alertDialog);
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                MediaProjectionPermissionActivity mediaProjectionPermissionActivity = MediaProjectionPermissionActivity.this;
                int i = MediaProjectionPermissionActivity.$r8$clinit;
                if (mediaProjectionPermissionActivity.isFinishing()) {
                    return;
                }
                mediaProjectionPermissionActivity.finish();
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                MediaProjectionPermissionActivity mediaProjectionPermissionActivity = MediaProjectionPermissionActivity.this;
                int i = MediaProjectionPermissionActivity.$r8$clinit;
                if (mediaProjectionPermissionActivity.isFinishing()) {
                    return;
                }
                mediaProjectionPermissionActivity.finish();
            }
        });
        alertDialog.create();
        alertDialog.getButton(-1).setFilterTouchesWhenObscured(true);
        alertDialog.getWindow().addSystemFlags(524288);
    }

    public final void finish(int i, IMediaProjection iMediaProjection) {
        boolean z = this.mReviewGrantedConsentRequired;
        IMediaProjectionManager iMediaProjectionManager = MediaProjectionServiceHelper.service;
        MediaProjectionServiceHelper.Companion.setReviewedConsentIfNeeded(i, z, iMediaProjection);
        super.finish();
    }
}
