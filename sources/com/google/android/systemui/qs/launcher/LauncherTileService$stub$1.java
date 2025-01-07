package com.google.android.systemui.qs.launcher;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.people.PeopleSpaceUtils;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tileimpl.SubtitleArrayMapping;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LauncherTileService$stub$1 extends Binder implements IInterface {
    public final /* synthetic */ LauncherTileService this$0;

    public LauncherTileService$stub$1(LauncherTileService launcherTileService) {
        this.this$0 = launcherTileService;
        attachInterface(this, "com.google.android.systemui.qs.launcher.ILauncherTileService");
    }

    public final int getMaxTransactionId() {
        return 3;
    }

    public final QSTile getTile(String str, boolean z) {
        Object obj;
        Iterator it = ((QSHostAdapter) this.this$0.qsHost).getTiles().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((QSTile) obj).getTileSpec(), str)) {
                break;
            }
        }
        QSTile qSTile = (QSTile) obj;
        if (qSTile != null) {
            return qSTile;
        }
        if (str.startsWith("custom(")) {
            Log.w("LauncherTileService", String.format("Can't create a custom tile %s.", Arrays.copyOf(new Object[]{str}, 1)));
            return null;
        }
        QSTile qSTile2 = (QSTile) this.this$0.createdTilesMap.get(str);
        if (qSTile2 != null) {
            return qSTile2;
        }
        if (!z) {
            Log.i("LauncherTileService", String.format("Tile %s should be already created.", Arrays.copyOf(new Object[]{str}, 1)));
            return null;
        }
        QSHostAdapter qSHostAdapter = (QSHostAdapter) this.this$0.qsHost;
        qSHostAdapter.getClass();
        TileSpec create = TileSpec.Companion.create(str);
        CurrentTilesInteractorImpl currentTilesInteractorImpl = (CurrentTilesInteractorImpl) qSHostAdapter.interactor;
        currentTilesInteractorImpl.featureFlags.getClass();
        QSTile createTile = currentTilesInteractorImpl.tileFactory.createTile(create.getSpec());
        if (createTile != null) {
            createTile.setTileSpec(str);
            if (!createTile.isAvailable()) {
                createTile.destroy();
                Log.e("LauncherTileService", String.format("Tile %s is not available.", Arrays.copyOf(new Object[]{str}, 1)));
                return null;
            }
            this.this$0.createdTilesMap.put(str, createTile);
        } else {
            Log.e("LauncherTileService", String.format("The created tile %s is null.", Arrays.copyOf(new Object[]{str}, 1)));
        }
        return createTile;
    }

    public final String getTransactionName(int i) {
        if (i == 1) {
            return "addTileListener";
        }
        if (i == 2) {
            return "clearTileListeners";
        }
        if (i == 3) {
            return "handleClick";
        }
        if (i != 4) {
            return null;
        }
        return "handleLongClick";
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        final ILauncherTileListener$Stub$Proxy iLauncherTileListener$Stub$Proxy;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.google.android.systemui.qs.launcher.ILauncherTileService");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.google.android.systemui.qs.launcher.ILauncherTileService");
            return true;
        }
        if (i == 1) {
            final String readString = parcel.readString();
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                iLauncherTileListener$Stub$Proxy = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.systemui.qs.launcher.ILauncherTileListener");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof ILauncherTileListener$Stub$Proxy)) {
                    ILauncherTileListener$Stub$Proxy iLauncherTileListener$Stub$Proxy2 = new ILauncherTileListener$Stub$Proxy();
                    iLauncherTileListener$Stub$Proxy2.mRemote = readStrongBinder;
                    iLauncherTileListener$Stub$Proxy = iLauncherTileListener$Stub$Proxy2;
                } else {
                    iLauncherTileListener$Stub$Proxy = (ILauncherTileListener$Stub$Proxy) queryLocalInterface;
                }
            }
            parcel.enforceNoDataAvail();
            this.this$0.executor.execute(new Runnable() { // from class: com.google.android.systemui.qs.launcher.LauncherTileService$stub$1$addTileListener$1
                @Override // java.lang.Runnable
                public final void run() {
                    LauncherTileService$stub$1 launcherTileService$stub$1 = LauncherTileService$stub$1.this;
                    final String str = readString;
                    final ILauncherTileListener$Stub$Proxy iLauncherTileListener$Stub$Proxy3 = iLauncherTileListener$Stub$Proxy;
                    QSTile tile = launcherTileService$stub$1.getTile(str, true);
                    if (tile == null) {
                        TileState tileState = new TileState();
                        tileState.mUnSupported = true;
                        tileState.mTileSpec = str;
                        iLauncherTileListener$Stub$Proxy3.onTileUpdated(tileState);
                        return;
                    }
                    final LauncherTileService launcherTileService = launcherTileService$stub$1.this$0;
                    QSTile.Callback callback = new QSTile.Callback() { // from class: com.google.android.systemui.qs.launcher.LauncherTileService$stub$1$addTileListenerInternal$callback$1
                        @Override // com.android.systemui.plugins.qs.QSTile.Callback
                        public final void onStateChanged(QSTile.State state) {
                            TileState tileState2 = new TileState();
                            tileState2.mTileSpec = str;
                            if (state.disabledByPolicy) {
                                tileState2.mState = 0;
                            } else {
                                tileState2.mState = state.state;
                            }
                            tileState2.mLabel = state.label;
                            HashMap hashMap = SubtitleArrayMapping.subtitleIdsMap;
                            int subtitleId = SubtitleArrayMapping.getSubtitleId(state.spec);
                            LauncherTileService launcherTileService2 = launcherTileService;
                            tileState2.mSubtitle = state.getSecondaryLabel(state.getStateText(subtitleId, launcherTileService2.getApplicationContext().getResources()));
                            tileState2.mIsTransient = state.isTransient;
                            Supplier supplier = state.iconSupplier;
                            QSTile.Icon icon = supplier != null ? (QSTile.Icon) supplier.get() : state.icon;
                            if (icon instanceof QSTileImpl.ResourceIcon) {
                                tileState2.mIcon = Icon.createWithResource(launcherTileService2.getApplicationContext(), ((QSTileImpl.ResourceIcon) icon).mResId);
                            } else if (icon instanceof QSTileImpl.DrawableIconWithRes) {
                                ((QSTileImpl.DrawableIconWithRes) icon).getClass();
                                tileState2.mIcon = Icon.createWithResource(launcherTileService2.getApplicationContext(), 0);
                            } else if (icon != null) {
                                Bitmap convertDrawableToBitmap = PeopleSpaceUtils.convertDrawableToBitmap(icon.getDrawable(launcherTileService2.getApplicationContext()));
                                tileState2.mIcon = Icon.createWithBitmap(convertDrawableToBitmap);
                                if (convertDrawableToBitmap != null) {
                                    convertDrawableToBitmap.recycle();
                                }
                            } else {
                                Log.d("LauncherTileService", "The icon is null from QS tile state");
                            }
                            tileState2.mSupportClick = !state.disabledByPolicy;
                            tileState2.mSupportLongClick = state.handlesLongClick;
                            tileState2.mShowChevron = !(state instanceof QSTile.AdapterState) || ((QSTile.AdapterState) state).forceExpandIcon;
                            tileState2.mContentDescription = state.contentDescription;
                            tileState2.mStateDescription = state.stateDescription;
                            try {
                                iLauncherTileListener$Stub$Proxy3.onTileUpdated(tileState2);
                            } catch (RemoteException e) {
                                Log.e("LauncherTileService", "Failed to call onTileUpdated", e);
                            }
                        }
                    };
                    if (launcherTileService.callbacksMap.get(str) == null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(callback);
                        launcherTileService$stub$1.this$0.callbacksMap.put(str, arrayList);
                    } else {
                        List list = (List) launcherTileService$stub$1.this$0.callbacksMap.get(str);
                        if (list != null) {
                            list.add(callback);
                        }
                    }
                    tile.addCallback(callback);
                    tile.setListening(launcherTileService$stub$1, true);
                }
            });
            parcel2.writeNoException();
        } else if (i == 2) {
            String readString2 = parcel.readString();
            parcel.enforceNoDataAvail();
            this.this$0.executor.execute(new LauncherTileService$stub$1$handleClick$1(this, readString2, 1));
            parcel2.writeNoException();
        } else if (i == 3) {
            String readString3 = parcel.readString();
            parcel.enforceNoDataAvail();
            this.this$0.executor.execute(new LauncherTileService$stub$1$handleClick$1(this, readString3, 0));
            parcel2.writeNoException();
        } else {
            if (i != 4) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            String readString4 = parcel.readString();
            parcel.enforceNoDataAvail();
            this.this$0.executor.execute(new LauncherTileService$stub$1$handleClick$1(this, readString4, 2));
            parcel2.writeNoException();
        }
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}
