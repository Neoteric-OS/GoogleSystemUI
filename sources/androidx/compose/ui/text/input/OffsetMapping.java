package androidx.compose.ui.text.input;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface OffsetMapping {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = null;
        public static final OffsetMapping$Companion$Identity$1 Identity = new OffsetMapping$Companion$Identity$1();
    }

    int originalToTransformed(int i);

    int transformedToOriginal(int i);
}
