package mustafaozhan.github.com.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {
    // region SEED
    abstract val state: StateFlow<BaseState>?
    abstract val effect: SharedFlow<BaseEffect>?
    abstract val event: BaseEvent?
    abstract val data: BaseData?
    // endregion
}
