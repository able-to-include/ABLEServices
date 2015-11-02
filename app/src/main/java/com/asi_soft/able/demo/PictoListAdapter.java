package com.asi_soft.able.demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by javier on 5/02/15.
 */
public class PictoListAdapter extends ArrayAdapter<String> {

    Context mContext;
    List<String> objects;

    public PictoListAdapter(Context context, int textViewResourceId,
                              List<String> objects) {
        super(context, textViewResourceId, objects);
        this.mContext = context;
        this.objects = objects;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder;

    /*
     * The convertView argument is essentially a "ScrapView" as described is Lucas post
     * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
     * It will have a non-null value when ListView is asking you recycle the row layout.
     * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
     */
        if(convertView==null){

            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.pictos_list_item, parent, false);

            // well set up the ViewHolder
            viewHolder = new ViewHolderItem();

            viewHolder.pictoImage = (ImageView) convertView.findViewById(R.id.pictoImage);
            viewHolder.pictoName = (TextView) convertView.findViewById(R.id.pictoText);

            // store the holder with the view.
            convertView.setTag(viewHolder);

        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        // object item based on the position
        String text = objects.get(position);

        // assign values if the object is not null
        if(text != null) {
            //if (text.startsWith("http://")) {
            // get the TextView from the ViewHolder and then set the text (item name) and tag (item ID) values
            BitmapWorkerTask task = new BitmapWorkerTask(viewHolder.pictoImage);
            task.execute(text);
            if (text.startsWith("http://")) {
                viewHolder.pictoName.setText("");
            } else {
                viewHolder.pictoName.setText(text);
            }

        }

        return convertView;

    }

    static class ViewHolderItem {
        ImageView pictoImage;
        TextView pictoName;
    }


    class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
        private final WeakReference<ImageView> imageViewReference;
        private String data;

        public BitmapWorkerTask(ImageView imageView) {
            // Use a WeakReference to ensure the ImageView can be garbage
            // collected
            imageViewReference = new WeakReference<>(imageView);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(String... params) {
            // Initialize transparent Bitmap
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.transparent);
            // Get pictogram URL from params (if any)
            data = params[0];
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(data)
                        .getContent());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Return bitmap (transparent if the pictogram does not exist)
            return bitmap;
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (imageViewReference != null && bitmap != null) {
                final ImageView imageView = imageViewReference.get();
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}