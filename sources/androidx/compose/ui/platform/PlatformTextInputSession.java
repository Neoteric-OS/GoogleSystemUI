package androidx.compose.ui.platform;

import android.view.View;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface PlatformTextInputSession {
    View getView();

    void startInputMethod(PlatformTextInputMethodRequest platformTextInputMethodRequest, ContinuationImpl continuationImpl);
}
