package com.example.somy.camerabasic;

import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnCamera;
    ImageView imageView;

    private final int REQ_PERMISSTION = 100;
    private final int REQ_CAMERA = 101;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 위젯을 세팅
        setWidget();
        // 2. 버튼관련 컨트롤러 활성화처리
        buttonDisable();
        // 3. 리스너 계열을 등록
        setListener();
        // 4. 권한처리
        checkPermission();
    }

    private void buttonDisable() {
        btnCamera.setEnabled(false);
    }

    // 버튼 활성화하기
    private void buttonEnable() {
        btnCamera.setEnabled(true);
    }

    private void init() {
        buttonEnable();

    }


    //control permission
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionConrtol.checkPermission(this, REQ_PERMISSTION)) ;

            init();

        } else {
            init();
        }

    }

    private void setWidget() {
        imageView = (ImageView) findViewById(R.id.imageView);
        btnCamera = (Button) findViewById(R.id.btnCamera);
    }

    private void setListener() {
        btnCamera.setOnClickListener(clickListener);

    }


    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;

            switch (v.getId()) {
                case R.id.btnCamera: //Camera button
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQ_CAMERA);

                    break;

            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override //called from system
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //                         recieve the result. start [0]. permitted
        if (requestCode == REQ_PERMISSTION) {

            if (PermissionConrtol.onCheckResult(grantResults)) init();

        } else {

            Toast.makeText(this, "No permission, can't use this app", Toast.LENGTH_SHORT).show();
            finish();
        }

    }


}

