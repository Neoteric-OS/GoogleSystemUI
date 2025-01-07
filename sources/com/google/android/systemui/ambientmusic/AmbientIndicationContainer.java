package com.google.android.systemui.ambientmusic;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.media.MediaMetadata;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.MathUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.app.animation.Interpolators;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.AutoReinflateContainer;
import com.android.systemui.Dependency;
import com.android.systemui.doze.DozeReceiver;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationMediaManager$$ExternalSyntheticLambda0;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class AmbientIndicationContainer extends AutoReinflateContainer implements DozeReceiver, StatusBarStateController.StateListener, NotificationMediaManager.MediaListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public ActivityStarter mActivityStarter;
    public Drawable mAmbientIconOverride;
    public ConstraintLayout mAmbientIndicationContainer;
    public int mAmbientIndicationIconSize;
    public Drawable mAmbientMusicAnimation;
    public Drawable mAmbientMusicNoteIcon;
    public int mAmbientMusicNoteIconIconSize;
    public CharSequence mAmbientMusicText;
    public boolean mAmbientSkipUnlock;
    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17 mDelayedWakeLockFactory;
    public boolean mDozing;
    public PendingIntent mFavoritingIntent;
    public final Handler mHandler;
    public final Rect mIconBounds;
    public String mIconDescription;
    public int mIconOverride;
    public ImageView mIconView;
    public int mIndicationTextMode;
    public boolean mInflated;
    public KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public int mMediaPlaybackState;
    public PendingIntent mOpenIntent;
    public PowerInteractor mPowerInteractor;
    public Drawable mReverseChargingAnimation;
    public CharSequence mReverseChargingMessage;
    public ShadeViewController mShadeViewController;
    public int mStatusBarState;
    public int mTextColor;
    public ValueAnimator mTextColorAnimator;
    public TextView mTextView;
    public WakeLock mWakeLock;
    public CharSequence mWirelessChargingMessage;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.ambientmusic.AmbientIndicationContainer$2, reason: invalid class name */
    public final class AnonymousClass2 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ AmbientIndicationContainer this$0;

        public /* synthetic */ AnonymousClass2(AmbientIndicationContainer ambientIndicationContainer, int i) {
            this.$r8$classId = i;
            this.this$0 = ambientIndicationContainer;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.mWakeLock.release("AmbientIndication");
                    this.this$0.mTextView.animate().setListener(null);
                    break;
                default:
                    this.this$0.mTextColorAnimator = null;
                    break;
            }
        }
    }

    public static void $r8$lambda$DFan0h9JQgIimo3ogLWaY_C9MMU(final AmbientIndicationContainer ambientIndicationContainer) {
        final int i = 1;
        ambientIndicationContainer.mTextView = (TextView) ambientIndicationContainer.findViewById(R.id.ambient_indication_text);
        ambientIndicationContainer.mIconView = (ImageView) ambientIndicationContainer.findViewById(R.id.ambient_indication_icon);
        ambientIndicationContainer.mAmbientIndicationContainer = (ConstraintLayout) ambientIndicationContainer.findViewById(R.id.ambient_indication);
        ConstraintSet constraintSet = new ConstraintSet();
        if (ambientIndicationContainer.mKeyguardUpdateMonitor.mAuthController.isUdfpsSupported()) {
            constraintSet.load(R.xml.ambient_indication_inner_downwards, ((FrameLayout) ambientIndicationContainer).mContext);
        } else {
            constraintSet.load(R.xml.ambient_indication_inner_upwards, ((FrameLayout) ambientIndicationContainer).mContext);
        }
        constraintSet.applyTo(ambientIndicationContainer.mAmbientIndicationContainer);
        ambientIndicationContainer.mAmbientMusicAnimation = null;
        ambientIndicationContainer.mAmbientMusicNoteIcon = null;
        ambientIndicationContainer.mReverseChargingAnimation = null;
        ambientIndicationContainer.mTextColor = ambientIndicationContainer.mTextView.getCurrentTextColor();
        ambientIndicationContainer.mAmbientIndicationIconSize = ambientIndicationContainer.getResources().getDimensionPixelSize(R.dimen.ambient_indication_icon_size);
        ambientIndicationContainer.mAmbientMusicNoteIconIconSize = ambientIndicationContainer.getResources().getDimensionPixelSize(R.dimen.ambient_indication_note_icon_size);
        ambientIndicationContainer.mTextView.setEnabled(!ambientIndicationContainer.mDozing);
        ambientIndicationContainer.updateColors$3();
        ambientIndicationContainer.updatePill();
        final int i2 = 0;
        ambientIndicationContainer.mTextView.setOnClickListener(new View.OnClickListener(ambientIndicationContainer) { // from class: com.google.android.systemui.ambientmusic.AmbientIndicationContainer$$ExternalSyntheticLambda4
            public final /* synthetic */ AmbientIndicationContainer f$0;

            {
                this.f$0 = ambientIndicationContainer;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i2;
                AmbientIndicationContainer ambientIndicationContainer2 = this.f$0;
                switch (i3) {
                    case 0:
                        int i4 = AmbientIndicationContainer.$r8$clinit;
                        ambientIndicationContainer2.onTextClick();
                        break;
                    default:
                        if (ambientIndicationContainer2.mFavoritingIntent == null) {
                            ambientIndicationContainer2.onTextClick();
                            break;
                        } else {
                            ambientIndicationContainer2.mPowerInteractor.wakeUpIfDozing(4, "AMBIENT_MUSIC_CLICK");
                            AmbientIndicationContainer.sendBroadcastWithoutDismissingKeyguard(ambientIndicationContainer2.mFavoritingIntent);
                            break;
                        }
                }
            }
        });
        ambientIndicationContainer.mIconView.setOnClickListener(new View.OnClickListener(ambientIndicationContainer) { // from class: com.google.android.systemui.ambientmusic.AmbientIndicationContainer$$ExternalSyntheticLambda4
            public final /* synthetic */ AmbientIndicationContainer f$0;

            {
                this.f$0 = ambientIndicationContainer;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i;
                AmbientIndicationContainer ambientIndicationContainer2 = this.f$0;
                switch (i3) {
                    case 0:
                        int i4 = AmbientIndicationContainer.$r8$clinit;
                        ambientIndicationContainer2.onTextClick();
                        break;
                    default:
                        if (ambientIndicationContainer2.mFavoritingIntent == null) {
                            ambientIndicationContainer2.onTextClick();
                            break;
                        } else {
                            ambientIndicationContainer2.mPowerInteractor.wakeUpIfDozing(4, "AMBIENT_MUSIC_CLICK");
                            AmbientIndicationContainer.sendBroadcastWithoutDismissingKeyguard(ambientIndicationContainer2.mFavoritingIntent);
                            break;
                        }
                }
            }
        });
        ambientIndicationContainer.mInflated = true;
        ambientIndicationContainer.updateBottomSpacing$1();
    }

    public AmbientIndicationContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIconBounds = new Rect();
        this.mIconOverride = -1;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public static void sendBroadcastWithoutDismissingKeyguard(PendingIntent pendingIntent) {
        if (pendingIntent.isActivity()) {
            return;
        }
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            Log.w("AmbientIndication", "Sending intent failed: " + e);
        }
    }

    public WakeLock createWakeLock() {
        return this.mDelayedWakeLockFactory.create("AmbientIndication");
    }

    @Override // com.android.systemui.doze.DozeReceiver
    public final void dozeTimeTick() {
        updatePill();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).addCallback(this);
        NotificationMediaManager notificationMediaManager = (NotificationMediaManager) Dependency.sDependency.getDependencyInner(NotificationMediaManager.class);
        notificationMediaManager.mMediaListeners.add(this);
        notificationMediaManager.mBackgroundExecutor.execute(new NotificationMediaManager$$ExternalSyntheticLambda0(0, notificationMediaManager, this));
    }

    @Override // com.android.systemui.AutoReinflateContainer, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateBottomSpacing$1();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).removeCallback(this);
        ((NotificationMediaManager) Dependency.sDependency.getDependencyInner(NotificationMediaManager.class)).mMediaListeners.remove(this);
        this.mMediaPlaybackState = 0;
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onDozingChanged(boolean z) {
        this.mDozing = z;
        if (this.mStatusBarState == 1) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setEnabled(!z);
            updateColors$3();
        }
    }

    @Override // com.android.systemui.statusbar.NotificationMediaManager.MediaListener
    public final void onPrimaryMetadataOrStateChanged(MediaMetadata mediaMetadata, int i) {
        if (this.mMediaPlaybackState != i) {
            this.mMediaPlaybackState = i;
            if (NotificationMediaManager.isPlayingState(i)) {
                setAmbientMusic(null, null, null, 0, false, null);
            }
        }
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onStateChanged(int i) {
        this.mStatusBarState = i;
        if (i == 1) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    public final void onTextClick() {
        if (this.mOpenIntent != null) {
            this.mPowerInteractor.wakeUpIfDozing(4, "AMBIENT_MUSIC_CLICK");
            if (this.mAmbientSkipUnlock) {
                sendBroadcastWithoutDismissingKeyguard(this.mOpenIntent);
            } else {
                this.mActivityStarter.startPendingIntentDismissingKeyguard(this.mOpenIntent);
            }
        }
    }

    public final void setAmbientMusic(CharSequence charSequence, PendingIntent pendingIntent, PendingIntent pendingIntent2, int i, boolean z, String str) {
        Drawable drawable;
        if (Objects.equals(this.mAmbientMusicText, charSequence) && Objects.equals(this.mOpenIntent, pendingIntent) && Objects.equals(this.mFavoritingIntent, pendingIntent2) && this.mIconOverride == i && Objects.equals(this.mIconDescription, str) && this.mAmbientSkipUnlock == z) {
            return;
        }
        this.mAmbientMusicText = charSequence;
        this.mOpenIntent = pendingIntent;
        this.mFavoritingIntent = pendingIntent2;
        this.mAmbientSkipUnlock = z;
        this.mIconOverride = i;
        this.mIconDescription = str;
        Context context = ((FrameLayout) this).mContext;
        switch (i) {
            case 1:
                drawable = context.getDrawable(R.drawable.ic_music_search);
                break;
            case 2:
            default:
                drawable = null;
                break;
            case 3:
                drawable = context.getDrawable(R.drawable.ic_music_not_found);
                break;
            case 4:
                drawable = context.getDrawable(R.drawable.ic_cloud_off);
                break;
            case 5:
                drawable = context.getDrawable(R.drawable.ic_favorite);
                break;
            case 6:
                drawable = context.getDrawable(R.drawable.ic_favorite_border);
                break;
            case 7:
                drawable = context.getDrawable(R.drawable.ic_error);
                break;
            case 8:
                drawable = context.getDrawable(R.drawable.ic_favorite_note);
                break;
        }
        this.mAmbientIconOverride = drawable;
        updatePill();
    }

    public final void updateBottomSpacing$1() {
        if (this.mInflated) {
            this.mShadeViewController.setAmbientIndicationTop(getTop(), this.mTextView.getVisibility() == 0);
        }
    }

    public final void updateColors$3() {
        ValueAnimator valueAnimator = this.mTextColorAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mTextColorAnimator.cancel();
        }
        int defaultColor = this.mTextView.getTextColors().getDefaultColor();
        int i = this.mDozing ? -1 : this.mTextColor;
        if (defaultColor == i) {
            this.mTextView.setTextColor(i);
            this.mIconView.setImageTintList(ColorStateList.valueOf(i));
            return;
        }
        ValueAnimator ofArgb = ValueAnimator.ofArgb(defaultColor, i);
        this.mTextColorAnimator = ofArgb;
        ofArgb.setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN);
        this.mTextColorAnimator.setDuration(500L);
        this.mTextColorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.systemui.ambientmusic.AmbientIndicationContainer$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                AmbientIndicationContainer ambientIndicationContainer = AmbientIndicationContainer.this;
                int i2 = AmbientIndicationContainer.$r8$clinit;
                ambientIndicationContainer.getClass();
                int intValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                ambientIndicationContainer.mTextView.setTextColor(intValue);
                ambientIndicationContainer.mIconView.setImageTintList(ColorStateList.valueOf(intValue));
            }
        });
        this.mTextColorAnimator.addListener(new AnonymousClass2(this, 1));
        this.mTextColorAnimator.start();
    }

    public final void updatePill() {
        Drawable drawable;
        int i = 0;
        TextView textView = this.mTextView;
        if (textView == null) {
            return;
        }
        int i2 = this.mIndicationTextMode;
        boolean z = true;
        this.mIndicationTextMode = 1;
        CharSequence charSequence = this.mAmbientMusicText;
        boolean z2 = textView.getVisibility() == 0;
        CharSequence charSequence2 = this.mAmbientMusicText;
        boolean z3 = charSequence2 != null && charSequence2.length() == 0;
        this.mTextView.setClickable(this.mOpenIntent != null);
        this.mIconView.setClickable((this.mFavoritingIntent == null && this.mOpenIntent == null) ? false : true);
        CharSequence charSequence3 = TextUtils.isEmpty(this.mIconDescription) ? charSequence : this.mIconDescription;
        Drawable drawable2 = null;
        if (!TextUtils.isEmpty(this.mReverseChargingMessage)) {
            this.mIndicationTextMode = 2;
            charSequence = this.mReverseChargingMessage;
            if (this.mReverseChargingAnimation == null) {
                this.mReverseChargingAnimation = ((FrameLayout) this).mContext.getDrawable(R.anim.reverse_charging_animation);
            }
            Drawable drawable3 = this.mReverseChargingAnimation;
            this.mTextView.setClickable(false);
            this.mIconView.setClickable(false);
            charSequence3 = null;
            drawable2 = drawable3;
            z3 = false;
        } else if (!TextUtils.isEmpty(this.mWirelessChargingMessage)) {
            this.mIndicationTextMode = 3;
            charSequence = this.mWirelessChargingMessage;
            this.mTextView.setClickable(false);
            this.mIconView.setClickable(false);
            z3 = false;
            charSequence3 = null;
        } else if ((!TextUtils.isEmpty(charSequence) || z3) && (drawable2 = this.mAmbientIconOverride) == null) {
            if (z2) {
                if (this.mAmbientMusicNoteIcon == null) {
                    this.mAmbientMusicNoteIcon = ((FrameLayout) this).mContext.getDrawable(R.drawable.ic_music_note);
                }
                drawable2 = this.mAmbientMusicNoteIcon;
            } else {
                if (this.mAmbientMusicAnimation == null) {
                    this.mAmbientMusicAnimation = ((FrameLayout) this).mContext.getDrawable(R.anim.audioanim_animation);
                }
                drawable2 = this.mAmbientMusicAnimation;
            }
        }
        this.mTextView.setText(charSequence);
        this.mTextView.setContentDescription(charSequence);
        this.mIconView.setContentDescription(charSequence3);
        if (drawable2 != null) {
            this.mIconBounds.set(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
            MathUtils.fitRect(this.mIconBounds, drawable2 == this.mAmbientMusicNoteIcon ? this.mAmbientMusicNoteIconIconSize : this.mAmbientIndicationIconSize);
            drawable = new DrawableWrapper(drawable2) { // from class: com.google.android.systemui.ambientmusic.AmbientIndicationContainer.1
                @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
                public final int getIntrinsicHeight() {
                    return AmbientIndicationContainer.this.mIconBounds.height();
                }

                @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
                public final int getIntrinsicWidth() {
                    return AmbientIndicationContainer.this.mIconBounds.width();
                }
            };
            int i3 = !TextUtils.isEmpty(charSequence) ? (int) (getResources().getDisplayMetrics().density * 24.0f) : 0;
            TextView textView2 = this.mTextView;
            textView2.setPaddingRelative(textView2.getPaddingStart(), this.mTextView.getPaddingTop(), i3, this.mTextView.getPaddingBottom());
        } else {
            TextView textView3 = this.mTextView;
            textView3.setPaddingRelative(textView3.getPaddingStart(), this.mTextView.getPaddingTop(), 0, this.mTextView.getPaddingBottom());
            drawable = drawable2;
        }
        this.mIconView.setImageDrawable(drawable);
        if (TextUtils.isEmpty(charSequence) && !z3) {
            z = false;
        }
        int i4 = z ? 0 : 8;
        this.mTextView.setVisibility(i4);
        if (drawable2 == null) {
            this.mIconView.setVisibility(8);
        } else {
            this.mIconView.setVisibility(i4);
        }
        if (!z) {
            this.mTextView.animate().cancel();
            if (drawable2 != null && (drawable2 instanceof AnimatedVectorDrawable)) {
                ((AnimatedVectorDrawable) drawable2).reset();
            }
            this.mHandler.post(this.mWakeLock.wrap(new AmbientIndicationContainer$$ExternalSyntheticLambda1()));
        } else if (!z2) {
            this.mWakeLock.acquire("AmbientIndication");
            if (drawable2 != null && (drawable2 instanceof AnimatedVectorDrawable)) {
                ((AnimatedVectorDrawable) drawable2).start();
            }
            this.mTextView.setTranslationY(r1.getHeight() / 2);
            this.mTextView.setAlpha(0.0f);
            this.mTextView.animate().alpha(1.0f).translationY(0.0f).setStartDelay(150L).setDuration(100L).setListener(new AnonymousClass2(this, i)).setInterpolator(Interpolators.DECELERATE_QUINT).start();
        } else if (i2 == this.mIndicationTextMode) {
            this.mHandler.post(this.mWakeLock.wrap(new AmbientIndicationContainer$$ExternalSyntheticLambda1()));
        } else if (drawable2 != null && (drawable2 instanceof AnimatedVectorDrawable)) {
            this.mWakeLock.acquire("AmbientIndication");
            ((AnimatedVectorDrawable) drawable2).start();
            this.mWakeLock.release("AmbientIndication");
        }
        updateBottomSpacing$1();
    }
}
