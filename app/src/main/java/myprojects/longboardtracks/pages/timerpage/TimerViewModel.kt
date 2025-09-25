package myprojects.longboardtracks.pages.timerpage

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import myprojects.longboardtracks.pages.basepage.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor() : BaseViewModel() {
    private val _timerValue = MutableStateFlow(5)
    private var timerJob: Job? = null
    val timerValue = _timerValue.asStateFlow()

    fun startTimer(start: Int = 5) {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            for (t in start downTo 0) {
                _timerValue.update { t }
                delay(1000)
            }
        }
    }

    fun addTime(time: Int){
        //TODO: Implement addTime +5Sek
    }

    fun stopTimer(){
        timerJob?.cancel()
        timerJob = null
    }
}