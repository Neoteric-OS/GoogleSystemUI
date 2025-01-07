package androidx.slice.widget;

import android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.SliceItem;
import androidx.slice.core.SliceQuery;
import com.android.systemui.volume.VolumePanelDialog$$ExternalSyntheticLambda1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class MessageView extends SliceChildView {
    public TextView mDetails;
    public ImageView mIcon;

    public MessageView(Context context) {
        super(context);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mDetails = (TextView) findViewById(R.id.summary);
        this.mIcon = (ImageView) findViewById(R.id.icon);
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void setSliceItem(SliceContent sliceContent, boolean z, int i, int i2, VolumePanelDialog$$ExternalSyntheticLambda1 volumePanelDialog$$ExternalSyntheticLambda1) {
        Drawable loadDrawable;
        SliceItem sliceItem = sliceContent.mSliceItem;
        SliceItem findSubtype = SliceQuery.findSubtype(sliceItem, "image", "source");
        if (findSubtype != null) {
            Object obj = findSubtype.mObj;
            if (((IconCompat) obj) != null && (loadDrawable = ((IconCompat) obj).loadDrawable(getContext())) != null) {
                int applyDimension = (int) TypedValue.applyDimension(1, 24.0f, getContext().getResources().getDisplayMetrics());
                Bitmap createBitmap = Bitmap.createBitmap(applyDimension, applyDimension, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                loadDrawable.setBounds(0, 0, applyDimension, applyDimension);
                loadDrawable.draw(canvas);
                ImageView imageView = this.mIcon;
                Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap.getWidth(), createBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap2);
                Paint paint = new Paint();
                Rect rect = new Rect(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
                paint.setAntiAlias(true);
                canvas2.drawARGB(0, 0, 0, 0);
                canvas2.drawCircle(createBitmap.getWidth() / 2, createBitmap.getHeight() / 2, createBitmap.getWidth() / 2, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas2.drawBitmap(createBitmap, rect, rect, paint);
                imageView.setImageBitmap(createBitmap2);
            }
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (SliceItem sliceItem2 : SliceQuery.findAll(sliceItem, "text", null, null)) {
            if (spannableStringBuilder.length() != 0) {
                spannableStringBuilder.append('\n');
            }
            spannableStringBuilder.append(sliceItem2.getSanitizedText());
        }
        this.mDetails.setText(spannableStringBuilder.toString());
    }

    @Override // androidx.slice.widget.SliceChildView
    public final void resetView() {
    }
}
