package feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import components.ButtonComponent
import components.ClickableRegisterTextComponent
import components.DividerTextComponent
import components.HeadingTextComponents
import components.NormalTextComponents
import components.PasswordTextFieldComponent
import components.TextFieldComponent
import components.UnderLineNormalTextComponent
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ){
                NormalTextComponents(value = "My Aplication")
                HeadingTextComponents(value = "Welcome Back")
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldComponent(
                    labelValue = "Email",
                    painterResource = painterResource("ic_email.png"),
                    keyboardType = KeyboardType.Email,
                    onTextSelected = {},
                    errorStatus =false,
                    errorMsg = ""
                )
                PasswordTextFieldComponent(
                    labelValue = "Password",
                    painterResource = painterResource("ic_password.png"),
                    onTextSelected = {

                    },
                    errorStatus = false,
                    errorMsg = ""
                )

                Spacer(modifier = Modifier.height(10.dp))

                UnderLineNormalTextComponent(
                    value = "Forget your Password",
                    onTextSelected = {
                    })

                Spacer(modifier = Modifier.height(200.dp))

                ButtonComponent(
                    value = "Login",
                    onButtonClicked = {

                    },
                    true
                )
                Spacer(modifier = Modifier.height(10.dp))

                DividerTextComponent()

                ClickableRegisterTextComponent(
                    value = "Dont have an account?",
                    onTextSelected = {

                    }
                )

            }
        }
    }
}

