package com.android.keyguard;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Trace;
import android.view.Display;
import android.view.View;
import androidx.collection.ArraySet;
import androidx.lifecycle.Observer;
import androidx.slice.ArrayUtils;
import androidx.slice.Slice;
import androidx.slice.widget.ListContent;
import androidx.slice.widget.RowContent;
import androidx.slice.widget.SliceLiveData;
import com.android.keyguard.KeyguardSliceView;
import com.android.keyguard.KeyguardSliceViewController;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.ViewController;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSliceViewController extends ViewController implements Dumpable {
    public final ActivityStarter mActivityStarter;
    public final Handler mBgHandler;
    public Map mClickActions;
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass1 mConfigurationListener;
    public int mDisplayId;
    public final DisplayTracker mDisplayTracker;
    public final DumpManager mDumpManager;
    public final Handler mHandler;
    public Uri mKeyguardSliceUri;
    public SliceLiveData.SliceLiveDataImpl mLiveData;
    public final AnonymousClass2 mObserver;
    public Slice mSlice;
    public final KeyguardSliceViewController$$ExternalSyntheticLambda1 mTunable;
    public final TunerService mTunerService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardSliceViewController$2, reason: invalid class name */
    public final class AnonymousClass2 implements Observer {
        public AnonymousClass2() {
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            Slice slice = (Slice) obj;
            KeyguardSliceViewController keyguardSliceViewController = KeyguardSliceViewController.this;
            keyguardSliceViewController.mSlice = slice;
            Trace.beginSection("KeyguardSliceViewController#showSlice");
            boolean z = false;
            if (slice == null) {
                KeyguardSliceView keyguardSliceView = (KeyguardSliceView) keyguardSliceViewController.mView;
                keyguardSliceView.mTitle.setVisibility(8);
                keyguardSliceView.mRow.setVisibility(8);
                keyguardSliceView.mHasHeader = false;
                Trace.endSection();
                return;
            }
            ListContent listContent = new ListContent(slice);
            RowContent rowContent = listContent.mHeaderContent;
            if (rowContent != null && !ArrayUtils.contains(rowContent.mSliceItem.mHints, "list_item")) {
                z = true;
            }
            List list = (List) listContent.mRowItems.stream().filter(new KeyguardSliceViewController$$ExternalSyntheticLambda0()).collect(Collectors.toList());
            KeyguardSliceView keyguardSliceView2 = (KeyguardSliceView) keyguardSliceViewController.mView;
            if (!z) {
                rowContent = null;
            }
            keyguardSliceViewController.mClickActions = keyguardSliceView2.showSlice(rowContent, list);
            Trace.endSection();
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.keyguard.KeyguardSliceViewController$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.android.keyguard.KeyguardSliceViewController$1] */
    public KeyguardSliceViewController(Handler handler, Handler handler2, KeyguardSliceView keyguardSliceView, ActivityStarter activityStarter, ConfigurationController configurationController, TunerService tunerService, DumpManager dumpManager, DisplayTracker displayTracker) {
        super(keyguardSliceView);
        this.mTunable = new TunerService.Tunable() { // from class: com.android.keyguard.KeyguardSliceViewController$$ExternalSyntheticLambda1
            @Override // com.android.systemui.tuner.TunerService.Tunable
            public final void onTuningChanged(String str, String str2) {
                boolean z;
                KeyguardSliceViewController keyguardSliceViewController = KeyguardSliceViewController.this;
                keyguardSliceViewController.getClass();
                if (str2 == null) {
                    str2 = "content://com.android.systemui.keyguard/main";
                }
                SliceLiveData.SliceLiveDataImpl sliceLiveDataImpl = keyguardSliceViewController.mLiveData;
                KeyguardSliceViewController.AnonymousClass2 anonymousClass2 = keyguardSliceViewController.mObserver;
                if (sliceLiveDataImpl == null || sliceLiveDataImpl.mActiveCount <= 0) {
                    z = false;
                } else {
                    sliceLiveDataImpl.removeObserver(anonymousClass2);
                    z = true;
                }
                keyguardSliceViewController.mKeyguardSliceUri = Uri.parse(str2);
                Context context = ((KeyguardSliceView) keyguardSliceViewController.mView).getContext();
                Uri uri = keyguardSliceViewController.mKeyguardSliceUri;
                ArraySet arraySet = SliceLiveData.SUPPORTED_SPECS;
                SliceLiveData.SliceLiveDataImpl sliceLiveDataImpl2 = new SliceLiveData.SliceLiveDataImpl(context.getApplicationContext(), uri, null);
                keyguardSliceViewController.mLiveData = sliceLiveDataImpl2;
                if (z) {
                    sliceLiveDataImpl2.observeForever(anonymousClass2);
                }
            }
        };
        this.mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.keyguard.KeyguardSliceViewController.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                ((KeyguardSliceView) KeyguardSliceViewController.this.mView).onDensityOrFontScaleChanged();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                KeyguardSliceView keyguardSliceView2 = (KeyguardSliceView) KeyguardSliceViewController.this.mView;
                for (int i = 0; i < keyguardSliceView2.mRow.getChildCount(); i++) {
                    View childAt = keyguardSliceView2.mRow.getChildAt(i);
                    if (childAt instanceof KeyguardSliceView.KeyguardSliceTextView) {
                        ((KeyguardSliceView.KeyguardSliceTextView) childAt).setTextAppearance(R.style.TextAppearance_Keyguard_Secondary);
                    }
                }
            }
        };
        this.mObserver = new AnonymousClass2();
        this.mHandler = handler;
        this.mBgHandler = handler2;
        this.mActivityStarter = activityStarter;
        this.mConfigurationController = configurationController;
        this.mTunerService = tunerService;
        this.mDumpManager = dumpManager;
        this.mDisplayTracker = displayTracker;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("  mSlice: " + this.mSlice);
        printWriter.println("  mClickActions: " + this.mClickActions);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        SliceLiveData.SliceLiveDataImpl sliceLiveDataImpl;
        Display display = ((KeyguardSliceView) this.mView).getDisplay();
        if (display != null) {
            this.mDisplayId = display.getDisplayId();
        }
        this.mTunerService.addTunable(this.mTunable, "keyguard_slice_uri");
        int i = this.mDisplayId;
        this.mDisplayTracker.getClass();
        if (i == 0 && (sliceLiveDataImpl = this.mLiveData) != null) {
            sliceLiveDataImpl.observeForever(this.mObserver);
        }
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        this.mDumpManager.registerNormalDumpable("KeyguardSliceViewCtrl@" + Integer.toHexString(hashCode()), this);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        int i = this.mDisplayId;
        this.mDisplayTracker.getClass();
        if (i == 0) {
            this.mLiveData.removeObserver(this.mObserver);
        }
        this.mTunerService.removeTunable(this.mTunable);
        ((ConfigurationControllerImpl) this.mConfigurationController).removeCallback(this.mConfigurationListener);
        this.mDumpManager.unregisterDumpable("KeyguardSliceViewCtrl@" + Integer.toHexString(hashCode()));
    }
}
