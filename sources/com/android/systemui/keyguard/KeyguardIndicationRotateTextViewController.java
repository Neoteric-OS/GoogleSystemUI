package com.android.systemui.keyguard;

import android.content.res.ColorStateList;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.data.ViewNode;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.Dumpable;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.phone.KeyguardIndicationTextView;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardIndicationRotateTextViewController extends ViewController implements Dumpable {
    public int mCurrIndicationType;
    public CharSequence mCurrMessage;
    public final DelayableExecutor mExecutor;
    public final FeatureFlags mFeatureFlags;
    public final Map mIndicationMessages;
    public final List mIndicationQueue;
    public final ColorStateList mInitialTextColorState;
    public boolean mIsDozing;
    public long mLastIndicationSwitch;
    public final KeyguardLogger mLogger;
    public final float mMaxAlpha;
    ShowNextIndication mShowNextIndicationRunnable;
    public final StatusBarStateController mStatusBarStateController;
    public final AnonymousClass1 mStatusBarStateListener;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShowNextIndication {
        public ExecutorImpl.ExecutionToken mCancelDelayedRunnable;
        public final KeyguardIndicationRotateTextViewController$ShowNextIndication$$ExternalSyntheticLambda0 mShowIndicationRunnable;

        public ShowNextIndication(long j) {
            KeyguardIndicationRotateTextViewController$ShowNextIndication$$ExternalSyntheticLambda0 keyguardIndicationRotateTextViewController$ShowNextIndication$$ExternalSyntheticLambda0 = new KeyguardIndicationRotateTextViewController$ShowNextIndication$$ExternalSyntheticLambda0(this);
            this.mShowIndicationRunnable = keyguardIndicationRotateTextViewController$ShowNextIndication$$ExternalSyntheticLambda0;
            this.mCancelDelayedRunnable = KeyguardIndicationRotateTextViewController.this.mExecutor.executeDelayed(keyguardIndicationRotateTextViewController$ShowNextIndication$$ExternalSyntheticLambda0, j);
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.keyguard.KeyguardIndicationRotateTextViewController$1] */
    public KeyguardIndicationRotateTextViewController(KeyguardIndicationTextView keyguardIndicationTextView, DelayableExecutor delayableExecutor, StatusBarStateController statusBarStateController, KeyguardLogger keyguardLogger, FeatureFlags featureFlags) {
        super(keyguardIndicationTextView);
        this.mIndicationMessages = new HashMap();
        this.mIndicationQueue = new ArrayList();
        this.mCurrIndicationType = -1;
        this.mStatusBarStateListener = new StatusBarStateController.StateListener() { // from class: com.android.systemui.keyguard.KeyguardIndicationRotateTextViewController.1
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onDozeAmountChanged(float f, float f2) {
                KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController = KeyguardIndicationRotateTextViewController.this;
                ((KeyguardIndicationTextView) keyguardIndicationRotateTextViewController.mView).setAlpha((1.0f - f) * keyguardIndicationRotateTextViewController.mMaxAlpha);
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onDozingChanged(boolean z) {
                KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController = KeyguardIndicationRotateTextViewController.this;
                if (z == keyguardIndicationRotateTextViewController.mIsDozing) {
                    return;
                }
                keyguardIndicationRotateTextViewController.mIsDozing = z;
                if (z) {
                    keyguardIndicationRotateTextViewController.showIndication(-1);
                } else if (keyguardIndicationRotateTextViewController.mIndicationQueue.size() > 0) {
                    keyguardIndicationRotateTextViewController.showIndication(((Integer) keyguardIndicationRotateTextViewController.mIndicationQueue.get(0)).intValue());
                }
            }
        };
        this.mMaxAlpha = keyguardIndicationTextView.getAlpha();
        this.mExecutor = delayableExecutor;
        this.mInitialTextColorState = keyguardIndicationTextView.getTextColors();
        this.mStatusBarStateController = statusBarStateController;
        this.mLogger = keyguardLogger;
        this.mFeatureFlags = featureFlags;
        init$9();
    }

    public static String indicationTypeToString(int i) {
        switch (i) {
            case -1:
                return "none";
            case 0:
                return "owner_info";
            case 1:
                return "disclosure";
            case 2:
                return "logout";
            case 3:
                return "battery";
            case 4:
                return "alignment";
            case 5:
                return "transient";
            case 6:
                return "trust";
            case 7:
                return "persistent_unlock_message";
            case 8:
                return "user_locked";
            case 9:
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
            default:
                return GenericDocument$$ExternalSyntheticOutline0.m("unknown[", "]", i);
            case 10:
                return "reverse_charging";
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return "biometric_message";
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return "biometric_message_followup";
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                return "adaptive_auth";
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "KeyguardIndicationRotatingTextViewController:", "    currentTextViewMessage=");
        m.append((Object) ((KeyguardIndicationTextView) this.mView).getText());
        printWriter.println(m.toString());
        printWriter.println("    currentStoredMessage=" + ((Object) ((KeyguardIndicationTextView) this.mView).mMessage));
        StringBuilder m2 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("    dozing:"), this.mIsDozing, printWriter, "    queue:");
        m2.append(this.mIndicationQueue);
        printWriter.println(m2.toString());
        printWriter.println("    showNextIndicationRunnable:" + this.mShowNextIndicationRunnable);
        if (this.mIndicationMessages.keySet().size() > 0) {
            printWriter.println("    All messages:");
            for (Integer num : this.mIndicationMessages.keySet()) {
                StringBuilder m3 = MutableObjectList$$ExternalSyntheticOutline0.m("        type=", " ", num.intValue());
                m3.append(this.mIndicationMessages.get(num));
                printWriter.println(m3.toString());
            }
        }
    }

    public final void hideIndication(int i) {
        if (!this.mIndicationMessages.containsKey(Integer.valueOf(i)) || TextUtils.isEmpty(((KeyguardIndication) this.mIndicationMessages.get(Integer.valueOf(i))).mMessage)) {
            return;
        }
        updateIndication(i, null, true);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        this.mStatusBarStateController.addCallback(this.mStatusBarStateListener);
        KeyguardIndicationTextView keyguardIndicationTextView = (KeyguardIndicationTextView) this.mView;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.mFeatureFlags.getClass();
        keyguardIndicationTextView.getClass();
        keyguardIndicationTextView.setAccessibilityLiveRegion(1);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.mStatusBarStateController.removeCallback(this.mStatusBarStateListener);
        ShowNextIndication showNextIndication = this.mShowNextIndicationRunnable;
        if (showNextIndication != null) {
            ExecutorImpl.ExecutionToken executionToken = showNextIndication.mCancelDelayedRunnable;
            if (executionToken != null) {
                executionToken.run();
                showNextIndication.mCancelDelayedRunnable = null;
            }
            this.mShowNextIndicationRunnable = null;
        }
    }

    public final void showIndication(int i) {
        CharSequence charSequence;
        Long l;
        ShowNextIndication showNextIndication = this.mShowNextIndicationRunnable;
        if (showNextIndication != null) {
            ExecutorImpl.ExecutionToken executionToken = showNextIndication.mCancelDelayedRunnable;
            if (executionToken != null) {
                executionToken.run();
                showNextIndication.mCancelDelayedRunnable = null;
            }
            this.mShowNextIndicationRunnable = null;
        }
        CharSequence charSequence2 = this.mCurrMessage;
        int i2 = this.mCurrIndicationType;
        this.mCurrIndicationType = i;
        this.mCurrMessage = this.mIndicationMessages.get(Integer.valueOf(i)) != null ? ((KeyguardIndication) this.mIndicationMessages.get(Integer.valueOf(i))).mMessage : null;
        ((ArrayList) this.mIndicationQueue).removeIf(new KeyguardIndicationRotateTextViewController$$ExternalSyntheticLambda0(i, 0));
        if (this.mCurrIndicationType != -1) {
            this.mIndicationQueue.add(Integer.valueOf(i));
        }
        this.mLastIndicationSwitch = SystemClock.uptimeMillis();
        if (!TextUtils.equals(charSequence2, this.mCurrMessage) || i2 != this.mCurrIndicationType) {
            CharSequence charSequence3 = this.mCurrMessage;
            this.mLogger.logKeyguardSwitchIndication(i, charSequence3 != null ? charSequence3.toString() : null);
            KeyguardIndicationTextView keyguardIndicationTextView = (KeyguardIndicationTextView) this.mView;
            KeyguardIndication keyguardIndication = (KeyguardIndication) this.mIndicationMessages.get(Integer.valueOf(i));
            if (keyguardIndication == null) {
                charSequence = null;
            } else {
                keyguardIndicationTextView.getClass();
                charSequence = keyguardIndication.mMessage;
            }
            keyguardIndicationTextView.switchIndication(charSequence, keyguardIndication, null);
        }
        if (this.mCurrIndicationType == -1 || ((ArrayList) this.mIndicationQueue).size() <= 1) {
            return;
        }
        KeyguardIndication keyguardIndication2 = (KeyguardIndication) this.mIndicationMessages.get(Integer.valueOf(i));
        long j = 0;
        if (keyguardIndication2 != null && (l = keyguardIndication2.mMinVisibilityMillis) != null) {
            j = l.longValue();
        }
        long max = Math.max(j, 3500L);
        ShowNextIndication showNextIndication2 = this.mShowNextIndicationRunnable;
        if (showNextIndication2 != null) {
            ExecutorImpl.ExecutionToken executionToken2 = showNextIndication2.mCancelDelayedRunnable;
            if (executionToken2 != null) {
                executionToken2.run();
                showNextIndication2.mCancelDelayedRunnable = null;
            }
            this.mShowNextIndicationRunnable = null;
        }
        this.mShowNextIndicationRunnable = new ShowNextIndication(max);
    }

    public final void updateIndication(int i, KeyguardIndication keyguardIndication, boolean z) {
        Long l;
        Long l2;
        if (i == 10) {
            return;
        }
        KeyguardIndication keyguardIndication2 = (KeyguardIndication) this.mIndicationMessages.get(Integer.valueOf(this.mCurrIndicationType));
        long j = 0;
        long longValue = (keyguardIndication2 == null || (l = keyguardIndication2.mMinVisibilityMillis) == null) ? 0L : l.longValue();
        boolean z2 = (keyguardIndication == null || TextUtils.isEmpty(keyguardIndication.mMessage)) ? false : true;
        if (z2) {
            if (!this.mIndicationQueue.contains(Integer.valueOf(i))) {
                this.mIndicationQueue.add(Integer.valueOf(i));
            }
            this.mIndicationMessages.put(Integer.valueOf(i), keyguardIndication);
        } else {
            this.mIndicationMessages.remove(Integer.valueOf(i));
            ((ArrayList) this.mIndicationQueue).removeIf(new KeyguardIndicationRotateTextViewController$$ExternalSyntheticLambda0(i, 1));
        }
        if (this.mIsDozing) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis() - this.mLastIndicationSwitch;
        boolean z3 = uptimeMillis >= longValue;
        if (!z2) {
            if (this.mCurrIndicationType == i && !z2 && z) {
                ShowNextIndication showNextIndication = this.mShowNextIndicationRunnable;
                if (showNextIndication == null) {
                    showIndication(-1);
                    return;
                }
                ExecutorImpl.ExecutionToken executionToken = showNextIndication.mCancelDelayedRunnable;
                if (executionToken != null) {
                    executionToken.run();
                    showNextIndication.mCancelDelayedRunnable = null;
                }
                showNextIndication.mShowIndicationRunnable.run();
                return;
            }
            return;
        }
        int i2 = this.mCurrIndicationType;
        if (i2 == -1 || i2 == i) {
            showIndication(i);
            return;
        }
        if (z) {
            if (z3) {
                showIndication(i);
                return;
            }
            ((ArrayList) this.mIndicationQueue).removeIf(new KeyguardIndicationRotateTextViewController$$ExternalSyntheticLambda0(i, 2));
            this.mIndicationQueue.add(0, Integer.valueOf(i));
            long j2 = longValue - uptimeMillis;
            ShowNextIndication showNextIndication2 = this.mShowNextIndicationRunnable;
            if (showNextIndication2 != null) {
                ExecutorImpl.ExecutionToken executionToken2 = showNextIndication2.mCancelDelayedRunnable;
                if (executionToken2 != null) {
                    executionToken2.run();
                    showNextIndication2.mCancelDelayedRunnable = null;
                }
                this.mShowNextIndicationRunnable = null;
            }
            this.mShowNextIndicationRunnable = new ShowNextIndication(j2);
            return;
        }
        if (this.mShowNextIndicationRunnable != null) {
            return;
        }
        KeyguardIndication keyguardIndication3 = (KeyguardIndication) this.mIndicationMessages.get(Integer.valueOf(i));
        if (keyguardIndication3 != null && (l2 = keyguardIndication3.mMinVisibilityMillis) != null) {
            j = l2.longValue();
        }
        long max = Math.max(j, 3500L);
        if (uptimeMillis >= max) {
            showIndication(i);
            return;
        }
        long j3 = max - uptimeMillis;
        ShowNextIndication showNextIndication3 = this.mShowNextIndicationRunnable;
        if (showNextIndication3 != null) {
            ExecutorImpl.ExecutionToken executionToken3 = showNextIndication3.mCancelDelayedRunnable;
            if (executionToken3 != null) {
                executionToken3.run();
                showNextIndication3.mCancelDelayedRunnable = null;
            }
            this.mShowNextIndicationRunnable = null;
        }
        this.mShowNextIndicationRunnable = new ShowNextIndication(j3);
    }
}
