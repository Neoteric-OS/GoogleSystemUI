package com.android.systemui.qs.external;

import android.app.IUriGrantsManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.RemoteException;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.internal.logging.InstanceId;
import com.android.internal.statusbar.IAddTileResultCallback;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.external.TileServiceRequestController;
import com.android.systemui.qs.external.TileServiceRequestController.TileServiceRequestCommand;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tileimpl.QSTileViewImpl;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.commandline.Command;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TileServiceRequestController {
    public final CommandQueue commandQueue;
    public final TileServiceRequestController$commandQueueCallback$1 commandQueueCallback;
    public final CommandRegistry commandRegistry;
    public Function1 dialogCanceller;
    public final Function0 dialogCreator;
    public final TileRequestDialogEventLogger eventLogger;
    public final IUriGrantsManager iUriGrantsManager;
    public final QSHost qsHost;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.external.TileServiceRequestController$1, reason: invalid class name */
    final class AnonymousClass1 extends Lambda implements Function0 {
        final /* synthetic */ QSHost $qsHost;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(QSHost qSHost) {
            super(0);
            this.$qsHost = qSHost;
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new TileRequestDialog(((QSHostAdapter) this.$qsHost).context);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final CommandQueue commandQueue;
        public final CommandRegistry commandRegistry;
        public final IUriGrantsManager iUriGrantsManager;

        public Builder(CommandQueue commandQueue, CommandRegistry commandRegistry, IUriGrantsManager iUriGrantsManager) {
            this.commandQueue = commandQueue;
            this.commandRegistry = commandRegistry;
            this.iUriGrantsManager = iUriGrantsManager;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SingleShotConsumer implements Consumer {
        public final TileServiceRequestController$requestTileAdd$dialogResponse$1 consumer;
        public final AtomicBoolean dispatched = new AtomicBoolean(false);

        public SingleShotConsumer(TileServiceRequestController$requestTileAdd$dialogResponse$1 tileServiceRequestController$requestTileAdd$dialogResponse$1) {
            this.consumer = tileServiceRequestController$requestTileAdd$dialogResponse$1;
        }

        @Override // java.util.function.Consumer
        public final void accept(Object obj) {
            if (this.dispatched.compareAndSet(false, true)) {
                this.consumer.accept(obj);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TileServiceRequestCommand implements Command {
        public TileServiceRequestCommand() {
        }

        @Override // com.android.systemui.statusbar.commandline.Command
        public final void execute(PrintWriter printWriter, List list) {
            ComponentName unflattenFromString = ComponentName.unflattenFromString((String) list.get(0));
            if (unflattenFromString == null) {
                Log.w("TileServiceRequestController", "Malformed componentName " + list.get(0));
                return;
            }
            TileServiceRequestController.this.requestTileAdd$frameworks__base__packages__SystemUI__android_common__SystemUI_core(0, unflattenFromString, (CharSequence) list.get(1), (CharSequence) list.get(2), null, TileServiceRequestController$TileServiceRequestCommand$execute$1.INSTANCE);
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.systemui.qs.external.TileServiceRequestController$commandQueueCallback$1] */
    public TileServiceRequestController(QSHost qSHost, CommandQueue commandQueue, CommandRegistry commandRegistry, TileRequestDialogEventLogger tileRequestDialogEventLogger, IUriGrantsManager iUriGrantsManager) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(qSHost);
        this.qsHost = qSHost;
        this.commandQueue = commandQueue;
        this.commandRegistry = commandRegistry;
        this.eventLogger = tileRequestDialogEventLogger;
        this.iUriGrantsManager = iUriGrantsManager;
        this.dialogCreator = anonymousClass1;
        this.commandQueueCallback = new CommandQueue.Callbacks() { // from class: com.android.systemui.qs.external.TileServiceRequestController$commandQueueCallback$1
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void cancelRequestAddTile(String str) {
                Function1 function1 = TileServiceRequestController.this.dialogCanceller;
                if (function1 != null) {
                    function1.invoke(str);
                }
            }

            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void requestAddTile(int i, ComponentName componentName, CharSequence charSequence, CharSequence charSequence2, Icon icon, final IAddTileResultCallback iAddTileResultCallback) {
                TileServiceRequestController.this.requestTileAdd$frameworks__base__packages__SystemUI__android_common__SystemUI_core(i, componentName, charSequence, charSequence2, icon, new Consumer() { // from class: com.android.systemui.qs.external.TileServiceRequestController$commandQueueCallback$1$requestAddTile$1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        try {
                            iAddTileResultCallback.onTileRequest(((Integer) obj).intValue());
                        } catch (RemoteException e) {
                            Log.e("TileServiceRequestController", "Couldn't respond to request", e);
                        }
                    }
                });
            }
        };
    }

    public final void init() {
        this.commandRegistry.registerCommand("tile-service-add", new Function0() { // from class: com.android.systemui.qs.external.TileServiceRequestController$init$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TileServiceRequestController.this.new TileServiceRequestCommand();
            }
        });
        this.commandQueue.addCallback((CommandQueue.Callbacks) this.commandQueueCallback);
    }

    public final void requestTileAdd$frameworks__base__packages__SystemUI__android_common__SystemUI_core(int i, ComponentName componentName, CharSequence charSequence, CharSequence charSequence2, Icon icon, Consumer consumer) {
        Drawable loadDrawableCheckingUriGrant;
        TileRequestDialogEventLogger tileRequestDialogEventLogger = this.eventLogger;
        InstanceId newInstanceId = tileRequestDialogEventLogger.instanceIdSequence.newInstanceId();
        final String packageName = componentName.getPackageName();
        if (((QSHostAdapter) this.qsHost).getSpecs().indexOf(CustomTile.toSpec(componentName)) != -1) {
            consumer.accept(1);
            tileRequestDialogEventLogger.uiEventLogger.logWithInstanceId(TileRequestDialogEvent.TILE_REQUEST_DIALOG_TILE_ALREADY_ADDED, 0, packageName, newInstanceId);
            return;
        }
        final SingleShotConsumer singleShotConsumer = new SingleShotConsumer(new TileServiceRequestController$requestTileAdd$dialogResponse$1(this, componentName, packageName, newInstanceId, consumer));
        String packageName2 = componentName.getPackageName();
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.android.systemui.qs.external.TileServiceRequestController$createDialog$dialogClickListener$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                if (i2 == -1) {
                    TileServiceRequestController.SingleShotConsumer.this.accept(2);
                } else {
                    TileServiceRequestController.SingleShotConsumer.this.accept(0);
                }
            }
        };
        Object invoke = ((AnonymousClass1) this.dialogCreator).invoke();
        TileRequestDialog tileRequestDialog = (TileRequestDialog) invoke;
        IUriGrantsManager iUriGrantsManager = this.iUriGrantsManager;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(tileRequestDialog.getContext()).inflate(R.layout.tile_service_request_dialog, (ViewGroup) null);
        TextView textView = (TextView) viewGroup.requireViewById(R.id.text);
        textView.setText(textView.getContext().getString(R.string.qs_tile_request_dialog_text, charSequence));
        final QSTileViewImpl qSTileViewImpl = new QSTileViewImpl(new ContextThemeWrapper(tileRequestDialog.getContext(), R.style.Theme_SystemUI_QuickSettings), true, null);
        QSTile.BooleanState booleanState = new QSTile.BooleanState();
        booleanState.label = charSequence2;
        booleanState.handlesLongClick = false;
        booleanState.icon = (icon == null || (loadDrawableCheckingUriGrant = icon.loadDrawableCheckingUriGrant(tileRequestDialog.getContext(), iUriGrantsManager, i, packageName2)) == null) ? QSTileImpl.ResourceIcon.get(R.drawable.android) : new QSTileImpl.DrawableIcon(loadDrawableCheckingUriGrant);
        booleanState.contentDescription = booleanState.label;
        qSTileViewImpl.onStateChanged(booleanState);
        qSTileViewImpl.post(new Runnable() { // from class: com.android.systemui.qs.external.TileRequestDialog$createTileView$1
            @Override // java.lang.Runnable
            public final void run() {
                QSTileViewImpl.this.setStateDescription("");
                QSTileViewImpl.this.setClickable(false);
                QSTileViewImpl.this.setSelected(true);
            }
        });
        viewGroup.addView(qSTileViewImpl, viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.qs_tile_service_request_tile_width), viewGroup.getContext().getResources().getDimensionPixelSize(R.dimen.qs_quick_tile_size));
        viewGroup.setSelected(true);
        tileRequestDialog.setView(viewGroup, 0, 0, 0, 0);
        SystemUIDialog.setShowForAllUsers(tileRequestDialog);
        tileRequestDialog.setCanceledOnTouchOutside(true);
        tileRequestDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.systemui.qs.external.TileServiceRequestController$createDialog$1$1
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                TileServiceRequestController.SingleShotConsumer.this.accept(3);
            }
        });
        tileRequestDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.qs.external.TileServiceRequestController$createDialog$1$2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                TileServiceRequestController.SingleShotConsumer.this.accept(3);
            }
        });
        tileRequestDialog.setPositiveButton(R.string.qs_tile_request_dialog_add, onClickListener);
        tileRequestDialog.setNegativeButton$1(R.string.qs_tile_request_dialog_not_add, onClickListener);
        final SystemUIDialog systemUIDialog = (SystemUIDialog) invoke;
        this.dialogCanceller = new Function1() { // from class: com.android.systemui.qs.external.TileServiceRequestController$requestTileAdd$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                if (Intrinsics.areEqual(packageName, (String) obj)) {
                    systemUIDialog.cancel();
                }
                this.dialogCanceller = null;
                return Unit.INSTANCE;
            }
        };
        systemUIDialog.show();
        tileRequestDialogEventLogger.uiEventLogger.logWithInstanceId(TileRequestDialogEvent.TILE_REQUEST_DIALOG_SHOWN, 0, packageName, newInstanceId);
    }
}
