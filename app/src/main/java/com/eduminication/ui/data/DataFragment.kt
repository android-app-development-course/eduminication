package com.eduminication.ui.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eduminication.databinding.FragmentDataBinding
import pub.devrel.easypermissions.EasyPermissions

class DataFragment : Fragment() {
    private lateinit var binding: FragmentDataBinding
    private lateinit var dataViewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBinding.inflate(inflater, container, false)

        binding.fab.setClosedOnTouchOutside(true)

        EasyPermissions.hasPermissions(context!!, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        dataViewModel = DataViewModel(context!!)
        binding.tbsView.src = dataViewModel.filePath.value


        return binding.root
    }
}
