package com.android.keyguard;

import android.content.Context;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.widget.LinearLayout;
import com.android.systemui.statusbar.notification.AnimatableProperty;
import com.android.wm.shell.R;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardStatusAreaView extends LinearLayout {
    public static final AnimatableProperty.AnonymousClass7 TRANSLATE_X_AOD = null;
    public static final AnimatableProperty.AnonymousClass7 TRANSLATE_X_CLOCK_DESIGN = null;
    public static final AnimatableProperty.AnonymousClass7 TRANSLATE_Y_CLOCK_DESIGN = null;
    public static final AnimatableProperty.AnonymousClass7 TRANSLATE_Y_CLOCK_SIZE = null;
    public float translateXFromAod;
    public float translateXFromClockDesign;
    public float translateYFromClockDesign;
    public float translateYFromClockSize;

    static {
        final String str = "TranslateXClockDesign";
        final int i = 1;
        FloatProperty floatProperty = new FloatProperty(str) { // from class: com.android.keyguard.KeyguardStatusAreaView$Companion$TRANSLATE_X_AOD$1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i) {
                    case 0:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateXFromAod);
                    case 1:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateXFromClockDesign);
                    case 2:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateYFromClockDesign);
                    default:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateYFromClockSize);
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i) {
                    case 0:
                        KeyguardStatusAreaView keyguardStatusAreaView = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView.translateXFromAod = f;
                        keyguardStatusAreaView.setTranslationX(f + keyguardStatusAreaView.translateXFromClockDesign + 0.0f);
                        break;
                    case 1:
                        KeyguardStatusAreaView keyguardStatusAreaView2 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView2.translateXFromClockDesign = f;
                        keyguardStatusAreaView2.setTranslationX(keyguardStatusAreaView2.translateXFromAod + f + 0.0f);
                        break;
                    case 2:
                        KeyguardStatusAreaView keyguardStatusAreaView3 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView3.translateYFromClockDesign = f;
                        keyguardStatusAreaView3.setTranslationY(f + keyguardStatusAreaView3.translateYFromClockSize);
                        break;
                    default:
                        KeyguardStatusAreaView keyguardStatusAreaView4 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView4.translateYFromClockSize = f;
                        keyguardStatusAreaView4.setTranslationY(f + keyguardStatusAreaView4.translateYFromClockDesign);
                        break;
                }
            }
        };
        AnimatableProperty.AnonymousClass7 anonymousClass7 = AnimatableProperty.Y;
        new AnimatableProperty.AnonymousClass7(R.id.translate_x_clock_design_animator_start_tag, R.id.translate_x_clock_design_animator_end_tag, R.id.translate_x_clock_design_animator_tag, floatProperty);
        final String str2 = "TranslateXAod";
        final int i2 = 0;
        new AnimatableProperty.AnonymousClass7(R.id.translate_x_aod_animator_start_tag, R.id.translate_x_aod_animator_end_tag, R.id.translate_x_aod_animator_tag, new FloatProperty(str2) { // from class: com.android.keyguard.KeyguardStatusAreaView$Companion$TRANSLATE_X_AOD$1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i2) {
                    case 0:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateXFromAod);
                    case 1:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateXFromClockDesign);
                    case 2:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateYFromClockDesign);
                    default:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateYFromClockSize);
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i2) {
                    case 0:
                        KeyguardStatusAreaView keyguardStatusAreaView = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView.translateXFromAod = f;
                        keyguardStatusAreaView.setTranslationX(f + keyguardStatusAreaView.translateXFromClockDesign + 0.0f);
                        break;
                    case 1:
                        KeyguardStatusAreaView keyguardStatusAreaView2 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView2.translateXFromClockDesign = f;
                        keyguardStatusAreaView2.setTranslationX(keyguardStatusAreaView2.translateXFromAod + f + 0.0f);
                        break;
                    case 2:
                        KeyguardStatusAreaView keyguardStatusAreaView3 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView3.translateYFromClockDesign = f;
                        keyguardStatusAreaView3.setTranslationY(f + keyguardStatusAreaView3.translateYFromClockSize);
                        break;
                    default:
                        KeyguardStatusAreaView keyguardStatusAreaView4 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView4.translateYFromClockSize = f;
                        keyguardStatusAreaView4.setTranslationY(f + keyguardStatusAreaView4.translateYFromClockDesign);
                        break;
                }
            }
        });
        final String str3 = "TranslateYClockSize";
        final int i3 = 3;
        new AnimatableProperty.AnonymousClass7(R.id.translate_y_clock_size_animator_start_tag, R.id.translate_y_clock_size_animator_end_tag, R.id.translate_y_clock_size_animator_tag, new FloatProperty(str3) { // from class: com.android.keyguard.KeyguardStatusAreaView$Companion$TRANSLATE_X_AOD$1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i3) {
                    case 0:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateXFromAod);
                    case 1:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateXFromClockDesign);
                    case 2:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateYFromClockDesign);
                    default:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateYFromClockSize);
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i3) {
                    case 0:
                        KeyguardStatusAreaView keyguardStatusAreaView = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView.translateXFromAod = f;
                        keyguardStatusAreaView.setTranslationX(f + keyguardStatusAreaView.translateXFromClockDesign + 0.0f);
                        break;
                    case 1:
                        KeyguardStatusAreaView keyguardStatusAreaView2 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView2.translateXFromClockDesign = f;
                        keyguardStatusAreaView2.setTranslationX(keyguardStatusAreaView2.translateXFromAod + f + 0.0f);
                        break;
                    case 2:
                        KeyguardStatusAreaView keyguardStatusAreaView3 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView3.translateYFromClockDesign = f;
                        keyguardStatusAreaView3.setTranslationY(f + keyguardStatusAreaView3.translateYFromClockSize);
                        break;
                    default:
                        KeyguardStatusAreaView keyguardStatusAreaView4 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView4.translateYFromClockSize = f;
                        keyguardStatusAreaView4.setTranslationY(f + keyguardStatusAreaView4.translateYFromClockDesign);
                        break;
                }
            }
        });
        final String str4 = "TranslateYClockDesign";
        final int i4 = 2;
        new AnimatableProperty.AnonymousClass7(R.id.translate_y_clock_design_animator_start_tag, R.id.translate_y_clock_design_animator_end_tag, R.id.translate_y_clock_design_animator_tag, new FloatProperty(str4) { // from class: com.android.keyguard.KeyguardStatusAreaView$Companion$TRANSLATE_X_AOD$1
            @Override // android.util.Property
            public final Float get(Object obj) {
                switch (i4) {
                    case 0:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateXFromAod);
                    case 1:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateXFromClockDesign);
                    case 2:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateYFromClockDesign);
                    default:
                        return Float.valueOf(((KeyguardStatusAreaView) obj).translateYFromClockSize);
                }
            }

            @Override // android.util.FloatProperty
            public final void setValue(Object obj, float f) {
                switch (i4) {
                    case 0:
                        KeyguardStatusAreaView keyguardStatusAreaView = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView.translateXFromAod = f;
                        keyguardStatusAreaView.setTranslationX(f + keyguardStatusAreaView.translateXFromClockDesign + 0.0f);
                        break;
                    case 1:
                        KeyguardStatusAreaView keyguardStatusAreaView2 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView2.translateXFromClockDesign = f;
                        keyguardStatusAreaView2.setTranslationX(keyguardStatusAreaView2.translateXFromAod + f + 0.0f);
                        break;
                    case 2:
                        KeyguardStatusAreaView keyguardStatusAreaView3 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView3.translateYFromClockDesign = f;
                        keyguardStatusAreaView3.setTranslationY(f + keyguardStatusAreaView3.translateYFromClockSize);
                        break;
                    default:
                        KeyguardStatusAreaView keyguardStatusAreaView4 = (KeyguardStatusAreaView) obj;
                        keyguardStatusAreaView4.translateYFromClockSize = f;
                        keyguardStatusAreaView4.setTranslationY(f + keyguardStatusAreaView4.translateYFromClockDesign);
                        break;
                }
            }
        });
    }

    public /* synthetic */ KeyguardStatusAreaView(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    public KeyguardStatusAreaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
