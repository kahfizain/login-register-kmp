@file:OptIn(ExperimentalMaterial3Api::class)

package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import thema.Primary
import thema.PurpleGrey40
import thema.Secondary
import thema.TextColor

@Composable
fun NormalTextComponents(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = Color.Gray,
        textAlign = TextAlign.Center

    )
}

@Composable
fun HeadingTextComponents(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = Color.Black,
        textAlign = TextAlign.Center
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    labelValue: String,
    painterResource: Painter,
    keyboardType: KeyboardType,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false,
    errorMsg: String
) {
    var textValue by remember {
        mutableStateOf(TextFieldValue(""))
    }


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(4.dp)),
        value = textValue,
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = thema.Primary,
            focusedLabelColor = thema.Primary,
            cursorColor = thema.Primary,

            ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, // Set the input type here
            imeAction = ImeAction.Next
        ),
        onValueChange = {
            textValue = it
            onTextSelected(it.text)
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource,
                contentDescription = ""
            )
        },
        isError = !errorStatus
    )

    if (errorMsg != "") {
        Text(text = errorMsg, color = Color.Red)
    }
}

@Composable
fun PasswordTextFieldComponent(
    labelValue: String, painterResource: Painter,
    onTextSelected: (String) -> Unit,
    errorStatus: Boolean = false,
    errorMsg: String
) {
    var passwordValue by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(4.dp)),
        value = passwordValue,
        label = { Text(text = labelValue) },
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = thema.Primary,
            focusedBorderColor = thema.Primary,
            focusedLabelColor = thema.Primary,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, // Set the input type here
            imeAction = ImeAction.Done
        ),
        onValueChange = {
            passwordValue = it
            onTextSelected(it.text)
        },
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource,
                contentDescription = "")
        },
        trailingIcon = {
            val iconImage = if (passwordVisible) {
                //painterResource(id = R.drawable.ic_show_password)
                Icons.Filled.Visibility

            } else {
                Icons.Filled.VisibilityOff
                //painterResource(id = R.drawable.ic_hide_password)
            }

            var description = if (passwordVisible) {
                "Hide Password"
            } else {
                "Show Password"
            }

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = iconImage, contentDescription = description)


            }
        },

        visualTransformation = if (passwordVisible) VisualTransformation.None else
            PasswordVisualTransformation(),
        isError = !errorStatus,

        )
    if (errorMsg != "") {
        Text(text = errorMsg, color = Color.Red)
    }
}


@Composable
fun UnderLineNormalTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style =
            SpanStyle(color = Primary)
        ) {
            pushStringAnnotation(tag = value, annotation = value)
            append(value)
        }

    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline

        ),
        text = annotatedString, onClick = { i ->
            annotatedString.getStringAnnotations(i, i)
                .firstOrNull()?.also { span ->
                    if (span.item == value) {
                        onTextSelected(span.item)

                    }
                }
        })
}


@Composable
fun ButtonComponent(value: String, onButtonClicked: () -> Unit, isEnabled: Boolean = false) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        onClick = {
            onButtonClicked.invoke()
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = PurpleGrey40,
            thickness = 1.dp
        )

        Text(
            modifier = Modifier.padding(8.dp),
            fontSize = 16.sp, text = "or",
            color = TextColor
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            color = PurpleGrey40,
            thickness = 1.dp
        )

    }

}

@Composable
fun ClickableRegisterTextComponent(value: String, onTextSelected: (String) -> Unit) {
    val initialText = "Don't have an account? "
    val registerText = "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)) {
            pushStringAnnotation(tag = registerText, annotation = registerText)
            append(registerText)
        }

    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 16.dp),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
        ),
        text = annotatedString, onClick = { i ->
            annotatedString.getStringAnnotations(i, i)
                .firstOrNull()?.also { span ->
                    if (span.item == registerText) {
                        onTextSelected(span.item)

                    }
                }
        })
}



