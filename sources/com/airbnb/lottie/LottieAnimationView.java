package com.airbnb.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.core.content.ContextCompat;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.manager.FontAssetManager;
import com.airbnb.lottie.manager.ImageAssetManager;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.layer.CompositionLayer;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieValueAnimator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.android.wm.shell.R;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.zip.ZipInputStream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class LottieAnimationView extends AppCompatImageView {
    public static final LottieAnimationView$$ExternalSyntheticLambda1 DEFAULT_FAILURE_LISTENER = new LottieAnimationView$$ExternalSyntheticLambda1();
    public String animationName;
    public int animationResId;
    public boolean autoPlay;
    public boolean cacheComposition;
    public LottieTask compositionTask;
    public LottieListener failureListener;
    public int fallbackResource;
    public boolean ignoreUnschedule;
    public final WeakFailureListener loadedListener;
    public final LottieDrawable lottieDrawable;
    public final Set lottieOnCompositionLoadedListeners;
    public final Set userActionsTaken;
    public final WeakFailureListener wrappedFailureListener;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new AnonymousClass1();
        public String animationName;
        public int animationResId;
        public String imageAssetsFolder;
        public boolean isAnimating;
        public float progress;
        public int repeatCount;
        public int repeatMode;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.airbnb.lottie.LottieAnimationView$SavedState$1, reason: invalid class name */
        public final class AnonymousClass1 implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            public final Object createFromParcel(Parcel parcel) {
                SavedState savedState = new SavedState(parcel);
                savedState.animationName = parcel.readString();
                savedState.progress = parcel.readFloat();
                savedState.isAnimating = parcel.readInt() == 1;
                savedState.imageAssetsFolder = parcel.readString();
                savedState.repeatMode = parcel.readInt();
                savedState.repeatCount = parcel.readInt();
                return savedState;
            }

            @Override // android.os.Parcelable.Creator
            public final Object[] newArray(int i) {
                return new SavedState[i];
            }
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.animationName);
            parcel.writeFloat(this.progress);
            parcel.writeInt(this.isAnimating ? 1 : 0);
            parcel.writeString(this.imageAssetsFolder);
            parcel.writeInt(this.repeatMode);
            parcel.writeInt(this.repeatCount);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class UserActionTaken {
        public static final /* synthetic */ UserActionTaken[] $VALUES;
        public static final UserActionTaken PLAY_OPTION;
        public static final UserActionTaken SET_ANIMATION;
        public static final UserActionTaken SET_IMAGE_ASSETS;
        public static final UserActionTaken SET_PROGRESS;
        public static final UserActionTaken SET_REPEAT_COUNT;
        public static final UserActionTaken SET_REPEAT_MODE;

        static {
            UserActionTaken userActionTaken = new UserActionTaken("SET_ANIMATION", 0);
            SET_ANIMATION = userActionTaken;
            UserActionTaken userActionTaken2 = new UserActionTaken("SET_PROGRESS", 1);
            SET_PROGRESS = userActionTaken2;
            UserActionTaken userActionTaken3 = new UserActionTaken("SET_REPEAT_MODE", 2);
            SET_REPEAT_MODE = userActionTaken3;
            UserActionTaken userActionTaken4 = new UserActionTaken("SET_REPEAT_COUNT", 3);
            SET_REPEAT_COUNT = userActionTaken4;
            UserActionTaken userActionTaken5 = new UserActionTaken("SET_IMAGE_ASSETS", 4);
            SET_IMAGE_ASSETS = userActionTaken5;
            UserActionTaken userActionTaken6 = new UserActionTaken("PLAY_OPTION", 5);
            PLAY_OPTION = userActionTaken6;
            $VALUES = new UserActionTaken[]{userActionTaken, userActionTaken2, userActionTaken3, userActionTaken4, userActionTaken5, userActionTaken6};
        }

        public static UserActionTaken valueOf(String str) {
            return (UserActionTaken) Enum.valueOf(UserActionTaken.class, str);
        }

        public static UserActionTaken[] values() {
            return (UserActionTaken[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WeakFailureListener implements LottieListener {
        public final /* synthetic */ int $r8$classId;
        public final WeakReference targetReference;

        public WeakFailureListener(LottieAnimationView lottieAnimationView, int i) {
            this.$r8$classId = i;
            switch (i) {
                case 1:
                    this.targetReference = new WeakReference(lottieAnimationView);
                    break;
                default:
                    this.targetReference = new WeakReference(lottieAnimationView);
                    break;
            }
        }

        @Override // com.airbnb.lottie.LottieListener
        public final void onResult(Object obj) {
            switch (this.$r8$classId) {
                case 0:
                    Throwable th = (Throwable) obj;
                    LottieAnimationView lottieAnimationView = (LottieAnimationView) this.targetReference.get();
                    if (lottieAnimationView != null) {
                        int i = lottieAnimationView.fallbackResource;
                        if (i != 0) {
                            lottieAnimationView.setImageResource(i);
                        }
                        LottieListener lottieListener = lottieAnimationView.failureListener;
                        if (lottieListener == null) {
                            lottieListener = LottieAnimationView.DEFAULT_FAILURE_LISTENER;
                        }
                        lottieListener.onResult(th);
                        break;
                    }
                    break;
                default:
                    LottieComposition lottieComposition = (LottieComposition) obj;
                    LottieAnimationView lottieAnimationView2 = (LottieAnimationView) this.targetReference.get();
                    if (lottieAnimationView2 != null) {
                        lottieAnimationView2.setComposition(lottieComposition);
                        break;
                    }
                    break;
            }
        }
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.loadedListener = new WeakFailureListener(this, 1);
        this.wrappedFailureListener = new WeakFailureListener(this, 0);
        this.fallbackResource = 0;
        this.lottieDrawable = new LottieDrawable();
        this.ignoreUnschedule = false;
        this.autoPlay = false;
        this.cacheComposition = true;
        this.userActionsTaken = new HashSet();
        this.lottieOnCompositionLoadedListeners = new HashSet();
        init(attributeSet, R.attr.lottieAnimationViewStyle);
    }

    public void addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.lottieDrawable.animator.addListener(animatorListener);
    }

    public void addAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.lottieDrawable.animator.addPauseListener(animatorPauseListener);
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.lottieDrawable.animator.addUpdateListener(animatorUpdateListener);
    }

    public boolean addLottieOnCompositionLoadedListener(LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener) {
        LottieComposition composition = getComposition();
        if (composition != null) {
            lottieOnCompositionLoadedListener.onCompositionLoaded(composition);
        }
        return this.lottieOnCompositionLoadedListeners.add(lottieOnCompositionLoadedListener);
    }

    public void addValueCallback(KeyPath keyPath, Object obj, LottieValueCallback lottieValueCallback) {
        this.lottieDrawable.addValueCallback(keyPath, obj, lottieValueCallback);
    }

    public void cancelAnimation() {
        this.autoPlay = false;
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        LottieDrawable lottieDrawable = this.lottieDrawable;
        lottieDrawable.lazyCompositionTasks.clear();
        lottieDrawable.animator.cancel();
        if (lottieDrawable.isVisible()) {
            return;
        }
        lottieDrawable.onVisibleAction = LottieDrawable.OnVisibleAction.NONE;
    }

    public final void cancelLoaderTask() {
        LottieTask lottieTask = this.compositionTask;
        if (lottieTask != null) {
            WeakFailureListener weakFailureListener = this.loadedListener;
            synchronized (lottieTask) {
                lottieTask.successListeners.remove(weakFailureListener);
            }
            LottieTask lottieTask2 = this.compositionTask;
            WeakFailureListener weakFailureListener2 = this.wrappedFailureListener;
            synchronized (lottieTask2) {
                lottieTask2.failureListeners.remove(weakFailureListener2);
            }
        }
    }

    public void clearValueCallback(KeyPath keyPath, Object obj) {
        this.lottieDrawable.addValueCallback(keyPath, obj, null);
    }

    @Deprecated
    public void disableExtraScaleModeInFitXY() {
        this.lottieDrawable.getClass();
    }

    public void enableMergePathsForKitKatAndAbove(boolean z) {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (lottieDrawable.enableMergePaths == z) {
            return;
        }
        lottieDrawable.enableMergePaths = z;
        if (lottieDrawable.composition != null) {
            lottieDrawable.buildCompositionLayer();
        }
    }

    public AsyncUpdates getAsyncUpdates() {
        AsyncUpdates asyncUpdates = this.lottieDrawable.asyncUpdates;
        return asyncUpdates != null ? asyncUpdates : AsyncUpdates.AUTOMATIC;
    }

    public boolean getAsyncUpdatesEnabled() {
        AsyncUpdates asyncUpdates = this.lottieDrawable.asyncUpdates;
        if (asyncUpdates == null) {
            asyncUpdates = AsyncUpdates.AUTOMATIC;
        }
        return asyncUpdates == AsyncUpdates.ENABLED;
    }

    public boolean getClipTextToBoundingBox() {
        return this.lottieDrawable.clipTextToBoundingBox;
    }

    public boolean getClipToCompositionBounds() {
        return this.lottieDrawable.clipToCompositionBounds;
    }

    public LottieComposition getComposition() {
        Drawable drawable = getDrawable();
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (drawable == lottieDrawable) {
            return lottieDrawable.composition;
        }
        return null;
    }

    public long getDuration() {
        LottieComposition composition = getComposition();
        if (composition != null) {
            return (long) composition.getDuration();
        }
        return 0L;
    }

    public int getFrame() {
        return (int) this.lottieDrawable.animator.frame;
    }

    public String getImageAssetsFolder() {
        return this.lottieDrawable.imageAssetsFolder;
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.lottieDrawable.maintainOriginalImageBounds;
    }

    public float getMaxFrame() {
        return this.lottieDrawable.animator.getMaxFrame();
    }

    public float getMinFrame() {
        return this.lottieDrawable.animator.getMinFrame();
    }

    public PerformanceTracker getPerformanceTracker() {
        LottieComposition lottieComposition = this.lottieDrawable.composition;
        if (lottieComposition != null) {
            return lottieComposition.performanceTracker;
        }
        return null;
    }

    public float getProgress() {
        return this.lottieDrawable.animator.getAnimatedValueAbsolute();
    }

    public RenderMode getRenderMode() {
        return this.lottieDrawable.useSoftwareRendering ? RenderMode.SOFTWARE : RenderMode.HARDWARE;
    }

    public int getRepeatCount() {
        return this.lottieDrawable.animator.getRepeatCount();
    }

    public int getRepeatMode() {
        return this.lottieDrawable.animator.getRepeatMode();
    }

    public float getSpeed() {
        return this.lottieDrawable.animator.speed;
    }

    public boolean hasMasks() {
        CompositionLayer compositionLayer = this.lottieDrawable.compositionLayer;
        return compositionLayer != null && compositionLayer.hasMasks();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean hasMatte() {
        /*
            r3 = this;
            com.airbnb.lottie.LottieDrawable r3 = r3.lottieDrawable
            com.airbnb.lottie.model.layer.CompositionLayer r3 = r3.compositionLayer
            if (r3 == 0) goto L43
            java.lang.Boolean r0 = r3.hasMatte
            r1 = 1
            if (r0 != 0) goto L3a
            com.airbnb.lottie.model.layer.BaseLayer r0 = r3.matteLayer
            if (r0 == 0) goto L15
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r3.hasMatte = r0
        L13:
            r3 = r1
            goto L40
        L15:
            java.util.List r0 = r3.layers
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r0 = r0.size()
            int r0 = r0 - r1
        L1e:
            if (r0 < 0) goto L36
            java.util.List r2 = r3.layers
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            java.lang.Object r2 = r2.get(r0)
            com.airbnb.lottie.model.layer.BaseLayer r2 = (com.airbnb.lottie.model.layer.BaseLayer) r2
            com.airbnb.lottie.model.layer.BaseLayer r2 = r2.matteLayer
            if (r2 == 0) goto L33
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r3.hasMatte = r0
            goto L13
        L33:
            int r0 = r0 + (-1)
            goto L1e
        L36:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r3.hasMatte = r0
        L3a:
            java.lang.Boolean r3 = r3.hasMatte
            boolean r3 = r3.booleanValue()
        L40:
            if (r3 == 0) goto L43
            goto L44
        L43:
            r1 = 0
        L44:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.LottieAnimationView.hasMatte():boolean");
    }

    public final void init(AttributeSet attributeSet, int i) {
        String string;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.LottieAnimationView, i, 0);
        this.cacheComposition = obtainStyledAttributes.getBoolean(2, true);
        boolean hasValue = obtainStyledAttributes.hasValue(14);
        boolean hasValue2 = obtainStyledAttributes.hasValue(9);
        boolean hasValue3 = obtainStyledAttributes.hasValue(19);
        if (hasValue && hasValue2) {
            throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
        }
        if (hasValue) {
            int resourceId = obtainStyledAttributes.getResourceId(14, 0);
            if (resourceId != 0) {
                setAnimation(resourceId);
            }
        } else if (hasValue2) {
            String string2 = obtainStyledAttributes.getString(9);
            if (string2 != null) {
                setAnimation(string2);
            }
        } else if (hasValue3 && (string = obtainStyledAttributes.getString(19)) != null) {
            setAnimationFromUrl(string);
        }
        setFallbackResource(obtainStyledAttributes.getResourceId(8, 0));
        if (obtainStyledAttributes.getBoolean(1, false)) {
            this.autoPlay = true;
        }
        if (obtainStyledAttributes.getBoolean(12, false)) {
            this.lottieDrawable.animator.setRepeatCount(-1);
        }
        if (obtainStyledAttributes.hasValue(17)) {
            setRepeatMode(obtainStyledAttributes.getInt(17, 1));
        }
        if (obtainStyledAttributes.hasValue(16)) {
            setRepeatCount(obtainStyledAttributes.getInt(16, -1));
        }
        if (obtainStyledAttributes.hasValue(18)) {
            setSpeed(obtainStyledAttributes.getFloat(18, 1.0f));
        }
        if (obtainStyledAttributes.hasValue(4)) {
            setClipToCompositionBounds(obtainStyledAttributes.getBoolean(4, true));
        }
        if (obtainStyledAttributes.hasValue(3)) {
            setClipTextToBoundingBox(obtainStyledAttributes.getBoolean(3, false));
        }
        if (obtainStyledAttributes.hasValue(6)) {
            setDefaultFontFileExtension(obtainStyledAttributes.getString(6));
        }
        setImageAssetsFolder(obtainStyledAttributes.getString(11));
        boolean hasValue4 = obtainStyledAttributes.hasValue(13);
        float f = obtainStyledAttributes.getFloat(13, 0.0f);
        if (hasValue4) {
            this.userActionsTaken.add(UserActionTaken.SET_PROGRESS);
        }
        this.lottieDrawable.setProgress(f);
        enableMergePathsForKitKatAndAbove(obtainStyledAttributes.getBoolean(7, false));
        if (obtainStyledAttributes.hasValue(5)) {
            addValueCallback(new KeyPath("**"), LottieProperty.COLOR_FILTER, new LottieValueCallback(new SimpleColorFilter(ContextCompat.getColorStateList(obtainStyledAttributes.getResourceId(5, -1), getContext()).getDefaultColor(), PorterDuff.Mode.SRC_ATOP)));
        }
        if (obtainStyledAttributes.hasValue(15)) {
            int i2 = obtainStyledAttributes.getInt(15, 0);
            if (i2 >= RenderMode.values().length) {
                i2 = 0;
            }
            setRenderMode(RenderMode.values()[i2]);
        }
        if (obtainStyledAttributes.hasValue(0)) {
            int i3 = obtainStyledAttributes.getInt(0, 0);
            if (i3 >= RenderMode.values().length) {
                i3 = 0;
            }
            setAsyncUpdates(AsyncUpdates.values()[i3]);
        }
        setIgnoreDisabledSystemAnimations(obtainStyledAttributes.getBoolean(10, false));
        if (obtainStyledAttributes.hasValue(20)) {
            setUseCompositionFrameRate(obtainStyledAttributes.getBoolean(20, false));
        }
        obtainStyledAttributes.recycle();
        LottieDrawable lottieDrawable = this.lottieDrawable;
        Context context = getContext();
        Utils.AnonymousClass1 anonymousClass1 = Utils.threadLocalPathMeasure;
        boolean z = Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f) != 0.0f;
        lottieDrawable.getClass();
        lottieDrawable.systemAnimationsEnabled = z;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        Drawable drawable = getDrawable();
        if (drawable instanceof LottieDrawable) {
            boolean z = ((LottieDrawable) drawable).useSoftwareRendering;
            RenderMode renderMode = RenderMode.SOFTWARE;
            if ((z ? renderMode : RenderMode.HARDWARE) == renderMode) {
                this.lottieDrawable.invalidateSelf();
            }
        }
    }

    @Override // android.widget.ImageView, android.view.View, android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable drawable2 = getDrawable();
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (drawable2 == lottieDrawable) {
            super.invalidateDrawable(lottieDrawable);
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    public boolean isAnimating() {
        LottieValueAnimator lottieValueAnimator = this.lottieDrawable.animator;
        if (lottieValueAnimator == null) {
            return false;
        }
        return lottieValueAnimator.running;
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.lottieDrawable.enableMergePaths;
    }

    @Deprecated
    public void loop(boolean z) {
        this.lottieDrawable.animator.setRepeatCount(z ? -1 : 0);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode() || !this.autoPlay) {
            return;
        }
        this.lottieDrawable.playAnimation();
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        int i;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.animationName = savedState.animationName;
        Set set = this.userActionsTaken;
        UserActionTaken userActionTaken = UserActionTaken.SET_ANIMATION;
        if (!set.contains(userActionTaken) && !TextUtils.isEmpty(this.animationName)) {
            setAnimation(this.animationName);
        }
        this.animationResId = savedState.animationResId;
        if (!this.userActionsTaken.contains(userActionTaken) && (i = this.animationResId) != 0) {
            setAnimation(i);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_PROGRESS)) {
            this.lottieDrawable.setProgress(savedState.progress);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.PLAY_OPTION) && savedState.isAnimating) {
            playAnimation();
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_IMAGE_ASSETS)) {
            setImageAssetsFolder(savedState.imageAssetsFolder);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_REPEAT_MODE)) {
            setRepeatMode(savedState.repeatMode);
        }
        if (this.userActionsTaken.contains(UserActionTaken.SET_REPEAT_COUNT)) {
            return;
        }
        setRepeatCount(savedState.repeatCount);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        boolean z;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.animationName = this.animationName;
        savedState.animationResId = this.animationResId;
        savedState.progress = this.lottieDrawable.animator.getAnimatedValueAbsolute();
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (lottieDrawable.isVisible()) {
            z = lottieDrawable.animator.running;
        } else {
            LottieDrawable.OnVisibleAction onVisibleAction = lottieDrawable.onVisibleAction;
            z = onVisibleAction == LottieDrawable.OnVisibleAction.PLAY || onVisibleAction == LottieDrawable.OnVisibleAction.RESUME;
        }
        savedState.isAnimating = z;
        LottieDrawable lottieDrawable2 = this.lottieDrawable;
        savedState.imageAssetsFolder = lottieDrawable2.imageAssetsFolder;
        savedState.repeatMode = lottieDrawable2.animator.getRepeatMode();
        savedState.repeatCount = this.lottieDrawable.animator.getRepeatCount();
        return savedState;
    }

    public void pauseAnimation() {
        this.autoPlay = false;
        this.lottieDrawable.pauseAnimation();
    }

    public void playAnimation() {
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.playAnimation();
    }

    public void removeAllAnimatorListeners() {
        this.lottieDrawable.animator.removeAllListeners();
    }

    public void removeAllLottieOnCompositionLoadedListener() {
        this.lottieOnCompositionLoadedListeners.clear();
    }

    public void removeAllUpdateListeners() {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        lottieDrawable.animator.removeAllUpdateListeners();
        lottieDrawable.animator.addUpdateListener(lottieDrawable.progressUpdateListener);
    }

    public void removeAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.lottieDrawable.animator.removeListener(animatorListener);
    }

    public void removeAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.lottieDrawable.animator.removePauseListener(animatorPauseListener);
    }

    public boolean removeLottieOnCompositionLoadedListener(LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener) {
        return this.lottieOnCompositionLoadedListeners.remove(lottieOnCompositionLoadedListener);
    }

    public void removeUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.lottieDrawable.animator.removeUpdateListener(animatorUpdateListener);
    }

    public List resolveKeyPath(KeyPath keyPath) {
        return this.lottieDrawable.resolveKeyPath(keyPath);
    }

    public void resumeAnimation() {
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.resumeAnimation();
    }

    public void reverseAnimationSpeed() {
        LottieValueAnimator lottieValueAnimator = this.lottieDrawable.animator;
        lottieValueAnimator.speed = -lottieValueAnimator.speed;
    }

    public void setAnimation(InputStream inputStream, String str) {
        setCompositionTask(LottieCompositionFactory.cache(str, new LottieCompositionFactory$$ExternalSyntheticLambda1(inputStream, str), new LottieCompositionFactory$$ExternalSyntheticLambda2(inputStream)));
    }

    @Deprecated
    public void setAnimationFromJson(String str) {
        setAnimationFromJson(str, null);
    }

    public void setAnimationFromUrl(String str) {
        LottieTask cache;
        int i = 0;
        String str2 = null;
        if (this.cacheComposition) {
            Context context = getContext();
            Map map = LottieCompositionFactory.taskCache;
            String m = AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("url_", str);
            cache = LottieCompositionFactory.cache(m, new LottieCompositionFactory$$ExternalSyntheticLambda0(context, str, m, i), null);
        } else {
            cache = LottieCompositionFactory.cache(null, new LottieCompositionFactory$$ExternalSyntheticLambda0(getContext(), str, str2, i), null);
        }
        setCompositionTask(cache);
    }

    public void setApplyingOpacityToLayersEnabled(boolean z) {
        this.lottieDrawable.isApplyingOpacityToLayersEnabled = z;
    }

    public void setAsyncUpdates(AsyncUpdates asyncUpdates) {
        this.lottieDrawable.asyncUpdates = asyncUpdates;
    }

    public void setCacheComposition(boolean z) {
        this.cacheComposition = z;
    }

    public void setClipTextToBoundingBox(boolean z) {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (z != lottieDrawable.clipTextToBoundingBox) {
            lottieDrawable.clipTextToBoundingBox = z;
            lottieDrawable.invalidateSelf();
        }
    }

    public void setClipToCompositionBounds(boolean z) {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (z != lottieDrawable.clipToCompositionBounds) {
            lottieDrawable.clipToCompositionBounds = z;
            CompositionLayer compositionLayer = lottieDrawable.compositionLayer;
            if (compositionLayer != null) {
                compositionLayer.clipToCompositionBounds = z;
            }
            lottieDrawable.invalidateSelf();
        }
    }

    public void setComposition(LottieComposition lottieComposition) {
        this.lottieDrawable.setCallback(this);
        this.ignoreUnschedule = true;
        boolean composition = this.lottieDrawable.setComposition(lottieComposition);
        if (this.autoPlay) {
            this.lottieDrawable.playAnimation();
        }
        this.ignoreUnschedule = false;
        if (getDrawable() != this.lottieDrawable || composition) {
            if (!composition) {
                boolean isAnimating = isAnimating();
                setImageDrawable(null);
                setImageDrawable(this.lottieDrawable);
                if (isAnimating) {
                    this.lottieDrawable.resumeAnimation();
                }
            }
            onVisibilityChanged(this, getVisibility());
            requestLayout();
            Iterator it = ((HashSet) this.lottieOnCompositionLoadedListeners).iterator();
            while (it.hasNext()) {
                ((LottieOnCompositionLoadedListener) it.next()).onCompositionLoaded(lottieComposition);
            }
        }
    }

    public final void setCompositionTask(LottieTask lottieTask) {
        LottieResult lottieResult = lottieTask.result;
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (lottieResult != null && lottieDrawable == getDrawable() && lottieDrawable.composition == lottieResult.value) {
            return;
        }
        this.userActionsTaken.add(UserActionTaken.SET_ANIMATION);
        this.lottieDrawable.clearComposition();
        cancelLoaderTask();
        lottieTask.addListener(this.loadedListener);
        lottieTask.addFailureListener(this.wrappedFailureListener);
        this.compositionTask = lottieTask;
    }

    public void setDefaultFontFileExtension(String str) {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        lottieDrawable.defaultFontFileExtension = str;
        FontAssetManager fontAssetManager = lottieDrawable.getFontAssetManager();
        if (fontAssetManager != null) {
            fontAssetManager.defaultFontFileExtension = str;
        }
    }

    public void setFailureListener(LottieListener lottieListener) {
        this.failureListener = lottieListener;
    }

    public void setFallbackResource(int i) {
        this.fallbackResource = i;
    }

    public void setFontAssetDelegate(FontAssetDelegate fontAssetDelegate) {
        FontAssetManager fontAssetManager = this.lottieDrawable.fontAssetManager;
    }

    public void setFontMap(Map map) {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (map == lottieDrawable.fontMap) {
            return;
        }
        lottieDrawable.fontMap = map;
        lottieDrawable.invalidateSelf();
    }

    public void setFrame(int i) {
        this.lottieDrawable.setFrame(i);
    }

    public void setIgnoreDisabledSystemAnimations(boolean z) {
        this.lottieDrawable.ignoreSystemAnimationsDisabled = z;
    }

    public void setImageAssetDelegate(ImageAssetDelegate imageAssetDelegate) {
        ImageAssetManager imageAssetManager = this.lottieDrawable.imageAssetManager;
    }

    public void setImageAssetsFolder(String str) {
        this.lottieDrawable.imageAssetsFolder = str;
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        this.animationResId = 0;
        this.animationName = null;
        cancelLoaderTask();
        super.setImageBitmap(bitmap);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.animationResId = 0;
        this.animationName = null;
        cancelLoaderTask();
        super.setImageDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        this.animationResId = 0;
        this.animationName = null;
        cancelLoaderTask();
        super.setImageResource(i);
    }

    public void setMaintainOriginalImageBounds(boolean z) {
        this.lottieDrawable.maintainOriginalImageBounds = z;
    }

    public void setMaxFrame(int i) {
        this.lottieDrawable.setMaxFrame(i);
    }

    public void setMaxProgress(float f) {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        LottieComposition lottieComposition = lottieDrawable.composition;
        if (lottieComposition == null) {
            lottieDrawable.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda8(lottieDrawable, f, 1));
            return;
        }
        LottieValueAnimator lottieValueAnimator = lottieDrawable.animator;
        lottieValueAnimator.setMinAndMaxFrames(lottieValueAnimator.minFrame, MiscUtils.lerp(lottieComposition.startFrame, lottieComposition.endFrame, f));
    }

    public void setMinAndMaxFrame(String str) {
        this.lottieDrawable.setMinAndMaxFrame(str);
    }

    public void setMinAndMaxProgress(float f, float f2) {
        this.lottieDrawable.setMinAndMaxProgress(f, f2);
    }

    public void setMinFrame(int i) {
        this.lottieDrawable.setMinFrame(i);
    }

    public void setMinProgress(float f) {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        LottieComposition lottieComposition = lottieDrawable.composition;
        if (lottieComposition == null) {
            lottieDrawable.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda8(lottieDrawable, f, 0));
        } else {
            lottieDrawable.setMinFrame((int) MiscUtils.lerp(lottieComposition.startFrame, lottieComposition.endFrame, f));
        }
    }

    public void setOutlineMasksAndMattes(boolean z) {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        if (lottieDrawable.outlineMasksAndMattes == z) {
            return;
        }
        lottieDrawable.outlineMasksAndMattes = z;
        CompositionLayer compositionLayer = lottieDrawable.compositionLayer;
        if (compositionLayer != null) {
            compositionLayer.setOutlineMasksAndMattes(z);
        }
    }

    public void setPerformanceTrackingEnabled(boolean z) {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        lottieDrawable.performanceTrackingEnabled = z;
        LottieComposition lottieComposition = lottieDrawable.composition;
        if (lottieComposition != null) {
            lottieComposition.performanceTracker.enabled = z;
        }
    }

    public void setProgress(float f) {
        this.userActionsTaken.add(UserActionTaken.SET_PROGRESS);
        this.lottieDrawable.setProgress(f);
    }

    public void setRenderMode(RenderMode renderMode) {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        lottieDrawable.renderMode = renderMode;
        lottieDrawable.computeRenderMode();
    }

    public void setRepeatCount(int i) {
        this.userActionsTaken.add(UserActionTaken.SET_REPEAT_COUNT);
        this.lottieDrawable.animator.setRepeatCount(i);
    }

    public void setRepeatMode(int i) {
        this.userActionsTaken.add(UserActionTaken.SET_REPEAT_MODE);
        this.lottieDrawable.animator.setRepeatMode(i);
    }

    public void setSafeMode(boolean z) {
        this.lottieDrawable.safeMode = z;
    }

    public void setSpeed(float f) {
        this.lottieDrawable.animator.speed = f;
    }

    public void setTextDelegate(TextDelegate textDelegate) {
        this.lottieDrawable.getClass();
    }

    public void setUseCompositionFrameRate(boolean z) {
        this.lottieDrawable.animator.useCompositionFrameRate = z;
    }

    @Override // android.view.View
    public void unscheduleDrawable(Drawable drawable) {
        LottieDrawable lottieDrawable;
        boolean z = this.ignoreUnschedule;
        if (!z && drawable == (lottieDrawable = this.lottieDrawable)) {
            LottieValueAnimator lottieValueAnimator = lottieDrawable.animator;
            if (lottieValueAnimator == null ? false : lottieValueAnimator.running) {
                pauseAnimation();
                super.unscheduleDrawable(drawable);
            }
        }
        if (!z && (drawable instanceof LottieDrawable)) {
            LottieDrawable lottieDrawable2 = (LottieDrawable) drawable;
            LottieValueAnimator lottieValueAnimator2 = lottieDrawable2.animator;
            if (lottieValueAnimator2 != null ? lottieValueAnimator2.running : false) {
                lottieDrawable2.pauseAnimation();
            }
        }
        super.unscheduleDrawable(drawable);
    }

    public Bitmap updateBitmap(String str, Bitmap bitmap) {
        LottieDrawable lottieDrawable = this.lottieDrawable;
        ImageAssetManager imageAssetManager = lottieDrawable.getImageAssetManager();
        Bitmap bitmap2 = null;
        if (imageAssetManager == null) {
            Logger.warning("Cannot update bitmap. Most likely the drawable is not added to a View which prevents Lottie from getting a Context.");
        } else {
            if (bitmap == null) {
                LottieImageAsset lottieImageAsset = (LottieImageAsset) imageAssetManager.imageAssets.get(str);
                Bitmap bitmap3 = lottieImageAsset.bitmap;
                lottieImageAsset.bitmap = null;
                bitmap2 = bitmap3;
            } else {
                bitmap2 = ((LottieImageAsset) imageAssetManager.imageAssets.get(str)).bitmap;
                imageAssetManager.putBitmap(str, bitmap);
            }
            lottieDrawable.invalidateSelf();
        }
        return bitmap2;
    }

    public void addValueCallback(KeyPath keyPath, Object obj, final SimpleLottieValueCallback simpleLottieValueCallback) {
        this.lottieDrawable.addValueCallback(keyPath, obj, new LottieValueCallback() { // from class: com.airbnb.lottie.LottieAnimationView.1
            @Override // com.airbnb.lottie.value.LottieValueCallback
            public final Object getValue(LottieFrameInfo lottieFrameInfo) {
                return SimpleLottieValueCallback.this.getValue();
            }
        });
    }

    public void setAnimationFromJson(String str, String str2) {
        setAnimation(new ByteArrayInputStream(str.getBytes()), str2);
    }

    public void setMaxFrame(String str) {
        this.lottieDrawable.setMaxFrame(str);
    }

    public void setMinAndMaxFrame(String str, String str2, boolean z) {
        this.lottieDrawable.setMinAndMaxFrame(str, str2, z);
    }

    public void setMinFrame(String str) {
        this.lottieDrawable.setMinFrame(str);
    }

    public void setAnimation(final int i) {
        LottieTask fromRawRes;
        LottieTask lottieTask;
        this.animationResId = i;
        this.animationName = null;
        if (isInEditMode()) {
            lottieTask = new LottieTask(new Callable() { // from class: com.airbnb.lottie.LottieAnimationView$$ExternalSyntheticLambda2
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    LottieAnimationView lottieAnimationView = LottieAnimationView.this;
                    boolean z = lottieAnimationView.cacheComposition;
                    int i2 = i;
                    if (!z) {
                        return LottieCompositionFactory.fromRawResSync(lottieAnimationView.getContext(), null, i2);
                    }
                    Context context = lottieAnimationView.getContext();
                    return LottieCompositionFactory.fromRawResSync(context, LottieCompositionFactory.rawResCacheKey(i2, context), i2);
                }
            }, true);
        } else {
            if (this.cacheComposition) {
                Context context = getContext();
                fromRawRes = LottieCompositionFactory.fromRawRes(context, LottieCompositionFactory.rawResCacheKey(i, context), i);
            } else {
                fromRawRes = LottieCompositionFactory.fromRawRes(getContext(), null, i);
            }
            lottieTask = fromRawRes;
        }
        setCompositionTask(lottieTask);
    }

    public void setMinAndMaxFrame(int i, int i2) {
        this.lottieDrawable.setMinAndMaxFrame(i, i2);
    }

    public LottieAnimationView(Context context) {
        super(context);
        this.loadedListener = new WeakFailureListener(this, 1);
        this.wrappedFailureListener = new WeakFailureListener(this, 0);
        this.fallbackResource = 0;
        this.lottieDrawable = new LottieDrawable();
        this.ignoreUnschedule = false;
        this.autoPlay = false;
        this.cacheComposition = true;
        this.userActionsTaken = new HashSet();
        this.lottieOnCompositionLoadedListeners = new HashSet();
        init(null, R.attr.lottieAnimationViewStyle);
    }

    public void setAnimation(ZipInputStream zipInputStream, String str) {
        setCompositionTask(LottieCompositionFactory.cache(str, new LottieCompositionFactory$$ExternalSyntheticLambda1(zipInputStream, str), new LottieCompositionFactory$$ExternalSyntheticLambda2(zipInputStream)));
    }

    public void setAnimationFromUrl(String str, String str2) {
        setCompositionTask(LottieCompositionFactory.cache(str2, new LottieCompositionFactory$$ExternalSyntheticLambda0(getContext(), str, str2, 0), null));
    }

    public void setAnimation(final String str) {
        LottieTask cache;
        LottieTask lottieTask;
        int i = 1;
        this.animationName = str;
        this.animationResId = 0;
        if (isInEditMode()) {
            lottieTask = new LottieTask(new Callable() { // from class: com.airbnb.lottie.LottieAnimationView$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    LottieAnimationView lottieAnimationView = LottieAnimationView.this;
                    boolean z = lottieAnimationView.cacheComposition;
                    String str2 = str;
                    if (!z) {
                        return LottieCompositionFactory.fromAssetSync(lottieAnimationView.getContext(), str2, null);
                    }
                    Context context = lottieAnimationView.getContext();
                    Map map = LottieCompositionFactory.taskCache;
                    return LottieCompositionFactory.fromAssetSync(context, str2, "asset_" + str2);
                }
            }, true);
        } else {
            String str2 = null;
            if (this.cacheComposition) {
                Context context = getContext();
                Map map = LottieCompositionFactory.taskCache;
                String m = AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("asset_", str);
                cache = LottieCompositionFactory.cache(m, new LottieCompositionFactory$$ExternalSyntheticLambda0(context.getApplicationContext(), str, m, i), null);
            } else {
                Context context2 = getContext();
                Map map2 = LottieCompositionFactory.taskCache;
                cache = LottieCompositionFactory.cache(null, new LottieCompositionFactory$$ExternalSyntheticLambda0(context2.getApplicationContext(), str, str2, i), null);
            }
            lottieTask = cache;
        }
        setCompositionTask(lottieTask);
    }

    public LottieAnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.loadedListener = new WeakFailureListener(this, 1);
        this.wrappedFailureListener = new WeakFailureListener(this, 0);
        this.fallbackResource = 0;
        this.lottieDrawable = new LottieDrawable();
        this.ignoreUnschedule = false;
        this.autoPlay = false;
        this.cacheComposition = true;
        this.userActionsTaken = new HashSet();
        this.lottieOnCompositionLoadedListeners = new HashSet();
        init(attributeSet, i);
    }
}
