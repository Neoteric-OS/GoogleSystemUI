package com.android.systemui.navigationbar.views.buttons;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContextualButtonGroup extends ButtonDispatcher {
    public final List mButtonData;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ButtonData {
        public ContextualButton button;
        public boolean markedVisible;
    }

    public ContextualButtonGroup() {
        super(R.id.menu_container);
        this.mButtonData = new ArrayList();
    }

    public final void setButtonVisibility(int i, boolean z) {
        int i2 = 0;
        while (true) {
            if (i2 >= ((ArrayList) this.mButtonData).size()) {
                i2 = -1;
                break;
            } else if (((ButtonData) ((ArrayList) this.mButtonData).get(i2)).button.mId == i) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 == -1) {
            throw new RuntimeException(GenericDocument$$ExternalSyntheticOutline0.m("Cannot find the button id of ", " in context group", i));
        }
        setVisibility(4);
        ((ButtonData) ((ArrayList) this.mButtonData).get(i2)).markedVisible = z;
        boolean z2 = false;
        for (int size = ((ArrayList) this.mButtonData).size() - 1; size >= 0; size--) {
            ButtonData buttonData = (ButtonData) ((ArrayList) this.mButtonData).get(size);
            if (z2 || !buttonData.markedVisible) {
                buttonData.button.setVisibility(4);
            } else {
                buttonData.button.setVisibility(0);
                setVisibility(0);
                z2 = true;
            }
        }
        ((ButtonData) ((ArrayList) this.mButtonData).get(i2)).button.getVisibility();
    }
}
