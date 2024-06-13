package br.com.fiap.animalmatchatt.screens.confirmAdoptionConcluded

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.ButtonComponent
import br.com.fiap.animalmatchatt.components.CircleButtonComponent
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.ProcessResponse
import br.com.fiap.animalmatchatt.services.AnimalService
import br.com.fiap.animalmatchatt.services.ProcessService
import br.com.fiap.animalmatchatt.services.RetrofitFactory
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ConfirmAdoptionToConcludedScreen (processId: String) {
    val context = LocalContext.current.applicationContext
    val retrofitFactory = RetrofitFactory()
    val processService = retrofitFactory.create(ProcessService::class.java)
    val tokenManager = TokenManager(context)
    val token = tokenManager.getAccessToken()

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier.weight(1f, fill = true)
        ) {
            Image(
                painter = painterResource(id = drawable.adote),
                contentDescription = "adote",
                modifier = Modifier
                    .size(280.dp)
            )
        }

        Box (
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 60.dp)
                .offset(y = (-50).dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "Confirme ou cancele a adoção!",
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Se você já está com seu novo amigo! Confirme abaixo para concluir o processo de adoção!",
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Desistiu da adoção? Cancele o processo abaixo!",
                    color = colorResource(id = color.gray_title),
                    fontFamily = poppyns,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row{
                    CircleButtonComponent(
                        iconImage = Icons.Default.Clear,
                        colorButton = color.red,
                        onClick = {
                            processService.cancelProcess(processId, token).enqueue(object : Callback<Void> {
                                override fun onResponse(
                                    call: Call<Void>,
                                    response: Response<Void>
                                ) {
                                    if (response.isSuccessful) {
                                        Toast.makeText(context, "Adoção cancelada com sucesso!", Toast.LENGTH_SHORT).show()
                                    } else {
                                        val errorBody = response.errorBody()?.string()
                                        val errorMessage = if (errorBody != null) {
                                            try {
                                                val errorResponse =
                                                    Gson().fromJson(errorBody, ErrorResponse::class.java)
                                                errorResponse.error
                                            } catch (e: Exception) {
                                                "Erro ao atualizar processo"
                                            }
                                        } else {
                                            "Erro ao atualizar processo"
                                        }
                                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    )

                    Spacer(modifier = Modifier.width(50.dp))

                    CircleButtonComponent(
                        iconImage = Icons.Default.Check,
                        colorButton = color.green_light,
                        onClick = {
                            processService.updateProcessToConcluded(processId, token).enqueue(object : Callback<Void> {
                            override fun onResponse(
                                call: Call<Void>,
                                response: Response<Void>
                            ) {
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Adoção concluida com sucesso!", Toast.LENGTH_SHORT).show()
                                } else {
                                        val errorBody = response.errorBody()?.string()
                                        val errorMessage = if (errorBody != null) {
                                            try {
                                                val errorResponse =
                                                    Gson().fromJson(errorBody, ErrorResponse::class.java)
                                                errorResponse.error
                                            } catch (e: Exception) {
                                                "Erro ao atualizar processo"
                                            }
                                        } else {
                                            "Erro ao atualizar processo"
                                        }
                                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                    }
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                                }
                            })
                        }
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}