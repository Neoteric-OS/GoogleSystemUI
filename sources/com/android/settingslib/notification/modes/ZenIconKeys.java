package com.android.settingslib.notification.modes;

import android.R;
import com.android.settingslib.notification.modes.ZenIcon;
import com.google.common.collect.ImmutableMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ZenIconKeys {
    public static final ZenIcon.Key MANUAL_DND = ZenIcon.Key.forSystemResource(R.drawable.jog_dial_arrow_short_left);
    public static final ZenIcon.Key IMPLICIT_MODE_DEFAULT = ZenIcon.Key.forSystemResource(R.drawable.jog_dial_arrow_short_left);
    public static final ImmutableMap TYPE_DEFAULTS = ImmutableMap.of(ZenIcon.Key.forSystemResource(R.drawable.jog_dial_arrow_short_right), ZenIcon.Key.forSystemResource(R.drawable.jog_dial_arrow_long_middle_yellow), ZenIcon.Key.forSystemResource(R.drawable.jog_dial_arrow_long_right_yellow), ZenIcon.Key.forSystemResource(R.drawable.jog_dial_arrow_long_right_red), ZenIcon.Key.forSystemResource(R.drawable.item_background_material_dark), ZenIcon.Key.forSystemResource(R.drawable.item_background_material_light), ZenIcon.Key.forSystemResource(R.drawable.jog_dial_arrow_long_left_green), ZenIcon.Key.forSystemResource(R.drawable.jog_dial_arrow_short_left_and_right), ZenIcon.Key.forSystemResource(R.drawable.jog_dial_arrow_long_left_yellow));
    public static final ZenIcon.Key FOR_UNEXPECTED_TYPE = ZenIcon.Key.forSystemResource(R.drawable.jog_dial_arrow_short_right);
}
