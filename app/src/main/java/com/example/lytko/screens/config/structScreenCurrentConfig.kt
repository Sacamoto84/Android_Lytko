package com.example.lytko.screens.config

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.lytko.R

//MARK: Структура для построения строки в картинке настроек
data class StructScreenCurrentConfig(
    val r: Int = R.drawable.right,              //id иконки
    val label: String = "?",     //текст строки
    val lastLabel: String = ".", // Последняя строка
    val lastLabelLight: Boolean = false, // Если екгу то последняя строка рисуется более всетлее
    val rLast: Int = R.drawable.right,          //id последней иконки
    val visible: Boolean = true, //Отображать строку
    val enable: Boolean = true,  //Для отображения серого и отсуствии реакции
    val route: String = "",      //Путь для перехода
)


//MARK: Собрание общих данных которые и определяют готовое устройство
data class DeviceData
(
    var name       : String = "1",       //Имя устройства ? Ванная
    var nameChipId : String = "2",       //имя устройста по чипу








    //Список длля построения и обновления данных в списке
    val ScreenConfigList: SnapshotStateList<StructScreenCurrentConfig> = mutableStateListOf<StructScreenCurrentConfig>(),

    )


