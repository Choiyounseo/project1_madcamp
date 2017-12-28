package com.example.user.album_tabb;

import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static com.example.user.album_tabb.R.id.galleryGridView;

public class FragmentB extends Fragment {

    GridView gv;
    GalleryGridAdapter gAdapter;

    LinearLayout XImg;
    FloatingActionButton FABAddImg;
    SeekBar seekBar;
    TextView seekText;
    ArrayList<String> storedImgPath = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2gallery, container, false);

        XImg = rootView.findViewById(R.id.Images);

        loadImageFromStorage();

        FABAddImg = rootView.findViewById(R.id.fab_add);
        gv = rootView.findViewById(galleryGridView);
        seekBar = rootView.findViewById(R.id.gall_seekbar);
        seekText = rootView.findViewById(R.id.gall_seekcnt);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Integer seek_cnt = seekBar.getProgress() + 1;
                String seek_text = String.valueOf(seek_cnt) + " in a row";
                seekText.setText(seek_text);
                gv.setNumColumns(seek_cnt);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        FABAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {

            if (storedImgPath.size() == 0) {
                XImg.setVisibility(View.GONE);
            }
            gAdapter = new GalleryGridAdapter(getContext());

            Toast.makeText(getContext(), "Added Image to Gallery", Toast.LENGTH_LONG).show();
//            Toast.makeText(getContext(), "Added Image to Gallery", Toast.LENGTH_LONG).show();

            // Let's read picked image data - its URI
            Uri uri = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = {MediaStore.Images.Media.DATA};
            gv.setAdapter(gAdapter);
            Cursor cursor = getContext().getContentResolver().query(uri, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            cursor.close();

            Bitmap image = BitmapFactory.decodeFile(imagePath);
            storeImage(image);
            gAdapter.notifyDataSetChanged();
        }

    }

    private String getInternalPath() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getContext().getApplicationContext().getPackageName()
                + "/Files/tabB");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        String mImageName = "ChanRong_" + timeStamp + ".jpg";
        return mediaStorageDir.getPath() + File.separator + mImageName;
    }

    /* Create a File for saving an image or video */
    private File getOutputMediaFile(String path) {
        if (path == null) {
            return null;
        }
        File mediaFile;
        mediaFile = new File(path);
        return mediaFile;
    }

    private void storeImage(Bitmap image) {
        String internalPath = getInternalPath();
        File pictureFile = getOutputMediaFile(internalPath);

        if (pictureFile == null) {
            Log.d("TAG", "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            storedImgPath.add(internalPath);
        } catch (FileNotFoundException e) {
            Log.d("TAG", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("TAG", "Error accessing file: " + e.getMessage());
        }
    }

    public void loadImageFromStorage() {
        String storagePath = Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getContext().getApplicationContext().getPackageName()
                + "/Files/tabB";
        File[] ImgFiles = (new File(storagePath).listFiles());
        if (ImgFiles != null) {
            for (int i = 0; i < ImgFiles.length; i++) {
                if (!ImgFiles[i].isDirectory()) {
                    storedImgPath.add(String.valueOf(ImgFiles[i]));
                }
            }
        } else {
            XImg.setVisibility(View.VISIBLE);
        }
    }

    public class GalleryGridAdapter extends BaseAdapter {
        Context context;

        public GalleryGridAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return storedImgPath.size();
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int arg0) {
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            LinearLayout linear = new LinearLayout(context);
            linear.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, 500));
            linear.setPadding(10, 10, 10, 10);

            final ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);

            Bitmap bitmap = BitmapFactory.decodeFile(storedImgPath.get(position));
            //Log.i("test", bitmap.getWidth() + ", " + bitmap.getHeight());
            BitmapFactory.Options options = new BitmapFactory.Options();
       //     options.inSampleSize = 4;
            final Bitmap resized = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/4, bitmap.getHeight()/4, true);
            imageview.setImageBitmap(resized);

            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final String[] actions = new String[]{"Show Image", "Delete Image"};
                    AlertDialog.Builder selectAct = new AlertDialog.Builder(getContext());
                    selectAct.setTitle("Select Action");
                    selectAct.setNegativeButton("Cancel", null);
                    selectAct.setItems(actions, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i == 0) {
//                               Intent showIntent = new Intent(Intent.ACTION_VIEW, Uri.fromFile(new File(storedImgPath.get(position))));
//                               startActivity(showIntent);

                                final ImageView picture = new ImageView(context);
                                picture.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                                picture.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                picture.setImageBitmap(resized);

                                Toast.makeText(getContext(), storedImgPath.get(position), Toast.LENGTH_LONG).show();
                                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                                dlg.setTitle("picture");
                                dlg.setView(picture);
                                dlg.setNegativeButton("Back", null);
                                dlg.show();

                                //Intent galleryIntent = new Intent(Intent.ACTION_VIEW);
                                //galleryIntent.setDataAndType(FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", new File(storedImgPath.get(position))), "image/*");
                                        //fromFile(new File(storedImgPath.get(position))), "image/*");
                                //galleryIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                //startActivity(galleryIntent);

                            } else {
                                File delFile = new File(storedImgPath.get(position));
                                if (delFile.delete()) {
                                    Toast.makeText(getContext(), "Deleted Image from Gallery", Toast.LENGTH_LONG).show();
                                }
                                storedImgPath.remove(position);
                                gAdapter.notifyDataSetChanged();
                                if (storedImgPath.size() == 0) {
                                    XImg.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
                    selectAct.show();

                }
            });

            linear.addView(imageview);
            return linear;
        }
    }


}