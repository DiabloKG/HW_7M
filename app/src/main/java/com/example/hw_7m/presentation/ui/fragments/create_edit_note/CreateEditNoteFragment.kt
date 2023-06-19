import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.hw_7m.R
import com.example.hw_7m.databinding.FragmentCreateEditNoteBinding
import com.example.hw_7m.domain.model.Note
import com.example.hw_7m.presentation.UIState
import com.example.hw_7m.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateEditNoteFragment : BaseFragment(R.layout.fragment_create_edit_note) {

    private val binding by viewBinding(FragmentCreateEditNoteBinding::bind)
    private val viewModel by viewModels<CreateEditNoteViewModel>()
    private var note: Note? = null

    override fun initClickListeners() {
        binding.btnSave.setOnClickListener {
            viewModel.createNote(
                Note(
                    title = binding.etTitle.text.toString(),
                    description = binding.etDescription.text.toString()
                )
            )
        }
    }

    override fun setupObservers() {
        viewModel.createNoteState.collectUIState(
            state = {
                binding.progressBar.isVisible = it is UIState.Loading
            },
            onSuccess = {
                findNavController().navigateUp()
            }
        )

        viewModel.updateNoteState.collectUIState(
            state = {
                binding.progressBar.isVisible = it is UIState.Loading
            },
            onSuccess = {
                findNavController().navigateUp()
            }
        )
    }
}