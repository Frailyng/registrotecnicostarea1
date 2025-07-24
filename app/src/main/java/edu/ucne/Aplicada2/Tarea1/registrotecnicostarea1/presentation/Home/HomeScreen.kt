package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.Home


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import android.Manifest
import android.content.Context
import android.os.Build
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.Common.NotificationHandler
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.presentation.navigation.Screen

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    context: Context
) {
    //notificaciones
    // Solo necesitamos pedir permiso en Android 13 (TIRAMISU) o superior
    val postNotificationPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    } else {
        // En versiones anteriores, el permiso se concede implícitamente
        null
    }

    val notificationHandler = NotificationHandler(context)

    // Solicita el permiso cuando la pantalla se carga por primera vez
    LaunchedEffect(key1 = true) {
        if (postNotificationPermission != null &&
            !postNotificationPermission.status.isGranted) {
            postNotificationPermission.launchPermissionRequest()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            notificationHandler.showSimpleNotification()
        }) {
            Text(text = "Mostrar Notificación")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
    ) {

        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 8.dp),
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)),
            color = MaterialTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                OutlinedButton(
                    onClick = { navController.navigate(Screen.TecnicoList) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color(0xFFF5F5F5),
                        contentColor = Color.DarkGray
                    )
                ) {
                    Text(
                        text = "Registro Técnico",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                }

                OutlinedButton(
                    onClick = { navController.navigate(Screen.PrioridadList) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color(0xFFF5F5F5),
                        contentColor = Color.DarkGray
                    )
                ) {
                    Text(
                        text = "Registro Prioridades",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                }

                OutlinedButton(
                    onClick = { navController.navigate(Screen.TicketsList) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color(0xFFF5F5F5),
                        contentColor = Color.DarkGray
                    )
                ) {
                    Text(
                        text = "Registro Tickets",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                }

                OutlinedButton(
                    onClick = { navController.navigate(Screen.CompraList)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color(0xFFF5F5F5),
                        contentColor = Color.DarkGray
                    )
                ) {
                    Text(
                        text = "Registro Compras",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                }

                OutlinedButton(
                    onClick = { notificationHandler.showSimpleNotification() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = Color(0xFFF5F5F5),
                        contentColor = Color.DarkGray
                    )
                ) {
                    Text(
                        text = "Mostrar notificacion",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )
                }

            }
        }
    }
}