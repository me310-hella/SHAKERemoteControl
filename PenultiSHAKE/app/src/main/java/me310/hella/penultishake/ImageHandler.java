package me310.hella.penultishake;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ImageHandler extends Activity{
    private ImageView mImageView;
    private Resources mResources;
    private int currentImageIDIndex;
    private final int REQUIRED_IMAGE_WIDTH = 800;
    private final int REQUIRED_IMAGE_HEIGHT = 800;
    private final int[] imageIDs = {
            R.drawable.draft_page_01,
            R.drawable.draft_page_02,
            R.drawable.draft_page_03,
            R.drawable.draft_page_04,
            R.drawable.draft_page_05,
            R.drawable.draft_page_06,
            R.drawable.draft_page_07,
            R.drawable.draft_page_08,
            R.drawable.draft_page_09,
            R.drawable.draft_page_10,
            R.drawable.draft_page_11,
            R.drawable.draft_page_12,
            R.drawable.draft_page_13,
            R.drawable.draft_page_14,
            R.drawable.draft_page_15,
            R.drawable.draft_page_16,
            R.drawable.draft_page_17,
            R.drawable.draft_page_18,
            R.drawable.draft_page_19,
            R.drawable.draft_page_20,
            R.drawable.draft_page_21,
            R.drawable.draft_page_22,
            R.drawable.draft_page_23,
    };

    public ImageHandler(ImageView imageView, Resources resources){
        currentImageIDIndex = 0;
        mResources = resources;
        mImageView = imageView;
        mImageView.setImageBitmap(
                decodeSampledBitmapFromResource(mResources, R.drawable.draft_page_01, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));

    }

    public void nextImage(){
        runOnUiThread(new Runnable() {
            public void run() {
                currentImageIDIndex = currentImageIDIndex < imageIDs.length -1 ? currentImageIDIndex + 1 : 0;
                mImageView.setImageBitmap(
                        decodeSampledBitmapFromResource(mResources, imageIDs[currentImageIDIndex], REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
            }
        });
    }

    public void previousImage(){
        runOnUiThread(new Runnable() {
            public void run() {
                currentImageIDIndex = currentImageIDIndex > 0 ? currentImageIDIndex - 1 : imageIDs.length - 1;
                mImageView.setImageBitmap(
                        decodeSampledBitmapFromResource(mResources, imageIDs[currentImageIDIndex], REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
            }
        });
    }

    public static int calculateInSampleSize(
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

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
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

}
