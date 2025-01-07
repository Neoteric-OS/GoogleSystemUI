package com.android.systemui.media.controls.ui.view;

import android.graphics.Outline;
import android.util.MathUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import androidx.core.view.GestureDetectorCompat;
import com.android.app.tracing.TraceStateLogger;
import com.android.systemui.media.controls.util.MediaUiEvent;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.PageIndicator;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaCarouselScrollHandler {
    public static final MediaCarouselScrollHandler$Companion$CONTENT_TRANSLATION$1 CONTENT_TRANSLATION = new MediaCarouselScrollHandler$Companion$CONTENT_TRANSLATION$1();
    public int carouselHeight;
    public int carouselWidth;
    public final Function1 closeGuts;
    public float contentTranslation;
    public int cornerRadius;
    public final Function0 dismissCallback;
    public final FalsingManager falsingManager;
    public boolean falsingProtectionNeeded;
    public final GestureDetectorCompat gestureDetector;
    public final Function1 logSmartspaceImpression;
    public final MediaUiEventLogger logger;
    public final DelayableExecutor mainExecutor;
    public final ViewGroup mediaContent;
    public final PageIndicator pageIndicator;
    public int playerWidthPlusPadding;
    public boolean qsExpanded;
    public int scrollIntoCurrentMedia;
    public final MediaScrollView scrollView;
    public final Function1 seekBarUpdateListener;
    public View settingsButton;
    public boolean showsSettingsButton;
    public final MediaCarouselScrollHandler$touchListener$1 touchListener;
    public final Function0 translationChangedListener;
    public int visibleMediaIndex;
    public final TraceStateLogger visibleStateLogger = new TraceStateLogger("MediaCarouselScrollHandler#visibleToUser", 14);
    public boolean visibleToUser;

    public MediaCarouselScrollHandler(MediaScrollView mediaScrollView, PageIndicator pageIndicator, DelayableExecutor delayableExecutor, Function0 function0, Function0 function02, Function1 function1, Function1 function12, FalsingManager falsingManager, Function1 function13, MediaUiEventLogger mediaUiEventLogger) {
        this.scrollView = mediaScrollView;
        this.pageIndicator = pageIndicator;
        this.mainExecutor = delayableExecutor;
        this.dismissCallback = function0;
        this.translationChangedListener = function02;
        this.seekBarUpdateListener = function1;
        this.closeGuts = function12;
        this.falsingManager = falsingManager;
        this.logSmartspaceImpression = function13;
        this.logger = mediaUiEventLogger;
        GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.android.systemui.media.controls.ui.view.MediaCarouselScrollHandler$gestureListener$1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public final boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                int i = 0;
                MediaCarouselScrollHandler mediaCarouselScrollHandler = MediaCarouselScrollHandler.this;
                mediaCarouselScrollHandler.getClass();
                float f3 = f * f;
                double d = f2;
                if (f3 < 0.5d * d * d || f3 < 1000000.0f) {
                    return false;
                }
                MediaScrollView mediaScrollView2 = mediaCarouselScrollHandler.scrollView;
                float contentTranslation = mediaScrollView2.getContentTranslation();
                float f4 = 0.0f;
                DelayableExecutor delayableExecutor2 = mediaCarouselScrollHandler.mainExecutor;
                if (contentTranslation != 0.0f) {
                    if (Math.signum(f) == Math.signum(contentTranslation) && (!mediaCarouselScrollHandler.falsingProtectionNeeded || !mediaCarouselScrollHandler.falsingManager.isFalseTouch(1))) {
                        f4 = mediaCarouselScrollHandler.getMaxTranslation() * Math.signum(contentTranslation);
                        if (!mediaCarouselScrollHandler.showsSettingsButton) {
                            delayableExecutor2.executeDelayed(new MediaCarouselScrollHandler$onFling$1(mediaCarouselScrollHandler, i), 100L);
                        }
                    }
                    Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                    PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(mediaCarouselScrollHandler);
                    companion.spring(MediaCarouselScrollHandler.CONTENT_TRANSLATION, f4, f, MediaCarouselScrollHandlerKt.translationConfig);
                    companion.start();
                    mediaScrollView2.animationTargetX = f4;
                    return true;
                }
                int scrollX = mediaScrollView2.getScrollX();
                if (mediaScrollView2.isLayoutRtl()) {
                    ViewGroup viewGroup = mediaScrollView2.contentContainer;
                    if (viewGroup == null) {
                        viewGroup = null;
                    }
                    scrollX = (viewGroup.getWidth() - mediaScrollView2.getWidth()) - scrollX;
                }
                int i2 = mediaCarouselScrollHandler.playerWidthPlusPadding;
                int i3 = i2 > 0 ? scrollX / i2 : 0;
                if (!mediaScrollView2.isLayoutRtl() ? f < 0.0f : f > 0.0f) {
                    i3++;
                }
                ((ExecutorImpl) delayableExecutor2).execute(new MediaCarouselScrollHandler$onFling$2(mediaCarouselScrollHandler, mediaCarouselScrollHandler.mediaContent.getChildAt(Math.min(mediaCarouselScrollHandler.mediaContent.getChildCount() - 1, Math.max(0, i3))), 0));
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                MediaCarouselScrollHandler mediaCarouselScrollHandler = MediaCarouselScrollHandler.this;
                Intrinsics.checkNotNull(motionEvent);
                mediaCarouselScrollHandler.getClass();
                float x = motionEvent2.getX() - motionEvent.getX();
                MediaScrollView mediaScrollView2 = mediaCarouselScrollHandler.scrollView;
                float contentTranslation = mediaScrollView2.getContentTranslation();
                if (contentTranslation == 0.0f && mediaScrollView2.canScrollHorizontally((int) (-x))) {
                    return false;
                }
                float f3 = contentTranslation - f;
                float abs = Math.abs(f3);
                if (abs > mediaCarouselScrollHandler.getMaxTranslation() && Math.signum(f) != Math.signum(contentTranslation)) {
                    if (Math.abs(contentTranslation) > mediaCarouselScrollHandler.getMaxTranslation()) {
                        f3 = contentTranslation - (f * 0.2f);
                    } else {
                        f3 = Math.signum(f3) * (((abs - mediaCarouselScrollHandler.getMaxTranslation()) * 0.2f) + mediaCarouselScrollHandler.getMaxTranslation());
                    }
                }
                if (Math.signum(f3) != Math.signum(contentTranslation) && contentTranslation != 0.0f && mediaScrollView2.canScrollHorizontally(-((int) f3))) {
                    f3 = 0.0f;
                }
                Function2 function2 = PhysicsAnimator.onAnimatorCreated;
                PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(mediaCarouselScrollHandler);
                if (companion.isRunning()) {
                    companion.spring(MediaCarouselScrollHandler.CONTENT_TRANSLATION, f3, 0.0f, MediaCarouselScrollHandlerKt.translationConfig);
                    companion.start();
                } else {
                    mediaCarouselScrollHandler.setContentTranslation(f3);
                }
                mediaScrollView2.animationTargetX = f3;
                return true;
            }
        };
        MediaCarouselScrollHandler$touchListener$1 mediaCarouselScrollHandler$touchListener$1 = new MediaCarouselScrollHandler$touchListener$1(this);
        View.OnScrollChangeListener onScrollChangeListener = new View.OnScrollChangeListener() { // from class: com.android.systemui.media.controls.ui.view.MediaCarouselScrollHandler$scrollChangedListener$1
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                MediaCarouselScrollHandler mediaCarouselScrollHandler = MediaCarouselScrollHandler.this;
                if (mediaCarouselScrollHandler.playerWidthPlusPadding == 0) {
                    return;
                }
                MediaScrollView mediaScrollView2 = mediaCarouselScrollHandler.scrollView;
                int scrollX = mediaScrollView2.getScrollX();
                if (mediaScrollView2.isLayoutRtl()) {
                    ViewGroup viewGroup = mediaScrollView2.contentContainer;
                    if (viewGroup == null) {
                        viewGroup = null;
                    }
                    scrollX = (viewGroup.getWidth() - mediaScrollView2.getWidth()) - scrollX;
                }
                MediaCarouselScrollHandler mediaCarouselScrollHandler2 = MediaCarouselScrollHandler.this;
                int i5 = mediaCarouselScrollHandler2.playerWidthPlusPadding;
                int i6 = scrollX / i5;
                int i7 = scrollX % i5;
                boolean z = true;
                boolean z2 = mediaCarouselScrollHandler2.scrollIntoCurrentMedia != 0;
                mediaCarouselScrollHandler2.scrollIntoCurrentMedia = i7;
                boolean z3 = i7 != 0;
                int i8 = mediaCarouselScrollHandler2.visibleMediaIndex;
                if (i6 != i8 || z2 != z3) {
                    mediaCarouselScrollHandler2.visibleMediaIndex = i6;
                    if (i8 != i6 && mediaCarouselScrollHandler2.visibleToUser) {
                        mediaCarouselScrollHandler2.logSmartspaceImpression.invoke(Boolean.valueOf(mediaCarouselScrollHandler2.qsExpanded));
                        mediaCarouselScrollHandler2.logger.logger.logWithPosition(MediaUiEvent.CAROUSEL_PAGE, 0, (String) null, i6);
                    }
                    mediaCarouselScrollHandler2.closeGuts.invoke(Boolean.FALSE);
                    mediaCarouselScrollHandler2.updatePlayerVisibilities();
                }
                float f = mediaCarouselScrollHandler2.visibleMediaIndex;
                int i9 = mediaCarouselScrollHandler2.playerWidthPlusPadding;
                float f2 = f + (i9 > 0 ? i7 / i9 : 0.0f);
                MediaScrollView mediaScrollView3 = mediaCarouselScrollHandler2.scrollView;
                if (mediaScrollView3.isLayoutRtl()) {
                    f2 = (mediaCarouselScrollHandler2.mediaContent.getChildCount() - f2) - 1;
                }
                mediaCarouselScrollHandler2.pageIndicator.setLocation(f2);
                if (mediaCarouselScrollHandler2.contentTranslation == 0.0f && mediaCarouselScrollHandler2.scrollIntoCurrentMedia == 0) {
                    z = false;
                }
                mediaScrollView3.setClipToOutline(z);
            }
        };
        this.gestureDetector = new GestureDetectorCompat(mediaScrollView.getContext(), simpleOnGestureListener);
        mediaScrollView.touchListener = mediaCarouselScrollHandler$touchListener$1;
        mediaScrollView.setOverScrollMode(2);
        ViewGroup viewGroup = mediaScrollView.contentContainer;
        this.mediaContent = viewGroup == null ? null : viewGroup;
        mediaScrollView.setOnScrollChangeListener(onScrollChangeListener);
        mediaScrollView.setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.systemui.media.controls.ui.view.MediaCarouselScrollHandler.1
            @Override // android.view.ViewOutlineProvider
            public final void getOutline(View view, Outline outline) {
                if (outline != null) {
                    MediaCarouselScrollHandler mediaCarouselScrollHandler = MediaCarouselScrollHandler.this;
                    outline.setRoundRect(0, 0, mediaCarouselScrollHandler.carouselWidth, mediaCarouselScrollHandler.carouselHeight, mediaCarouselScrollHandler.cornerRadius);
                }
            }
        });
    }

    public final int getMaxTranslation() {
        if (!this.showsSettingsButton) {
            return this.playerWidthPlusPadding;
        }
        View view = this.settingsButton;
        if (view == null) {
            view = null;
        }
        return view.getWidth();
    }

    public final void onPlayersChanged() {
        updatePlayerVisibilities();
        int dimensionPixelSize = this.scrollView.getContext().getResources().getDimensionPixelSize(R.dimen.qs_media_padding);
        int childCount = this.mediaContent.getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = this.mediaContent.getChildAt(i);
            int i2 = i == childCount + (-1) ? 0 : dimensionPixelSize;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
            if (marginLayoutParams.getMarginEnd() != i2) {
                marginLayoutParams.setMarginEnd(i2);
                childAt.setLayoutParams(marginLayoutParams);
            }
            i++;
        }
    }

    public final void resetTranslation(boolean z) {
        MediaScrollView mediaScrollView = this.scrollView;
        if (mediaScrollView.getContentTranslation() == 0.0f) {
            return;
        }
        if (!z) {
            Function2 function2 = PhysicsAnimator.onAnimatorCreated;
            PhysicsAnimator.Companion.getInstance(this).cancel();
            setContentTranslation(0.0f);
        } else {
            Function2 function22 = PhysicsAnimator.onAnimatorCreated;
            PhysicsAnimator companion = PhysicsAnimator.Companion.getInstance(this);
            companion.spring(CONTENT_TRANSLATION, 0.0f, 0.0f, MediaCarouselScrollHandlerKt.translationConfig);
            companion.start();
            mediaScrollView.animationTargetX = 0.0f;
        }
    }

    public final void scrollToPlayer(int i, int i2) {
        if (i >= 0 && i < this.mediaContent.getChildCount()) {
            int i3 = i * this.playerWidthPlusPadding;
            MediaScrollView mediaScrollView = this.scrollView;
            if (mediaScrollView.isLayoutRtl()) {
                ViewGroup viewGroup = mediaScrollView.contentContainer;
                if (viewGroup == null) {
                    viewGroup = null;
                }
                i3 = (viewGroup.getWidth() - mediaScrollView.getWidth()) - i3;
            }
            mediaScrollView.setScrollX(i3);
        }
        this.mainExecutor.executeDelayed(new MediaCarouselScrollHandler$onFling$2(this, this.mediaContent.getChildAt(Math.min(this.mediaContent.getChildCount() - 1, i2)), 1), 100L);
    }

    public final void setContentTranslation(float f) {
        this.contentTranslation = f;
        this.mediaContent.setTranslationX(f);
        updateSettingsPresentation();
        this.translationChangedListener.invoke();
        this.scrollView.setClipToOutline((this.contentTranslation == 0.0f && this.scrollIntoCurrentMedia == 0) ? false : true);
    }

    public final void updatePlayerVisibilities() {
        boolean z = this.scrollIntoCurrentMedia != 0;
        int childCount = this.mediaContent.getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = this.mediaContent.getChildAt(i);
            int i2 = this.visibleMediaIndex;
            childAt.setVisibility((i == i2 || (i == i2 + 1 && z)) ? 0 : 4);
            i++;
        }
    }

    public final void updateSettingsPresentation() {
        if (this.showsSettingsButton) {
            View view = this.settingsButton;
            if (view == null) {
                view = null;
            }
            if (view.getWidth() > 0) {
                float map = MathUtils.map(0.0f, getMaxTranslation(), 0.0f, 1.0f, Math.abs(this.contentTranslation));
                float f = 1.0f - map;
                View view2 = this.settingsButton;
                if (view2 == null) {
                    view2 = null;
                }
                float f2 = (-view2.getWidth()) * f * 0.3f;
                MediaScrollView mediaScrollView = this.scrollView;
                if (mediaScrollView.isLayoutRtl()) {
                    if (this.contentTranslation > 0.0f) {
                        float width = mediaScrollView.getWidth() - f2;
                        View view3 = this.settingsButton;
                        if (view3 == null) {
                            view3 = null;
                        }
                        f2 = -(width - view3.getWidth());
                    } else {
                        f2 = -f2;
                    }
                } else if (this.contentTranslation <= 0.0f) {
                    float width2 = mediaScrollView.getWidth() - f2;
                    View view4 = this.settingsButton;
                    if (view4 == null) {
                        view4 = null;
                    }
                    f2 = width2 - view4.getWidth();
                }
                float f3 = f * 50;
                View view5 = this.settingsButton;
                if (view5 == null) {
                    view5 = null;
                }
                view5.setRotation(f3 * (-Math.signum(this.contentTranslation)));
                float saturate = MathUtils.saturate(MathUtils.map(0.5f, 1.0f, 0.0f, 1.0f, map));
                View view6 = this.settingsButton;
                if (view6 == null) {
                    view6 = null;
                }
                view6.setAlpha(saturate);
                View view7 = this.settingsButton;
                if (view7 == null) {
                    view7 = null;
                }
                view7.setVisibility(saturate != 0.0f ? 0 : 4);
                View view8 = this.settingsButton;
                if (view8 == null) {
                    view8 = null;
                }
                view8.setTranslationX(f2);
                View view9 = this.settingsButton;
                if (view9 == null) {
                    view9 = null;
                }
                int height = mediaScrollView.getHeight();
                view9.setTranslationY((height - (this.settingsButton != null ? r10 : null).getHeight()) / 2.0f);
                return;
            }
        }
        View view10 = this.settingsButton;
        (view10 != null ? view10 : null).setVisibility(4);
    }

    public static /* synthetic */ void getTouchListener$annotations() {
    }
}
