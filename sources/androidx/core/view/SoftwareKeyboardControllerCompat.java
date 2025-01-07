package androidx.core.view;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SoftwareKeyboardControllerCompat {
    public final Impl30 mImpl;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Impl30 {
        public View mView;
        public final View mView$1;

        public Impl30(View view) {
            this.mView$1 = view;
        }
    }

    public SoftwareKeyboardControllerCompat(View view) {
        Impl30 impl30 = new Impl30(view);
        impl30.mView = view;
        this.mImpl = impl30;
    }
}
