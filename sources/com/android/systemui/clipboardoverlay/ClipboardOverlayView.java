package com.android.systemui.clipboardoverlay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Icon;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView;
import com.android.systemui.screenshot.DraggableConstraintLayout$2;
import com.android.systemui.screenshot.DraggableConstraintLayout$SwipeDismissCallbacks;
import com.android.systemui.screenshot.DraggableConstraintLayout$SwipeDismissHandler;
import com.android.systemui.screenshot.FloatingWindowUtil;
import com.android.systemui.screenshot.ui.binder.ActionButtonViewBinder;
import com.android.systemui.screenshot.ui.viewmodel.ActionButtonAppearance;
import com.android.systemui.screenshot.ui.viewmodel.ActionButtonViewModel;
import com.android.wm.shell.R;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ClipboardOverlayView extends ConstraintLayout implements ViewTreeObserver.OnComputeInternalInsetsListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final AccessibilityManager mAccessibilityManager;
    public final ActionButtonViewBinder mActionButtonViewBinder;
    public final ArrayList mActionChips;
    public LinearLayout mActionContainer;
    public View mActionContainerBackground;
    public View mActionsContainer;
    public DraggableConstraintLayout$SwipeDismissCallbacks mCallbacks;
    public ClipboardOverlayCallbacks mClipboardCallbacks;
    public View mClipboardPreview;
    public View mDismissButton;
    public final DisplayMetrics mDisplayMetrics;
    public final DisplayMetrics mDisplayMetrics$1;
    public TextView mHiddenPreview;
    public ImageView mImagePreview;
    public LinearLayout mMinimizedPreview;
    public View mPreviewBorder;
    public View mRemoteCopyChip;
    public View mShareChip;
    public final GestureDetector mSwipeDetector;
    public final DraggableConstraintLayout$SwipeDismissHandler mSwipeDismissHandler;
    public TextView mTextPreview;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.clipboardoverlay.ClipboardOverlayView$3, reason: invalid class name */
    public final class AnonymousClass3 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ ClipboardOverlayView this$0;

        public /* synthetic */ AnonymousClass3(ClipboardOverlayView clipboardOverlayView, int i) {
            this.$r8$classId = i;
            this.this$0 = clipboardOverlayView;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    super.onAnimationEnd(animator);
                    this.this$0.mMinimizedPreview.setVisibility(8);
                    this.this$0.mMinimizedPreview.setAlpha(1.0f);
                    break;
                default:
                    super.onAnimationEnd(animator);
                    this.this$0.setAlpha(1.0f);
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ClipboardOverlayCallbacks extends DraggableConstraintLayout$SwipeDismissCallbacks {
        void onDismissButtonTapped();

        void onMinimizedViewTapped();

        void onPreviewTapped();

        void onRemoteCopyButtonTapped();

        void onShareButtonTapped();
    }

    public ClipboardOverlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mDisplayMetrics$1 = displayMetrics;
        ((ViewGroup) this).mContext.getDisplay().getRealMetrics(displayMetrics);
        DraggableConstraintLayout$SwipeDismissHandler draggableConstraintLayout$SwipeDismissHandler = new DraggableConstraintLayout$SwipeDismissHandler(this, ((ViewGroup) this).mContext, this);
        this.mSwipeDismissHandler = draggableConstraintLayout$SwipeDismissHandler;
        setOnTouchListener(draggableConstraintLayout$SwipeDismissHandler);
        GestureDetector gestureDetector = new GestureDetector(((ViewGroup) this).mContext, new GestureDetector.SimpleOnGestureListener() { // from class: com.android.systemui.screenshot.DraggableConstraintLayout$1
            public final Rect mActionsRect = new Rect();

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                ClipboardOverlayView.this.mActionsContainer.getBoundsOnScreen(this.mActionsRect);
                return (this.mActionsRect.contains((int) motionEvent2.getRawX(), (int) motionEvent2.getRawY()) && ClipboardOverlayView.this.mActionsContainer.canScrollHorizontally((int) f)) ? false : true;
            }
        });
        this.mSwipeDetector = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        this.mCallbacks = new DraggableConstraintLayout$2();
        this.mActionChips = new ArrayList();
        this.mActionButtonViewBinder = new ActionButtonViewBinder();
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        this.mDisplayMetrics = displayMetrics2;
        ((ViewGroup) this).mContext.getDisplay().getRealMetrics(displayMetrics2);
        this.mAccessibilityManager = AccessibilityManager.getInstance(((ViewGroup) this).mContext);
    }

    public static boolean fitsInView(CharSequence charSequence, TextView textView, Paint paint, float f) {
        paint.setTextSize(f);
        return paint.measureText(((String) charSequence).toString()) < ((float) ((textView.getWidth() - textView.getPaddingLeft()) - textView.getPaddingRight()));
    }

    public static void updateTextSize(CharSequence charSequence, TextView textView) {
        Paint paint = new Paint(textView.getPaint());
        Resources resources = textView.getResources();
        float dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.clipboard_overlay_min_font);
        float dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.clipboard_overlay_max_font);
        String str = (String) charSequence;
        if (str.toString().split("\\s+", 2).length != 1 || !fitsInView(str, textView, paint, dimensionPixelSize)) {
            textView.setAutoSizeTextTypeUniformWithConfiguration((int) dimensionPixelSize, (int) dimensionPixelSize2, 4, 0);
            textView.setGravity(8388627);
            return;
        }
        while (true) {
            float f = 4.0f + dimensionPixelSize;
            if (f >= dimensionPixelSize2 || !fitsInView(str, textView, paint, f)) {
                break;
            } else {
                dimensionPixelSize = f;
            }
        }
        textView.setAutoSizeTextTypeWithDefaults(0);
        textView.setGravity(17);
        textView.setTextSize(0, (int) dimensionPixelSize);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnComputeInternalInsetsListener(this);
    }

    public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        Region region = new Region();
        Rect rect = new Rect();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                childAt.getGlobalVisibleRect(rect);
                rect.inset((int) FloatingWindowUtil.dpToPx(this.mDisplayMetrics$1, -12.0f), (int) FloatingWindowUtil.dpToPx(this.mDisplayMetrics$1, -12.0f));
                region.op(rect, Region.Op.UNION);
            }
        }
        internalInsetsInfo.setTouchableInsets(3);
        internalInsetsInfo.touchableRegion.set(region);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnComputeInternalInsetsListener(this);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        final int i = 1;
        this.mActionContainerBackground = requireViewById(R.id.actions_container_background);
        this.mActionContainer = (LinearLayout) requireViewById(R.id.actions);
        this.mClipboardPreview = requireViewById(R.id.clipboard_preview);
        this.mPreviewBorder = requireViewById(R.id.preview_border);
        this.mImagePreview = (ImageView) requireViewById(R.id.image_preview);
        this.mTextPreview = (TextView) requireViewById(R.id.text_preview);
        this.mHiddenPreview = (TextView) requireViewById(R.id.hidden_preview);
        this.mMinimizedPreview = (LinearLayout) requireViewById(R.id.minimized_preview);
        this.mShareChip = requireViewById(R.id.share_chip);
        this.mRemoteCopyChip = requireViewById(R.id.remote_copy_chip);
        this.mDismissButton = requireViewById(R.id.dismiss_button);
        ActionButtonViewBinder actionButtonViewBinder = this.mActionButtonViewBinder;
        View view = this.mRemoteCopyChip;
        ActionButtonAppearance actionButtonAppearance = new ActionButtonAppearance(Icon.createWithResource(((ViewGroup) this).mContext, R.drawable.ic_baseline_devices_24).loadDrawable(((ViewGroup) this).mContext), null, ((ViewGroup) this).mContext.getString(R.string.clipboard_send_nearby_description), true);
        final int i2 = 0;
        Function0 function0 = new Function0(this) { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayView.1
            public final /* synthetic */ ClipboardOverlayView this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        ClipboardOverlayCallbacks clipboardOverlayCallbacks = this.this$0.mClipboardCallbacks;
                        if (clipboardOverlayCallbacks != null) {
                            clipboardOverlayCallbacks.onRemoteCopyButtonTapped();
                            break;
                        }
                        break;
                    default:
                        ClipboardOverlayCallbacks clipboardOverlayCallbacks2 = this.this$0.mClipboardCallbacks;
                        if (clipboardOverlayCallbacks2 != null) {
                            clipboardOverlayCallbacks2.onShareButtonTapped();
                            break;
                        }
                        break;
                }
                return null;
            }
        };
        int i3 = ActionButtonViewModel.nextId;
        ActionButtonViewModel.nextId = i3 + 1;
        ActionButtonViewModel actionButtonViewModel = new ActionButtonViewModel(actionButtonAppearance, i3, true, function0);
        actionButtonViewBinder.getClass();
        ActionButtonViewBinder.bind(view, actionButtonViewModel);
        ActionButtonViewBinder actionButtonViewBinder2 = this.mActionButtonViewBinder;
        View view2 = this.mShareChip;
        ActionButtonAppearance actionButtonAppearance2 = new ActionButtonAppearance(Icon.createWithResource(((ViewGroup) this).mContext, R.drawable.ic_screenshot_share).loadDrawable(((ViewGroup) this).mContext), null, ((ViewGroup) this).mContext.getString(android.R.string.sms_control_message), true);
        Function0 function02 = new Function0(this) { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayView.1
            public final /* synthetic */ ClipboardOverlayView this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        ClipboardOverlayCallbacks clipboardOverlayCallbacks = this.this$0.mClipboardCallbacks;
                        if (clipboardOverlayCallbacks != null) {
                            clipboardOverlayCallbacks.onRemoteCopyButtonTapped();
                            break;
                        }
                        break;
                    default:
                        ClipboardOverlayCallbacks clipboardOverlayCallbacks2 = this.this$0.mClipboardCallbacks;
                        if (clipboardOverlayCallbacks2 != null) {
                            clipboardOverlayCallbacks2.onShareButtonTapped();
                            break;
                        }
                        break;
                }
                return null;
            }
        };
        int i4 = ActionButtonViewModel.nextId;
        ActionButtonViewModel.nextId = i4 + 1;
        ActionButtonViewModel actionButtonViewModel2 = new ActionButtonViewModel(actionButtonAppearance2, i4, true, function02);
        actionButtonViewBinder2.getClass();
        ActionButtonViewBinder.bind(view2, actionButtonViewModel2);
        this.mTextPreview.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayView$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                ClipboardOverlayView clipboardOverlayView = ClipboardOverlayView.this;
                int height = clipboardOverlayView.mTextPreview.getHeight() - (clipboardOverlayView.mTextPreview.getPaddingBottom() + clipboardOverlayView.mTextPreview.getPaddingTop());
                TextView textView = clipboardOverlayView.mTextPreview;
                textView.setMaxLines(Math.max(height / textView.getLineHeight(), 1));
                return true;
            }
        });
        onFinishInflate$com$android$systemui$screenshot$DraggableConstraintLayout();
    }

    public final void onFinishInflate$com$android$systemui$screenshot$DraggableConstraintLayout() {
        this.mActionsContainer = findViewById(R.id.actions_container);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        this.mCallbacks.onInteraction();
        return super.onInterceptHoverEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            this.mSwipeDismissHandler.onTouch(this, motionEvent);
        }
        return this.mSwipeDetector.onTouchEvent(motionEvent);
    }

    public final void setActionChip(final RemoteAction remoteAction, final ClipboardOverlayController$$ExternalSyntheticLambda1 clipboardOverlayController$$ExternalSyntheticLambda1) {
        this.mActionContainerBackground.setVisibility(0);
        View inflate = LayoutInflater.from(((ViewGroup) this).mContext).inflate(R.layout.shelf_action_chip, (ViewGroup) this.mActionContainer, false);
        ActionButtonViewBinder actionButtonViewBinder = this.mActionButtonViewBinder;
        ActionButtonAppearance actionButtonAppearance = new ActionButtonAppearance(remoteAction.getIcon().loadDrawable(((ViewGroup) this).mContext), remoteAction.getTitle(), remoteAction.getTitle(), false);
        Function0 function0 = new Function0() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayView.5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                try {
                    remoteAction.getActionIntent().send();
                    clipboardOverlayController$$ExternalSyntheticLambda1.run();
                    return null;
                } catch (PendingIntent.CanceledException unused) {
                    Log.e("ClipboardView", "Failed to send intent");
                    return null;
                }
            }
        };
        int i = ActionButtonViewModel.nextId;
        ActionButtonViewModel.nextId = i + 1;
        ActionButtonViewModel actionButtonViewModel = new ActionButtonViewModel(actionButtonAppearance, i, true, function0);
        actionButtonViewBinder.getClass();
        ActionButtonViewBinder.bind(inflate, actionButtonViewModel);
        this.mActionContainer.addView(inflate);
        this.mActionChips.add(inflate);
    }

    public final void setEditAccessibilityAction(boolean z) {
        if (z) {
            ViewCompat.replaceAccessibilityAction(this.mClipboardPreview, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, ((ViewGroup) this).mContext.getString(R.string.clipboard_edit), null);
        } else {
            ViewCompat.replaceAccessibilityAction(this.mClipboardPreview, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, null, null);
        }
    }

    public final void setInsets(WindowInsets windowInsets, int i) {
        Rect rect;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        DisplayCutout displayCutout = windowInsets.getDisplayCutout();
        Insets insets = windowInsets.getInsets(WindowInsets.Type.navigationBars());
        Insets insets2 = windowInsets.getInsets(WindowInsets.Type.ime());
        if (displayCutout == null) {
            rect = new Rect(0, 0, 0, Math.max(insets2.bottom, insets.bottom));
        } else {
            Insets waterfallInsets = displayCutout.getWaterfallInsets();
            rect = i == 1 ? new Rect(waterfallInsets.left, Math.max(displayCutout.getSafeInsetTop(), waterfallInsets.top), waterfallInsets.right, Math.max(insets2.bottom, Math.max(displayCutout.getSafeInsetBottom(), Math.max(insets.bottom, waterfallInsets.bottom)))) : new Rect(waterfallInsets.left, waterfallInsets.top, waterfallInsets.right, Math.max(insets2.bottom, Math.max(insets.bottom, waterfallInsets.bottom)));
        }
        layoutParams.setMargins(rect.left, rect.top, rect.right, rect.bottom);
        setLayoutParams(layoutParams);
        requestLayout();
    }

    public final void setMinimized(boolean z) {
        if (!z) {
            this.mMinimizedPreview.setVisibility(8);
            this.mClipboardPreview.setVisibility(0);
            this.mPreviewBorder.setVisibility(0);
            this.mActionContainer.setVisibility(0);
            return;
        }
        this.mMinimizedPreview.setVisibility(0);
        this.mClipboardPreview.setVisibility(8);
        this.mPreviewBorder.setVisibility(8);
        this.mActionContainer.setVisibility(8);
        this.mActionContainerBackground.setVisibility(8);
    }

    public final void showDefaultTextPreview() {
        showTextPreview(((ViewGroup) this).mContext.getString(R.string.clipboard_overlay_text_copied), false);
    }

    public final void showImagePreview(Bitmap bitmap) {
        if (bitmap == null) {
            this.mHiddenPreview.setText(((ViewGroup) this).mContext.getString(R.string.clipboard_text_hidden));
            showSinglePreview(this.mHiddenPreview);
        } else {
            this.mImagePreview.setImageBitmap(bitmap);
            showSinglePreview(this.mImagePreview);
        }
    }

    public final void showSinglePreview(View view) {
        this.mTextPreview.setVisibility(8);
        this.mImagePreview.setVisibility(8);
        this.mHiddenPreview.setVisibility(8);
        this.mMinimizedPreview.setVisibility(8);
        view.setVisibility(0);
    }

    public final void showTextPreview(CharSequence charSequence, boolean z) {
        final TextView textView = z ? this.mHiddenPreview : this.mTextPreview;
        showSinglePreview(textView);
        final String str = (String) charSequence;
        textView.setText(str.subSequence(0, Math.min(500, str.length())));
        updateTextSize(str, textView);
        textView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.clipboardoverlay.ClipboardOverlayView$$ExternalSyntheticLambda10
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                CharSequence charSequence2 = str;
                TextView textView2 = textView;
                if (i3 - i != i7 - i5) {
                    ClipboardOverlayView.updateTextSize(charSequence2, textView2);
                } else {
                    int i9 = ClipboardOverlayView.$r8$clinit;
                }
            }
        });
    }

    public ClipboardOverlayView(Context context) {
        this(context, null);
    }

    public ClipboardOverlayView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
