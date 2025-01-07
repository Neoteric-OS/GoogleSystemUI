package androidx.appcompat.content.res;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.ResourceManagerInternal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AppCompatResources {
    public static Drawable getDrawable(int i, Context context) {
        return ResourceManagerInternal.get().getDrawable(i, context);
    }
}
