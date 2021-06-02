package mustafaozhan.github.com.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.addRepeatingJob
import androidx.recyclerview.widget.DiffUtil
import com.github.mustafaozhan.basemob.adapter.BaseVBRecyclerViewAdapter
import com.github.mustafaozhan.basemob.bottomsheet.BaseVBBottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collect
import mustafaozhan.github.com.model.History
import mustafaozhan.github.com.ui.databinding.FragmentHistoryBinding
import mustafaozhan.github.com.ui.databinding.ItemHistoryBinding
import mustafaozhan.github.com.ui.history.HistoryEffect
import mustafaozhan.github.com.ui.history.HistoryEvent
import mustafaozhan.github.com.viewmodel.history.HistoryViewModel
import javax.inject.Inject

class HistoryFragment : BaseVBBottomSheetDialogFragment<FragmentHistoryBinding>() {

    @Inject
    lateinit var historyViewModel: HistoryViewModel

    private lateinit var historyAdapter: HistoryAdapter

    override fun getViewBinding() = FragmentHistoryBinding.inflate(layoutInflater)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeStates()
        observeEffects()
    }

    private fun initViews() {
        historyAdapter = HistoryAdapter(historyViewModel.getEvent())
        binding.recyclerViewHistory.adapter = historyAdapter
    }

    private fun observeStates() = viewLifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        historyViewModel.state.collect {
            historyAdapter.submitList(it.historyList)
        }
    }

    private fun observeEffects() = viewLifecycleOwner.addRepeatingJob(Lifecycle.State.STARTED) {
        historyViewModel.effect.collect { viewEffect ->
            when (viewEffect) {
                is HistoryEffect.OpenForecast -> navigate(
                    R.id.historyFragment,
                    HistoryFragmentDirections.actionHistoryFragmentToForecastFragment(viewEffect.text),
                    false
                )
            }
        }
    }
}

class HistoryAdapter(
    private val historyEvent: HistoryEvent
) : BaseVBRecyclerViewAdapter<History, ItemHistoryBinding>(CalculatorDiffer()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CalculatorVBViewHolder(
        ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class CalculatorVBViewHolder(itemBinding: ItemHistoryBinding) :
        BaseVBRecyclerViewAdapter.BaseVBViewHolder<History, ItemHistoryBinding>(itemBinding) {

        override fun onItemBind(item: History) = with(itemBinding) {
            txtHistory.text = item.text
            itemBinding.root.setOnClickListener {
                historyEvent.onItemClick(item)
            }
        }
    }

    class CalculatorDiffer : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History) = false

        override fun areContentsTheSame(oldItem: History, newItem: History) = false
    }
}
