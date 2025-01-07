package kotlin.enums;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class EnumEntriesKt {
    public static final EnumEntries enumEntries(Enum[] enumArr) {
        return new EnumEntriesList(enumArr);
    }
}
