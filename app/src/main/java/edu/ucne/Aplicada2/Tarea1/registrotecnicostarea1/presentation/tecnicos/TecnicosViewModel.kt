package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.tecnicos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.local.entities.TecnicoEntity
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.repository.TecnicosRepository
import kotlinx.coroutines.launch

class TecnicosViewModel(
    private val tecnicosRepository: TecnicosRepository
): ViewModel() {
    fun saveTecnico(tecnico: TecnicoEntity) {
        viewModelScope.launch {
            tecnicosRepository.save(tecnico)
        }
    }

    suspend fun findTecnico(id: Int): TecnicoEntity? {
        return tecnicosRepository.find(id)
    }

    fun deleteTecnico(tecnico: TecnicoEntity) {
        viewModelScope.launch {
            tecnicosRepository.delete(tecnico)
        }
    }

    /*companion object {
       val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
           @Suppress("UNCHECKED_CAST")
           override fun <T : ViewModel> create(
               modelClass: Class<T>,
               extras: CreationExtras
           ): T {
               // Get the Application object from extras
               val application = checkNotNull(extras[APPLICATION_KEY])


               return  TecnicosViewModel(

               ) as T
           }
       }*/
}