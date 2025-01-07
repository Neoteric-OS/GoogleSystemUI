package com.android.systemui.brightness.ui.viewmodel;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.brightness.shared.model.GammaBrightness;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface Drag {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Dragging implements Drag {
        public final int brightness;

        public final boolean equals(Object obj) {
            if (obj instanceof Dragging) {
                return this.brightness == ((Dragging) obj).brightness;
            }
            return false;
        }

        public final int hashCode() {
            return Integer.hashCode(this.brightness);
        }

        public final String toString() {
            return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Dragging(brightness=", GammaBrightness.m789toStringimpl(this.brightness), ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Stopped implements Drag {
        public final int brightness;

        public final boolean equals(Object obj) {
            if (obj instanceof Stopped) {
                return this.brightness == ((Stopped) obj).brightness;
            }
            return false;
        }

        public final int hashCode() {
            return Integer.hashCode(this.brightness);
        }

        public final String toString() {
            return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Stopped(brightness=", GammaBrightness.m789toStringimpl(this.brightness), ")");
        }
    }
}
