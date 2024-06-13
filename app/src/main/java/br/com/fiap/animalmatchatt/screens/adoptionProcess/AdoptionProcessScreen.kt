package br.com.fiap.animalmatchatt.screens.adoptionProcess

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.animalmatchatt.R.*
import br.com.fiap.animalmatchatt.components.ColumnProcessListComponent
import br.com.fiap.animalmatchatt.components.TitleComponent
import br.com.fiap.animalmatchatt.model.ErrorResponse
import br.com.fiap.animalmatchatt.model.ProcessAd
import br.com.fiap.animalmatchatt.model.ProcessResponse
import br.com.fiap.animalmatchatt.services.ProcessService
import br.com.fiap.animalmatchatt.services.RetrofitFactory
import br.com.fiap.animalmatchatt.utils.TokenManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AdoptionProcessScreen (navController: NavController) {
    val context = LocalContext.current
    val retrofitFactory = RetrofitFactory()
    val processService = retrofitFactory.create(ProcessService::class.java)
    val tokenManager = TokenManager(context)
    val token = tokenManager.getAccessToken()

    var listProcess by remember { mutableStateOf<List<ProcessAd>>(emptyList()) }

    var searchTextState by remember {
        mutableStateOf("")
    }

    val poppyns = FontFamily(
        Font(font.poppins_regular)
    )

    LaunchedEffect(Unit) {
        val call = processService.getProcessesByAdopter(token)
        call.enqueue(object : Callback<ProcessResponse> {
            override fun onResponse(
                call: Call<ProcessResponse>,
                response: Response<ProcessResponse>
            ) {
                if (response.isSuccessful) {
                    listProcess = response.body()?.processes ?: emptyList()
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = if (errorBody != null) {
                        try {
                            val errorResponse =
                                Gson().fromJson(errorBody, ErrorResponse::class.java)
                            errorResponse.error


                        } catch (e: Exception) {
                            "Erro ao buscar processos"
                        }
                    } else {
                        "Erro ao buscar processos"
                    }
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProcessResponse>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    Column (
        modifier = Modifier.padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier.padding(vertical = 10.dp)
        ){
            TitleComponent(
                title = "Processo de Adoção",
                colorText = color.orange,
                nameProfileFontSize = 25.sp
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Box{
            OutlinedTextField(
                value = searchTextState,
                onValueChange = {
                    searchTextState = it
                },
                modifier = Modifier
                    .width(330.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorResource(id = color.orange),
                    focusedBorderColor = colorResource(id = color.orange)
                ),
                placeholder = {
                    Text(
                        text = "Procure pelos processos",
                        fontFamily = poppyns,
                        color = colorResource(id = color.gray_title)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        listProcess = listProcess.filter { process ->
                            process.animalName.contains(searchTextState, ignoreCase = true)
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = colorResource(id = color.gray_title)
                        )
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Column {
            Row(
                modifier = Modifier.padding(horizontal = 30.dp).offset(x = 20.dp)
            ) {
                TitleComponent(
                    title = "Nome",
                    colorText = color.orange,
                    nameProfileFontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(33.dp))

                TitleComponent(
                    title = "Tempo",
                    colorText = color.orange,
                    nameProfileFontSize = 18.sp
                )

                Spacer(modifier = Modifier.width(30.dp))

                TitleComponent(
                    title = "Status Adoção",
                    colorText = color.orange,
                    nameProfileFontSize = 18.sp
                )
            }

            Row {
                LazyColumn(modifier = Modifier.fillMaxWidth().padding(12.dp),
                ) {
                    items(listProcess) { process ->
                        ColumnProcessListComponent(
                            process = process,
                            navController
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }
    }

    Canvas(
        modifier = Modifier.height(10.dp).width(10.dp).offset(x = (126).dp, y = 230.dp)
    ) {
        drawLine(
            color = Color(0xFFFF8B6A),
            start = Offset(size.width/ 2, 10f),
            end = Offset(size.width / 2, 999f),
            strokeWidth = 1.5f,
            cap = StrokeCap.Round,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
        )
    }

    Canvas(
        modifier = Modifier.height(10.dp).width(10.dp).offset(x = 215.dp, y = 230.dp)
    ) {
        drawLine(
            color = Color(0xFFFF8B6A),
            start = Offset(size.width/ 2, 10f),
            end = Offset(size.width / 2, 999f),
            strokeWidth = 1.5f,
            cap = StrokeCap.Round,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
        )
    }
}