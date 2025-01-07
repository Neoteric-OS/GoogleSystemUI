package com.android.systemui.media.taptotransfer.sender;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SenderEndItem {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Error extends SenderEndItem {
        public static final Error INSTANCE = new Error();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Loading extends SenderEndItem {
        public static final Loading INSTANCE = new Loading();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UndoButton extends SenderEndItem {
        public final int newState;
        public final MediaTttSenderUiEvents uiEventOnClick;

        public UndoButton(MediaTttSenderUiEvents mediaTttSenderUiEvents, int i) {
            this.uiEventOnClick = mediaTttSenderUiEvents;
            this.newState = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UndoButton)) {
                return false;
            }
            UndoButton undoButton = (UndoButton) obj;
            return this.uiEventOnClick.equals(undoButton.uiEventOnClick) && this.newState == undoButton.newState;
        }

        public final int hashCode() {
            return Integer.hashCode(this.newState) + (this.uiEventOnClick.hashCode() * 31);
        }

        public final String toString() {
            MediaTttSenderUiEvents mediaTttSenderUiEvents = this.uiEventOnClick;
            StringBuilder sb = new StringBuilder("UndoButton(uiEventOnClick=");
            sb.append(mediaTttSenderUiEvents);
            sb.append(", newState=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.newState, ")");
        }
    }
}
