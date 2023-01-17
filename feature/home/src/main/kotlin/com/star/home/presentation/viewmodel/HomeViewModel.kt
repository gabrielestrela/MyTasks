package com.star.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.star.core.coroutines.CoroutineDispatcherProvider
import com.star.home.domain.usecase.GetHomeStateUseCase
import com.star.home.presentation.model.ListOrderTypes
import com.star.home.domain.usecase.SaveHomeStateUseCase
import com.star.home.presentation.viewstate.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val saveHomeState: SaveHomeStateUseCase,
    private val getHomeState: GetHomeStateUseCase,
    private val dispatcher: CoroutineDispatcherProvider,
) : ViewModel() {
    private val _state: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    init {
        savableProcedure {
            viewModelScope.launch(dispatcher.io()) {
                with(getHomeState()) {
                    this?.let {
                        _state.update {
                            it.copy(
                                userInfo = this.userInfo,
                                isLoading = this.isLoading,
                                todoLists = this.todoLists,
                            )
                        }
                    }
                }
            }
        }
    }

    fun setDialogVisibility(shouldDisplay: Boolean) {
        _state.update {
            it.copy(dialogInfo = it.dialogInfo.copy(shouldDisplayDialog = shouldDisplay))
        }
    }

    fun updateSelectedColorInfo(index: Int) {
        _state.update {
            it.copy(
                dialogInfo = it.dialogInfo.copy(
                    colorInfoList = updateSelectedColor(it.dialogInfo.colorInfoList, index)
                )
            )
        }
    }

    fun resetSelectedColor() {
        _state.update {
            it.copy(
                dialogInfo = it.dialogInfo.copy(
                    colorInfoList = resetAnySelectedColor(it.dialogInfo.colorInfoList)
                )
            )
        }
    }

    fun resetSelectedIconList() {
        _state.update {
            it.copy(
                dialogInfo = it.dialogInfo.copy(
                    iconInfoList = resetIconInfoList(it.dialogInfo.iconInfoList)
                )
            )
        }
    }

    private fun resetIconInfoList(iconInfoList: List<IconInfo>): List<IconInfo> =
        iconInfoList.map {
            IconInfo(
                imageVector = it.imageVector,
                color = it.color,
                uuid = it.uuid,
                isSelected = false
            )
        }

    private fun resetAnySelectedColor(colorInfoList: List<ColorInfo>): List<ColorInfo> {
        return colorInfoList
            .map { ColorInfo(color = it.color, isSelected = false, uuid = it.uuid) }
    }

    private fun updateSelectedColor(colorInfoList: List<ColorInfo>, index: Int): List<ColorInfo> {
        val finalList = colorInfoList
            .map { ColorInfo(color = it.color, isSelected = false, uuid = it.uuid) }
            .toMutableList()
        val elementToUpdate = finalList[index]
        finalList[index] = elementToUpdate.copy(isSelected = elementToUpdate.isSelected.not())
        return finalList
    }

    fun updateSelectedIconInfo(index: Int) {
        _state.update {
            it.copy(
                dialogInfo = it.dialogInfo.copy(
                    iconInfoList = updateSelectedIconByIndex(it.dialogInfo.iconInfoList, index)
                )
            )
        }
    }

    private fun updateSelectedIconByIndex(
        iconInfoList: List<IconInfo>, index: Int
    ): List<IconInfo> {
        val finalList = iconInfoList.mapIndexed { loopIndex, iconInfo ->
            if (index == loopIndex) {
                iconInfo.copy(isSelected = true)
            } else iconInfo.copy(isSelected = false)
        }

        return finalList
    }

    fun onDialogAddList(listInfo: ListInfo) {
        savableProcedure {
            _state.update {
                it.copy(
                    todoLists = addCreatedListInfo(listInfo),
                    dialogInfo = it.dialogInfo.copy(
                        colorInfoList = resetAnySelectedColor(it.dialogInfo.colorInfoList)
                    )
                )
            }
        }
    }

    private fun addCreatedListInfo(listInfo: ListInfo): List<ListInfo> {
        val updatedList = _state.value.todoLists.toMutableList()
        updatedList.add(listInfo)
        return updatedList
    }

    fun onSortListsByNameClick() {
        savableProcedure {
            _state.update {
                it.copy(
                    todoLists = sortListsBy(ListOrderTypes.NAME, it.todoLists)
                )
            }
        }
    }

    fun onSortListsByCreationDate() {
        savableProcedure {
            _state.update {
                it.copy(
                    todoLists = sortListsBy(ListOrderTypes.CREATION_DATE, it.todoLists)
                )
            }
        }
    }

    fun onDeleteAllLists() {
        savableProcedure { _state.update { it.copy(todoLists = listOf()) } }
    }

    private fun savableProcedure(block: () -> Unit) {
        block()
        viewModelScope.launch(dispatcher.io()) {
            saveState()
        }
    }

    private fun saveState() {
        saveHomeState(_state.value)
    }

    private fun sortListsBy(sortType: ListOrderTypes, todoLists: List<ListInfo>): List<ListInfo> =
        when (sortType) {
            ListOrderTypes.NAME -> todoLists.sortedBy { it.name.lowercase() }
            ListOrderTypes.CREATION_DATE -> todoLists.sortedBy { it.createdAt }
        }

    companion object {
        private const val PROFILE_URL =
            "https://images.unsplash.com/" +
                    "photo-1575459780154-bc56e94c5076?" +
                    "ixlib=rb-4.0.3&" +
                    "ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&" +
                    "auto=format&" +
                    "fit=crop&" +
                    "w=640&" +
                    "q=960"
    }
}
