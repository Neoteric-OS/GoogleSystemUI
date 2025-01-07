package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.windowdecor.additionalviewcontainer.AdditionalViewHostViewContainer;
import com.android.wm.shell.windowdecor.common.DecorThemeUtil;
import com.android.wm.shell.windowdecor.extension.TaskInfoKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HandleMenu {
    public final Bitmap appIconBitmap;
    public final CharSequence appName;
    public final int captionHeight;
    public final int captionWidth;
    public final Context context;
    public final Point globalMenuPosition;
    public final PointF handleMenuPosition;
    public HandleMenuView handleMenuView;
    public AdditionalViewHostViewContainer handleMenuViewContainer;
    public final int layoutResId;
    public final int marginMenuStart;
    public final int marginMenuTop;
    public final int menuHeight;
    public final int menuWidth;
    public final Uri openInBrowserLink;
    public final DesktopModeWindowDecoration parentDecor;
    public final boolean shouldShowWindowingPill;
    public final SplitScreenController splitScreenController;
    public final ActivityManager.RunningTaskInfo taskInfo;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HandleMenuView {
        public final HandleMenuAnimator animator;
        public final ImageView appIconView;
        public final View appInfoPill;
        public final TextView appNameView;
        public final Button browserBtn;
        public final HandleMenuImageButton collapseMenuButton;
        public final DecorThemeUtil decorThemeUtil;
        public final ImageButton desktopBtn;
        public final ImageButton floatingBtn;
        public final ImageButton fullscreenBtn;
        public final Button manageWindowBtn;
        public final View moreActionsPill;
        public final Button newWindowBtn;
        public DesktopModeWindowDecoration$$ExternalSyntheticLambda3 onCloseMenuClickListener;
        public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 onManageWindowsClickListener;
        public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 onNewWindowClickListener;
        public Function0 onOpenInBrowserClickListener;
        public DesktopModeWindowDecoration$$ExternalSyntheticLambda3 onOutsideTouchListener;
        public DesktopModeWindowDecoration$$ExternalSyntheticLambda3 onToDesktopClickListener;
        public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 onToFullscreenClickListener;
        public DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 onToSplitScreenClickListener;
        public final View openInBrowserPill;
        public final View rootView;
        public final Button screenshotBtn;
        public final ImageButton splitscreenBtn;
        public MenuStyle style;
        public ActivityManager.RunningTaskInfo taskInfo;
        public final View windowingPill;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class MenuStyle {
            public final int backgroundColor;
            public final int textColor;
            public final ColorStateList windowingButtonColor;

            public MenuStyle(int i, int i2, ColorStateList colorStateList) {
                this.backgroundColor = i;
                this.textColor = i2;
                this.windowingButtonColor = colorStateList;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof MenuStyle)) {
                    return false;
                }
                MenuStyle menuStyle = (MenuStyle) obj;
                return this.backgroundColor == menuStyle.backgroundColor && this.textColor == menuStyle.textColor && Intrinsics.areEqual(this.windowingButtonColor, menuStyle.windowingButtonColor);
            }

            public final int hashCode() {
                return this.windowingButtonColor.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.textColor, Integer.hashCode(this.backgroundColor) * 31, 31);
            }

            public final String toString() {
                return "MenuStyle(backgroundColor=" + this.backgroundColor + ", textColor=" + this.textColor + ", windowingButtonColor=" + this.windowingButtonColor + ")";
            }
        }

        public HandleMenuView(Context context, int i, int i2, boolean z, boolean z2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.desktop_mode_window_decor_handle_menu, (ViewGroup) null);
            this.rootView = inflate;
            View requireViewById = inflate.requireViewById(R.id.app_info_pill);
            this.appInfoPill = requireViewById;
            HandleMenuImageButton handleMenuImageButton = (HandleMenuImageButton) requireViewById.requireViewById(R.id.collapse_menu_button);
            this.collapseMenuButton = handleMenuImageButton;
            this.appIconView = (ImageView) requireViewById.requireViewById(R.id.application_icon);
            this.appNameView = (TextView) requireViewById.requireViewById(R.id.application_name);
            View requireViewById2 = inflate.requireViewById(R.id.windowing_pill);
            this.windowingPill = requireViewById2;
            ImageButton imageButton = (ImageButton) requireViewById2.requireViewById(R.id.fullscreen_button);
            this.fullscreenBtn = imageButton;
            ImageButton imageButton2 = (ImageButton) requireViewById2.requireViewById(R.id.split_screen_button);
            this.splitscreenBtn = imageButton2;
            this.floatingBtn = (ImageButton) requireViewById2.requireViewById(R.id.floating_button);
            ImageButton imageButton3 = (ImageButton) requireViewById2.requireViewById(R.id.desktop_button);
            this.desktopBtn = imageButton3;
            View requireViewById3 = inflate.requireViewById(R.id.more_actions_pill);
            this.moreActionsPill = requireViewById3;
            this.screenshotBtn = (Button) requireViewById3.requireViewById(R.id.screenshot_button);
            Button button = (Button) requireViewById3.requireViewById(R.id.new_window_button);
            this.newWindowBtn = button;
            Button button2 = (Button) requireViewById3.requireViewById(R.id.manage_windows_button);
            this.manageWindowBtn = button2;
            View requireViewById4 = inflate.requireViewById(R.id.open_in_browser_pill);
            this.openInBrowserPill = requireViewById4;
            Button button3 = (Button) requireViewById4.requireViewById(R.id.open_in_browser_button);
            this.browserBtn = button3;
            this.decorThemeUtil = new DecorThemeUtil(context);
            this.animator = new HandleMenuAnimator(inflate, i, i2);
            final int i3 = 0;
            imageButton.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.windowdecor.HandleMenu.HandleMenuView.1
                public final /* synthetic */ HandleMenuView this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i3) {
                        case 0:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.this$0.onToFullscreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 1:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.this$0.onToSplitScreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 2:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = this.this$0.onToDesktopClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 3:
                            Function0 function0 = this.this$0.onOpenInBrowserClickListener;
                            if (function0 != null) {
                                function0.invoke();
                                break;
                            }
                            break;
                        case 4:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda32 = this.this$0.onCloseMenuClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 5:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.this$0.onNewWindowClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33.invoke();
                                break;
                            }
                            break;
                        default:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 = this.this$0.onManageWindowsClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34.invoke();
                                break;
                            }
                            break;
                    }
                }
            });
            final int i4 = 1;
            imageButton2.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.windowdecor.HandleMenu.HandleMenuView.1
                public final /* synthetic */ HandleMenuView this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i4) {
                        case 0:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.this$0.onToFullscreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 1:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.this$0.onToSplitScreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 2:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = this.this$0.onToDesktopClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 3:
                            Function0 function0 = this.this$0.onOpenInBrowserClickListener;
                            if (function0 != null) {
                                function0.invoke();
                                break;
                            }
                            break;
                        case 4:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda32 = this.this$0.onCloseMenuClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 5:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.this$0.onNewWindowClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33.invoke();
                                break;
                            }
                            break;
                        default:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 = this.this$0.onManageWindowsClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34.invoke();
                                break;
                            }
                            break;
                    }
                }
            });
            final int i5 = 2;
            imageButton3.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.windowdecor.HandleMenu.HandleMenuView.1
                public final /* synthetic */ HandleMenuView this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i5) {
                        case 0:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.this$0.onToFullscreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 1:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.this$0.onToSplitScreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 2:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = this.this$0.onToDesktopClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 3:
                            Function0 function0 = this.this$0.onOpenInBrowserClickListener;
                            if (function0 != null) {
                                function0.invoke();
                                break;
                            }
                            break;
                        case 4:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda32 = this.this$0.onCloseMenuClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 5:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.this$0.onNewWindowClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33.invoke();
                                break;
                            }
                            break;
                        default:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 = this.this$0.onManageWindowsClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34.invoke();
                                break;
                            }
                            break;
                    }
                }
            });
            final int i6 = 3;
            button3.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.windowdecor.HandleMenu.HandleMenuView.1
                public final /* synthetic */ HandleMenuView this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i6) {
                        case 0:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.this$0.onToFullscreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 1:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.this$0.onToSplitScreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 2:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = this.this$0.onToDesktopClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 3:
                            Function0 function0 = this.this$0.onOpenInBrowserClickListener;
                            if (function0 != null) {
                                function0.invoke();
                                break;
                            }
                            break;
                        case 4:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda32 = this.this$0.onCloseMenuClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 5:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.this$0.onNewWindowClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33.invoke();
                                break;
                            }
                            break;
                        default:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 = this.this$0.onManageWindowsClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34.invoke();
                                break;
                            }
                            break;
                    }
                }
            });
            final int i7 = 4;
            handleMenuImageButton.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.windowdecor.HandleMenu.HandleMenuView.1
                public final /* synthetic */ HandleMenuView this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i7) {
                        case 0:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.this$0.onToFullscreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 1:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.this$0.onToSplitScreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 2:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = this.this$0.onToDesktopClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 3:
                            Function0 function0 = this.this$0.onOpenInBrowserClickListener;
                            if (function0 != null) {
                                function0.invoke();
                                break;
                            }
                            break;
                        case 4:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda32 = this.this$0.onCloseMenuClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 5:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.this$0.onNewWindowClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33.invoke();
                                break;
                            }
                            break;
                        default:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 = this.this$0.onManageWindowsClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34.invoke();
                                break;
                            }
                            break;
                    }
                }
            });
            final int i8 = 5;
            button.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.windowdecor.HandleMenu.HandleMenuView.1
                public final /* synthetic */ HandleMenuView this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i8) {
                        case 0:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.this$0.onToFullscreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 1:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.this$0.onToSplitScreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 2:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = this.this$0.onToDesktopClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 3:
                            Function0 function0 = this.this$0.onOpenInBrowserClickListener;
                            if (function0 != null) {
                                function0.invoke();
                                break;
                            }
                            break;
                        case 4:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda32 = this.this$0.onCloseMenuClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 5:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.this$0.onNewWindowClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33.invoke();
                                break;
                            }
                            break;
                        default:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 = this.this$0.onManageWindowsClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34.invoke();
                                break;
                            }
                            break;
                    }
                }
            });
            final int i9 = 6;
            button2.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.windowdecor.HandleMenu.HandleMenuView.1
                public final /* synthetic */ HandleMenuView this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i9) {
                        case 0:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 = this.this$0.onToFullscreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 1:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 = this.this$0.onToSplitScreenClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 2:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = this.this$0.onToDesktopClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda3 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                                break;
                            }
                            break;
                        case 3:
                            Function0 function0 = this.this$0.onOpenInBrowserClickListener;
                            if (function0 != null) {
                                function0.invoke();
                                break;
                            }
                            break;
                        case 4:
                            DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda32 = this.this$0.onCloseMenuClickListener;
                            if (desktopModeWindowDecoration$$ExternalSyntheticLambda32 != null) {
                                desktopModeWindowDecoration$$ExternalSyntheticLambda32.invoke();
                                break;
                            }
                            break;
                        case 5:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 = this.this$0.onNewWindowClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda33.invoke();
                                break;
                            }
                            break;
                        default:
                            DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 = this.this$0.onManageWindowsClickListener;
                            if (desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34 != null) {
                                desktopModeWindowDecorViewModel$$ExternalSyntheticLambda34.invoke();
                                break;
                            }
                            break;
                    }
                }
            });
            inflate.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.wm.shell.windowdecor.HandleMenu.HandleMenuView.8
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getActionMasked() != 4) {
                        return true;
                    }
                    DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = HandleMenuView.this.onOutsideTouchListener;
                    if (desktopModeWindowDecoration$$ExternalSyntheticLambda3 == null) {
                        return false;
                    }
                    desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                    return false;
                }
            });
        }
    }

    public HandleMenu(DesktopModeWindowDecoration desktopModeWindowDecoration, WindowManagerWrapper windowManagerWrapper, int i, Bitmap bitmap, CharSequence charSequence, SplitScreenController splitScreenController, boolean z, Uri uri, int i2, int i3, int i4) {
        this.parentDecor = desktopModeWindowDecoration;
        this.layoutResId = i;
        this.appIconBitmap = bitmap;
        this.appName = charSequence;
        this.splitScreenController = splitScreenController;
        this.shouldShowWindowingPill = z;
        this.openInBrowserLink = uri;
        this.captionWidth = i2;
        this.captionHeight = i3;
        this.context = desktopModeWindowDecoration.mDecorWindowContext;
        this.taskInfo = desktopModeWindowDecoration.mTaskInfo;
        int loadDimensionPixelSize = loadDimensionPixelSize(R.dimen.desktop_mode_handle_menu_pill_elevation);
        int loadDimensionPixelSize2 = loadDimensionPixelSize(R.dimen.desktop_mode_handle_menu_pill_spacing_margin);
        this.menuWidth = loadDimensionPixelSize(R.dimen.desktop_mode_handle_menu_width) + loadDimensionPixelSize;
        int loadDimensionPixelSize3 = loadDimensionPixelSize(R.dimen.desktop_mode_handle_menu_height) + loadDimensionPixelSize;
        int loadDimensionPixelSize4 = ((((z ? loadDimensionPixelSize3 : (loadDimensionPixelSize3 - loadDimensionPixelSize(R.dimen.desktop_mode_handle_menu_windowing_pill_height)) - loadDimensionPixelSize2) - loadDimensionPixelSize(R.dimen.desktop_mode_handle_menu_screenshot_height)) - loadDimensionPixelSize(R.dimen.desktop_mode_handle_menu_new_window_height)) - loadDimensionPixelSize(R.dimen.desktop_mode_handle_menu_manage_windows_height)) - loadDimensionPixelSize2;
        this.menuHeight = uri == null ? (loadDimensionPixelSize4 - loadDimensionPixelSize(R.dimen.desktop_mode_handle_menu_open_in_browser_pill_height)) - loadDimensionPixelSize2 : loadDimensionPixelSize4;
        this.marginMenuTop = loadDimensionPixelSize(R.dimen.desktop_mode_handle_menu_margin_top);
        this.marginMenuStart = loadDimensionPixelSize(R.dimen.desktop_mode_handle_menu_margin_start);
        this.handleMenuPosition = new PointF();
        this.globalMenuPosition = new Point();
        updateHandleMenuPillPositions(i4);
    }

    public final void checkMotionEvent(MotionEvent motionEvent) {
        PointF pointF = new PointF(motionEvent.getX() - this.handleMenuPosition.x, motionEvent.getY() - this.handleMenuPosition.y);
        HandleMenuView handleMenuView = this.handleMenuView;
        if (handleMenuView != null) {
            float f = pointF.x;
            float f2 = pointF.y;
            boolean z = false;
            HandleMenuImageButton handleMenuImageButton = handleMenuView.collapseMenuButton;
            boolean z2 = handleMenuImageButton != null && ((float) handleMenuImageButton.getLeft()) <= f && ((float) handleMenuImageButton.getRight()) >= f && ((float) handleMenuImageButton.getTop()) <= f2 && ((float) handleMenuImageButton.getBottom()) >= f2;
            int actionMasked = motionEvent.getActionMasked();
            handleMenuImageButton.setHovered(z2 && actionMasked != 1);
            if (z2 && actionMasked == 0) {
                z = true;
            }
            handleMenuImageButton.setPressed(z);
            if (actionMasked == 1 && z2) {
                handleMenuImageButton.performClick();
            }
        }
    }

    public final int loadDimensionPixelSize(int i) {
        if (i == 0) {
            return 0;
        }
        return this.context.getResources().getDimensionPixelSize(i);
    }

    public final void updateHandleMenuPillPositions(int i) {
        Rect bounds = this.taskInfo.getConfiguration().windowConfiguration.getBounds();
        int i2 = (this.captionWidth / 2) + i;
        int i3 = this.menuWidth;
        int i4 = i2 - (i3 / 2);
        boolean isFreeform = this.taskInfo.isFreeform();
        int i5 = this.marginMenuStart;
        int i6 = this.marginMenuTop;
        if (isFreeform) {
            this.globalMenuPosition.set(bounds.left + i5, bounds.top + i6);
        } else if (TaskInfoKt.isFullscreen(this.taskInfo)) {
            this.globalMenuPosition.set(i4, i6);
        } else if (TaskInfoKt.isMultiWindow(this.taskInfo)) {
            int i7 = this.taskInfo.taskId;
            SplitScreenController splitScreenController = this.splitScreenController;
            int splitPosition = splitScreenController.getSplitPosition(i7);
            Rect rect = new Rect();
            splitScreenController.getStageBounds(rect, new Rect());
            if (splitPosition == 0) {
                this.globalMenuPosition.set(i4, i6);
            } else if (splitPosition == 1) {
                this.globalMenuPosition.set(rect.width() + i4, i6);
            }
        }
        if (this.layoutResId != R.layout.desktop_mode_app_header) {
            i5 = (bounds.width() / 2) - (i3 / 2);
        }
        this.handleMenuPosition.set(i5, i6);
    }

    public static /* synthetic */ void getHandleMenuPosition$annotations() {
    }

    public static /* synthetic */ void getHandleMenuViewContainer$annotations() {
    }
}
