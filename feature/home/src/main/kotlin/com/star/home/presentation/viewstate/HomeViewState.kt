package com.star.home.presentation.viewstate

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.star.core.domain.listdetails.Task
import com.star.core.extension.empty
import com.star.designsystem.theme.*
import java.util.*

data class HomeViewState(
    val isLoading: Boolean = false,
    val userInfo: UserInfo = UserInfo(),
    val todoLists: List<ListInfo> = listOf(),
    val dialogInfo: DialogInfo = DialogInfo()
)

data class UserInfo(
    val name: String = "DbTest",
    val email: String = "DbTest@dbtest.com",
    val pictureUrl: String = "bla"
)

data class DialogInfo(
    val shouldDisplayDialog: Boolean = false,
    val colorInfoList: List<ColorInfo> =
        listOf(
            ColorInfo(Green200),
            ColorInfo(Green250),
            ColorInfo(Green300),
            ColorInfo(Red200),
            ColorInfo(Red250),
            ColorInfo(Red300),
            ColorInfo(Deletion),
            ColorInfo(Yellow200),
            ColorInfo(Yellow250),
            ColorInfo(Yellow300),
            ColorInfo(Teal700),
            ColorInfo(Gray700),
        ),
    val iconInfoList: List<IconInfo> = listOf(
        IconInfo(Icons.Default.List),
        IconInfo(Icons.Default.Check),
        IconInfo(Icons.Default.Edit),
        IconInfo(Icons.Default.Favorite),
        IconInfo(Icons.Default.Notifications),
        IconInfo(Icons.Default.Phone),
        IconInfo(Icons.Default.ShoppingCart),
        IconInfo(Icons.Default.Star),
        IconInfo(Icons.Default.Warning),
        IconInfo(Icons.Default.Person),
        IconInfo(Icons.Default.Place),
        IconInfo(Icons.Default.Call),
        IconInfo(Icons.Default.PlayArrow),
        IconInfo(Icons.Default.Settings),
        IconInfo(Icons.Default.Search),
        IconInfo(Icons.Default.Face),
    )
)

data class IconInfo(
    val imageVector: ImageVector = Icons.Default.List,
    val color: Color = Green250,
    val isSelected: Boolean = false,
    val uuid: UUID = UUID.randomUUID()
)

data class ColorInfo(
    val color: Color = Green250,
    val isSelected: Boolean = false,
    val uuid: UUID = UUID.randomUUID()
)

data class ListInfo(
    val name: String = String.empty(),
    val selectedColorInfo: ColorInfo? = null,
    val selectedIconInfo: IconInfo? = null,
    val items: List<Task> = listOf(),
    val uuid: UUID = UUID.randomUUID(),
    val createdAt: Date = Date()
)
