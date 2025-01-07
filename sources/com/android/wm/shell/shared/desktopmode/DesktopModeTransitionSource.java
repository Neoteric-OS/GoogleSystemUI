package com.android.wm.shell.shared.desktopmode;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeTransitionSource implements Parcelable {
    public static final /* synthetic */ DesktopModeTransitionSource[] $VALUES;
    public static final DesktopModeTransitionSource APP_HANDLE_MENU_BUTTON;
    public static final Parcelable.Creator CREATOR;
    public static final DesktopModeTransitionSource KEYBOARD_SHORTCUT;
    public static final DesktopModeTransitionSource TASK_DRAG;
    public static final DesktopModeTransitionSource UNKNOWN;

    static {
        DesktopModeTransitionSource desktopModeTransitionSource = new DesktopModeTransitionSource("TASK_DRAG", 0);
        TASK_DRAG = desktopModeTransitionSource;
        DesktopModeTransitionSource desktopModeTransitionSource2 = new DesktopModeTransitionSource("APP_FROM_OVERVIEW", 1);
        DesktopModeTransitionSource desktopModeTransitionSource3 = new DesktopModeTransitionSource("APP_HANDLE_MENU_BUTTON", 2);
        APP_HANDLE_MENU_BUTTON = desktopModeTransitionSource3;
        DesktopModeTransitionSource desktopModeTransitionSource4 = new DesktopModeTransitionSource("KEYBOARD_SHORTCUT", 3);
        KEYBOARD_SHORTCUT = desktopModeTransitionSource4;
        DesktopModeTransitionSource desktopModeTransitionSource5 = new DesktopModeTransitionSource("UNKNOWN", 4);
        UNKNOWN = desktopModeTransitionSource5;
        DesktopModeTransitionSource[] desktopModeTransitionSourceArr = {desktopModeTransitionSource, desktopModeTransitionSource2, desktopModeTransitionSource3, desktopModeTransitionSource4, desktopModeTransitionSource5};
        $VALUES = desktopModeTransitionSourceArr;
        EnumEntriesKt.enumEntries(desktopModeTransitionSourceArr);
        CREATOR = new DesktopModeTransitionSource$Companion$CREATOR$1();
    }

    public static DesktopModeTransitionSource valueOf(String str) {
        return (DesktopModeTransitionSource) Enum.valueOf(DesktopModeTransitionSource.class, str);
    }

    public static DesktopModeTransitionSource[] values() {
        return (DesktopModeTransitionSource[]) $VALUES.clone();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
