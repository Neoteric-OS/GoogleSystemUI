package com.android.systemui.common.ui.binder;

import android.widget.ImageView;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IconViewBinder {
    public static final IconViewBinder INSTANCE = null;

    public static void bind(Icon icon, ImageView imageView) {
        String string;
        ContentDescription contentDescription = icon.getContentDescription();
        if (contentDescription == null) {
            string = null;
        } else if (contentDescription instanceof ContentDescription.Loaded) {
            string = ((ContentDescription.Loaded) contentDescription).description;
        } else {
            if (!(contentDescription instanceof ContentDescription.Resource)) {
                throw new NoWhenBranchMatchedException();
            }
            string = imageView.getContext().getResources().getString(((ContentDescription.Resource) contentDescription).res);
        }
        imageView.setContentDescription(string);
        if (icon instanceof Icon.Loaded) {
            imageView.setImageDrawable(((Icon.Loaded) icon).drawable);
        } else if (icon instanceof Icon.Resource) {
            imageView.setImageResource(((Icon.Resource) icon).res);
        }
    }
}
