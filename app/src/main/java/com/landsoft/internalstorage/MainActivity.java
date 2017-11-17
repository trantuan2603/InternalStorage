package com.landsoft.internalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName() ;
    Button btnWriteDefault, btnWriteCache, btnReadDefault, btnReadCache;
    TextView tvReader;

    private final String DOC_WRITE_DEFAULT = "public void saveDataDefault(){\n" +
            "        String fileName = \"thangcoder.com\";\n" +
            "        String content = \"Blog chia se kien thuc lap trinh\";\n" +
            " \n" +
            "        FileOutputStream outputStream = null;\n" +
            "        try {\n" +
            "            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);\n" +
            "            outputStream.write(content.getBytes());\n" +
            "            outputStream.close();\n" +
            "            Toast.makeText(this, \"Saved successfully\", Toast.LENGTH_SHORT).show();\n" +
            "        } catch (Exception e) {\n" +
            "            e.printStackTrace();\n" +
            "        }\n" +
            "    }\n" +
            "try {\n" +
            "            FileInputStream fileInputStream = openFileInput(FILE_NAME);\n" +
            "            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));\n" +
            "            StringBuffer stringBuffer = new StringBuffer();\n" +
            "            String line=null;\n" +
            "            while ((line = bufferedReader.readLine()) != null){\n" +
            "                stringBuffer.append(line);\n" +
            "            }\n" +
            "            bufferedReader.close();\n" +
            "            fileInputStream.close();\n" +
            "            \n" +
            "            \n" +
            "        } catch (FileNotFoundException e) {\n" +
            "            e.printStackTrace();\n" +
            "        } catch (IOException e) {\n" +
            "            e.printStackTrace();\n" +
            "        }";
    private final String DOC_WRITE_CACHE ="try {\n" +
            "            File file = new File(getCacheDir(),FILE_NAME_CACHE);\n" +
            "            Log.d(TAG, \"fileWriteCache: \"+getCacheDir());\n" +
            "            FileOutputStream outputStream = new FileOutputStream(file);\n" +
            "            outputStream.write(DOC_WRITE_CACHE.getBytes());\n" +
            "\n" +
            "            outputStream.close();\n" +
            "        }\n " +" try {\n" +
            "            File file = new File(getCacheDir(),FILE_NAME_CACHE);\n" +
            "            FileInputStream inputStream = new FileInputStream(file);\n" +
            "            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));\n" +
            "            StringBuffer buffer = new StringBuffer();\n" +
            "            String line = \"\";\n" +
            "            while ((line = reader.readLine()) != null){\n" +
            "                buffer.append(line);\n" +
            "            }\n" +
            "            Log.d(TAG, \"fileReadCache:\" + getCacheDir());\n" +
            "            Log.d(TAG, \"fileReadCache: \" + buffer);\n" +
            "            tvReader.setText(buffer.toString());\n" +
            "            reader.close();\n" +
            "            inputStream.close();\n" +
            "\n" +
            "        } ";
    private final String FILE_NAME = "trantuan.com";
    private final String FILE_NAME_CACHE = "trantuan.cache";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappedWidget();
        setOnClick();

    }

    private void setOnClick() {
        btnWriteDefault.setOnClickListener(this);
        btnWriteCache.setOnClickListener(this);
        btnReadDefault.setOnClickListener(this);
        btnReadCache.setOnClickListener(this);
    }

    private void mappedWidget() {
        btnWriteDefault = findViewById(R.id.btn_write_default);
        btnWriteCache = findViewById(R.id.btn_write_cache);
        btnReadDefault = findViewById(R.id.btn_read_default);
        btnReadCache = findViewById(R.id.btn_read_cache);
        tvReader = findViewById(R.id.mtv_reader);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_write_default:
//                To do
                fileWriteDefault();
                break;

            case R.id.btn_write_cache:
//                To do
                fileWriteCache();
                break;
            case R.id.btn_read_default:
//                To do
                fileReadDefault();
                break;
            case R.id.btn_read_cache:
//                To do
                fileReadCache();
                break;
        }
    }

    private void fileReadCache() {

        try {
            File file = new File(getCacheDir(),FILE_NAME_CACHE);
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
            Log.d(TAG, "fileReadCache:" + getCacheDir());
            Log.d(TAG, "fileReadCache: " + buffer);
            tvReader.setText(buffer.toString());
            reader.close();
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fileWriteCache() {

        try {
            File file = new File(getCacheDir(),FILE_NAME_CACHE);
            Log.d(TAG, "fileWriteCache: "+getCacheDir());
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(DOC_WRITE_CACHE.getBytes());

            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fileReadDefault() {
        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();
            fileInputStream.close();
            tvReader.setText(stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fileWriteDefault() {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(DOC_WRITE_DEFAULT.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
