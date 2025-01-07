package com.android.systemui.biometrics.domain.interactor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract /* synthetic */ class CredentialInteractorKt$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static {
        int[] iArr = new int[UserType.values().length];
        try {
            UserType userType = UserType.PRIMARY;
            iArr[0] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            UserType userType2 = UserType.PRIMARY;
            iArr[1] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            UserType userType3 = UserType.PRIMARY;
            iArr[2] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        $EnumSwitchMapping$0 = iArr;
    }
}
