package net.ivyventures.rstudio.boggle
import groovy.util.logging.Slf4j
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct
import java.util.stream.Stream

import static java.util.function.Function.identity
import static java.util.stream.Collectors.toList
import static java.util.stream.Collectors.toSet

@Slf4j
@Service
class BoggleService {
    Set<String> dictionary
    Set<String> stems
    IntRange range = 0..3

    @PostConstruct
    void loadDictionary() {
        dictionary = (new ClassPathResource('boggle.dict').inputStream.readLines()) as Set<String>
        stems = dictionary.parallelStream().map { it -> it[0..<3] }.distinct().collect(toSet())
        log.info("Loaded ${dictionary.size()} words with ${stems.size()} stems")
    }

    List<String> findWords(List<List<String>> rows) {
        log.info("Checking board against dictionary with ${dictionary.size()} words")

        List<Stream<String>> streams = []
        [range, range].combinations().each { List<Integer> it ->
            streams += findWords(rows[it[0]][it[1]], it[0], it[1], rows)
        }

        List<String> results = Stream.of(streams.toArray()).flatMap(identity()).sorted().distinct().collect(toList())
        log.info("Found $results")

        results
    }

    Stream<String> findWords(String prefix, int r, int c, List<List<String>> letters) {
        letters = letters.collectNested { it }
        letters[r][c] = ''

        List<Stream<String>> streams = []

        if (prefix.length() == 3 && !(prefix in stems)) return Stream.empty()

        [-1..1, -1..1].combinations().each { List<Integer> it ->
            Integer row = r + it[0]
            Integer col = c + it[1]

            if (prefix.length() < 10 && (it[0] || it[1]) && range.contains(row) && range.contains(col) && letters[row][col]) {
                streams += findWords(prefix + letters[row][col], row, col, letters)
            }

        }

        if (prefix.length() >= 3 && prefix in dictionary) {
            streams += Stream.of(prefix)
        }

        Stream.of(streams.toArray()).parallel().flatMap(identity())
    }
}
