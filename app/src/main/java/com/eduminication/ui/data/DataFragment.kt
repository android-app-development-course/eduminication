package com.eduminication.ui.data

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.eduminication.databinding.FragmentDataBinding
import com.koushikdutta.async.future.FutureCallback
import kotlinx.android.synthetic.main.fragment_data.*
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

class DataFragment : Fragment() {
    private lateinit var dataViewModel: DataViewModel
    private lateinit var binding: FragmentDataBinding
    private val args: DataFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(inflater, container, false)

        EasyPermissions.requestPermissions(this, "", 0, Manifest.permission.INTERNET)
        dataViewModel = DataViewModel(context!!, args.url,
            FutureCallback { e, result ->
                if (result != null) {
                    Toast.makeText(context, "File download complete", Toast.LENGTH_LONG).show()
                    openPdf(result)
                } else {
                    var errorStr = "Error downloading file "
                    e?.let { errorStr += it }
                    Toast.makeText(context, errorStr, Toast.LENGTH_LONG).show()
                }

            })

        dataViewModel.ionInstance?.progressBar(progress_bar)
        binding.menuFloatingButton.setClosedOnTouchOutside(true)

        return binding.root
    }

    private fun openPdf(pdfFile: File) {
        binding.pdfView.fromFile(pdfFile).onTap { true }.load()
    }
}
