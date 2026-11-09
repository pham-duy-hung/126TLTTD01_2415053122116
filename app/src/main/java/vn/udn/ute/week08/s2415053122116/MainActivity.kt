package vn.udn.ute.week08.s2415053122116

import android.os.Bundle
import android.os.PersistableBundle
import  vn.udn.ute.week08.s2415053122116.databinding.ActivityMainBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCheck.setOnClickListener {
            processValidationAndCaculation()
        }
        binding.btnClear.setOnClickListener {
            clearAllFields()
        }
    }
    private fun processValidationAndCaculation() {
        val name = binding.etSubjectName.text.toString()
        val trimmedName = name.trim()
        if (trimmedName.isEmpty() || trimmedName.length > 40) {
            binding.etSubjectName.error = "tên học phần phải từ 1 đến 40 ký tự"
            binding.tvResult.text = "loi dau vao"
            return
        }
        val qt = binding.etScoreQT.text.toString().trim().toDoubleOrNull()
        val ck = binding.etScoreCK.text.toString().trim().toDoubleOrNull()
        if (qt == null || qt !in 0.0..10.0 || ck == null || ck !in 0.0..10.0) {
            binding.tvResult.text = "loi: diem phai tu 0.0->10.0"
            return
        }
        val t = 0.4 * qt + 0.6 * ck
        val status = if (t >= 5.0 && ck >= 3.0) "Dat" else "chua dat"

        val canhbao = if (abs(qt-ck)>3.0) "cảnh báo lệch điểm thi" else "Điểm cân đối"

        binding.tvResult.text = String.format(
            Locale.getDefault(),
            "HP: %s\nQT:%.2f| CK: %.2f| T: %2.f\nketluan: %s",
            name, qt, ck, t, status
        )
    }
    private fun clearAllFields(){
        binding.etSubjectName.text?.clear()
        binding.etScoreQT.text?.clear()
        binding.etScoreCK.text?.clear()
        binding.etSubjectName.error = null
        binding.etScoreQT.error = null
        binding.etScoreCK.error = null
        binding.tvResult.text = "ket qua: ..."
    }
}