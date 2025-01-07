package com.android.wm.shell;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class FakeFeatureFlagsImpl extends CustomFeatureFlags {
    public final Map mFlagMap;

    public FakeFeatureFlagsImpl() {
        new HashSet(Arrays.asList("com.android.wm.shell.bubble_view_info_executors", "com.android.wm.shell.enable_app_pairs", "com.android.wm.shell.enable_bubble_anything", "com.android.wm.shell.enable_bubble_bar", "com.android.wm.shell.enable_bubble_bar_in_persistent_task_bar", "com.android.wm.shell.enable_bubble_stashing", "com.android.wm.shell.enable_bubble_to_fullscreen", "com.android.wm.shell.enable_bubbles_long_press_nav_handle", "com.android.wm.shell.enable_flexible_split", "com.android.wm.shell.enable_left_right_split_in_portrait", "com.android.wm.shell.enable_new_bubble_animations", "com.android.wm.shell.enable_optional_bubble_overflow", "com.android.wm.shell.enable_pip2", "com.android.wm.shell.enable_pip_umo_experience", "com.android.wm.shell.enable_retrievable_bubbles", "com.android.wm.shell.enable_split_contextual", "com.android.wm.shell.enable_taskbar_navbar_unification", "com.android.wm.shell.enable_taskbar_on_phones", "com.android.wm.shell.enable_tiny_taskbar", "com.android.wm.shell.only_reuse_bubbled_task_when_launched_from_bubble", ""));
        this.mFlagMap = new HashMap();
        Iterator it = Arrays.asList("com.android.wm.shell.bubble_view_info_executors", "com.android.wm.shell.enable_app_pairs", "com.android.wm.shell.enable_bubble_anything", "com.android.wm.shell.enable_bubble_bar", "com.android.wm.shell.enable_bubble_bar_in_persistent_task_bar", "com.android.wm.shell.enable_bubble_stashing", "com.android.wm.shell.enable_bubble_to_fullscreen", "com.android.wm.shell.enable_bubbles_long_press_nav_handle", "com.android.wm.shell.enable_flexible_split", "com.android.wm.shell.enable_left_right_split_in_portrait", "com.android.wm.shell.enable_new_bubble_animations", "com.android.wm.shell.enable_optional_bubble_overflow", "com.android.wm.shell.enable_pip2", "com.android.wm.shell.enable_pip_umo_experience", "com.android.wm.shell.enable_retrievable_bubbles", "com.android.wm.shell.enable_split_contextual", "com.android.wm.shell.enable_taskbar_navbar_unification", "com.android.wm.shell.enable_taskbar_on_phones", "com.android.wm.shell.enable_tiny_taskbar", "com.android.wm.shell.only_reuse_bubbled_task_when_launched_from_bubble").iterator();
        while (it.hasNext()) {
            this.mFlagMap.put((String) it.next(), null);
        }
    }
}
