package microservices.bookservice

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


data class Author(val id : String, val name : String)

@FeignClient("author-service")
@Service
interface AuthorClient {

    @GetMapping("/{id}")
    fun get(@PathVariable id:String) : Author
}