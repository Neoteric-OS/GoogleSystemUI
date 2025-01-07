package com.android.systemui.statusbar.notification.logging;

import android.R;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.notification.logging.NotificationMemoryViewWalker;
import com.android.systemui.util.ConvenienceExtensionsKt;
import java.util.HashSet;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceBuilderIterator;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationMemoryViewWalker {
    public static final NotificationMemoryViewWalker INSTANCE = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UsageBuilder {
        public int customViews;
        public int largeIcon;
        public int smallIcon;
        public int softwareBitmaps;
        public int style;
        public int systemIcons;
    }

    /* JADX WARN: Type inference failed for: r9v2, types: [kotlin.coroutines.jvm.internal.RestrictedSuspendLambda, kotlin.jvm.functions.Function2] */
    public static void computeViewHierarchyUse(ViewGroup viewGroup, UsageBuilder usageBuilder, HashSet hashSet) {
        ImageView imageView;
        Drawable drawable;
        Bitmap bitmap;
        SequenceBuilderIterator it = SequencesKt__SequenceBuilderKt.iterator(ConvenienceExtensionsKt.getChildren(viewGroup).$block$inlined);
        while (it.hasNext()) {
            View view = (View) it.next();
            if (view instanceof ViewGroup) {
                computeViewHierarchyUse((ViewGroup) view, usageBuilder, hashSet);
            } else {
                Intrinsics.checkNotNull(view);
                if ((view instanceof ImageView) && (drawable = (imageView = (ImageView) view).getDrawable()) != null) {
                    int identityHashCode = System.identityHashCode(drawable);
                    if (!hashSet.contains(Integer.valueOf(identityHashCode))) {
                        boolean z = drawable instanceof BitmapDrawable;
                        int i = 0;
                        if (z && (bitmap = ((BitmapDrawable) drawable).getBitmap()) != null) {
                            int identityHashCode2 = System.identityHashCode(bitmap);
                            if (!hashSet.contains(Integer.valueOf(identityHashCode2))) {
                                hashSet.add(Integer.valueOf(identityHashCode2));
                                i = bitmap.getAllocationByteCount();
                            }
                        }
                        switch (imageView.getId()) {
                            case R.id.icon:
                            case R.id.datePicker:
                            case R.id.lock_screen:
                                usageBuilder.smallIcon += i;
                                break;
                            case R.id.animation:
                            case R.id.feedbackSpoken:
                            case R.id.fingerprints:
                            case R.id.portrait:
                            case R.id.resolver_empty_state_progress:
                                usageBuilder.systemIcons += i;
                                break;
                            case R.id.buttonPanel:
                                usageBuilder.style += i;
                                break;
                            case R.id.scrollView:
                                usageBuilder.largeIcon += i;
                                break;
                            default:
                                if (Log.isLoggable("NotificationMemory", 3)) {
                                    FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Custom view: ", view.getId() == -1 ? "no-id" : view.getResources().getResourceName(view.getId()), "NotificationMemory");
                                }
                                usageBuilder.customViews += i;
                                break;
                        }
                        if (z) {
                            Bitmap bitmap2 = ((BitmapDrawable) drawable).getBitmap();
                            if ((bitmap2 != null ? bitmap2.getConfig() : null) != Bitmap.Config.HARDWARE) {
                                usageBuilder.softwareBitmaps += i;
                            }
                        }
                        hashSet.add(Integer.valueOf(identityHashCode));
                    }
                }
            }
        }
    }

    public static NotificationViewUsage getViewUsage(ViewType viewType, View[] viewArr, HashSet hashSet) {
        Lazy lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.logging.NotificationMemoryViewWalker$getViewUsage$usageBuilder$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new NotificationMemoryViewWalker.UsageBuilder();
            }
        });
        int length = viewArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            View view = viewArr[i];
            ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
            if (viewGroup != null) {
                computeViewHierarchyUse(viewGroup, (UsageBuilder) lazy.getValue(), hashSet);
            }
            i++;
        }
        if (!lazy.isInitialized()) {
            return null;
        }
        UsageBuilder usageBuilder = (UsageBuilder) lazy.getValue();
        return new NotificationViewUsage(viewType, usageBuilder.smallIcon, usageBuilder.largeIcon, usageBuilder.systemIcons, usageBuilder.style, usageBuilder.customViews, usageBuilder.softwareBitmaps);
    }
}
