package com.example.ecommerce.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Composable
fun CostumeTextField(modifier: Modifier = Modifier,
                    value: String,
                    hinText:String,
                    hintStyle: TextStyle,
                    colorScheme:ColorScheme,
                    trailingIcon: @Composable() (() -> Unit)? = null,
                    onValueChange:(String)->Unit,
                    errorMessage: String?=null,
                    supportingText: @Composable() (() -> Unit)? = null,
                     visualTransformation: VisualTransformation = VisualTransformation.None,
                     keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
                     leadingIcon: @Composable() (() -> Unit)? = null,
                     size: Dp? = 16.dp


                    ) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = {
            Text(
                text = hinText,
                style = hintStyle)
        },
        isError = errorMessage!=null,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorScheme.secondary,
            unfocusedContainerColor = colorScheme.secondary,
            errorBorderColor = colorScheme.error
        ),
        supportingText = supportingText,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(size !!),

    )

}