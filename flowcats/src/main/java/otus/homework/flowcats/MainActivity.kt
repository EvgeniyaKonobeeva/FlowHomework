package otus.homework.flowcats

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val diContainer = DiContainer()
    private val catsViewModel by viewModels<CatsViewModel> { CatsViewModelFactory(diContainer.repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = layoutInflater.inflate(R.layout.activity_main, null) as CatsView
        setContentView(view)

        lifecycleScope.launch {
            catsViewModel.stateFlow.collect { state ->
                when (state) {
                    is Result.Success -> {
                        view.populate(state.fact)
                    }

                    is Result.Error -> {
                        Toast.makeText(baseContext, state.msg, Toast.LENGTH_SHORT).show()
                    }

                    Result.Initial -> Unit
                }

            }
        }

    }
}