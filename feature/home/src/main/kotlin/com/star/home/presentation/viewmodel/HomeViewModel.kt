package com.star.home.presentation.viewmodel

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import com.star.home.domain.model.ListOrderTypes
import com.star.home.presentation.event.HomeEvents
import com.star.home.presentation.viewstate.*
import de.palm.composestateevents.consumed
import de.palm.composestateevents.triggered
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val _state: MutableStateFlow<HomeViewState> = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(userInfo = UserInfo(
                name = "Gabriel Estrela",
                email = "gabriel.estrela@email.com",
                pictureUrl = PROFILE_URL
            ))
        }
    }

    private val _event: MutableStateFlow<HomeEvents> = MutableStateFlow(HomeEvents())
    val event = _event.asStateFlow()

    fun onAddListClick() {
        _event.update { it.copy(showCreateListDialog = triggered) }
    }

    fun onAddListClickComplete() {
        _event.update { it.copy(showCreateListDialog = consumed) }
    }

    fun setDialogVisibility(shouldDisplay: Boolean) {
        _state.update {
            it.copy(dialogInfo = it.dialogInfo.copy(shouldDisplayDialog = shouldDisplay)) }
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

    private fun updateSelectedIconByIndex(iconInfoList: List<IconInfo>, index: Int): List<IconInfo> {
        val finalList = iconInfoList.mapIndexed { loopIndex, iconInfo ->
            if (index == loopIndex) {
                iconInfo.copy(isSelected = true)
            } else iconInfo.copy(isSelected = false)
        }

        return finalList
    }

    fun onDialogAddList(listInfo: ListInfo) {
        _state.update {
            it.copy(
                todoLists = addCreatedListInfo(listInfo),
                dialogInfo = it.dialogInfo.copy(
                    colorInfoList = resetAnySelectedColor(it.dialogInfo.colorInfoList)
                )
            )
        }
    }

    private fun addCreatedListInfo(listInfo: ListInfo): List<ListInfo> {
        val updatedList = _state.value.todoLists.toMutableList()
        updatedList.add(listInfo)
        return updatedList
    }

    fun onSortListsByNameClick() {
        _state.update {
            it.copy(
                todoLists = sortListsBy(ListOrderTypes.NAME, it.todoLists)
            )
        }
    }

    fun onSortListsByCreationDate() {
        _state.update {
            it.copy(
                todoLists = sortListsBy(ListOrderTypes.CREATION_DATE, it.todoLists)
            )
        }
    }

    fun onDeleteAllLists() {
        _state.update { it.copy(todoLists = listOf()) }
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
