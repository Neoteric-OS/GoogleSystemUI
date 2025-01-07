package com.android.systemui.media.controls.ui.view;

import android.view.MotionEvent;
import android.view.ViewGroup;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaCarouselScrollHandler$touchListener$1 implements Gefingerpoken {
    public final /* synthetic */ MediaCarouselScrollHandler this$0;

    public MediaCarouselScrollHandler$touchListener$1(MediaCarouselScrollHandler mediaCarouselScrollHandler) {
        this.this$0 = mediaCarouselScrollHandler;
    }

    @Override // com.android.systemui.Gefingerpoken
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNull(motionEvent);
        return this.this$0.gestureDetector.mDetector.onTouchEvent(motionEvent);
    }

    @Override // com.android.systemui.Gefingerpoken
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        float f;
        int i = 1;
        Intrinsics.checkNotNull(motionEvent);
        final MediaCarouselScrollHandler mediaCarouselScrollHandler = this.this$0;
        mediaCarouselScrollHandler.getClass();
        boolean z = motionEvent.getAction() == 1;
        boolean onTouchEvent = mediaCarouselScrollHandler.gestureDetector.mDetector.onTouchEvent(motionEvent);
        MediaScrollView mediaScrollView = mediaCarouselScrollHandler.scrollView;
        if (onTouchEvent) {
            if (z) {
                mediaScrollView.cancelCurrentScroll();
                return true;
            }
        } else if (motionEvent.getAction() == 2) {
            Function2 function2 = PhysicsAnimator.onAnimatorCreated;
            PhysicsAnimator.Companion.getInstance(mediaCarouselScrollHandler).cancel();
        } else if (z || motionEvent.getAction() == 3) {
            int scrollX = mediaScrollView.getScrollX();
            if (mediaScrollView.isLayoutRtl()) {
                ViewGroup viewGroup = mediaScrollView.contentContainer;
                if (viewGroup == null) {
                    viewGroup = null;
                }
                scrollX = (viewGroup.getWidth() - mediaScrollView.getWidth()) - scrollX;
            }
            int i2 = mediaCarouselScrollHandler.playerWidthPlusPadding;
            int i3 = scrollX % i2;
            int i4 = i3 > i2 / 2 ? i2 - i3 : i3 * (-1);
            DelayableExecutor delayableExecutor = mediaCarouselScrollHandler.mainExecutor;
            if (i4 != 0) {
                if (mediaScrollView.isLayoutRtl()) {
                    i4 = -i4;
                }
                final int scrollX2 = mediaScrollView.getScrollX() + i4;
                ((ExecutorImpl) delayableExecutor).execute(new Runnable() { // from class: com.android.systemui.media.controls.ui.view.MediaCarouselScrollHandler$onTouch$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        MediaScrollView mediaScrollView2 = MediaCarouselScrollHandler.this.scrollView;
                        mediaScrollView2.smoothScrollTo(scrollX2, mediaScrollView2.getScrollY());
                    }
                });
            }
            float contentTranslation = mediaScrollView.getContentTranslation();
            if (contentTranslation != 0.0f) {
                if (Math.abs(contentTranslation) < mediaCarouselScrollHandler.getMaxTranslation() / 2 || (mediaCarouselScrollHandler.falsingProtectionNeeded && mediaCarouselScrollHandler.falsingManager.isFalseTouch(1))) {
                    f = 0.0f;
                } else {
                    f = Math.signum(contentTranslation) * mediaCarouselScrollHandler.getMaxTranslation();
                    if (!mediaCarouselScrollHandler.showsSettingsButton) {
                        delayableExecutor.executeDelayed(new MediaCarouselScrollHandler$onFling$1(mediaCarouselScrollHandler, i), 100L);
                    }
                }
                Function2 function22 = PhysicsAnimator.onAnimatorCreated;
                PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(mediaCarouselScrollHandler);
                companion.spring(MediaCarouselScrollHandler.CONTENT_TRANSLATION, f, 0.0f, MediaCarouselScrollHandlerKt.translationConfig);
                companion.start();
                mediaScrollView.animationTargetX = f;
            }
        }
        return false;
    }
}
