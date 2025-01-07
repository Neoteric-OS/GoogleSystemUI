package com.android.systemui.statusbar.policy;

import android.R;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Trace;
import android.os.UserHandle;
import android.text.Editable;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.util.Property;
import android.view.ContentInfo;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.OnReceiveContentListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import androidx.core.animation.Animator;
import androidx.core.animation.AnimatorListenerAdapter;
import androidx.core.animation.AnimatorSet;
import androidx.core.animation.LinearInterpolator;
import androidx.core.animation.ObjectAnimator;
import androidx.core.animation.ValueAnimator;
import com.android.app.animation.InterpolatorsAndroidX;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.policy.RemoteInputView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class RemoteInputView extends LinearLayout implements View.OnClickListener {
    public static final Object VIEW_TAG = new Object();
    public boolean mColorized;
    public GradientDrawable mContentBackground;
    public Rect mContentBackgroundBounds;
    public LinearLayout mContentView;
    public RemoteInputController mController;
    public ImageView mDelete;
    public ImageView mDeleteBg;
    public RemoteEditText mEditText;
    public final ArrayList mEditTextFocusChangeListeners;
    public final EditorActionHandler mEditorActionHandler;
    public NotificationEntry mEntry;
    public boolean mIsAnimatingAppearance;
    public int mLastBackgroundColor;
    public final ArrayList mOnSendListeners;
    public NotificationContentView$$ExternalSyntheticLambda0 mOnVisibilityChangedListener;
    public ProgressBar mProgressBar;
    public boolean mResetting;
    public ImageButton mSendButton;
    public boolean mSending;
    public ViewRootImpl mTestableViewRootImpl;
    public final SendButtonTextWatcher mTextWatcher;
    public final Object mToken;
    public final UiEventLogger mUiEventLogger;
    public RemoteInputViewControllerImpl mViewController;
    public NotificationViewWrapper mWrapper;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.policy.RemoteInputView$3, reason: invalid class name */
    public final class AnonymousClass3 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass3(RemoteInputView remoteInputView, int i) {
            this.$r8$classId = i;
            this.this$0 = remoteInputView;
        }

        @Override // androidx.core.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            Object obj = this.this$0;
            switch (this.$r8$classId) {
                case 0:
                    ((RemoteInputView) obj).mIsAnimatingAppearance = false;
                    break;
                case 1:
                    Object obj2 = RemoteInputView.VIEW_TAG;
                    ((RemoteInputView) obj).setFocusAnimationScaleY(1.0f);
                    break;
                default:
                    ((View) obj).setAlpha(1.0f);
                    break;
            }
        }

        public AnonymousClass3(View view) {
            this.$r8$classId = 2;
            this.this$0 = view;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EditorActionHandler implements TextView.OnEditorActionListener {
        public EditorActionHandler() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            boolean z = keyEvent == null && (i == 6 || i == 5 || i == 4);
            boolean z2 = keyEvent != null && KeyEvent.isConfirmKey(keyEvent.getKeyCode()) && keyEvent.getAction() == 0;
            if (!z && !z2) {
                return false;
            }
            if (RemoteInputView.this.mEditText.length() > 0 || RemoteInputView.this.mEntry.remoteInputAttachment != null) {
                Iterator it = new ArrayList(RemoteInputView.this.mOnSendListeners).iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next()).run();
                }
            }
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    enum NotificationRemoteInputEvent implements UiEventLogger.UiEventEnum {
        NOTIFICATION_REMOTE_INPUT_OPEN(795),
        NOTIFICATION_REMOTE_INPUT_CLOSE(796),
        NOTIFICATION_REMOTE_INPUT_SEND(797),
        NOTIFICATION_REMOTE_INPUT_FAILURE(798),
        NOTIFICATION_REMOTE_INPUT_ATTACH_IMAGE(825);

        private final int mId;

        NotificationRemoteInputEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class RemoteEditText extends EditText {
        public static final /* synthetic */ int $r8$clinit = 0;
        public InputMethodManager mInputMethodManager;
        public final LightBarController mLightBarController;
        public final RemoteInputView$RemoteEditText$$ExternalSyntheticLambda1 mOnBackInvokedCallback;
        public final RemoteInputView$RemoteEditText$$ExternalSyntheticLambda0 mOnReceiveContentListener;
        public RemoteInputView mRemoteInputView;
        public boolean mShowImeOnInputConnection;
        public final ArraySet mSupportedMimes;
        public UserHandle mUser;

        /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.policy.RemoteInputView$RemoteEditText$$ExternalSyntheticLambda0] */
        /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.statusbar.policy.RemoteInputView$RemoteEditText$$ExternalSyntheticLambda1] */
        public RemoteEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mOnReceiveContentListener = new OnReceiveContentListener() { // from class: com.android.systemui.statusbar.policy.RemoteInputView$RemoteEditText$$ExternalSyntheticLambda0
                @Override // android.view.OnReceiveContentListener
                public final ContentInfo onReceiveContent(View view, ContentInfo contentInfo) {
                    RemoteInputView.RemoteEditText remoteEditText = RemoteInputView.RemoteEditText.this;
                    int i = RemoteInputView.RemoteEditText.$r8$clinit;
                    remoteEditText.getClass();
                    Pair partition = contentInfo.partition(new RemoteInputView$RemoteEditText$$ExternalSyntheticLambda2());
                    ContentInfo contentInfo2 = (ContentInfo) partition.first;
                    ContentInfo contentInfo3 = (ContentInfo) partition.second;
                    if (contentInfo2 != null) {
                        remoteEditText.mRemoteInputView.setAttachment(contentInfo2);
                    }
                    return contentInfo3;
                }
            };
            this.mSupportedMimes = new ArraySet();
            this.mOnBackInvokedCallback = new OnBackInvokedCallback() { // from class: com.android.systemui.statusbar.policy.RemoteInputView$RemoteEditText$$ExternalSyntheticLambda1
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    RemoteInputView.RemoteEditText remoteEditText = RemoteInputView.RemoteEditText.this;
                    int i = RemoteInputView.RemoteEditText.$r8$clinit;
                    remoteEditText.defocusIfNeeded(true);
                }
            };
            this.mLightBarController = (LightBarController) Dependency.sDependency.getDependencyInner(LightBarController.class);
        }

        public final void defocusIfNeeded(boolean z) {
            RemoteInputView remoteInputView;
            RemoteInputView remoteInputView2 = this.mRemoteInputView;
            if ((remoteInputView2 != null && remoteInputView2.mEntry.row.mChangingPosition) || isTemporarilyDetached()) {
                if (!isTemporarilyDetached() || (remoteInputView = this.mRemoteInputView) == null) {
                    return;
                }
                remoteInputView.mEntry.remoteInputText = getText();
                return;
            }
            if (isFocusable() && isEnabled()) {
                setFocusableInTouchMode(false);
                setFocusable(false);
                setCursorVisible(false);
                RemoteInputView remoteInputView3 = this.mRemoteInputView;
                if (remoteInputView3 != null) {
                    remoteInputView3.onDefocus(z, true, null);
                }
                this.mShowImeOnInputConnection = false;
            }
        }

        @Override // android.widget.TextView, android.view.View
        public final void getFocusedRect(Rect rect) {
            super.getFocusedRect(rect);
            int i = ((EditText) this).mScrollY;
            rect.top = i;
            rect.bottom = (((EditText) this).mBottom - ((EditText) this).mTop) + i;
        }

        @Override // android.widget.TextView, android.view.View
        public final boolean onCheckIsTextEditor() {
            RemoteInputView remoteInputView = this.mRemoteInputView;
            if (remoteInputView != null) {
                Object obj = RemoteInputView.VIEW_TAG;
                remoteInputView.getClass();
            }
            return super.onCheckIsTextEditor();
        }

        @Override // android.widget.TextView
        public final void onCommitCompletion(CompletionInfo completionInfo) {
            clearComposingText();
            setText(completionInfo.getText());
            setSelection(getText().length());
        }

        @Override // android.widget.TextView, android.view.View
        public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            Context context;
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            try {
                Context context2 = ((EditText) this).mContext;
                context = context2.createPackageContextAsUser(context2.getPackageName(), 0, this.mUser);
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("RemoteInput", "Unable to create user context:" + e.getMessage(), e);
                context = null;
            }
            if (this.mShowImeOnInputConnection && onCreateInputConnection != null) {
                if (context == null) {
                    context = getContext();
                }
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(InputMethodManager.class);
                this.mInputMethodManager = inputMethodManager;
                if (inputMethodManager != null) {
                    post(new Runnable() { // from class: com.android.systemui.statusbar.policy.RemoteInputView.RemoteEditText.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            RemoteEditText remoteEditText = RemoteEditText.this;
                            remoteEditText.mInputMethodManager.viewClicked(remoteEditText);
                            RemoteEditText remoteEditText2 = RemoteEditText.this;
                            remoteEditText2.mInputMethodManager.showSoftInput(remoteEditText2, 0);
                        }
                    });
                }
            }
            return onCreateInputConnection;
        }

        @Override // android.widget.TextView, android.view.View
        public final void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            RemoteInputView remoteInputView = this.mRemoteInputView;
            if (remoteInputView != null) {
                Object obj = RemoteInputView.VIEW_TAG;
                remoteInputView.getClass();
                Iterator it = new ArrayList(remoteInputView.mEditTextFocusChangeListeners).iterator();
                while (it.hasNext()) {
                    ((View.OnFocusChangeListener) it.next()).onFocusChange(this, z);
                }
            }
            if (!z) {
                defocusIfNeeded(true);
            }
            RemoteInputView remoteInputView2 = this.mRemoteInputView;
            if (remoteInputView2 != null) {
                Object obj2 = RemoteInputView.VIEW_TAG;
                remoteInputView2.getClass();
                LightBarController lightBarController = this.mLightBarController;
                if (lightBarController.mDirectReplying == z) {
                    return;
                }
                lightBarController.mDirectReplying = z;
                lightBarController.reevaluate();
            }
        }

        @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
        public final boolean onKeyDown(int i, KeyEvent keyEvent) {
            if (i == 4) {
                return true;
            }
            return super.onKeyDown(i, keyEvent);
        }

        @Override // android.widget.TextView, android.view.View
        public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
                defocusIfNeeded(true);
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
        public final boolean onKeyUp(int i, KeyEvent keyEvent) {
            if (i != 4) {
                return super.onKeyUp(i, keyEvent);
            }
            defocusIfNeeded(true);
            return true;
        }

        @Override // android.widget.TextView, android.view.View
        public final void onVisibilityChanged(View view, int i) {
            super.onVisibilityChanged(view, i);
            if (isShown()) {
                return;
            }
            defocusIfNeeded(false);
        }

        @Override // android.view.View
        public final boolean requestRectangleOnScreen(Rect rect) {
            RemoteInputView remoteInputView = this.mRemoteInputView;
            RemoteInputController remoteInputController = remoteInputView.mController;
            NotificationEntry notificationEntry = remoteInputView.mEntry;
            NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayoutController.this.mView;
            ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
            if (notificationStackScrollLayout.mForcedScroll == expandableNotificationRow) {
                return true;
            }
            notificationStackScrollLayout.mForcedScroll = expandableNotificationRow;
            notificationStackScrollLayout.updateForcedScroll();
            return true;
        }
    }

    public RemoteInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mToken = new Object();
        this.mOnSendListeners = new ArrayList();
        this.mOnVisibilityChangedListener = null;
        this.mEditTextFocusChangeListeners = new ArrayList();
        this.mIsAnimatingAppearance = false;
        this.mTextWatcher = new SendButtonTextWatcher();
        this.mEditorActionHandler = new EditorActionHandler();
        this.mUiEventLogger = (UiEventLogger) Dependency.sDependency.getDependencyInner(UiEventLogger.class);
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{R.^attr-private.materialColorSurfaceDim});
        this.mLastBackgroundColor = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchFinishTemporaryDetach() {
        if (isAttachedToWindow()) {
            RemoteEditText remoteEditText = this.mEditText;
            attachViewToParent(remoteEditText, 0, remoteEditText.getLayoutParams());
        } else {
            removeDetachedView(this.mEditText, false);
        }
        super.dispatchFinishTemporaryDetach();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchStartTemporaryDetach() {
        super.dispatchStartTemporaryDetach();
        int indexOfChild = indexOfChild(this.mEditText);
        if (indexOfChild != -1) {
            detachViewFromParent(indexOfChild);
        }
    }

    public final void focus() {
        this.mUiEventLogger.logWithInstanceId(NotificationRemoteInputEvent.NOTIFICATION_REMOTE_INPUT_OPEN, this.mEntry.mSbn.getUid(), this.mEntry.mSbn.getPackageName(), this.mEntry.mSbn.getInstanceId());
        setVisibility(0);
        NotificationViewWrapper notificationViewWrapper = this.mWrapper;
        if (notificationViewWrapper != null) {
            notificationViewWrapper.setRemoteInputVisible(true);
        }
        RemoteEditText remoteEditText = this.mEditText;
        remoteEditText.setFocusableInTouchMode(true);
        remoteEditText.setFocusable(true);
        remoteEditText.setCursorVisible(true);
        remoteEditText.requestFocus();
        RemoteEditText remoteEditText2 = this.mEditText;
        remoteEditText2.mShowImeOnInputConnection = true;
        remoteEditText2.setText(this.mEntry.remoteInputText);
        RemoteEditText remoteEditText3 = this.mEditText;
        remoteEditText3.setSelection(remoteEditText3.length());
        this.mEditText.requestFocus();
        RemoteInputController remoteInputController = this.mController;
        NotificationEntry notificationEntry = this.mEntry;
        Object obj = this.mToken;
        remoteInputController.getClass();
        Objects.requireNonNull(notificationEntry);
        Objects.requireNonNull(obj);
        boolean pruneWeakThenRemoveAndContains = remoteInputController.pruneWeakThenRemoveAndContains(notificationEntry, null, null);
        boolean pruneWeakThenRemoveAndContains2 = remoteInputController.pruneWeakThenRemoveAndContains(notificationEntry, null, obj);
        remoteInputController.mLogger.logAddRemoteInput(notificationEntry.mKey, notificationEntry.getNotificationStyle(), pruneWeakThenRemoveAndContains, pruneWeakThenRemoveAndContains2);
        if (!pruneWeakThenRemoveAndContains2) {
            remoteInputController.mOpen.add(new Pair(new WeakReference(notificationEntry), obj));
        }
        if (!pruneWeakThenRemoveAndContains) {
            remoteInputController.apply(notificationEntry);
        }
        setAttachment(this.mEntry.remoteInputAttachment);
        updateSendButton();
    }

    public final ViewRootImpl getViewRootImpl() {
        ViewRootImpl viewRootImpl = this.mTestableViewRootImpl;
        return viewRootImpl != null ? viewRootImpl : super.getViewRootImpl();
    }

    public final boolean isActive() {
        return this.mEditText.isFocused() && this.mEditText.isEnabled();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        setEditTextReferenceToSelf();
        this.mEditText.setOnEditorActionListener(this.mEditorActionHandler);
        this.mEditText.addTextChangedListener(this.mTextWatcher);
        if (this.mEntry.row.mChangingPosition && getVisibility() == 0 && this.mEditText.isFocusable()) {
            this.mEditText.requestFocus();
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (view == this.mSendButton) {
            Iterator it = new ArrayList(this.mOnSendListeners).iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
    }

    public void onDefocus(boolean z, boolean z2, final Runnable runnable) {
        int i = 1;
        this.mController.removeRemoteInput(this.mEntry, this.mToken, "RemoteInputView#onDefocus");
        this.mEntry.remoteInputText = this.mEditText.getText();
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (!z || viewGroup == null) {
            setVisibility(8);
            if (runnable != null) {
                runnable.run();
            }
            NotificationViewWrapper notificationViewWrapper = this.mWrapper;
            if (notificationViewWrapper != null) {
                notificationViewWrapper.setRemoteInputVisible(false);
            }
        } else {
            final ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
            ViewGroup viewGroup3 = (ViewGroup) getParent();
            View findViewById = viewGroup3 == null ? null : viewGroup3.findViewById(R.id.alerted_icon);
            int height = (findViewById != null ? findViewById.getHeight() : 0) - getHeight();
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                layoutParams2.topMargin = height;
                setLayoutParams(layoutParams2);
            }
            if (viewGroup2 != null) {
                viewGroup2.setClipChildren(false);
            }
            AnimatorSet animatorSet = new AnimatorSet();
            Property property = View.ALPHA;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, property, 1.0f, 0.0f);
            ofFloat.setDuration(83L);
            ofFloat.setStartDelay(120L);
            LinearInterpolator linearInterpolator = InterpolatorsAndroidX.LINEAR;
            ofFloat.setInterpolator(linearInterpolator);
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(1.0f, 0.5f);
            ofFloat2.addUpdateListener(new RemoteInputView$$ExternalSyntheticLambda1(this, ofFloat2, 1));
            ofFloat2.setDuration(360L);
            ofFloat2.setInterpolator(InterpolatorsAndroidX.FAST_OUT_SLOW_IN);
            ofFloat2.addListener(new AnonymousClass3(this, i));
            if (findViewById == null) {
                animatorSet.playTogether(ofFloat, ofFloat2);
            } else {
                findViewById.forceHasOverlappingRendering(false);
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(findViewById, property, 0.0f, 1.0f);
                ofFloat3.setDuration(83L);
                ofFloat3.setInterpolator(linearInterpolator);
                ofFloat3.setStartDelay(180L);
                animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3);
            }
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.policy.RemoteInputView.2
                @Override // androidx.core.animation.AnimatorListenerAdapter, androidx.core.animation.Animator.AnimatorListener
                public final void onAnimationEnd$1(Animator animator) {
                    Object obj = RemoteInputView.VIEW_TAG;
                    RemoteInputView remoteInputView = RemoteInputView.this;
                    ViewGroup.LayoutParams layoutParams3 = remoteInputView.getLayoutParams();
                    if (layoutParams3 instanceof FrameLayout.LayoutParams) {
                        FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) layoutParams3;
                        layoutParams4.topMargin = 0;
                        remoteInputView.setLayoutParams(layoutParams4);
                    }
                    ViewGroup viewGroup4 = viewGroup2;
                    if (viewGroup4 != null) {
                        viewGroup4.setClipChildren(true);
                    }
                    remoteInputView.setVisibility(8);
                    remoteInputView.setAlpha(1.0f);
                    NotificationViewWrapper notificationViewWrapper2 = remoteInputView.mWrapper;
                    if (notificationViewWrapper2 != null) {
                        notificationViewWrapper2.setRemoteInputVisible(false);
                    }
                    Runnable runnable2 = runnable;
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            });
            if (findViewById != null) {
                findViewById.setAlpha(0.0f);
            }
            animatorSet.start();
        }
        if (z2) {
            this.mUiEventLogger.logWithInstanceId(NotificationRemoteInputEvent.NOTIFICATION_REMOTE_INPUT_CLOSE, this.mEntry.mSbn.getUid(), this.mEntry.mSbn.getPackageName(), this.mEntry.mSbn.getInstanceId());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mEditText.removeTextChangedListener(this.mTextWatcher);
        this.mEditText.setOnEditorActionListener(null);
        this.mEditText.mRemoteInputView = null;
        if (this.mEntry.row.mChangingPosition || isTemporarilyDetached()) {
            return;
        }
        NotificationEntry notificationEntry = this.mEntry;
        notificationEntry.mRemoteEditImeAnimatingAway = false;
        this.mController.removeRemoteInput(notificationEntry, this.mToken, "RemoteInputView#onDetachedFromWindow");
        RemoteInputController remoteInputController = this.mController;
        String str = this.mEntry.mKey;
        Object obj = this.mToken;
        remoteInputController.getClass();
        Objects.requireNonNull(str);
        if (obj == null || remoteInputController.mSpinning.get(str) == obj) {
            remoteInputController.mSpinning.remove(str);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mProgressBar = (ProgressBar) findViewById(com.android.wm.shell.R.id.remote_input_progress);
        ImageButton imageButton = (ImageButton) findViewById(com.android.wm.shell.R.id.remote_input_send);
        this.mSendButton = imageButton;
        imageButton.setOnClickListener(this);
        this.mContentBackground = (GradientDrawable) ((LinearLayout) this).mContext.getDrawable(com.android.wm.shell.R.drawable.remote_input_view_text_bg).mutate();
        this.mDelete = (ImageView) findViewById(com.android.wm.shell.R.id.remote_input_delete);
        ImageView imageView = (ImageView) findViewById(com.android.wm.shell.R.id.remote_input_delete_bg);
        this.mDeleteBg = imageView;
        BlendMode blendMode = BlendMode.SRC_IN;
        imageView.setImageTintBlendMode(blendMode);
        this.mDelete.setImageTintBlendMode(blendMode);
        this.mDelete.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.statusbar.policy.RemoteInputView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RemoteInputView remoteInputView = RemoteInputView.this;
                Object obj = RemoteInputView.VIEW_TAG;
                remoteInputView.setAttachment(null);
            }
        });
        LinearLayout linearLayout = (LinearLayout) findViewById(com.android.wm.shell.R.id.remote_input_content);
        this.mContentView = linearLayout;
        linearLayout.setBackground(this.mContentBackground);
        RemoteEditText remoteEditText = (RemoteEditText) findViewById(com.android.wm.shell.R.id.remote_input_text);
        this.mEditText = remoteEditText;
        remoteEditText.setFocusableInTouchMode(false);
        remoteEditText.setFocusable(false);
        remoteEditText.setCursorVisible(false);
        this.mEditText.setEnabled(false);
        this.mEditText.setWindowInsetsAnimationCallback(new WindowInsetsAnimation.Callback() { // from class: com.android.systemui.statusbar.policy.RemoteInputView.1
            @Override // android.view.WindowInsetsAnimation.Callback
            public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                super.onEnd(windowInsetsAnimation);
                if (windowInsetsAnimation.getTypeMask() == WindowInsets.Type.ime()) {
                    RemoteInputView remoteInputView = RemoteInputView.this;
                    boolean z = false;
                    remoteInputView.mEntry.mRemoteEditImeAnimatingAway = false;
                    WindowInsets rootWindowInsets = remoteInputView.mEditText.getRootWindowInsets();
                    if (rootWindowInsets == null) {
                        Log.w("RemoteInput", "onEnd called on detached view", new Exception());
                    }
                    NotificationEntry notificationEntry = RemoteInputView.this.mEntry;
                    if (rootWindowInsets != null && rootWindowInsets.isVisible(WindowInsets.Type.ime())) {
                        z = true;
                    }
                    notificationEntry.mRemoteEditImeVisible = z;
                    RemoteInputView remoteInputView2 = RemoteInputView.this;
                    NotificationEntry notificationEntry2 = remoteInputView2.mEntry;
                    if (notificationEntry2.mRemoteEditImeVisible || remoteInputView2.mEditText.mShowImeOnInputConnection) {
                        return;
                    }
                    remoteInputView2.mController.removeRemoteInput(notificationEntry2, remoteInputView2.mToken, "RemoteInputView$WindowInsetAnimation#onEnd");
                }
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
                return windowInsets;
            }
        });
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
            notificationStackScrollLayoutController.mView.cancelLongPress();
            notificationStackScrollLayoutController.mView.mDisallowDismissInThisMotion = true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        setPivotY(getMeasuredHeight());
        Rect rect = this.mContentBackgroundBounds;
        if (rect != null) {
            this.mContentBackground.setBounds(rect);
        }
    }

    public final void onNotificationUpdateOrReset() {
        NotificationViewWrapper notificationViewWrapper;
        if (this.mProgressBar.getVisibility() == 0) {
            this.mProgressBar.setVisibility(4);
            this.mResetting = true;
            this.mSending = false;
            RemoteInputController remoteInputController = this.mController;
            String str = this.mEntry.mKey;
            Object obj = this.mToken;
            remoteInputController.getClass();
            Objects.requireNonNull(str);
            if (obj == null || remoteInputController.mSpinning.get(str) == obj) {
                remoteInputController.mSpinning.remove(str);
            }
            onDefocus(true, false, new Runnable() { // from class: com.android.systemui.statusbar.policy.RemoteInputView$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RemoteInputView remoteInputView = RemoteInputView.this;
                    remoteInputView.mEntry.remoteInputTextWhenReset = SpannedString.valueOf(remoteInputView.mEditText.getText());
                    remoteInputView.mEditText.getText().clear();
                    remoteInputView.mEditText.setEnabled(remoteInputView.isAggregatedVisible());
                    remoteInputView.mSendButton.setVisibility(0);
                    remoteInputView.updateSendButton();
                    remoteInputView.setAttachment(null);
                    remoteInputView.mResetting = false;
                }
            });
        }
        if (!isActive() || (notificationViewWrapper = this.mWrapper) == null) {
            return;
        }
        notificationViewWrapper.setRemoteInputVisible(true);
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        if (this.mResetting && view == this.mEditText) {
            return false;
        }
        return super.onRequestSendAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.View
    public final void onVisibilityAggregated(boolean z) {
        if (z) {
            ViewRootImpl viewRootImpl = getViewRootImpl();
            if (viewRootImpl != null) {
                viewRootImpl.getOnBackInvokedDispatcher().registerOnBackInvokedCallback(1000000, this.mEditText.mOnBackInvokedCallback);
            }
        } else {
            ViewRootImpl viewRootImpl2 = getViewRootImpl();
            if (viewRootImpl2 != null) {
                viewRootImpl2.getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.mEditText.mOnBackInvokedCallback);
            }
        }
        super.onVisibilityAggregated(z);
        this.mEditText.setEnabled(z && !this.mSending);
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this) {
            NotificationContentView$$ExternalSyntheticLambda0 notificationContentView$$ExternalSyntheticLambda0 = this.mOnVisibilityChangedListener;
            if (notificationContentView$$ExternalSyntheticLambda0 != null) {
                notificationContentView$$ExternalSyntheticLambda0.accept(Boolean.valueOf(i == 0));
            }
            if (i == 0 || this.mController.isRemoteInputActive$1()) {
                return;
            }
            RemoteEditText remoteEditText = this.mEditText;
            int i2 = RemoteEditText.$r8$clinit;
            remoteEditText.getClass();
            Trace.beginSection("RemoteEditText#hideIme");
            WindowInsetsController windowInsetsController = remoteEditText.getWindowInsetsController();
            if (windowInsetsController != null) {
                windowInsetsController.hide(WindowInsets.Type.ime());
            }
            Trace.endSection();
        }
    }

    public void setAttachment(ContentInfo contentInfo) {
        ContentInfo contentInfo2 = this.mEntry.remoteInputAttachment;
        if (contentInfo2 != null && contentInfo2 != contentInfo) {
            contentInfo2.releasePermissions();
        }
        NotificationEntry notificationEntry = this.mEntry;
        notificationEntry.remoteInputAttachment = contentInfo;
        if (contentInfo != null) {
            notificationEntry.remoteInputUri = contentInfo.getClip().getItemAt(0).getUri();
            this.mEntry.remoteInputMimeType = contentInfo.getClip().getDescription().getMimeType(0);
        }
        View findViewById = findViewById(com.android.wm.shell.R.id.remote_input_content_container);
        ImageView imageView = (ImageView) findViewById(com.android.wm.shell.R.id.remote_input_attachment_image);
        imageView.setImageDrawable(null);
        if (contentInfo == null) {
            findViewById.setVisibility(8);
            return;
        }
        imageView.setImageURI(contentInfo.getClip().getItemAt(0).getUri());
        if (imageView.getDrawable() == null) {
            findViewById.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
            this.mUiEventLogger.logWithInstanceId(NotificationRemoteInputEvent.NOTIFICATION_REMOTE_INPUT_ATTACH_IMAGE, this.mEntry.mSbn.getUid(), this.mEntry.mSbn.getPackageName(), this.mEntry.mSbn.getInstanceId());
        }
        updateSendButton();
    }

    public final void setBackgroundTintColor(int i, boolean z) {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        int defaultColor;
        int i2;
        int i3;
        int i4;
        if (z == this.mColorized && i == this.mLastBackgroundColor) {
            return;
        }
        this.mColorized = z;
        this.mLastBackgroundColor = i;
        int dimensionPixelSize = z ? ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.remote_input_view_text_stroke) : 0;
        if (z) {
            boolean isColorDark = ContrastColorUtil.isColorDark(i);
            int i5 = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
            i2 = isColorDark ? -1 : -16777216;
            if (!isColorDark) {
                i5 = -1;
            }
            colorStateList = new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{ColorUtils.setAlphaComponent(i2, 77), i2});
            colorStateList2 = new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{ColorUtils.setAlphaComponent(i2, 153), i2});
            i4 = ColorUtils.setAlphaComponent(i2, 153);
            defaultColor = i5;
            i3 = i;
        } else {
            colorStateList = ((LinearLayout) this).mContext.getColorStateList(com.android.wm.shell.R.color.remote_input_send);
            colorStateList2 = ((LinearLayout) this).mContext.getColorStateList(com.android.wm.shell.R.color.remote_input_text);
            int color = ((LinearLayout) this).mContext.getColor(com.android.wm.shell.R.color.remote_input_hint);
            defaultColor = colorStateList2.getDefaultColor();
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{R.^attr-private.materialColorSurfaceDim, R.^attr-private.materialColorSurfaceVariant});
            try {
                int color2 = obtainStyledAttributes.getColor(0, i);
                int color3 = obtainStyledAttributes.getColor(1, -7829368);
                obtainStyledAttributes.close();
                i2 = color3;
                i3 = color2;
                i4 = color;
            } catch (Throwable th) {
                if (obtainStyledAttributes != null) {
                    try {
                        obtainStyledAttributes.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        this.mEditText.setTextColor(colorStateList2);
        this.mEditText.setHintTextColor(i4);
        if (this.mEditText.getTextCursorDrawable() != null) {
            this.mEditText.getTextCursorDrawable().setColorFilter(colorStateList.getDefaultColor(), PorterDuff.Mode.SRC_IN);
        }
        this.mContentBackground.setColor(i3);
        this.mContentBackground.setStroke(dimensionPixelSize, colorStateList);
        this.mDelete.setImageTintList(ColorStateList.valueOf(defaultColor));
        this.mDeleteBg.setImageTintList(ColorStateList.valueOf(i2));
        this.mSendButton.setImageTintList(colorStateList);
        this.mProgressBar.setProgressTintList(colorStateList);
        this.mProgressBar.setIndeterminateTintList(colorStateList);
        this.mProgressBar.setSecondaryProgressTintList(colorStateList);
        setBackgroundColor(i);
    }

    public void setEditTextReferenceToSelf() {
        this.mEditText.mRemoteInputView = this;
    }

    public final void setFocusAnimationScaleY(float f) {
        int height = (int) ((1.0f - f) * 0.5f * this.mContentView.getHeight());
        Rect rect = new Rect(0, height, this.mContentView.getWidth(), this.mContentView.getHeight() - height);
        this.mContentBackground.setBounds(rect);
        this.mContentView.setBackground(this.mContentBackground);
        if (f == 1.0f) {
            this.mContentBackgroundBounds = null;
        } else {
            this.mContentBackgroundBounds = rect;
        }
        setTranslationY(height);
    }

    public final void setSupportedMimeTypes(Collection collection) {
        String[] strArr;
        RemoteInputView$RemoteEditText$$ExternalSyntheticLambda0 remoteInputView$RemoteEditText$$ExternalSyntheticLambda0;
        RemoteEditText remoteEditText = this.mEditText;
        if (collection != null) {
            remoteEditText.getClass();
            if (!collection.isEmpty()) {
                strArr = (String[]) collection.toArray(new String[0]);
                remoteInputView$RemoteEditText$$ExternalSyntheticLambda0 = remoteEditText.mOnReceiveContentListener;
                remoteEditText.setOnReceiveContentListener(strArr, remoteInputView$RemoteEditText$$ExternalSyntheticLambda0);
                remoteEditText.mSupportedMimes.clear();
                remoteEditText.mSupportedMimes.addAll(collection);
            }
        }
        strArr = null;
        remoteInputView$RemoteEditText$$ExternalSyntheticLambda0 = null;
        remoteEditText.setOnReceiveContentListener(strArr, remoteInputView$RemoteEditText$$ExternalSyntheticLambda0);
        remoteEditText.mSupportedMimes.clear();
        remoteEditText.mSupportedMimes.addAll(collection);
    }

    public void setViewRootImpl(ViewRootImpl viewRootImpl) {
        this.mTestableViewRootImpl = viewRootImpl;
    }

    public final void updateSendButton() {
        this.mSendButton.setEnabled((this.mEditText.length() == 0 && this.mEntry.remoteInputAttachment == null) ? false : true);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SendButtonTextWatcher implements TextWatcher {
        public SendButtonTextWatcher() {
        }

        @Override // android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            RemoteInputView remoteInputView = RemoteInputView.this;
            Object obj = RemoteInputView.VIEW_TAG;
            remoteInputView.updateSendButton();
        }

        @Override // android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }
}
