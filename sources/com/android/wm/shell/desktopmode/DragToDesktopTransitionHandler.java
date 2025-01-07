package com.android.wm.shell.desktopmode;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.SparseBooleanArray;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.protolog.ProtoLog;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel;
import com.android.wm.shell.windowdecor.MoveToDesktopAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterator;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DragToDesktopTransitionHandler implements Transitions.TransitionHandler {
    public final Context context;
    public DesktopTasksController$dragToDesktopStateListener$1 dragToDesktopStateListener;
    public final InteractionJankMonitor interactionJankMonitor;
    public DesktopModeWindowDecorViewModel.DragStartListenerImpl onTaskResizeAnimationListener;
    public SplitScreenController splitScreenController;
    public final RootTaskDisplayAreaOrganizer taskDisplayAreaOrganizer;
    public final Supplier transactionSupplier;
    public TransitionState transitionState;
    public final Transitions transitions;
    public final RectEvaluator rectEvaluator = new RectEvaluator(new Rect());
    public final Intent launchHomeIntent = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME");

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CancelState {
        public static final /* synthetic */ CancelState[] $VALUES;
        public static final CancelState CANCEL_SPLIT_LEFT;
        public static final CancelState CANCEL_SPLIT_RIGHT;
        public static final CancelState NO_CANCEL;
        public static final CancelState STANDARD_CANCEL;

        static {
            CancelState cancelState = new CancelState("NO_CANCEL", 0);
            NO_CANCEL = cancelState;
            CancelState cancelState2 = new CancelState("STANDARD_CANCEL", 1);
            STANDARD_CANCEL = cancelState2;
            CancelState cancelState3 = new CancelState("CANCEL_SPLIT_LEFT", 2);
            CANCEL_SPLIT_LEFT = cancelState3;
            CancelState cancelState4 = new CancelState("CANCEL_SPLIT_RIGHT", 3);
            CANCEL_SPLIT_RIGHT = cancelState4;
            CancelState[] cancelStateArr = {cancelState, cancelState2, cancelState3, cancelState4};
            $VALUES = cancelStateArr;
            EnumEntriesKt.enumEntries(cancelStateArr);
        }

        public static CancelState valueOf(String str) {
            return (CancelState) Enum.valueOf(CancelState.class, str);
        }

        public static CancelState[] values() {
            return (CancelState[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DragToDesktopLayers {
        public final int dragLayer;
        public final int topAppLayer;
        public final int topHomeLayer;
        public final int topWallpaperLayer;

        public DragToDesktopLayers(int i, int i2, int i3, int i4) {
            this.topAppLayer = i;
            this.topHomeLayer = i2;
            this.topWallpaperLayer = i3;
            this.dragLayer = i4;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DragToDesktopLayers)) {
                return false;
            }
            DragToDesktopLayers dragToDesktopLayers = (DragToDesktopLayers) obj;
            return this.topAppLayer == dragToDesktopLayers.topAppLayer && this.topHomeLayer == dragToDesktopLayers.topHomeLayer && this.topWallpaperLayer == dragToDesktopLayers.topWallpaperLayer && this.dragLayer == dragToDesktopLayers.dragLayer;
        }

        public final int hashCode() {
            return Integer.hashCode(this.dragLayer) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.topWallpaperLayer, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.topHomeLayer, Integer.hashCode(this.topAppLayer) * 31, 31), 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("DragToDesktopLayers(topAppLayer=");
            sb.append(this.topAppLayer);
            sb.append(", topHomeLayer=");
            sb.append(this.topHomeLayer);
            sb.append(", topWallpaperLayer=");
            sb.append(this.topWallpaperLayer);
            sb.append(", dragLayer=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.dragLayer, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class TransitionState {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class FromFullscreen extends TransitionState {
            public CancelState cancelState;
            public IBinder cancelTransitionToken;
            public final MoveToDesktopAnimator dragAnimator;
            public TransitionInfo.Change draggedTaskChange;
            public final int draggedTaskId;
            public List freeformTaskChanges;
            public TransitionInfo.Change homeChange;
            public final List otherRootChanges;
            public boolean startAborted;
            public Transitions.TransitionFinishCallback startTransitionFinishCb;
            public SurfaceControl.Transaction startTransitionFinishTransaction;
            public final IBinder startTransitionToken;
            public DragToDesktopLayers surfaceLayers;

            public FromFullscreen(int i, MoveToDesktopAnimator moveToDesktopAnimator, IBinder iBinder) {
                EmptyList emptyList = EmptyList.INSTANCE;
                CancelState cancelState = CancelState.NO_CANCEL;
                ArrayList arrayList = new ArrayList();
                this.draggedTaskId = i;
                this.dragAnimator = moveToDesktopAnimator;
                this.startTransitionToken = iBinder;
                this.startTransitionFinishCb = null;
                this.startTransitionFinishTransaction = null;
                this.cancelTransitionToken = null;
                this.homeChange = null;
                this.draggedTaskChange = null;
                this.freeformTaskChanges = emptyList;
                this.surfaceLayers = null;
                this.cancelState = cancelState;
                this.startAborted = false;
                this.otherRootChanges = arrayList;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof FromFullscreen)) {
                    return false;
                }
                FromFullscreen fromFullscreen = (FromFullscreen) obj;
                return this.draggedTaskId == fromFullscreen.draggedTaskId && Intrinsics.areEqual(this.dragAnimator, fromFullscreen.dragAnimator) && Intrinsics.areEqual(this.startTransitionToken, fromFullscreen.startTransitionToken) && Intrinsics.areEqual(this.startTransitionFinishCb, fromFullscreen.startTransitionFinishCb) && Intrinsics.areEqual(this.startTransitionFinishTransaction, fromFullscreen.startTransitionFinishTransaction) && Intrinsics.areEqual(this.cancelTransitionToken, fromFullscreen.cancelTransitionToken) && Intrinsics.areEqual(this.homeChange, fromFullscreen.homeChange) && Intrinsics.areEqual(this.draggedTaskChange, fromFullscreen.draggedTaskChange) && Intrinsics.areEqual(this.freeformTaskChanges, fromFullscreen.freeformTaskChanges) && Intrinsics.areEqual(this.surfaceLayers, fromFullscreen.surfaceLayers) && this.cancelState == fromFullscreen.cancelState && this.startAborted == fromFullscreen.startAborted && Intrinsics.areEqual(this.otherRootChanges, fromFullscreen.otherRootChanges);
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final CancelState getCancelState() {
                return this.cancelState;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final IBinder getCancelTransitionToken() {
                return this.cancelTransitionToken;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final MoveToDesktopAnimator getDragAnimator() {
                return this.dragAnimator;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final TransitionInfo.Change getDraggedTaskChange() {
                return this.draggedTaskChange;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final int getDraggedTaskId() {
                return this.draggedTaskId;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final TransitionInfo.Change getHomeChange() {
                return this.homeChange;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final boolean getStartAborted() {
                return this.startAborted;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final Transitions.TransitionFinishCallback getStartTransitionFinishCb() {
                return this.startTransitionFinishCb;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final SurfaceControl.Transaction getStartTransitionFinishTransaction() {
                return this.startTransitionFinishTransaction;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final IBinder getStartTransitionToken() {
                return this.startTransitionToken;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final DragToDesktopLayers getSurfaceLayers() {
                return this.surfaceLayers;
            }

            public final int hashCode() {
                int hashCode = (this.startTransitionToken.hashCode() + ((this.dragAnimator.hashCode() + (Integer.hashCode(this.draggedTaskId) * 31)) * 31)) * 31;
                Transitions.TransitionFinishCallback transitionFinishCallback = this.startTransitionFinishCb;
                int hashCode2 = (hashCode + (transitionFinishCallback == null ? 0 : transitionFinishCallback.hashCode())) * 31;
                SurfaceControl.Transaction transaction = this.startTransitionFinishTransaction;
                int hashCode3 = (hashCode2 + (transaction == null ? 0 : transaction.hashCode())) * 31;
                IBinder iBinder = this.cancelTransitionToken;
                int hashCode4 = (hashCode3 + (iBinder == null ? 0 : iBinder.hashCode())) * 31;
                TransitionInfo.Change change = this.homeChange;
                int hashCode5 = (hashCode4 + (change == null ? 0 : change.hashCode())) * 31;
                TransitionInfo.Change change2 = this.draggedTaskChange;
                int m = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m((hashCode5 + (change2 == null ? 0 : change2.hashCode())) * 31, 31, this.freeformTaskChanges);
                DragToDesktopLayers dragToDesktopLayers = this.surfaceLayers;
                return this.otherRootChanges.hashCode() + TransitionData$$ExternalSyntheticOutline0.m((this.cancelState.hashCode() + ((m + (dragToDesktopLayers != null ? dragToDesktopLayers.hashCode() : 0)) * 31)) * 31, 31, this.startAborted);
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setCancelState(CancelState cancelState) {
                this.cancelState = cancelState;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setCancelTransitionToken(IBinder iBinder) {
                this.cancelTransitionToken = iBinder;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setDraggedTaskChange(TransitionInfo.Change change) {
                this.draggedTaskChange = change;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setFreeformTaskChanges(List list) {
                this.freeformTaskChanges = list;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setHomeChange(TransitionInfo.Change change) {
                this.homeChange = change;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setStartAborted() {
                this.startAborted = true;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setStartTransitionFinishCb(Transitions.TransitionFinishCallback transitionFinishCallback) {
                this.startTransitionFinishCb = transitionFinishCallback;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setStartTransitionFinishTransaction(SurfaceControl.Transaction transaction) {
                this.startTransitionFinishTransaction = transaction;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setSurfaceLayers(DragToDesktopLayers dragToDesktopLayers) {
                this.surfaceLayers = dragToDesktopLayers;
            }

            public final String toString() {
                return "FromFullscreen(draggedTaskId=" + this.draggedTaskId + ", dragAnimator=" + this.dragAnimator + ", startTransitionToken=" + this.startTransitionToken + ", startTransitionFinishCb=" + this.startTransitionFinishCb + ", startTransitionFinishTransaction=" + this.startTransitionFinishTransaction + ", cancelTransitionToken=" + this.cancelTransitionToken + ", homeChange=" + this.homeChange + ", draggedTaskChange=" + this.draggedTaskChange + ", freeformTaskChanges=" + this.freeformTaskChanges + ", surfaceLayers=" + this.surfaceLayers + ", cancelState=" + this.cancelState + ", startAborted=" + this.startAborted + ", otherRootChanges=" + this.otherRootChanges + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class FromSplit extends TransitionState {
            public CancelState cancelState;
            public IBinder cancelTransitionToken;
            public final MoveToDesktopAnimator dragAnimator;
            public TransitionInfo.Change draggedTaskChange;
            public final int draggedTaskId;
            public List freeformTaskChanges;
            public TransitionInfo.Change homeChange;
            public final int otherSplitTask;
            public TransitionInfo.Change splitRootChange;
            public boolean startAborted;
            public Transitions.TransitionFinishCallback startTransitionFinishCb;
            public SurfaceControl.Transaction startTransitionFinishTransaction;
            public final IBinder startTransitionToken;
            public DragToDesktopLayers surfaceLayers;

            public FromSplit(int i, MoveToDesktopAnimator moveToDesktopAnimator, IBinder iBinder, int i2) {
                EmptyList emptyList = EmptyList.INSTANCE;
                CancelState cancelState = CancelState.NO_CANCEL;
                this.draggedTaskId = i;
                this.dragAnimator = moveToDesktopAnimator;
                this.startTransitionToken = iBinder;
                this.startTransitionFinishCb = null;
                this.startTransitionFinishTransaction = null;
                this.cancelTransitionToken = null;
                this.homeChange = null;
                this.draggedTaskChange = null;
                this.freeformTaskChanges = emptyList;
                this.surfaceLayers = null;
                this.cancelState = cancelState;
                this.startAborted = false;
                this.splitRootChange = null;
                this.otherSplitTask = i2;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof FromSplit)) {
                    return false;
                }
                FromSplit fromSplit = (FromSplit) obj;
                return this.draggedTaskId == fromSplit.draggedTaskId && Intrinsics.areEqual(this.dragAnimator, fromSplit.dragAnimator) && Intrinsics.areEqual(this.startTransitionToken, fromSplit.startTransitionToken) && Intrinsics.areEqual(this.startTransitionFinishCb, fromSplit.startTransitionFinishCb) && Intrinsics.areEqual(this.startTransitionFinishTransaction, fromSplit.startTransitionFinishTransaction) && Intrinsics.areEqual(this.cancelTransitionToken, fromSplit.cancelTransitionToken) && Intrinsics.areEqual(this.homeChange, fromSplit.homeChange) && Intrinsics.areEqual(this.draggedTaskChange, fromSplit.draggedTaskChange) && Intrinsics.areEqual(this.freeformTaskChanges, fromSplit.freeformTaskChanges) && Intrinsics.areEqual(this.surfaceLayers, fromSplit.surfaceLayers) && this.cancelState == fromSplit.cancelState && this.startAborted == fromSplit.startAborted && Intrinsics.areEqual(this.splitRootChange, fromSplit.splitRootChange) && this.otherSplitTask == fromSplit.otherSplitTask;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final CancelState getCancelState() {
                return this.cancelState;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final IBinder getCancelTransitionToken() {
                return this.cancelTransitionToken;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final MoveToDesktopAnimator getDragAnimator() {
                return this.dragAnimator;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final TransitionInfo.Change getDraggedTaskChange() {
                return this.draggedTaskChange;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final int getDraggedTaskId() {
                return this.draggedTaskId;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final TransitionInfo.Change getHomeChange() {
                return this.homeChange;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final boolean getStartAborted() {
                return this.startAborted;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final Transitions.TransitionFinishCallback getStartTransitionFinishCb() {
                return this.startTransitionFinishCb;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final SurfaceControl.Transaction getStartTransitionFinishTransaction() {
                return this.startTransitionFinishTransaction;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final IBinder getStartTransitionToken() {
                return this.startTransitionToken;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final DragToDesktopLayers getSurfaceLayers() {
                return this.surfaceLayers;
            }

            public final int hashCode() {
                int hashCode = (this.startTransitionToken.hashCode() + ((this.dragAnimator.hashCode() + (Integer.hashCode(this.draggedTaskId) * 31)) * 31)) * 31;
                Transitions.TransitionFinishCallback transitionFinishCallback = this.startTransitionFinishCb;
                int hashCode2 = (hashCode + (transitionFinishCallback == null ? 0 : transitionFinishCallback.hashCode())) * 31;
                SurfaceControl.Transaction transaction = this.startTransitionFinishTransaction;
                int hashCode3 = (hashCode2 + (transaction == null ? 0 : transaction.hashCode())) * 31;
                IBinder iBinder = this.cancelTransitionToken;
                int hashCode4 = (hashCode3 + (iBinder == null ? 0 : iBinder.hashCode())) * 31;
                TransitionInfo.Change change = this.homeChange;
                int hashCode5 = (hashCode4 + (change == null ? 0 : change.hashCode())) * 31;
                TransitionInfo.Change change2 = this.draggedTaskChange;
                int m = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m((hashCode5 + (change2 == null ? 0 : change2.hashCode())) * 31, 31, this.freeformTaskChanges);
                DragToDesktopLayers dragToDesktopLayers = this.surfaceLayers;
                int m2 = TransitionData$$ExternalSyntheticOutline0.m((this.cancelState.hashCode() + ((m + (dragToDesktopLayers == null ? 0 : dragToDesktopLayers.hashCode())) * 31)) * 31, 31, this.startAborted);
                TransitionInfo.Change change3 = this.splitRootChange;
                return Integer.hashCode(this.otherSplitTask) + ((m2 + (change3 != null ? change3.hashCode() : 0)) * 31);
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setCancelState(CancelState cancelState) {
                this.cancelState = cancelState;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setCancelTransitionToken(IBinder iBinder) {
                this.cancelTransitionToken = iBinder;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setDraggedTaskChange(TransitionInfo.Change change) {
                this.draggedTaskChange = change;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setFreeformTaskChanges(List list) {
                this.freeformTaskChanges = list;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setHomeChange(TransitionInfo.Change change) {
                this.homeChange = change;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setStartAborted() {
                this.startAborted = true;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setStartTransitionFinishCb(Transitions.TransitionFinishCallback transitionFinishCallback) {
                this.startTransitionFinishCb = transitionFinishCallback;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setStartTransitionFinishTransaction(SurfaceControl.Transaction transaction) {
                this.startTransitionFinishTransaction = transaction;
            }

            @Override // com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler.TransitionState
            public final void setSurfaceLayers(DragToDesktopLayers dragToDesktopLayers) {
                this.surfaceLayers = dragToDesktopLayers;
            }

            public final String toString() {
                IBinder iBinder = this.startTransitionToken;
                Transitions.TransitionFinishCallback transitionFinishCallback = this.startTransitionFinishCb;
                SurfaceControl.Transaction transaction = this.startTransitionFinishTransaction;
                IBinder iBinder2 = this.cancelTransitionToken;
                TransitionInfo.Change change = this.homeChange;
                TransitionInfo.Change change2 = this.draggedTaskChange;
                List list = this.freeformTaskChanges;
                DragToDesktopLayers dragToDesktopLayers = this.surfaceLayers;
                CancelState cancelState = this.cancelState;
                boolean z = this.startAborted;
                TransitionInfo.Change change3 = this.splitRootChange;
                StringBuilder sb = new StringBuilder("FromSplit(draggedTaskId=");
                sb.append(this.draggedTaskId);
                sb.append(", dragAnimator=");
                sb.append(this.dragAnimator);
                sb.append(", startTransitionToken=");
                sb.append(iBinder);
                sb.append(", startTransitionFinishCb=");
                sb.append(transitionFinishCallback);
                sb.append(", startTransitionFinishTransaction=");
                sb.append(transaction);
                sb.append(", cancelTransitionToken=");
                sb.append(iBinder2);
                sb.append(", homeChange=");
                sb.append(change);
                sb.append(", draggedTaskChange=");
                sb.append(change2);
                sb.append(", freeformTaskChanges=");
                sb.append(list);
                sb.append(", surfaceLayers=");
                sb.append(dragToDesktopLayers);
                sb.append(", cancelState=");
                sb.append(cancelState);
                sb.append(", startAborted=");
                sb.append(z);
                sb.append(", splitRootChange=");
                sb.append(change3);
                sb.append(", otherSplitTask=");
                return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.otherSplitTask, ")");
            }
        }

        public abstract CancelState getCancelState();

        public abstract IBinder getCancelTransitionToken();

        public abstract MoveToDesktopAnimator getDragAnimator();

        public abstract TransitionInfo.Change getDraggedTaskChange();

        public abstract int getDraggedTaskId();

        public abstract TransitionInfo.Change getHomeChange();

        public abstract boolean getStartAborted();

        public abstract Transitions.TransitionFinishCallback getStartTransitionFinishCb();

        public abstract SurfaceControl.Transaction getStartTransitionFinishTransaction();

        public abstract IBinder getStartTransitionToken();

        public abstract DragToDesktopLayers getSurfaceLayers();

        public abstract void setCancelState(CancelState cancelState);

        public abstract void setCancelTransitionToken(IBinder iBinder);

        public abstract void setDraggedTaskChange(TransitionInfo.Change change);

        public abstract void setFreeformTaskChanges(List list);

        public abstract void setHomeChange(TransitionInfo.Change change);

        public abstract void setStartAborted();

        public abstract void setStartTransitionFinishCb(Transitions.TransitionFinishCallback transitionFinishCallback);

        public abstract void setStartTransitionFinishTransaction(SurfaceControl.Transaction transaction);

        public abstract void setSurfaceLayers(DragToDesktopLayers dragToDesktopLayers);
    }

    public DragToDesktopTransitionHandler(Context context, Transitions transitions, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, InteractionJankMonitor interactionJankMonitor, Supplier supplier) {
        this.context = context;
        this.transitions = transitions;
        this.taskDisplayAreaOrganizer = rootTaskDisplayAreaOrganizer;
        this.interactionJankMonitor = interactionJankMonitor;
        this.transactionSupplier = supplier;
    }

    public static void restoreWindowOrder(WindowContainerTransaction windowContainerTransaction, TransitionState transitionState) {
        WindowContainerToken container;
        WindowContainerToken container2;
        WindowContainerToken container3;
        if (transitionState instanceof TransitionState.FromFullscreen) {
            List list = ((TransitionState.FromFullscreen) transitionState).otherRootChanges;
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                WindowContainerToken container4 = ((TransitionInfo.Change) it.next()).getContainer();
                if (container4 != null) {
                    arrayList.add(container4);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                windowContainerTransaction.reorder((WindowContainerToken) it2.next(), true);
            }
            TransitionInfo.Change change = ((TransitionState.FromFullscreen) transitionState).draggedTaskChange;
            if (change == null || (container3 = change.getContainer()) == null) {
                throw new IllegalStateException("Dragged task should be non-null before cancelling");
            }
            windowContainerTransaction.reorder(container3, true);
        } else if (transitionState instanceof TransitionState.FromSplit) {
            TransitionInfo.Change change2 = ((TransitionState.FromSplit) transitionState).splitRootChange;
            if (change2 == null || (container = change2.getContainer()) == null) {
                throw new IllegalStateException("Split root should be non-null before cancelling");
            }
            windowContainerTransaction.reorder(container, true);
        }
        TransitionInfo.Change homeChange = transitionState.getHomeChange();
        if (homeChange == null || (container2 = homeChange.getContainer()) == null) {
            throw new IllegalStateException("Home task should be non-null before cancelling");
        }
        windowContainerTransaction.restoreTransientOrder(container2);
    }

    public final void cancelDragToDesktopTransition(CancelState cancelState) {
        ActivityManager.RunningTaskInfo taskInfo;
        if (this.transitionState != null) {
            TransitionState requireTransitionState = requireTransitionState();
            if (requireTransitionState.getStartAborted()) {
                this.transitionState = null;
                return;
            }
            requireTransitionState.setCancelState(cancelState);
            if (requireTransitionState.getDraggedTaskChange() != null && cancelState == CancelState.STANDARD_CANCEL) {
                TransitionState requireTransitionState2 = requireTransitionState();
                MoveToDesktopAnimator dragAnimator = requireTransitionState2.getDragAnimator();
                TransitionInfo.Change draggedTaskChange = requireTransitionState2.getDraggedTaskChange();
                if (draggedTaskChange == null) {
                    throw new IllegalStateException("Expected non-null task change");
                }
                final SurfaceControl leash = draggedTaskChange.getLeash();
                dragAnimator.velocityTracker.clear();
                dragAnimator.dragToDesktopAnimator.cancel();
                PointF pointF = dragAnimator.position;
                final float f = pointF.x;
                final float f2 = pointF.y;
                final float f3 = draggedTaskChange.getEndAbsBounds().left - f;
                final float f4 = draggedTaskChange.getEndAbsBounds().top - f2;
                final SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) this.transactionSupplier.get();
                ValueAnimator duration = ValueAnimator.ofFloat(0.4f, 1.0f).setDuration(336L);
                duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler$startCancelAnimation$1$1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        float animatedFraction = valueAnimator.getAnimatedFraction();
                        float f5 = (f3 * animatedFraction) + f;
                        float f6 = (f4 * animatedFraction) + f2;
                        SurfaceControl.Transaction transaction2 = transaction;
                        SurfaceControl surfaceControl = leash;
                        transaction2.setPosition(surfaceControl, f5, f6);
                        transaction2.setScale(surfaceControl, floatValue, floatValue);
                        transaction2.show(surfaceControl);
                        transaction2.apply();
                    }
                });
                duration.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler$startCancelAnimation$1$2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        DesktopTasksController$dragToDesktopStateListener$1 desktopTasksController$dragToDesktopStateListener$1 = DragToDesktopTransitionHandler.this.dragToDesktopStateListener;
                        if (desktopTasksController$dragToDesktopStateListener$1 != null) {
                            SurfaceControl.Transaction transaction2 = transaction;
                            DesktopTasksController desktopTasksController = (DesktopTasksController) desktopTasksController$dragToDesktopStateListener$1.this$0;
                            DesktopModeVisualIndicator desktopModeVisualIndicator = desktopTasksController.visualIndicator;
                            if (desktopModeVisualIndicator != null) {
                                desktopModeVisualIndicator.fadeOutIndicator(new DesktopTasksController$dragToDesktopStateListener$1$removeVisualIndicator$1(desktopTasksController, transaction2));
                            }
                        }
                        DragToDesktopTransitionHandler dragToDesktopTransitionHandler = DragToDesktopTransitionHandler.this;
                        DragToDesktopTransitionHandler.TransitionState requireTransitionState3 = dragToDesktopTransitionHandler.requireTransitionState();
                        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                        DragToDesktopTransitionHandler.restoreWindowOrder(windowContainerTransaction, requireTransitionState3);
                        requireTransitionState3.setCancelTransitionToken(dragToDesktopTransitionHandler.transitions.startTransition(1013, windowContainerTransaction, dragToDesktopTransitionHandler));
                    }
                });
                duration.start();
                return;
            }
            if (requireTransitionState.getDraggedTaskChange() != null) {
                CancelState cancelState2 = CancelState.CANCEL_SPLIT_LEFT;
                if (cancelState == cancelState2 || cancelState == CancelState.CANCEL_SPLIT_RIGHT) {
                    int i = cancelState == cancelState2 ? 0 : 1;
                    WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                    restoreWindowOrder(windowContainerTransaction, requireTransitionState);
                    SurfaceControl.Transaction startTransitionFinishTransaction = requireTransitionState.getStartTransitionFinishTransaction();
                    if (startTransitionFinishTransaction != null) {
                        startTransitionFinishTransaction.apply();
                    }
                    Transitions.TransitionFinishCallback startTransitionFinishCb = requireTransitionState.getStartTransitionFinishCb();
                    if (startTransitionFinishCb != null) {
                        startTransitionFinishCb.onTransitionFinished(null);
                    }
                    TransitionState requireTransitionState3 = requireTransitionState();
                    TransitionInfo.Change draggedTaskChange2 = requireTransitionState3.getDraggedTaskChange();
                    if (draggedTaskChange2 == null || (taskInfo = draggedTaskChange2.getTaskInfo()) == null) {
                        throw new IllegalStateException("Expected non-null taskInfo");
                    }
                    Rect rect = new Rect(taskInfo.configuration.windowConfiguration.getBounds());
                    float scale = requireTransitionState3.getDragAnimator().getScale();
                    float width = rect.width() * scale;
                    float height = rect.height() * scale;
                    PointF pointF2 = new PointF(requireTransitionState3.getDragAnimator().position);
                    MoveToDesktopAnimator dragAnimator2 = requireTransitionState3.getDragAnimator();
                    dragAnimator2.velocityTracker.clear();
                    dragAnimator2.dragToDesktopAnimator.cancel();
                    float f5 = pointF2.x;
                    float f6 = pointF2.y;
                    requestSplitSelect(i, taskInfo, new Rect((int) f5, (int) f6, (int) (f5 + width), (int) (f6 + height)), windowContainerTransaction);
                    this.transitionState = null;
                }
            }
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        return null;
    }

    public final boolean isHomeChange(TransitionInfo.Change change) {
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        if (taskInfo == null || taskInfo.getActivityType() != 2) {
            return false;
        }
        return (taskInfo.isTopActivityTransparent && taskInfo.numActivities == 1) ? false : true;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        DesktopTasksController desktopTasksController;
        DesktopModeVisualIndicator desktopModeVisualIndicator;
        SurfaceControl leash;
        ActivityManager.RunningTaskInfo taskInfo;
        TransitionState requireTransitionState = requireTransitionState();
        if (requireTransitionState.getCancelState() == CancelState.CANCEL_SPLIT_LEFT || requireTransitionState.getCancelState() == CancelState.CANCEL_SPLIT_RIGHT) {
            this.transitionState = null;
            return;
        }
        int i = 0;
        boolean z = transitionInfo.getType() == 1013 && iBinder.equals(requireTransitionState.getCancelTransitionToken()) && iBinder2.equals(requireTransitionState.getStartTransitionToken());
        boolean z2 = transitionInfo.getType() == 1011 && iBinder2.equals(requireTransitionState.getStartTransitionToken());
        SurfaceControl.Transaction startTransitionFinishTransaction = requireTransitionState.getStartTransitionFinishTransaction();
        if (startTransitionFinishTransaction == null) {
            throw new IllegalStateException("Start transition expected to be waiting for merge but wasn't");
        }
        final Transitions.TransitionFinishCallback startTransitionFinishCb = requireTransitionState.getStartTransitionFinishCb();
        if (startTransitionFinishCb == null) {
            throw new IllegalStateException("Start transition expected to be waiting for merge but wasn't");
        }
        if (!z2) {
            if (z) {
                for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                    transaction.show(change.getLeash());
                    startTransitionFinishTransaction.show(change.getLeash());
                }
                transaction.apply();
                transitionFinishCallback.onTransitionFinished(null);
                startTransitionFinishCb.onTransitionFinished(null);
                this.transitionState = null;
                return;
            }
            return;
        }
        TransitionState requireTransitionState2 = requireTransitionState();
        ArrayList arrayList = new ArrayList();
        for (Object obj : transitionInfo.getChanges()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            TransitionInfo.Change change2 = (TransitionInfo.Change) obj;
            if ((requireTransitionState2 instanceof TransitionState.FromSplit) && (taskInfo = change2.getTaskInfo()) != null && taskInfo.taskId == ((TransitionState.FromSplit) requireTransitionState2).otherSplitTask) {
                transaction.hide(change2.getLeash());
                startTransitionFinishTransaction.hide(change2.getLeash());
            } else if (change2.getMode() == 2) {
                transaction.hide(change2.getLeash());
                startTransitionFinishTransaction.hide(change2.getLeash());
            } else {
                ActivityManager.RunningTaskInfo taskInfo2 = change2.getTaskInfo();
                if (taskInfo2 == null || taskInfo2.taskId != requireTransitionState2.getDraggedTaskId()) {
                    ActivityManager.RunningTaskInfo taskInfo3 = change2.getTaskInfo();
                    if (taskInfo3 != null && taskInfo3.getWindowingMode() == 5) {
                        TransitionInfo.Change draggedTaskChange = requireTransitionState2.getDraggedTaskChange();
                        if (draggedTaskChange == null || (leash = draggedTaskChange.getLeash()) == null) {
                            throw new IllegalStateException("Expected dragged leash to be non-null");
                        }
                        int i3 = -i;
                        transaction.setRelativeLayer(change2.getLeash(), leash, i3);
                        startTransitionFinishTransaction.setRelativeLayer(change2.getLeash(), leash, i3);
                        arrayList.add(change2);
                    }
                } else {
                    transaction.show(change2.getLeash());
                    startTransitionFinishTransaction.show(change2.getLeash());
                    requireTransitionState2.setDraggedTaskChange(change2);
                    DragToDesktopLayers surfaceLayers = requireTransitionState2.getSurfaceLayers();
                    if (surfaceLayers != null) {
                        transaction.setLayer(change2.getLeash(), surfaceLayers.dragLayer);
                    }
                }
            }
            i = i2;
        }
        requireTransitionState2.setFreeformTaskChanges(arrayList);
        transitionFinishCallback.onTransitionFinished(null);
        final TransitionState requireTransitionState3 = requireTransitionState();
        TransitionInfo.Change draggedTaskChange2 = requireTransitionState3.getDraggedTaskChange();
        if (draggedTaskChange2 == null) {
            throw new IllegalStateException("Expected non-null change of dragged task");
        }
        final SurfaceControl leash2 = draggedTaskChange2.getLeash();
        Rect startAbsBounds = draggedTaskChange2.getStartAbsBounds();
        Rect endAbsBounds = draggedTaskChange2.getEndAbsBounds();
        MoveToDesktopAnimator dragAnimator = requireTransitionState3.getDragAnimator();
        dragAnimator.velocityTracker.clear();
        dragAnimator.dragToDesktopAnimator.cancel();
        final float scale = requireTransitionState3.getDragAnimator().getScale();
        PointF pointF = requireTransitionState3.getDragAnimator().position;
        int width = startAbsBounds.width();
        int height = startAbsBounds.height();
        int i4 = (int) pointF.x;
        int i5 = (int) pointF.y;
        Rect rect = new Rect(i4, i5, width + i4, height + i5);
        DesktopTasksController$dragToDesktopStateListener$1 desktopTasksController$dragToDesktopStateListener$1 = this.dragToDesktopStateListener;
        if (desktopTasksController$dragToDesktopStateListener$1 != null && (desktopModeVisualIndicator = (desktopTasksController = (DesktopTasksController) desktopTasksController$dragToDesktopStateListener$1.this$0).visualIndicator) != null) {
            desktopModeVisualIndicator.fadeOutIndicator(new DesktopTasksController$dragToDesktopStateListener$1$removeVisualIndicator$1(desktopTasksController, transaction));
        }
        DesktopModeWindowDecorViewModel.DragStartListenerImpl dragStartListenerImpl = this.onTaskResizeAnimationListener;
        (dragStartListenerImpl != null ? dragStartListenerImpl : null).onAnimationStart(requireTransitionState3.getDraggedTaskId(), transaction, rect);
        final SurfaceControl.Transaction transaction2 = (SurfaceControl.Transaction) this.transactionSupplier.get();
        ValueAnimator duration = ValueAnimator.ofObject(this.rectEvaluator, rect, endAbsBounds).setDuration(336L);
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler$animateEndDragToDesktop$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                Rect rect2 = (Rect) valueAnimator.getAnimatedValue();
                float animatedFraction = valueAnimator.getAnimatedFraction();
                float f = scale;
                float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(1, f, animatedFraction, f);
                SurfaceControl.Transaction transaction3 = transaction2;
                SurfaceControl surfaceControl = leash2;
                transaction3.setScale(surfaceControl, m, m);
                transaction3.setPosition(surfaceControl, rect2.left, rect2.top);
                transaction3.setWindowCrop(surfaceControl, rect2.width(), rect2.height());
                DesktopModeWindowDecorViewModel.DragStartListenerImpl dragStartListenerImpl2 = this.onTaskResizeAnimationListener;
                if (dragStartListenerImpl2 == null) {
                    dragStartListenerImpl2 = null;
                }
                dragStartListenerImpl2.onBoundsChange(requireTransitionState3.getDraggedTaskId(), transaction2, rect2);
            }
        });
        duration.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler$animateEndDragToDesktop$1$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                DesktopModeWindowDecorViewModel.DragStartListenerImpl dragStartListenerImpl2 = DragToDesktopTransitionHandler.this.onTaskResizeAnimationListener;
                if (dragStartListenerImpl2 == null) {
                    dragStartListenerImpl2 = null;
                }
                dragStartListenerImpl2.onAnimationEnd(requireTransitionState3.getDraggedTaskId());
                startTransitionFinishCb.onTransitionFinished(null);
                DragToDesktopTransitionHandler dragToDesktopTransitionHandler = DragToDesktopTransitionHandler.this;
                dragToDesktopTransitionHandler.transitionState = null;
                dragToDesktopTransitionHandler.interactionJankMonitor.end(116);
            }
        });
        duration.start();
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        TransitionState transitionState = this.transitionState;
        if (transitionState != null && z) {
            if (Intrinsics.areEqual(transitionState.getStartTransitionToken(), iBinder)) {
                ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DragToDesktop: onTransitionConsumed() start transition aborted", new Object[0]);
                transitionState.setStartAborted();
                this.interactionJankMonitor.cancel(107);
            } else {
                if (Intrinsics.areEqual(transitionState.getCancelTransitionToken(), iBinder)) {
                    return;
                }
                this.interactionJankMonitor.cancel(116);
            }
        }
    }

    public final void requestSplitSelect(int i, ActivityManager.RunningTaskInfo runningTaskInfo, Rect rect, WindowContainerTransaction windowContainerTransaction) {
        if (runningTaskInfo.getWindowingMode() == 6) {
            SplitScreenController splitScreenController = this.splitScreenController;
            SplitScreenController splitScreenController2 = splitScreenController == null ? null : splitScreenController;
            if (splitScreenController == null) {
                splitScreenController = null;
            }
            splitScreenController2.prepareExitSplitScreen(splitScreenController.getStageOfTask(runningTaskInfo.taskId), 12, windowContainerTransaction);
            SplitScreenController splitScreenController3 = this.splitScreenController;
            if (splitScreenController3 == null) {
                splitScreenController3 = null;
            }
            splitScreenController3.getTransitionHandler().setSplitsVisible(false);
        }
        windowContainerTransaction.setWindowingMode(runningTaskInfo.token, 6);
        windowContainerTransaction.setDensityDpi(runningTaskInfo.token, this.context.getResources().getDisplayMetrics().densityDpi);
        SplitScreenController splitScreenController4 = this.splitScreenController;
        (splitScreenController4 != null ? splitScreenController4 : null).requestEnterSplitSelect(i, runningTaskInfo, rect, windowContainerTransaction);
    }

    public final TransitionState requireTransitionState() {
        TransitionState transitionState = this.transitionState;
        if (transitionState != null) {
            return transitionState;
        }
        throw new IllegalStateException("Expected non-null transition state");
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        ActivityManager.RunningTaskInfo taskInfo;
        boolean z;
        TransitionState requireTransitionState = requireTransitionState();
        if (transitionInfo.getType() != 1010 || !iBinder.equals(requireTransitionState.getStartTransitionToken())) {
            return false;
        }
        DragToDesktopLayers dragToDesktopLayers = new DragToDesktopLayers(transitionInfo.getChanges().size(), transitionInfo.getChanges().size() * 2, transitionInfo.getChanges().size() * 3, transitionInfo.getChanges().size() * 3);
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        Iterator it = CollectionsKt.withIndex(transitionInfo.getChanges()).iterator();
        while (true) {
            IndexingIterator indexingIterator = (IndexingIterator) it;
            if (!indexingIterator.iterator.hasNext()) {
                break;
            }
            IndexedValue indexedValue = (IndexedValue) indexingIterator.next();
            int i = indexedValue.index;
            TransitionInfo.Change change = (TransitionInfo.Change) indexedValue.value;
            if (TransitionUtil.isWallpaper(change)) {
                transaction.setLayer(change.getLeash(), dragToDesktopLayers.topWallpaperLayer - i);
                transaction.show(change.getLeash());
            } else if (isHomeChange(change)) {
                requireTransitionState.setHomeChange(change);
                transaction.setLayer(change.getLeash(), dragToDesktopLayers.topHomeLayer - i);
                transaction.show(change.getLeash());
            } else {
                boolean isIndependent = TransitionInfo.isIndependent(change, transitionInfo);
                int i2 = dragToDesktopLayers.dragLayer;
                if (isIndependent) {
                    boolean z2 = requireTransitionState instanceof TransitionState.FromSplit;
                    int i3 = dragToDesktopLayers.topAppLayer;
                    if (z2) {
                        ((TransitionState.FromSplit) requireTransitionState).splitRootChange = change;
                        if (requireTransitionState.getCancelState() == CancelState.NO_CANCEL) {
                            i2 = i3 - i;
                        }
                        transaction.setLayer(change.getLeash(), i2);
                        transaction.show(change.getLeash());
                    } else if (requireTransitionState instanceof TransitionState.FromFullscreen) {
                        ActivityManager.RunningTaskInfo taskInfo2 = change.getTaskInfo();
                        if (taskInfo2 == null || taskInfo2.taskId != requireTransitionState.getDraggedTaskId()) {
                            ((TransitionState.FromFullscreen) requireTransitionState).otherRootChanges.add(change);
                            Rect endAbsBounds = change.getEndAbsBounds();
                            transaction.setLayer(change.getLeash(), i3 - i);
                            transaction.setWindowCrop(change.getLeash(), endAbsBounds.width(), endAbsBounds.height());
                            transaction.show(change.getLeash());
                        } else {
                            requireTransitionState.setDraggedTaskChange(change);
                            Rect endAbsBounds2 = change.getEndAbsBounds();
                            transaction.setLayer(change.getLeash(), i2);
                            transaction.setWindowCrop(change.getLeash(), endAbsBounds2.width(), endAbsBounds2.height());
                            transaction.show(change.getLeash());
                        }
                    }
                } else {
                    ActivityManager.RunningTaskInfo taskInfo3 = change.getTaskInfo();
                    if (taskInfo3 == null) {
                        z = false;
                    } else {
                        boolean z3 = sparseBooleanArray.get(taskInfo3.taskId);
                        if (taskInfo3.hasParentTask()) {
                            sparseBooleanArray.put(taskInfo3.parentTaskId, true);
                        }
                        z = !z3;
                    }
                    if (z) {
                        ActivityManager.RunningTaskInfo taskInfo4 = change.getTaskInfo();
                        if (taskInfo4 != null && taskInfo4.taskId == requireTransitionState.getDraggedTaskId() && requireTransitionState.getCancelState() != CancelState.STANDARD_CANCEL) {
                            requireTransitionState.setDraggedTaskChange(change);
                        }
                        ActivityManager.RunningTaskInfo taskInfo5 = change.getTaskInfo();
                        if (taskInfo5 != null && taskInfo5.taskId == requireTransitionState.getDraggedTaskId() && requireTransitionState.getCancelState() == CancelState.NO_CANCEL) {
                            transaction.reparent(change.getLeash(), (SurfaceControl) this.taskDisplayAreaOrganizer.mLeashes.get(change.getEndDisplayId()));
                            Rect endAbsBounds3 = change.getEndAbsBounds();
                            transaction.setLayer(change.getLeash(), i2);
                            transaction.setWindowCrop(change.getLeash(), endAbsBounds3.width(), endAbsBounds3.height());
                            transaction.show(change.getLeash());
                        }
                    }
                }
            }
        }
        requireTransitionState.setSurfaceLayers(dragToDesktopLayers);
        requireTransitionState.setStartTransitionFinishCb(transitionFinishCallback);
        requireTransitionState.setStartTransitionFinishTransaction(transaction2);
        transaction.apply();
        if (requireTransitionState.getCancelState() == CancelState.NO_CANCEL) {
            MoveToDesktopAnimator dragAnimator = requireTransitionState.getDragAnimator();
            dragAnimator.allowSurfaceChangesOnMove = true;
            dragAnimator.dragToDesktopAnimator.start();
        } else if (requireTransitionState.getCancelState() == CancelState.STANDARD_CANCEL) {
            TransitionState requireTransitionState2 = requireTransitionState();
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            restoreWindowOrder(windowContainerTransaction, requireTransitionState2);
            requireTransitionState2.setCancelTransitionToken(this.transitions.startTransition(1013, windowContainerTransaction, this));
        } else {
            CancelState cancelState = requireTransitionState.getCancelState();
            CancelState cancelState2 = CancelState.CANCEL_SPLIT_LEFT;
            if (cancelState == cancelState2 || requireTransitionState.getCancelState() == CancelState.CANCEL_SPLIT_RIGHT) {
                int i4 = requireTransitionState.getCancelState() != cancelState2 ? 1 : 0;
                TransitionInfo.Change draggedTaskChange = requireTransitionState.getDraggedTaskChange();
                if (draggedTaskChange == null || (taskInfo = draggedTaskChange.getTaskInfo()) == null) {
                    throw new IllegalStateException("Expected non-null task info.");
                }
                WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
                restoreWindowOrder(windowContainerTransaction2, requireTransitionState());
                SurfaceControl.Transaction startTransitionFinishTransaction = requireTransitionState.getStartTransitionFinishTransaction();
                if (startTransitionFinishTransaction != null) {
                    startTransitionFinishTransaction.apply();
                }
                Transitions.TransitionFinishCallback startTransitionFinishCb = requireTransitionState.getStartTransitionFinishCb();
                if (startTransitionFinishCb != null) {
                    startTransitionFinishCb.onTransitionFinished(null);
                }
                requestSplitSelect(i4, taskInfo, new Rect(taskInfo.configuration.windowConfiguration.getBounds()), windowContainerTransaction2);
            }
        }
        return true;
    }
}
