package com.android.systemui.biometrics;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.hardware.biometrics.BiometricSourceType;
import android.hardware.face.Face;
import android.hardware.face.FaceManager;
import android.hardware.fingerprint.Fingerprint;
import android.hardware.fingerprint.FingerprintManager;
import android.util.Log;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricNotificationDialogFactory {
    public final FaceManager mFaceManager;
    public final FingerprintManager mFingerprintManager;
    public final Resources mResources;
    public final SystemUIDialog.Factory mSystemUIDialogFactory;

    public BiometricNotificationDialogFactory(Resources resources, SystemUIDialog.Factory factory, FingerprintManager fingerprintManager, FaceManager faceManager) {
        this.mResources = resources;
        this.mSystemUIDialogFactory = factory;
        this.mFingerprintManager = fingerprintManager;
        this.mFaceManager = faceManager;
    }

    public final SystemUIDialog createReenrollDialog(final int i, final BiometricNotificationBroadcastReceiver$$ExternalSyntheticLambda0 biometricNotificationBroadcastReceiver$$ExternalSyntheticLambda0, final BiometricSourceType biometricSourceType, boolean z) {
        SystemUIDialog create = this.mSystemUIDialogFactory.create();
        if (biometricSourceType == BiometricSourceType.FACE) {
            create.setTitle(this.mResources.getString(R.string.face_re_enroll_dialog_title));
            create.setMessage(this.mResources.getString(R.string.face_re_enroll_dialog_content));
        } else if (biometricSourceType == BiometricSourceType.FINGERPRINT) {
            create.setTitle(this.mResources.getString(R.string.fingerprint_re_enroll_dialog_title));
            if (this.mFingerprintManager.getEnrolledFingerprints().size() == 1) {
                create.setMessage(this.mResources.getString(R.string.fingerprint_re_enroll_dialog_content_singular));
            } else {
                create.setMessage(this.mResources.getString(R.string.fingerprint_re_enroll_dialog_content));
            }
        }
        create.setPositiveButton(R.string.biometric_re_enroll_dialog_confirm, new DialogInterface.OnClickListener() { // from class: com.android.systemui.biometrics.BiometricNotificationDialogFactory$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                final BiometricNotificationDialogFactory biometricNotificationDialogFactory = BiometricNotificationDialogFactory.this;
                int i3 = i;
                BiometricSourceType biometricSourceType2 = biometricSourceType;
                final BiometricNotificationBroadcastReceiver$$ExternalSyntheticLambda0 biometricNotificationBroadcastReceiver$$ExternalSyntheticLambda02 = biometricNotificationBroadcastReceiver$$ExternalSyntheticLambda0;
                biometricNotificationDialogFactory.getClass();
                BiometricSourceType biometricSourceType3 = BiometricSourceType.FACE;
                if (biometricSourceType2 == biometricSourceType3) {
                    FaceManager faceManager = biometricNotificationDialogFactory.mFaceManager;
                    if (faceManager == null) {
                        Log.e("BiometricNotificationDialogFactory", "Not launching enrollment. Face manager was null!");
                        biometricNotificationDialogFactory.createReenrollFailureDialog(biometricSourceType3).show();
                        return;
                    } else if (faceManager.hasEnrolledTemplates(i3)) {
                        biometricNotificationDialogFactory.mFaceManager.removeAll(i3, new FaceManager.RemovalCallback() { // from class: com.android.systemui.biometrics.BiometricNotificationDialogFactory.2
                            public boolean mDidShowFailureDialog;

                            public final void onRemovalError(Face face, int i4, CharSequence charSequence) {
                                Log.e("BiometricNotificationDialogFactory", "Not launching enrollment.Failed to remove existing face(s).");
                                if (this.mDidShowFailureDialog) {
                                    return;
                                }
                                this.mDidShowFailureDialog = true;
                                BiometricNotificationDialogFactory.this.createReenrollFailureDialog(BiometricSourceType.FACE).show();
                            }

                            public final void onRemovalSucceeded(Face face, int i4) {
                                if (this.mDidShowFailureDialog || i4 != 0) {
                                    return;
                                }
                                Intent intent = new Intent("android.settings.FACE_ENROLL");
                                intent.setPackage("com.android.settings");
                                intent.setFlags(268435456);
                                biometricNotificationBroadcastReceiver$$ExternalSyntheticLambda02.startActivity(intent);
                            }
                        });
                        return;
                    } else {
                        biometricNotificationDialogFactory.createReenrollFailureDialog(biometricSourceType3).show();
                        return;
                    }
                }
                BiometricSourceType biometricSourceType4 = BiometricSourceType.FINGERPRINT;
                if (biometricSourceType2 == biometricSourceType4) {
                    FingerprintManager fingerprintManager = biometricNotificationDialogFactory.mFingerprintManager;
                    if (fingerprintManager == null) {
                        Log.e("BiometricNotificationDialogFactory", "Not launching enrollment. Fingerprint manager was null!");
                        biometricNotificationDialogFactory.createReenrollFailureDialog(biometricSourceType4).show();
                    } else if (fingerprintManager.hasEnrolledTemplates(i3)) {
                        biometricNotificationDialogFactory.mFingerprintManager.removeAll(i3, new FingerprintManager.RemovalCallback() { // from class: com.android.systemui.biometrics.BiometricNotificationDialogFactory.1
                            public boolean mDidShowFailureDialog;

                            public final void onRemovalError(Fingerprint fingerprint, int i4, CharSequence charSequence) {
                                Log.e("BiometricNotificationDialogFactory", "Not launching enrollment.Failed to remove existing face(s).");
                                if (this.mDidShowFailureDialog) {
                                    return;
                                }
                                this.mDidShowFailureDialog = true;
                                BiometricNotificationDialogFactory.this.createReenrollFailureDialog(BiometricSourceType.FINGERPRINT).show();
                            }

                            public final void onRemovalSucceeded(Fingerprint fingerprint, int i4) {
                                if (this.mDidShowFailureDialog || i4 != 0) {
                                    return;
                                }
                                Intent intent = new Intent("android.settings.FINGERPRINT_ENROLL");
                                intent.setPackage("com.android.settings");
                                intent.setFlags(268435456);
                                biometricNotificationBroadcastReceiver$$ExternalSyntheticLambda02.startActivity(intent);
                            }
                        });
                    } else {
                        biometricNotificationDialogFactory.createReenrollFailureDialog(biometricSourceType4).show();
                    }
                }
            }
        });
        if (!z) {
            create.setNegativeButton$1(R.string.biometric_re_enroll_dialog_cancel, new BiometricNotificationDialogFactory$$ExternalSyntheticLambda1());
        }
        create.setCanceledOnTouchOutside(!z);
        return create;
    }

    public final SystemUIDialog createReenrollFailureDialog(BiometricSourceType biometricSourceType) {
        SystemUIDialog create = this.mSystemUIDialogFactory.create();
        if (biometricSourceType == BiometricSourceType.FACE) {
            create.setMessage(this.mResources.getString(R.string.face_reenroll_failure_dialog_content));
        } else if (biometricSourceType == BiometricSourceType.FINGERPRINT) {
            create.setMessage(this.mResources.getString(R.string.fingerprint_reenroll_failure_dialog_content));
        }
        create.setPositiveButton(R.string.ok, new BiometricNotificationDialogFactory$$ExternalSyntheticLambda1());
        return create;
    }
}
