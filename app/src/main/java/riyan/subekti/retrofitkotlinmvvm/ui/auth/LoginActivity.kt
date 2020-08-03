package riyan.subekti.retrofitkotlinmvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
//import kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

import riyan.subekti.retrofitkotlinmvvm.R
import riyan.subekti.retrofitkotlinmvvm.data.db.entities.User
import riyan.subekti.retrofitkotlinmvvm.databinding.ActivityLoginBinding
import riyan.subekti.retrofitkotlinmvvm.ui.home.HomeActivity
import riyan.subekti.retrofitkotlinmvvm.util.hide
import riyan.subekti.retrofitkotlinmvvm.util.show
import riyan.subekti.retrofitkotlinmvvm.util.snackbar

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    // Implement kodein
    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
//        root_layout.snackbar("${user.name} is Logged In")
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
