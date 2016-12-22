package com.laboratory.android.login.view;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laboratory.android.login.R;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CropActivity extends AppCompatActivity {
    private Dialog caixaOpcoesFoto;
    private final int PROFIBRO_PERMISSIONS_CAMERA = 3;
    private final int PROFIBRO_PERMISSIONS_GRAVAR_MEMORIA = 4;
    private Dialog caixaExplicacaoPermissaoCamera;
    private Dialog caixaExplicacaoPermissaoGravaMemoria;
    private String localArquivoFoto;
    private String pastaProFibroImagens;
    private LinearLayout linearLayout;
    private CropImageView imagemCrop;
    private Button okCrop;
    private String nomeDoArquivo;
    private File file;
    private File file_original;
    private Button cancelCrop;
    private final int IMAGEM_SELECIONADA_GALERIA = 1;
    private String pathImagemSelecionadaGaleria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        /*verificaPermissoesEabreOpcoes();*/

        imagemCrop = (CropImageView)findViewById(R.id.imageCrop);
        imagemCrop.setFixedAspectRatio(true);

        cancelCrop = (Button)findViewById(R.id.cancel_crop);
        okCrop = (Button)findViewById(R.id.ok_crop);

        /*cancelCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostraCaixaOpcoesFoto();
            }
        })*/;

        /*okCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matrix matrix = new Matrix();
                matrix.setRotate(0);
                Bitmap auxBitmap = Bitmap.createScaledBitmap(imagemCrop.getCroppedImage(),
                        imagemCrop.getCroppedImage().getWidth(),
                        imagemCrop.getCroppedImage().getHeight(),
                        true);
                auxBitmap = Bitmap.createBitmap(auxBitmap, 0, 0, auxBitmap.getWidth(), auxBitmap.getHeight(), matrix, true);
                salvarImagemSD(auxBitmap);
                alteraFotoUsuarioBD();
                finish();
                abreHomeActivity();

            }
        });*/
    }


}
