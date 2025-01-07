package androidx.compose.foundation.text;

import android.view.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextFieldFocusModifier_androidKt {
    /* renamed from: access$isKeyCode-YhN2O0w, reason: not valid java name */
    public static final boolean m163access$isKeyCodeYhN2O0w(int i, KeyEvent keyEvent) {
        return ((int) (KeyEvent_androidKt.m448getKeyZmokQxo(keyEvent) >> 32)) == i;
    }
}
