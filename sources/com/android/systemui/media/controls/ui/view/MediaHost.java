package com.android.systemui.media.controls.ui.view;

import android.graphics.Rect;
import android.util.ArraySet;
import android.view.View;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.SmartspaceMediaData;
import com.android.systemui.media.controls.ui.controller.MediaCarouselController;
import com.android.systemui.media.controls.ui.controller.MediaHierarchyManager;
import com.android.systemui.media.controls.ui.controller.MediaHostStatesManager;
import com.android.systemui.util.animation.DisappearParameters;
import com.android.systemui.util.animation.MeasurementInput;
import com.android.systemui.util.animation.UniqueObjectHostView;
import java.util.Iterator;
import java.util.Objects;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaHost implements MediaHostState {
    public UniqueObjectHostView hostView;
    public boolean inited;
    public boolean listeningToMediaData;
    public final MediaCarouselController mediaCarouselController;
    public final MediaDataManager mediaDataManager;
    public final MediaHierarchyManager mediaHierarchyManager;
    public final MediaHostStatesManager mediaHostStatesManager;
    public final MediaHostStateHolder state;
    public int location = -1;
    public final ArraySet visibleChangedListeners = new ArraySet();
    public final int[] tmpLocationOnScreen = {0, 0};
    public final Rect currentBounds = new Rect();
    public final Rect currentClipping = new Rect();
    public final MediaHost$listener$1 listener = new MediaDataManager.Listener() { // from class: com.android.systemui.media.controls.ui.view.MediaHost$listener$1
        @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
        public final void onMediaDataLoaded(String str, String str2, MediaData mediaData, boolean z, int i, boolean z2) {
            if (z) {
                MediaHost.this.updateViewVisibility();
            }
        }

        @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
        public final void onMediaDataRemoved(String str, boolean z) {
            MediaHost.this.updateViewVisibility();
        }

        @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
        public final void onSmartspaceMediaDataLoaded(String str, SmartspaceMediaData smartspaceMediaData, boolean z) {
            MediaHost.this.updateViewVisibility();
        }

        @Override // com.android.systemui.media.controls.domain.pipeline.MediaDataManager.Listener
        public final void onSmartspaceMediaDataRemoved(String str, boolean z) {
            if (z) {
                MediaHost.this.updateViewVisibility();
            }
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaHostStateHolder implements MediaHostState {
        public Function0 changedListener;
        public boolean disablePagination;
        public DisappearParameters disappearParameters;
        public boolean expandedMatchesParentHeight;
        public float expansion;
        public boolean falsingProtectionNeeded;
        public int lastDisappearHash;
        public MeasurementInput measurementInput;
        public boolean showsOnlyActiveMedia;
        public float squishFraction = 1.0f;
        public boolean visible = true;

        public MediaHostStateHolder() {
            DisappearParameters disappearParameters = new DisappearParameters();
            this.disappearParameters = disappearParameters;
            this.lastDisappearHash = disappearParameters.hashCode();
        }

        @Override // com.android.systemui.media.controls.ui.view.MediaHostState
        public final MediaHostStateHolder copy() {
            MediaHostStateHolder mediaHostStateHolder = new MediaHostStateHolder();
            mediaHostStateHolder.setExpansion(this.expansion);
            boolean z = this.expandedMatchesParentHeight;
            if (z != mediaHostStateHolder.expandedMatchesParentHeight) {
                mediaHostStateHolder.expandedMatchesParentHeight = z;
                Function0 function0 = mediaHostStateHolder.changedListener;
                if (function0 != null) {
                    ((MediaHost$init$3) function0).invoke();
                }
            }
            float f = this.squishFraction;
            if (!Float.valueOf(f).equals(Float.valueOf(mediaHostStateHolder.squishFraction))) {
                mediaHostStateHolder.squishFraction = f;
                Function0 function02 = mediaHostStateHolder.changedListener;
                if (function02 != null) {
                    ((MediaHost$init$3) function02).invoke();
                }
            }
            boolean z2 = this.showsOnlyActiveMedia;
            if (!Boolean.valueOf(z2).equals(Boolean.valueOf(mediaHostStateHolder.showsOnlyActiveMedia))) {
                mediaHostStateHolder.showsOnlyActiveMedia = z2;
                Function0 function03 = mediaHostStateHolder.changedListener;
                if (function03 != null) {
                    ((MediaHost$init$3) function03).invoke();
                }
            }
            MeasurementInput measurementInput = this.measurementInput;
            mediaHostStateHolder.setMeasurementInput(measurementInput != null ? new MeasurementInput(measurementInput.widthMeasureSpec, measurementInput.heightMeasureSpec) : null);
            boolean z3 = this.visible;
            if (mediaHostStateHolder.visible != z3) {
                mediaHostStateHolder.visible = z3;
                Function0 function04 = mediaHostStateHolder.changedListener;
                if (function04 != null) {
                    ((MediaHost$init$3) function04).invoke();
                }
            }
            DisappearParameters disappearParameters = this.disappearParameters;
            disappearParameters.getClass();
            DisappearParameters disappearParameters2 = new DisappearParameters();
            disappearParameters2.disappearSize.set(disappearParameters.disappearSize);
            disappearParameters2.gonePivot.set(disappearParameters.gonePivot);
            disappearParameters2.contentTranslationFraction.set(disappearParameters.contentTranslationFraction);
            disappearParameters2.disappearEnd = disappearParameters.disappearEnd;
            disappearParameters2.fadeStartPosition = disappearParameters.fadeStartPosition;
            mediaHostStateHolder.setDisappearParameters(disappearParameters2);
            boolean z4 = this.falsingProtectionNeeded;
            if (mediaHostStateHolder.falsingProtectionNeeded != z4) {
                mediaHostStateHolder.falsingProtectionNeeded = z4;
                Function0 function05 = mediaHostStateHolder.changedListener;
                if (function05 != null) {
                    ((MediaHost$init$3) function05).invoke();
                }
            }
            boolean z5 = this.disablePagination;
            if (mediaHostStateHolder.disablePagination != z5) {
                mediaHostStateHolder.disablePagination = z5;
                Function0 function06 = mediaHostStateHolder.changedListener;
                if (function06 != null) {
                    ((MediaHost$init$3) function06).invoke();
                }
            }
            return mediaHostStateHolder;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof MediaHostState)) {
                return false;
            }
            MediaHostState mediaHostState = (MediaHostState) obj;
            return Objects.equals(this.measurementInput, mediaHostState.getMeasurementInput()) && this.expansion == mediaHostState.getExpansion() && this.squishFraction == mediaHostState.getSquishFraction() && this.showsOnlyActiveMedia == mediaHostState.getShowsOnlyActiveMedia() && this.visible == mediaHostState.getVisible() && this.falsingProtectionNeeded == mediaHostState.getFalsingProtectionNeeded() && this.disappearParameters.equals(mediaHostState.getDisappearParameters()) && this.disablePagination == mediaHostState.getDisablePagination();
        }

        @Override // com.android.systemui.media.controls.ui.view.MediaHostState
        public final boolean getDisablePagination() {
            return this.disablePagination;
        }

        @Override // com.android.systemui.media.controls.ui.view.MediaHostState
        public final DisappearParameters getDisappearParameters() {
            return this.disappearParameters;
        }

        @Override // com.android.systemui.media.controls.ui.view.MediaHostState
        public final boolean getExpandedMatchesParentHeight() {
            return this.expandedMatchesParentHeight;
        }

        @Override // com.android.systemui.media.controls.ui.view.MediaHostState
        public final float getExpansion() {
            return this.expansion;
        }

        @Override // com.android.systemui.media.controls.ui.view.MediaHostState
        public final boolean getFalsingProtectionNeeded() {
            return this.falsingProtectionNeeded;
        }

        @Override // com.android.systemui.media.controls.ui.view.MediaHostState
        public final MeasurementInput getMeasurementInput() {
            return this.measurementInput;
        }

        @Override // com.android.systemui.media.controls.ui.view.MediaHostState
        public final boolean getShowsOnlyActiveMedia() {
            return this.showsOnlyActiveMedia;
        }

        @Override // com.android.systemui.media.controls.ui.view.MediaHostState
        public final float getSquishFraction() {
            return this.squishFraction;
        }

        @Override // com.android.systemui.media.controls.ui.view.MediaHostState
        public final boolean getVisible() {
            return this.visible;
        }

        public final int hashCode() {
            MeasurementInput measurementInput = this.measurementInput;
            return Boolean.hashCode(this.disablePagination) + ((this.disappearParameters.hashCode() + ((TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((measurementInput != null ? measurementInput.hashCode() : 0) * 31, this.expansion, 31), this.squishFraction, 31), 31, this.falsingProtectionNeeded), 31, this.showsOnlyActiveMedia) + (this.visible ? 1 : 2)) * 31)) * 31);
        }

        public final void setDisablePagination(boolean z) {
            if (this.disablePagination == z) {
                return;
            }
            this.disablePagination = z;
            Function0 function0 = this.changedListener;
            if (function0 != null) {
                ((MediaHost$init$3) function0).invoke();
            }
        }

        public final void setDisappearParameters(DisappearParameters disappearParameters) {
            int hashCode = disappearParameters.hashCode();
            if (Integer.valueOf(this.lastDisappearHash).equals(Integer.valueOf(hashCode))) {
                return;
            }
            this.disappearParameters = disappearParameters;
            this.lastDisappearHash = hashCode;
            Function0 function0 = this.changedListener;
            if (function0 != null) {
                function0.invoke();
            }
        }

        public final void setExpandedMatchesParentHeight(boolean z) {
            if (z != this.expandedMatchesParentHeight) {
                this.expandedMatchesParentHeight = z;
                Function0 function0 = this.changedListener;
                if (function0 != null) {
                    ((MediaHost$init$3) function0).invoke();
                }
            }
        }

        public final void setExpansion(float f) {
            if (Float.valueOf(f).equals(Float.valueOf(this.expansion))) {
                return;
            }
            this.expansion = f;
            Function0 function0 = this.changedListener;
            if (function0 != null) {
                function0.invoke();
            }
        }

        public final void setMeasurementInput(MeasurementInput measurementInput) {
            if (measurementInput == null || !measurementInput.equals(this.measurementInput)) {
                this.measurementInput = measurementInput;
                Function0 function0 = this.changedListener;
                if (function0 != null) {
                    function0.invoke();
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [com.android.systemui.media.controls.ui.view.MediaHost$listener$1] */
    public MediaHost(MediaHostStateHolder mediaHostStateHolder, MediaHierarchyManager mediaHierarchyManager, MediaDataManager mediaDataManager, MediaHostStatesManager mediaHostStatesManager, MediaCarouselController mediaCarouselController) {
        this.state = mediaHostStateHolder;
        this.mediaHierarchyManager = mediaHierarchyManager;
        this.mediaDataManager = mediaDataManager;
        this.mediaHostStatesManager = mediaHostStatesManager;
        this.mediaCarouselController = mediaCarouselController;
    }

    @Override // com.android.systemui.media.controls.ui.view.MediaHostState
    public final MediaHostStateHolder copy() {
        return this.state.copy();
    }

    public final Rect getCurrentBounds() {
        UniqueObjectHostView uniqueObjectHostView = this.hostView;
        if (uniqueObjectHostView == null) {
            uniqueObjectHostView = null;
        }
        int[] iArr = this.tmpLocationOnScreen;
        uniqueObjectHostView.getLocationOnScreen(iArr);
        int i = 0;
        int i2 = iArr[0];
        UniqueObjectHostView uniqueObjectHostView2 = this.hostView;
        if (uniqueObjectHostView2 == null) {
            uniqueObjectHostView2 = null;
        }
        int paddingLeft = uniqueObjectHostView2.getPaddingLeft() + i2;
        int i3 = iArr[1];
        UniqueObjectHostView uniqueObjectHostView3 = this.hostView;
        if (uniqueObjectHostView3 == null) {
            uniqueObjectHostView3 = null;
        }
        int paddingTop = uniqueObjectHostView3.getPaddingTop() + i3;
        int i4 = iArr[0];
        UniqueObjectHostView uniqueObjectHostView4 = this.hostView;
        if (uniqueObjectHostView4 == null) {
            uniqueObjectHostView4 = null;
        }
        int width = uniqueObjectHostView4.getWidth() + i4;
        UniqueObjectHostView uniqueObjectHostView5 = this.hostView;
        if (uniqueObjectHostView5 == null) {
            uniqueObjectHostView5 = null;
        }
        int paddingRight = width - uniqueObjectHostView5.getPaddingRight();
        int i5 = iArr[1];
        UniqueObjectHostView uniqueObjectHostView6 = this.hostView;
        if (uniqueObjectHostView6 == null) {
            uniqueObjectHostView6 = null;
        }
        int height = uniqueObjectHostView6.getHeight() + i5;
        UniqueObjectHostView uniqueObjectHostView7 = this.hostView;
        int paddingBottom = height - (uniqueObjectHostView7 != null ? uniqueObjectHostView7 : null).getPaddingBottom();
        if (paddingRight < paddingLeft) {
            paddingLeft = 0;
            paddingRight = 0;
        }
        if (paddingBottom < paddingTop) {
            paddingBottom = 0;
        } else {
            i = paddingTop;
        }
        this.currentBounds.set(paddingLeft, i, paddingRight, paddingBottom);
        return this.currentBounds;
    }

    @Override // com.android.systemui.media.controls.ui.view.MediaHostState
    public final boolean getDisablePagination() {
        return this.state.disablePagination;
    }

    @Override // com.android.systemui.media.controls.ui.view.MediaHostState
    public final DisappearParameters getDisappearParameters() {
        return this.state.disappearParameters;
    }

    @Override // com.android.systemui.media.controls.ui.view.MediaHostState
    public final boolean getExpandedMatchesParentHeight() {
        return this.state.expandedMatchesParentHeight;
    }

    @Override // com.android.systemui.media.controls.ui.view.MediaHostState
    public final float getExpansion() {
        return this.state.expansion;
    }

    @Override // com.android.systemui.media.controls.ui.view.MediaHostState
    public final boolean getFalsingProtectionNeeded() {
        return this.state.falsingProtectionNeeded;
    }

    @Override // com.android.systemui.media.controls.ui.view.MediaHostState
    public final MeasurementInput getMeasurementInput() {
        return this.state.measurementInput;
    }

    @Override // com.android.systemui.media.controls.ui.view.MediaHostState
    public final boolean getShowsOnlyActiveMedia() {
        return this.state.showsOnlyActiveMedia;
    }

    @Override // com.android.systemui.media.controls.ui.view.MediaHostState
    public final float getSquishFraction() {
        return this.state.squishFraction;
    }

    @Override // com.android.systemui.media.controls.ui.view.MediaHostState
    public final boolean getVisible() {
        return this.state.visible;
    }

    public final void init(int i) {
        if (this.inited) {
            return;
        }
        this.inited = true;
        this.location = i;
        this.hostView = this.mediaHierarchyManager.register(this);
        setListeningToMediaData(true);
        UniqueObjectHostView uniqueObjectHostView = this.hostView;
        if (uniqueObjectHostView == null) {
            uniqueObjectHostView = null;
        }
        uniqueObjectHostView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.media.controls.ui.view.MediaHost$init$1
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                MediaHost.this.setListeningToMediaData(true);
                MediaHost.this.updateViewVisibility();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
                MediaHost.this.setListeningToMediaData(false);
            }
        });
        UniqueObjectHostView uniqueObjectHostView2 = this.hostView;
        (uniqueObjectHostView2 != null ? uniqueObjectHostView2 : null).measurementManager = new MediaHost$init$2(this, i);
        this.state.changedListener = new MediaHost$init$3(this, i);
        updateViewVisibility();
    }

    public final void setExpansion(float f) {
        this.state.setExpansion(f);
    }

    public final void setFalsingProtectionNeeded(boolean z) {
        MediaHostStateHolder mediaHostStateHolder = this.state;
        if (mediaHostStateHolder.falsingProtectionNeeded == z) {
            return;
        }
        mediaHostStateHolder.falsingProtectionNeeded = z;
        Function0 function0 = mediaHostStateHolder.changedListener;
        if (function0 != null) {
            ((MediaHost$init$3) function0).invoke();
        }
    }

    public final void setListeningToMediaData(boolean z) {
        if (z != this.listeningToMediaData) {
            this.listeningToMediaData = z;
            MediaHost$listener$1 mediaHost$listener$1 = this.listener;
            MediaDataManager mediaDataManager = this.mediaDataManager;
            if (z) {
                mediaDataManager.addListener(mediaHost$listener$1);
            } else {
                mediaDataManager.removeListener(mediaHost$listener$1);
            }
        }
    }

    public final void setShowsOnlyActiveMedia(boolean z) {
        MediaHostStateHolder mediaHostStateHolder = this.state;
        if (Boolean.valueOf(z).equals(Boolean.valueOf(mediaHostStateHolder.showsOnlyActiveMedia))) {
            return;
        }
        mediaHostStateHolder.showsOnlyActiveMedia = z;
        Function0 function0 = mediaHostStateHolder.changedListener;
        if (function0 != null) {
            ((MediaHost$init$3) function0).invoke();
        }
    }

    public final void setSquishFraction(float f) {
        MediaHostStateHolder mediaHostStateHolder = this.state;
        if (Float.valueOf(f).equals(Float.valueOf(mediaHostStateHolder.squishFraction))) {
            return;
        }
        mediaHostStateHolder.squishFraction = f;
        Function0 function0 = mediaHostStateHolder.changedListener;
        if (function0 != null) {
            ((MediaHost$init$3) function0).invoke();
        }
    }

    public final void updateViewVisibility() {
        boolean hasActiveMediaOrRecommendation;
        MediaCarouselController mediaCarouselController = this.mediaCarouselController;
        boolean z = (mediaCarouselController.allowMediaPlayerOnLockScreen || ((Boolean) mediaCarouselController.isOnGone.getValue()).booleanValue()) ? false : true;
        MediaHostStateHolder mediaHostStateHolder = this.state;
        if (z) {
            hasActiveMediaOrRecommendation = false;
        } else {
            boolean z2 = mediaHostStateHolder.showsOnlyActiveMedia;
            MediaDataManager mediaDataManager = this.mediaDataManager;
            hasActiveMediaOrRecommendation = z2 ? mediaDataManager.hasActiveMediaOrRecommendation() : mediaDataManager.hasAnyMediaOrRecommendation();
        }
        if (mediaHostStateHolder.visible != hasActiveMediaOrRecommendation) {
            mediaHostStateHolder.visible = hasActiveMediaOrRecommendation;
            Function0 function0 = mediaHostStateHolder.changedListener;
            if (function0 != null) {
                ((MediaHost$init$3) function0).invoke();
            }
        }
        int i = mediaHostStateHolder.visible ? 0 : 8;
        UniqueObjectHostView uniqueObjectHostView = this.hostView;
        if (uniqueObjectHostView == null) {
            uniqueObjectHostView = null;
        }
        if (i != uniqueObjectHostView.getVisibility()) {
            UniqueObjectHostView uniqueObjectHostView2 = this.hostView;
            (uniqueObjectHostView2 != null ? uniqueObjectHostView2 : null).setVisibility(i);
            Iterator it = this.visibleChangedListeners.iterator();
            while (it.hasNext()) {
                ((Function1) it.next()).invoke(Boolean.valueOf(mediaHostStateHolder.visible));
            }
        }
    }
}
