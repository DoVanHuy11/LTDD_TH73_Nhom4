package com.example.fashion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.regex.Pattern;

public class LogInActivity extends AppCompatActivity {
    EditText edtUser,edtPassWord;
    Button btnDangNhap;
    TextView tvDangKy;
    ImageView imgThoat;
    String user,passWord;
    private FirebaseAuth mAuth;
    //Khuôn email hợp lệ , vd : abc@gmail.com
    String EMAIL_PATTERN =
            "^[\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();

        anhXa();
        edtUser.setText("nhantruong1298@gmail.com");
        edtPassWord.setText("123456");
        controlImage();
        SetUpSignUp();
        SetUpButtonLogin();
    }

    private void SetUpSignUp() {
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOnline()) {
                    Toast.makeText(LogInActivity.this, "Chưa kết nối internet", Toast.LENGTH_SHORT).show();
                } else {
                    final Dialog dialog = new Dialog(LogInActivity.this);
                    dialog.setTitle("Đăng ký");
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.sign_up);

                    final EditText editName = (EditText) dialog.findViewById(R.id.edtName);
                    //final TextInputLayout usernameWrapper = dialog.findViewById(R.id.usernameWrapper);
                    editName.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() == 0) {
                                editName.setError("Không được bỏ trống");
//                            usernameWrapper.setError("Không được bỏ trống");
                            } else
//                            usernameWrapper.setError(null);
                                editName.setError(null);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    final EditText edtEmail = (EditText) dialog.findViewById(R.id.edtEmail);
                    edtEmail.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() == 0) {
                                edtEmail.setError("Không được bỏ trống");
                            } else if (!Pattern.matches(EMAIL_PATTERN, edtEmail.getText().toString())) {
                                edtEmail.setError("Email không hợp lệ");
                            } else
                                edtEmail.setError(null);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    final EditText editPhone = (EditText) dialog.findViewById(R.id.edtPhone);
                    editPhone.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() == 0) {
                                editPhone.setError("Không được bỏ trống");
                            } else
                                editPhone.setError(null);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    final EditText edtMatKhau = (EditText) dialog.findViewById(R.id.edtMatKhau);
                    edtMatKhau.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() == 0) {
                                edtMatKhau.setError("Không được bỏ trống");
                            } else if (s.length() < 6) {
                                edtMatKhau.setError("Mật khẩu ít nhất 6 kí tự");
                            } else
                                edtMatKhau.setError(null);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    final EditText edtReMatkhau = (EditText) dialog.findViewById(R.id.edtReMatKhau);
                    edtReMatkhau.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.length() == 0) {
                                edtReMatkhau.setError("Không được bỏ trống");
                            } else if (!s.toString().equals(edtMatKhau.getText().toString().trim())) {
                                edtReMatkhau.setError("Mật khẩu không trùng khớp");
                            } else
                                edtReMatkhau.setError(null);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    Button btnDangKy = (Button) dialog.findViewById(R.id.btnDangKy);
                    btnDangKy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            user = edtEmail.getText().toString().trim();
                            passWord = edtMatKhau.getText().toString().trim();
                            if (editName.length() == 0 || edtEmail.length() == 0 || editPhone.length() == 0
                                    || edtEmail.length() == 0 || edtMatKhau.length() == 0 || edtReMatkhau.length() == 0) {
                                Toast.makeText(LogInActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                            } else if (!edtMatKhau.getText().toString().equals(edtReMatkhau.getText().toString())) {
                                Toast.makeText(LogInActivity.this, "Kiểm tra lại mật khẩu", Toast.LENGTH_SHORT).show();
                            } else {
                                dangKy(user, passWord);

                                edtUser.setText(user);
                                edtPassWord.setText(passWord);

                                dialog.cancel();
                            }
                        }
                    });

                    TextView tvHuyDangKy = (TextView) dialog.findViewById(R.id.tvHuyDangKy);
                    tvHuyDangKy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                    dialog.show();
                }
            }
        });
    }
    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void dangKy(final String user,final String passWord) {
        mAuth.createUserWithEmailAndPassword(user, passWord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LogInActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(LogInActivity.this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void SetUpButtonLogin() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = edtUser.getText().toString().trim();
                passWord = edtPassWord.getText().toString().trim();

                if(!isOnline()){
                    Toast.makeText(LogInActivity.this,"Chưa kết nối internet",Toast.LENGTH_SHORT).show();
                }else {
                    if (!Pattern.matches(EMAIL_PATTERN, user))
                        Toast.makeText(LogInActivity.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                    else if (passWord.length() == 0)
                        Toast.makeText(LogInActivity.this, "Password không hợp lệ", Toast.LENGTH_SHORT).show();
                    else
                        dangNhap(user, passWord);
                }
            }
        });
    }

    private void dangNhap(String user, String passWord){
        mAuth.signInWithEmailAndPassword(user, passWord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(LogInActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LogInActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void controlImage() {
        imgThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
                builder.setTitle("Thoát ứng dụng");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    private void anhXa() {
        edtUser =(EditText) findViewById(R.id.edtUser);
        edtPassWord = (EditText) findViewById(R.id.edtPassWord);
        btnDangNhap = (Button) findViewById(R.id.btDangNhap);
        tvDangKy = (TextView) findViewById(R.id.tvDangKy);
        imgThoat = (ImageView) findViewById(R.id.imgThoat);
    }
}