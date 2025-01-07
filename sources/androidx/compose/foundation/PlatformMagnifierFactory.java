package androidx.compose.foundation;

import android.view.View;
import androidx.compose.ui.unit.Density;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface PlatformMagnifierFactory {
    /* renamed from: create-nHHXs2Y, reason: not valid java name */
    PlatformMagnifier mo40createnHHXs2Y(View view, Density density);

    boolean getCanUpdateZoom();
}
