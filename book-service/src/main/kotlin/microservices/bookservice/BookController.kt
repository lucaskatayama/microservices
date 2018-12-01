package microservices.bookservice

import org.apache.commons.codec.binary.Base32
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.security.SecureRandom


@RestController
@RequestMapping
class BookController(val repo: BookRepository) {

    @GetMapping
    fun get(
            @RequestParam(required = false, defaultValue = "20") size: Int,
            @RequestParam(required = false, defaultValue = "0") page: Int,
            @RequestParam(required = false, defaultValue = "ASC") sort: Sort.Direction,
            @RequestParam(required = false, defaultValue = "name") by: String
            ): Page<Book> {
        return repo.findAll(PageRequest.of(page, size, sort, by))
    }

    @PostMapping
    fun create(@RequestBody book: Book): Book {
        // Create a secure random generator (it's thread-safe)
        val sr = SecureRandom()

        // Allocate an array for 8 bytes
        val random = ByteArray(8)

        // Generate the random bytes
        sr.nextBytes(random)

        book.id = Base32().encodeToString(random).replace("=", "")
        return repo.save(book)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String) = repo.findByIdEquals(id)
}