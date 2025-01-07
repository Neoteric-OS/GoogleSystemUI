package com.android.systemui.media.controls.ui.view;

import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.systemui.monet.ColorScheme;
import com.android.wm.shell.R;
import java.util.Set;
import kotlin.collections.SetsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GutsViewHolder {
    public static final Set ids = SetsKt.setOf(Integer.valueOf(R.id.remove_text), Integer.valueOf(R.id.cancel), Integer.valueOf(R.id.dismiss), Integer.valueOf(R.id.settings));
    public final View cancel;
    public final TextView cancelText;
    public ColorScheme colorScheme;
    public final ViewGroup dismiss;
    public final TextView dismissText;
    public final TextView gutsText;
    public boolean isDismissible = true;
    public final ImageButton settings;

    public GutsViewHolder(View view) {
        this.gutsText = (TextView) view.requireViewById(R.id.remove_text);
        this.cancel = view.requireViewById(R.id.cancel);
        this.cancelText = (TextView) view.requireViewById(R.id.cancel_text);
        this.dismiss = (ViewGroup) view.requireViewById(R.id.dismiss);
        this.dismissText = (TextView) view.requireViewById(R.id.dismiss_text);
        this.settings = (ImageButton) view.requireViewById(R.id.settings);
    }

    public final void setColors(ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
        int s800 = colorScheme.mAccent2.getS800();
        this.dismissText.setTextColor(s800);
        if (!this.isDismissible) {
            this.cancelText.setTextColor(s800);
        }
        ColorStateList valueOf = ColorStateList.valueOf(colorScheme.mNeutral1.getS50());
        this.gutsText.setTextColor(valueOf);
        if (this.isDismissible) {
            this.cancelText.setTextColor(valueOf);
        }
        ColorStateList valueOf2 = ColorStateList.valueOf(colorScheme.mAccent1.getS100());
        this.settings.setImageTintList(valueOf2);
        this.cancelText.setBackgroundTintList(valueOf2);
        this.dismissText.setBackgroundTintList(valueOf2);
    }
}
