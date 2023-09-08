package com.amsolver.semente.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.amsolver.semente.R;
import com.pdfview.PDFView;
import com.pdfview.subsamplincscaleimageview.SubsamplingScaleImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ShowPdfActivity extends AppCompatActivity {

    File pdfFile;
    public String PDF_FILENAME = "";

    ImageView ivBack;
    ImageView ivDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pdf_layout);
        PDF_FILENAME = getIntent().getStringExtra("pdf_filename");
        PDFView view = findViewById(R.id.pdfView);
        view.setPanLimit(SubsamplingScaleImageView.PAN_LIMIT_INSIDE);
        //view.setDebug(true);

        pdfFile = getFilePdf();

        if (pdfFile != null) {
            view.fromFile(pdfFile)
                    .show();
            loadViews();
            loadListeners();
        } else {
            // Handle error if the file cannot be loaded
            Toast.makeText(this, "Non poidemos atopar o pdf da nosa orixe. Por favor, inténteo de novo máis tarde.", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadViews() {
        ivBack = findViewById(R.id.ivBack);
        ivDownload = findViewById(R.id.ivDownload);
    }

    public void loadListeners() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descargarArchivoPDF(pdfFile);
            }
        });

    }

    private void descargarArchivoPDF(File archivoPDF) {
        try {
            // Abre un InputStream para leer el archivo PDF
            FileInputStream inputStream = new FileInputStream(archivoPDF);

            // Crea un buffer de lectura
            byte[] buffer = new byte[4096];
            int bytesRead;

            // Crea un OutputStream para escribir el archivo descargado
            File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), archivoPDF.getName());
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            // Lee el archivo PDF y escribe los datos en el OutputStream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Cierra los streams
            inputStream.close();
            outputStream.close();

            // Notifica al sistema de archivos sobre el nuevo archivo descargado
            MediaScannerConnection.scanFile(getApplicationContext(), new String[]{outputFile.getAbsolutePath()}, null, null);

            // Muestra una notificación o mensaje al usuario para informarle que la descarga se ha completado
            Toast.makeText(getApplicationContext(), "O PDF descargouse correctamente", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Produciuse un erro ao descargar o PDF", Toast.LENGTH_SHORT).show();
        }
    }


    private File getFileFromRawResource(int resourceId) {
        InputStream inputStream = getResources().openRawResource(resourceId);
        File outputFile = new File(getCacheDir(), PDF_FILENAME);

        try {
            OutputStream outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return outputFile;
    }

    private File getFilePdf() {
        if (PDF_FILENAME.equals("a_nosa_orixe.pdf")) {
            return getFileFromRawResource(R.raw.a_nosa_orixe);
        } else if (PDF_FILENAME.equals("dados_de_contacto.pdf")) {
            return getFileFromRawResource(R.raw.dados_de_contacto);
        } else if (PDF_FILENAME.equals("ficha_para_asociarse.pdf")) {
            return getFileFromRawResource(R.raw.ficha_para_asociarse);
        } else if (PDF_FILENAME.equals("guia_para_familias.pdf")) {
            return getFileFromRawResource(R.raw.guia_para_familias);
        }  else if (PDF_FILENAME.equals("triptico_informativo.pdf")) {
            return getFileFromRawResource(R.raw.triptico_informativo);
        } else {
            return null;
        }
    }
}
