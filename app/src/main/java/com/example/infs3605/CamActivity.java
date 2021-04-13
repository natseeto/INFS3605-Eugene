package com.example.infs3605;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;


/*
https://stackoverflow.com/questions/35476182/updating-google-play-services-in-emulator
UPDATE Google play service in EMULATOR
 */


public class CamActivity extends AppCompatActivity {

    /////////////////////////////////////
    // Text Recognition activity
    /////////////////////////////////////


    ImageView exampleEmail;
    TextView answerText;
    Button recButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);

        exampleEmail = findViewById(R.id.ivCamImage);
        answerText = findViewById(R.id.tvCamAnswer);
        recButton = findViewById(R.id.btCamRec);



        exampleEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage(CamActivity.this);
            }
        });

        recButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rec();
            }
        });
    }


    // Upload image
    public void uploadImage(Context context){
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        exampleEmail.setImageBitmap(selectedImage);
                    }
                    break;

                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        if (selectedImage != null) {
                            exampleEmail.setImageURI(selectedImage);
                        }
                    }
                    break;
            }
        }
    }



    // Recognise the text from image
    public void rec(){
        // define text recogniser
        TextRecognizer recognizer = new TextRecognizer.Builder(CamActivity.this).build();

        // get bitmap from imageview
        Bitmap bitmap = ((BitmapDrawable)exampleEmail.getDrawable()).getBitmap();

        // get frame from bitmap
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();

        // get data from frame
        SparseArray<TextBlock> sparseArray = recognizer.detect(frame);

        // set data on textview
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < sparseArray.size(); i++){
            TextBlock tb = sparseArray.get(i);
            String str = tb.getValue();

            stringBuilder.append(str);
        }

        answerText.setText(stringBuilder);
    }
}