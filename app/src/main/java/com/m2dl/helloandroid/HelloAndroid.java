package com.m2dl.helloandroid;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public class HelloAndroid extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_android);

        takePhoto(findViewById(R.id.imageView));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hello_android, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Uri imageUri;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    //On veut prendre une photo
    public void takePhoto(View view) {
        //Création d'un intent
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

        //Création du fichier image
        File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);

        //On lance l'intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    //On a reçu le résultat d'une activité
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //Si l'activité était une prise de photo
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);
                    ImageView imageView = (ImageView) findViewById(R.id.imageView);
                    ContentResolver cr = getContentResolver();
                    Bitmap bitmap;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);

                        imageView.setImageBitmap(bitmap);
                        //Affichage de l'infobulle
                        Toast.makeText(this, selectedImage.toString(),
                                Toast.LENGTH_LONG).show();

                        int count = 0;
                        for (int i = 0; i < bitmap.getWidth(); ++i) {
                            for (int j = 0; j<bitmap.getHeight(); ++j) {
                                int pixel = bitmap.getPixel(i, j);
                                int red = Color.red(pixel);
                                int green = Color.green(pixel);
                                if(red > green) {
                                    ++count;
                                }
                            }
                        }

                        TextView textView = (TextView) findViewById(R.id.text);
                        textView.setText("Total pixel count : " + bitmap.getHeight()*bitmap.getWidth()
                                + "\nPixel count where red > green : " + count);
                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", "Picture", e);
                    }
                }
        }
    }
}
