package com.android.systemui.statusbar;

import android.app.AppGlobals;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.hardware.input.InputManagerGlobal;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyboardShortcutGroup;
import android.view.KeyboardShortcutInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.internal.logging.MetricsLogger;
import com.android.settingslib.Utils;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.wm.shell.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyboardShortcutListSearch {
    public static KeyboardShortcutListSearch sInstance;
    public static final Object sLock = new Object();
    public boolean mAppShortcutsReceived;
    Handler mBackgroundHandler;
    public KeyCharacterMap mBackupKeyCharacterMap;
    public Button mButtonInput;
    public Button mButtonOpenApps;
    public Button mButtonSpecificApp;
    public Button mButtonSystem;
    public Context mContext;
    public CharSequence mCurrentAppPackageName;
    public int mCurrentCategoryIndex = 0;
    public ImageButton mEditTextCancel;
    public final ArrayList mFullButtonList;
    public final List mFullShortsGroup;
    public final Handler mHandler;
    public final HandlerThread mHandlerThread;
    public boolean mImeShortcutsReceived;
    public final List mInputGroup;
    public KeyCharacterMap mKeyCharacterMap;
    public final Map mKeySearchResultMap;
    BottomSheetDialog mKeyboardShortcutsBottomSheetDialog;
    public final SparseArray mModifierDrawables;
    public final int[] mModifierList;
    public final SparseArray mModifierNames;
    public TextView mNoSearchResults;
    public final List mOpenAppsGroup;
    public String mQueryString;
    public EditText mSearchEditText;
    public final SparseArray mSpecialCharacterNames;
    public final List mSpecificAppGroup;
    public final List mSystemGroup;
    public final WindowManager mWindowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.KeyboardShortcutListSearch$1, reason: invalid class name */
    public final class AnonymousClass1 implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            ShortcutMultiMappingInfo shortcutMultiMappingInfo = (ShortcutMultiMappingInfo) obj;
            ShortcutMultiMappingInfo shortcutMultiMappingInfo2 = (ShortcutMultiMappingInfo) obj2;
            CharSequence charSequence = shortcutMultiMappingInfo.mLabel;
            boolean z = charSequence == null || charSequence.toString().isEmpty();
            CharSequence charSequence2 = shortcutMultiMappingInfo2.mLabel;
            boolean z2 = charSequence2 == null || charSequence2.toString().isEmpty();
            if (z && z2) {
                return 0;
            }
            if (z) {
                return 1;
            }
            if (z2) {
                return -1;
            }
            return shortcutMultiMappingInfo.mLabel.toString().compareToIgnoreCase(shortcutMultiMappingInfo2.mLabel.toString());
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class KeyboardShortcutMultiMappingGroup {
        public final CharSequence mCategory;
        public final List mItems;

        public KeyboardShortcutMultiMappingGroup(CharSequence charSequence, List list) {
            this.mCategory = charSequence;
            this.mItems = list;
        }

        public final void addItem(ShortcutMultiMappingInfo shortcutMultiMappingInfo) {
            this.mItems.add(shortcutMultiMappingInfo);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShortcutKeyAccessibilityDelegate extends View.AccessibilityDelegate {
        public final String mContentDescription;

        public ShortcutKeyAccessibilityDelegate(String str) {
            this.mContentDescription = str;
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            String str = this.mContentDescription;
            if (str != null) {
                accessibilityNodeInfo.setContentDescription(str.toLowerCase(Locale.getDefault()));
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShortcutKeyGroup {
        public final KeyboardShortcutInfo mKeyboardShortcutInfo;

        public ShortcutKeyGroup(KeyboardShortcutInfo keyboardShortcutInfo) {
            this.mKeyboardShortcutInfo = keyboardShortcutInfo;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShortcutKeyGroupMultiMappingInfo {
        public final List mKeycodeGroupList;
        public final String mLabel;

        public ShortcutKeyGroupMultiMappingInfo(String str, List list) {
            this.mLabel = str;
            this.mKeycodeGroupList = list;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StringDrawableContainer {
        public final Drawable mDrawable;
        public final String mString;

        public StringDrawableContainer(Drawable drawable, String str) {
            this.mString = str;
            this.mDrawable = drawable;
        }
    }

    public KeyboardShortcutListSearch(Context context, WindowManager windowManager, int i) {
        String str;
        HashMap hashMap = new HashMap();
        this.mKeySearchResultMap = hashMap;
        this.mFullShortsGroup = new ArrayList();
        this.mSpecificAppGroup = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.mSystemGroup = arrayList;
        this.mInputGroup = new ArrayList();
        this.mOpenAppsGroup = new ArrayList();
        this.mFullButtonList = new ArrayList();
        SparseArray sparseArray = new SparseArray();
        this.mSpecialCharacterNames = sparseArray;
        SparseArray sparseArray2 = new SparseArray();
        this.mModifierNames = sparseArray2;
        SparseArray sparseArray3 = new SparseArray();
        this.mModifierDrawables = sparseArray3;
        this.mModifierList = new int[]{65536, 4096, 2, 1, 4, 8};
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mHandlerThread = new HandlerThread("KeyboardShortcutHelper");
        this.mContext = new ContextThemeWrapper(context, R.style.KeyboardShortcutHelper);
        AppGlobals.getPackageManager();
        if (windowManager != null) {
            this.mWindowManager = windowManager;
        } else {
            this.mWindowManager = (WindowManager) this.mContext.getSystemService(WindowManager.class);
        }
        Context context2 = this.mContext;
        sparseArray.put(4, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context2, R.string.keyboard_key_home, sparseArray, 3, R.string.keyboard_key_back));
        sparseArray.put(20, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context2, R.string.keyboard_key_dpad_up, sparseArray, 19, R.string.keyboard_key_dpad_down));
        sparseArray.put(22, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context2, R.string.keyboard_key_dpad_left, sparseArray, 21, R.string.keyboard_key_dpad_right));
        sparseArray.put(23, context2.getString(R.string.keyboard_key_dpad_center));
        sparseArray.put(56, ".");
        sparseArray.put(62, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context2, R.string.keyboard_key_tab, sparseArray, 61, R.string.keyboard_key_space));
        sparseArray.put(67, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context2, R.string.keyboard_key_enter, sparseArray, 66, R.string.keyboard_key_backspace));
        sparseArray.put(86, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context2, R.string.keyboard_key_media_play_pause, sparseArray, 85, R.string.keyboard_key_media_stop));
        sparseArray.put(88, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context2, R.string.keyboard_key_media_next, sparseArray, 87, R.string.keyboard_key_media_previous));
        sparseArray.put(90, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context2, R.string.keyboard_key_media_rewind, sparseArray, 89, R.string.keyboard_key_media_fast_forward));
        sparseArray.put(93, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context2, R.string.keyboard_key_page_up, sparseArray, 92, R.string.keyboard_key_page_down));
        sparseArray.put(96, context2.getString(R.string.keyboard_key_button_template, "A"));
        sparseArray.put(97, context2.getString(R.string.keyboard_key_button_template, "B"));
        sparseArray.put(98, context2.getString(R.string.keyboard_key_button_template, "C"));
        sparseArray.put(99, context2.getString(R.string.keyboard_key_button_template, "X"));
        sparseArray.put(100, context2.getString(R.string.keyboard_key_button_template, "Y"));
        sparseArray.put(101, context2.getString(R.string.keyboard_key_button_template, "Z"));
        sparseArray.put(102, context2.getString(R.string.keyboard_key_button_template, "L1"));
        sparseArray.put(103, context2.getString(R.string.keyboard_key_button_template, "R1"));
        sparseArray.put(104, context2.getString(R.string.keyboard_key_button_template, "L2"));
        sparseArray.put(105, context2.getString(R.string.keyboard_key_button_template, "R2"));
        sparseArray.put(108, context2.getString(R.string.keyboard_key_button_template, "Start"));
        sparseArray.put(109, context2.getString(R.string.keyboard_key_button_template, "Select"));
        sparseArray.put(110, context2.getString(R.string.keyboard_key_button_template, "Mode"));
        sparseArray.put(111, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context2, R.string.keyboard_key_forward_del, sparseArray, 112, R.string.keyboard_key_esc));
        sparseArray.put(120, "SysRq");
        sparseArray.put(121, "Break");
        sparseArray.put(116, "Scroll Lock");
        sparseArray.put(123, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context2, R.string.keyboard_key_move_home, sparseArray, 122, R.string.keyboard_key_move_end));
        sparseArray.put(124, context2.getString(R.string.keyboard_key_insert));
        sparseArray.put(131, "F1");
        sparseArray.put(132, "F2");
        sparseArray.put(133, "F3");
        sparseArray.put(134, "F4");
        sparseArray.put(135, "F5");
        sparseArray.put(136, "F6");
        sparseArray.put(137, "F7");
        sparseArray.put(138, "F8");
        sparseArray.put(139, "F9");
        sparseArray.put(140, "F10");
        sparseArray.put(141, "F11");
        sparseArray.put(142, "F12");
        sparseArray.put(143, context2.getString(R.string.keyboard_key_num_lock));
        sparseArray.put(69, "-");
        sparseArray.put(68, "`");
        sparseArray.put(70, "=");
        sparseArray.put(144, context2.getString(R.string.keyboard_key_numpad_template, "0"));
        sparseArray.put(145, context2.getString(R.string.keyboard_key_numpad_template, "1"));
        sparseArray.put(146, context2.getString(R.string.keyboard_key_numpad_template, "2"));
        sparseArray.put(147, context2.getString(R.string.keyboard_key_numpad_template, "3"));
        sparseArray.put(148, context2.getString(R.string.keyboard_key_numpad_template, "4"));
        sparseArray.put(149, context2.getString(R.string.keyboard_key_numpad_template, "5"));
        sparseArray.put(150, context2.getString(R.string.keyboard_key_numpad_template, "6"));
        sparseArray.put(151, context2.getString(R.string.keyboard_key_numpad_template, "7"));
        sparseArray.put(152, context2.getString(R.string.keyboard_key_numpad_template, "8"));
        sparseArray.put(153, context2.getString(R.string.keyboard_key_numpad_template, "9"));
        sparseArray.put(154, context2.getString(R.string.keyboard_key_numpad_template, "/"));
        sparseArray.put(155, context2.getString(R.string.keyboard_key_numpad_template, "*"));
        sparseArray.put(156, context2.getString(R.string.keyboard_key_numpad_template, "-"));
        sparseArray.put(157, context2.getString(R.string.keyboard_key_numpad_template, "+"));
        sparseArray.put(158, context2.getString(R.string.keyboard_key_numpad_template, "."));
        sparseArray.put(159, context2.getString(R.string.keyboard_key_numpad_template, ","));
        sparseArray.put(160, context2.getString(R.string.keyboard_key_numpad_template, context2.getString(R.string.keyboard_key_enter)));
        sparseArray.put(161, context2.getString(R.string.keyboard_key_numpad_template, "="));
        sparseArray.put(162, context2.getString(R.string.keyboard_key_numpad_template, "("));
        sparseArray.put(163, context2.getString(R.string.keyboard_key_numpad_template, ")"));
        sparseArray.put(211, "半角/全角");
        sparseArray.put(212, "英数");
        sparseArray.put(213, "無変換");
        sparseArray.put(214, "変換");
        sparseArray.put(215, "かな");
        sparseArray.put(57, "Alt");
        sparseArray.put(58, "Alt");
        sparseArray.put(113, "Ctrl");
        sparseArray.put(114, "Ctrl");
        sparseArray.put(59, "Shift");
        sparseArray.put(60, "Shift");
        sparseArray2.put(65536, "Meta");
        sparseArray2.put(4096, "Ctrl");
        sparseArray2.put(2, "Alt");
        sparseArray2.put(1, "Shift");
        sparseArray2.put(4, "Sym");
        sparseArray2.put(8, "Fn");
        sparseArray3.put(65536, context2.getDrawable(R.drawable.ic_ksh_key_meta));
        hashMap.put(0, Boolean.TRUE);
        Context context3 = this.mContext;
        KeyboardShortcutMultiMappingGroup keyboardShortcutMultiMappingGroup = new KeyboardShortcutMultiMappingGroup(context3.getString(R.string.keyboard_shortcut_group_system), new ArrayList());
        for (ShortcutKeyGroupMultiMappingInfo shortcutKeyGroupMultiMappingInfo : Arrays.asList(new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_access_all_apps_search), Arrays.asList(Pair.create(0, 65536))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_access_home_screen), Arrays.asList(Pair.create(36, 65536), Pair.create(66, 65536))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_overview_open_apps), Arrays.asList(Pair.create(61, 65536))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_go_back), Arrays.asList(Pair.create(111, 65536), Pair.create(67, 65536), Pair.create(21, 65536))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_full_screenshot), Arrays.asList(Pair.create(47, 69632))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_access_system_app_shortcuts), Arrays.asList(Pair.create(76, 65536))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_cycle_forward), Arrays.asList(Pair.create(61, 2))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_cycle_back), Arrays.asList(Pair.create(61, 3))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_access_notification_shade), Arrays.asList(Pair.create(42, 65536))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_lock_screen), Arrays.asList(Pair.create(40, 65536))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_quick_memo), Arrays.asList(Pair.create(42, 69632))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_access_system_settings), Arrays.asList(Pair.create(37, 65536))), new ShortcutKeyGroupMultiMappingInfo(context3.getString(R.string.group_system_access_google_assistant), Arrays.asList(Pair.create(29, 65536))))) {
            shortcutKeyGroupMultiMappingInfo.getClass();
            ArrayList arrayList2 = new ArrayList();
            Iterator it = shortcutKeyGroupMultiMappingInfo.mKeycodeGroupList.iterator();
            while (true) {
                boolean hasNext = it.hasNext();
                str = shortcutKeyGroupMultiMappingInfo.mLabel;
                if (hasNext) {
                    Pair pair = (Pair) it.next();
                    arrayList2.add(new ShortcutKeyGroup(new KeyboardShortcutInfo(str, ((Integer) pair.first).intValue(), ((Integer) pair.second).intValue())));
                }
            }
            ShortcutMultiMappingInfo shortcutMultiMappingInfo = new ShortcutMultiMappingInfo(str, arrayList2);
            KeyboardShortcutMultiMappingGroup keyboardShortcutMultiMappingGroup2 = keyboardShortcutMultiMappingGroup;
            keyboardShortcutMultiMappingGroup2.addItem(shortcutMultiMappingInfo);
            keyboardShortcutMultiMappingGroup = keyboardShortcutMultiMappingGroup2;
        }
        arrayList.add(keyboardShortcutMultiMappingGroup);
        List list = this.mSystemGroup;
        Context context4 = this.mContext;
        KeyboardShortcutMultiMappingGroup keyboardShortcutMultiMappingGroup3 = new KeyboardShortcutMultiMappingGroup(context4.getString(R.string.keyboard_shortcut_group_system_multitasking), new ArrayList());
        keyboardShortcutMultiMappingGroup3.addItem(getMultitaskingShortcut(context4.getString(R.string.system_multitasking_rhs), 22, 69632));
        keyboardShortcutMultiMappingGroup3.addItem(getMultitaskingShortcut(context4.getString(R.string.system_multitasking_lhs), 21, 69632));
        keyboardShortcutMultiMappingGroup3.addItem(getMultitaskingShortcut(context4.getString(R.string.system_multitasking_full_screen), 19, 69632));
        keyboardShortcutMultiMappingGroup3.addItem(getMultitaskingShortcut(context4.getString(R.string.system_multitasking_splitscreen_focus_rhs), 22, 65538));
        keyboardShortcutMultiMappingGroup3.addItem(getMultitaskingShortcut(context4.getString(R.string.system_multitasking_splitscreen_focus_lhs), 21, 65538));
        list.add(keyboardShortcutMultiMappingGroup3);
        this.mKeySearchResultMap.put(1, Boolean.TRUE);
        List list2 = this.mInputGroup;
        Context context5 = this.mContext;
        list2.add(new KeyboardShortcutMultiMappingGroup(context5.getString(R.string.keyboard_shortcut_group_input), Arrays.asList(new ShortcutMultiMappingInfo(context5.getString(R.string.input_switch_input_language_next), Arrays.asList(new ShortcutKeyGroup(new KeyboardShortcutInfo(context5.getString(R.string.input_switch_input_language_next), 62, 4096)))), new ShortcutMultiMappingInfo(context5.getString(R.string.input_switch_input_language_previous), Arrays.asList(new ShortcutKeyGroup(new KeyboardShortcutInfo(context5.getString(R.string.input_switch_input_language_previous), 62, 4097)))))));
        ArrayList arrayList3 = new ArrayList();
        KeyboardShortcutGroup applicationLaunchKeyboardShortcuts = this.mWindowManager.getApplicationLaunchKeyboardShortcuts(i);
        Iterator it2 = reMapToKeyboardShortcutMultiMappingGroup(Arrays.asList(applicationLaunchKeyboardShortcuts)).iterator();
        while (it2.hasNext()) {
            Iterator it3 = ((KeyboardShortcutMultiMappingGroup) it2.next()).mItems.iterator();
            while (it3.hasNext()) {
                arrayList3.add((ShortcutMultiMappingInfo) it3.next());
            }
        }
        CharSequence label = applicationLaunchKeyboardShortcuts.getLabel();
        Collections.sort(arrayList3, new AnonymousClass1());
        List asList = Arrays.asList(new KeyboardShortcutMultiMappingGroup(label, arrayList3));
        if (asList == null || asList.isEmpty()) {
            this.mKeySearchResultMap.put(2, Boolean.FALSE);
        } else {
            this.mOpenAppsGroup = asList;
            this.mKeySearchResultMap.put(2, Boolean.TRUE);
        }
    }

    public static void dismiss() {
        synchronized (sLock) {
            try {
                KeyboardShortcutListSearch keyboardShortcutListSearch = sInstance;
                if (keyboardShortcutListSearch != null) {
                    MetricsLogger.hidden(keyboardShortcutListSearch.mContext, 500);
                    sInstance.dismissKeyboardShortcuts();
                    sInstance = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static ShortcutMultiMappingInfo getMultitaskingShortcut(String str, int i, int i2) {
        return new ShortcutMultiMappingInfo(str, Arrays.asList(new ShortcutKeyGroup(new KeyboardShortcutInfo(str, i, i2))));
    }

    public static List reMapToKeyboardShortcutMultiMappingGroup(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            KeyboardShortcutGroup keyboardShortcutGroup = (KeyboardShortcutGroup) it.next();
            KeyboardShortcutMultiMappingGroup keyboardShortcutMultiMappingGroup = new KeyboardShortcutMultiMappingGroup(keyboardShortcutGroup.getLabel(), new ArrayList());
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (KeyboardShortcutInfo keyboardShortcutInfo : keyboardShortcutGroup.getItems()) {
                String charSequence = keyboardShortcutInfo.getLabel().toString();
                Icon icon = keyboardShortcutInfo.getIcon();
                if (linkedHashMap.containsKey(charSequence)) {
                    List<ShortcutMultiMappingInfo> list2 = (List) linkedHashMap.get(charSequence);
                    for (ShortcutMultiMappingInfo shortcutMultiMappingInfo : list2) {
                        Icon icon2 = shortcutMultiMappingInfo.mIcon;
                        if ((icon2 != null && icon != null && icon2.sameAs(icon)) || (icon2 == null && icon == null)) {
                            shortcutMultiMappingInfo.mShortcutKeyGroups.add(new ShortcutKeyGroup(keyboardShortcutInfo));
                            break;
                        }
                    }
                    list2.add(new ShortcutMultiMappingInfo(keyboardShortcutInfo));
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(new ShortcutMultiMappingInfo(keyboardShortcutInfo));
                    linkedHashMap.put(charSequence, arrayList2);
                }
            }
            Iterator it2 = linkedHashMap.values().iterator();
            while (it2.hasNext()) {
                Iterator it3 = ((List) it2.next()).iterator();
                while (it3.hasNext()) {
                    keyboardShortcutMultiMappingGroup.addItem((ShortcutMultiMappingInfo) it3.next());
                }
            }
            arrayList.add(keyboardShortcutMultiMappingGroup);
        }
        return arrayList;
    }

    public static void show(int i, Context context) {
        MetricsLogger.visible(context, 500);
        synchronized (sLock) {
            try {
                KeyboardShortcutListSearch keyboardShortcutListSearch = sInstance;
                if (keyboardShortcutListSearch != null && !keyboardShortcutListSearch.mContext.equals(context)) {
                    dismiss();
                }
                if (sInstance == null) {
                    sInstance = new KeyboardShortcutListSearch(context, null, i);
                }
                sInstance.showKeyboardShortcuts(i);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void toggle(int i, Context context) {
        BottomSheetDialog bottomSheetDialog;
        synchronized (sLock) {
            try {
                KeyboardShortcutListSearch keyboardShortcutListSearch = sInstance;
                if ((keyboardShortcutListSearch == null || (bottomSheetDialog = keyboardShortcutListSearch.mKeyboardShortcutsBottomSheetDialog) == null || !bottomSheetDialog.isShowing()) ? false : true) {
                    dismiss();
                } else {
                    show(i, context);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void dismissKeyboardShortcuts() {
        BottomSheetDialog bottomSheetDialog = this.mKeyboardShortcutsBottomSheetDialog;
        if (bottomSheetDialog != null) {
            bottomSheetDialog.dismiss();
            this.mKeyboardShortcutsBottomSheetDialog = null;
        }
        this.mHandlerThread.quit();
    }

    public final void mergeAndShowKeyboardShortcutsGroups() {
        this.mFullShortsGroup.add(0, this.mSystemGroup);
        this.mFullShortsGroup.add(1, this.mInputGroup);
        this.mFullShortsGroup.add(2, this.mOpenAppsGroup);
        this.mFullShortsGroup.add(3, this.mSpecificAppGroup);
        this.mHandler.post(new Runnable(this.mFullShortsGroup) { // from class: com.android.systemui.statusbar.KeyboardShortcutListSearch.2
            /* JADX WARN: Type inference failed for: r6v1, types: [com.google.android.material.bottomsheet.BottomSheetDialog$5] */
            @Override // java.lang.Runnable
            public final void run() {
                final int i = 3;
                final int i2 = 0;
                final int i3 = 1;
                final KeyboardShortcutListSearch keyboardShortcutListSearch = KeyboardShortcutListSearch.this;
                keyboardShortcutListSearch.mQueryString = null;
                LayoutInflater layoutInflater = (LayoutInflater) keyboardShortcutListSearch.mContext.getSystemService(LayoutInflater.class);
                Context context = keyboardShortcutListSearch.mContext;
                TypedValue typedValue = new TypedValue();
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context.getTheme().resolveAttribute(R.attr.bottomSheetDialogTheme, typedValue, true) ? typedValue.resourceId : R.style.Theme_Design_Light_BottomSheetDialog, context);
                bottomSheetDialog.cancelable = true;
                bottomSheetDialog.canceledOnTouchOutside = true;
                bottomSheetDialog.bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() { // from class: com.google.android.material.bottomsheet.BottomSheetDialog.5
                    @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
                    public final void onStateChanged(View view, int i4) {
                        if (i4 == 5) {
                            BottomSheetDialog.this.cancel();
                        }
                    }

                    @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
                    public final void onSlide(View view) {
                    }
                };
                bottomSheetDialog.getDelegate().requestWindowFeature(1);
                bottomSheetDialog.edgeToEdgeEnabled = bottomSheetDialog.getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.enableEdgeToEdge}).getBoolean(0, false);
                bottomSheetDialog.edgeToEdgeEnabled = bottomSheetDialog.getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.enableEdgeToEdge}).getBoolean(0, false);
                keyboardShortcutListSearch.mKeyboardShortcutsBottomSheetDialog = bottomSheetDialog;
                View inflate = layoutInflater.inflate(R.layout.keyboard_shortcuts_search_view, (ViewGroup) null);
                final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.keyboard_shortcuts_container);
                keyboardShortcutListSearch.mNoSearchResults = (TextView) inflate.findViewById(R.id.shortcut_search_no_result);
                Window window = keyboardShortcutListSearch.mKeyboardShortcutsBottomSheetDialog.getWindow();
                window.setType(2008);
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(window.getAttributes());
                layoutParams.flags |= 512;
                layoutParams.setFitInsetsTypes(WindowInsets.Type.statusBars());
                window.setAttributes(layoutParams);
                window.getDecorView().setOnApplyWindowInsetsListener(new KeyboardShortcutListSearch$$ExternalSyntheticLambda11());
                window.setWindowAnimations(R.style.KeyboardShortcutHelper_BottomSheetDialogAnimation);
                keyboardShortcutListSearch.mKeyboardShortcutsBottomSheetDialog.setContentView(inflate);
                keyboardShortcutListSearch.mButtonSystem = (Button) inflate.findViewById(R.id.shortcut_system);
                keyboardShortcutListSearch.mButtonInput = (Button) inflate.findViewById(R.id.shortcut_input);
                keyboardShortcutListSearch.mButtonOpenApps = (Button) inflate.findViewById(R.id.shortcut_open_apps);
                keyboardShortcutListSearch.mButtonSpecificApp = (Button) inflate.findViewById(R.id.shortcut_specific_app);
                final LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.keyboard_shortcuts_container);
                keyboardShortcutListSearch.mButtonSystem.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.statusbar.KeyboardShortcutListSearch$$ExternalSyntheticLambda7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        switch (i2) {
                            case 0:
                                KeyboardShortcutListSearch keyboardShortcutListSearch2 = keyboardShortcutListSearch;
                                LinearLayout linearLayout3 = linearLayout2;
                                keyboardShortcutListSearch2.mCurrentCategoryIndex = 0;
                                keyboardShortcutListSearch2.populateKeyboardShortcutSearchList(linearLayout3);
                                linearLayout3.setAccessibilityPaneTitle(keyboardShortcutListSearch2.mContext.getString(R.string.keyboard_shortcut_a11y_filter_system));
                                break;
                            case 1:
                                KeyboardShortcutListSearch keyboardShortcutListSearch3 = keyboardShortcutListSearch;
                                LinearLayout linearLayout4 = linearLayout2;
                                keyboardShortcutListSearch3.mCurrentCategoryIndex = 3;
                                keyboardShortcutListSearch3.populateKeyboardShortcutSearchList(linearLayout4);
                                linearLayout4.setAccessibilityPaneTitle(keyboardShortcutListSearch3.mContext.getString(R.string.keyboard_shortcut_a11y_filter_current_app));
                                break;
                            case 2:
                                KeyboardShortcutListSearch keyboardShortcutListSearch4 = keyboardShortcutListSearch;
                                LinearLayout linearLayout5 = linearLayout2;
                                keyboardShortcutListSearch4.mCurrentCategoryIndex = 1;
                                keyboardShortcutListSearch4.populateKeyboardShortcutSearchList(linearLayout5);
                                linearLayout5.setAccessibilityPaneTitle(keyboardShortcutListSearch4.mContext.getString(R.string.keyboard_shortcut_a11y_filter_input));
                                break;
                            default:
                                KeyboardShortcutListSearch keyboardShortcutListSearch5 = keyboardShortcutListSearch;
                                LinearLayout linearLayout6 = linearLayout2;
                                keyboardShortcutListSearch5.mCurrentCategoryIndex = 2;
                                keyboardShortcutListSearch5.populateKeyboardShortcutSearchList(linearLayout6);
                                linearLayout6.setAccessibilityPaneTitle(keyboardShortcutListSearch5.mContext.getString(R.string.keyboard_shortcut_a11y_filter_open_apps));
                                break;
                        }
                    }
                });
                final int i4 = 2;
                keyboardShortcutListSearch.mButtonInput.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.statusbar.KeyboardShortcutListSearch$$ExternalSyntheticLambda7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        switch (i4) {
                            case 0:
                                KeyboardShortcutListSearch keyboardShortcutListSearch2 = keyboardShortcutListSearch;
                                LinearLayout linearLayout3 = linearLayout2;
                                keyboardShortcutListSearch2.mCurrentCategoryIndex = 0;
                                keyboardShortcutListSearch2.populateKeyboardShortcutSearchList(linearLayout3);
                                linearLayout3.setAccessibilityPaneTitle(keyboardShortcutListSearch2.mContext.getString(R.string.keyboard_shortcut_a11y_filter_system));
                                break;
                            case 1:
                                KeyboardShortcutListSearch keyboardShortcutListSearch3 = keyboardShortcutListSearch;
                                LinearLayout linearLayout4 = linearLayout2;
                                keyboardShortcutListSearch3.mCurrentCategoryIndex = 3;
                                keyboardShortcutListSearch3.populateKeyboardShortcutSearchList(linearLayout4);
                                linearLayout4.setAccessibilityPaneTitle(keyboardShortcutListSearch3.mContext.getString(R.string.keyboard_shortcut_a11y_filter_current_app));
                                break;
                            case 2:
                                KeyboardShortcutListSearch keyboardShortcutListSearch4 = keyboardShortcutListSearch;
                                LinearLayout linearLayout5 = linearLayout2;
                                keyboardShortcutListSearch4.mCurrentCategoryIndex = 1;
                                keyboardShortcutListSearch4.populateKeyboardShortcutSearchList(linearLayout5);
                                linearLayout5.setAccessibilityPaneTitle(keyboardShortcutListSearch4.mContext.getString(R.string.keyboard_shortcut_a11y_filter_input));
                                break;
                            default:
                                KeyboardShortcutListSearch keyboardShortcutListSearch5 = keyboardShortcutListSearch;
                                LinearLayout linearLayout6 = linearLayout2;
                                keyboardShortcutListSearch5.mCurrentCategoryIndex = 2;
                                keyboardShortcutListSearch5.populateKeyboardShortcutSearchList(linearLayout6);
                                linearLayout6.setAccessibilityPaneTitle(keyboardShortcutListSearch5.mContext.getString(R.string.keyboard_shortcut_a11y_filter_open_apps));
                                break;
                        }
                    }
                });
                keyboardShortcutListSearch.mButtonOpenApps.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.statusbar.KeyboardShortcutListSearch$$ExternalSyntheticLambda7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        switch (i) {
                            case 0:
                                KeyboardShortcutListSearch keyboardShortcutListSearch2 = keyboardShortcutListSearch;
                                LinearLayout linearLayout3 = linearLayout2;
                                keyboardShortcutListSearch2.mCurrentCategoryIndex = 0;
                                keyboardShortcutListSearch2.populateKeyboardShortcutSearchList(linearLayout3);
                                linearLayout3.setAccessibilityPaneTitle(keyboardShortcutListSearch2.mContext.getString(R.string.keyboard_shortcut_a11y_filter_system));
                                break;
                            case 1:
                                KeyboardShortcutListSearch keyboardShortcutListSearch3 = keyboardShortcutListSearch;
                                LinearLayout linearLayout4 = linearLayout2;
                                keyboardShortcutListSearch3.mCurrentCategoryIndex = 3;
                                keyboardShortcutListSearch3.populateKeyboardShortcutSearchList(linearLayout4);
                                linearLayout4.setAccessibilityPaneTitle(keyboardShortcutListSearch3.mContext.getString(R.string.keyboard_shortcut_a11y_filter_current_app));
                                break;
                            case 2:
                                KeyboardShortcutListSearch keyboardShortcutListSearch4 = keyboardShortcutListSearch;
                                LinearLayout linearLayout5 = linearLayout2;
                                keyboardShortcutListSearch4.mCurrentCategoryIndex = 1;
                                keyboardShortcutListSearch4.populateKeyboardShortcutSearchList(linearLayout5);
                                linearLayout5.setAccessibilityPaneTitle(keyboardShortcutListSearch4.mContext.getString(R.string.keyboard_shortcut_a11y_filter_input));
                                break;
                            default:
                                KeyboardShortcutListSearch keyboardShortcutListSearch5 = keyboardShortcutListSearch;
                                LinearLayout linearLayout6 = linearLayout2;
                                keyboardShortcutListSearch5.mCurrentCategoryIndex = 2;
                                keyboardShortcutListSearch5.populateKeyboardShortcutSearchList(linearLayout6);
                                linearLayout6.setAccessibilityPaneTitle(keyboardShortcutListSearch5.mContext.getString(R.string.keyboard_shortcut_a11y_filter_open_apps));
                                break;
                        }
                    }
                });
                keyboardShortcutListSearch.mButtonSpecificApp.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.statusbar.KeyboardShortcutListSearch$$ExternalSyntheticLambda7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        switch (i3) {
                            case 0:
                                KeyboardShortcutListSearch keyboardShortcutListSearch2 = keyboardShortcutListSearch;
                                LinearLayout linearLayout3 = linearLayout2;
                                keyboardShortcutListSearch2.mCurrentCategoryIndex = 0;
                                keyboardShortcutListSearch2.populateKeyboardShortcutSearchList(linearLayout3);
                                linearLayout3.setAccessibilityPaneTitle(keyboardShortcutListSearch2.mContext.getString(R.string.keyboard_shortcut_a11y_filter_system));
                                break;
                            case 1:
                                KeyboardShortcutListSearch keyboardShortcutListSearch3 = keyboardShortcutListSearch;
                                LinearLayout linearLayout4 = linearLayout2;
                                keyboardShortcutListSearch3.mCurrentCategoryIndex = 3;
                                keyboardShortcutListSearch3.populateKeyboardShortcutSearchList(linearLayout4);
                                linearLayout4.setAccessibilityPaneTitle(keyboardShortcutListSearch3.mContext.getString(R.string.keyboard_shortcut_a11y_filter_current_app));
                                break;
                            case 2:
                                KeyboardShortcutListSearch keyboardShortcutListSearch4 = keyboardShortcutListSearch;
                                LinearLayout linearLayout5 = linearLayout2;
                                keyboardShortcutListSearch4.mCurrentCategoryIndex = 1;
                                keyboardShortcutListSearch4.populateKeyboardShortcutSearchList(linearLayout5);
                                linearLayout5.setAccessibilityPaneTitle(keyboardShortcutListSearch4.mContext.getString(R.string.keyboard_shortcut_a11y_filter_input));
                                break;
                            default:
                                KeyboardShortcutListSearch keyboardShortcutListSearch5 = keyboardShortcutListSearch;
                                LinearLayout linearLayout6 = linearLayout2;
                                keyboardShortcutListSearch5.mCurrentCategoryIndex = 2;
                                keyboardShortcutListSearch5.populateKeyboardShortcutSearchList(linearLayout6);
                                linearLayout6.setAccessibilityPaneTitle(keyboardShortcutListSearch5.mContext.getString(R.string.keyboard_shortcut_a11y_filter_open_apps));
                                break;
                        }
                    }
                });
                keyboardShortcutListSearch.mFullButtonList.add(keyboardShortcutListSearch.mButtonSystem);
                keyboardShortcutListSearch.mFullButtonList.add(keyboardShortcutListSearch.mButtonInput);
                keyboardShortcutListSearch.mFullButtonList.add(keyboardShortcutListSearch.mButtonOpenApps);
                keyboardShortcutListSearch.mFullButtonList.add(keyboardShortcutListSearch.mButtonSpecificApp);
                Button button = keyboardShortcutListSearch.mButtonSpecificApp;
                if (button != null) {
                    if (keyboardShortcutListSearch.mCurrentAppPackageName != null) {
                        int userId = keyboardShortcutListSearch.mContext.getUserId();
                        try {
                            PackageManager packageManagerForUser = CentralSurfaces.getPackageManagerForUser(userId, keyboardShortcutListSearch.mContext);
                            keyboardShortcutListSearch.mButtonSpecificApp.setText(packageManagerForUser.getApplicationLabel(packageManagerForUser.getApplicationInfoAsUser(keyboardShortcutListSearch.mCurrentAppPackageName.toString(), 0, userId)));
                        } catch (PackageManager.NameNotFoundException e) {
                            Log.e("KeyboardShortcutListSearch", "Package name not found", e);
                            Button button2 = keyboardShortcutListSearch.mButtonSpecificApp;
                            if (button2 != null) {
                                button2.setText(keyboardShortcutListSearch.mContext.getString(R.string.keyboard_shortcut_search_category_current_app));
                            }
                        }
                    } else if (button != null) {
                        button.setText(keyboardShortcutListSearch.mContext.getString(R.string.keyboard_shortcut_search_category_current_app));
                    }
                }
                keyboardShortcutListSearch.populateKeyboardShortcutSearchList(linearLayout);
                FrameLayout frameLayout = (FrameLayout) keyboardShortcutListSearch.mKeyboardShortcutsBottomSheetDialog.findViewById(R.id.design_bottom_sheet);
                if (frameLayout != null) {
                    frameLayout.setBackgroundResource(android.R.color.transparent);
                }
                BottomSheetBehavior from = BottomSheetBehavior.from(frameLayout);
                from.draggable = true;
                from.setState$2(3);
                from.skipCollapsed = true;
                synchronized (KeyboardShortcutListSearch.sLock) {
                    try {
                        if (KeyboardShortcutListSearch.sInstance != null) {
                            keyboardShortcutListSearch.mKeyboardShortcutsBottomSheetDialog.show();
                            keyboardShortcutListSearch.setDialogScreenSize();
                            inflate.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.statusbar.KeyboardShortcutListSearch.3
                                @Override // android.view.View.OnLayoutChangeListener
                                public final void onLayoutChange(View view, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
                                    KeyboardShortcutListSearch.this.setDialogScreenSize();
                                }
                            });
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                keyboardShortcutListSearch.mSearchEditText = (EditText) inflate.findViewById(R.id.keyboard_shortcuts_search);
                keyboardShortcutListSearch.mEditTextCancel = (ImageButton) inflate.findViewById(R.id.keyboard_shortcuts_search_cancel);
                keyboardShortcutListSearch.mSearchEditText.addTextChangedListener(new TextWatcher() { // from class: com.android.systemui.statusbar.KeyboardShortcutListSearch.4
                    @Override // android.text.TextWatcher
                    public final void afterTextChanged(Editable editable) {
                        KeyboardShortcutListSearch.this.mQueryString = editable.toString();
                        KeyboardShortcutListSearch.this.populateKeyboardShortcutSearchList(linearLayout);
                        if (KeyboardShortcutListSearch.this.mNoSearchResults.getVisibility() == 0) {
                            linearLayout.setAccessibilityPaneTitle(KeyboardShortcutListSearch.this.mContext.getString(R.string.keyboard_shortcut_search_list_no_result));
                        } else if (KeyboardShortcutListSearch.this.mSearchEditText.getText().length() > 0) {
                            linearLayout.setAccessibilityPaneTitle(KeyboardShortcutListSearch.this.mContext.getString(R.string.keyboard_shortcut_a11y_show_search_results));
                        }
                        KeyboardShortcutListSearch keyboardShortcutListSearch2 = KeyboardShortcutListSearch.this;
                        keyboardShortcutListSearch2.mEditTextCancel.setVisibility(TextUtils.isEmpty(keyboardShortcutListSearch2.mQueryString) ? 8 : 0);
                    }

                    @Override // android.text.TextWatcher
                    public final void beforeTextChanged(CharSequence charSequence, int i5, int i6, int i7) {
                    }

                    @Override // android.text.TextWatcher
                    public final void onTextChanged(CharSequence charSequence, int i5, int i6, int i7) {
                    }
                });
                keyboardShortcutListSearch.mEditTextCancel.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.statusbar.KeyboardShortcutListSearch$$ExternalSyntheticLambda5
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        KeyboardShortcutListSearch.this.mSearchEditText.setText((CharSequence) null);
                    }
                });
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r5v12, types: [java.util.List] */
    public final void populateKeyboardShortcutSearchList(LinearLayout linearLayout) {
        View view;
        ArrayList arrayList;
        int i;
        boolean z;
        char c;
        int i2;
        List list;
        KeyboardShortcutMultiMappingGroup keyboardShortcutMultiMappingGroup;
        int i3;
        ArrayList arrayList2;
        int i4;
        View view2;
        List list2;
        TextView textView;
        int i5;
        View view3;
        ArrayList arrayList3;
        int i6;
        int[] iArr;
        ArrayList arrayList4;
        int i7;
        ArrayList arrayList5;
        String valueOf;
        int i8;
        LinearLayout linearLayout2 = linearLayout;
        LayoutInflater from = LayoutInflater.from(this.mContext);
        boolean z2 = false;
        TextView textView2 = (TextView) from.inflate(R.layout.keyboard_shortcuts_key_view, (ViewGroup) linearLayout2, false);
        textView2.measure(0, 0);
        int measuredHeight = textView2.getMeasuredHeight();
        textView2.getMeasuredHeight();
        textView2.getPaddingTop();
        textView2.getPaddingBottom();
        linearLayout.removeAllViews();
        if (this.mQueryString != null) {
            for (int i9 = 0; i9 < ((ArrayList) this.mFullShortsGroup).size(); i9++) {
                this.mKeySearchResultMap.put(Integer.valueOf(i9), Boolean.FALSE);
                Iterator it = ((List) ((ArrayList) this.mFullShortsGroup).get(i9)).iterator();
                while (it.hasNext()) {
                    Iterator it2 = ((KeyboardShortcutMultiMappingGroup) it.next()).mItems.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (((ShortcutMultiMappingInfo) it2.next()).mLabel.toString().toUpperCase(Locale.getDefault()).contains(this.mQueryString.toUpperCase(Locale.getDefault()))) {
                                this.mKeySearchResultMap.put(Integer.valueOf(i9), Boolean.TRUE);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        for (int i10 = 0; i10 < this.mKeySearchResultMap.size(); i10++) {
            if (((Boolean) this.mKeySearchResultMap.get(Integer.valueOf(i10))).booleanValue()) {
                ((Button) this.mFullButtonList.get(i10)).setVisibility(0);
                setButtonFocusColor(i10, false);
            } else {
                ((Button) this.mFullButtonList.get(i10)).setVisibility(8);
            }
        }
        if (((Button) this.mFullButtonList.get(this.mCurrentCategoryIndex)).getVisibility() == 8) {
            int i11 = 0;
            while (true) {
                if (i11 >= this.mKeySearchResultMap.size()) {
                    break;
                }
                if (((Boolean) this.mKeySearchResultMap.get(Integer.valueOf(i11))).booleanValue()) {
                    this.mCurrentCategoryIndex = i11;
                    break;
                }
                i11++;
            }
        }
        setButtonFocusColor(this.mCurrentCategoryIndex, true);
        List list3 = (List) ((ArrayList) this.mFullShortsGroup).get(this.mCurrentCategoryIndex);
        int size = list3.size();
        ArrayList arrayList6 = new ArrayList();
        int i12 = 0;
        while (i12 < size) {
            View inflate = from.inflate(R.layout.keyboard_shortcuts_category_short_separator, linearLayout2, z2);
            if (i12 > 0) {
                linearLayout2.addView(inflate);
            }
            ArrayList arrayList7 = new ArrayList();
            KeyboardShortcutMultiMappingGroup keyboardShortcutMultiMappingGroup2 = (KeyboardShortcutMultiMappingGroup) list3.get(i12);
            TextView textView3 = (TextView) from.inflate(R.layout.keyboard_shortcuts_category_title, linearLayout2, z2);
            textView3.setText(keyboardShortcutMultiMappingGroup2.mCategory);
            linearLayout2.addView(textView3);
            LinearLayout linearLayout3 = (LinearLayout) from.inflate(R.layout.keyboard_shortcuts_container, linearLayout2, z2);
            int size2 = keyboardShortcutMultiMappingGroup2.mItems.size();
            ?? r2 = z2;
            ?? r13 = arrayList7;
            while (r2 < size2) {
                ShortcutMultiMappingInfo shortcutMultiMappingInfo = (ShortcutMultiMappingInfo) keyboardShortcutMultiMappingGroup2.mItems.get(r2);
                int i13 = size2;
                if (this.mQueryString != null) {
                    list = list3;
                    keyboardShortcutMultiMappingGroup = keyboardShortcutMultiMappingGroup2;
                    if (shortcutMultiMappingInfo.mLabel.toString().toUpperCase(Locale.getDefault()).contains(this.mQueryString.toUpperCase(Locale.getDefault()))) {
                        r13.add(r2, Boolean.TRUE);
                    } else {
                        r13.add(r2, Boolean.FALSE);
                        i3 = size;
                        arrayList2 = arrayList6;
                        i4 = i12;
                        view2 = inflate;
                        list2 = r13;
                        textView = textView3;
                        i5 = 1;
                        int i14 = r2 + i5;
                        size2 = i13;
                        list3 = list;
                        keyboardShortcutMultiMappingGroup2 = keyboardShortcutMultiMappingGroup;
                        size = i3;
                        textView3 = textView;
                        r13 = list2;
                        inflate = view2;
                        i12 = i4;
                        arrayList6 = arrayList2;
                        r2 = i14;
                    }
                } else {
                    list = list3;
                    keyboardShortcutMultiMappingGroup = keyboardShortcutMultiMappingGroup2;
                }
                View inflate2 = from.inflate(R.layout.keyboard_shortcut_app_item, (ViewGroup) linearLayout3, false);
                TextView textView4 = (TextView) inflate2.findViewById(R.id.keyboard_shortcuts_keyword);
                textView4.setText(shortcutMultiMappingInfo.mLabel);
                if (shortcutMultiMappingInfo.mIcon != null) {
                    ImageView imageView = (ImageView) inflate2.findViewById(R.id.keyboard_shortcuts_icon);
                    i3 = size;
                    imageView.setImageIcon(shortcutMultiMappingInfo.mIcon);
                    imageView.setVisibility(0);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) textView4.getLayoutParams();
                    layoutParams.removeRule(20);
                    textView4.setLayoutParams(layoutParams);
                } else {
                    i3 = size;
                }
                ViewGroup viewGroup = (ViewGroup) inflate2.findViewById(R.id.keyboard_shortcuts_item_container);
                int size3 = shortcutMultiMappingInfo.mShortcutKeyGroups.size();
                int i15 = 0;
                List list4 = r13;
                while (i15 < size3) {
                    TextView textView5 = textView3;
                    KeyboardShortcutInfo keyboardShortcutInfo = ((ShortcutKeyGroup) shortcutMultiMappingInfo.mShortcutKeyGroups.get(i15)).mKeyboardShortcutInfo;
                    List list5 = list4;
                    ((ShortcutKeyGroup) shortcutMultiMappingInfo.mShortcutKeyGroups.get(i15)).getClass();
                    ArrayList arrayList8 = new ArrayList();
                    int modifiers = keyboardShortcutInfo.getModifiers();
                    ShortcutMultiMappingInfo shortcutMultiMappingInfo2 = shortcutMultiMappingInfo;
                    if (modifiers == 0) {
                        arrayList3 = arrayList6;
                        i6 = i12;
                        view3 = inflate;
                    } else {
                        int[] iArr2 = this.mModifierList;
                        int length = iArr2.length;
                        view3 = inflate;
                        int i16 = 0;
                        while (i16 < length) {
                            int i17 = length;
                            int i18 = iArr2[i16];
                            if ((modifiers & i18) != 0) {
                                iArr = iArr2;
                                i7 = i12;
                                arrayList4 = arrayList6;
                                arrayList8.add(new StringDrawableContainer((Drawable) this.mModifierDrawables.get(i18), (String) this.mModifierNames.get(i18)));
                                modifiers &= ~i18;
                            } else {
                                iArr = iArr2;
                                arrayList4 = arrayList6;
                                i7 = i12;
                            }
                            i16++;
                            length = i17;
                            iArr2 = iArr;
                            i12 = i7;
                            arrayList6 = arrayList4;
                        }
                        arrayList3 = arrayList6;
                        i6 = i12;
                        if (modifiers != 0) {
                            arrayList8 = null;
                        }
                    }
                    if (arrayList8 == null) {
                        arrayList5 = null;
                    } else {
                        if (keyboardShortcutInfo.getBaseCharacter() > 0) {
                            valueOf = String.valueOf(keyboardShortcutInfo.getBaseCharacter()).toUpperCase(Locale.getDefault());
                        } else if (this.mSpecialCharacterNames.get(keyboardShortcutInfo.getKeycode()) != null) {
                            valueOf = (String) this.mSpecialCharacterNames.get(keyboardShortcutInfo.getKeycode());
                        } else {
                            if (keyboardShortcutInfo.getKeycode() != 0) {
                                char displayLabel = this.mKeyCharacterMap.getDisplayLabel(keyboardShortcutInfo.getKeycode());
                                if (displayLabel != 0) {
                                    valueOf = String.valueOf(displayLabel);
                                } else {
                                    char displayLabel2 = this.mBackupKeyCharacterMap.getDisplayLabel(keyboardShortcutInfo.getKeycode());
                                    if (displayLabel2 != 0) {
                                        valueOf = String.valueOf(displayLabel2);
                                    } else {
                                        arrayList5 = null;
                                    }
                                }
                            }
                            arrayList5 = arrayList8;
                        }
                        if (valueOf != null) {
                            arrayList8.add(new StringDrawableContainer(null, valueOf));
                        } else {
                            Log.w("KeyboardShortcutListSearch", "Keyboard Shortcut does not have a text representation, skipping.");
                        }
                        arrayList5 = arrayList8;
                    }
                    if (arrayList5 == null) {
                        Log.w("KeyboardShortcutListSearch", "Keyboard Shortcut contains unsupported keys, skipping.");
                        i8 = 1;
                    } else {
                        int size4 = arrayList5.size();
                        for (int i19 = 0; i19 < size4; i19++) {
                            StringDrawableContainer stringDrawableContainer = (StringDrawableContainer) arrayList5.get(i19);
                            Drawable drawable = stringDrawableContainer.mDrawable;
                            String str = stringDrawableContainer.mString;
                            if (drawable != null) {
                                ImageView imageView2 = (ImageView) from.inflate(R.layout.keyboard_shortcuts_key_icon_view, viewGroup, false);
                                imageView2.setImageDrawable(stringDrawableContainer.mDrawable);
                                imageView2.post(new KeyboardShortcutListSearch$$ExternalSyntheticLambda3(this, imageView2, 2));
                                imageView2.setImportantForAccessibility(1);
                                imageView2.setAccessibilityDelegate(new ShortcutKeyAccessibilityDelegate(str));
                                viewGroup.addView(imageView2);
                            } else if (str != null) {
                                TextView textView6 = (TextView) from.inflate(R.layout.keyboard_shortcuts_key_view, viewGroup, false);
                                textView6.setMinimumWidth(measuredHeight);
                                textView6.setText(str);
                                textView6.setAccessibilityDelegate(new ShortcutKeyAccessibilityDelegate(str));
                                viewGroup.addView(textView6);
                            }
                        }
                        i8 = 1;
                        if (i15 < size3 - 1) {
                            viewGroup.addView((TextView) from.inflate(R.layout.keyboard_shortcuts_key_separator_view, viewGroup, false));
                        }
                    }
                    i15 += i8;
                    textView3 = textView5;
                    list4 = list5;
                    shortcutMultiMappingInfo = shortcutMultiMappingInfo2;
                    inflate = view3;
                    i12 = i6;
                    arrayList6 = arrayList3;
                }
                arrayList2 = arrayList6;
                i4 = i12;
                view2 = inflate;
                list2 = list4;
                textView = textView3;
                i5 = 1;
                linearLayout3.addView(inflate2);
                int i142 = r2 + i5;
                size2 = i13;
                list3 = list;
                keyboardShortcutMultiMappingGroup2 = keyboardShortcutMultiMappingGroup;
                size = i3;
                textView3 = textView;
                r13 = list2;
                inflate = view2;
                i12 = i4;
                arrayList6 = arrayList2;
                r2 = i142;
            }
            List list6 = list3;
            int i20 = size;
            ArrayList arrayList9 = arrayList6;
            int i21 = i12;
            View view4 = inflate;
            List list7 = r13;
            TextView textView7 = textView3;
            if (arrayList9.isEmpty()) {
                linearLayout2 = linearLayout;
                view = view4;
                arrayList = arrayList9;
            } else {
                arrayList = arrayList9;
                if (((Boolean) arrayList.get(i21 - 1)).booleanValue()) {
                    linearLayout2 = linearLayout;
                    view = view4;
                } else {
                    linearLayout2 = linearLayout;
                    view = view4;
                    linearLayout2.removeView(view);
                }
            }
            if (!list7.isEmpty()) {
                Boolean bool = Boolean.TRUE;
                if (!list7.contains(bool)) {
                    linearLayout2.removeView(textView7);
                    linearLayout2.removeView(view);
                    arrayList.add(Boolean.FALSE);
                    i = i21;
                    if (i != i20 - 1 || arrayList.contains(bool)) {
                        z = false;
                    } else {
                        z = false;
                        this.mNoSearchResults.setVisibility(0);
                    }
                    i2 = 1;
                    c = '\b';
                    int i22 = i + i2;
                    arrayList6 = arrayList;
                    z2 = z;
                    list3 = list6;
                    size = i20;
                    i12 = i22;
                }
            }
            i = i21;
            z = false;
            arrayList.add(Boolean.TRUE);
            c = '\b';
            this.mNoSearchResults.setVisibility(8);
            linearLayout2.addView(linearLayout3);
            i2 = 1;
            int i222 = i + i2;
            arrayList6 = arrayList;
            z2 = z;
            list3 = list6;
            size = i20;
            i12 = i222;
        }
    }

    public final void setButtonFocusColor(int i, boolean z) {
        if (z) {
            ((Button) this.mFullButtonList.get(i)).setTextColor(Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorOnPrimary, 0, this.mContext));
            ((Button) this.mFullButtonList.get(i)).setBackground(this.mContext.getDrawable(R.drawable.shortcut_button_focus_colored));
        } else {
            ((Button) this.mFullButtonList.get(i)).setTextColor(Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorOnSurface, 0, this.mContext));
            ((Button) this.mFullButtonList.get(i)).setBackground(this.mContext.getDrawable(R.drawable.shortcut_button_colored));
        }
    }

    public final void setDialogScreenSize() {
        Window window = this.mKeyboardShortcutsBottomSheetDialog.getWindow();
        Display defaultDisplay = this.mWindowManager.getDefaultDisplay();
        WindowManager.LayoutParams attributes = this.mKeyboardShortcutsBottomSheetDialog.getWindow().getAttributes();
        if (this.mContext.getResources().getConfiguration().orientation == 1) {
            attributes.width = (int) (defaultDisplay.getWidth() * 0.8d);
            attributes.height = (int) (defaultDisplay.getHeight() * 0.8d);
        } else {
            attributes.width = (int) (defaultDisplay.getWidth() * 0.7d);
            attributes.height = (int) (defaultDisplay.getHeight() * 0.95d);
        }
        window.setGravity(80);
        window.setAttributes(attributes);
    }

    public void showKeyboardShortcuts(int i) {
        InputDevice inputDevice;
        if (this.mBackgroundHandler == null) {
            this.mHandlerThread.start();
            this.mBackgroundHandler = new Handler(this.mHandlerThread.getLooper());
        }
        InputManagerGlobal inputManagerGlobal = InputManagerGlobal.getInstance();
        this.mBackupKeyCharacterMap = inputManagerGlobal.getInputDevice(-1).getKeyCharacterMap();
        if (i == -1 || (inputDevice = inputManagerGlobal.getInputDevice(i)) == null) {
            int[] inputDeviceIds = inputManagerGlobal.getInputDeviceIds();
            int length = inputDeviceIds.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    this.mKeyCharacterMap = this.mBackupKeyCharacterMap;
                    break;
                }
                InputDevice inputDevice2 = inputManagerGlobal.getInputDevice(inputDeviceIds[i2]);
                if (inputDevice2.getId() != -1 && inputDevice2.isFullKeyboard()) {
                    this.mKeyCharacterMap = inputDevice2.getKeyCharacterMap();
                    break;
                }
                i2++;
            }
        } else {
            this.mKeyCharacterMap = inputDevice.getKeyCharacterMap();
        }
        this.mAppShortcutsReceived = false;
        this.mImeShortcutsReceived = false;
        final int i3 = 0;
        this.mWindowManager.requestAppKeyboardShortcuts(new WindowManager.KeyboardShortcutsReceiver(this) { // from class: com.android.systemui.statusbar.KeyboardShortcutListSearch$$ExternalSyntheticLambda1
            public final /* synthetic */ KeyboardShortcutListSearch f$0;

            {
                this.f$0 = this;
            }

            public final void onKeyboardShortcutsReceived(List list) {
                int i4 = i3;
                KeyboardShortcutListSearch keyboardShortcutListSearch = this.f$0;
                switch (i4) {
                    case 0:
                        keyboardShortcutListSearch.mBackgroundHandler.post(new KeyboardShortcutListSearch$$ExternalSyntheticLambda3(keyboardShortcutListSearch, list, 1));
                        break;
                    default:
                        keyboardShortcutListSearch.mBackgroundHandler.post(new KeyboardShortcutListSearch$$ExternalSyntheticLambda3(keyboardShortcutListSearch, list, 0));
                        break;
                }
            }
        }, i);
        final int i4 = 1;
        this.mWindowManager.requestImeKeyboardShortcuts(new WindowManager.KeyboardShortcutsReceiver(this) { // from class: com.android.systemui.statusbar.KeyboardShortcutListSearch$$ExternalSyntheticLambda1
            public final /* synthetic */ KeyboardShortcutListSearch f$0;

            {
                this.f$0 = this;
            }

            public final void onKeyboardShortcutsReceived(List list) {
                int i42 = i4;
                KeyboardShortcutListSearch keyboardShortcutListSearch = this.f$0;
                switch (i42) {
                    case 0:
                        keyboardShortcutListSearch.mBackgroundHandler.post(new KeyboardShortcutListSearch$$ExternalSyntheticLambda3(keyboardShortcutListSearch, list, 1));
                        break;
                    default:
                        keyboardShortcutListSearch.mBackgroundHandler.post(new KeyboardShortcutListSearch$$ExternalSyntheticLambda3(keyboardShortcutListSearch, list, 0));
                        break;
                }
            }
        }, i);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShortcutMultiMappingInfo {
        public final Icon mIcon;
        public final CharSequence mLabel;
        public final List mShortcutKeyGroups;

        public ShortcutMultiMappingInfo(CharSequence charSequence, List list) {
            this.mLabel = charSequence;
            this.mIcon = null;
            this.mShortcutKeyGroups = list;
        }

        public ShortcutMultiMappingInfo(KeyboardShortcutInfo keyboardShortcutInfo) {
            this.mLabel = keyboardShortcutInfo.getLabel();
            this.mIcon = keyboardShortcutInfo.getIcon();
            this.mShortcutKeyGroups = new ArrayList(Arrays.asList(new ShortcutKeyGroup(keyboardShortcutInfo)));
        }
    }
}
