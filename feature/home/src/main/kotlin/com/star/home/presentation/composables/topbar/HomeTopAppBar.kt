package com.star.home.presentation.composables.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.star.designsystem.spacer.SimpleSpacer
import com.star.designsystem.theme.*
import com.star.home.presentation.viewstate.UserInfo

private const val PICTURE_REF = "userPicture"
private const val NAME_REF = "userName"
private const val EMAIL_REF = "userEmail"
private const val DROP_DOWN_ARROW_REF = "dropDownArrow"

@Composable
fun HomeTopAppBar(
    userInfo: UserInfo = UserInfo(),
    onProfileClick: () -> Unit = {},
    onSortByDateClick: (() -> Unit)? = null,
    onSortByNameClick: (() -> Unit)? = null,
    onDeleteAllListsClick: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = Modifier.wrapContentHeight(),
        backgroundColor = BackgroundBrighter,
        title = {
            UserInfoLayout(
                userInfo,
                onProfileClick
            )
        },
        actions = {
            onSortByDateClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        painter = painterResource(
                            id = com.star.designsystem.R.drawable.ic_sort_by_time_24
                        ),
                        contentDescription = stringResource(
                            id =
                            com.star.designsystem.R.string.sort_tasks_button_created_date_description
                        ),
                        tint = Gray100,
                    )
                }
            }
            onSortByNameClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        painter = painterResource(
                            id = com.star.designsystem.R.drawable.ic_sort_by_alpha_24
                        ),
                        contentDescription = stringResource(
                            id = com.star.designsystem.R.string.sort_tasks_button_description
                        ),
                        tint = Gray100,
                    )
                }
            }
            onDeleteAllListsClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        painter = painterResource(
                            id = com.star.designsystem.R.drawable.ic_delete_all_24
                        ),
                        contentDescription = stringResource(
                            id = com.star.designsystem.R.string.delete_all_tasks_button_description
                        ),
                        tint = Gray100,
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalUnitApi::class)
@Composable
fun UserInfoLayout(
    userInfo: UserInfo = UserInfo(),
    onProfileClick: () -> Unit = {}
) {
    Surface(color = Color.Transparent) {

        ConstraintLayout(
            constraintSet = getConstraintSet(),
            modifier = Modifier.wrapContentSize().clickable { onProfileClick() }
        ) {
//            GlideImage(
//                model = userInfo.pictureUrl,
//                contentDescription = stringResource(
//                    id = com.star.feature.home.R.string.profile_picture_description
//                ),
//                modifier = Modifier
//                    .size(dimensionResource(id = com.star.designsystem.R.dimen.size_32))
//                    .clip(CircleShape)
//                    .border(
//                        width = dimensionResource(id = com.star.designsystem.R.dimen.size_1),
//                        color = Gray700,
//                        shape = CircleShape
//                    ).layoutId(PICTURE_REF)
//            )
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(
                    com.star.feature.home.R.string.profile_picture_description
                ),
                modifier = Modifier
                    .size(dimensionResource(id = com.star.designsystem.R.dimen.size_32))
                    .clip(CircleShape)
                    .border(
                        width = dimensionResource(id = com.star.designsystem.R.dimen.size_1),
                        color = Gray700,
                        shape = CircleShape
                    )
                    .layoutId(PICTURE_REF)
            )
            Text(
                text = userInfo.name,
                modifier = Modifier.layoutId(NAME_REF),
                color = Gray300,
                fontSize = TextUnit(value = 12f, type = TextUnitType.Sp)
            )
            Text(
                text = userInfo.email,
                modifier = Modifier.layoutId(EMAIL_REF),
                color = Green250,
                fontSize = TextUnit(value = 12f, type = TextUnitType.Sp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                modifier = Modifier
                    .size(dimensionResource(id = com.star.designsystem.R.dimen.size_16))
                    .layoutId(DROP_DOWN_ARROW_REF),
                tint = Gray300,
                contentDescription = null
            )
        }
    }
}

@Composable
@Preview()
fun UserInfoLayoutPreview() {
    UserInfoLayout()
}

internal fun getConstraintSet() = ConstraintSet {
    val pictureRef = createRefFor(PICTURE_REF)
    val nameRef = createRefFor(NAME_REF)
    val emailRef = createRefFor(EMAIL_REF)
    val dropDownArrowRef = createRefFor(DROP_DOWN_ARROW_REF)

    constrain(pictureRef) {
        start.linkTo(parent.start, margin = 4.dp)
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
    }

    constrain(nameRef) {
        top.linkTo(pictureRef.top, margin = (-1).dp)
        start.linkTo(pictureRef.end, margin = 8.dp)
    }

    constrain(emailRef) {
        start.linkTo(nameRef.start)
        top.linkTo(nameRef.bottom, margin = 4.dp)
    }

    constrain(dropDownArrowRef) {
        start.linkTo(nameRef.end, margin = 2.dp)
        top.linkTo(nameRef.top)
        bottom.linkTo(nameRef.bottom)
    }
}