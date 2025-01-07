package com.android.wm.shell.pip2.phone;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.emoji2.text.FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda1;
import com.android.wm.shell.common.ShellExecutor;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PipMenuView extends FrameLayout {
    public final List mActions;
    public final PhonePipMenuController mController;
    public boolean mDidLastShowMenuResize;
    public final View mDismissButton;
    public final FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda1 mHideMenuRunnable;
    public final ShellExecutor mMainExecutor;
    public final AnonymousClass1 mMenuBgUpdateListener;
    public final View mMenuContainer;
    public AnimatorSet mMenuContainerAnimator;
    public int mMenuState;
    public final PipMenuIconsAlgorithm mPipMenuIconsAlgorithm;
    public final View mSettingsButton;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.pip2.phone.PipMenuView$1, reason: invalid class name */
    public abstract class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
    }

    @Override // android.view.View
    public abstract boolean dispatchGenericMotionEvent(MotionEvent motionEvent);

    @Override // android.view.ViewGroup, android.view.View
    public abstract boolean dispatchTouchEvent(MotionEvent motionEvent);

    public abstract void hideMenu(FontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda1 fontRequestEmojiCompatConfig$FontRequestMetadataLoader$$ExternalSyntheticLambda1, boolean z, boolean z2, int i);

    public abstract void notifyMenuStateChangeStart(int i, boolean z, PipMenuView$$ExternalSyntheticLambda0 pipMenuView$$ExternalSyntheticLambda0);

    public abstract void repostDelayedHide(int i);

    public abstract void updateActionViews(int i, Rect rect);
}
