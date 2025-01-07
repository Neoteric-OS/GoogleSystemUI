package com.android.systemui.statusbar.events;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import com.android.app.animation.Interpolators;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.statusbar.events.PrivacyDotViewController;
import com.android.systemui.util.leak.RotationUtils;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PrivacyDotViewController$initialize$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PrivacyDotViewController this$0;

    public /* synthetic */ PrivacyDotViewController$initialize$1(PrivacyDotViewController privacyDotViewController, int i) {
        this.$r8$classId = i;
        this.this$0 = privacyDotViewController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ViewState copy$default;
        int i;
        int i2;
        int i3;
        View view;
        final View view2;
        View view3;
        switch (this.$r8$classId) {
            case 0:
                PrivacyDotViewController privacyDotViewController = this.this$0;
                privacyDotViewController.animationScheduler.addCallback(privacyDotViewController.systemStatusAnimationCallback);
                return;
            default:
                final PrivacyDotViewController privacyDotViewController2 = this.this$0;
                synchronized (privacyDotViewController2.lock) {
                    copy$default = ViewState.copy$default(privacyDotViewController2.nextViewState, false, false, false, null, null, null, null, false, 0, 0, 0, null, null, 16383);
                }
                Objects.toString(copy$default);
                if (copy$default.viewInitialized && !copy$default.equals(privacyDotViewController2.currentViewState)) {
                    boolean areEqual = Intrinsics.areEqual(copy$default.designatedCorner, privacyDotViewController2.currentViewState.designatedCorner);
                    int i4 = privacyDotViewController2.currentViewState.rotation;
                    int i5 = copy$default.paddingTop;
                    int i6 = copy$default.rotation;
                    if (i6 != i4 || !areEqual) {
                        for (View view4 : privacyDotViewController2.getViews()) {
                            view4.setPadding(0, i5, 0, 0);
                            int cornerForView = privacyDotViewController2.cornerForView(view4) - i6;
                            if (cornerForView < 0) {
                                cornerForView += 4;
                            }
                            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view4.getLayoutParams();
                            if (cornerForView == 0) {
                                i = 51;
                            } else if (cornerForView == 1) {
                                i = 53;
                            } else if (cornerForView == 2) {
                                i = 85;
                            } else {
                                if (cornerForView != 3) {
                                    throw new IllegalArgumentException("Not a corner");
                                }
                                i = 83;
                            }
                            layoutParams.gravity = i;
                            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) view4.requireViewById(R.id.privacy_dot).getLayoutParams();
                            int i7 = 21;
                            if (cornerForView != 0) {
                                if (cornerForView == 1 || cornerForView == 2) {
                                    i7 = 19;
                                } else if (cornerForView != 3) {
                                    throw new IllegalArgumentException("Not a corner");
                                }
                            }
                            layoutParams2.gravity = i7;
                        }
                    }
                    ViewState viewState = privacyDotViewController2.currentViewState;
                    int i8 = viewState.rotation;
                    boolean z = copy$default.layoutRtl;
                    if (i6 != i8 || z != viewState.layoutRtl || !Intrinsics.areEqual(copy$default.portraitRect, viewState.portraitRect) || !Intrinsics.areEqual(copy$default.landscapeRect, viewState.landscapeRect) || !Intrinsics.areEqual(copy$default.upsideDownRect, viewState.upsideDownRect) || !Intrinsics.areEqual(copy$default.seascapeRect, viewState.seascapeRect)) {
                        Point point = new Point();
                        View view5 = privacyDotViewController2.tl;
                        if (view5 == null) {
                            view5 = null;
                        }
                        Display display = view5.getContext().getDisplay();
                        if (display != null) {
                            display.getRealSize(point);
                        }
                        View view6 = privacyDotViewController2.tl;
                        if (view6 == null) {
                            view6 = null;
                        }
                        int exactRotation = RotationUtils.getExactRotation(view6.getContext());
                        if (exactRotation == 1 || exactRotation == 3) {
                            i2 = point.y;
                            i3 = point.x;
                        } else {
                            i2 = point.x;
                            i3 = point.y;
                        }
                        View view7 = privacyDotViewController2.tl;
                        if (view7 == null) {
                            view7 = null;
                        }
                        Rect contentRectForRotation = copy$default.contentRectForRotation(privacyDotViewController2.activeRotationForCorner(view7, z));
                        View view8 = privacyDotViewController2.tl;
                        if (view8 == null) {
                            view8 = null;
                        }
                        view8.setPadding(0, i5, 0, 0);
                        View view9 = privacyDotViewController2.tl;
                        if (view9 == null) {
                            view9 = null;
                        }
                        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) view9.getLayoutParams();
                        layoutParams3.topMargin = contentRectForRotation.top;
                        layoutParams3.height = contentRectForRotation.height();
                        if (z) {
                            layoutParams3.width = contentRectForRotation.left;
                        } else {
                            layoutParams3.width = i3 - contentRectForRotation.right;
                        }
                        View view10 = privacyDotViewController2.tr;
                        if (view10 == null) {
                            view10 = null;
                        }
                        Rect contentRectForRotation2 = copy$default.contentRectForRotation(privacyDotViewController2.activeRotationForCorner(view10, z));
                        View view11 = privacyDotViewController2.tr;
                        if (view11 == null) {
                            view11 = null;
                        }
                        view11.setPadding(0, i5, 0, 0);
                        View view12 = privacyDotViewController2.tr;
                        if (view12 == null) {
                            view12 = null;
                        }
                        FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) view12.getLayoutParams();
                        layoutParams4.topMargin = contentRectForRotation2.top;
                        layoutParams4.height = contentRectForRotation2.height();
                        if (z) {
                            layoutParams4.width = contentRectForRotation2.left;
                        } else {
                            layoutParams4.width = i2 - contentRectForRotation2.right;
                        }
                        View view13 = privacyDotViewController2.br;
                        if (view13 == null) {
                            view13 = null;
                        }
                        Rect contentRectForRotation3 = copy$default.contentRectForRotation(privacyDotViewController2.activeRotationForCorner(view13, z));
                        View view14 = privacyDotViewController2.br;
                        if (view14 == null) {
                            view14 = null;
                        }
                        view14.setPadding(0, i5, 0, 0);
                        View view15 = privacyDotViewController2.br;
                        if (view15 == null) {
                            view15 = null;
                        }
                        FrameLayout.LayoutParams layoutParams5 = (FrameLayout.LayoutParams) view15.getLayoutParams();
                        layoutParams5.topMargin = contentRectForRotation3.top;
                        layoutParams5.height = contentRectForRotation3.height();
                        if (z) {
                            layoutParams5.width = contentRectForRotation3.left;
                        } else {
                            layoutParams5.width = i3 - contentRectForRotation3.right;
                        }
                        View view16 = privacyDotViewController2.bl;
                        if (view16 == null) {
                            view16 = null;
                        }
                        Rect contentRectForRotation4 = copy$default.contentRectForRotation(privacyDotViewController2.activeRotationForCorner(view16, z));
                        View view17 = privacyDotViewController2.bl;
                        if (view17 == null) {
                            view17 = null;
                        }
                        view17.setPadding(0, i5, 0, 0);
                        View view18 = privacyDotViewController2.bl;
                        if (view18 == null) {
                            view18 = null;
                        }
                        FrameLayout.LayoutParams layoutParams6 = (FrameLayout.LayoutParams) view18.getLayoutParams();
                        layoutParams6.topMargin = contentRectForRotation4.top;
                        layoutParams6.height = contentRectForRotation4.height();
                        if (z) {
                            layoutParams6.width = contentRectForRotation4.left;
                        } else {
                            layoutParams6.width = i2 - contentRectForRotation4.right;
                        }
                        Iterator it = privacyDotViewController2.getViews().iterator();
                        while (it.hasNext()) {
                            ((View) it.next()).requestLayout();
                        }
                    }
                    String str = copy$default.contentDescription;
                    if (!areEqual) {
                        View view19 = privacyDotViewController2.currentViewState.designatedCorner;
                        if (view19 != null) {
                            view19.setContentDescription(null);
                        }
                        View view20 = copy$default.designatedCorner;
                        if (view20 != null) {
                            view20.setContentDescription(str);
                        }
                        View view21 = copy$default.designatedCorner;
                        if (copy$default.shouldShowDot()) {
                            PrivacyDotViewController.ShowingListener showingListener = privacyDotViewController2.showingListener;
                            if (showingListener != null) {
                                ScreenDecorations.this.updateOverlayWindowVisibilityIfViewExists(view21);
                            }
                            if (view21 != null) {
                                view21.clearAnimation();
                                view21.setVisibility(0);
                                view21.setAlpha(0.0f);
                                view21.animate().alpha(1.0f).setDuration(300L).start();
                            }
                        }
                    } else if (!Intrinsics.areEqual(str, privacyDotViewController2.currentViewState.contentDescription) && (view = copy$default.designatedCorner) != null) {
                        view.setContentDescription(str);
                    }
                    boolean shouldShowDot = copy$default.shouldShowDot();
                    if (shouldShowDot != privacyDotViewController2.currentViewState.shouldShowDot()) {
                        if (shouldShowDot && (view3 = copy$default.designatedCorner) != null) {
                            view3.clearAnimation();
                            view3.setVisibility(0);
                            view3.setAlpha(0.0f);
                            view3.animate().alpha(1.0f).setDuration(160L).setInterpolator(Interpolators.ALPHA_IN).start();
                            PrivacyDotViewController.ShowingListener showingListener2 = privacyDotViewController2.showingListener;
                            if (showingListener2 != null) {
                                ScreenDecorations.this.updateOverlayWindowVisibilityIfViewExists(view3);
                            }
                        } else if (!shouldShowDot && (view2 = copy$default.designatedCorner) != null) {
                            view2.clearAnimation();
                            view2.animate().setDuration(160L).setInterpolator(Interpolators.ALPHA_OUT).alpha(0.0f).withEndAction(new Runnable() { // from class: com.android.systemui.statusbar.events.PrivacyDotViewController$hideDotView$1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    view2.setVisibility(4);
                                    PrivacyDotViewController.ShowingListener showingListener3 = privacyDotViewController2.showingListener;
                                    if (showingListener3 != null) {
                                        ScreenDecorations.this.updateOverlayWindowVisibilityIfViewExists(view2);
                                    }
                                }
                            }).start();
                        }
                    }
                    privacyDotViewController2.currentViewState = copy$default;
                    return;
                }
                return;
        }
    }
}
