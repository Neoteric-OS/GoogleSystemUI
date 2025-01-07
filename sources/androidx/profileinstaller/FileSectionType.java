package androidx.profileinstaller;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
enum FileSectionType {
    DEX_FILES("DEX_FILES"),
    /* JADX INFO: Fake field, exist only in values array */
    EF1("EXTRA_DESCRIPTORS"),
    CLASSES("CLASSES"),
    METHODS("METHODS"),
    /* JADX INFO: Fake field, exist only in values array */
    EF53("AGGREGATION_COUNT");

    private final long mValue;

    FileSectionType(String str) {
        this.mValue = r2;
    }

    public final long getValue() {
        return this.mValue;
    }
}
