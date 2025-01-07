package com.android.systemui.volume.panel.component.selector.ui.composable;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class RadioButtonBarComponent {
    public static final /* synthetic */ RadioButtonBarComponent[] $VALUES;
    public static final RadioButtonBarComponent Buttons;
    public static final RadioButtonBarComponent ButtonsBackground;
    public static final RadioButtonBarComponent Indicator;
    public static final RadioButtonBarComponent Labels;
    private final float zIndex;

    static {
        RadioButtonBarComponent radioButtonBarComponent = new RadioButtonBarComponent(0.0f, 0, "ButtonsBackground");
        ButtonsBackground = radioButtonBarComponent;
        RadioButtonBarComponent radioButtonBarComponent2 = new RadioButtonBarComponent(1.0f, 1, "Indicator");
        Indicator = radioButtonBarComponent2;
        RadioButtonBarComponent radioButtonBarComponent3 = new RadioButtonBarComponent(2.0f, 2, "Buttons");
        Buttons = radioButtonBarComponent3;
        RadioButtonBarComponent radioButtonBarComponent4 = new RadioButtonBarComponent(2.0f, 3, "Labels");
        Labels = radioButtonBarComponent4;
        RadioButtonBarComponent[] radioButtonBarComponentArr = {radioButtonBarComponent, radioButtonBarComponent2, radioButtonBarComponent3, radioButtonBarComponent4};
        $VALUES = radioButtonBarComponentArr;
        EnumEntriesKt.enumEntries(radioButtonBarComponentArr);
    }

    public RadioButtonBarComponent(float f, int i, String str) {
        this.zIndex = f;
    }

    public static RadioButtonBarComponent valueOf(String str) {
        return (RadioButtonBarComponent) Enum.valueOf(RadioButtonBarComponent.class, str);
    }

    public static RadioButtonBarComponent[] values() {
        return (RadioButtonBarComponent[]) $VALUES.clone();
    }

    public final float getZIndex() {
        return this.zIndex;
    }
}
