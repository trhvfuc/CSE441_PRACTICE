package com.example.bmi;

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

    EditText edtten, edtchieucao, edtcannang, edtBMI, edtChuanDoan;
    Button btnBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Chuyển super lên đầu tiên
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Tìm các view sau khi đã setContentView
        edtChuanDoan = findViewById(R.id.edtChuanDoan);
        edtten = findViewById(R.id.edtten);
        edtchieucao = findViewById(R.id.edtchieucao);
        edtcannang = findViewById(R.id.edtcannang);
        edtBMI = findViewById(R.id.edtBMI);
        btnBMI = findViewById(R.id.btnBMI);

        // Thêm sự kiện cho nút btnBMI
        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Lấy chiều cao và cân nặng từ EditText
                    double H = Double.parseDouble(edtchieucao.getText().toString());
                    double W = Double.parseDouble(edtcannang.getText().toString());

                    // Tính BMI
                    double BMI = W / Math.pow(H, 2);
                    String chandoan;

                    // Chẩn đoán theo giá trị BMI
                    if (BMI < 18) {
                        chandoan = "Bạn gầy";
                    } else if (BMI <= 24.9) {
                        chandoan = "Bạn bình thường";
                    } else if (BMI <= 29.9) {
                        chandoan = "Bạn béo phì độ 1";
                    } else if (BMI <= 34.9) {
                        chandoan = "Bạn béo phì độ 2";
                    } else {
                        chandoan = "Bạn béo phì độ 3";
                    }

                    // Định dạng số BMI và hiển thị
                    DecimalFormat dcf = new DecimalFormat("#.0");
                    edtBMI.setText(dcf.format(BMI));
                    edtChuanDoan.setText(chandoan);

                } catch (NumberFormatException e) {
                    // Xử lý lỗi nếu nhập sai định dạng
                    edtBMI.setText("Lỗi nhập liệu");
                    edtChuanDoan.setText("Vui lòng nhập đúng số");
                }
            }
        });

        // Thiết lập layout để xử lý Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
