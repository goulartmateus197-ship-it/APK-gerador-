package com.example.detectorvisualff

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.view.Gravity
import android.graphics.Color
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : Activity() {
    private lateinit var status: TextView
    private val pickVideo = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
        if (uri != null) {
            contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            status.text = "Vídeo selecionado. Próxima etapa: analisar os quadros do vídeo."
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pad = (20 * resources.displayMetrics.density).toInt()
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(pad, pad * 2, pad, pad)
            setBackgroundColor(Color.rgb(11, 18, 32))
            gravity = Gravity.CENTER_HORIZONTAL
        }
        val title = TextView(this).apply {
            text = "DETECTOR VISUAL FF"
            textSize = 24f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }
        val subtitle = TextView(this).apply {
            text = "Analisador de partidas gravadas"
            textSize = 16f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }
        val choose = Button(this).apply {
            text = "Selecionar vídeo da partida"
            setOnClickListener { pickVideo.launch(arrayOf("video/*")) }
        }
        status = TextView(this).apply {
            text = "Nenhum vídeo selecionado."
            textSize = 15f
            setTextColor(Color.rgb(54, 214, 160))
            gravity = Gravity.CENTER
        }
        val note = TextView(this).apply {
            text = "Esta versão inicial trabalha com vídeos escolhidos por você. Não lê o Free Fire ao vivo, não revela inimigos escondidos e não mede distância real."
            textSize = 13f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }
        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(choose)
        layout.addView(status)
        layout.addView(note)
        setContentView(layout)
    }
}
