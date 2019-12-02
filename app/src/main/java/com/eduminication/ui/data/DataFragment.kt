package com.eduminication.ui.data

import android.Manifest
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduminication.databinding.FragmentDataBinding
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class DataFragment : Fragment(){
    private lateinit var binding: FragmentDataBinding
    private lateinit var mFile: File
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        binding.fab.setClosedOnTouchOutside(true)

        openPdf("test.pdf")

        return binding.root
    }
    fun openPdf(pdfName: String) {
        EasyPermissions.requestPermissions(this,"", 0,Manifest.permission.READ_EXTERNAL_STORAGE)
        val f = File(Environment.getExternalStorageDirectory(), pdfName)
        binding.pdfView.fromFile(f)
            ?.onDraw { canvas, _, _, _ ->
                val effacer = null
                var x = 0
                var y = 0
                if(effacer == 0){
                    var p = Paint()
                    p.color = Color.BLUE
                    canvas.drawLine(x.toFloat(), y.toFloat(), (x+10).toFloat(), y.toFloat(), p)
                }
            }
            ?.onTap { e ->
                var x = 0
                var y = 0
                x = e?.x!!.toInt()
                y = e?.y!!.toInt()
                var effacer = 0
                true
            }
            ?.load()
    }
}
