package androidx.compose.ui.text.font;

import androidx.compose.runtime.State;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface TypefaceResult extends State {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Async implements TypefaceResult, State {
        public final AsyncFontListLoader current;

        public Async(AsyncFontListLoader asyncFontListLoader) {
            this.current = asyncFontListLoader;
        }

        @Override // androidx.compose.ui.text.font.TypefaceResult
        public final boolean getCacheable() {
            return this.current.cacheable;
        }

        @Override // androidx.compose.runtime.State
        public final Object getValue() {
            return this.current.getValue();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Immutable implements TypefaceResult {
        public final boolean cacheable;
        public final Object value;

        public Immutable(Object obj, boolean z) {
            this.value = obj;
            this.cacheable = z;
        }

        @Override // androidx.compose.ui.text.font.TypefaceResult
        public final boolean getCacheable() {
            return this.cacheable;
        }

        @Override // androidx.compose.runtime.State
        public final Object getValue() {
            return this.value;
        }
    }

    boolean getCacheable();
}
