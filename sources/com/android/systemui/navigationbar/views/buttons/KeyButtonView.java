package com.android.systemui.navigationbar.views.buttons;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.hardware.input.InputManagerGlobal;
import android.media.AudioManager;
import android.metrics.LogMaker;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.logging.UiEventLoggerImpl;
import com.android.systemui.Dependency;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.res.R$styleable;
import com.android.systemui.shared.navigationbar.KeyButtonRipple;
import com.android.wm.shell.R;
import com.google.android.systemui.assist.AssistManagerGoogle;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class KeyButtonView extends ImageView implements ButtonInterface {
    public final AudioManager mAudioManager;
    public final AnonymousClass1 mCheckLongPress;
    public int mCode;
    public final int mContentDescriptionRes;
    public float mDarkIntensity;
    public long mDownTime;
    public boolean mGestureAborted;
    public boolean mHasOvalBg;
    public final InputManagerGlobal mInputManagerGlobal;
    boolean mLongClicked;
    public final MetricsLogger mMetricsLogger;
    public NavBarButtonClickLogger mNavBarButtonClickLogger;
    public View.OnClickListener mOnClickListener;
    public final Paint mOvalBgPaint;
    public final OverviewProxyService mOverviewProxyService;
    public final boolean mPlaySounds;
    public final KeyButtonRipple mRipple;
    public int mTouchDownX;
    public int mTouchDownY;
    public final UiEventLogger mUiEventLogger;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum NavBarButtonEvent implements UiEventLogger.UiEventEnum {
        NAVBAR_HOME_BUTTON_TAP(533),
        NAVBAR_BACK_BUTTON_TAP(534),
        NAVBAR_OVERVIEW_BUTTON_TAP(535),
        NAVBAR_IME_SWITCHER_BUTTON_TAP(923),
        NAVBAR_HOME_BUTTON_LONGPRESS(536),
        NAVBAR_BACK_BUTTON_LONGPRESS(537),
        NAVBAR_OVERVIEW_BUTTON_LONGPRESS(538),
        NAVBAR_IME_SWITCHER_BUTTON_LONGPRESS(1799),
        NONE(0);

        private final int mId;

        NavBarButtonEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    public KeyButtonView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public final void abortCurrentGesture() {
        Log.d("b/63783866", "KeyButtonView.abortCurrentGesture");
        if (this.mCode != 0) {
            sendEvent(1, 32);
        }
        if (this.mCode == 3 && ((AssistManagerGoogle) Dependency.sDependency.getDependencyInner(AssistManagerGoogle.class)).shouldOverrideAssist(5)) {
            this.mRipple.mSpeedUpNextFade = true;
        }
        setPressed(false);
        this.mRipple.mHandler.removeCallbacksAndMessages(null);
        this.mGestureAborted = true;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        if (this.mHasOvalBg) {
            float min = Math.min(getWidth(), getHeight());
            canvas.drawOval(0.0f, 0.0f, min, min, this.mOvalBgPaint);
        }
        super.draw(canvas);
    }

    @Override // android.view.View
    public final boolean isClickable() {
        return this.mCode != 0 || super.isClickable();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = this.mContentDescriptionRes;
        if (i != 0) {
            setContentDescription(((ImageView) this).mContext.getString(i));
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (isClickable()) {
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, null));
            if (isLongClickable()) {
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(32, getId() == R.id.ime_switcher ? getContext().getText(android.R.string.js_dialog_before_unload_title) : null));
            }
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        View.OnClickListener onClickListener;
        boolean shouldShowSwipeUpUI = this.mOverviewProxyService.shouldShowSwipeUpUI();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mGestureAborted = false;
        }
        if (this.mGestureAborted) {
            setPressed(false);
            return false;
        }
        if (action == 0) {
            this.mDownTime = SystemClock.uptimeMillis();
            this.mLongClicked = false;
            setPressed(true);
            this.mTouchDownX = (int) motionEvent.getX();
            this.mTouchDownY = (int) motionEvent.getY();
            if (this.mCode != 0) {
                sendEvent(0, 0, this.mDownTime);
            } else {
                performHapticFeedback(1);
            }
            if (!shouldShowSwipeUpUI) {
                playSoundEffect(0);
            }
            removeCallbacks(this.mCheckLongPress);
            postDelayed(this.mCheckLongPress, ViewConfiguration.getLongPressTimeout());
        } else if (action == 1) {
            boolean z = isPressed() && !this.mLongClicked;
            setPressed(false);
            boolean z2 = SystemClock.uptimeMillis() - this.mDownTime > 150;
            if (shouldShowSwipeUpUI) {
                if (z) {
                    performHapticFeedback(1);
                    playSoundEffect(0);
                }
            } else if (z2 && !this.mLongClicked) {
                performHapticFeedback(8);
            }
            if (this.mCode != 0) {
                if (z) {
                    sendEvent(1, 0);
                    sendAccessibilityEvent(1);
                } else {
                    sendEvent(1, 32);
                }
            } else if (z && (onClickListener = this.mOnClickListener) != null) {
                onClickListener.onClick(this);
                sendAccessibilityEvent(1);
            }
            removeCallbacks(this.mCheckLongPress);
        } else if (action == 2) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            float scaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop() * 3.0f;
            if (Math.abs(x - this.mTouchDownX) > scaledTouchSlop || Math.abs(y - this.mTouchDownY) > scaledTouchSlop) {
                setPressed(false);
                removeCallbacks(this.mCheckLongPress);
            }
        } else if (action == 3) {
            setPressed(false);
            if (this.mCode != 0) {
                sendEvent(1, 32);
            }
            removeCallbacks(this.mCheckLongPress);
        }
        return true;
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i != 0) {
            jumpDrawablesToCurrentState();
        }
    }

    public final boolean performAccessibilityActionInternal(int i, Bundle bundle) {
        if (i == 16 && this.mCode != 0) {
            sendEvent(0, 0, SystemClock.uptimeMillis());
            sendEvent(1, 0);
            sendAccessibilityEvent(1);
            playSoundEffect(0);
            return true;
        }
        if (i != 32 || this.mCode == 0) {
            return super.performAccessibilityActionInternal(i, bundle);
        }
        sendEvent(0, 128);
        sendEvent(1, 0);
        sendAccessibilityEvent(2);
        return true;
    }

    @Override // android.view.View
    public final void playSoundEffect(int i) {
        if (this.mPlaySounds) {
            this.mAudioManager.playSoundEffect(i, ActivityManager.getCurrentUser());
        }
    }

    public final void sendEvent(int i, int i2) {
        sendEvent(i, i2, SystemClock.uptimeMillis());
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public final void setDarkIntensity(float f) {
        this.mDarkIntensity = f;
        Drawable drawable = getDrawable();
        if (drawable != null) {
            ((KeyButtonDrawable) drawable).setDarkIntensity(f);
            invalidate();
        }
        KeyButtonRipple keyButtonRipple = this.mRipple;
        keyButtonRipple.getClass();
        keyButtonRipple.mDark = f >= 0.5f;
    }

    @Override // android.widget.ImageView, com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public final void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (drawable == null) {
            return;
        }
        KeyButtonDrawable keyButtonDrawable = (KeyButtonDrawable) drawable;
        keyButtonDrawable.setDarkIntensity(this.mDarkIntensity);
        keyButtonDrawable.mState.getClass();
        this.mHasOvalBg = false;
        KeyButtonRipple keyButtonRipple = this.mRipple;
        keyButtonDrawable.mState.getClass();
        keyButtonRipple.mType = KeyButtonRipple.Type.ROUNDED_RECT;
    }

    @Override // android.view.View
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        this.mOnClickListener = onClickListener;
    }

    public KeyButtonView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, InputManagerGlobal.getInstance(), new UiEventLoggerImpl());
    }

    public final void sendEvent(int i, int i2, long j) {
        NavBarButtonEvent navBarButtonEvent;
        this.mMetricsLogger.write(new LogMaker(931).setType(4).setSubtype(this.mCode).addTaggedData(933, Integer.valueOf(i)).addTaggedData(932, Integer.valueOf(i2)));
        int i3 = i2 & 128;
        boolean z = i3 != 0;
        NavBarButtonEvent navBarButtonEvent2 = NavBarButtonEvent.NONE;
        if ((i != 1 || !this.mLongClicked) && ((i != 0 || z) && (i2 & 32) == 0)) {
            int i4 = this.mCode;
            if (i4 == 3) {
                navBarButtonEvent = z ? NavBarButtonEvent.NAVBAR_HOME_BUTTON_LONGPRESS : NavBarButtonEvent.NAVBAR_HOME_BUTTON_TAP;
                NavBarButtonClickLogger navBarButtonClickLogger = this.mNavBarButtonClickLogger;
                if (navBarButtonClickLogger != null) {
                    LogLevel logLevel = LogLevel.DEBUG;
                    NavBarButtonClickLogger$logHomeButtonClick$2 navBarButtonClickLogger$logHomeButtonClick$2 = new Function1() { // from class: com.android.systemui.navigationbar.views.buttons.NavBarButtonClickLogger$logHomeButtonClick$2
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            return "Home Button Triggered";
                        }
                    };
                    LogBuffer logBuffer = navBarButtonClickLogger.buffer;
                    logBuffer.commit(logBuffer.obtain("NavBarButtonClick", logLevel, navBarButtonClickLogger$logHomeButtonClick$2, null));
                }
            } else if (i4 != 4) {
                navBarButtonEvent = i4 != 187 ? navBarButtonEvent2 : z ? NavBarButtonEvent.NAVBAR_OVERVIEW_BUTTON_LONGPRESS : NavBarButtonEvent.NAVBAR_OVERVIEW_BUTTON_TAP;
            } else {
                navBarButtonEvent = z ? NavBarButtonEvent.NAVBAR_BACK_BUTTON_LONGPRESS : NavBarButtonEvent.NAVBAR_BACK_BUTTON_TAP;
                NavBarButtonClickLogger navBarButtonClickLogger2 = this.mNavBarButtonClickLogger;
                if (navBarButtonClickLogger2 != null) {
                    LogLevel logLevel2 = LogLevel.DEBUG;
                    NavBarButtonClickLogger$logBackButtonClick$2 navBarButtonClickLogger$logBackButtonClick$2 = new Function1() { // from class: com.android.systemui.navigationbar.views.buttons.NavBarButtonClickLogger$logBackButtonClick$2
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            return "Back Button Triggered";
                        }
                    };
                    LogBuffer logBuffer2 = navBarButtonClickLogger2.buffer;
                    logBuffer2.commit(logBuffer2.obtain("NavBarButtonClick", logLevel2, navBarButtonClickLogger$logBackButtonClick$2, null));
                }
            }
            if (navBarButtonEvent != navBarButtonEvent2) {
                this.mUiEventLogger.log(navBarButtonEvent);
            }
        }
        if (this.mCode == 4 && i2 != 128) {
            Log.i("KeyButtonView", "Back button event: " + KeyEvent.actionToString(i));
        }
        KeyEvent keyEvent = new KeyEvent(this.mDownTime, j, i, this.mCode, i3 != 0 ? 1 : 0, 0, -1, 0, i2 | 72, 257);
        int displayId = getDisplay() != null ? getDisplay().getDisplayId() : -1;
        if (displayId != -1) {
            keyEvent.setDisplayId(displayId);
        }
        this.mInputManagerGlobal.injectInputEvent(keyEvent, 0);
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [com.android.systemui.navigationbar.views.buttons.KeyButtonView$1] */
    public KeyButtonView(Context context, AttributeSet attributeSet, int i, InputManagerGlobal inputManagerGlobal, UiEventLogger uiEventLogger) {
        super(context, attributeSet);
        this.mMetricsLogger = (MetricsLogger) Dependency.sDependency.getDependencyInner(MetricsLogger.class);
        this.mOvalBgPaint = new Paint(3);
        this.mHasOvalBg = false;
        this.mCheckLongPress = new Runnable() { // from class: com.android.systemui.navigationbar.views.buttons.KeyButtonView.1
            @Override // java.lang.Runnable
            public final void run() {
                if (KeyButtonView.this.isPressed()) {
                    if (KeyButtonView.this.isLongClickable()) {
                        KeyButtonView.this.performLongClick();
                        KeyButtonView.this.mLongClicked = true;
                        return;
                    }
                    KeyButtonView keyButtonView = KeyButtonView.this;
                    if (keyButtonView.mCode != 0) {
                        keyButtonView.sendEvent(0, 128);
                        KeyButtonView.this.sendAccessibilityEvent(2);
                    }
                    KeyButtonView.this.mLongClicked = true;
                }
            }
        };
        this.mUiEventLogger = uiEventLogger;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.KeyButtonView, i, 0);
        this.mCode = obtainStyledAttributes.getInteger(1, 0);
        this.mPlaySounds = obtainStyledAttributes.getBoolean(2, true);
        TypedValue typedValue = new TypedValue();
        if (obtainStyledAttributes.getValue(0, typedValue)) {
            this.mContentDescriptionRes = typedValue.resourceId;
        }
        obtainStyledAttributes.recycle();
        setClickable(true);
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        KeyButtonRipple keyButtonRipple = new KeyButtonRipple(context, this);
        this.mRipple = keyButtonRipple;
        this.mOverviewProxyService = (OverviewProxyService) Dependency.sDependency.getDependencyInner(OverviewProxyService.class);
        this.mInputManagerGlobal = inputManagerGlobal;
        setBackground(keyButtonRipple);
        setWillNotDraw(false);
        forceHasOverlappingRendering(false);
    }

    @Override // com.android.systemui.navigationbar.views.buttons.ButtonInterface
    public final void setVertical(boolean z) {
    }
}
