package com.watasolutions.week7_t7.screens.login

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.watasolutions.week7_t7.MySharedPreferences
import com.watasolutions.week7_t7.adapter.UserAdapter
import com.watasolutions.week7_t7.app.MyApp
import com.watasolutions.week7_t7.app.ViewModelFactory
import com.watasolutions.week7_t7.database.UserDatabase
import com.watasolutions.week7_t7.databinding.FragmentLoginBinding
import com.watasolutions.week7_t7.model.Users


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment() : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var prefs: MySharedPreferences
    private lateinit var userAdapter: UserAdapter
    private lateinit var mListUser: List<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactory(activity?.application as MyApp)).get(
            LoginViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userAdapter = UserAdapter()
        mListUser = ArrayList()
        userAdapter.setData(mListUser)

        var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context);
        binding.rcvData.setLayoutManager(linearLayoutManager)
        binding.rcvData.setAdapter(userAdapter)

//        registerSaveSuccess()
//        registerLoadUserEvent()
        binding.btnSave.setOnClickListener {
            val username = binding.edtUsername.editText?.text.toString().trim()
            val pass = binding.edtPassword.editText?.text.toString().trim()
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pass)) {
                return@setOnClickListener
            }
            var user: Users = Users(username, pass)
            UserDatabase.getInstance(context).dao().insertUser(user)
            Toast.makeText(context, "SUCCESSFULLY", Toast.LENGTH_LONG).show()
//            viewModel.saveUserInfo(username, pass)
//            var user: Users = Users(username, pass)
//            UserDatabase.getInstance(context).dao().insertUser(user)
//            Toast.makeText(context, "SUCCESSFULLY", Toast.LENGTH_LONG).show()
            viewModel.saveEventSuccess.observe(viewLifecycleOwner) {
                binding.edtUsername.editText?.setText("")
                binding.edtPassword.editText?.setText("")
            }

        }
        binding.btnLoad.setOnClickListener {
            mListUser = UserDatabase.getInstance(context).dao().listUser() as List<Users>
            Log.d("iii", mListUser.toString())
            userAdapter.setData(mListUser)
//            viewModel.loadUserInfo()
            viewModel.loadUserEvent.observe(viewLifecycleOwner) {
            }
        }
    }
}