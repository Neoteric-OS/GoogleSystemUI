package androidx.appcompat.app;

import android.R;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.view.menu.MenuDialogHelper;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AlertDialog extends AppCompatDialog implements DialogInterface {
    public final AlertController mAlert;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final AlertController.AlertParams P;
        public final int mTheme;

        public Builder(Context context) {
            int resolveDialogTheme = AlertDialog.resolveDialogTheme(0, context);
            this.P = new AlertController.AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(resolveDialogTheme, context)));
            this.mTheme = resolveDialogTheme;
        }

        public final AlertDialog create() {
            final AlertController.AlertParams alertParams = this.P;
            AlertDialog alertDialog = new AlertDialog(this.mTheme, alertParams.mContext);
            final AlertController alertController = alertDialog.mAlert;
            View view = alertParams.mCustomTitleView;
            if (view != null) {
                alertController.mCustomTitleView = view;
            } else {
                CharSequence charSequence = alertParams.mTitle;
                if (charSequence != null) {
                    alertController.mTitle = charSequence;
                    TextView textView = alertController.mTitleView;
                    if (textView != null) {
                        textView.setText(charSequence);
                    }
                    alertController.mWindow.setTitle(charSequence);
                }
                Drawable drawable = alertParams.mIcon;
                if (drawable != null) {
                    alertController.mIcon = drawable;
                    ImageView imageView = alertController.mIconView;
                    if (imageView != null) {
                        imageView.setVisibility(0);
                        alertController.mIconView.setImageDrawable(drawable);
                    }
                }
            }
            CharSequence charSequence2 = alertParams.mPositiveButtonText;
            if (charSequence2 != null) {
                alertController.setButton(-1, charSequence2, alertParams.mPositiveButtonListener);
            }
            CharSequence charSequence3 = alertParams.mNegativeButtonText;
            if (charSequence3 != null) {
                alertController.setButton(-2, charSequence3, alertParams.mNegativeButtonListener);
            }
            if (alertParams.mAdapter != null) {
                AlertController.RecycleListView recycleListView = (AlertController.RecycleListView) alertParams.mInflater.inflate(alertController.mListLayout, (ViewGroup) null);
                int i = alertParams.mIsSingleChoice ? alertController.mSingleChoiceItemLayout : alertController.mListItemLayout;
                ListAdapter listAdapter = alertParams.mAdapter;
                if (listAdapter == null) {
                    listAdapter = new AlertController.CheckedItemAdapter(alertParams.mContext, i, R.id.text1, null);
                }
                alertController.mAdapter = listAdapter;
                alertController.mCheckedItem = alertParams.mCheckedItem;
                if (alertParams.mOnClickListener != null) {
                    recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: androidx.appcompat.app.AlertController.AlertParams.3
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public final void onItemClick(AdapterView adapterView, View view2, int i2, long j) {
                            AlertParams.this.mOnClickListener.onClick(alertController.mDialog, i2);
                            if (AlertParams.this.mIsSingleChoice) {
                                return;
                            }
                            alertController.mDialog.dismiss();
                        }
                    });
                }
                if (alertParams.mIsSingleChoice) {
                    recycleListView.setChoiceMode(1);
                }
                alertController.mListView = recycleListView;
            }
            int i2 = alertParams.mViewLayoutResId;
            if (i2 != 0) {
                alertController.getClass();
                alertController.mViewLayoutResId = i2;
                alertController.mViewSpacingSpecified = false;
            }
            alertDialog.setCancelable(true);
            alertDialog.setCanceledOnTouchOutside(true);
            alertDialog.setOnCancelListener(null);
            alertDialog.setOnDismissListener(alertParams.mOnDismissListener);
            MenuDialogHelper menuDialogHelper = alertParams.mOnKeyListener;
            if (menuDialogHelper != null) {
                alertDialog.setOnKeyListener(menuDialogHelper);
            }
            return alertDialog;
        }
    }

    public AlertDialog(int i, Context context) {
        super(resolveDialogTheme(i, context), context);
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    public static int resolveDialogTheme(int i, Context context) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(com.android.wm.shell.R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        int i;
        ListAdapter listAdapter;
        View findViewById;
        super.onCreate(bundle);
        AlertController alertController = this.mAlert;
        alertController.mDialog.setContentView(alertController.mAlertDialogLayout);
        View findViewById2 = alertController.mWindow.findViewById(com.android.wm.shell.R.id.parentPanel);
        View findViewById3 = findViewById2.findViewById(com.android.wm.shell.R.id.topPanel);
        View findViewById4 = findViewById2.findViewById(com.android.wm.shell.R.id.contentPanel);
        View findViewById5 = findViewById2.findViewById(com.android.wm.shell.R.id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById2.findViewById(com.android.wm.shell.R.id.customPanel);
        View inflate = alertController.mViewLayoutResId != 0 ? LayoutInflater.from(alertController.mContext).inflate(alertController.mViewLayoutResId, viewGroup, false) : null;
        boolean z = inflate != null;
        if (!z || !AlertController.canTextInput(inflate)) {
            alertController.mWindow.setFlags(131072, 131072);
        }
        if (z) {
            FrameLayout frameLayout = (FrameLayout) alertController.mWindow.findViewById(com.android.wm.shell.R.id.custom);
            frameLayout.addView(inflate, new ViewGroup.LayoutParams(-1, -1));
            if (alertController.mViewSpacingSpecified) {
                frameLayout.setPadding(0, 0, 0, 0);
            }
            if (alertController.mListView != null) {
                ((LinearLayout.LayoutParams) ((LinearLayoutCompat.LayoutParams) viewGroup.getLayoutParams())).weight = 0.0f;
            }
        } else {
            viewGroup.setVisibility(8);
        }
        View findViewById6 = viewGroup.findViewById(com.android.wm.shell.R.id.topPanel);
        View findViewById7 = viewGroup.findViewById(com.android.wm.shell.R.id.contentPanel);
        View findViewById8 = viewGroup.findViewById(com.android.wm.shell.R.id.buttonPanel);
        ViewGroup resolvePanel = AlertController.resolvePanel(findViewById6, findViewById3);
        ViewGroup resolvePanel2 = AlertController.resolvePanel(findViewById7, findViewById4);
        ViewGroup resolvePanel3 = AlertController.resolvePanel(findViewById8, findViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) alertController.mWindow.findViewById(com.android.wm.shell.R.id.scrollView);
        alertController.mScrollView = nestedScrollView;
        nestedScrollView.setFocusable(false);
        alertController.mScrollView.setNestedScrollingEnabled(false);
        TextView textView = (TextView) resolvePanel2.findViewById(R.id.message);
        alertController.mMessageView = textView;
        if (textView != null) {
            textView.setVisibility(8);
            alertController.mScrollView.removeView(alertController.mMessageView);
            if (alertController.mListView != null) {
                ViewGroup viewGroup2 = (ViewGroup) alertController.mScrollView.getParent();
                int indexOfChild = viewGroup2.indexOfChild(alertController.mScrollView);
                viewGroup2.removeViewAt(indexOfChild);
                viewGroup2.addView(alertController.mListView, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
            } else {
                resolvePanel2.setVisibility(8);
            }
        }
        Button button = (Button) resolvePanel3.findViewById(R.id.button1);
        alertController.mButtonPositive = button;
        AlertController.AnonymousClass1 anonymousClass1 = alertController.mButtonHandler;
        button.setOnClickListener(anonymousClass1);
        if (TextUtils.isEmpty(alertController.mButtonPositiveText)) {
            alertController.mButtonPositive.setVisibility(8);
            i = 0;
        } else {
            alertController.mButtonPositive.setText(alertController.mButtonPositiveText);
            alertController.mButtonPositive.setVisibility(0);
            i = 1;
        }
        Button button2 = (Button) resolvePanel3.findViewById(R.id.button2);
        alertController.mButtonNegative = button2;
        button2.setOnClickListener(anonymousClass1);
        if (TextUtils.isEmpty(alertController.mButtonNegativeText)) {
            alertController.mButtonNegative.setVisibility(8);
        } else {
            alertController.mButtonNegative.setText(alertController.mButtonNegativeText);
            alertController.mButtonNegative.setVisibility(0);
            i |= 2;
        }
        Button button3 = (Button) resolvePanel3.findViewById(R.id.button3);
        alertController.mButtonNeutral = button3;
        button3.setOnClickListener(anonymousClass1);
        if (TextUtils.isEmpty(alertController.mButtonNeutralText)) {
            alertController.mButtonNeutral.setVisibility(8);
        } else {
            alertController.mButtonNeutral.setText(alertController.mButtonNeutralText);
            alertController.mButtonNeutral.setVisibility(0);
            i |= 4;
        }
        Context context = alertController.mContext;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(com.android.wm.shell.R.attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (i == 1) {
                Button button4 = alertController.mButtonPositive;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button4.getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.weight = 0.5f;
                button4.setLayoutParams(layoutParams);
            } else if (i == 2) {
                Button button5 = alertController.mButtonNegative;
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button5.getLayoutParams();
                layoutParams2.gravity = 1;
                layoutParams2.weight = 0.5f;
                button5.setLayoutParams(layoutParams2);
            } else if (i == 4) {
                Button button6 = alertController.mButtonNeutral;
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button6.getLayoutParams();
                layoutParams3.gravity = 1;
                layoutParams3.weight = 0.5f;
                button6.setLayoutParams(layoutParams3);
            }
        }
        if (i == 0) {
            resolvePanel3.setVisibility(8);
        }
        if (alertController.mCustomTitleView != null) {
            resolvePanel.addView(alertController.mCustomTitleView, 0, new ViewGroup.LayoutParams(-1, -2));
            alertController.mWindow.findViewById(com.android.wm.shell.R.id.title_template).setVisibility(8);
        } else {
            alertController.mIconView = (ImageView) alertController.mWindow.findViewById(R.id.icon);
            if (TextUtils.isEmpty(alertController.mTitle) || !alertController.mShowTitle) {
                alertController.mWindow.findViewById(com.android.wm.shell.R.id.title_template).setVisibility(8);
                alertController.mIconView.setVisibility(8);
                resolvePanel.setVisibility(8);
            } else {
                TextView textView2 = (TextView) alertController.mWindow.findViewById(com.android.wm.shell.R.id.alertTitle);
                alertController.mTitleView = textView2;
                textView2.setText(alertController.mTitle);
                Drawable drawable = alertController.mIcon;
                if (drawable != null) {
                    alertController.mIconView.setImageDrawable(drawable);
                } else {
                    alertController.mTitleView.setPadding(alertController.mIconView.getPaddingLeft(), alertController.mIconView.getPaddingTop(), alertController.mIconView.getPaddingRight(), alertController.mIconView.getPaddingBottom());
                    alertController.mIconView.setVisibility(8);
                }
            }
        }
        boolean z2 = viewGroup.getVisibility() != 8;
        int i2 = (resolvePanel == null || resolvePanel.getVisibility() == 8) ? 0 : 1;
        boolean z3 = resolvePanel3.getVisibility() != 8;
        if (!z3 && (findViewById = resolvePanel2.findViewById(com.android.wm.shell.R.id.textSpacerNoButtons)) != null) {
            findViewById.setVisibility(0);
        }
        if (i2 != 0) {
            NestedScrollView nestedScrollView2 = alertController.mScrollView;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            View findViewById9 = alertController.mListView != null ? resolvePanel.findViewById(com.android.wm.shell.R.id.titleDividerNoCustom) : null;
            if (findViewById9 != null) {
                findViewById9.setVisibility(0);
            }
        } else {
            View findViewById10 = resolvePanel2.findViewById(com.android.wm.shell.R.id.textSpacerNoTitle);
            if (findViewById10 != null) {
                findViewById10.setVisibility(0);
            }
        }
        AlertController.RecycleListView recycleListView = alertController.mListView;
        if (recycleListView != null) {
            recycleListView.getClass();
            if (!z3 || i2 == 0) {
                recycleListView.setPadding(recycleListView.getPaddingLeft(), i2 != 0 ? recycleListView.getPaddingTop() : recycleListView.mPaddingTopNoTitle, recycleListView.getPaddingRight(), z3 ? recycleListView.getPaddingBottom() : recycleListView.mPaddingBottomNoButtons);
            }
        }
        if (!z2) {
            View view = alertController.mListView;
            if (view == null) {
                view = alertController.mScrollView;
            }
            if (view != null) {
                int i3 = i2 | (z3 ? 2 : 0);
                View findViewById11 = alertController.mWindow.findViewById(com.android.wm.shell.R.id.scrollIndicatorUp);
                View findViewById12 = alertController.mWindow.findViewById(com.android.wm.shell.R.id.scrollIndicatorDown);
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                ViewCompat.Api23Impl.setScrollIndicators(view, i3, 3);
                if (findViewById11 != null) {
                    resolvePanel2.removeView(findViewById11);
                }
                if (findViewById12 != null) {
                    resolvePanel2.removeView(findViewById12);
                }
            }
        }
        AlertController.RecycleListView recycleListView2 = alertController.mListView;
        if (recycleListView2 == null || (listAdapter = alertController.mAdapter) == null) {
            return;
        }
        recycleListView2.setAdapter(listAdapter);
        int i4 = alertController.mCheckedItem;
        if (i4 > -1) {
            recycleListView2.setItemChecked(i4, true);
            recycleListView2.setSelection(i4);
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.mAlert.mScrollView;
        if (nestedScrollView == null || !nestedScrollView.executeKeyEvent(keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.mAlert.mScrollView;
        if (nestedScrollView == null || !nestedScrollView.executeKeyEvent(keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        AlertController alertController = this.mAlert;
        alertController.mTitle = charSequence;
        TextView textView = alertController.mTitleView;
        if (textView != null) {
            textView.setText(charSequence);
        }
        alertController.mWindow.setTitle(charSequence);
    }
}
