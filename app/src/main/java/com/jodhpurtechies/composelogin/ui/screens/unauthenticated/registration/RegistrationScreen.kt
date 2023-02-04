package com.jodhpurtechies.composelogin.ui.screens.unauthenticated.registration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jodhpurtechies.composelogin.R
import com.jodhpurtechies.composelogin.ui.common.customComposableViews.SmallClickableWithIconAndText
import com.jodhpurtechies.composelogin.ui.common.customComposableViews.TitleText
import com.jodhpurtechies.composelogin.ui.screens.unauthenticated.registration.state.RegistrationUiEvent
import com.jodhpurtechies.composelogin.ui.theme.AppTheme
import com.jodhpurtechies.composelogin.ui.theme.ComposeLoginTheme

@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit
) {

    val registrationState by remember {
        registrationViewModel.registrationState
    }

    if (registrationState.isRegistrationSuccessful) {
        LaunchedEffect(key1 = true) {
            onNavigateToAuthenticatedRoute.invoke()
        }
    } else {
        // Full Screen Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {

            // Back Button Icon
            SmallClickableWithIconAndText(
                modifier = Modifier
                    .padding(horizontal = AppTheme.dimens.paddingLarge)
                    .padding(top = AppTheme.dimens.paddingLarge),
                iconContentDescription = stringResource(id = R.string.navigate_back),
                iconVector = Icons.Outlined.ArrowBack,
                text = stringResource(id = R.string.back_to_login),
                onClick = onNavigateBack
            )


            // Main card Content for Registration
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.paddingLarge)
            ) {

                Column(
                    modifier = Modifier
                        .padding(horizontal = AppTheme.dimens.paddingLarge)
                        .padding(bottom = AppTheme.dimens.paddingExtraLarge)
                ) {

                    // Heading Registration
                    TitleText(
                        modifier = Modifier.padding(top = AppTheme.dimens.paddingLarge),
                        text = stringResource(id = R.string.registration_heading_text)
                    )

                    /**
                     * Registration Inputs Composable
                     */
                    RegistrationInputs(
                        registrationState = registrationState,
                        onEmailIdChange = { inputString ->
                            registrationViewModel.onUiEvent(
                                registrationUiEvent = RegistrationUiEvent.EmailChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onMobileNumberChange = { inputString ->
                            registrationViewModel.onUiEvent(
                                registrationUiEvent = RegistrationUiEvent.MobileNumberChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onPasswordChange = { inputString ->
                            registrationViewModel.onUiEvent(
                                registrationUiEvent = RegistrationUiEvent.PasswordChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onConfirmPasswordChange = { inputString ->
                            registrationViewModel.onUiEvent(
                                registrationUiEvent = RegistrationUiEvent.ConfirmPasswordChanged(
                                    inputValue = inputString
                                )
                            )
                        },
                        onSubmit = {
                            registrationViewModel.onUiEvent(registrationUiEvent = RegistrationUiEvent.Submit)
                        }
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewRegistrationScreen() {
    ComposeLoginTheme {
        RegistrationScreen(onNavigateBack = {}, onNavigateToAuthenticatedRoute = {})
    }
}