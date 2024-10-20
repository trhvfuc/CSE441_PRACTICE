package com.example.ex05_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btnTieptuc, btnGiai, btnThoat;
    EditText edita, editb, editc;
    TextView txtkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ánh xạ các phần tử giao diện
        btnTieptuc = findViewById(R.id.btntieptuc);
        btnGiai = findViewById(R.id.btngiaipt);
        btnThoat = findViewById(R.id.btnthoat);
        edita = findViewById(R.id.edta);
        editb = findViewById(R.id.edtb);
        editc = findViewById(R.id.edtc);
        txtkq = findViewById(R.id.txtkq);

        // Xử lý sự kiện khi bấm nút Giải phương trình
        btnGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sa = edita.getText().toString();
                String sb = editb.getText().toString();
                String sc = editc.getText().toString();

                // Kiểm tra đầu vào có hợp lệ hay không
                if (sa.isEmpty() || sb.isEmpty() || sc.isEmpty()) {
                    txtkq.setText("Vui lòng nhập đầy đủ các hệ số.");
                    return;
                }

                // Chuyển các giá trị từ EditText thành số
                int a = Integer.parseInt(sa);
                int b = Integer.parseInt(sb);
                int c = Integer.parseInt(sc);
                String kq = "";

                DecimalFormat dcf = new DecimalFormat("0.00");

                // Bắt đầu xử lý phương trình
                if (a == 0) {  // Phương trình bậc nhất
                    if (b == 0) {
                        if (c == 0) {
                            kq = "Phương trình vô số nghiệm";
                        } else {
                            kq = "Phương trình vô nghiệm";
                        }
                    } else {
                        kq = "Phương trình có 1 nghiệm x = " + dcf.format(-c / (double) b);
                    }
                } else {  // Phương trình bậc hai
                    double delta = b * b - 4 * a * c;

                    if (delta < 0) {
                        kq = "Phương trình vô nghiệm";
                    } else if (delta == 0) {
                        kq = "Phương trình có nghiệm kép x1 = x2 = " + dcf.format(-b / (2.0 * a));
                    } else {
                        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                        kq = "Phương trình có 2 nghiệm: x1 = " + dcf.format(x1) + ", x2 = " + dcf.format(x2);
                    }
                }

                // Hiển thị kết quả
                txtkq.setText(kq);
            }
        });

        // Xử lý sự kiện khi bấm nút Tiếp tục
        btnTieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa nội dung các ô nhập liệu và kết quả
                edita.setText("");
                editb.setText("");
                editc.setText("");
                txtkq.setText("");
                edita.requestFocus();
            }
        });

        // Xử lý sự kiện khi bấm nút Thoát
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Đóng ứng dụng
            }
        });

        // Thiết lập padding cho các phần tử để tránh hệ thống thanh điều hướng
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
