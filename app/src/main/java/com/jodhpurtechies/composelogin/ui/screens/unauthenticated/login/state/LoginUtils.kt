package com.jodhpurtechies.composelogin.ui.screens.unauthenticated.login.state

import com.jodhpurtechies.composelogin.R
import com.jodhpurtechies.composelogin.ui.common.state.ErrorState

val emailOrMobileEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_email_mobile
)

val passwordEmptyErrorState = ErrorState(
    hasError = true,
    errorMessageStringResource = R.string.login_error_msg_empty_password
)