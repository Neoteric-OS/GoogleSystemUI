package com.android.systemui.media.controls.ui.controller;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Trace;
import android.util.MathUtils;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.app.animation.Interpolators;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.media.controls.ui.view.GutsViewHolder;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.media.controls.ui.view.MediaHostState;
import com.android.systemui.media.controls.ui.view.MediaViewHolder;
import com.android.systemui.media.controls.ui.view.RecommendationViewHolder;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.animation.MeasurementInput;
import com.android.systemui.util.animation.MeasurementOutput;
import com.android.systemui.util.animation.TransitionLayout;
import com.android.systemui.util.animation.TransitionLayoutController;
import com.android.systemui.util.animation.TransitionViewState;
import com.android.systemui.util.animation.WidgetState;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaViewController {
    public boolean animateNextStateChange;
    public long animationDelay;
    public long animationDuration;
    public ConstraintSet collapsedLayout;
    public MediaControlPanel$$ExternalSyntheticLambda5 configurationChangeListener;
    public final ConfigurationController configurationController;
    public final MediaViewController$configurationListener$1 configurationListener;
    public final Context context;
    public int currentEndLocation;
    public int currentHeight;
    public int currentStartLocation;
    public float currentTransitionProgress;
    public int currentWidth;
    public ConstraintSet expandedLayout;
    public boolean firstRefresh = true;
    public boolean isGutsVisible;
    public final TransitionLayoutController layoutController;
    public final Lambda locationChangeListener;
    public final MediaViewLogger logger;
    public final MeasurementOutput measurement;
    public final MediaHostStatesManager mediaHostStatesManager;
    public FunctionReferenceImpl sizeChangedListener;
    public final MediaViewController$stateCallback$1 stateCallback;
    public final CacheKey tmpKey;
    public final TransitionViewState tmpState;
    public final TransitionViewState tmpState2;
    public final TransitionViewState tmpState3;
    public TransitionLayout transitionLayout;
    public TYPE type;
    public final Map viewStates;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TYPE {
        public static final /* synthetic */ TYPE[] $VALUES;
        public static final TYPE PLAYER;
        public static final TYPE RECOMMENDATION;

        static {
            TYPE type = new TYPE("PLAYER", 0);
            PLAYER = type;
            TYPE type2 = new TYPE("RECOMMENDATION", 1);
            RECOMMENDATION = type2;
            TYPE[] typeArr = {type, type2};
            $VALUES = typeArr;
            EnumEntriesKt.enumEntries(typeArr);
        }

        public static TYPE valueOf(String str) {
            return (TYPE) Enum.valueOf(TYPE.class, str);
        }

        public static TYPE[] values() {
            return (TYPE[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.android.systemui.media.controls.ui.controller.MediaViewController$configurationListener$1, java.lang.Object] */
    public MediaViewController(Context context, ConfigurationController configurationController, MediaHostStatesManager mediaHostStatesManager, MediaViewLogger mediaViewLogger, SeekBarViewModel seekBarViewModel, DelayableExecutor delayableExecutor, GlobalSettings globalSettings) {
        this.context = context;
        this.configurationController = configurationController;
        this.mediaHostStatesManager = mediaHostStatesManager;
        this.logger = mediaViewLogger;
        final TransitionLayoutController transitionLayoutController = new TransitionLayoutController();
        transitionLayoutController.currentState = new TransitionViewState();
        transitionLayoutController.state = new TransitionViewState();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        transitionLayoutController.animator = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.util.animation.TransitionLayoutController$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                TransitionLayoutController transitionLayoutController2 = TransitionLayoutController.this;
                if (transitionLayoutController2.animationStartState == null || !transitionLayoutController2.animator.isRunning()) {
                    return;
                }
                TransitionViewState transitionViewState = transitionLayoutController2.animationStartState;
                Intrinsics.checkNotNull(transitionViewState);
                TransitionViewState interpolatedState = transitionLayoutController2.getInterpolatedState(transitionViewState, transitionLayoutController2.state, transitionLayoutController2.animator.getAnimatedFraction(), transitionLayoutController2.currentState);
                transitionLayoutController2.currentState = interpolatedState;
                transitionLayoutController2.applyStateToLayout(interpolatedState);
            }
        });
        ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        this.layoutController = transitionLayoutController;
        this.measurement = new MeasurementOutput();
        this.type = TYPE.PLAYER;
        this.viewStates = new LinkedHashMap();
        this.currentEndLocation = -1;
        this.currentStartLocation = -1;
        this.currentTransitionProgress = 1.0f;
        this.tmpState = new TransitionViewState();
        this.tmpState2 = new TransitionViewState();
        this.tmpState3 = new TransitionViewState();
        this.tmpKey = new CacheKey(-1, -1, 0.0f, false);
        ?? r4 = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.media.controls.ui.controller.MediaViewController$configurationListener$1
            public int lastOrientation = -1;

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                if (configuration != null) {
                    MediaViewController mediaViewController = MediaViewController.this;
                    TransitionLayout transitionLayout = mediaViewController.transitionLayout;
                    if (transitionLayout == null || transitionLayout.getRawLayoutDirection() != configuration.getLayoutDirection()) {
                        TransitionLayout transitionLayout2 = mediaViewController.transitionLayout;
                        if (transitionLayout2 != null) {
                            transitionLayout2.setLayoutDirection(configuration.getLayoutDirection());
                        }
                        mediaViewController.refreshState();
                    }
                    int i = configuration.orientation;
                    if (this.lastOrientation != i) {
                        this.lastOrientation = i;
                        mediaViewController.setBackgroundHeights(mediaViewController.context.getResources().getDimensionPixelSize(R.dimen.qs_media_session_height_expanded));
                    }
                    MediaControlPanel$$ExternalSyntheticLambda5 mediaControlPanel$$ExternalSyntheticLambda5 = mediaViewController.configurationChangeListener;
                    if (mediaControlPanel$$ExternalSyntheticLambda5 != null) {
                        mediaControlPanel$$ExternalSyntheticLambda5.invoke();
                        mediaViewController.refreshState();
                    }
                }
            }
        };
        this.configurationListener = r4;
        this.stateCallback = new MediaViewController$stateCallback$1(this);
        this.collapsedLayout = new ConstraintSet();
        this.expandedLayout = new ConstraintSet();
        mediaHostStatesManager.controllers.add(this);
        transitionLayoutController.sizeChangedListener = new Function2() { // from class: com.android.systemui.media.controls.ui.controller.MediaViewController.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v3, types: [kotlin.jvm.functions.Function0] */
            /* JADX WARN: Type inference failed for: r0v5 */
            /* JADX WARN: Type inference failed for: r0v6 */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                int intValue = ((Number) obj).intValue();
                int intValue2 = ((Number) obj2).intValue();
                MediaViewController mediaViewController = MediaViewController.this;
                mediaViewController.currentWidth = intValue;
                mediaViewController.currentHeight = intValue2;
                FunctionReferenceImpl functionReferenceImpl = mediaViewController.sizeChangedListener;
                ?? r0 = functionReferenceImpl;
                if (functionReferenceImpl == null) {
                    r0 = 0;
                }
                r0.invoke();
                return Unit.INSTANCE;
            }
        };
        ((ConfigurationControllerImpl) configurationController).addCallback(r4);
    }

    public static float calculateWidgetGroupAlphaForSquishiness(Set set, float f, TransitionViewState transitionViewState, float f2) {
        float f3 = transitionViewState.measureHeight;
        Set set2 = set;
        Iterator it = set2.iterator();
        float f4 = f3;
        float f5 = 0.0f;
        while (it.hasNext()) {
            WidgetState widgetState = (WidgetState) transitionViewState.widgetStates.get(Integer.valueOf(((Number) it.next()).intValue()));
            if (widgetState != null) {
                f4 = Float.min(f4, widgetState.y);
                f5 = Float.max(f5, widgetState.y + widgetState.height);
            }
        }
        if (f5 == f) {
            f5 = (float) (f - ((f5 - f4) * 0.2d));
        }
        Iterator it2 = set2.iterator();
        while (it2.hasNext()) {
            WidgetState widgetState2 = (WidgetState) transitionViewState.widgetStates.get(Integer.valueOf(((Number) it2.next()).intValue()));
            if (widgetState2 != null && widgetState2.alpha != 0.0f) {
                PathInterpolator pathInterpolator = MediaCarouselController.TRANSFORM_BEZIER;
                float f6 = f5 / f3;
                widgetState2.alpha = MediaCarouselController.TRANSFORM_BEZIER.getInterpolation(MathUtils.constrain((f2 - f6) / ((f / f3) - f6), 0.0f, 1.0f));
            }
        }
        return f4;
    }

    public static TransitionViewState squishViewState$frameworks__base__packages__SystemUI__android_common__SystemUI_core(TransitionViewState transitionViewState, float f) {
        TransitionViewState copy = transitionViewState.copy(null);
        int i = (int) (copy.measureHeight * f);
        copy.height = i;
        Iterator it = MediaViewHolder.backgroundIds.iterator();
        while (it.hasNext()) {
            WidgetState widgetState = (WidgetState) copy.widgetStates.get(Integer.valueOf(((Number) it.next()).intValue()));
            if (widgetState != null) {
                widgetState.height = i;
            }
        }
        calculateWidgetGroupAlphaForSquishiness(MediaViewHolder.expandedBottomActionIds, copy.measureHeight, copy, f);
        calculateWidgetGroupAlphaForSquishiness(MediaViewHolder.detailIds, copy.measureHeight, copy, f);
        calculateWidgetGroupAlphaForSquishiness(RecommendationViewHolder.mediaContainersIds, calculateWidgetGroupAlphaForSquishiness(RecommendationViewHolder.mediaTitlesAndSubtitlesIds, copy.measureHeight, copy, f), copy, f);
        return copy;
    }

    public final void attach(TransitionLayout transitionLayout, TYPE type) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("MediaViewController#attach");
        }
        try {
            loadLayoutForType(type);
            MediaViewLogger mediaViewLogger = this.logger;
            int i = this.currentStartLocation;
            int i2 = this.currentEndLocation;
            mediaViewLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            MediaViewLogger$logMediaLocation$2 mediaViewLogger$logMediaLocation$2 = MediaViewLogger$logMediaLocation$2.INSTANCE;
            LogBuffer logBuffer = mediaViewLogger.buffer;
            LogMessage obtain = logBuffer.obtain("MediaView", logLevel, mediaViewLogger$logMediaLocation$2, null);
            ((LogMessageImpl) obtain).str1 = "attach " + type;
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.int1 = i;
            logMessageImpl.int2 = i2;
            logBuffer.commit(obtain);
            this.transitionLayout = transitionLayout;
            this.layoutController.transitionLayout = transitionLayout;
            int i3 = this.currentEndLocation;
            if (i3 == -1) {
                if (isEnabled) {
                    return;
                } else {
                    return;
                }
            }
            setCurrentState(this.currentStartLocation, i3, this.currentTransitionProgress, true, false);
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    public AnimatorSet loadAnimator(Context context, int i, Interpolator interpolator, View... viewArr) {
        ArrayList arrayList = new ArrayList();
        for (View view : viewArr) {
            AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, i);
            animatorSet.getChildAnimations().get(0).setInterpolator(interpolator);
            animatorSet.setTarget(view);
            arrayList.add(animatorSet);
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(arrayList);
        return animatorSet2;
    }

    public final void loadLayoutForType(TYPE type) {
        this.type = type;
        int ordinal = type.ordinal();
        if (ordinal == 0) {
            this.collapsedLayout.load(R.xml.media_session_collapsed, this.context);
            this.expandedLayout.load(R.xml.media_session_expanded, this.context);
        } else if (ordinal == 1) {
            this.collapsedLayout.load(R.xml.media_recommendations_collapsed, this.context);
            this.expandedLayout.load(R.xml.media_recommendations_expanded, this.context);
        }
        refreshState();
    }

    public final TransitionViewState obtainViewState(MediaHostState mediaHostState, boolean z) {
        TransitionViewState transitionViewState;
        Set set;
        if (mediaHostState == null || mediaHostState.getMeasurementInput() == null) {
            return null;
        }
        boolean z2 = this.isGutsVisible;
        MeasurementInput measurementInput = mediaHostState.getMeasurementInput();
        int i = measurementInput != null ? measurementInput.heightMeasureSpec : 0;
        CacheKey cacheKey = this.tmpKey;
        cacheKey.heightMeasureSpec = i;
        MeasurementInput measurementInput2 = mediaHostState.getMeasurementInput();
        cacheKey.widthMeasureSpec = measurementInput2 != null ? measurementInput2.widthMeasureSpec : 0;
        cacheKey.expansion = mediaHostState.getExpansion();
        cacheKey.gutsVisible = z2;
        TransitionViewState transitionViewState2 = (TransitionViewState) this.viewStates.get(cacheKey);
        if (transitionViewState2 != null) {
            return (mediaHostState.getSquishFraction() > 1.0f || z) ? transitionViewState2 : squishViewState$frameworks__base__packages__SystemUI__android_common__SystemUI_core(transitionViewState2, mediaHostState.getSquishFraction());
        }
        CacheKey cacheKey2 = new CacheKey(cacheKey.widthMeasureSpec, cacheKey.heightMeasureSpec, cacheKey.expansion, cacheKey.gutsVisible);
        if (this.transitionLayout == null) {
            return null;
        }
        if (mediaHostState.getExpansion() == 0.0f || mediaHostState.getExpansion() == 1.0f) {
            if (mediaHostState.getExpansion() == 1.0f) {
                setBackgroundHeights(mediaHostState.getExpandedMatchesParentHeight() ? 0 : this.context.getResources().getDimensionPixelSize(R.dimen.qs_media_session_height_expanded));
            }
            TransitionLayout transitionLayout = this.transitionLayout;
            Intrinsics.checkNotNull(transitionLayout);
            MeasurementInput measurementInput3 = mediaHostState.getMeasurementInput();
            Intrinsics.checkNotNull(measurementInput3);
            ConstraintSet constraintSet = mediaHostState.getExpansion() > 0.0f ? this.expandedLayout : this.collapsedLayout;
            transitionViewState = new TransitionViewState();
            transitionLayout.calculateViewState(measurementInput3, constraintSet, transitionViewState);
            int ordinal = this.type.ordinal();
            if (ordinal == 0) {
                set = MediaViewHolder.controlsIds;
            } else {
                if (ordinal != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                set = RecommendationViewHolder.controlsIds;
            }
            Set set2 = GutsViewHolder.ids;
            Iterator it = set.iterator();
            while (it.hasNext()) {
                WidgetState widgetState = (WidgetState) transitionViewState.widgetStates.get(Integer.valueOf(((Number) it.next()).intValue()));
                if (widgetState != null) {
                    boolean z3 = this.isGutsVisible;
                    widgetState.alpha = z3 ? 0.0f : widgetState.alpha;
                    widgetState.gone = z3 ? true : widgetState.gone;
                }
            }
            Iterator it2 = set2.iterator();
            while (it2.hasNext()) {
                WidgetState widgetState2 = (WidgetState) transitionViewState.widgetStates.get(Integer.valueOf(((Number) it2.next()).intValue()));
                if (widgetState2 != null) {
                    boolean z4 = this.isGutsVisible;
                    widgetState2.alpha = z4 ? widgetState2.alpha : 0.0f;
                    widgetState2.gone = z4 ? widgetState2.gone : true;
                }
            }
            this.viewStates.put(cacheKey2, transitionViewState);
        } else {
            MediaHost.MediaHostStateHolder copy = mediaHostState.copy();
            copy.setExpansion(0.0f);
            TransitionViewState obtainViewState = obtainViewState(copy, z);
            MediaHost.MediaHostStateHolder copy2 = mediaHostState.copy();
            copy2.setExpansion(1.0f);
            transitionViewState = this.layoutController.getInterpolatedState(obtainViewState, obtainViewState(copy2, z), mediaHostState.getExpansion(), null);
        }
        return (mediaHostState.getSquishFraction() > 1.0f || z) ? transitionViewState : squishViewState$frameworks__base__packages__SystemUI__android_common__SystemUI_core(transitionViewState, mediaHostState.getSquishFraction());
    }

    public final void refreshState() {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("MediaViewController#refreshState");
        }
        try {
            this.viewStates.clear();
            if (this.firstRefresh) {
                Iterator it = this.mediaHostStatesManager.mediaHostStates.entrySet().iterator();
                while (it.hasNext()) {
                    obtainViewState((MediaHostState) ((Map.Entry) it.next()).getValue(), false);
                }
                this.firstRefresh = false;
            }
            setCurrentState(this.currentStartLocation, this.currentEndLocation, this.currentTransitionProgress, true, false);
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        } catch (Throwable th) {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    public final void setBackgroundHeights(int i) {
        Set singleton;
        if (this.type == TYPE.PLAYER) {
            Set set = MediaViewHolder.controlsIds;
            singleton = MediaViewHolder.backgroundIds;
        } else {
            Set set2 = RecommendationViewHolder.controlsIds;
            singleton = Collections.singleton(Integer.valueOf(RecommendationViewHolder.backgroundId));
        }
        Iterator it = singleton.iterator();
        while (it.hasNext()) {
            this.expandedLayout.getConstraint(((Number) it.next()).intValue()).layout.mHeight = i;
        }
    }

    public final void setCollapsedLayout(ConstraintSet constraintSet) {
        this.collapsedLayout = constraintSet;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setCurrentState(int r17, int r18, float r19, boolean r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.media.controls.ui.controller.MediaViewController.setCurrentState(int, int, float, boolean, boolean):void");
    }

    public final void setExpandedLayout(ConstraintSet constraintSet) {
        this.expandedLayout = constraintSet;
    }

    public final TransitionViewState updateViewStateSize(TransitionViewState transitionViewState, int i, TransitionViewState transitionViewState2) {
        int i2;
        int i3;
        if (transitionViewState == null) {
            return null;
        }
        TransitionViewState copy = transitionViewState.copy(transitionViewState2);
        MediaHostStatesManager mediaHostStatesManager = this.mediaHostStatesManager;
        MediaHostState mediaHostState = (MediaHostState) mediaHostStatesManager.mediaHostStates.get(Integer.valueOf(i));
        MeasurementOutput measurementOutput = (MeasurementOutput) mediaHostStatesManager.carouselSizes.get(Integer.valueOf(i));
        if (measurementOutput != null && ((i2 = copy.measureHeight) != (i3 = measurementOutput.measuredHeight) || copy.measureWidth != measurementOutput.measuredWidth)) {
            copy.measureHeight = Math.max(i3, i2);
            int max = Math.max(measurementOutput.measuredWidth, copy.measureWidth);
            copy.measureWidth = max;
            copy.height = copy.measureHeight;
            copy.width = max;
            Iterator it = MediaViewHolder.backgroundIds.iterator();
            while (it.hasNext()) {
                WidgetState widgetState = (WidgetState) copy.widgetStates.get(Integer.valueOf(((Number) it.next()).intValue()));
                if (widgetState != null) {
                    widgetState.height = copy.height;
                    widgetState.width = copy.width;
                }
            }
            if (mediaHostState != null && mediaHostState.getSquishFraction() <= 1.0f) {
                copy = squishViewState$frameworks__base__packages__SystemUI__android_common__SystemUI_core(copy, mediaHostState.getSquishFraction());
            }
        }
        this.logger.logMediaSize("update to carousel", copy.width, copy.height);
        return copy;
    }

    private static /* synthetic */ void getTransitionLayout$annotations() {
    }
}
