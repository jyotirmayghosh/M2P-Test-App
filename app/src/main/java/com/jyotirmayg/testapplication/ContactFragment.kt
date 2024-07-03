package com.jyotirmayg.testapplication

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jyotirmayg.testapplication.databinding.FragmentContactBinding
import com.jyotirmayg.testapplication.viewModel.ContactViewModel


class ContactFragment : Fragment() {

    private var binding: FragmentContactBinding? = null
    private lateinit var viewModel: ContactViewModel

    companion object {
        const val READ_CONTACTS_PERMISSION_CODE = 10001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        checkPermissionAndFetch()
        observer()
    }

    private fun observer() {
        viewModel.liveData.observe(this) {
            //binding?.progress?.visibility = View.GONE

            val mAdapter = ContactAdapter(it)
            binding?.recycleView?.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = mAdapter
            }
        }
    }

    private fun checkPermissionAndFetch() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_CONTACTS
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                READ_CONTACTS_PERMISSION_CODE
            )
        } else {
            //binding?.progress?.visibility = View.VISIBLE
            viewModel.getContactDetails(requireActivity().contentResolver)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            READ_CONTACTS_PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //binding?.progress?.visibility = View.VISIBLE
                    viewModel.getContactDetails(requireActivity().contentResolver)
                } else {
                    Toast.makeText(requireContext(), "Permission not granted", Toast.LENGTH_SHORT)
                        .show()
                }
                return
            }
        }
    }
}