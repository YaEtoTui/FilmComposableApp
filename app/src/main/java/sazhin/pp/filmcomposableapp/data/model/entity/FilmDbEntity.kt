package sazhin.pp.filmcomposableapp.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FilmDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "filmName") val name: String?,
    @ColumnInfo(name = "filmYear") val year: Int?,
    @ColumnInfo(name = "pathPhoto") val pathPhoto: String?,
    @ColumnInfo(name = "filmDescription") val description: String?,
)