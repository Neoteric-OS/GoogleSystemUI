package com.android.systemui.biometrics.domain.model;

import android.content.ComponentName;
import android.graphics.Bitmap;
import android.hardware.biometrics.PromptContentView;
import android.hardware.biometrics.PromptInfo;
import com.android.systemui.biometrics.shared.model.BiometricModalities;
import com.android.systemui.biometrics.shared.model.BiometricUserInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BiometricPromptRequest {
    public final PromptContentView contentView;
    public final String description;
    public final BiometricOperationInfo operationInfo;
    public final boolean showEmergencyCallButton;
    public final String subtitle;
    public final String title;
    public final BiometricUserInfo userInfo;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Biometric extends BiometricPromptRequest {
        public final boolean allowBackgroundAuthentication;
        public final ComponentName componentNameForConfirmDeviceCredentialActivity;
        public final Bitmap logoBitmap;
        public final String logoDescription;
        public final BiometricModalities modalities;
        public final String negativeButtonText;
        public final String opPackageName;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public Biometric(android.hardware.biometrics.PromptInfo r11, com.android.systemui.biometrics.shared.model.BiometricUserInfo r12, com.android.systemui.biometrics.domain.model.BiometricOperationInfo r13, com.android.systemui.biometrics.shared.model.BiometricModalities r14, java.lang.String r15) {
            /*
                r10 = this;
                java.lang.CharSequence r0 = r11.getTitle()
                java.lang.String r1 = ""
                if (r0 == 0) goto L11
                java.lang.String r0 = r0.toString()
                if (r0 != 0) goto Lf
                goto L11
            Lf:
                r3 = r0
                goto L12
            L11:
                r3 = r1
            L12:
                java.lang.CharSequence r0 = r11.getSubtitle()
                if (r0 == 0) goto L21
                java.lang.String r0 = r0.toString()
                if (r0 != 0) goto L1f
                goto L21
            L1f:
                r4 = r0
                goto L22
            L21:
                r4 = r1
            L22:
                java.lang.CharSequence r0 = r11.getDescription()
                if (r0 == 0) goto L31
                java.lang.String r0 = r0.toString()
                if (r0 != 0) goto L2f
                goto L31
            L2f:
                r5 = r0
                goto L32
            L31:
                r5 = r1
            L32:
                android.hardware.biometrics.PromptContentView r6 = r11.getContentView()
                boolean r9 = r11.isShowEmergencyCallButton()
                r2 = r10
                r7 = r12
                r8 = r13
                r2.<init>(r3, r4, r5, r6, r7, r8, r9)
                r10.modalities = r14
                r10.opPackageName = r15
                android.graphics.Bitmap r12 = r11.getLogo()
                r10.logoBitmap = r12
                java.lang.String r12 = r11.getLogoDescription()
                r10.logoDescription = r12
                java.lang.CharSequence r12 = r11.getNegativeButtonText()
                if (r12 == 0) goto L5e
                java.lang.String r12 = r12.toString()
                if (r12 != 0) goto L5d
                goto L5e
            L5d:
                r1 = r12
            L5e:
                r10.negativeButtonText = r1
                android.content.ComponentName r12 = r11.getRealCallerForConfirmDeviceCredentialActivity()
                r10.componentNameForConfirmDeviceCredentialActivity = r12
                boolean r11 = r11.isAllowBackgroundAuthentication()
                r10.allowBackgroundAuthentication = r11
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.domain.model.BiometricPromptRequest.Biometric.<init>(android.hardware.biometrics.PromptInfo, com.android.systemui.biometrics.shared.model.BiometricUserInfo, com.android.systemui.biometrics.domain.model.BiometricOperationInfo, com.android.systemui.biometrics.shared.model.BiometricModalities, java.lang.String):void");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Credential extends BiometricPromptRequest {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Password extends Credential {
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Pattern extends Credential {
            public final boolean stealthMode;

            public Pattern(PromptInfo promptInfo, BiometricUserInfo biometricUserInfo, BiometricOperationInfo biometricOperationInfo, boolean z) {
                super(promptInfo, biometricUserInfo, biometricOperationInfo);
                this.stealthMode = z;
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Pin extends Credential {
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public Credential(android.hardware.biometrics.PromptInfo r11, com.android.systemui.biometrics.shared.model.BiometricUserInfo r12, com.android.systemui.biometrics.domain.model.BiometricOperationInfo r13) {
            /*
                r10 = this;
                java.lang.CharSequence r0 = r11.getDeviceCredentialTitle()
                if (r0 != 0) goto La
                java.lang.CharSequence r0 = r11.getTitle()
            La:
                java.lang.String r1 = ""
                if (r0 == 0) goto L17
                java.lang.String r0 = r0.toString()
                if (r0 != 0) goto L15
                goto L17
            L15:
                r3 = r0
                goto L18
            L17:
                r3 = r1
            L18:
                java.lang.CharSequence r0 = r11.getDeviceCredentialSubtitle()
                if (r0 != 0) goto L22
                java.lang.CharSequence r0 = r11.getSubtitle()
            L22:
                if (r0 == 0) goto L2d
                java.lang.String r0 = r0.toString()
                if (r0 != 0) goto L2b
                goto L2d
            L2b:
                r4 = r0
                goto L2e
            L2d:
                r4 = r1
            L2e:
                java.lang.CharSequence r0 = r11.getDeviceCredentialDescription()
                if (r0 != 0) goto L38
                java.lang.CharSequence r0 = r11.getDescription()
            L38:
                if (r0 == 0) goto L43
                java.lang.String r0 = r0.toString()
                if (r0 != 0) goto L41
                goto L43
            L41:
                r5 = r0
                goto L44
            L43:
                r5 = r1
            L44:
                android.hardware.biometrics.PromptContentView r6 = r11.getContentView()
                boolean r9 = r11.isShowEmergencyCallButton()
                r2 = r10
                r7 = r12
                r8 = r13
                r2.<init>(r3, r4, r5, r6, r7, r8, r9)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.domain.model.BiometricPromptRequest.Credential.<init>(android.hardware.biometrics.PromptInfo, com.android.systemui.biometrics.shared.model.BiometricUserInfo, com.android.systemui.biometrics.domain.model.BiometricOperationInfo):void");
        }
    }

    public BiometricPromptRequest(String str, String str2, String str3, PromptContentView promptContentView, BiometricUserInfo biometricUserInfo, BiometricOperationInfo biometricOperationInfo, boolean z) {
        this.title = str;
        this.subtitle = str2;
        this.description = str3;
        this.contentView = promptContentView;
        this.userInfo = biometricUserInfo;
        this.operationInfo = biometricOperationInfo;
        this.showEmergencyCallButton = z;
    }
}
