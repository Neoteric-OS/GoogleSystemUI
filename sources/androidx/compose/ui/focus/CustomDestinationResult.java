package androidx.compose.ui.focus;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CustomDestinationResult {
    public static final /* synthetic */ CustomDestinationResult[] $VALUES;
    public static final CustomDestinationResult Cancelled;
    public static final CustomDestinationResult None;
    public static final CustomDestinationResult RedirectCancelled;
    public static final CustomDestinationResult Redirected;

    static {
        CustomDestinationResult customDestinationResult = new CustomDestinationResult("None", 0);
        None = customDestinationResult;
        CustomDestinationResult customDestinationResult2 = new CustomDestinationResult("Cancelled", 1);
        Cancelled = customDestinationResult2;
        CustomDestinationResult customDestinationResult3 = new CustomDestinationResult("Redirected", 2);
        Redirected = customDestinationResult3;
        CustomDestinationResult customDestinationResult4 = new CustomDestinationResult("RedirectCancelled", 3);
        RedirectCancelled = customDestinationResult4;
        $VALUES = new CustomDestinationResult[]{customDestinationResult, customDestinationResult2, customDestinationResult3, customDestinationResult4};
    }

    public static CustomDestinationResult valueOf(String str) {
        return (CustomDestinationResult) Enum.valueOf(CustomDestinationResult.class, str);
    }

    public static CustomDestinationResult[] values() {
        return (CustomDestinationResult[]) $VALUES.clone();
    }
}
