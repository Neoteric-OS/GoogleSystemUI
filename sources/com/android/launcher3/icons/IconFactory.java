package com.android.launcher3.icons;

import android.content.Context;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IconFactory extends BaseIconFactory {
    public static IconFactory sPool;
    public static final Object sPoolSync = new Object();
    public IconFactory next;

    public static IconFactory obtain(Context context) {
        synchronized (sPoolSync) {
            try {
                IconFactory iconFactory = sPool;
                if (iconFactory == null) {
                    return new IconFactory(context, context.getResources().getConfiguration().densityDpi, context.getResources().getDimensionPixelSize(R.dimen.default_icon_bitmap_size), false);
                }
                sPool = iconFactory.next;
                iconFactory.next = null;
                return iconFactory;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.launcher3.icons.BaseIconFactory, java.lang.AutoCloseable
    public final void close() {
        synchronized (sPoolSync) {
            this.mWrapperBackgroundColor = -1;
            this.next = sPool;
            sPool = this;
        }
    }
}
