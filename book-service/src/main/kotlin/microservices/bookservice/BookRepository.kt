package microservices.bookservice

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Document
data class Book(@JsonIgnore @Id val _id: String? = null,
                var name: String = "",
                var isbn: String = "",
                var id: String = "",
                @JsonFormat(pattern = "yyyy-MM-dd")
                var publishedAt: Date? = null,
                var author: String = "",
                var imgUrl: String = "")

@Repository
interface BookRepository : MongoRepository<Book, String> {
    fun findByIdEquals(id: String): Optional<Book>
}