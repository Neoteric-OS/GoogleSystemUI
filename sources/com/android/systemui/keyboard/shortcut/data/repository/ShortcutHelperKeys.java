package com.android.systemui.keyboard.shortcut.data.repository;

import android.content.Context;
import com.android.wm.shell.R;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ShortcutHelperKeys {
    public static final Map keyIcons = MapsKt.mapOf(new Pair(65536, Integer.valueOf(R.drawable.ic_ksh_key_meta)), new Pair(4, Integer.valueOf(R.drawable.ic_arrow_back_2)), new Pair(3, Integer.valueOf(R.drawable.ic_radio_button_unchecked)), new Pair(312, Integer.valueOf(R.drawable.ic_check_box_outline_blank)));
    public static final Map specialKeyLabels = MapsKt.mapOf(new Pair(3, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_home);
        }
    }), new Pair(4, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_back);
        }
    }), new Pair(19, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$3
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_dpad_up);
        }
    }), new Pair(20, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$4
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_dpad_down);
        }
    }), new Pair(21, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$5
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_dpad_left);
        }
    }), new Pair(22, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$6
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_dpad_right);
        }
    }), new Pair(23, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$7
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_dpad_center);
        }
    }), new Pair(56, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$8
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return ".";
        }
    }), new Pair(61, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$9
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_tab);
        }
    }), new Pair(62, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$10
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_space);
        }
    }), new Pair(66, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$11
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_enter);
        }
    }), new Pair(67, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$12
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_backspace);
        }
    }), new Pair(85, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$13
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_media_play_pause);
        }
    }), new Pair(86, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$14
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_media_stop);
        }
    }), new Pair(87, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$15
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_media_next);
        }
    }), new Pair(88, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$16
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_media_previous);
        }
    }), new Pair(89, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$17
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_media_rewind);
        }
    }), new Pair(90, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$18
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_media_fast_forward);
        }
    }), new Pair(92, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$19
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_page_up);
        }
    }), new Pair(93, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$20
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_page_down);
        }
    }), new Pair(96, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$21
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "A");
        }
    }), new Pair(97, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$22
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "B");
        }
    }), new Pair(98, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$23
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "C");
        }
    }), new Pair(99, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$24
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "X");
        }
    }), new Pair(100, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$25
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "Y");
        }
    }), new Pair(101, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$26
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "Z");
        }
    }), new Pair(102, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$27
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "L1");
        }
    }), new Pair(103, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$28
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "R1");
        }
    }), new Pair(104, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$29
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "L2");
        }
    }), new Pair(105, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$30
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "R2");
        }
    }), new Pair(108, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$31
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "Start");
        }
    }), new Pair(109, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$32
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "Select");
        }
    }), new Pair(110, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$33
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_button_template, "Mode");
        }
    }), new Pair(112, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$34
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_forward_del);
        }
    }), new Pair(111, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$35
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_esc);
        }
    }), new Pair(120, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$36
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "SysRq";
        }
    }), new Pair(121, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$37
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Break";
        }
    }), new Pair(116, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$38
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Scroll Lock";
        }
    }), new Pair(122, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$39
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_move_home);
        }
    }), new Pair(123, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$40
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_move_end);
        }
    }), new Pair(124, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$41
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_insert);
        }
    }), new Pair(131, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$42
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F1";
        }
    }), new Pair(132, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$43
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F2";
        }
    }), new Pair(133, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$44
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F3";
        }
    }), new Pair(134, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$45
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F4";
        }
    }), new Pair(135, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$46
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F5";
        }
    }), new Pair(136, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$47
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F6";
        }
    }), new Pair(137, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$48
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F7";
        }
    }), new Pair(138, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$49
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F8";
        }
    }), new Pair(139, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$50
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F9";
        }
    }), new Pair(140, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$51
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F10";
        }
    }), new Pair(141, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$52
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F11";
        }
    }), new Pair(142, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$53
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "F12";
        }
    }), new Pair(143, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$54
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_num_lock);
        }
    }), new Pair(69, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$55
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "-";
        }
    }), new Pair(68, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$56
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "`";
        }
    }), new Pair(70, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$57
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "=";
        }
    }), new Pair(144, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$58
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "0");
        }
    }), new Pair(145, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$59
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "1");
        }
    }), new Pair(146, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$60
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "2");
        }
    }), new Pair(147, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$61
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "3");
        }
    }), new Pair(148, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$62
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "4");
        }
    }), new Pair(149, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$63
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "5");
        }
    }), new Pair(150, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$64
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "6");
        }
    }), new Pair(151, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$65
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "7");
        }
    }), new Pair(152, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$66
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "8");
        }
    }), new Pair(153, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$67
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "9");
        }
    }), new Pair(154, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$68
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "/");
        }
    }), new Pair(155, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$69
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "*");
        }
    }), new Pair(156, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$70
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "-");
        }
    }), new Pair(157, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$71
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "+");
        }
    }), new Pair(158, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$72
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, ".");
        }
    }), new Pair(159, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$73
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, ",");
        }
    }), new Pair(160, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$74
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            Context context = (Context) obj;
            return context.getString(R.string.keyboard_key_numpad_template, context.getString(R.string.keyboard_key_enter));
        }
    }), new Pair(161, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$75
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "=");
        }
    }), new Pair(162, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$76
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, "(");
        }
    }), new Pair(163, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$77
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return ((Context) obj).getString(R.string.keyboard_key_numpad_template, ")");
        }
    }), new Pair(211, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$78
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "半角/全角";
        }
    }), new Pair(212, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$79
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "英数";
        }
    }), new Pair(213, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$80
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "無変換";
        }
    }), new Pair(214, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$81
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "変換";
        }
    }), new Pair(215, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$82
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "かな";
        }
    }), new Pair(57, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$83
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Alt";
        }
    }), new Pair(58, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$84
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Alt";
        }
    }), new Pair(113, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$85
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Ctrl";
        }
    }), new Pair(114, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$86
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Ctrl";
        }
    }), new Pair(59, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$87
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Shift";
        }
    }), new Pair(60, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$88
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Shift";
        }
    }), new Pair(65536, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$89
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Meta";
        }
    }), new Pair(4096, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$90
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Ctrl";
        }
    }), new Pair(2, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$91
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Alt";
        }
    }), new Pair(1, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$92
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Shift";
        }
    }), new Pair(4, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$93
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Sym";
        }
    }), new Pair(8, new Function1() { // from class: com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperKeys$specialKeyLabels$94
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return "Fn";
        }
    }));
}
