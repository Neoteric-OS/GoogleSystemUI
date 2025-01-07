package androidx.compose.foundation;

import android.view.KeyEvent;
import android.view.ViewConfiguration;
import androidx.compose.ui.input.key.KeyEvent_androidKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Clickable_androidKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long TapIndicationDelay = ViewConfiguration.getTapTimeout();

    /* renamed from: isEnter-ZmokQxo, reason: not valid java name */
    public static final boolean m36isEnterZmokQxo(KeyEvent keyEvent) {
        int m448getKeyZmokQxo = (int) (KeyEvent_androidKt.m448getKeyZmokQxo(keyEvent) >> 32);
        return m448getKeyZmokQxo == 23 || m448getKeyZmokQxo == 62 || m448getKeyZmokQxo == 66 || m448getKeyZmokQxo == 160;
    }
}
