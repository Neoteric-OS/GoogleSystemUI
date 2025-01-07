package com.android.systemui.statusbar;

import android.R;
import android.app.AppGlobals;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyboardShortcutGroup;
import android.view.KeyboardShortcutInfo;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.android.internal.app.AssistUtils;
import com.android.internal.logging.MetricsLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyboardShortcuts {
    public static KeyboardShortcuts sInstance;
    public static final Object sLock = new Object();
    public final AnonymousClass2 mApplicationItemsComparator;
    Handler mBackgroundHandler;
    public KeyCharacterMap mBackupKeyCharacterMap;
    public Context mContext;
    public KeyboardShortcutGroup mDefaultApplicationShortcuts;
    public final AnonymousClass1 mDialogCloseListener;
    public final Handler mHandler;
    public final HandlerThread mHandlerThread;
    public KeyCharacterMap mKeyCharacterMap;
    Dialog mKeyboardShortcutsDialog;
    public final SparseArray mModifierDrawables;
    public final int[] mModifierList;
    public final SparseArray mModifierNames;
    public final IPackageManager mPackageManager;
    public List mReceivedAppShortcutGroups;
    public List mReceivedImeShortcutGroups;
    public final SparseArray mSpecialCharacterNames;
    public final WindowManager mWindowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.KeyboardShortcuts$2, reason: invalid class name */
    public final class AnonymousClass2 implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            KeyboardShortcutInfo keyboardShortcutInfo = (KeyboardShortcutInfo) obj;
            KeyboardShortcutInfo keyboardShortcutInfo2 = (KeyboardShortcutInfo) obj2;
            boolean z = keyboardShortcutInfo.getLabel() == null || keyboardShortcutInfo.getLabel().toString().isEmpty();
            boolean z2 = keyboardShortcutInfo2.getLabel() == null || keyboardShortcutInfo2.getLabel().toString().isEmpty();
            if (z && z2) {
                return 0;
            }
            if (z) {
                return 1;
            }
            if (z2) {
                return -1;
            }
            return keyboardShortcutInfo.getLabel().toString().compareToIgnoreCase(keyboardShortcutInfo2.getLabel().toString());
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
                accessibilityNodeInfo.setContentDescription(str.toLowerCase());
            }
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

    /* JADX WARN: Type inference failed for: r9v4, types: [com.android.systemui.statusbar.KeyboardShortcuts$1] */
    public KeyboardShortcuts(Context context, WindowManager windowManager) {
        SparseArray sparseArray = new SparseArray();
        this.mSpecialCharacterNames = sparseArray;
        SparseArray sparseArray2 = new SparseArray();
        this.mModifierNames = sparseArray2;
        SparseArray sparseArray3 = new SparseArray();
        this.mModifierDrawables = sparseArray3;
        this.mModifierList = new int[]{65536, 4096, 2, 1, 4, 8};
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mHandlerThread = new HandlerThread("KeyboardShortcutHelper");
        this.mDialogCloseListener = new DialogInterface.OnClickListener() { // from class: com.android.systemui.statusbar.KeyboardShortcuts.1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                KeyboardShortcuts.this.dismissKeyboardShortcuts();
            }
        };
        this.mApplicationItemsComparator = new AnonymousClass2();
        this.mReceivedAppShortcutGroups = null;
        this.mReceivedImeShortcutGroups = null;
        this.mDefaultApplicationShortcuts = null;
        this.mContext = new ContextThemeWrapper(context, R.style.Theme.DeviceDefault.Settings);
        this.mPackageManager = AppGlobals.getPackageManager();
        if (windowManager != null) {
            this.mWindowManager = windowManager;
        } else {
            this.mWindowManager = (WindowManager) this.mContext.getSystemService(WindowManager.class);
        }
        sparseArray.put(4, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context, com.android.wm.shell.R.string.keyboard_key_home, sparseArray, 3, com.android.wm.shell.R.string.keyboard_key_back));
        sparseArray.put(20, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context, com.android.wm.shell.R.string.keyboard_key_dpad_up, sparseArray, 19, com.android.wm.shell.R.string.keyboard_key_dpad_down));
        sparseArray.put(22, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context, com.android.wm.shell.R.string.keyboard_key_dpad_left, sparseArray, 21, com.android.wm.shell.R.string.keyboard_key_dpad_right));
        sparseArray.put(23, context.getString(com.android.wm.shell.R.string.keyboard_key_dpad_center));
        sparseArray.put(56, ".");
        sparseArray.put(62, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context, com.android.wm.shell.R.string.keyboard_key_tab, sparseArray, 61, com.android.wm.shell.R.string.keyboard_key_space));
        sparseArray.put(67, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context, com.android.wm.shell.R.string.keyboard_key_enter, sparseArray, 66, com.android.wm.shell.R.string.keyboard_key_backspace));
        sparseArray.put(86, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context, com.android.wm.shell.R.string.keyboard_key_media_play_pause, sparseArray, 85, com.android.wm.shell.R.string.keyboard_key_media_stop));
        sparseArray.put(88, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context, com.android.wm.shell.R.string.keyboard_key_media_next, sparseArray, 87, com.android.wm.shell.R.string.keyboard_key_media_previous));
        sparseArray.put(90, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context, com.android.wm.shell.R.string.keyboard_key_media_rewind, sparseArray, 89, com.android.wm.shell.R.string.keyboard_key_media_fast_forward));
        sparseArray.put(93, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context, com.android.wm.shell.R.string.keyboard_key_page_up, sparseArray, 92, com.android.wm.shell.R.string.keyboard_key_page_down));
        sparseArray.put(96, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "A"));
        sparseArray.put(97, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "B"));
        sparseArray.put(98, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "C"));
        sparseArray.put(99, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "X"));
        sparseArray.put(100, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "Y"));
        sparseArray.put(101, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "Z"));
        sparseArray.put(102, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "L1"));
        sparseArray.put(103, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "R1"));
        sparseArray.put(104, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "L2"));
        sparseArray.put(105, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "R2"));
        sparseArray.put(108, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "Start"));
        sparseArray.put(109, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "Select"));
        sparseArray.put(110, context.getString(com.android.wm.shell.R.string.keyboard_key_button_template, "Mode"));
        sparseArray.put(111, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context, com.android.wm.shell.R.string.keyboard_key_forward_del, sparseArray, 112, com.android.wm.shell.R.string.keyboard_key_esc));
        sparseArray.put(120, "SysRq");
        sparseArray.put(121, "Break");
        sparseArray.put(116, "Scroll Lock");
        sparseArray.put(123, KeyboardShortcutListSearch$$ExternalSyntheticOutline0.m(context, com.android.wm.shell.R.string.keyboard_key_move_home, sparseArray, 122, com.android.wm.shell.R.string.keyboard_key_move_end));
        sparseArray.put(124, context.getString(com.android.wm.shell.R.string.keyboard_key_insert));
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
        sparseArray.put(143, context.getString(com.android.wm.shell.R.string.keyboard_key_num_lock));
        sparseArray.put(144, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "0"));
        sparseArray.put(145, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "1"));
        sparseArray.put(146, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "2"));
        sparseArray.put(147, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "3"));
        sparseArray.put(148, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "4"));
        sparseArray.put(149, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "5"));
        sparseArray.put(150, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "6"));
        sparseArray.put(151, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "7"));
        sparseArray.put(152, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "8"));
        sparseArray.put(153, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "9"));
        sparseArray.put(154, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "/"));
        sparseArray.put(155, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "*"));
        sparseArray.put(156, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "-"));
        sparseArray.put(157, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "+"));
        sparseArray.put(158, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "."));
        sparseArray.put(159, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, ","));
        sparseArray.put(160, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, context.getString(com.android.wm.shell.R.string.keyboard_key_enter)));
        sparseArray.put(161, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "="));
        sparseArray.put(162, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, "("));
        sparseArray.put(163, context.getString(com.android.wm.shell.R.string.keyboard_key_numpad_template, ")"));
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
        sparseArray3.put(65536, context.getDrawable(com.android.wm.shell.R.drawable.ic_ksh_key_meta));
    }

    public static void dismiss() {
        synchronized (sLock) {
            try {
                KeyboardShortcuts keyboardShortcuts = sInstance;
                if (keyboardShortcuts != null) {
                    MetricsLogger.hidden(keyboardShortcuts.mContext, 500);
                    sInstance.dismissKeyboardShortcuts();
                    sInstance = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void sanitiseShortcuts(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Iterator<KeyboardShortcutInfo> it2 = ((KeyboardShortcutGroup) it.next()).getItems().iterator();
            while (it2.hasNext()) {
                it2.next().clearIcon();
            }
        }
    }

    public static void show(int i, Context context) {
        MetricsLogger.visible(context, 500);
        synchronized (sLock) {
            try {
                KeyboardShortcuts keyboardShortcuts = sInstance;
                if (keyboardShortcuts != null && !keyboardShortcuts.mContext.equals(context)) {
                    dismiss();
                }
                if (sInstance == null) {
                    sInstance = new KeyboardShortcuts(context, null);
                }
                sInstance.showKeyboardShortcuts(i);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void toggle(int i, Context context) {
        Dialog dialog;
        synchronized (sLock) {
            try {
                KeyboardShortcuts keyboardShortcuts = sInstance;
                if ((keyboardShortcuts == null || (dialog = keyboardShortcuts.mKeyboardShortcutsDialog) == null || !dialog.isShowing()) ? false : true) {
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
        Dialog dialog = this.mKeyboardShortcutsDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mKeyboardShortcutsDialog = null;
        }
        this.mHandlerThread.quit();
    }

    public final void maybeMergeAndShowKeyboardShortcuts() {
        if (this.mReceivedAppShortcutGroups == null || this.mReceivedImeShortcutGroups == null) {
            return;
        }
        final ArrayList arrayList = new ArrayList(this.mReceivedAppShortcutGroups);
        arrayList.addAll(this.mReceivedImeShortcutGroups);
        this.mReceivedAppShortcutGroups = null;
        this.mReceivedImeShortcutGroups = null;
        KeyboardShortcutGroup keyboardShortcutGroup = this.mDefaultApplicationShortcuts;
        if (keyboardShortcutGroup != null) {
            arrayList.add(keyboardShortcutGroup);
        }
        KeyboardShortcutGroup keyboardShortcutGroup2 = new KeyboardShortcutGroup((CharSequence) this.mContext.getString(com.android.wm.shell.R.string.keyboard_shortcut_group_system), true);
        keyboardShortcutGroup2.addItem(new KeyboardShortcutInfo(this.mContext.getString(com.android.wm.shell.R.string.keyboard_shortcut_group_system_home), 66, 65536));
        keyboardShortcutGroup2.addItem(new KeyboardShortcutInfo(this.mContext.getString(com.android.wm.shell.R.string.keyboard_shortcut_group_system_back), 67, 65536));
        if (this.mContext.getResources().getBoolean(R.bool.config_imeDrawsImeNavBar)) {
            keyboardShortcutGroup2.addItem(new KeyboardShortcutInfo(this.mContext.getString(com.android.wm.shell.R.string.keyboard_shortcut_group_system_recents), 61, 2));
        }
        keyboardShortcutGroup2.addItem(new KeyboardShortcutInfo(this.mContext.getString(com.android.wm.shell.R.string.keyboard_shortcut_group_system_notifications), 42, 65536));
        keyboardShortcutGroup2.addItem(new KeyboardShortcutInfo(this.mContext.getString(com.android.wm.shell.R.string.keyboard_shortcut_group_system_shortcuts_helper), 76, 65536));
        keyboardShortcutGroup2.addItem(new KeyboardShortcutInfo(this.mContext.getString(com.android.wm.shell.R.string.keyboard_shortcut_group_system_switch_input), 62, 65536));
        arrayList.add(keyboardShortcutGroup2);
        this.mHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.KeyboardShortcuts.3
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:13:0x0172  */
            /* JADX WARN: Removed duplicated region for block: B:16:0x0189  */
            /* JADX WARN: Type inference failed for: r15v11 */
            /* JADX WARN: Type inference failed for: r15v3 */
            /* JADX WARN: Type inference failed for: r15v4 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() {
                /*
                    Method dump skipped, instructions count: 848
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.KeyboardShortcuts.AnonymousClass3.run():void");
            }
        });
    }

    public void showKeyboardShortcuts(int i) {
        PackageInfo packageInfo;
        InputDevice inputDevice;
        final int i2 = 0;
        final int i3 = 1;
        if (this.mBackgroundHandler == null) {
            this.mHandlerThread.start();
            this.mBackgroundHandler = new Handler(this.mHandlerThread.getLooper());
        }
        InputManager inputManager = (InputManager) this.mContext.getSystemService(InputManager.class);
        this.mBackupKeyCharacterMap = inputManager.getInputDevice(-1).getKeyCharacterMap();
        if (i == -1 || (inputDevice = inputManager.getInputDevice(i)) == null) {
            int[] inputDeviceIds = inputManager.getInputDeviceIds();
            int i4 = 0;
            while (true) {
                if (i4 >= inputDeviceIds.length) {
                    this.mKeyCharacterMap = this.mBackupKeyCharacterMap;
                    break;
                }
                InputDevice inputDevice2 = inputManager.getInputDevice(inputDeviceIds[i4]);
                if (inputDevice2.getId() != -1 && inputDevice2.isFullKeyboard()) {
                    this.mKeyCharacterMap = inputDevice2.getKeyCharacterMap();
                    break;
                }
                i4++;
            }
        } else {
            this.mKeyCharacterMap = inputDevice.getKeyCharacterMap();
        }
        KeyboardShortcutGroup keyboardShortcutGroup = null;
        this.mReceivedAppShortcutGroups = null;
        this.mReceivedImeShortcutGroups = null;
        int userId = this.mContext.getUserId();
        ArrayList arrayList = new ArrayList();
        ComponentName assistComponentForUser = new AssistUtils(this.mContext).getAssistComponentForUser(userId);
        if (assistComponentForUser != null) {
            try {
                packageInfo = this.mPackageManager.getPackageInfo(assistComponentForUser.getPackageName(), 0L, userId);
            } catch (RemoteException unused) {
                Log.e("KeyboardShortcuts", "PackageManagerService is dead");
                packageInfo = null;
            }
            if (packageInfo != null) {
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                arrayList.add(new KeyboardShortcutInfo(this.mContext.getString(com.android.wm.shell.R.string.keyboard_shortcut_group_applications_assist), Icon.createWithResource(applicationInfo.packageName, applicationInfo.icon), 29, 65536));
            }
        }
        KeyboardShortcutGroup applicationLaunchKeyboardShortcuts = this.mWindowManager.getApplicationLaunchKeyboardShortcuts(i);
        CharSequence label = applicationLaunchKeyboardShortcuts.getLabel();
        arrayList.addAll(applicationLaunchKeyboardShortcuts.getItems());
        if (arrayList.size() != 0) {
            Collections.sort(arrayList, this.mApplicationItemsComparator);
            keyboardShortcutGroup = new KeyboardShortcutGroup(label, arrayList, true);
        }
        this.mDefaultApplicationShortcuts = keyboardShortcutGroup;
        this.mWindowManager.requestAppKeyboardShortcuts(new WindowManager.KeyboardShortcutsReceiver(this) { // from class: com.android.systemui.statusbar.KeyboardShortcuts$$ExternalSyntheticLambda0
            public final /* synthetic */ KeyboardShortcuts f$0;

            {
                this.f$0 = this;
            }

            public final void onKeyboardShortcutsReceived(final List list) {
                int i5 = i2;
                final KeyboardShortcuts keyboardShortcuts = this.f$0;
                switch (i5) {
                    case 0:
                        final int i6 = 0;
                        keyboardShortcuts.mBackgroundHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.KeyboardShortcuts$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i6) {
                                    case 0:
                                        KeyboardShortcuts keyboardShortcuts2 = keyboardShortcuts;
                                        List list2 = list;
                                        if (list2 == null) {
                                            keyboardShortcuts2.getClass();
                                            list2 = Collections.emptyList();
                                        }
                                        keyboardShortcuts2.mReceivedAppShortcutGroups = list2;
                                        KeyboardShortcuts.sanitiseShortcuts(list2);
                                        keyboardShortcuts2.maybeMergeAndShowKeyboardShortcuts();
                                        break;
                                    default:
                                        KeyboardShortcuts keyboardShortcuts3 = keyboardShortcuts;
                                        List list3 = list;
                                        if (list3 == null) {
                                            keyboardShortcuts3.getClass();
                                            list3 = Collections.emptyList();
                                        }
                                        keyboardShortcuts3.mReceivedImeShortcutGroups = list3;
                                        KeyboardShortcuts.sanitiseShortcuts(list3);
                                        keyboardShortcuts3.maybeMergeAndShowKeyboardShortcuts();
                                        break;
                                }
                            }
                        });
                        break;
                    default:
                        final int i7 = 1;
                        keyboardShortcuts.mBackgroundHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.KeyboardShortcuts$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i7) {
                                    case 0:
                                        KeyboardShortcuts keyboardShortcuts2 = keyboardShortcuts;
                                        List list2 = list;
                                        if (list2 == null) {
                                            keyboardShortcuts2.getClass();
                                            list2 = Collections.emptyList();
                                        }
                                        keyboardShortcuts2.mReceivedAppShortcutGroups = list2;
                                        KeyboardShortcuts.sanitiseShortcuts(list2);
                                        keyboardShortcuts2.maybeMergeAndShowKeyboardShortcuts();
                                        break;
                                    default:
                                        KeyboardShortcuts keyboardShortcuts3 = keyboardShortcuts;
                                        List list3 = list;
                                        if (list3 == null) {
                                            keyboardShortcuts3.getClass();
                                            list3 = Collections.emptyList();
                                        }
                                        keyboardShortcuts3.mReceivedImeShortcutGroups = list3;
                                        KeyboardShortcuts.sanitiseShortcuts(list3);
                                        keyboardShortcuts3.maybeMergeAndShowKeyboardShortcuts();
                                        break;
                                }
                            }
                        });
                        break;
                }
            }
        }, i);
        this.mWindowManager.requestImeKeyboardShortcuts(new WindowManager.KeyboardShortcutsReceiver(this) { // from class: com.android.systemui.statusbar.KeyboardShortcuts$$ExternalSyntheticLambda0
            public final /* synthetic */ KeyboardShortcuts f$0;

            {
                this.f$0 = this;
            }

            public final void onKeyboardShortcutsReceived(final List list) {
                int i5 = i3;
                final KeyboardShortcuts keyboardShortcuts = this.f$0;
                switch (i5) {
                    case 0:
                        final int i6 = 0;
                        keyboardShortcuts.mBackgroundHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.KeyboardShortcuts$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i6) {
                                    case 0:
                                        KeyboardShortcuts keyboardShortcuts2 = keyboardShortcuts;
                                        List list2 = list;
                                        if (list2 == null) {
                                            keyboardShortcuts2.getClass();
                                            list2 = Collections.emptyList();
                                        }
                                        keyboardShortcuts2.mReceivedAppShortcutGroups = list2;
                                        KeyboardShortcuts.sanitiseShortcuts(list2);
                                        keyboardShortcuts2.maybeMergeAndShowKeyboardShortcuts();
                                        break;
                                    default:
                                        KeyboardShortcuts keyboardShortcuts3 = keyboardShortcuts;
                                        List list3 = list;
                                        if (list3 == null) {
                                            keyboardShortcuts3.getClass();
                                            list3 = Collections.emptyList();
                                        }
                                        keyboardShortcuts3.mReceivedImeShortcutGroups = list3;
                                        KeyboardShortcuts.sanitiseShortcuts(list3);
                                        keyboardShortcuts3.maybeMergeAndShowKeyboardShortcuts();
                                        break;
                                }
                            }
                        });
                        break;
                    default:
                        final int i7 = 1;
                        keyboardShortcuts.mBackgroundHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.KeyboardShortcuts$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i7) {
                                    case 0:
                                        KeyboardShortcuts keyboardShortcuts2 = keyboardShortcuts;
                                        List list2 = list;
                                        if (list2 == null) {
                                            keyboardShortcuts2.getClass();
                                            list2 = Collections.emptyList();
                                        }
                                        keyboardShortcuts2.mReceivedAppShortcutGroups = list2;
                                        KeyboardShortcuts.sanitiseShortcuts(list2);
                                        keyboardShortcuts2.maybeMergeAndShowKeyboardShortcuts();
                                        break;
                                    default:
                                        KeyboardShortcuts keyboardShortcuts3 = keyboardShortcuts;
                                        List list3 = list;
                                        if (list3 == null) {
                                            keyboardShortcuts3.getClass();
                                            list3 = Collections.emptyList();
                                        }
                                        keyboardShortcuts3.mReceivedImeShortcutGroups = list3;
                                        KeyboardShortcuts.sanitiseShortcuts(list3);
                                        keyboardShortcuts3.maybeMergeAndShowKeyboardShortcuts();
                                        break;
                                }
                            }
                        });
                        break;
                }
            }
        }, i);
    }
}
