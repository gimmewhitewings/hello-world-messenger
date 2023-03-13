package com.example.helloworldmessenger

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.helloworldmessenger.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private var encodedImage: String = "s"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpButton.setOnClickListener {
            if (isValidDetails())
                signUp()
        }
    }

    private fun signUp() {

    }

    private fun isValidDetails(): Boolean {
        // check for avatar
        if (encodedImage == "") {
            Toast.makeText(context, getString(R.string.choose_avatar), Toast.LENGTH_SHORT).show()
            return false
        }

        // check for name
        if (binding.inputName.editText?.text.toString() == "") {
            binding.inputName.error = getString(R.string.name_error)
            return false
        }

        // check for email
        if (binding.inputEmail.editText?.text.toString() == "") {
            binding.inputEmail.error = getString(R.string.email_error)
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(
                binding.inputEmail.editText?.text.toString()
            ).matches()
        ) {
            binding.inputEmail.error = getString(R.string.email_error)
            return false
        }

        // check for password
        if (binding.inputPassword.editText?.text.toString() == "") {
            binding.inputPassword.error = getString(R.string.password_error)
            return false
        }

        // check for correct password
        if (binding.inputConfirmPassword.editText?.text.toString() == "") {
            binding.inputConfirmPassword.error = getString(R.string.password_error)
            return false
        } else if (binding.inputConfirmPassword.editText?.text.toString() != binding.inputPassword.editText?.text.toString()) {
            binding.inputConfirmPassword.error = getString(R.string.confirm_password_error)
            return false
        }

        // reset all errors
        binding.inputName.error = null
        binding.inputEmail.error = null
        binding.inputPassword.error = null
        binding.inputConfirmPassword.error = null

        binding.inputName.isErrorEnabled = false
        binding.inputEmail.isErrorEnabled = false
        binding.inputPassword.isErrorEnabled = false
        binding.inputConfirmPassword.isErrorEnabled = false

        return true
    }
}