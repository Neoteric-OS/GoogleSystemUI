package androidx.compose.ui.text.font;

import androidx.collection.MutableScatterMap;
import androidx.collection.SieveCache;
import androidx.compose.ui.text.platform.SynchronizedObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AsyncTypefaceCache {
    public final SieveCache resultCache = new SieveCache();
    public final MutableScatterMap permanentCache = new MutableScatterMap();
    public final SynchronizedObject cacheLock = new SynchronizedObject();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AsyncTypefaceResult {
        public final Object result;

        public final boolean equals(Object obj) {
            if (obj instanceof AsyncTypefaceResult) {
                return Intrinsics.areEqual(this.result, ((AsyncTypefaceResult) obj).result);
            }
            return false;
        }

        public final int hashCode() {
            Object obj = this.result;
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        public final String toString() {
            return "AsyncTypefaceResult(result=" + this.result + ')';
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Key {
        public final Font font;

        public Key(Font font) {
            this.font = font;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Key) {
                return Intrinsics.areEqual(this.font, ((Key) obj).font) && Intrinsics.areEqual((Object) null, (Object) null);
            }
            return false;
        }

        public final int hashCode() {
            return this.font.hashCode() * 31;
        }

        public final String toString() {
            return "Key(font=" + this.font + ", loaderKey=null)";
        }
    }
}
