package cl.daracenad.elearning.exercises.domain

import cl.daracenad.elearning.exercises.domain.services.course.download.AnswerDownloadSrv
import javax.inject.Inject

class UpdateDataUserCase  @Inject constructor(
    private val answerUpdateTable: AnswerDownloadSrv
)  {
    suspend operator fun invoke(token:String):Int {
        answerUpdateTable.invoke()
        return 0
    }
/*
        var donwloadTableResponse = repository.downloadSysTablesFromAPI(token)
        if(donwloadTableResponse!!.success == 0){
            val dts:List<DownloadTableEntity>
                    = donwloadTableResponse.downloadTables.map { it.toEntity() }
            repository.downloadTablesInsertFromDB(dts)
            repository.parameterInsertFormDB(AppParameterEntity("download_tables","OK", 0))

            //for (dt in dts){


        }
*/

}