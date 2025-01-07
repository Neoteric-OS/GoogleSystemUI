package androidx.compose.ui.platform.actionmodecallback;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public enum MenuItemOption {
    Copy(0),
    Paste(1),
    Cut(2),
    SelectAll(3);

    private final int id;
    private final int order;

    MenuItemOption(int i) {
        this.id = i;
        this.order = i;
    }

    public final int getId() {
        return this.id;
    }

    public final int getOrder() {
        return this.order;
    }
}
