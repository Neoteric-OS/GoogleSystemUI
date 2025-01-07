package androidx.compose.ui.text;

import androidx.compose.runtime.OpaqueKey$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.AnnotatedString;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LinkAnnotation implements AnnotatedString.Annotation {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Clickable extends LinkAnnotation {
        public final TextLinkStyles styles;
        public final String tag;

        public Clickable(String str, TextLinkStyles textLinkStyles) {
            this.tag = str;
            this.styles = textLinkStyles;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Clickable)) {
                return false;
            }
            Clickable clickable = (Clickable) obj;
            if (!Intrinsics.areEqual(this.tag, clickable.tag)) {
                return false;
            }
            if (!Intrinsics.areEqual(this.styles, clickable.styles)) {
                return false;
            }
            clickable.getClass();
            return Intrinsics.areEqual((Object) null, (Object) null);
        }

        @Override // androidx.compose.ui.text.LinkAnnotation
        public final TextLinkStyles getStyles() {
            return this.styles;
        }

        public final int hashCode() {
            int hashCode = this.tag.hashCode() * 31;
            TextLinkStyles textLinkStyles = this.styles;
            return (hashCode + (textLinkStyles != null ? textLinkStyles.hashCode() : 0)) * 31;
        }

        public final String toString() {
            return OpaqueKey$$ExternalSyntheticOutline0.m(new StringBuilder("LinkAnnotation.Clickable(tag="), this.tag, ')');
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Url extends LinkAnnotation {
        public final TextLinkStyles styles;
        public final String url;

        public Url(String str, TextLinkStyles textLinkStyles) {
            this.url = str;
            this.styles = textLinkStyles;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Url)) {
                return false;
            }
            Url url = (Url) obj;
            if (!Intrinsics.areEqual(this.url, url.url)) {
                return false;
            }
            if (!Intrinsics.areEqual(this.styles, url.styles)) {
                return false;
            }
            url.getClass();
            return Intrinsics.areEqual((Object) null, (Object) null);
        }

        @Override // androidx.compose.ui.text.LinkAnnotation
        public final TextLinkStyles getStyles() {
            return this.styles;
        }

        public final int hashCode() {
            int hashCode = this.url.hashCode() * 31;
            TextLinkStyles textLinkStyles = this.styles;
            return (hashCode + (textLinkStyles != null ? textLinkStyles.hashCode() : 0)) * 31;
        }

        public final String toString() {
            return OpaqueKey$$ExternalSyntheticOutline0.m(new StringBuilder("LinkAnnotation.Url(url="), this.url, ')');
        }
    }

    public abstract TextLinkStyles getStyles();
}
