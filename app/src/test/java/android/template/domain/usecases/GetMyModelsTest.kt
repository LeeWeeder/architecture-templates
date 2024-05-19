package android.template.domain.usecases

import android.template.data.repository.FakeMyModelRepository
import android.template.domain.model.MyModel
import android.template.domain.usecases.mymodel.GetMyModels
import kotlinx.coroutines.runBlocking
import org.junit.Before

class GetNotesTest {

    private lateinit var getMyModels: GetMyModels
    private lateinit var fakeRepository: FakeMyModelRepository

    @Before
    fun setUp() {
        fakeRepository = FakeMyModelRepository()
        getMyModels = GetMyModels(fakeRepository)

        val myModelsToInsert = mutableListOf<MyModel>()
        ('a'..'z').forEachIndexed { _, c ->
            myModelsToInsert.add(
                MyModel(
                    name = c.toString()
                )
            )
        }
        myModelsToInsert.shuffle()
        runBlocking {
            myModelsToInsert.forEach { fakeRepository.upsertMyModel(it) }
        }
    }
}