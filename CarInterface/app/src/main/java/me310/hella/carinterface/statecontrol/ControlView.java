package me310.hella.carinterface.statecontrol;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

import me310.hella.carinterface.FullscreenActivity;

public abstract class ControlView {

    protected static int defaultColor = Color.parseColor("#FFFFFF");
    protected static int activatedColor = Color.parseColor("#FF0000");
    protected boolean activated = false;
    protected Button topLeftButton;
    protected Button topRightButton;
    protected Button bottomRightButton;
    protected Button bottomLeftButton;
    protected List<Button> buttons;
    protected Context ctx;

    protected ImageView imageView;
    protected final int REQUIRED_IMAGE_WIDTH = 800;
    protected final int REQUIRED_IMAGE_HEIGHT = 800;
    protected Resources resources; // TODO: set them


    public ControlView(List<Button> buttons) {
        this.ctx = FullscreenActivity.ctx;
        this.buttons = buttons;
        this.topLeftButton = buttons.get(0);
        this.topRightButton = buttons.get(1);
        this.bottomRightButton = buttons.get(2);
        this.bottomLeftButton = buttons.get(3);
        topLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topLeft().show();
            }
        });
        topRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRight().show();
            }
        });
        bottomRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomRight().show();
            }
        });
    }

    public ControlView(){}

    public ControlView doAction(Triggers t) {
        switch (t) {
            case TOP_LEFT:
                return topLeft();
            case TOP_RIGHT:
                return topRight(); //This one should be BACK
            case MIDDLE_LEFT:
                return middleLeft();
            case MIDDLE_RIGHT:
                return middleRight();
            case BOTTOM_LEFT:
                return bottomLeft();
            case BOTTOM_RIGHT:
                return bottomRight();
            default:
                return this;
        }
    }

    public abstract ControlView topLeft();
    public abstract ControlView topRight();
    public abstract ControlView middleLeft();
    public abstract ControlView middleRight();
    public abstract ControlView bottomLeft();
    public abstract ControlView bottomRight();

    public abstract void show();

    protected static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                          int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    protected static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
