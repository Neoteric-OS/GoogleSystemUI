package androidx.compose.foundation;

import android.widget.Magnifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PlatformMagnifierFactoryApi28Impl$PlatformMagnifierImpl implements PlatformMagnifier {
    public final Magnifier magnifier;

    public PlatformMagnifierFactoryApi28Impl$PlatformMagnifierImpl(Magnifier magnifier) {
        this.magnifier = magnifier;
    }

    /* renamed from: getSize-YbymL2g, reason: not valid java name */
    public final long m41getSizeYbymL2g() {
        return (this.magnifier.getWidth() << 32) | (this.magnifier.getHeight() & 4294967295L);
    }
}
