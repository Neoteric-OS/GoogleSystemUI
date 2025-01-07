package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Latch {
    public final Object lock = new Object();
    public List awaiters = new ArrayList();
    public List spareList = new ArrayList();
    public boolean _isOpen = true;
}
