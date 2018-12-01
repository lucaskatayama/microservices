package microservices.authorservice

import com.fasterxml.jackson.annotation.JsonIgnore
import jdk.nashorn.internal.runtime.options.Option
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

@Document
data class Author(@JsonIgnore @Id var _id : String?, var id : String = "", var name: String = "")


interface AuthorRepository : MongoRepository<Author, String> {
    fun findByIdEquals(id: String) : Optional<Author>
}