package com.android.wm.shell.windowdecor.viewholder;

import android.app.ActivityManager;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.flags.DesktopModeFlags;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.DynamicTonalPaletteKt;
import androidx.compose.ui.graphics.ColorKt;
import com.android.wm.shell.R;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda3;
import com.android.wm.shell.windowdecor.MaximizeButtonView;
import com.android.wm.shell.windowdecor.common.DecorThemeUtil;
import com.android.wm.shell.windowdecor.common.Theme;
import com.android.wm.shell.windowdecor.extension.TaskInfoKt;
import com.android.wm.shell.windowdecor.viewholder.AppHeaderViewHolder$HeaderStyle$Background;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntriesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AppHeaderViewHolder extends WindowDecorationViewHolder {
    public final DrawableInsets appChipDrawableInsets;
    public final ImageView appIconImageView;
    public final TextView appNameTextView;
    public final View captionView;
    public final DrawableInsets closeDrawableInsets;
    public final ImageButton closeWindowButton;
    public final ColorScheme darkColors;
    public final DecorThemeUtil decorThemeUtil;
    public final ImageButton expandMenuButton;
    public final int headerButtonsRippleRadius;
    public final ColorScheme lightColors;
    public final MaximizeButtonView maximizeButtonView;
    public final DrawableInsets maximizeDrawableInsets;
    public final ImageButton maximizeWindowButton;
    public final DrawableInsets minimizeDrawableInsets;
    public final ImageButton minimizeWindowButton;
    public final View openMenuButton;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DrawableInsets {
        public final int b;
        public final int l;
        public final int r;
        public final int t;

        public DrawableInsets(int i, int i2) {
            this.l = i2;
            this.t = i;
            this.r = i2;
            this.b = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DrawableInsets)) {
                return false;
            }
            DrawableInsets drawableInsets = (DrawableInsets) obj;
            return this.l == drawableInsets.l && this.t == drawableInsets.t && this.r == drawableInsets.r && this.b == drawableInsets.b;
        }

        public final int hashCode() {
            return Integer.hashCode(this.b) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.r, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.t, Integer.hashCode(this.l) * 31, 31), 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("DrawableInsets(l=");
            sb.append(this.l);
            sb.append(", t=");
            sb.append(this.t);
            sb.append(", r=");
            sb.append(this.r);
            sb.append(", b=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.b, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Header {
        public final Theme appTheme;
        public final boolean isAppearanceCaptionLight;
        public final boolean isFocused;
        public final Type type;

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Type {
            public static final /* synthetic */ Type[] $VALUES;
            public static final Type CUSTOM;
            public static final Type DEFAULT;

            static {
                Type type = new Type("DEFAULT", 0);
                DEFAULT = type;
                Type type2 = new Type("CUSTOM", 1);
                CUSTOM = type2;
                Type[] typeArr = {type, type2};
                $VALUES = typeArr;
                EnumEntriesKt.enumEntries(typeArr);
            }

            public static Type valueOf(String str) {
                return (Type) Enum.valueOf(Type.class, str);
            }

            public static Type[] values() {
                return (Type[]) $VALUES.clone();
            }
        }

        public Header(Type type, Theme theme, boolean z, boolean z2) {
            this.type = type;
            this.appTheme = theme;
            this.isFocused = z;
            this.isAppearanceCaptionLight = z2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Header)) {
                return false;
            }
            Header header = (Header) obj;
            return this.type == header.type && this.appTheme == header.appTheme && this.isFocused == header.isFocused && this.isAppearanceCaptionLight == header.isAppearanceCaptionLight;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isAppearanceCaptionLight) + TransitionData$$ExternalSyntheticOutline0.m((this.appTheme.hashCode() + (this.type.hashCode() * 31)) * 31, 31, this.isFocused);
        }

        public final String toString() {
            return "Header(type=" + this.type + ", appTheme=" + this.appTheme + ", isFocused=" + this.isFocused + ", isAppearanceCaptionLight=" + this.isAppearanceCaptionLight + ")";
        }
    }

    public AppHeaderViewHolder(View view, DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener desktopModeTouchEventListener, DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener desktopModeTouchEventListener2, DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener desktopModeTouchEventListener3, DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener desktopModeTouchEventListener4, CharSequence charSequence, Bitmap bitmap, DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3) {
        super(view);
        this.decorThemeUtil = new DecorThemeUtil(this.context);
        this.lightColors = DynamicTonalPaletteKt.dynamicLightColorScheme(this.context);
        this.darkColors = DynamicTonalPaletteKt.dynamicDarkColorScheme(this.context);
        this.headerButtonsRippleRadius = this.context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_header_buttons_ripple_radius);
        this.appChipDrawableInsets = new DrawableInsets(this.context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_header_app_chip_ripple_inset_vertical), 0);
        this.minimizeDrawableInsets = new DrawableInsets(this.context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_header_minimize_ripple_inset_vertical), this.context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_header_minimize_ripple_inset_horizontal));
        this.maximizeDrawableInsets = new DrawableInsets(this.context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_header_maximize_ripple_inset_vertical), this.context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_header_maximize_ripple_inset_horizontal));
        this.closeDrawableInsets = new DrawableInsets(this.context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_header_close_ripple_inset_vertical), this.context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_header_close_ripple_inset_horizontal));
        View requireViewById = view.requireViewById(R.id.desktop_mode_caption);
        this.captionView = requireViewById;
        View requireViewById2 = view.requireViewById(R.id.caption_handle);
        View requireViewById3 = view.requireViewById(R.id.open_menu_button);
        this.openMenuButton = requireViewById3;
        ImageButton imageButton = (ImageButton) view.requireViewById(R.id.close_window);
        this.closeWindowButton = imageButton;
        this.expandMenuButton = (ImageButton) view.requireViewById(R.id.expand_menu_button);
        MaximizeButtonView maximizeButtonView = (MaximizeButtonView) view.requireViewById(R.id.maximize_button_view);
        this.maximizeButtonView = maximizeButtonView;
        ImageButton imageButton2 = (ImageButton) view.requireViewById(R.id.maximize_window);
        this.maximizeWindowButton = imageButton2;
        ImageButton imageButton3 = (ImageButton) view.requireViewById(R.id.minimize_window);
        this.minimizeWindowButton = imageButton3;
        TextView textView = (TextView) view.requireViewById(R.id.application_name);
        this.appNameTextView = textView;
        ImageView imageView = (ImageView) view.requireViewById(R.id.application_icon);
        this.appIconImageView = imageView;
        requireViewById.setOnTouchListener(desktopModeTouchEventListener);
        requireViewById2.setOnTouchListener(desktopModeTouchEventListener);
        requireViewById3.setOnClickListener(desktopModeTouchEventListener2);
        requireViewById3.setOnTouchListener(desktopModeTouchEventListener);
        imageButton.setOnClickListener(desktopModeTouchEventListener2);
        imageButton2.setOnClickListener(desktopModeTouchEventListener2);
        imageButton2.setOnTouchListener(desktopModeTouchEventListener);
        imageButton2.setOnGenericMotionListener(desktopModeTouchEventListener4);
        imageButton2.setOnLongClickListener(desktopModeTouchEventListener3);
        imageButton.setOnTouchListener(desktopModeTouchEventListener);
        imageButton3.setOnClickListener(desktopModeTouchEventListener2);
        imageButton3.setOnTouchListener(desktopModeTouchEventListener);
        textView.setText(charSequence);
        imageView.setImageBitmap(bitmap);
        maximizeButtonView.onHoverAnimationFinishedListener = desktopModeWindowDecoration$$ExternalSyntheticLambda3;
    }

    public static RippleDrawable createRippleDrawable(int i, int i2, DrawableInsets drawableInsets) {
        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{android.R.attr.state_hovered}, new int[]{android.R.attr.state_pressed}, new int[0]}, new int[]{Color.argb(28, Color.red(i), Color.green(i), Color.blue(i)), Color.argb(38, Color.red(i), Color.green(i), Color.blue(i)), 0});
        ShapeDrawable[] shapeDrawableArr = new ShapeDrawable[1];
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        float[] fArr = new float[8];
        for (int i3 = 0; i3 < 8; i3++) {
            fArr[i3] = i2;
        }
        shapeDrawable.setShape(new RoundRectShape(fArr, null, null));
        shapeDrawable.getPaint().setColor(-1);
        shapeDrawableArr[0] = shapeDrawable;
        LayerDrawable layerDrawable = new LayerDrawable(shapeDrawableArr);
        if (layerDrawable.getNumberOfLayers() != 1) {
            throw new IllegalArgumentException("Must only contain one layer");
        }
        layerDrawable.setLayerInset(0, drawableInsets.l, drawableInsets.t, drawableInsets.r, drawableInsets.b);
        return new RippleDrawable(colorStateList, null, layerDrawable);
    }

    @Override // com.android.wm.shell.windowdecor.viewholder.WindowDecorationViewHolder
    public final void bindData(ActivityManager.RunningTaskInfo runningTaskInfo, Point point, int i, int i2, boolean z) {
        int color;
        Object opaque;
        AppHeaderViewHolder$HeaderStyle$Foreground appHeaderViewHolder$HeaderStyle$Foreground;
        boolean isTrue = DesktopModeFlags.ENABLE_THEMED_APP_HEADERS.isTrue();
        MaximizeButtonView maximizeButtonView = this.maximizeButtonView;
        if (!isTrue) {
            int i3 = 166;
            View view = this.captionView;
            if (TaskInfoKt.isTransparentCaptionBarAppearance(runningTaskInfo)) {
                color = 0;
            } else {
                color = this.context.obtainStyledAttributes(null, new int[]{isDarkMode() ? !runningTaskInfo.isFocused ? android.R.^attr-private.materialColorSurfaceContainerHigh : android.R.^attr-private.materialColorSurfaceDim : !runningTaskInfo.isFocused ? android.R.^attr-private.materialColorSurfaceContainerLow : android.R.^attr-private.materialColorSecondaryContainer}, 0, 0).getColor(0, 0);
            }
            view.setBackgroundColor(color);
            boolean isTransparentCaptionBarAppearance = TaskInfoKt.isTransparentCaptionBarAppearance(runningTaskInfo);
            int i4 = android.R.^attr-private.materialColorOnSecondaryContainer;
            if ((!isTransparentCaptionBarAppearance || !TaskInfoKt.isLightCaptionBarAppearance(runningTaskInfo)) && ((TaskInfoKt.isTransparentCaptionBarAppearance(runningTaskInfo) && !TaskInfoKt.isLightCaptionBarAppearance(runningTaskInfo)) || isDarkMode())) {
                i4 = 17957043;
            }
            if (isDarkMode() && !runningTaskInfo.isFocused) {
                i3 = 140;
            } else if (isDarkMode() || runningTaskInfo.isFocused) {
                i3 = 255;
            }
            int color2 = this.context.obtainStyledAttributes(null, new int[]{i4}, 0, 0).getColor(0, 0);
            if (i3 != 255) {
                color2 = Color.argb(i3, Color.red(color2), Color.green(color2), Color.blue(color2));
            }
            int alpha = Color.alpha(color2);
            this.closeWindowButton.setImageTintList(ColorStateList.valueOf(color2));
            this.maximizeWindowButton.setImageTintList(ColorStateList.valueOf(color2));
            this.minimizeWindowButton.setImageTintList(ColorStateList.valueOf(color2));
            this.expandMenuButton.setImageTintList(ColorStateList.valueOf(color2));
            this.appNameTextView.setVisibility(!TaskInfoKt.isTransparentCaptionBarAppearance(runningTaskInfo) ? 0 : 8);
            this.appNameTextView.setTextColor(color2);
            this.appIconImageView.setImageAlpha(alpha);
            this.maximizeWindowButton.setImageAlpha(alpha);
            this.minimizeWindowButton.setImageAlpha(alpha);
            this.closeWindowButton.setImageAlpha(alpha);
            this.expandMenuButton.setImageAlpha(alpha);
            TypedArray obtainStyledAttributes = this.context.obtainStyledAttributes(null, new int[]{android.R.attr.selectableItemBackground, android.R.attr.selectableItemBackgroundBorderless}, 0, 0);
            this.openMenuButton.setBackground(obtainStyledAttributes.getDrawable(0));
            this.maximizeWindowButton.setBackground(obtainStyledAttributes.getDrawable(1));
            this.closeWindowButton.setBackground(obtainStyledAttributes.getDrawable(1));
            this.minimizeWindowButton.setBackground(obtainStyledAttributes.getDrawable(1));
            obtainStyledAttributes.recycle();
            boolean isDarkMode = isDarkMode();
            int i5 = MaximizeButtonView.$r8$clinit;
            maximizeButtonView.setAnimationTints(isDarkMode, null, null, null);
            this.minimizeWindowButton.setVisibility(8);
            return;
        }
        Header.Type type = TaskInfoKt.isTransparentCaptionBarAppearance(runningTaskInfo) ? Header.Type.CUSTOM : Header.Type.DEFAULT;
        Theme appTheme = this.decorThemeUtil.getAppTheme(runningTaskInfo);
        boolean z2 = runningTaskInfo.isFocused;
        boolean isLightCaptionBarAppearance = TaskInfoKt.isLightCaptionBarAppearance(runningTaskInfo);
        Header header = new Header(type, appTheme, z2, isLightCaptionBarAppearance);
        int ordinal = type.ordinal();
        AppHeaderViewHolder$HeaderStyle$Background.Transparent transparent = AppHeaderViewHolder$HeaderStyle$Background.Transparent.INSTANCE;
        ColorScheme colorScheme = this.lightColors;
        ColorScheme colorScheme2 = this.darkColors;
        if (ordinal == 0) {
            int ordinal2 = appTheme.ordinal();
            if (ordinal2 == 0) {
                opaque = z2 ? new AppHeaderViewHolder$HeaderStyle$Background.Opaque(ColorKt.m373toArgb8_81llA(colorScheme.secondaryContainer)) : new AppHeaderViewHolder$HeaderStyle$Background.Opaque(ColorKt.m373toArgb8_81llA(colorScheme.surfaceContainerLow));
            } else {
                if (ordinal2 != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                opaque = z2 ? new AppHeaderViewHolder$HeaderStyle$Background.Opaque(ColorKt.m373toArgb8_81llA(colorScheme2.surfaceContainerHigh)) : new AppHeaderViewHolder$HeaderStyle$Background.Opaque(ColorKt.m373toArgb8_81llA(colorScheme2.surfaceDim));
            }
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            opaque = transparent;
        }
        int ordinal3 = type.ordinal();
        if (ordinal3 == 0) {
            int ordinal4 = appTheme.ordinal();
            if (ordinal4 == 0) {
                appHeaderViewHolder$HeaderStyle$Foreground = z2 ? new AppHeaderViewHolder$HeaderStyle$Foreground(ColorKt.m373toArgb8_81llA(colorScheme.onSecondaryContainer), 255) : new AppHeaderViewHolder$HeaderStyle$Foreground(ColorKt.m373toArgb8_81llA(colorScheme.onSecondaryContainer), 166);
            } else {
                if (ordinal4 != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                appHeaderViewHolder$HeaderStyle$Foreground = z2 ? new AppHeaderViewHolder$HeaderStyle$Foreground(ColorKt.m373toArgb8_81llA(colorScheme2.onSurface), 255) : new AppHeaderViewHolder$HeaderStyle$Foreground(ColorKt.m373toArgb8_81llA(colorScheme2.onSurface), 140);
            }
        } else {
            if (ordinal3 != 1) {
                throw new NoWhenBranchMatchedException();
            }
            if (isLightCaptionBarAppearance && z2) {
                appHeaderViewHolder$HeaderStyle$Foreground = new AppHeaderViewHolder$HeaderStyle$Foreground(ColorKt.m373toArgb8_81llA(colorScheme.onSecondaryContainer), 255);
            } else if (isLightCaptionBarAppearance && !z2) {
                appHeaderViewHolder$HeaderStyle$Foreground = new AppHeaderViewHolder$HeaderStyle$Foreground(ColorKt.m373toArgb8_81llA(colorScheme.onSecondaryContainer), 166);
            } else if (!isLightCaptionBarAppearance && z2) {
                appHeaderViewHolder$HeaderStyle$Foreground = new AppHeaderViewHolder$HeaderStyle$Foreground(ColorKt.m373toArgb8_81llA(colorScheme2.onSurface), 255);
            } else {
                if (isLightCaptionBarAppearance || z2) {
                    throw new IllegalStateException(("No other combination expected header=" + header).toString());
                }
                appHeaderViewHolder$HeaderStyle$Foreground = new AppHeaderViewHolder$HeaderStyle$Foreground(ColorKt.m373toArgb8_81llA(colorScheme2.onSurface), 140);
            }
        }
        if (opaque instanceof AppHeaderViewHolder$HeaderStyle$Background.Opaque) {
            this.captionView.setBackgroundColor(((AppHeaderViewHolder$HeaderStyle$Background.Opaque) opaque).color);
        } else if (opaque.equals(transparent)) {
            this.captionView.setBackgroundColor(0);
        }
        int i6 = appHeaderViewHolder$HeaderStyle$Foreground.color;
        ColorStateList valueOf = ColorStateList.valueOf(i6);
        int i7 = appHeaderViewHolder$HeaderStyle$Foreground.opacity;
        ColorStateList withAlpha = valueOf.withAlpha(i7);
        View view2 = this.openMenuButton;
        DrawableInsets drawableInsets = this.appChipDrawableInsets;
        int i8 = this.headerButtonsRippleRadius;
        view2.setBackground(createRippleDrawable(i6, i8, drawableInsets));
        this.expandMenuButton.setImageTintList(withAlpha);
        TextView textView = this.appNameTextView;
        textView.setVisibility(type == Header.Type.DEFAULT ? 0 : 8);
        textView.setTextColor(withAlpha);
        this.appIconImageView.setImageAlpha(i7);
        ImageButton imageButton = this.minimizeWindowButton;
        imageButton.setImageTintList(withAlpha);
        imageButton.setBackground(createRippleDrawable(i6, i8, this.minimizeDrawableInsets));
        this.minimizeWindowButton.setVisibility(8);
        maximizeButtonView.setAnimationTints(appTheme == Theme.DARK, withAlpha, Integer.valueOf(i6), createRippleDrawable(i6, i8, this.maximizeDrawableInsets));
        ImageButton imageButton2 = this.closeWindowButton;
        imageButton2.setImageTintList(withAlpha);
        imageButton2.setBackground(createRippleDrawable(i6, i8, this.closeDrawableInsets));
    }

    public final boolean isDarkMode() {
        return (this.context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    @Override // com.android.wm.shell.windowdecor.viewholder.WindowDecorationViewHolder
    public final void onHandleMenuClosed() {
        this.openMenuButton.post(new AppHeaderViewHolder$onHandleMenuClosed$1(this, 0));
    }

    @Override // com.android.wm.shell.windowdecor.viewholder.WindowDecorationViewHolder
    public final void onHandleMenuOpened() {
    }
}
