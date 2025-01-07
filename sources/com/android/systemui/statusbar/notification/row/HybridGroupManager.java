package com.android.systemui.statusbar.notification.row;

import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HybridGroupManager {
    public final Context mContext;
    public int mOverflowNumberColor;
    public int mOverflowNumberPadding;
    public float mOverflowNumberSize;

    public HybridGroupManager(Context context) {
        this.mContext = context;
        Resources resources = context.getResources();
        this.mOverflowNumberSize = resources.getDimensionPixelSize(R.dimen.group_overflow_number_size);
        this.mOverflowNumberPadding = resources.getDimensionPixelSize(R.dimen.group_overflow_number_padding);
    }

    public static CharSequence resolveText(Notification notification) {
        CharSequence charSequence = notification.extras.getCharSequence("android.text");
        return charSequence == null ? notification.extras.getCharSequence("android.bigText") : charSequence;
    }

    public static CharSequence resolveTitle(Notification notification) {
        CharSequence charSequence = notification.extras.getCharSequence("android.title");
        return charSequence == null ? notification.extras.getCharSequence("android.title.big") : charSequence;
    }
}
