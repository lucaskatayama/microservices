package microservices.authorservice

import org.apache.commons.codec.binary.Base32
import org.springframework.web.bind.annotation.*
import java.security.SecureRandom
import java.util.*


@RestController
@RequestMapping("")
class AuthorController (val repo: AuthorRepository) {

    @GetMapping
    fun getAll() = repo.findAll()

    @PostMapping
    fun create(@RequestBody author: Author) : Author {
        val sr = SecureRandom()

        // Allocate an array for 8 bytes
        val random = ByteArray(8)

        // Generate the random bytes
        sr.nextBytes(random)

        author.id = Base32().encodeToString(random).replace("=", "")


        return repo.save(author)
    }

    @GetMapping("{id}")
    fun getById(@PathVariable id : String): Optional<Author> = repo.findByIdEquals(id)
}