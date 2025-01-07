package com.android.systemui.model;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.util.Log;
import com.android.systemui.Dumpable;
import com.android.systemui.model.SysUiState;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.shared.system.QuickStepContract;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SysUiState implements Dumpable {
    public final DisplayTracker mDisplayTracker;
    public long mFlags;
    public final SceneContainerPlugin mSceneContainerPlugin;
    public final List mCallbacks = new ArrayList();
    public long mFlagsToSet = 0;
    public long mFlagsToClear = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface SysUiStateCallback {
        void onSystemUiStateChanged(long j);
    }

    public SysUiState(DisplayTracker displayTracker, SceneContainerPlugin sceneContainerPlugin) {
        this.mDisplayTracker = displayTracker;
        this.mSceneContainerPlugin = sceneContainerPlugin;
    }

    public final void commitUpdate(int i) {
        this.mDisplayTracker.getClass();
        if (i != 0) {
            Log.w("SysUiState", AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Ignoring flag update for display: "), new Throwable());
        } else {
            long j = this.mFlags;
            final long j2 = (this.mFlagsToSet | j) & (~this.mFlagsToClear);
            if (j2 != j) {
                ((ArrayList) this.mCallbacks).forEach(new Consumer() { // from class: com.android.systemui.model.SysUiState$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((SysUiState.SysUiStateCallback) obj).onSystemUiStateChanged(j2);
                    }
                });
                this.mFlags = j2;
            }
        }
        this.mFlagsToSet = 0L;
        this.mFlagsToClear = 0L;
    }

    @Override // com.android.systemui.Dumpable
    @NeverCompile
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("SysUiState state:");
        printWriter.print("  mSysUiStateFlags=");
        printWriter.println(this.mFlags);
        StringBuilder sb = new StringBuilder("    ");
        long j = this.mFlags;
        StringJoiner stringJoiner = new StringJoiner("|");
        if ((1 & j) != 0) {
            stringJoiner.add("screen_pinned");
        }
        if ((128 & j) != 0) {
            stringJoiner.add("overview_disabled");
        }
        if ((256 & j) != 0) {
            stringJoiner.add("home_disabled");
        }
        if ((1024 & j) != 0) {
            stringJoiner.add("search_disabled");
        }
        if ((2 & j) != 0) {
            stringJoiner.add("navbar_hidden");
        }
        if ((j & 4) != 0) {
            stringJoiner.add("notif_expanded");
        }
        if ((2048 & j) != 0) {
            stringJoiner.add("qs_visible");
        }
        if ((j & 64) != 0) {
            stringJoiner.add("keygrd_visible");
        }
        if ((512 & j) != 0) {
            stringJoiner.add("keygrd_occluded");
        }
        if ((8 & j) != 0) {
            stringJoiner.add("bouncer_visible");
        }
        if ((32768 & j) != 0) {
            stringJoiner.add("dialog_showing");
        }
        if ((16 & j) != 0) {
            stringJoiner.add("a11y_click");
        }
        if ((32 & j) != 0) {
            stringJoiner.add("a11y_long_click");
        }
        if ((4096 & j) != 0) {
            stringJoiner.add("disable_gesture_split_invocation");
        }
        if ((8192 & j) != 0) {
            stringJoiner.add("asst_gesture_constrain");
        }
        if ((16384 & j) != 0) {
            stringJoiner.add("bubbles_expanded");
        }
        if ((65536 & j) != 0) {
            stringJoiner.add("one_handed_active");
        }
        if ((j & 131072) != 0) {
            stringJoiner.add("allow_gesture");
        }
        if ((262144 & j) != 0) {
            stringJoiner.add("ime_visible");
        }
        if ((524288 & j) != 0) {
            stringJoiner.add("magnification_overlap");
        }
        if ((1048576 & j) != 0) {
            stringJoiner.add("ime_switcher_showing");
        }
        if ((2097152 & j) != 0) {
            stringJoiner.add("device_dozing");
        }
        if ((4194304 & j) != 0) {
            stringJoiner.add("back_disabled");
        }
        if ((8388608 & j) != 0) {
            stringJoiner.add("bubbles_mange_menu_expanded");
        }
        if ((33554432 & j) != 0) {
            stringJoiner.add("vis_win_showing");
        }
        if ((67108864 & j) != 0) {
            stringJoiner.add("freeform_active_in_desktop_mode");
        }
        if ((134217728 & j) != 0) {
            stringJoiner.add("device_dreaming");
        }
        if ((536870912 & j) != 0) {
            stringJoiner.add("wakefulness_transition");
        }
        if ((268435456 & j) != 0) {
            stringJoiner.add("awake");
        }
        if ((1073741824 & j) != 0) {
            stringJoiner.add("notif_visible");
        }
        if ((2147483648L & j) != 0) {
            stringJoiner.add("keygrd_going_away");
        }
        if ((4294967296L & j) != 0) {
            stringJoiner.add("shortcut_helper_showing");
        }
        if ((8589934592L & j) != 0) {
            stringJoiner.add("touchpad_gestures_disabled");
        }
        if ((17179869184L & j) != 0) {
            stringJoiner.add("disable_gesture_pip_animating");
        }
        if ((j & 34359738368L) != 0) {
            stringJoiner.add("communal_hub_showing");
        }
        sb.append(stringJoiner.toString());
        printWriter.println(sb.toString());
        printWriter.print("    backGestureDisabled=");
        boolean z = false;
        printWriter.println(QuickStepContract.isBackGestureDisabled(this.mFlags, false));
        printWriter.print("    assistantGestureDisabled=");
        long j2 = this.mFlags;
        if ((131072 & j2) != 0) {
            j2 &= -3;
        }
        if ((3083 & j2) != 0 || ((4 & j2) != 0 && (j2 & 64) == 0)) {
            z = true;
        }
        printWriter.println(z);
    }

    public final SysUiState setFlag(long j, boolean z) {
        this.mSceneContainerPlugin.getClass();
        if (z) {
            this.mFlagsToSet = j | this.mFlagsToSet;
        } else {
            this.mFlagsToClear = j | this.mFlagsToClear;
        }
        return this;
    }
}
