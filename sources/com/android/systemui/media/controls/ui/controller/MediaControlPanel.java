package com.android.systemui.media.controls.ui.controller;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.app.WallpaperColors;
import android.app.smartspace.SmartspaceAction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.app.animation.Interpolators;
import com.android.internal.logging.InstanceId;
import com.android.internal.widget.CachingIconView;
import com.android.settingslib.widget.AdaptiveIcon;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.GhostedViewTransitionAnimatorController;
import com.android.systemui.bluetooth.BroadcastDialogController;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.widgets.CommunalTransitionAnimatorController;
import com.android.systemui.media.controls.domain.pipeline.MediaActionsKt;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.media.controls.ui.animation.AnimatingColorTransition;
import com.android.systemui.media.controls.ui.animation.AnimationBindHandler;
import com.android.systemui.media.controls.ui.animation.ColorSchemeTransition;
import com.android.systemui.media.controls.ui.animation.MetadataAnimationHandler;
import com.android.systemui.media.controls.ui.binder.SeekBarObserver;
import com.android.systemui.media.controls.ui.controller.MediaViewController;
import com.android.systemui.media.controls.ui.view.GutsViewHolder;
import com.android.systemui.media.controls.ui.view.GutsViewHolder$marquee$1;
import com.android.systemui.media.controls.ui.view.MediaViewHolder;
import com.android.systemui.media.controls.ui.view.RecommendationViewHolder;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel$onDestroy$1;
import com.android.systemui.media.controls.util.MediaDataUtils;
import com.android.systemui.media.controls.util.MediaUiEvent;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import com.android.systemui.media.controls.util.SmallHash;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.monet.ColorScheme;
import com.android.systemui.monet.Style;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.surfaceeffects.loadingeffect.LoadingEffect;
import com.android.systemui.surfaceeffects.loadingeffect.LoadingEffect$playMain$1;
import com.android.systemui.surfaceeffects.loadingeffect.LoadingEffect$playMain$2;
import com.android.systemui.surfaceeffects.loadingeffect.LoadingEffectView;
import com.android.systemui.surfaceeffects.ripple.MultiRippleController;
import com.android.systemui.surfaceeffects.ripple.MultiRippleController$play$1;
import com.android.systemui.surfaceeffects.ripple.MultiRippleView;
import com.android.systemui.surfaceeffects.ripple.RippleAnimation;
import com.android.systemui.surfaceeffects.ripple.RippleAnimationConfig;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseAnimationConfig;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseController;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseShader;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseShader$Companion$Type;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseView;
import com.android.systemui.util.ColorUtilKt;
import com.android.systemui.util.animation.TransitionLayout;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaControlPanel {
    public static final List SEMANTIC_ACTIONS_ALL;
    public static final List SEMANTIC_ACTIONS_COMPACT;
    public static final List SEMANTIC_ACTIONS_HIDE_WHEN_SCRUBBING;
    public static final Intent SETTINGS_INTENT = new Intent("android.settings.ACTION_MEDIA_CONTROLS_SETTINGS");
    static final long TURBULENCE_NOISE_PLAY_DURATION = 7500;
    public final ActivityIntentHelper mActivityIntentHelper;
    public final ActivityStarter mActivityStarter;
    public final Executor mBackgroundExecutor;
    public final BroadcastDialogController mBroadcastDialogController;
    public final BroadcastSender mBroadcastSender;
    public ColorSchemeTransition mColorSchemeTransition;
    public final CommunalSceneInteractor mCommunalSceneInteractor;
    public final Context mContext;
    public MediaController mController;
    public final FalsingManager mFalsingManager;
    public final GlobalSettings mGlobalSettings;
    public InstanceId mInstanceId;
    public String mKey;
    public final KeyguardStateController mKeyguardStateController;
    public LoadingEffect mLoadingEffect;
    public final NotificationLockscreenUserManager mLockscreenUserManager;
    public final MediaUiEventLogger mLogger;
    public final DelayableExecutor mMainExecutor;
    public final MediaCarouselController mMediaCarouselController;
    public MediaData mMediaData;
    public final Lazy mMediaDataManagerLazy;
    public final MediaOutputDialogManager mMediaOutputDialogManager;
    public final MediaViewController mMediaViewController;
    public MediaViewHolder mMediaViewHolder;
    public MetadataAnimationHandler mMetadataAnimationHandler;
    public MultiRippleController mMultiRippleController;
    public String mPackageName;
    public SmartspaceMediaData mRecommendationData;
    public RecommendationViewHolder mRecommendationViewHolder;
    public SeekBarObserver mSeekBarObserver;
    public final SeekBarViewModel mSeekBarViewModel;
    public int mSmartspaceMediaItemsCount;
    public final SystemClock mSystemClock;
    public MediaSession.Token mToken;
    public TurbulenceNoiseAnimationConfig mTurbulenceNoiseAnimationConfig;
    public int mUid = -1;
    public Drawable mPrevArtwork = null;
    public boolean mIsArtworkBound = false;
    public int mArtworkBoundId = 0;
    public int mArtworkNextBindRequestId = 0;
    public boolean mIsImpressed = false;
    public int mSmartspaceId = -1;
    public boolean mIsScrubbing = false;
    public boolean mIsSeekBarEnabled = false;
    public final MediaControlPanel$$ExternalSyntheticLambda4 mScrubbingChangeListener = new MediaControlPanel$$ExternalSyntheticLambda4(this);
    public final MediaControlPanel$$ExternalSyntheticLambda4 mEnabledChangeListener = new MediaControlPanel$$ExternalSyntheticLambda4(this);
    public boolean mWasPlaying = false;
    public boolean mButtonClicked = false;
    public final AnonymousClass1 mNoiseDrawCallback = new AnonymousClass1();
    public final AnonymousClass1 mStateChangedCallback = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.media.controls.ui.controller.MediaControlPanel$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public /* synthetic */ AnonymousClass1() {
        }

        public void onDraw(Paint paint) {
            LoadingEffectView loadingEffectView = MediaControlPanel.this.mMediaViewHolder.loadingEffectView;
            loadingEffectView.paint = paint;
            paint.setBlendMode(loadingEffectView.blendMode);
            loadingEffectView.invalidate();
        }
    }

    static {
        Integer valueOf = Integer.valueOf(R.id.actionPlayPause);
        Integer valueOf2 = Integer.valueOf(R.id.actionPrev);
        Integer valueOf3 = Integer.valueOf(R.id.actionNext);
        SEMANTIC_ACTIONS_COMPACT = List.of(valueOf, valueOf2, valueOf3);
        SEMANTIC_ACTIONS_HIDE_WHEN_SCRUBBING = List.of(valueOf2, valueOf3);
        SEMANTIC_ACTIONS_ALL = List.of(valueOf, valueOf2, valueOf3, Integer.valueOf(R.id.action0), Integer.valueOf(R.id.action1));
    }

    public MediaControlPanel(Context context, Executor executor, DelayableExecutor delayableExecutor, ActivityStarter activityStarter, BroadcastSender broadcastSender, MediaViewController mediaViewController, SeekBarViewModel seekBarViewModel, Lazy lazy, MediaOutputDialogManager mediaOutputDialogManager, MediaCarouselController mediaCarouselController, FalsingManager falsingManager, SystemClock systemClock, MediaUiEventLogger mediaUiEventLogger, KeyguardStateController keyguardStateController, ActivityIntentHelper activityIntentHelper, CommunalSceneInteractor communalSceneInteractor, NotificationLockscreenUserManager notificationLockscreenUserManager, BroadcastDialogController broadcastDialogController, GlobalSettings globalSettings) {
        this.mContext = context;
        this.mBackgroundExecutor = executor;
        this.mMainExecutor = delayableExecutor;
        this.mActivityStarter = activityStarter;
        this.mBroadcastSender = broadcastSender;
        this.mSeekBarViewModel = seekBarViewModel;
        this.mMediaViewController = mediaViewController;
        this.mMediaDataManagerLazy = lazy;
        this.mMediaOutputDialogManager = mediaOutputDialogManager;
        this.mMediaCarouselController = mediaCarouselController;
        this.mFalsingManager = falsingManager;
        this.mSystemClock = systemClock;
        this.mLogger = mediaUiEventLogger;
        this.mKeyguardStateController = keyguardStateController;
        this.mActivityIntentHelper = activityIntentHelper;
        this.mLockscreenUserManager = notificationLockscreenUserManager;
        this.mCommunalSceneInteractor = communalSceneInteractor;
        seekBarViewModel.logSeek = new MediaControlPanel$$ExternalSyntheticLambda5(this, 0);
        this.mGlobalSettings = globalSettings;
        SeekBarObserver seekBarObserver = this.mSeekBarObserver;
        if (seekBarObserver != null) {
            String string = ((GlobalSettingsImpl) globalSettings).getString("animator_duration_scale");
            float f = 1.0f;
            if (string != null) {
                try {
                    f = Float.parseFloat(string);
                } catch (NumberFormatException unused) {
                }
            }
            seekBarObserver.animationEnabled = f > 0.0f;
        }
    }

    public static void scaleTransitionDrawableLayer(TransitionDrawable transitionDrawable, int i, int i2, int i3) {
        Drawable drawable = transitionDrawable.getDrawable(i);
        if (drawable == null) {
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Pair pair = new Pair(Integer.valueOf(intrinsicWidth), Integer.valueOf(intrinsicHeight));
        Pair pair2 = new Pair(Integer.valueOf(i2), Integer.valueOf(i3));
        float intValue = ((Integer) pair.first).intValue();
        float intValue2 = ((Integer) pair.second).intValue();
        float intValue3 = ((Integer) pair2.first).intValue();
        float intValue4 = ((Integer) pair2.second).intValue();
        float f = 0.0f;
        if (intValue != 0.0f && intValue2 != 0.0f && intValue3 != 0.0f && intValue4 != 0.0f) {
            f = intValue / intValue2 > intValue3 / intValue4 ? intValue4 / intValue2 : intValue3 / intValue;
        }
        if (f == 0.0f) {
            return;
        }
        transitionDrawable.setLayerSize(i, (int) (intrinsicWidth * f), (int) (f * intrinsicHeight));
    }

    public static void setVisibleAndAlpha(ConstraintSet constraintSet, int i, boolean z) {
        setVisibleAndAlpha(constraintSet, i, z, 8);
    }

    public static LayerDrawable setupGradientColorOnDrawable(Drawable drawable, GradientDrawable gradientDrawable, ColorScheme colorScheme, float f) {
        gradientDrawable.setColors(new int[]{ColorUtilKt.getColorWithAlpha(colorScheme.mAccent2.getS700(), f), ColorUtilKt.getColorWithAlpha(colorScheme.mAccent1.getS700(), 1.0f)});
        return new LayerDrawable(new Drawable[]{drawable, gradientDrawable});
    }

    public LayerDrawable addGradientToPlayerAlbum(Icon icon, ColorScheme colorScheme, int i, int i2) {
        return setupGradientColorOnDrawable(getScaledBackground(icon, i, i2), (GradientDrawable) this.mContext.getDrawable(R.drawable.qs_media_scrim).mutate(), colorScheme, 0.25f);
    }

    public LayerDrawable addGradientToRecommendationAlbum(Icon icon, ColorScheme colorScheme, int i, int i2) {
        Drawable drawable = null;
        if (i != 0 && i2 != 0 && icon != null && ((icon.getType() == 1 || icon.getType() == 5) && icon.getBitmap() != null)) {
            drawable = new BitmapDrawable(this.mContext.getResources(), Bitmap.createScaledBitmap(icon.getBitmap(), i, i2, false));
        }
        if (drawable == null) {
            drawable = getScaledBackground(icon, i, i2);
        }
        return setupGradientColorOnDrawable(drawable, (GradientDrawable) this.mContext.getDrawable(R.drawable.qs_media_rec_scrim).mutate(), colorScheme, 0.15f);
    }

    public final void attachPlayer(MediaViewHolder mediaViewHolder) {
        this.mMediaViewHolder = mediaViewHolder;
        TransitionLayout transitionLayout = mediaViewHolder.player;
        SeekBarObserver seekBarObserver = new SeekBarObserver(mediaViewHolder);
        this.mSeekBarObserver = seekBarObserver;
        SeekBarViewModel seekBarViewModel = this.mSeekBarViewModel;
        seekBarViewModel._progress.observeForever(seekBarObserver);
        SeekBar seekBar = mediaViewHolder.seekBar;
        seekBar.setOnSeekBarChangeListener(new SeekBarViewModel.SeekBarChangeListener(seekBarViewModel, seekBarViewModel.falsingManager));
        seekBar.setOnTouchListener(new SeekBarViewModel.SeekBarTouchListener(seekBarViewModel, seekBar));
        seekBarViewModel.scrubbingChangeListener = this.mScrubbingChangeListener;
        seekBarViewModel.enabledChangeListener = this.mEnabledChangeListener;
        this.mMediaViewController.attach(transitionLayout, MediaViewController.TYPE.PLAYER);
        transitionLayout.setOnLongClickListener(new MediaControlPanel$$ExternalSyntheticLambda0(this, 0));
        this.mMediaViewHolder.albumView.setLayerType(2, null);
        MediaViewHolder mediaViewHolder2 = this.mMediaViewHolder;
        TextView textView = mediaViewHolder2.titleText;
        TextView textView2 = mediaViewHolder2.artistText;
        CachingIconView cachingIconView = mediaViewHolder2.explicitIndicator;
        AnimatorSet loadAnimator = loadAnimator(R.anim.media_metadata_enter, Interpolators.EMPHASIZED_DECELERATE, textView, textView2, cachingIconView);
        AnimatorSet loadAnimator2 = loadAnimator(R.anim.media_metadata_exit, Interpolators.EMPHASIZED_ACCELERATE, textView, textView2, cachingIconView);
        this.mMultiRippleController = new MultiRippleController(mediaViewHolder.multiRippleView);
        BlendMode blendMode = BlendMode.SCREEN;
        TurbulenceNoiseView turbulenceNoiseView = mediaViewHolder.turbulenceNoiseView;
        turbulenceNoiseView.paint.setBlendMode(blendMode);
        LoadingEffectView loadingEffectView = mediaViewHolder.loadingEffectView;
        loadingEffectView.blendMode = blendMode;
        loadingEffectView.setVisibility(4);
        this.mColorSchemeTransition = new ColorSchemeTransition(this.mContext, this.mMediaViewHolder, this.mMultiRippleController, new TurbulenceNoiseController(turbulenceNoiseView));
        this.mMetadataAnimationHandler = new MetadataAnimationHandler(loadAnimator2, loadAnimator);
    }

    public final void bindButtonCommon(final ImageButton imageButton, MediaAction mediaAction) {
        if (mediaAction == null) {
            imageButton.setImageDrawable(null);
            imageButton.setContentDescription(null);
            imageButton.setEnabled(false);
            imageButton.setBackground(null);
            return;
        }
        final Drawable drawable = mediaAction.icon;
        imageButton.setImageDrawable(drawable);
        imageButton.setContentDescription(mediaAction.contentDescription);
        final Drawable drawable2 = mediaAction.background;
        imageButton.setBackground(drawable2);
        final Runnable runnable = mediaAction.action;
        if (runnable == null) {
            imageButton.setEnabled(false);
        } else {
            imageButton.setEnabled(true);
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda13
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MediaControlPanel mediaControlPanel = MediaControlPanel.this;
                    ImageButton imageButton2 = imageButton;
                    Runnable runnable2 = runnable;
                    Object obj = drawable;
                    Object obj2 = drawable2;
                    if (mediaControlPanel.mFalsingManager.isFalseTap(2)) {
                        return;
                    }
                    int id = imageButton2.getId();
                    int i = mediaControlPanel.mUid;
                    String str = mediaControlPanel.mPackageName;
                    InstanceId instanceId = mediaControlPanel.mInstanceId;
                    MediaUiEventLogger mediaUiEventLogger = mediaControlPanel.mLogger;
                    mediaUiEventLogger.getClass();
                    mediaUiEventLogger.logger.logWithInstanceId(id == R.id.actionPlayPause ? MediaUiEvent.TAP_ACTION_PLAY_PAUSE : id == R.id.actionPrev ? MediaUiEvent.TAP_ACTION_PREV : id == R.id.actionNext ? MediaUiEvent.TAP_ACTION_NEXT : MediaUiEvent.TAP_ACTION_OTHER, i, str, instanceId);
                    mediaControlPanel.logSmartspaceCardReported(760, 0, 0);
                    mediaControlPanel.mWasPlaying = mediaControlPanel.isPlaying();
                    mediaControlPanel.mButtonClicked = true;
                    runnable2.run();
                    MultiRippleController multiRippleController = mediaControlPanel.mMultiRippleController;
                    float width = mediaControlPanel.mMediaViewHolder.multiRippleView.getWidth() * 2;
                    RippleShader.RippleShape rippleShape = RippleShader.RippleShape.CIRCLE;
                    final RippleAnimation rippleAnimation = new RippleAnimation(new RippleAnimationConfig((imageButton2.getWidth() * 0.5f) + imageButton2.getX(), (imageButton2.getHeight() * 0.5f) + imageButton2.getY(), width, width, mediaControlPanel.mContext.getResources().getDisplayMetrics().density, mediaControlPanel.mColorSchemeTransition.accentPrimary.currentColor));
                    MultiRippleView multiRippleView = multiRippleController.multipleRippleView;
                    if (multiRippleView.ripples.size() < 10) {
                        multiRippleView.ripples.add(rippleAnimation);
                        final MultiRippleController$play$1 multiRippleController$play$1 = new MultiRippleController$play$1(multiRippleController, rippleAnimation);
                        if (!rippleAnimation.animator.isRunning()) {
                            rippleAnimation.animator.setDuration(1500L);
                            rippleAnimation.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.surfaceeffects.ripple.RippleAnimation$play$1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    long currentPlayTime = valueAnimator.getCurrentPlayTime();
                                    RippleAnimation.this.rippleShader.setRawProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                                    RippleAnimation.this.rippleShader.setDistortionStrength(0.0f);
                                    RippleAnimation.this.rippleShader.setFloatUniform("in_time", currentPlayTime);
                                }
                            });
                            rippleAnimation.animator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.surfaceeffects.ripple.RippleAnimation$play$2
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public final void onAnimationEnd(Animator animator) {
                                    MultiRippleController$play$1.this.run();
                                }
                            });
                            rippleAnimation.animator.start();
                        }
                        multiRippleView.invalidate();
                    }
                    if (obj instanceof Animatable) {
                        ((Animatable) obj).start();
                    }
                    if (obj2 instanceof Animatable) {
                        ((Animatable) obj2).start();
                    }
                }
            });
        }
    }

    public final void bindGutsMenuCommon(boolean z, String str, GutsViewHolder gutsViewHolder, Runnable runnable) {
        final int i = 1;
        final int i2 = 0;
        gutsViewHolder.gutsText.setText(z ? this.mContext.getString(R.string.controls_media_close_session, str) : this.mContext.getString(R.string.controls_media_active_session));
        gutsViewHolder.dismissText.setVisibility(z ? 0 : 8);
        gutsViewHolder.dismiss.setEnabled(z);
        gutsViewHolder.dismiss.setOnClickListener(new MediaControlPanel$$ExternalSyntheticLambda1(this, runnable, i));
        TextView textView = gutsViewHolder.cancelText;
        if (z) {
            textView.setBackground(this.mContext.getDrawable(R.drawable.qs_media_outline_button));
        } else {
            textView.setBackground(this.mContext.getDrawable(R.drawable.qs_media_solid_button));
        }
        gutsViewHolder.cancel.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda27
            public final /* synthetic */ MediaControlPanel f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i2;
                MediaControlPanel mediaControlPanel = this.f$0;
                switch (i3) {
                    case 0:
                        if (!mediaControlPanel.mFalsingManager.isFalseTap(1)) {
                            mediaControlPanel.closeGuts(false);
                            break;
                        }
                        break;
                    default:
                        if (!mediaControlPanel.mFalsingManager.isFalseTap(1)) {
                            mediaControlPanel.mLogger.logger.logWithInstanceId(MediaUiEvent.OPEN_SETTINGS_LONG_PRESS, mediaControlPanel.mUid, mediaControlPanel.mPackageName, mediaControlPanel.mInstanceId);
                            mediaControlPanel.mActivityStarter.startActivity(MediaControlPanel.SETTINGS_INTENT, true);
                            break;
                        }
                        break;
                }
            }
        });
        if (gutsViewHolder.isDismissible != z) {
            gutsViewHolder.isDismissible = z;
            ColorScheme colorScheme = gutsViewHolder.colorScheme;
            if (colorScheme != null) {
                gutsViewHolder.setColors(colorScheme);
            }
        }
        gutsViewHolder.settings.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda27
            public final /* synthetic */ MediaControlPanel f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i;
                MediaControlPanel mediaControlPanel = this.f$0;
                switch (i3) {
                    case 0:
                        if (!mediaControlPanel.mFalsingManager.isFalseTap(1)) {
                            mediaControlPanel.closeGuts(false);
                            break;
                        }
                        break;
                    default:
                        if (!mediaControlPanel.mFalsingManager.isFalseTap(1)) {
                            mediaControlPanel.mLogger.logger.logWithInstanceId(MediaUiEvent.OPEN_SETTINGS_LONG_PRESS, mediaControlPanel.mUid, mediaControlPanel.mPackageName, mediaControlPanel.mInstanceId);
                            mediaControlPanel.mActivityStarter.startActivity(MediaControlPanel.SETTINGS_INTENT, true);
                            break;
                        }
                        break;
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void bindPlayer(final MediaData mediaData, String str) {
        boolean z;
        boolean z2;
        AnimationBindHandler animationBindHandler;
        Double d;
        int i = 2;
        int i2 = 0;
        int i3 = 1;
        if (this.mMediaViewHolder == null) {
            return;
        }
        if (Trace.isEnabled()) {
            Trace.traceBegin(4096L, "MediaControlPanel#bindPlayer<" + str + ">");
        }
        this.mKey = str;
        this.mMediaData = mediaData;
        MediaSession.Token token = mediaData.token;
        this.mPackageName = mediaData.packageName;
        int i4 = mediaData.appUid;
        this.mUid = i4;
        if (this.mSmartspaceId == -1) {
            ((SystemClockImpl) this.mSystemClock).getClass();
            this.mSmartspaceId = SmallHash.hash(i4 + ((int) System.currentTimeMillis()));
        }
        this.mInstanceId = mediaData.instanceId;
        MediaSession.Token token2 = this.mToken;
        if (token2 == null || !token2.equals(token)) {
            this.mToken = token;
        }
        if (this.mToken != null) {
            this.mController = new MediaController(this.mContext, this.mToken);
        } else {
            this.mController = null;
        }
        PendingIntent pendingIntent = mediaData.clickIntent;
        if (pendingIntent != null) {
            this.mMediaViewHolder.player.setOnClickListener(new MediaControlPanel$$ExternalSyntheticLambda1(this, pendingIntent, str));
        }
        boolean z3 = mediaData.resumption;
        if (!z3 || (d = mediaData.resumeProgress) == null) {
            this.mBackgroundExecutor.execute(new MediaControlPanel$$ExternalSyntheticLambda2(this, this.mController, i2));
        } else {
            this.mSeekBarViewModel.set_data(new SeekBarViewModel.Progress(true, false, false, false, Integer.valueOf((int) (d.doubleValue() * 100)), 100, false));
        }
        ViewGroup viewGroup = this.mMediaViewHolder.seamless;
        viewGroup.setVisibility(0);
        MediaViewHolder mediaViewHolder = this.mMediaViewHolder;
        ImageView imageView = mediaViewHolder.seamlessIcon;
        TextView textView = mediaViewHolder.seamlessText;
        MediaDeviceData mediaDeviceData = mediaData.device;
        boolean z4 = !(mediaDeviceData == null || mediaDeviceData.enabled) || z3;
        boolean z5 = !z4;
        CharSequence string = this.mContext.getString(R.string.media_seamless_other_device);
        this.mMediaViewHolder.seamlessButton.setAlpha(z4 ? 0.38f : 1.0f);
        viewGroup.setEnabled(z5);
        if (mediaDeviceData != null) {
            Drawable drawable = mediaDeviceData.icon;
            if (drawable instanceof AdaptiveIcon) {
                AdaptiveIcon adaptiveIcon = (AdaptiveIcon) drawable;
                adaptiveIcon.setBackgroundColor(this.mColorSchemeTransition.bgColor);
                imageView.setImageDrawable(adaptiveIcon);
            } else {
                imageView.setImageDrawable(drawable);
            }
            CharSequence charSequence = mediaDeviceData.name;
            if (charSequence != null) {
                string = charSequence;
            }
        } else {
            imageView.setImageResource(R.drawable.ic_media_home_devices);
        }
        textView.setText(string);
        viewGroup.setContentDescription(string);
        viewGroup.setOnClickListener(new MediaControlPanel$$ExternalSyntheticLambda1(this, mediaDeviceData, i));
        bindGutsMenuCommon(mediaData.isClearable, mediaData.app, this.mMediaViewHolder.gutsViewHolder, new MediaControlPanel$$ExternalSyntheticLambda2(this, mediaData, 4));
        bindPlayerContentDescription(mediaData);
        bindScrubbingTime(mediaData);
        ArrayList<ImageButton> arrayList = new ArrayList();
        Iterator it = MediaViewHolder.genericButtonIds.iterator();
        while (it.hasNext()) {
            arrayList.add(this.mMediaViewHolder.getAction(((Integer) it.next()).intValue()));
        }
        MediaViewController mediaViewController = this.mMediaViewController;
        ConstraintSet constraintSet = mediaViewController.expandedLayout;
        ConstraintSet constraintSet2 = mediaViewController.collapsedLayout;
        final MediaButton mediaButton = mediaData.semanticActions;
        if (mediaButton != null) {
            for (ImageButton imageButton : arrayList) {
                setVisibleAndAlpha(constraintSet2, imageButton.getId(), false);
                setVisibleAndAlpha(constraintSet, imageButton.getId(), false);
            }
            Iterator it2 = SEMANTIC_ACTIONS_ALL.iterator();
            while (it2.hasNext()) {
                int intValue = ((Integer) it2.next()).intValue();
                final ImageButton action = this.mMediaViewHolder.getAction(intValue);
                final MediaAction actionById = mediaButton.getActionById(intValue);
                if (action.getTag() == null) {
                    animationBindHandler = new AnimationBindHandler();
                    action.setTag(animationBindHandler);
                } else {
                    animationBindHandler = (AnimationBindHandler) action.getTag();
                }
                final AnimationBindHandler animationBindHandler2 = animationBindHandler;
                Function0 function0 = new Function0() { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        ImageButton imageButton2 = action;
                        MediaControlPanel mediaControlPanel = MediaControlPanel.this;
                        mediaControlPanel.getClass();
                        MediaAction mediaAction = actionById;
                        AnimationBindHandler animationBindHandler3 = animationBindHandler2;
                        if (mediaAction != null) {
                            Integer num = animationBindHandler3.rebindId;
                            Integer num2 = mediaAction.rebindId;
                            if (num == null || num2 == null || !Intrinsics.areEqual(num, num2)) {
                                animationBindHandler3.rebindId = num2;
                                animationBindHandler3.unregisterAll();
                                animationBindHandler3.tryRegister(mediaAction.icon);
                                animationBindHandler3.tryRegister(mediaAction.background);
                                mediaControlPanel.bindButtonCommon(imageButton2, mediaAction);
                            }
                        } else {
                            animationBindHandler3.unregisterAll();
                            imageButton2.setImageDrawable(null);
                            imageButton2.setContentDescription(null);
                            imageButton2.setEnabled(false);
                            imageButton2.setBackground(null);
                        }
                        mediaControlPanel.setSemanticButtonVisibleAndAlpha(imageButton2.getId(), mediaAction, mediaButton);
                        return Unit.INSTANCE;
                    }
                };
                if (animationBindHandler2.isAnimationRunning()) {
                    animationBindHandler2.onAnimationsComplete.add(function0);
                } else {
                    function0.invoke();
                }
            }
        } else {
            Iterator it3 = SEMANTIC_ACTIONS_COMPACT.iterator();
            while (it3.hasNext()) {
                int intValue2 = ((Integer) it3.next()).intValue();
                setVisibleAndAlpha(constraintSet2, intValue2, false);
                setVisibleAndAlpha(constraintSet, intValue2, false);
            }
            List list = mediaData.actionsToShowInCompact;
            List notificationActions = MediaActionsKt.getNotificationActions(mediaData.actions, this.mActivityStarter);
            int i5 = 0;
            while (true) {
                ArrayList arrayList2 = (ArrayList) notificationActions;
                if (i5 >= arrayList2.size() || i5 >= arrayList.size()) {
                    break;
                }
                boolean contains = list.contains(Integer.valueOf(i5));
                ImageButton imageButton2 = (ImageButton) arrayList.get(i5);
                MediaAction mediaAction = (MediaAction) arrayList2.get(i5);
                bindButtonCommon(imageButton2, mediaAction);
                boolean z6 = mediaAction != null;
                setVisibleAndAlpha(constraintSet, imageButton2.getId(), z6);
                setVisibleAndAlpha(constraintSet2, imageButton2.getId(), z6 && contains);
                i5++;
            }
            while (i5 < arrayList.size()) {
                ImageButton imageButton3 = (ImageButton) arrayList.get(i5);
                bindButtonCommon(imageButton3, null);
                setVisibleAndAlpha(constraintSet, imageButton3.getId(), false);
                setVisibleAndAlpha(constraintSet2, imageButton3.getId(), false);
                i5++;
            }
        }
        updateSeekBarVisibility();
        MediaViewHolder mediaViewHolder2 = this.mMediaViewHolder;
        final TextView textView2 = mediaViewHolder2.titleText;
        final TextView textView3 = mediaViewHolder2.artistText;
        final ConstraintSet constraintSet3 = mediaViewController.expandedLayout;
        final ConstraintSet constraintSet4 = mediaViewController.collapsedLayout;
        MetadataAnimationHandler metadataAnimationHandler = this.mMetadataAnimationHandler;
        Triple triple = new Triple(mediaData.song, mediaData.artist, Boolean.valueOf(mediaData.isExplicit));
        Function0 function02 = new Function0() { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TextView textView4 = textView2;
                TextView textView5 = textView3;
                MediaControlPanel mediaControlPanel = MediaControlPanel.this;
                mediaControlPanel.getClass();
                MediaData mediaData2 = mediaData;
                textView4.setText(mediaData2.song);
                textView5.setText(mediaData2.artist);
                ConstraintSet constraintSet5 = constraintSet3;
                boolean z7 = mediaData2.isExplicit;
                MediaControlPanel.setVisibleAndAlpha(constraintSet5, R.id.media_explicit_indicator, z7);
                MediaControlPanel.setVisibleAndAlpha(constraintSet4, R.id.media_explicit_indicator, z7);
                mediaControlPanel.mMediaViewController.refreshState();
                return Unit.INSTANCE;
            }
        };
        MediaControlPanel$$ExternalSyntheticLambda5 mediaControlPanel$$ExternalSyntheticLambda5 = new MediaControlPanel$$ExternalSyntheticLambda5(this, 2);
        if (triple.equals(metadataAnimationHandler.targetData)) {
            z = false;
        } else {
            metadataAnimationHandler.targetData = triple;
            metadataAnimationHandler.postExitUpdate = function02;
            metadataAnimationHandler.postEnterUpdate = mediaControlPanel$$ExternalSyntheticLambda5;
            if (!metadataAnimationHandler.isRunning()) {
                metadataAnimationHandler.exitAnimator.start();
            }
            z = true;
        }
        final int hashCode = mediaData.hashCode();
        final String str2 = "MediaControlPanel#bindArtworkAndColors<" + str + ">";
        Trace.beginAsyncSection(str2, hashCode);
        final int i6 = this.mArtworkNextBindRequestId;
        this.mArtworkNextBindRequestId = i6 + 1;
        if (z) {
            this.mIsArtworkBound = false;
        }
        final int measuredWidth = this.mMediaViewHolder.albumView.getMeasuredWidth();
        final int measuredHeight = this.mMediaViewHolder.albumView.getMeasuredHeight();
        final boolean z7 = z;
        this.mBackgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                final Drawable drawable2;
                final ColorScheme colorScheme;
                final MediaControlPanel mediaControlPanel = MediaControlPanel.this;
                final MediaData mediaData2 = mediaData;
                final int i7 = measuredWidth;
                final int i8 = measuredHeight;
                final int i9 = i6;
                final String str3 = str2;
                final int i10 = hashCode;
                final boolean z8 = z7;
                mediaControlPanel.getClass();
                Icon icon = mediaData2.artwork;
                String str4 = mediaData2.packageName;
                WallpaperColors wallpaperColor = mediaControlPanel.getWallpaperColor(icon);
                Style style = Style.CONTENT;
                final boolean z9 = true;
                if (wallpaperColor != null) {
                    ColorScheme colorScheme2 = new ColorScheme(wallpaperColor, true, style);
                    drawable2 = mediaControlPanel.addGradientToPlayerAlbum(icon, colorScheme2, i7, i8);
                    colorScheme = colorScheme2;
                } else {
                    Drawable colorDrawable = new ColorDrawable(0);
                    try {
                        drawable2 = colorDrawable;
                        z9 = false;
                        colorScheme = new ColorScheme(WallpaperColors.fromDrawable(mediaControlPanel.mContext.getPackageManager().getApplicationIcon(str4)), true, style);
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.w("MediaControlPanel", "Cannot find icon for package " + str4, e);
                        drawable2 = colorDrawable;
                        colorScheme = null;
                        z9 = false;
                    }
                }
                ((ExecutorImpl) mediaControlPanel.mMainExecutor).execute(new Runnable() { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda25
                    @Override // java.lang.Runnable
                    public final void run() {
                        boolean z10;
                        MediaControlPanel mediaControlPanel2 = MediaControlPanel.this;
                        int i11 = i9;
                        String str5 = str3;
                        int i12 = i10;
                        ColorScheme colorScheme3 = colorScheme;
                        boolean z11 = z8;
                        boolean z12 = z9;
                        Drawable drawable3 = drawable2;
                        int i13 = i7;
                        int i14 = i8;
                        MediaData mediaData3 = mediaData2;
                        if (i11 < mediaControlPanel2.mArtworkBoundId) {
                            Trace.endAsyncSection(str5, i12);
                            return;
                        }
                        mediaControlPanel2.mArtworkBoundId = i11;
                        ColorSchemeTransition colorSchemeTransition = mediaControlPanel2.mColorSchemeTransition;
                        AnimatingColorTransition[] animatingColorTransitionArr = colorSchemeTransition.colorTransitions;
                        int length = animatingColorTransitionArr.length;
                        int i15 = 0;
                        boolean z13 = false;
                        while (i15 < length) {
                            AnimatingColorTransition animatingColorTransition = animatingColorTransitionArr[i15];
                            int intValue3 = colorScheme3 == null ? animatingColorTransition.defaultColor : ((Number) animatingColorTransition.extractColor.invoke(colorScheme3)).intValue();
                            AnimatingColorTransition[] animatingColorTransitionArr2 = animatingColorTransitionArr;
                            if (intValue3 != animatingColorTransition.targetColor) {
                                animatingColorTransition.sourceColor = animatingColorTransition.currentColor;
                                animatingColorTransition.targetColor = intValue3;
                                animatingColorTransition.valueAnimator.cancel();
                                animatingColorTransition.valueAnimator.start();
                                z10 = true;
                            } else {
                                z10 = false;
                            }
                            if (!animatingColorTransition.equals(colorSchemeTransition.colorSeamless)) {
                                z13 = z10 || z13;
                            }
                            i15++;
                            animatingColorTransitionArr = animatingColorTransitionArr2;
                        }
                        if (colorScheme3 != null) {
                            colorSchemeTransition.mediaViewHolder.gutsViewHolder.colorScheme = colorScheme3;
                        }
                        ImageView imageView2 = mediaControlPanel2.mMediaViewHolder.albumView;
                        imageView2.setPadding(0, 0, 0, 0);
                        if (z11 || z13 || (!mediaControlPanel2.mIsArtworkBound && z12)) {
                            if (mediaControlPanel2.mPrevArtwork == null) {
                                imageView2.setImageDrawable(drawable3);
                            } else {
                                TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{mediaControlPanel2.mPrevArtwork, drawable3});
                                MediaControlPanel.scaleTransitionDrawableLayer(transitionDrawable, 0, i13, i14);
                                MediaControlPanel.scaleTransitionDrawableLayer(transitionDrawable, 1, i13, i14);
                                transitionDrawable.setLayerGravity(0, 17);
                                transitionDrawable.setLayerGravity(1, 17);
                                transitionDrawable.setCrossFadeEnabled(true);
                                imageView2.setImageDrawable(transitionDrawable);
                                transitionDrawable.startTransition(z12 ? 333 : 80);
                            }
                            mediaControlPanel2.mPrevArtwork = drawable3;
                            mediaControlPanel2.mIsArtworkBound = z12;
                        }
                        ImageView imageView3 = mediaControlPanel2.mMediaViewHolder.appIcon;
                        imageView3.clearColorFilter();
                        Icon icon2 = mediaData3.appIcon;
                        String str6 = mediaData3.packageName;
                        if (icon2 == null || mediaData3.resumption) {
                            ColorMatrix colorMatrix = new ColorMatrix();
                            colorMatrix.setSaturation(0.0f);
                            imageView3.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
                            try {
                                imageView3.setImageDrawable(mediaControlPanel2.mContext.getPackageManager().getApplicationIcon(str6));
                            } catch (PackageManager.NameNotFoundException e2) {
                                Log.w("MediaControlPanel", "Cannot find icon for package " + str6, e2);
                                imageView3.setImageResource(R.drawable.ic_music_note);
                            }
                        } else {
                            imageView3.setImageIcon(icon2);
                            imageView3.setColorFilter(mediaControlPanel2.mColorSchemeTransition.accentPrimary.targetColor);
                        }
                        Trace.endAsyncSection(str5, i12);
                    }
                });
            }
        });
        if (!this.mMetadataAnimationHandler.isRunning()) {
            mediaViewController.refreshState();
        }
        if (this.mButtonClicked && !this.mWasPlaying && isPlaying()) {
            if (this.mTurbulenceNoiseAnimationConfig == null) {
                LoadingEffectView loadingEffectView = this.mMediaViewHolder.loadingEffectView;
                int width = loadingEffectView.getWidth();
                int height = loadingEffectView.getHeight();
                Random random = new Random();
                this.mTurbulenceNoiseAnimationConfig = new TurbulenceNoiseAnimationConfig(random.nextFloat(), random.nextFloat(), random.nextFloat(), width, height, this.mContext.getResources().getDisplayMetrics().density, this.mColorSchemeTransition.accentPrimary.currentColor);
            }
            if (this.mLoadingEffect == null) {
                TurbulenceNoiseShader$Companion$Type turbulenceNoiseShader$Companion$Type = TurbulenceNoiseShader$Companion$Type.SIMPLEX_NOISE;
                LoadingEffect loadingEffect = new LoadingEffect(this.mTurbulenceNoiseAnimationConfig, this.mNoiseDrawCallback, this.mStateChangedCallback);
                this.mLoadingEffect = loadingEffect;
                this.mColorSchemeTransition.loadingEffect = loadingEffect;
            }
            LoadingEffect loadingEffect2 = this.mLoadingEffect;
            LoadingEffect.AnimationState animationState = loadingEffect2.state;
            LoadingEffect.AnimationState animationState2 = LoadingEffect.AnimationState.NOT_PLAYING;
            if (animationState == animationState2 && animationState == animationState2) {
                loadingEffect2.setState(LoadingEffect.AnimationState.EASE_IN);
                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                loadingEffect2.config.getClass();
                ofFloat.setDuration((long) 1350.0f);
                TurbulenceNoiseShader turbulenceNoiseShader = loadingEffect2.turbulenceNoiseShader;
                ofFloat.addUpdateListener(new LoadingEffect$playMain$1(loadingEffect2, turbulenceNoiseShader.noiseOffsetX, turbulenceNoiseShader.noiseOffsetY, turbulenceNoiseShader.noiseOffsetZ, 1));
                ofFloat.addListener(new LoadingEffect$playMain$2(loadingEffect2, i3));
                ofFloat.start();
                loadingEffect2.currentAnimator = ofFloat;
            }
            LoadingEffect loadingEffect3 = this.mLoadingEffect;
            Objects.requireNonNull(loadingEffect3);
            z2 = false;
            this.mMainExecutor.executeDelayed(new MediaControlPanel$$ExternalSyntheticLambda3(0 == true ? 1 : 0, loadingEffect3), TURBULENCE_NOISE_PLAY_DURATION);
        } else {
            z2 = false;
        }
        this.mButtonClicked = z2;
        this.mWasPlaying = isPlaying();
        Trace.endSection();
    }

    public final void bindPlayerContentDescription(MediaData mediaData) {
        MediaViewHolder mediaViewHolder = this.mMediaViewHolder;
        if (mediaViewHolder == null) {
            return;
        }
        this.mMediaViewHolder.player.setContentDescription(this.mMediaViewController.isGutsVisible ? mediaViewHolder.gutsViewHolder.gutsText.getText() : mediaData != null ? this.mContext.getString(R.string.controls_media_playing_item_description, mediaData.song, mediaData.artist, mediaData.app) : null);
    }

    public final void bindRecommendation(SmartspaceMediaData smartspaceMediaData) {
        boolean z;
        SmartspaceMediaData smartspaceMediaData2 = smartspaceMediaData;
        int i = 2;
        if (this.mRecommendationViewHolder == null) {
            return;
        }
        if (!smartspaceMediaData.isValid()) {
            Log.e("MediaControlPanel", "Received an invalid recommendation list; returning");
            return;
        }
        boolean isEnabled = Trace.isEnabled();
        String str = ">";
        String str2 = smartspaceMediaData2.packageName;
        if (isEnabled) {
            Trace.traceBegin(4096L, "MediaControlPanel#bindRecommendation<" + str2 + ">");
        }
        this.mRecommendationData = smartspaceMediaData2;
        this.mSmartspaceId = SmallHash.hash(Objects.hashCode(smartspaceMediaData2.targetId));
        this.mPackageName = str2;
        this.mInstanceId = smartspaceMediaData2.instanceId;
        try {
            ApplicationInfo applicationInfo = this.mContext.getPackageManager().getApplicationInfo(str2, 0);
            this.mUid = applicationInfo.uid;
            CharSequence appName = smartspaceMediaData2.getAppName(this.mContext);
            if (appName == null) {
                Log.w("MediaControlPanel", "Fail to get media recommendation's app name");
                Trace.endSection();
                return;
            }
            this.mBackgroundExecutor.execute(new MediaControlPanel$$ExternalSyntheticLambda2(this, this.mContext.getPackageManager().getApplicationIcon(applicationInfo), i));
            setSmartspaceRecItemOnClickListener(this.mRecommendationViewHolder.recommendations, smartspaceMediaData2.cardAction, -1);
            bindRecommendationContentDescription(smartspaceMediaData);
            RecommendationViewHolder recommendationViewHolder = this.mRecommendationViewHolder;
            List list = recommendationViewHolder.mediaCoverItems;
            List list2 = recommendationViewHolder.mediaCoverContainers;
            List validRecommendations = smartspaceMediaData.getValidRecommendations();
            int numberOfFittedRecommendations = getNumberOfFittedRecommendations();
            int i2 = 0;
            boolean z2 = false;
            boolean z3 = false;
            while (i2 < 3) {
                SmartspaceAction smartspaceAction = (SmartspaceAction) ((ArrayList) validRecommendations).get(i2);
                ImageView imageView = (ImageView) ((ArrayList) list).get(i2);
                int hashCode = smartspaceAction.hashCode();
                StringBuilder sb = new StringBuilder("MediaControlPanel#bindRecommendationArtwork<");
                String str3 = smartspaceMediaData2.packageName;
                sb.append(str3);
                sb.append(str);
                String sb2 = sb.toString();
                Trace.beginAsyncSection(sb2, hashCode);
                String str4 = str;
                int i3 = i2;
                int i4 = numberOfFittedRecommendations;
                List list3 = list2;
                List list4 = list;
                this.mBackgroundExecutor.execute(new MediaControlPanel$$ExternalSyntheticLambda24(this, smartspaceAction, this.mContext.getResources().getDimensionPixelSize(R.dimen.qs_media_rec_album_width), this.mContext.getResources().getDimensionPixelSize(R.dimen.qs_media_rec_album_height_expanded), i2, str3, sb2, hashCode));
                ViewGroup viewGroup = (ViewGroup) list3.get(i3);
                setSmartspaceRecItemOnClickListener(viewGroup, smartspaceAction, i3);
                viewGroup.setOnLongClickListener(new MediaControlPanel$$ExternalSyntheticLambda0(this, 2));
                String string = smartspaceAction.getExtras().getString("artist_name", "");
                if (string.isEmpty()) {
                    imageView.setContentDescription(this.mContext.getString(R.string.controls_media_smartspace_rec_item_no_artist_description, smartspaceAction.getTitle(), appName));
                } else {
                    imageView.setContentDescription(this.mContext.getString(R.string.controls_media_smartspace_rec_item_description, smartspaceAction.getTitle(), string, appName));
                }
                CharSequence title = smartspaceAction.getTitle();
                z2 |= !TextUtils.isEmpty(title);
                ((TextView) ((ArrayList) this.mRecommendationViewHolder.mediaTitles).get(i3)).setText(title);
                CharSequence subtitle = TextUtils.isEmpty(title) ? "" : smartspaceAction.getSubtitle();
                z3 |= !TextUtils.isEmpty(subtitle);
                ((TextView) ((ArrayList) this.mRecommendationViewHolder.mediaSubtitles).get(i3)).setText(subtitle);
                SeekBar seekBar = (SeekBar) ((ArrayList) this.mRecommendationViewHolder.mediaProgressBars).get(i3);
                TextView textView = (TextView) ((ArrayList) this.mRecommendationViewHolder.mediaSubtitles).get(i3);
                Double descriptionProgress = MediaDataUtils.getDescriptionProgress(smartspaceAction.getExtras());
                if (descriptionProgress == null || descriptionProgress.doubleValue() <= 0.0d) {
                    z = false;
                    seekBar.setVisibility(8);
                    textView.setVisibility(0);
                } else {
                    seekBar.setProgress((int) (descriptionProgress.doubleValue() * 100.0d));
                    z = false;
                    seekBar.setVisibility(0);
                    textView.setVisibility(8);
                }
                i2 = i3 + 1;
                list2 = list3;
                str = str4;
                list = list4;
                numberOfFittedRecommendations = i4;
                smartspaceMediaData2 = smartspaceMediaData;
            }
            int i5 = numberOfFittedRecommendations;
            this.mSmartspaceMediaItemsCount = 3;
            MediaViewController mediaViewController = this.mMediaViewController;
            final ConstraintSet constraintSet = mediaViewController.expandedLayout;
            final ConstraintSet constraintSet2 = mediaViewController.collapsedLayout;
            final int i6 = 0;
            final boolean z4 = z2;
            ((ArrayList) this.mRecommendationViewHolder.mediaTitles).forEach(new Consumer(this) { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda16
                public final /* synthetic */ MediaControlPanel f$0;

                {
                    this.f$0 = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i6) {
                        case 0:
                            MediaControlPanel mediaControlPanel = this.f$0;
                            ConstraintSet constraintSet3 = constraintSet;
                            boolean z5 = z4;
                            ConstraintSet constraintSet4 = constraintSet2;
                            TextView textView2 = (TextView) obj;
                            mediaControlPanel.getClass();
                            MediaControlPanel.setVisibleAndAlpha(constraintSet3, textView2.getId(), z5);
                            MediaControlPanel.setVisibleAndAlpha(constraintSet4, textView2.getId(), z5);
                            break;
                        default:
                            MediaControlPanel mediaControlPanel2 = this.f$0;
                            ConstraintSet constraintSet5 = constraintSet;
                            boolean z6 = z4;
                            ConstraintSet constraintSet6 = constraintSet2;
                            TextView textView3 = (TextView) obj;
                            mediaControlPanel2.getClass();
                            MediaControlPanel.setVisibleAndAlpha(constraintSet5, textView3.getId(), z6);
                            MediaControlPanel.setVisibleAndAlpha(constraintSet6, textView3.getId(), z6);
                            break;
                    }
                }
            });
            final int i7 = 1;
            final boolean z5 = z3;
            ((ArrayList) this.mRecommendationViewHolder.mediaSubtitles).forEach(new Consumer(this) { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda16
                public final /* synthetic */ MediaControlPanel f$0;

                {
                    this.f$0 = this;
                }

                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    switch (i7) {
                        case 0:
                            MediaControlPanel mediaControlPanel = this.f$0;
                            ConstraintSet constraintSet3 = constraintSet;
                            boolean z52 = z5;
                            ConstraintSet constraintSet4 = constraintSet2;
                            TextView textView2 = (TextView) obj;
                            mediaControlPanel.getClass();
                            MediaControlPanel.setVisibleAndAlpha(constraintSet3, textView2.getId(), z52);
                            MediaControlPanel.setVisibleAndAlpha(constraintSet4, textView2.getId(), z52);
                            break;
                        default:
                            MediaControlPanel mediaControlPanel2 = this.f$0;
                            ConstraintSet constraintSet5 = constraintSet;
                            boolean z6 = z5;
                            ConstraintSet constraintSet6 = constraintSet2;
                            TextView textView3 = (TextView) obj;
                            mediaControlPanel2.getClass();
                            MediaControlPanel.setVisibleAndAlpha(constraintSet5, textView3.getId(), z6);
                            MediaControlPanel.setVisibleAndAlpha(constraintSet6, textView3.getId(), z6);
                            break;
                    }
                }
            });
            setMediaCoversVisibility(i5);
            bindGutsMenuCommon(true, appName.toString(), this.mRecommendationViewHolder.gutsViewHolder, new MediaControlPanel$$ExternalSyntheticLambda2(this, smartspaceMediaData, 1));
            this.mController = null;
            MetadataAnimationHandler metadataAnimationHandler = this.mMetadataAnimationHandler;
            if (metadataAnimationHandler == null || !metadataAnimationHandler.isRunning()) {
                mediaViewController.refreshState();
            }
            Trace.endSection();
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("MediaControlPanel", "Fail to get media recommendation's app info", e);
            Trace.endSection();
        }
    }

    public final void bindRecommendationContentDescription(SmartspaceMediaData smartspaceMediaData) {
        RecommendationViewHolder recommendationViewHolder = this.mRecommendationViewHolder;
        if (recommendationViewHolder == null) {
            return;
        }
        this.mRecommendationViewHolder.recommendations.setContentDescription(this.mMediaViewController.isGutsVisible ? recommendationViewHolder.gutsViewHolder.gutsText.getText() : smartspaceMediaData != null ? this.mContext.getString(R.string.controls_media_smartspace_rec_header) : null);
    }

    public final void bindScrubbingTime(MediaData mediaData) {
        ConstraintSet constraintSet = this.mMediaViewController.expandedLayout;
        int id = this.mMediaViewHolder.scrubbingElapsedTimeView.getId();
        int id2 = this.mMediaViewHolder.scrubbingTotalTimeView.getId();
        MediaButton mediaButton = mediaData.semanticActions;
        boolean z = mediaButton != null && SEMANTIC_ACTIONS_HIDE_WHEN_SCRUBBING.stream().allMatch(new MediaControlPanel$$ExternalSyntheticLambda10(mediaButton)) && this.mIsScrubbing;
        setVisibleAndAlpha(constraintSet, id, z);
        setVisibleAndAlpha(constraintSet, id2, z, 8);
    }

    public final ActivityTransitionAnimator.Controller buildLaunchAnimatorController(TransitionLayout transitionLayout) {
        if (!(transitionLayout.getParent() instanceof ViewGroup)) {
            Log.wtf("MediaControlPanel", "Skipping player animation as it is not attached to a ViewGroup", new Exception());
            return null;
        }
        GhostedViewTransitionAnimatorController ghostedViewTransitionAnimatorController = new GhostedViewTransitionAnimatorController(transitionLayout) { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel.3
            @Override // com.android.systemui.animation.GhostedViewTransitionAnimatorController
            public final float getCurrentBottomCornerRadius() {
                return getCurrentTopCornerRadius();
            }

            @Override // com.android.systemui.animation.GhostedViewTransitionAnimatorController
            public final float getCurrentTopCornerRadius() {
                return MediaControlPanel.this.mContext.getResources().getDimension(R.dimen.notification_corner_radius);
            }
        };
        if (this.mMediaViewController.currentEndLocation != 4) {
            return ghostedViewTransitionAnimatorController;
        }
        CommunalSceneInteractor communalSceneInteractor = this.mCommunalSceneInteractor;
        communalSceneInteractor.setIsLaunchingWidget(true);
        return new CommunalTransitionAnimatorController(ghostedViewTransitionAnimatorController, communalSceneInteractor);
    }

    public final void closeGuts(boolean z) {
        MediaViewHolder mediaViewHolder = this.mMediaViewHolder;
        if (mediaViewHolder != null) {
            GutsViewHolder gutsViewHolder = mediaViewHolder.gutsViewHolder;
            Handler handler = gutsViewHolder.gutsText.getHandler();
            if (handler == null) {
                Log.d("MediaViewHolder", "marquee while longPressText.getHandler() is null", new Exception());
            } else {
                handler.postDelayed(new GutsViewHolder$marquee$1(gutsViewHolder, false), 234L);
            }
        } else {
            RecommendationViewHolder recommendationViewHolder = this.mRecommendationViewHolder;
            if (recommendationViewHolder != null) {
                GutsViewHolder gutsViewHolder2 = recommendationViewHolder.gutsViewHolder;
                Handler handler2 = gutsViewHolder2.gutsText.getHandler();
                if (handler2 == null) {
                    Log.d("RecommendationViewHolder", "marquee while longPressText.getHandler() is null", new Exception());
                } else {
                    handler2.postDelayed(new GutsViewHolder$marquee$1(gutsViewHolder2, false), 234L);
                }
            }
        }
        MediaViewController mediaViewController = this.mMediaViewController;
        if (mediaViewController.isGutsVisible) {
            mediaViewController.isGutsVisible = false;
            if (!z) {
                mediaViewController.animateNextStateChange = true;
                mediaViewController.animationDuration = 234L;
                mediaViewController.animationDelay = 0L;
            }
            mediaViewController.setCurrentState(mediaViewController.currentStartLocation, mediaViewController.currentEndLocation, mediaViewController.currentTransitionProgress, z, true);
        }
        if (this.mMediaViewHolder != null) {
            bindPlayerContentDescription(this.mMediaData);
        } else if (this.mRecommendationViewHolder != null) {
            bindRecommendationContentDescription(this.mRecommendationData);
        }
    }

    public boolean getListening() {
        return this.mSeekBarViewModel.listening;
    }

    public int getNumberOfFittedRecommendations() {
        Resources resources = this.mContext.getResources();
        Configuration configuration = resources.getConfiguration();
        int integer = resources.getInteger(R.integer.default_qs_media_rec_width_dp);
        int dimensionPixelSize = (resources.getDimensionPixelSize(R.dimen.qs_media_info_spacing) * 2) + resources.getDimensionPixelSize(R.dimen.qs_media_rec_album_width);
        int i = configuration.screenWidthDp;
        if (configuration.orientation == 2) {
            i /= 2;
        }
        return Math.min(i > integer ? resources.getDimensionPixelSize(R.dimen.qs_media_rec_default_width) / dimensionPixelSize : ((int) TypedValue.applyDimension(1, i, resources.getDisplayMetrics())) / dimensionPixelSize, 3);
    }

    public final Drawable getScaledBackground(Icon icon, int i, int i2) {
        if (icon == null) {
            return null;
        }
        Drawable loadDrawable = icon.loadDrawable(this.mContext);
        Rect rect = new Rect(0, 0, i, i2);
        if (rect.width() > i || rect.height() > i2) {
            rect.offset((int) (-((rect.width() - i) / 2.0f)), (int) (-((rect.height() - i2) / 2.0f)));
        }
        loadDrawable.setBounds(rect);
        return loadDrawable;
    }

    public final int getSurfaceForSmartspaceLogging() {
        int i = this.mMediaViewController.currentEndLocation;
        if (i == 1 || i == 0) {
            return 4;
        }
        if (i == 2) {
            return 2;
        }
        return i == 3 ? 5 : 0;
    }

    public WallpaperColors getWallpaperColor(Icon icon) {
        if (icon != null) {
            if (icon.getType() == 1 || icon.getType() == 5) {
                Bitmap bitmap = icon.getBitmap();
                if (!bitmap.isRecycled()) {
                    return WallpaperColors.fromBitmap(bitmap);
                }
                Log.d("MediaControlPanel", "Cannot load wallpaper color from a recycled bitmap");
                return null;
            }
            Drawable loadDrawable = icon.loadDrawable(this.mContext);
            if (loadDrawable != null) {
                return WallpaperColors.fromDrawable(loadDrawable);
            }
        }
        return null;
    }

    public final boolean isPlaying() {
        PlaybackState playbackState;
        MediaController mediaController = this.mController;
        return (mediaController == null || (playbackState = mediaController.getPlaybackState()) == null || playbackState.getState() != 3) ? false : true;
    }

    public AnimatorSet loadAnimator(int i, Interpolator interpolator, View... viewArr) {
        ArrayList arrayList = new ArrayList();
        for (View view : viewArr) {
            AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this.mContext, i);
            animatorSet.getChildAnimations().get(0).setInterpolator(interpolator);
            animatorSet.setTarget(view);
            arrayList.add(animatorSet);
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(arrayList);
        return animatorSet2;
    }

    public final void logSmartspaceCardReported(int i, int i2, int i3) {
        int i4 = this.mSmartspaceId;
        int i5 = this.mUid;
        int[] iArr = {getSurfaceForSmartspaceLogging()};
        MediaCarouselController mediaCarouselController = this.mMediaCarouselController;
        mediaCarouselController.getClass();
        MediaCarouselController.logSmartspaceCardReported$default(mediaCarouselController, i, i4, i5, iArr, i2, i3, 0, 0, 448);
    }

    public final void onDestroy() {
        SeekBarObserver seekBarObserver = this.mSeekBarObserver;
        SeekBarViewModel seekBarViewModel = this.mSeekBarViewModel;
        if (seekBarObserver != null) {
            seekBarViewModel._progress.removeObserver(seekBarObserver);
        }
        if (this.mScrubbingChangeListener.equals(seekBarViewModel.scrubbingChangeListener)) {
            seekBarViewModel.scrubbingChangeListener = null;
        }
        if (this.mEnabledChangeListener.equals(seekBarViewModel.enabledChangeListener)) {
            seekBarViewModel.enabledChangeListener = null;
        }
        seekBarViewModel.bgExecutor.execute(new SeekBarViewModel$onDestroy$1(seekBarViewModel, 0));
        MediaViewController mediaViewController = this.mMediaViewController;
        mediaViewController.mediaHostStatesManager.controllers.remove(mediaViewController);
        ((ConfigurationControllerImpl) mediaViewController.configurationController).removeCallback(mediaViewController.configurationListener);
    }

    public final void openGuts() {
        MediaViewHolder mediaViewHolder = this.mMediaViewHolder;
        if (mediaViewHolder != null) {
            GutsViewHolder gutsViewHolder = mediaViewHolder.gutsViewHolder;
            Handler handler = gutsViewHolder.gutsText.getHandler();
            if (handler == null) {
                Log.d("MediaViewHolder", "marquee while longPressText.getHandler() is null", new Exception());
            } else {
                handler.postDelayed(new GutsViewHolder$marquee$1(gutsViewHolder, true), 234L);
            }
        } else {
            RecommendationViewHolder recommendationViewHolder = this.mRecommendationViewHolder;
            if (recommendationViewHolder != null) {
                GutsViewHolder gutsViewHolder2 = recommendationViewHolder.gutsViewHolder;
                Handler handler2 = gutsViewHolder2.gutsText.getHandler();
                if (handler2 == null) {
                    Log.d("RecommendationViewHolder", "marquee while longPressText.getHandler() is null", new Exception());
                } else {
                    handler2.postDelayed(new GutsViewHolder$marquee$1(gutsViewHolder2, true), 234L);
                }
            }
        }
        MediaViewController mediaViewController = this.mMediaViewController;
        if (!mediaViewController.isGutsVisible) {
            mediaViewController.isGutsVisible = true;
            mediaViewController.animateNextStateChange = true;
            mediaViewController.animationDuration = 234L;
            mediaViewController.animationDelay = 0L;
            mediaViewController.setCurrentState(mediaViewController.currentStartLocation, mediaViewController.currentEndLocation, mediaViewController.currentTransitionProgress, false, true);
        }
        if (this.mMediaViewHolder != null) {
            bindPlayerContentDescription(this.mMediaData);
        } else if (this.mRecommendationViewHolder != null) {
            bindRecommendationContentDescription(this.mRecommendationData);
        }
        this.mLogger.logger.logWithInstanceId(MediaUiEvent.OPEN_LONG_PRESS, this.mUid, this.mPackageName, this.mInstanceId);
    }

    public final void setMediaCoversVisibility(int i) {
        MediaViewController mediaViewController = this.mMediaViewController;
        ConstraintSet constraintSet = mediaViewController.expandedLayout;
        ConstraintSet constraintSet2 = mediaViewController.collapsedLayout;
        List list = this.mRecommendationViewHolder.mediaCoverContainers;
        int i2 = 0;
        while (i2 < 3) {
            boolean z = true;
            setVisibleAndAlpha(constraintSet, ((ViewGroup) list.get(i2)).getId(), i2 < i);
            int id = ((ViewGroup) list.get(i2)).getId();
            if (i2 >= i) {
                z = false;
            }
            setVisibleAndAlpha(constraintSet2, id, z);
            i2++;
        }
    }

    public final void setSemanticButtonVisibleAndAlpha(int i, MediaAction mediaAction, MediaButton mediaButton) {
        int i2;
        MediaViewController mediaViewController = this.mMediaViewController;
        ConstraintSet constraintSet = mediaViewController.collapsedLayout;
        ConstraintSet constraintSet2 = mediaViewController.expandedLayout;
        boolean contains = SEMANTIC_ACTIONS_COMPACT.contains(Integer.valueOf(i));
        List list = SEMANTIC_ACTIONS_HIDE_WHEN_SCRUBBING;
        boolean z = false;
        boolean z2 = (mediaAction == null || ((mediaButton != null && list.stream().allMatch(new MediaControlPanel$$ExternalSyntheticLambda10(mediaButton))) && list.contains(Integer.valueOf(i)) && this.mIsScrubbing)) ? false : true;
        if ((i == R.id.actionPrev && mediaButton.reservePrev) || (i == R.id.actionNext && mediaButton.reserveNext)) {
            this.mMediaViewHolder.getAction(i).setFocusable(z2);
            this.mMediaViewHolder.getAction(i).setClickable(z2);
            i2 = 4;
        } else {
            i2 = 8;
        }
        setVisibleAndAlpha(constraintSet2, i, z2, i2);
        if (z2 && contains) {
            z = true;
        }
        setVisibleAndAlpha(constraintSet, i, z);
    }

    public final void setSmartspaceRecItemOnClickListener(final View view, final SmartspaceAction smartspaceAction, final int i) {
        if (view == null || smartspaceAction == null || smartspaceAction.getIntent() == null || smartspaceAction.getIntent().getExtras() == null) {
            Log.e("MediaControlPanel", "No tap action can be set up");
        } else {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda22
                /* JADX WARN: Code restructure failed: missing block: B:12:0x004c, code lost:
                
                    r0 = r7.getIntent().getExtras().getString("com.google.android.apps.gsa.smartspace.extra.SMARTSPACE_INTENT");
                 */
                /* JADX WARN: Removed duplicated region for block: B:17:0x007b  */
                /* JADX WARN: Removed duplicated region for block: B:21:0x008d  */
                @Override // android.view.View.OnClickListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void onClick(android.view.View r11) {
                    /*
                        r10 = this;
                        com.android.systemui.media.controls.ui.controller.MediaControlPanel r11 = com.android.systemui.media.controls.ui.controller.MediaControlPanel.this
                        int r6 = r2
                        android.app.smartspace.SmartspaceAction r7 = r3
                        android.view.View r10 = r4
                        com.android.systemui.plugins.FalsingManager r0 = r11.mFalsingManager
                        r8 = 1
                        boolean r0 = r0.isFalseTap(r8)
                        if (r0 == 0) goto L13
                        goto L9c
                    L13:
                        com.android.systemui.media.controls.util.MediaUiEventLogger r0 = r11.mLogger
                        r9 = 0
                        r1 = -1
                        if (r6 != r1) goto L25
                        java.lang.String r1 = r11.mPackageName
                        com.android.internal.logging.InstanceId r2 = r11.mInstanceId
                        com.android.internal.logging.UiEventLogger r0 = r0.logger
                        com.android.systemui.media.controls.util.MediaUiEvent r3 = com.android.systemui.media.controls.util.MediaUiEvent.MEDIA_RECOMMENDATION_CARD_TAP
                        r0.logWithInstanceId(r3, r9, r1, r2)
                        goto L32
                    L25:
                        java.lang.String r3 = r11.mPackageName
                        com.android.internal.logging.InstanceId r4 = r11.mInstanceId
                        com.android.internal.logging.UiEventLogger r0 = r0.logger
                        com.android.systemui.media.controls.util.MediaUiEvent r1 = com.android.systemui.media.controls.util.MediaUiEvent.MEDIA_RECOMMENDATION_ITEM_TAP
                        r2 = 0
                        r5 = r6
                        r0.logWithInstanceIdAndPosition(r1, r2, r3, r4, r5)
                    L32:
                        r0 = 760(0x2f8, float:1.065E-42)
                        int r1 = r11.mSmartspaceMediaItemsCount
                        r11.logSmartspaceCardReported(r0, r6, r1)
                        if (r7 == 0) goto L5c
                        android.content.Intent r0 = r7.getIntent()
                        if (r0 == 0) goto L5c
                        android.content.Intent r0 = r7.getIntent()
                        android.os.Bundle r0 = r0.getExtras()
                        if (r0 != 0) goto L4c
                        goto L5c
                    L4c:
                        android.content.Intent r0 = r7.getIntent()
                        android.os.Bundle r0 = r0.getExtras()
                        java.lang.String r1 = "com.google.android.apps.gsa.smartspace.extra.SMARTSPACE_INTENT"
                        java.lang.String r0 = r0.getString(r1)
                        if (r0 != 0) goto L5e
                    L5c:
                        r0 = r9
                        goto L79
                    L5e:
                        android.content.Intent r1 = android.content.Intent.parseUri(r0, r8)     // Catch: java.net.URISyntaxException -> L69
                        java.lang.String r2 = "KEY_OPEN_IN_FOREGROUND"
                        boolean r0 = r1.getBooleanExtra(r2, r9)     // Catch: java.net.URISyntaxException -> L69
                        goto L79
                    L69:
                        r1 = move-exception
                        java.lang.String r2 = "Failed to create intent from URI: "
                        java.lang.String r0 = r2.concat(r0)
                        java.lang.String r2 = "MediaControlPanel"
                        android.util.Log.wtf(r2, r0)
                        r1.printStackTrace()
                        goto L5c
                    L79:
                        if (r0 == 0) goto L8d
                        android.content.Intent r10 = r7.getIntent()
                        com.android.systemui.media.controls.ui.view.RecommendationViewHolder r0 = r11.mRecommendationViewHolder
                        com.android.systemui.util.animation.TransitionLayout r0 = r0.recommendations
                        com.android.systemui.animation.ActivityTransitionAnimator$Controller r0 = r11.buildLaunchAnimatorController(r0)
                        com.android.systemui.plugins.ActivityStarter r1 = r11.mActivityStarter
                        r1.postStartActivityDismissingKeyguard(r10, r9, r0)
                        goto L98
                    L8d:
                        android.content.Context r10 = r10.getContext()
                        android.content.Intent r0 = r7.getIntent()
                        r10.startActivity(r0)
                    L98:
                        com.android.systemui.media.controls.ui.controller.MediaCarouselController r10 = r11.mMediaCarouselController
                        r10.shouldScrollToKey = r8
                    L9c:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.ui.controller.MediaControlPanel$$ExternalSyntheticLambda22.onClick(android.view.View):void");
                }
            });
        }
    }

    public final void updateSeekBarVisibility() {
        ConstraintSet constraintSet = this.mMediaViewController.expandedLayout;
        constraintSet.setVisibility(R.id.media_progress_bar, this.mIsSeekBarEnabled ? 0 : 4);
        constraintSet.setAlpha(R.id.media_progress_bar, this.mIsSeekBarEnabled ? 1.0f : 0.0f);
    }

    public static void setVisibleAndAlpha(ConstraintSet constraintSet, int i, boolean z, int i2) {
        if (z) {
            i2 = 0;
        }
        constraintSet.setVisibility(i, i2);
        constraintSet.setAlpha(i, z ? 1.0f : 0.0f);
    }
}
