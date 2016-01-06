package net.ivyventures.rstudio.boggle

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@RequestMapping(path = '/board', consumes = 'application/json')
class BoggleController {
    @Autowired
    BoggleService boggleService

    @RequestMapping(method = RequestMethod.POST)
    List<String> score(@RequestBody(required = true) List<List<String>> rows) {
        assert rows.size() == 4
        assert rows.every { it.size() == 4 }
        assert rows.every { row -> row.every { it.size() == 1 }}
        log.info("Scoring $rows")
        boggleService.findWords(rows)
    }
}
