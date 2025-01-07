package com.android.systemui.temporarydisplay.chipbar;

import com.android.systemui.common.shared.model.Text;
import com.android.systemui.media.taptotransfer.sender.MediaTttSenderCoordinator$getUndoButton$onClickListener$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ChipbarEndItem {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Button extends ChipbarEndItem {
        public final MediaTttSenderCoordinator$getUndoButton$onClickListener$1 onClickListener;
        public final Text.Resource text;

        public Button(Text.Resource resource, MediaTttSenderCoordinator$getUndoButton$onClickListener$1 mediaTttSenderCoordinator$getUndoButton$onClickListener$1) {
            this.text = resource;
            this.onClickListener = mediaTttSenderCoordinator$getUndoButton$onClickListener$1;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Button)) {
                return false;
            }
            Button button = (Button) obj;
            return this.text.equals(button.text) && this.onClickListener.equals(button.onClickListener);
        }

        public final int hashCode() {
            return this.onClickListener.hashCode() + (Integer.hashCode(this.text.res) * 31);
        }

        public final String toString() {
            return "Button(text=" + this.text + ", onClickListener=" + this.onClickListener + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Error extends ChipbarEndItem {
        public static final Error INSTANCE = new Error();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Loading extends ChipbarEndItem {
        public static final Loading INSTANCE = new Loading();
    }
}
