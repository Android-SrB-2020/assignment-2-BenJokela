package com.example.rickandmorty2


import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.rickandmorty2.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

    private val questionBank = listOf(
        Question(R.string.question_1, false),
        Question(R.string.question_2, true),
        Question(R.string.question_3, true),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false),
        Question(R.string.question_6, true),
        Question(R.string.question_7, false),
        Question(R.string.question_8, true),
        Question(R.string.question_9, false),
        Question(R.string.question_10, false),
        Question(R.string.question_11, false),
        Question(R.string.question_12, true),
        Question(R.string.question_13, false),
        Question(R.string.question_14, true),
        Question(R.string.question_15, false),
        Question(R.string.question_16, false),
        Question(R.string.question_17, true),
        Question(R.string.question_18, false),
        Question(R.string.question_19, false),
        Question(R.string.question_20, true)
    )

    private var questionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()


        //set on click listeners with binding
        binding.apply {
            nextButton.setOnClickListener {
                questionIndex = (questionIndex + 1) % (questionBank.size)
                questionView.setText(questionBank[questionIndex].textResId)

            }
            prevButton.setOnClickListener {
                questionIndex = if (questionIndex == 0) questionBank.size - 1 else questionIndex - 1

            }
            trueButton.setOnClickListener {
                val toastText =
                    if (questionBank[questionIndex].answer) getString(R.string.you_got_it) else getString(
                        R.string.wrong
                    )
                Toast.makeText(activity, toastText, Toast.LENGTH_SHORT).show()
            }
            falseButton.setOnClickListener{
                val toastText = if(questionBank[questionIndex].answer) getString(R.string.wrong) else getString(
                    R.string.you_got_it)
                Toast.makeText(activity, toastText, Toast.LENGTH_SHORT).show()
            }

        }

        binding.cheatButton.setOnClickListener{

            navController.navigate(MainFragmentDirections
                .actionMainFragmentToCheatFragment(getString(questionBank[questionIndex].textResId), questionBank[questionIndex].answer.toString()))
        }
    }
}
