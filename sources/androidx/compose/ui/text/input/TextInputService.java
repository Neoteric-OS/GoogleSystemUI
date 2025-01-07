package androidx.compose.ui.text.input;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.Deprecated;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
@Deprecated
/* loaded from: classes.dex */
public class TextInputService {
    public final AtomicReference _currentInputSession = new AtomicReference(null);
    public final PlatformTextInputService platformTextInputService;

    public TextInputService(PlatformTextInputService platformTextInputService) {
        this.platformTextInputService = platformTextInputService;
    }
}
