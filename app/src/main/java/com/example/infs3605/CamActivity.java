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

import java.util.ArrayList;
import java.util.List;


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
    static final String UNLIKELY = "UNLIKELY";
    static final String POSSIBLY = "POSSIBLY";
    static final String LIKELY = "LIKELY";


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
                launchLikelihoodActivity(analysisLikelihood());
            }
        });
    }

    private void launchLikelihoodActivity(String message) {
        Intent intent = new Intent(this, LikelihoodActivity.class);
        intent.putExtra(LikelihoodActivity.INTENT_MESSAGE, message);
        startActivity(intent);
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

    // Display image
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



    // Analysis likelihood
    public String analysisLikelihood(){
        String result = POSSIBLY;
        // get text
        String emailText = answerText.getText().toString();;
        // remove non-letter characters, punctuations
        String[] emailWords = emailText.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
        ArrayList<String> scamTerms = getScamTerms();

        // match
        int count = 0;
        for(String ew: emailWords){
            for(String st: scamTerms){
                if(ew.toLowerCase().equals(st.toLowerCase())) count++;
            }
        }

        if(count <= 0) result = UNLIKELY;
        else if(count <= 1) result = POSSIBLY;
        else if(count >= 2) result = LIKELY;
        return result;
    }



    // Get common scam terms
    public ArrayList<String> getScamTerms(){
        ArrayList<String> scamTerms = new ArrayList<String>();
        scamTerms.add("label");
        scamTerms.add("invoice");
        scamTerms.add("post");
        scamTerms.add("document");
        scamTerms.add("postal");
        scamTerms.add("calculations");
        scamTerms.add("copy");
        scamTerms.add("fedex");
        scamTerms.add("statement");
        scamTerms.add("financial");
        scamTerms.add("dhl");
        scamTerms.add("usps");
        scamTerms.add("notification");
        scamTerms.add("irs");
        scamTerms.add("ups");
        scamTerms.add("no");
        scamTerms.add("delivery");
        scamTerms.add("ticket");
        scamTerms.add("link");
        scamTerms.add("website");
        scamTerms.add("follow");
        scamTerms.add("click");
        scamTerms.add("restricted");
        scamTerms.add("restrict");
        scamTerms.add("continue");
        scamTerms.add("vulnerability");
        scamTerms.add("hours");
        scamTerms.add("disabled");
        scamTerms.add("disable");
        scamTerms.add("update");
        scamTerms.add("check");
        scamTerms.add("refund");
        scamTerms.add("account");
        scamTerms.add("important");
        return scamTerms;
    }




}