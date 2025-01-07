package androidx.window.core;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VerificationMode {
    public static final /* synthetic */ VerificationMode[] $VALUES;
    public static final VerificationMode QUIET = null;
    public static final VerificationMode STRICT;

    static {
        VerificationMode verificationMode = new VerificationMode("STRICT", 0);
        STRICT = verificationMode;
        $VALUES = new VerificationMode[]{verificationMode, new VerificationMode("LOG", 1), new VerificationMode("QUIET", 2)};
    }

    public static VerificationMode valueOf(String str) {
        return (VerificationMode) Enum.valueOf(VerificationMode.class, str);
    }

    public static VerificationMode[] values() {
        return (VerificationMode[]) $VALUES.clone();
    }
}
