package com.android.wm.shell.windowdecor;

import android.animation.AnimatorSet;
import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.windowdecor.additionalviewcontainer.AdditionalViewHostViewContainer;
import com.android.wm.shell.windowdecor.common.DecorThemeUtil;
import java.util.ArrayList;
import java.util.function.Supplier;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MaximizeMenu {
    public final Context decorWindowContext;
    public SurfaceControl leash;
    public AdditionalViewHostViewContainer maximizeMenu;
    public MaximizeMenuView maximizeMenuView;
    public final PointF menuPosition;
    public SurfaceControlViewHost viewHost;
    public final float cornerRadius = loadDimensionPixelSize(R.dimen.desktop_mode_maximize_menu_corner_radius);
    public final int menuWidth = loadDimensionPixelSize(R.dimen.desktop_mode_maximize_menu_width);
    public final int menuHeight = loadDimensionPixelSize(R.dimen.desktop_mode_maximize_menu_height);
    public final int menuPadding = loadDimensionPixelSize(R.dimen.desktop_mode_menu_padding);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MaximizeMenuView {
        public final View container;
        public final DecorThemeUtil decorThemeUtil;
        public final int fillPadding;
        public final int fillRadius;
        public final Rect hoverTempRect;
        public final Button maximizeButton;
        public final TextView maximizeText;
        public AnimatorSet menuAnimatorSet;
        public final int menuHeight;
        public final int menuPadding;
        public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 onLeftSnapClickListener;
        public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 onMaximizeClickListener;
        public DesktopModeWindowDecoration$$ExternalSyntheticLambda6 onMenuHoverListener;
        public DesktopModeWindowDecoration$$ExternalSyntheticLambda3 onOutsideTouchListener;
        public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 onRightSnapClickListener;
        public final int outlineRadius;
        public final int outlineStroke;
        public final ViewGroup rootView;
        public final View snapButtonsLayout;
        public final Button snapLeftButton;
        public final Button snapRightButton;
        public final TextView snapWindowText;
        public MenuStyle style;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class MenuStyle {
            public final int backgroundColor;
            public final MaximizeOption maximizeOption;
            public final SnapOptions snapOptions;
            public final int textColor;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            public final class MaximizeOption {
                public final StateListDrawable drawable;

                public MaximizeOption(StateListDrawable stateListDrawable) {
                    this.drawable = stateListDrawable;
                }

                public final boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return (obj instanceof MaximizeOption) && Intrinsics.areEqual(this.drawable, ((MaximizeOption) obj).drawable);
                }

                public final int hashCode() {
                    return this.drawable.hashCode();
                }

                public final String toString() {
                    return "MaximizeOption(drawable=" + this.drawable + ")";
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            public final class SnapOptions {
                public final int activeBackgroundColor;
                public final int activeSnapSideColor;
                public final int activeStrokeColor;
                public final int inactiveBackgroundColor;
                public final int inactiveSnapSideColor;
                public final int inactiveStrokeColor;
                public final int semiActiveSnapSideColor;

                public SnapOptions(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
                    this.inactiveSnapSideColor = i;
                    this.semiActiveSnapSideColor = i2;
                    this.activeSnapSideColor = i3;
                    this.inactiveStrokeColor = i4;
                    this.activeStrokeColor = i5;
                    this.inactiveBackgroundColor = i6;
                    this.activeBackgroundColor = i7;
                }

                public final boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof SnapOptions)) {
                        return false;
                    }
                    SnapOptions snapOptions = (SnapOptions) obj;
                    return this.inactiveSnapSideColor == snapOptions.inactiveSnapSideColor && this.semiActiveSnapSideColor == snapOptions.semiActiveSnapSideColor && this.activeSnapSideColor == snapOptions.activeSnapSideColor && this.inactiveStrokeColor == snapOptions.inactiveStrokeColor && this.activeStrokeColor == snapOptions.activeStrokeColor && this.inactiveBackgroundColor == snapOptions.inactiveBackgroundColor && this.activeBackgroundColor == snapOptions.activeBackgroundColor;
                }

                public final int hashCode() {
                    return Integer.hashCode(this.activeBackgroundColor) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.inactiveBackgroundColor, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.activeStrokeColor, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.inactiveStrokeColor, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.activeSnapSideColor, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.semiActiveSnapSideColor, Integer.hashCode(this.inactiveSnapSideColor) * 31, 31), 31), 31), 31), 31);
                }

                public final String toString() {
                    StringBuilder sb = new StringBuilder("SnapOptions(inactiveSnapSideColor=");
                    sb.append(this.inactiveSnapSideColor);
                    sb.append(", semiActiveSnapSideColor=");
                    sb.append(this.semiActiveSnapSideColor);
                    sb.append(", activeSnapSideColor=");
                    sb.append(this.activeSnapSideColor);
                    sb.append(", inactiveStrokeColor=");
                    sb.append(this.inactiveStrokeColor);
                    sb.append(", activeStrokeColor=");
                    sb.append(this.activeStrokeColor);
                    sb.append(", inactiveBackgroundColor=");
                    sb.append(this.inactiveBackgroundColor);
                    sb.append(", activeBackgroundColor=");
                    return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.activeBackgroundColor, ")");
                }
            }

            public MenuStyle(int i, int i2, MaximizeOption maximizeOption, SnapOptions snapOptions) {
                this.backgroundColor = i;
                this.textColor = i2;
                this.maximizeOption = maximizeOption;
                this.snapOptions = snapOptions;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof MenuStyle)) {
                    return false;
                }
                MenuStyle menuStyle = (MenuStyle) obj;
                return this.backgroundColor == menuStyle.backgroundColor && this.textColor == menuStyle.textColor && Intrinsics.areEqual(this.maximizeOption, menuStyle.maximizeOption) && Intrinsics.areEqual(this.snapOptions, menuStyle.snapOptions);
            }

            public final int hashCode() {
                return this.snapOptions.hashCode() + ((this.maximizeOption.drawable.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.textColor, Integer.hashCode(this.backgroundColor) * 31, 31)) * 31);
            }

            public final String toString() {
                return "MenuStyle(backgroundColor=" + this.backgroundColor + ", textColor=" + this.textColor + ", maximizeOption=" + this.maximizeOption + ", snapOptions=" + this.snapOptions + ")";
            }
        }

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class SnapToHalfSelection {
            public static final /* synthetic */ SnapToHalfSelection[] $VALUES;
            public static final SnapToHalfSelection LEFT;
            public static final SnapToHalfSelection NONE;
            public static final SnapToHalfSelection RIGHT;

            static {
                SnapToHalfSelection snapToHalfSelection = new SnapToHalfSelection("NONE", 0);
                NONE = snapToHalfSelection;
                SnapToHalfSelection snapToHalfSelection2 = new SnapToHalfSelection("LEFT", 1);
                LEFT = snapToHalfSelection2;
                SnapToHalfSelection snapToHalfSelection3 = new SnapToHalfSelection("RIGHT", 2);
                RIGHT = snapToHalfSelection3;
                SnapToHalfSelection[] snapToHalfSelectionArr = {snapToHalfSelection, snapToHalfSelection2, snapToHalfSelection3};
                $VALUES = snapToHalfSelectionArr;
                EnumEntriesKt.enumEntries(snapToHalfSelectionArr);
            }

            public static SnapToHalfSelection valueOf(String str) {
                return (SnapToHalfSelection) Enum.valueOf(SnapToHalfSelection.class, str);
            }

            public static SnapToHalfSelection[] values() {
                return (SnapToHalfSelection[]) $VALUES.clone();
            }
        }

        public MaximizeMenuView(int i, int i2, Context context) {
            final int i3 = 1;
            this.menuHeight = i;
            this.menuPadding = i2;
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.desktop_mode_window_decor_maximize_menu, (ViewGroup) null);
            this.rootView = viewGroup;
            this.container = viewGroup.requireViewById(R.id.container);
            View requireViewById = viewGroup.requireViewById(R.id.maximize_menu_overlay);
            TextView textView = (TextView) viewGroup.requireViewById(R.id.maximize_menu_maximize_window_text);
            this.maximizeText = textView;
            Button button = (Button) viewGroup.requireViewById(R.id.maximize_menu_maximize_button);
            this.maximizeButton = button;
            this.snapWindowText = (TextView) viewGroup.requireViewById(R.id.maximize_menu_snap_window_text);
            Button button2 = (Button) viewGroup.requireViewById(R.id.maximize_menu_snap_right_button);
            this.snapRightButton = button2;
            Button button3 = (Button) viewGroup.requireViewById(R.id.maximize_menu_snap_left_button);
            this.snapLeftButton = button3;
            this.snapButtonsLayout = viewGroup.requireViewById(R.id.maximize_menu_snap_menu_layout);
            this.decorThemeUtil = new DecorThemeUtil(context);
            this.outlineRadius = context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_maximize_menu_buttons_outline_radius);
            this.outlineStroke = context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_maximize_menu_buttons_outline_stroke);
            this.fillPadding = context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_maximize_menu_buttons_fill_padding);
            this.fillRadius = context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_maximize_menu_buttons_fill_radius);
            this.hoverTempRect = new Rect();
            requireViewById.setOnHoverListener(new View.OnHoverListener() { // from class: com.android.wm.shell.windowdecor.MaximizeMenu.MaximizeMenuView.1
                @Override // android.view.View.OnHoverListener
                public final boolean onHover(View view, MotionEvent motionEvent) {
                    DesktopModeWindowDecoration$$ExternalSyntheticLambda6 desktopModeWindowDecoration$$ExternalSyntheticLambda6;
                    int action = motionEvent.getAction();
                    if (action == 9) {
                        DesktopModeWindowDecoration$$ExternalSyntheticLambda6 desktopModeWindowDecoration$$ExternalSyntheticLambda62 = MaximizeMenuView.this.onMenuHoverListener;
                        if (desktopModeWindowDecoration$$ExternalSyntheticLambda62 != null) {
                            desktopModeWindowDecoration$$ExternalSyntheticLambda62.invoke(Boolean.TRUE);
                        }
                    } else if (action == 10 && (desktopModeWindowDecoration$$ExternalSyntheticLambda6 = MaximizeMenuView.this.onMenuHoverListener) != null) {
                        desktopModeWindowDecoration$$ExternalSyntheticLambda6.invoke(Boolean.FALSE);
                    }
                    MaximizeMenuView maximizeMenuView = MaximizeMenuView.this;
                    Rect rect = maximizeMenuView.hoverTempRect;
                    maximizeMenuView.snapButtonsLayout.getDrawingRect(rect);
                    maximizeMenuView.rootView.offsetDescendantRectToMyCoords(maximizeMenuView.snapButtonsLayout, rect);
                    if (motionEvent.getAction() != 9 && motionEvent.getAction() != 7) {
                        return false;
                    }
                    if (!rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                        MaximizeMenuView.this.updateSplitSnapSelection(SnapToHalfSelection.NONE);
                        return false;
                    }
                    if (motionEvent.getX() < rect.centerX()) {
                        MaximizeMenuView.this.updateSplitSnapSelection(SnapToHalfSelection.LEFT);
                        return false;
                    }
                    MaximizeMenuView.this.updateSplitSnapSelection(SnapToHalfSelection.RIGHT);
                    return false;
                }
            });
            final int i4 = 0;
            button.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.windowdecor.MaximizeMenu.MaximizeMenuView.2
                public final /* synthetic */ MaximizeMenuView this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i4) {
                        case 0:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.this$0.onMaximizeClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 1:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.this$0.onRightSnapClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        default:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.this$0.onLeftSnapClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33.invoke();
                                break;
                            }
                            break;
                    }
                }
            });
            button2.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.windowdecor.MaximizeMenu.MaximizeMenuView.2
                public final /* synthetic */ MaximizeMenuView this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i3) {
                        case 0:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.this$0.onMaximizeClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 1:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.this$0.onRightSnapClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        default:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.this$0.onLeftSnapClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33.invoke();
                                break;
                            }
                            break;
                    }
                }
            });
            final int i5 = 2;
            button3.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.windowdecor.MaximizeMenu.MaximizeMenuView.2
                public final /* synthetic */ MaximizeMenuView this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i5) {
                        case 0:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.this$0.onMaximizeClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 1:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.this$0.onRightSnapClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        default:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.this$0.onLeftSnapClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33.invoke();
                                break;
                            }
                            break;
                    }
                }
            });
            viewGroup.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.wm.shell.windowdecor.MaximizeMenu.MaximizeMenuView.5
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getActionMasked() != 4) {
                        return true;
                    }
                    DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = MaximizeMenuView.this.onOutsideTouchListener;
                    if (desktopModeWindowDecoration$$ExternalSyntheticLambda3 == null) {
                        return false;
                    }
                    desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                    return false;
                }
            });
            button.setLayerType(1, null);
            textView.setLayerType(1, null);
        }

        public final void activateSnapOption(boolean z) {
            View view = this.snapButtonsLayout;
            view.setBackgroundResource(R.drawable.desktop_mode_maximize_menu_layout_background_on_hover);
            GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground();
            MenuStyle menuStyle = this.style;
            if (menuStyle == null) {
                menuStyle = null;
            }
            gradientDrawable.setColor(menuStyle.snapOptions.activeBackgroundColor);
            MenuStyle menuStyle2 = this.style;
            if (menuStyle2 == null) {
                menuStyle2 = null;
            }
            gradientDrawable.setStroke(this.outlineStroke, menuStyle2.snapOptions.activeStrokeColor);
            if (z) {
                Drawable background = this.snapLeftButton.getBackground();
                MenuStyle menuStyle3 = this.style;
                if (menuStyle3 == null) {
                    menuStyle3 = null;
                }
                background.setTint(menuStyle3.snapOptions.activeSnapSideColor);
                Drawable background2 = this.snapRightButton.getBackground();
                MenuStyle menuStyle4 = this.style;
                background2.setTint((menuStyle4 != null ? menuStyle4 : null).snapOptions.semiActiveSnapSideColor);
                return;
            }
            Drawable background3 = this.snapRightButton.getBackground();
            MenuStyle menuStyle5 = this.style;
            if (menuStyle5 == null) {
                menuStyle5 = null;
            }
            background3.setTint(menuStyle5.snapOptions.activeSnapSideColor);
            Drawable background4 = this.snapLeftButton.getBackground();
            MenuStyle menuStyle6 = this.style;
            background4.setTint((menuStyle6 != null ? menuStyle6 : null).snapOptions.semiActiveSnapSideColor);
        }

        public final LayerDrawable createMaximizeButtonDrawable(int i, int i2, Integer num) {
            int i3;
            ArrayList arrayList = new ArrayList();
            ShapeDrawable shapeDrawable = new ShapeDrawable();
            float[] fArr = new float[8];
            int i4 = 0;
            while (true) {
                i3 = this.outlineRadius;
                if (i4 >= 8) {
                    break;
                }
                fArr[i4] = i3;
                i4++;
            }
            shapeDrawable.setShape(new RoundRectShape(fArr, null, null));
            shapeDrawable.getPaint().setColor(i);
            shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
            arrayList.add(shapeDrawable);
            if (num != null) {
                int intValue = num.intValue();
                ShapeDrawable shapeDrawable2 = new ShapeDrawable();
                float[] fArr2 = new float[8];
                for (int i5 = 0; i5 < 8; i5++) {
                    fArr2[i5] = i3;
                }
                shapeDrawable2.setShape(new RoundRectShape(fArr2, null, null));
                shapeDrawable2.getPaint().setColor(intValue);
                shapeDrawable2.getPaint().setStyle(Paint.Style.FILL);
                arrayList.add(shapeDrawable2);
            }
            ShapeDrawable shapeDrawable3 = new ShapeDrawable();
            float[] fArr3 = new float[8];
            for (int i6 = 0; i6 < 8; i6++) {
                fArr3[i6] = i3;
            }
            shapeDrawable3.setShape(new RoundRectShape(fArr3, null, null));
            shapeDrawable3.getPaint().setColor(i2);
            shapeDrawable3.getPaint().setStyle(Paint.Style.FILL);
            arrayList.add(shapeDrawable3);
            ShapeDrawable shapeDrawable4 = new ShapeDrawable();
            float[] fArr4 = new float[8];
            for (int i7 = 0; i7 < 8; i7++) {
                fArr4[i7] = this.fillRadius;
            }
            shapeDrawable4.setShape(new RoundRectShape(fArr4, null, null));
            shapeDrawable4.getPaint().setColor(i);
            shapeDrawable4.getPaint().setStyle(Paint.Style.FILL);
            arrayList.add(shapeDrawable4);
            LayerDrawable layerDrawable = new LayerDrawable((Drawable[]) arrayList.toArray(new Drawable[0]));
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            if (numberOfLayers == 3) {
                int i8 = this.outlineStroke;
                layerDrawable.setLayerInset(1, i8, i8, i8, i8);
                int i9 = this.fillPadding;
                layerDrawable.setLayerInset(2, i9, i9, i9, i9);
            } else {
                if (numberOfLayers != 4) {
                    throw new IllegalStateException(("Unexpected number of layers: " + layerDrawable.getNumberOfLayers()).toString());
                }
                int[] iArr = {1, 2};
                for (int i10 = 0; i10 < 2; i10++) {
                    int i11 = iArr[i10];
                    int i12 = this.outlineStroke;
                    layerDrawable.setLayerInset(i11, i12, i12, i12, i12);
                }
                int i13 = this.fillPadding;
                layerDrawable.setLayerInset(3, i13, i13, i13, i13);
            }
            return layerDrawable;
        }

        public final void updateSplitSnapSelection(SnapToHalfSelection snapToHalfSelection) {
            int ordinal = snapToHalfSelection.ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    activateSnapOption(true);
                    return;
                } else {
                    if (ordinal != 2) {
                        return;
                    }
                    activateSnapOption(false);
                    return;
                }
            }
            int[][] iArr = {new int[]{android.R.attr.state_pressed}, new int[]{android.R.attr.state_focused}, new int[]{android.R.attr.state_selected}, new int[0]};
            MenuStyle menuStyle = this.style;
            MenuStyle.SnapOptions snapOptions = (menuStyle == null ? null : menuStyle).snapOptions;
            MenuStyle.SnapOptions snapOptions2 = (menuStyle == null ? null : menuStyle).snapOptions;
            MenuStyle.SnapOptions snapOptions3 = (menuStyle == null ? null : menuStyle).snapOptions;
            if (menuStyle == null) {
                menuStyle = null;
            }
            ColorStateList colorStateList = new ColorStateList(iArr, new int[]{snapOptions.activeSnapSideColor, snapOptions2.activeSnapSideColor, snapOptions3.activeSnapSideColor, menuStyle.snapOptions.inactiveSnapSideColor});
            Drawable background = this.snapLeftButton.getBackground();
            if (background != null) {
                background.setTintList(colorStateList);
            }
            Drawable background2 = this.snapRightButton.getBackground();
            if (background2 != null) {
                background2.setTintList(colorStateList);
            }
            View view = this.snapButtonsLayout;
            view.setBackgroundResource(R.drawable.desktop_mode_maximize_menu_layout_background);
            GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground();
            MenuStyle menuStyle2 = this.style;
            if (menuStyle2 == null) {
                menuStyle2 = null;
            }
            gradientDrawable.setColor(menuStyle2.snapOptions.inactiveBackgroundColor);
            MenuStyle menuStyle3 = this.style;
            gradientDrawable.setStroke(this.outlineStroke, (menuStyle3 != null ? menuStyle3 : null).snapOptions.inactiveStrokeColor);
        }
    }

    public MaximizeMenu(SyncTransactionQueue syncTransactionQueue, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, DisplayController displayController, ActivityManager.RunningTaskInfo runningTaskInfo, Context context, PointF pointF, Supplier supplier) {
        this.decorWindowContext = context;
        this.menuPosition = pointF;
    }

    public final int loadDimensionPixelSize(int i) {
        if (i == 0) {
            return 0;
        }
        return this.decorWindowContext.getResources().getDimensionPixelSize(i);
    }
}
