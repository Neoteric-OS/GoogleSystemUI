package com.android.systemui.media.taptotransfer.receiver;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.MediaRoute2Info;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.logging.InstanceId;
import com.android.internal.widget.CachingIconView;
import com.android.settingslib.Utils;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.TintedIcon;
import com.android.systemui.common.ui.binder.IconViewBinder;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.media.taptotransfer.MediaTttFlags;
import com.android.systemui.media.taptotransfer.common.IconInfo;
import com.android.systemui.media.taptotransfer.common.MediaTttIcon;
import com.android.systemui.media.taptotransfer.common.MediaTttLoggerUtils;
import com.android.systemui.media.taptotransfer.common.MediaTttUtils$Companion;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import com.android.systemui.temporarydisplay.TemporaryViewDisplayController;
import com.android.systemui.temporarydisplay.TemporaryViewDisplayController$removeViewFromWindow$1;
import com.android.systemui.temporarydisplay.TemporaryViewInfo;
import com.android.systemui.temporarydisplay.TemporaryViewUiEventLogger;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.view.ViewUtil;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.wm.shell.R;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaTttChipControllerReceiver extends TemporaryViewDisplayController {
    public static final long ICON_ALPHA_ANIM_DURATION = MathKt.roundToLong((5 * 1000.0f) / 60.0f);
    public final ValueAnimator bounceAnimator;
    public final CommandQueue commandQueue;
    public final MediaTttChipControllerReceiver$commandQueueCallbacks$1 commandQueueCallbacks;
    public final MediaTttChipControllerReceiver$displayListener$1 displayListener;
    public final Map instanceMap;
    public final Handler mainHandler;
    public final MediaTttFlags mediaTttFlags;
    public final MediaTttReceiverRippleController rippleController;
    public final TemporaryViewUiEventLogger temporaryViewUiEventLogger;
    public final MediaTttReceiverUiEventLogger uiEventLogger;
    public final ViewUtil viewUtil;
    public final WindowManager.LayoutParams windowLayoutParams;

    /* JADX WARN: Type inference failed for: r0v11, types: [com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver$commandQueueCallbacks$1] */
    /* JADX WARN: Type inference failed for: r0v13, types: [com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver$displayListener$1] */
    public MediaTttChipControllerReceiver(CommandQueue commandQueue, Context context, MediaTttReceiverLogger mediaTttReceiverLogger, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, DelayableExecutor delayableExecutor, AccessibilityManager accessibilityManager, ConfigurationController configurationController, DumpManager dumpManager, PowerManager powerManager, Handler handler, MediaTttFlags mediaTttFlags, MediaTttReceiverUiEventLogger mediaTttReceiverUiEventLogger, ViewUtil viewUtil, WakeLock.Builder builder, SystemClock systemClock, MediaTttReceiverRippleController mediaTttReceiverRippleController, TemporaryViewUiEventLogger temporaryViewUiEventLogger) {
        super(context, mediaTttReceiverLogger, viewCaptureAwareWindowManager, delayableExecutor, accessibilityManager, configurationController, dumpManager, powerManager, R.layout.media_ttt_chip_receiver, builder, systemClock, temporaryViewUiEventLogger);
        this.commandQueue = commandQueue;
        this.mainHandler = handler;
        this.mediaTttFlags = mediaTttFlags;
        this.uiEventLogger = mediaTttReceiverUiEventLogger;
        this.viewUtil = viewUtil;
        this.rippleController = mediaTttReceiverRippleController;
        this.temporaryViewUiEventLogger = temporaryViewUiEventLogger;
        WindowManager.LayoutParams layoutParams = this.commonWindowLayoutParams;
        layoutParams.gravity = 81;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.setFitInsetsTypes(0);
        this.windowLayoutParams = layoutParams;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(2);
        ofFloat.setDuration(750L);
        this.bounceAnimator = ofFloat;
        this.commandQueueCallbacks = new CommandQueue.Callbacks() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver$commandQueueCallbacks$1
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void updateMediaTapToTransferReceiverDisplay(int i, final MediaRoute2Info mediaRoute2Info, Icon icon, final CharSequence charSequence) {
                String str;
                int i2;
                final MediaTttChipControllerReceiver mediaTttChipControllerReceiver = MediaTttChipControllerReceiver.this;
                mediaTttChipControllerReceiver.getClass();
                ChipStateReceiver.Companion.getClass();
                try {
                } catch (NoSuchElementException e) {
                    Log.e("ChipStateReceiver", "Could not find requested state " + i, e);
                    r3 = null;
                }
                for (ChipStateReceiver chipStateReceiver : ChipStateReceiver.values()) {
                    if (chipStateReceiver.getStateInt() == i) {
                        if (chipStateReceiver == null || (str = chipStateReceiver.name()) == null) {
                            str = "Invalid";
                        }
                        MediaTttReceiverLogger mediaTttReceiverLogger2 = (MediaTttReceiverLogger) mediaTttChipControllerReceiver.logger;
                        MediaTttLoggerUtils.logStateChange(mediaTttReceiverLogger2.buffer, "MediaTttReceiver", str, mediaRoute2Info.getId(), mediaRoute2Info.getClientPackageName());
                        if (chipStateReceiver == null) {
                            MediaTttLoggerUtils.logStateChangeError(mediaTttReceiverLogger2.buffer, "MediaTttReceiver", i);
                            return;
                        }
                        InstanceId instanceId = (InstanceId) mediaTttChipControllerReceiver.instanceMap.get(mediaRoute2Info.getId());
                        if (instanceId == null) {
                            instanceId = mediaTttChipControllerReceiver.temporaryViewUiEventLogger.instanceIdSequence.newInstanceId();
                        }
                        final InstanceId instanceId2 = instanceId;
                        mediaTttChipControllerReceiver.uiEventLogger.logger.log(chipStateReceiver.getUiEvent(), instanceId2);
                        if (chipStateReceiver != ChipStateReceiver.CLOSE_TO_SENDER) {
                            mediaTttChipControllerReceiver.removeView(mediaRoute2Info.getId(), chipStateReceiver.name());
                            return;
                        }
                        mediaTttChipControllerReceiver.instanceMap.put(mediaRoute2Info.getId(), instanceId2);
                        if (icon == null) {
                            mediaTttChipControllerReceiver.displayView(new ChipReceiverInfo(mediaRoute2Info, null, charSequence, mediaRoute2Info.getId(), instanceId2));
                            return;
                        } else {
                            icon.loadDrawableAsync(mediaTttChipControllerReceiver.context, new Icon.OnDrawableLoadedListener() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver$updateMediaTapToTransferReceiverDisplay$1
                                @Override // android.graphics.drawable.Icon.OnDrawableLoadedListener
                                public final void onDrawableLoaded(Drawable drawable) {
                                    MediaTttChipControllerReceiver mediaTttChipControllerReceiver2 = MediaTttChipControllerReceiver.this;
                                    MediaRoute2Info mediaRoute2Info2 = mediaRoute2Info;
                                    mediaTttChipControllerReceiver2.displayView(new ChipReceiverInfo(mediaRoute2Info2, drawable, charSequence, mediaRoute2Info2.getId(), instanceId2));
                                }
                            }, mediaTttChipControllerReceiver.mainHandler);
                            return;
                        }
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        };
        this.instanceMap = new LinkedHashMap();
        this.displayListener = new TemporaryViewDisplayController.Listener() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver$displayListener$1
            @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController.Listener
            public final void onInfoPermanentlyRemoved(String str, String str2) {
                MediaTttChipControllerReceiver.this.instanceMap.remove(str);
            }
        };
    }

    public static void animateViewTranslationAndFade$default(MediaTttChipControllerReceiver mediaTttChipControllerReceiver, ViewGroup viewGroup, float f, float f2, TimeInterpolator timeInterpolator, final MediaTttChipControllerReceiver$animateViewIn$1 mediaTttChipControllerReceiver$animateViewIn$1, int i) {
        if ((i & 8) != 0) {
            timeInterpolator = null;
        }
        long j = (i & 16) != 0 ? 500L : 167L;
        long j2 = (i & 32) != 0 ? ICON_ALPHA_ANIM_DURATION : 167L;
        if ((i & 64) != 0) {
            mediaTttChipControllerReceiver$animateViewIn$1 = null;
        }
        mediaTttChipControllerReceiver.getClass();
        viewGroup.animate().translationYBy(f).setInterpolator(timeInterpolator).setDuration(j).withEndAction(new Runnable() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver$animateViewTranslationAndFade$1
            @Override // java.lang.Runnable
            public final void run() {
                Runnable runnable = mediaTttChipControllerReceiver$animateViewIn$1;
                if (runnable != null) {
                    runnable.run();
                }
            }
        }).start();
        viewGroup.animate().alpha(f2).setDuration(j2).start();
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver$animateViewIn$1] */
    @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController
    public final void animateViewIn$frameworks__base__packages__SystemUI__android_common__SystemUI_core(ViewGroup viewGroup) {
        final ViewGroup viewGroup2 = (ViewGroup) viewGroup.requireViewById(R.id.icon_container_view);
        ReceiverChipRippleView receiverChipRippleView = (ReceiverChipRippleView) viewGroup.requireViewById(R.id.icon_glow_ripple);
        ReceiverChipRippleView receiverChipRippleView2 = (ReceiverChipRippleView) viewGroup.requireViewById(R.id.ripple);
        final MediaTttReceiverRippleController mediaTttReceiverRippleController = this.rippleController;
        final float dimensionPixelSize = mediaTttReceiverRippleController.context.getResources().getDimensionPixelSize(R.dimen.media_ttt_icon_size_receiver) * 2.0f;
        mediaTttReceiverRippleController.getClass();
        if (!receiverChipRippleView2.animator.isRunning()) {
            final boolean z = false;
            receiverChipRippleView2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttReceiverRippleController$expandRipple$1
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    if (view == null) {
                        return;
                    }
                    ReceiverChipRippleView receiverChipRippleView3 = (ReceiverChipRippleView) view;
                    if (z) {
                        MediaTttReceiverRippleController.access$layoutIconRipple(mediaTttReceiverRippleController, receiverChipRippleView3);
                    } else {
                        mediaTttReceiverRippleController.layoutRipple(receiverChipRippleView3, false);
                    }
                    receiverChipRippleView3.invalidate();
                }
            });
            receiverChipRippleView2.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttReceiverRippleController$expandRipple$2
                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewAttachedToWindow(View view) {
                    ReceiverChipRippleView receiverChipRippleView3 = (ReceiverChipRippleView) view;
                    if (z) {
                        MediaTttReceiverRippleController.access$layoutIconRipple(mediaTttReceiverRippleController, receiverChipRippleView3);
                    } else {
                        mediaTttReceiverRippleController.layoutRipple(receiverChipRippleView3, false);
                    }
                    int i = ReceiverChipRippleView.$r8$clinit;
                    receiverChipRippleView3.duration = 333L;
                    receiverChipRippleView3.isStarted = true;
                    receiverChipRippleView3.startRipple(null);
                    receiverChipRippleView3.removeOnAttachStateChangeListener(this);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewDetachedFromWindow(View view) {
                }
            });
        }
        if (!receiverChipRippleView.animator.isRunning()) {
            final boolean z2 = true;
            receiverChipRippleView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttReceiverRippleController$expandRipple$1
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    if (view == null) {
                        return;
                    }
                    ReceiverChipRippleView receiverChipRippleView3 = (ReceiverChipRippleView) view;
                    if (z2) {
                        MediaTttReceiverRippleController.access$layoutIconRipple(mediaTttReceiverRippleController, receiverChipRippleView3);
                    } else {
                        mediaTttReceiverRippleController.layoutRipple(receiverChipRippleView3, false);
                    }
                    receiverChipRippleView3.invalidate();
                }
            });
            receiverChipRippleView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttReceiverRippleController$expandRipple$2
                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewAttachedToWindow(View view) {
                    ReceiverChipRippleView receiverChipRippleView3 = (ReceiverChipRippleView) view;
                    if (z2) {
                        MediaTttReceiverRippleController.access$layoutIconRipple(mediaTttReceiverRippleController, receiverChipRippleView3);
                    } else {
                        mediaTttReceiverRippleController.layoutRipple(receiverChipRippleView3, false);
                    }
                    int i = ReceiverChipRippleView.$r8$clinit;
                    receiverChipRippleView3.duration = 333L;
                    receiverChipRippleView3.isStarted = true;
                    receiverChipRippleView3.startRipple(null);
                    receiverChipRippleView3.removeOnAttachStateChangeListener(this);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewDetachedFromWindow(View view) {
                }
            });
        }
        viewGroup2.setTranslationY(mediaTttReceiverRippleController.context.getResources().getDimensionPixelSize(R.dimen.media_ttt_icon_size_receiver));
        animateViewTranslationAndFade$default(this, viewGroup2, (-1) * dimensionPixelSize, 1.0f, Interpolators.EMPHASIZED_DECELERATE, new Runnable() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver$animateViewIn$1
            @Override // java.lang.Runnable
            public final void run() {
                MediaTttChipControllerReceiver mediaTttChipControllerReceiver = MediaTttChipControllerReceiver.this;
                final ViewGroup viewGroup3 = viewGroup2;
                final float f = dimensionPixelSize * 0.15f;
                if (mediaTttChipControllerReceiver.bounceAnimator.isStarted()) {
                    return;
                }
                final float translationY = viewGroup3.getTranslationY();
                mediaTttChipControllerReceiver.bounceAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver$addViewToBounceAnimation$1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        viewGroup3.setTranslationY((f * floatValue) + translationY);
                    }
                });
                viewGroup3.setAccessibilityLiveRegion(0);
                mediaTttChipControllerReceiver.bounceAnimator.start();
            }
        }, 48);
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController
    public final void animateViewOut$frameworks__base__packages__SystemUI__android_common__SystemUI_core(ViewGroup viewGroup, String str, final TemporaryViewDisplayController$removeViewFromWindow$1 temporaryViewDisplayController$removeViewFromWindow$1) {
        final int i = 1;
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.requireViewById(R.id.icon_container_view);
        final ReceiverChipRippleView receiverChipRippleView = (ReceiverChipRippleView) viewGroup.requireViewById(R.id.ripple);
        MediaTttReceiverRippleController mediaTttReceiverRippleController = this.rippleController;
        float dimensionPixelSize = 2.0f * mediaTttReceiverRippleController.context.getResources().getDimensionPixelSize(R.dimen.media_ttt_icon_size_receiver);
        this.bounceAnimator.removeAllUpdateListeners();
        this.bounceAnimator.cancel();
        if (!Intrinsics.areEqual(str, "TRANSFER_TO_RECEIVER_SUCCEEDED")) {
            mediaTttReceiverRippleController.getClass();
            if (receiverChipRippleView.isStarted) {
                receiverChipRippleView.duration = 333L;
                receiverChipRippleView.animator.removeAllListeners();
                ValueAnimator valueAnimator = receiverChipRippleView.animator;
                final MediaTttReceiverLogger mediaTttReceiverLogger = mediaTttReceiverRippleController.mediaTttReceiverLogger;
                valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.media.taptotransfer.receiver.ReceiverChipRippleView$expandToFull$2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        switch (i) {
                            case 0:
                                ReceiverChipRippleView receiverChipRippleView2 = receiverChipRippleView;
                                MediaTttReceiverLogger mediaTttReceiverLogger2 = mediaTttReceiverLogger;
                                receiverChipRippleView2.setVisibility(8);
                                int id = receiverChipRippleView2.getId();
                                mediaTttReceiverLogger2.getClass();
                                LogLevel logLevel = LogLevel.DEBUG;
                                MediaTttReceiverLogger$logRippleAnimationEnd$2 mediaTttReceiverLogger$logRippleAnimationEnd$2 = new Function1() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttReceiverLogger$logRippleAnimationEnd$2
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return GenericDocument$$ExternalSyntheticOutline0.m("ripple animation for view with id: ", " is ended", ((LogMessage) obj).getInt1());
                                    }
                                };
                                String str2 = mediaTttReceiverLogger2.tag;
                                LogBuffer logBuffer = mediaTttReceiverLogger2.buffer;
                                LogMessage obtain = logBuffer.obtain(str2, logLevel, mediaTttReceiverLogger$logRippleAnimationEnd$2, null);
                                ((LogMessageImpl) obtain).int1 = id;
                                logBuffer.commit(obtain);
                                temporaryViewDisplayController$removeViewFromWindow$1.run();
                                receiverChipRippleView.isStarted = false;
                                break;
                            default:
                                ReceiverChipRippleView receiverChipRippleView3 = receiverChipRippleView;
                                MediaTttReceiverLogger mediaTttReceiverLogger3 = mediaTttReceiverLogger;
                                receiverChipRippleView3.setVisibility(8);
                                int id2 = receiverChipRippleView3.getId();
                                mediaTttReceiverLogger3.getClass();
                                LogLevel logLevel2 = LogLevel.DEBUG;
                                MediaTttReceiverLogger$logRippleAnimationEnd$2 mediaTttReceiverLogger$logRippleAnimationEnd$22 = new Function1() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttReceiverLogger$logRippleAnimationEnd$2
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return GenericDocument$$ExternalSyntheticOutline0.m("ripple animation for view with id: ", " is ended", ((LogMessage) obj).getInt1());
                                    }
                                };
                                String str3 = mediaTttReceiverLogger3.tag;
                                LogBuffer logBuffer2 = mediaTttReceiverLogger3.buffer;
                                LogMessage obtain2 = logBuffer2.obtain(str3, logLevel2, mediaTttReceiverLogger$logRippleAnimationEnd$22, null);
                                ((LogMessageImpl) obtain2).int1 = id2;
                                logBuffer2.commit(obtain2);
                                temporaryViewDisplayController$removeViewFromWindow$1.run();
                                receiverChipRippleView.isStarted = false;
                                break;
                        }
                    }
                });
                receiverChipRippleView.animator.reverse();
            }
            animateViewTranslationAndFade$default(this, viewGroup2, dimensionPixelSize, 0.0f, null, null, 120);
            return;
        }
        mediaTttReceiverRippleController.layoutRipple(receiverChipRippleView, true);
        float f = mediaTttReceiverRippleController.maxRippleHeight;
        if (receiverChipRippleView.isStarted) {
            receiverChipRippleView.animator.removeAllListeners();
            receiverChipRippleView.animator.removeAllUpdateListeners();
            RippleShader rippleShader = receiverChipRippleView.rippleShader;
            RippleShader rippleShader2 = rippleShader != null ? rippleShader : null;
            RippleShader.FadeParams fadeParams = rippleShader2.baseRingFadeParams;
            fadeParams.fadeOutStart = 0.3f;
            fadeParams.fadeOutEnd = 1.0f;
            RippleShader.FadeParams fadeParams2 = rippleShader2.centerFillFadeParams;
            fadeParams2.getClass();
            fadeParams2.fadeInEnd = 0.0f;
            fadeParams2.fadeOutStart = fadeParams.fadeInEnd;
            fadeParams2.fadeOutEnd = 1.0f;
            if (rippleShader == null) {
                rippleShader = null;
            }
            float f2 = rippleShader.rippleSize.currentHeight / f;
            final float pow = 1 - ((float) Math.pow(r12 - f2, 0.3333333333333333d));
            receiverChipRippleView.animator.setDuration(1000L);
            receiverChipRippleView.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.media.taptotransfer.receiver.ReceiverChipRippleView$expandToFull$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    long currentPlayTime = valueAnimator2.getCurrentPlayTime();
                    float floatValue = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                    ReceiverChipRippleView receiverChipRippleView2 = ReceiverChipRippleView.this;
                    int i2 = ReceiverChipRippleView.$r8$clinit;
                    RippleShader rippleShader3 = receiverChipRippleView2.rippleShader;
                    if (rippleShader3 == null) {
                        rippleShader3 = null;
                    }
                    float f3 = pow;
                    float f4 = 1;
                    rippleShader3.setRawProgress(((f4 - f3) * floatValue) + f3);
                    RippleShader rippleShader4 = ReceiverChipRippleView.this.rippleShader;
                    RippleShader rippleShader5 = rippleShader4 != null ? rippleShader4 : null;
                    if (rippleShader4 == null) {
                        rippleShader4 = null;
                    }
                    rippleShader5.setDistortionStrength(f4 - rippleShader4.rawProgress);
                    RippleShader rippleShader6 = ReceiverChipRippleView.this.rippleShader;
                    RippleShader rippleShader7 = rippleShader6 != null ? rippleShader6 : null;
                    if (rippleShader6 == null) {
                        rippleShader6 = null;
                    }
                    rippleShader7.setPixelDensity(f4 - rippleShader6.rawProgress);
                    RippleShader rippleShader8 = ReceiverChipRippleView.this.rippleShader;
                    (rippleShader8 != null ? rippleShader8 : null).setFloatUniform("in_time", currentPlayTime);
                    ReceiverChipRippleView.this.invalidate();
                }
            });
            ValueAnimator valueAnimator2 = receiverChipRippleView.animator;
            final MediaTttReceiverLogger mediaTttReceiverLogger2 = mediaTttReceiverRippleController.mediaTttReceiverLogger;
            final int i2 = 0;
            valueAnimator2.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.media.taptotransfer.receiver.ReceiverChipRippleView$expandToFull$2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    switch (i2) {
                        case 0:
                            ReceiverChipRippleView receiverChipRippleView2 = receiverChipRippleView;
                            MediaTttReceiverLogger mediaTttReceiverLogger22 = mediaTttReceiverLogger2;
                            receiverChipRippleView2.setVisibility(8);
                            int id = receiverChipRippleView2.getId();
                            mediaTttReceiverLogger22.getClass();
                            LogLevel logLevel = LogLevel.DEBUG;
                            MediaTttReceiverLogger$logRippleAnimationEnd$2 mediaTttReceiverLogger$logRippleAnimationEnd$2 = new Function1() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttReceiverLogger$logRippleAnimationEnd$2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return GenericDocument$$ExternalSyntheticOutline0.m("ripple animation for view with id: ", " is ended", ((LogMessage) obj).getInt1());
                                }
                            };
                            String str2 = mediaTttReceiverLogger22.tag;
                            LogBuffer logBuffer = mediaTttReceiverLogger22.buffer;
                            LogMessage obtain = logBuffer.obtain(str2, logLevel, mediaTttReceiverLogger$logRippleAnimationEnd$2, null);
                            ((LogMessageImpl) obtain).int1 = id;
                            logBuffer.commit(obtain);
                            temporaryViewDisplayController$removeViewFromWindow$1.run();
                            receiverChipRippleView.isStarted = false;
                            break;
                        default:
                            ReceiverChipRippleView receiverChipRippleView3 = receiverChipRippleView;
                            MediaTttReceiverLogger mediaTttReceiverLogger3 = mediaTttReceiverLogger2;
                            receiverChipRippleView3.setVisibility(8);
                            int id2 = receiverChipRippleView3.getId();
                            mediaTttReceiverLogger3.getClass();
                            LogLevel logLevel2 = LogLevel.DEBUG;
                            MediaTttReceiverLogger$logRippleAnimationEnd$2 mediaTttReceiverLogger$logRippleAnimationEnd$22 = new Function1() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttReceiverLogger$logRippleAnimationEnd$2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return GenericDocument$$ExternalSyntheticOutline0.m("ripple animation for view with id: ", " is ended", ((LogMessage) obj).getInt1());
                                }
                            };
                            String str3 = mediaTttReceiverLogger3.tag;
                            LogBuffer logBuffer2 = mediaTttReceiverLogger3.buffer;
                            LogMessage obtain2 = logBuffer2.obtain(str3, logLevel2, mediaTttReceiverLogger$logRippleAnimationEnd$22, null);
                            ((LogMessageImpl) obtain2).int1 = id2;
                            logBuffer2.commit(obtain2);
                            temporaryViewDisplayController$removeViewFromWindow$1.run();
                            receiverChipRippleView.isStarted = false;
                            break;
                    }
                }
            });
            receiverChipRippleView.animator.start();
        }
        animateViewTranslationAndFade$default(this, viewGroup2, dimensionPixelSize * (-1), 0.0f, null, null, 72);
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController
    public final void getTouchableRegion(Rect rect, View view) {
        CachingIconView requireViewById = view.requireViewById(R.id.app_icon);
        this.viewUtil.getClass();
        int[] iArr = new int[2];
        requireViewById.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        rect.set(i, i2, requireViewById.getWidth() + i, requireViewById.getHeight() + i2);
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController
    public final WindowManager.LayoutParams getWindowLayoutParams$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        return this.windowLayoutParams;
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController, com.android.systemui.CoreStartable
    public final void start() {
        super.start();
        MediaTttFlags mediaTttFlags = this.mediaTttFlags;
        mediaTttFlags.getClass();
        if (((FeatureFlagsClassicRelease) mediaTttFlags.featureFlags).isEnabled(Flags.MEDIA_TAP_TO_TRANSFER)) {
            this.commandQueue.addCallback((CommandQueue.Callbacks) this.commandQueueCallbacks);
        }
        this.listeners.add(this.displayListener);
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController
    public final void updateView(TemporaryViewInfo temporaryViewInfo, ViewGroup viewGroup) {
        ChipReceiverInfo chipReceiverInfo = (ChipReceiverInfo) temporaryViewInfo;
        final String clientPackageName = chipReceiverInfo.routeInfo.getClientPackageName();
        IconInfo iconInfoFromPackageName = MediaTttUtils$Companion.getIconInfoFromPackageName(this.context, clientPackageName, true, new Function0() { // from class: com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver$updateView$iconInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String str = clientPackageName;
                if (str != null) {
                    MediaTttLoggerUtils.logPackageNotFound(((MediaTttReceiverLogger) this.logger).buffer, "MediaTttReceiver", str);
                }
                return Unit.INSTANCE;
            }
        });
        CharSequence charSequence = chipReceiverInfo.appNameOverride;
        if (charSequence != null) {
            iconInfoFromPackageName = IconInfo.copy$default(iconInfoFromPackageName, new ContentDescription.Loaded(charSequence.toString()), null, 14);
        }
        Drawable drawable = chipReceiverInfo.appIconDrawableOverride;
        if (drawable != null) {
            iconInfoFromPackageName = IconInfo.copy$default(iconInfoFromPackageName, null, new MediaTttIcon.Loaded(drawable), 5);
        }
        int dimensionPixelSize = iconInfoFromPackageName.isAppIcon ? 0 : this.context.getResources().getDimensionPixelSize(R.dimen.media_ttt_generic_icon_padding);
        CachingIconView requireViewById = viewGroup.requireViewById(R.id.app_icon);
        requireViewById.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        TintedIcon tintedIcon = iconInfoFromPackageName.toTintedIcon();
        IconViewBinder.bind(tintedIcon.icon, requireViewById);
        Integer num = tintedIcon.tint;
        requireViewById.setImageTintList(num != null ? Utils.getColorAttr(num.intValue(), requireViewById.getContext()) : null);
        ((ViewGroup) viewGroup.requireViewById(R.id.icon_container_view)).setAccessibilityLiveRegion(2);
    }
}
